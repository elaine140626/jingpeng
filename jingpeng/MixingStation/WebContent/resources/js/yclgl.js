var i_org_Id = "";
var baseUrl = "";
var mold = "";
$(function() {
	
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

function getList() {

	$('#materialList').DataTable({
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
            url: baseUrl + "/Material/getMaterialInfo.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.I_org_Id =i_org_Id
                param.str_material_Type = $("#str_material_Type").val();
                return param;
            }
        },
		"deferRender" : true,
		"columns" : [ {
			"data" : "serialNumber"
		}, {
			"data" : "str_material_Code"
		}, {
			"data" : "nameAndModel"
		}, {
			"data" : "d_density"
		}, {
			"data" : "str_measure_Unit"
		}, {
			"data" : "str_material_Mold"
		}, {
			"data" : "str_operator"
		}, {
			"data" : "str_create_Date"
		}, {
			"data" : "operate"
		} ],
		"createdRow" : function(row, data, dataIndex) {
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

function add() {
	
	$("#i_org_Id").val(i_org_Id)
	var str_material_Code = $("#str_material_Code").val();
	var str_material_Name = $("#str_material_Names").val();
	var str_material_Model = $("#str_material_Models").val();
	var d_density = $("#d_density").val();
	var str_measure_Unit = $("#str_measure_Unit").val();
	var str_material_Mold = $("#str_material_Mold").val();
	
	if(str_material_Code == null || str_material_Code == "") {
		swal("操作失败","物料编码不能为空!", "info");
		return;
	}
	if(str_material_Name == null || str_material_Name =="") {
		swal("操作失败","物料名称不能为空!", "info");
		return;
	}
	if(str_material_Model == null || str_material_Model == "") {
		swal("操作失败","物料型号不能为空!", "info");
		return;
	}
	if(d_density == null || d_density == "") {
		swal("操作失败","密度不能为空!", "info");
		return;
	}
	if(isNaN(d_density)==true){
		swal("操作失败","密度只能是整数或小数", "info");
		return;
	}
	if(str_measure_Unit == null || str_measure_Unit == "") {
		swal("操作失败","计量单位不能为空!", "info");
		return;
	}
	if(str_material_Mold==null || str_material_Mold==""){
		swal("操作失败","物料类型不能为空!", "info");
		return;
	}
//	if($("#str_material_Mold").val() == "1") {
//		var str_material_Model = $("#str_material_Mold").val()
//		if(str_material_Model.indexOf("-") < 1 && str_material_Model.split("-").lenght != 2) {
//			alert("产成品 时，规格型号录入要求限定录入格式为  XXXX—001");
//			return;
//		}
//	}
	var params = $('#addForm').serialize();
	$.ajax({
		type : "POST",
		url : baseUrl + "/Material/addMaterial.action",
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
					window.location.href=baseUrl + "/Material/material.action";
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
	return false
}

function show(i_id,str_material_Code) {
	//alert(i_id);
	$("#updateloginModal").modal("show");
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
		url : baseUrl + "/Material/getMaterialById.action",
		data : {i_id:i_id},
		dataType : "json",
		success : function(data) {
			var obj = data;
			$("#i_id").val(i_id);
			$("#updatestr_material_Code").val(obj.str_material_Code);
			$("#updatestr_material_Code").attr("readonly","readonly");
			$("#updatestr_material_Name").val(obj.str_material_Name);
			$("#updatestr_material_Model").val(obj.str_material_Model);
			$("#updated_density").val(obj.d_density);
			$("#updatestr_measure_Unit").val(obj.str_measure_Unit);
			var material_Mold;
			if(obj.str_material_Mold =="原材料") {
				material_Mold = 0
			} else if(obj.str_material_Mold =="产成品") {
				material_Mold = 1
			} else {
				material_Mold = 2
			}
			$("#updatestr_material_Mold").val(material_Mold);
			mold = material_Mold;
		}
	});
    var Material_Code = str_material_Code;
	$.ajax({
		type : "POST",
		url : baseUrl + "/AsphTargetPro/Find_Asph_TargetProportionByMaterial_Code.action",
		data : {Material_Code:Material_Code},
		dataType : "json",
		success : function(data) {
			if(data.code=="success"){
				$("#updatestr_material_Mold").attr("disabled","disabled");
			}else{
				$("#updatestr_material_Mold").removeAttr("disabled");
				
			}
		}
	});
}

function update() {
	$("#i_org_Id2").val(i_org_Id)
	var i_id = $("#i_id").val()
	var str_material_Code = $("#updatestr_material_Code").val();
	var str_material_Name = $("#updatestr_material_Name").val();
	var str_material_Model = $("#updatestr_material_Model").val();
	var d_density = $("#updated_density").val();
	var str_measure_Unit = $("#updatestr_measure_Unit").val();
	var str_material_Mold = mold;
	console.log(str_material_Mold);
	if(str_material_Code == null || str_material_Code == "") {
		swal("操作失败","物料编码不能为空!", "info");
		return;
	}
	if(str_material_Name == null || str_material_Name =="") {
		swal("操作失败","物料名称不能为空!", "info");
		return;
	}
	if(str_material_Model == null || str_material_Model == "") {
		swal("操作失败","物料型号不能为空!", "info");
		return;
	}
	if(d_density == null || d_density == "") {
		swal("操作失败","密度不能为空!", "info");
		return;
	}
	if(isNaN(d_density)==true){
		swal("操作失败","密度只能是整数或小数", "info");
		return;
	}
	if(str_measure_Unit == null || str_measure_Unit == "") {
		swal("操作失败","计量单位不能为空!", "info");
		return;
	}
	/*if(str_material_Mold === 1 || str_material_Mold === 0){
		
	}else {
		swal("操作失败","物料类型不能为空!", "info");
		return;
	}*/	
	var params = {}
	params.i_id = i_id;
	params.i_org_Id = i_org_Id;
	params.str_material_Code = str_material_Code;
	params.str_material_Name = str_material_Name;
	params.str_material_Model = str_material_Model;
	params.d_density= d_density;
	params.str_measure_Unit = str_measure_Unit;
	params.str_material_Mold = str_material_Mold;
	params.str_material_Type = 0;
	//var params = $('#updateForm').serialize();
///	params.i_org_Id = i_org_Id
//	params.i_id = $("#i_id").val();
//	params.str_material_Mold = mold; 
	$.ajax({
		type : "POST",
		url : baseUrl + "/Material/updateMaterial.action",
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
					window.location.href=baseUrl + "/Material/material.action";
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		
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
			url : baseUrl + "/Material/deletAsphaltMaterial.action",
			data : {
				"i_id":i_id,
				"i_org_Id":i_org_Id
				},
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
						window.location.href=baseUrl + "/Material/material.action";
					}); },200);
				}else{
					setTimeout(function(){swal(data.message,"","error"); },200);
				}
			}
		});
	
	});
	

	
}

function num(obj){
	obj.value = obj.value.replace(/[\W]/g,''); 
	/*//整数位置
	if( (obj.value.toString()).indexOf('.')==-1 ){
		//alert((obj.value.toString()).indexOf('.'));
		
		if(obj.value.length > 6 && obj.value.substr(obj.value.length-1,1)!="."){
			
			obj.value = obj.value.substr(0,6);
		}
	}
	
	else{
		
	obj.value = obj.value.replace(/[^\d.]/g,""); 
	obj.value = obj.value.replace(/^\./g,"");
	obj.value = obj.value.replace(/\.{2,}/g,".");
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
	
	}*/
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

function search(){
	
	var table = $('#materialList').dataTable();
	table.fnDestroy();
    
	$('#materialList').DataTable({
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
            url: baseUrl + "/Material/getMaterialInfo.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.I_org_Id =i_org_Id
                param.str_material_Name = $("#material_Name").val();
                param.str_material_Model = $("#material_Model").val();
                param.str_material_Mold = $("#material_Mold").val();
                param.str_material_Type = $("#str_material_Type").val();
                return param;
            }
        },
		"deferRender" : true,
		"columns" : [ {
			"data" : "serialNumber"
		}, {
			"data" : "str_material_Code"
		}, {
			"data" : "nameAndModel"
		}, {
			"data" : "d_density"
		}, {
			"data" : "str_measure_Unit"
		}, {
			"data" : "str_material_Mold"
		}, {
			"data" : "str_operator"
		}, {
			"data" : "str_create_Date"
		}, {
			"data" : "operate"
		} ],
		"createdRow" : function(row, data, dataIndex) {
			$(row).children('td').eq(1).attr('style', 'text-align: center;')
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		}]
	});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

function login_popup() {
	$("#str_material_Code").val("");
	$("#str_material_Names").val("");
	$("#str_material_Models").val("");
	$("#d_density").val("");
	$("#str_measure_Unit").val("");
	//$("#str_material_Mold").val("");
    $("#loginModal").modal("show");
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