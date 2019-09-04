<%@ include file="/init.jsp" %>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

    <link href="https://nightly.datatables.net/css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
    <script src="https://nightly.datatables.net/js/jquery.dataTables.js"></script>

    <link href="https://cdn.datatables.net/rowgroup/1.0.2/css/rowGroup.dataTables.min.css" rel="stylesheet" type="text/css" />
    <script src="https://cdn.datatables.net/rowgroup/1.0.2/js/dataTables.rowGroup.min.js"></script>
    
    <script src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.js"></script>
    
    <link rel="stylesheet" href="css/datatable.css">
    <script type="text/javascript" src="js/datatable.js"> </script>
    <!-- Latest compiled and minified CSS -->

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<style>
#tbl-politicas .header-cuervo{
  background: #181818;
  color: white;
  font-family: "Source Sans Pro";
}
#tbl-politicas thead tr th{
  text-align: center;
}

#tbl-politicas tbody tr{
  background: #181818;
  color: white;
}

#tbl-politicas .group td {
    background: #181818 !important;
    padding: 10px 32px !important;
    font-size: 16px;
    color: #CCB874 !important;
    border-style: none;
    /* border-color: #808080 !important; */
    font-family: "Source Sans Pro" !important;
    text-transform: uppercase;
    border: none;
}


#tbl-politicas .group td:hover{
  border-color: #CCB874 !important;
  color: #CCB874 !important;
}
#tbl-politicas tbody td {
  border-top: .5px solid #808080;
  padding: 14px 8px;
}
#tbl-politicas tbody .td-politicas {
  text-align: center;
}
#tbl-politicas .icon-politicas{
  float: right;
}
#tbl-politicas .top{
  padding-top: 54px !important;
}
#tbl-politicas .name-product{
  color: #CCB874;
}

#tbl-politicas tbody .collapsed td {
  border-color: #808080;
  color:#808080 !important;
  margin: 5%;
}

#tbl-politicas tr td .head {
    border: 1px solid;
    margin: 1px;
    padding: 7px 16px;
}
</style>
<div class="container">
	<div class="row">
		<div class="table-responsive">
      <table id="tbl-politicas" class="display" style="width:100%">
        <thead>
            <tr class="header-cuervo">
                <th>Material</th>
                <th>Descripción</th>
                <th>Capacidad</th>
                <th>Precio Normal</th>
                <th>Precio Banquete</th>
                <th>Precio Especial</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="td-politicas" data-search="Tiger Nixon"><h4 class="name-product">Mausalen</h4>T. Nixon</td>
                <td class="td-politicas top">System Architect</td>
                <td class="td-politicas top">Tequila</td>
                <td class="td-politicas top">61</td>
                <td class="td-politicas top" data-order="1303689600">Mon 25th Apr 11</td>
                <td class="td-politicas top" data-order="320800">$320,800/y</td>
            </tr>
            <tr>
                <td class="td-politicas" data-search="Tiger Nixon"><h4 class="name-product">Mausalen</h4>T. Nixon</td>
                <td class="td-politicas top">System Architect</td>
                <td class="td-politicas top">Tequila</td>
                <td class="td-politicas top">61</td>
                <td class="td-politicas top" data-order="1303689600">Mon 25th Apr 11</td>
                <td class="td-politicas top" data-order="320800">$320,800/y</td>
            </tr>
            <tr>
                <td class="td-politicas" data-search="Tiger Nixon"><h4 class="name-product">Mausalen</h4>T. Nixon</td>
                <td class="td-politicas top">System Architect</td>
                <td class="td-politicas top">Tequila</td>
                <td class="td-politicas top">61</td>
                <td class="td-politicas top" data-order="1303689600">Mon 25th Apr 11</td>
                <td class="td-politicas top" data-order="320800">$320,800/y</td>
            </tr>
            <tr>
                <td class="td-politicas" data-search="Tiger Nixon"><h4 class="name-product">Mausalen</h4>T. Nixon</td>
                <td class="td-politicas top">System Architect</td>
                <td class="td-politicas top">Vodka</td>
                <td class="td-politicas top">61</td>
                <td class="td-politicas top" data-order="1303689600">Mon 25th Apr 11</td>
                <td class="td-politicas top" data-order="320800">$320,800/y</td>
            </tr>
        </tbody>
    </table>
    </div>
    </div>
</div>
    <script>
    $(document).ready(function() {
    	 var collapsedGroups = {};

    	    var table = $('#tbl-politicas').DataTable({
    	      searching: false,
    	      responsive: true,
    	      ordering:  false,
    	      paging: false,
    	      info: false,
    	      order: [[2, 'asc']],
    	      columnDefs: [
    	            {
    	                "targets": [ 2 ],
    	                "visible": false,
    	                "searchable": false
    	            }
    	        ],
    	      rowGroup: {
    	        // Uses the 'row group' plugin
    	        dataSrc: 2,
    	        startRender: function (rows, group) {
    	            var collapsed = !!collapsedGroups[group];
    	            console.log(rows);
    	            rows.nodes().each(function (r) {
    	                r.style.display = collapsed ? 'none' : '';
    	            });

    	            // Add category name to the <tr>. NOTE: Hardcoded colspan
    	            return $('<tr/>')
    	                .append('<td  class="name-group" colspan="8"><div class="head">'+ group + '<span class="icon-politicas glyphicon glyphicon-chevron-down"></span></div></td>')
    	                .attr('data-name', group)
    	                .toggleClass('collapsed', collapsed);

    	        }
    	      }

    	    });

    	   $('#tbl-politicas tbody').on('click', 'tr.group-start', function () {
    	        var name = $(this).data('name');
    	        collapsedGroups[name] = !collapsedGroups[name];
    	        table.draw(false);
    	        console.log(name);

    	    });

    	});

    </script>