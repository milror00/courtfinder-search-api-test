package uk.gov.justice.digital.courtfinder.searchapi.factories;

public class FakeDataFactory {


	public static String getBaseUrl(){
		if ( ConfigurationFactory.getEnvironment() == ConfigurationFactory.developmentEnvironment )
	       return "http://54.72.152.89/search/results.json";
		else
		return "https://courttribunalfinder.service.gov.uk/";
	}

}
