var baseUrl = "";
$(function() {
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
		"ajax" : baseUrl + "/Material/getMaterialInfo.html",
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
	
}

function add() {
	if($("#str_material_Mold").val() == "1") {
		var str_material_Model = $("#str_material_Model").val()
		if(str_material_Model.indexOf("-") < 1 && str_material_Model.split("-").lenght != 2) {
			alert("产成品 时，规格型号录入要求限定录入格式为  XXXX—001");
			return;
		}
	}
	var params = $('#addForm').serialize();
	$.ajax({
		type : "POST",
		url : baseUrl + "/Material/addMaterial.html",
		data : params,
		dataType : "json",
		success : function(data) {
			alert(data.message);
			window.location.href=baseUrl + "/Material/snycmaterial.html";
		}
	});
	
}

function show(i_id) {
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
		url : baseUrl + "/Material/getMaterialInfo.html",
		data : {i_id:i_id},
		dataType : "json",
		success : function(data) {
			var obj = data.data[0];
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
		}
	});
}

function update() {
	var params = $('#updateForm').serialize();
	$.ajax({
		type : "POST",
		url : baseUrl + "/Material/updateMaterial.html",
		data : params,
		dataType : "json",
		success : function(data) {
			alert(data.message);
			window.location.href=baseUrl + "/Material/material.html";
		}
	});
}

function del(i_id) {
	$.ajax({
		type : "POST",
		url : baseUrl + "/Material/deletMaterial.html",
		data : {i_id},
		dataType : "json",
		success : function(data) {
			alert(data.message);
			window.location.href=baseUrl + "/Material/material.html";
		}
	});
}


