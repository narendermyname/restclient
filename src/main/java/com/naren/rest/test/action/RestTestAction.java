package com.naren.rest.test.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author narender
 *
 */
public class RestTestAction extends ActionSupport{
	/**
	 * 
	 */
	private static final Logger LOG=Logger.getLogger(RestTestAction.class);
	private static final long serialVersionUID = -663918296295691317L;
	private InputStream stream;
	private String method;
	private String url;
	private String ctype;
	private String accept;
	private String requestBody;
	
	
	
	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	/**
	 * 
	 * @return
	 */
	public String executeRest(){
		LOG.debug("Mehotd"+method+"Accept"+accept+"Content Type "+ctype+"URL "+url+"Request Body"+requestBody);
		String response="";
		try {
			response = RestClient.execute(url, method, ctype, accept, requestBody);
		} catch (final Exception e) {
			LOG.error("Error while calling REST URL"+e.getMessage());
		}
		stream = new ByteArrayInputStream(response.getBytes());
		return SUCCESS;
	}
	
	

}
