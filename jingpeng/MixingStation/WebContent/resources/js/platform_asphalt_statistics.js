var org_Ids = "";
var start_Time = "";
var start = "";
var end_Time = "";
var end = "";
//用于论辩的数组
var dtList = [];
var strList = [];
$(function () {


	var afterUrl =  window.location.search.substring(1);
	var start_Time = "";
	var end_Time = "";
	if(afterUrl != "") {
		var a =  afterUrl.toString().split("&");
		var start_Time = a[0].substring(11);
	    var end_Time = a[1].substring(9);
	    var org_id = a[2].substring(7)
	}
   
    
        
//        var start_Time = new Date().getFullYear() + "-" + ((new Date().getMonth()) < 10 ? '0' + (new Date().getMonth()) : '' + (new Date().getMonth() + 1)) + "-" + new Date().getDate()+" "+"00:"+"00:"+"00";
//        var start = new Date().getFullYear() + "-" + ((new Date().getMonth()) < 10 ? '0' + (new Date().getMonth()) : '' + (new Date().getMonth() + 1)) + "-" + new Date().getDate();
        
		var date = new Date();
		var year = date.getFullYear(); //获取年 
		var month = date.getMonth()+1;//获取月  
		var day = date.getDate(); //获取当日
		
	    end = getDatePosition(year+"-"+month+"-"+day);
	    end_Time = getDatePosition(year+"-"+month+"-"+day)+" 23:"+"59:"+"59";
        
        $("#end_Time").attr("value",end);

         start = getPreMonth(end);
         start_Time = start+" 00:"+"00:"+"00";
        
        $("#start_Time").attr("value",start);
        
        window.start_Time = start_Time;
        window.end_Time = end_Time;
    
	 $.ajax({
	        type: "post",
	        url: "../Andr/getValue.html",
	        data:{},
	        async:false,
	        dataType: "json",
	        success: function (data) {
	            $(".userid").html(data.userName)
	        	/*if (data.start_Time != null){
	        		$("#start_Time").attr("value",data.start_Time);
	        	}
	        	if (data.end_Time != null){
	        		$("#end_Time").attr("value",data.end_Time);
	        	}*/
	        	if (data.org_Ids != null && data.org_Ids != "" && data.org_Ids != "null"){ 
	        		org_Ids = data.org_Ids;

	        	}
	        	var list = data.list;
	        	if(list != null && list.length > 0) {
	        		var html ="";
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
    
    var end_Time = end;
    var start_Time =start;
    var param = {};
    param.os = org_Ids;
    param.start_Time= start_Time;
    param.end_Time =end_Time;
    
    function getDate(datestr){
    	  var temp = datestr.split("-");
    	  var date = new Date(temp[0],temp[1],temp[2]);
    	  return date;
    }
    $.ajax({
        type: "post",
        data : param,
    	dataType : "json",
        url :  "../Andr/getAsphalt_production_statisticsechar",
        success: function (data) {
        	dtList = data;
//        	for (var i = 0; i < data.data.length; i++) {
//        		
//        		for (var j = 0; j < data.data[i].date.length; j++) {
//					dtList[i]=data.data[i].date;
//					strList[i]=data.data[i].store;
//				}
//				
//			}
        }
    });
    
    getList(start_Time,end_Time,org_Ids);
    setInterval("Broadcast()","3000");
    
    
})

  


function detail(id) {
    $("#loginModal").modal("show")

    getList2(id);
    
}

/*function dataScope(value1, value2) {
        var getDate = function(str) {
            var tempDate = new Date();
            var list = str.split("-");
            tempDate.setFullYear(list[0]);
            tempDate.setMonth(list[1] - 1);
            tempDate.setDate(list[2]);

            return tempDate;
        }
        var date1 = getDate(value1);
        var date2 = getDate(value2);
        if (date1 > date2) {
            var tempDate = date1;
            date1 = date2;
            date2 = tempDate;
        }

        date1.setDate(date1.getDate() + 1);
        var dateArr = [];
        var i = 0;
        while (!(date1.getFullYear() == date2.getFullYear()&& date1.getMonth() == date2.getMonth() && date1.getDate() == date2.getDate())) {
            var dayStr =date1.getDate().toString();


            if(dayStr.length ==1){
                dayStr="0"+dayStr;
            }

            dateArr[i] = date1.getFullYear() + "-" + (date1.getMonth() + 1) + "-"+ dayStr;
            i++;

            date1.setDate(date1.getDate() + 1);
        }

        return dateArr;
    }*/
function getTotalCount(org_id) {

    window.open("../Andr/platform_asphalt_statisticsTotalCount.html?start_Time="+$("#start_Time").val()+'&'+'end_Time='+$("#end_Time").val()+'&'+'org_id='+org_id)

}
function getHeGePanShu(org_id) {

    window.open("../Andr/platform_asphalt_statisticsHeGePanShu.html?start_Time="+$("#start_Time").val()+'&'+'end_Time='+$("#end_Time").val()+'&'+'org_id='+org_id)

}
function getBuHeGePanShu(org_id) {

    window.open("../Andr/platform_asphalt_statisticsBuHeGePanShu.html?start_Time="+$("#start_Time").val()+'&'+'end_Time='+$("#end_Time").val()+'&'+'org_id='+org_id)

}

$(".btncha").on("click",function () {

	//debugger;
	
    var os = "";
	if(org_Ids.length > 0 ) {
		os = org_Ids;
	} else {
		os = "";
	}
	
	 var sj1 = $("#start_Time").val()+" "+"00:"+"00:"+"00";
     var sj2 = $("#end_Time").val()+" "+"23:"+"59:"+"59";
     var sj3 = $("#start_Time").val();
     var sj4 = $("#end_Time").val();
     
	selectTb(sj1,sj2,sj3,sj4);
	var table = $('#lqstatisticslist').dataTable();
	table.fnDestroy();
	$('#lqstatisticslist').DataTable({
        "paging" : false,
        "lengthChange" : false,
        "bLengthChange": false,
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
        "ajax" : {
            type: "post",
            url: "../Andr/getAsphalt_production_statisticslist",
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
            "data" : "ZongChanLiang"
        }, {
            "data" : "LQYongLiang"
        }, {
            "data" : "Str_TotalCount"
        }, {
            "data" : "Str_HeGePanShu"
        }, {
            "data" : "Str_BuHeGePanShu"
        }, {
            "data" : "hegelv"
        }, {
            "data" : "details"
        } ],
        "createdRow" : function(row, data, dataIndex) {
            var c = parseInt(data.HeGePanShu) * 100 /parseInt(data.TotalCount)
            var bfb = c.toFixed(2) +'%'
            if (bfb=="NaN%"){
                bfb ="0%"
            }
            if(data.ZongChanLiang == null || data.ZongChanLiang == "null") {
            	$('td:eq(2)', row).html(0)
            }
            if(data.LQYongLiang == null || data.LQYongLiang == "null") {
            	$('td:eq(3)', row).html(0)
            }
            if(data.TotalCount == null || data.TotalCount == "null") {
            	$('td:eq(4)', row).html(0)
            }
            if(data.HeGePanShu == null || data.HeGePanShu == "null") {
            	$('td:eq(5)', row).html(0)
            }
            if(data.BuHeGePanShu == null || data.BuHeGePanShu == "null") {
            	$('td:eq(6)', row).html(0)
            }
            $('td:eq(7)', row).html(bfb)
            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
    /*$.ajax({
        type: "post",
        contentType: 'application/json;charset=UTF-8',
        url: "../Andr/getAsphalt_production_statisticsechar",
        data:JSON.stringify({"os":os,"start_Time":$("#start_Time").val(),"end_Time":$("#end_Time").val()}),
        dataType: "json",
        success: function (data) {

            var arr = [
                {
                    name:'',
                    data:''
                }

            ]
           $.each(data.data,function (i,v) {
               console.log(v.Org_Name)
               console.log(v.ZongChanLiang)
               arr[i].name=v.Org_Name
               arr[i].data=v.ZongChanLiang

           })

            console.log(arr)


            var chart = Highcharts.chart('container', {
                title: {
                    text: '生产统计图'
                },
                xAxis: {
                    title: {
                        text: '生产日期'
                    },
                    categories: listtime,
                },
                yAxis: {
                    title: {
                        text: '当日生产量(t)'
                    }
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle'
                },
                series: arr,
                responsive: {
                    rules: [{
                        condition: {
                            maxWidth: 500
                        },
                        chartOptions: {
                            legend: {
                                layout: 'horizontal',
                                align: 'center',
                                verticalAlign: 'bottom'
                            }
                        }
                    }]
                }
            });


        }
    });*/



})

function getList2(id) {

    /*$('#lqstatisticspage').DataTable({
        "paging" : false,
        "lengthChange" : false,
        "bLengthChange": false,
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
        "ajax" : {

            type: "post",
            url: "../Andr/getAsphalt_production_statisticspage",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.os = id;
                param.start_Time = window.start_Time;
                param.end_Time = window.end_Time;

                return param;//自定义需要传递的参数。
            }},
        "deferRender" : true,
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "collect_Date"
        }, {
            "data" : "Equipment_Name"
        }, {
            "data" : "TotalCount"
        }, {
            "data" : "HeGePanShu"
        }, {
            "data" : "BuHeGePanShu"
        }, {
            "data" : "LQYongLiang"
        }, {
            "data" : "ZongChanLiang"
        } ],
        "createdRow" : function(row, data, dataIndex) {

            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });*/

	var param = {};
    param.Os = id;
    param.start_Time = $("#start_Time").val()+" 00:"+"00:"+"00";
    param.end_Time =   $("#end_Time").val()+" 23:"+"59:"+"59";
    
	$.ajax({
	       type: "post",
	       url: "../Andr/getAsphalt_production_statisticspage",
	       data:param,
	       async:false,
	       dataType: "json",
	       success: function (data) {
	    	   var list = data.data;
	    	   html = "";
	    	   var a = 0;
	    	   for(var i = 0; i < list.length; i++) {
	    		   html += "<tr>"
	    			   +"<td>"+list[i].serialNumber+"</td>"
	    			   +"<td>"+list[i].collect_Date+"</td>"
	    			   +"<td>"+list[i].Equipment_Name+"</td>"
	    			   +"<td>"+list[i].TotalCount+"</td>"
	    			   +"<td>"+list[i].HeGePanShu+"</td>"
	    			   +"<td>"+list[i].BuHeGePanShu+"</td>"
	    			   +"<td>"+list[i].LQYongLiang+"</td>"
	    			   +"<td>"+list[i].ZongChanLiang+"</td>"
	    			   +"<tr>";
	    		   a++;
	    	   }
	    	   $("#tb").html(html);
	       }
	 });

}
function getList(start_Time,end_Time,org_Ids) {
	
	//debugger;
	
    $('#lqstatisticslist').DataTable({
        "paging" : false,
        "lengthChange" : false,
        "bLengthChange": false,
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
        "ajax" : {

            type: "post",
            url: "../Andr/getAsphalt_production_statisticslist",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.os = org_Ids;//从session获得orgid
                param.start_Time = start_Time+" 00:"+"00:"+"00";
                param.end_Time = end_Time+" 23:"+"59:"+"59";

                return param;//自定义需要传递的参数。
            }

        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "Org_Name"
        }, {
            "data" : "ZongChanLiang"
        }, {
            "data" : "LQYongLiang"
        }, {
            "data" : "Str_TotalCount"
        }, {
            "data" : "Str_HeGePanShu"
        }, {
            "data" : "Str_BuHeGePanShu"
        }, {
            "data" : "hegelv"
        }, {
            "data" : "details"
        } ],
        "createdRow" : function(row, data, dataIndex) {
            var c = parseInt(data.HeGePanShu) * 100 /parseInt(data.TotalCount)
            var bfb = c.toFixed(2) +'%'
            if (bfb=="NaN%"){
                bfb ="0%"
            }
            if(data.ZongChanLiang == null || data.ZongChanLiang == "null") {
            	$('td:eq(2)', row).html(0)
            }
            if(data.LQYongLiang == null || data.LQYongLiang == "null") {
            	$('td:eq(3)', row).html(0)
            }
            if(data.TotalCount == null || data.TotalCount == "null") {
            	$('td:eq(4)', row).html(0)
            }
            if(data.HeGePanShu == null || data.HeGePanShu == "null") {
            	$('td:eq(5)', row).html(0)
            }
            if(data.BuHeGePanShu == null || data.BuHeGePanShu == "null") {
            	$('td:eq(6)', row).html(0)
            }
            $('td:eq(7)', row).html(bfb)
            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
    
    $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
    

}


var option = {
		
		title: {  
	        text: '拌合站名',  
//	        subtext: '',  
	        left: 'center'  
	    }, 
	    legend: {
	        data:['asfasfasfasfas']
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis: {
	    	 type: 'category',
	            axisLabel :{
	            	  interval:0,  
	            },
	        boundaryGap: false,
	    	data:[]
	    },
	    yAxis: {
	        type: 'value',
	        show: true,
	        min: '0',
	    },
	    series: []
};
function pad(num, n) {  
    var len = num.toString().length;
    var nums = parseInt(num)+1
    while(len <= n) {  
    	nums = nums+"0";  
        len++;  
    }  
    return nums;  
}
//记录次数
var nums = 0;
function Broadcast(){
	//给x轴的数据
	var str_Date = '';
	//对比数据，空的补0
	var str_Str = [];
	//循环下标
	var number = 0;
	
	var startTime = getDate(start);
	var endTime = getDate(end);
	
	while((endTime.getTime()-startTime.getTime())>=0){
		 var year = startTime.getFullYear();
		 var month = startTime.getMonth().toString().length==1?"0"+startTime.getMonth().toString():startTime.getMonth();
		 var day = startTime.getDate().toString().length==1?"0"+startTime.getDate():startTime.getDate();
		  
		 str_Str[number] = year+"-"+month+"-"+day;
		 str_Date = day;
		  
		 startTime.setDate(startTime.getDate()+1);
		 option.xAxis.data[number] = str_Date;
		 number++;
	}
	var str = "[";
	if(dtList.data.length>0){
		str += 
			'{'+'"name'+'":'+'"'+'",'+
			'"type'+'":'+'"'+'line'+'",'+
				'"'+"data"+'"'+":["

	    for (var i = 0; i < str_Str.length; i++) {
			var rd = false;
			var index = 0;
			var fh = "";
	    	for (var j = 0; j < dtList.data[nums].date.length; j++) {
				
	    		if(str_Str[i]==dtList.data[nums].date[j]){
	    			rd = true;
	    			index = j;
	    		}
	    		
			}
	    	
	    	if(i+1<str_Str.length){
	    		fh = ",";
	    	}
	    	if(rd==true){
	    		str+=dtList.data[nums].store[index]+fh;
	    	}else{
	    		str+=0+fh;
	    	}
	    	
		}
		str+=']}';
	}
	str+=']';
	var Series = eval('(' + str + ')')
	option.series = Series; 
    var myChart = echarts.init(document.getElementById('chart'))
    myChart.setOption(option);
    nums++;
    if(nums==dtList.data.length){
    	nums=0;
    }

}
function getDate(datestr){
	  var temp = datestr.split("-");
	  var date = new Date(temp[0],temp[1],temp[2]);
	  return date;
}
function selectTb(start_Time,end_Time,day1,day2){
	var param = {};
	param.os = org_Ids;
	param.start_Time= start_Time;
	param.end_Time =end_Time;

	//param.start_Time ='2018-04-01';
	//param.end_Time = '2018-05-11';
	function getDate(datestr){
		  var temp = datestr.split("-");
		  var date = new Date(temp[0],temp[1],temp[2]);
		  return date;
	}

	$.ajax({
	    type: "post",
//	    contentType: 'application/json;charset=UTF-8',
	    data : param,
		dataType : "json",
	    url :  "../Andr/getAsphalt_production_statisticsechar",
//	    data: JSON.stringify(param),
//	    dataType: "json",
	    success: function (data) {
	    	
			//给x轴的数据
			var str_Date = '';
			//对比数据，空的补0
			var str_Str = [];
			//循环下标
			var number = 0;
//			var start = '2018-04-01';
//			var end =   '2018-05-11';
			
			var start = day1;
			var end =  day2;
			
			var startTime = getDate(start);
			var endTime = getDate(end);
			option.xAxis.data.splice(0,option.xAxis.data.length);
			while((endTime.getTime()-startTime.getTime())>=0){
				 var year = startTime.getFullYear();
				 var month = startTime.getMonth().toString().length==1?"0"+startTime.getMonth().toString():startTime.getMonth();
				 var day = startTime.getDate().toString().length==1?"0"+startTime.getDate():startTime.getDate();
				  
				 str_Str[number] = year+"-"+month+"-"+day;
				 str_Date = day;
				  
				 startTime.setDate(startTime.getDate()+1);
				 option.xAxis.data[number] = str_Date;
				 number++;
			}
			
			var str = "[";
			str += 
				'{'+'"name'+'":'+'"'+'",'+
				'"type'+'":'+'"'+'line'+'",'+
					'"'+"data"+'"'+":["
			if(data.data.length>0){
    			for (var i = 0; i < str_Str.length; i++) {
    				var l = 0;
    				var p = 0;
    				for (var j = 0; j < data.data.length; j++) {
						for (var k = 0; k < data.data[j].date.length; k++) {
							if(str_Str[i]==data.data[j].date[k]){
								l = k;
								p = j;
							}
						}
					}
    				
    				if(str_Str[i]==data.data[p].date[l]){
    					str+=data.data[p].store[l]
						if(i+1  < str_Str.length) {
			        		str += ","
			        	}
					}else{
						str+=0
			        	if(i+1  < str_Str.length) {
			        		str += ","
			        	} 
					}
    			}
    			
    		}
			str+=']}]';
			
			var Series = eval('(' + str + ')')
			option.series = Series; 
	        var myChart = echarts.init(document.getElementById('chart'))
	        myChart.setOption(option);
	    }
	});
}

d.checkNode = function (id,i_parent_Id,flag,str,checked) {
	if(checked == true) {
		org_Ids += ","+id
		var html = "<span class='s'>"+str+"</span>"
		$("#station").append(html)
		
	} else {
		org_Ids = org_Ids.replace(","+id,"")
		$(".s").each(function() {
			if ($(this).text() == str) {
				$(this).remove();
			}
		});
	}


}

function goto(url) {
    if(org_Ids.length > 0 ) {
        org_Ids =org_Ids;
    }
    window.location.href=url+"?start_Time="+$("#start_Time").val()+"&end_Time="+$("#end_Time").val()+"&org_Ids="+org_Ids

}
