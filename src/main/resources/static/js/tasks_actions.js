$(document).ready(function(){
	
	//Modal edición tareas backlog
	$(".showBacklogmodal").each(
			function() {
				var button = $(this);
				var idTask = button.attr("data-idTask");

				$(this).AJAXmodal(
						{
							idModal : "edit_backlogtask_modal",
							copyData : function() {
								$("#editBacklogTaskTaskId").val(idTask);
								
								$("#taskBacklogComments").empty();
								
								var comments = JSON.parse($("#comments_" + idTask).val());
								
								for(var i = 0; i < comments.length; i++){
									$( "#taskBacklogComments" ).append("" +
										"<li class='list-group-item' >" +											
											"<div class='heading'><strong>" + comments[i].datecomment + "</strong></div><br/>" +
											"<p>" + comments[i].detail + "</p>"+ 
										"</li>");
								}
								
							},
							url : "/api/savebacklog/",
							formData : function() {
								var data = {
									comment : $('#editBacklogTaskComment').val(),
									taskId : $('#editBacklogTaskTaskId').val(),
									username : $("#editBacklogTaskUser").val()
								}

								return data;
							}
						});
			});
	
	$(".showAJAXmodal").each(
			function() {
				var button = $(this);
				var idTask = button.attr("data-idTask");

				$(this).AJAXmodal(
						{
							idModal : "edit_task_modal",
							copyData : function() {
								$("#currentHours").text(
										$("#hours_" + idTask).val());
								$("#estimatedHours").text(
										$("#estimatedhours_" + idTask)
												.val());
								$("#progressBar").css(
										'width',
										$("#realvsestimated_" + idTask)
												.val()
												+ '%');
								$("#editTaskTaskId").val(idTask);
								
								$("#taskComments").empty();
								
								var comments = JSON.parse($("#comments_" + idTask).val());
								
								for(var i = 0; i < comments.length; i++){
									$( "#taskComments" ).append("" +
											"<li class='list-group-item' >" +		
												"<div class='heading'><strong>" + comments[i].datecomment + "</strong></div><br/>" +
												"<p>" + comments[i].detail + "</p>"+ 
											"</li>");
								}
								
							},
							url : "/api/savetask/",
							formData : function() {
								var data = {
									time : $('#editTaskTime').val(),
									unit : $('#editTaskUnit').val(),
									comment : $('#editTaskComment').val(),
									taskId : $('#editTaskTaskId').val()
								}

								return data;
							}
						});
			});
});