//接收项目路径
var baseUrl = "";
var data2 = "";
var str_Str2 ="";
$('.rightcon ').show();
$(function() {
	//试验日期初始化赋值
	$("#startTime").val(getNowFormatDate(1));
	$("#endTime").val(getNowFormatDate(0));
	$("#shiyan").hide();
	$("#mangyang").hide();
	//调用列表方法
	getList();
});

function shiyanclick(){
	$("#shiyan").show();
	$("#echarBox").hide();
	$("#mangyang").hide();
}

function ecarclick(){
	$("#shiyan").hide();
	$("#echarBox").show();
	$("#mangyang").hide();
}


function mangyangclick(){
	$("#shiyan").hide();
	$("#echarBox").hide();
	$("#mangyang").show();
}


//试验日期初始化赋值
function getNowFormatDate(flg) {
	if(flg == 0){
		//获取当前日期
		var date = new Date(); 
	}
	else{
		//获取30天前的日期
		var myDate = new Date();
		var date = new Date(myDate - 1000 * 60 * 60 * 24 * 30);
	}
      
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

//初始化加载列表
function getList() {
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	
	//如果为空 初始时间为2010年 结束时间为当前日期
	if(startTime==""){
		startTime = "2010-01-01"
	}
	if(endTime==""){
		endTime = NowFormatDate();
	}
	
	if(!compareDay(startTime,endTime)){
		swal("操作失败","开始时间不能大于结束时间！", "info");
		return;
	}
	
	// 试验室名称
	var authority = document.getElementsByName("authority");
	var children = document.getElementsByName("children");
	var uniqueIdentifier = "";
	
	// 判断试验室是否选中
	var flag = false;
	for (var j = 0; j < authority.length; j++) {
		// 获取选中项
		if (authority[j].checked) {
			flag = true;
			if(j < authority.length - 1){			
				uniqueIdentifier += authority[j].value + ",";
			}else{
				uniqueIdentifier += authority[j].value;
			}
		}
	}
	
	// 如果父类有勾选
	if(uniqueIdentifier != ""){
		uniqueIdentifier += ",";
	}
	
	for (var j = 0; j < children.length; j++) {
		// 获取选中项
		if (children[j].checked) {
			flag = true;
			if(j < children.length - 1){			
				uniqueIdentifier += children[j].value + ",";
			}else{
				uniqueIdentifier += children[j].value;
			}
		}
	}
	
	//生成初始时间和结束时间时间段的数组(按天查询)
	var d_arr = [];
	var isDate = $("input[name='radio']:checked").val()
	if(isDate == 0){
		d_arr = getDayAll(startTime,endTime);
	}else if(isDate == 1){
		d_arr = getMonthAll(startTime,endTime);
	}else if (isDate == 2){
		d_arr = getYearAll(startTime,endTime);
	}
/*	var bd = new Date(startTime), be = new Date(endTime);
	var bd_time = bd.getTime(), be_time = be.getTime(), time_diff = be_time - bd_time;
	var d_arr = [];
	for (var i = 0; i <= time_diff; i += 86400000) {
		var ds = new Date(bd_time + i);
		var year =ds.getYear()+"";
		var years ='20'+ year.slice(1);
		var month = ds.getMonth() + 1;
		var day = ds.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		d_arr.push(years + "-"+month + '-' + day)
	}*/

	var table = $('#list').dataTable();
	table.fnDestroy();
	$('#list').DataTable({
		"paging" : true,
		"lengthChange" : false,
		"pageLength" : 7,
		"searching" : false,
		"ordering" : false,
		"info" : true,
		"sInfo" : true,
		"autoWidth" : false,
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
		"ajax" : "../../testInfoNew/getTestSummaryDetailed.action?startTime="+startTime+"&endTime="+endTime,
		"deferRender" : true,
		"columns" : [ 
			{"data" : "rowCount"}, 
			{"data" : "testRoomName"},//试验室名
			{"data" : "testZD"},//自动采集试验总数量
			{"data" : "testName"}, //自动采集试验名称
			{"data" : "testZDHE"}, //合格数量
			{"data" : "testNoZDHE"} ,//不合格数量
			],
		"createdRow" : function(row, data, dataIndex) {
			
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		},{
        	targets: [0,1], //要合并的列数（第1，2，3 列）
            createdCell: function (td, cellData, rowData, row, col) { //重要的操作可以合并列的代码
                var rowspan = rowData.merge;//这里主要是利用了模拟数据中的merge来控制
                if (rowspan > 1) {
                    $(td).attr('rowspan', rowspan)
                }
                if (rowspan == 0) {
                    $(td).remove();
                }
            }
        

        } ]
		
	});

	var table = $('#listMangYang').dataTable();
	table.fnDestroy();
	$('#listMangYang').DataTable({
		"paging" : true,
		"lengthChange" : false,
		"pageLength" : 14,
		"searching" : false,
		"ordering" : false,
		"info" : true,
		"sInfo" : true,
		"autoWidth" : false,
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
		"ajax" : "../../testInfoNew/getTestSummaryDetailed.action?startTime="+startTime+"&endTime="+endTime,
		"deferRender" : true,
		"columns" : [ 
			{"data" : "no"}, 
			{"data" : "testRoomName"},//试验室名
			{"data" : "testMY"},//自动采集试验总数量
			{"data" : "testName"}, //自动采集试验名称
			{"data" : "testMYHE"}, //合格数量
			{"data" : "testNoMYHE"} ,//不合格数量
			],
		"createdRow" : function(row, data, dataIndex) {
			
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		},{
        	targets: [0,1], //要合并的列数（第1，2，3 列）
            createdCell: function (td, cellData, rowData, row, col) { //重要的操作可以合并列的代码
                var rowspan = rowData.merge;//这里主要是利用了模拟数据中的merge来控制
                if (rowspan > 1) {
                    $(td).attr('rowspan', rowspan)
                }
                if (rowspan == 0) {
                    $(td).remove();
                }
            }
        

        } ]
		
	});
//	$('.lq_biao .row:first-child').css('display','none');
	$('.dataTables_info').css('text-align','center');
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	
	var echarTitleArray = [];
	var echarLegendDataArray = [];
	var echarSeriesArray = [];
	
	
	//所有部门 试验室名称
	var TestNameBUmenArray = [];
	//所有部门 各个试验室试验名称集合
	var isHaveTestNameArray = [];
	//所有部门 各个试验室试验项目数量及参数集合
	var seriesOptionArray = [];
	$.ajax({
		type : "POST",
		url : "../../testInfoNew/getChart.action",
/*		data : {"startTime":$("#startTime").val()+" "+"00:"+"00:"+"00",
				"endTime":$("#endTime").val()+" "+"23:"+"59:"+"59",
				"uniqueidentifiers":uniqueIdentifier
				},*/
		data : {"startTime":startTime,
			"endTime":endTime,
			"uniqueidentifiers":uniqueIdentifier,
			"isDate":isDate
			},
		dataType : "json",
		success : function(data) {
			if(data.data.length<=0){
				var chartNullArr = [];
				for (var i = 0;i < d_arr.length;i ++) {
					chartNullArr.push("0");

				}
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
				var myChart = echarts.init(document.getElementById('echar'));
				myChart.setOption(option, true);
				return;
			}
			$.each(data.data, function(i, b) {
				if (b.dataList.length > 0) {
					TestNameBUmenArray.push(b.name)
					//series所有参数总数组
					var seriesOption = [];
					//是否存在
					var isHaveTestName = [];
					
					
					var testInfoObject = []
					for (var v = 0; v < b.dataList.length; v++) {
						
						var nowTestName='';
						//把当前循环同一个试验室项目的时间放到一起 
						var nowTestData = [];
						//把当前循环同一个试验室项目的时间下的数量放到一起 
						var nowTestcount = []
						var NowTestAll = {
								NowTestName:nowTestName,
								NowTestData:nowTestData,
								NowTestcount:nowTestcount
						}
						for (var r = 0; r < b.dataList.length; r++) {
							if(b.dataList[v].testName ==  b.dataList[r].testName){
								NowTestAll.NowTestData.push(b.dataList[r].testDate)
								NowTestAll.NowTestcount.push(b.dataList[r].countId)
								NowTestAll.NowTestName = b.dataList[v].testName;
							}
						}
						if($.inArray(b.dataList[v].testName, isHaveTestName)  == -1){
							isHaveTestName.push(b.dataList[v].testName)
							testInfoObject.push(NowTestAll);
						}
					}
					
					var seriesOption = [];
					for (var i = 0; i < testInfoObject.length; i++) {
						var chartArr = [];
						//series 各值集合
						var seriesOptionArry = {
								data:[],
								type : 'line',
								name : ''
						};
						//seriesOption数组
						//循环日期
						for (var z = 0;z < d_arr.length;z ++) {
						    var flg = true;
						    var Testcount;
						    //循环试验项目的每个日期 
						    	for (var u = 0; u < testInfoObject[i].NowTestData.length; u++) {
						    		//如果当前循环的日期 与 试验项目时间循环的其中一天日期一样 添加数量 否则 数量为0
						    		if (testInfoObject[i].NowTestData[u] === d_arr[z]+"") {
						    			flg = false;
						    			Testcount = testInfoObject[i].NowTestcount[u]+"";
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
						seriesOptionArry.name = testInfoObject[i].NowTestName
						seriesOption.push(seriesOptionArry);
					}
				} 	
				//所有部门 试验室名称
				//TestNameBUmenArray.push(b.name);
				//所有部门 各个试验室试验名称集合
				isHaveTestNameArray.push(isHaveTestName);
				seriesOptionArray.push(seriesOption);
			})
				//定时刷新图标，显示不同的数据
	var i = 1;
	var echarTitle = TestNameBUmenArray[0];
	var echarLegend = isHaveTestNameArray[0];
	var echarSeries = seriesOptionArray[0];
	if(chartTimer) {
		clearInterval(chartTimer)
	}
	creatChart(echarTitle,d_arr,echarLegend,echarSeries)
	chartTimer = setInterval(function(){
		if(i >= seriesOptionArray.length) {
			i=0;
		}
		/*var titles = TestNameBUmenArray[i].name;
		var pram = echartBox[i].list;*/
		var echarTitle = TestNameBUmenArray[i];
		var echarLegend = isHaveTestNameArray[i];
		var echarSeries = seriesOptionArray[i];
		creatChart(echarTitle,d_arr,echarLegend,echarSeries)
		i ++;
	},5000)
		}
	});
	
}
//echart创建折线图
function creatChart(title,d_arr, echarLegend,echarSeries) {
	var myChart = echarts.init(document.getElementById('echar'));
	option = {
		title : {
			text : title,
			x : 'center'
		},
		 legend: {
		        data:echarLegend,
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
		series :[]
	};
	
	for (var i = 0; i < echarSeries.length; i++) {
		var seriesOptionSet = {
				data:[],
				type: 'line',
				name:''
		}
		seriesOptionSet.data = echarSeries[i].data[0]
		seriesOptionSet.name = echarSeries[i].name;
		option.series.push(seriesOptionSet)
	}

	myChart.setOption(option, true);
}

//详情各个数
function showDetailsNumber(source,TestRoomName){
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	//跳转页面
	window.open("../item_pages/shiyan_2_1.html?TestRoomName="+TestRoomName+"&source="+source+"&startTime="+startTime+"&endTime="+endTime);
}

//转换时间格式
function getDate(datestr){
	//按-分割
	var temp = datestr.split("-");
	//年月日格式
	var date = new Date(temp[0],temp[1],temp[2]);
	//返回
	return date;
}

//比较日期大小
function compareDay(startTime,endTime){
	var d1 = new Date(startTime.replace(/\-/g, "\/"));  
	var d2 = new Date(endTime.replace(/\-/g, "\/"));  
	if (startTime!=""&&endTime!=""&&d1 >d2){
		return false;
	}else{
		return true;
	}
}

//获取当前时间，格式YYYY-MM-DD
function NowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

