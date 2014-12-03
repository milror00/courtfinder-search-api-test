package uk.gov.justice.digital.courtfinder.searchapi.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.net.URLEncoder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;


public class JsonRestfulRequest {
	
	private List<String> _parameters = new ArrayList<String>();
	private int _responseCode = 0;
	private String _baseUrl = "";
	
	public int getResponseCode(){
		return _responseCode;
	}
	
	public void setBaseUrl(String url){
		_baseUrl = url;
	}
	
	public void addParameter(String parameter, String parameterValue){
		_parameters.add(String.format("%s=%s",parameter,parameterValue));
	}
	
	private String getURL(String command) throws MalformedURLException{
		String parameterString = "";
		for(int index = 0; index < _parameters.size(); index++){
			if (index == 0)
				parameterString = parameterString + _parameters.get(index).replace(" ", "+");
			else
			    parameterString = parameterString +"&"+ _parameters.get(index).replace(" ", "+");
		}
		return _baseUrl+command+"?"+parameterString;
	}
	
	public String _GETRequest(String command){
		String output = "";
		try {
			System.out.println(getURL(command));
			
			URL url = new URL(getURL(command));
			
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[] { new TrustAllX509TrustManager() }, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier( new HostnameVerifier(){
			    public boolean verify(String string,SSLSession ssls) {
			        return true;
			    }
			});
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
            conn.toString();
            _responseCode = conn.getResponseCode();
			if (conn.getResponseCode() != 200){
				System.out.println("Throwing Error: conn.getResponseCode()");
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode() + " -- "
						+ conn.getURL().toString());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String readResult;
			while ((readResult = br.readLine()) != null) {
				output = output + readResult;
			}
			conn.disconnect();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_parameters.clear();
		return output;
	}
	


}
