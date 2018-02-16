jQuery.fn.AJAXmodal = function(opciones) {
	//Configuraciones
	var configuracion = {
		idModal : "",
		copyData : function(){},
		url : "",
		formData : function(){}
	}
	
	//Combinamos con las configuraciones recibidas por parametro
	jQuery.extend(configuracion, opciones);

	this.each(function() {
		//Click sobre el botón que muestra el modal
		$(this).click(function() {
			//Copiamos los datos que se quieren editar
			configuracion.copyData();
			
			//Evento click en el submit del modal
			$(".submitAJAXModal").click(function(){
				//Obtenemos los datos POST
				var datosPost = configuracion.formData();
				
				//Desactivamos el botón submit
				$(".submitAJAXModal").prop("disabled",true);
				
				//Obtenemos el token
				var token = document.getElementsByName("_csrf")[0].value;
				
				//AJAX Call
				$.ajax({
	    			type : "POST",
	    			contentType : "application/json",
	    			url : configuracion.url,
	    			data : JSON.stringify(datosPost),
	    			dataType : 'json',
	    			
	    			beforeSend: function(request) {
	    		        return request.setRequestHeader('X-CSRF-Token', token);
	    		    },
	    		    
	    			success : function(result) {
	    				if(result.status == "Done"){
	    					//Refresh
	    					window.location.reload();
	    				}
	    			},
	    			error : function(e) {
	    				alert("error");
	    			}
	    		});
				
			});
			
			//Al salir del modal, destruimos el evento submitAJAX
			$("#" + configuracion.idModal).on("hidden.bs.modal", function () {
			    $(".submitAJAXModal").unbind( "click" );
			});
			
			//Mostramos el modal
			$("#" + configuracion.idModal).modal('show');
		});
	});

	return this;

};