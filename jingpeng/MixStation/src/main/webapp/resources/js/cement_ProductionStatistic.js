var i_org_Id = ""
$(function () {
	$.ajax({
        type: "post",
        url: "../cement_Production/getcementdataValue",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
            $(".userid").html(data.userName)
        }
    });
    window.totledata =[]
    window.hegedata =[]
    window.buhegedata = []
    
	console.log(i_org_Id)
    i_org_Id = orgid;
  
    
    $("input[type='radio']").each(function(){
		var cshow = $(this).attr("cshow");
		if(cshow == orgname) {
			$(this).attr('checked', 'checked');
		}
	});
    
    /* 处理带入图表问题 tongn 2018.6.26*/
    var param = {};
    param.i_org_Id = i_org_Id;
 
    
    getList();
	getBar(param);

})

 function getList() {
    	
        $("#Statistics").DataTable({
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
            "ajax" : "../cement_Production/getcement_ProductionStatistics?i_org_Id="+i_org_Id,
            "deferRender" : true,
            "columns" : [ {
                "data" : "serialNumber"
            }, {
                "data" : "str_collect_Date"
            }, {
                "data" : "str_product"
            }, {
                "data" : "str_construction_Unit"
            }, {
                "data" : "str_proj_Pos"
            }, {
                "data" : "i_total_number"
            }, {
                "data" : "i_qualified_number"
            }, {
                "data" : "i_failures"
            },{
                "data" : "cementWeight"
            },{
                "data" : "d_total_Weight"
            } ],
            "createdRow" : function(row, data, dataIndex) {

                var tol = data.d_weight_Cement1 + data.d_weight_Cement2 + data.d_weight_Cement3 +data.d_weight_Cement4

                $('td:eq(8)', row).html(tol)
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
    

function getCement_ProductionStatisticSelect(){
	
	var param = {};
	var table = $('#Statistics').dataTable();
	table.fnDestroy();
	$('#Statistics').DataTable({
        "paging" : false,
        "lengthChange" : false,
        "bLengthChange": false,
        "pageLength" : 14,
        "searching" : false,
        "ordering" : false,
        "info" : true,
        "sInfo" : true,
        "autoWidth" : false,

        "ajax" : {
            type: "post",
            url : baseUrl + "/cement_Production/getcement_ProductionStatistics.html", 
            dataSrc: "data",
            data: function (d) {
                
                param.i_org_Id = i_org_Id;
                param.productName = $("#productName").val();
                param.productModel = $("#productModel").val();
                param.str_beginDate = $("#str_beginDate").val();
                param.str_endDate = $("#str_endDate").val();
                param.constructionUnit = $("#constructionUnit").val();
                param.engineeringSite = $("#engineeringSite").val();
                return param;//自定义需要传递的参数。
            }
        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "str_collect_Date"
        }, {
            "data" : "str_product"
        }, {
            "data" : "str_construction_Unit"
        }, {
            "data" : "str_proj_Pos"
        }, {
            "data" : "i_total_number"
        }, {
            "data" : "i_qualified_number"
        }, {
            "data" : "i_failures"
        },{
            "data" : "cementWeight"
        },{
            "data" : "d_total_Weight"
        } ],
        "createdRow" : function(row, data, dataIndex) {

        	   var tol = data.d_weight_Cement1 + data.d_weight_Cement2 + data.d_weight_Cement3 +data.d_weight_Cement4

               $('td:eq(8)', row).html(tol)
               $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	/* 处理水泥图表问题 tongn 2018.6.25*/
	getBar(param);
}

function getBar(param) {
	
    // 清空图表
	//$("#chartmain").empty();
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/cement_Production/getBar.html", 
		data :  param,
		dataType : "json",
		success : function(data) {
			console.log(data);
			var str = "{"
					+"name:'',"
					+"type:'bar',"
					+"barWidth: '40%',"
					+"data:["+data.i_total_number+", "+data.i_qualified_number+", "+data.i_failures+"]"
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
