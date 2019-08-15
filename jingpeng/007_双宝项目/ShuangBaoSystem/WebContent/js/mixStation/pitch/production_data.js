var params = formToJson($('#searchForm'));
params.orgId = orgId;
$(function() {
	$("#locationText").html(orgName);
	// 初始化列表
	getDataList(params);
	jeDate({
		dateCell:"#startTime",
		format:"YYYY-MM-DD",
		isinitVal:false,
		isTime:true, //isClear:false,
//		okfun:function(val){alert(val)}
	})
	
	jeDate({
		dateCell:"#endTime",
		format:"YYYY-MM-DD",
		isinitVal:false,
		isTime:true, //isClear:false,
//		okfun:function(val){alert(val)}
	})
});


//生产数据列表函数
function getDataList(datas) {
	var table = $('#dataList').dataTable();
	table.fnDestroy();
	$("#dataList").DataTable({
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
			url : baseUrl + "/asphaltProductionData/getProductionList.action",
			dataSrc : "data",
			data : datas
		},
		"deferRender" : true,
		"columns" : [
			{"data" : "id"},
			{"data" : "collectTime",},
			{"data" : "constructionUnit"},
			{"data" : "projPos"},
			{"data" : "equipmentName"}, 
			{"data" : "productInfo"}, 
			{"data" : "proportionCode"}, 
			{"data" : "gradeCode"}, 
			{"data" : "temperatureMeter"}, 
			{"data" : "totalWeight"}, 
			{"data" : "no1MeterValue"}, 
			{"data" : "no2MeterValue"}, 
			{"data" : "no3MeterValue"}, 
			{"data" : "no4MeterValue"}, 
			{"data" : "no5MeterValue"}, 
			{"data" : "no6MeterValue"}, 
			{"data" : "coldPMeterValue"}, 
			{"data" : "coldP2MeterValue"}, 
			{"data" : "hotPMeterValue"}, 
			{"data" : "asphaltMeter"}, 
			{"data" : "admixture1Meter"}, 
			{"data" : "admixture2Meter"}, 
			{"data" : "analysisResult",
	            render:function(data,type,row,meta) {
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
	            		if(result[i] == 10){
	            			str += ''
	            		}
	            	}
  	            	var html = '';
  		  		    html += "<td class='warn1' >"+str+"</td>";
  		  		    return html;
            }} 
		],
		"order" : [ [ 1, 'asc' ] ],
		"createdRow": function( row, data, dataIndex ) {
			$(row).children("td").eq(1).html('<a href="javasript:;"onclick="getDetailss('+data.id+')">'+data.collectTime+'</a>');
			if(!data.no1) {
				$(row).children("td").eq(10).addClass("warn2");
			}
			if(!data.no2) {
				$(row).children("td").eq(11).addClass("warn2");						
			}
			if(!data.no3) {
				$(row).children("td").eq(12).addClass("warn2");
			}
			if(!data.no4) {
				$(row).children("td").eq(13).addClass("warn2");
			}
			if(!data.no5) {
				$(row).children("td").eq(14).addClass("warn2");
			}
			if(!data.no6) {
				$(row).children("td").eq(15).addClass("warn2");
			}
			if(!data.hotPowder) {
				$(row).children("td").eq(18).addClass("warn2");
			}
			if(!data.coldPowder) {
				$(row).children("td").eq(16).addClass("warn2");
			}
			if(!data.coldPowder2) {
				$(row).children("td").eq(17).addClass("warn2");
			}
			if(!data.asphalt) {
				$(row).children("td").eq(19).addClass("warn2");
			}
			if(!data.admixture1) {
				$(row).children("td").eq(20).addClass("warn2");
			}
			if(!data.admixture2) {
				$(row).children("td").eq(21).addClass("warn2");
			}
			if(data.analysisResult != "" && data.analysisResult != 1) {
				$(row).children("td").eq(22).addClass("warn1");
			}
			
			
		 },
		"fnRowCallback" : function(nRow, aData, iDisplayIndex) {
			$("td:first", nRow).html(iDisplayIndex + 1);// 设置序号位于第一列，并顺次加一
			return nRow;
		}
	});
}
//搜索
function search() {
	params = formToJson($('#searchForm'));
	params.orgIds = orgIds;
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
	getDataList(params);
}

//弹窗详情
function getDetailss(id) {
    $("#info span").html("");
    $("#loginModal").modal("show")
    var e = [];
    $(".modal").on("show.bs.modal",
        function() {
            for (var s = 0; e.length > s; s++) e[s] && (e[s].modal("hide"), e[s] = null);
            e.push($(this));
            var o = $(this),
                a = o.find(".modal-dialog"),
                t = $('<div style="display:table; width:100%; height:100%;"></div>');
            t.html('<div style="vertical-align:middle; display:table-cell;"></div>'),
                t.children("div").html(a),
                o.html(t)
        })

    $.ajax({
        type : "POST",
        url :  baseUrl + "/asphaltProductionData/getAsphaltPropDataAnalysis.action",
        data : {"id":id},
        dataType : "json",
        success : function(data) {
            var asphaltProductionData = data.asphaltProductionData;
            var asphaltPropDataAnalysisList = data.asphaltPropDataAnalysisList;
            $("#str_proportion_Code").html(asphaltProductionData.prodPlanNo);
            $("#str_grade_Code").html(asphaltProductionData.gradeCode);
            $("#str_productInfo").html(asphaltProductionData.productInfo);
            $("#d_total_Weight").html(asphaltProductionData.totalWeight);
            var html="";
            for(var i = 0; i < asphaltPropDataAnalysisList.length; i++) {
            	  if(asphaltPropDataAnalysisList[i].str_data_Type == 0) {
                       html += "<tr><td class='dise'>采集重量</td>"
                           +"<td>"+asphaltPropDataAnalysisList[i].d_no1+"</td>"
                           +"<td>"+asphaltPropDataAnalysisList[i].d_no2+"</td>"
                           +"<td>"+asphaltPropDataAnalysisList[i].d_no3+"</td>"
                           +"<td>"+asphaltPropDataAnalysisList[i].d_no4+"</td>"
                           +"<td>"+asphaltPropDataAnalysisList[i].d_no5+"</td>"
                           +"<td>"+asphaltPropDataAnalysisList[i].d_no6+"</td>"
                           +"<td>"+asphaltPropDataAnalysisList[i].d_hotPowder+"</td>"
                           +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder+"</td>"
                           +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder2+"</td>"
                           +"<td>"+asphaltPropDataAnalysisList[i].d_asphalt+"</td>"
                           +"<td>"+asphaltPropDataAnalysisList[i].d_admixture1+"</td>"
                           +"<td>"+asphaltPropDataAnalysisList[i].d_admixture2+"</td></tr>";
                   }
                if(asphaltPropDataAnalysisList[i].str_data_Type == 1) {
                    html += "<tr><td class='dise'>采集配比</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no3+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no4+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no5+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no6+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_hotPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_asphalt+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture2+"</td></tr>";
                }
                if(asphaltPropDataAnalysisList[i].str_data_Type == 2) {
                    html += "<tr><td class='dise'>生产配比</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no3+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no4+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no5+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no6+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_hotPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_asphalt+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture2+"</td></tr>";
                }
                if(asphaltPropDataAnalysisList[i].str_data_Type == 4) {
                    html += "<tr><td class='dise'>实际偏差</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no3+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no4+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no5+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no6+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_hotPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_asphalt+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture2+"</td></tr>";
                }
            }
            var asphaltGradDataAnalysisList = data.asphaltGradDataAnalysisList;
            var meshSizelist = [];
            var upperLimitlist = [];
            var lowerLimitlist = [];
            var medianlimitlist = [];
            var comprehensivelist = [];
            for(var i = 0; i < asphaltGradDataAnalysisList.length; i++) {
            	meshSizelist.push(asphaltGradDataAnalysisList[i].meshSize);
            	upperLimitlist.push(asphaltGradDataAnalysisList[i].upperLimit);
            	lowerLimitlist.push(asphaltGradDataAnalysisList[i].lowerLimit);
            	medianlimitlist.push(evenRound(asphaltGradDataAnalysisList[i].medianlimit,1));
            	comprehensivelist.push(evenRound((asphaltGradDataAnalysisList[i].comprehensive),1));
            	
            }
            var htmltb="";
            console.log(asphaltGradDataAnalysisList)
            for(var i = 0; i < asphaltPropDataAnalysisList.length; i++) {
          	  if(i<asphaltGradDataAnalysisList.length) {
          		htmltb += "<tr><td>"+asphaltGradDataAnalysisList[i].meshSize+"</td>"
          				 +"<td>"+asphaltGradDataAnalysisList[i].upperLimit+"</td>"
                         +"<td>"+asphaltGradDataAnalysisList[i].lowerLimit+"</td>"
          		         +"<td>"+evenRound(asphaltGradDataAnalysisList[i].medianlimit,1)+"</td>"
          		 		 +"<td>"+evenRound((asphaltGradDataAnalysisList[i].comprehensive),1)+"</td></tr>";
              }
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
           $("#tb").html(html);
           $("#tb2").html(htmltb);
            var myChart = echarts.init(document.getElementById('chartmain'))
            myChart.setOption(option);
            var materialConsumption = data.materialConsumption;
            var xh = "";
            var str = 0;
            var weight = asphaltProductionData.totalWeight
            for(var i = 0; i < materialConsumption.length; i++) {
            	str += materialConsumption[i].d_weight*1;
            }
            for(var i = 0; i < materialConsumption.length; i++) {
                xh += "<tr><td>"+i+1+"</td><td>"+materialConsumption[i].material+"</td><td>"+((materialConsumption[i].d_weight)/str*weight).toFixed(1)+"</td></tr>"
            }
            $("#xh").html(xh);
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