var baseUrl = "";
var i_org_Id = "";
$(function() {
	
i_org_Id = orgid;
    

    $("input[type='radio']").each(function(){
		var cshow = $(this).attr("cshow");
		if(cshow == orgname) {
			$(this).attr('checked', 'checked');
		}
	});
	baseUrl = getContextPath();
	getList();
});
$.ajax({
    type: "post",
    url: "../Common/getUserName.action",
    data:{},
    async:false,
    dataType: "json",
    success: function (data) {
        $(".userid").html(data.str_name)
    }
})

function getList() {
	$('#list').DataTable(
			{
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
				"ajax" : baseUrl+ "/AsphaltProductionData/getAsphaltProductionData.action?i_org_Id="+i_org_Id,
				"deferRender" : true,
				"columns" : [/*
								 * { orderable: false, className:
								 * 'select-checkbox', targets: 0 },
								 */
					{"data" : "serialNumber"},
					{"data" : "str_collect_Time"},
					{"data" : "str_construction_Unit"},
					{"data" : "str_proj_Pos"},
					{"data" : "str_equipment_Name"},
					{"data" : "str_productInfo"},
					{"data" : "str_proportion_Code"},
					{"data" : "str_grade_Code"},
					{"data" : "d_temperature_Meter"},
					{"data" : "d_total_Weight"},
					{"data" : "d_no1_MeterValue"},
					{"data" : "d_no2_MeterValue"},
					{"data" : "d_no3_MeterValue"},
					{"data" : "d_no4_MeterValue"},
					{"data" : "d_no5_MeterValue"},
					{"data" : "d_no6_MeterValue"},
					{"data" : "d_coldP_MeterValue"},
					{"data" : "d_hotP_MeterValue"},
					{"data" : "d_asphalt_Meter"},
					{"data" : "d_admixture1"},
					{"data" : "d_admixture2"},
					{"data" : "str_analysis_Result"},
					],
				"createdRow" : function(row, data, dataIndex) {
					console.log(data);
					$(row).children("td").eq(2).addClass("scsj_xh");
					$(row).children("td").eq(3).addClass("sgdw_count");
					$(row).children("td").eq(4).addClass("gcbw_100");
					$(row).children("td").eq(5).addClass("gcbw_100");
					$(row).children("td").eq(6).addClass("cpmc_140");
					
					if(data.no1 == 0) {
						$(row).children("td").eq(10).addClass("warn2");
					}
					if(data.no2 == 0) {
						$(row).children("td").eq(11).addClass("warn2");
					}
					if(data.no3 == 0) {
						$(row).children("td").eq(12).addClass("warn2");
					}
					if(data.no4 == 0) {
						$(row).children("td").eq(13).addClass("warn2");
					}
					if(data.no5 == 0) {
						$(row).children("td").eq(14).addClass("warn2");
					}
					if(data.no6 == 0) {
						$(row).children("td").eq(15).addClass("warn2");
					}
					if(data.hotPower == 0) {
						$(row).children("td").eq(16).addClass("warn2");
					}
					if(data.coldPower == 0) {
						$(row).children("td").eq(17).addClass("warn2");
					}
					if(data.asphalt == 0) {
						$(row).children("td").eq(18).addClass("warn2");
					}
					if(data.admixture1 == 0) {
						$(row).children("td").eq(19).addClass("warn2");
					}
					if(data.admixture2 == 0) {
						$(row).children("td").eq(20).addClass("warn2");
					}
					if(data.str_proportion_Code==null){
						$(row).children("td").eq(6).html("<a href='javascript:void(0);'></a>");
					}else{
						$(row).children("td").eq(6).html("<a href='javascript:void(0);'>"+data.str_proportion_Code+"</a>");
					}
					$(row).children("td").eq(6).attr('onMouseOver',"Baywindow(this,\"mydiv2\","+data.i_id+");");
			        $(row).children("td").eq(6).attr('onMouseOut','hide2(this,\"mydiv2\")');
			            
					if(data.str_grade_Code==null){
						 $(row).children("td").eq(7).html("<a href='javascript:void(0);'></a>");
					}else{
						 $(row).children("td").eq(7).html("<a href='javascript:void(0);'>"+data.str_grade_Code+"</a>");
					}
		            $(row).children("td").eq(7).attr('onMouseOver',"show2(this,\"mydiv1\","+data.i_id+");");
		            $(row).children("td").eq(7).attr('onMouseOut','hide2(this,\"mydiv1\")');
				},
				"columnDefs" : [ {
					"targets" : [ 0 ],
					"visible" : true,
					"searchable" : false
				} ]
		});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

//飘窗
function Baywindow(obj,i_id){

	 
		var objDiv = $("#"+"mydiv1"+""); 
		$(objDiv).css("display","block"); 
		$(objDiv).css("left", event.clientX); 
		$(objDiv).css("top", event.clientY + 10); 
		$("#tabIds  tr:not(:first)").html("");
  $.ajax({
        type : "POST",
        url : baseUrl + "/AsphProportion/getAsphalt_Prod_ProportionById.action",
        data : {"i_id":i_id,"i_org_Id":i_org_Id,"pc":1},
        dataType : "json",
        success : function(data) {
       	 
        	$("#pcstr_proportion_Code").html(data.data.str_proportion_Code);
 			$("#pcstr_material_Name").html(data.data.str_material_Name+"_"+data.data.str_material_Model);
 			$("#Grade_Code").html(data.data.Grade_Code);
 			$("#pc_no1_SetValue").html(data.data.d_no1_SetValue);
 			$("#pc_no2_SetValue").html(data.data.d_no2_SetValue);
 			$("#pc_no3_SetValue").html(data.data.d_no3_SetValue);
 			$("#pc_no4_SetValue").html(data.data.d_no4_SetValue);
 			$("#pc_no5_SetValue").html(data.data.d_no5_SetValue);
 			$("#pc_no6_SetValue").html(data.data.d_no6_SetValue);
 			$("#pc_coldPowder_SetValue").html(data.data.d_coldPowder_SetValue);
 			$("#pc_hotPowder_SetValue").html(data.data.d_hotPowder_SetValue);
 			$("#pc_asphalt_SetValue").html(data.data.d_asphalt_SetValue);
 			$("#pc_admixture1_SetValue").html(data.data.d_admixture1_SetValue);
 			$("#pc_admixture2_SetValue").html(data.data.d_admixture2_SetValue);
			
			

 			 var i_product_Id = data.data.i_product_Id;
 			 if(i_product_Id=="null"){
 				 return;
 			 }
 				$.ajax({
 					type : "POST",
 					url : baseUrl + "/Common/getAsph_TargetProCodeById.action",
 					data : {"str_material_Type":0,
 				        "str_material_Mold":i_product_Id,
 				        "i_id":i_org_Id},
 				    dataType : "json",
 					success : function(data) {
 	
 						for(var i = 0; i < data.length; i++) {
 							
 			
 						    $("#mb_proportion_Code").html(data[i].str_proportion_Code);
 						}
 					
 					}
 				});
 				
 			

        }
    });



}


function getInfo(i_id) {
	$("#info span").html("");
	$("#loginModal").modal("show")
	var e = [];
	$(".modal").on("show.bs.modal",
	function() {
	    for (var s = 0; e.length > s; s++) e[s] && (e[s].modal("hide"), e[s] = null);
	    e.push($(this));
	    var o = $(this),
	    a = o.find(".modal-dialog"),
	    t = $('<div style="display:table; width:100%; height:100%;"></div>');
	    t.html('<div style="vertical-align:middle; display:table-cell;"></div>'),
	    t.children("div").html(a),
	    o.html(t)
	})
	
	$.ajax({
 		type : "POST",
 		url :  baseUrl + "/AsphaltProductionData/getAsphaltPropDataAnalysis.action",
 		data : {"i_id":i_id},
 		dataType : "json",
 		success : function(data) {
 			var asphalt_Production = data.asphalt_ProductionDTO;
 			var asphaltPropDataAnalysisList = data.asphaltPropDataAnalysisList;
 			$("#str_proportion_Code").html(asphalt_Production.str_proportion_Code);
 			$("#str_grade_Code").html(asphalt_Production.str_grade_Code);
 			$("#str_productInfo").html(asphalt_Production.str_productInfo);
 			$("#d_total_Weight").html(asphalt_Production.d_total_Weight);
 			var html="";
 			for(var i = 0; i < asphaltPropDataAnalysisList.length; i++) {
 				if(asphaltPropDataAnalysisList[i].str_data_Type == 0) {
					html += "<tr><td class='dise'>采集重量</td>"
                            +"<td>"+asphaltPropDataAnalysisList[i].d_no1+"</td>"
                            +"<td>"+asphaltPropDataAnalysisList[i].d_no2+"</td>"
                            +"<td>"+asphaltPropDataAnalysisList[i].d_no3+"</td>"
                            +"<td>"+asphaltPropDataAnalysisList[i].d_no4+"</td>"
                            +"<td>"+asphaltPropDataAnalysisList[i].d_no5+"</td>"
                            +"<td>"+asphaltPropDataAnalysisList[i].d_no6+"</td>"
                            +"<td>"+asphaltPropDataAnalysisList[i].d_hotPowder+"</td>"
                            +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder+"</td>"
                            +"<td>"+asphaltPropDataAnalysisList[i].d_asphalt+"</td>"
                            +"<td>"+asphaltPropDataAnalysisList[i].d_admixture1+"</td>"
                            +"<td>"+asphaltPropDataAnalysisList[i].d_admixture2+"</td></tr>";
 				}
 				if(asphaltPropDataAnalysisList[i].str_data_Type == 1) {
					html += "<tr><td class='dise'>采集配比</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no3+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no4+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no5+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no6+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_hotPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_asphalt+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture2+"</td></tr>";
 				}
 				if(asphaltPropDataAnalysisList[i].str_data_Type == 2) {
					html += "<tr><td class='dise'>生产配比</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no3+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no4+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no5+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no6+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_hotPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_asphalt+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture2+"</td></tr>";
 				}
				if(asphaltPropDataAnalysisList[i].str_data_Type == 3) {
					html += "<tr><td class='dise'>生产配比重量</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no3+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no4+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no5+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no6+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_hotPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_asphalt+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture2+"</td></tr>";
				}
				if(asphaltPropDataAnalysisList[i].str_data_Type == 4) {
					html += "<tr><td class='dise'>实际偏差</td>"
						+"<td>"+asphaltPropDataAnalysisList[i].d_no1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no3+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no4+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no5+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no6+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_hotPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_asphalt+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture2+"</td></tr>";
				}
 			}
 	        var asphaltGradDataAnalysisList = data.asphaltGradDataAnalysisList;
 	        var str = "[";
 	        for(var i = 0; i < asphaltGradDataAnalysisList.length; i++) {
 	        	var name="";
 	        	if(asphaltGradDataAnalysisList[i].str_curve_Type == 1) {
 	        		name ="采集数据曲线";
 	        	}
 	        	if(asphaltGradDataAnalysisList[i].str_curve_Type == 2) {
 	        		name ="生产配比重量曲线";
 	        	}
 	        	if(asphaltGradDataAnalysisList[i].str_curve_Type == 3) {
 	        		name ="级配上限";
 	        	}
 	        	if(asphaltGradDataAnalysisList[i].str_curve_Type == 4) {
 	        		name ="级配下限";
 	        		 
 	        	}
 	        	if(asphaltGradDataAnalysisList[i].str_curve_Type == 5) {
 	        		name ="级配中值";
 	        	}
 	        	str += "{name: '"+name+"',type:'line',"
     				+"data:[" +asphaltGradDataAnalysisList[i].d_a0_075+","
     				+asphaltGradDataAnalysisList[i].d_a0_15+","
     				+asphaltGradDataAnalysisList[i].d_a0_3+","
     				+asphaltGradDataAnalysisList[i].d_a0_6+","
     				+asphaltGradDataAnalysisList[i].d_a1_18+","
     				+asphaltGradDataAnalysisList[i].d_a2_36+","
     				+asphaltGradDataAnalysisList[i].d_a4_75+","
     				+asphaltGradDataAnalysisList[i].d_a9_5+","
     				+asphaltGradDataAnalysisList[i].d_a13_2+","
     				+asphaltGradDataAnalysisList[i].d_a16+","
     				+asphaltGradDataAnalysisList[i].d_a19+","
     				+asphaltGradDataAnalysisList[i].d_a26_5+","
     				+asphaltGradDataAnalysisList[i].d_a31_5+","
     				+asphaltGradDataAnalysisList[i].d_a37_5+","
     				+asphaltGradDataAnalysisList[i].d_a53+"]";
 	        	if(i + 1 < asphaltGradDataAnalysisList.length) {
 	        		str += "},"
 	        	} else {
 	        		str += "}]"
 	        	}
 	        }
 	        
 	        $("#tb").html(html);
 	        var Series = eval('(' + str + ')')
 	        option.series = Series; 
 	        var myChart = echarts.init(document.getElementById('chartmain'))
 	        myChart.setOption(option);
 	        var materialConsumption = data.materialConsumption;
 	        var xh = "";
 	        for(var i = 0; i < materialConsumption.length; i++) {
 	        	xh += "<tr><td>"+i+1+"</td><td>"+materialConsumption[i].Material+"</td><td>"+materialConsumption[i].MaterialConsumption.toFixed(2)+"</td></tr>"
 	        }
 	       $("#xh").html(xh);
		}
 	});
}


//飘窗
function Baywindow(obj,i_id){

	 
	var objDiv = $("#"+"mydiv2"+""); 
	$(objDiv).css("display","block"); 
	$(objDiv).css("left", event.clientX); 
	$(objDiv).css("top", event.clientY + 10); 
	$("#tabIds  tr:not(:first)").html("");
	
	
	$.ajax({
        type : "POST",
        url : baseUrl + "/AsphProportion/getAsphalt_Prod_ProportionById.action",
        data : {"i_id":i_id,"i_org_Id":i_org_Id},
        dataType : "json",
        success : function(data) {
        	$("#pcstr_proportion_Code").html(data.data.str_proportion_Code);
 			$("#pcstr_material_Name").html(data.data.str_material_Name+"_"+data.data.str_material_Model);
 			$("#Grade_Code").html(data.data.Grade_Code);
 			$("#pc_no1_SetValue").html(data.data.d_no1_SetValue);
 			$("#pc_no2_SetValue").html(data.data.d_no2_SetValue);
 			$("#pc_no3_SetValue").html(data.data.d_no3_SetValue);
 			$("#pc_no4_SetValue").html(data.data.d_no4_SetValue);
 			$("#pc_no5_SetValue").html(data.data.d_no5_SetValue);
 			$("#pc_no6_SetValue").html(data.data.d_no6_SetValue);
 			$("#pc_coldPowder_SetValue").html(data.data.d_coldPowder_SetValue);
 			$("#pc_hotPowder_SetValue").html(data.data.d_hotPowder_SetValue);
 			$("#pc_asphalt_SetValue").html(data.data.d_asphalt_SetValue);
 			$("#pc_admixture1_SetValue").html(data.data.d_admixture1_SetValue);
 			$("#pc_admixture2_SetValue").html(data.data.d_admixture2_SetValue);
			
			

 			 var i_product_Id = data.data.i_product_Id;
 			 if(i_product_Id=="null"){
 				 return;
 			 }
 				$.ajax({
 					type : "POST",
 					url : baseUrl + "/Common/getAsph_TargetProCodeById.action",
 					data : {"str_material_Type":0,
 				        "str_material_Mold":i_product_Id,
 				        "i_id":i_org_Id},
 				    dataType : "json",
 					success : function(data) {
 	
 						for(var i = 0; i < data.length; i++) {
 							
 			
 						    $("#mb_proportion_Code").html(data[i].str_proportion_Code);
 						}
 					
 					}
 				});
 				
 			

        }
    });


}


function hide(obj){
	 var objDiv = $("#"+"mydiv2"+""); 
		$(objDiv).css("display", "none"); 

}

function NewappendVal(obj, num) {
	var id = "#"+num.toString()+"_";
	for(var key in obj) {
		switch(key)
		{
		case "d_a53":
			$(id+"1").html(obj.d_a53)
			break;
		case "d_a37_5":
			$(id+"2").html(obj.d_a37_5)
			break;
		case "d_a31_5":
			$(id+"3").html(obj.d_a31_5)
			break;
		case "d_a26_5":
			$(id+"4").html(obj.d_a26_5)
			break;
		case "d_a19":
			$(id+"5").html(obj.d_a19)
			break;
		case "d_a16":
			$(id+"6").html(obj.d_a16)
			break;
	    case "d_a13_2":
	    	$(id+"7").html(obj.d_a13_2)
			break;
		case "d_a9_5":
			$(id+"8").html(obj.d_a9_5)
			break;
		case "d_a4_75":
			$(id+"9").html(obj.d_a4_75)
			break;
		case "d_a2_36":
			$(id+"10").html(obj.d_a2_36)
			break;
		case "d_a1_18":
			$(id+"11").html(obj.d_a1_18)
			break;
		case "d_a0_6":
			$(id+"12").html(obj.d_a0_6)
			break;
		case "d_a0_3":
			$(id+"13").html(obj.d_a0_3)
			break;
		case "d_a0_15":
			$(id+"14").html(obj.d_a0_15)
			break;
		default:
			$(id+"15").html(obj.d_a0_075)
			break;
		}
	}
}
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
	    url :  "../AsphaltGrading/getAsphalt_GradDetailedById.action",
//	    data: JSON.stringify(param),
//	    dataType: "json",
	    success: function (data) {
	    	if(data!=null){
	    		
	    		 if(data.length>0){
	    			 for (var i = 0; i < data.length; i++) {
	    				 
	    				 $("#yjph").html(data[i].str_grade_Code);
	    	    		 $("#cpmc").html(data[i].str_proportion_Code);
	    				 switch(data[i].str_warehouse_Num)
	    					{
	    					case "N1":
	    						NewappendVal(data[i], "101");
	    						break;
	    					case "N2":
	    						NewappendVal(data[i], "102");
	    						break;
	    					case "N3":
	    						NewappendVal(data[i], "103");
	    						break;
	    					case "N4":
	    						NewappendVal(data[i], "104");
	    						break;
	    					case "N5":
	    						NewappendVal(data[i], "105");
	    						break;
	    				    case "N6":
	    				    	NewappendVal(data[i], "106");
	    				    	break;
	    					case "NH":
	    						NewappendVal(data[i], "107");
	    						break;
	    					case "NC":
	    						NewappendVal(data[i], "108");
	    						break;
	    					case "Max":
	    						NewappendVal(data[i], "109");
	    						break;
	    					default:
	    						NewappendVal(data[i], "110");
	    						break;
	    					}
	    				 
					}
	    		 }
	    	}
	    }
	});
} 
function hide2(obj,id) { 
	var objDiv = $("#"+id+""); 
	$(objDiv).css("display", "none"); 
} 
d.checkNode = function (id,i_parent_Id,flag,checked) {

	if(checked == true) {
		i_org_Id = id;
	} else {
		i_org_Id = "";
	}
	
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
	var table = $('#list').dataTable();
	table.fnDestroy();
    
    $('#list').DataTable(
			{
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
			        url: baseUrl+ "/AsphaltProductionData/getAsphaltProductionData.action",
			        dataSrc: "data",
			        data: function () {
			            var param = {};
			            param.i_org_Id = i_org_Id;			            
			            param.str_material_Name = $("#material_Name").val();
//			            param.str_material_Model = $("#material_Model").val();
			            param.str_startTime = $("#str_startTime").val();
			            param.str_endTime = $("#str_endTime").val();
			            param.str_equipment_Name = $("#equipment_Name").val();
			            /*param.str_analysis_Result = $("#analysis_Result").val(); str_productInfo*/
			            param.str_construction_Unit = $("#construction_Unit").val();
			            param.str_proj_Pos = $("#proj_Pos").val();
			            console.log(param);
			            return param;
			        }
			    },
				"deferRender" : true,
				"columns" : [/*
								 * { orderable: false, className:
								 * 'select-checkbox', targets: 0 },
								 */
					{"data" : "serialNumber"},
					{"data" : "str_collect_Time"},
					{"data" : "str_construction_Unit"},
					{"data" : "str_proj_Pos"},
					{"data" : "str_equipment_Name"},
					{"data" : "str_productInfo"},
					{"data" : "str_proportion_Code"},
					{"data" : "str_grade_Code"},
					{"data" : "d_temperature_Meter"},
					{"data" : "d_total_Weight"},
					{"data" : "d_no1_MeterValue"},
					{"data" : "d_no2_MeterValue"},
					{"data" : "d_no3_MeterValue"},
					{"data" : "d_no4_MeterValue"},
					{"data" : "d_no5_MeterValue"},
					{"data" : "d_no6_MeterValue"},
					{"data" : "d_coldP_MeterValue"},
					{"data" : "d_hotP_MeterValue"},
					{"data" : "d_asphalt_Meter"},
					{"data" : "d_admixture1"},
					{"data" : "d_admixture2"},
					{"data" : "str_analysis_Result"},
					],
				"createdRow" : function(row, data, dataIndex) {
					// $(row).children("td").eq(1).attr("onclick",
					// "getDetailed('" + data.i_id + "');");
					// $(row).children("td").eq(0).addClass("scgl_xz");
					$(row).children("td").eq(2).addClass("scsj_xh");
					$(row).children("td").eq(3).addClass("sgdw_count");
					$(row).children("td").eq(4).addClass("gcbw_100");
					$(row).children("td").eq(5).addClass("gcbw_100");
					$(row).children("td").eq(6).addClass("cpmc_140");
					if(data.no1 == 0) {
						$(row).children("td").eq(10).addClass("warn2");
					}
					if(data.no2 == 0) {
						$(row).children("td").eq(11).addClass("warn2");
					}
					if(data.no3 == 0) {
						$(row).children("td").eq(12).addClass("warn2");
					}
					if(data.no4 == 0) {
						$(row).children("td").eq(13).addClass("warn2");
					}
					if(data.no5 == 0) {
						$(row).children("td").eq(14).addClass("warn2");
					}
					if(data.no6 == 0) {
						$(row).children("td").eq(15).addClass("warn2");
					}
					if(data.hotPower == 0) {
						$(row).children("td").eq(16).addClass("warn2");
					}
					if(data.coldPower == 0) {
						$(row).children("td").eq(17).addClass("warn2");
					}
					if(data.asphalt == 0) {
						$(row).children("td").eq(18).addClass("warn2");
					}
					if(data.admixture1 == 0) {
						$(row).children("td").eq(19).addClass("warn2");
					}
					if(data.admixture2 == 0) {
						$(row).children("td").eq(20).addClass("warn2");
					}
					
					if(data.str_proportion_Code==null){
						$(row).children("td").eq(6).html("<a href='javascript:void(0);'></a>");
					}else{
						$(row).children("td").eq(6).html("<a href='javascript:void(0);'>"+data.str_proportion_Code+"</a>");
					}
					$(row).children("td").eq(6).attr('onMouseOver',"show2(this,\"mydiv1\","+data.i_id+");");
			        $(row).children("td").eq(6).attr('onMouseOut','hide2(this,\"mydiv1\")');
			            
					if(data.str_grade_Code==null){
						 $(row).children("td").eq(7).html("<a href='javascript:void(0);'></a>");
					}else{
						 $(row).children("td").eq(7).html("<a href='javascript:void(0);'>"+data.str_grade_Code+"</a>");
					}
		            $(row).children("td").eq(7).attr('onMouseOver',"show2(this,\"mydiv1\","+data.i_id+");");
		            $(row).children("td").eq(7).attr('onMouseOut','hide2(this,\"mydiv1\")');
				},
				"columnDefs" : [ {
					"targets" : [ 0 ],
					"visible" : true,
					"searchable" : false
				} ]
		});
    $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}