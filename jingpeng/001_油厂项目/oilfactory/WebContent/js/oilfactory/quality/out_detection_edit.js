var id;
// 0:新增  
var flag;
// 物料信息下拉列表
var detectionIndex;
// 物料List
var detectionIndexList;
//项目检测下拉列表
var detectionProject;

var testreport; 
// 当前登录人姓名
var Creater = "";
var testreportInfoByIdFalg = true;
var checkFlag;
var isExchange;
$(function(){
	
	$("#indexType").html(getDataDictionary('indexType'));
	$("#conclusion").html(getDataDictionary('conclusion'));
	// 获取报告list（复制）
	getTestreportList();
	//物料信息取得
	getDetectionIndex();
	// 检测人员获取list
	getCachetPersonnel();
	getCachetCompany();
	
	//检测项目取得
	detectionProjects = getDataDictionarys('jcxm');
	
	//检测项目对象
	testreport = getTestreport();
	
	//检测项目取得
	detectionProject = getDataDictionary('jcxm');

	// 获取前页面传过来的id
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	checkFlag = getUrlParam("checkFlag");
	isExchange = getUrlParam("isExchange");
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	
	if(id != null && id != ''){
		getTestreportInfoById();
	}

	if(userInfo != ""){	
		Creater = userInfo.id;
	}
})

// 根据出库单id获取检测项目明细
function getTestreportInfoById(){
	var param = {};
	// 出库单检测进入
	if(flag == 1){
		param.exportStorageId = id;
		param.exchange = isExchange;
	}else if(flag == 3){
		// 空白检测报告单
		param.id = id;
	}else if(flag == 2){
		// 查看的情况
		if(checkFlag == 1){
			param.exportStorageId = id;
			param.exchange = isExchange;
		}else if(checkFlag == 2){
			param.id = id;
		}
	}
	$.ajax({
		type : "POST",
		url : "../../testreportsaledetailed/getTestreportInfoById.action",
		async:false,
		data : param,
		dataType : "json",
		success : function(data) {
			var testreportListList = data.testreportListList;
			if(testreportListList.length > 0){
				$("#testreportId").val(testreportListList[0].id);
				$("#reportRenown").val(testreportListList[0].reportRenown);
				$("#productModel").val(testreportListList[0].productModel);
				$("#usePlace").val(testreportListList[0].usePlace);
				$("#testDate").val(testreportListList[0].testDate);
				$("#testReportNumber").val(testreportListList[0].testReportNumber);
				$("#indexType option[value='"+testreportListList[0].indexType+"']").attr("selected", true);
				$("#conclusion option[value='"+testreportListList[0].conclusion+"']").attr("selected", true);
				$("#testPersonnelId option[value='"+testreportListList[0].testPersonnelId+"']").attr("selected", true);
				$("#reviewerId option[value='"+testreportListList[0].reviewerId+"']").attr("selected", true);
				$("#inspectionSupervisorId option[value='"+testreportListList[0].inspectionSupervisorId+"']").attr("selected", true);
				$("#detectionUnitId option[value='"+testreportListList[0].detectionUnitId+"']").attr("selected", true);
				$("#reportRemarks").val(testreportListList[0].reportRemarks);
				$("#insideRemarks").val(testreportListList[0].insideRemarks);
				var testreportsaledetailedList = data.testreportsaledetailedList;
				// 检测报告单销售订单明细
				if (testreportsaledetailedList.length > 0){
					for(var i=0;i<testreportsaledetailedList.length;i++){
						if (testreportInfoByIdFalg) {
							//检测报告单销售订单明细新加一条
							addTable();
						}
						// 物料名称
						$("#testingItems"+(i+1)).val(testreportsaledetailedList[i].detectionIndexId);				
						//检测项目
						$("#indexUnit"+(i+1)).val(testreportsaledetailedList[i].indexUnit);
						//技术指标
						$("#testMethod"+(i+1)).val(testreportsaledetailedList[i].testMethod);
						
						$("#skillRequire"+(i+1)).val(testreportsaledetailedList[i].skillRequire);
						//检测结果
						$("#testResult"+(i+1)).val(testreportsaledetailedList[i].testResult);
						//检测报告单销售订单明细id
						$("#testreportsaledetailedId"+(i+1)).val(testreportsaledetailedList[i].id);
					}
				} 	
			}
			
			// 查看功能
			if(flag == 2){
				$("input").attr("readonly",true);
				$("select").attr("disabled",true);
				$("#testDate").attr("disabled",true);
				$("#addBtn").hide();
				$("#saveBtn").hide();
			}
		}
	});
}

// 指标类型change事件
function changeDetectionIndex(indexType){
	$.ajax({
		type : "POST",
		url : "../../Detectionindex/getDetectionindex.action",
		async:false,
		data : {"indexType":indexType},
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'>请选择</option>";
			var id = "";
			for (var i = 0; i < data.data.length; i++) {
				if (!id.match(","+data.data[i].id+",")){
					html += "<option value='" + data.data[i].id + "'>"
					+ data.data[i].testingItems + "</option>"
					id += ","+data.data[i].id+",";
				}			
			}
			detectionIndex = html;
			if($('#tableList1 tr').length >1) {
				$('#tableList1 tr').not(":first").each(function(h,g) {
					$(g).find('#testingItems'+ (h+1)).html(detectionIndex);
					$(g).find('#indexUnit'+ (h+1)).val('');
					$(g).find('#testMethod'+ (h+1)).val('');
				})
			}
		}
	});
}

// 检测人员获取list
function getCachetPersonnel(){
	$.ajax({
		type : "POST",
		url : "../../CachetPersonnel/getCachetPersonnel.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'>请选择</option>";
			var id = "";
			for (var i = 0; i < data.data.length; i++) {
				if (!id.match(","+data.data[i].id+",")){
					html += "<option value='" + data.data[i].id + "'>"
					+ data.data[i].cachetName + "</option>"
					id += ","+data.data[i].id+",";
				}			
			}
			$("#testPersonnelId").html(html);
			$("#reviewerId").html(html);
			$("#inspectionSupervisorId").html(html);
		}
	});
}

// 检测单位
function getCachetCompany(){
	$.ajax({
		type : "POST",
		url : "../../CachetCompany/getCachetCompany.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'>请选择</option>";
			var id = "";
			for (var i = 0; i < data.data.length; i++) {
				if (!id.match(","+data.data[i].id+",")){
					html += "<option value='" + data.data[i].id + "'>"
					+ data.data[i].testCompany + "</option>"
					id += ","+data.data[i].id+",";
				}			
			}
			$("#detectionUnitId").html(html);
		}
	});
}


//检测指标信息取得
function getDetectionIndex(){
	$.ajax({
		type : "POST",
		url : "../../Detectionindex/getDetectionindex.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			detectionIndexList = data.data;
			var html = "<option value='' selected='selected'>请选择</option>";
			var id = "";
			for (var i = 0; i < detectionIndexList.length; i++) {
				if (!id.match(","+detectionIndexList[i].id+",")){
					html += "<option value='" + detectionIndexList[i].id + "'>"
					+ detectionIndexList[i].testingItems + "</option>"
					id += ","+detectionIndexList[i].id+",";
				}			
			}
			detectionIndex = html;
		}
	});
}

// 检测指标的change事件
function getDetectionIndexEdit(data,value){
	for (var i = 0; i < detectionIndexList.length; i++) {
		if(data ==detectionIndexList[i].id ){
			$("#indexUnit"+value).val(detectionIndexList[i].indexUnit);
			$("#testMethod"+value).val(detectionIndexList[i].testMethod);
		}
	}
}

// 出库单检测报告单明细新增一条
function addTable(){
	var len = $('#tableList tr').length;
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
	result +="<td ><a class='del' style='margin: 0 15px;' onclick='removeTr1(this,"+ row +")'><img src='../../img/common/del.png' width='20' height='20' alt='删除' title='删除'></a></td>";
	result +="<td ><select name='testingItems' onchange='getDetectionIndexEdit(this.value,"+ row +");' id='testingItems"+row+"'>"+detectionIndex+"</select></td>";
	result +="<td ><input class='ui_input_txt01' name='indexUnit'  readonly='readonly' style='background-color: #e4e4e4' id='indexUnit"+row+"'/></td>";
	result +="<td ><input class='ui_input_txt01' name='testMethod' readonly='readonly' style='background-color: #e4e4e4' id='testMethod"+row+"'/></td>";
	result +="<td ><input class='ui_input_txt01' name='skillRequire' id='skillRequire"+row+"' type='text' maxlength='30' /></td>";
	result +="<td ><input class='ui_input_txt01' name='testResult' id='testResult"+row+"' maxlength='30'/></td>";
	result +="<td ><input class='ui_input_txt01' name='testreportsaledetailedId'  id='testreportsaledetailedId"+row+"' value = '0' type='hidden'/></td>";
	result +="</tr>";
	$("#tableList1").append(result);
}

//只能入力数字和小数点  
function keepNumOrDecimal(obj){ 
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数 
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value= parseFloat(obj.value); 
    } 
} 

//最后一位不是小数点
function checkPoint(obj){
	keepNumOrDecimal(obj);
	if (obj.value.charAt(obj.value.length - 1)=='.'){
		obj.value = obj.value.substring(0,obj.value.length - 1);
	}		
}

// 保存
function save(){
	// 报告名头
	var reportRenown = $("#reportRenown").val();
	if(reportRenown == ""){
		swal("操作失败", "报告名头不能为空", "info");
		return;
	}
	//产品型号
	var productModel = $("#productModel").val();
	if(productModel == ""){
		swal("操作失败", "产品型号不能为空", "info");
		return;
	}
	//使用地点
	var usePlace = $("#usePlace").val();
	
	//检测日期
	var testDate = $("#testDate").val();
	if(testDate == ""){
		swal("操作失败", "检测日期不能为空", "info");
		return;
	}
	
	//检测报告编号
	var testReportNumber = $("#testReportNumber").val();
	if(testReportNumber == ""){
		swal("操作失败", "检测报告编号不能为空", "info");
		return;
	}
	//指标类型
	var indexType = $("#indexType").val();
	if(indexType == ""){
		swal("操作失败", "指标类型不能为空", "info");
		return;
	}
	//结论
	var conclusion = $("#conclusion").val();
	if(conclusion == ""){
		swal("操作失败", "结论不能为空", "info");
		return;
	}
	
	//报告备注
	var reportRemarks = $("#reportRemarks").val();
	
	//内部备注
	var insideRemarks = $("#insideRemarks").val();
	
	//备检测人
	var testPersonnelId = $("#testPersonnelId").val();
	if(testPersonnelId == ""){
		swal("操作失败", "备检测人不能为空", "info");
		return;
	}
	
	//复核人
	var reviewerId = $("#reviewerId").val();
	if(reviewerId == ""){
		swal("操作失败", "复核人不能为空", "info");
		return;
	}
	
	//检测负责人
	var inspectionSupervisorId = $("#inspectionSupervisorId").val();
	if(inspectionSupervisorId == ""){
		swal("操作失败", "检测负责人不能为空", "info");
		return;
	}
	
	//检测单位
	var detectionUnitId = $("#detectionUnitId").val();
	if(detectionUnitId == ""){
		swal("操作失败", "检测单位不能为空", "info");
		return;
	}
	
	var params = {
			"id":id,
			"exchange":isExchange,
			"flag":flag,
			"testreportId":$("#testreportId").val(),
			"saleOrOut":0,
			"Remarks":$("#Remarks").val(),
			"reportRenown" : reportRenown,
			"productModel" : productModel,
			"usePlace" : usePlace,
			"testDate" : testDate,
			"testReportNumber":testReportNumber,
			"indexType":indexType,
			"conclusion":conclusion,
			"testPersonnelId":testPersonnelId,
			"reviewerId":reviewerId,
			"inspectionSupervisorId":inspectionSupervisorId,
			"detectionUnitId":detectionUnitId,
			"reportRemarks":reportRemarks,
			"insideRemarks":insideRemarks,
			"Creater" : Creater
	};
	// 出库单检测报告单明细
	var testreportsaledetailedList = [];
	var i = 0;
	var message = "";
	$('#tableList tbody').find('tr').each(function () { 
		var testreportsaledetailed = {};
		$(this).find('td').each(function () {				
			$(this).find('input').each(function () {                         //获取td中input的值
	           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
	              var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name");  //获取该name的属性名称 
	              if (name == "indexUnit") {
	            	  testreportsaledetailed.indexUnit = idValue;
	              }else if(name == "testMethod"){
	            	  testreportsaledetailed.testMethod = idValue;
	              }else if (idValue !='' && name == 'skillRequire'){
	            	  testreportsaledetailed.skillRequire = idValue;
            	  }else if (idValue !=''&& name == 'testResult'){
            		  testreportsaledetailed.testResult = idValue;
            	  }else if(name == "testreportsaledetailedId"){
            		  testreportsaledetailed.testreportsaledetailedId = idValue;
            	  }else {
            		  if(name == 'skillRequire'){
            			  message += ("\n")+"第"+i+"行技术要求不能为空";
            		  }
            		  if(name == 'testResult'){
            			  message += ("\n")+"第"+i+"行检测结果不能为空";
            		  }
            	  }            
	           }
	        })
	        $(this).find('select').each(function () {                        //获取td中input的值
	           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		          var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name");  //获取该name的属性名称 
	              if (idValue !=''){
	            	  eval("testreportsaledetailed."+name+"=" + idValue);
	              }else {
	            	  if (name == 'testingItems'){
	            		  message += ("\n")+"第"+i+"行物检测指标不能为空";
	            	  }
	              } 
	           }
	        }) 
	        
		})				  
		testreportsaledetailedList.push(testreportsaledetailed);	
		i++;
	})
	if (message!=""){
		swal("操作失败", "出库单检测报告单明细"+message, "info");
		return;
	}
	params.testreportsaledetailedList = JSON.stringify(testreportsaledetailedList);
	
	$.ajax({
        type: "post",
        url: '../../testreportsaledetailed/addTestreportNameInfo.action',
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
 			},function() {
 				location.href = 'out_detection.html';
			});
 			testreportInfoByIdFalg = false;
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

// 删除出库单检测报告单明细
function removeTr1(obj,data){
	if($("#testreportsaledetailedId"+data).val() == 0 ){
		var tr= $(obj).parent().parent();
	    tr.remove();
	    var len = $('#tableList tr').length;
	    for (var i = 0; i < len; i++) {
			$('#tableList tr:eq('+i+') td:first').text(i);		
		}	
	}else{
		var params = {
				"testreportsaledetailedId":$("#testreportsaledetailedId"+data).val(),
				"Creater" : Creater
		};
		$.ajax({
	        type: "post",
	        url: '../../testreportsaledetailed/deleteTestreportsaledetailed.action',
	        async:false,
	        data:params,
	        dataType: "json",
	        success: function (data) {
	        	var tr= $(obj).parent().parent();
	    	    tr.remove();
	    	    var len = $('#tableList tr').length;
	    	    for (var i = 0; i < len; i++) {
	    			$('#tableList tr:eq('+i+') td:first').text(i);		
	    		}
	      }
		});
	}
}

// 根据出库单id获取检测项目明细
function getTestreportList(){
	var param = {};
	param.id = id;
	$.ajax({
		type : "POST",
		url : "../../testreportsaledetailed/getTestreports.action",
		async:false,
		data : param,
		dataType : "json",
		success : function(data) {			
			if(data != null && data.length > 0){
				$("#reportList").empty();
				var html = '<option value="">请选择</option>';
				// 产品编号
				var productModel = '';
				for(var i = 0; i< data.length; i++){
					
					if(productModel != data[i].productModel){
						html +="<option value="+data[i].id +">"+data[i].productModel+"</option>";
					}
					productModel = data[i].productModel;
				}
				$("#reportList").append(html);
			}
		}
	});
}

// 选择报告单进行复制
function changeReportList(value){
	var param = {};
	param.id = value;
	$.ajax({
		type : "POST",
		url : "../../testreportsaledetailed/getTestreportInfoById.action",
		async:false,
		data : param,
		dataType : "json",
		success : function(data) {			
			var testreportListList = data.testreportListList;
			if(testreportListList.length > 0){
				$("#reportRenown").val(testreportListList[0].reportRenown);
				$("#productModel").val(testreportListList[0].productModel);
				$("#usePlace").val(testreportListList[0].usePlace);
				$("#testDate").val(testreportListList[0].testDate);
				$("#testReportNumber").val(testreportListList[0].testReportNumber);
				$("#indexType option[value='"+testreportListList[0].indexType+"']").attr("selected", true);
				$("#conclusion option[value='"+testreportListList[0].conclusion+"']").attr("selected", true);
				$("#testPersonnelId option[value='"+testreportListList[0].testPersonnelId+"']").attr("selected", true);
				$("#reviewerId option[value='"+testreportListList[0].reviewerId+"']").attr("selected", true);
				$("#inspectionSupervisorId option[value='"+testreportListList[0].inspectionSupervisorId+"']").attr("selected", true);
				$("#detectionUnitId option[value='"+testreportListList[0].detectionUnitId+"']").attr("selected", true);
				$("#reportRemarks").val(testreportListList[0].reportRemarks);
				$("#insideRemarks").val(testreportListList[0].insideRemarks);
				var testreportsaledetailedList = data.testreportsaledetailedList;
				
				// 检测报告单销售订单明细
				$("#tableList1").empty();
				if (testreportsaledetailedList.length > 0){
					for(var i=0;i<testreportsaledetailedList.length;i++){
						if (testreportInfoByIdFalg) {
							//检测报告单销售订单明细新加一条
							addTable();
						}
						// 物料名称
						$("#testingItems"+(i+1)).val(testreportsaledetailedList[i].detectionIndexId);				
						//检测项目
						$("#indexUnit"+(i+1)).val(testreportsaledetailedList[i].indexUnit);
						//技术指标
						$("#testMethod"+(i+1)).val(testreportsaledetailedList[i].testMethod);
						$("#skillRequire"+(i+1)).val(testreportsaledetailedList[i].skillRequire);
						//检测结果
						$("#testResult"+(i+1)).val(testreportsaledetailedList[i].testResult);
						//检测报告单销售订单明细id
						$("#testreportsaledetailedId"+(i+1)).val(testreportsaledetailedList[i].id);
					}
				} 	
			}
		}
	});
}

