// 合同id
var id;
// 0:新增  1:修改  2:复制
var flag;
// 物料信息下拉列表
var MaterielInfo;
//合同物料信息下拉列表
var contractMaterielInfo;
//合同物料list
var contractMaterielList;
//客户止运地下拉列表
var TrchangeInfo;
// 物料List
var MaterielList;
//物料型号
var MaterielModel = "<option value='' selected='selected'>请选择</option>";
//运输结算情况
var TransportBalanceInfo;
////税率下拉列表
//var TaxRateInfo;
// 当前登录人姓名
var Creater = "";
// CancelPerson
var CancelPerson = "";
//客户Id
var CustId = 0;
//区别标识
var FlagId = 0;
//物料原材信息下拉列表
var rawMaterielInfo;
//物料产成品信息下拉列表
var finishedMaterielInfo;


//ID客户止运
var TrchangeList;
$(function(){
	// 合同编号下拉列表
	getContractNumber();
	// 客户名称下拉列表
	$("#ContractIdInput").hide();
	var CustomerNameInfo = getCustomerName();
	$("#CustomerId").empty();
	$("#CustomerId").html(CustomerNameInfo);
	// 订单状态下拉列表
	var CustomerNameInfo = getDataDictionary('ddzt');	
	$("#OrderState").empty();
	$("#OrderState").html(CustomerNameInfo);
	// 销售员下拉列表
	var SalesNameInfo = getSalesName();
	$("#UserInfoId").empty();
	$("#UserInfoId").html(SalesNameInfo);
	//销售公司下拉
	var SalesCompany = getSalesCompany();
	$("#SalesCompanyId").empty();
	$("#SalesCompanyId").html(SalesCompany);
	$("#SalesCompanyId").attr("disabled",true)
	$("#SalesCompanyId").css("background","#CCCCCC")
	//物料信息取得
	getMaterielInfo();
	//运输结算情况取得
	TransportBalanceInfo = getDataDictionary('ysjsqk');
//	//税率取得
//	TaxRateInfo = getDataDictionaryDefaultNum('sl');

	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		Creater = userInfo.id;
		CancelPerson = userInfo.name;
	}
	
	// 获取前页面传过来的id
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	if (id!=null&&id!=''){
		// 修改
//		if(flag == 1){
//			$("#OrderNumberFlag").show();
//		}
		// 根据销售订单id获取销售订单信息
		getSalesOrdersInfoById();
	}else{
		// 订单编号
		var orderNumberId =  getContractInfoPrefix("DD")
		$("#OrderNumber").val(orderNumberId);
		// 销售员
		$("#UserInfoId").val(Creater);
		// 销售公司名称
		//$("#SalesCompanyName").val("辽宁瑞德");
		for (var i = 0 ; i < 6 ; i++) {
			//销售订单明细新加一条
			addTable1();
		}
	}	
});

// 合同编号变化时
function changeContractId(){
	FlagId = 1;
	// 合同编号 
	ContractId = document.getElementById("ContractId").value;
	if (ContractId != ''){
		getContractMaterielInfo(ContractId)
		$.ajax({
			type : "POST",
			url : "../../salesContractManagement/getContractInfoById.action",
			async:false,
			data : {"id":ContractId},
			dataType : "json",
			success : function(data) {
				var ContractInfoList = data.ContractInfoList;
				var ContractDetailedList = data.ContractDetailedList;				
				// 客户编号
				$("#CustomerId").val(ContractInfoList[0].customerCode);
				CustId = ContractInfoList[0].customerId;
				// 客户别称
				$("#CustomerAlias").val(ContractInfoList[0].customerName);
				// 送货地址
				$("#Address").val(ContractInfoList[0].address);
				// 联系人
				$("#Contacts").val(ContractInfoList[0].contacts);
				// 联系方式
				$("#Telephone").val(ContractInfoList[0].telephone);
				// 销售员
				$("#UserInfoId").val(ContractInfoList[0].userInfoId);
				// 销售公司名称
				$("#SalesCompanyId").val(ContractInfoList[0].salesCompanyId);
				$("#SalesCompanyId").attr("disabled",true)
				$("#SalesCompanyId").css("background","#CCCCCC")
				// 备注
				$("#remarks").val(ContractInfoList[0].remarks);			  				
				// 合同明细
				$("#tableList1 tr:gt(0)").remove();
				if (ContractDetailedList.length>0){
					for(var i=0;i<ContractDetailedList.length;i++){
						//合同明细新加一条
						addTable1(true);
						// 产品名称
						$("#MaterielName"+(i+1)).val(ContractDetailedList[i].materielName);				
						// 规格型号
						changeMaterielName(i+1,ContractDetailedList[i].materielName);
						$("#MaterielModel"+(i+1)).val(ContractDetailedList[i].materielId);
						// 磅单显示产品名称-型号
						$("#ListModel"+(i+1)).val(ContractDetailedList[i].materielName+"-"+ContractDetailedList[i].materielModel);	
						// 单价
						$("#UnitPrice"+(i+1)).val(ContractDetailedList[i].unitPrice);
						// 销售单价
						$("#SalePrice"+(i+1)).val(ContractDetailedList[i].unitPrice);						
						// 销售数量
						$("#SaleNumber"+(i+1)).val(ContractDetailedList[i].saleNumber);
						// 税率
						$("#TaxRate"+(i+1)).val(ContractDetailedList[i].taxRate);
						// 金额
						$("#SaleMoney"+(i+1)).val(ContractDetailedList[i].money);
						// 备注
						$("#Remarks"+(i+1)).val(ContractDetailedList[i].remarks);
					}
				} else {
					for (var i = 0 ; i < 6 ; i++) {
						//合同明细新加一条
						addTable1();
					}
				}
			}
		});
	} else {
		CustId=0;
		// 客户名称
		$("#CustomerId").val("");
		// 客户别称
		$("#CustomerAlias").val("");
		// 送货地址
		$("#Address").val("");
		// 联系人
		$("#Contacts").val("");
		// 联系方式
		$("#Telephone").val("");
		// 销售员
		$("#UserInfoId").val("");
		// 销售公司名称
		$("#SalesCompanyId").val("");
		$("#SalesCompanyId").removeAttr("disabled")
		$("#SalesCompanyId").css("background","white")
		// 备注
		$("#remarks").val("");
		// 合同明细remove
		$("#tableList1 tr:gt(0)").remove();
		for (var i = 0 ; i < 6 ; i++) {
			//合同明细新加一条
			addTable1();
		}
	}
}

function mouseover(){
	$("#ContractId").removeAttr("disabled")
	var ContractId = $("#ContractId").val();
	if (ContractId!=''){
		$.ajax({
			type: "post",
			url: "../../salesContractManagement/getContractInfo.action",
			data:{"id":ContractId},
			dataType: "json",
			async:false,
			success: function (data) {
				$("#contractName").html(data.data[0].contractName)
				$("#contractDate").html(data.data[0].contractDate);
				$("#customerName").html(data.data[0].customerName);
				$("#salesCompanyId").html(data.data[0].salesCompanyName);
				$("#saleNumber").html(data.data[0].saleNumber);
				if (data.data[0].isIncoming == '0'){
					$("#isIncoming").html('是');
				} else {
					$("#isIncoming").html('否');
				}
				
				//鼠标放在合同编号，显示飘窗
				$('.fuchuang3').show();
			}
		});		
	}	
}

function mouseout(id){
	//鼠标离开合同编号，飘窗消失
	if(flag == 1){
		$("#ContractId").attr("disabled",true);
	}
	$('.fuchuang3').hide();
}

// 客户名称变更
function changeCustomerId(){
	if ($("#CustomerId").val() != ''){
		// 客户名称
		var CustomerId = $("#CustomerId").find("option:selected").text();
		// 客户别称
		$("#CustomerAlias").val(CustomerId);
		$.ajax({
			type : "POST",
			url : "../../salesOrders/getCustomerInfo.action",
			async:false,
			data : {"id":$("#CustomerId").val()},
			dataType : "json",
			success : function(data) {
				// 送货地址
				$("#Address").val(data.address);
				// 联系人
				$("#Contacts").val(data.contacts);
				// 联系方式
				$("#Telephone").val(data.telephone);
			}
		})
	} else {
		// 客户别称
		$("#CustomerAlias").val("");
		// 送货地址
		$("#Address").val("");
		// 联系人
		$("#Contacts").val("");
		// 联系方式
		$("#Telephone").val("");
	}
}

//根据销售订单id获取销售订单信息
function getSalesOrdersInfoById(){
	FlagId = 1;
	$.ajax({
		type : "POST",
		url : "../../salesOrders/getSalesOrdersInfoById.action",
		async:false,
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			var SalesOrdersInfoList = data.SalesOrdersInfoList;
			var SalesOrdersDetailedList = data.SalesOrdersDetailedList;
			// 订单编号
			if(flag == 2){
				var orderNumberId =  getContractInfoPrefix("DD")
				$("#OrderNumber").val(orderNumberId);
			}else{
				$("#OrderNumber").val(SalesOrdersInfoList[0].orderNumber);
			}
			// 合同编号
			$("#ContractId").val(SalesOrdersInfoList[0].contractId);
			getContractMaterielInfo(SalesOrdersInfoList[0].contractId)
			// 客户编号
			$("#CustomerId").val(SalesOrdersInfoList[0].customerCode);
			CustId = SalesOrdersInfoList[0].customerId;
			// 客户别称
			$("#CustomerAlias").val(SalesOrdersInfoList[0].customerAlias);
			// 订单状态
			$("#OrderState").val(SalesOrdersInfoList[0].orderState);
			// 送货地址
			$("#Address").val(SalesOrdersInfoList[0].address);
			// 联系人
			$("#Contacts").val(SalesOrdersInfoList[0].contacts);
			// 联系方式
			$("#Telephone").val(SalesOrdersInfoList[0].telephone);
			// 销售公司名称
			$("#SalesCompanyId").val(SalesOrdersInfoList[0].salesCompanyId);
			$("#SalesCompanyId").attr("disabled",true)
			$("#SalesCompanyId").css("background","#CCCCCC")
			// 销售员
			$("#UserInfoId").val(SalesOrdersInfoList[0].userInfoId);
			// 备注
			$("#remarks").val(SalesOrdersInfoList[0].remarks);			
			// 是否来料加工
			$("input:radio[name=IsActualDelivery][value="+SalesOrdersInfoList[0].isActualDelivery+"]").attr("checked",true);  
			
			// 销售订单明细
			if (SalesOrdersDetailedList.length>0){
				for(var i=0;i<SalesOrdersDetailedList.length;i++){
					//销售订单明细新加一条
					addTable1(true,SalesOrdersDetailedList,i);
					// 产品名称
					$("#MaterielName"+(i+1)).val(SalesOrdersDetailedList[i].materielName);				
					// 规格型号
					changeMaterielName(i+1,SalesOrdersDetailedList[i].materielName);
					$("#MaterielModel"+(i+1)).val(SalesOrdersDetailedList[i].materielId);
					//沥青含量（%）
					$("#AsphaltContent"+(i+1)).val(SalesOrdersDetailedList[i].asphaltContent);
					//兑换后沥青含量（%）
					$("#AsphaltContentExchange"+(i+1)).val(SalesOrdersDetailedList[i].asphaltContentExchange);
					// 单价
					$("#UnitPrice"+(i+1)).val(SalesOrdersDetailedList[i].unitPrice);
					// 磅单显示产品名称-型号
					$("#ListModel"+(i+1)).val(SalesOrdersDetailedList[i].materielName+"-"+SalesOrdersDetailedList[i].materielModel);
					// 是否来料加工
					$("#IsActualDelivery"+(i+1)).val(SalesOrdersDetailedList[i].isActualDelivery);
					if(SalesOrdersDetailedList[i].isActualDelivery == '0'){//实际发货
						$("#IsExchange"+(i+1)).attr("disabled",false);
					}else{
						$("#IsExchange"+(i+1)).css("background","#CCCCCC");
						$("#IsExchange"+(i+1)).attr("disabled",true);
					}
					// 运输结算情况
					$("#TransportBalance"+(i+1)).val(SalesOrdersDetailedList[i].transportBalance);
					// 预计发车时间
					$("#ExpectedDeliveryDate"+(i+1)).val(SalesOrdersDetailedList[i].expectedDeliveryDate);
					// 是否作废
					$("#IsCancel"+(i+1)).val(SalesOrdersDetailedList[i].isCancel);
					if (SalesOrdersDetailedList[i].isCancel == '0'){
						$("#CancelPerson"+(i+1)).attr("disabled",false);//做废人
						$("#CancelDate"+(i+1)).attr("disabled",false);//作废时间
					}
					// 是否兑换
					$("#IsExchange"+(i+1)).val(SalesOrdersDetailedList[i].isExchange);
					if (SalesOrdersDetailedList[i].isExchange == '0'){
						// 产品名称
						$("#MaterielNameDh"+(i+1)).val(SalesOrdersDetailedList[i].exchangeMaterielName);				
						// 规格型号
						changeMaterielNameDh(i+1,SalesOrdersDetailedList[i].exchangeMaterielName);
						$("#MaterielModelDh"+(i+1)).val(SalesOrdersDetailedList[i].exchangeMaterielId);
						$("#Proportion"+(i+1)).attr("disabled",false);//兑换比例
						$("#ExchangeWeight"+(i+1)).removeAttr("disabled"); 
						$("#ExchangeWeight"+(i+1)).attr("disabled",true);//兑换重量
						//兑换重量
						$("#ExchangeWeight"+(i+1)).val((SalesOrdersDetailedList[i].saleNumber*SalesOrdersDetailedList[i].proportion/100).toFixed(2));
						$("#MaterielNameDh"+(i+1)).css("background","white")
						$("#MaterielModelDh"+(i+1)).css("background","white")
						$("#Proportion"+(i+1)).css("background","white")
					}else{
						$("#MaterielNameDh"+(i+1)).css("background","#CCCCCC")
						$("#MaterielModelDh"+(i+1)).css("background","#CCCCCC")
						$("#Proportion"+(i+1)).css("background","#CCCCCC")
					}
					//兑换比例
					$("#Proportion"+(i+1)).val(SalesOrdersDetailedList[i].proportion);
					//if(SalesOrdersDetailedList[i].transportBalance != 1){
						// 起运地点
/*						$("#"+("StartAddress"+(i+1))).find("option").each(function (data) {
							var $this = $(this);
							if($this.text() == SalesOrdersDetailedList[i].startAddress) {
								$this.attr("selected", true);
							}
						})
						$("#StartAddress"+(i+1)).css("background","white")
						$("#StartAddress"+(i+1)).attr("disabled",false);*/
						// 运输地点
						$("#Transports"+(i+1)).attr("disabled",false);
						//$("#Transports"+(i+1)).append("<option value="+SalesOrdersDetailedList[i].id+">"+SalesOrdersDetailedList[i].transports+"</option>");	
						$("#"+("Transports"+(i+1))).find("option").each(function (data) {
							var $this = $(this);
							if($this.text() == SalesOrdersDetailedList[i].transports) {
								$this.attr("selected", true);
							}
						})
						//$("#Transports"+(i+1)).attr("disabled",true);
						$("#Transports"+(i+1)).css("background","white")
						// 运输距离
						$("#Distance"+(i+1)).val(SalesOrdersDetailedList[i].distance);	
						//里程
						$("#Mileage"+(i+1)).val(SalesOrdersDetailedList[i].mileage);
					//}
					// 作废人
					$("#CancelPerson"+(i+1)).val(SalesOrdersDetailedList[i].cancelPerson);
					// 作废时间
					$("#CancelDate"+(i+1)).val(SalesOrdersDetailedList[i].cancelDate);
					// 销售数量
					$("#SaleNumber"+(i+1)).val(SalesOrdersDetailedList[i].saleNumber);
					// 销售单价
					$("#SalePrice"+(i+1)).val(SalesOrdersDetailedList[i].salePrice);	
					// 销售数量
					$("#SaleNumber"+(i+1)).val(SalesOrdersDetailedList[i].saleNumber);
					// 销售单价
					$("#SalePrice"+(i+1)).val(SalesOrdersDetailedList[i].salePrice);
					// 税率
					$("#TaxRate"+(i+1)).val(SalesOrdersDetailedList[i].taxRate);
					// 销售金额
					$("#SaleMoney"+(i+1)).val(SalesOrdersDetailedList[i].saleMoney);
					// 备注
					$("#Remarks"+(i+1)).val(SalesOrdersDetailedList[i].remarks);
					var salesProgressHtml = "订单生成";
					$("#salesProgress"+(i+1)).attr('title',salesProgressHtml);
					if(SalesOrdersDetailedList[i].isLowerHair == 0){
						salesProgressHtml += "==>已下发生产计划"
							$("#salesProgress"+(i+1)).attr('title',salesProgressHtml);
					}else{
						continue;
					}
					if(SalesOrdersDetailedList[i].isInspector == 0){
						salesProgressHtml += "==>质检员已审核"
						$("#salesProgress"+(i+1)).attr('title',salesProgressHtml);
					}else{
						continue;
					}
					if(SalesOrdersDetailedList[i].isExamine == 0){
						salesProgressHtml += "==>工艺通知单已核对"  //原生产任务已核对
						$("#salesProgress"+(i+1)).attr('title',salesProgressHtml);
					}else{
						continue;
					}
					if(SalesOrdersDetailedList[i].isCheck == 0){
						salesProgressHtml += "==>生产任务已确认"
						$("#salesProgress"+(i+1)).attr('title',salesProgressHtml);
					}else{
						continue;
					}
					if(SalesOrdersDetailedList[i].isProduction == 0){
						salesProgressHtml += "==>是否确认生产"
						$("#salesProgress"+(i+1)).attr('title',salesProgressHtml);
					}else{
						continue;
					}
					if(SalesOrdersDetailedList[i].isApplication == 0){
						salesProgressHtml += "==>已提交质检申请"
						$("#salesProgress"+(i+1)).attr('title',salesProgressHtml);
					}else{
						continue;
					}
					if(SalesOrdersDetailedList[i].isQualified == 0){
						salesProgressHtml += "==>已提交质检完成"
						$("#salesProgress"+(i+1)).attr('title',salesProgressHtml);
					}else{
						continue;
					}
					if(SalesOrdersDetailedList[i].IsAdjust == 0){
						salesProgressHtml += "==>已调整"
						$("#salesProgress"+(i+1)).attr('title',salesProgressHtml);
					}else{
						continue;
					}
					if(SalesOrdersDetailedList[i].IsComplete == 0){
						salesProgressHtml += "==>生产完成确认"
						$("#salesProgress"+(i+1)).attr('title',salesProgressHtml);
					}else{
						continue;
					}
					if(SalesOrdersDetailedList[i].isConfirmSubmission == 0){
						salesProgressHtml += "==>确认实际原料消耗"
						$("#salesProgress"+(i+1)).attr('title',salesProgressHtml);
					}else{
						continue;
					}
					if(SalesOrdersDetailedList[i].tareWeight == 0){
						salesProgressHtml += "==>一次称重"
						$("#salesProgress"+(i+1)).attr('title',salesProgressHtml);
					}else{
						continue;
					}
					if(SalesOrdersDetailedList[i].grossWeight == 0){
						salesProgressHtml += "==>二次称重 已发车"
						$("#salesProgress"+(i+1)).attr('title',salesProgressHtml);
					}else{
						continue;
					}
				}
			} else {
				//销售订单明细新加一条
				addTable1();
			}
		}
	});
	if(flag == 1){
		//$("#ContractId").attr("disabled",true);
		$("#ContractIdInput").show();
		var ContractIdOptions=$("#ContractId option:selected")
		$("#ContractIdInput").val(ContractIdOptions.text())
		$("#ContractId").hide();
		$("#ContractIdInput").attr("disabled",true)
		$("#ContractIdInput").css("background","#CCCCCC")
	}
	//运输信息取得
//	getTrchangeInfo();
}

function showProgress(row){
	swal("销售进度进度查询", $("#salesProgress"+row).attr("title"));
}

//合同编号下拉列表
function getContractNumber(){
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getContractInfo.action",
		async:false,
		data : {"ContractState":'0,4,5'},
		dataType : "json",
		success : function(data) {
			var data = data.data;
			var html = "<option value='' selected='selected'>请选择</option>";
			for (var i = 0; i < data.length; i++) {				
				html += "<option value='" + data[i].id + "'>"
				+ data[i].contractNumber + "</option>"						
			}
			$("#ContractId").empty();
			$("#ContractId").html(html);
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

//物料信息取得
function getContractMaterielInfo(id){
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getContractInfoById.action",
		async:false,
		data : {id:id},
		dataType : "json",
		success : function(data) {
			contractMaterielList = data.ContractDetailedList;
			var html = "<option value='' selected='selected'>请选择</option>";
			var MaterielName = "";
			for (var i = 0; i < contractMaterielList.length; i++) {
				if (!MaterielName.match(","+contractMaterielList[i].materielName+",")){
					html += "<option value='" + contractMaterielList[i].materielName + "'>"
					+ contractMaterielList[i].materielName + "</option>"
					MaterielName += ","+contractMaterielList[i].materielName+",";
				}			
			}
			contractMaterielInfo = html;
		}
	});
}

//运输信息取得
function getTrchangeInfo(){
	$.ajax({
		type : "POST",
		url : "../../Transports/getTrchangeInfo.action",
		async:false,
		data : {"id":CustId},
		dataType : "json",
		success : function(data) {
			data = data.list
			TransportsList = data;
			$("#Transports").empty();
			var html = "<option value='' selected='selected'>请选择</option>";
				for (var i = 0; i < data.length; i++) {
					html += "<option value='" + data[i].Id + "'>"
					+ data[i].transports + "</option>";
				}
				TrchangeInfo = html;
		}
	});
}
//销售订单明细新增一条
function addTable1(isFlag,SalesOrdersDetailedList,count,isadd){
	//运输信息取得
	getTrchangeInfo();
	var len = $('#tableList1 tr').length;
	//var rowCount = createRandom2(100,len,90);
	var row;
	var rowCount;
	var salerOrderId;
	var result="";
	if(len==1){
		rowCount = 1;
		row = 1;	
	}else{
		for(var i = 1;i<len;i++){
			rowCount = i+1;
			row = i+1;
		}
		if(!isFlag){
			row+= Math.floor( Math.random() * ((len+20)-len + 1) + len +(new Date()).getTime());
		}
	}
	// 订单明细编号
	var orderDetailedNumber = "";
	if(isFlag) {
		if(flag == 1){
				orderDetailedNumber = SalesOrdersDetailedList[count].orderDetailedNumber
		}else{
			orderDetailedNumber = getContractInfoPrefix("DDMX");
			updateContractInfoPrefix("DDMX");
		}
	}else{
		orderDetailedNumber = getContractInfoPrefix("DDMX");
		updateContractInfoPrefix("DDMX");
	}
	if(SalesOrdersDetailedList!=undefined){
		salerOrderId=0
	}else{
		salerOrderId=1
	}
	var trColor = (row%2 ==0) ?"#E6E6FA":"#E6E6FA";
	var zz = "this.value=this.value.replace(/\\s+/g,'')"
	result +="<tr id='r"+row+"' style='border: 2px solid #708090;height:70px;' bgcolor='"+trColor+"'>";
	result +="<td id='d"+row+"' width=\"30\">"+rowCount+"</td>";
	result +="<td ><a class='del' style='margin: 0 15px;' onclick=\"removeTr1(this,'"+orderDetailedNumber+"',"+flag+","+salerOrderId+")\"><img src='../../img/common/del.png' width='20' height='20' alt='删除' title='删除'></a></td>";
	result +="<td ><textarea draggable='false' rows='2' cols='20' style='background:#CCCCCC;width:150px' type='text' id='orderDetailedNumber"+row+"' name='orderDetailedNumber' readonly='readonly'/></td>";
	result +="<td ><select style='width:90%;' class='MaterielName' name='MaterielName' id='MaterielName"+row+"' onchange='changeMaterielName("+row+",this.options[this.options.selectedIndex].text)'>"+contractMaterielInfo+"</select><span style='color:red;'>*</span>";
	result +="<select style='width:90%;' class='MaterielName' name='MaterielModel' id='MaterielModel"+row+"' onchange='changeMaterielModel("+row+",this.options[this.options.selectedIndex].value)'>"+MaterielModel+"</select><span style='color:red;'>*</span></td>";
	result +="<td ><input style='margin-left: -6px; width:90%; background:#CCCCCC' disabled='disabled' name='AsphaltContent' id='AsphaltContent"+row+"' type='text' maxlength='10' onkeyup='keepNumOrDecimal(this)' onblur='checkPoint(this)'/>";
	result +="<input style='width:90%;' name=\'ListModel\' id='ListModel"+row+"' onkeyup="+zz+"    type=\'text\' maxlength=\'50\'><span style=\'color:red;\'>*</span></td>";
	result +="<td ><select style='margin-left: -6px; width:90%;' name='IsActualDelivery' id='IsActualDelivery"+row+"' onchange='changeIsActualDelivery("+row+",this.options[this.options.selectedIndex].value)'><option value='0' selected>是</option><option value='1'>否</option></select>";
	result +="<select style='width:90%;' name='TransportBalance' id='TransportBalance"+row+"' onchange='Balance("+row+",this.options[this.options.selectedIndex].text)'>"+TransportBalanceInfo+"</select><span style='color:red;'>*</span></td>";
	//result +="<td style='white-space:nowrap;'><input name='ExpectedDeliveryDate' id='ExpectedDeliveryDate"+row+"' class='Wdate' onfocus='WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(dp) {$dp.$('dateTime').value=dp.cal.getDateStr();}})' type='text'/><span style='color:red;'>*</span></td>";
	//result +="<td><select style='width:90%;height:25px;background:#CCCCCC;' name='StartAddress' id='StartAddress"+row+"' disabled='disabled' onchange='OnStartAddress("+row+",this,this.options[this.options.selectedIndex].text)'>"+TrchangeInfo+"</select><span style='color:red;'>*</span>";
	result +="<td><input style='width:90%; background:#CCCCCC' name='UnitPrice' id='UnitPrice"+row+"' type='text' disabled='disabled' maxlength='10' onkeyup='keepNumOrDecimal(this)' onblur='checkPoint(this)'/>";
	result +="<input style='width:90%;' type=\"text\" value=\"\" style='width:160px;' name='ExpectedDeliveryDate' id='ExpectedDeliveryDate"+row+"' onchange=\"isJudgeVisitDate()\" class=\"ui_input_txt01\"    onfocus=\"WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\',onpicked:function(dp) {$dp.$(\'dateTime\').value=dp.cal.getDateStr();}})\"  class=\"Wdate\"/><span style=\'color:red;\'>*</span></td>";
	if(FlagId == 0 && CustId == 0){
		result +="<td ><select style='width:90%;margin-top: -10px;background:#CCCCCC;' name='Transports' id='Transports"+row+"' disabled='disabled' onchange='Trchange("+row+",this,this.options[this.options.selectedIndex].text)'>"+TrchangeInfo+"</select><span style='color:red;'>*</span>";
	}else{
		result +="<td ><select style='width:90%;margin-top: -10px;background:#CCCCCC;' name='Transports' id='Transports"+row+"' disabled='disabled'  onchange='Trchange("+row+",this,this.options[this.options.selectedIndex].text)'>"+TrchangeInfo+"</select><span style='color:red;'>*</span>";
	}
	result +="<input style='width:91%;height:22px;background:#CCCCCC;margin-top: 2px;' name='Distance' id='Distance"+row+"'type='text'  disabled='disabled' maxlength='10'/><span style='color:red;'>*</span></td>";
	result +="<td><input style='width:90%;' name='Mileage' id='Mileage"+row+"'type='text' maxlength='10' onkeyup='keepNumOrDecimal(this)' onblur='checkPoint(this)'/></td>";
	result +="<td><input style='width:90%;height:21px' name='SaleNumber' id='SaleNumber"+row+"'  type='text' onchange='count("+row+")' onkeyup='clearNoDecimal(this,8,4)'/><span style='color:red;'>*</span>";
	result +="<input style='width:90%;height:21px' name='SalePrice' id='SalePrice"+row+"' type='text' onchange='count("+row+")' maxlength='13' onkeyup='clearNoDecimal(this,8,4)'/><span style='color:red;'>*</span></td>";
	result +="<td><input style='margin-left: -6px;width:90%;height:21px' name='TaxRate' id='TaxRate"+row+"' onkeyup='checkNumber(this)' onblur='checkPoint(this)'/>";
	result +="<input style='width:90%;height:21px' name='SaleMoney' id='SaleMoney"+row+"' type='text' maxlength='18' onkeyup='keepNumOrDecimal(this)' onblur='checkPoint(this)'/><span style='color:red;'>*</span></td>";
	result +="<td><select style='width:90%;' name='IsExchange' id='IsExchange"+row+"' onchange='changeIsExchange("+row+",this.options[this.options.selectedIndex].value)'><option value='0'>是</option><option value='1' selected>否</option></select>";
	result +="<input style='width:90%; background:#CCCCCC' disabled='disabled' name='AsphaltContentExchange' id='AsphaltContentExchange"+row+"' type='text' maxlength='10' onkeyup='keepNumOrDecimal(this)'/></td>";
	result +="<td><select  style='width:90%;background:#CCCCCC' name='MaterielNameDh' disabled='disabled' id='MaterielNameDh"+row+"' onchange='changeMaterielNameDh("+row+",this.options[this.options.selectedIndex].text)'>"+rawMaterielInfo+"</select><span style='color:red;'>*</span>";
	result +="<select style='width:90%;background:#CCCCCC' name='MaterielModelDh' disabled='disabled' id='MaterielModelDh"+row+"'" +
			/*" onchange='changeMaterielModelDh("+row+",this.options[this.options.selectedIndex].value)'"*/ 
					">"+MaterielModel+"</select><span style='color:red;'>*</span></td>";
	result +="<td><input style='width:90%;height:21px;background:#CCCCCC' onkeyup=\"keepNumOrDecimal(this);\"   name='Proportion'  onchange='Exchange("+row+")'  id='Proportion"+row+"' type='text' disabled='disabled' maxlength='10'/><span style='color:red;'>*</span>";
	result +="<input style='width:90%;height:21px;background:#CCCCCC' name='ExchangeWeight' id='ExchangeWeight"+row+"'type='text' disabled='disabled' maxlength='10' onkeyup='clearNoDecimal(this,8,4)'/><span style='color:red;'>*</span></td>";
	result +="<td ><select style='margin-left: -6px; width:90%;margin-top: -1px;' name='IsCancel' id='IsCancel"+row+"' onchange='changeIsCancel("+row+",this.options[this.options.selectedIndex].value)'><option value='1'>否</option><option value='0'>是</option></select>";
	result +="<input style='width:90%;background:#CCCCCC' name='CancelPerson' id='CancelPerson"+row+"' type='text' disabled='disabled' maxlength='10'/><span style='color:red;'>*</span></td>";
/*	result +="<td style=\'white-space:nowrap;\'><input type=\"text\" value=\"\"  name='CancelDate' id='CancelDate"+row+"' onchange=\"isJudgeVisitDate()\" class=\"ui_input_txt01\"    disabled='disabled' onfocus=\"WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\',onpicked:function(dp) {$dp.$(\'dateTime\').value=dp.cal.getDateStr();}})\"  class=\"Wdate\"/><span style=\'color:red;\'>*</span></td>";
	*/
	result +="<td ><textarea  style='resize:none;'  draggable='false' rows='2' cols='20'  name='Remarks' id='Remarks"+row+"' type='text' maxlength='200'/></td>";
	if (id!=null&&id!=''&& isFlag == true){
		result += "<td><p onclick='showProgress("+row+")' id='salesProgress"+row+"' title=''>鼠标移动此次查看<br>销售进度</p></td>"
/*		result +=
		"<td  colspan='2'>销售进度</td>"+
		"<td colspan='13'>" +
		"<input type='text' value='' style='width: 100%;float: right;' readonly='readonly' class='ui_input_txt02 col-lg-8 col-sm-12' id='salesProgress"+row+"' name='salesProgress"+row+"' maxlength='200'/>"+
		"</td>"*/
	}
	result +="</tr>";
	$("#tableList1").append(result);
	$("#"+("orderDetailedNumber"+row)).val(orderDetailedNumber); 
}

//产品名称选择
function changeMaterielName(data,value){
	//规格型号取得
	getMaterielModel(data,value);
	//样式的变化
	if (value == "乳化沥青"){
		$("#AsphaltContent"+data).css("background","white");//沥青含量
		$("#AsphaltContent"+data).attr("disabled",false);//沥青含量
	} else {
		$("#AsphaltContent"+data).css("background","#CCCCCC");//沥青含量
		$("#AsphaltContent"+data).attr("disabled",true);//沥青含量
		document.getElementById("AsphaltContent"+data).value = "";
	}
}
//运输地点选择
function Trchange(data,tis,value){
	//运输距离取得
	if(tis.value == ''){
		$("#Distance"+data).val("")
		return;
	}
	getTrchange(data,tis,value);
}

//止运地变化
/*
function OnStartAddress(data,tis,value){
	$("#Transports"+data).empty();
	$("#Transports"+data).attr("disabled",false);//做废人
	if(value == '请选择'){
		$("#Transports"+data).attr("disabled",true);
		document.getElementById("Transports"+data).value = "";
		document.getElementById("Distance"+data).value = "";
		$("#Transports"+data).css("background","#CCCCCC")
	}else{
		var idval = tis.value;
		$.ajax({
			type : "POST",
			url : "../../salesOrders/getCustomertransportsById.action",
			async:false,
			data : {"id":idval,"startAddress":value},
			dataType : "json",
			success : function(data) {
				TrchangeList = data.data
			}
		});
		
		$("#Transports"+data).css("background","white")
		$("#Transports"+data).append("<option value=''>请选择</option>");
		for (var i = 0; i < TrchangeList.length; i++) {
			$("#Transports"+data).append("<option value="+TrchangeList[i].Id+">"+TrchangeList[i].transports+"</option>");
		}
	}
}
*/
//运输结算情况变化
function Balance (data,value){
//$("#StartAddress"+data).attr("disabled",false);//做废人
	$("#Transports"+data).attr("disabled",false);
	$("#Transports"+data).css("background","white")
/*	if(value == '客户自提'){
		//$("#StartAddress"+data).attr("disabled",true);
		//document.getElementById("StartAddress"+data).value = "";
		document.getElementById("Transports"+data).value = "";
		$("#Transports"+data).attr("disabled",true);
		$("#Transports"+data).css("background","#CCCCCC")
		document.getElementById("Distance"+data).value = "";
		//$("#StartAddress"+data).css("background","#CCCCCC")
	}else{
		//$("#StartAddress"+data).css("background","white")
	}*/
}
//规格型号取得
function getMaterielModel(data,value){
	if (MaterielList!=null){
		var html = "<option value='' selected='selected'>请选择</option>";
		for (var i = 0; i < MaterielList.length; i++) {
			if (MaterielList[i].MaterielName.replace(/\s*/g,"") == value.replace(/\s*/g,"")){
				html += "<option value='" + MaterielList[i].Id + "'>"
				+ MaterielList[i].MaterielModel + "</option>"
			}			
		}
		$("#MaterielModel"+data).empty();
		$("#MaterielModel"+data).html(html);
	}	
}
//兑换产品名称选择
function changeMaterielNameDh(data,value){
	//规格型号取得
	getMaterielModelDh(data,value);
	//样式的变化
	if (value == "乳化沥青"){
		$("#AsphaltContentExchange"+data).css("background","white");//沥青含量
		$("#AsphaltContentExchange"+data).attr("disabled",false);//沥青含量
	} else {
		$("#AsphaltContentExchange"+data).css("background","#CCCCCC");//沥青含量
		$("#AsphaltContentExchange"+data).attr("disabled",true);//沥青含量
		document.getElementById("AsphaltContentExchange"+data).value = "";
	}
}
//兑换规格型号取得
function getMaterielModelDh(data,value){
	if (MaterielList!=null){
		var html = "<option value='' selected='selected'>请选择</option>";
		for (var i = 0; i < MaterielList.length; i++) {
			if (MaterielList[i].MaterielName.replace(/\s*/g,"") == value.replace(/\s*/g,"")){
				html += "<option value='" + MaterielList[i].Id + "'>"
				+ MaterielList[i].MaterielModel + "</option>"
			}			
		}
		$("#MaterielModelDh"+data).empty();
		$("#MaterielModelDh"+data).html(html);
	}	
}
//运输地点取得
function getTrchange(row,tis,value){
	var DistanceList;
	var idval = tis.value;
	$.ajax({
		type : "POST",
		url : "../../salesOrders/getCustomertransportsById.action",
		async:false,
		data : {"id":idval,"Transports":value},
		dataType : "json",
		success : function(data) {
			DistanceList = data.data
			$("#Distance"+row).val(DistanceList[0].distance);
		}
	});
/*	
	if (TrchangeList!=null){
		for (var i = 0; i < TrchangeList.length; i++) {
			if (TrchangeList[i].transports == value){
				$("#Distance"+data).val(TrchangeList[i].distance);
			}			
		}
	}	*/
}


//规格型号选择
function changeMaterielModel(data,value){
	if (MaterielList!=null){
		for (var i = 0; i < MaterielList.length; i++) {
			if (MaterielList[i].Id == value){
				// 单价
				document.getElementById("UnitPrice"+data).value = MaterielList[i].NoTaxMoney;
				// 销售单价
				document.getElementById("SalePrice"+data).value = MaterielList[i].NoTaxMoney;
				// 磅单显示产品名称-型号
				//document.getElementById("ListModel"+data).value = document.getElementById("MaterielName"+data).options[data].text 
				//+ "-" +document.getElementById("MaterielModel"+data).options[data].text;
				var Mname = $("#MaterielName"+data+" option:selected").text();
				var Mmodel = $("#MaterielModel"+data+" option:selected").text()
				// 磅单显示产品名称-型号
				document.getElementById("ListModel"+data).value = Mname+"-"+Mmodel;
			}
		}
		// 计算金额
		count(data);
	}	
}

function Exchange(data){
	// 数量 
	SaleNumber = document.getElementById("SaleNumber"+data).value;
	// 兑换比例
	Proportion = document.getElementById("Proportion"+data).value;
	// 兑换重量
	document.getElementById("ExchangeWeight"+data).value = (SaleNumber/100*Proportion).toFixed(4);
}
//计算金额
function count(data){
	// 销售单价
	SalePrice = document.getElementById("SalePrice"+data).value;
	// 数量 
	SaleNumber = document.getElementById("SaleNumber"+data).value;
	// 税率
//	TaxRate = $("#TaxRate"+data+" option:selected").text();
	//TaxRate = document.getElementById("TaxRate"+data).value;
	// 销售金额
	//document.getElementById("SaleMoney"+data).value = (SalePrice*(1+TaxRate/100)*SaleNumber).toFixed(2);
	document.getElementById("SaleMoney"+data).value = (SalePrice*SaleNumber).toFixed(2);
	if(document.getElementById("IsExchange"+data).value == 0){
		// 数量 
		SaleNumber = document.getElementById("SaleNumber"+data).value;
		// 兑换比例
		Proportion = document.getElementById("Proportion"+data).value;
		// 兑换重量
		document.getElementById("ExchangeWeight"+data).value = (SaleNumber/100*Proportion).toFixed(2);
	}
}

//是否作废变化时
function changeIsCancel(data,value){
	// 作废的场合 
	if (value == "0"){
		$("#CancelPerson"+data).attr("disabled",false);//做废人
		$("#CancelPerson"+data).css("background","white")
		document.getElementById("CancelPerson"+data).value = CancelPerson;
	} else {
		$("#CancelPerson"+data).attr("disabled",true);//做废人
		$("#CancelPerson"+data).css("background","#CCCCCC")
		document.getElementById("CancelPerson"+data).value = "";
	}
}
//是否兑换变化时
function changeIsExchange(data,value){
	// 兑换的场合 
	if (value == "0"){
		$("#MaterielNameDh"+data).css("background","white");//产品名称
		$("#MaterielModelDh"+data).css("background","white");//产品名称
		$("#Proportion"+data).css("background","white");//兑换比例
		$("#MaterielNameDh"+data).attr("disabled",false);//产品名称
		$("#MaterielModelDh"+data).attr("disabled",false);//规格型号
		$("#Proportion"+data).attr("disabled",false);//兑换比例
		//$("#ExchangeWeight"+data).removeAttr("disabled"); 
		//$("#ExchangeWeight"+data).attr("disabled",false);//兑换重量
	} else {
		$("#MaterielNameDh"+data).css("background","#CCCCCC");//产品名称
		$("#MaterielModelDh"+data).css("background","#CCCCCC");//产品名称
		$("#Proportion"+data).css("background","#CCCCCC");//产品名称
		$("#MaterielNameDh"+data).attr("disabled",true);//产品名称
		$("#MaterielModelDh"+data).attr("disabled",true);//规格型号
		$("#Proportion"+data).attr("disabled",true);//兑换比例
		$("#ExchangeWeight"+data).attr("disabled",true);//兑换重量
		document.getElementById("Proportion"+data).value = "";
		document.getElementById("ExchangeWeight"+data).value = "";
		document.getElementById("MaterielNameDh"+data).value = "";
		document.getElementById("MaterielModelDh"+data).value = "";
		$("#AsphaltContentExchange"+data).css("background","#CCCCCC");//沥青含量
		$("#AsphaltContentExchange"+data).attr("disabled",true);//沥青含量
		document.getElementById("AsphaltContentExchange"+data).value = "";
	}
}
//是否实际发货变化时
function changeIsActualDelivery(data,value){
	//实际发货的场合
	if(value == "0"){
		$("#IsExchange"+data).css("background","white");//是否兑换
		$("#IsExchange"+data).attr("disabled",false);
	}else{
		$("#IsExchange"+data).css("background","#CCCCCC");//是否兑换
		$("#IsExchange"+data).attr("disabled",true);
		document.getElementById("IsExchange"+data).value = "1";
		//不兑换
		$("#MaterielNameDh"+data).css("background","#CCCCCC");//产品名称
		$("#MaterielModelDh"+data).css("background","#CCCCCC");//产品名称
		$("#Proportion"+data).css("background","#CCCCCC");//产品名称
		$("#MaterielNameDh"+data).attr("disabled",true);//产品名称
		$("#MaterielModelDh"+data).attr("disabled",true);//规格型号
		$("#Proportion"+data).attr("disabled",true);//兑换比例
		$("#ExchangeWeight"+data).attr("disabled",true);//兑换重量
		document.getElementById("Proportion"+data).value = "";
		document.getElementById("ExchangeWeight"+data).value = "";
		document.getElementById("MaterielNameDh"+data).value = "";
		document.getElementById("MaterielModelDh"+data).value = "";
		$("#AsphaltContentExchange"+data).css("background","#CCCCCC");//沥青含量
		$("#AsphaltContentExchange"+data).attr("disabled",true);//沥青含量
		document.getElementById("AsphaltContentExchange"+data).value = "";
	}
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

// 联系方式
function changeTelephone(value){
	var regBox = {
	        regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/,//手机
	        regTel : /^0[\d]{2,3}-[\d]{7,8}$/ // 固定电话
	    }
	if(value != null && value != ''){
		var mflag = regBox.regMobile.test(value);
	    var tflag = regBox.regTel.test(value);
	    if (!(mflag || tflag)) {	    	
    		swal({
	 			title: "错误提示",
	 			text: "联系方式格式错误,请重新填写!",
	 			type: "error",
	 			confirmButtonText: '确定',
	 			cancelButtonFont: '微软雅黑',
	 		},function(){
	 		// 清空处理
				$("#Telephone").val("");
	 		});    	
	    }
	}
}

//保存
function save(){
	var outboundList;
	// 订单编号
	/*var OrderNumber = $("#OrderNumber").val();
	if(OrderNumber==""){
		swal("操作失败", "订单编号不能为空", "info");
		return;
	}*/
	// 合同编号
	$("#SalesCompanyId").removeAttr("disabled")
	$("#ContractId").removeAttr("disabled")
	var ContractId = $("#ContractId").val();
	if(ContractId==""){
		swal("操作失败", "合同编号不能为空", "info");
		return;
	}
	var isAdd = true;
	$.ajax({
        type: "post",
        url: '../../salesContractManagement/getContractInfo.action',
        async:false,
        data:{"id":$("#ContractId").val()},
        dataType: "json",
        success: function (data) {
        	outboundList = data.data
        	if(outboundList.length == 0){
        		isAdd = false;
        	}
        }
	})
	if(isAdd == false){
		swal("操作失败", "该合同已在别处删除,不可添加", "info");
		return;
	}
	// 客户名称
	var CustomerId = $("#CustomerId").val();
	if(CustomerId==""){
		swal("操作失败", "客户名称不能为空", "info");
		return;
	}
	// 客户别称
	var CustomerAlias = $("#CustomerAlias").val();
	if(CustomerAlias==""){
		swal("操作失败", "客户别称不能为空", "info");
		return;
	}
	// 订单状态
	var OrderState = $("#OrderState").val();
	if(OrderState==""){
		swal("操作失败", "订单状态不能为空", "info");
		return;
	}
	
	var isZuofei = false;
	if(id!=""&&flag==1&&OrderState==3){
		var salesOrdersList;
		$.ajax({
			type: "post",
	        url: '../../outbound/getInfoList.action',
	        async:false,
	        data:{"salesOrderId":id},
	        dataType: "json",
	        success: function (data) {
	        	outboundList = data.data
	        	if(outboundList.length>0){
	        		//swal("操作失败", "该合同已生成销售订单,不可作废", "info");
	        		isZuofei = true;
	        	}
	        }
	       })
	}
	if(isZuofei){
		swal("操作失败", "该订单已在别处调用,不可作废", "info");
		return;
	}
	
	
	// 送货地址
	var Address = $("#Address").val();
	if(Address==""){
		swal("操作失败", "送货地址不能为空", "info");
		return;
	}
	// 联系人
	var Contacts = $("#Contacts").val();
	if(Contacts==""){
		swal("操作失败", "联系人不能为空", "info");
		return;
	}
	// 联系方式
	var Telephone = $("#Telephone").val();
	if(Telephone==""){
		swal("操作失败", "联系方式不能为空", "info");
		return;
	}
	// 销售公司名称
	var SalesCompanyId = $("#SalesCompanyId").val();
	if(SalesCompanyId==""){
		swal("操作失败", "销售公司名称不能为空", "info");
		return;
	}
	// 销售员
	var UserInfoId = $("#UserInfoId").val();
	if(UserInfoId==""){
		swal("操作失败", "销售员不能为空", "info");
		return;
	}
	// 备注
	var remarks = $("#remarks").val();
	// 是否实际发货
	var IsActualDelivery = $("#IsActualDelivery").val();
//	//是否兑换
//	var IsExchange = $("#IsExchange").val();
//	//兑换比例
//	var ExchangeRatio = $("#ExchangeRatio").val();
//	//兑换重量
//	var SaleNumber = $("#SaleNumber").val;
//	var ExchangeWeight = ExchangeRatio*SaleNumber;
	
	var params = {
			"id":id,
			"flag":flag,
			// "OrderNumber" : OrderNumber,
			"ContractId" : ContractId,			
			"CustomerId" : CustId,			
			"CustomerAlias" : CustomerAlias,			
			"OrderState" : OrderState,			
			"Address" : Address,
			"Contacts" : Contacts,
			"Telephone" : Telephone,
			"SalesCompanyId" : SalesCompanyId,
			"UserInfoId" : UserInfoId,
			"remarks" : remarks,
			"IsActualDelivery" : IsActualDelivery,
			"Creater" : Creater
	};
	// 合同编号
//	params.OrderNumber = getContractInfoPrefix("DD");
//	updateContractInfoPrefix("DD");
	if(flag != 1){
		// 合同编号
		params.OrderNumber = getContractInfoPrefix("DD");
	}else{
		var OrderNumber = $("#OrderNumber").val();
		params.OrderNumber = OrderNumber;
	}
	// 订单明细
	var SalesOrdersDetailedList = [];
	var i = 0;
	var swalMessage = "";
	//重复数据验证
	var list = $("#tableList1 tr:not(:first)")
	var isRepeatNext = false;
	for (var q = 1; q < list.length; q++) {
			 var tableObj = document.getElementById("tableList1");
	         var trObjArr = tableObj.rows;
	         var result =  trObjArr[q].id;	         
	         var count = parseInt(result.replace(/[^0-9]/ig,""))        
	            
		 var MaterielNameIsRepeat = $("#"+("MaterielName"+count)).val();
		 var MaterielModelIsRepeat = $("#"+("MaterielModel"+count)).val();
		 var IsActualDeliveryIsRepeat = $("#"+("IsActualDelivery"+count)).val();
		 var TransportsIsRepeat = $("#"+("Transports"+count)).val();
		 var IsExchangeIsRepeat = $("#"+("IsExchange"+count)).val();
		 
		 for (var m = q+1; m <= list.length; m++) {
			 var resultNext =  trObjArr[m].id;
			 var countNext = parseInt(resultNext.replace(/[^0-9]/ig,""))
			 var MaterielNameIsRepeatNext = $("#"+("MaterielName"+countNext)).val();
			 var MaterielModelIsRepeatNext = $("#"+("MaterielModel"+countNext)).val();
			 var IsActualDeliveryIsRepeatNext = $("#"+("IsActualDelivery"+countNext)).val();
			 var TransportsIsRepeatNext = $("#"+("Transports"+countNext)).val();
			 var IsExchangeIsRepeatNext = $("#"+("IsExchange"+countNext)).val();
			 if(MaterielNameIsRepeat==MaterielNameIsRepeatNext 
					 && MaterielModelIsRepeat==MaterielModelIsRepeatNext 
					 && IsActualDeliveryIsRepeat==IsActualDeliveryIsRepeatNext 
					 && TransportsIsRepeat==TransportsIsRepeatNext 
					 && IsExchangeIsRepeat == IsExchangeIsRepeatNext){
				 isRepeatNext = true;
			 }
		 }	 
	}
	
	if(isRepeatNext){
		swal("操作失败", "信息重复", "info");
		return;
	}
	$('#tableList1 tbody').find('tr').each(function () { 
		var SalesOrdersDetailed = {};
		var swalFlag = false;
		var message = "";
		var unfilled = 0;
		$(this).find('td').each(function () {				
			$(this).find('input').each(function () {                         //获取td中input的值
	           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
	              var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name");  //获取该name的属性名称 
	              if (idValue !=""){
	            	  eval("SalesOrdersDetailed."+name+"=" + "'"+idValue+"'");
	            	  swalFlag = true;
	              } else {
	            	  //沥青含量
	            	  if(name == 'AsphaltContent'){
	            		  SalesOrdersDetailed.AsphaltContent = '';
	            	  }
	            	  //兑换后沥青含量（%）
	            	  if(name == 'AsphaltContentExchange'){
	            		  SalesOrdersDetailed.AsphaltContentExchange = '';
	            	  }
	            	  //里程
	            	  if(name == 'Mileage'){
	            		  SalesOrdersDetailed.Mileage = '';
	            	  }
	            	  if (name == 'UnitPrice'){
	            		  message += ("\n")+"第"+i+"行单价不能为空";
	            		  unfilled += 1;
	            	  }
	            	  if (name == 'ListModel'){
	            		  message += ("\n")+"第"+i+"行磅单显示产品名称-型号不能为空";
	            		  unfilled += 1;
	            	  }
	            	  if (name == 'ExpectedDeliveryDate'){
	            		  message += ("\n")+"第"+i+"行预计发车时间不能为空";
	            		  unfilled += 1;
	            	  }
	            	  if (name == 'CancelPerson'){
	            		  if (SalesOrdersDetailed.IsCancel == '0'){
	            			  message += ("\n")+"第"+i+"行作废人不能为空";
	            			  unfilled += 1;
	            		  } else {
	            			  SalesOrdersDetailed.CancelPerson = '';
	            		  }	            		 
	            	  }
	            	  if (name == 'CancelDate'){
	            		  if (SalesOrdersDetailed.IsCancel == '0'){
	            			  message += ("\n")+"第"+i+"行作废时间不能为空";
	            			  unfilled += 1;
	            		  } else {
	            			  SalesOrdersDetailed.CancelDate = '';
	            		  }	            		 
	            	  }
	            	  if (name == 'SaleNumber'){
	            		  message += ("\n")+"第"+i+"行销售数量不能为空";
	            		  unfilled += 1;
	            	  }
	            	  if (name == 'SalePrice'){
	            		  message += ("\n")+"第"+i+"行销售单价不能为空";
	            		  unfilled += 1;
	            	  }
	            	  if (name == 'SaleMoney'){
	            		  message += ("\n")+"第"+i+"行销售金额不能为空";
	            		  unfilled += 1;
	            	  }
	            	  if (name == 'Proportion'){
	            		  if (SalesOrdersDetailed.IsExchange == '0'){
	            			  message += ("\n")+"第"+i+"行兑换比例不能为空";
	            			  unfilled += 1;
	            		  } else {
	            			  SalesOrdersDetailed.Proportion = '';
	            		  }	            		 
	            	  }
	            	  if (name == 'ExchangeWeight'){
	            		  if (SalesOrdersDetailed.IsExchange == '0'){
	            			  message += ("\n")+"第"+i+"行兑换重量不能为空";
	            			  unfilled += 1;
	            		  } else {
	            			  SalesOrdersDetailed.ExchangeWeight = '';
	            		  }	            		 
	            	  }
	            	//  if (name == 'Remarks'){
	            	//	  SalesOrdersDetailed.Remarks = '';
	            	//  }
	              }              
	           }
	        })
	        var TransportBalanceisOne;
			var StartAddressOne;
	        $(this).find('select').each(function () {                        //获取td中input的值
	           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		          var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name");  //获取该name的属性名称 
	              if (idValue !=''){
/*	            	  var row = parseInt(this.id.replace(/[^0-9]/ig,""))
	            	  TransportsOne = $("#"+("StartAddress"+row)).val();*/
	          /*  	  	if(name == 'TransportBalance' && idValue != 1 && TransportsOne ==""){
	        	        		message += ("\n")+"第"+i+"条起运地点不能为空";
	            	  	}else *//*if(name != 'StartAddress' && name != 'Transports'){
	            	  		eval("SalesOrdersDetailed."+name+"=" + "'"+idValue+"'");
	            	  		swalFlag = true;
	            	  	}*/
	            	  	if(name != 'Transports'){
	            	  		eval("SalesOrdersDetailed."+name+"=" + "'"+idValue+"'");
	            	  		swalFlag = true;
	            	  	}
	            	  	
/*	            	  	if(name == 'StartAddress'){
	            	  		eval("SalesOrdersDetailed."+name+"=" + "'"+$(this).find("option:selected").text()+"'");
	            	  	}*/
	            	  	
	            	  	if(name == 'Transports'){
	            	  		/*if($(this).find("option:selected").text() == '' ||$(this).find("option:selected").text() == '请选择'){
	            	  			message += ("\n")+"第"+i+"条请选择止运地点";
	            	  			unfilled += 1;
	            	  		}*/
	            	  		eval("SalesOrdersDetailed."+name+"=" + "'"+$(this).find("option:selected").text()+"'");
	            	  	}
	            	  	
	              } else {
	            	  var row = parseInt(this.id.replace(/[^0-9]/ig,""))
	            	  TransportBalanceisOne =  $("#"+("TransportBalance"+row)).val();
	            	  //StartAddressOne =  $("#"+("StartAddress"+row)).val();
/*	            	  if(name == 'StartAddress' && TransportBalanceisOne !=1){
	            		  message += ("\n")+"第"+i+"条请选择起运地点";
	            		  unfilled += 1;
	            	  }*/
	            	  if(name == 'Transports'){
	            		  message += ("\n")+"第"+i+"条请选择止运地点";
	            		  unfilled += 1;
	            	  }
	            	  
	            	  if (name == 'MaterielNameDh'){
	            		  if(SalesOrdersDetailed.IsExchange == '0'){
	            			  message += ("\n")+"第"+i+"行兑换产品名称不能为空";
		            		  unfilled += 1;
	            		  }
	            	  }
	            	  if (name == 'MaterielModelDh'){
	            		  if(SalesOrdersDetailed.IsExchange == '0'){
	            			  message += ("\n")+"第"+i+"行兑换规格型号不能为空";
		            		  unfilled += 1;
	            		  }
	            	  }
	            	  	// 是否实际发货
	            		if(name == 'IsActualDelivery'){
	            			message += ("\n")+"第"+i+"行请选择是否实际发货";
	            			unfilled += 1;
	            		}
	            		// 是否兑换
	            		if(name == 'IsExchange'){
	            			message += ("\n")+"第"+i+"行请选择是否兑换";
	            			unfilled += 1;
	            		}
	            	  if (name == 'TransportBalance'){
	            		  message += ("\n")+"第"+i+"行运输结算情况不能为空";
	            		  unfilled += 1;
	            	  }
//	            	  if (name == 'TaxRate'){
//	            		  SalesOrdersDetailed.TaxRate = '';
//	            	  }
	              } 
	           }
	        })  
	        $(this).find('input').each(function () {                         //获取td中input的值
	           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
	              var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name");  //获取该name的属性名称 
	              if (idValue !=''){
	            	  eval("SalesOrdersDetailed."+name+"=" + "'"+idValue+"'");
	              } else {
	            	  if (name == 'TaxRate'){
		            	  message += ("\n")+"第"+i+"行税率不能为空";
	            		  unfilled += 1;
	            	  }
	              }
	           }
	        });
	        $(this).find('textarea').each(function () {                        //获取td中input的值
		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
			          var idValue = $(this).val(); //获取属性id对应的属性值
		              var name=$(this).attr("name");  //获取该name的属性名称 
		              if (idValue !=''){
		            	  eval("SalesOrdersDetailed."+name+"=" + "'"+idValue+"'");
		            	  swalFlag = true;
		              } else {
		            	  if (name == 'Remarks'){
		            		  SalesOrdersDetailed.Remarks = '';
		            	  }
		              } 
		           }
		        })      
		})
		
/*			 var tableList1list = $("#tableList1")
	         var TransportBalanceisOne;
			 var TransportsOne;
	        for (var m = 0; m < tableList1list[0].rows.length; m++) {
	        	TransportBalanceisOne = $("#"+("TransportBalance"+m)).val();
	        	TransportsOne = $("#"+("Transports"+m)).val();
	        	if(TransportBalanceisOne != 1 && TransportsOne ==""){
	        		message += ("\n")+"第"+m+"条运输地点不能为空";
	        		swalFlag = false;
	        	}
			}*/
		
		if (swalFlag) {
			SalesOrdersDetailedList.push(SalesOrdersDetailed);
		} 
		// 当该行所有必填项都未填时，不提示未填信息
		if (unfilled < 16) {
			swalMessage += message;
		}
		i++;
	})
	if (swalMessage!=""){
		swal("操作失败", "订单明细"+swalMessage, "info");
		return;
	}
	params.SalesOrdersDetailedList = JSON.stringify(SalesOrdersDetailedList);
	$.ajax({
        type: "post",
        url: '../../salesOrders/addSalesOrdersInfo.action',
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
 				location.href='sales_orders.html';
 			});
 			if(flag != 1){
 				updateContractInfoPrefix("DD");
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
}

//删除销售订单明细
function removeTr1(obj,seid,isAdd,isNewAdd){	
	if(isAdd == 1 && isNewAdd == 0){
		swal({
			title: "确定操作吗？",
			text:"删除后将无法恢复，请谨慎操作！",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: '#AEDEF4',
			confirmButtonText: '确定',
			cancelButtonText: '取消',
			cancelButtonFont: '微软雅黑',
		},
		function(){
			var len = $('#tableList1 tr').length;
			var tr= $(obj).parent().parent();
			if(len<3){
				setTimeout(function(){swal({
					title: "最少保留一条明细",
					type: "error",
					cancelButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
				}); },200);
			}else{
		    	$.ajax({
		    		type: "post",
		    		url: '../../salesOrders/updateIsDelSalesOrdersDetailedByOrderDetailedNumber.action',
		    		async:false,
		    		data:{"orderDetailedNumber":seid},
		    		dataType: "json",
		    		success: function (data) {
		    			if(data.code == "existence"){
		    				setTimeout(function(){swal({
		    					title: "该订单明细已在别处调用,不可删除",
		    					type: "error",
		    					cancelButtonText: '确定',
		    					cancelButtonFont: '微软雅黑',
		    				},
		    				function(){
		    				}); },200);
		    			}else if(data.code == "success"){
		    				tr.remove();
		    			}else{
		    				setTimeout(function(){swal({
		    					title: "操作失败",
		    					type: "error",
		    					cancelButtonText: '确定',
		    					cancelButtonFont: '微软雅黑',
		    				},
		    				function(){
		    				}); },200);
		    				return;
		    			}
		    		}
		    	});
			}
		    for (var i = 0; i < len; i++) {
				$('#tableList1 tr:eq('+i+') td:first').text(i);		
			}
	});
		}else{
			var len = $('#tableList1 tr').length;
			var tr= $(obj).parent().parent();
			if(len<3){
				swal("操作失败", "最少保留一条详细", "info");
				return;
			}else{
				tr.remove();
			}
		    for (var i = 0; i < len; i++) {
				$('#tableList1 tr:eq('+i+') td:first').text(i);		
			}
		}
	
	
/*	var len = $('#tableList1 tr').length;
	var tr= $(obj).parent().parent();
	if(len<3){
		swal("操作失败", "最少保留一条详细", "info");
		return;
	}else{
		tr.remove();
	}
    for (var i = 0; i < len; i++) {
		$('#tableList1 tr:eq('+i+') td:first').text(i);		
	}*/
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
||||||| .r4
				if(data[i].MaterielType == 1 || data[i].MaterielType == 3){
=======
				/*if(data[i].MaterielType == 1 || data[i].MaterielType == 3){
>>>>>>> .r122
					if (!MaterielName.match(","+data[i].MaterielName+",")){
						rawhtml += "<option value='" + data[i].MaterielName + "'>"
						+ data[i].MaterielName + "</option>"
						MaterielName += ","+data[i].MaterielName+",";
					}			
				}*/
				//获取所有物料名称
				if (!MaterielName.match(","+data[i].MaterielName+",")){
					rawhtml += "<option value='" + data[i].MaterielName + "'>"
					+ data[i].MaterielName + "</option>"
					MaterielName += ","+data[i].MaterielName+",";
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