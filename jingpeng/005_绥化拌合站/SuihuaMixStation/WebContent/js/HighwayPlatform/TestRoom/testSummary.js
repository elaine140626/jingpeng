//接收项目路径
var baseUrl = "";
var data2 = "";
var str_Str2 ="";
$(function() {
	//试验日期初始化赋值
	$("#startTime").val(getNowFormatDate(1));
	$("#endTime").val(getNowFormatDate(0));

	//调用列表方法
	getList();
});

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
	if(chartTimer) {
		clearInterval(chartTimer)
	}//生成初始时间和结束时间时间段的数组
	//var bd = new Date($("#startTime").val()), be = new Date($("#endTime").val());
	var bd = new Date(startTime), be = new Date(endTime);
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
	}
	// 图标显示的日期
	// console.log(d_arr);
	//生成图标的相应数据数组
	$.ajax({
		type : "POST",
		url : "../../testInfo/getChart.action",
/*		data : {"startTime":$("#startTime").val()+" "+"00:"+"00:"+"00",
				"endTime":$("#endTime").val()+" "+"23:"+"59:"+"59",
				"uniqueidentifiers":uniqueIdentifier
				},*/
		data : {"startTime":startTime,
			"endTime":endTime,
			"uniqueidentifiers":uniqueIdentifier
			},
		dataType : "json",
		success : function(data) {
			var echartBox = [];
			console.log(data.data);
			$.each(data.data, function(i, b) {
				var echartObj = {};
				var chartArr = [];
				var chartArr2 = [];
				echartObj.name = b.name;
				if (b.dataList.length > 0) {
					var smallArr = [];
					$.each(b.dataList, function(w, k) {
						if(k.testDate) {
							smallArr.push(k.testDate + ":" + k.countId + ":" + k.testHE);
						}
						
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
					    	chartArr2.push("0");
					    } else {
					    	chartArr.push(smallArr[j].split(':')[1]);
					    	chartArr2.push(smallArr[j].split(':')[2]);
					    }
					}
					echartObj.list = chartArr;
					echartObj.list2 = chartArr2;
					echartBox.push(echartObj);
				} else {
					$.each(d_arr, function(w, q) {
						chartArr.push("0");
						chartArr2.push("0");
					})
					echartObj.list = chartArr;
					echartObj.list2 = chartArr2;
					echartBox.push(echartObj);
				}
				console.log(echartBox);
			})
			//定时刷新图标，显示不同的数据
			var i = 1;
			var titl = echartBox[0].name;
			var pra = echartBox[0].list;
			var pp = echartBox[0].list2;
			creatChart(titl, d_arr, pra, pp)
			chartTimer = setInterval(function(){
				if(i >= echartBox.length) {
					i=0;
				}
				var titles = echartBox[i].name;
				var pram = echartBox[i].list;
				var prams = echartBox[i].list2;
				creatChart(titles, d_arr, pram, prams)
				i ++;
			},5000)
		}
	});
	
	
	
	
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
		"ajax" : "../../testInfo/getTestSummary.action?startTime="+startTime+"&endTime="+endTime+"&uniqueidentifiers="+uniqueIdentifier,
		"deferRender" : true,
		"columns" : [ 
			{"data" : "no"}, 
			{"data" : "TestRoomName"},//试验室名
			{"data" : "testCount"},//试验总数
			{"data" : "testHG"}, //区间内试验总数
			{"data" : "ZD"}, //自动采集
			{"data" : "ZDHE"}, //区间自动采集
			{"data" : "MY"} ,//盲样
			{"data" : "MYHE"},//区间盲样
			{"data" : "operation"} 
			],
		"createdRow" : function(row, data, dataIndex) {
			
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		} ]
		
	});
//	$('.lq_biao .row:first-child').css('display','none');
	$('.dataTables_info').css('text-align','center');
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	
}
//echart创建折线图
function creatChart(title, Xprams, series,series2) {
	var myChart = echarts.init(document.getElementById('echar'));
	option = {
		title : {
			text : title,
			x : 'center'
		},
		 legend: {
		        data:['实验总数','实验合格数'],
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
			data : Xprams
		},
		yAxis : {
			type : 'value'
		},
		series : [{
			data:series,
			type: 'line',
			 name:'实验总数',
		},
		{
			data:series2,
			type: 'line',
			 name:'实验合格数',
		}]
	};
	myChart.setOption(option, true);
}
//查看列表详情
function showDetails(TestRoomName){
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	$.ajax({
		type : "POST",
		url : "../../testInfo/getTestSummaryDetailed.action",
		data : {"TestRoomName":TestRoomName,"startTime":startTime,"endTime":endTime},
		dataType : "json",
		success : function(data) {
			var list = data.data;
	    	html = "";
	    	for(var i = 0; i < list.length; i++) {
	    		html += "<tr>"
	    			+"<td>"+list[i].no+"</td>"
	    			+"<td>"+list[i].testName+"</td>"
	    			+"<td>"+list[i].testZD+"</td>"
	    			+"<td>"+list[i].testMY+"</td>"
	    			+"<td>"+list[i].testZDHE+"</td>"
	    			+"<td>"+list[i].testMYHE+"</td>"
	    			+"<tr>";
	    	}
	    	//把拼接好的tr td 放入容器
	    	$("#listDetailed").html(html);
		}
	});
	//弹窗显示
	$("#loginModal").modal("show")
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
