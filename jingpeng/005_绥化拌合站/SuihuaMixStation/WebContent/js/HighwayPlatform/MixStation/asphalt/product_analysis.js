var startTime;
var endTime;
var org_ID;
//var materials_echartimer ;
// 沥青原材料List
var AsphaltMaterialInfo;
// echar List
var AsphaltProductAnalysisList;
//// 横轴时间
//var collect_Time=[];
// 横轴物料数组数据
var List=[];
$(function() {	
	// 材料消耗分析查询dataTable
	getList();
});

/* ******************tab1 产能分析******************* */
// 产能分析 沥青原材料取得
function getAsphaltMaterialInfo(){
	$.ajax({
		type : "post",
		url : "../../PlatformAsphaltProductAnalysis/getAsphaltMaterialInfo.action",
		data : {
			"startTime" : startTime + " " + "00:" + "00:" + "00",
			"endTime" : endTime + " " + "23:" + "59:" + "59",
			"org_ID" : org_ID
		},
		async : false,
		dataType : "json",
		success : function(data) {
			AsphaltMaterialInfo = data;
		}
	});
}
//产能分析 echar
function getAsphaltProductAnalysisList(radio){	
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
		url : "../../PlatformAsphaltProductAnalysis/getAsphaltProductAnalysisList.action",
		data : {
			"startTime" : startTime + " " + "00:" + "00:" + "00",
			"endTime" : endTime + " " + "23:" + "59:" + "59",
			"org_ID" : org_ID,
			"date":date
		},
		async : false,
		dataType : "json",
		success : function(data) {
			AsphaltProductAnalysisList = data;	
		}
	});
}

// 数据拆分到横轴物料数组
function dataSplitting(listSize,d_arr){
	AnalysisList = AsphaltProductAnalysisList[listSize].list;
//	// 横轴时间数组清空
//	collect_Time.splice(0,collect_Time.length);
//	if (AnalysisList != null && AnalysisList.length>0){
//		var time = "";
//		for(var i=0;i<AnalysisList.length;i++){	
//			if (time != AnalysisList[i].collect_Time){
//				collect_Time.push(AnalysisList[i].collect_Time);
//				time = AnalysisList[i].collect_Time;
//			}					
//		}
//	}
	MaterialInfo = AsphaltMaterialInfo[listSize].list;
	// 循环原材料
	if (MaterialInfo.length>0){
		// 横轴物料数组数据清空
		List.splice(0,List.length);
		for (var i = 0; i < MaterialInfo.length; i++) {
			var arr = [];
      var material_Name = null;
			var material_Model = null;
			if (MaterialInfo[i] != null){
        material_Name = MaterialInfo[i].material_Name;
				material_Model = MaterialInfo[i].material_Model;
			}
			// 循环横轴时间								
			for(var j = 0; j <d_arr.length; j++){
				// 循环产能分析List
				var weight = 0;
				if(AnalysisList != null && AnalysisList.length>0){	
					for(var k = 0; k < AnalysisList.length; k++){
						if (d_arr[j] == AnalysisList[k].collect_Time 
								&& material_Name == AnalysisList[k].material_Name && material_Model == AnalysisList[k].material_Model){										
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
	if (AsphaltMaterialInfo[listsize].list.length>0){
		for (i = 0; i < AsphaltMaterialInfo[listsize].list.length; i++) {
			var name = '';
			if (AsphaltMaterialInfo[listsize].list[i] != null){
				name = AsphaltMaterialInfo[listsize].list[i].material_Name+AsphaltMaterialInfo[listsize].list[i].material_Model;
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
			option.title.text = AsphaltMaterialInfo[listsize].orgName;
		}	
	} else {
		option.title.text = AsphaltMaterialInfo[listsize].orgName;
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
	m_arr = getMonthAll(startTime,endTime);
	y_arr = getYearAll(startTime,endTime);
	
	if(chart_timer) {
		clearInterval(chart_timer);
	}
	// 产能分析 沥青原材料取得
	getAsphaltMaterialInfo();
	// 产能分析 echar
	getAsphaltProductAnalysisList(radio);
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
		if(listsize >= AsphaltProductAnalysisList.length) {
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
//	if(chart_timer) {
//		clearInterval(chart_timer)
//	}
	if(materials_echartimer) {
		clearInterval(materials_echartimer);
	}
	if(warning_echartimer) {
		clearInterval(warning_echartimer);
	}
	if (org_ID.length>0){
		org_ID = org_ID.substr(1,org_ID.length-1)
	}
	getMaterialList(startTime,endTime,org_ID)
	//生成图标的相应数据数组
	var echartBox = [];
	$.ajax({
				type : "post",
				url : "../../PlatformAsphaltMaterialsConsumption/getPlatformAsphaltMaterialsConsumptionChars.action",
				data : {
					"startTime" : startTime + " " + "00:" + "00:" + "00",
					"endTime" : endTime + " " + "23:" + "59:" + "59",
					"org_ID" : org_ID
				},
				async : false,
				dataType : "json",
				success : function(data) {
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
            url: "../../PlatformAsphaltMaterialsConsumption/getPlatformAsphaltMaterialsConsumption.action",
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
	
	onchangeEchar();
}

// 材料消耗分析 echart创建折线图
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

// 材料消耗分析 popup明细
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
            url: "../../PlatformAsphaltMaterialsConsumption/getPlatformAsphaltMaterialsConsumptionDetail.action",
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

/* ******************tab3 油石比波动******************* */
function getMaterialList(strTime,endTime,orgId){
	$.ajax({
		type: "post",
		url: "../../PlatformAsphaltProductAnalysis/getMaterialList.action",
		async: true,
		dataType: 'json',
		data : {
			"startTime" : strTime, 
			"endTime" : endTime,
			"org_Id" : orgId
		},
		success: function(data) {
			var dataList = data[0].list
			var html = "";
			if( dataList.length >0){
				html = "<option value='' selected='selected'>查询全部</option>";
				for (var i = 0; i < dataList.length; i++) {
					html += "<option value='" + dataList[i].Product_ID + "'>"
					+ dataList[i].Material_Name + "-"+dataList[i].Material_Model+"</option>"
				}
				$("#AsphaltMaterialList").empty();
				$("#AsphaltMaterialList").html(html);
			}else{
				html = "<option value='' selected='selected'>无记录</option>";
				$("#AsphaltMaterialList").empty();
				$("#AsphaltMaterialList").html(html);
			}
		}
	});
}

function onchangeEchar(){
	var pId = $("#AsphaltMaterialList").val();
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
	org_ID = $("#orgIds").val();
	//生成初始时间和结束时间时间段的数组(按天查询)
	var d_arr = [];
	d_arr = getDayAll(startTime,endTime);
	var AsphaltList = [] //存放所有工区数组
	$.ajax({
		type: "post",
		url: "../../PlatformAsphaltProductAnalysis/getAggregateEchar.action",
		async: false,
		dataType: 'json',
		data : {
			"startTime" : startTime, 
			"endTime" : endTime,
			"org_Id" : org_ID,
			"pid":pId
		},
		success: function(data) {
			var dataList = data[0].list
			option = {
					title : {
						text : '无记录',
						x : 'center'
					},
					 legend: {
					        data:'',
					        x : 'right'
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
						data : d_arr
					},
					yAxis : {
						type : 'value'
					},
					series :[{
						data:chartNullArr,
						type: 'line',
						name:''
					}
					]
				};
			if(dataList.length<=0){
				var chartNullArr = [];
				for (var i = 0;i < d_arr.length;i ++) {
					chartNullArr.push("0");

				}
				var myChart=document.getElementById('shiYou_echar');
				myChart.style.width=window.innerWidth*0.9+'px';
				chartObj=echarts.init(myChart);
				chartObj.setOption(option);
				var myChart_shiYou = echarts.init(document.getElementById('shiYou_echar'));
				myChart_shiYou.setOption(option, true);
				return;
			}else{
				//拆分各个工区
				var dataListOrg_Name = []; //工区名称数组
				for (var i = 0; i < dataList.length; i++) {
					if($.inArray(dataList[i].Org_Name,dataListOrg_Name)==-1){
						dataListOrg_Name.push(dataList[i].Org_Name);
					}
				}
				
				
				
				//拆分名称型号
				for (var i = 0; i < dataListOrg_Name.length; i++) {
					var nowTestMaterialNameAll = []; //所有型号
					for (var w = 0; w < dataList.length; w++) {
						var nowTestMaterialName = {
								nowTestName:'', //名称型号
								nowTestCollect_Time:[],//采集时间
								nowTestAvgAsphalt:[],	//平均值
						}
						for (var r = 0; r < dataList.length; r++) {
							if(dataList[w].Org_Name == dataListOrg_Name[i] && dataList[w].Material_Name	== dataList[r].Material_Name && dataList[w].Material_Model	== dataList[r].Material_Model){
								nowTestMaterialName.nowTestName = dataList[w].Material_Name+"-"+dataList[w].Material_Model;
								nowTestMaterialName.nowTestCollect_Time.push(dataList[w].Collect_Time)
								nowTestMaterialName.nowTestAvgAsphalt.push(dataList[w].avgAsphalt)
								nowTestMaterialNameAll.push(nowTestMaterialName)
							}
<<<<<<< .mine
||||||| .r4
						}*/
						if(dataList[w].Org_Name == dataListOrg_Name[i]){
							//nowTestMaterialName.nowTestName = dataList[w].Material_Name+"-"+dataList[w].Material_Model;
							nowTestMaterialName.nowTestCollect_Time.push(dataList[w].Collect_Time)
							nowTestMaterialName.nowTestAvgAsphalt.push(dataList[w].avgAsphalt)
							nowTestMaterialNameAll.push(nowTestMaterialName)
=======
						}*/
						if(dataList[w].Org_Name == dataListOrg_Name[i]){
							nowTestMaterialName.nowTestName = dataList[w].Material_Name+"-"+dataList[w].Material_Model;
							nowTestMaterialName.nowTestCollect_Time.push(dataList[w].Collect_Time)
							nowTestMaterialName.nowTestAvgAsphalt.push(dataList[w].avgAsphalt)
							nowTestMaterialNameAll.push(nowTestMaterialName)
>>>>>>> .r200
						}
					}
					var nowTestAll = {
							dataListOrg_Name:'', //当前工区
							nowTestMaterialNameAll:[] //工区下所有型号参数
					}
					nowTestAll.dataListOrg_Name = dataListOrg_Name[i]
					nowTestAll.nowTestMaterialNameAll = nowTestMaterialNameAll
					AsphaltList.push(nowTestAll);
				} //dataListOrg_Name.length 结束
			}
		}//success结尾
	});
	
	var GseriesOption = [];
	for (var i = 0; i < AsphaltList.length; i++) {
		var seriesOption = [];
		//循环日期
		for (var t = 0; t < AsphaltList[i].nowTestMaterialNameAll.length; t++) {
			var chartArr = [];
			//series 各值集合
			var seriesOptionArry = {
					data:[],
					type : 'line',
					name : '',
					title:'',
					legend:[]
			};
		    //循环试验项目的每个日期 
		    for (var z = 0;z < d_arr.length;z ++) {
		    	var flg = true;
		    	var Testcount;
		    	for (var u = 0; u < AsphaltList[i].nowTestMaterialNameAll[t].nowTestCollect_Time.length; u++) {
		    		//如果当前循环的日期 与 试验项目时间循环的其中一天日期一样 添加数量 否则 数量为0
		    		if (AsphaltList[i].nowTestMaterialNameAll[t].nowTestCollect_Time[u] === d_arr[z]+"") {
		    			flg = false;
		    			Testcount = AsphaltList[i].nowTestMaterialNameAll[t].nowTestAvgAsphalt[u]+"";
		    			break
		    		}
		    	}
		    	if (flg) {
		    		chartArr.push("0");
		    	} else {
		    		chartArr.push(Testcount);
		    	}
			}
		    seriesOptionArry.data.push(chartArr)
		    seriesOptionArry.name =  AsphaltList[i].nowTestMaterialNameAll[t].nowTestName
		    seriesOptionArry.title = AsphaltList[i].dataListOrg_Name
		    seriesOption.push(seriesOptionArry);
		}
		/*var legendArray = [];
		for (var o = 0; o < seriesOption.length; o++) {
			legendArray.push(seriesOption[o].name)
		}
		for (var p = 0; p < seriesOption.length; p++) {
			seriesOption[p].legend = legendArray
		}*/
		GseriesOption.push(seriesOption)
	}
	
	$("#shiYou_echar").empty();
	for (var i = 0; i < GseriesOption.length; i++) {
		result = ["<div class=\"charttt\" id=\"shiYou_echar"+i+"\"></div>"].join("");
		 $("#shiYou_echar").append(result);
			var myChart=document.getElementById("shiYou_echar"+i);
			myChart.style.width=window.innerWidth*0.9+'px';
			chartObj=echarts.init(myChart);
			chartObj.setOption(option);
			var myChart_shiYou = echarts.init(document.getElementById('shiYou_echar'+i));
			option = {
				title : {
					text : GseriesOption[i][0].title,
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
					data : d_arr
				},
				yAxis : {
					type : 'value'
				},
				series :[]
			};
			
			for (var g = 0; g < GseriesOption[i].length; g++) {
				var seriesOptionSet = {
						data:[],
						type: 'line',
						name:''
				}
				seriesOptionSet.data = GseriesOption[i][g].data[0]
				seriesOptionSet.name = GseriesOption[i][g].name;
				option.series.push(seriesOptionSet)
			}
			myChart_shiYou.setOption(option, true);
	}
}

//// 生产数据
//function platformAsphaltDate(value){
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