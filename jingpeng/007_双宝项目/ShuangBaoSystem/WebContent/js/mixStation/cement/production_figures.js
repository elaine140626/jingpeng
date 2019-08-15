var params = formToJson($('#searchForm'));
params.orgId = cementOrgId;

$(function(){
	$("#locationText").html(cementOrgName);
	getList();
	getchart(cementOrgId);
	//时间
	jeDate({
		dateCell:"#startTime",
		format:"YYYY-MM-DD",
		isinitVal:false,
		isTime:true, //isClear:false
	})
	
	jeDate({
		dateCell:"#endTime",
		format:"YYYY-MM-DD",
		isinitVal:false,
		isTime:true, //isClear:false,
	})
});

function getList() {
	var table = $('#list').dataTable();
	table.fnDestroy();
	$("#list").DataTable({
		"paging" : true,
		"lengthChange" : false,
		"pageLength" : 14,
		"searching" : false,
		"ordering" : false,
		"info" : true,
		"sInfo" : true,
		"autoWidth" : false,
		"iDisplayStart" : 0,
		scrollY : "100%",
		scrollX : true,
		scrollCollapse : true,
		"language" : {
			"lengthMenu" : "每页 _MENU_ 条记录",
			"zeroRecords" : "没有找到记录",
			"info" : "第 _PAGE_ 页,共 _PAGES_ 页",
			"infoEmpty" : "无记录",
			"sSearch" : "在结果中查找：",
			"infoFiltered" : "(从 _MAX_ 条记录过滤)",
			"oPaginate" : {
				"sFirst" : "第一页",
				"sPrevious" : "上一页",
				"sNext" : "下一页",
				"sLast" : "最后一页"
			},
		},
		"ajax" : {
			type : "post",
			url : baseUrl + "/cementProductionFigures/getCementProductionStatisticsList.action",
			dataSrc : "data",
			data : params,
		},
		"deferRender" : true,
		"columns" : [
				{
					"data" : "collectDate"
				},
				{
					"data" : "collectDate",
				},
				{
					"data" : "strengthGrade"
				},
				{
					"data" : "totalNumber",
					
				}, {
					"data" : "qualifiedNumber",
					
				}, {
					"data" : "unqualifiedNumber",
					
				}, {
					"data" : "cementWeight",
					render : function(data, type, row) {
						console.log(data);
						return parseFloat(data/1000).toFixed(2);
					}
					
				}, {
					"data" : "totalWeight",
					render : function(data, type, row) {
						return parseFloat(data/1000).toFixed(2);
					}
					
				}, {
					"data" : "productionVolume"
				} ],
		"order" : [ [ 1, 'asc' ] ],
		"fnRowCallback" : function(nRow, aData, iDisplayIndex) {
			$("td:first", nRow).html(iDisplayIndex + 1);// 设置序号位于第一列，并顺次加一
			return nRow;
		}

	});
}
function search(){
	params = formToJson($('#searchForm'));
	params.orgId = cementOrgId;
	if(params.endTime && params.startTime && params.startTime > params.endTime) {
		swal("输入错误","结束时间不得小于开始时间", "info");
		return;
	}
	if(params.startTime) {
		params.startTime = params.startTime + " " + "00:" + "00:" + "00";
	}
	if(params.endTime) {
		params.endTime = params.endTime + " " + "23:" + "59:" + "59";
	}
	getList();
}
function getchart(i){
	$.ajax({
		type:"post",
		url:baseUrl + "/cementProductionFigures/getCementProductionData.action",
		async:true,
		data:{
			orgId:i
		},
		success:function(datas){
			console.log(datas);
			option = {
					color: ['#3398DB'],
				    xAxis: {
				        type: 'category',
				        data: ['总盘数', '合格盘数', '不合格盘数']
				    },
				    yAxis: {
				        type: 'value'
				    },
				    series: [{
				        data: [datas.total, datas.qualified, datas.unqualified],
				        type: 'bar'
				    }]
				};
			var myChart = echarts.init(document.getElementById('chartmain'));
		    myChart.setOption(option);
		}
		
	});
	
}