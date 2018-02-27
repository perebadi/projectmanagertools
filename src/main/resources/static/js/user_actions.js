window.onload = function(){
	
	$("#assign_project_modal").on("hidden.bs.modal", function () {
	    $("#addedProjectOk").hide();
	    $("#userInProject").hide();
	});
	
	//--- EDICIÓN USUARIO---
    //Obtenemos todos los botones de editar
    var userEditButton = document.getElementsByName("userEditButton");
    var userEditForm = document.getElementsByName("userEditForm");
    
    //Recorremos todos los botones
    for(var i = 0; i < userEditButton.length; i++){
    	//Linkamos el modal que abre
    	userEditButton[i].setAttribute('data-target', "#userEditForm_" + i);
    	//Linkamos el form
    	userEditForm[i].id = "userEditForm_" + i;
    }
    
    //--- ELIMINAR USUARIO---
    //Obtenemos todos los botones de eliminar
    var userDeleteButton = document.getElementsByName("userDeleteButton");
    
    //Recorremos todos los botones de eliminar
    for(var i = 0; i<userDeleteButton.length; i++){
    	//Programamos el evento on click del botón
    	userDeleteButton[i].onclick = function(){
    		//Guardamos una copia del botón
    		var userDeleteButton = this;
    		
    		//Mostramos el alert para confirmar que se quiere eliminar el usuario
    		bootbox.confirm({
    		    message: "Are you sure you want to delete the user?",
    		    buttons: {
    		        confirm: {
    		            label: 'Yes',
    		            className: 'btn-success'
    		        },
    		        cancel: {
    		            label: 'No',
    		            className: 'btn-danger'
    		        }
    		    },
    		    callback: function (result) {
    		    	//Comprovamos el resultado
    		    	if(result){
    		    		//Eliminamos el usuario
    		    		document.getElementById("userDeleteForm_" + userDeleteButton.dataset.username).submit();
    		    	}
    		    }
    		});
    	}
    }
    
    //--- EVENTOS DE LA TABLA USUARIOS---
    //Obtenemos la tabla del DOM y la convertimos en datatable
	var table = $('#usersTable').DataTable({
		"bFilter": false,
		"bLengthChange": false,
		"bInfo": false,
		"bPaginate": false,		
	});   
	
	//Obtenemos la página actual
	var pageNumber = document.getElementById("pageNumber").value;
	
	//Evento botón next
	document.getElementById("nextButton").onclick = function(){
		//Atributos URI
		var uriAttributes = "?page=" + (parseInt(pageNumber) + parseInt(1));
		
		//Obtenemos la búsqueda
		var search = document.getElementById("searchInput").value;
		
		//Comprovamos si hay búsqueda y la añadimos a la URI
		if(search != ""){
			uriAttributes += "&search=" + search;
		}
		
		//Redirigimos
		window.location.replace(
				window.location.protocol + "//" + window.location.host + window.location.pathname + uriAttributes);
	};
	
	//Evento botón prev
	document.getElementById("previousButton").onclick = function(){
		//Atributos URI
		var uriAttributes = "?page=" + (parseInt(pageNumber) - parseInt(1));
		
		//Obtenemos la búsqueda
		var search = document.getElementById("searchInput").value;
		
		//Comprovamos si hay búsqueda y la añadimos a la URI
		if(search != ""){
			uriAttributes += "&search=" + search;
		}
		
		//Redirigimos
		window.location.replace(
				window.location.protocol + "//" + window.location.host + window.location.pathname + uriAttributes);
	};
	
	//Evento enter al buscar
	document.getElementById("searchInput").onkeypress = function(e){
		//Comprovamos si se ha presionado enter
		if(e.keyCode == 13){
			//Obtenemos la búsqueda a realizar
			var search = document.getElementById("searchInput").value;
			
			//Si no se quiere limpiar la búsqueda
			if(search != ""){
				//Realizamos la búsqueda
				window.location.replace(
						window.location.protocol + "//" + window.location.host + window.location.pathname + 
							"?search=" + search);
			}else{
				//Realizamos la búsqueda
				window.location.replace(
						window.location.protocol + "//" + window.location.host + window.location.pathname);
			}
		}
	}
}