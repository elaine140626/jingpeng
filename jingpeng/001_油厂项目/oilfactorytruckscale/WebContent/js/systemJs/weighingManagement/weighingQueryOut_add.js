var id = "";
var uesrInfo = ""; //用户信息
var username = "出库单测试";
$(function(){
	id = getUrlParam("id")
	//uesrInfo = getUserInfo(uesrId);
	FindWeighingQueryById(id);
})
//根据id查询出库单信息
function FindWeighingQueryById(id){
			$.ajax({
				type: 'POST',
				url: '../../WeighingQueryOut/getWeighingQueryOut.action',
				data: {"id":id},
				dataType: 'json',
				success: function(data){
					data = data.data[0];	
					$("#plateNumber").val(data.plateNumber);			//车牌号码
					$("#customerName").val(data.client);				//客户名称
					$("#materielName").val(data.materielName);			//产品名称
					$("#materielModel").val(data.materielModel);		//规格型号
					$("#grossWeight").val(data.grossWeight);			//毛重
					$("#grossMeasureTime").val(data.grossMeasureTime);	//毛重称重时间
					$("#tareWeight").val(data.tareWeight);				//皮重
					$("#tareMeasureTime").val(data.tareMeasureTime);	//皮重称重时间
					$("#suttle").val(data.suttle);						//净重
					$("#deliveryMan").val(data.deliveryMan);			//送货人
					$("#deliveryManPhone").val(data.deliveryManPhone);	//送货电话
					$("#fleetName").val(data.fleetName);				//车队名称
					$("#consignee").val(data.consignee);				//收货人
					$("#consigneePhone").val(data.consigneePhone);		//收货电话
					$("#reportNum").val(data.reportNum);				//报告编号
					$("#temperature").val(data.temperature);			//温度
					$("#facingSlipNum").val(data.facingSlipNum);		//封签号
					$("#facingSlipNum2").val(data.facingSlipNum2);		//封签号2
					$("#facingSlipNum3").val(data.facingSlipNum3);		//封签号3
					$("#facingSlipNum4").val(data.facingSlipNum4);		//封签号4
					$("#remarks").val(data.remarks);					//备注
				}
			});
}
//编辑出库单信息
function save(){
			var param = {};
			var temperature    = $("#temperature").val();			//温度
			var facingSlipNum  = $("#facingSlipNum").val();			//封签号
			var facingSlipNum2 = $("#facingSlipNum2").val();		//封签号2
			var facingSlipNum3 = $("#facingSlipNum3").val();		//封签号3
			var facingSlipNum4 = $("#facingSlipNum4").val();		//封签号4
			var remarks = $("#remarks").val();						//备注
			param.id = id;
			param.temperature = temperature;
			param.facingSlipNum = facingSlipNum;
			param.facingSlipNum2 = facingSlipNum2;
			param.facingSlipNum3 = facingSlipNum3;
			param.facingSlipNum4 = facingSlipNum4;
			param.reviser = username;
			param.remarks = remarks;
			$.ajax({
				type : 'POST',
				url : '../../WeighingQueryOut/updateWeighingQueryOut.action',
				data:JSON.stringify(param),
				dataType: 'json',
				contentType: 'application/json',
				success : function(data) {
					if (data.code = "success") {
						layer.alert(data.message, {
							icon : 1,
							title : "提示"
						}, function() {
							window.parent.location.reload();
							var index=parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
						});
					} else {
						layer.msg(data.message, {
							icon: 2,
							time: 1000
						},function(){
							window.location.reload();
						});
					}
				}
			});
	}
//窗口关闭
function layer_close(){
	var index=parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

