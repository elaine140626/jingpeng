// 合同id
var id;
// 0:新增  1:修改  2:复制
var flag;
//是否生成销售订单
var isSales;
// 物料原材信息下拉列表
var rawMaterielInfo;
//物料产成品信息下拉列表
var finishedMaterielInfo;
// 物料List
var MaterielList;
// 物料型号
var MaterielModel = "<option value='' selected='selected'>请选择</option>";
//结算方式下拉列表
var SettlementMethodInfo;
//货物结算情况
var CargoBalanceInfo;
//运输结算情况
var TransportBalanceInfo;
// 当前登录人姓名
var Creater = "";
var MaterielModelLsit = [];
var MaterielModelLsit1 = [];

$(function(){
	// 合同状态
	var ContractStateInfo = getDataDictionary('htzt');
	$("#ContractState").empty();
	$("#ContractState").html(ContractStateInfo);
	// 客户名称下拉列表
	var CustomerNameInfo = getCustomerName();
	$("#CustomerId").empty();
	$("#CustomerId").html(CustomerNameInfo);
	//销售员下拉列表
	var SalesNameInfo = getSalesName();
	$("#UserInfoId").empty();
	$("#UserInfoId").html(SalesNameInfo);
	//销售公司下拉
	var SalesCompany = getSalesCompany();
	$("#SalesCompanyId").empty();
	$("#SalesCompanyId").html(SalesCompany);
	//其它公司下拉
	$("#RelationCompanyId").empty();
	$("#RelationCompanyId").html(SalesCompany);
	//物料信息取得
	getMaterielInfo();
	//结算方式取得
	SettlementMethodInfo = getDataDictionary('jsfs');
	//货物结算情况取得
	CargoBalanceInfo = getDataDictionary('hwjsqk');
	//运输结算情况取得
	TransportBalanceInfo = getDataDictionary('ysjsqk');
	//其它公司信息
	$("input[type=radio][name=IsRelation]").change(function() {
		if(this.value == '0') {
			//关联其它公司
			$("#relation").show();
		} else if(this.value == '1') {
			//不关联其它公司
			$("#relation").hide();
			$("#RelationCompanyId").val("");
		}
	});

	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		Creater = userInfo.id;
	}
	
	// 获取前页面传过来的id
	id = getUrlParam("id");
	// 0:新增  1:修改  2:复制
	flag = getUrlParam("flag");
	// 是否生成销售订单
	isSales = getUrlParam("isSales");
	if (id!=null&&id!=''){
		// 根据合同id获取合同信息
		getContractInfoById();
		if ($("input[name='IsIncoming']:checked").val()==1){
			//合同来料加工明细新增一条
			addTable2();
		}
		if ($("input[name='IsRelation']:checked").val()==0){
			//关联其它公司
			$("#relation").show();
		}
	}else{
		$("#ContractNumber").val(getContractInfoPrefix("HT"));
		// 销售员
		$("#UserInfoId").val(Creater);
		// 销售公司名称
		$("#SalesCompanyId").val(1);
		//合同明细新加一条
		for (var i = 0 ; i < 4 ; i++) {
			//销售订单明细新加一条
			addTable1();
		}
		//合同来料加工明细新增一条
		addTable2();
	}		
})

// 根据合同id获取合同信息
function getContractInfoById(){
	var fileList;
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getContractInfoById.action",
		async:false,
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			var ContractInfoList = data.ContractInfoList;
			var ContractDetailedList = data.ContractDetailedList;
			var ContractIncomingDetailedList = data.ContractIncomingDetailedList;
			// 合同编号
			if(flag == 1){
				$("#ContractNumber").val(ContractInfoList[0].contractNumber);
				var ContractNumberUpload = $("#ContractNumber").val();
				$.ajax({
					type: "post",
					url: '../../salesContractManagement/getUploadfile.action',
					async:false,
					data : {"serialID":ContractNumberUpload},
					dataType: "json",
					success: function (data) {
						fileList = data.data;
					} 
				})
				if(fileList.length>0){
					$("#upShow").css("display","none");
					$("#upSelectShow").css("display","block");
					$("#fileShow").html(fileList[0].fileName.substring(0, fileList[0].fileName.length - 37));
					$("#files").val(fileList[0].fileName);
				}
			}else{
				$("#ContractNumber").val(getContractInfoPrefix("HT"));
			}
			// 合同名称
			$("#ContractName").val(ContractInfoList[0].contractName);			
			// 合同状态
			$("#ContractState").val(ContractInfoList[0].contractState);
			// 合同日期
			$("#ContractDate").val(ContractInfoList[0].contractDate);
			// 客户名称
			$("#CustomerId").val(ContractInfoList[0].customerId);
			// 销售数量（t）
			$("#SaleNumber").val(ContractInfoList[0].saleNumber);
			// 销售员
			$("#UserInfoId").val(ContractInfoList[0].userInfoId);
			// 销售公司名称
			$("#SalesCompanyId").val(ContractInfoList[0].salesCompanyId);
			// 是否来料加工
			$("input:radio[name=IsIncoming][value="+ContractInfoList[0].isIncoming+"]").attr("checked",true); 
			$("input:radio[name=IsIncoming]").attr("disabled","#disabled");
			// 备注
			$("#remarks").val(ContractInfoList[0].remarks);
			$("#remarks").css("background","#CCCCCC");
			$("#remarks").attr("readonly","#readonly");
			// 附件上传
			// 没有附件时隐藏下载删除按钮
			if (ContractInfoList[0].contractRoute) {
				$("#downloadBtn").show();
				$("#deleteBtn").show();
			}	
			// 出库记录是否需要审批
			$("input:radio[name=IsApproval][value="+ContractInfoList[0].isApproval+"]").attr("checked",true);  
			//是否关联其它公司
			$("input:radio[name=IsRelation][value="+ContractInfoList[0].isRelation+"]").attr("checked",true);
			// 其它公司名称
			$("#RelationCompanyId").val(ContractInfoList[0].relationCompanyId);
			//是否可修改
			$("input:radio[name=IsModify][value="+ContractInfoList[0].isModify+"]").attr("checked",true);
			
			if (isSales>0){
				// 合同名称
				$("#ContractName").css("background","#CCCCCC");
				$("#ContractName").attr("readonly","#readonly");
				// 合同状态
				$("#ContractState").css("background","#CCCCCC");
				$("#ContractState").attr("disabled","#disabled");
				// 合同日期
				$("#ContractDate").css("background","#CCCCCC");
				$("#ContractDate").attr("disabled","#disabled");
				// 客户名称
				$("#CustomerName").css("background","#CCCCCC");
				$("#CustomerName").attr("readonly","#readonly");
				// 销售员
				$("#UserInfoId").css("background","#CCCCCC");
				$("#UserInfoId").attr("disabled","#disabled");
				// 销售公司名称
				$("#SalesCompanyId").css("background","#CCCCCC");
				$("#SalesCompanyId").attr("disabled","#disabled");
				// 是否来料加工
				$("input:radio[name=IsIncoming]").attr("disabled","#disabled");
				//是否关联其它公司
				$("input:radio[name=IsRelation]").attr("disabled","#disabled");
				// 其它公司名称
				$("#RelationCompanyId").css("background","#CCCCCC");
				$("#RelationCompanyId").attr("disabled","#disabled");
				
				$("#myBlogImage").attr("disabled","#disabled");
				$("#deleteBtn").attr("disabled","#disabled");
			}
			
			// 合同明细
			if (ContractDetailedList.length>0){
				for(var i=0;i<ContractDetailedList.length;i++){
					if (isSales>0){
						addTable1IsModify();					
					} else {
						addTable1();
					}	
					// 产品名称
					$("#MaterielName"+(i+1)).val(ContractDetailedList[i].materielName);				
					// 规格型号
					changeMaterielName(i+1,ContractDetailedList[i].materielName);
					$("#MaterielModel"+(i+1)).val(ContractDetailedList[i].materielModel);
					// 单价
					$("#UnitPrice"+(i+1)).val(ContractDetailedList[i].unitPrice);
					// 数量
					$("#SaleNumber"+(i+1)).val(ContractDetailedList[i].saleNumber);
					// 税率
					$("#TaxRate"+(i+1)).val(ContractDetailedList[i].taxRate);
					// 金额
					$("#Money"+(i+1)).val(ContractDetailedList[i].money);
					//是否调价
					$("#IsModifyPrice"+(i+1)).val(ContractDetailedList[i].isModifyPrice);
					// 备注
					$("#Remarks"+(i+1)).val(ContractDetailedList[i].remarks);
				}
			} else {
				for (var i = 0 ; i < 4 ; i++) {
					//合同明细新加一条
					addTable1();
				}
			}
			
			// 合同来料加工明细
			if (ContractInfoList[0].isIncoming == '0'){
				$('#details2').show();	
				if (ContractIncomingDetailedList.length>0){
					for(var i=0;i<ContractIncomingDetailedList.length;i++){
						//合同来料加工明细新增一条
						addTable2();
						// 授手加工物资名称
						$("#MaterielName2"+(i+1)).val(ContractIncomingDetailedList[i].materielName);
						// 规格型号
						changeMaterielName2(i+1,ContractIncomingDetailedList[i].materielName);
						$("#MaterielModel2"+(i+1)).val(ContractIncomingDetailedList[i].materielModel);
						// 结算方式
						$("#SettlementMethod"+(i+1)).val(ContractIncomingDetailedList[i].settlementMethod);
						if($("#SettlementMethod"+(i+1)).val() == "" || $("#CargoBalance"+(i+1)).val() != 1 ){
							$("#SettlementMethodRemarks"+(i+1)).css("background","#CCCCCC")
							$("#SettlementMethodRemarks"+(i+1)).attr("readonly","#readonly")
						}
						// 结算方式备注
						$("#SettlementMethodRemarks"+(i+1)).val(ContractIncomingDetailedList[i].settlementMethodRemarks);
						// 货物结算情况
						$("#CargoBalance"+(i+1)).val(ContractIncomingDetailedList[i].cargoBalance);
						// 货物结算情况备注
						$("#CargoRemarks"+(i+1)).val(ContractIncomingDetailedList[i].cargoRemarks);
						if($("#CargoBalance"+(i+1)).val() == "" || $("#CargoBalance"+(i+1)).val() != 2 ){
							$("#CargoRemarks"+(i+1)).css("background","#CCCCCC")
							$("#CargoRemarks"+(i+1)).attr("readonly","#readonly")
						}
						// 运输结算情况
						$("#TransportBalance"+(i+1)).val(ContractIncomingDetailedList[i].transportBalance);
						if($("#TransportBalance"+(i+1)).val() == "" || $("#TransportBalance"+(i+1)).val() != 3 ){
							$("#TransportRemarks"+(i+1)).css("background","#CCCCCC")
							$("#TransportRemarks"+(i+1)).attr("readonly","#readonly")
						}
						// 运输结算情况备注
						$("#TransportRemarks"+(i+1)).val(ContractIncomingDetailedList[i].transportRemarks);
					}
				} else {
					//合同来料加工明细新增一条
					addTable2();
				}
			}
		}
	});
}

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
			var rawhtml = "<option value='' selected='selected'>请选择</option>";
			var finishedhtml = "<option value='' selected='selected'>请选择</option>";
			var MaterielName = "";
			for (var i = 0; i < data.length; i++) {
<<<<<<< .mine
				if(data[i].MaterielType == 1){
					if (!MaterielName.match(","+data[i].MaterielName+",")){
						rawhtml += "<option value='" + data[i].MaterielName + "'>"
						+ data[i].MaterielName + "</option>"
						MaterielName += ","+data[i].MaterielName+",";
					}			
||||||| .r4
				if(data[i].MaterielType == 1 || data[i].MaterielType == 3){
					if (!MaterielName.match(","+data[i].MaterielName+",")){
						rawhtml += "<option value='" + data[i].MaterielName + "'>"
						+ data[i].MaterielName + "</option>"
						MaterielName += ","+data[i].MaterielName+",";
					}			
=======
				//查询是所有的物料名称
				if (!MaterielName.match(","+data[i].MaterielName+",")){
					rawhtml += "<option value='" + data[i].MaterielName + "'>"
					+ data[i].MaterielName + "</option>"
					MaterielName += ","+data[i].MaterielName+",";
>>>>>>> .r122
				}
				
				if(data[i].MaterielType == 2){
					if (!MaterielName.match(","+data[i].MaterielName+",")){
						finishedhtml += "<option value='" + data[i].MaterielName + "'>"
						+ data[i].MaterielName + "</option>"
						MaterielName += ","+data[i].MaterielName+",";
					}			
				}
			}
			rawMaterielInfo = rawhtml
			finishedMaterielInfo = finishedhtml;
		}
	});
}

// 合同明细新增一条
function addTable1(){
	var len = $('#tableList1 tr').length;
	var dataCount = $('#tableList1').find('tr:last').attr('data-id');
	if(dataCount) {
		dataCount = parseInt(dataCount) + 1
	}else {
		dataCount = 1;
	}
	var row;
	var result="";
	if(len==1){
		row = 1;
	}else{
		for(var i = 1;i<len;i++){
			row = i+1;
		}
	}
	result +="<tr data-id="+ dataCount +">";
	result +="<td>"+row+"</td>";
	result +="<td ><a class='del' style='margin: 0 15px;' onclick='removeTr1(this)'><img src='../../img/common/del.png' width='20' height='20' alt='删除' title='删除'></a></td>";
	result +="<td style='white-space:nowrap;'><input name='MaterielName' id='MaterielName"+dataCount+"' onchange='changeMaterielName("+dataCount+",this.value)' list='plateNumbers0"+dataCount+"' type='text' /><dataList id='plateNumbers0"+dataCount+"' class='ui_input_txt02' style='padding: 0;'>"+rawMaterielInfo+"</dataList><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><input name='MaterielModel' id='MaterielModel"+dataCount+"' onchange='changeMaterielModel("+dataCount+",this.value)' list='plateNumbers1"+dataCount+"' type='text' /><dataList id='plateNumbers1"+dataCount+"' class='ui_input_txt02' style='padding: 0;'></dataList><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><input name='UnitPrice' id='UnitPrice"+dataCount+"' type='text' onchange='count("+dataCount+")' onkeyup='checkPrice(this)' onblur='checkPoint(this)'/><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><input name='SaleNumber' id='SaleNumber"+dataCount+"' type='text' onchange='count("+dataCount+")' onkeyup='clearNoDecimal(this,8,4)' /><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><input name='TaxRate' id='TaxRate"+dataCount+"' onkeyup='checkNumber(this)' onblur='checkPoint(this)'/>%</td>";
	result +="<td style='white-space:nowrap;'><input name='Money' id='Money"+dataCount+"' type='text' maxlength='15' onkeyup='checkPriceSum(this)' onblur='checkPoint(this)'/><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><select name='IsModifyPrice' id='IsModifyPrice"+dataCount+"'><option value='0'>是</option><option value='1' selected>否</option></select>";
	result +="<td ><input name='Remarks' id='Remarks"+dataCount+"' type='text' maxlength='200'/></td>";
	result +="</tr>";
	$("#tableList1").append(result);
}

//合同明细新增一条(可修改合同)
function addTable1IsModify(){
	var len = $('#tableList1 tr').length;
	var dataCount = $('#tableList1').find('tr:last').attr('data-id');
	if(dataCount) {
		dataCount = parseInt(dataCount) + 1
	}else {
		dataCount = 1;
	}
	var row;
	var result="";
	if(len==1){
		row = 1;
	}else{
		for(var i = 1;i<len;i++){
			row = i+1;
		}
	}
	result +="<tr data-id="+ dataCount +">";
	result +="<td>"+row+"</td>";
	result +="<td ></td>";
	result +="<td style='white-space:nowrap;'><input readonly='readonly' name='MaterielName' id='MaterielName"+dataCount+"' onchange='changeMaterielName("+dataCount+",this.value)' list='plateNumbers0"+dataCount+"' type='text' /><dataList id='plateNumbers0"+dataCount+"' class='ui_input_txt02' style='padding: 0;'>"+rawMaterielInfo+"</dataList><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><input readonly='readonly' name='MaterielModel' id='MaterielModel"+dataCount+"' onchange='changeMaterielModel("+dataCount+",this.value)' list='plateNumbers1"+dataCount+"' type='text' /><dataList id='plateNumbers1"+dataCount+"' class='ui_input_txt02' style='padding: 0;'></dataList><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><input name='UnitPrice' id='UnitPrice"+dataCount+"' type='text' onchange='count("+dataCount+")' onkeyup='checkPrice(this)' onblur='checkPoint(this)'/><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><input name='SaleNumber' id='SaleNumber"+dataCount+"' type='text' onchange='count("+dataCount+")' onkeyup='clearNoDecimal(this,8,4)' /><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><input name='TaxRate' id='TaxRate"+dataCount+"' onkeyup='checkNumber(this)' onblur='checkPoint(this)'/>%</td>";
	result +="<td style='white-space:nowrap;'><input name='Money' id='Money"+dataCount+"' type='text' maxlength='15' onkeyup='checkPriceSum(this)' onblur='checkPoint(this)'/><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><select disabled='disabled' name='IsModifyPrice' id='IsModifyPrice"+dataCount+"'><option value='0'>是</option><option value='1' selected>否</option></select>";
	result +="<td ><input readonly='readonly' name='Remarks' id='Remarks"+dataCount+"' type='text' maxlength='200'/></td>";
	result +="</tr>";
	$("#tableList1").append(result);
}


//合同来料加工明细新增一条
function addTable2(){
	var len = $('#tableList2 tr').length;
	var dataCount = $('#tableList2').find('tr:last').attr('data-id');
	if(dataCount) {
		dataCount = parseInt(dataCount) + 1
	}else {
		dataCount = 1;
	}
	var row;
	var result="";
	if(len==1){
		row = 1;
	}else{
		for(var i = 1;i<len;i++){
			row = i+1;
		}
	}
	result +="<tr data-id="+ dataCount +">";
	result +="<td>"+row+"</td>";
	result +="<td ><a class='del' style='margin: 0 15px;' onclick='removeTr2(this)'><img src='../../img/common/del.png' width='20' height='20' alt='删除' title='删除'></a></td>";
	result +="<td style='white-space:nowrap;'><input name='MaterielName' id='MaterielName2"+dataCount+"' onchange='changeMaterielName2("+dataCount+",this.value)' list='plateNumbers2"+dataCount+"' type='text' /><dataList id='plateNumbers2"+dataCount+"' class='ui_input_txt02' style='padding: 0;'>"+finishedMaterielInfo+"</dataList><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><input name='MaterielModel' id='MaterielModel2"+dataCount+"' onchange='changeMaterielModel2("+dataCount+",this.value)'list='plateNumbers3"+dataCount+"' type='text' /><dataList id='plateNumbers3"+dataCount+"' class='ui_input_txt02' style='padding: 0;'></dataList><span style='color:red;'>*</span></td>";
	result +="<td style='white-space:nowrap;'><select class='half_select' name='SettlementMethod' id='SettlementMethod"+dataCount+"'  onchange='changeSettlementMethod("+dataCount+",this.value)'>"+SettlementMethodInfo+"</select><span style='color:red;'>*</span><input class='harf_input' name='SettlementMethodRemarks' id='SettlementMethodRemarks"+dataCount+"' type='text' maxlength='200'/></td>";
	result +="<td style='white-space:nowrap;'><select class='half_select' onchange='changeCargoBalance("+dataCount+",this.value)' name='CargoBalance' id='CargoBalance"+dataCount+"'>"+CargoBalanceInfo+"</select><span style='color:red;'>*</span><input class='harf_input' name='CargoRemarks' id='CargoRemarks"+dataCount+"' type='text' maxlength='200'/></td>";
	result +="<td style='white-space:nowrap;'><select class='half_select' name='TransportBalance' id='TransportBalance"+dataCount+"' onchange='changeTransportBalance("+dataCount+",this.value)'>"+TransportBalanceInfo+"</select><span style='color:red;'>*</span><input class='harf_input' name='TransportRemarks' id='TransportRemarks"+dataCount+"' type='text' maxlength='200'/></td>";
	result +="</tr>";
	$("#tableList2").append(result);
}

//产品名称选择
function changeMaterielName(data,value){
	var flag1 = true;
	if (MaterielList!=null){
		for (var i = 0; i < MaterielList.length; i++) {
			if(value == MaterielList[i].MaterielName){
				flag1 = false;
				break;
			}
		}
    }
	if(flag1){
		swal("操作失败", "合同明细"+("\n")+"第"+data+"行产品名称选择内容不正确", "info");
		$("#MaterielName"+data).val("");
		return;
	}
	//规格型号取得
	getMaterielModel(data,value);
}

//规格型号取得
function getMaterielModel(data,value,flags){
	if (MaterielList!=null){
		MaterielModelLsit = [];
		var html = "<option value='' selected='selected'>请选择</option>";
		for (var i = 0; i < MaterielList.length; i++) {
			var materielModel = {};
			if (MaterielList[i].MaterielName == value){
				html += "<option value='" + MaterielList[i].MaterielModel + "'>"
				+ MaterielList[i].MaterielModel + "</option>";
				materielModel.MaterielName = MaterielList[i].MaterielModel;
				MaterielModelLsit.push(materielModel);
			}			
		}
		$("#plateNumbers1"+data).empty();
		$("#plateNumbers1"+data).html(html);
		if (flags!=1) {
			$("#MaterielModel"+data).val("");
		}
	}	
}

//来料加工货物结算情况
function changeCargoBalance(data,value){
	if(value == '' || value != 2){
		$("#CargoRemarks"+data).css("background","#CCCCCC")
		$("#CargoRemarks"+data).attr("readonly","true")
		$("#CargoRemarks"+data).val("")
	}else{
		$("#CargoRemarks"+data).css("background","white")
		$("#CargoRemarks"+data).removeAttr("readonly")
	}
}

function changeSettlementMethod(data,value){
	if(value == '' || value != 1){
		$("#SettlementMethodRemarks"+data).css("background","#CCCCCC")
		$("#SettlementMethodRemarks"+data).attr("readonly","true")
		$("#SettlementMethodRemarks"+data).val("")
	}else{
		$("#SettlementMethodRemarks"+data).css("background","white")
		$("#SettlementMethodRemarks"+data).removeAttr("readonly")
	}
}

function changeTransportBalance(data,value){
	if(value == '' || value != 3){
		$("#TransportRemarks"+data).css("background","#CCCCCC")
		$("#TransportRemarks"+data).attr("readonly","true")
		$("#TransportRemarks"+data).val("")
	}else{
		$("#TransportRemarks"+data).css("background","white")
		$("#TransportRemarks"+data).removeAttr("readonly")
	}
}

//规格型号选择
function changeMaterielModel(data,value){
	getMaterielModel(data,$("#MaterielName"+data).val(),1);
	var flag1 = true;
	if (MaterielList!=null){
		for (var i = 0; i < MaterielList.length; i++) {
			if (MaterielList[i].MaterielModel == value){
				// 单价
				document.getElementById("UnitPrice"+data).value = MaterielList[i].NoTaxMoney;
				flag1 = false;
				break;
			}
		}
		// 计算金额
		count(data);
	}
	if(flag1){
		swal("操作失败", "合同明细"+("\n")+"第"+data+"行规格型号选择内容不正确", "info");
		$("#MaterielModel"+data).val("");
		return;
	}
}

// 计算金额
function count(data){
	// 单价
	UnitPrice = document.getElementById("UnitPrice"+data).value;
	// 数量 
	SaleNumber = document.getElementById("SaleNumber"+data).value;
	// 金额
	document.getElementById("Money"+data).value = (UnitPrice*SaleNumber).toFixed(2);
	if(document.getElementById("Money"+data).value =="NaN"){
		document.getElementById("Money"+data).value = 0
	}
}

//产品名称选择(来料加工)
function changeMaterielName2(data,value){
	var flag1 = true;
	if (MaterielList!=null){
		for (var i = 0; i < MaterielList.length; i++) {
			if(value == MaterielList[i].MaterielName){
				flag1 = false;
				break;
			}
		}
    }
	if(flag1){
		swal("操作失败", "来料加工明细"+("\n")+"第"+data+"行产品名称选择内容不正确", "info");
		$("#MaterielName2"+data).val("");
		return;
	}
	//规格型号取得
	getMaterielModel2(data,value);
}

//规格型号取得(来料加工)
function getMaterielModel2(data,value,flags){
	if (MaterielList!=null){
		MaterielModelLsit1 = [];
		var html = "<option value='' selected='selected'>请选择</option>";
		for (var i = 0; i < MaterielList.length; i++) {
			var materielModel1 = {};
			if (MaterielList[i].MaterielName == value){
				html += "<option value='" + MaterielList[i].MaterielModel + "'>"
				+ MaterielList[i].MaterielModel + "</option>";
				materielModel1.MaterielName = MaterielList[i].MaterielModel;
				MaterielModelLsit1.push(materielModel1);
			}			
		}
		$("#plateNumbers3"+data).empty();
		$("#plateNumbers3"+data).html(html);
		if(flags != 1){
			$("#MaterielModel2"+data).val("");
		}
	}	
}

//规格型号选择
function changeMaterielModel2(data,value){
	getMaterielModel2(data,$("#MaterielName2"+data).val(),1);
	var flag1 = true;
	if (MaterielModelLsit1!=null){
		for (var i = 0; i < MaterielModelLsit1.length; i++) {
			if (MaterielModelLsit1[i].MaterielName == value){
				flag1 = false;
				break;
			}
		}
	}
	if(flag1){
		swal("操作失败", "来料加工明细"+("\n")+"第"+data+"行规格型号选择内容不正确", "info");
		$("#MaterielModel2"+data).val("");
		return;
	}
}

// 保存
function save(){
	// 合同名称
	var ContractName = $("#ContractName").val();
	if(ContractName==""){
		swal("操作失败", "合同名称不能为空", "info");
		return;
	}
	// 合同状态
	var ContractState = $("#ContractState").val();
	if(ContractState==""){
		swal("操作失败", "合同状态不能为空", "info");
		return;
	}
	var isZuofei = false;
	if(id!=""&&flag==1&&ContractState==3){
		var salesOrdersList;
		$.ajax({
	        type: "post",
	        url: '../../salesOrders/getSalesOrdersInfo.action',
	        async:false,
	        data:{"contractId":id},
	        dataType: "json",
	        success: function (data) {
	        	salesOrdersList = data.data;
	        	if(salesOrdersList.length>0){
	        		//swal("操作失败", "该合同已生成销售订单,不可作废", "info");
	        		isZuofei = true;
	        	}
	        }
	       })
	}
	if(isZuofei){
		swal("操作失败", "该合同已生成销售订单,不可作废", "info");
		return;
	}
	
	// 合同日期
	var ContractDate = $("#ContractDate").val();
	if(ContractDate==""){
		swal("操作失败", "合同日期不能为空", "info");
		return;
	}
	// 客户名称
	var CustomerId = $("#CustomerId").val();
	if(CustomerId==""){
		swal("操作失败", "客户编号不能为空", "info");
		return;
	}
	// 销售数量（t）
	var SaleNumber = $("#SaleNumber").val();
	if(SaleNumber==""){
		swal("操作失败", "销售数量（t）不能为空", "info");
		return;
	}
	// 销售员
	var UserInfoId = $("#UserInfoId").val();
	if(UserInfoId==""){
		swal("操作失败", "销售员不能为空", "info");
		return;
	}
	// 销售公司名称
	var SalesCompanyId = $("#SalesCompanyId").val();
	if(SalesCompanyId==""){
		swal("操作失败", "销售公司名称不能为空", "info");
		return;
	}
	// 是否来料加工
	var IsIncoming = $("input[name='IsIncoming']:checked").val();
	// 备注
	var remarks = $("#remarks").val();
	// 附件上传
	var ContractRoute = $("#myBlogImage").val();
	// 出库记录是否需要审批
	var IsApproval = $("input[name='IsApproval']:checked").val();
	// 是否关联其它公司
	var IsRelation = $("input[name='IsRelation']:checked").val();
	// 其它公司
	var RelationCompanyId = $("#RelationCompanyId").val();
	// 是否可修改 0:可修改 1:不可修改
	var IsModify = $("input[name='IsModify']:checked").val();
	
	var params = {
			"id":id,
			"flag":flag,
			// "ContractNumber" : ContractNumber,
			"ContractName" : ContractName,
			"ContractState" : ContractState,
			"ContractDate" : ContractDate,
			"CustomerId" : CustomerId,
			"SaleNumber" : SaleNumber,
			"UserInfoId" : UserInfoId,
			"SalesCompanyId" : SalesCompanyId,
			"IsIncoming" : IsIncoming,
			"remarks" : remarks,
			"ContractRoute" : ContractRoute,
			"IsApproval" : IsApproval,
			"IsRelation" : IsRelation,
			"RelationCompanyId" : RelationCompanyId,
			"IsModify":IsModify,
			"Creater" : Creater
	};
	
	// 不修改的时候
	if(flag != 1){
		// 合同编号
		params.ContractNumber = getContractInfoPrefix("HT");
	}else{
		var ContractNumber = $("#ContractNumber").val();
		params.ContractNumber = ContractNumber;
	}
	var serialID = params.ContractNumber;
	// 合同明细
	var ContractDetailedList = [];
	var i = 0;
	var swalMessage = "";
	$('#tableList1 tbody').find('tr').each(function () { 
		var ContractDetailed = {};
		var swalFlag = false;
		var message = "";
		var unfilled = 0;
		var materielName = "";
		$(this).find('td').each(function () {				
			$(this).find('input').each(function () {                         //获取td中input的值
	           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
	              var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name");  //获取该name的属性名称 
	              if (idValue !=''){
	            	  if(name == 'MaterielName'){
	            		  if (MaterielList!=null){
	            				for (var j = 0; j < MaterielList.length; j++) {
	            					if(idValue == MaterielList[j].MaterielName){
	            						eval("ContractDetailed."+name+"=" + "'"+MaterielList[j].MaterielNameId+"'");
	            						materielName = idValue;
	            						break;
	            					}
	            				}
	            	       }
	            	  }else if(name == 'MaterielModel'){
		            		  if (MaterielList!=null){
		            				for (var j = 0; j < MaterielList.length; j++) {
		            					if(idValue == MaterielList[j].MaterielModel && materielName == MaterielList[j].MaterielName){
		            						eval("ContractDetailed."+name+"=" + "'"+MaterielList[j].Id+"'");
		            						break;
		            					}
		            				}
		            	       }
	            	  }else{
	            		  eval("ContractDetailed."+name+"=" + "'"+idValue+"'");
	            	  }
	            	  swalFlag = true;
	              } else {
	            	  if (name == 'MaterielName'){
	            		  message += ("\n")+"第"+i+"行产品名称不能为空";
	            		  unfilled += 1;
	            	  }
	            	  if (name == 'MaterielModel'){
	            		  message += ("\n")+"第"+i+"行规格型号不能为空";
	            		  unfilled += 1;
	            	  }
	            	  if (name == 'UnitPrice'){
	            		  message += ("\n")+"第"+i+"行单价不能为空";
	            		  unfilled += 1;
	            	  }
	            	  if (name == 'SaleNumber'){
	            		  message += ("\n")+"第"+i+"行数量不能为空";
	            		  unfilled += 1;
	            	  }
	            	  if (name == 'Money'){
	            		  message += ("\n")+"第"+i+"行金额不能为空";
	            		  unfilled += 1;
	            	  }
	            	  if (name == 'TaxRate'){
	            		  message += ("\n")+"第"+i+"行税率不能为空";
	            		  unfilled += 1;
	            	  }
	            	  if (name == 'Remarks'){
	            		  ContractDetailed.Remarks = '';
	            		  unfilled += 1;
	            	  }
	              }              
	           }
	        })
	        $(this).find('select').each(function () {                        //获取td中input的值
	           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		          var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name");  //获取该name的属性名称 
	              if (idValue !=''){
	            	  eval("ContractDetailed."+name+"=" + "'"+idValue+"'");
	            	  swalFlag = true;
	              } 
	           }
	        }) 
	        
		})				  
		if (swalFlag) {
			ContractDetailedList.push(ContractDetailed);	
		}	
		// 当该行所有必填项都未填时，不提示未填信息
		if (unfilled < 8) {
			swalMessage += message;
		}
		i++;
	})
	if(ContractDetailedList.length <= 0){
		swal("操作失败", "订单明细最少添加一条", "info");
		return;
	}
	if (swalMessage!=""){
		swal("操作失败", "订单明细"+swalMessage, "info");
		return;
	}
	params.ContractDetailedList = JSON.stringify(ContractDetailedList);
	
	// 合同来料加工明细
	var ContractIncomingDetailedList = [];	
	if (IsIncoming == '0'){
		var i = 0;
		var message = "";
		var materielName = "";
		$('#tableList2 tbody').find('tr').each(function () { 
			var ContractIncomingDetailed = {};
			$(this).find('td').each(function () {				
				$(this).find('input').each(function () {                         //获取td中input的值
		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		              var idValue = $(this).val(); //获取属性id对应的属性值
		              var name=$(this).attr("name");  //获取该name的属性名称 
		              if (idValue !=''){
		            	  if(name == 'MaterielName'){
		            		  if (MaterielList!=null){
		            				for (var j = 0; j < MaterielList.length; j++) {
		            					if(idValue == MaterielList[j].MaterielName){
		            						eval("ContractIncomingDetailed."+name+"=" + "'"+MaterielList[j].MaterielNameId+"'");
		            						materielName = idValue;
		            						break;
		            					}
		            				}
		            	       }
		            	  }else if(name == 'MaterielModel'){
			            		  if (MaterielList!=null){
			            				for (var j = 0; j < MaterielList.length; j++) {
			            					if(idValue == MaterielList[j].MaterielModel && materielName == MaterielList[j].MaterielName){
			            						eval("ContractIncomingDetailed."+name+"=" + "'"+MaterielList[j].Id+"'");
			            						break;
			            					}
			            				}
			            	       }
		            	  }else{
		            		  eval("ContractIncomingDetailed."+name+"=" + "'"+idValue+"'");
		            	  }
		              } else {
		            	  if (name == 'MaterielName'){
		            		  message += ("\n")+"第"+i+"行产品名称不能为空";
		            	  }
		            	  if (name == 'MaterielModel'){
		            		  message += ("\n")+"第"+i+"行规格型号不能为空";
		            	  }
		            	  if (name == 'SettlementMethodRemarks'){
		            		  ContractIncomingDetailed.SettlementMethodRemarks = '';
		            	  }
		            	  if (name == 'CargoRemarks'){
		            		  ContractIncomingDetailed.CargoRemarks = '';
		            	  }
		            	  if (name == 'TransportRemarks'){
		            		  ContractIncomingDetailed.TransportRemarks = '';
		            	  }
		              }              
		           }
		        })
		        $(this).find('select').each(function () {                        //获取td中input的值
		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
			          var idValue = $(this).val(); //获取属性id对应的属性值
		              var name=$(this).attr("name");  //获取该name的属性名称 
		              if (idValue !=''){
		            	  eval("ContractIncomingDetailed."+name+"=" + "'"+idValue+"'");
		              } else {
		            	  if (name == 'SettlementMethod'){
		            		  message += ("\n")+"第"+i+"行结算方式不能为空";
		            	  }
		            	  if (name == 'CargoBalance'){
		            		  message += ("\n")+"第"+i+"行货物结算情况不能为空";
		            	  }
		            	  if (name == 'TransportBalance'){
		            		  message += ("\n")+"第"+i+"行运输结算情况不能为空";
		            	  }
		              } 
		           }
		        }) 
		        
			})				  
			ContractIncomingDetailedList.push(ContractIncomingDetailed);	
			i++;
		})
		if (message!=""){
			swal("操作失败", "合同来料加工明细"+message, "info");
			return;
		}
	}
	
	params.ContractIncomingDetailedList = JSON.stringify(ContractIncomingDetailedList);
	var isSuccessAU = false;
	$.ajax({
        type: "post",
        url: '../../salesContractManagement/addContractInfo.action',
        async:false,
        data:params,
        dataType: "json",
        success: function (data) {
       	 if(data.code == "success"){
       		isSuccessAU = true;
 			swal({
 				title: data.message,
 				text: "",
 				type: data.code,
 				confirmButtonText: '确定',
 				cancelButtonFont: '微软雅黑',
 			},
 			function (){
 				location.href='contract_management.html';
 			});
 			if(flag != 1){
 				updateContractInfoPrefix("HT");
 			}
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
	
	
	if($("#myBlogImage").val()!=""){
		if(isSuccessAU){
		var baseUrl = getContextPath();
		$.ajaxFileUpload({  
	        url:baseUrl+'/fileUp/upload.action',
	        secureuri:false,                           //是否启用安全提交,默认为false   
	        fileElementId:'myBlogImage',   //文件选择框的id属性  
	        data:{"serialID":serialID},		
	        dataType:'json',                           //服务器返回的格式,选择json或者xml貌似有问题
	        success:function(fileData){            //服务器响应成功时的处理函数  
	            if(fileData.fileName!="1"){         //1表示失败
	            	fileNames = fileData.fileName;
	            	fileRoutes = fileData.uploadAddress;
	            	fileserialID = fileData.serialID;
	            	var files = {
	        				"serialID": fileserialID,
	        				"fileName":fileNames,
	        				"fileRoute":fileRoutes
	        		};
	            	$.ajax({
	            		type: "post",
	            		url: baseUrl+'/salesContractManagement/adduploadfile.action',
	            		data:files,
	            		async:false,
	            		dataType: "json",
	            		success: function (data) {
	            			if(data.code == "success"){
	    					}else{
	    						isSuccess = false;
	    						message = "图片上传失败003，请重试";
	    					}
	            		}
	            	});
	            	
	            }
	        },  
	        error:function(data, status, e){ //服务器响应失败时的处理函数  
	            alert("图片上传失败001 文件不能大于10M，请重试")
	        }  
	        });
		}
	}
	
	
	
}
// 删除合同明细
function removeTr1(obj){
	var tr= $(obj).parent().parent();
    tr.remove();
    var len = $('#tableList1 tr').length;
    for (var i = 0; i < len; i++) {
		$('#tableList1 tr:eq('+i+') td:first').text(i);		
	}
}

//删除合同明细
function removeTr2(obj){
	var tr= $(obj).parent().parent();
    tr.remove();
    var len = $('#tableList2 tr').length;
    for (var i = 0; i < len; i++) {
		$('#tableList2 tr:eq('+i+') td:first').text(i);
		
	}
}


function downloadFile(e){
	var okdown = false;
	var fileId = e.id;
	var fName =  $("#files").val();
	  $.ajax({
			type: "post",
			url: '../../salesContractManagement/isHaveUpload.action',
			data:{"fileName":fName},
			async:false,
			dataType: "json",
			success: function (data) {
				if(data.code == "success"){
					okdown = true;
				}
			}
	  })
	  if(okdown){
		  window.location.href = "../../fileUp/fileDownload.action?filename=" +fName;
	  }else{
			swal("操作失败", "文件已被删除", "error");
			return;
	  }
}

// 删除文件
function deleteFile (e) {
	var okdown = false;
	var fileId = e.id
	var serialID = $("#ContractNumber").val()
	var fDName = $("#files").val();
	swal({
		title: "确定要删除吗？",
		text:"删除后将无法恢复，请谨慎操作！",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
	}, function(){
		
		  $.ajax({
				type: "post",
				url: '../../salesContractManagement/isHaveUpload.action',
				data:{"fileName":fDName},
				async:false,
				dataType: "json",
				success: function (data) {
					if(data.code == "success"){
						okdown = true;
					}
				}
		  })
		  
		if(okdown){ 
		$.ajax({
			type: "post",
			url: '../../salesContractManagement/delUploadfile.action',
			data:{"serialID":serialID},
			async:false,
			dataType: "json",
			success: function (data) {
				if(data != null){
					if(data.code == "success"){
						$.ajax({
							url : "../../fileUp/fileDelete.action",
							async : false,
							dataType:'json',
							data : {
								"filename" : fDName
							},
							type : "post",
							success : function(_info) {
								setTimeout(function(){swal({
									title: "操作成功",
									type: "success",
									cancelButtonText: '确定',
									cancelButtonFont: '微软雅黑',
								},
								function(){
									$("#files").val("");
									$("#upShow").css('display','block'); 
									$("#upSelectShow").css('display','none'); 
								}); },200);
							}
						});
					}else{
						 swal("操作失败", "", "error");
					}
				}
			}
		})
		}else{
			setTimeout(function(){swal({
				title: "操作失败,文件已被删除",
				type: "error",
				cancelButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			}); },200);
		}
	});
}

<<<<<<< .mine


////打开文件选择框    
//function selectFile(){         
//	$("#excelFile").trigger("click");    
//} 
//
////文件上传
//function fileUpload(){    
//	var option = {        
//			url : "C:\Users\Administrator\Desktop\新建文件夹",//这里写你的url        
//			type : 'POST',        
//			datatype:'json',//这里是返回类型，一般是json,text        
//			clearForm: true,//提交后是否清空        
//			success : function(map) {         
//				alert("上传"+map.message+"!");        
//			} ,        
//			error:function(data){         
//				alert("页面请求失败！");        
//			}    
//		};   
//	$("#signupListImportForm").ajaxSubmit(option);    
//	return false;	
//}
||||||| .r4


////打开文件选择框    
//function selectFile(){         
//	$("#excelFile").trigger("click");    
//} 
//
////文件上传
//function fileUpload(){    
//	var option = {        
//			url : "C:\Users\Administrator\Desktop\新建文件夹",//这里写你的url        
//			type : 'POST',        
//			datatype:'json',//这里是返回类型，一般是json,text        
//			clearForm: true,//提交后是否清空        
//			success : function(map) {         
//				alert("上传"+map.message+"!");        
//			} ,        
//			error:function(data){         
//				alert("页面请求失败！");        
//			}    
//		};   
//	$("#signupListImportForm").ajaxSubmit(option);    
//	return false;	
//}


// 获取客户编号
function ChangeCustomerName(value){
	
	if(value != ""){
		$.ajax({
			type : "POST",
			url : "../../salesContractManagement/getCustomerName.action",
			async:false,
			data : {},
			dataType : "json",
			success : function(data) {
				$("#CustomerId").val("");
				for (var i = 0; i < data.length; i++) {
					if(value == data[i].CustomerName){
						$("#CustomerId").val(data[i].CustomerCode);
						return;
					}	
				}
			}
		});
	}
}
=======
// 获取客户编号
function ChangeCustomerName(value){
	if(value != ""){
		$.ajax({
			type : "POST",
			url : "../../salesContractManagement/getCustomerName.action",
			async:false,
			data : {},
			dataType : "json",
			success : function(data) {
				$("#CustomerId").val("");
				for (var i = 0; i < data.length; i++) {
					if(value == data[i].CustomerName){
						$("#CustomerId").val(data[i].CustomerCode);
						return;
					}	
				}
			}
		});
	}
}
>>>>>>> .r122
