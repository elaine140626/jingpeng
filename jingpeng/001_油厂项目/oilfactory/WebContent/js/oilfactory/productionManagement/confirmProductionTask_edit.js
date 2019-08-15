// 当前登录人姓名
var Creater = "";
// 计划调度id
var id;
var isProduction;
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		Creater = userInfo.name;
	}
	
	// 储位赋值
	getWareHouseInfoList();
	
	// 获取前页面传过来的id
	id = getUrlParam("id");
	isProduction= getUrlParam("isProduction");
	
	if (id!=null && id!=''){
		// 根据计划调度id获取确认生产
		getProductionTaskById();	
	}	
})

// 根据计划调度id获取确认生产
function getProductionTaskById(){
	$.ajax({
		type : "POST",
		url : "../../ConfirmProductionTask/getPlanMeasure.action",
		async:false,
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			var list = data.data[0];
			// 产品名称
			$("#MaterielName").val(list.materielName);
			// 产品规格
			$("#MaterielModel").val(list.materielModel);
			// 单位
			$("#Unit").val(list.unit);
			// 储位
			$("#StorageLocation").val(list.storageLocation);
			
			// 已经确认生产只查看
			if(isProduction == 0){
				$("#submitbutton").hide();
				$("select").attr("disabled", "disabled");//只读
			}else{
				$("#submitbutton").show();
				$("select").removeAttr("disabled", "disabled");//可编辑
			}
			
		}
	});
}

//保存
function save(){
	// 储位
	var StorageLocationName = $("#StorageLocation").val();
	if(StorageLocationName==""){
		swal("操作失败", "储位不能为空", "info");
		return;
	}
	var params = {
			"Id":id,
			"StorageLocation" : StorageLocationName,
			"Reviser" : Creater
	};
	
	$.ajax({
        type: "post",
        url: '../../ConfirmProductionTask/updateProductionTask.action',
        async:false,
        data:params,
        dataType: "json",
        success: function (data) {
       	 if(data.code == "success"){
 			swal({
 				title: data.message,
 				text: "",
 				type: data.code,
 				confirmButtonText: '确定',
 				cancelButtonFont: '微软雅黑',
 			},
 			function (){
 				window.parent.$.fancybox.close();
				window.location.reload();
 			});
 		}else{
 			swal({
 				title: data.message,
 				text: "",
 				type: data.code,
 				confirmButtonText: '确定',
 				cancelButtonFont: '微软雅黑',
 			});
 		}
      }
	});	
}

// 获取储位信息
function getWareHouseInfoList(){
	$.ajax({
        type: "post",
        url: '../../ConfirmProductionTask/getWareHouseInfoList.action',
        async:false,
        data:{},
        dataType: "json",
        success: function (data) {
       	 if(data != null && data.length > 0){
       		$("#StorageLocation").empty();
 			var html = '<option value="">请选择</option>';
 			for(var i= 0; i<data.length; i++){
 				html +="<option value="+ data[i].id +">" + data[i].warehouseName +"</option>";
 			}
 			
 			$("#StorageLocation").append(html);
 		}
      }
	});	
}