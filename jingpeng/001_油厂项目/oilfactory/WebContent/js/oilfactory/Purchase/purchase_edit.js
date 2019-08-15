// 合同id
var id;
// 0:新增  1:修改  2:复制
var flag;
//供应商集合
var Supplier;
//采购申请集合
var PurchaserequisitionList;
//采购申请HTML集合
var PurchaseOrderSerialListHtml;
//税率
var TaxRateInfo;
//是否初始化状态
var isCharNew = true;

//回显明细
var getPurchaseorderinfoByIdList;
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		Creater = userInfo.id;
	}
	
	// 合同状态初始化
	var ContractStateInfo = getDataDictionary('htzt');
	$("#ContractState").empty();
	$("#ContractState").html(ContractStateInfo);
	
	//供应商集合
	Supplier = getSupplier();
	
	//供应商初始化
	SupplierShow(Supplier);
	
	//税率取得
	TaxRateInfo = getDataDictionaryDefaultNum('sl');
	
	//预计运费金额默认隐藏
	$("#ExpectMoneyIsShow").hide();
	
	// 获取前页面传过来的id
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	if(flag == 0){
		var contractNumber =  getContractInfoPrefix("CGHT");
		$("#ContractNumber").val(contractNumber);
		//新增时出现4条明细
		for (var i = 0 ; i < 4 ; i++) {
			//销售订单明细新加一条
			addTable(false);
		}
	}else{
		findPurchaseById(id)
	}
	
})

//添加明细
function addTable(isSerialIDNext,getPurchaseorderinfoByIdList,count){
	//采购申请集合
	PurchaserequisitionList = getPurchaserequisition();
	var len = $('#tableList tr').length;
	var row;
	var rowCount;
	var result="";
	var CGMXSerialID;
	if(len==1){
		rowCount = 1;
		row = 1;
	}else{
		for(var i = 1;i<len;i++){
			rowCount = i+1;
			row = i+1;
		}
		if(!isSerialIDNext){
			row+= Math.floor( Math.random() * ((len+20)-len + 1) + len );
		}
	}
	if(isSerialIDNext){
		if(flag == 1){
			CGMXSerialID = getPurchaseorderinfoByIdList[count].serialID
		}else{
			CGMXSerialID = getContractInfoPrefix("CGMX");
			updateContractInfoPrefix("CGMX");
		}
	}else{
		CGMXSerialID = getContractInfoPrefix("CGMX");
		updateContractInfoPrefix("CGMX");
	}
	
	if(getPurchaseorderinfoByIdList!=undefined){
		OrderId=0
	}else{
		OrderId=1
	}
	
	result +="<tr id='Tr"+row+"'>";
	result +="<td id='Td"+row+"'>"+rowCount+"</td>";
	//result +="<td ><a class='del' style='margin: 0 15px;' onclick='removeTr(this,'"+CGMXSerialID+"','"+OrderId+"')'><img src='../../img/common/del.png' width='20' height='20' alt='删除' title='删除'></a></td>";
	result +="<td ><a class='del' style='margin: 0 15px;' onclick=\"removeTr(this,'"+CGMXSerialID+"',"+OrderId+","+row+")\"><img src='../../img/common/del.png' width='20' height='20' alt='删除' title='删除'></a></td>";
	result +="<td style='white-space:nowrap;'><textarea style='resize:none;' draggable='false' rows='2' cols='20' name='SerialID' style=\"background:#CCCCCC\" readonly=\"readonly\" id='SerialID"+row+"' type='text' />" +
					"<input style='display:none' name='MaterielInfoId' id='MaterielInfoId"+row+"' type='text' />" +
					"<input style='display:none' name='LowPurchaseOrderSerialID' id='LowPurchaseOrderSerialID"+row+"' type='text' />"+
					"<input style='display:none' name='PurchaseRequisitionId' id='PurchaseRequisitionId"+row+"' type='text' /></td>";
	result +="<td style='white-space:nowrap;'><input name='PurchaseOrderSerialID' id='PurchaseOrderSerialID"+row+"' onchange='changePurchaseOrder("+row+",this.value,"+isSerialIDNext+")' list='PurchaseOrderSerialList"+row+"' type='text' />" +
			"<dataList id='PurchaseOrderSerialList"+row+"' class='ui_input_txt02' style='padding: 0;'></dataList></td>";
	result +="<td style='white-space:nowrap;'><input style=\"background:#CCCCCC\" readonly=\"readonly\"  name='MaterielName' id='MaterielName"+row+"' type='text' /></td>";
	result +="<td style='white-space:nowrap;'><input style=\"background:#CCCCCC\" readonly=\"readonly\"  name='MaterielModel' id='MaterielModel"+row+"' type='text' /></td>";
	result +="<td style='white-space:nowrap;'><input style=\"background:#CCCCCC\" readonly=\"readonly\"  name='Unit' id='Unit"+row+"' type='text' /></td>";
	result +="<td style='white-space:nowrap;'><input style=\"background:#CCCCCC\" readonly=\"readonly\"  name='MaterielType' id='MaterielType"+row+"' type='text' onkeyup='checkNumber(this)' /></td>";
	result +="<td style='white-space:nowrap;'><input style=\"background:#CCCCCC\" readonly=\"readonly\" onchange='countSum("+row+")'  name='Number' id='Number"+row+"' type='text' onkeyup='checkNumber(this)' /></td>";
	result +="<td style='white-space:nowrap;'><input style=\"background:#CCCCCC\" readonly=\"readonly\"  name='ArrivalDate' id='ArrivalDate"+row+"' type='text' maxlength='15'/></td>";
	result +="<td style='white-space:nowrap;'><input name='UnitPrice' id='UnitPrice"+row+"' onchange='countSum("+row+")' onkeyup='checkPrice(this)' type='text'  maxlength='9'/></td>";
	result +="<td style='white-space:nowrap;'><select name='TaxRate' id='TaxRate"+row+"' onchange='countSum("+row+")'></select>%</td>";
	result +="<td style='white-space:nowrap;'><input name='Money' readonly=\"readonly\" id='Money"+row+"' type='text' maxlength='9'/></td>";
	result +="<td ><textarea  style='resize:none;'  draggable='false' rows='2' cols='20'name='Remarks' id='Remarks"+row+"' type='text' maxlength='180'/></td>";
	result +="</tr>";
	$("#tableList").append(result);
	$("#"+("SerialID"+row)).val(CGMXSerialID);
	//采购申请单据初始化 
	if(flag ==1 && isSerialIDNext){
		PurchaserequisitionListShow(PurchaserequisitionList,true);
	}else{
		PurchaserequisitionListShow(PurchaserequisitionList,false);
	}
	$("#"+("PurchaseOrderSerialList"+row)).append(PurchaseOrderSerialListHtml);
	$("#"+("TaxRate"+row)).append(TaxRateInfo);
}

//供应商初始化
function SupplierShow(Suppliers){
	if(Suppliers != "" && Suppliers != null){
		var html = "<option value='' selected='selected'>请选择</option>";
		for (var i = 0; i < Suppliers.length; i++) {
			html += "<option value='" + Suppliers[i].Id + "'>"
			+ Suppliers[i].SupplierName + "</option>"
		}
		$("#SupplierId").html(html);
	}
}

//采购申请列表初始化
function PurchaserequisitionListShow(list,isAll){
	if(isAll){
		PurchaseOrderSerialListHtml = "";
		if(list != "" && list != null){
			for (var q = 0; q < list.length; q++) {
				PurchaseOrderSerialListHtml += "<option value='" + list[q].serialID + "'>"
				+ list[q].serialID + "</option>"
			}
		}
	}else{
		if(list != "" && list != null){	
			PurchaseOrderSerialListHtml = "";
			for (var q = 0; q < list.length; q++) {
				if(!list[q].applySign){
					PurchaseOrderSerialListHtml += "<option value='" + list[q].serialID + "'>"
					+ list[q].serialID + "</option>"
				}
			}
		}
	}
	
}

//计算金额
function countSum(data){
	// 单价
	UnitPrice = document.getElementById("UnitPrice"+data).value;
	// 数量 
	Number = document.getElementById("Number"+data).value;
	// 税率
	TaxRate = $("#TaxRate"+data+" option:selected").text();
	if(UnitPrice != ""){
		// 金额
		document.getElementById("Money"+data).value = (UnitPrice*(1+TaxRate/100)*Number).toFixed(2);
	}else{
		document.getElementById("Money"+data).value = ""
	}
}


//采购申请编号变化时
function changePurchaseOrder(count,tis,isNew){
	PurchaseOrderSerialID = tis;
	var serialIDArray = new Array();
	if(getPurchaseorderinfoByIdList !="" && getPurchaseorderinfoByIdList != null){
		for (var i = 0; i < getPurchaseorderinfoByIdList.length; i++) {
			serialIDArray.push(getPurchaseorderinfoByIdList[i].ptSerialID)
		}
	}
	var tableList = $('#tableList tr:not(:first)')
	for (var i = 0; i < tableList.length; i++) {
		var NextId = tableList[i].id;
		var row = parseInt(NextId.replace(/[^0-9]/ig,""))
		if(row != count &&  $("#PurchaseOrderSerialID"+count).val() == $("#PurchaseOrderSerialID"+row).val()){
			swal("提示", "该申请单据已添加", "error");
			$("#PurchaseOrderSerialID"+count).val("");
			return;
		}
	}
	if(!isCharNew || flag == 0){
		if(flag != 1){
			for (var i = 0; i < PurchaserequisitionList.length; i++) {
				if(PurchaserequisitionList[i].serialID == $("#PurchaseOrderSerialID"+count).val() && PurchaserequisitionList[i].applySign ){
					swal("提示", "该申请单据已被调用", "error");
					$("#PurchaseOrderSerialID"+count).val($("#LowPurchaseOrderSerialID"+count).val());
					return;
				}
			}
		}else{
			for (var i = 0; i < PurchaserequisitionList.length; i++) {
				if(PurchaserequisitionList[i].serialID == $("#PurchaseOrderSerialID"+count).val() && PurchaserequisitionList[i].applySign && ($.inArray($("#PurchaseOrderSerialID"+count).val(), serialIDArray)==-1)){
					swal("提示", "该申请单据已被调用", "error");
					$("#PurchaseOrderSerialID"+count).val($("#LowPurchaseOrderSerialID"+count).val());
					return;
				}
			}
		}
	}
	var isNoHave = true;
	for (var i = 0; i < PurchaserequisitionList.length; i++) {
		if(PurchaserequisitionList[i].serialID == PurchaseOrderSerialID){
			$("#MaterielInfoId"+count).val(PurchaserequisitionList[i].goodsName);
			$("#PurchaseRequisitionId"+count).val(PurchaserequisitionList[i].id);
			var Materie = getMaterielInfo(PurchaserequisitionList[i].goodsName);
			$("#MaterielName"+count).val(Materie[0].materielName);
			$("#MaterielModel"+count).val(Materie[0].materielModel);
			$("#Unit"+count).val(Materie[0].unit);
			$("#MaterielType"+count).val(Materie[0].content);
			$("#Number"+count).val(PurchaserequisitionList[i].applyNumber);
			$("#ArrivalDate"+count).val(PurchaserequisitionList[i].applyDate);
			isNoHave = false;
			break;
		}
	}
	if(isNoHave){
		swal("提示", "该申请单据不存在或已在其他合同添加", "error");
		$("#PurchaseOrderSerialID"+count).val($("#LowPurchaseOrderSerialID"+count).val());
	}else{
		countSum(count)
	}
}

//供应商改变时 对联系人 电话赋值
function onchangeSupplier(SupplierId){
	var contacts = "";
	var telephone = "";
	$("#Contacts").val("");
	$("#Telephone").val("");
	if(Supplier != "" && Supplier != null){
		for (var i = 0; i < Supplier.length; i++) {
			if(Supplier[i].Id == SupplierId){
				contacts = Supplier[i].Contacts;
				telephone = Supplier[i].Telephone;
				break;
			}
		}
	}
	$("#Contacts").val(contacts);
	$("#Telephone").val(telephone);
}

function onchangeFreightBalance(FreightBalance){
	if(FreightBalance.value == 0){
		$("#ExpectMoneyIsShow").show();
	}else{
		$("#ExpectMoneyIsShow").hide();
	}
}

//采购申请集合获取
function getPurchaserequisition(){
	
	var list;
	$.ajax({
		type : "POST",
		url : "../../Purchase/getPurchaserequisition.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			if(data!=null && data !=""){
				list = data.data;
			}
		}
	});
	return list;
}

//根据采购申请物料Id获取物料信息
function getMaterielInfo(id){
	
	var MaterielInfoOne;
	$.ajax({
		type : "POST",
		url : "../../Purchase/getMaterielInfo.action",
		async:false,
		data : {id:id},
		dataType : "json",
		success : function(data) {
			if(data!=null && data !=""){
				MaterielInfoOne = data.data;
			}
		}
	});
	return MaterielInfoOne;
}

function save(){
	var purchaseorderinfo; //采购明细对象
	var purchaseorderinfoList = []; //采购明细集合
	var purchasecontract; //采购合同对象
	var ContractNumber = $("#ContractNumber").val();
	var ContractState = $("#ContractState").val();
	var date = $("#date").val();
	var SupplierId = $("#SupplierId").val();
	var Contacts = $("#Contacts").val();
	var Telephone = $("#Telephone").val();
	var FreightBalance = $("#FreightBalance").val();
	var remarks = $("#remarks").val();
	var myBlogImage = $("#myBlogImage").val();
	var ExpectMoney = $("#ExpectMoney").val();
	var purchaseorderinfoId = "";
	if(ContractState == ""){
		swal("操作失败", "请选择合同状态", "error");
		return;
	}
	if(date == ""){
		swal("操作失败", "请输入时间", "error");
		return;
	}
	if(SupplierId == ""){
		swal("操作失败", "请选择供应商", "error");
		return;
	}
	if(FreightBalance == -1){
		swal("操作失败", "请选择运费结算情况", "error");
		return;
	}
	if(FreightBalance != 0){
		ExpectMoney = 0;
	}else{
		if(ExpectMoney == ""){
			swal("操作失败", "请输入预计运费金额", "error");
			return;
		}
	}
	purchaseorderinfo = {
			"Id":id,
			"contractNumber":ContractNumber,
			"contractState":ContractState,
			"date":date,
			"supplierId":SupplierId,
			"contacts":Contacts,
			"telephone":Telephone,
			"freightBalance":FreightBalance,
			"remarks":remarks,
			"myBlogImage":myBlogImage,
			"expectMoney":ExpectMoney
	}
	
	var purchaseorderinfoParams = JSON.stringify(purchaseorderinfo);
	
	
	var tableList = $('#tableList tr:not(:first)')
	for (var i = 0; i < tableList.length; i++) {
		var NextId = tableList[i].id;
		var row = parseInt(NextId.replace(/[^0-9]/ig,""))
		var SerialID = $("#SerialID"+row).val(); //明细流水号
		var PurchaseOrderSerialID = $("#PurchaseOrderSerialID"+row).val(); //采购申请流水号 不添加 只添加ID ↓
		var PurchaseRequisitionId = $("#PurchaseRequisitionId"+row).val(); //采购申请ID
		var MaterielInfoId = $("#MaterielInfoId"+row).val();
		var Number = $("#Number"+row).val();
		var ArrivalDate = $("#ArrivalDate"+row).val();
		var UnitPrice = $("#UnitPrice"+row).val();
		var Money = $("#Money"+row).val();
		var TaxRate = $("#TaxRate"+row).val();
		var Remarks = $("#Remarks"+row).val();
		//如果采购申请流水号 单价 金额 税率 都没添加 则 这条跳过 不添加
		if(PurchaseRequisitionId =="" && UnitPrice == "" && Money == "" && TaxRate == ""){
			continue;
		}
		if(PurchaseRequisitionId == "" || PurchaseOrderSerialID ==""){
			swal("操作失败", "请选择第"+(i+1)+"行采购申请单号", "error");
			return;
		}
		if(UnitPrice == ""){
			swal("操作失败", "请输入第"+(i+1)+"行单价", "error");
			return;
		}
		if(Money == ""){
			swal("操作失败", "请输入第"+(i+1)+"行金额", "error");
			return;
		}
/*		if(TaxRate == ""){
			swal("操作失败", "请输入第"+(i+1)+"行税率", "error");
			return;
		}*/
		purchaseorderinfo = {
				"serialID":SerialID,
				"purchaseRequisitionId":PurchaseRequisitionId,
				"materielInfoId":MaterielInfoId,
				"number":Number,
				"arrivalDate":ArrivalDate,
				"unitPrice":UnitPrice,
				"money":Money,
				"taxRate":TaxRate,
				"remarks":Remarks
		}
		
		purchaseorderinfoList.push(purchaseorderinfo);
	}
	
	var purchaseorderinfoListParams = JSON.stringify(purchaseorderinfoList);
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!神功护体!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!妖魔退散!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		$.ajaxFileUpload({  
	        url:'../../Purchase/upload.action',
	        secureuri:false,                           //是否启用安全提交,默认为false   
	        fileElementId:'myBlogImage',   //文件选择框的id属性  
	        data:{
	        	"purchaseorderinfoParams":purchaseorderinfoParams,
	        	"purchaseorderinfoListParams":purchaseorderinfoListParams,
	        	"contractNumber":ContractNumber,
	        	"flag":flag
	        	},		
	        dataType:'json',                           //服务器返回的格式,选择json或者xml貌似有问题
	        success:function(data){            //服务器响应成功时的处理函数  
				if(data != null){
					if(data.code == "success"){
						swal({
			 				title: data.message,
			 				text: "",
			 				type: data.code,
			 				confirmButtonText: '确定',
			 				cancelButtonFont: '微软雅黑',
			 			},
			 			function (){
			 				updateContractInfoPrefix("CGHT");
			 				location.href='purchase.html';
			 			});
					}else{
						swal("操作失败", "失败", "error");
						return;
					}
				}
	        },  
	        error:function(data, status, e){ //服务器响应失败时的处理函数  
	        	swal("操作失败", "未知错误", "error");
				return;
	        }  
	        });
}

//移除一条明细
function removeTr(obj,CGMXID,isNewAdd,Rcount){
	if(flag != 1 || isNewAdd == 1){
		var tr= $(obj).parent().parent();
		var len = $('#tableList tr').length;
		if(len<3){
			swal("操作失败", "最少保留一条详细", "info");
			return;
		}else{
			tr.remove();
		}
	    for (var i = 0; i < len; i++) {
			$('#tableList tr:eq('+i+') td:first').text(i);		
		}
	}else{
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
			var len = $('#tableList tr').length;
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
				var PurchaseRequisitionId = $("#PurchaseRequisitionId"+Rcount).val();
		    	$.ajax({
		    		type: "post",
		    		url: '../../Purchase/delPurchaseorderinfo.action',
		    		async:false,
		    		data:{"serialID":CGMXID,
		    			"id":PurchaseRequisitionId
		    			},
		    		dataType: "json",
		    		success: function (data) {
		    			if(data.code == "success"){
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
				$('#tableList tr:eq('+i+') td:first').text(i);		
			}
	});
	}
}

//根据Id回显信息
function findPurchaseById(id){
	var purchasecontractById;
	$.ajax({
		type : "POST",
		url : "../../Purchase/getPurchasecontractById.action",
		async:false,
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			purchasecontractById = data.data.purchasecontract;
			getPurchaseorderinfoByIdList =  data.data.getPurchaseorderinfoByIdList;
		}
	})
	if(flag == 2){
		$("#ContractNumber").val( getContractInfoPrefix("CGHT"));
	}else{
		$("#ContractNumber").val(purchasecontractById.serialID);
	}
	$("#ContractState").val(purchasecontractById.contractState);
	$("#date").val(purchasecontractById.date);
	$("#SupplierId").val(purchasecontractById.supplierId);
	onchangeSupplier(purchasecontractById.supplierId);
	$("#FreightBalance").val(purchasecontractById.freightBalance);
	$("#remarks").val(purchasecontractById.remarks);
	if(flag != 2){
	if(purchasecontractById.fileName != ""){
		$("#upShow").css("display","none");
		$("#upSelectShow").css("display","block");
		$("#fileShow").html(purchasecontractById.fileName.substring(0, purchasecontractById.fileName.length - 37));
		$("#files").val(purchasecontractById.fileName);
	}
	}
	if(purchasecontractById.freightBalance == 0){
		$("#ExpectMoneyIsShow").show();
		$("#ExpectMoney").val(purchasecontractById.expectMoney);
	}
	
	for (var i = 0; i < getPurchaseorderinfoByIdList.length; i++) {
		if(flag == 1){
			addTable(true,getPurchaseorderinfoByIdList,i);
		}else{
			addTable(false,getPurchaseorderinfoByIdList,i);
		}
/*		//采购申请单据初始化 
		PurchaserequisitionListShow(PurchaserequisitionList);*/
	}
	if(flag != 2){
		var tableList = $('#tableList tr:not(:first)')
		for(var i = 0;i<tableList.length;i++){
			var NextId = tableList[i].id;
			var row = parseInt(NextId.replace(/[^0-9]/ig,""))
			$("#LowPurchaseOrderSerialID"+row).val(getPurchaseorderinfoByIdList[i].ptSerialID);
			$("#PurchaseOrderSerialID"+row).val(getPurchaseorderinfoByIdList[i].ptSerialID);
			changePurchaseOrder(row,getPurchaseorderinfoByIdList[i].ptSerialID,true)
			$("#UnitPrice"+row).val(getPurchaseorderinfoByIdList[i].unitPrice);
			$("#Money"+row).val(getPurchaseorderinfoByIdList[i].money);
			$("#TaxRate"+row).val(getPurchaseorderinfoByIdList[i].taxRate);
			$("#Remarks"+row).val(getPurchaseorderinfoByIdList[i].remarks);
		}
	}
	isCharNew = false;
}

function downloadFile(e){
	var okdown = false;
	var fName =  $("#files").val();
	  $.ajax({
			type: "post",
			url: '../../TranSportList/isHaveUpload.action',
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
				url: '../../TranSportList/isHaveUpload.action',
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
			url: '../../TranSportList/delUploadfile.action',
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
			$("#files").val("");
			$("#upShow").css('display','block'); 
			$("#upSelectShow").css('display','none'); 
			$.ajax({
				type: "post",
				url: '../../TranSportList/delUploadfile.action',
				data:{"serialID":serialID},
				async:false,
				dataType: "json",
				success: function (data) {
					}
				})
			setTimeout(function(){swal({
				title: "操作失败,文件已被删除",
				type: "error",
				cancelButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			}); },200);
		}
	});
}


