// 当前登录用户id
var userId = ''; 
// 主键id
var id = '';
// 项目id
var ProjectId = '';
// 查看/修改标识
var flag = "";
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}
	
	// 招标性质列表取得
	var TenderingNature = getDataDictionaryMultiple('zbxz');
	$("#tenderingNature").empty();
	$("#tenderingNature").html(TenderingNature);

	// 获取id
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	// 修改的场合
	if (id != null && id != "null" && id != ""){
		// 获取合同信息
		$.ajax({
			type : "post",
			url : "../../ProjectInfoSummary/getProjectInfoSummaryById.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				if(data != null && data.length > 0){
					console.log(data[0]);
					SetFromValues($("#form1"), data[0]);
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
	if (id != null && id != "null" && id != ""){
		// 修改
		url = '../../ProjectInfoSummary/updateProjectTenderingSummary.action';
		params.id = id;
		params.reviser=userId;
	}
	var arrId = [];//文件上传Id数组
	$("input[name='myfiles']").each(function(){
       arrId.push($(this).attr("id"));
     });
	var formParams = JSON.stringify(params);
	console.log(params);
	
	$.ajaxFileUpload({  
        url:url,
        secureuri:false,                           //是否启用安全提交,默认为false   
        fileElementId:arrId,   //文件选择框的id属性  
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
	
	/*$.ajax({
		type: 'POST',
		url: url,
		data:JSON.stringify(params),
		dataType: 'json',
		contentType: 'application/json',
		success: function(data){
			if (data.code == 'success'){
				// 操作成功
				layer.alert(data.message, {
					icon: 1,
					title: "提示"
				},function(){
					window.parent.location.reload();
				});
			} else {
				// 操作失败
				layer.alert(data.message, {
					icon: 2,
					title: "提示"
				});
			}			
		}
	});		*/
}


//打开文件
function openFile(){
	var fileId = $("#id").val();
	var index = layer.open({
		type: 2,
		title: '预览图片',
		content: 'ProjectTenderingSummaryImage.html?id='+fileId,
	});
	layer.full(index);
}