package com.consistent.listadepreciosPortlet.portlet;

import com.consistent.listadepreciosPortlet.conection.Conection;
import com.consistent.listadepreciosPortlet.constants.ServiceListaPreciosPortletKeys;
import com.consistent.listadepreciosPortlet.models.Producto;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

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
				Conection conection = new Conection(ServiceListaPreciosPortletKeys.CLIENT_ID, ServiceListaPreciosPortletKeys.CLIENT_SECRET, renderRequest);
				Producto producto = new Producto(conection.getJSON());
				renderRequest.setAttribute("Productos", producto.getProductos());
			} catch (Exception e) {
				// TODO: handle exception
				throw new PortletException(e);
			}
		
			super.render(renderRequest, renderResponse);
		}

}
