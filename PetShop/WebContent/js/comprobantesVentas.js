/* Formatting function for row details - modify as you need */
function format ( d ) {
    // `d` is the original data object for the row
	var lineas = d.lineas;
	var html='<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';
	lineas.forEach(function(obj) { 
		html+='<tr>'+
        		'<td>ID</td>'+
        		'<td>PRODUCTO</td>'+
        		'<td>PRESENTACION</td>'+
        		'<td>CANTIDAD</td>'+
        		'<td>PRECIO</td>'+
        		'<td>SUBTOTAL</td>'+
        		'</tr>'
        		});
	html+='</table>';
    return 
	}

$(document).ready(function() {
		
	  var table = $('#dataTable').DataTable( {
	        "ajax":  {
	            "url": "VentasDatatable",
	            "method": "POST",
	            "contentType": "application/json",
	            "dataSrc": function ( json ) { return json; }
	          	},
	        searching : false,
	        dom: '<"top"l>rt<"bottom"Bp><"clear">',
	        buttons: [
	            'copy', 'csv', 'excel', 'pdf', 'print'
	        ],
	        "columns" : [
	            { "data": "idVenta" },
	            { "data": "usuario.nombre" },
	            { "data": "usuario.apellido" },
	            { "data": "usuario.direccion" },
	            { "data": "fecha" },
	            { "data": "estado" },
	            { "data": "medioPago.tipo" },
	            { "data": "total" },
	            {
	                "className":      'details-control',
	                "orderable":      false,
	                "data":           null,
	                "defaultContent": ''
	            }
	            ],
	        "order": [[0, 'asc']]
	    })
	
	
    // Add event listener for opening and closing details
    $('#dataTable tbody').on('click', 'td.details-control', function () {
        
    	var tr = $(this).closest('tr');
        var row = table.row( tr );
        
 
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child( format(row.data())).show();
            tr.addClass('shown');
        }
    } );
});