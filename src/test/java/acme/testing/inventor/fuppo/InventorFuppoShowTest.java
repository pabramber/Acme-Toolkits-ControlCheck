package acme.testing.inventor.fuppo;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class InventorFuppoShowTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/fuppo/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String name, final String statement, final String creationMoment, final String periodStart, final String periodEnd, final String quantity, final String moreInfo, final String inventionCode) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List my fuppos");
		super.sortListing(1, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("component.code", inventionCode);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("periodStart", periodStart);
		super.checkInputBoxHasValue("periodEnd", periodEnd);
		super.checkInputBoxHasValue("quantity", quantity);
		super.checkInputBoxHasValue("moreInfo", moreInfo);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/fuppo/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String code, final String name, final String statement, final String creationMoment, final String periodStart, final String periodEnd, final String quantity, final String moreInfo, final String inventionCode) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List my fuppos");
		super.clickOnListingRecord(recordIndex);
		
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
