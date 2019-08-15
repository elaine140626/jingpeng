// 当前登录用户id
var userId = ''; 
// 主键id
var id = '';
// 项目id
var ProjectId = '';
var supervisionBean;
//查看/编辑标识
var flag = '';
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}
	id = getUrlParam("id");
	ProjectId = getUrlParam("ProjectId");
	flag = getUrlParam("flag");
	// 修改的场合
	if (id != null && id != ""){
		// 获取合同信息
		$.ajax({
			type : "post",
			url : "../../SupervisionApplication/getSupervisionApplicationById.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				if(data != null && data.length > 0){
					supervisionBean = data[0];
					SetFromValues($("#form1"), data[0]);
					var reply = $("#reply").val();
					if(reply == 0){
						$("#imageDiv").css("display","block");
					}else{
						$("#imageDiv").css("display","none");
					}
					if(data[0].supervisorPlan != ""){
						$("#supervisorPlan").css("display","none");
						$("#supervisorPlanUpSelectShow").css("display","block");
						$("#supervisorPlanFileShow").html(data[0].supervisorPlan)
					}
					if(data[0].supervisorDetailed != ""){
						$("#supervisorDetailed").css("display","none");
						$("#supervisorDetailedUpSelectShow").css("display","block");
						$("#supervisorDetailedFileShow").html(data[0].supervisorDetailed)
						
					}	
				}
				
				if(flag == 0){
					// 查看
					$('input[type="text"]').attr('disabled','disabled');
					$('select').attr('disabled','disabled');
					$('select').css('background-color','rgb(235, 235, 228)');
					
					// 隐藏保存按钮
					$("#saveButton").hide();
				}else if(flag == 1){
					// 修改
					$('input[type="text"]').removeAttr('readonly','readonly');
					$('select').removeAttr('disabled','disabled');
					$('select').css('background-color','#fff');
					
					// 显示保存按钮
					$("#saveButton").show();
				}
			}
		});
	}
})

// 保存
function save(){	
	var params = formToJson($("#form1"));	
	var url = '';
	if (id == null){
		// 新增
		url = '../../SupervisionApplication/insertSupervisionApplication.action';
		params.projectId = ProjectId;
		params.creater=userId;
	} else {
		// 修改
		url = '../../SupervisionApplication/updateSupervisionApplication.action';
		params.id = id;
		params.reviser=userId;
	}
	
		$.ajaxFileUpload({  
			url:'../../SupervisionApplication/uploadSupervisorPlan.action',
			secureuri:false,                           //是否启用安全提交,默认为false   
			fileElementId:'supervisorPlan',   //文件选择框的id属性  	
	        data:{
	        	"id":id
	        },
			dataType:'json',                           //服务器返回的格式,选择json或者xml貌似有问题
			success:function(data){            //服务器响应成功时的处理函数  
				params.supervisorPlan = data.code;
					$.ajaxFileUpload({  
						url:'../../SupervisionApplication/uploadSupervisorDetailed.action',
						secureuri:false,                           //是否启用安全提交,默认为false   
						fileElementId:'supervisorDetailed',   //文件选择框的id属性  	
				        data:{
				        	"id":id
				        },
						dataType:'json',                           //服务器返回的格式,选择json或者xml貌似有问题
						success:function(data){            //服务器响应成功时的处理函数  
							params.supervisorDetailed = data.code;
							var imageId ="";
							var reply = $("#reply").val();
							if(reply == 0){
								imageId = 'myBlogImage'
							}
							var formParams = JSON.stringify(params);
							$.ajaxFileUpload({  
						        url:url,
						        secureuri:false,                           //是否启用安全提交,默认为false   
						        fileElementId:imageId,   //文件选择框的id属性  
						        data:{
						        	"params":formParams
						        },		
						        dataType:'json',                           //服务器返回的格式,选择json或者xml貌似有问题
						        success:function(data){            //服务器响应成功时的处理函数  
									if(data != null){
										if(data.code == "success"){
											// 操作成功
											layer.alert(data.message, {
												icon: 1,
												title: "提示"
											},function(){
												window.parent.location.reload();
											});
										}else{
											// 操作失败
											layer.alert(data.message, {
												icon: 2,
												title: "提示"
											});
											return;
										}
									}
						        },  
						        error:function(data, status, e){ //服务器响应失败时的处理函数  
						        	// 操作失败
									layer.alert("操作失败", {
										icon: 2,
										title: "提示"
									});
									return;
						        }  
						    });	
						},  
						error:function(data, status, e){ //服务器响应失败时的处理函数  
							// 操作失败
							layer.alert("操作失败", {
								icon: 2,
								title: "提示"
							});
							return;
						}  
					});	
			},  
			error:function(data, status, e){ //服务器响应失败时的处理函数  
				// 操作失败
				layer.alert("操作失败", {
					icon: 2,
					title: "提示"
				});
				return;
			}  
		});	
}

function downSupervisorPlan(){
	window.location.href = "../../SupervisionApplication/downSupervisorPlan.action?id=" + supervisionBean.id;
}


function downSupervisorDetailed(){
	window.location.href = "../../SupervisionApplication/downSupervisorDetailed.action?id=" + supervisionBean.id;
}

function deleteSupervisorPlanFile(){
	$.ajax({
		type : "post",
		url : "../../SupervisionApplication/deleteSupervisorPlanFile.action",
		data :{
			"id":supervisionBean.id
		},
		dataType : "json",
		success : function(data) {
			if(data.code == "success"){
				layer.alert(data.message, {
					icon: 1,
					title: "提示"
				})
				$("#supervisorPlan").css("display","block");
				$("#supervisorPlanUpSelectShow").css("display","none");
				$("#supervisorPlanFileShow").html("")
			}else{
				layer.alert(data.message, {
					icon: 1,
					title: "提示"
				})
			}
		}
	});
}

function deleteSupervisorDetailed(){
	
	// 删除所选图片信息
	$.ajax({
		type : "post",
		url : "../../SupervisionApplication/deleteSupervisorDetailedFile.action",
		data :{
			"id":supervisionBean.id
		},
		dataType : "json",
		success : function(data) {
			if(data.code == "success"){
				layer.alert(data.message, {
					icon: 1,
					title: "提示"
				})
				$("#supervisorDetailed").css("display","block");
				$("#supervisorDetailedUpSelectShow").css("display","none");
				$("#supervisorDetailedFileShow").html("")
			}else{
				layer.alert(data.message, {
					icon: 1,
					title: "提示"
				})
			}
		}
	});
}

function replyOnchange(){
	var reply = $("#reply").val();
	if(reply == 0){
		$("#imageDiv").css("display","block");
	}else{
		$("#imageDiv").css("display","none");
	}
}

