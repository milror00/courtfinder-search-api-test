package uk.gov.justice.digital.courtfinder.searchapi.helper;

import javax.naming.PartialResultException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.stub.java.rmi._Remote_Stub;

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

	public void _GETRequest(String baseUrl) {
		String stringResponse = restfulRequest._GETRequest(baseUrl);
		System.out.println("\n\n\n");
		System.out.println("BaseURL: "+ FakeDataFactory.getBaseUrl());
		System.out.println("Request: " + restfulRequest.getURL());
		System.out.println("\n");
		System.out.println("Response Code: " + restfulRequest.getResponseCode());
		System.out.println("Response:");
		System.out.println(stringResponse);
		System.out.println("\n\n\n");
		root = new JSONArray(stringResponse);
	}
	
	private String extractFromArrayToDouble(JSONObject source, String path) 
	{
		String name = path.substring(0, path.indexOf("["));
		int index = Integer.parseInt(path.substring(path.indexOf("[")+1,
				path.indexOf("]")));
		return Double.toString( source.getJSONArray(name).getDouble(index));
	}
	
	private String extractFromArrayToString(JSONObject source, String path) 
	{
		String name = path.substring(0, path.indexOf("["));
		int index = Integer.parseInt(path.substring(path.indexOf("[")+1,
				path.indexOf("]")));
		return source.getJSONArray(name).getString(index);
	}

	private JSONObject extractFromArray(JSONObject source, String path) 
	{
		String name = path.substring(0, path.indexOf("["));
		int index = Integer.parseInt(path.substring(path.indexOf("[")+1,
				path.indexOf("]")));
		return source.getJSONArray(name).getJSONObject(index);
	}

	private JSONObject extractFromAnnoymousArray(String path) {
		int index = Integer.parseInt(path.replace("[", "").replace("]", ""));
		return root.getJSONObject(index);
	}

	public String getResponseValue(String path, String type) {
		String[] pathParts = path.split("\\.");
		JSONObject jobj = null;
		for (int index = 0; index < pathParts.length; index++) {
			if (pathParts[index].contains("[")) {
				if ((index == 0))
					jobj = extractFromAnnoymousArray(pathParts[index]);
				else if (index == pathParts.length - 1){
					if (type.equalsIgnoreCase("string"))
					  return extractFromArrayToString(jobj, pathParts[index]);
					else if (type.equalsIgnoreCase("double"))
					  return extractFromArrayToDouble(jobj, pathParts[index]);	
				}
				else
					jobj = extractFromArray(jobj, pathParts[index]);
			}else{
				if (index == pathParts.length-1){
					if (type.endsWith("string"))
					return jobj.getString(pathParts[index]);
					else if (type.equalsIgnoreCase("double"))
						return Double.toString(jobj.getDouble(pathParts[index]));
				}else
					jobj = jobj.getJSONObject(pathParts[index]);
			}
		}
		return "";
	}

	public int getCourtCount() {
		return root.length();
	}

	public void setBaseUrl(String url) {
		restfulRequest.setBaseUrl(url);
	}

}
