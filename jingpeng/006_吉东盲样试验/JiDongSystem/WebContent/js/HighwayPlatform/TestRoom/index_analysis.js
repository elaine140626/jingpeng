// 加载layui的form组件，以显示页面筛选条件绑定的组件
var form;
layui.use('form', function(){
	form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
	form.on('select(changeAsphaltType)', function(data){
		initAsphaltGradeCombobox(data);
	});
	form.on('select(changeGradationType)', function(data){
		initGradationTypeCombobox(data);
	});
	form.render();
});  

// 图表数据
var chartDataX = [];
var chartDataY = [];
var chartDataY2 = [];
//定时任务
var chartTimer1;
var chartTimer2;
var chartTimer3;
// 页面筛选条件
var param = {};

// 加载layDate时间插件
var laydate  = layui.laydate;
laydate.render({
	elem: '#startTime'
});
laydate.render({
	elem: '#endTime'
});
laydate.render({
	elem: '#startTime2'
});
laydate.render({
	elem: '#endTime2'
});
laydate.render({
	elem: '#startTime3'
});
laydate.render({
	elem: '#endTime3'
});
$(function(){
	// 页面初始化时为第一个按钮添加选中样式
	$("#btn1")[0].classList.add("layui-btn-normal");
	// 初始化下拉框数据
	initCombobox();
	// 混凝土强度分布图
	initChart1();
	$("#chart2").hide();
	$("#chart3").hide();
});

// 初始化第一张图
function initChart1() {
	// 清空定时任务
	if (chartTimer1) {
		window.clearInterval(chartTimer1);
	}
	
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	
	//如果为空 初始时间为2010年 结束时间为当前日期
	if(startTime==""){
		startTime = "2010-01-01"
		$("#startTime").val("2010-01-01");
	}
	if(endTime==""){
		endTime = NowFormatDate();
		$("#endTime").val(endTime);
	}
	
	if(!compareDay(startTime,endTime)){
		swal("操作失败","开始时间不能大于结束时间！", "info");
		return;
	}
	
	//试验室名称
	var authority = document.getElementsByName("authority");
	var children = document.getElementsByName("children");
	var uniqueIdentifier = "";
	
	// 判断试验室是否选中
	var flag = false;
	for (var j = 0; j < authority.length; j++) {
		// 获取选中项
		if (authority[j].checked) {
			flag = true;
			if(j < authority.length - 1){			
				uniqueIdentifier += authority[j].value + ",";
			}else{
				uniqueIdentifier += authority[j].value;
			}
		}
	}
	
	// 如果父类有勾选
	if(uniqueIdentifier != ""){
		uniqueIdentifier += ",";
	}
	
	for (var j = 0; j < children.length; j++) {
		// 获取选中项
		if (children[j].checked) {
			flag = true;
			if(j < children.length - 1){			
				uniqueIdentifier += children[j].value + ",";
			}else{
				uniqueIdentifier += children[j].value;
			}
		}
	}

	// 筛选条件
	// 实验室
	param.uniqueidentifiers = uniqueIdentifier;
	// 时间
	param.startTime = startTime;
	param.endTime = endTime;
	// 查询数据
	$.ajax({
		type: "post",
		url: "../../testInfo/getConcreteStrengthChartData.action",
		data: param,
		async: false,
		dataType: "json",
		success: function (info) {
			if (info.data.length > 0) {
				var data = info.data;
				var i = 1;
				// 生成图片
				creatChart1(data[0].testRoomName, data[0].xAxis, data[0].yAxis1, data[0].yAxis2);
				chartTimer1 = window.setInterval(function() {
					if (i == data.length) {
						i = 0;
					}
					// 生成图片
					creatChart1(data[i].testRoomName, data[i].xAxis, data[i].yAxis1, data[i].yAxis2);
					i++;
				}, 5000);
			} else {
				chartTimer1 = window.setInterval(function() {
					// 生成图片
					creatChart1("", [], [], []);
					i++;
				}, 5000);
			}
		}
	});
}

// 初始化第二张图
function initChart2() {
	// 清空定时任务
	if (chartTimer2) {
		window.clearInterval(chartTimer2);
	}
	
	var startTime = $("#startTime2").val();
	var endTime = $("#endTime2").val();
	
	//如果为空 初始时间为2010年 结束时间为当前日期
	if(startTime==""){
		startTime = "2010-01-01"
		$("#startTime2").val("2010-01-01");
	}
	if(endTime==""){
		endTime = NowFormatDate();
		$("#endTime2").val(endTime);
	}
	
	if(!compareDay(startTime,endTime)){
		swal("操作失败","开始时间不能大于结束时间！", "info");
		return;
	}
	
	//试验室名称
	var authority = document.getElementsByName("authority");
	var children = document.getElementsByName("children");
	var uniqueIdentifier = "";
	
	// 判断试验室是否选中
	var flag = false;
	for (var j = 0; j < authority.length; j++) {
		// 获取选中项
		if (authority[j].checked) {
			flag = true;
			if(j < authority.length - 1){			
				uniqueIdentifier += authority[j].value + ",";
			}else{
				uniqueIdentifier += authority[j].value;
			}
		}
	}
	
	// 如果父类有勾选
	if(uniqueIdentifier != ""){
		uniqueIdentifier += ",";
	}
	
	for (var j = 0; j < children.length; j++) {
		// 获取选中项
		if (children[j].checked) {
			flag = true;
			if(j < children.length - 1){			
				uniqueIdentifier += children[j].value + ",";
			}else{
				uniqueIdentifier += children[j].value;
			}
		}
	}
	
	// 筛选条件
	// 实验室
	param.uniqueidentifiers = uniqueIdentifier;
	// 时间
	param.startTime = startTime;
	param.endTime = endTime;
	param.asphaltType = $("#asphaltType").val();
	param.asphaltGrade = $("#asphaltGrade").val();
	// 查询数据
	$.ajax({
		type: "post",
		url: "../../testInfo/getConcreteChartData.action",
		data: param,
		async: false,
		dataType: "json",
		success: function (info) {
			if (info.data.length > 0) {
				var data = info.data;
				var i = 1;
				// 生成图片
				creatChart2(data[0].testRoomName, data[0].xAxis, data[0].yAxis1, data[0].yAxis2);
				chartTimer2 = window.setInterval(function() {
					if (i == data.length) {
						i = 0;
					}
					// 生成图片
					creatChart2(data[i].testRoomName, data[i].xAxis, data[i].yAxis1, data[i].yAxis2);
					i++;
				}, 5000);
			} else {
				chartTimer2 = window.setInterval(function() {
					// 生成图片
					creatChart2("", [], [], []);
					i++;
				}, 5000);
			}
		}
	});
}

// 初始化第三张图
function initChart3() {
	// 清空定时任务
	if (chartTimer3) {
		window.clearInterval(chartTimer3);
	}
	
	var startTime = $("#startTime3").val();
	var endTime = $("#endTime3").val();
	
	//如果为空 初始时间为2010年 结束时间为当前日期
	if(startTime==""){
		startTime = "2010-01-01"
		$("#startTime3").val("2010-01-01");
	}
	if(endTime==""){
		endTime = NowFormatDate();
		$("#endTime3").val(endTime);
	}
	
	if(!compareDay(startTime,endTime)){
		swal("操作失败","开始时间不能大于结束时间！", "info");
		return;
	}
	
	//试验室名称
	var authority = document.getElementsByName("authority");
	var children = document.getElementsByName("children");
	var uniqueIdentifier = "";
	
	// 判断试验室是否选中
	var flag = false;
	for (var j = 0; j < authority.length; j++) {
		// 获取选中项
		if (authority[j].checked) {
			flag = true;
			if(j < authority.length - 1){			
				uniqueIdentifier += authority[j].value + ",";
			}else{
				uniqueIdentifier += authority[j].value;
			}
		}
	}
	
	// 如果父类有勾选
	if(uniqueIdentifier != ""){
		uniqueIdentifier += ",";
	}
	
	for (var j = 0; j < children.length; j++) {
		// 获取选中项
		if (children[j].checked) {
			flag = true;
			if(j < children.length - 1){			
				uniqueIdentifier += children[j].value + ",";
			}else{
				uniqueIdentifier += children[j].value;
			}
		}
	}
	
	// 筛选条件
	// 实验室
	param.uniqueidentifiers = uniqueIdentifier;
	// 时间
	param.startTime = startTime;
	param.endTime = endTime;
	param.mixtureType = $("#mixtureType").val();
	param.gradationType = $("#gradationType").val();
	// 查询数据
	$.ajax({
		type: "post",
		url: "../../testInfo/getMixtureChartData.action",
		data: param,
		async: false,
		dataType: "json",
		success: function (info) {
			if (info.data.length > 0) {
				var data = info.data;
				var i = 1;
				// 生成图片
				creatChart3(data[0].testRoomName, data[0].xAxis, data[0].yAxis1, data[0].yAxis2);
				chartTimer3 = window.setInterval(function() {
					if (i == data.length) {
						i = 0;
					}
					// 生成图片
					creatChart3(data[i].testRoomName, data[i].xAxis, data[i].yAxis1, data[i].yAxis2);
					i++;
				}, 5000);
			} else {
				chartTimer3 = window.setInterval(function() {
					// 生成图片
					creatChart3("", [], [], []);
					i++;
				}, 5000);
			}
		}
	});
}

// 混凝土强度分布图
function creatChart1(title, xAxis, yAxis1, yAxis2) {
	var echart = echarts.init(document.getElementById('echart1'));
	option = {
		title : {
			text: title,
			subtext: '混凝土强度分布图',
			x: 'center',
			align: 'right'
		},
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            crossStyle: {
	                color: '#999'
	            }
	        }
	    },
	    legend: {
	        data:['',''],
			x: 'left'
	    },
	    xAxis: [
	        {
	            type: 'category',
	            data: xAxis,
	            axisPointer: {
	                type: 'shadow'
	            }
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            name: ''
	        },
	        {
	            type: 'value',
	            name: ''
	        }
	    ],
	    series: [
	        {
	            name:'',
	            type:'bar',
	            data:yAxis1
	        },
	        {
	            name:'',
	            type:'line',
	            yAxisIndex: 1,
	            data:yAxis2
	        }
	    ]
	};

	echart.setOption(option, true);
}

// 指标沥青波动图
function creatChart2(title, xAxis, yAxis1, yAxis2) {
	var echart = echarts.init(document.getElementById('echart2'));
	option = {
		title : {
			text: title,
			subtext: '指标沥青波动图 ',
			x: 'center',
			align: 'right'
		},
		grid: {
			bottom: 80
		},
		tooltip : {
			trigger: 'axis',
			axisPointer: {
				type: 'cross',
				animation: false,
				label: {
					backgroundColor: '#505765'
				}
			}
		},
		legend: {
			data:['针入度','软化点'],
			x: 'left'
		},
		xAxis : [
			{
				type : 'category',
				boundaryGap : false,
				axisPointer: {
                    label: {
                        formatter: function (params) {
                        	var result = xAxis[params.value - 1];
                        	return result.substring(result.indexOf('(') + 1, result.length - 1);
                        }
                    }
                },
				data : xAxis.map(function (str) {
	                return str.substring(0, str.indexOf("("));
	            })
			}
		],
		yAxis: [
			{
				name: '针入度(0.1mm)',
				type: 'value'
			},
			{
				name: '软化点(°C)',
				type: 'value'
			}
		],
		series: [
			{
				name:'针入度',
				type:'line',
				smooth: true,
				animation: false,
				lineStyle: {
					width: 1
				},
				data:yAxis1
			},
			{
				name:'软化点',
				type:'line',
				smooth: true,
				yAxisIndex:1,
				animation: false,
				lineStyle: {
					width: 1
				},
				data:yAxis2
			}
		]
	};
	echart.setOption(option, true);
}

// 沥青混合料指标波动图
function creatChart3(title, xAxis, yAxis1, yAxis2) {
	var echart = echarts.init(document.getElementById('echart3'));
	option = {
		title : {
			text: title,
			subtext: '沥青混合料指标波动图 ',
			x: 'center',
			align: 'right'
		},
		grid: {
			bottom: 80
		},
		tooltip : {
			trigger: 'axis',
			axisPointer: {
				type: 'cross',
				animation: false,
				label: {
					backgroundColor: '#505765'
				}
			}
		},
		legend: {
			data:['稳定度','留值'],
			x: 'left'
		},
		xAxis : [
			{
				type : 'category',
				boundaryGap : false,
				axisPointer: {
                    label: {
                        formatter: function (params) {
                        	var result = xAxis[params.value - 1];
                        	return result.substring(result.indexOf('(') + 1, result.length - 1);
                        }
                    }
                },
				data : xAxis.map(function (str) {
	                return str.substring(0, str.indexOf("("));
	            })
			}
		],
		yAxis: [
			{
				name: '稳定度(kN)',
				type: 'value'
			},
			{
				name: '留值(mm)',
				type: 'value'
			}
		],
		series: [
			{
				name:'稳定度',
				type:'line',
				animation: false,
				lineStyle: {
					width: 1
				},
				data:yAxis1
			},
			{
				name:'留值',
				type:'line',
				yAxisIndex:1,
				animation: false,
				lineStyle: {
					width: 1
				},
				data:yAxis2
			}
		]
	};
	echart.setOption(option, true);
}

// 点击按钮切换echart显示
function changeChart(obj, id) {
	// 先隐藏全部
	$("#chart1").hide();
	$("#chart2").hide();
	$("#chart3").hide();
	// 展示按钮对应的图
	$("#" + id).show();
	// 加载对应的图
	if (id == "chart1") {
		// 混凝土强度分布图
		initChart1();
	} else if (id == "chart2") {
		// 指标沥青波动图
		initChart2();
	} else if (id == "chart3") {
		// 沥青混合料指标波动图
		initChart3();
	}
	// 变更选中按钮样式
	// 先移除所有选中样式
	$("#btn1")[0].classList.remove("layui-btn-normal");
	$("#btn2")[0].classList.remove("layui-btn-normal");
	$("#btn3")[0].classList.remove("layui-btn-normal");
	// 再给选中的按钮添加选中样式
	$("#" + obj.id)[0].classList.add("layui-btn-normal");
}

//获取当前时间，格式YYYY-MM-DD
function NowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

//比较日期大小
function compareDay(startTime,endTime){
	var d1 = new Date(startTime.replace(/\-/g, "\/"));  
	var d2 = new Date(endTime.replace(/\-/g, "\/"));  
	if (startTime!=""&&endTime!=""&&d1 >d2){
		return false;
	}else{
		return true;
	}
}

// 初始化下拉框数据
function initCombobox(){
	// 沥青种类下拉框
	initAsphaltTypeCombobox();
	// 沥青标号/等级下拉框
	initAsphaltGradeCombobox();
	// 初始化混合料种类下拉框
	initMixtureTypeCombobox();
	// 初始化级配类型下拉框
	initGradationTypeCombobox();
}

// 初始化沥青种类下拉框
function initAsphaltTypeCombobox(){
	// 清空下拉框
	$("#asphaltType").empty();
	// 沥青种类下拉框
	$.ajax({
		type: "post",
		url: "../../testInfo/queryAsphaltTypeCombobox.action",
		data: {},
		async: false,
		dataType: "json",
		success: function (data) {
			$('#asphaltType').append(new Option("请选择",""));//往下拉菜单里添加元素
			if(data != null && data[0] != null){
				$.each(data,function(index,item){
					$('#asphaltType').append(new Option(item.asphaltType,item.asphaltType));//往下拉菜单里添加元素
				})
			}
		}
	});
	form.render();
}

// 初始化沥青标号/等级下拉框
function initAsphaltGradeCombobox(asphaltType){
	// 清空下拉框
	$("#asphaltGrade").empty();
	if (asphaltType) {
		// 沥青种类下拉框
		$.ajax({
			type: "post",
			url: "../../testInfo/queryAsphaltGradeCombobox.action",
			data: {
				asphaltType: asphaltType.value
			},
			async: false,
			dataType: "json",
			success: function (data) {
				$('#asphaltGrade').append(new Option("请选择",""));//往下拉菜单里添加元素
				if(data != null && data[0] != null){
					$.each(data,function(index,item){
						$('#asphaltGrade').append(new Option(item.asphaltGrade,item.asphaltGrade));//往下拉菜单里添加元素
					})
				}
			}
		});
	}
	form.render();
}

// 初始化混合料种类下拉框
function initMixtureTypeCombobox(){
	// 清空下拉框
	$("#mixtureType").empty();
	// 沥青种类下拉框
	$.ajax({
		type: "post",
		url: "../../testInfo/queryMixtureTypeCombobox.action",
		data: {},
		async: false,
		dataType: "json",
		success: function (data) {
			$('#mixtureType').append(new Option("请选择",""));//往下拉菜单里添加元素
			if(data != null && data[0] != null){
				$.each(data,function(index,item){
					$('#mixtureType').append(new Option(item.mixtureType,item.mixtureType));//往下拉菜单里添加元素
				})
			}
		}
	});
	form.render();
}

// 初始化级配类型下拉框
function initGradationTypeCombobox(mixtureType){
	// 清空下拉框
	$("#gradationType").empty();
	if (mixtureType) {
		// 沥青种类下拉框
		$.ajax({
			type: "post",
			url: "../../testInfo/queryGradationTypeCombobox.action",
			data: {
				mixtureType: mixtureType.value
			},
			async: false,
			dataType: "json",
			success: function (data) {
				$('#gradationType').append(new Option("请选择",""));//往下拉菜单里添加元素
				if(data != null && data[0] != null){
					$.each(data,function(index,item){
						$('#gradationType').append(new Option(item.gradationType,item.gradationType));//往下拉菜单里添加元素
					})
				}
			}
		});
	}
	form.render();
}