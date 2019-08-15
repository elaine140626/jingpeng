var i_org_Id = "";
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

    i_org_Id = orgid;
    

    $("input[type='radio']").each(function(){
		var cshow = $(this).attr("cshow");
		if(cshow == orgname) {
			$(this).attr('checked', 'checked');
		}
	});
    getList();


})

function getC_ProductiondataSelect(){
	
	var table = $('#cement_ProductionDatas').dataTable();
	table.fnDestroy();
	$('#cement_ProductionDatas').DataTable({
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
            url: "../cement_Production/getCement_ProductionDatas",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.i_org_Id = i_org_Id;
                param.productName = $("#productName").val();
/*                param.productModel = $("#productModel").val();
*/                param.str_beginDate = $("#str_beginDate").val();
                param.str_endDate = $("#str_endDate").val();
                param.mixingEquipmentName = $("#mixingEquipmentName").val();
                param.analysisResults = $("#analysisResults").val();
                return param;//自定义需要传递的参数。
            }
        },
        "columns" : [{
            "data": "serialNumber"
        }, {
            "data": "str_Equipment_Name"
        }, {
            "data": "str_Prop_Code"
        }, {
            "data": "str_collect_Date"
        }, {
            "data": "i_mix_Time"
        }, {
            "data": "d_weight_Cement1"
        }, {
            "data": "d_weight_Cement2"
        }, {
            "data": "d_weight_Cement3"
        }, {
            "data": "d_weight_Cement4"
        }, {
            "data": "d_weight_Aggregate1"
        }, {
            "data": "d_weight_Aggregate2"
        }, {
            "data": "d_weight_Aggregate3"
        }, {
            "data": "d_weight_Aggregate4"
        }, {
            "data": "d_weight_Aggregate5"
        }, {
            "data": "d_weight_Aggregate6"
        }, {
            "data": "d_weight_Water"
        }, {
            "data": "d_weight_Admixture1"
        }, {
            "data": "d_weight_Admixture2"
        }, {
            "data": "d_total_Weight"
        }, {
            "data": "str_proj_Pos"
        }, {
            "data": "str_watering_Site"
        }, {
            "data": "i_analysis_Results"
        }],
        "createdRow" : function(row, data, dataIndex) {

            var c = parseInt(data.HeGePanShu) * 100 /parseInt(data.TotalCount)
            var bfb = c.toFixed(2) +'%'
            $('td:eq(7)', row).html(bfb)
            $(row).children('td').eq(1).attr('style', 'text-align: center;')
            
            if(data.str_Prop_Code==null){
        		$(row).children("td").eq(2).html("");
        	}else{
        		$(row).children("td").eq(2).html("<a href='javascript:void(0);'>"+data.str_Prop_Code+"</a>");
        	}
            $(row).children("td").eq(2).attr('onMouseOver',"show2(this,\"mydiv1\","+data.i_id+");");
            $(row).children("td").eq(2).attr('onMouseOut','hide(this,\"mydiv1\")');
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

function getList() {

    $("#cement_ProductionDatas").DataTable({
        "paging": true,
        "lengthChange": false,
        "pageLength": 14,
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
            url: "../cement_Production/getCement_ProductionDatas?i_org_Id="+i_org_Id,
            dataSrc: "data",
            data: function (d) {
                var param = {};

                return param;//自定义需要传递的参数。
            }},
        "deferRender": true,
        "createdRow": function (row, data, dataIndex) {
        	if(data.str_Prop_Code==null){
        		$(row).children("td").eq(2).html("");
        	}else{
        		$(row).children("td").eq(2).html("<a href='javascript:void(0);'>"+data.str_Prop_Code+"</a>");
        	}
           var s = (data.i_analysis_Results ==1) ?  ("数据正常") :(data.i_analysis_Results == 2 ? "拌合时间不合格":"配比不合格")
            $('td:eq(21)', row).html(s)

            if(data.i_analysis_Results_Aggregate != 1){
                $('td:eq(9)', row).attr("class","warn2")
                $('td:eq(10)', row).attr("class","warn2")
                $('td:eq(11)', row).attr("class","warn2")
                $('td:eq(12)', row).attr("class","warn2")
                $('td:eq(13)', row).attr("class","warn2")
                $('td:eq(14)', row).attr("class","warn2")
            }
            if(data.i_analysis_Results_Cement !=1 ){
                $('td:eq(5)', row).attr("class","warn2")
                $('td:eq(6)', row).attr("class","warn2")
                $('td:eq(7)', row).attr("class","warn2")
                $('td:eq(8)', row).attr("class","warn2")

            }


            $(row).children('td').eq(1).attr('style', 'text-align: center;')
			//$(row).children("td").eq(2).html("<a href='javascript:void(0);' id='t1' onMouseOver='show2(this,\"mydiv1\","+data.i_id+");' onMouseOut='hide(this,\"mydiv1\")'>"+data.str_Prop_Code+"</a>");
            $(row).children("td").eq(2).attr('onMouseOver',"show2(this,\"mydiv1\","+data.i_id+");");
            $(row).children("td").eq(2).attr('onMouseOut','hide(this,\"mydiv1\")');
        },
        "columns": [{
            "data": "serialNumber"
        }, {
            "data": "str_Equipment_Name"
        }, {
            "data": "str_Prop_Code"
        }, {
            "data": "str_collect_Date"
        }, {
            "data": "i_mix_Time"
        }, {
            "data": "d_weight_Cement1"
        }, {
            "data": "d_weight_Cement2"
        }, {
            "data": "d_weight_Cement3"
        }, {
            "data": "d_weight_Cement4"
        }, {
            "data": "d_weight_Aggregate1"
        }, {
            "data": "d_weight_Aggregate2"
        }, {
            "data": "d_weight_Aggregate3"
        }, {
            "data": "d_weight_Aggregate4"
        }, {
            "data": "d_weight_Aggregate5"
        }, {
            "data": "d_weight_Aggregate6"
        }, {
            "data": "d_weight_Water"
        }, {
            "data": "d_weight_Admixture1"
        }, {
            "data": "d_weight_Admixture2"
        }, {
            "data": "d_total_Weight"
        }, {
            "data": "str_proj_Pos"
        }, {
            "data": "str_watering_Site"
        }, {
            "data": "i_analysis_Results"
        }],

        "columnDefs": [{
            "targets": [0],
            "visible": true,
            "searchable": false
        }]
    });
    $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

//飘窗

function show2(obj,id,ids) { 
	var objDiv = $("#"+id+""); 
	$(objDiv).css("display","block"); 
	$(objDiv).css("left", event.clientX); 
	$(objDiv).css("top", event.clientY + 10); 
	
	var param = {};
	param.i_id = ids;
	$.ajax({
	    type: "post",
//	    contentType: 'application/json;charset=UTF-8',
	    data : param,
		dataType : "json",
	    url :  "../CementConstructionProp/getC_ConstructionDeatlByid.html",
//	    data: JSON.stringify(param),
//	    dataType: "json",
	    success: function (data) {
	    	if(data!=null){
	    		/* $("#nr_l").html(data.data.cc.str_prop_Code);
	    		 $("#xh_l").html(data.data.cc.str_material_Name+"-"+data.data.cc.str_material_Model);*/
	    		 var html = "";
	    		 if(data.data.cd.length>0){
	    			 for (var i = 0; i < data.data.cd.length; i++) {
	    				 html+="<tr> <td>"+(i+1)+"</td>" +
	    				 		"<td>"+data.data.cd[i].Material_Name+"/"+data.data.cd[i].Material_Model+"</td>" +
	    				 		"<td align='center'>"+data.data.cd[i].Theory_Weight+"</td>" +
	    				 		"<td align='center'>"+data.data.cd[i].Construction_Weight+"</td>" +
	    				 		"<td align='center'>"+data.data.cd[i].Manufacturer+"</td>" +
	    				 		"<td align='center'>"+data.data.cd[i].Material_Origin+"</td> </tr>"
	    				 
	    			 }
	    			 $("#nr_l_new").html(html);
	    		 }
	    	}
	    }
	});
} 

function hide(obj,id) { 
	var objDiv = $("#"+id+""); 
	$(objDiv).css("display", "none"); 
} 


function getcollect_Date(i_id) {
  
    $("#cemdtel").DataTable({
        "paging": true,
        "lengthChange": false,
        "pageLength": 14,
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

        },

        "ajax" : {
            type: "post",
            url: "../Cement_ProductionDetailed/getCement_ProductionDetaileds.html",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.i_cemment_Id = i_id;
                param.str_beginDate = $("#str_beginDate").val();
                param.str_endDate = $("#str_endDate").val();

                return param;//自定义需要传递的参数。
            }},
        "deferRender": true,
        "createdRow": function (row, data, dataIndex) {
        	
            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columns": [{
            "data": "serialNumber"
        }, {
            "data": "str_materialInfo"
        }, {
            "data": "d_cons_Prop_Weight"
        }, {
            "data": "d_collection_Weight"
        }, {
            "data": "d_actual_Proportion"
        }],

        "columnDefs": [{
            "targets": [0],
            "visible": true,
            "searchable": false
        }]
    });

    $("#loginModal").modal("show")
   $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出 
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