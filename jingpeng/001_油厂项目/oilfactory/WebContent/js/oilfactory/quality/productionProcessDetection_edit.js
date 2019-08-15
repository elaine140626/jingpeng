// 当前登录人姓名
var Creater = "";
// 计划调度id
var id;
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		Creater = userInfo.name;
	}
	// 获取前页面传过来的id
	id = getUrlParam("id");
	if (id!=null&&id!=''){
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
			// 是否调整
			$("#IsQualified").val(list.isQualified);
			// 质检是否合格
			$("#IsAdjust").val(list.isAdjust);
			changeIsQualified();
		}
	});
}

// 质检是否合格change事件
function changeIsQualified(){
	// 质检是否合格
	var option = $('#IsQualified option').filter(':selected').val();
	console.log(option);
	if (option == 1){
		// 不合格的场合
		$("#IsAdjust").attr("disabled","");//取消只读		
	} else {
		// 合格的场合 是否调整只读
		$("#IsAdjust").attr("disabled","disabled");//只读
		// 是否调整：否
		$("#IsAdjust").val("1");
	}
}

//保存
function save(){
	// 是否调整
	var IsQualified = $("#IsQualified").val();
	// 质检是否合格
	var IsAdjust = $("#IsAdjust").val();
	var params = {
			"Id":id,
			"IsQualified" : IsQualified,
			"QualifiedPerson": Creater,
			"IsAdjust" : IsAdjust,
			"Reviser" : Creater
	};
	$.ajax({
        type: "post",
        url: '../../ProductionProcessDetection/updateProductionProcessDetection.action',
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