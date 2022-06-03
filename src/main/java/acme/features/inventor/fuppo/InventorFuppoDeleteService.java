package acme.features.inventor.fuppo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fuppo.Fuppo;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorFuppoDeleteService implements AbstractDeleteService<Inventor, Fuppo> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorFuppoRepository repository;

	// AbstractDeleteService<Inventor, Fuppo> -------------------------------


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
	public void unbind(final Request<Fuppo> request, final Fuppo entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "name", "statement", "creationMoment", "periodStart", "periodEnd", "quantity", "moreInfo", "component.code");
	}

	@Override
	public void validate(final Request<Fuppo> request, final Fuppo entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Fuppo> request, final Fuppo entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
	}
	
}
