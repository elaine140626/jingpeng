var baseUrl = "";
var billNumber = "";
var MaterielinfoList; //所有物料信息
var infoList;
var type;
var user;
var billNumber;
var flag;
$(function(){
	user = getUserInfo();
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	baseUrl = getContextPath();
	
	$("#cancelbutton").click(function() {
		/**  关闭弹出iframe  **/
		window.parent.$.fancybox.close();
	});
	
	if(id != null && id != ""){
		findTranSportListByid(id);
	}
})
//修改
function update(){
	var param = formToJson($("#submitForm"));
	if(param.weigh == ''){
		param.weigh = null
	}
	if(param.riseLoss == ''){
		param.riseLoss = null
	}
	if(param.escortMoney == ''){
		param.escortMoney = null
	}
	if(param.type == ''){
		param.type = null
	}
	if(param.settleDate == ''){
		param.settleDate = null
	}
	if(param.receiptWeight == ''){
		param.receiptWeight = null
	}
	if(param.freightPrice == ''){
		param.freightPrice = null;
	}
	if(param.escortDays == ''){
		param.escortDays = null;
	}
	if(param.freightMoney == ''){
		param.freightMoney = null
	}
	if(param.escortPrice == ''){
		param.escortPrice = null
	}
	if(param.escortMoney == ''){
		param.escortMoney = null
	}
	if(param.sumMoney == ''){
		param.sumMoney = null
	}
	tranSportListInfo = {
			"id":id,
			"billNumber":param.billNumber,
			"userName":user.name,
			"materielId":param.materielName,
			"model":param.materielModel,
			"carOwner":param.carOwner,
			"carOwnerTelephone":param.carOwnerTelephone,
			"endAddress":param.endAddress,
			"settlementKilometre":param.distance,
			"weigh":param.weigh,
			"riseLoss":param.riseLoss,
			"freightPrice":param.freightPrice,
			"freightMoney":param.freightMoney,
			"escortDays":param.escortDays,
			"escortPrice":param.escortPrice,
			"escortMoney":param.escortMoney,
			"plateNumber":param.plateNumber,
			"remarks":param.remarks,
			"type":param.type,
			"settleDate":param.settleDate
	}
	
	var tranSportListInfoParams = JSON.stringify(tranSportListInfo);
	$.ajaxFileUpload({  
        url:baseUrl+'/TranSportList/updateTranSportList.action',
        secureuri:false,                           //是否启用安全提交,默认为false   
        fileElementId:'myBlogImage',   //文件选择框的id属性  
        data:{
        	"param":tranSportListInfoParams,
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
		 				updateContractInfoPrefix("YS");
		 				window.parent.$.fancybox.close();
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

//回显
function findTranSportListByid(id){
	if(flag == 3){
		$("#submitbutton").css('display','none'); 
		$("#divTitle").html("查看运输单")
	}else{
		$("#divTitle").html("修改运输单")
	}
	$.ajax({
		type: "post",
		url: baseUrl+'/TranSportList/getTranSportList.action',
		async:false,
		data:{"id":id},
		dataType: "json",
		success: function (data) {
			infoList = data.data[0];
			$("#plateNumber").val(infoList.plateNumber)
			$("#carOwner").val(infoList.carOwner)
			$("#carOwnerTelephone").val(infoList.carOwnerTelephone)
			$("#type").val(infoList.type)
			switch (infoList.type) {
			case 0:
				$("#typeShow").val("正常出库单")
				break;
			case 1:
				$("#typeShow").val("调拨出库单")
				break;
			case 3:
				$("#typeShow").val("空发出库单")
				break;
			case 4:
				$("#typeShow").val("兑换出库单")
				break;
			case 99:
				$("#typeShow").val("已被调拨出库单")
				break;
			default :
				$("#typeShow").val("")
			}
			$("#billNumber").val(infoList.billNumber)
			$("#serialID").val(infoList.serialID)
			if(infoList.factoryTime != undefined){
				$("#factoryTime").val(infoList.factoryTime)
			}
			$("#materielName").val(infoList.materielName)
			$("#materielModel").val(infoList.materielModel)
			$("#materielNameId").val(infoList.materielId)
			$("#materielModelId").val(infoList.model)
			if(infoList.settleDate != undefined){
				$("#settleDate").val(infoList.settleDate)
			}
			if(infoList.type == 1){
				$("#weigh").val(infoList.weigh)
				if(infoList.weigh != undefined){
					$("#company").val(infoList.clients)
				}
			}else{
				$("#company").val(infoList.client)
				if(infoList.suttle != undefined){
					$("#weigh").val(infoList.suttle)
				}
			}
			if(infoList.receiptWeight != undefined){
				$("#receiptWeight").val(infoList.receiptWeight)
			}
			if(infoList.transportBalance == 1){
				$("#receiptWeight").val(infoList.suttle)
				$("#receiptWeight").css("background","#CCCCCC")
				$("#receiptWeight").attr("readonly","#readonly")
			}
			if($("#receiptWeight").val() != '' && $("#weigh").val() != ''){
				$("#riseLoss").val(($("#receiptWeight").val()*1 - $("#weigh").val()*1).toFixed(4))
			}
			$("#endAddress").val(infoList.endAddress)
			$("#distance").val(infoList.distanceNumber)
			if(infoList.freightPrice != undefined){
				$("#freightPrice").val(infoList.freightPrice)
			}
			if(infoList.freightMoney != undefined){
				$("#freightMoney").val(infoList.freightMoney)
			}
			if(infoList.escortDays != undefined){
				$("#escortDays").val(infoList.escortDays)
			}
			if(infoList.escortPrice != undefined){
				$("#escortPrice").val(infoList.escortPrice)
			}
			if(infoList.escortMoney != undefined){
				$("#escortMoney").val(infoList.escortMoney)
			}
			if(infoList.escortMoney != undefined && infoList.freightMoney != undefined){
				$("#sumMoney").val((infoList.escortMoney*1) + (infoList.freightMoney*1))
			}
			if(infoList.fileName != undefined ){
				$("#upShow").css('display','none'); 
				$("#upSelectShow").css('display','block'); 
				$("#fileShow").val(infoList.fileName)
			}
			if(infoList.remarks != undefined){
				$("#remarks").val(infoList.remarks)
			}
			if(infoList.receiptPicture != undefined){
				$("#receiptPicture").attr("src",baseUrl + "/oilfactoryApp/preview.json?receiptPicture=" + infoList.receiptPicture);
			}
		}
	})
}

/*function changeEndAddress(e){
	var thisValue = e.value;
	var thisId = e.id;
	var row = parseInt(thisId.replace(/[^0-9]/ig,""))
	  $.ajax({
			type: "post",
			url: baseUrl+'/TranSportList/getTransportsById.action',
			data:{"kid":thisValue},
			async:false,
			dataType: "json",
			success: function (data) {
				$("#"+("distance"+row)).empty("")
					$.each(data.data, function (k, v) {
						$('<option></option>').attr("value", v.Id).html(v.Distance).appendTo($("#distance"));
					})
			}
})
}*/
//数值计算
function changereceiptWeight(e){
	var thisId = e.id;
	var row = parseInt(thisId.replace(/[^0-9]/ig,""))
	if($("#receiptWeight").val()!=""){
		var thisNumber = e.value;
		if( $("#SettlementWeight").val() != undefined && $("#SettlementWeight").val() != ""){
			var sum = parseFloat($("#SettlementWeight").val()) + parseFloat($("#receiptWeight").val()) - parseFloat($("#weigh").val())
		}else{
			var sum = parseFloat($("#receiptWeight").val()) - parseFloat($("#weigh").val())
		}
		$("#riseLoss").val(Number(sum).toFixed(2))
	}else{
		$("#riseLoss").val(0)
	}
}
//数值计算
function changefreightMoney(e){
	var thisId = e.id;
	var row = parseInt(thisId.replace(/[^0-9]/ig,""))
	if($("#distance").val()!="" && $("#freightPrice").val()!=""){
		var thisNumber = e.value;
		var sum = $("#distance").val() * $("#freightPrice").val();
		$("#freightMoney").val(sum)
	}else{
		$("#freightMoney").val("")
	}
	
	if($("#freightMoney").val()!="" && $("#escortMoney").val()!=""){
			var sum = $("#freightMoney").val()*1 + $("#escortMoney").val()*1;
			$("#sumMoney").val(sum)
	}else{
		$("#sumMoney").val("")
	}
}

//数值计算
function changeEscort(e){
	if($("#escortPrice").val()!="" && $("#escortDays").val()!=""){
		var thisNumber = e.value;
		var sum = $("#escortPrice").val() * $("#escortDays").val();
		$("#escortMoney").val(sum)
	}else{
		$("#escortMoney").val("")
	}
	
	if($("#freightMoney").val()!="" && $("#escortMoney").val()!=""){
			var sum = $("#freightMoney").val()*1 + $("#escortMoney").val()*1;
			$("#sumMoney").val(sum)
	}else{
		$("#sumMoney").val("")
	}
}

//下载
function downloadFile(e){
	var okdown = false;
	var fileId = e.id 
	var row = parseInt(fileId.replace(/[^0-9]/ig,""))
	  $.ajax({
			type: "post",
			url: baseUrl+'/TranSportList/isHaveUpload.action',
			data:{"fileName":$("#fileShow").val()},
			async:false,
			dataType: "json",
			success: function (data) {
				if(data.code == "success"){
					okdown = true;
				}
			}
	  })
	if(okdown){
		window.location.href = "../../fileUp/fileDownload.action?filename=" +  $("#fileShow").val();
	}else{
		swal("操作失败", "文件已被删除", "error");
		return;
	}
}
//删除文件
function deleteFile (e) {
	var okdown = false;
	var fileId = e.id
	var row = parseInt(fileId.replace(/[^0-9]/ig,""))
	var billNumber = $("#billNumber").val()
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
				url: baseUrl+'/TranSportList/delUploadfile.action',
				data:{"fileName":$("#fileShow").val(),"billNumber":billNumber},
				async:false,
				dataType: "json",
				success: function (data) {
					if(data.code != "success"){
						setTimeout(function(){swal({
							title:data.message,
							type: "error",
							cancelButtonText: '确定',
							cancelButtonFont: '微软雅黑',
						}); },200);
					}else{
						$("#upShow").css('display','block');
						$("#upSelectShow").css('display','none'); 
					}
				}
		  })
	});
}
function trimStr(str){
	
	var valTr = str.value.replace(/(^\s*)|(\s*$)/g,"");
	str.value = valTr	
}