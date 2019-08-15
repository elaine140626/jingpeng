var startTime;
var endTime;
var org_ID;
$(function() {
	getList();
});

// 预警信息查询dataTable
function getList(){
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
	org_ID = $("#orgIds").val();
	if(chart_timer) {
		clearInterval(chart_timer)
	}
	if(materials_echartimer) {
		clearInterval(materials_echartimer);
	}
	if(warning_echartimer) {
		clearInterval(warning_echartimer);
	}
	if (org_ID.length>0){
		org_ID = org_ID.substr(1,org_ID.length-1)
	}
	//生成初始时间和结束时间时间段的数组
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
	//生成图标的相应数据数组
	var echartBox = [];
	$.ajax({
				type : "post",
				url : "../../platformCementStableWarningInformation/getPlatformCementStableWarningInformationChars.action",
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
								smallArr.push(k.collect_Date + ":" + k.totalCount);
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
					warning_echartimer = setInterval(function(){
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
	
	var table = $('#cementStable_warning').dataTable();
	table.fnDestroy();
	$('#cementStable_warning').DataTable({
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
            url: "../../platformCementStableWarningInformation/getPlatformCementStableWarningInformation.action",
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
        },{
            "data" : "totalCount",
            render: function(data, type, row, meta) {   
            	return "<span><a href='production_data_href.html?startTime="+startTime+"&endTime="+endTime+"&org_ID="+row.org_ID +"&Analysis_Result=0' target='_blank'>"+data+"</a></span>";
	        }
        }, {
            "data" : "unqualified",
            render: function(data, type, row, meta) {   
            	return "<span><a href='production_data_href.html?startTime="+startTime+"&endTime="+endTime+"&org_ID="+row.org_ID +"&Analysis_Result=2' target='_blank''>"+data+"</a></span>";
	        }
        }, {
            "data" : "snunqualified"
        }, {
            "data" : "glunqualified"
        }, {
            "data" : "waterUnqualified"
        }, {
            "data" : "wcjunqualified"
        }, {
            "data" : "totalCount",
            render: function(data, type, row, meta) {
            	var html = '';
            	if(data != 0){
					html = percentNum(row.unqualified,row.totalCount);
				}else{
					html ="0%";
				}
                return html;
	        }
        }, {
            "data" : "id",
            render: function(data, type, row, meta) {
            	var html = '';
				html ="<span><a onclick='detailed("+data+")'>详情</a></span>";
                return html;
	        }
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
        }]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('#Asphalt_warning_info').css('text-align','center');
}
//echart创建折线图
function creatChart(title, Xprams, series) {
	var myChart_warning = echarts.init(document.getElementById('warning_echar'));
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
	myChart_warning.setOption(option, true);
}
// popup明细
function detailed(data){
	var org_ID = data;
	$('#alertdetail').DataTable({
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
            url: "../../platformCementStableWarningInformation/getPlatformCementStableWarningInformationDetail.action",
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
            "data" : "org_Name"
        }, {
            "data" : "totalCount"
        }, {
            "data" : "unqualified"
        }, {
            "data" : "snunqualified"
        }, {
            "data" : "glunqualified"
        }, {
            "data" : "waterUnqualified"
        }, {
            "data" : "wcjunqualified"
        },{
            "data" : "totalCount",
            render: function(data, type, row, meta) {
            	var html = '';
            	if(data != 0){
					html = percentNum(row.unqualified,row.totalCount);
				}else{
					html ="0%";
				}
                return html;
	        }
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
	$('#alertdetail_info').css('text-align','center');
	// 弹窗显示
	$('#loginModal').modal('show');
}
