package acme.testing.inventor.fuppo;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class InventorFuppoDeleteTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------

	@Test
	@Order(10)
	public void positiveTest() {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my fuppos");
		super.checkListingExists();
		super.clickOnListingRecord(0);

		super.clickOnSubmit("Delete");
		super.checkListingExists();

	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List my fuppos");
		super.clickOnListingRecord(0);
		
		final String componentInventor1Path = super.getCurrentPath();
		final String componentInventor1Query = super.getCurrentQuery().replace("?", "");
		
		super.signOut();
		
		super.signIn("inventor2", "inventor2");
		
		this.navigateWithQuery();
		
		super.navigate(componentInventor1Path, componentInventor1Query);
		
		super.checkPanicExists();
		super.checkErrorsExist();
		
		this.setQueryContext();
		
		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------ 
	
	private void navigateWithQuery() {
		final BrowserDriver bd = super.getDriver();
		bd.contextQuery += "&";
	}
	
	private void setQueryContext() {
		final BrowserDriver bd = super.getDriver();
		final Integer lastAdd = bd.contextQuery.lastIndexOf("&");
		bd.contextQuery = bd.contextQuery.substring(0, lastAdd);
	}

}
