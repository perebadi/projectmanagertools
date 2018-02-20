$(document).ready(function(){
	
	//Establecemos el orden por defecto de la tabla projectPlanTable
	$("#projectPlanTable").DataTable({
		"order": [[ 1, "asc" ], [2, "asc"]]
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
	
	//Validación comment modal form
	var commentFormValidator = $("#commentProjectForm").validate({
		ignore : [],
		rules : {
			commentcomment : {
				required : true
			}
		}
	});

	
	//Función achievement addButton
	function addAchievementButton(){
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
		
		$("#modalProject").on("hidden.bs.modal", function () {
		    $("#addButton").unbind( "click" );
		});
		
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
			
			$("#modalProject").on("hidden.bs.modal", function () {
			    $("#addButton").unbind( "click" );
			});
		});
	});
	
	//Función nextstep addButon
	function addNextStepButton(){
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
		
		$("#modalProject").on("hidden.bs.modal", function () {
		    $("#addButton").unbind( "click" );
		});
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
		$("#addProblemButton").click(function(){
			//Prevent form submit
			$("#modalProblemForm").submit(function(e){
				e.preventDefault();
			});
				
			//Obtenemos el token
			var token = document.getElementsByName("_csrf")[0].value;			
		
			var formData;
			var urlPOST;
			
			//Obtenemos si es un problema o un riesgo
			var value = $( 'input[name=problemriskopt]:checked' ).val();
			
			if(value == "problem"){
				//Form data
				formData = {
					summaryproblem : $('#summaryProblem').val(),
					dateproblem : $('#dateProblem').val(),
					txtproblem : $('#txtProblem').val(),
					week : $('#weekProblem').val(),
					idproblem : $('#idProblem').val(),
					status : $('#statusProblem').val(),
					responsable : $('#responsableProblem').val(),
					impact : $('#impactProblem').val(),
					type : $('#typeProblem').val(),
					actions : $('#actionsProblem').val(),
					dateclose : $('#dateCloseProblem').val(),
					responsable : $('#responsableProblem').val(),
					estimatedclosingdate : $('#estimatedDateCloseProblem').val()					
				}
				
				urlPOST = "/api/project/" + $("#projectId").val() + "/problem/save/";
				
				//Validación add problem modal form
				var problemFormValidator = $("#modalProblemForm").validate({
					ignore : [],
					rules : {
						summaryProblem : {
							required : true
						},
						dateProblem : {
							required : true
						}, 
						weekProblem : {
							required : true
						},
						txtProblem : {
							required : true
						},
						statusProblem : {
							required : true
						},
						responsableProblem : {
							required : true
						},
						typeProblem : {
							required : true
						},
						impactProblem : {
							required : true
						},
						actionsProblem : {
							required : true
						},
						estimatedDateCloseProblem : {
							required : true
						}
					}
				});
			}else{
				//Form data
				formData = {
					summaryproblem : $('#summaryProblem').val(),
					dateproblem : $('#dateProblem').val(),
					txtproblem : $('#txtProblem').val(),
					week : $('#weekProblem').val(),
					idproblem : $('#idProblem').val(),
					status : $('#statusProblem').val(),
					responsable : $('#responsableProblem').val(),
					impact : $('#impactProblem').val(),
					type : $('#typeProblem').val(),
					actions : $('#actionsProblem').val(),
					dateclose : $('#dateCloseProblem').val(),
					responsable : $('#responsableProblem').val(),
					probability : $('#probabilityRisk').val(),
					strategy : $('#strategyRisk').val()
				}
				
				urlPOST = "/api/project/" + $("#projectId").val() + "/risk/save/";
				
				//Validación add risk modal form
				var riskFormValidator = $("#modalProblemForm").validate({
					ignore : [],
					rules : {
						summaryProblem : {
							required : true
						},
						dateProblem : {
							required : true
						}, 
						weekProblem : {
							required : true
						},
						txtProblem : {
							required : true
						},
						statusProblem : {
							required : true
						},
						responsableProblem : {
							required : true
						},
						typeProblem : {
							required : true
						},
						impactProblem : {
							required : true
						},
						actionsProblem : {
							required : true
						},
						probabilityRisk : {
							required : true
						},
						strategyRisk : {
							required : true
						}
					}
				});
			}
			
			if($("#modalProblemForm").valid()){
				$("#addProblemButton").prop("disabled",true);
			
				//AJAX Call
				$.ajax({
		    		type : "POST",
		    		contentType : "application/json",
		    		url : urlPOST,
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
		document.getElementById('modalProblemForm').reset();
		
		$("#titleProblemModal").html("Add problem");
		
		addProblemButton();
		
		$("#problemRiskRadio").show();
		$("#problemOption").prop("checked", true);
		$("#problemFields").show();
		$("#riskFields").hide();
		
		//Show modal
		$("#modalProblem").modal('show');
		
		$("#modalProblem").on("hidden.bs.modal", function () {
		    $("#addProblemButton").unbind( "click" );
		});
	});
	
	//Linkamos los botónes editar problem
	$("span[name='viewDetailsProblem']").each(function(){
		$(this).click(function(){			
			var problem = $(this).attr("data-problem");
			var token = document.getElementsByName("_csrf")[0].value;	
			
			if($("#problemrisk_" + problem).val() == "Problem"){
				//Obtenemos el problema
				
				$.ajax({
		    		type : "GET",
		    		contentType : "application/json",
		    		url : "/api/project/" + $("#projectId").val() + "/problem/" + problem + "/",
		    		dataType : 'json',
		    		async : false,
		    		beforeSend: function(request) {
		    	        return request.setRequestHeader('X-CSRF-Token', token);
		    	    },
		    		    
		    		success : function(result) {
		    			if(result.status == "Done"){
		    				var problem = JSON.parse(result.data);
		    				
		    				$("#problemRiskRadio").hide();
		    				$("#problemOption").prop("checked", true);
		    				$("#problemFields").show();
		    				$("#riskFields").hide();
		    				
		    				$("#summaryProblem").val(problem.summaryproblem);
		    				$("#dateProblem").val(problem.dateproblemstr);
		    				$("#dateCloseProblem").val(problem.dateclosestr);
		    				$("#weekProblem").val(problem.week);
		    				$("#txtProblem").val(problem.txtproblem);
		    				$("#idProblem").val(problem.idproblem);
		    				
		    				$("#statusProblem").val(problem.status);
		    				$('#statusProblem').selectpicker('refresh');
		    				
		    				$("#responsableProblem").val(problem.responsable);
		    				$('#responsableProblem').selectpicker('refresh');
		    				
		    				$("#typeProblem").val(problem.type);
		    				$('#typeProblem').selectpicker('refresh');
		    				
		    				$("#impactProblem").val(problem.impact);
		    				$('#impactProblem').selectpicker('refresh');
		    				
		    				$("#actionsProblem").val(problem.actions);
		    				
		    				$("#estimatedDateCloseProblem").val(problem.estimatedclosingdatestr);
		    			}
		    		},
		    		
		    		error : function(e) {
		    		}
		    	});
				
			}else{
				//Obtenemos el riesgo
				$.ajax({
		    		type : "GET",
		    		contentType : "application/json",
		    		url : "/api/project/" + $("#projectId").val() + "/risk/" + problem + "/",
		    		dataType : 'json',
		    		async : false,
		    		beforeSend: function(request) {
		    	        return request.setRequestHeader('X-CSRF-Token', token);
		    	    },
		    		    
		    		success : function(result) {
		    			if(result.status == "Done"){
		    				var risk = JSON.parse(result.data);
		    				
		    				$("#problemRiskRadio").hide();
		    				$("#riskOption").prop("checked", true);
		    				$("#problemFields").hide();
		    				$("#riskFields").show();
		    				
		    				$("#summaryProblem").val(risk.summaryproblem);
		    				$("#dateProblem").val(risk.dateproblemstr);
		    				$("#dateCloseProblem").val(risk.dateclosestr);
		    				$("#weekProblem").val(risk.week);
		    				$("#txtProblem").val(risk.txtproblem);
		    				$("#idProblem").val(risk.idproblem);
		    				
		    				$("#statusProblem").val(risk.status);
		    				$('#statusProblem').selectpicker('refresh');
		    				
		    				$("#responsableProblem").val(risk.responsable);
		    				$('#responsableProblem').selectpicker('refresh');
		    				
		    				$("#typeProblem").val(risk.type);
		    				$('#typeProblem').selectpicker('refresh');
		    				
		    				$("#impactProblem").val(risk.impact);
		    				$('#impactProblem').selectpicker('refresh');
		    				
		    				$("#actionsProblem").val(risk.actions);
		    				
		    				$("#probabilityRisk").val(risk.probability);
		    				$('#probabilityRisk').selectpicker('refresh');
		    				
		    				$("#strategyRisk").val(risk.strategy);
		    				$('#strategyRisk').selectpicker('refresh');
		    				
		    			}
		    		},
		    		
		    		error : function(e) {
		    		}
		    	});
			}			
			
			addProblemButton();
			
			$("#titleProblemModal").html("Save problem");
			
			//Show modal
			$("#modalProblem").modal('show');
			
			$("#modalProblem").on("hidden.bs.modal", function () {
			    $("#addProblemButton").unbind( "click" );
			});
		});
	});
	
	//Al cambiar la opcion de tipo de problema mostramos los campos pertinentes
	$('input[name=problemriskopt]').change(function(){
		var value = $( 'input[name=problemriskopt]:checked' ).val();
		
		if(value == "problem"){
			$("#riskFields").hide();
			$("#problemFields").show(100);
		}else{
			$("#problemFields").hide();
			$("#riskFields").show(100);
		}
	});
	
	//Ocultamos los fields de risk
	$("#riskFields").hide();
	
	//Función addButton escalation
	function addEscalationButton(){
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
		
		$("#modalProject").on("hidden.bs.modal", function () {
		    $("#addButton").unbind( "click" );
		});
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
			
			$("#modalProject").on("hidden.bs.modal", function () {
			    $("#addButton").unbind( "click" );
			});
		});
	});
	
	//Linkamos el botón financials
	$("#financialsModal").click(function(){
		financialFormValidator.resetForm();
		
		$("#saveFinancialButton").click(function(){
			if($("#modalFinancialProjectForm").valid()){
				$("#saveFinancialButton").prop("disabled", true);
			
				//Prevent form submit
				$("#modalFinancialProjectForm").submit(function(e){
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
	
	//AJAX formulario comentarios
	$("#addCommentButton").click(function(){
		//Validamos el formulario
		if($("#commentProjectForm").valid()){
			$("#addCommentButton").prop("disabled",true);
			
			//Prevent form submit
			$("#commentProjectForm").submit(function(e){
		        e.preventDefault();
		    });
			
			//Obtenemos el token
			var token = document.getElementsByName("_csrf")[0].value;			
			
			//Form data
			var formData = {
				idcomment : $('#commentid').val(),
				comment : $('#commentcomment').val(),
				tags : $('#commenttags').tagsinput('items')	
			}
			
			//AJAX Call
			$.ajax({
    			type : "POST",
    			contentType : "application/json",
    			url :"/api/project/" + $("#idCommentProject").val() + "/comment/save/",
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
	
	//Linkamos el botón para añadir comentarios del proyecto
	$("#commentsModal").click(function(){
		commentFormValidator.resetForm();
		
		//Show modal
		$("#modalComment").modal('show');
	});
	
	//Linkamos los botónes editar comentario
	$("a[name='editCommentButton']").each(function(){
		$(this).click(function(){
			commentFormValidator.resetForm();
			
			var comment = $(this).attr("data-comment");
			
			//Copiamos los valores en el modal
			$("#commentcomment").val($("#commentcomment_" + comment).val());
			$("#commentid").val($("#idcomment_" + comment).val());
			
			$("#commenttags").tagsinput('removeAll');
			
			var commenttags = JSON.parse($("#tagscomment_" + comment).val());
			
			for(var i = 0; i < commenttags.length; i++){
				$("#commenttags").tagsinput('add', commenttags[i]);
			}
			
			//Show modal
			$("#modalComment").modal('show');
		});
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