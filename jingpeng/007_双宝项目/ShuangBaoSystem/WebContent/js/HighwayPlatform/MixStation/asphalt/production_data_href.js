var startTime;
var endTime;
var org_ID;
var Analysis_Result;
var weight = "";//盘重量
$(function() { 
	startTime = getUrlParam("startTime");
	endTime = getUrlParam("endTime");
	org_ID = getUrlParam("org_ID");
	Analysis_Result = getUrlParam("Analysis_Result");
	getList();
});

// 生产统计dataTable
function getList(){
	var table = $('#list').dataTable();
	table.fnDestroy();
	$('#list').DataTable({
        "paging" : true,
        "lengthChange" : false,
        "bLengthChange": false,
        "pageLength" : 10,
        "searching" : false,
        "ordering" : false,
        "info" : true,
        "sInfo" : true,
        "autoWidth" : false,
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
            url: "../../PlatformAsphaltData/getPlatformAsphaltData.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.startTime = startTime +" "+"00:"+"00:"+"00";
                param.endTime = endTime +" "+"23:"+"59:"+"59";
                param.org_ID = org_ID;
                param.Analysis_Result = Analysis_Result;
                return param;//自定义需要传递的参数。
            }
        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "org_Name"
        },{
        	"data" : "collect_Time",
        	 render: function(data, type, row, meta) {
        		 row = JSON.stringify(row);
             	 var html = '';
             	 html ="<span><a onclick='detailed("+row+")'>"+data+"</a></span>";
                 return html;
 	        }
        },
        {"data" : "construction_Unit"},
        {"data" : "proj_Pos"},
        {"data" : "equipment_Name"},
        {"data" : "str_productInfo"},
        {"data" : "proportion_Code"},
        {"data" : "grade_Code"},
        {"data" : "temperature_Meter"},
        {"data" : "total_Weight"},
        {"data" : "no1_MeterValue"},
        {"data" : "no2_MeterValue"},
        {"data" : "no3_MeterValue"},
        {"data" : "no4_MeterValue"},
        {"data" : "no5_MeterValue"},
        {"data" : "no6_MeterValue"},
        {"data" : "coldP_MeterValue"},
        {"data" : "coldP2_MeterValue"},
        {"data" : "hotP_MeterValue"},
        {"data" : "asphalt_Meter"},
        {"data" : "admixture1"},
        {"data" : "admixture2"},
        {
			"data" : "analysis_Result",
			render : function(data, type, row, meta) {
				var str =''
	            	var result=data.split(",");
	            	for(var i=0;i<result.length;i++){
	            		if(result[i] == 1){
	            			str += '正常   '
	            		}
	            		if(result[i] == 2){
	            			str += '骨料超差   '
	            		}
	            		if(result[i] == 3){
	            			str += '粉料超差   '
	            		}
	            		if(result[i] == 4){
	            			str += '沥青超差   '
	            		}
	            		if(result[i] == 5){
	            			str += '外掺剂超差   '
	            		}
	            		if(result[i] == 6){
	            			str += '温度超差   '
	            		}
	            	}
  	            	var html = '';
  		  		    html += "<span style='color: red;' >"+str+"</span>";
  		  		    return html;
			}
        }
        ],
        "createdRow" : function(row, data, dataIndex) {
//        	console.log(row);
//        	console.log(data);
//        	console.log(dataIndex);
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('#list_info').css('text-align','center');
}

//popup明细
function detailed(row){
	/*// 生产配合比编号
	$("#str_proportion_Code").html(row.proportion_Code);*/
	//生产计划编号
	$("#str_proportion_Code").html(row.prodPlan_No);
	// 级配编号
	$("#str_grade_Code").html(row.grade_Code);
	// 产品名称/型号
	$("#str_productInfo").html(row.str_productInfo);
	// 盘重量(kg)
	$("#d_total_Weight").html(row.total_Weight);
	weight = row.total_Weight;
	// 采集数据明细
	getTable1(row.id);
	// 筛分通过率
	//getTable2(row.id);
	//获取生产计划
	$.ajax({
		type : "POST",
		url : "../../PlatformAsphaltData/getProductionPlanListByPlanNo.action",
		data : {"plan_No" : row.prodPlan_No},
		dataType : "json",
		success : function(data){
			if(data != ""){
				getTable2(row.id,data[0].gradId);
			}
		}
	});
	// 原材料消耗
	//getTable3(row.id);
	getTable3(row.prodPlan_No);
	// 弹窗显示
	$('#loginModal').modal('show');
}

//采集数据明细
function getTable1(id){
	$.ajax({
        type : "POST",
        url :  "../../PlatformAsphaltData/getAsphaltPropDataAnalysis.action",
        data : {"id":id},
        dataType : "json",
        success : function(data) { 
            var html="";
            if (data.data.length!=0){
            	for(var i = 0; i < data.data.length; i++) {                 
            		if(data.data[i].data_Type == 0){
						html += "<tr><td class='dise'>" + data.data[i].data_Name
						+ "</td>" + "<td>" + data.data[i].no1 + "</td>"
						+ "<td>" + data.data[i].no2 + "</td>" + "<td>"
						+ data.data[i].no3 + "</td>" + "<td>"
						+ data.data[i].no4 + "</td>" + "<td>"
						+ data.data[i].no5 + "</td>" + "<td>"
						+ data.data[i].no6 + "</td>" + "<td>"
						+ data.data[i].hotPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder2 + "</td>" + "<td>"
						+ data.data[i].asphalt + "</td>" + "<td>"
						+ data.data[i].admixture1 + "</td>" + "<td>"
						+ data.data[i].admixture2 + "</td></tr>";
					}
					if(data.data[i].data_Type == 1){
						html += "<tr><td class='dise'>" + data.data[i].data_Name
						+ "</td>" + "<td>" + data.data[i].no1 + "</td>"
						+ "<td>" + data.data[i].no2 + "</td>" + "<td>"
						+ data.data[i].no3 + "</td>" + "<td>"
						+ data.data[i].no4 + "</td>" + "<td>"
						+ data.data[i].no5 + "</td>" + "<td>"
						+ data.data[i].no6 + "</td>" + "<td>"
						+ data.data[i].hotPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder2 + "</td>" + "<td>"
						+ data.data[i].asphalt + "</td>" + "<td>"
						+ data.data[i].admixture1 + "</td>" + "<td>"
						+ data.data[i].admixture2 + "</td></tr>";
					}
					if(data.data[i].data_Type == 2){
						html += "<tr><td class='dise'>" + data.data[i].data_Name
						+ "</td>" + "<td>" + data.data[i].no1 + "</td>"
						+ "<td>" + data.data[i].no2 + "</td>" + "<td>"
						+ data.data[i].no3 + "</td>" + "<td>"
						+ data.data[i].no4 + "</td>" + "<td>"
						+ data.data[i].no5 + "</td>" + "<td>"
						+ data.data[i].no6 + "</td>" + "<td>"
						+ data.data[i].hotPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder2 + "</td>" + "<td>"
						+ data.data[i].asphalt + "</td>" + "<td>"
						+ data.data[i].admixture1 + "</td>" + "<td>"
						+ data.data[i].admixture2 + "</td></tr>";
					}
					if(data.data[i].data_Type == 4){
						html += "<tr><td class='dise'>" + data.data[i].data_Name
						+ "</td>" + "<td>" + data.data[i].no1 + "</td>"
						+ "<td>" + data.data[i].no2 + "</td>" + "<td>"
						+ data.data[i].no3 + "</td>" + "<td>"
						+ data.data[i].no4 + "</td>" + "<td>"
						+ data.data[i].no5 + "</td>" + "<td>"
						+ data.data[i].no6 + "</td>" + "<td>"
						+ data.data[i].hotPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder2 + "</td>" + "<td>"
						+ data.data[i].asphalt + "</td>" + "<td>"
						+ data.data[i].admixture1 + "</td>" + "<td>"
						+ data.data[i].admixture2 + "</td></tr>";
					}                 
                }   
            }
            $("#tb").html(html);
        }
	});                       
}

//筛分通过率
/*function getTable2(id){
	$.ajax({
        type : "POST",
        url :  "../../PlatformAsphaltData/getAsphaltGradDataAnalysis.action",
        data : {"id":id},
        dataType : "json",
        success : function(data) { 
        	console.log(data);
        }
	});   
}*/
function getTable2(id,gradId){
	$.ajax({
		type : "POST",
		url : "../../PlatformAsphaltData/getAsphaltGradDataAnalysis.action",
		data : {"id" : id,"gradId" : gradId,"data_Type" : 1},
		dataType : "json",
		success : function(data) {
			//列表
            var tb2 = "";
			//earch图
			var meshSizelist = [];
            var upperLimitlist = [];
            var lowerLimitlist = [];
            var medianlimitlist = [];
            var comprehensivelist = [];
            for (var i = 0; i < data.data.length; i++) {
            	meshSizelist.push(data.data[i].meshSize);
            	upperLimitlist.push(data.data[i].upperLimit);
            	lowerLimitlist.push(data.data[i].lowerLimit);
            	medianlimitlist.push(evenRound(data.data[i].medianlimit,1));
            	comprehensivelist.push(evenRound((data.data[i].comprehensive),1));
            	//列表
            	tb2 += "<tr>" 
            				+"<td>"+data.data[i].meshSize+"</td>"
            				+"<td>"+data.data[i].upperLimit+"</td>"
            				+"<td>"+data.data[i].lowerLimit+"</td>"
            				+"<td>"+evenRound(data.data[i].medianlimit,1)+"</td>"
            				+"<td>"+evenRound((data.data[i].comprehensive),1)+"</td>"
            			+"</tr>";
            }
            option = {
            	    tooltip: {
            	        trigger: 'axis'
            	    },
            	    legend: {
            	        data:['级配上线','级配下限','级配中值','综合级配']
            	    },
            	    grid: {
            	        left: '3%',
            	        right: '4%',
            	        bottom: '3%',
            	        containLabel: true
            	    },
            	    toolbox: {
            	        feature: {
            	            saveAsImage: {}
            	        }
            	    },
            	    xAxis: {
            	        type: 'category',
            	        boundaryGap: false,
            	        data: meshSizelist
            	    },
            	    yAxis: {
            	        type: 'value'
            	    },
            	    series: [
            	        {
            	            name:'级配上线',
            	            type:'line',
            	            data:upperLimitlist
            	        },
            	        {
            	            name:'级配下限',
            	            type:'line',
            	            data:lowerLimitlist
            	        },
            	        {
            	            name:'级配中值',
            	            type:'line',
            	            data:medianlimitlist
            	        },
            	        {
            	            name:'综合级配',
            	            type:'line',
            	            data:comprehensivelist
            	        }]
            	};
            var myChart = echarts.init(document.getElementById('chartmain'));
            myChart.setOption(option,true);
            $("#tb2").html(tb2);
		}
	});
}

//原材料消耗
/*function getTable3(id){
	$.ajax({
        type : "POST",
        url :  "../../PlatformAsphaltData/getVMaterialConsumption.action",
        data : {"id":id},
        dataType : "json",
        success : function(data) { 
            var html="";
            if (data.data.length!=0){
            	for(var i = 0; i < data.data.length; i++) {                 
                    html += "<tr><td class='dise'>"+data.data[i].serialNumber+"</td>"
                        +"<td>"+data.data[i].material+"</td>"
                        +"<td>"+data.data[i].materialConsumption+"</td></tr>";                 
                }   
            }
            $("#xh").html(html);
        }
	});                       
}*/
function getTable3(prodPlanNo) {
	$.ajax({
		type : "POST",
		url : "../../PlatformAsphaltData/getVMaterialConsumption.action",
		data : {"prodPlanNo" : prodPlanNo},
		dataType : "json",
		success : function(data) {
			var html = "";
			var str = 0;
			if (data.data.length != 0) {
				for (var i = 0; i < data.data.length; i++) {
					str += data.data[i].d_weight*1;
				}
				for (var i = 0; i < data.data.length; i++) {
					html += "<tr><td class='dise'>" + data.data[i].serialNumber
							+ "</td>" + "<td>" + data.data[i].material
							+ "</td>" + "<td>"
							+ ((data.data[i].d_weight)/str*weight).toFixed(1) + "</td></tr>";
				}
			}
			$("#xh").html(html);
		}
	});
}

//四舍六入 && 补零 参数1：要变换的数；参数2：保留小数位数
function evenRound(num, decimalPlaces) {
	if (num == "" || num == null){
		return "";
	} else {
		// 四舍六入
	    var d = decimalPlaces || 0;
	    var m = Math.pow(10, d);
	    var n = +(d ? num * m : num).toFixed(8); // Avoid rounding errors
	    var i = Math.floor(n), f = n - i;
	    var e = 1e-8; // Allow for rounding errors in f
	    var r = (f > 0.5 - e && f < 0.5 + e) ?
	                ((i % 2 == 0) ? i : i + 1) : Math.round(n);
		// 补零
	    var number = d ? r / m : r;
	    var f_x = parseFloat(number);
	    if (isNaN(f_x)) {
	        return 0;
	    }
	    var s_x = number.toString();
	    var pos_decimal = s_x.indexOf('.');
	    if (pos_decimal < 0 && decimalPlaces > 0) {
	        pos_decimal = s_x.length;
	        s_x += '.';
	    }
	    while (s_x.length <= pos_decimal + decimalPlaces) {
	        s_x += '0';
	    }
	    return s_x;
	}	
}