// 当前登录人姓名
var Creater = "";
// 计划调度id
var id;
var isConfirmSubmission;
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		Creater = userInfo.name;
	}
	// 获取前页面传过来的id
	id = getUrlParam("id");
	isConfirmSubmission = getUrlParam("isConfirmSubmission");
	if (id!=null&&id!=''){
		// 根据计划调度id获取实际原材料消耗
		getActualMaterialConsumptionById();	
	}	
})

// 根据计划调度id获取实际原材料消耗
function getActualMaterialConsumptionById(){
	$.ajax({
		type : "POST",
		url : "../../ActualMaterialConsumption/getActualMaterialConsumptionById.action",
		async:false,
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			var list = data.list;
			var listDetail = data.listDetail;
			// 生产任务编号
			$("#PlanNumber").val(list[0].planNumber);	
			// 产品名称
			$("#MaterielName").val(list[0].materielName);
			// 产品规格
			$("#MaterielModel").val(list[0].materielModel);
			// 实际生产数量
			$("#ActualWeight").val(list[0].actualWeight);	
			// 单位
			$("#Unit").val(list[0].unit);
			// 生产管理明细
			if (listDetail.length>0){
				for(var i=0;i<listDetail.length;i++){
					//生产管理明细新加一条
					addTable1();
					// 产品名称
					$("#MaterielName"+(i+1)).val(listDetail[i].materielName);				
					// 规格型号
					$("#MaterielModel"+(i+1)).val(listDetail[i].materielModel);
					// 理论消耗
					$("#MaterielWeight"+(i+1)).val(listDetail[i].materielWeight);
					// 实际消耗
					$("#ActualWeight"+(i+1)).val(listDetail[i].actualWeight);
					// Id
					$("#Id"+(i+1)).val(listDetail[i].id);				
					// 单位
					$("#Unit"+(i+1)).val(listDetail[i].unit);
					// 备注
					$("#Remarks"+(i+1)).val(listDetail[i].remarks);
				}
			} 			
			if (isConfirmSubmission == 0){
				$("#saveBtn1").attr("style","display:none;");//隐藏button
				$("input").attr("readonly", "readonly");//只读
			}	
		}
	});
}

//合同明细新增一条
function addTable1(){
	var len = $('#tableList1 tr').length;
	var row;
	var result="";
	if(len==1){
		row = 1;
	}else{
		for(var i = 1;i<len;i++){
			row = i+1;
		}
	}
	result +="<tr>";
	result +="<td>"+row+"</td>";
	result +="<td style='white-space:nowrap;'><input name='MaterielName' id='MaterielName"+row+"' readonly='readonly'/></td>";
	result +="<td style='white-space:nowrap;'><input name='MaterielModel' id='MaterielModel"+row+"' readonly='readonly'/></td>";
	result +="<td style='white-space:nowrap;'><input name='MaterielWeight' id='MaterielWeight"+row+"' type='text' readonly='readonly'/></td>";
	result +="<td style='white-space:nowrap;'><input name='ActualWeight' id='ActualWeight"+row+"' type='text' onkeyup='clearNoDecimal(this,8,4)' onblur='checkPoint(this)'/><input name='Id' id='Id"+row+"'type='hidden'/></td>";
	result +="<td style='white-space:nowrap;'><input name='Unit' id='Unit"+row+"' type='text'/ readonly='readonly'></td>";
	result +="<td ><input name='Remarks' id='Remarks"+row+"' type='text' maxlength='200'/></td>";
	result +="</tr>";
	$("#tableList1").append(result);
}

//保存
function save(){
	// 实际生产数量(吨)
	var ActualWeight = $("#ActualWeight").val();
	if(ActualWeight==""){
		swal("操作失败", "实际生产数量(吨)不能为空", "info");
		return;
	}
	var params = {
			"Id":id,
			"ActualWeight" : ActualWeight,
			"Reviser" : Creater
	};

	// 生产管理明细
	var listDetail = [];
	var i = 0;
	var message = "";
	$('#tableList1 tbody').find('tr').each(function () { 
		var detail = {};		
		$(this).find('td').each(function () {				
			$(this).find('input').each(function () { //获取td中input的值
	           if($(this).attr("name")) { //myName是input标签的一个自定义属性
	              var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name"); //获取该name的属性名称 
	              if (idValue !=''){
	            	  eval("detail."+name+"=" + "'"+idValue+"'");
	              } else {
	            	  if (name == 'ActualWeight'){
	            		  message += ("\n")+"第"+i+"行实际消耗不能为空";
	            	  }
	            	  if (name == 'Remarks'){
	            		  detail.Remarks = '';
	            	  }
	              }            
	           }
	        })	        
		})				  		
		listDetail.push(detail);	
		i++;	
	})
	if (message!=""){
		swal("操作失败", "原材料消耗"+message, "info");
		return;
	}
	params.listDetail = JSON.stringify(listDetail);
	
	$.ajax({
        type: "post",
        url: '../../ActualMaterialConsumption/updateActualMaterialConsumption.action',
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
 				location.href='actualMaterialConsumption.html';
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