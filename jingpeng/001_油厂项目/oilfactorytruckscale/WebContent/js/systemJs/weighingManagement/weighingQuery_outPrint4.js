//出库单id
var id = "";
//出库单状态
var outType = "";
//是否关联其它公司
var isRelation = "";
//调拨的数据条数
var dataLength;

$(function(){
	//获取父页面的全局变量id
	id = parent.id;
	//获取父页面的全局变量outType
	outType = parent.outType;
	//获取父页面的全局变量isRelation
	isRelation = parent.isRelation;
	//获取父页面的全局变量dataLength
	dataLength = parent.dataLength;
	
	if(id != null && id != ""){
		//获取出库单信息
		$.ajax({
			type : "post",
			url : "../../WeighingQueryOut/getNoweighEntityPrintInfo.action",
			data : {"id" : id,"outType" : outType},
			dataType : "json",
			success : function(data) {
				// 画面赋值
				if(data != null && data.length > 0){
					//流水号
					if(outType == 1 && dataLength == 2){
						var serialNumber = getSerialNumber();
						$("#serialId").html(serialNumber);
					}else{
						$("#serialId").html("");
					}
					//车牌名称
					$("#plateNumber").html(data[1].carName);
					//称重时间
					$("#weightTime").html(data[1].tareMeasureTime);
					//称重单位
					$("#weighingUnit").html("t");	
					//客户名称
					$("#customerName").html(data[1].customerName);
					//供料单位
					if(isRelation == "0"){//是
						$("#supplierName").html(data[1].otherCompanyName);
						//小票名头
						$("#diaoBoB").hide();
					}else{//否
						$("#supplierName").html(data[1].companyName);
						//小票名头
						$("#diaoBoB").show();
						$("#reportRenown").html("辽 宁 瑞 德 出 库 单");
					}
					//材料名称
					$("#materielName").html(data[1].materielName);
					//规格型号
					$("#materielModel").html(data[1].materielModel);
					//皮重（t）
					$("#tareWeight").html(data[1].tareWeight);
					//毛重（t）
					$("#grossWeight").html(data[1].grossWeight);
					//净重（t）
					$("#suttle").html(data[1].suttle);
					//承运人
					$("#carrierName").html(data[1].deliveryMan);
					//承运人电话
					$("#carrierTelephone").html(data[1].deliveryManPhone);
					//车队名称
					$("#fleetName").html(data[1].fleetName);
					//是否自提
					$("#isSelfLifting").html(data[1].transportBalance);
					//收货人
					$("#consignee").html(data[1].consignee);
					//收货人电话
					$("#consigneePhone").html(data[1].consigneePhone);
					//检测报告编号
					$("#testReportNumber").html(data[1].testReportNumber);
					//封签号
					$("#facingSlipNum").html(data[1].facingSlipNum);
					$("#facingSlipNum2").html(data[1].facingSlipNum2);
					$("#facingSlipNum3").html(data[1].facingSlipNum3);
					$("#facingSlipNum4").html(data[1].facingSlipNum4);
					//温度（℃）
					$("#temperature").html(data[1].temperature);
					//备注
					$("#remarks").html(data[1].orderDetailedRemarks+"&nbsp;&nbsp;"+data[1].remarks);
					//检斤员
					$("#userName").html(data[1].tareOperator);
					//司机
					if(data[1].driverAutograph){
						$("#driver").css("display","block");
						$("#driver").attr("src","../../file/downImage.action?fileName="+data[1].driverAutograph);
					}else{
						$("#driver").css("display","none");
					}
					
					//二维码信息
					var qrcodeInfo = data[1].outType+"\r\n"+data[1].tareMeasureTime+"\r\n"+data[1].customerName+"\r\n"+data[1].carName+"\r\n"+data[1].materielName+"\r\n"+data[1].materielModel+"\r\n"+data[1].suttle;
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