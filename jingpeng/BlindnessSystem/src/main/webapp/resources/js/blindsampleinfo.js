var baseUrl = "";
var start_Time = "";
var start = "";
var end_Time = "";
var end = "";
var orgId = "";
var map = {};
var orgId1 = "";
var oId ="";
var html ="";

$(function() {
	 var authority = document.getElementsByName("authority"); 
	    for(var i=0;i<$(".dtree_node").length;i++){
	    	//var rownum = $(".dtree_node").length - authority.length;
	    	var autOut = authority[i];
	    	if(autOut!=null && autOut!=""){
	    		$("#"+autOut.id).attr("checked","checked");
	    		//var j = autOut.id;
	    	}
	    	if($(".dtree_node")[i].previousSibling == null||$(".dtree_node")[i].previousSibling==""){
	    		continue;
	    	}
	    	$("#position").empty();
	    		html+="<span class='s'>"+ ($(".dtree_node")[i]).childNodes[0].data+"</span>";	
	    		$("#position").append(html);

	     }
	
	$("#isOrName").show();
	$("#isOrName2").show();
	d.checkNode = function (id,i_parent_Id,flag,str,obj) {
		if(obj == true) {
			html = "<span id='s' class='s'>"+str+"</span>"
			$("#isOrName").show();
			$("#isOrName2").show();
			$("#position").append(html);
		}else{
			$(".s").each(function() {
				if ($(this).text() == str) {
					$(this).remove();
				}
			});
			var isAllCheck =true;
		    for(var i=0;i<$(".dtree_node").length;i++){
		    	var autEm = authority[i];
		    	if(autEm!=null && autEm!=""){
		    		varisChecked = document.getElementById(autEm.id).checked;
		    	}
		    	if(varisChecked){
		    		isAllCheck = false;
		    	}
		     }
		    if(isAllCheck){
		    	$("#isOrName").hide();
		    	$("#isOrName2").hide();
		    	//$("#position").empty();		    		
		    }
		}
		/*if($('#position').children().length>0){
			$("#isOrName").css('display','black');
			$("#isOrName2").css('display','black');
		}*/
/*		if(obj == true) {
			if(id==2||id==3||id==4){
			if(id==2){	
				$(".s5").empty();
				var html = "<span id='s' class='s2'>"+str+"</span>"
			}
			if(id==3){				
				var html = "<span id='s' class='s3'>"+str+"</span>"
			}
			if(id==4){				
				var html = "<span id='s' class='s4'>"+str+"</span>"
			}
			}else{
				var html = "<span id='s' class='s5'>"+str+"</span>"
			}

			//var html = "<span class='s'>"+">"+str+"</span>"
			$("#position").append(html)
			//$("#position").append(html2)
		} else {
			$(".s5").each(function() {
				if ($(this).text() == str) {
					$(this).remove();
				}
			});
			if(id==2&&obj==false){
				$(".s5").each(function() {
					$("#s").remove(".s5");
				});
				$("#s").remove(".s2");
			}
			if(id==3&&obj==false){
				$(".s5").each(function() {
					$("#s").remove(".s5");
				});
				$("#s").remove(".s3");
			}
			if(id==4&&obj==false){
				$(".s5").each(function() {
					$("#s").remove(".s5");
				});
				$("#s").remove(".s4");
			}
		}*/
		
			/*if(id==2){
				if(obj==true){
				for (var a = 5; a < 9; a++) {
					$("#authority_"+a).prop("checked",true);			
				}
				}else{
					for (var a = 5; a < 9; a++) {
						$("#authority_"+a).prop("checked",false)			
					}	
				}
			}
			if(id==3){
				if(obj==true){					
					for (var a = 9; a < 14; a++) {
						$("#authority_"+a).prop("checked",true);			
					}
				}else{
					for (var a = 9; a < 14; a++) {
						$("#authority_"+a).prop("checked",false)		
					}
				}
			}
			if(id==4){
				if(obj==true){
					for (var a = 14; a < 18; a++) {
						$("#authority_"+a).prop("checked",true);			
					}					
				}else{
					for (var a = 14; a < 18; a++) {
						$("#authority_"+a).prop("checked",false)			
					}
				}
			}
			if(id!=2&&id!=3&&id!=4){
				if(obj==true){
						$("#authority_"+id).prop("checked",true);								
				}else{
						$("#authority_"+id).prop("checked",false);		
				}
			}*/
		
	}
	
	
	var authorit = document.getElementsByName("authority");
	for(var i=0;i<authorit.length;i++){
			var aut = authorit[i]
			if(aut.id!="authority_2"&&aut.id!="authority_3"&&aut.id!="authority_4"){
			$('#'+aut.id).css("margin-left","20px");
		}
     }
	$("input[name='authority']") .each(function(){
		varisChecked = this.checked
    	if(varisChecked) {
    		orgId1 = orgId1+$(this).attr('value')+","
    	}
		  });
	
	$.ajax({
        type: "post",
        url: "../BlindSampleInfo/getBlindSampleInfoValue",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	orgId = data.orgId;
        	$(".userid").html(data.name)
        }
    });
	var afterUrl =  window.location.search.substring(1);
	var start_Time = "";
	var end_Time = "";
	if(afterUrl != "") {
		var a =  afterUrl.toString().split("&");
		var start_Time = a[0].substring(11);
	    var end_Time = a[1].substring(9);
	    var orgid = a[2].substring(7)
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
    
    window.start_Time = start_Time;
    window.end_Time = end_Time;
    getConstructionUnit();
    getEngineeringName();
    getList(start_Time,end_Time,orgId);
/*    getList(start_Time,end_Time);*/
	/*getList();*/
});
function showBlind(id) {

    window.open("../BlindSampleInfo/jilu_2.html?id="+id+"&orgid="+orgId);

}
function getList(start_Time,end_Time,orgId){
		$('#blind_1').DataTable({
		  "paging": true,
	      "lengthChange": false,
	      "pageLength": 12,
	      "searching": false,
	      "ordering": false,
	      "info": true,
	      "sInfo": true,
	      "autoWidth": false,
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
              url: "../BlindSampleInfo/getBlindSampleInfo.html",
              dataSrc: "data",
              data: function (d) {
                  var param = {};
                  param.orgId = orgId;
                  param.str_start_Time = start_Time;
                  param.str_end_Time = end_Time;
                  param.tag = "1";
                  return param;//自定义需要传递的参数。
              }
          },
          "deferRender" : true,
	          "columns": [{
	          "data": "serialNumber"
	      }, {
	          "data": "sampeCode"
	      }, {
	          "data": "engineeringName"
	      }, {
	          "data": "Org_Name"
	      }, {
	          "data": "purpose"
	      }, {
	          "data": "constructionUnit"
	      }, {
	          "data": "testName"
	      }, {
	          "data": "testState"
	      }, {
	          "data": "isQualifiedSamp"
	      }, {
	          "data": "samplingPerson"
	      }, {
	          "data": "samplingDate"
	      }, {
	          "data": "materialName"
	      }, {
	          "data": "placeOrigin"
	      },{
	          "data": "remarks"
	      },{
	          "data": "details"
	      }],
	      "createdRow" : function(row, data, dataIndex) {
	          $(row).children('td').eq(1).attr('style', 'text-align: center;')
	          if(data.isQualifiedSamp == '不合格'){
	        	  $(row).children('td').eq(8).attr('style', 'color: red;')
	          }
	          if(data.testState == '未检'){
	        	  $(row).children('td').eq(7).attr('style', 'color: red;')
	          }
	      },
	      "columnDefs" : [ {
	          "targets" : [ 0 ],
	          "visible" : true,
	          "searchable" : false
	      }]
	});
		$('#blind_1_wrapper .row:first-child').css('display','none');
}

/*$("#ba1").on("click",function ()*/ 
function btncha(){
	var isCheck = true;
    var os = "";
    var oId;
   var constructionUnit = $("#constructionUnit").val();
   var engineeringName = $("#engineeringName").val();
/*	  var authority = document.getElementsByName("authority"); 
	    for(var i=0;i<authority.length;i++){
	    	var aut = authority[i]
	    	if($('#'+aut.id).attr('checked')){
	    		var orgId = $('#'+aut.id).val();
	    	}
	    	varisChecked = document.getElementById(aut.id).checked;
	     }*/
    var table = $('#blind_1').dataTable();
    table.fnDestroy();
	$('#blind_1').DataTable({
		  "paging": true,
	      "lengthChange": false,
	      "pageLength": 12,
	      "searching": false,
	      "ordering": false,
	      "info": true,
	      "sInfo": true,
	      "autoWidth": false,
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
            url: "../BlindSampleInfo/getBlindSampleInfo.html",
            dataSrc: "data",
            data: function (d) {
            	
            	orgId1 ="";
            	var authority = document.getElementsByName("authority");
            	for(var i=0;i<authority.length+1;i++){
        	    	var aut = authority[i]
        	    	if(aut!=null && aut!=""){
        	    		varisChecked = document.getElementById(aut.id).checked;       	    		
        	    		if(varisChecked){
        	    			isCheck = false
        	    		}
        	    		if(varisChecked) {
        	    			orgId1 = orgId1+$('#'+aut.id).val()+","
        	    		}
        	    	}
/*        	    	if(varisChecked) {
        	    		orgId1 = orgId1+$(this).attr('value')+","
        	    	}*/
        	    	if(authority.length==i && isCheck){
        	    		orgId1 = "9999999";
        	    	}
            	}  
                var param = {};
                param.orgId = orgId;
                param.str_start_Time =$("#start_Time").val()+" "+"00:"+"00:"+"00";
                param.str_end_Time = $("#end_Time").val()+" "+"23:"+"59:"+"59";
                param.constructionUnit = constructionUnit;
                param.engineeringName = engineeringName;
                param.oId = orgId1;
                param.tag = "1";
                return param;//自定义需要传递的参数。
            }
        },
        "deferRender" : true,
	          "columns": [{
	          "data": "serialNumber"
	      }, {
	          "data": "sampeCode"
	      }, {
	          "data": "engineeringName"
	      }, {
	          "data": "Org_Name"
	      }, {
	          "data": "purpose"
	      }, {
	          "data": "constructionUnit"
	      }, {
	          "data": "testName"
	      }, {
	          "data": "testState"
	      }, {
	          "data": "isQualifiedSamp"
	      }, {
	          "data": "samplingPerson"
	      }, {
	          "data": "samplingDate"
	      }, {
	          "data": "materialName"
	      }, {
	          "data": "placeOrigin"
	      },{
	          "data": "remarks"
	      },{
	          "data": "details"
	      }],
	      "createdRow" : function(row, data, dataIndex) {
	          $(row).children('td').eq(1).attr('style', 'text-align: center;')
	          if(data.isQualifiedSamp == '不合格'){
	        	  $(row).children('td').eq(8).attr('style', 'color: red;')
	          }
	          if(data.testState == '未检' ){
	        	  $(row).children('td').eq(7).attr('style', 'color: red;')
	          }
	      },
	      "columnDefs" : [ {
	          "targets" : [ 0 ],
	          "visible" : true,
	          "searchable" : false
          } ]
    });
	
	$('#blind_1_wrapper .row:first-child').css('display','none');
}
function getConstructionUnit(){
		$.ajax({
			type : "POST",
			url :"../BlindSampleInfo/getConstructionUnit.html",
			data : {"orgId":orgId},
			dataType : "json",
			success : function(data) {
				console.log(data);
				var html ="<option></option>"
				for(var i = 0; i < data.data.length; i++) {
					html += "<option>"+data.data[i].ConstructionUnit+"</option>"
				}
				$("#constructionUnit").empty();
				$("#constructionUnit").html(html);
			}
		});
}

function getEngineeringName(){
	$.ajax({
		type : "POST",
		url :"../BlindSampleInfo/getEngineeringName.html",
		data : {"orgId":orgId},
		dataType : "json",
		success : function(data) {
			console.log(data);
			var html =""
			for(var i = 0; i < data.data.length; i++) {
				html += "<option>"+data.data[i].EngineeringName+"</option>"
			}
			$("#browsers").empty();
			$("#browsers").html(html);
		}
	});
}



/*d.checkNode = function (id,i_parent_Id,flag,checked) {
	if(checked == true) {
		map[id] = id;
	} else {
		
		delete map[id];
	}
}*/

