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
        {"data" : "equipment_Name"},
        {"data" : "str_productInfo"},
        {
        	"data" : "total_Weight",
			render : function(data, type, row, meta){
				if(row.org_Name.indexOf("一标") != -1){
					if(row.no1_MeterValue > 20){
						data = data - 20;
					}
					if(row.no2_MeterValue > 20){
						data = data - 20;
					}
					if(row.no3_MeterValue > 20){
						data = data - 20;
					}
					if(row.no4_MeterValue > 20){
						data = data - 20;
					}
					if(row.no5_MeterValue > 20){
						data = data - 20;
					}
					if(row.no6_MeterValue > 20){
						data = data - 20;
					}
				}else{
					data = row.total_Weight;
				}
				return parseFloat(data).toFixed(2);
			}
        },
        {
			"data" : "no1_MeterValue",
			render : function(data, type, row, meta){
				if(row.org_Name.indexOf("一标") != -1){
					if(row.no1_MeterValue > 20){
						data = row.no1_MeterValue - 20;
					}else{
						data = row.no1_MeterValue;
					}
				}
				return data;
			}
		},
        {
			"data" : "no2_MeterValue",
			render : function(data, type, row, meta){
				if(row.org_Name.indexOf("一标") != -1){
					if(row.no2_MeterValue > 20){
						data = row.no2_MeterValue - 20;
					}else{
						data = row.no2_MeterValue;
					}
				}
				return data;
			}
		}, 
		{
			"data" : "no3_MeterValue",
			render : function(data, type, row, meta){
				if(row.org_Name.indexOf("一标") != -1){
					if(row.no3_MeterValue > 20){
						data = row.no3_MeterValue - 20;
					}else{
						data = row.no3_MeterValue;
					}
				}
				return data;
			}
		},
		{
			"data" : "no4_MeterValue",
			render : function(data, type, row, meta){
				if(row.org_Name.indexOf("一标") != -1){
					if(row.no4_MeterValue > 20){
						data = row.no4_MeterValue - 20;
					}else{
						data = row.no4_MeterValue;
					}
				}
				return data;
			}
		},
		{
			"data" : "no5_MeterValue",
			render : function(data, type, row, meta){
				if(row.org_Name.indexOf("一标") != -1){
					if(row.no5_MeterValue > 20){
						data = row.no5_MeterValue - 20;
					}else{
						data = row.no5_MeterValue;
					}
				}
				return data;
			}	
		}, 
		{
			"data" : "no6_MeterValue",
			render : function(data, type, row, meta){
				if(row.org_Name.indexOf("一标") != -1){
					if(row.no6_MeterValue > 20){
						data = row.no6_MeterValue - 20;
					}else{
						data = row.no6_MeterValue;
					}
				}
				return data;
			}
		}, 
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
					var a=0;
	            	var result=data.split(",");
	            	for(var i=0;i<result.length;i++){
	            		if(result[i] == 1){
	            			str += '正常   '
	            				a=1;
	            		}
	            		if(result[i] == 2 || result[i] == 3 || result[i] == 4){
	            			str += '骨料超差   '
	            		}
	            		if(result[i] == 5 || result[i] == 6 || result[i] == 7){
	            			str += '粉料超差   '
	            		}
	            		if(result[i] == 8 || result[i] == 9 || result[i] == 10){
	            			str += '沥青超差   '
	            		}
	            		if(result[i] == 11 || result[i] == 12 || result[i] == 13){
	            			str += '外掺剂超差   '
	            		}
	            		if(result[i] == 14){
	            			str += '温度超差   '
	            		}
	            	}
	            	var html = '';
  	            	if(a==1){
  	            		html += "<span style='color: black;' >"+str+"</span>";	
  	            	}else{
  	            		html += "<span style='color: red;' >"+str+"</span>";
  	            	}
  		  		    return html;
			}
        }
        ],
        "createdRow" : function(row, data, dataIndex) {
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
<<<<<<< .mine
	/*// 生产配合比编号
	$("#str_proportion_Code").html(row.proportion_Code);*/
	//生产计划编号
	$("#str_proportion_Code").html(row.prodPlan_No);
	// 级配编号
	$("#str_grade_Code").html(row.grade_Code);
||||||| .r357
	/*// 生产配合比编号
	$("#str_proportion_Code").html(row.proportion_Code);*/
	//生产计划编号
	//$("#str_proportion_Code").html(row.prodPlan_No);
	// 级配编号
	//$("#str_grade_Code").html(row.grade_Code);
=======
>>>>>>> .r512
	// 产品名称/型号
	$("#str_productInfo").html(row.str_productInfo);
	// 盘重量(kg)
	var total_Weight = row.total_Weight;
	if(row.org_Name.indexOf("一标") != -1){
		if(row.no1_MeterValue > 20){
			total_Weight = total_Weight - 20;
		}
		if(row.no2_MeterValue > 20){
			total_Weight = total_Weight - 20;
		}
		if(row.no3_MeterValue > 20){
			total_Weight = total_Weight - 20;
		}
		if(row.no4_MeterValue > 20){
			total_Weight = total_Weight - 20;
		}
		if(row.no5_MeterValue > 20){
			total_Weight = total_Weight - 20;
		}
		if(row.no6_MeterValue > 20){
			total_Weight = total_Weight - 20;
		}
		$("#d_total_Weight").html(total_Weight.toFixed(2));
		weight = total_Weight;
	}else{
		$("#d_total_Weight").html(parseFloat(row.total_Weight).toFixed(2));
		weight = row.total_Weight;
	}
	// 采集数据明细
	getTable1(row.id);
	// 筛分通过率(数据id，级配id，标段id)
	getTable2(row.id,row.pro_Id,row.org_ID);
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