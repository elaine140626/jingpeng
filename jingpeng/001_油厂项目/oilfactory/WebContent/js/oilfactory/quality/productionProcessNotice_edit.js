// 当前登录人姓名
var Creater = "";
// 计划调度id
var id;
// flag=1:可编辑 flag=2:不可编辑(生产完成) flag=3:不可编辑(提交质检) flag=4:不可编辑(生产完成) flag=5:不可编辑(实际消耗)
var flag;
// 是否确认完成
var isProduction;
// 是否审核(主任)
var isExamine;
// 质检员是否审核
var isInspector;
//物料信息下拉列表
var MaterielInfo;
// 用户的角色
var roleCode = "";
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		Creater = userInfo.name;
		var list = userInfo.rolecode;	
		if(list.indexOf(",4,") != -1){
			// 质检员(操作)
			roleCode = 4;
		}
		if(list.indexOf(",5,") != -1){
			// 质检主任(操作)
			roleCode = 5;
		}
		if(list.indexOf(",0,") != -1){
			// 管理员(操作)
			roleCode = 0;
		}
		if(list.indexOf(",7,") != -1){
			// 总经理(操作)
			roleCode = 7;
		}
	}
	// 物料信息取得
	getMaterielInfo();
	// 获取前页面传过来的id
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	isProduction = getUrlParam("isProduction");
	isExamine = getUrlParam("isExamine");
	isInspector = getUrlParam("isInspector");
	if (id!=null && id !=''){
		// 根据计划调度id获取配料单
		getProductionProcessNoticeById();	
	}	
})

//物料信息取得
function getMaterielInfo(){
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getMaterielInfo.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			MaterielList = data;
			var html = "<option value='' selected='selected'>请选择</option>";
			var MaterielName = "";
			for (var i = 0; i < data.length; i++) {
				if (!MaterielName.match(","+data[i].MaterielName+",")){
					html += "<option value='" + data[i].MaterielName + "'>"
					+ data[i].MaterielName + "</option>"
					MaterielName += ","+data[i].MaterielName+",";
				}			
			}
			MaterielInfo = html;
		}
	});
}

// 根据合同id获取合同信息
function getProductionProcessNoticeById(){
	$.ajax({
		type : "POST",
		url : "../../ProductionProcessNotice/getProductionProcessNoticeById.action",
		async:false,
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			var list = data.list;
			var listDetail = data.listDetail;
			// 生产批次
			$("#Batch").val(list[0].batch);
			// 产品名称
			$("#MaterielName").val(list[0].materielName);
			// 产品规格
			$("#MaterielModel").val(list[0].materielModel);
			// 生产批次
			$("#BatchingList").val(list[0].batchingList);
			// 工艺编号
			$("#TechnicsNumber").val(list[0].technicsNumber); 	
			// 计划生产量
			$("#PlanWeight").val(list[0].planWeight + list[0].unit);
			// 收货单位编号
			$("#CustomerCode").val(list[0].customerCode);
			// 收货单位名称
			$("#CustomerName").val(list[0].customerName);
			if (flag == 2 || flag == 3 || flag == 4 || flag == 5){
				// 储位
				$("#StorageLocationDiv").show();
				// 储位
				$("#StorageLocationName").val(list[0].storageLocationName);
			}else{
				// 储位
				$("#StorageLocationDiv").hide();
			}
			// 生产管理明细
			if (listDetail.length>0){
				for(var i=0;i<listDetail.length;i++){
					//生产管理明细新加一条
					addTable1();
					// 产品名称
					$("#MaterielName"+(i+1)).val(listDetail[i].materielName);				
					// 规格型号
					changeMaterielName(i+1,listDetail[i].materielName);
					$("#MaterielModel"+(i+1)).val(listDetail[i].materielId);
					//规格型号选择
					changeMaterielModel(i+1,listDetail[i].materielId);
					// 含量（%）
					$("#RatioWeight"+(i+1)).val(listDetail[i].ratioWeight);
					// 重量
					$("#MaterielWeight"+(i+1)).val(listDetail[i].materielWeight);
					// 备注
					$("#Remarks"+(i+1)).val(listDetail[i].remarks);
					if (flag == 2 || flag == 3 || flag == 4 || flag == 5 || isProduction == 0 || isExamine == 0 || (roleCode == 4 && isInspector == 0)){
						$("#MaterielName"+(i+1)).attr("disabled","disabled");//只读
						$("#MaterielModel"+(i+1)).attr("disabled","disabled");//只读
					}
				}
			}else {
				if(id == ''){
					// 生产管理明细新加一条
					addTable1();
				}
			}	
			if (flag == 2 || flag == 3 || flag == 4 || flag == 5 || isProduction == 0 || isExamine == 0 || (roleCode == 4 && isInspector == 0)){
				$("input").attr("readonly", "readonly");//只读
				$("#addBtn").attr("style","display:none;");//隐藏div(添加一行)
				$("#saveBtn1").attr("style","display:none;");//隐藏button	
				$("#now_area").attr("style","display:none;");//隐藏返回	
				$("#caozuo").attr("style","display:none;");//操作隐藏	
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
	
	// 没有审核即可修改
	if (flag == 1 && isExamine != 0){
		if(roleCode == 5 || roleCode == 0 || roleCode == 7 || (roleCode == 4 && isInspector != 0)){
			result +="<td ><a class='del' style='margin: 0 15px;' onclick='removeTr1(this)'><img src='../../img/common/del.png' width='20' height='20' alt='删除' title='删除'></a></td>";
		}
		
	}
	result +="<td style='white-space:nowrap;'><select name='MaterielName' id='MaterielName"+row+"' onchange='changeMaterielName("+row+",this.value)'>"+MaterielInfo+"</select><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><select name='MaterielModel' id='MaterielModel"+row+"' onchange='changeMaterielModel("+row+",this.value)'></select><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><input name='PrimeNumber' id='PrimeNumber"+row+"' type='text'/ readonly='readonly'></td>";
	result +="<td style='white-space:nowrap;'><input name='RatioWeight' id='RatioWeight"+row+"' type='text' onchange='changeRatioWeight("+row+",this.value)'onkeyup='clearNoDecimal(this,8,2)' onblur='checkPoint(this)'/><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><input name='MaterielWeight' id='MaterielWeight"+row+"' type='text' onkeyup='clearNoDecimal(this,8,4)' onblur='checkPoint(this)'/><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><input name='Unit' id='Unit"+row+"' type='text'/ readonly='readonly'></td>";
	result +="<td ><input name='Remarks' id='Remarks"+row+"' type='text' maxlength='200'/></td>";
	result +="</tr>";
	$("#tableList1").append(result);
}

//产品名称选择
function changeMaterielName(data,value){
	//规格型号取得
	getMaterielModel(data,value);
}

//规格型号取得
function getMaterielModel(data,value){
	if (MaterielList!=null){
		var html = "<option value='' selected='selected'>请选择</option>";
		for (var i = 0; i < MaterielList.length; i++) {
			if (MaterielList[i].MaterielName == value){
				html += "<option value='" + MaterielList[i].Id + "'>"
				+ MaterielList[i].MaterielModel + "</option>"
			}			
		}
		$("#MaterielModel"+data).empty();
		$("#MaterielModel"+data).html(html);
	}	
}

//规格型号选择
function changeMaterielModel(data,value){
	if (MaterielList!=null){
		for (var i = 0; i < MaterielList.length; i++) {
			if (MaterielList[i].Id == value){
				// 单位
				document.getElementById("Unit"+data).value = MaterielList[i].Unit;
				// 库存量
				document.getElementById("PrimeNumber"+data).value = MaterielList[i].PrimeNumber;				
			}
		}
	}	
}

// 含量修改
function changeRatioWeight(data,value){
	if(value != null && value != ''){
		// 含量
		var RatioWeight = document.getElementById("RatioWeight"+data).value;
		// 计划生产量
		var PlanWeight = document.getElementById("PlanWeight").value
		// 重量
		document.getElementById("MaterielWeight"+data).value 
			= parseFloat((parseFloat(RatioWeight) * parseFloat(PlanWeight) / 100).toFixed(4));
	}	
}

//保存
function save(){
	// 生产批次
	var BatchingList = $("#BatchingList").val();
	if(BatchingList==""){
		swal("操作失败", "生产批次不能为空", "info");
		return;
	}
	// 工艺编号
	var TechnicsNumber = $("#TechnicsNumber").val();
	if(TechnicsNumber==""){
		swal("操作失败", "工艺编号不能为空", "info");
		return;
	}			
	var params = {
			"Id":id,
			"BatchingList" : BatchingList,
			"TechnicsNumber" : TechnicsNumber,
			"Reviser" : Creater
	};

	// 生产管理明细
	var listDetail = [];
	var i = 0;
	var message = "";
	$('#tableList1 tbody').find('tr').each(function () { 
		var detail = {};		
		// 重量
		var MaterielWeight = '';
		// 库存量
		var PrimeNumber = '';
		$(this).find('td').each(function () {			
			$(this).find('input').each(function () { //获取td中input的值
	           if($(this).attr("name")) { //myName是input标签的一个自定义属性
	              var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name"); //获取该name的属性名称 
	              if (idValue !=''){
	            	  eval("detail."+name+"=" + "'"+idValue+"'");
	            	  if (name == 'MaterielWeight'){
	            		  MaterielWeight = idValue;
	            	  }
	            	  if (name == 'PrimeNumber'){
	            		  PrimeNumber = idValue;
	            	  }
	              } else {
	            	  if (name == 'RatioWeight'){
	            		  message += ("\n")+"第"+i+"行含量不能为空";
	            	  }
	            	  if (name == 'MaterielWeight'){
	            		  message += ("\n")+"第"+i+"行重量不能为空";
	            	  }
	            	  if (name == 'Remarks'){
	            		  detail.Remarks = '';
	            	  }
	              }            
	           }
	        })
	       
	        $(this).find('select').each(function () { //获取td中input的值
	           if($(this).attr("name")) { //myName是input标签的一个自定义属性
		          var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name"); //获取该name的属性名称 
	              if (idValue !=''){
	            	  eval("detail."+name+"=" + "'"+idValue+"'");
	              } else {
	            	  if (name == 'MaterielName'){
	            		  message += ("\n")+"第"+i+"行产品名称不能为空";
	            	  }
	            	  if (name == 'MaterielModel'){
	            		  message += ("\n")+"第"+i+"行规格型号不能为空";
	            	  }
	              }  
	           }
	        }) 
	        
		})		
		if (parseFloat(MaterielWeight) > parseFloat(PrimeNumber)){
			message += ("\n")+"第"+i+"行物料不足";
        }
		listDetail.push(detail);	
		i++;	
	})
	if(listDetail.length <= 1){
		swal("操作失败", "配料单最少添加一条", "info");
		return;
	}
	if (message!=""){
		swal("操作失败", "配料单"+message, "info");
		return;
	}
	params.listDetail = JSON.stringify(listDetail);
	$.ajax({
        type: "post",
        url: '../../ProductionProcessNotice/updateProductionProcessNotice.action',
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
 				location.href='productionProcessNotice.html';
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

// 删除配料单明细明细
function removeTr1(obj){
	var tr= $(obj).parent().parent();
    tr.remove();
    var len = $('#tableList1 tr').length;
    for (var i = 0; i < len; i++) {
		$('#tableList1 tr:eq('+i+') td:first').text(i);		
	}
}

// 画面关闭
function closeScreen(){
	if (flag == 1){
		location.href='productionProcessNotice.html';
	}
	if (flag == 2){
		location.href='../productionManagement/confirmProductionTask.html';
	}
	if (flag == 3){
		location.href='../productionManagement/qualityChecking.html';
	}
	if (flag == 4){
		location.href='../productionManagement/completeConfirm.html';
	}
	if (flag == 5){
		location.href='../productionManagement/actualMaterialConsumption.html';
	}
}