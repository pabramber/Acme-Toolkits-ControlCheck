package acme.features.inventor.fuppo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fuppo.Fuppo;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorFuppoShowMineService implements AbstractShowService<Inventor, Fuppo> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected InventorFuppoRepository repository;
	
	// AbstractListService<Inventor, Fuppo> interface -----------------------
	
	@Override
	public boolean authorise(final Request<Fuppo> request) {
		assert request != null;
		
		boolean result;
		int chimpumId;
		Fuppo chimpum;

		chimpumId = request.getModel().getInteger("id");
		chimpum = this.repository.findOneFuppoById(chimpumId);
		result = (chimpum != null  && request.isPrincipal(chimpum.getComponent().getInventor()));
		
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
	public void unbind(final Request<Fuppo> request, final Fuppo entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final String defaultCurrency;
		final Money quantity;

		defaultCurrency = this.repository.getSystemConfiguration().getSystemCurrency();
		quantity = MoneyExchange.of(entity.getQuantity(), defaultCurrency).execute().getTarget();
		
		model.setAttribute("quantity", quantity);		
		request.unbind(entity, model, "code", "name", "statement", "creationMoment", "periodStart", "periodEnd", "moreInfo", "component.code");
	}

}
