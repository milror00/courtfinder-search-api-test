package uk.gov.justice.digital.courtfinder.searchapi.factories;


public class ConfigurationFactory {
	
	
	public final static String developmentEnvironment = "DEVELOPMENT";
	public final static String localEnvironment = "LOCAL";
	
	public static String getEnvironment(){
		if (!(System.getProperty("ENVIRONMENT") == null) )
			   return System.getProperty("ENVIRONMENT").toUpperCase();
		//return default if no system property set		
		return ConfigurationFactory.localEnvironment;
	}
	

}
