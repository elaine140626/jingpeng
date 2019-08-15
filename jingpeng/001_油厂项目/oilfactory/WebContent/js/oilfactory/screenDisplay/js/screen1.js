// 生产计划完成量
var count = 0;
// 生产计划未完成量
var count1 = 0;
function myrefresh() 
{ 
       window.location.reload(); 
} 
setTimeout('myrefresh()',6000000); 
$(document).ready(function() {
	
	$.ajax({
		type: "post",
		url: "../../sdProductionPlan/getProductionPlanCount.action",
		data: {},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null && data.length > 0){
				count = data[0].count;
				count1 = data[0].count1;
			}			
		}
	})
	
	var myChart = echarts.init(document.getElementById('pie'));
	option = {
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b}: {c} ({d}%)"
		},
		series: [{
			name: '生产统计',
			type: 'pie',
			radius: ['60%', '70%'],
			 labelLine: {
                normal: {
                    length: 15,
                    length2: 40,
                    lineStyle: {
                        color: '#fff'
                    }
                }
 
           },
			data: [{
					value: count,
					name: '已完成',
					itemStyle:{color:'#52ecba'}
				},
				{
					value: count1,
					name: '未完成',
					itemStyle:{color:'#00ffff'}
				}
			],
			itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
		}]
	};

	myChart.setOption(option);
	// 获取最新生产计划list
	getList();

})

// 获取最新生产计划list
function getList(){
	$.ajax({
		type: "post",
		url: "../../sdProductionPlan/getProductionPlanList.action",
		data: {},
		dataType: "json",
		async:false,
		success: function (data) {
			$("#dataList").html("");
			if(data != null && data.length > 0){
				var html = "";
				for(var i = 0; i < data.length; i++){
					html +="<tr>"
						+"<td>"+(i+1)+"</td>"
						+"<td>"+data[i].batch+"</td>"
						+"<td>"+data[i].materielName+"</td>"
						+"<td>"+data[i].materielModel+"</td>"
						+"<td>"+data[i].planWeight+"</td>"
						+"<td>"+data[i].unit+"</td>"
						+"<td>"+data[i].customerCode+"</td>"
						+"</tr>";
				}
				$("#dataList").append(html);
			}			
		}
	})
}