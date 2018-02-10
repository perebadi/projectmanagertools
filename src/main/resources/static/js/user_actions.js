window.onload = function(){
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
    		    message: "¿Quieres eliminar el usuario?",
    		    buttons: {
    		        confirm: {
    		            label: 'Si',
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
}