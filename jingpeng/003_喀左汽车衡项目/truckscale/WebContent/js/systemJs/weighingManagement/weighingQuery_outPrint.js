//出库单id
var id = "";

$(function(){
	
	//获取传到当前页面的出库单id
	id = getUrlParam("id");
	
	if(id != null && id != ""){
		//获取出库单信息
		$.ajax({
			type : "post",
			url : "../../WeighingQuery/getPrintInfo.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				// 画面赋值
				if(data != null && data.length > 0){
					//流水号
					$("#serialId").html(data[0].SerialId);
					//车号
					$("#plateNumber").html(data[0].PlateNumber);
					//称重时间
					$("#weightTime").html(data[0].CreaterDate);
					//供料单位
					$("#feedCompanyName").html(data[0].FeedCompanyName);
					//收料单位
					$("#receiveUnitName").html(data[0].ReceiveUnitName);
					//材料名称
					$("#materielName").html(data[0].MaterielName);
					//规格型号
					$("#materielModel").html(data[0].MaterielModel);
					//皮重（t）
					$("#tareWeight").html(data[0].TareWeight);
					//毛重（t）
					$("#grossWeight").html(data[0].GrossWeight);
					//净重（t）
					$("#netWeight").html(data[0].NetWeight);
					//司机姓名
					$("#driverName").html(data[0].DriverName);
					//车主姓名
					$("#carOwnerName").html(data[0].CarOwnerName);
					//温度（℃）
					$("#temperature").html(data[0].Temperature);
					//油石比
					$("#ratioOfOil").html(data[0].RatioOfOil);
					//路线、桩号
					$("#route").html(data[0].Route);
					//起运地
					$("#startAddress").html(data[0].StartAddress);
					//止运地
					$("#endAddress").html(data[0].EndAddress);
					//备注
					$("#remarks").html(data[0].Remarks);
					//检斤员
					$("#creater").html(data[0].Creater);
					/*//司机
					$("#serialId").html(data[0].serialId);
					//复核
					$("#serialId").html(data[0].serialId);*/
				}
			}
		});
	}
});

//关闭按钮
function closes(){
	//关闭当前页面，刷新父页面
	window.parent.location.reload();
}