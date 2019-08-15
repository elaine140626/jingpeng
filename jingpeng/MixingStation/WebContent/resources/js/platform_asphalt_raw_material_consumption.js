var org_Ids = "";

var start_Time = "";
var start = "";
var end_Time = "";
var end = "";

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
    window.start_Time = start_Time
    window.end_Time = end_Time

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
	
    getList(start_Time,end_Time,org_Ids);
    
    
    $("#lqrawbtn").on("click",function () {
        var os = "";
        if(org_Ids.length > 0 ) {
            os = org_Ids;
        } else {
            os = "";
        }
        var $org_Ids = os
    	var $start_Time = $("#start_Time").val()+" "+"00:"+"00:"+"00";
        var $end_Time = $("#end_Time").val()+" "+"23:"+"59:"+"59";

        var sj3 = $("#start_Time").val();
        var sj4 = $("#end_Time").val();
        
        selectTb($start_Time,$end_Time,sj3,sj4);
        
        var param = {};
        param.os = os;
        param.start_Time = start_Time;
        param.end_Time = end_Time;
       
   	$.ajax({
           type: "post",
           url: "../Andr/getmaterial_consumption",
           data:param,
           async:false,
           dataType: "json",
           success: function (data) {
           	var html = "";
           	var serialNumber = 0;
           	var org_Name = data[0].Org_Name;
           	if(data.length == 0) {
           		html = "<tr><td colspan='5'>没有找到记录</td></tr>";
           	} else {
           		for(var i = 0; i < data.length; i++) {
               		var count = 0;
               		html += "<tr>";
               		for(var j = 0; j < data.length; j++) {
               			if(data[j].Org_Name == data[i].Org_Name) {
               				count++;
               			}
               		}
               		if(org_Name != data[i].Org_Name || i == 0) {
               			serialNumber++;
               			html +="<td rowspan='"+count+"'>"+serialNumber+"</td>"
           			    	 +"<td rowspan='"+count+"'>"+data[i].Org_Name+"</td>"
           			    	 +"<td>"+data[i].Material+"</td>"
           			    	 +"<td>"+Number(data[i].MaterialConsumption).toFixed(2)+"</td>"
           			    	 +"<td rowspan='"+count+"'>"+"<span><a href='javascript:void(0)' onclick='detail('" + data[i].Id+ "');' >详情</a></span>"+"</td>"
           			    	 +"</tr>";
               			org_Name = data[i].Org_Name;
               		} else {
               			html +="<td>"+data[i].Material+"</td>"
          			    		  +"<td>"+Number(data[i].MaterialConsumption).toFixed(2)+"</td>"
          			    		  +"</tr>";
               		}
               	}
           	}
           	$('#tab').html(html);
           }
       });

        
    })

    var end_Time = end;
    var start_Time = start;
    var param = {};
    param.os = "6,23,24,3";
    param.start_Time =start_Time;
    param.end_Time = end_Time;
    $.ajax({
	    type: "post",
//	    contentType: 'application/json;charset=UTF-8',
	    data : param,
		dataType : "json",
		url :  "../Andr/getmaterial_consumptionechar",
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
			
			var start = param.start_Time;
			var end =  param.end_Time;
			
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
			
			for (var i = 0; i < data.data.length; i++) {
				str += 
					'{'+'"name'+'":'+'"'+'",'+
					'"type'+'":'+'"'+'line'+'",'+
						'"'+"data"+'"'+":[";
				for (var j = 0; j < data.data[i].date.length; j++) {
					var rd = false;
					var l = 0;
					for (var k = 0; k < str_Str.length; k++) {
						
						if(data.data[i].date[j]==str_Str[k]){
							if(k+1<str_Str.length){
								str+=data.data[i].store[j]+",";
							}else{
								str+=data.data[i].store[j];
							}
						}else{
							if(k+1<str_Str.length){
								str+=0+",";
							}else{
								str+=0;
							}
						}
					}
					if(i+1<data.data.length){
						str+="]},"
					}else{
						str+="]}"
					}
				}
			}
			str+="]";
//			if(data.data.length>0){
//				for (var i = 0; i < str_Str.length; i++) {
//					str += 
//						'{'+'"name'+'":'+'"'+'",'+
//						'"type'+'":'+'"'+'line'+'",'+
//							'"'+"data"+'"'+":["
//					var rd = false;
//					var k = 0;
//					var xh = data.data.length-1;
//					for(var j = 0; j < xh; j++) {
//						var fhs = "";
//						for (var l = 0; l < data.data[j].date.length; l++) {
//
//							
//							if(str_Str[i]==data.data[j].date[l]){
//								rd=true;
//								k=l;
//							}
//							if(j<data.data.length){
//								fhs=",";
//							}
//							if(rd==true){
//								str+=fhs+data.data[j].store[k]
//								
//							}else{
//								str+=fhs+0
//							}
//						
//						}
//						
//					}
//					
//					if(i+1  < str_Str.length) {
//		        		str += "]},"
//		        	}
//					
//				}
//				str+=']}';
//			}
//			str+=']';
			
			var Series = eval('(' + str + ')')
			option.series = Series; 
	        var myChart = echarts.init(document.getElementById('charts'))
	        myChart.setOption(option);
	    }
	});
})

function getList(start_Time,end_Time,org_Ids) {
	
	 var param = {};
     param.os = org_Ids;
     param.start_Time = start_Time;
     param.end_Time = end_Time;
    
	$.ajax({
        type: "post",
        url: "../Andr/getmaterial_consumption",
        data:param,
        async:false,
        dataType: "json",
        success: function (data) {
        	var html = "";
        	var serialNumber = 0;
        	var org_Name = data[0].Org_Name;
        	if(data.length == 0) {
        		html = "<tr><td colspan='5'>没有找到记录</td></tr>";
        	} else {
        		for(var i = 0; i < data.length; i++) {
            		var count = 0;
            		html += "<tr>";
            		for(var j = 0; j < data.length; j++) {
            			if(data[j].Org_Name == data[i].Org_Name) {
            				count++;
            			}
            		}
            		if(org_Name != data[i].Org_Name || i == 0) {
            			serialNumber++;
            			html +="<td rowspan='"+count+"'>"+serialNumber+"</td>"
        			    	 +"<td rowspan='"+count+"'>"+data[i].Org_Name+"</td>"
        			    	 +"<td>"+data[i].Material+"</td>"
        			    	 +"<td>"+Number(data[i].MaterialConsumption).toFixed(2)+"</td>"
        			    	 +"<td rowspan='"+count+"'>"+"<span><a href='javascript:void(0)' onclick='detail(" + data[i].Id+ ");'>详情</a></span>"+"</td>"
        			    	 +"</tr>";
            			org_Name = data[i].Org_Name;
            		} else {
            			html +="<td>"+data[i].Material+"</td>"
       			    		  +"<td>"+Number(data[i].MaterialConsumption).toFixed(2)+"</td>"
       			    		  +"</tr>";
            		}
            	}
        	}
        	$('#tab').html(html);
        }
    });
}

function detail(id) {
	$("#loginModal").modal("show")
    list2(id)

}

function list2(id) {
	var $start_Time = $("#start_Time").val()+" "+"00:"+"00:"+"00";
    var $end_Time = $("#end_Time").val()+" "+"23:"+"59:"+"59";
    var param = {};
    param.os = id;
    param.start_Time = window.start_Time;
    param.end_Time = window.end_Time;
	$.ajax({
	       type: "post",
	       url: "../Andr/getmaterial_consumption1",
	       data:param,
	       async:false,
	       dataType: "json",
	       success: function (data) {
	    	   var list = data.data;
	    	   html = "";
	    	   for(var i = 0; i < list.length; i++) {
	    		   var ma = "";
	    		   if(list[i].Material != null) {
	    			   ma = list[i].Material;
	    		   }
	    		   html += "<tr>"
	    			   +"<td>"+list[i].serialNumber+"</td>"
	    			   +"<td>"+list[i].date+"</td>"
	    			   +"<td>"+ma+"</td>"
	    			   +"<td>"+Number(list[i].MaterialConsumption).toFixed(2)+"</td>"
	    			   +"<tr>";
	    	   }
	    	   $("#wtb").html(html);
	       }
	 });

}

var option = {
	    
//	    tooltip: {
//	        trigger: 'axis'
//	    },
	    legend: {
	        data:['1111']
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
//	    toolbox: {
//	        feature: {
//	            saveAsImage: {}
//	        }
//	    },
	    xAxis: {
	    	 type: 'category',
	            axisLabel :{
	            	  interval:0,  
		    		   /* rotate:40 */  
	            },
	    /* 	type: 'category', */
	        boundaryGap: false,
	    	data:[]
	        /* type: 'category',
	        boundaryGap: false,
	        data: ['0.075','0.15','0.3','0.6','1.18','2.36','4.75','9.5','13.2','16','19','26.5','31.5','37.5','53'] */
	    },
	    yAxis: {
	        type: 'value',
	        show: true,
	        min: '0',
//	        max:'',
//	        splitNumber: 1,
//	        maxInterval: 
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

function getDate(datestr){
	  var temp = datestr.split("-");
	  var date = new Date(temp[0],temp[1],temp[2]);
	  return date;
}

 
 function selectTb(start_Time,end_Time,day1,day2){
	 var param = {};
	 param.os = "6,23,24,3";
	 param.start_Time =start_Time;
	 param.end_Time = end_Time;


	 function getDate(datestr){
	 	  var temp = datestr.split("-");
	 	  var date = new Date(temp[0],temp[1],temp[2]);
	 	  return date;
	 }
	  $.ajax({
	 	    type: "post",
//	 	    contentType: 'application/json;charset=UTF-8',
	 	    data : param,
	 		dataType : "json",
	 		url :  "../Andr/getmaterial_consumptionechar",
//	 	    data: JSON.stringify(param),
//	 	    dataType: "json",
	 	    success: function (data) {
	 	    	
	 	    	
	 	    	
	 	    	//通过查询得到区间的日期
	 			
	 			
	 			//给x轴的数据
	 			var str_Date = '';
	 			//对比数据，空的补0
	 			var str_Str = [];
	 			//循环下标
	 			var number = 0;
//	 			var start = '2018-04-01';
//	 			var end =   '2018-05-11';
	 			
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
						'"'+"data"+'"'+":[";
	 			if(data.data.lengt>0){
					for (var i = 0; i < str_Str.length; i++) {
						
						var rd = false;
						var k = 0;
						var xh = data.data.length-1;
						for(var j = 0; j < xh; j++) {
							var fhs = "";
							for (var l = 0; l < data.data[j].date.length; l++) {

								
								if(str_Str[i]==data.data[j].date[l]){
									rd=true;
									k=l;
								}
								if(j<data.data.length){
									fhs=",";
								}
								if(rd==true){
									str+=fhs+data.data[j].nuber[k]
									
								}else{
									str+=fhs+0
								}
							
							}
							
						}
						
						if(i+1  < str_Str.length) {
			        		str += "]},"
			        	}
						
					}
					
				}
	 			str+=']}]';
	 			
	 			var Series = eval('(' + str + ')')
//	 			option.series.splice(0,option.series.length);
	 			option.series = '[]'; 
	 	        var myChart = echarts.init(document.getElementById('charts'))
	 	        myChart.setOption(option);
	 	    }
	 	});
 }
d.checkNode = function (id,i_parent_Id,flag,str,obj) {
	if(obj == true) {
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
	window.location.href=url+"?start_Time="+$("#start_Time").val()+"&end_Time="+$("#end_Time").val()+"&org_Ids="+org_Ids;
}