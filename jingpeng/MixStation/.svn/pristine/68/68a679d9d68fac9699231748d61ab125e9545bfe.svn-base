var baseUrl = "";
var i_org_Id = "";
$(function() {
	
	
	$.ajax({
	        type: "post",
	        url: "../Common/getUserName.html",
	        data:{},
	        async:false,
	        dataType: "json",
	        success: function (data) {
	            $(".userid").html(data.str_user_Name)
	        }
	 })
	
	  i_org_Id = orgid;
    
    $("input[type='radio']").each(function(){
		var cshow = $(this).attr("cshow");
		console.log(orgid);
		console.log(orgname);
		if(cshow == orgname) {
			$(this).attr('checked', 'checked');
		}
	});
	baseUrl = getContextPath();  
	
	//处理柱状图数据参数  t
    var param = {};
    param.i_org_Id = i_org_Id;
    
    
	getList();
	getBar(param);
});

function getList() {
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
		"ajax" : baseUrl + "/AsphaltProduceStatistic/getAsphaltProduceStatistic.html?i_org_Id="+i_org_Id,
		"deferRender" : true,
		"columns" : [ 
			{"data" : "serialNumber"},
			{"data" : "collect_Time"},
			{"data" : "str_productInfo"},
			{"data" : "construction_Unit"},
			{"data" : "proj_Pos"},
			{"data" : "total"},
			{"data" : "standard"},
			{"data" : "unqualified"},
			{"data" : "total_Weight"},
			{"data" : "sumasphalt_Meter"}
			],
		"createdRow" : function(row, data, dataIndex) {
			/*$(row).children("td").eq(1).attr("onmouseover", "getDetailed('"+data.i_id+"');")
			$(row).children("td").eq(1).addClass("mbpb_xg");*/
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		} ]
	});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

function getBar(param) {
	
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/AsphaltProduceStatistic/getBar.html", 
		data :  param,
		dataType : "json",
		success : function(data) {
			var str = "{"
					+"name:'',"
					+"type:'bar',"
					+"barWidth: '40%',"
					+"data:["+data.total+", "+data.standard+", "+data.unqualified+"]"
					+"}";
			var Series = eval('(' + str + ')');
			option.series = Series;
			var myChart = echarts.init(document.getElementById('chartmain'));
		    myChart.setOption(option);
		}
	});
}

d.checkNode = function (id,i_parent_Id,flag,checked) {
	
	if(checked == true) {
		i_org_Id = id;
	} else {
		i_org_Id = "";
	}
	

	
	/* 查找nodes 名称  tongn 2018.6.22*/
	var objList = d.aNodes;
	for(var i=0,l=objList.length;i<l;i++){  
		
		if(objList[i].id == id){
			
			$("#locationText").text(objList[i].cshow);
		}
		  
	}  
	
}

function search() {
	
	var param = {};
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
		"ajax" : {
            type: "post",
            url: baseUrl + "/AsphaltProduceStatistic/getAsphaltProduceStatistic.html",
            dataSrc: "data",
            data: function (d) {
                
                param.i_org_Id = i_org_Id;
                param.str_startTime = $("#str_startTime").val();
                param.str_endTime = $("#str_endTime").val();
                param.str_material_Name = $("#material_Name").val();
                param.str_material_Model = $("#material_Model").val();
                param.str_proj_Pos = $("#proj_Pos").val();
                param.str_construction_Unit = $("#construction_Unit").val();
                return param;//自定义需要传递的参数。
            }
        },
		"deferRender" : true,
		"columns" : [ 
			{"data" : "serialNumber"},
			{"data" : "collect_Time"},
			{"data" : "str_productInfo"},
			{"data" : "construction_Unit"},
			{"data" : "proj_Pos"},
			{"data" : "total"},
			{"data" : "standard"},
			{"data" : "unqualified"},
			{"data" : "total_Weight"},
			{"data" : "sumasphalt_Meter"}
			],
		"createdRow" : function(row, data, dataIndex) {
			/*$(row).children("td").eq(1).attr("onmouseover", "getDetailed('"+data.i_id+"');")
			$(row).children("td").eq(1).addClass("mbpb_xg");*/
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		} ]
	});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	/*	option.series.data.splice(0,option.series.data.length);
	$.ajax({
		type : "POST",
		url : baseUrl + "/AsphaltProduceStatistic/getBar.html", 
		data :  {
			'i_power_Org_Id' : i_org_Id,
		    'str_startTime' : $("#str_startTime").val(),
		    'str_endTime' : $("#str_endTime").val(),
		    'str_material_Name' : $("#material_Name").val(),
		    'str_material_Model' : $("#material_Model").val(),
		    'str_proj_Pos' : $("#proj_Pos").val(),
		    'str_construction_Unit' : $("#construction_Unit").val(),
		},
		dataType : "json",
		success : function(data) {
			if(data!=null){

//				$("#chartmain").empty();
				var str = "{"
						+"name:'',"
						+"type:'bar',"
						+"barWidth: '60%',"
						+"data:["+data.total+", "+data.standard+", "+data.unqualified+"]"
						+"}";
				var Series = eval('(' + str + ')');
				option.series = Series;
				var myChart = echarts.init(document.getElementById('chartmain'));
			    myChart.setOption(option);
			
			}else{
				
			}
		}
	});*/
	
	getBar(param);
}