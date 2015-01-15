/**
 * 
 */
package com.naren.rest.test.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

/**
 * @author narender
 *
 */
public class RestClient {

	private static final Logger LOG=Logger.getLogger(RestClient.class);
	/**
	 * 
	 */
	public RestClient() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String url="http://localhost:9001/interview-task/task";
		String method="GET";
		String ctype="application/json";
		String accept="application/json";
		String requestBody="{\"name\":\"narender\",\"status\":\"Pending\",\"priority\":\"LOW\"}";
		String response=new RestClient().execute(url, method, ctype, accept, requestBody);
		LOG.debug("Response : "+response );
	}

	public String execute(String url,String method,String ctype,String accept,String requestBody){
		LOG.debug("Starts RestClient.execute() Method :" +method+" URL : "+url+" Content Type : "+ctype+" Accept : "+accept+" Request Body : "+requestBody);
		String response="";
		HttpClient client= new DefaultHttpClient();
		HttpResponse httpResponse=null;
		try {
			if(method.equals("GET")){
				HttpGet getRequet=new HttpGet(url);
				getRequet.setHeader("Accept", accept);
				getRequet.setHeader("Content-Type", ctype);
				httpResponse=client.execute(getRequet);
			}else if(method.equals("POST")){
				HttpPost postRequest=new HttpPost(url);
				StringEntity entity;
				entity = new StringEntity(requestBody);
				entity.setContentType(ctype);
				postRequest.setEntity(entity);
				postRequest.setHeader("Accept", accept);
				postRequest.setHeader("Content-Type", ctype);
				httpResponse=client.execute(postRequest);
			}else if(method.equals("PUT")){
				HttpPut httpPut=new HttpPut(url);
				StringEntity entity;
				entity = new StringEntity(requestBody);
				entity.setContentType("application/json");
				httpPut.setEntity(entity);
				httpPut.setHeader("Accept", "application/json");
				httpResponse=client.execute(httpPut);
			}else if(method.equals("DELETE")){
				HttpDelete httpDelete= new HttpDelete(url);
				httpDelete.setHeader("Accept", "application/json");
				httpDelete.setHeader("Content-Type", ctype);
				httpResponse=client.execute(httpDelete);
			}
			BufferedReader reader=new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			String isvResponse;
			while (( isvResponse = reader.readLine()) != null) {
				if( httpResponse.getStatusLine().getStatusCode() == 404) {
					LOG.debug("ERROR" +isvResponse);
					return null;
				} else if(httpResponse.getStatusLine().getStatusCode() >= 300) {
					LOG.debug("ERROR" +isvResponse);
				} else {
					response +=isvResponse;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			
		}
		LOG.debug("Ends RestClient.execute()");
		return response;
	}
}
