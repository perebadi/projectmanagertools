$(document).ready(function(){
	//Linkamos el onclick del botón logros
	$("#achievementModal").click(function(){
		document.getElementById('modalProjectForm').reset();
		
		$("#titleModal").html("Add achievement");
		
		$("#addButton").click(function(){
			$("#addButton").prop("disabled",true);
			
			//Prevent form submit
			$("#modalProjectForm").submit(function(e){
		        e.preventDefault();
		    });
			
			//Obtenemos el token
			var token = document.getElementsByName("_csrf")[0].value;			
	
			//Form data
			var formData = {
				summaryachievement : $('#summary').val(),
				dateachievement : $('#date').val(),
				txtachievement : $('#txt').val(),
				week : $('#week').val()
			}
	        	
			//AJAX Call
			$.ajax({
    			type : "POST",
    			contentType : "application/json",
    			url :"/api/project/" + $("#projectId").val() + "/achievement/save/",
    			data : JSON.stringify(formData),
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
    			}
    		});
		});
		
		//Show modal
		$("#modalProject").modal('show');
	});
	
	//Linkamos el botón futuras actividades
	$("#nextStepModal").click(function(){
		document.getElementById('modalProjectForm').reset();
		
		$("#titleModal").html("Add next step");
		
		$("#addButton").click(function(){
			$("#addButton").prop("disabled",true);
			
			//Prevent form submit
			$("#modalProjectForm").submit(function(e){
		        e.preventDefault();
		    });
			
			//Obtenemos el token
			var token = document.getElementsByName("_csrf")[0].value;			
	
			//Form data
			var formData = {
				summarynextstep : $('#summary').val(),
				datenextstep : $('#date').val(),
				txtnextstep : $('#txt').val(),
				week : $('#week').val()
			}
	        	
			//AJAX Call
			$.ajax({
    			type : "POST",
    			contentType : "application/json",
    			url :"/api/project/" + $("#projectId").val() + "/nextstep/save/",
    			data : JSON.stringify(formData),
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
    			}
    		});
		});
		
		//Show modal
		$("#modalProject").modal('show');
	});
	
	//Linkamos el botón problemas
	$("#problemModal").click(function(){
		document.getElementById('modalProjectForm').reset();
		
		$("#titleModal").html("Add problem");
		
		$("#addButton").click(function(){
			$("#addButton").prop("disabled",true);
			
			//Prevent form submit
			$("#modalProjectForm").submit(function(e){
		        e.preventDefault();
		    });
			
			//Obtenemos el token
			var token = document.getElementsByName("_csrf")[0].value;			
	
			//Form data
			var formData = {
				summaryproblem : $('#summary').val(),
				dateproblem : $('#date').val(),
				txtproblem : $('#txt').val(),
				week : $('#week').val()
			}
	        	
			//AJAX Call
			$.ajax({
    			type : "POST",
    			contentType : "application/json",
    			url :"/api/project/" + $("#projectId").val() + "/problem/save/",
    			data : JSON.stringify(formData),
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
    			}
    		});
		});
		
		//Show modal
		$("#modalProject").modal('show');
	});
	
	//Linkamos el botón escalaciones
	$("#escalationModal").click(function(){
		document.getElementById('modalProjectForm').reset();
		
		$("#titleModal").html("Add escalation");
		
		$("#addButton").click(function(){
			$("#addButton").prop("disabled",true);
			
			//Prevent form submit
			$("#modalProjectForm").submit(function(e){
		        e.preventDefault();
		    });
			
			//Obtenemos el token
			var token = document.getElementsByName("_csrf")[0].value;			
	
			//Form data
			var formData = {
				summaryescalation : $('#summary').val(),
				dateescalation : $('#date').val(),
				txtescalation : $('#txt').val(),
				week : $('#week').val()
			}
	        	
			//AJAX Call
			$.ajax({
    			type : "POST",
    			contentType : "application/json",
    			url :"/api/project/" + $("#projectId").val() + "/escalation/save/",
    			data : JSON.stringify(formData),
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
    			}
    		});
		});
		
		//Show modal
		$("#modalProject").modal('show');
	});
	
	//Linkamos el botón RAG
	$("#ragModal").click(function(){
		$("#titleRagModal").html("RAG project");
		
		$("#addRagButton").click(function(){
			$("#addRagButton").prop("disabled",true);
			
			//Prevent form submit
			$("#modalProjectRagForm").submit(function(e){
		        e.preventDefault();
		    });
			
			//Obtenemos el token
			var token = document.getElementsByName("_csrf")[0].value;			
	
			//Form data
			var formData = {
				projectStatus : $('#projectStatus').val(),
				projectDeliveryConfidence : $('#projectDeliveryConfidence').val(),
				projectGovernance : $('#projectGovernance').val(),
				projectBusinessChange : $('#projectBusinessChange').val(),
				projectBenefitsRealisation : $('#projectBenefitsRealisation').val(),
				projectDependency : $('#projectDependency').val(),
				projectResourcing : $('#projectResourcing').val(),
				projectScope : $('#projectScope').val(),
				id : $("#projectRagId").val()
			}
	        	
			//AJAX Call
			$.ajax({
    			type : "POST",
    			contentType : "application/json",
    			url :"/api/project/" + $("#projectId").val() + "/rag/save/",
    			data : JSON.stringify(formData),
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
    			}
    		});
		});
		
		//Show modal
		$("#ragModel").modal('show');
	});
	
});