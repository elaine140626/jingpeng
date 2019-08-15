//出库单id
var id = "";
//用户信息
var userId = "";
var userInfo = "";

$(function(){
	//获取页面传过来的id
	id = getUrlParam("id");
	
	if(id != null && id != ""){
		//获取出库单信息
		$.ajax({
			type : "post",
			url : "../../WeighingQueryOtherOut/getWeighingQueryOtherInfo.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				// 画面赋值
				if(data != null && data.length > 0){
					//流水号
					$("#serialId").html(data[0].serial_ID);
					//车牌名称
					$("#plateNumber").html(data[0].plateNumber);
					//称重时间
					$("#weightTime").html(data[0].measureTime);
					//称重单位
					$("#weighingUnit").html("t");	
					//客户名称
					$("#customerName").html(data[0].client);
					//供料单位
					$("#supplierName").html(data[0].unit);
					//材料名称
					$("#materielName").html(data[0].materielName);
					//规格型号
					$("#materielModel").html(data[0].materielModel);
					//皮重（t）
					$("#tareWeight").html(data[0].tareWeight);
					//毛重（t）
					$("#grossWeight").html(data[0].grossWeight);
					//净重（t）
					$("#suttle").html(data[0].suttle);
					//承运人
					$("#carrierName").html(data[0].deliveryMan);
					//承运人电话
					$("#carrierTelephone").html(data[0].deliveryManPhone);
					//车队名称
					$("#fleetName").html(data[0].fleetName);
					//是否自提
					$("#isSelfLifting").html(data[0].isSelfMention);
					//收货人
					$("#consignee").html(data[0].consignee);
					//收货人电话
					$("#consigneePhone").html(data[0].consigneePhone);
					//检测报告编号
					$("#testReportNumber").html(data[0].testReport);
					//封签号
					$("#facingSlipNum").html(data[0].facingSlipNum);
					$("#facingSlipNum2").html(data[0].facingSlipNum2);
					$("#facingSlipNum3").html(data[0].facingSlipNum3);
					$("#facingSlipNum4").html(data[0].facingSlipNum4);
					//温度（℃）
					$("#temperature").html(data[0].temperature);
					//备注
					$("#remarks").html(data[0].remarks);
					//检斤员
					$("#userName").html(data[0].qualityInspector);
					
					//二维码信息
					var qrcodeInfo = "调度出库，"+data[0].measureTime+"，"+data[0].client+"，"+data[0].plateNumber+"，"+data[0].materielName+"，"+data[0].materielModel+"，"+data[0].suttle;
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
	window.parent.location.reload();
}

//生成二维码
function makeCode (qrcodeInfo) {		
	var qrcode = new QRCode("qrcode", { 
		  text: qrcodeInfo, 
		  correctLevel : QRCode.CorrectLevel.H
		}); 
}