package acme.features.inventor.fuppo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fuppo.Fuppo;
import acme.entities.inventions.Invention;
import acme.entities.inventions.InventionType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorFuppoCreateService implements AbstractCreateService<Inventor, Fuppo> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected InventorFuppoRepository repository;

	// AbstractCreateService<Inventor, Fuppo> interface ---------------------
	
	@Override
	public boolean authorise(final Request<Fuppo> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public Fuppo instantiate(final Request<Fuppo> request) {
		assert request != null;
		
		final Date todayPlus5Mins;
		final Date startDate;
		final Date finalDate;
		final Fuppo result;
		
		todayPlus5Mins = DateUtils.addMinutes(new Date(System.currentTimeMillis()), 5);
		startDate = DateUtils.addMonths(DateUtils.addMinutes(todayPlus5Mins, 1), 1);
		finalDate = DateUtils.addWeeks(DateUtils.addMinutes(startDate, 1), 1);
		
		result = new Fuppo();
		result.setName("");
		result.setStatement("");
		result.setPeriodStart(startDate);
		result.setPeriodEnd(finalDate);
		result.setMoreInfo("");

		return result;
	}

	@Override
	public void bind(final Request<Fuppo> request, final Fuppo entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Date today;
		final String inventionCode;
		final Invention invention;
				
		today = new Date(System.currentTimeMillis() - 1);
		inventionCode = request.getModel().getString("component.code");
		invention = this.repository.findOneInventionByCode(inventionCode);
		
		entity.setCreationMoment(today);
		entity.setComponent(invention);
		request.bind(entity, errors, "code", "name", "statement", "periodStart", "periodEnd", "quantity", "moreInfo", "component.code");
	}

	@Override
	public void validate(final Request<Fuppo> request, final Fuppo entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
				
		if (!errors.hasErrors("component.code")) {
			final Invention invention;
			final boolean exists;
			final int principalId;
			final int inventorId;
			
			invention = entity.getComponent();
			exists = invention.getId() != 0;
			
			errors.state(request, exists, "component.code", "inventor.fuppo.form.error.invalid");
			
			if (exists)	{
				principalId = request.getPrincipal().getActiveRoleId();
				inventorId = entity.getComponent().getInventor().getId();
				
				errors.state(request, this.repository.countByInventionId(invention.getId()) == 0, "component.code", "inventor.fuppo.form.error.duplicated");
				errors.state(request, principalId == inventorId, "component.code", "inventor.fuppo.form.error.no-permission");
				errors.state(request, invention.getInventionType().equals(InventionType.COMPONENT), "component.code", "inventor.fuppo.form.error.invalid-type");
			}
		}
		
		if (!errors.hasErrors("code")) {
			final String date;
			final String creationMoment;
			
			date = request.getModel().getString("code").split("-")[1] + request.getModel().getString("code").split("/")[1];
			creationMoment = new SimpleDateFormat("yyMMdd").format(entity.getCreationMoment());
			
			errors.state(request, date.equals(creationMoment), "code", "inventor.fuppo.form.error.not-today");
		}
		
		if (!errors.hasErrors("periodStart")) {
			final Date minStartDate;
			
			minStartDate = DateUtils.addMonths(entity.getCreationMoment(), 1);
			
			errors.state(request, entity.getPeriodStart().after(minStartDate), "periodStart", "inventor.fuppo.form.error.wrong-period-start");
		}
		
		if (!errors.hasErrors("periodEnd") && entity.getPeriodStart() != null) {
			final Date minFinalDate;
			
			minFinalDate = DateUtils.addWeeks(entity.getPeriodStart(), 1);

			errors.state(request, entity.getPeriodEnd().after(minFinalDate), "periodEnd", "inventor.fuppo.form.error.wrong-period-end");
		}
		
		if (!errors.hasErrors("quantity")) {
			final Boolean acceptedCurrency;
			
			acceptedCurrency = Arrays.stream(this.repository.getSystemConfiguration().getAcceptedCurrencies().split(";"))
									.map(String::trim)
									.anyMatch(currency -> currency.equals(entity.getQuantity().getCurrency()));
			
			errors.state(request, entity.getQuantity().getAmount() > 0, "quantity", "inventor.fuppo.form.error.negative-quantity");
			errors.state(request, acceptedCurrency, "quantity", "inventor.fuppo.form.error.non-accepted-currency");
		}
			
	}
	
	@Override
	public void unbind(final Request<Fuppo> request, final Fuppo entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model!=null;
		
		request.unbind(entity, model, "code", "name", "statement", "periodStart", "periodEnd", "quantity", "moreInfo", "component.code");
	}

	@Override
	public void create(final Request<Fuppo> request, final Fuppo entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
}
