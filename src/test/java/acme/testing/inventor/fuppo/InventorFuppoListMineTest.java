package acme.testing.inventor.fuppo;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorFuppoListMineTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/fuppo/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String name, final String statement, final String creationMoment, final String periodStart, final String periodEnd, final String quantity, final String moreInfo, final String inventionCode) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List my fuppos");
		super.checkCurrentUrl("http://localhost:8081/acme-toolkits-22.1/inventor/fuppo/list-mine");
		
		super.checkListingExists();
		super.checkNotListingEmpty();
		
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, statement);

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

	@Test
	@Order(20)
	public void negativeTest() {
		// There aren't any negative tests for this feature since it's a listing that
		// doesn't involve entering any data into any forms.
	}
	
	// Ancillary methods ------------------------------------------------------ 
	
}
