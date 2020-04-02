package uk.gov.justice.digital.courtfinder.searchapi.steps;



import static org.junit.Assert.assertTrue;

import java.util.List;

import uk.gov.justice.digital.courtfinder.searchapi.factories.FakeDataFactory;
import uk.gov.justice.digital.courtfinder.searchapi.helper.CourtFinderAdapter;
import uk.gov.justice.digital.courtfinder.searchapi.helper.JsonRestfulRequest;
import cucumber.api.java.en.*;

public class CourtFinderSearchAPISteps {

	private JsonRestfulRequest request;
	private CourtFinderAdapter adapter;
	
	public CourtFinderSearchAPISteps(JsonRestfulRequest request){
		this.request = request;
		adapter = new CourtFinderAdapter(request);
	}
	

	@Given("^I search by postcode \"(.*?)\" and area of law \"(.*?)\"$")
	public void i_search_by_postcode_and_area_of_law(String postcode, String aol) throws Throwable {
	    adapter.setBaseUrl(FakeDataFactory.getBaseUrl());
		adapter.addParameter("postcode", postcode);
		adapter.addParameter("aol", aol);
	    adapter._GETRequest("search/results.json");
	}
	
	@Given("^I search name or address \"(.*?)\" of a court$")
	public void i_search_name_or_address_of_a_court(String nameOrAddress) throws Throwable {
	    adapter.setBaseUrl(FakeDataFactory.getBaseUrl());
		adapter.addParameter("q", nameOrAddress);
	    adapter._GETRequest("search/results.json");
	}

	@Then("^the court details will be:$")
	public void the_court_details_will_be(List<List<String>> courtValuesToVerify) throws Throwable {
		for (int index =1; index < courtValuesToVerify.size();index++)
		{
			String path = courtValuesToVerify.get(index).get(0);
			String expectedValue = courtValuesToVerify.get(index).get(1);
			String type = courtValuesToVerify.get(index).get(2);
			String actualValue = adapter.getResponseValue(path, type);
			assertTrue(String.format("Incorrect value for path : %s\nExpected Value: %s\nActual Value: %s",
					               path,expectedValue,actualValue),actualValue.equalsIgnoreCase(expectedValue));

		}
	}
	
	@Then("^the number of courts returned will be (\\d+)$")
	public void the_number_of_courts_returned_will_be(int numberOfCourts) throws Throwable {
		int actualNumberOfCourts = adapter.getCourtCount();
		  
		assertTrue(String.format("Invalid total number of courts :\nExpected: %d; Actual: %d ",numberOfCourts,actualNumberOfCourts),
				   actualNumberOfCourts == numberOfCourts);

	}
	
	@Then("^the value for the response path \"(.*?)\" of \"(.*?)\" is \"(.*?)\"$")
	public void the_value_for_the_response_path_of_is(String path, String type, String expectedValue) throws Throwable {
		String actualValue = adapter.getResponseValue(path, type);
		assertTrue(String.format("Incorrect value for path : %s\nExpected Value: %s\nActual Value: %s",
				               path,expectedValue,actualValue),actualValue.equalsIgnoreCase(expectedValue));
	}

	@Then("^the a response code is (\\d+)$")
	public void the_a_response_code_is(int expectedResponseCode) throws Throwable {
		int actualResponseCode = adapter.getResponseCode();
		assertTrue(String.format("Incorrect response code :\nExpected Value: %s\nActual Value: %s",
				expectedResponseCode,actualResponseCode),(actualResponseCode == expectedResponseCode));

	}


}
