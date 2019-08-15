// 当前登录用户id
var userId = ''; 
// 批次
var planBatch = '';
var lumianNum = 3;
var qiaosuiNum = 3;
var title = '';
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}
	planBatch = getUrlParam("planBatch");
	if(planBatch == 1){
		$('#reportTitle').html("第一批计划进度");
		title = "全省普通公路建设改造工程进度情况汇总表(第一批)";
	}
	else if(planBatch == 2){
		$('#reportTitle').html("第二批计划进度");
		title = "全省普通公路建设改造工程进度情况汇总表(第二批)";
	}
	else if(planBatch == 3){
		$('#reportTitle').html("第三批计划进度");
		title = "全省普通公路建设改造工程进度情况汇总表(第三批)";
	}
	else{
		$('#reportTitle').html("全省");
		title = "全省普通公路建设改造工程进度情况汇总表";
	}
	// datatable数据初始化
	getList();
	
	// 合计值赋值
	getSum();
})

// datatable数据初始化
function getList(){
	var CityName = $("#CityName").val();
	var table1 = $('#table1').dataTable();
	table1.fnDestroy();
	$("#table1").DataTable({
		 "paging": false,
         "lengthChange": false,
//         "pageLength": 14,
         "searching": false,
         "ordering": false,
         "info": true,
         "sInfo": true,
         "autoWidth": false,
         "iDisplayStart" : 0,
         "language": {
             "lengthMenu": "每页 _MENU_ 条记录",
             "zeroRecords": "没有找到记录",
             "info": "第 _PAGE_ 页,共 _PAGES_ 页 (显示_END_ 条，共 _TOTAL_ 项)",
             "infoEmpty": "无记录",
             "sSearch": "在结果中查找：",
             "infoFiltered": "(从 _MAX_ 条记录过滤)",
             "oPaginate": {
                 "sFirst": "第一页",
                 "sPrevious": "上一页",
                 "sNext": "下一页",
                 "sLast": "最后一页"
             },
         },
        "ajax" : {
            type: "post",
            url: "../../FirstPlannedProgress/getFirstPlannedProgressList.action",
            dataSrc: "data",
            data: function (d) {
            	var param = {};
            	param.PlanBatch = planBatch;// 批次
            	param.CityName = CityName;// 市别
                return param;//自定义需要传递的参数。
            }
        },
        "deferRender": true,
        "columns": [
        	{"data": "cityId"},
        	{
        		"data": "cityId",
        		render:function(data,type,row,meta) {
	            	var html = '';
	            	html += "<a onclick='lookUp(\""+row.cityId+"\")'style='text-decoration:none;font-size: 14px;' href='javascript:;' title='查看'>查看</a>&nbsp;&nbsp;&nbsp";
		  		    html += "<a onclick='update(\""+row.cityId+"\")'style='text-decoration:none;font-size: 14px;' href='javascript:;' title='编辑'>编辑</a>";
		  		    return html;
	            }
        	},
        	{"data": "cityName"},
        	{"data": "totalCount"},
        	{"data": "totalStartNumber"},
        	{"data": "totalStartRate"},     	
        	{"data": "totalProvince"},
        	{"data": "totalFinish"},
        	{"data": "totalSchedule"},
        	{
        		"data": "pavementAmount",
        		render:function(data,type,row,meta) {
	            	var html = '';
		  		    html += row.pavementAmount+"/"+row.pavementCount;
		  		    return html;
	            }
        	},
        	{"data": "pavementStartNumber"},
        	{"data": "pavementStartRate"},
        	{"data": "pavementProvince"},
        	{"data": "pavementFinish"},
        	{"data": "pavementSchedule"},
        	{
        		"data": "tunnelAmount",
        		render:function(data,type,row,meta) {
	            	var html = '';
		  		    html += row.tunnelAmount+"/"+row.tunnelCount;
		  		    return html;
	            }
        	},
        	{"data": "tunnelStartNumber"},
        	{"data": "tunnelStartRate"},
        	{"data": "tunnelProvince"},
        	{"data": "tunnelFinish"},
        	{"data": "tunnelSchedule"},
        	{"data": "projectProgress"}   	     	
        ],
        "createdRow": function( row, data, dataIndex ) {
//        	console.log(data);
        },
        "order": [],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一      
            return nRow;
        },
        "columnDefs": []
    });
//	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
//	$('.table1_info').parent('div').addClass('col-sm-12');
}

//合计值赋值
function getSum(){	
	var CityName = $("#CityName").val();
	$.ajax({
		type: 'POST',
		url: '../../FirstPlannedProgress/getFirstPlannedProgressSum.action',
		data:{"PlanBatch":planBatch,"CityName":CityName},
		dataType: 'json',
		success: function(data){
			if (data[0] != null){
				$("#routeLengthSum").html(data[0].routeLengthSum);
				$("#totalCountSum").html(data[0].totalCountSum);
				$("#totalStartNumberSum").html(data[0].totalStartNumberSum);
				$("#totalStartRateSum").html(data[0].totalStartRateSum);
				$("#totalProvinceSum").html(data[0].totalProvinceSum);
				$("#totalFinishSum").html(data[0].totalFinishSum);
				$("#totalScheduleSum").html(data[0].totalScheduleSum);
				$("#pavementAmountSum").html(data[0].pavementAmountSum+"/"+data[0].pavementCountSum);
				$("#pavementStartNumberSum").html(data[0].pavementStartNumberSum);
				$("#pavementStartRateSum").html(data[0].pavementStartRateSum);
				$("#pavementProvinceSum").html(data[0].pavementProvinceSum);
				$("#pavementFinishSum").html(data[0].pavementFinishSum);
				$("#pavementScheduleSum").html(data[0].pavementScheduleSum);
				$("#tunnelAmountSum").html(data[0].tunnelAmountSum+"/"+data[0].tunnelCountSum);
				$("#tunnelStartNumberSum").html(data[0].tunnelStartNumberSum);
				$("#tunnelStartRateSum").html(data[0].tunnelStartRateSum);
				$("#tunnelProvinceSum").html(data[0].tunnelProvinceSum);
				$("#tunnelFinishSum").html(data[0].tunnelFinishSum);
				$("#tunnelScheduleSum").html(data[0].tunnelScheduleSum);
			} else {
				$("#routeLengthSum").html("");
				$("#totalCountSum").html("");
				$("#totalStartNumberSum").html("");
				$("#totalStartRateSum").html("");
				$("#totalProvinceSum").html("");
				$("#totalFinishSum").html("");
				$("#totalScheduleSum").html("");
				$("#pavementAmountSum").html("");
				$("#pavementStartNumberSum").html("");
				$("#pavementStartRateSum").html("");
				$("#pavementProvinceSum").html("");
				$("#pavementFinishSum").html("");
				$("#pavementScheduleSum").html("");
				$("#tunnelAmountSum").html("");
				$("#tunnelStartNumberSum").html("");
				$("#tunnelStartRateSum").html("");
				$("#tunnelProvinceSum").html("");
				$("#tunnelFinishSum").html("");
				$("#tunnelScheduleSum").html("");
			}	
		}
	});			
}

//查询
function search(){	
	// datatable
	getList();	
	// 合计值赋值
	getSum();
}

// 查看
function lookUp(cityId){
	var index = layer.open({
		type: 2,
		title: '查看第一批计划进度',
		content: 'firstPlannedProgress_add.html?cityId='+cityId+'&planBatch='+planBatch+'&flag=0',
	});
	layer.full(index);
}

// 编辑
function update(cityId){
	var index = layer.open({
		type: 2,
		title: '修改第一批计划进度',
		content: 'firstPlannedProgress_add.html?cityId='+cityId+'&planBatch='+planBatch+'&flag=1',
	});
	layer.full(index);
}

//打印
function printTable() { 
	var myDate = new Date();//获取系统当前时间
	var notChecked = $("input:checkbox").not("input:checked") ;//获取未被选择的checkbox
	if (notChecked.length>0){
		for(var i=notChecked.length-1;i>=0;i--){
			// 移除列
			delcol(notChecked[i].value);
		}
	}
	
	// 删除操作列
	delCZ();
	
	//Get the HTML of table
    var tableToPrint = document.getElementById('table1').outerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;
    //Reset the page's HTML with div's HTML only
    document.body.innerHTML = 
        "<html><head><title></title></head><body>" + 
        "<center><h1>"+title+"</h1></center>"+"<right><span>"
        +"填表日期："+myDate.getFullYear()+"-"+Number(Number(myDate.getMonth())+1)+"-"+myDate.getDate()+"</h1></span><br/>"
  	    +tableToPrint + "</body>";
    //Print Page
    window.print();
    //Restore orignal HTML
    document.body.innerHTML = oldPage;
    //Refresh the current page
    location.reload();  
}

// 移除列
function delcol(colnum) {
	var thred = Number(colnum) + 2;
	var tbody = Number(colnum) + 3;
	$("#table1 tr th");
	//$("#table1 tr td");
	//nth-child:获取子元素从 1 开始(tbody)	
	// 移除tbody部分
	$("#table1 tr td:nth-child("+tbody+")").remove();
	
	// 移除thread部分
	// 总项数~工程进度
	if (colnum>0 && colnum<=6){
		//eq:获取子元素索引从 0 开始，先删除表头
    	$("#table1 tr th:eq("+ thred +")").remove();						
	}
	// 路面工程
	if (colnum>6 && colnum<=12){
		$("#table1 tr th:eq("+Number(Number(thred)+Number(lumianNum))+")").remove();
		if ($("#lumianTab").attr('colSpan')>1){					
			$("#lumianTab").attr('colSpan',$("#lumianTab").attr('colSpan') -1);					
		} else {
			$("#table1 tr th:eq(9)").remove();
		}			
	}
	// 桥隧工程
	if (colnum>12 && colnum<=18){
		$("#table1 tr th:eq("+Number(Number(thred)+Number(qiaosuiNum))+")").remove();
		if ($("#qiaosuiTab").attr('colSpan')>1){				
			$("#qiaosuiTab").attr('colSpan',$("#qiaosuiTab").attr('colSpan') -1);					
		} else {
			$("#table1 tr th:eq(10)").remove();
			lumianNum = lumianNum - 1;
		}			
	}
	// 项目进展情况
	if (colnum == 19){
		//eq:获取子元素索引从 0 开始，先删除表头
    	$("#table1 tr th:eq(11)").remove();	
    	lumianNum = lumianNum - 1;
    	qiaosuiNum = qiaosuiNum - 1;
	}
	
	//(tfoot)
	$("#table1 tfoot tr th:eq("+colnum+")").remove();
}

// 删除操作列
function delCZ(){
	//eq:获取子元素索引从 0 开始，先删除表头
	$("#table1 tr th:eq(1)").remove();
	//nth-child:获取子元素从 1 开始
	$("#table1 tr td:nth-child(2)").remove();
	$("#caozuoTab").attr('colSpan',$("#caozuoTab").attr('colSpan') -1);
}
