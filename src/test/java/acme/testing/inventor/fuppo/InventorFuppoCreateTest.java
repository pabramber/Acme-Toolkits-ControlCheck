package acme.testing.inventor.fuppo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorFuppoCreateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/fuppo/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String name, final String statement, final String quantity, final String inventionCode) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my fuppos");
		super.checkListingExists();

		final Date date = new Date(System.currentTimeMillis());
		final String c = code.split("-")[0] + "-" + new SimpleDateFormat("yyMM").format(date) + "-" + code.split("-")[1] + "/" + new SimpleDateFormat("dd").format(date); 
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("component.code", inventionCode);
		super.fillInputBoxIn("code", c);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("quantity", quantity);
		super.clickOnSubmit("Create");

		super.clickOnMenu("Inventor", "List my fuppos");
		super.checkListingExists();
		super.sortListing(1, "desc");
		super.checkColumnHasValue(recordIndex, 0, c);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, statement);

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/fuppo/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String code, final String name, final String statement, final String periodStart, final String periodEnd, final String quantity, final String moreInfo, final String inventionCode) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my fuppos");
		super.checkListingExists();

		super.clickOnButton("Create");
		super.fillInputBoxIn("component.code", inventionCode);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.fillInputBoxIn("quantity", quantity);
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
				
	}
	
	// Ancillary methods ------------------------------------------------------ 
	
}
