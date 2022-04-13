package acme.features.any.inventor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;
import acme.roles.Inventor;

@Controller
public class AnyInventorController extends AbstractController<Any, Inventor>{

	@Autowired
	protected AnyInventorListService 	listService;
	
	@Autowired
	protected AnyInventorShowService 	showService;
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}
}
