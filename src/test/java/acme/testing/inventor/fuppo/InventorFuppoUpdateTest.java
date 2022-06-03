package acme.testing.inventor.fuppo;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class InventorFuppoUpdateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/fuppo/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String statement, final String quantity) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my fuppos");
		super.checkListingExists();
		super.sortListing(1, "desc");
		super.clickOnListingRecord(0);

		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("quantity", quantity);
		super.clickOnSubmit("Update");
		super.checkListingExists();
		super.sortListing(1, "desc");
		super.checkColumnHasValue(0, 1, name);
		super.checkColumnHasValue(0, 2, statement);

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/fuppo/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String code, final String name, final String statement, final String periodStart, final String periodEnd, final String quantity, final String moreInfo) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my fuppos");
		super.checkListingExists();

		super.clickOnListingRecord(0);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.fillInputBoxIn("quantity", quantity);
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
				
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
