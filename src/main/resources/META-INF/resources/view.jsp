<%@ include file="/init.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>


<portlet:defineObjects />

<liferay-portlet:resourceURL var="resourceUrl1">
	<liferay-portlet:param  name="pdf" value="descargar"/>
</liferay-portlet:resourceURL>

<!-- Hoja de estilos datatables -->
<link href='<%=request.getContextPath()+"/css/jquery.dataTables.css" %>' rel="stylesheet" type="text/css" />
<!-- Hoja de estilos personalizada -->
<link rel="stylesheet" href='<%=request.getContextPath()+"/css/listaPreciosCuervo.css" %>'>

<div class="container">
	<div class="tituloSeccion-contenedor d-flex align-items-center justify-content-center">
	<img src='<%=request.getContextPath()+"/img/ListaPreciosBanner.png" %>' alt="" class="img-fliud">
	<div id="listaPrecios" class="mascara-tituloSeccion"></div>
		<h1 class="tituloSeccion position-absolute">
			Lista de Precios
		</h1>
	</div>
	<div class="row">
		<div class="col-md-12">
			<button onclick="callServeResource()" class="boton-listaPrecios" type="submit">Descargar lista de precios</button>
		</div>
	</div>
	
	<div class="row">
		<div class="table-responsive">
      <table id="tbl-politicas" class="display" style="width:100%">
        <thead>
            <tr class="header-cuervo">
                <th>Material</th>
                <th>Descripcion</th>
                <th>Capacidad</th>
                <th>Precio Normal</th>
                <th>Precio Banquete</th>
                <th>Precio Especial</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="nom" items="${Productos}">
	        	<tr>
	                <td class="td-politicas"><h4 class="name-product"><c:out value="${nom.nombre}"></c:out></h4><c:out value="${nom.material}"></c:out></td>
	                <td class="td-politicas top"><c:out value="${nom.descripcion}"></c:out></td>
	                <td class="td-politicas top"><c:out value="${nom.category}"></c:out></td>
	                <td class="td-politicas top"><c:out value="${nom.precioNormal}"></c:out></td>
	                <td class="td-politicas top"><c:out value="${nom.precioBanquete}"></c:out></td>
	                <td class="td-politicas top"><c:out value="${nom.precioEspecial}"></c:out></td>
	            </tr>
			</c:forEach>
        </tbody>
    </table>
    </div>
    </div>
</div>

<script src='<%=request.getContextPath()+"/js/jquery-1.11.3.min.js" %>'></script>
<script src='<%=request.getContextPath()+"/js/jquery.dataTables.js" %>'></script>
<script src='<%=request.getContextPath()+"/js/dataTables.rowGroup.min.js" %>'></script>
<script src='<%=request.getContextPath()+"/js/dataTables.buttons.js" %>'></script>
<script src='<%=request.getContextPath()+"/js/api.js" %>'></script>
<script src='<%=request.getContextPath()+"/js/datatable-custom.js" %>'></script>
<script src='<%=request.getContextPath()+"/js/portlet.js" %>'></script>

