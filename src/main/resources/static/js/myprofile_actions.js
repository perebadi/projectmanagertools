$(document).ready(function(){
	//Linkamos el desplegar el formulario al hacer clicl
	$("#resetPasswordText").click(function(){
        $("#resetPasswordForm").toggle(100);
    });
	
	//Ocultamos el formulario de reset de password
	$("#resetPasswordForm").hide();
	
	//Ajax para reiniciar la password
	$("#resetPasswordButton").click(function(event) {
		//Validem el formulari de reset password
		if ($("#resetPasswordForm").valid()) {
			//Obtenim el token
			var token = document.getElementsByName("_csrf")[0].value;

			var formData = {
				password : $('#passwordText').val(),
				confirmPassword : $('#confirmPasswordText').val()
			}
	        	
			$.ajax({
    			type : "POST",
    			contentType : "application/json",
    			url :"/api/resetpassword",
    			data : JSON.stringify(formData),
    			dataType : 'json',
    			beforeSend: function(request) {
    		        return request.setRequestHeader('X-CSRF-Token', token);
    		    },
    		    
    			success : function(result) {
    				if(result.status == "Done"){
    					alert("oki");
    				}else{
    				}
    				console.log(result);
    			},
    			error : function(e) {
    				alert("Error!")
    				console.log("ERROR: ", e);
    			}
    		});
		}
	});
});