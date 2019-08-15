//出库单id
var id = "";
//出库单状态
var outType = "";
//是否关联其它公司
var isRelation = "";

$(function(){
	//获取父页面的全局变量id
	id = parent.id;
	//获取父页面的全局变量outType
	outType = parent.outType;
	//获取父页面的全局变量isRelation
	isRelation = parent.isRelation;
	
	if(id != null && id != ""){
		//获取出库单信息
		$.ajax({
			type : "post",
			url : "../../WeighingQueryOut/getWeighingQueryOutPrintInfo.action",
			data : {"id" : id,"outType" : outType},
			dataType : "json",
			success : function(data) {
				// 画面赋值
				if(data != null && data.length > 0){
					//流水号
					var serialNumber = getSerialNumber();
					$("#serialId").html(serialNumber);
					//车牌名称
					$("#plateNumber").html(data[0].plateNumber);
					//称重时间
					if(outType == 3){//空发
						$("#weightTime").html(data[0].createrDate);
					}else{
						$("#weightTime").html(data[0].tareMeasureTime);
					}
					//称重单位
					$("#weighingUnit").html("t");	
					//客户名称
					if(isRelation == 0){//关联公司
						$("#customerName").html(data[0].otherCompanyName);
					}else{
						$("#customerName").html(data[0].customerName);
					}
					//供料单位
					$("#supplierName").html(data[0].supplierName);
					//材料名称
					$("#materielName").html(data[0].materielName);
					//规格型号
					$("#materielModel").html(data[0].materielModel);
					if(outType == 4){//兑换
						//皮重（t）
						$("#tareWeight").html(data[0].tareWeight);
						//毛重（t）
						$("#grossWeight").html(data[0].tareWeight+data[0].suttle);
						//净重（t）
						$("#suttle").html(data[0].suttle);
					}else{
						//皮重（t）
						$("#tareWeight").html(data[0].tareWeight);
						//毛重（t）
						$("#grossWeight").html(data[0].grossWeight);
						//净重（t）
						$("#suttle").html(data[0].suttle);
					}
					//承运人
					$("#carrierName").html(data[0].carrierName);
					//承运人电话
					$("#carrierTelephone").html(data[0].carrierTelephone);
					//车队名称
					$("#fleetName").html(data[0].fleetName);
					//是否自提
					$("#isSelfLifting").html(data[0].transportBalance);
					//收货人
					$("#consignee").html(data[0].consignee);
					//收货人电话
					$("#consigneePhone").html(data[0].consigneePhone);
					//检测报告编号
					$("#testReportNumber").html(data[0].testReportNumber);
					//封签号
					$("#facingSlipNum").html(data[0].facingSlipNum);
					$("#facingSlipNum2").html(data[0].facingSlipNum2);
					$("#facingSlipNum3").html(data[0].facingSlipNum3);
					$("#facingSlipNum4").html(data[0].facingSlipNum4);
					//温度（℃）
					$("#temperature").html(data[0].temperature);
					//备注
					if(outType == "3"){//空发出库单
						$("#remarks").html("");
					}else{
						$("#remarks").html(data[0].orderDetailedRemarks+"&nbsp;&nbsp;"+data[0].remarks);
					}
					//检斤员
					$("#userName").html(data[0].tareOperator);
					//司机
					if(data[0].driverAutograph){
						$("#driver").css("display","block");
						$("#driver").attr("src","../../file/downImage.action?fileName="+data[0].driverAutograph);
					}else{
						$("#driver").css("display","none");
					}
					
					
					//二维码信息
					var qrcodeInfo = "";
					if(isRelation == 0){
						if(outType == 3){
							qrcodeInfo = data[0].outType+"\r\n"+data[0].createrDate+"\r\n"+data[0].otherCompanyName+"\r\n"+data[0].plateNumber+"\r\n"+data[0].materielName+"\r\n"+data[0].materielModel+"\r\n"+data[0].suttle;
						}else{
							qrcodeInfo = data[0].outType+"\r\n"+data[0].tareMeasureTime+"\r\n"+data[0].otherCompanyName+"\r\n"+data[0].plateNumber+"\r\n"+data[0].materielName+"\r\n"+data[0].materielModel+"\r\n"+data[0].suttle;
						}
					}else{
						if(outType == 3){
							qrcodeInfo = data[0].outType+"\r\n"+data[0].createrDate+"\r\n"+data[0].customerName+"\r\n"+data[0].plateNumber+"\r\n"+data[0].materielName+"\r\n"+data[0].materielModel+"\r\n"+data[0].suttle;
						}else{
							qrcodeInfo = data[0].outType+"\r\n"+data[0].tareMeasureTime+"\r\n"+data[0].customerName+"\r\n"+data[0].plateNumber+"\r\n"+data[0].materielName+"\r\n"+data[0].materielModel+"\r\n"+data[0].suttle;
						}
					}
					//生成二维码
					makeCode(qrcodeInfo);
				}
			}
		});
	}
});

//关闭按钮
function closes(){
	//关闭当前页面，刷新父页面的父页面
	window.parent.parent.location.reload();
}

//生成二维码
function makeCode (qrcodeInfo) {		
	var qrcode = new QRCode("qrcode", { 
		  text: qrcodeInfo, 
		  correctLevel : QRCode.CorrectLevel.H
		}); 
}