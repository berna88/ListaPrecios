package com.consistent.listadeprecios.conection;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.portlet.RenderRequest;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

public class Conection {
	
	 private static Log log = LogFactoryUtil.getLog(Conection.class.getName());
	 private String clientId;
	 private String clientSecret;
	 private RenderRequest request;
	 
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public RenderRequest getRequest() {
		return request;
	}

	public void setRequest(RenderRequest request) {
		this.request = request;
	}

	public Conection(String clientId, String clientSecret, RenderRequest request) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.request = request;
	}
	
	public Conection() {
		
	}
	
	public String getToken() {
		final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		String TOKEN_REQUEST_URL = themeDisplay.getPortalURL()+"/o/oauth2/token";
		log.info("Url: " + TOKEN_REQUEST_URL);
		try {
			OAuthClient client = new OAuthClient(new URLConnectionClient());
			OAuthClientRequest request = OAuthClientRequest.tokenLocation(TOKEN_REQUEST_URL)
					.setGrantType(GrantType.CLIENT_CREDENTIALS)
					.setClientId(clientId)
					.setClientSecret(clientSecret)
					.buildQueryMessage();
			request.addHeader("Accept", "application/json");
            request.addHeader("Content-Type", "application/json");
            String token  = client.accessToken(request, OAuth.HttpMethod.POST).getAccessToken();
            log.info("Token: "+token);
            return token; 
		} catch (OAuthSystemException e) {
			// TODO Auto-generated catch block
			e.getMessage();
			return "";
			//e.printStackTrace();
		} catch (OAuthProblemException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			e.getMessage();
			return "";
		}
		//return "";
	}
	
	public String getJSON() {
		try {
			log.info("getJSON ");
			final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			URL url = new URL(themeDisplay.getPortalURL()+"/o/listaPrecios/obtenerListaDePrecio");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Authorization","Bearer "+getToken());
			connection.setRequestProperty("Content-Type","application/json");
			connection.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String output;
			StringBuffer response = new StringBuffer();
			while ((output = in.readLine()) != null) {
				response.append(output);
			}
			in.close();
			return response.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			log.error("MalformedURLException: "+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
