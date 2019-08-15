$(function(){
	//获取汽车衡类别(下拉列表用--默认请选择)
	var scaleList = getDataDictionary('scale')
	// 汽车衡类别
	$("#u3279_input").empty();
	$("#u3279_input").append(scaleList);
	//获取串口(下拉列表用--默认请选择)
	var serialPortList = getDataDictionary('serialPort')
	// 串口
	$("#u3281_input").empty();
	$("#u3281_input").append(serialPortList);
	//获取波特率(下拉列表用--默认请选择)
	var baudRateList = getDataDictionary('baudRate')
	// 波特率
	$("#u3283_input").empty();
	$("#u3283_input").append(baudRateList);
	getSystem();
})

//保存
function save(){
	var truckScaleType = $("#u3279_input").val();
	var serialPort = $("#u3281_input").val();
	var baudRate = $("#u3283_input").val();
	var weighingMethod = $("input[name='称重方式']:checked").val();
	var saveSettings = $("input[name='保存设置选项']:checked").val();
	var materialSelection = $("input[name='材料筛选选项']:checked").val();
	var creater = '刘江'
	var param = {};
	param.truckScaleType = truckScaleType;
	param.serialPort = serialPort;
	param.baudRate = baudRate;
	param.weighingMethod = weighingMethod;
	param.saveSettings = saveSettings;
	param.materialSelection = materialSelection;
	param.creater = creater;
	$.ajax({
		type : 'POST',
		url : "../../System/saveSystem.action",
		data:JSON.stringify(param),
		dataType: 'json',
		contentType: 'application/json',
		success : function(data) {
			if (data.code = "success") {
				layer.alert(data.message, {
					icon : 1,
					title : "提示"
				}, function() {
					window.location.reload();
				});
			} else {
				layer.alert(data.message, {
					icon : 2,
					title : "提示"
				}, function() {
					window.location.reload();
				});
			}
		}
	});
}
//赋值
function getSystem(){
	$.ajax({
		type : 'POST',
		url : "../../System/getSystem.action",
		dataType: 'json',
		contentType: 'application/json',
		success : function(data) {
				data = data.system;
				if(data.length>0){
				data = data[0];
				$("#u3279_input").val(data.truckScaleType);
				$("#u3281_input").val(data.serialPort);
				$("#u3283_input").val(data.baudRate);
				$("input[name='称重方式'][value="+data.weighingMethod+"]").attr("checked",true);
				$("input[name='保存设置选项'][value="+data.saveSettings+"]").attr("checked",true);
				$("input[name='材料筛选选项'][value="+data.materialSelection+"]").attr("checked",true);
		}
		}
	});
	}

