var baseUrl = "";
var addOrupdateTag = 1;
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
		"ajax" : baseUrl + "/ProductionPlan/getProductionPlan.action?i_org_Id="+i_org_Id,
		"deferRender" : true,
		"columns" : [ 
			{"data" : "serialNumber"},
			{"data" : "str_plan_No"},
			{"data" : "str_Mproportion_Code"},//
			{"data" : "str_equipment_Name"},
			{"data" : "nameAndModel"},
			{"data" : "str_proportion_Code"},
			{"data" : "str_grade_Code"},
			{"data" : "str_proj_Pos"},
			{"data" : "str_construction_Unit"},
			{"data" : "d_scheduled_Production"},
			{"data" : "str_isCancel"},
			{"data" : "str_startTime"},
			{"data" : "str_create_Date"},
			{"data" : "str_cancel_Reason"},
			{"data" : "operate",
                render: function(data, type, row) {
                	var html = '';
                	html +='<span class="scgl_del2"><a href="javascript:void(0)" onclick="show('+row.i_id+');"><img src="../resources/images/xiu.png" width="17" height="16" alt="查看" title="查看"></a></span>';
                	if(row.i_isCancel == 0){
                    	html +='<span class="scgl_del2"><a href="javascript:void(0)" onclick="upt('+row.i_id+');"><img src="../resources/images/zuofei.png" width="17" height="17" alt="作废" title="作废"></a></span>';
                	}
                	return html;
                }
				}],
		"createdRow" : function(row, data, dataIndex) {
			console.log(data);
			$(row).children("td").eq(1).addClass("mbpb_xg");
			$(row).children("td").eq(2).html("<a href='javascript:void(0);'>"+data.str_Mproportion_Code+"</a>");
            $(row).children("td").eq(2).attr('onMouseOver',"show3(this,\"mydiv3\","+data.i_peih_id+");");
            $(row).children("td").eq(2).attr('onMouseOut','hide2(this,\"mydiv3\")');
			$(row).children("td").eq(5).html("<a href='javascript:void(0);'>"+data.str_proportion_Code+"</a>");
            $(row).children("td").eq(5).attr('onMouseOver',"Baywindow(this,"+data.i_prod_Id+");");
            $(row).children("td").eq(5).attr('onMouseOut','hide2(this,\"mydiv2\")');
            $(row).children("td").eq(6).html("<a href='javascript:void(0);'>"+data.str_grade_Code+"</a>");
            $(row).children("td").eq(6).attr('onMouseOver',"show2(this,\"mydiv1\","+data.i_grad_Id+");");
            $(row).children("td").eq(6).attr('onMouseOut','hide2(this,\"mydiv1\")');
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		} ],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            if(aData.i_isCancel == 1){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#FFB90F");        
            	//设置满足条件行的字体颜色                	
            	$(nRow).css("color", "white !important");                	
            }
           // 获取所有数据
           return nRow;
        }
	});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

function login_popup() {
	$("#str_plan_No").removeAttr("readonly");
	$("#tab input:hidden").val("");
	$("#tab input:text").val("");
	$("#tab select").html("<option></option>")
    $("#loginModal").modal("show");
    addOrupdateTag = 1;
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
//function getMaterialName(){
//
//	
//	
//}

function clearModel(){
	$("#str_material_Model").empty();
}


function getMaterialModel() {
	var str_material_Name;
	str_material_Name = $("#str_material_Name").find("option:selected").text();
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getMaterialModels.action",
		data : {"str_material_Type":0,
		        "str_material_Mold":1,
		        "str_material_Name":$("#str_material_Name").val(),
		        "i_org_Id":i_org_Id},
		dataType : "json",
		success : function(data) {
			var html = "<option value='null'></option>"
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].i_id+"'>"+data[i].str_material_Model+"</option>";
			}
			$("#str_material_Model").empty();
			$("#str_material_Model").html(html);
		}
	});

}
//拌合设备名称
var bhsbName = "";
var cpName = "";
function getEqu_Id() {
//	document.getElementById("tabForm").reset();  
	$("#i_org_Id").val(i_org_Id)
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getEquipmentInfo.action",
		data : {"i_equipment_Type":0,"i_org_Id":i_org_Id},
		dataType : "json",
		success : function(data) {
			var html = "<option value='null'></option>"
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].i_id+"'>"+data[i].str_equipment_Name+"</option>";
			}
			bhsbName = html;
			
		}
	});

}

function getMcName(){
	$("#str_material_Name").removeAttr("disabled");
    $("#str_material_Model").removeAttr("disabled");
    $("#str_proportion_Code").removeAttr("disabled");
    $("#str_material_Name").html("");
    $("#str_material_Model").html("");
    $("#str_proportion_Code").html("");
    
	$.ajax({
	type : "POST",
	url : baseUrl + "/Common/getMaterialName.action",
	data : {"str_material_Type":0,
		    "str_material_Mold":1,"i_org_Id":i_org_Id},
	dataType : "json",
	success : function(data) {
		var html = "<option value='null'></option>"
		for(var i = 0; i < data.length; i++) {
			html += "<option value='"+data[i].name+"'>"+data[i].name+"</option>"
			//("<option></option>").attr(value,data[i].i_mateName_id).html(data[i].str_material_Name).appendTo("#str_material_Name")
		}
		cpName = html;
	}
});
}
//产品名称
function getCpName(obj){
	obj.html(cpName);
}
function getBhsb(obj){
	obj.empty();
	obj.html(bhsbName);
}
var sfphbbh = "";
function getProportionCode() {
	var i_product_Id = $("#str_material_Model").val();
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getProportionCode.action",
		data : {"str_material_Type":0,
			"i_org_Id":i_org_Id,
			"i_product_Id":i_product_Id
			},
		dataType : "json",
		success : function(data) {
			var html = "<option value='null'></option>"
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].i_id+"'>"+data[i].str_proportion_Code+"</option>";
			}
			sfphbbh = html;
			
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

function getGradeCode() {
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getGradeCode.action",
		data : {"i_id":$("#str_proportion_Code").val(),"i_org_Id":i_org_Id},
		dataType : "json",
		success : function(data) {
			$("#str_grade_Code").val(data[0].str_grade_Code);
			$("#i_grad_Id").val(data[0].i_id);
		}
	});
}

function addOrUpdate() {
	$('#i_org_Id').val(i_org_Id);
	var url = "";
	var params = $('#tabForm').serialize();
	var str_plan_No = $("#str_plan_No").val();
	var i_equ_Id = $("#i_equ_Id").val()
	var str_material_Name = $("#str_material_Name").find("option:selected").text();
	var str_material_Model = $("#str_material_Model").find("option:selected").text();
	var str_proportion_Code = $("#str_proportion_Code").find("option:selected").text();
	var str_proj_Pos = $("#str_proj_Pos").val();
	var str_construction_Unit = $("#str_construction_Unit").val();
	var d_scheduled_Production = $("#d_scheduled_Production").val();
	var str_startTime = $("#str_startTime").val();

	if(str_plan_No == null || str_plan_No == "") {
		swal("操作失败","生产计划编号不能为空!", "info");
		return;
	}
	if(i_equ_Id == null || i_equ_Id == "") {
		swal("操作失败","拌合设备名称不能为空!", "info");
		return;
	}
	if(str_material_Name == null || str_material_Name == "") {
		swal("操作失败","产品名称不能为空!", "info");
		return;
	}
	if(str_material_Model == null || str_material_Model == "") {
		swal("操作失败","产品型号不能为空!", "info");
		return;
	}
	if(str_proportion_Code == null || str_proportion_Code == "") {
		swal("操作失败","生产配合比编号不能为空!", "info");
		return;
	}
	if(str_proj_Pos == null || str_proj_Pos == "") {
		swal("操作失败","工程部位/用途不能为空!", "info");
		return;
	}
	if(str_construction_Unit == null || str_construction_Unit == "") {
		swal("操作失败","施工单位不能为空!", "info");
		return;
	}
	if(d_scheduled_Production == null || d_scheduled_Production == "") {
		swal("操作失败","计划生产量不能为空!", "info");
		return;
	}
	if(str_startTime == null || str_startTime == "") {
		swal("操作失败","计划生产时间不能为空!", "info");
		return;
	}
	if(addOrupdateTag == 1) {
		url =  baseUrl + "/ProductionPlan/addProductionPlan.action";
	} else {
		$('#i_id').val(upDateid);
		url = baseUrl + "/ProductionPlan/updateProductionPlan.action";
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
					window.location.href=baseUrl + "/ProductionPlan/productionPlan.action";
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
	
}
function getBtnScphbBh(){
	
	$("#str_grade_Code").empty();
	$("#str_proportion_Code").html(sfphbbh);
}
var upDateid = 0;

function show(i_id) {
    $("#str_plan_No").attr("disabled","disabled");
    $("#i_equ_Id").attr("disabled","disabled");
    $("#str_material_Name").attr("disabled","disabled");
    $("#str_material_Model").attr("disabled","disabled");
    $("#str_proportion_Code").attr("disabled","disabled");
    $("#str_grade_Code").attr("disabled","disabled");
    $("#i_grad_Id").attr("disabled","disabled");
    $("#str_proj_Pos").attr("disabled","disabled");
    $("#str_construction_Unit").attr("disabled","disabled");
    $("#d_scheduled_Production").attr("disabled","disabled");
    $("#str_startTime").attr("disabled","disabled");
//	getProportionCode();
	upDateid = i_id;
	/*var e = [];
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
	addOrupdateTag = 2;*/
	$.ajax({
		type : "POST",
		async: false,
		url : baseUrl + "/ProductionPlan/getProductionPlanById.action",
		data : {"i_id" : i_id},
		dataType : "json",
		success : function(data) {
			$("#str_plan_No").val(data.str_plan_No);
			$("#i_equ_Id").html("<option selected='selected' value='"+data.i_equ_Id+"'>"+data.str_equipment_Name+"</option>");
			$("#str_material_Name").html("<option selected='selected'>"+data.str_material_Name+"</option>");
			$("#str_material_Model").html("<option selected='selected' value='"+data.i_product_Id+"'>"+data.str_material_Model+"</option>");
			$("#str_proportion_Code").html("<option selected='selected' value='"+data.i_prod_Id+"'>"+data.str_proportion_Code+"</option>");
			$("#str_grade_Code").val(data.str_grade_Code);
			$("#i_grad_Id").val(data.i_grad_Id);
			$("#str_construction_Unit").val(data.str_construction_Unit);
			$("#d_scheduled_Production").val(data.d_scheduled_Production);
			$("#str_startTime").val(data.str_startTime);
			$("#str_proj_Pos").val(data.str_proj_Pos);
			$("#i_id").val(data.i_id);
			$("#planbtn").css("display","none");
		}
	});
	 $("#loginModal #tanName").html("查询生产计划")
	 $("#loginModal").modal("show");
}
function closes(){
	window.location.href=baseUrl + "/ProductionPlan/productionPlan.action";
}
function upt(i_id) {
    window.i_id= i_id
    $("#loginModal4").modal("show")

}
$("#zfbutton").on("click",function () {
    $.ajax({
        type: "post",
        async: false,
        contentType: 'application/json;charset=UTF-8',
        url: "../ProductionPlan/zfProductionPlan.action",
        data: JSON.stringify({"i_id": window.i_id,"str_cancel_Reason":window.textarea.value,"str_invalid_Person":'朱昭霖',"str_cancel_Time":CurentTime}),
        dataType: "json",
        success: function (data) {
            location.reload();
        }
    });
})
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
			url : baseUrl + "/ProductionPlan/delProductionPlan.action",
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
						window.location.href=baseUrl + "/ProductionPlan/productionPlan.action";
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
	 
	var objDiv = $("#"+"mydiv2"+""); 
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
	    url :  "../AsphaltGrading/getAsphalt_GradDetailedById.action",
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
            url: baseUrl + "/ProductionPlan/getProductionPlan.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.i_org_Id = i_org_Id;
                param.str_material_Name = $("#material_Name").val();
                param.str_material_Model = $("#material_Model").val();
                param.str_plan_No = $("#plan_No").val();
                return param;
            }
        },
		"deferRender" : true,
		"columns" : [ 
			{"data" : "serialNumber"},
			{"data" : "str_plan_No"},
			{"data" : "str_Mproportion_Code"},//
			{"data" : "str_equipment_Name"},
			{"data" : "nameAndModel"},
			{"data" : "str_proportion_Code"},
			{"data" : "str_grade_Code"},
			{"data" : "str_proj_Pos"},
			{"data" : "str_construction_Unit"},
			{"data" : "d_scheduled_Production"},
			{"data" : "str_isCancel"},
			{"data" : "str_startTime"},
			{"data" : "str_create_Date"},
			{"data" : "str_cancel_Reason"},
			{"data" : "operate",
                render: function(data, type, row) {
                	var html = '';
                	html +='<span class="scgl_del2"><a href="javascript:void(0)" onclick="show('+row.i_id+');"><img src="../resources/images/xiu.png" width="17" height="16" alt="查看" title="查看"></a></span>';
                	if(row.i_isCancel == 0){
                    	html +='<span class="scgl_del2"><a href="javascript:void(0)" onclick="upt('+row.i_id+');"><img src="../resources/images/zuofei.png" width="17" height="17" alt="作废" title="作废"></a></span>';
                	}
                	return html;
                }
			}],
		"createdRow" : function(row, data, dataIndex) {
			console.log(data);
//			$(row).children("td").eq(1).attr("onmouseover", "getDetailed('"+data.i_id+"');")
			$(row).children("td").eq(1).addClass("mbpb_xg");
//			$(row).children("td").eq(1).html("<a href='javascript:void(0);' id='t1' onMouseOver='show2(this,\"mydiv1\","+data.i_id+");' onMouseOut='hide(this,\"mydiv1\")'>"+data.str_proportion_Code+"</a>");
			
			//$(row).children("td").eq(4).html("<a onMouseOver=\"Baywindow(this,"+data.i_prod_Id+");\"  onMouseOut=\"hide(this)\" id='(\""+data.i_id+"\");'>"+data.str_proportion_Code+ "</a>");
			//$(row).children("td").eq(5).html("<a href='javascript:void(0);' id='t1' onMouseOver='show2(this,\"mydiv1\","+data.i_id+");' onMouseOut='hide2(this,\"mydiv1\")'>"+data.str_grade_Code+"</a>");
			$(row).children("td").eq(2).html("<a href='javascript:void(0);'>"+data.str_Mproportion_Code+"</a>");
            $(row).children("td").eq(2).attr('onMouseOver',"show3(this,\"mydiv3\","+data.i_peih_id+");");
            $(row).children("td").eq(2).attr('onMouseOut','hide2(this,\"mydiv3\")');
			
			$(row).children("td").eq(5).html("<a href='javascript:void(0);'>"+data.str_proportion_Code+"</a>");
            $(row).children("td").eq(5).attr('onMouseOver',"Baywindow(this,"+data.i_prod_Id+");");
            $(row).children("td").eq(5).attr('onMouseOut','hide2(this,\"mydiv2\")');
            
            $(row).children("td").eq(6).html("<a href='javascript:void(0);'>"+data.str_grade_Code+"</a>");
            $(row).children("td").eq(6).attr('onMouseOver',"show2(this,\"mydiv1\","+data.i_grad_Id+");");
            $(row).children("td").eq(6).attr('onMouseOut','hide2(this,\"mydiv1\")');
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		} ],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            if(aData.i_isCancel == 1){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#FFB90F");        
            	//设置满足条件行的字体颜色                	
            	$(nRow).css("color", "white !important");                	
            }
           // 获取所有数据
           return nRow;
        }
	});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

function show3(obj,id,ids) { 
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
	    url :  "../AsphTargetPro/getAsphTargetProById.action",
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
function CurentTime()
{
    var now = new Date();

    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();           //秒

    var clock = year + "-";

    if(month < 10)
        clock += "0";

    clock += month + "-";

    if(day < 10)
        clock += "0";

    clock += day + " ";

    if(hh < 10)
        clock += "0";

    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm + ":";

    if (ss < 10) clock += '0';
    clock += ss;
    return(clock);
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
