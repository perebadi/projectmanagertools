$(document).ready(function(){
	
	//Establecemos el orden por defecto de la tabla projectPlanTable
	$("#projectPlanTable").DataTable({
		"order": [[ 2, "asc" ]]
	});
	
	//Validación project modal form
	var projectFormValidator = $("#modalProjectForm").validate({
		ignore : [],
		rules : {
			summary : {
				required : true
			},
			date : {
				required : true
			}, 
			week : {
				required : true
			}
		}
	});
	
	//Validación financial modal form
	var financialFormValidator = $("#modalFinancialProjectForm").validate({
		ignore : [],
		rules : {
			TVC : {
				required : true
			},
			TIC : {
				required : true
			}, 
			OP : {
				required : true
			},
			budgettodate : {
				required : true
			},
			costestimated : {
				required : true
			},
			EACOP : {
				required : true
			},
			variance : {
				required : true
			},
			certifiedprogress : {
				required : true
			},
			invoiced : {
				required : true
			}
		}
	});
	
	//Validación phase modal form
	var phaseFormValidator = $("#phaseModalForm").validate({
		ignore : [],
		rules : {
			summaryphase : {
				required : true
			},
			startdate : {
				required : true
			}, 
			enddate : {
				required : true
			},
			progress : {
				required : true
			}
		}
	});
	
	//Linkamos el onclick del botón logros
	$("#achievementModal").click(function(){
		document.getElementById('modalProjectForm').reset();
		projectFormValidator.resetForm();
		
		$("#titleModal").html("Add achievement");
		
		$("#addButton").click(function(){
			//Validamos el formulario
			if($("#modalProjectForm").valid()){
				//Disable add button
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
			}
		});
		
		//Show modal
		$("#modalProject").modal('show');
	});
	
	//Linkamos el botón futuras actividades
	$("#nextStepModal").click(function(){
		document.getElementById('modalProjectForm').reset();
		projectFormValidator.resetForm();
		
		$("#titleModal").html("Add next step");
		
		$("#addButton").click(function(){
			//Validamos el formulario
			if($("#modalProjectForm").valid()){
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
			}
		});
		
		//Show modal
		$("#modalProject").modal('show');
	});
	
	//Linkamos el botón problemas
	$("#problemModal").click(function(){
		document.getElementById('modalProjectForm').reset();
		projectFormValidator.resetForm();
		
		$("#titleModal").html("Add problem");
		
		$("#addButton").click(function(){
			//Validamos el formulario
			if($("#modalProjectForm").valid()){
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
			}
		});
		
		//Show modal
		$("#modalProject").modal('show');
	});
	
	//Linkamos el botón escalaciones
	$("#escalationModal").click(function(){
		document.getElementById('modalProjectForm').reset();
		projectFormValidator.resetForm();
		
		$("#titleModal").html("Add escalation");
		
		$("#addButton").click(function(){
			//Validamos el formulario
			if($("#modalProjectForm").valid()){
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
			}
		});
		
		//Show modal
		$("#modalProject").modal('show');
	});
	
	//Linkamos el botón financials
	$("#financialsModal").click(function(){
		financialFormValidator.resetForm();
		
		$("#saveFinancialButton").click(function(){
			if($("#modalFinancialProjectForm").valid()){
				$("#saveFinancialButton").prop("disabled", true);
			
				//Prevent form submit
				$("#financialsModal").submit(function(e){
			        e.preventDefault();
			    });
			
				//Obtenemos el token
				var token = document.getElementsByName("_csrf")[0].value;			
			
				//Form data
				var formData = {
					tvc : $('#TVC').val(),
					tic : $('#TIC').val(),
					op : $('#OP').val(),
					budgettodate : $('#budgettodate').val(),
					costestimated : $('#costestimated').val(),
					eacop : $('#EACOP').val(),
					variance : $('#variance').val(),
					certifiedprogress : $('#certifiedprogress').val(),
					invoiced : $('#invoiced').val()
				}
			
				//AJAX Call
				$.ajax({
	    			type : "POST",
	    			contentType : "application/json",
	    			url :"/api/project/" + $("#idFinancialsProject").val() + "/finance/save/",
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
			}
		});
		
		//Show modal
		$("#modalFinancialsProject").modal('show');
	});
	
	//Linkamos el botón fases del proyecto
	$("#phaseModal").click(function(){
		phaseFormValidator.resetForm();
		
		$("#addPhaseButton").click(function(){
			//Validamos el formulario
			if($("#phaseModalForm").valid()){
				$("#addPhaseButton").prop("disabled",true);
				
				//Prevent form submit
				$("#modalPhaseProject").submit(function(e){
			        e.preventDefault();
			    });
				
				//Obtenemos el token
				var token = document.getElementsByName("_csrf")[0].value;			
		
				//Form data
				var formData = {
					idphase : $('#idphase').val(),
					summaryphase : $('#summaryphase').val(),
					startdate : $('#startdate').val(),
					enddate : $('#enddate').val(),
					newdate : $('#newdate').val(),
					weekdelay : $('#weekdelay').val(),
					rag : $('#rag').val(),
					progress : $('#progress').val(),
				}
		        	
				//AJAX Call
				$.ajax({
	    			type : "POST",
	    			contentType : "application/json",
	    			url :"/api/project/" + $("#idPhaseProject").val() + "/phase/save/",
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
			}
		});
		
		//Show modal
		$("#modalPhaseProject").modal('show');
	});
	
	//Linkamos el botón RAG
	$("#ragModal").click(function(){
		
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