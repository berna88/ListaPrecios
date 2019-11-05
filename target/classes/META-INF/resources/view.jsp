<%@ include file="/init.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<portlet:defineObjects />

<liferay-portlet:resourceURL var="resourceUrl1">
	<liferay-portlet:param  name="pdf" value="descargar"/>
</liferay-portlet:resourceURL>

<!-- Hoja de estilos datatables -->
<link href='<%=request.getContextPath()+"/css/jquery.dataTables.min.css" %>' rel="stylesheet" type="text/css" />
<!-- Hoja de estilos personalizada -->
<link rel="stylesheet" href='<%=request.getContextPath()+"/css/listaPreciosCuervo.css" %>'>



<div class="container">
	<div class="tituloSeccion-contenedor d-flex align-items-center justify-content-center">
		<img src='<%=request.getContextPath()+"/img/ListaPreciosBanner.jpg" %>' alt="" class="img-fliud">
		<div id="listaPrecios" class="mascara-tituloSeccion"></div>
		<h1 class="tituloSeccion position-absolute">
			Lista de Precios
		</h1>
		<p id="fecha"></p>
	</div>
	<div class="row">
		<div class="col-md-12">
			<button onclick="generate()" class="boton-listaPrecios" type="submit">Descargar lista de precios</button>
			
		</div>
	</div>
	
	<div class="row">
		<div class="table-responsive">
      <table id="tbl-politicas" class="display" style="width:100%;padding:0px 32px">
        <thead>
            <tr class="header-cuervo">
                <th>Material</th>
                <th>Descripcion</th>
                <th>Categoria</th>
                <th>Capacidad</th>
                <th>Precio Normal</th>
                <th>Precio Banquete</th>
                <th>Precio Especial</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="nom" items="${Productos}">
	        	<tr>
	                <td class="td-politicas"><h5 class="name-product"><c:out value="${nom.nombre}"></c:out></h5><c:out value="${nom.material}"></c:out></td>
	                <td class="td-politicas top"><c:out value="${nom.descripcion}"></c:out></td>
	                <td class="td-politicas top"><c:out value="${nom.category}"></c:out></td>
	                <td class="td-politicas top"><c:out value="${nom.capacidad}"></c:out></td>
	                <td class="td-politicas top"><c:out value="${nom.precioNormal}"></c:out></td>
	                <td class="td-politicas top"><c:out value="${nom.precioBanquete}"></c:out></td>
	                <td class="td-politicas top"><c:out value="${nom.precioEspecial}"></c:out>
	                
	            </tr>
	            
			</c:forEach>
        </tbody>
    </table>
        
    </div>
    </div>
</div>



<script src='<%=request.getContextPath()+"/js/jquery.dataTables.min.js" %>'></script>
<script src='<%=request.getContextPath()+"/js/dataTables.rowGroup.min.js" %>'></script>
<script src='<%=request.getContextPath()+"/js/dataTables.buttons.js" %>'></script>
<script src='<%=request.getContextPath()+"/js/datatable-custom.js" %>'></script>
<!--  <script src='<%=request.getContextPath()+"/js/jspdf.js" %>'></script>-->
  <script src='<%=request.getContextPath()+"/js/jspdf.debug.js" %>'></script>
   <script src='<%=request.getContextPath()+"/js/jspdf.plugin.autotable.js" %>'></script>
   

<script>
var today = new Date();
var dia = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0');
var ano = today.getFullYear();

var mes = getMes(mm);

today = 'Visualización el '+ dia + ' de ' + mes + ' del ' + ano;
document.getElementById('fecha').innerHTML = today;
function getMes(mes){
	switch (mes) {
	case "01": 
		return "Enero";
	break;
	case "02": 
		return "Febrero";
	break;
	case "03": 
		return "Marzo";
	break;
	case "04": 
		return "Abril";
	break;
	case "05": 
		return "Mayo";
	break;
	case "06": 
		return "Junio";
	break;
	case "07": 
		return "Julio";
	break;
	case "08": 
		return "Agosto";
	break;
	case "09": 
		return "Septiembre";
	break;
	case "10": 
		return "Octubre";
	break;
	case "11": 
		return "Noviembre";
	break;
	case "12": 
		return "Diciembre";
	break;
}
}
</script>
<script>
function generate() {
    var doc = new jsPDF();
	        // Simple html example
	        doc.autoTable({html: '#tbl-politicas'});
	        console.log(doc);
	        doc.save('ListaDePrecios.pdf');
	        
	    }
</script>
