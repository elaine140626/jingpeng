// 当前登录用户id
var userId = ''; 
// 主键id
var id = '';
// 项目id
var ProjectId = '';
// 项目名
var content = "";
// 行次
var number = "";
//查看/编辑标识
var flag = '';
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}

	// 年份下拉列表
	getYear();
	// 月份下拉列表
	getMonth();
	
	// 主键
	id = getUrlParam("id");
	// 项目id
	ProjectId = getUrlParam("ProjectId");
	// 项目
	content = getUrlParam("content");
	// 行次
	number = getUrlParam("number");
	$("#content").val(content);
	
	flag = getUrlParam("flag");
	
	// 修改的场合
	if (id != null && id != "null" && id != ""){
		// 获取合同信息
		$.ajax({
			type : "post",
			url : "../../EngineeringSettlement/getEngineeringSettlementById.action",
			data : {"id" : id,"treeIds":ProjectId},
			dataType : "json",
			success : function(data) {
				if(data != null && data.length > 0){
					SetFromValues($("#form1"), data[0]);
				}
			}
		});
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
		// 项目名称
		$("#content").attr('disabled','disabled');
		$('select').removeAttr('disabled','disabled');
		$('select').css('background-color','#fff');
		
		// 显示保存按钮
		$("#saveButton").show();
	}
})

// 保存
function save(){	
	var params = formToJson($("#form1"));
	// 行次
	params.rowNumber = number;
	
	var url = '';
	if (id != null && id != "null" && id != ""){
		// 新增
		url = '../../EngineeringSettlement/updateEngineeringSettlement.action';
		params.id = id;
		params.reviser=userId;
	} else {
		// 修改
		url = '../../EngineeringSettlement/insertEngineeringSettlement.action';
		params.projectId = ProjectId;
		params.creater=userId;
	}
	$.ajax({
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
	});		
}

//年份下拉列表
function getYear(){
	//设置年份的选择 
	var myDate= new Date(); 
	var startYear=2010;//起始年份 
	var endYear=myDate.getFullYear();//结束年份 
	var obj=document.getElementById('dailyYear') 
	for (var i=endYear;i>=startYear;i--) 
	{ 
		obj.options.add(new Option(i,i)); 
	} 
	// 默认选中当前年
	obj.options[0].selected=1; 
}

// 月份下拉列表
function getMonth(){
	var obj=document.getElementById('dailyMonth'); 
	for(var i=1 ; i<13;i++){
		obj.options.add(new Option(i,i));
	}
	var myDate = new Date(); 
	var month=myDate.getMonth();
	// 默认选中当前月
	obj.options[month].selected=1;
}