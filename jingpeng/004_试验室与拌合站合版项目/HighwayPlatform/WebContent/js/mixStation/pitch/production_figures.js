var baseUrl = "";
$(function(){
	baseUrl = getContextPath();
	$("#locationText").html(orgName);
	var param = {};
	param.orgId = orgId;
	if(orgId != ''){
		getList(param);
		getBar(param);
	}
});

//查询所有
function getList(param) {
	var table = $('#list').dataTable();
	table.fnDestroy();
	$('#list').DataTable({
		"paging" : true,
		"lengthChange" : false,
		"pageLength" : 14,
		"searching" : false,
		"ordering" : false,
		"info" : true,
		"sInfo" : true,
		"autoWidth" : false,
		"iDisplayStart" : 0,
		"language" : {
			"lengthMenu" : "每页 _MENU_ 条记录",
			"zeroRecords" : "没有找到记录",
			"info" : "第 _PAGE_ 页,共 _PAGES_ 页 (显示_END_ 条，共 _TOTAL_ 项)",
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
            type: "post",
            url: baseUrl + "/asphaltProductionStatistics/getAsphaltProductionStatistics.action",
            dataSrc: "data",
            data: function (d) {
                return param;
            }
        },		
        "deferRender" : true,
		"columns" : [ 
			{"data" : "serialNumber"},
			{"data" : "collectTime"},
			{"data" : "productInfo"},
			{"data" : "total"},
			{"data" : "standard"},
			{"data" : "unqualified"},
			{
				"data" : "sumasphaltMeter",
				render:function(data,type,row){
					return parseFloat(data/1000).toFixed(2);
				}
			},
			{
				"data" : "totalWeight",
				render:function(data,type,row){
					return parseFloat(data/1000).toFixed(2);
				}
			}
			],
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		}],
		"order": [[ 1, 'asc' ]],
		"fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一        
            // 获取所有数据
            return nRow;
        }
	});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

//模糊查询
function search(){
	var param = {};
	//组织机构权限
	param.orgId = orgId;
	//开始时间
    param.startTime = $("#startTime").val();
    //结束时间
    param.endTime = $("#endTime").val();
    //产品名称
    param.materialName = $("#materialName").val();
    //产品型号
    param.materialModel = $("#materialModel").val();
    //施工单位
    param.projPos = $("#projPos").val();
    //工程部位/用途
    param.constructionUnit = $("#constructionUnit").val();
    if(orgId != ''){
		getList(param);
		getBar(param);
	}
}

//获取树状图数据
function getBar(param) {
	$.ajax({
		type : "POST",
		url : baseUrl + "/asphaltProductionStatistics/getBar.action", 
		data :  param,
		dataType : "json",
		success : function(data) {
			var str = "{"
					+"name:'',"
					+"type:'bar',"
					+"barWidth: '40%',"
					+"data:["+data.total+", "+data.standard+", "+data.unqualified+"]"
					+"}";
			var Series = eval('(' + str + ')');
			option.series = Series;
			var myChart = echarts.init(document.getElementById('chartmain'));
		    myChart.setOption(option);
		}
	});
}