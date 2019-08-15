function myrefresh() 
{ 
       window.location.reload(); 
} 
setTimeout('myrefresh()',6000000); 
$(document).ready(function() {
	// 总数统计
	getCountList();	
	// 出库车辆信息List
	getScreen2List();	
	// echar
	getEchar();
})

// 总数统计
function getCountList(){
	$.ajax({
		type: "post",
		url: "../../Screen2/getCountList.action",
		data: {},
		dataType: "json",
		async:false,
		success: function (data) {
			if (data != null && data.length > 0){
				$("#zccount").text(data[0].zccount);
				$("#dhcount").text(data[0].dhcount);
				$("#kfcount").text(data[0].kfcount);
				$("#zscount").text(data[0].zscount);
			} else {
				$("#zccount").text("0");
				$("#dhcount").text("0");
				$("#kfcount").text("0");
				$("#zscount").text("0");
			}		
		}
	})
}

// 出库车辆信息List
function getScreen2List(){
	$.ajax({
		type: "post",
		url: "../../Screen2/getScreen2List.action",
		data: {},
		dataType: "json",
		async:false,
		success: function (data) {
			$("#tablelist").html("");
			if(data != null && data.length > 0){
				var html = "";
				for(var i = 0; i < data.length; i++){
					html += "<tr>"
						+ "<td>" + (i+1) + "</td>"
						+ "<td>" + data[i].plateNumber + "</td>"
						+ "<td>" + data[i].materielName + "</td>"
						+ "<td>" + data[i].materielModel + "</td>"
						+ "</tr>";
				}
				$("#tablelist").append(html);
			}			
		}
	})
}

// echar
function getEchar(){
	var myChart = echarts.init(document.getElementById('chart1'));
	var serie = [];   
	$.ajax({
		type: "post",
		url: "../../Screen2/getEchar.action",
		data: {},
		dataType: "json",
		async:false,
		success: function (data) {
			if (data != null && data.length > 0){				
				serie = [data[0].month1,data[0].month2,data[0].month3,data[0].month4,data[0].month5,data[0].month6
					,data[0].month7,data[0].month8,data[0].month9,data[0].month10,data[0].month11,data[0].month12];			    
			} else {
				serie = [0,0,0,0,0,0,0,0,0,0,0,0];
			}		
		}
	});
	option = {
		color: ['#48f1b6'],
		tooltip: {
			trigger: 'axis',
			axisPointer: { // 坐标轴指示器，坐标轴触发有效
				type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		axisLabel: {

			textStyle: {
				color: '#fff'
			}
		},
		xAxis: [{
			type: 'category',
			data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
			axisTick: {
				alignWithLabel: true
			},
			axisLabel: {
				color: "#fff"
			},
			axisLine: {
				lineStyle: {
					type: 'solid',
					color: '#fff',
					width: '2'
				}
			}
		}],
		yAxis: [{
			type: 'value',
			axisLabel: {
				color: "#fff"
			},
			axisLine: {
				lineStyle: {
					type: 'solid',
					color: '#fff',
					width: '2'
				}
			}
		}],
		series: [{
			name: '调度统计',
			type: 'bar',
			barWidth: '30%',
			data: serie
		}]
	};

	myChart.setOption(option);
}