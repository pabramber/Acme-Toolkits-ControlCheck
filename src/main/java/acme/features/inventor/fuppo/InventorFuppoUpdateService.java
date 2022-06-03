package acme.features.inventor.fuppo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fuppo.Fuppo;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorFuppoUpdateService implements AbstractUpdateService<Inventor, Fuppo> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected InventorFuppoRepository repository;

	// AbstractUpdateService<Inventor, Fuppo> interface ---------------------
	
	@Override
	public boolean authorise(final Request<Fuppo> request) {
		assert request != null;

		boolean result;
		int fuppoId;
		Fuppo fuppo;

		fuppoId = request.getModel().getInteger("id");
		fuppo = this.repository.findOneFuppoById(fuppoId);
		result = (fuppo != null  && request.isPrincipal(fuppo.getComponent().getInventor()));

		return result;
	}

	@Override
	public Fuppo findOne(final Request<Fuppo> request) {
		assert request != null;
		
		Fuppo result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneFuppoById(id);

		return result;
	}

	@Override
	public void bind(final Request<Fuppo> request, final Fuppo entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "name", "statement", "periodStart", "periodEnd", "quantity", "moreInfo");
	}

	@Override
	public void validate(final Request<Fuppo> request, final Fuppo entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			final String date;
			final String creationMoment;
			
			date = request.getModel().getString("code").split("-")[1] + request.getModel().getString("code").split("/")[1];
			creationMoment = new SimpleDateFormat("yyMMdd").format(entity.getCreationMoment());
			
			errors.state(request, date.equals(creationMoment), "code", "inventor.fuppo.form.error.wrong-code-date");
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
		assert model != null;

		request.unbind(entity, model, "code", "name", "statement", "creationMoment", "periodStart", "periodEnd", "quantity", "moreInfo", "component.code");
	}

	@Override
	public void update(final Request<Fuppo> request, final Fuppo entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
		
}
