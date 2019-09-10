package com.consistent.cuervo.portlet;

import com.consistent.cuervo.constants.ServiceListaPreciosPortletKeys;
import com.consistent.cuervo.models.Producto;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

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
		public void render(RenderRequest renderRequest, RenderResponse renderResponse)
				throws IOException, PortletException {
		
			try {
				Producto producto = new Producto(ServiceListaPreciosPortletKeys.JSON_EXAMPLE);
				renderRequest.setAttribute("Productos", producto.getProductos());
			} catch (Exception e) {
				// TODO: handle exception
				throw new PortletException(e);
			}
		
			super.render(renderRequest, renderResponse);
		}

}
