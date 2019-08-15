var startTime;
var endTime;
var org_ID;
//var materials_echartimer ;
//沥青原材料List
var CementMaterialInfo;
//echar List
var CementProductAnalysisList;
//横轴物料数组数据
var List=[];
$(function() {
	getList();
});

/* ******************tab1 产能分析******************* */
//产能分析 沥青原材料取得
function getCementMaterialInfo(){
	$.ajax({
		type : "post",
		url : "../../PlatformCementProductAnalysis/getCementMaterialInfo.action",
		data : {
			"startTime" : startTime + " " + "00:" + "00:" + "00",
			"endTime" : endTime + " " + "23:" + "59:" + "59",
			"org_ID" : org_ID
		},
		async : false,
		dataType : "json",
		success : function(data) {
			CementMaterialInfo = data;
		}
	});
}
//产能分析 echar
function getCementProductAnalysisList(radio){	
	var date = '';
	if (radio == "day"){
		date = 1;
	} else if (radio == "month"){
		date = 2;
	} else if (radio == "year"){
		date = 3;
	}
	$.ajax({
		type : "post",
		url : "../../PlatformCementProductAnalysis/getCementProductAnalysisList.action",
		data : {
			"startTime" : startTime + " " + "00:" + "00:" + "00",
			"endTime" : endTime + " " + "23:" + "59:" + "59",
			"org_ID" : org_ID,
			"date":date
		},
		async : false,
		dataType : "json",
		success : function(data) {
			CementProductAnalysisList = data;	
		}
	});
}

// 数据拆分到横轴物料数组
function dataSplitting(listSize,d_arr){
	AnalysisList = CementProductAnalysisList[listSize].list;
	MaterialInfo = CementMaterialInfo[listSize].list;
	// 循环原材料
	if (MaterialInfo.length>0){
		// 横轴物料数组数据清空
		List.splice(0,List.length);
		for (var i = 0; i < MaterialInfo.length; i++) {
			var arr = [];
			var product_ID = null;
			if (MaterialInfo[i] != null){
				product_ID = MaterialInfo[i].product_ID;
			}
			// 循环横轴时间								
			for(var j = 0; j <d_arr.length; j++){
				// 循环产能分析List
				var weight = 0;
				if(AnalysisList != null && AnalysisList.length>0){	
					for(var k = 0; k < AnalysisList.length; k++){
						if (d_arr[j] == AnalysisList[k].collect_Time 
								&& product_ID == AnalysisList[k].product_ID){										
							weight = AnalysisList[k].total_Weight;
						} 
					}					
					
				}
				arr.push(weight);
			}
			List.push(arr);
		}	
	}
}

//产能分析 echart创建折线图
function creatChart1(listsize,d_arr) {	
	var myChart_materials = echarts.init(document.getElementById('productivity_echar'));
	option = {
		title: {text:'',x:'center'},
		tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    xAxis: {
	        type: 'category',
	        data: d_arr
	    },
	    yAxis:  {
	        type: 'value',
//	        nameLocation:'middle',
	        name:'生产数量（t）'
	    },	
	    series: []
	};
	if (CementMaterialInfo[listsize].list.length>0){
		for (i = 0; i < CementMaterialInfo[listsize].list.length; i++) {
			var name = '';
			if (CementMaterialInfo[listsize].list[i] != null){
				name = CementMaterialInfo[listsize].list[i].material_Name+CementMaterialInfo[listsize].list[i].material_Model;
			} else {
				name = '未分析';
			}			
			var item = {
				name: name,
		        type: 'bar',
		        stack: '总量',
		        data: List[i]
			}
			option.series.push(item);
			option.title.text = CementMaterialInfo[listsize].orgName;
		}	
	} else {
		option.title.text = CementMaterialInfo[listsize].orgName;
	}	
	myChart_materials.setOption(option, true);
}

/* ******************tab2 材料消耗分析******************* */
// 材料消耗分析查询dataTable
function getList(){
	var radio = $("input[name='date']:checked").val();
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
	org_ID = $("#orgIds").val();
	
	//生成初始时间和结束时间时间段的数组(按天查询)
	var d_arr = [];
	// 生成初始时间和结束时间时间段的数组(按月查询)
	var m_arr = [];
	// 生成初始时间和结束时间时间段的数组(按年查询)
	var y_arr = [];
	d_arr = getDayAll(startTime,endTime);
	console.log(d_arr);
	m_arr = getMonthAll(startTime,endTime);
	console.log(m_arr);
	y_arr = getYearAll(startTime,endTime);
	console.log(y_arr);
	
	if(chart_timer) {
		clearInterval(chart_timer)
	}
	// 产能分析 沥青原材料取得
	getCementMaterialInfo();
	// 产能分析 echar
	getCementProductAnalysisList(radio);
	if (radio == "day"){
		dataSplitting(0,d_arr);
		creatChart1(0,d_arr);
	} else if (radio == "month"){
		dataSplitting(0,m_arr);
		creatChart1(0,m_arr);
	} else if (radio == "year"){
		dataSplitting(0,y_arr);
		creatChart1(0,y_arr);
	}
	
	// 产能分析 轮播
	var listsize = 1;	
	chart_timer = setInterval(function(){
		if(listsize >= CementProductAnalysisList.length) {
			listsize=0;
		}
		if (radio == "day"){
			dataSplitting(listsize,d_arr);
			creatChart1(listsize,d_arr);
		} else if (radio == "month"){
			dataSplitting(listsize,m_arr);
			creatChart1(listsize,m_arr);
		} else if (radio == "year"){
			dataSplitting(listsize,y_arr);
			creatChart1(listsize,y_arr);
		}
		listsize ++;		
	},5000);
	
	// 材料消耗分析
	if(materials_echartimer) {
		clearInterval(materials_echartimer);
	}
	if(warning_echartimer) {
		clearInterval(warning_echartimer);
	}
	if (org_ID.length>0){
		org_ID = org_ID.substr(1,org_ID.length-1)
	}
	
	//生成图标的相应数据数组
	var echartBox = [];
	$.ajax({
				type : "post",
				url : "../../PlatformCementMaterialsConsumption/getPlatformCementMaterialsConsumptionChars.action",
				data : {
					"startTime" : startTime + " " + "00:" + "00:" + "00",
					"endTime" : endTime + " " + "23:" + "59:" + "59",
					"org_ID" : org_ID
				},
				async : false,
				dataType : "json",
				success : function(data) {
					console.log(data);
					$.each(data, function(i, b) {
						var echartObj = {};
						var chartArr = [];
						echartObj.name = b.name;
						if (b.list.length > 0) {
							var smallArr = [];
							$.each(b.list, function(w, k) {
								smallArr.push(k.collect_Date + ":" + k.materialConsumption);
							})
							for (var i = 0;i < d_arr.length;i ++) {
							    var flg = true;
							    for(var j = 0;j < smallArr.length;j ++) {
							        if (smallArr[j].split(':')[0] === d_arr[i]) {
							            flg = false;
							            break
							        }
							    }
							    if (flg) {
							    	chartArr.push("0");
							    } else {
							    	chartArr.push(smallArr[j].split(':')[1]);
							    }
							}
							echartObj.list = chartArr;
							echartBox.push(echartObj);
						} else {
							$.each(d_arr, function(w, q) {
								chartArr.push("0");
							})
							echartObj.list = chartArr;
							echartBox.push(echartObj);
						}
					})
					//定时刷新图标，显示不同的数据
					var i = 1;
					var titl = echartBox[0].name;
					var pra = echartBox[0].list;
					creatChart(titl, d_arr, pra)
					materials_echartimer = setInterval(function(){
						if(i >= echartBox.length) {
							i=0;
						}
						var titles = echartBox[i].name;
						var pram = echartBox[i].list;
						creatChart(titles, d_arr, pram)
						i ++;
					},5000)
				}
			});
	var table = $('#ycl').dataTable();
	table.fnDestroy();
	$('#ycl').DataTable({
        "paging" : true,
        "lengthChange" : false,
        "bLengthChange": false,
        "pageLength" : 14,
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
            url: "../../PlatformCementMaterialsConsumption/getPlatformCementMaterialsConsumption.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.startTime = startTime +" "+"00:"+"00:"+"00";
                param.endTime = endTime +" "+"23:"+"59:"+"59";
                param.org_ID = org_ID;
                return param;//自定义需要传递的参数。
            }
        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "org_Name"
        }, {
            "data" : "material"
        }, {
            "data" : "materialConsumption"
        },{
            "data" : "id",
            render: function(data, type, row, meta) {
            	var html = '';
				html ="<span><a onclick='detailed("+data+")'>详情</a></span>";				
                return html;
	        }
        },],
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
	$('#ycl_info').css('text-align','center');
}
//echart创建折线图
function creatChart(title, Xprams, series) {
	// echarts宽度100%变成了100px 解决方法:在echarts初始化之前再用js获取当前屏幕的大小，然后再给他设置宽度
	var myChart=document.getElementById('materials_echar');
	myChart.style.width=window.innerWidth*0.9+'px';
	chartObj=echarts.init(myChart);
	chartObj.setOption(option);
	var myChart_materials = echarts.init(document.getElementById('materials_echar'));
	option = {
		title : {
			text : title,
			x : 'center'
		},
		tooltip : {
			trigger : 'axis'
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		xAxis : {
			type : 'category',
			boundaryGap: false,
			data : Xprams
		},
		yAxis : {
			type : 'value'
		},
		series : [{
			data:series,
			type: 'line'
		}]
	};
	myChart_materials.setOption(option, true);
}
// popup明细
function detailed(data){
	var org_ID = data;
	$('#ma').DataTable({
        "paging" : true,
        "lengthChange" : false,
        "bLengthChange": false,
        "pageLength" : 14,
        "searching" : false,
        "ordering" : false,
        "info" : true,
        "sInfo" : true,
        "autoWidth" : false,
        "destroy":true, //Cannot reinitialise DataTable,解决重新加载表格内容问题
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
            url: "../../PlatformCementMaterialsConsumption/getPlatformCementMaterialsConsumptionDetail.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.startTime = startTime +" "+"00:"+"00:"+"00";
                param.endTime = endTime +" "+"23:"+"59:"+"59";
                param.org_ID = org_ID;
                return param;//自定义需要传递的参数。
            }

        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "collect_Date"
        }, {
            "data" : "materialConsumption"
        }],
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
	$('#ma_info').css('text-align','center');
	// 弹窗显示
	$('#loginModal').modal('show');
}

//// 生产数据
//function platformCementDate(value){
////	console.log("production_data.html?startTime="+startTime+"&endTime="+endTime+"&org_ID="+org_ID +"&Analysis_Result="+value);
//	loadHTML("production_data.html?startTime="+startTime+"&endTime="+endTime+"&org_ID="+org_ID +"&Analysis_Result="+value, contain);
//	$('.left').each(function(i, n) {
//		$(n).find("a").removeClass($(n).find("a").attr('data-clickClass')).addClass($(n).find("a").attr('data-class'));
//		if($(n).attr('data-src') == 'production_data.html') {
//			$(n).find('a').removeClass($(n).find("a").attr('data-class')).addClass($(n).find("a").attr('data-clickClass'));
//		}
//	})
////	var url = "../asphalt/production_data.html"
////	window.location.href=url+"?startTime="+startTime+"&endTime="+endTime+"&org_ID="+org_ID +"&Analysis_Result="+value;
//}
