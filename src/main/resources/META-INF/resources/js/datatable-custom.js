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