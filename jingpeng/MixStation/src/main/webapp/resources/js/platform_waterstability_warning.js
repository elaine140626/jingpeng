var org_Ids = "";
$(function () {

	var afterUrl =  window.location.search.substring(1);

	var start_Time = "";
    var end_Time = "";
	if(afterUrl != "") {
		var a =  afterUrl.toString().split("&");
		start_Time = a[0].substring(11);
	    end_Time = a[1].substring(9);
	    var org_id = a[2].substring(8)
	}
	
    //if(start_Time == null || start_Time == ''){
       /* var start_Time = new Date().getFullYear() + "-" + ((new Date().getMonth()) < 10 ? '0' + (new Date().getMonth()) : '' + (new Date().getMonth() + 1)) + "-" + new Date().getDate()+" "+"00:"+"00:"+"00"
        var start = new Date().getFullYear() + "-" + ((new Date().getMonth()) < 10 ? '0' + (new Date().getMonth()) : '' + (new Date().getMonth() + 1)) + "-" + new Date().getDate();
        $("#start_Time").attr("value",start);*/
    //}else{
    	//start_Time = start_Time+" "+"00:"+"00:"+"00"
    //}
    //if(end_Time == null || end_Time == '') {
	var date = new Date();
	var year = date.getFullYear(); //获取年 
	var month = date.getMonth()+1;//获取月  
	var day = date.getDate(); //获取当日
	
    end = getDatePosition(year+"-"+month+"-"+day);
    end_Time = getDatePosition(year+"-"+month+"-"+day)+" 23:"+"59:"+"59";
        $("#end_Time").attr("value",end);
        
        var start = getPreMonth(end);
        start_Time = start + " 00:00:00";
        $("#start_Time").attr("value",start);
    //}else {
    	//end_Time = end_Time+" "+"23:"+"59:"+"59"
   // }
    window.start_Time = start_Time
    window.end_Time = end_Time

    $.ajax({
        type: "post",
        url: "../waterstabilityAndr/getValuebySw.html",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
            $(".userid").html(data.userName)
          /*  if (data.start_Time != null){ 
        		$("#start_Time").attr("value",data.start_Time);
        	}
        	if (data.end_Time != null){ 
        		$("#end_Time").attr("value",data.end_Time);
        	}*/
        	if (data.org_Ids != null && data.org_Ids != "" && data.org_Ids != "null"){ 
        		org_Ids = data.org_Ids;
        	}
        	   $.each(data.listObject,function (i,v) {
   		    	org_Ids =    v.Id
                   
               })
           	
            var list = data.list;
            if(list != null && list.length > 0) {
		           for(var i = 0; i < list.length; i++) {
		        	   var name = list[i].org_Name;
		        	   $("input[type='checkbox']").each(function(){
		        		   var cshow = $(this).attr("cshow");
		        		   if(cshow == name) {
		        			   $(this).attr('checked', 'true');
		        		   }
		        	   });
		           }
            } else {
            	$("input[type='checkbox']").attr('checked', 'true');
            }
        }
    });

    getList11(start_Time,end_Time,org_Ids);
    

//    var chart = {
//        type: 'column'
//    };
//    var title = {
//        text: '生产统计图'
//    };
//    var subtitle = {
//        text: 'Source: runoob.com'
//    };
//    var xAxis = {
//        type:'category',
//        categories: ['04-03','04-05','04-06','04-07','04-08','04-09','04-10','04-11','04-12','04-13','04-14','04-15'],
//        crosshair: true
//    };
//    var yAxis = {
//        min: 0,
//        title: {
//            text: '当日生产总量(t)'
//        }
//    };
//    var tooltip = {
//        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
//        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
//        '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
//        footerFormat: '</table>',
//        shared: true,
//        useHTML: true
//    };
//    var plotOptions = {
//
//    };
//    var credits = {
//        enabled: false
//    };
//
//    var series= [{
//        name: '水稳站1',
//        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
//    }, {
//        name: '水稳站2',
//        data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]
//    }];
//
//    var json = {};
//    json.chart = chart;
//    json.title = title;
//    json.subtitle = subtitle;
//    json.tooltip = tooltip;
//    json.xAxis = xAxis;
//    json.yAxis = yAxis;
//    json.series = series;
//    json.plotOptions = plotOptions;
//    json.credits = credits;
//    $('#container').highcharts(json);

    $("#btnclose").on("click",function () {
       location.reload(false)
    })


    $("#cementwarnbtn").on("click",function () {
    	
//    	console.log("查询")
        var os = "";
        if(org_Ids.length > 0 ) {
            os = org_Ids;
        } else {
            os = "";
        }
        
//        console.log(os)
        var sj1 = $("#start_Time").val()+" "+"00:"+"00:"+"00";
        var sj2 = $("#end_Time").val()+" "+"23:"+"59:"+"59";
        var sj3 = $("#start_Time").val();
        var sj4 = $("#end_Time").val();
        
        selectTb(sj1,sj2,sj3,sj4);
        
       
        var table = $('#waterstability').dataTable();
        table.fnDestroy();
        $('#waterstability').DataTable({
            "paging" : false,
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
                url: "../waterstabilityAndr/getWaterstability_warning",
                dataSrc: "data",
                data: function (d) {
                	var param = {};
                    param.os = os;
                   
                    param.start_Time = $("#start_Time").val()+" "+"00:"+"00:"+"00";
                    param.end_Time = $("#end_Time").val()+" "+"23:"+"59:"+"59";

                    return param;//自定义需要传递的参数。
                }

            },
            "columns" : [ {
                "data" : "serialNumber"
            }, {
                "data" : "Org_Name"
            }, {
                "data" : "Str_TotalCount"
            }, {
                "data" : "Str_Unqualified"
            }, {
                "data" : "SNUnqualified"
            }, {
                "data" : "GLUnqualified"
            }, {
                "data" : "WCJUnqualified"
            }, {
                "data" : "WaterUnqualified"
            }, {
                "data" : "unqualifiedLV"
            }, {
                "data" : "details"
            } ],
            "createdRow" : function(row, data, dataIndex) {
            	
                var c = parseInt(data.Str_Unqualified) * 100 /parseInt(data.TotalCount)
                var bfb = c.toFixed(2) +'%'
                if (bfb=="NaN%"){
                    bfb ="0%"
                }
                $('td:eq(8)', row).html(bfb)
                $(row).children('td').eq(1).attr('style', 'text-align: center;')
                if(data.Str_TotalCount==null || data.Str_TotalCount==''){
                	$('td:eq(2)', row).html(0)
                }
                if(data.Str_Unqualified==null || data.Str_Unqualified==''){
               	 	$('td:eq(3)', row).html(0)
                }
                if(data.SNUnqualified==null || data.SNUnqualified==''){
                	$('td:eq(4)', row).html(0)
                }
                if(data.GLUnqualified==null || data.GLUnqualified==''){
                	$('td:eq(5)', row).html(0)
                }
                if(data.WCJUnqualified==null || data.WCJUnqualified==''){
                	$('td:eq(6)', row).html(0)
                }
                if(data.WaterUnqualified==null || data.WaterUnqualified==''){
                	$('td:eq(7)', row).html(0)
               }
            },
            "columnDefs" : [ {
                "targets" : [ 0 ],
                "visible" : true,
                "searchable" : false
            } ]
        });
        $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出

    })
})
function getList11(start_Time,end_Time,org_Ids) {

    $('#waterstability').DataTable({
        "paging" : false,
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
            url: "../waterstabilityAndr/getWaterstability_warning",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.os = org_Ids;
                param.start_Time = start_Time;
                param.end_Time = end_Time;

                return param;//自定义需要传递的参数。
            }

        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "Org_Name"
        }, {
            "data" : "Str_TotalCount"
        }, {
            "data" : "Str_Unqualified"
        }, {
            "data" : "SNUnqualified"
        }, {
            "data" : "GLUnqualified"
        }, {
            "data" : "WCJUnqualified"
        }, {
            "data" : "WCJUnqualified"
        }, {
            "data" : "unqualifiedLV"
        }, {
            "data" : "details"
        } ],
        "createdRow" : function(row, data, dataIndex) {
        	
            var c = parseInt(data.Str_Unqualified) * 100 /parseInt(data.TotalCount)
            var bfb = c.toFixed(2) +'%'
            if (bfb=="NaN%"){
                bfb ="0%"
            }
         
            $('td:eq(8)', row).html(bfb)
            $(row).children('td').eq(1).attr('style', 'text-align: center;')
            if(data.Str_TotalCount==null || data.Str_TotalCount==''){
            	$('td:eq(2)', row).html(0)
            }
            if(data.Str_Unqualified==null || data.Str_Unqualified==''){
           	 	$('td:eq(3)', row).html(0)
            }
            if(data.SNUnqualified==null || data.SNUnqualified==''){
            	$('td:eq(4)', row).html(0)
            }
            if(data.GLUnqualified==null || data.GLUnqualified==''){
            	$('td:eq(5)', row).html(0)
            }
            if(data.WCJUnqualified==null || data.WCJUnqualified==''){
            	$('td:eq(6)', row).html(0)
            }
            if(data.WaterUnqualified==null || data.WaterUnqualified==''){
            	$('td:eq(7)', row).html(0)
           }
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
    $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出

}

function getTotalCount(org_id) {

    window.open("../waterstabilityAndr/platform_waterstability_warningTotalCount.html?start_Time="+$("#start_Time").val()+'&'+'end_Time='+$("#end_Time").val()+'&'+'org_id='+org_id)

}
function getUnqualified(org_id) {

    window.open("../waterstabilityAndr/platform_waterstability_warningCount.html?start_Time="+$("#start_Time").val()+'&'+'end_Time='+$("#end_Time").val()+'&'+'org_id='+org_id)
}
function detail(orgid) {

    $("#loginModal").modal("show")
    list3(orgid);
    
}
function list3(orgid) {
	
	 var param = {};
     param.os = orgid;

     param.start_Time =  window.start_Time;
     param.end_Time = window.end_Time;
	$.ajax({
	       type: "post",
	       url: "../waterstabilityAndr/getWaterstability_warningdetails",
	       data:param,
	       async:false,
	       dataType: "json",
	       success: function (data) {
	    	   var list = data.data;
	    	   html = "";
	    	   for(var i = 0; i < list.length; i++) {
	    		   var c = parseInt(list[i].Unqualified) * 100 /parseInt(list[i].TotalCount)
	               var bfb = c.toFixed(2) +'%'
	               if (bfb=="NaN%"){
	                   bfb ="0%"
	               }
	    		   if(list[i].collect_Date == null || list[i].collect_Date == ""){
	    			   list[i].collect_Date = ""
	    		   }
	    		   if(list[i].TotalCount==null || list[i].TotalCount==''){
	    			   list[i].TotalCount=0;
	    		   }
	    		   if(list[i].Unqualified==null || list[i].Unqualified==''){
	    			   list[i].Unqualified=0;
	    		   }
	    		   if(list[i].SNUnqualified==null || list[i].SNUnqualified==''){
	    			   list[i].SNUnqualified=0;
	    		   }
	    		   if(list[i].GLUnqualified==null || list[i].GLUnqualified==''){
	    			   list[i].GLUnqualified=0;
	    		   }
	    		   if(list[i].WaterUnqualified==null || list[i].WaterUnqualified==''){
	    			   list[i].WaterUnqualified=0;
	    		   }
	    		   if(list[i].WCJUnqualified==null || list[i].WCJUnqualified==''){
	    			   list[i].WCJUnqualified=0;
	    		   }
	    		   html += "<tr>"
	    			   +"<td>"+list[i].serialNumber+"</td>"
	    			   +"<td>"+list[i].collect_Date+"</td>"
	    			   +"<td>"+list[i].Org_Name+"</td>"
	    			   +"<td>"+list[i].TotalCount+"</td>"
	    			   +"<td>"+list[i].Unqualified+"</td>"
	    			   +"<td>"+list[i].SNUnqualified+"</td>"
	    			   +"<td>"+list[i].GLUnqualified+"</td>"
	    			   +"<td>"+list[i].WaterUnqualified+"</td>"
	    			   +"<td>"+list[i].WCJUnqualified+"</td>"
	    			   +"<td>"+bfb+"</td>"
	    			   +"<tr>";
	    	   }
	    	   $("#wtb").html(html);
	       }
	 });

   /* $('#alertdetail').DataTable({
        "paging" : false,
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
            url: "../waterstabilityAndr/getWaterstability_warningdetails",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.os = orgid;

                param.start_Time = window.start_Time;
                param.end_Time = window.end_Time;

                return param;//自定义需要传递的参数。
            }

        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "collect_Date"
        }, {
            "data" : "Org_Name"
        }, {
            "data" : "TotalCount"
        }, {
            "data" : "Unqualified"
        },{
            "data" : "SNUnqualified"
        }, {
            "data" : "GLUnqualified"
        }, {
            "data": "WaterUnqualified"
        },{
            "data" : "WCJUnqualified"
        },{
            "data" : "unqualifiedLV"
        }
        ],
        "createdRow" : function(row, data, dataIndex) {
            var c = parseInt(data.Unqualified) * 100 /parseInt(data.TotalCount)
            var bfb = c.toFixed(2) +'%'
            if (bfb=="NaN%"){
                bfb ="0%"
            }
            $('td:eq(9)', row).html(bfb)
            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });*/
    
}





var param = {};
//param.start_Time = $("#start_Time").val();
//param.end_Time = $("#end_Time").val();
var end_Time =new Date().getFullYear() + '-' + ((new Date().getMonth() + 1) < 10 ? '0' + (new Date().getMonth() + 1) : '' + (new Date().getMonth() + 1) ) + '-' + new Date().getDate();
var start_Time = getPreMonth(end_Time);
param.os=org_Ids;
param.start_Time =start_Time+" 00:"+"00:"+"00";
param.end_Time = end_Time+" 23:"+"59:"+"59";
function pad(num, n) {  
    var len = num.toString().length;
    var nums = parseInt(num)+1
    while(len <= n) {  
    	nums = nums+"0";  
        len++;  
    }  
    return nums;  
}
$.ajax({
		type : "POST",
		url :  "../waterstabilityAndr/getPlatform_waterstability_warningchars",
		data : param,
		dataType : "json",
		success : function(data) {
			if(data.data[0]!=null && data.data[0]!=''){
		  		if(data.data[0].sum!=null && data.data[0].sum!=''){

				    	//获取最大值
				    	var sum = Math.max.apply(null, data.data[0].sum);
			    		var sumInt=parseInt(sum);
				    	var sumStr = sumInt+"";
				    	var sumY= pad(sumStr.substring(0,1),sumStr.length-1);
				    	option.yAxis[0].max=sumY;
				    	option.yAxis[0].maxInterval=sumY/10;
			    	}else{
			    		option.yAxis[0].max=100;
				    	option.yAxis[0].maxInterval=10;
			    	}
			  	}else{
			  		option.yAxis[0].max=100;
				    	option.yAxis[0].maxInterval=10;
			  	}
			
			//通过查询得到区间的日期
			function getDate(datestr){
			  var temp = datestr.split("-");
			  var date = new Date(temp[0],temp[1],temp[2]);
			  return date;
		}
			
			//给x轴的数据
		var str_Date = '';
		//对比数据，空的补0
		var str_Str = [];
		//循环下标
		var number = 0;
		var start = start_Time ;
		var end =  end_Time;
		var startTime = getDate(start);
		var endTime = getDate(end);
		while((endTime.getTime()-startTime.getTime())>=0){
			 var year = startTime.getFullYear();
			 var month = startTime.getMonth().toString().length==1?"0"+startTime.getMonth().toString():startTime.getMonth();
			 var day = startTime.getDate().toString().length==1?"0"+startTime.getDate():startTime.getDate();
			  
			 str_Str[number] = year+"-"+month+"-"+day;
			 str_Date = day;
			 startTime.setDate(startTime.getDate()+1);
			 option.xAxis[0].data[number] = str_Date;
//			 option.xAxis.data[number] = str_Date;
			 number++;
		}
		var str = "{data: [";
		var jsons="";
		var fh="";
		var fh2="";
		var dataStr="";
		jsons+="{name:'生产总盘数',type:'bar',data:[";
		for (var i = 0; i < str_Str.length; i++) {
			var rd = false;
			var k = 0;
			if(i>=1){
				fh=",";
			}
			str+=fh+'"'+str_Str[i]+'"';
			
			
			for(var j = 0; j < data.data.length; j++) {
				if(str_Str[i]==data.data[0].number[j].collect_Date){
					rd = true;
					k = j ;
				}
				
				
			}
			if(i>0){
				fh2=",";
			}
			if(rd==true){
				jsons+=fh2+data.data[0].number[k].TotalCount;
			}else{
				jsons+=fh2+0;
			}
			
			
		}
		
		
		jsons+="]}";
		str+="]}";
			
		option.series=eval('(' + jsons + ')');
		
	    var myChart = echarts.init(document.getElementById('container'))
	    myChart.setOption(option);
	       
	}
	});

function selectTb(start_Time,end_Time,day1,day2){
	var param = {};
	param.os=org_Ids;
	param.start_Time =start_Time;
	param.end_Time = end_Time;

	$.ajax({
		type : "POST",
		url :  "../waterstabilityAndr/getPlatform_waterstability_warningchars",
		data : param,
		dataType : "json",
		success : function(data) {
			
			if(data.data[0]!=null && data.data[0]!=''){
		  		if(data.data[0].sum!=null && data.data[0].sum!=''){

				    	//获取最大值
				    	var sum = Math.max.apply(null, data.data[0].sum);
			    		var sumInt=parseInt(sum);
				    	var sumStr = sumInt+"";
				    	var sumY= pad(sumStr.substring(0,1),sumStr.length-1);
				    	option.yAxis[0].max=sumY;
				    	option.yAxis[0].maxInterval=sumY/10;
			    	}else{
			    		option.yAxis[0].max=100;
				    	option.yAxis[0].maxInterval=10;
			    	}
			  	}else{
			  		option.yAxis[0].max=100;
				    	option.yAxis[0].maxInterval=10;
			  	}
			
			//通过查询得到区间的日期
			function getDate(datestr){
			  var temp = datestr.split("-");
			  var date = new Date(temp[0],temp[1],temp[2]);
			  return date;
		}
			
			//给x轴的数据
		var str_Date = '';
		//对比数据，空的补0
		var str_Str = [];
		//循环下标
		var number = 0;
		var start = day1;
		var end =  day2;
		var startTime = getDate(start);
		var endTime = getDate(end);
		while((endTime.getTime()-startTime.getTime())>=0){
			 var year = startTime.getFullYear();
			 var month = startTime.getMonth().toString().length==1?"0"+startTime.getMonth().toString():startTime.getMonth();
			 var day = startTime.getDate().toString().length==1?"0"+startTime.getDate():startTime.getDate();
			  
			 str_Str[number] = year+"-"+month+"-"+day;
			 str_Date = day;
			  
			 startTime.setDate(startTime.getDate()+1);
//			 option.xAxis[0].data=eval('(' + str + ')').data;
			 option.xAxis[0].data[number] = str_Date;
			 number++;
		}
		var str = "{data: [";
		var jsons="";
		
		
		var dataStr="";
		jsons+="{name:'生产总盘数',type:'bar',data:[";
		for (var i = 0; i < str_Str.length; i++) {
			var fh="";
			var fh2="";
			var rd = false;
			var k = 0;
			if(i<str_Str.length){
				fh2=",";
			}
			if(i+1<str_Str.length){
				fh=",";
			}
			str+='"'+str_Str[i]+'"'+fh;
			
			for(var j = 0; j < data.data.length; j++) {
				
				
				if(str_Str[i]==data.data[0].number[j].collect_Date){
					rd=true;
					k=j;
				}
			}
			
			if(rd==true){
				jsons+=data.data[0].number[k].TotalCount+fh2;
			}else{
				jsons+=0+fh2;
			}
			
		}
		
		
		jsons+="]}";
		str+="]}";
			
		option.series=eval('(' + jsons + ')');
//		option.xAxis[0].data=eval('(' + str + ')').data;
	    var myChart = echarts.init(document.getElementById('container'))
	    myChart.setOption(option);
	       
	}
	});
}

d.checkNode = function (id,i_parent_Id,flag,obj) {
    if(obj == true) {
        org_Ids += ","+id
    } else {
        org_Ids = org_Ids.replace(","+id,"")
    }
}
d.checkNode = function (id,i_parent_Id,flag,str,obj) {
    if(obj == true) {
        org_Ids += ","+id;
        var html = "<span class='s'>"+str+"</span>";
		$("#station").append(html);
    } else {
        org_Ids = org_Ids.replace(","+id,"");
        $(".s").each(function() {
			if ($(this).text() == str) {
				$(this).remove();
			}
		});
    }

}
function goto(url) {
	window.location.href=url+"?start_Time="+$("#start_Time").val()+"&end_Time="+$("#end_Time").val()+"&org_Ids="+org_Ids;
}