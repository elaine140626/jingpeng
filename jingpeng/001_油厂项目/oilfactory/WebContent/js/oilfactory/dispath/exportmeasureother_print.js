//出库单id
var id = "";
$(function(){
	//获取页面传过来的id
	id = getUrlParam("id");
	if(id != null && id != ""){
		//获取出库单信息
		$.ajax({
			type : "post",
			url : "../../exportmeasureother/getExportmeasureotherList.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				data = data.data[0];
				// 画面赋值
				if(data != null){
					//流水号
					$("#serialId").html(data.serial_ID);
					//车牌名称
					$("#plateNumber").html(data.plateNumber);
					//称重时间
					$("#weightTime").html(data.measureTime);
					//称重单位
					$("#weighingUnit").html("t");	
					//客户名称
					$("#customerName").html(data.client);
					//供料单位
					$("#supplierName").html(data.unit);
					//材料名称
					$("#materielName").html(data.materielName);
					//规格型号
					$("#materielModel").html(data.materielModel);
					//皮重（t）
					$("#tareWeight").html(data.tareWeight);
					//毛重（t）
					$("#grossWeight").html(data.grossWeight);
					//净重（t）
					$("#suttle").html(data.suttle);
					//承运人
					$("#carrierName").html(data.deliveryMan);
					//承运人电话
					$("#carrierTelephone").html(data.deliveryManPhone);
					//车队名称
					$("#fleetName").html(data.fleetName);
					if(data.isSelfMention == 1){
						//是否自提
						$("#isSelfLifting").html("否");
					}else if(data.isSelfMention == 0){
						//是否自提
						$("#isSelfLifting").html("是");
					}
					
					//收货人
					$("#consignee").html(data.consignee);
					//收货人电话
					$("#consigneePhone").html(data.consigneePhone);
					//检测报告编号
					$("#testReportNumber").html(data.testReport);
					//封签号
					$("#facingSlipNum").html(data.facingSlipNum);
					$("#facingSlipNum2").html(data.facingSlipNum2);
					$("#facingSlipNum3").html(data.facingSlipNum3);
					$("#facingSlipNum4").html(data.facingSlipNum4);
					//温度（℃）
					$("#temperature").html(data.temperature);
					//备注
					$("#remarks").html(data.remarks);
					//检斤员
					$("#userName").html(data.qualityInspector);
					//司机
					if(data.driverAutograph){
						$("#driver").css("display","block");
						$("#driver").attr("src","../../file/preview.action?fileName="+data.driverAutograph);
					}else{
						$("#driver").css("display","none");
					}
					//二维码信息
					var qrcodeInfo = "调度出库，"+data.measureTime+"，"+data.client+"，"+data.plateNumber+"，"+data.materielName+"，"+data.materielModel+"，"+data.suttle;
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
	window.location.href = 'exportmeasureother.html';
}

//生成二维码
function makeCode (qrcodeInfo) {		
	var qrcode = new QRCode("qrcode", { 
		  text: qrcodeInfo, 
		  correctLevel : QRCode.CorrectLevel.H
		}); 
}