// 当前登录用户id
var userId = ''; 
// 主键id
var id = '';
// 项目id
var ProjectId = '';
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}
	
	// 工程项目赋值
	var engineeringInfo = getDataDictionaryMultiple('gcxm');
	$("#engineering").empty();
	$("#engineering").html(engineeringInfo);
	// 计划批次
	var planBatchInfo = getDataDictionaryMultiple('jhpc');
	$("#planBatch").empty();
	$("#planBatch").html(planBatchInfo);
	// 建设性质
	var buildNatureInfo = getDataDictionaryMultiple('jsxz');
	$("#buildNature").empty();
	$("#buildNature").html(buildNatureInfo);
	// 技术等级
	var technicalGradeInfo = getDataDictionaryMultiple('jsdj');
	$("#technicalGrade").empty();
	$("#technicalGrade").html(technicalGradeInfo);
	// 行政等级
	var administrationGradeInfo = getDataDictionaryMultiple('xzdj');
	$("#administrationGrade").empty();
	$("#administrationGrade").html(administrationGradeInfo);
	// 项目来源
	var projectSourceInfo = getDataDictionaryMultiple('xmly');
	$("#projectSource").empty();
	$("#projectSource").html(projectSourceInfo);
	// 项目分类
	$("#projectType").select2({
        placeholder:"请选择项目分类",//默认值
	});
	var projectTypeInfo = getDataDictionary('xmfl');
	$("#projectType").empty();
	$("#projectType").html(projectTypeInfo);

	// 批文种类
	$("#approvalsType").select2({
        placeholder:"请选择批文种类",//默认值
	});
	var approvalsTypeInfo = getDataDictionary('pwlx');
	$("#approvalsType").empty();
	$("#approvalsType").html(approvalsTypeInfo);
	
	id = getUrlParam("id");
	ProjectId = getUrlParam("ProjectId");
	// 修改的场合
	if (id != null && id != ""){
		// 获取合同信息
		$.ajax({
			type : "post",
			url : "../../ProjectInfoSummary/getProjectInfoSummaryById.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				if(data != null && data.length > 0){
					SetFromValues($("#form1"), data[0]);
					$("#myfilesLuJing").val(data[0].uploadPicture)
					// 项目分类
					var testItems = data[0].projectType;
					if(testItems != null){
						var testItemList = testItems.split(",");
						if(testItemList != null && testItemList.length > 0){
							$("#projectType").select2().val(testItemList).trigger("change");
						}
					}
				}
			}
		});
	}
})

// 保存
function save(){	
	var params = formToJson($("#form1"));
	
	// 路线名称
	if(params.lineName == ''){
		layer.alert("路线名称不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 工程项目
	if(params.engineering == ''){
		layer.alert("工程项目不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 计划批次
	if(params.planBatch == ''){
		layer.alert("计划批次不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 工程所在地
	if(params.location == ''){
		layer.alert("工程所在地不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 建设性质
	if(params.buildNature == ''){
		layer.alert("建设性质不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 起讫点及桩号
	if(params.pileNumber == ''){
		layer.alert("起讫点及桩号不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 计划批次
	if(params.planBatch == ''){
		layer.alert("计划批次不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 技术等级
	if(params.technicalGrade == ''){
		layer.alert("技术等级不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 项目分类
	var oMenuIcon = $("#projectType").select2().val();
	var testItems = "";
	if(oMenuIcon != null){
		for(var i = 0; i < oMenuIcon.length; i++){
			testItems += oMenuIcon[i] + ',';
		}
	}
	params.projectType = testItems;
	
	// 批文种类
	var oMenuIcon1 = $("#approvalsType").select2().val();
	var testItems1 = "";
	if(oMenuIcon1 != null){
		for(var i = 0; i < oMenuIcon1.length; i++){
			testItems1 += oMenuIcon1[i] + ',';
		}
	}
	params.approvalsType = testItems1;
	
	var url = '';
	var arrId = [];//文件上传Id数组
	$("input[name='myfiles']").each(function(){
       arrId.push($(this).attr("id"));
     });
	if (id == null){
		// 新增
		url = '../../ProjectInfoSummary/insertProjectInfoSummary.action';
		params.projectId = ProjectId;
		params.creater=userId;
	} else {
		// 修改
		url = '../../ProjectInfoSummary/updateProjectInfoSummary.action';
		params.id = id;
		params.reviser=userId;
	}
	var formParams = JSON.stringify(params);
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
}

// 打开文件
function openFile(){
	var fileId = $("#id").val();
	var index = layer.open({
		type: 2,
		title: '预览图片',
		content: 'projectInfoSummaryImage.html?id='+fileId,
	});
	layer.full(index);
}

