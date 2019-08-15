var baseUrl = "";
var i_org_Id = "";
//修改id没有带过来  tongn 2018.7.3
var i_update_id="";
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

    $.ajax({
        type: "post",
        url: "../AsphProportion/getAsphalt_ProdValue",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
            $(".userid").html(data.userName)
        }
    });

});

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
				"ajax" : baseUrl+ "/AsphProportion/getAsphalt_Prod_Proportion.html?i_org_Id="+i_org_Id,
				"deferRender" : true,
				"columns" : [ {
					"data" : "serialNumber"
				}, {
					"data" : "str_proportion_Code"
				}, {
					"data" : "str_grade_Code"//
				},{
					"data" : "str_Mproportion_Code"//
				},{
					"data" : "materNameAndModel"
				}, {
					"data" : "str_operator"
				}, {
					"data" : "str_create_Date" //str_modify_Date改为str_create_Date
				}, {
					"data" : "str_remarks"
				}, {
					"data" : "operate"
				} ],
				"createdRow" : function(row, data, dataIndex) {
//					$(row).children("td").eq(1).attr("onmouseover",
//							"getDetailed('" + data.i_id + "');")
//					$(row).children("td").eq(1).addClass("mbpb_xg");
					//$(row).children("td").eq(1).html("<a href='javascript:void(0);' id='t1' onMouseOver='show2(this,\"mydiv1\","+data.i_id+");' onMouseOut='hide(this,\"mydiv1\")'>"+data.str_proportion_Code+"</a>");
					$(row).children("td").eq(1).html("<a href='javascript:void(0);'>"+data.str_proportion_Code+"</a>");
		            $(row).children("td").eq(1).attr('onMouseOver',"Baywindow(this,"+data.i_id+");");
		            $(row).children("td").eq(1).attr('onMouseOut','hide(this,\"mydiv1\")');
		            
		            $(row).children("td").eq(2).html("<a href='javascript:void(0);'>"+data.str_grade_Code+"</a>");
		            $(row).children("td").eq(2).attr('onMouseOver',"show3(this,\"mydiv2\","+data.i_grad_Id+");");
		            $(row).children("td").eq(2).attr('onMouseOut','hide(this,\"mydiv2\")');
		            
		            $(row).children("td").eq(3).html("<a href='javascript:void(0);'>"+data.str_Mproportion_Code+"</a>");
		            $(row).children("td").eq(3).attr('onMouseOver',"show4(this,\"mydiv3\","+data.i_targ_PropId+");");
		            $(row).children("td").eq(3).attr('onMouseOut','hide(this,\"mydiv3\")');
				},
				"columnDefs" : [ {
					"targets" : [ 0 ],
					"visible" : true,
					"searchable" : false
				} ]
		});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}
var upDateName = "";
function getUpdateMaterialName(str_material_Mold,obj){
	$("#updatestr_material_Name").empty();
	$("#updatestr_material_Name").html(upDateName);
}

function clearModel(tag){
	if(tag == 1 || tag == "1") {
		$("#str_material_Model").empty();
		$("#str_mb_proportion_Code").empty();
		$("#str_grade_Code").empty();
	} else {
		$("#updatestr_material_Model").empty();
		$("#updatestr_mb_proportion_Code").empty();
		$("#updatestr_grade_Code").empty();
	}
}

function clearCode(tag) {
	if(tag == 1 || tag == "1") {
		$("#str_mb_proportion_Code").empty();
		$("#str_grade_Code").empty();
	} else {
		$("#updatestr_mb_proportion_Code").empty();
		$("#updatestr_grade_Code").empty();
	}
}

function getMaterialModel(str_material_Mold, tag, obj) {
	var str_material_Name;
	if(tag == 1 || tag == "1") {
		str_material_Name = $("#str_material_Name").find("option:selected").text();
	} else {
		str_material_Name = $("#updatestr_material_Name").find("option:selected").text();
	}
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getMaterialModel.html",
		data : {"str_material_Type":0,
			  "i_org_Id":i_org_Id,
		        "str_material_Mold":str_material_Mold,
		        "str_material_Name":str_material_Name},
		dataType : "json",
		success : function(data) {
			var html = "";
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].i_id+"'>"+data[i].str_material_Model+"</option>";
			}
			obj.empty();
			obj.html(html);
		}
	});
}


function show2(obj,id,ids) { 
	var objDiv = $("#"+id+""); 
	$(objDiv).css("display","block"); 
	$(objDiv).css("left", event.clientX); 
	$(objDiv).css("top", event.clientY + 10); 
	$("#tabIds  tr:not(:first)").html("");
	var param = {};
	param.i_id = ids;
//	$.ajax({
//	    type: "post",
//	    data : param,
//		dataType : "json",
//	    url :  "../AsphaltGrading/getAsphalt_GradDetailedById.html",
//	    success: function (data) {
//	    	
//	    	if(data!=null){
//	    		
//	    		 if(data.length>0){
//	    			
//	    			 for (var i = 0; i < data.length; i++) {
//	    				 $("#yjph").html(data[i].str_grade_Code);
//			    		 $("#cpmc").html(data[i].str_material_Name);
//	    				 switch(data[i].str_warehouse_Num)
//	    					{
//	    					case "N1":
//	    						NewappendVal(data[i], "101");
//	    						break;
//	    					case "N2":
//	    						NewappendVal(data[i], "102");
//	    						break;
//	    					case "N3":
//	    						NewappendVal(data[i], "103");
//	    						break;
//	    					case "N4":
//	    						NewappendVal(data[i], "104");
//	    						break;
//	    					case "N5":
//	    						NewappendVal(data[i], "105");
//	    						break;
//	    				    case "N6":
//	    				    	NewappendVal(data[i], "106");
//	    				    	break;
//	    					case "NH":
//	    						NewappendVal(data[i], "107");
//	    						break;
//	    					case "NC":
//	    						NewappendVal(data[i], "108");
//	    						break;
//	    					case "Max":
//	    						NewappendVal(data[i], "109");
//	    						break;
//	    					default:
//	    						NewappendVal(data[i], "110");
//	    						break;
//	    					}
//	    				 
//					}
//	    		 }
//	    	}
//	    }
//	});
} 

function hide(obj,id) { 
	var objDiv = $("#"+id+""); 
	$(objDiv).css("display", "none"); 
} 

//function getProportionCode(str_material_Mold,tag,obj) {
//	var i_product_Id;
//	if(tag == 1 || tag == "1") {
//		i_product_Id = $("#str_material_Model").val();
//	} else {
//		i_product_Id = $("#updatestr_material_Model").val();
//	}
//	$.ajax({
//		type : "POST",
//		url : baseUrl + "/Common/getAsph_TargetProCodeById.html",
//		data : {"str_material_Type":0,
//		        "str_material_Mold":str_material_Mold,
//		        "i_id":i_product_Id},
//		dataType : "json",
//		success : function(data) {
//			var html = "";
//			for(var i = 0; i < data.length; i++) {
//				html += "<option value='"+data[i].i_id+"'>"+data[i].str_proportion_Code+"</option>";
//			}
//			obj.empty();
//			obj.html(html);
//		}
//	});
//}
//增加
//function getAddProportionCode(str_material_Mold,tag,obj) {}
//修改
function getUpdateProportionCode(str_material_Mold,tag,obj) {
	
	 var i_product_Id = $("#updatestr_material_Model").val();
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getAsph_TargetProCodeById.html",
		data : {"str_material_Type":0,
		        "str_material_Mold":i_product_Id,
		        "i_id":i_org_Id},
		dataType : "json",
		success : function(data) {
			var html = "";
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].i_id+"'>"+data[i].str_proportion_Code+"</option>";
			}
			obj.empty();
			obj.html(html);
		}
	});
}

//function getGradeCode(str_material_Mold,tag,obj) {
//	var i_product_Id;
//	if(tag == 1 || tag == "1") {
//		i_product_Id = $("#str_material_Model").val();
//	} else {
//		i_product_Id = $("#updatestr_material_Model").val();
//	}
//	$.ajax({
//		type : "POST",
//		url : baseUrl + "/Common/getGradeCodeById.html",
//		data : {"str_material_Type":0,
//				"i_org_Id":i_org_Id,
//		        "str_material_Mold":str_material_Mold,
//		        "i_id":i_product_Id},
//		dataType : "json",
//		success : function(data) {
//			var html = "";
//			for(var i = 0; i < data.length; i++) {
//				html += "<option value='"+data[i].i_id+"'>"+data[i].str_grade_Code+"</option>";
//			}
//			obj.empty();
//			obj.html(html);
//		}
//	});
//}

function getOutModel(){
	getJpbh();
	 var i_product_Id = $("#str_material_Model").val();
	 if(i_product_Id=="null"){
		 return;
	 }
		$.ajax({
			type : "POST",
			url : baseUrl + "/Common/getAsph_TargetProCodeById.html",
			data : {"str_material_Type":0,
		        "str_material_Mold":i_product_Id,
		        "i_id":i_org_Id},
		    dataType : "json",
			success : function(data) {
				var html =""
				for(var i = 0; i < data.length; i++) {
					html += "<option value='"+data[i].i_id+"'>"+data[i].str_proportion_Code+"</option>"
				}
				$("#str_grade_Code").empty();
				$("#str_mb_proportion_Code").empty();
				$("#str_mb_proportion_Code").html(html);
			}
		});
	

}

function getUpdateOutModel(pid){
	

	if(pid!=0){
		getUpdateJpbh();
	}

	 var i_product_Id = $("#updatestr_material_Model").val();
	 if(i_product_Id=="null"){
		 return;
	 }
		$.ajax({
			type : "POST",
			url : baseUrl + "/Common/getAsph_TargetProCodeById.html",
			data : {"str_material_Type":0,
		        "str_material_Mold":i_product_Id,
		        "i_id":i_org_Id},
		    dataType : "json",
			success : function(data) {
			
				
				var html =""
				for(var i = 0; i < data.length; i++) {
					html += "<option value='"+data[i].i_id+"'>"+data[i].str_proportion_Code+"</option>"
				}
				if(pid!=0){
					$("#updatestr_grade_Code").empty();
				}
				
				$("#updatestr_mb_proportion_Code").empty();
				$("#updatestr_mb_proportion_Code").html(html);
			}
		});
	

}

//级配添加
function getAddGradeCode(str_material_Mold,tag,obj) {
	obj.empty();
	obj.html(jpbh);
}
//级配修改
function getUpdateGradeCode(str_material_Mold,tag,obj) {
	var i_product_Id = $("#updatestr_material_Model").val();
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getGradeCodeById.html",
		data : {"str_material_Type":0,
				"i_org_Id":i_org_Id,
		        "str_material_Mold":1,
		        "i_id":i_product_Id},
		dataType : "json",
		success : function(data) {
			var html = "";
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].i_id+"'>"+data[i].str_grade_Code+"</option>";
			}
			obj.empty();
			obj.html(html);
		}
	});
}

function num(obj){
	obj.value = obj.value.replace(/[^\d.]/g,""); 
	obj.value = obj.value.replace(/^\./g,"");
	obj.value = obj.value.replace(/\.{2,}/g,".");
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
}

function getMaterialName(){

	
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getMaterialName.html",
		data : {"str_material_Type":0,
			  "i_org_Id":i_org_Id,
		    "str_material_Mold":1},
		dataType : "json",
		success : function(data) {
			var html = "<option value='null' selected='selected'></option>"
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].name+"'>"+data[i].name+"</option>"
			}
			upDateName = html;
			$("#str_material_Name").empty();
			$("#str_material_Name").html(html);
		}
	});
}
//产品名称监听事件
var mbphbbh = "";
function getOutName(){
	
	
    var index = $("#str_material_Name")[0].selectedOptions[0].index-1;
    if(index<0){
    	$("#str_material_Model").empty();
    	$("#str_mb_proportion_Code").empty();
    	$("#str_grade_Code").empty();
    	return;
    }
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getMaterialName.html",
		data : {"str_material_Type":0,
			"i_org_Id":i_org_Id,
			 "str_material_Mold":'1'},
		dataType : "json",
		success : function(data) {
			var html ="<option value='null'></option>"
			for(var i = 0; i < data[index].model.length; i++) {
				html += "<option value='"+data[index].model[i].i_id+"'>"+data[index].model[i].str_material_Model+"</option>"
			}
	    	$("#str_mb_proportion_Code").empty();
	    	$("#str_grade_Code").empty();
			$("#str_material_Model").empty();
			$("#str_material_Model").html(html);
		}
	});

}

function getUpdateOutName(){
	
    var index = $("#updatestr_material_Name")[0].selectedOptions[0].index-1;
    if(index<0){
    	$("#updatestr_material_Model").empty();
    	$("#updatestr_mb_proportion_Code").empty();
    	$("#updatestr_grade_Code").empty();
    	return;
    }
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getMaterialName.html",
		data : {"str_material_Type":0,
			"i_org_Id":i_org_Id,
			 "str_material_Mold":'1'},
		dataType : "json",
		success : function(data) {
			var html ="<option value='null'></option>"
			for(var i = 0; i < data[index].model.length; i++) {
				html += "<option value='"+data[index].model[i].i_id+"'>"+data[index].model[i].str_material_Model+"</option>"
			}
	    	$("#updatestr_mb_proportion_Code").empty();
	    	$("#updatestr_grade_Code").empty();
			$("#updatestr_material_Model").empty();
			$("#updatestr_material_Model").html(html);
		}
	});

}
var  str_grade_Code1 ="";
function getJpbh(){
	
	var i_product_Id = $("#str_material_Model").val();
	if(i_product_Id==null || i_product_Id==""){
		$("#str_grade_Code").empty();
		return;
	}	

		$.ajax({
			type : "POST",
			url : baseUrl + "/Common/getGradeCodeById.html",
			data : {"str_material_Type":0,
					"i_org_Id":i_org_Id,
			        "str_material_Mold":i_product_Id,
//			        "i_id":i_product_Id
			        },
			dataType : "json",
			success : function(data) {
				var html = "";
				for(var i = 0; i < data.length; i++) {
					html += "<option value='"+data[i].i_id+"'>"+data[i].str_grade_Code+"</option>";
				}
				str_grade_Code1 = html;
			}
		});
}

function getUpdateJpbh(){
	
	var i_product_Id = $("#updatestr_material_Model").val();
	if(i_product_Id==null || i_product_Id==""){
		$("#updatestr_grade_Code").empty();
		return;
	}	

		$.ajax({
			type : "POST",
			url : baseUrl + "/Common/getGradeCodeById.html",
			data : {"str_material_Type":0,
					"i_org_Id":i_org_Id,
			        "str_material_Mold":i_product_Id
//			        "i_id":i_product_Id
			        },
			dataType : "json",
			success : function(data) {
				var html = "";
				for(var i = 0; i < data.length; i++) {
					html += "<option value='"+data[i].i_id+"'>"+data[i].str_grade_Code+"</option>";
				}
				str_grade_Code1 = html;
			}
		});
}
function getJpbhShow(){
	$("#str_grade_Code").empty();
	$("#str_grade_Code").html(str_grade_Code1);
}

function getUpdateJpbhShow(){
	$("#updatestr_grade_Code").empty();
	$("#updatestr_grade_Code").html(str_grade_Code1);
}
function addOrUpdate(tag) {
	
	$("#i_org_Id").val(i_org_Id)
	
	var obj;
	var url = "";
	var params = {};
	var str_proportion_Code;
	var str_material_Name;
	var str_material_Model;
	var str_mb_proportion_Code;
	var str_grade_Code;
	var d_no1_SetValue;
	var d_no2_SetValue;
	var d_no3_SetValue;
	var d_no4_SetValue;
	var d_no5_SetValue;
	var d_no6_SetValue;
	var d_asphalt_SetValue;
	var d_admixture1_SetValue;
	var d_admixture2_SetValue;
	var d_hotPowder_SetValue;
	var d_coldPowder_SetValue;
	var i_product_Id;
	var i_targ_PropId;
	if(tag == 1) {
		obj = $("#tb input");
		url = baseUrl+ "/AsphProportion/addAsphalt_Prod_Proportion.html";
		str_proportion_Code = $("#str_proportion_Code").val();
		str_material_Name = $("#str_material_Name").find("option:selected").text();
		str_material_Model = $("#str_material_Model").find("option:selected").text();
		//产品id
		i_product_Id = $("#str_material_Model").find("option:selected")[0].value;
		//目标配合比id
		if($("#str_mb_proportion_Code").find("option:selected").text()!="" && $("#str_mb_proportion_Code").find("option:selected").text()!=null){
			i_targ_PropId = $("#str_mb_proportion_Code").find("option:selected")[0].value;
		}
		
		
		str_mb_proportion_Code = $("#str_mb_proportion_Code").find("option:selected").text();
		if($("#str_grade_Code").find("option:selected").text()!="" || $("#str_grade_Code").find("option:selected").text()!=null){
			str_grade_Code = $("#str_grade_Code").find("option:selected").val();
		}
		
		
		d_no1_SetValue = $("#d_no1_SetValue").val();
		d_no2_SetValue = $("#d_no2_SetValue").val();
		d_no3_SetValue = $("#d_no3_SetValue").val();
		d_no4_SetValue = $("#d_no4_SetValue").val();
		d_no5_SetValue = $("#d_no5_SetValue").val();
		d_no6_SetValue = $("#d_no6_SetValue").val();
		str_remarks = $("#str_remarks").val();
		d_coldPowder_SetValue = $("#d_coldPowder_SetValue").val();
		d_hotPowder_SetValue = $("#d_hotPowder_SetValue").val();
		d_asphalt_SetValue = $("#d_asphalt_SetValue").val();
		d_admixture1_SetValue = $("#d_admixture1_SetValue").val();
		d_admixture2_SetValue = $("#d_admixture2_SetValue").val();
		if(str_proportion_Code==null || str_proportion_Code==""){
			swal("操作失败","生产配合比编号不能为空!", "info");
			return;
		}
		if(str_material_Name==null || str_material_Name==""){
			swal("操作失败","产品名称不能为空!", "info");
			return;
		}
		if(str_material_Model==null || str_material_Model==""){
			swal("操作失败","产品型号不能为空!", "info");
			return;
		}
		
		if(i_targ_PropId==null || i_targ_PropId==""){
			swal("操作失败","目标配合比编号不能为空!", "info");
			return;
		}
		if(str_grade_Code==null || str_grade_Code==""){
			swal("操作失败","级配编号不能为空!", "info");
			return;
		}
		
		if(d_asphalt_SetValue == null || d_asphalt_SetValue == "") {
			swal("操作失败","油石比不能为空!", "info");
			return;
		}
		params.i_product_Id = $("#str_material_Name").val();
	} else {
		
		obj = $("#updatetb input");
		url = baseUrl+ "/AsphProportion/updateAsphalt_Prod_Proportion.html";
//		params = $("#update").serialize();
		//产品id
		i_product_Id = $("#updatestr_material_Model").find("option:selected")[0].value;
		//目标配合比id
		i_targ_PropId = $("#updatestr_mb_proportion_Code").find("option:selected")[0].value;
		str_proportion_Code = $("#updatestr_proportion_Code").val();
		str_material_Name = $("#updatestr_material_Name").find("option:selected").text();
		str_material_Model = $("#updatestr_material_Model").find("option:selected").text();
		str_mb_proportion_Code = $("#updatestr_mb_proportion_Code").find("option:selected").text();
		//级配
		str_grade_Code = $("#updatestr_grade_Code").find("option:selected")[0].value;
		
		if(str_proportion_Code==null || str_proportion_Code==""){
			swal("操作失败","生产配合比编号不能为空!", "info");
			return;
		}
		if(str_material_Name==null || str_material_Name==""){
			swal("操作失败","产品名称不能为空!", "info");
			return;
		}
		if(str_material_Model==null || str_material_Model==""){
			swal("操作失败","产品型号不能为空!", "info");
			return;
		}
		d_coldPowder_SetValue = $("#updated_coldPowder_SetValue").val();
		d_hotPowder_SetValue = $("#updated_hotPowder_SetValue").val();
		
		d_no1_SetValue = $("#updated_no1_SetValue").val();
		d_no2_SetValue = $("#updated_no2_SetValue").val();
		d_no3_SetValue = $("#updated_no3_SetValue").val();
		d_no4_SetValue = $("#updated_no4_SetValue").val();
		d_no5_SetValue = $("#updated_no5_SetValue").val();
		d_no6_SetValue = $("#updated_no6_SetValue").val();
		str_remarks = $("#upadatestr_remarks").val();
		d_asphalt_SetValue = $("#updated_asphalt_SetValue").val();
		d_admixture1_SetValue = $("#updated_admixture1_SetValue").val();
		d_admixture2_SetValue = $("#updated_admixture2_SetValue").val();
		
		str_remarks = $("#updatestr_remarks").val();
		
		if(d_asphalt_SetValue == null || d_asphalt_SetValue == "") {
			swal("操作失败","油石比不能为空!", "info");
			return;
		}
	}
	var num = 0;
	//tongn update  添加修改id
	if(tag!=1){
		
	  params.i_id = i_update_id;
	}
	params.i_product_Id = i_product_Id;
	params.i_targ_PropId = i_targ_PropId;
	params.i_org_Id = i_org_Id;
	params.str_proportion_Code = str_proportion_Code;
	params.str_material_Name = str_material_Name;
	params.str_material_Model = str_material_Model;
	params.str_mb_proportion_Code = str_mb_proportion_Code;
	params.str_grade_Code = str_grade_Code;
	params.str_remarks = str_remarks;
	params.d_asphalt_SetValue = d_asphalt_SetValue;
	if(d_no1_SetValue == null || d_no1_SetValue == "") {
		d_no1_SetValue = 0;
	}
	params.d_no1_SetValue = d_no1_SetValue;
	num = num + parseInt(d_no1_SetValue);
	if(d_no2_SetValue == null || d_no2_SetValue == "") {
		d_no2_SetValue = 0
	}
	params.d_no2_SetValue = d_no2_SetValue;
	num = num + parseInt(d_no2_SetValue);
	if(d_no3_SetValue == null || d_no3_SetValue == "") {
		d_no3_SetValue = 0;
	}
	params.d_no3_SetValue = d_no3_SetValue;
	num = num + parseInt(d_no3_SetValue);
	if(d_no4_SetValue == null || d_no4_SetValue == "") {
		d_no4_SetValue = 0;
	}
	params.d_no4_SetValue = d_no4_SetValue;
	num = num + parseInt(d_no4_SetValue);
	if(d_no5_SetValue == null || d_no5_SetValue == "") {
		d_no5_SetValue = 0;
	}
	params.d_no5_SetValue = d_no5_SetValue;
	num = num + parseInt(d_no5_SetValue);
	if(d_no6_SetValue == null || d_no6_SetValue == "") {
		d_no6_SetValue = 0;
	}
	params.d_no6_SetValue = d_no6_SetValue;
	num = num + parseInt(d_no6_SetValue);
	if(d_coldPowder_SetValue == null || d_coldPowder_SetValue == "") {
		d_coldPowder_SetValue = 0;
	}
	params.d_coldPowder_SetValue = d_coldPowder_SetValue;
	num = num + parseInt(d_coldPowder_SetValue);
	if(d_hotPowder_SetValue == null || d_hotPowder_SetValue == "") {
		d_hotPowder_SetValue = 0;
	}
	params.d_hotPowder_SetValue = d_hotPowder_SetValue;
	num = num + parseInt(d_hotPowder_SetValue);
	if(d_admixture1_SetValue == null || d_admixture1_SetValue == "") {
		d_admixture1_SetValue = 0;
	}
	params.d_admixture1_SetValue = d_admixture1_SetValue;
	num = num + parseInt(d_admixture1_SetValue);
	if(d_admixture2_SetValue == null || d_admixture2_SetValue == "") {
		d_admixture2_SetValue = 0;
	}
	params.d_admixture2_SetValue = d_admixture2_SetValue;
	num = num + parseInt(d_admixture2_SetValue);
	
	if(num==0){
		swal("操作失败","骨料仓、粉料仓、外掺剂至少要录入一项！", "info");
		return;
	}
	if(num != 100) {
		swal("操作失败","除油石比外用量总和必须等于100！", "info");
		return;
	}

	
	$.ajax({
		type : "POST",
		url : url,
		data : params,
		dataType : "json",
		success : function(data) {

			if(data.code=="success"){
				swal({
					title: data.message,
					text: "",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					window.location.href=baseUrl+ "/AsphProportion/asphaltProdProportion.html";
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
			
		}
	});
} 

function login_popup() {
	$("#add input").val("");
	$("#add select").html("<option></option>");
	$("#str_remarks").val("");
    $("#loginModal").modal("show")
    $("#tb").empty();
	var html = "<tr>"
		+"<td class='mbphb_luru_dise h50'>用量</td>"
        +"<td><input type='text' name='d_no1_SetValue' id='d_no1_SetValue' value='' onkeyup='num(this)'></td>"
        +"<td><input type='text' name='d_no2_SetValue' id='d_no2_SetValue' value='' onkeyup='num(this)'></td>"
        +"<td><input type='text' name='d_no3_SetValue' id='d_no3_SetValue' value='' onkeyup='num(this)'></td>"
        +"<td><input type='text' name='d_no4_SetValue' id='d_no4_SetValue' value='' onkeyup='num(this)'></td>"
        +"<td><input type='text' name='d_no5_SetValue' id='d_no5_SetValue' value='' onkeyup='num(this)'></td>"
        +"<td><input type='text' name='d_no6_SetValue' id='d_no6_SetValue' value='' onkeyup='num(this)'></td>"
        +"<td><input type='text' name='d_coldPowder_SetValue' id='d_coldPowder_SetValue' value='' onkeyup='num(this)'></td>"
        +"<td><input type='text' name='d_hotPowder_SetValue' id='d_hotPowder_SetValue' value='' onkeyup='num(this)'></td>"
        +"<td><input type='text' name='d_asphalt_SetValue' id='d_asphalt_SetValue' value='' onkeyup='num(this)'></td>"
        +"<td><input type='text' name='d_admixture1_SetValue' id='d_admixture1_SetValue' value='' onkeyup='num(this)'></td>"
        +"<td><input type='text' name='d_admixture2_SetValue' id='d_admixture2_SetValue' value='' onkeyup='num(this)'></td>"
        +"</tr>";
	$("#tb").html(html);
}

$(".globalLoginBtn").on("click", login_popup),function() {
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
} ();

function show(i_id) {
	
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
	var html = "";
	$("#updatetb").html(html);
	$.ajax({
		type : "POST",
		url : baseUrl+ "/AsphProportion/getAsphalt_Prod_ProportionById.html",
		data : {"i_id": i_id,"i_org_Id":i_org_Id,"pc":0},
		dataType : "json",
		success : function(data) {
			
			if(data.data.code == "error"){
        		swal("操作失败",data.data.message, "error");
        		return;
        	}
			$("#loginModal2").modal("show");
			$("#updatestr_proportion_Code").val(data.data.str_proportion_Code);
			$("#updatestr_material_Name").html("<option selected='selected'>"+data.data.str_material_Name+"</option>");
			$("#updatestr_material_Model").html("<option selected='selected' value='"+data.data.i_product_Id+"'>"+data.data.str_material_Model+"</option>");
			$("#updatestr_mb_proportion_Code").html("<option selected='selected' value='"+data.data.i_targ_PropId+"'>"+data.data.str_mb_proportion_Code+"</option>");
			$("#updatestr_grade_Code").html("<option selected='selected' value='"+data.data.i_grad_Id+"'>"+data.data.str_grade_Code+"</option>");
			$("#updated_no1_SetValue").val(data.data.d_no1_SetValue);
			$("#updated_no2_SetValue").val(data.data.d_no2_SetValue);
			$("#updated_no3_SetValue").val(data.data.d_no3_SetValue);
			$("#updated_no4_SetValue").val(data.data.d_no4_SetValue);
			$("#updated_no5_SetValue").val(data.data.d_no5_SetValue);
			$("#updated_no6_SetValue").val(data.data.d_no6_SetValue);
			$("#updated_coldPowder_SetValue").val(data.data.d_coldPowder_SetValue);
			$("#updated_hotPowder_SetValue").val(data.data.d_hotPowder_SetValue);
			$("#updated_asphalt_SetValue").val(data.data.d_asphalt_SetValue);
			$("#updated_admixture1_SetValue").val(data.data.d_admixture1_SetValue);
			$("#updated_admixture2_SetValue").val(data.data.d_admixture2_SetValue);
			$("#updatestr_remarks").val(data.data.str_remarks);
			$("#i_id").val(data.data.i_id);
			$("#updatestr_grade_Code").html("<option selected='selected' value='"+data.data.Grad_Id+"'>"+data.data.Grade_Code+"</option>");
			
			getMaterialName();
			getUpdateOutModel(0);
//			getUpdateJpbh();
//			$("#updatestr_grade_Code").empty();
//			$("#updatestr_grade_Code").html(str_grade_Code);
			
			i_update_id = i_id;
		}
	});
	
	
}

function del(i_id) {

	swal({
		title: "确定操作吗？",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
		
	},
	function(){
		$.ajax({
			type : "POST",
			url : baseUrl + "/AsphProportion/deletAsphalt_Prod_Proportion.html",
			data : {"i_id":i_id},
			dataType : "json",
			success : function(data) {
				
				if(data.code=="success"){
					setTimeout(function(){swal({
						title: data.message,
						type: "success",
						cancelButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					},
					function(){
						window.location.href=baseUrl+ "/AsphProportion/asphaltProdProportion.html";
					}); },200);
				}else{
					setTimeout(function(){swal(data.message,"","error"); },200);
				}
			}
		});
	
	});
}
//飘窗
 function Baywindow(obj,i_id){

	 
		var objDiv = $("#"+"mydiv1"+""); 
		$(objDiv).css("display","block"); 
		$(objDiv).css("left", event.clientX); 
		$(objDiv).css("top", event.clientY + 10); 
		$("#tabIds  tr:not(:first)").html("");
		
		
/*     var  div = $("<div></div>").addClass("mbpb_tanc").attr("style","position:absolute; right:20%; top:50%").appendTo(obj.parentNode)

     var table = $("<table></table>").addClass("table").attr("style", "width:100%; border:0;cellpadding:0; cellspacing:0" ).appendTo(div)

	 var tr1 = $("<tr></tr>").appendTo(table)
	 var td1 = $("<td></td>").attr("width","26%").addClass("ydq").appendTo(tr1).html("生产配合比编号")
     var td2 = $("<td></td>").attr("width","20%").addClass("zdq").appendTo(tr1).attr("id","Prop_Code")
     var td3 = $("<td></td>").attr("width","20%").addClass("ydq").appendTo(tr1).html("产品名称")
     var td4 = $("<td></td>").attr("width","34%").addClass("zdq").appendTo(tr1).attr("id","MaterialModel")
     var tr2 = $("<tr></tr>").appendTo(table)
     var td21 = $("<td></td>").attr("width","24%").addClass("ydq").appendTo(tr2).html("目标配合比编号")
     var td22 = $("<td></td>").attr("width","21%").addClass("zdq").appendTo(tr2).attr("id","Target")
     var td23 = $("<td></td>").attr("width","21%").addClass("ydq").appendTo(tr2).html("级配编号")
     var td24 = $("<td></td>").addClass("zdq").appendTo(tr2).attr("id","Grading")

     var table1 = $("<table></table>").addClass("table").attr("width","100%").attr("border",'0').attr("cellpadding",'0').attr("cellspacing","0").addClass("sbj xbj mbphb_bg").appendTo(div)

     var tr2_ = $("<tr></tr>").addClass("dise").appendTo(table1)
     var td2_1 = $("<td></td>").attr("height","42").appendTo(tr2_).html("料仓")
     var td2_2 = $("<td></td>").appendTo(tr2_).html("用量(%)")
     var td2_3 = $("<td></td>").appendTo(tr2_).html("料仓")
     var td2_4 = $("<td></td>").appendTo(tr2_).html("用量(%)")

	 var tr3_ =  $("<tr></tr>").appendTo(table1)
     var td2_1 = $("<td></td>").addClass("dise").appendTo(tr3_).html("骨料1#仓")
     var td2_2 = $("<td></td>").appendTo(tr3_).attr("id","aggregate1")
     var td2_3 = $("<td></td>").addClass("dise").appendTo(tr3_).html("冷粉仓")
     var td2_4 = $("<td></td>").appendTo(tr3_).attr("id","Cold_storage")

     var tr4_ =  $("<tr></tr>").appendTo(table1)
     var td2_1 = $("<td></td>").addClass("dise").appendTo(tr4_).html("骨料2#仓")
     var td2_2 = $("<td></td>").appendTo(tr4_).attr("id","aggregate2")
     var td2_3 = $("<td></td>").addClass("dise").appendTo(tr4_).html("热粉仓")
     var td2_4 = $("<td></td>").appendTo(tr4_).attr("id","hot_storage")

     var tr5_ =  $("<tr></tr>").appendTo(table1)
     var td2_1 = $("<td></td>").addClass("dise").appendTo(tr5_).html("骨料3#仓")
     var td2_2 = $("<td></td>").appendTo(tr5_).attr("id","aggregate3")
     var td2_3 = $("<td></td>").addClass("dise").appendTo(tr5_).html("油石比")
     var td2_4 = $("<td></td>").appendTo(tr5_).attr("id","Petroleum_ratio")

     var tr6_ =  $("<tr></tr>").appendTo(table1)
     var td2_1 = $("<td></td>").addClass("dise").appendTo(tr6_).html("骨料4#仓")
     var td2_2 = $("<td></td>").appendTo(tr6_).attr("id","aggregate4")
     var td2_3 = $("<td></td>").addClass("dise").appendTo(tr6_).html("外掺剂1")
     var td2_4 = $("<td></td>").appendTo(tr6_).attr("id","Additives1")

     var tr7_ =  $("<tr></tr>").appendTo(table1)
     var td2_1 = $("<td></td>").addClass("dise").appendTo(tr7_).html("骨料5#仓")
     var td2_2 = $("<td></td>").appendTo(tr7_).attr("id","aggregate5")
     var td2_3 = $("<td></td>").addClass("dise").appendTo(tr7_).html("外掺剂2")
     var td2_4 = $("<td></td>").appendTo(tr7_).attr("id","Additives2")

     var tr8_ =  $("<tr></tr>").appendTo(table1)
     var td2_1 = $("<td></td>").addClass("dise").appendTo(tr8_).html("骨料6#仓")
     var td2_2 = $("<td></td>").appendTo(tr8_).attr("id","aggregate6")
     var td2_3 = $("<td></td>").addClass("dise").appendTo(tr8_).html("&nbsp;")
     var td2_4 = $("<td></td>").appendTo(tr8_).html("&nbsp;")*/

   $.ajax({
         type : "POST",
         url : baseUrl + "/AsphProportion/getAsphalt_Prod_ProportionById.html",
         data : {"i_id":i_id,"i_org_Id":i_org_Id,"pc":1},
         dataType : "json",
         success : function(data) {
         	/*$("#Grading").html(data.str_grade_Code)
			$("#Prop_Code").html(data.str_proportion_Code)
             $("#Target").html(data.str_mb_proportion_Code)
             $("#MaterialModel").html(data.str_material_Name+"-"+data.str_material_Model)
			 $("#aggregate1").html(data.d_no1_SetValue)
             $("#aggregate2").html(data.d_no2_SetValue)
             $("#aggregate3").html(data.d_no3_SetValue)
             $("#aggregate4").html(data.d_no4_SetValue)
             $("#aggregate5").html(data.d_no5_SetValue)
             $("#aggregate6").html(data.d_no6_SetValue)
             $("#Cold_storage").html(data.d_coldPowder_SetValue)
             $("#hot_storage").html(data.d_hotPowder_SetValue)
             $("#Petroleum_ratio").html(data.d_asphalt_SetValue)
             $("#Additives1").html(data.d_admixture1_SetValue)
             $("#Additives2").html(data.d_admixture2_SetValue)*/
        	 
         	$("#pcstr_proportion_Code").html(data.data.str_proportion_Code);
  			$("#pcstr_material_Name").html(data.data.str_material_Name+"_"+data.data.str_material_Model);
  			//$("#pctr_mb_proportion_Code").html(data.data.str_mb_proportion_Code);
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
  					url : baseUrl + "/Common/getAsph_TargetProCodeById.html",
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

/* function hide(obj){
	 var objDiv = $("#"+"mydiv1"+""); 
	$(objDiv).css("display", "none"); 

 }*/

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
		            url: baseUrl+ "/AsphProportion/getAsphalt_Prod_Proportion.html",
		            dataSrc: "data",
		            data: function (d) {
		                var param = {};
		                param.i_org_Id = i_org_Id;
		                param.str_material_Name = $("#material_Name").val();
		                param.str_material_Model = $("#material_Model").val();
		                param.str_proportion_Code = $("#proportion_Code").val();
		                return param;
		            }
		        },
				"deferRender" : true,
				"columns" : [ {
					"data" : "serialNumber"
				}, {
					"data" : "str_proportion_Code"
				}, {
					"data" : "str_grade_Code"//
				},{
					"data" : "str_Mproportion_Code"//
				},{
					"data" : "materNameAndModel"
				}, {
					"data" : "str_operator"
				}, {
					"data" : "str_create_Date" //str_modify_Date改为str_create_Date
				}, {
					"data" : "str_remarks"
				}, {
					"data" : "operate"
				} ],
				"createdRow" : function(row, data, dataIndex) {
//					$(row).children("td").eq(1).attr("onmouseover",
//							"getDetailed('" + data.i_id + "');")
//					$(row).children("td").eq(1).addClass("mbpb_xg");
					//$(row).children("td").eq(1).html("<a href='javascript:void(0);' id='t1' onMouseOver='show2(this,\"mydiv1\","+data.i_id+");' onMouseOut='hide(this,\"mydiv1\")'>"+data.str_proportion_Code+"</a>");
					$(row).children("td").eq(1).html("<a href='javascript:void(0);'>"+data.str_proportion_Code+"</a>");
		            $(row).children("td").eq(1).attr('onMouseOver',"Baywindow(this,"+data.i_id+");");
		            $(row).children("td").eq(1).attr('onMouseOut','hide(this,\"mydiv1\")');
		            
		            $(row).children("td").eq(2).html("<a href='javascript:void(0);'>"+data.str_grade_Code+"</a>");
		            $(row).children("td").eq(2).attr('onMouseOver',"show3(this,\"mydiv2\","+data.i_grad_Id+");");
		            $(row).children("td").eq(2).attr('onMouseOut','hide(this,\"mydiv2\")');
		            
		            $(row).children("td").eq(3).html("<a href='javascript:void(0);'>"+data.str_Mproportion_Code+"</a>");
		            $(row).children("td").eq(3).attr('onMouseOver',"show4(this,\"mydiv3\","+data.i_targ_PropId+");");
		            $(row).children("td").eq(3).attr('onMouseOut','hide(this,\"mydiv3\")');
				},
				"columnDefs" : [ {
					"targets" : [ 0 ],
					"visible" : true,
					"searchable" : false
				} ]
		});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

function show4(obj,id,ids) { 
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
	    url :  "../AsphTargetPro/getAsphTargetProById.html",
//	    data: JSON.stringify(param),
//	    dataType: "json",
	    success: function (data) {
	    	if(data!=null){
	    		 $("#nr_l").html(data.asphTargetPro.str_proportion_Code);
	    		 $("#xh_l").html(data.asphTargetPro.str_material_Name+"-"+data.asphTargetPro.str_material_Model);
	    		 var html = "";
	    		 if(data.asphTargetProDList.length>0){
	    			 for (var i = 0; i < data.asphTargetProDList.length; i++) {
	    				 html+="<tr> <td>"+(i+1)+"</td><td>"+data.asphTargetProDList[i].str_material_Name+data.asphTargetProDList[i].str_material_Model+"</td><td align='center'>"+data.asphTargetProDList[i].d_weight+"</td> </tr>"
					}
	    			 $("#nr_l_new").html(html);
	    		 }
	    	}
	    }
	});
} 

function show3(obj,id,ids) { 
	for (var i = 100; i <= 110; i++) {
		for (var j = 1; j <= 15; j++) {
			var ist = "#"+i+"_"+j
			$(ist).empty(""); 
		}
	}
	var objDiv = $("#"+id+""); 
	$(objDiv).css("display","block"); 
	$(objDiv).css("left", event.clientX); 
	$(objDiv).css("top", event.clientY + 10); 
	$("#tabIds  tr:not(:first)").html("");
	var param = {};
	param.i_id = ids;
	param.str_grade_Code='判定飘窗';
	$.ajax({
	    type: "post",
//	    contentType: 'application/json;charset=UTF-8',
	    data : param,
		dataType : "json",
	    url :  "../AsphaltGrading/getAsphalt_GradDetailedById.html",
//	    data: JSON.stringify(param),
//	    dataType: "json",
	    success: function (data) {
	    	
	    	if(data!=null){
	    		
	    		 if(data.length>0){
	    			
	    			 for (var i = 0; i < data.length; i++) {
	    				 $("#yjph").html(data[i].str_grade_Code);
			    		 $("#cpmc").html(data[i].str_material_Name);
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





	