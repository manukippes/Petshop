/* Formatting function for row details - modify as you need */
function format ( d ) {
    // `d` is the original data object for the row
	var lineas = d.lineasList;
	var html='<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
		'<tr>'+
			'<th> </th>'+
			'<th>ID</th>'+
			'<th>PRODUCTO</th>'+
			'<th>PRESENTACION</th>'+
			'<th>CANTIDAD</th>'+
			'<th>PRECIO</th>'+
			'<th>SUBTOTAL</th>'+
		'</tr>'
	
	lineas.forEach(function(obj) { 
		html+='<tr>'+
				'<td> </td>'+
        		'<td>'+obj.idLineaVenta+'</td>'+
        		'<td>'+obj.producto.nombre+'</td>'+
        		'<td>'+obj.producto.presentacion+'</td>'+
        		'<td>'+obj.cantidad+'</td>'+
        		'<td>'+obj.precioUnitario+'</td>'+
        		'<td>'+obj.cantidad*obj.precioUnitario+'</td>'+
        		'</tr>'
        		});
	html+='</table>';
    return html;
};

function formatearFecha(fecha){
	var fechaF = new Date(fecha);
	var dia = fechaF.getDate();
	if (dia <=9){dia = "0"+dia};
	var mes = fechaF.getMonth()+1;
	if (mes <=9){mes = "0"+mes};
	var anio = fechaF.getFullYear();
	fechaParseada = anio+"-"+mes+"-"+dia
	return fechaParseada;
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
	        dom: '<"top"l>rt<"bottom"pB><"clear">',
	        buttons: [
	            'copy', 'csv', 'excel', 'pdf', 'print'
	        ],
	        "columns" : [
	            { "data": "ventaList.idVenta" },
	            { "data": "ventaList.usuario.nombre" },
	            { "data": "ventaList.usuario.apellido" },
	            { "data": "ventaList.usuario.direccion" },
	            { "data": "ventaList.fecha" },
	            { "data": "ventaList.estado" },
	            { "data": "ventaList.medioPago.tipo" },
	            { "data": "ventaList.total" },
	            {
	                "className":      'ampliar',
	                "orderable":      false,
	                "data":           null,
	                //"defaultContent": "<span id='btnAmpliar' class='fa fa-plus'></span>",
	                "render" : function (data,type,full,meta){
	                	var buttonID = "rollover"+full.idVenta;
	                	return "<a id='buttonID' class='btn btn-info'><span id='icono' class='fa fa-plus'></span></a>"; 
	                }
	            }
	            ],
	        "order": [[0, 'asc']]
	    })
	   
	    table.buttons(0,null).container().appendTo('#botonera');
	
	
    // Add event listener for opening and closing details
    $('#dataTable tbody').on('click', 'td.ampliar', function () {
        
    	var tr = $(this).closest('tr');
        var row = table.row( tr );
        
 
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
            $(this).find("#buttonID").removeClass("btn btn-danger");
            $(this).find('#buttonID').addClass("btn btn-info");
            $(this).find('#icono').removeClass("fa-minus");
            $(this).find('#icono').addClass("fa-plus");            
        }
        else {
            // Open this row
            row.child(format(row.data())).show();
            tr.addClass('shown');
            $(this).find("#buttonID").removeClass("btn btn-info");
            $(this).find('#buttonID').addClass("btn btn-danger");
            $(this).find('#icono').removeClass("fa-plus");
            $(this).find('#icono').addClass("fa-minus");
        }
    } )
	 
    $("#imprimirBtn").click(function(e){
		e.preventDefault();
		$(table).buttons('copy').action();
	})
	  
	
});