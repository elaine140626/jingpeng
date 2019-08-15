var displayType;
var _style;
//条形图显示数据
var series;

$(function () {
	// 加载页面
	getDisplayType();
})

// 加载页面
function getDisplayType(){
	$.ajax({
		type : "POST",
		url :  "../../testInfo/queryAllChart.action",
		async : false,
		data : {
			systemCode: "display_type"
		},
		dataType : "json",
		success : function(data) {
			displayType = data[0].Value;
			if (displayType == 6) {
				_style = "width: 33%;"
			} else {
				_style = "width: 50%;"
			}
		}
	});
	
	$.ajax({
		type : "POST",
		url :  "../../testInfo/queryAllChart.action",
		async : false,
		data : {
			systemCode: "chart_type"
		},
		dataType : "json",
		success : function(data) {
			if (data.length > 0) {
				for (var i = 0; i < displayType; i++) {
					switchChart(data[i].Name, i + 1);
				}
			}
		}
	});
}

// 根据选中需要加载的图表id加载图表
function switchChart(chartId, index){
	var _title = "";
	switch (chartId) {
		case "chart1":
			_title = "试验汇总展示";
			initChart1(chartId, index, _title);
			break;
		case "chart2":
			_title = "自动采集查询";
			initChart2(chartId, index, _title);
			break;
		case "chart3":
			_title = "盲样查询";
			initChart3(chartId, index, _title);
			break;
		case "chart4":
			_title = "混凝土强度分布";
			initChart4(chartId, index, _title);
			break;
		case "chart5":
			_title = "沥青指标波动";
			initChart5(chartId, index, _title);
			break;
		case "chart6":
			_title = "沥青混合料指标波动";
			initChart6(chartId, index, _title);
			break;
		case "chart7":
			_title = "试验设备工作状态";
			initChart7(chartId, index, _title);
			break;
		case "chart8":
			_title = "拌合机实时状态";
			initChart8(chartId, index, _title);
			break;
		case "chart9":
			_title = "拌合站汇总展示";
			initChart9(chartId, index, _title);
			break;
		case "chart10":
			_title = "油石比波动";
			initChart10(chartId, index, _title);
			break;
		case "chart11":
			_title = "生产预警统计";
			initChart11(chartId, index, _title);
			break;
		case "chart12":
			_title = "预警分析";
			initChart12(chartId, index, _title);
			break;
	}
}

// 初始化试验汇总展示
function initChart1(chartId, index, _title){
	series = [];
	// 按照设定的格式向页面填充对应图表
	var _html = "";
	_html += '<div id="blind' + index + '" style="' + _style +'" class="half"><h3 class="title">' + _title + '</h3>' +
		'<div class="charts" id=' + chartId +'></div><div id="count"></div></div>';
	$("#contain").html($("#contain").html() + _html);
	
	// 获取试验室显示的数量
	$.ajax({
        type: "post",
        url:  "../../VideoSurveillance/getTestInfoCount.action",
        data:{},
        dataType: "json",
        success: function (data) {
            var html = "";
        	if(data != null && data.length > 0){
        		for(var i = 0; i < data.length; i++){
        			
        			html += "<div id='count"+(i+1)+"'>"
					        + "<ul>"
					        + "<li style='display:none;'>"+data[i].UniqueIdentifier+"</li>"
					        + "<li>"+data[i].TestRoomName+"</li>"
					        + "<li>试验总数：<span>"+data[i].zCount+"</span></li>"
					        + "<li>自动采集试验数：<span>"+data[i].dCount+"</span></li>"
					        + "<li>盲样试验数：<span>"+data[i].mCount+"</span></li>"
				            + "</ul>"
				            + "</div>";
        			
        			// 动态赋值
        		    var item={
        		    		product: data[i].TestRoomName,
        					'试验总数': data[i].zCount,
        					'盲样试验数量': data[i].mCount,
        					'自动采集试验数量': data[i].dCount
        		        };
        		    series.push(item);
        		    
        		}
        	}
        	$("#count").html(html);
        	
        	// 试验汇总展示图表
        	mainShow(series, chartId);
        }
	});
}

// 初始化自动采集查询
function initChart2(chartId, index, _title){
	// 按照设定的格式向页面填充对应图表
	var _html = "";
	_html += '<div id="blind' + index + '" style="' + _style +'" class="half"><h3 class="title">' + _title + '</h3>' +
		'<div class="swiper-container" id="swiper' + index + '"><div  class="swiper-wrapper" id=' + chartId +'></div></div></div>';
	$("#contain").html($("#contain").html() + _html);
	
	$.ajax({
        type: "post",
        url: "../../VideoSurveillance/getAutoCollectionCount.action",
        data:{},
        dataType: "json",
        success: function (data) {
        	if(data != null && data.length > 0){
        		for(var i = 0; i < data.length; i++){
        			var html = "";
        			html += "<div class='swiper-slide'>"
//						 + "<a href='#myModal' class='btn1' data-toggle='modal' onclick='getAutoCollectionList(\""+data[i].UniqueIdentifier+"\")';>点击查看详细数据</a>"
						 + "<div class='pie' id='pie"+(i+1)+"'></div>"
						 + "<div class='pieCon col-10'>"
						 + "<div class='icon-pie'>"
						 + "<div class='icon_half'></div>"
						 + "<div class='icon_text'>自动采集合格率：<span>"+percentNum(data[i].hgCount,data[i].zdCount)+"</span>"
						 + "</div>"
						 + "</div>"
						 + "<div class='noIconz-pie'>"
						 + "<span class='i1'>自动采集总数量：</span><span class='i2'>"+data[i].zdCount+"</span>"
						 + "</div>"
						 + "<div class='noIconz-pie'>"
						 + "<span class='i1'>合格试验数量：</span><span class='i3'>"+data[i].hgCount+"</span>"
						 + "</div>"
						 + "</div>"
					     + "</div>";
        			$("#" + chartId).append(html);
        			
        			// 显示自动采集试验饼形图
        			pieShow(data[i], (i+1));
        		}
        	}
        	var swiper1 = new Swiper('#swiper' + index, {
        		speed: 2500,
        		autoplay: 3500,
        		effect: 'flip',
        		flipEffect: {
        			slideShadows: true,
        			limitRotation: true,
        		}
        	});
        }
	})		
}

// 初始化盲样查询
function initChart3(chartId, index, _title){
	// 按照设定的格式向页面填充对应图表
	var _html = "";
	_html += '<div id="blind' + index + '" style="' + _style +'" class="half"><h3 class="title">' + _title + '</h3>' +
		'<div class="swiper-container" style="height:391.94px" id="swiper' + index + '"><div class="swiper-wrapper" id=' + chartId +'></div></div></div>';
	$("#contain").html($("#contain").html() + _html);

	$.ajax({
        type: "post",
        url:"../../VideoSurveillance/getBlindCount.action",
        data:{},
        dataType: "json",
        success: function (data) {
        	if(data != null && data.length > 0){
        		for(var i = 0; i < data.length; i++){
        			var html = "";
        			html += "<div class='swiper-slide'>"
//        				   + "<a href='#myModal1' class='btn2' data-toggle='modal' onclick='getBlindList()'>点击查看详细数据</a>"
				           + "<div class='pieCon0'>"
					        + "<div class='icon-pie0' style='margin-top: 20%;'>"
					        + "<div class='icon_half'></div>"
					        + "<div class='icon_text0'>盲样合格率：<span>"+percentNum(data[0].qualifiedBlindCount,data[0].completedBlindCount)+"</span>"
					        + "</div>"
					        + "</div>"
					        + "<div class='icon-pie0' style='margin-top: 5%;'>"
					        + "<div class='noIconz-pie'>"
					        + "<span class='i1'>盲样总数：</span><span class='i2'>"+data[0].blindCount+"</span>"
					        + "</div>"
					        + "<div class='noIconz-pie'>"
					        + "<span class='i1'>盲样合格数：</span><span class='i3'>"+data[0].qualifiedBlindCount+"</span>"
					        + "</div></div><div class='icon-pie0' style='margin-top: 0;'>"
					        + "<div class='noIconz-pie'>"
					        + "<span class='i1'>盲样未完成数：</span><span class='i3'>"+(data[0].blindCount - data[0].completedBlindCount)+"</span>"
					        + "</div>"
					        + "<div class='noIconz-pie'>"
					        + "<span class='i1'>盲样已完成数：</span><span class='i3'>"+data[0].completedBlindCount+"</span>"
					        + "</div>"
					        + "</div>"
					        + "</div>"
					        + "<div class='pie0' id='pie4'></div>"
				            + "</div>";
        			$("#" + chartId).html(html);
        			$('#' + chartId + ' .icon_text0').css('line-height','100px');
        			ZS1pieShow(data[i])
        		}
        	}   	
        	var swiper2 = new Swiper('#swiper' + index, { 
        		direction: 'vertical', 
        		speed: 2500,
        		autoplay: 3500, 
        		effect: 'coverflow'
        	});
        }
	});
}

// 初始化混凝土强度分布
function initChart4(chartId, index, _title){
	var _html = "";
	_html += '<div id="blind' + index + '" style="' + _style +'" class="half"><h3 class="title">' + _title + '</h3>' +
		'<div style="width:100%; height:400px" id=' + chartId +'></div></div>';
	$("#contain").html($("#contain").html() + _html);
	
	$.ajax({
		type: "post",
		url: "../../testInfo/getConcreteStrengthChartData.action",
		data: {},
		dataType: "json",
		success: function (info) {
			if (info.data.length > 0) {
				var data = info.data;
				var i = 1;
				// 生成图片
				creatChart1(data[0].testRoomName, data[0].xAxis, data[0].yAxis1, data[0].yAxis2, chartId);
				chartTimer1 = window.setInterval(function() {
					if (i == data.length) {
						i = 0;
					}
					// 生成图片
					creatChart1(data[i].testRoomName, data[i].xAxis, data[i].yAxis1, data[i].yAxis2, chartId);
					i++;
				}, 5000);
			} else {
				chartTimer1 = window.setInterval(function() {
					// 生成图片
					creatChart1("", [], [], [], chartId);
					i++;
				}, 5000);
			}
		}
	});
	
}

// 初始化沥青指标波动
function initChart5(chartId, index, _title){
	var _html = "";
	_html += '<div id="blind' + index + '" style="' + _style +'" class="half"><h3 class="title">' + _title + '</h3>' +
		'<div style="width:100%; height:400px" id=' + chartId +'></div></div>';
	$("#contain").html($("#contain").html() + _html);
	
	// 查询数据
	$.ajax({
		type: "post",
		url: "../../testInfo/getConcreteChartData.action",
		data: {},
		dataType: "json",
		success: function (info) {
			if (info.data.length > 0) {
				var data = info.data;
				var i = 1;
				// 生成图片
				creatChart2(data[0].testRoomName, data[0].xAxis, data[0].yAxis1, data[0].yAxis2, chartId);
				chartTimer2 = window.setInterval(function() {
					if (i == data.length) {
						i = 0;
					}
					// 生成图片
					creatChart2(data[i].testRoomName, data[i].xAxis, data[i].yAxis1, data[i].yAxis2, chartId);
					i++;
				}, 5000);
			} else {
				chartTimer2 = window.setInterval(function() {
					// 生成图片
					creatChart2("", [], [], [], chartId);
					i++;
				}, 5000);
			}
		}
	});
}

// 初始化沥青指标波动
function initChart6(chartId, index, _title){
	var _html = "";
	_html += '<div id="blind' + index + '" style="' + _style +'" class="half"><h3 class="title">' + _title + '</h3>' +
		'<div style="width:100%; height:400px" id=' + chartId +'></div></div>';
	$("#contain").html($("#contain").html() + _html);
	
	$.ajax({
		type: "post",
		url: "../../testInfo/getMixtureChartData.action",
		data: {},
		dataType: "json",
		success: function (info) {
			if (info.data.length > 0) {
				var data = info.data;
				var i = 1;
				// 生成图片
				creatChart3(data[0].testRoomName, data[0].xAxis, data[0].yAxis1, data[0].yAxis2, chartId);
				chartTimer3 = window.setInterval(function() {
					if (i == data.length) {
						i = 0;
					}
					// 生成图片
					creatChart3(data[i].testRoomName, data[i].xAxis, data[i].yAxis1, data[i].yAxis2, chartId);
					i++;
				}, 5000);
			} else {
				chartTimer3 = window.setInterval(function() {
					// 生成图片
					creatChart3("", [], [], [], chartId);
					i++;
				}, 5000);
			}
		}
	});
}

//初始化沥青指标波动
function initChart7(chartId, index, _title){
	
}

// 初始化沥青指标波动
function initChart8(chartId, index, _title){
	// 按照设定的格式向页面填充对应图表
	var _html = "";
	_html += '<div id="blind' + index + '" style="' + _style +'" class="half"><h3 class="title">' + _title + '</h3>' +
		'<div class="swiper-container" style="height:391.94px" id="swiper' + index + '"><div class="swiper-wrapper" id=' + chartId +'></div></div></div>';
	$("#contain").html($("#contain").html() + _html);
	$.ajax({
        type: "post",
        url: "../../PlatformAsphaltData/getEquipmentsData.action",
        data:{},
        dataType: "json",
        success: function (data) {
        	var info = data.data;
        	if(info != null && info.length > 0){
        		for(var i = 0; i < info.length; i++){
        			// 左侧机构名+设备名
    				var projName= info[i].orgName + "," + info[i].equipmentName;
    				// 设备图片
    				var projPicSrc = "";
    				// 搅拌机名称
    				var mixName = "";
    				if (info[i].equipmentType == 0) {
    					projPicSrc = "../../image/chart/liqing.png";
    					mixName = "沥青混合料拌和机";
    				} else if (info[i].equipmentType == 1) {
    					projPicSrc = "../../image/chart/shuini.png";
    					mixName = "水泥混凝土拌和机";
    				}
    				// 运行状态图片
    				var statePicSrc = "";
    				// 运行状态
    				var states = "";
    				// 左下角内容
    				var materialType = "";
    				// 右上角内容
    				var earlyCollectTime = "";
    				// 右下角内容
    				var div4 = "";
    				// 生产时间
    				var lastCollectTime = "";
    				if (!info[i].isOnline) {					// 停产
    					// 运行状态图片
    					statePicSrc = "<img style='height: 61%;width: 80%;' id='statePic' src='../../image/chart/state.png'>"
    					// 运行状态
    					states = "停产";
    					// 右上角内容
        				earlyCollectTime = "最后生产时间：" + info[i].collectTime ;
    					// 左下角内容
    					div3 = "<span class='wrap' id='materialType'>网络连通情况：已断开</span>";
    					// 右下角内容
    					div4 = "<span style='width: 100%;' class='wrap'>最后连接时间：" + info[i].lastlinkDate + "</span>"
    					// 生产时间
        				lastCollectTime = info[i].collectTime + "生产情况";
    				} else {																// 生产中
    					// 运行状态图片
    					statePicSrc = "<img style='height: 12%;width: 80%;' id='statePic' src='../../image/chart/state.gif'>"
    					// 运行状态
    					states = "生产中";
    					// 右上角内容
        				earlyCollectTime = "最早生产时间：" + info[i].productionInfo.collectTime;
        				if (info[i].equipmentType == 0) {
        					// 左下角内容
        					div3 = "<span class='wrap' id='materialType'>混凝土种类：" + info[i].productionInfo.materialModel + "</span>";
        					// 右下角内容
        					div4 = "<span style='width: 47%;' class='wrap'>已生产数量：</span>"
									 + "<div class='cell2'>"
									 + "<span class='wrap' id='total'>" + info[i].productionInfo.count + "（盘）</span>"
									 + "</div>"
									 + "<div class='line'></div>"
									 + "<div class='cell2'>"
									 + "<span class='wrap' id='volume'>" + info[i].productionInfo.total + "（m³）</span>"
									 + "</div>"
        				} else if(info[i].equipmentType == 1) {
        					// 左下角内容
        					div3 = "<span class='wrap' id='materialType'>混凝土种类：" + info[i].productionInfo.materialModel + "</span>" +
        							"<span class='wrap' id='materialType'>级配类型：" + info[i].productionInfo.materialName + "</span>";
        					// 右下角内容
        					div4 = "<span style='width: 47%;' class='wrap'>已生产数量：</span>"
								 + "<div class='cell2'>"
								 + "<span class='wrap' id='total'>" + info[i].productionInfo.count + "（盘）</span>"
								 + "</div>"
								 + "<div class='line'></div>"
								 + "<div class='cell2'>"
								 + "<span class='wrap' id='volume'>" + info[i].productionInfo.total + "（t）</span>"
								 + "</div>"
        				}
        				// 生产时间
        				lastCollectTime = "生产时间：" + info[i].productionInfo.collectTime;
    				}
    				
    				// 下方生产数据列表
    				var tableData = "";
    				for (var j = 0; j < info[i].totalInfo.length; j++) {
    					tableData += "<tr>"
    						+ "<td>" + info[i].totalInfo[j].materialName + "</td>"
    						+ "<td>" + info[i].totalInfo[j].materialModel + "</td>"
    						+ "<td>" + info[i].totalInfo[j].total + "（盘）" + info[i].totalInfo[j].totalWeight + "（t）</td>"
    						+ "<td>" + info[i].totalInfo[j].warn + "</td>"
    						+ "</tr>"
					}
    				
    				
    				// 最后拼装的html代码
        			var html = "";
        			
        			html += "<div class='swiper-slide'>"
						 + "<div class='grid1'>"
						 + "<span class='wrap' id='projName'>"+ projName +"</span>"
						 + "</div>"
						 + "<div class='grid2'>"
						 + "<div class='info'>"
						 + "<div class='picLeft'>"
						 + "<img id='projPic' style='height: 78%;width: 100%;' src='" + projPicSrc + "'>"
						 + "<div class='cell2' style='height:22%'>"
						 + "<span class='wrap' id='mixName'>" + mixName + "</span>"
						 + "</div>"
						 + "</div>"
						 + "<div class='picRight'>"
						 + "<div class='cell'>"
						 + statePicSrc
						 + "<span style='width: 46%;' id='states' class='wrap'>" + states + "</span>"
						 + "</div>"
						 + "<div class='cell'>"
						 + "<span class='wrap' id='earlyCollectTime'>" + earlyCollectTime + "</span>"
						 + "</div>"
						 + "<div class='cell'>"
						 + div3
						 + "</div>"
						 + "<div class='cell'>"
						 + div4
						 + "</div>"
						 + "</div>"
						 + "</div>"
						 + "<div class='table'>"
						 + "<span id='lastCollectTime'>" + lastCollectTime + "</span>"
						 + "<table style='width:100%' id='infoGrid' border='1'>"
						 + "<tr>"
						 + "<td>混凝土种类</td>"
						 + "<td>级配类型</td>"
						 + "<td>生产数量</td>"
						 + "<td>报警盘数</td>"
						 + "</tr>"
						 + tableData
						 + "</table>"
						 + "</div>"
						 + "</div>"
					     + "</div>";
        			$("#" + chartId).append(html);
        			
        			// 加载表格
        			
        			
        			
        		}
        	}
        	var swiper1 = new Swiper('#swiper' + index, {
        		speed: 2500,
        		autoplay: 3500,
        		effect: 'flip',
        		flipEffect: {
        			slideShadows: true,
        			limitRotation: true,
        		}
        	});
        }
	})
}

// 初始化拌和站汇总展示
function initChart9(chartId, index, _title){
	// 按照设定的格式向页面填充对应图表
	var _html = "";
	_html += '<div id="blind' + index + '" style="' + _style +'" class="half"><h3 class="title">' + _title + '</h3>' +
		'<div class="swiper-container" id="swiper' + index + '"><div  class="swiper-wrapper" id=' + chartId +'></div></div></div>';
	$("#contain").html($("#contain").html() + _html);
	
	$.ajax({
		type : "post",
		url : "../../PlatformAsphaltData/getAmalgamatorSummary.action",
		data : {},
		dataType : "json",
		success : function(data) {
			var list = data.data;
			for(var i=0;i<list.length;i++){
				var html = "";
				html += "<div class='swiper-slide'>" 
					+ "<div id='main" + i + "' style='width: 70%;height:100%;float:left;'></div>"
				 	+ "<div style=' width:30%;height:100%;float:left;'>"
					+ "<div id='count" + index + "'>" ;
				for(var j=0;j<list[i].xAxis.length;j++){
					html += "<ul>"
				        + "<li><h4>"+list[i].xAxis[j]+"</h4></li>"
				        + "<li>生产总量(t)：<span>"+list[i].totalProduction[j]+"</span></li>"
				        + "<li>生产盘数：<span>"+list[i].yseries1[j]+"</span></li>"
				        + "<li>预警盘数：<span>"+list[i].yseries2[j]+"</span></li>"
				        + "<li>预警率：<span>"+list[i].warnRate[j]+"</span></li>"
			            + "</ul>";
				}
				html += "</div>"
					+ "</div>"
					+ "</div>";
				$("#" + chartId).append(html);
				//生成图片
				creatChart9(list[i].xAxis,list[i].yseries1,list[i].yseries2,"main" + i);
			}
			var swiper3 = new Swiper('#swiper' + index, {
				speed: 2500,
				autoplay: 3500,
				effect: 'flip',
				flipEffect: {
					slideShadows: true,
					limitRotation: true,
				}
			});
		}
	});
}

// 初始化油石比波动
function initChart10(chartId, index, _title){
	var _html = "";
	_html += '<div id="blind' + index + '" style="' + _style +'" class="half"><h3 class="title">' + _title + '</h3>' +
		'<div style="width:100%; height:400px" id=' + chartId +'></div></div>';
	$("#contain").html($("#contain").html() + _html);
	
	$.ajax({
		type : "post",
		url : "../../PlatformAsphaltData/getAsphaltChartData.action",
		data : {},
		dataType : "json",
		success : function(data) {
			var list = data.data;
			for(var i=0;i<list.length;i++){
				creatChart10(list[i].xAxis,list[i].yseries,chartId);
			}
		}
	});
}

//初始化生产预警统计
function initChart11(chartId, index, _title){
	var _html = "";
	_html += '<div id="blind' + index + '" style="' + _style +'" class="half"><h3 class="title">' + _title + '</h3>' +
		'<div style="width:100%; height:400px" id=' + chartId +'></div></div>';
	$("#contain").html($("#contain").html() + _html);
	
	$.ajax({
		type : "post",
		url : "../../PlatformAsphaltData/getWarningStatistics.action",
		data : {},
		dataType : "json",
		success : function(data) {
			var list = data.data;
			for(var i=0;i<list.length;i++){
				//生成图片
				creatChart11(list[0].xAxis,list[0].yseries1, list[0].yseries2,chartId);
			}
			var a = 1;
			//生成图片
			creatChart11(list[0].xAxis,list[0].yseries1, list[0].yseries2,chartId);
			chart_timer = setInterval(function(){
				if(a >= list.length) {
					a=0;
					//生成图片
					creatChart11(list[a].xAxis,list[a].yseries1, list[a].yseries2,chartId);
				}else{
					//生成图片
					creatChart11(list[a].xAxis,list[a].yseries1, list[a].yseries2,chartId);
				}
				a++;
			},5000)
		}
	});
}

// 初始化预警分析
function initChart12(chartId, index, _title){
	var _html = "";
	_html += '<div id="blind' + index + '" style="' + _style +'" class="half"><h3 class="title">' + _title + '</h3>' +
		'<div style="width:100%; height:400px" id=' + chartId +'></div></div>';
	$("#contain").html($("#contain").html() + _html);
	
	$.ajax({
		type: "post",
		url: "../../PlatformAsphaltData/getWarnChartData.action",
		data: {},
		dataType: "json",
		success: function (info) {
			if (info.data) {
				var data = info.data;
				var i = 1;
				// 生成图片
				creatChart12(data.warnPieData, data.dosagePieData, chartId);
				chartTimer12 = window.setInterval(function() {
					if (i == data.length) {
						i = 0;
					}
					// 生成图片
					creatChart12(data.warnPieData, data.dosagePieData, chartId);
					i++;
				}, 5000);
			} else {
				chartTimer12 = window.setInterval(function() {
					// 生成图片
					creatChart12(data.warnPieData, data.dosagePieData, chartId);
					i++;
				}, 5000);
			}
		}
	});
	
}

//百分数计算
function percentNum(dividend, divisor) { 
	if(divisor != 0){
		return (Math.round(dividend / divisor * 10000) / 100.00 + "%"); // 小数点后两位百分比
	}else{
		return "0%";
	}
	
}