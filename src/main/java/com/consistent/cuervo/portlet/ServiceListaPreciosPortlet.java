package com.consistent.cuervo.portlet;

import com.consistent.cuervo.constants.ServiceListaPreciosPortletKeys;
import com.consistent.cuervo.models.Producto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author bernardohernadez
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
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

	@Override
		public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
				throws IOException, PortletException {
			// TODO Auto-generated method stub
		System.out.println("Hola");
			super.doView(renderRequest, renderResponse);
		}

	public void getProductos(){
		String json = "[\n" +
				"  {\n" +
				"    \"category\": \"Tequila\",\n" +
				"    \"nombre\": \"kraken Ghost 1\",\n" +
				"    \"material\": \"8159\",\n" +
				"    \"descripcion\": \"Ron the kraken ghost 12/750ml 35% Alc. V\",\n" +
				"    \"capacidad\": \"Botella de 700ml\",\n" +
				"    \"precioNormal\": \"$100.00\",\n" +
				"    \"precioBanquete\": \"$0.00\",\n" +
				"    \"precioEspecial\": \"$0.00\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"category\": \"Tequila\",\n" +
				"    \"nombre\": \"kraken Ghost 2\",\n" +
				"    \"material\": \"8159\",\n" +
				"    \"descripcion\": \"Ron the kraken ghost 12/750ml 35% Alc. V\",\n" +
				"    \"capacidad\": \"Botella de 700ml\",\n" +
				"    \"precioNormal\": \"$100.00\",\n" +
				"    \"precioBanquete\": \"$0.00\",\n" +
				"    \"precioEspecial\": \"$0.00\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"category\": \"Tequila\",\n" +
				"    \"nombre\": \"kraken Ghost 3\",\n" +
				"    \"material\": \"8159\",\n" +
				"    \"descripcion\": \"Ron the kraken ghost 12/750ml 35% Alc. V\",\n" +
				"    \"capacidad\": \"Botella de 700ml\",\n" +
				"    \"precioNormal\": \"$100.00\",\n" +
				"    \"precioBanquete\": \"$0.00\",\n" +
				"    \"precioEspecial\": \"$0.00\"\n" +
				"  }\n" +
				"]";
		Gson gson = new Gson();
	    Type type = new TypeToken<List<Producto>>(){}.getType();
	    List<Producto> contactList = gson.fromJson(json, type);
		for(Producto productos: contactList) {
			System.out.println(productos.getNombre());
		}
	}

}
