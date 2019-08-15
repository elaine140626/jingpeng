
//试验汇总展示图表
function mainShow(dataList) {
	console.log(dataList);
		//试验汇总展示图表
		var myChart = echarts.init(document.getElementById('main'), 'chalk');
		// 指定图表的配置项和数据
		option = {
			legend: {},
			tooltip: {},
			dataset: {
				dimensions: ['product', '实验总数', '自动采集试验数量', '盲样试验数量'],
				source: dataList
			},
			xAxis: {
				type: 'category',
			},
			yAxis: {},
			series: [{
					type: 'bar',
				},
				{
					type: 'bar'
				},
				{
					type: 'bar'
				}
			]
		};
		myChart.setOption(option);
}

//自动采集饼形图
function pieShow(data, id) {
	//饼图1
	var myChart = echarts.init(document.getElementById("pie"+id), 'chalk');
	// 指定图表的配置项和数据
	option = {
		title: {
			text: data.TestRoomName,
			x: 'center',
			y: '15px'
		},
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		legend: {
			orient: 'vertical',
			x: '120px',
			y: '60px',
			data: ['自动采集合格数量']
		},
		series: [{
			name: data.TestRoomName,
			type: 'pie',
			radius: '70%',
			center: ['50%', '60%'],
			data: [{
					value: data.hgCount,
					name: '自动采集合格数量'
				},
				{
					value: data.zdCount,
					name: '自动采集总数'
				}
			],

		}],
		color: ['#1471d8', '#cc930e']
	};
	myChart.setOption(option);
}

// 盲样检测饼形图
function ZS1pieShow(data) {
	//饼图4
	var myChart = echarts.init(document.getElementById('pie4'), 'chalk');
	// 指定图表的配置项和数据
	option = {
		title: {
			text: 'ZS1中心试验室盲样数据统计',
			x: 'center',
			y: '15px'
		},
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		legend: {
			orient: 'vertical',
			x: 'right',
			y: '60px',
			data: ['盲样合格数']
		},
		series: [{
			name: 'ZS1中心试验室盲样数据统计',
			type: 'pie',
			radius: '70%',
			center: ['50%', '60%'],
			data: [{
					value: data.qualifiedBlindCount,
					name: '盲样合格数'
				},
				{
					value: data.blindCount,
					name: '盲样总数'
				},
			],

		}],
		color: ['#e5be07', '#1ba80e']
	};
	myChart.setOption(option);
}