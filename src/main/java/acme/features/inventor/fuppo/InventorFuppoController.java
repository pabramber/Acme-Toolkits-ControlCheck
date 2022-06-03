package acme.features.inventor.fuppo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.fuppo.Fuppo;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorFuppoController extends AbstractController<Inventor, Fuppo> {

	// Internal state ------------------------------------------------------------
	
	@Autowired
	protected InventorFuppoShowMineService	showService;
	
	@Autowired
	protected InventorFuppoCreateService		createService;
	
	@Autowired
	protected InventorFuppoUpdateService		updateService;
	
	@Autowired
	protected InventorFuppoDeleteService		deleteService;
	
	@Autowired 
	protected InventorFuppoListMineService	listMineService;
	
	// Constructors --------------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		
		super.addCommand("list-mine", "list", this.listMineService);
	}
	
}
