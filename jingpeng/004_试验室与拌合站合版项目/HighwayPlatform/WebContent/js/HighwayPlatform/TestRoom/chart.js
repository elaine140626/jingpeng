
//试验汇总展示图表
function mainShow(dataList, chartId) {
	//试验汇总展示图表
	var myChart = echarts.init(document.getElementById(chartId), 'chalk');
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


//混凝土强度分布图
function creatChart1(title, xAxis, yAxis1, yAxis2, chartId) {
	var echart = echarts.init(document.getElementById(chartId));
	option = {
		title : {
			text: title,
			subtext: '混凝土强度分布图',
			textStyle: {
		        color: '#FFFFFF'
		    },
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
	            axisLine:{
                    lineStyle:{
                        color:'#FFFFFF',
                    }
                },
	            axisPointer: {
	                type: 'shadow'
	            }
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            axisLine:{
                    lineStyle:{
                        color:'#FFFFFF',
                    }
                },
	            name: ''
	        },
	        {
	            type: 'value',
	            axisLine:{
                    lineStyle:{
                        color:'#FFFFFF',
                    }
                },
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
	            itemStyle : {  
                    normal : {  
                        lineStyle:{  
                            color:'#FFFF00'  
                        }  
                    }  
                }, 
	            data:yAxis2
	        }
	    ]
	};

	echart.setOption(option, true);
}

//指标沥青波动图
function creatChart2(title, xAxis, yAxis1, yAxis2, chartId) {
	var echart = echarts.init(document.getElementById(chartId));
	option = {
		title : {
			text: title,
			subtext: '指标沥青波动图 ',
			x: 'center',
			textStyle: {
		        color: '#FFFFFF'
		    },
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
		color:['#FFFF00','#FF0000'],
		legend: {
			data:[{name:'针入度', textStyle:{color:"#FFFFFF"}}, {name:'软化点', textStyle:{color:"#FFFFFF"}}],
			x: 'left'
		},
		xAxis : [
			{
				type : 'category',
				boundaryGap : false,
				axisLine:{
                    lineStyle:{
                        color:'#FFFFFF',
                    }
                },
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
				axisLine:{
                    lineStyle:{
                        color:'#FFFFFF',
                    }
                },
				type: 'value'
			},
			{
				name: '软化点(°C)',
				axisLine:{
                    lineStyle:{
                        color:'#FFFFFF',
                    }
                },
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
					width: 1,
					color:'#FFFF00'  
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
					width: 1,
					color:'#FF0000'  
				},
				data:yAxis2
			}
		]
	};
	echart.setOption(option, true);
}

//沥青混合料指标波动图
function creatChart3(title, xAxis, yAxis1, yAxis2, chartId) {
	var echart = echarts.init(document.getElementById(chartId));
	option = {
		title : {
			text: title,
			subtext: '沥青混合料指标波动图 ',
			x: 'center',
			textStyle: {
		        color: '#FFFFFF'
		    },
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
		color:['#FFFF00','#FF0000'],
		legend: {
			data:[{name:'稳定度', textStyle:{color:"#FFFFFF"}}, {name:'留值', textStyle:{color:"#FFFFFF"}}],
			x: 'left'
		},
		xAxis : [
			{
				type : 'category',
				boundaryGap : false,
				axisLine:{
                    lineStyle:{
                        color:'#FFFFFF',
                    }
                },
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
				axisLine:{
                    lineStyle:{
                        color:'#FFFFFF',
                    }
                },
				type: 'value'
			},
			{
				name: '留值(mm)',
				axisLine:{
                    lineStyle:{
                        color:'#FFFFFF',
                    }
                },
				type: 'value'
			}
		],
		series: [
			{
				name:'稳定度',
				type:'line',
				animation: false,
				lineStyle: {
					width: 1,
					color:'#FFFF00' 
				},
				data:yAxis1
			},
			{
				name:'留值',
				type:'line',
				yAxisIndex:1,
				animation: false,
				lineStyle: {
					width: 1,
					color:'#FF0000' 
				},
				data:yAxis2
			}
		]
	};
	echart.setOption(option, true);
}

//拌和站汇总展示分布图
function creatChart9(xAxis,yseries1,yseries2,chartId){
	var myChart = echarts.init(document.getElementById(chartId),"chalk");
	option = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data: ['生产盘数', '预警盘数']
		    },
		    xAxis:  {
		        type: 'category',
		        data: xAxis
		    },
		    yAxis: {
		        type: 'value',
		        name: '盘数'
		    },
		    series: [
		        {
		            name: '生产盘数',
		            type: 'bar',
		            stack: '总量',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'insideRight'
		                }
		            },
		            data: yseries1
		        },
		        {
		            name: '预警盘数',
		            type: 'bar',
		            stack: '总量',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'insideRight'
		                }
		            },
		            data: yseries2
		        }
		    ]
		};
		// 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
}

//油石比波动分布图
function creatChart10(xAxis,yseries,chartId){
	var myChart = echarts.init(document.getElementById(chartId),"chalk");
	option = {
		    xAxis: {
		        type: 'category',
		        data: xAxis
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [{
		        data: yseries,
		        type: 'line'
		    }]
		};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

//生产预警统计分布图
function creatChart11(xAxis, yseries1, yseries2,chartId){
	var myChart = echarts.init(document.getElementById(chartId),"chalk");
	option = {
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
		        data:['盘数','预警率(%)']
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
		            name: '盘数',
		            axisLabel: {
		                formatter: '{value}'
		            }
		        },
		        {
		            type: 'value',
		            name: '预警率(%)',
		            axisLabel: {
		                formatter: '{value}'
		            }
		        }
		    ],
		    series: [
		        {
		            name:'盘数',
		            type:'bar',
		            data:yseries1
		        },
		        {
		            name:'预警率(%)',
		            type:'line',
		            yAxisIndex: 1,
		            data:yseries2
		        }
		    ]
		};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function creatChart12(warnPieData, dosagePieData, chartId){
	var myChart = echarts.init(document.getElementById(chartId));
	
	var canvas = document.createElement('canvas');
	var ctx = canvas.getContext('2d');
	canvas.width = canvas.height = 100;
	ctx.textAlign = 'center';
	ctx.textBaseline = 'middle';
	ctx.globalAlpha = 0.08;
	ctx.font = '20px Microsoft Yahei';
	ctx.translate(50, 50);
	ctx.rotate(-Math.PI / 4);

	option = {
	    backgroundColor: {
	        type: 'pattern',
	        image: canvas,
	        repeat: 'repeat'
	    },
	    tooltip: {},
	    title: [{
	        text: '沥青拌合站预警分析',
	        subtext: '预警盘数： ' + Object.keys(warnPieData).reduce(function (all, key) {
	        	if (key != '级配、用量都预警') {
	        		all += warnPieData[key];
	        	}
	        	return all;
	        }, 0),
	        textStyle: {
		        color: '#FFFFFF'
		    },
	        x: '25%',
	        y: '10%',
	        textAlign: 'center'
	    }, {
	        text: '用量偏差分布',
	        x: '75%',
	        y: '10%',
	        textStyle: {
		        color: '#FFFFFF'
		    },
	        textAlign: 'center'
	    }],
	    grid: [{
	        top: 50,
	        width: '50%',
	        bottom: '45%',
	        left: 10,
	        containLabel: true
	    }, {
	        top: '55%',
	        width: '50%',
	        bottom: 0,
	        left: 10,
	        containLabel: true
	    }],
	    series: [{
	        type: 'pie',
	        radius: [0, '45%'],
	        center: ['25%', '60%'],
	        data: Object.keys(warnPieData).map(function (key) {
	            return {
	                name: key.replace('.js', ''),
	                value: warnPieData[key]
	            }
	        })
	    }, {
	        type: 'pie',
	        radius: [0, '45%'],
	        center: ['75%', '60%'],
	        data: Object.keys(dosagePieData).map(function (key) {
	            return {
	                name: key.replace('.js', ''),
	                value: dosagePieData[key]
	            }
	        })
	    }]
	}
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}