package acme.features.inventor.fuppo;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fuppo.Fuppo;
import acme.entities.inventions.Invention;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorFuppoListMineService implements AbstractListService<Inventor, Fuppo> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected InventorFuppoRepository repository;

	// AbstractListService<Inventor, Fuppo> interface -----------------------

	@Override
	public boolean authorise(final Request<Fuppo> request) {
		assert request != null;
	
		return true;
	}

	@Override
	public Collection<Fuppo> findMany(final Request<Fuppo> request) {
		assert request != null;

		final Principal principal;
		final Collection<Fuppo> result;
		
		principal = request.getPrincipal();
		result = this.repository.findFupposByInventorId(principal.getActiveRoleId());

		return result;
	}

	@Override
	public void unbind(final Request<Fuppo> request, final Fuppo entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Invention invention;
		final String payload;
		
		invention = entity.getComponent();
		payload = invention.getCode() + "; " + invention.getName();
		
		request.unbind(entity, model, "code", "name", "statement");
		model.setAttribute("payload", payload);	
	}

}
