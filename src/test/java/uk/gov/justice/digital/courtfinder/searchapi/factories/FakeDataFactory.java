package uk.gov.justice.digital.courtfinder.searchapi.factories;

public class FakeDataFactory {


	public static String getBaseUrl(){
		if ( ConfigurationFactory.getEnvironment() == ConfigurationFactory.developmentEnvironment )
	       return "http://54.72.152.89/search/results.json";
		else
		   //return "http://127.0.0.1:8000/search/results.json"; 
		return "https://courttribunalfinder.service.gov.uk/";
			//return "http://172.22.5.225:8000/search/results.json";
	}

}
