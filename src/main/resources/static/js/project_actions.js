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
	
	//Función achievement addButton
	function addAchievementButton(){
		$("#addButton").click(function(){
			$(this).unbind( "click" );
			
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
					week : $('#week').val(),
					idachievement : $('#id').val()
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
	}
	
	//Linkamos el onclick del botón logros
	$("#achievementModal").click(function(){
		document.getElementById('modalProjectForm').reset();
		projectFormValidator.resetForm();
		$("#titleModal").html("Add achievement");
		
		addAchievementButton();
		
		$("#modalProject").modal('show');
	});
	
	//Linkamos los botónes editar logro
	$("span[name='viewDetailsAchievement']").each(function(){
		$(this).click(function(){
			projectFormValidator.resetForm();
			
			var achievement = $(this).attr("data-achievement");
			
			//Copiamos los valores en el modal
			$("#summary").val($("#summaryachievement_" + achievement).val());
			$("#date").val($("#dateachievement_" + achievement).val());
			$("#week").val($("#weekachievement_" + achievement).val());
			$("#txt").val($("#txtachievement_" + achievement).val());
			$("#id").val($("#idachievement_" + achievement).val());
			
			addAchievementButton();
			
			$("#titleModal").html("Save achievement");
			//Show modal
			$("#modalProject").modal('show');
		});
	});
	
	//Función nextstep addButon
	function addNextStepButton(){
		$("#addButton").click(function(){
			$(this).unbind( "click" );
			
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
					week : $('#week').val(),
					idnextstep : $('#id').val()
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
	}
	
	//Linkamos el botón futuras actividades
	$("#nextStepModal").click(function(){
		document.getElementById('modalProjectForm').reset();
		projectFormValidator.resetForm();
		
		$("#titleModal").html("Add next step");
		
		addNextStepButton();
		
		//Show modal
		$("#modalProject").modal('show');
	});
	
	//Linkamos los botónes editar nextstep
	$("span[name='viewDetailsNextStep']").each(function(){
		$(this).click(function(){
			projectFormValidator.resetForm();
			
			var nextstep = $(this).attr("data-nextstep");
			
			//Copiamos los valores en el modal
			$("#summary").val($("#summarynextstep_" + nextstep).val());
			$("#date").val($("#datenextstep_" + nextstep).val());
			$("#week").val($("#weeknextstep_" + nextstep).val());
			$("#txt").val($("#txtnextstep_" + nextstep).val());
			$("#id").val($("#idnextstep_" + nextstep).val());
			
			addNextStepButton();
			
			$("#titleModal").html("Save next step");
			//Show modal
			$("#modalProject").modal('show');
		});
	});
	
	//Función addProblem button
	function addProblemButton(){
		$("#addButton").click(function(){
			$(this).unbind( "click" );
			
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
					week : $('#week').val(),
					idproblem : $('#id').val()
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
	}
	
	//Linkamos el botón problemas
	$("#problemModal").click(function(){
		document.getElementById('modalProjectForm').reset();
		projectFormValidator.resetForm();
		
		$("#titleModal").html("Add problem");
		
		addProblemButton();
		
		//Show modal
		$("#modalProject").modal('show');
	});
	
	//Linkamos los botónes editar problem
	$("span[name='viewDetailsProblem']").each(function(){
		$(this).click(function(){
			projectFormValidator.resetForm();
			
			var problem = $(this).attr("data-problem");
			
			//Copiamos los valores en el modal
			$("#summary").val($("#summaryproblem_" + problem).val());
			$("#date").val($("#dateproblem_" + problem).val());
			$("#week").val($("#weekproblem_" + problem).val());
			$("#txt").val($("#txtproblem_" + problem).val());
			$("#id").val($("#idproblem_" + problem).val());
			
			addProblemButton();
			
			$("#titleModal").html("Save problem");
			//Show modal
			$("#modalProject").modal('show');
		});
	});
	
	//Función addButton escalation
	function addEscalationButton(){
		$("#addButton").click(function(){
			$(this).unbind( "click" );
			
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
					week : $('#week').val(),
					idescalation : $('#id').val()
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
	}
	
	//Linkamos el botón escalaciones
	$("#escalationModal").click(function(){
		document.getElementById('modalProjectForm').reset();
		projectFormValidator.resetForm();
		
		$("#titleModal").html("Add escalation");
		
		addEscalationButton();
		
		//Show modal
		$("#modalProject").modal('show');
	});
	
	//Linkamos los botónes editar escalation
	$("span[name='viewDetailsEscalation']").each(function(){
		$(this).click(function(){
			projectFormValidator.resetForm();
			
			var escalation = $(this).attr("data-escalation");
			
			//Copiamos los valores en el modal
			$("#summary").val($("#summaryescalation_" + escalation).val());
			$("#date").val($("#dateescalation_" + escalation).val());
			$("#week").val($("#weekescalation_" + escalation).val());
			$("#txt").val($("#txtescalation_" + escalation).val());
			$("#id").val($("#idescalation_" + escalation).val());
			
			addEscalationButton();
			
			$("#titleModal").html("Save escalation");
			//Show modal
			$("#modalProject").modal('show');
		});
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
	
	//AJAX formulario fases
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
	
	//Linkamos el botón fases del proyecto
	$("#phaseModal").click(function(){
		phaseFormValidator.resetForm();
		
		//Show modal
		$("#modalPhaseProject").modal('show');
	});
	
	//Linkamos los botónes editar fase
	$("button[name='editPhaseButton']").each(function(){
		$(this).click(function(){
			phaseFormValidator.resetForm();
			
			var phase = $(this).attr("data-phase");
			
			//Copiamos los valores en el modal
			$("#summaryphase").val($("#summaryphase_" + phase).val());
			$("#startdate").val($("#startdatephase_" + phase).val());
			$("#enddate").val($("#enddatephase_" + phase).val());
			$("#newdate").val($("#newdatephase_" + phase).val());
			$("#weekdelay").val($("#weekdelayphase_" + phase).val());
			
			$('select[name=rag]').val($("#ragphase_" + phase).val());
			$("#rag").change();
			
			$("#progress").val($("#progressphase_" + phase).val());
			$("#idphase").val(phase);
			
			//Show modal
			$("#modalPhaseProject").modal('show');
		});
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