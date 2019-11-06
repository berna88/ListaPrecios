package com.consistent.listadepreciosPortlet.conection;

import com.consistent.listadepreciosPortlet.constants.ServiceListaPreciosPortletKeys;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
	
	public String getJSONSQL() {
		String sql = "select categoria.nb_categoria, producto.nb_producto_comercial, producto.cl_producto_sku, producto.nb_producto_sap , producto.ds_presentacion," + 
				" IFNULL( (select precio from basicoin_cuerv1.c_producto_lista_precio where id_lista_precio = 1 and id_producto = producto.id_producto) , '$ 00.00')  as precio_normal," + 
				" IFNULL( (select precio from basicoin_cuerv1.c_producto_lista_precio where id_lista_precio = 2 and id_producto = producto.id_producto) , '$ 00.00') as precio_alzado," + 
				" IFNULL( (select precio from basicoin_cuerv1.c_producto_lista_precio where id_lista_precio = 3 and id_producto = producto.id_producto) , '$ 00.00') as precio_especial" + 
				" from basicoin_cuerv1.c_producto producto" + 
				" inner join basicoin_cuerv1.c_marca marca on marca.id_marca = producto.id_marca" + 
				" inner join basicoin_cuerv1.c_categoria categoria on categoria.id_categoria = producto.id_categoria" + 
				" order by nb_categoria desc";
		String resultado = "";
		Connection connection = null;
		Statement statement = null;
		 log.info(sql);
		 try{
			 Class.forName(ServiceListaPreciosPortletKeys.JDBC_DRIVER);  
			 connection= DriverManager.getConnection(ServiceListaPreciosPortletKeys.JDBC_CONNECTION,ServiceListaPreciosPortletKeys.JDBC_USER,ServiceListaPreciosPortletKeys.JDBC_PASS);  
			 statement = connection.createStatement();
			 ResultSet rs = statement.executeQuery(sql);
			 JsonArray resultados = new JsonArray();
			 while(rs.next())  {
				JsonObject jObject = new JsonObject();
				jObject.addProperty("category", rs.getString("nb_categoria"));
				jObject.addProperty("nombre", rs.getString("nb_producto_comercial"));
				jObject.addProperty("material", rs.getString("cl_producto_sku").replaceFirst("^0+(?!$)", ""));
				jObject.addProperty("descripcion", rs.getString("nb_producto_sap"));
				jObject.addProperty("capacidad", rs.getString("ds_presentacion"));
				jObject.addProperty("precioNormal", rs.getString("precio_normal"));
				jObject.addProperty("precioBanquete", rs.getString("precio_alzado"));
				jObject.addProperty("precioEspecial", rs.getString("precio_especial"));
				
				resultados.add(jObject);
				
			 }
			 resultado = resultados.toString();
			 rs.close();
			 statement.close();
			 connection.close();
		 }catch(Exception e){
			 log.error(e.getMessage());
			 e.printStackTrace();
		 }
		return resultado;
	}
}
