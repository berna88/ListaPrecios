package com.consistent.listadepreciosPortlet.portlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;


import com.consistent.listadepreciosPortlet.constants.ServiceListaPreciosPortletKeys;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

/**
 * @author bernardohernadez
 */ 
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=root//Cuervo//Tienda",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ServiceListaPreciosPortletKeys.ServiceListaPrecios,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ServiceListaPreciosPortlet extends MVCPortlet {
	
	private static Log log = LogFactoryUtil.getLog(ServiceListaPreciosPortlet.class.getName());
	
	private final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String JDBC_CONNECTION = "jdbc:mysql://mx56.hostgator.mx:3306/basicoin_cuervo?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false&serverTimezone=EST5EDT";
	private static String JDBC_USER = "basicoin_cuervo";
	private static String JDBC_PASS = "#Cuerv0";
	
	@Override
		public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
				throws IOException, PortletException {
		log.info("<---- Resouce Method ---->");
		
		PrintWriter out = resourceResponse.getWriter();
		out.println("Resource URL is created with Liferay Tag - liferay-portlet:resourceURL");
		out.flush();
			// TODO Auto-generated method stub
			super.serveResource(resourceRequest, resourceResponse);
		}

	@Override
		public void render(RenderRequest renderRequest, RenderResponse renderResponse)
				throws IOException, PortletException {
		log.info("<--- render --->");
			try {
				String resultado = obtenerListaDePrecio();
				com.consistent.listadepreciosPortlet.models.Producto producto = new com.consistent.listadepreciosPortlet.models.Producto(resultado);
				renderRequest.setAttribute("Productos", producto.getProductos());
			} catch (Exception e) {
				// TODO: handle exception
				throw new PortletException(e);
			}
		
			super.render(renderRequest, renderResponse);
		}
	
	
	private String obtenerListaDePrecio() {
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
		System.out.println(sql);
		 try{
			 Class.forName(JDBC_DRIVER);  
			 connection= DriverManager.getConnection(  
			 JDBC_CONNECTION,JDBC_USER,JDBC_PASS);  
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
		 }
		return resultado;
	}

}
