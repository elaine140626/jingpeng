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
	
	var date = new Date();
	var year = date.getFullYear(); //获取年 
	var month = date.getMonth()+1;//获取月  
	var day = date.getDate(); //获取当日
	
    
    	end_Time = getDatePosition(year+"-"+month+"-"+day)+" 23:"+"59:"+"59";
        var end = getDatePosition(year+"-"+month+"-"+day);
        $("#end_Time").attr("value",end);
        
        var start = getPreMonth(end);
        start_Time = start + " 00:00:00";
        $("#start_Time").attr("value",start);
        
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
          /*   if (data.start_Time != null){ 
        		$("#start_Time").attr("value",data.start_Time);
        	}
        	if (data.end_Time != null){ 
        		$("#end_Time").attr("value",data.end_Time);
        	}*/
        	if (data.org_Ids != null && data.org_Ids != "" && data.org_Ids != "null"){ 
        		org_Ids = data.org_Ids;
        		console.log(org_Ids)
        	}
        		//console.log(data)
		    $.each(data.listObject,function (i,v) {
		    	org_Ids =   v.Id
                
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


    getList(start_Time,end_Time,org_Ids);
    $("#btnchar").on("click", function () {
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
        var table = $('#waterstabilitystatisticslist').dataTable();
        table.fnDestroy();

        $('#waterstabilitystatisticslist').DataTable({
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
                url: "../waterstabilityAndr/getWaterstability_production_statisticslist",
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
                "data" : "SNYongLiang"
            }, {
                "data" : "TotalCount"
            }, {
                "data" : "HeGePanShu"
            }, {
                "data" : "BuHeGePanShu"
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
                $('td:eq(7)', row).html(bfb)
                $(row).children('td').eq(1).attr('style', 'text-align: center;')
                
                if(data.ZongChanLiang==null || data.ZongChanLiang==''){
                	$('td:eq(2)', row).html(0);
                }
                if(data.SNYongLiang==null || data.SNYongLiang==''){
                	$('td:eq(3)', row).html(0);
                }
                if(data.TotalCount==null || data.TotalCount==''){
                	$('td:eq(4)', row).html(0);
                }
                if(data.HeGePanShu==null || data.HeGePanShu==''){
                	$('td:eq(5)', row).html(0);
                }
                if(data.BuHeGePanShu==null || data.BuHeGePanShu==''){
                	$('td:eq(6)', row).html(0);
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

    var param = {};
    param.start_Time = start_Time;
    param.end_Time = end_Time;
    param.os = org_Ids;
    //param.start_Time = start_Time;
    //param.end_Time = end_Time;

     
     function getDate(datestr){
    	  var temp = datestr.split("-");
    	  var date = new Date(temp[0],temp[1],temp[2]);
    	  return date;
    }

    $.ajax({
    	    type: "post",
    	//  contentType: 'application/json;charset=UTF-8',
    	    data : param,
    		dataType : "json",
    		url :  "../waterstabilityAndr/getWaterstability_production_statisticsechar",
    	//  data: JSON.stringify(param),
    	//  dataType: "json",
    	  success: function (data) {
    	  	
    			//给x轴的数据
    			var str_Date = '';
    			//对比数据，空的补0
    			var str_Str = [];
    			//循环下标
    			var number = 0;
    			
    			var start = $("#start_Time").val();
    			var end = $("#end_Time").val();
    			
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
    			str += 
    				'{'+'"name'+'":'+'"'+'",'+
    				'"type'+'":'+'"'+'line'+'",'+
    					'"'+"data"+'"'+":["
    					if(data.data!=null){
    						for (var i = 0; i < str_Str.length; i++) {
    							var rd = false;
    							var k = 0;
    							for (var j = 0; j < data.data[0].date.length; j++) {
    								if(str_Str[i]==data.data[0].date[j]){
    									rd=true;
    									k=j;
    								}
    							}
    							
    							if(rd==true){
    								str+=data.data[0].nuber[k]
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
    	      var myChart = echarts.init(document.getElementById('container'))
    	      myChart.setOption(option);
    	  }
    	});
})
function getTotalCount(org_id) {

    window.open("../waterstabilityAndr/platform_waterstability_warningTotalCount.html?start_Time="+$("#start_Time").val()+'&'+'end_Time='+$("#end_Time").val()+'&'+'org_id='+org_id)

}
function getHeGePanShu(org_id) {

    window.open("../waterstabilityAndr/platform_waterstability_warningHeGePanShu.html?start_Time="+$("#start_Time").val()+'&'+'end_Time='+$("#end_Time").val()+'&'+'org_id='+org_id)

}
function getBuHeGePanShu(org_id) {

    window.open("../waterstabilityAndr/platform_waterstability_warningBuHeGePanShu.html?start_Time="+$("#start_Time").val()+'&'+'end_Time='+$("#end_Time").val()+'&'+'org_id='+org_id)

}
function detail(id) {
    $("#loginModal").modal("show")
    getList2(id);
}
function getList2(id) {
	

	
	var param = {};
	  param.Os = id;
	  param.start_Time = $("#start_Time").val()+" 00:"+"00:"+"00";
	  param.end_Time =   $("#end_Time").val()+" 23:"+"59:"+"59";
	$.ajax({
	       type: "post",
	       url: "../waterstabilityAndr/getWaterstability_production_statisticspage",
	       data:param,
	       async:false,
	       dataType: "json",
	       success: function (data) {
	    	   var list = data.data;
	    	   html = "";
	    	   for(var i = 0; i < list.length; i++) {
	    		   if(list[i].TotalCount==null || list[i].TotalCount==''){
	    			   list[i].TotalCount=0;
	    		   }
	    		   if(list[i].HeGePanShu==null || list[i].HeGePanShu==''){
	    			   list[i].HeGePanShu=0;
	    		   }
	    		   if(list[i].BuHeGePanShu==null || list[i].BuHeGePanShu==''){
	    			   list[i].BuHeGePanShu=0;
	    		   }
	    		   if(list[i].SNYongLiang==null || list[i].SNYongLiang==''){
	    			   list[i].SNYongLiang=0;
	    		   }
	    		   if(list[i].ZongChanLiang==null || list[i].ZongChanLiang==''){
	    			   list[i].ZongChanLiang=0;
	    		   }
	    		   html += "<tr>"
	    			   +"<td>"+list[i].serialNumber+"</td>"
	    			   +"<td>"+list[i].collect_Date+"</td>"
	    			   +"<td>"+list[i].str_Equipment_Name+"</td>"
	    			   +"<td>"+list[i].TotalCount+"</td>"
	    			   +"<td>"+list[i].HeGePanShu+"</td>"
	    			   +"<td>"+list[i].BuHeGePanShu+"</td>"
	    			   +"<td>"+list[i].SNYongLiang+"</td>"
	    			   +"<td>"+list[i].ZongChanLiang+"</td>"
	    			   +"<tr>";
	    	   }
	    	   
	    	   $("#wtb").html(html);
	       }
	 });
	


}
function getList(start_Time,end_Time,org_Ids) {

    $('#waterstabilitystatisticslist').DataTable({
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
            url: "../waterstabilityAndr/getWaterstability_production_statisticslist",
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
            "data" : "ZongChanLiang"
        }, {
            "data" : "SNYongLiang"
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
            $('td:eq(7)', row).html(bfb)
            $(row).children('td').eq(1).attr('style', 'text-align: center;')
            if(data.ZongChanLiang==null || data.ZongChanLiang==''){
            	$('td:eq(2)', row).html(0);
            }
            if(data.SNYongLiang==null || data.SNYongLiang==''){
            	$('td:eq(3)', row).html(0);
            }
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });

}

var option = {
	    
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['采集数据曲线','生产配比重量曲线','级配上限','级配下限','级配中值']
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
	     /*   max:'50000',
	        splitNumber: 1,
	        maxInterval: 5000*/
	    },
	    series: []
	};

var end_Time =new Date().getFullYear() + '-' + ((new Date().getMonth() + 1) < 10 ? '0' + (new Date().getMonth() + 1) : '' + (new Date().getMonth() + 1) ) + '-' + new Date().getDate();
var start_Time = getPreMonth(end_Time);
function pad(num, n) {  
    var len = num.toString().length;
    var nums = parseInt(num)+1
    while(len <= n) {  
    	nums = nums+"0";  
        len++;  
    }  
    return nums;  
}





function selectTb(start_Time,end_Time,day1,day2){
	var param = {};
	param.os = "";
	param.start_Time = start_Time;
	param.end_Time = end_Time;
	 function getDate(datestr){
		  var temp = datestr.split("-");
		  var date = new Date(temp[0],temp[1],temp[2]);
		  return date;
	}

	$.ajax({
	    type: "post",
	//  contentType: 'application/json;charset=UTF-8',
	    data : param,
		dataType : "json",
		url :  "../waterstabilityAndr/getWaterstability_production_statisticsechar",
	//  data: JSON.stringify(param),
	//  dataType: "json",
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
//			if(data.data!=null){
//				for (var i = 0; i < str_Str.length; i++) {
//					var rd = false;
//					var k = 0;
//					var xhcs = data.data.length-1;
//					for(var j = 0; j < xhcs; j++) {
	//
//						for (var l = 0; l < data.data[j].date.length; l++) {
	//
//							
//							if(str_Str[i]==data.data[j].date[l]){
//								rd=true;
//								k=l;
//							}
//						
//						}
//						
//						if(rd==true){
//							str+=data.data[j].nuber[k]
//							if(i+1  < str_Str.length) {
//				        		str += ","
//				        	}
//						}else{
//							str+=0
//				        	if(i+1  < str_Str.length) {
//				        		str += ","
//				        	} 
//						}
//					}
//					
//				}
//			}
					if(data.data!=null){
						for (var i = 0; i < str_Str.length; i++) {
							var rd = false;
							var k = 0;
							for (var j = 0; j < data.data[0].date.length; j++) {
								if(str_Str[i]==data.data[0].date[j]){
									rd=true;
									k=j;
								}
							}
							
							if(rd==true){
								str+=data.data[0].nuber[k]
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
	      var myChart = echarts.init(document.getElementById('container'))
	      myChart.setOption(option);
	  }
	});
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
    if(org_Ids.length > 0 ) {
        org_Ids =org_Ids;
    }
    window.location.href=url+"?start_Time="+$("#start_Time").val()+"&end_Time="+$("#end_Time").val()+"&org_Ids="+org_Ids

}