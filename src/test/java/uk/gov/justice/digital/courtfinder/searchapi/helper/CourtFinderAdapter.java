package uk.gov.justice.digital.courtfinder.searchapi.helper;


import com.urbangraphicstudio.json.JSONArray;
import com.urbangraphicstudio.json.JSONObject;

import uk.gov.justice.digital.courtfinder.searchapi.factories.FakeDataFactory;

public class CourtFinderAdapter {

	private JsonRestfulRequest restfulRequest;
	private JSONArray root;

	public CourtFinderAdapter(JsonRestfulRequest restfulRequest) {
		this.restfulRequest = restfulRequest;
	}

	public void addParameter(String parameter, String parameterValue) {
		restfulRequest.addParameter(parameter, parameterValue);
	}

	public void _GETRequest(String baseUrl) throws Exception {
		String stringResponse = restfulRequest._GETRequest(baseUrl);
		System.out.println("\n\n\n");
		System.out.println("BaseURL: "+ FakeDataFactory.getBaseUrl());
		System.out.println("Request: " + restfulRequest.getURL());
		System.out.println("Response Code: " + restfulRequest.getResponseCode());
		System.out.println("Response:");
		System.out.println(stringResponse);
		System.out.println("\n\n\n");
		if (restfulRequest.getResponseCode() == 200)
		    root = new JSONArray(stringResponse);
		else
			root = null;
	}
	
	private String extractFromArrayToDouble(JSONArray source, String path) 
	{
		return source.getSpecialString(path);
	}
	
	private String extractFromArrayToString(JSONArray source, String path)
	{
		return source.getSpecialString(path);
	}

	private JSONObject extractFromArray(JSONObject source, String path) 
	{
		return source.getSpecialJSONObject(path);
	}

	private JSONObject extractFromAnnoymousArray(String path) {
		return root.getSpecialJSONObject(path);
	}

	public String getResponseValue(String path, String type) {
		System.out.println(root);
		if (type.equalsIgnoreCase("string"))
		  return extractFromArrayToString(root, path);
		else if (type.equalsIgnoreCase("double"))
		  return extractFromArrayToDouble(root, path);
		return "";	
	}

	public int getCourtCount() {
		return root.length();
	}

	public void setBaseUrl(String url) {
		restfulRequest.setBaseUrl(url);
	}

	public int getResponseCode() {
		// TODO Auto-generated method stub
		return restfulRequest.getResponseCode();
	}

}
