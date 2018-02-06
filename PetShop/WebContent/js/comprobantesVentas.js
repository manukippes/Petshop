/**
 * 
 */
function format ( d ) {
    // `d` is the original data object for the row
    var datos = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
            '<td>Full name:</td>'+
            '<td>ALGO</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Extension number:</td>'+
            '<td>OTRA COSA</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Extra info:</td>'+
            '<td>And any further details here (images etc)...</td>'+
        '</tr>'+
    '</table>';
    return datos;
}

$(document).ready(function() {
	
	$.ajax({
	    'url': "VentasDatatable",
	    'method': "post",
	    'contentType': 'application/json'
	}).success( function(data) {
	    $('#example').dataTable( {
	        "aaData": data,
	        "columns": [
	            { "data": "idVenta" },
	            { "data": "usuario.nombre" },
	            { "data": "usuario.apellido" },
	            { "data": "usuario.direccion" },
	            { "data": "fecha" },
	            { "data": "estado" },
	            { "data": "medioPago.tipo" },
	            { "data": "total" },
	            {
	                "className":      'btnVer',
	                "orderable":      false,
	                "data":           null,
	                "defaultContent": ''
	            }
	        ]
	    })
	})
	
    // Add event listener for opening and closing details
    $('#example tbody').on('click', 'td.btnVer', function () {
        var tr = $(this).closest('tr');
        var row = table.row( tr );
 
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child( format(row.data()) ).show();
            tr.addClass('shown');
        }
    } );
} );