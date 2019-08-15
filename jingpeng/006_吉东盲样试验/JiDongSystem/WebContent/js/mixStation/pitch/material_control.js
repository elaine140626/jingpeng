var materialMold = '';
var materialName = '';
var materialModel = '';
var orgIds = '';
var materialType = materialTypes;
var material_Code = '';
var pramsId = '';
$('#str_material_Type').val(materialTypes);
$(function() {
	
	if(materialType == 0) {
		orgIds = orgId;
		$("#locationText").html(orgName);
		$("#location").html('沥青混凝土拌合站')
	} else {
		orgIds = cementOrgId;
		$("#locationText").html(cementOrgName);
		$("#location").html('水泥混合料拌合站')
	}
	// 初始化列表
	getMetarialList(materialMold, materialName, materialModel, materialType);
	
	//获取当前权限下的物料名称
	getMaterialName();
	
	//切换物料名称，加载物料型号
	$('#material_Name,#str_material_Names').change(function() {
		$('#material_Model').val('');
		$('#materialModels').empty();
		$('#str_material_Models').val('');
		$('#str_material_Model').empty();
		if ($('#material_Name').val() != '') {
			getMaterialModel($('#material_Name').val());
		}
		if ($('#str_material_Names').val() != '') {
			getMaterialModel($('#str_material_Names').val());
		}
	})

});
//获取当前权限下的物料名称
function getMaterialName(){
	$.ajax({
		type : "post",
		url : baseUrl + "/material/getMaterialName.action",
		async : true,
		data : {
			materialType : materialType,
			orgId : orgIds
		},
		success : function(data) {
			if (data.length > 0) {
				var html = '';
				$.each(data, function(i, b) {
					html += "<option value='" + b.mateName + "'>" + b.mateName
							+ "</option>"
				});
				$('#materialNames').html(html);
				$('#str_material_Name').html(html);
			}
		},
		error : function() {

		}
	});
}

//获取当前权限下物料名称下的物料型号
function getMaterialModel(str) {
	$.ajax({
		type : "post",
		url : baseUrl + "/material/getMaterialModel.action",
		async : true,
		data : {
			materialName : str,
			orgId : orgIds
		},
		success : function(data) {
			if (data.length > 0) {
				var html = '';
				$.each(data, function(i, b) {
					html += "<option value='" + b.mateModel + "'>"
							+ b.mateModel + "</option>"
				});
				$('#materialModels').html(html);
				$('#str_material_Model').html(html);
			}
		},
		error : function() {

		}
	});
}
//获取当前权限下的物料单位
function getMeasureUnit(){
	$.ajax({
		type : "post",
		url : baseUrl + "/material/getMeasureUnitList.action",
		async : true,
		data : {
			materialType : materialType,
			orgId : orgIds
		},
		success : function(data) {
			console.log(data);
			if (data.length > 0) {
				var html = '';
				$.each(data, function(i, b) {
					if(b) {
						html += "<option value='" + b.measureUnit + "'>" + b.measureUnit
						+ "</option>"
					}
				});
				$('#str_material_Units').html(html);
			}
		},
		error : function() {

		}
	});
}
//修改 新增弹窗显示
function show(id) {
	//加载物料单位list
	getMeasureUnit();
	
	pramsId = id;
	$('#loginModal').modal('show');
	empty();
	if (id == '') {//新增物料信息
		$('#modalTitle').html('新增物料信息');
		$('#continuous').show();
	} else {//修改物料信息
		$('#modalTitle').html('修改物料信息');
		$('#continuous').hide();
		$.ajax({
			 type : "post",
			 url : baseUrl + "/material/getMaterialList.action",
			 async : true,
			 data : { 
				id:id,
				materialType : materialType,
				orgId : orgIds,
			 },
			 success : function(data) {
			 	$('#str_material_Code').val(data.data[0].materialCode);
			 	material_Code = data.data[0].materialCode;
			 	$("#str_material_Code").attr("disabled","disabled");
				$('#str_material_Names').val(data.data[0].mateName);
				$('#str_material_Models').val(data.data[0].mateModel);
				getMaterialModel($('#str_material_Names').val());
				$('#d_density').val(data.data[0].density);
				$('#str_measure_Unit').val(data.data[0].measureUnit);
				$('#str_material_Mold').val(data.data[0].materialMold);
			 }
		});
	}
}
//删除物料信息
function del(id) {
	swal({
		title : "删除物料信息",
		text : "确定要删除吗？",
		type : "warning",
		showCancelButton : true,
		confirmButtonText : '确定',
		cancelButtonText : "取消",
		closeOnConfirm : false
	}, function() {
		$.ajax({
			 type : "post",
			 url : baseUrl + "/material/delMaterial.action",
			 async : true,
			 data :  {
				 validFlag:0,
				 id:id,
				 orgId:orgIds,
			 },
			 success : function(data) {
				 if(data.code == "操作成功") {
			 		swal("删除成功",data.message, "success");
			 		
			 	}else {
			 		swal("删除失败",data.message, "error");
			 	}
			 	getMaterialName();
			 	getMetarialList(materialMold, materialName, materialModel, materialType);
			 	
			 }
		});
		
	});
	

}
function empty(){
	$('#str_material_Code').val('');
	$("#str_material_Code").removeAttr("disabled");
	$('#str_material_Names').val('');
	$('#str_material_Models').val('');
	$('#str_material_Model').empty();
	$('#d_density').val(null);
	$('#str_measure_Unit').val(null);
	$('#str_material_Mold').val("0");
	
}
//输入数字
function num(obj){
	var re = /^[+-]?\d*\.?\d{0,4}$/;
	var temp = obj.value.toString().split('.');
     if (!re.test(obj.value)) {
		obj.value = temp[0]+"."+temp[1].substring(0,4);
        return false;
     }
//		obj.value = obj.value.replace(/[^\d.]/g,""); 
//		obj.value = obj.value.replace(/^\./g,"");
//		obj.value = obj.value.replace(/\.{2,}/g,".");
//		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
//		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
}
function add(){
	var isChecked = $('#continuouSure').is(':checked');
	var materialCode = $('#str_material_Code').val();
	var mateNameId = $('#str_material_Names').val();
	var mateModelId = $('#str_material_Models').val();
	var density = $('#d_density').val();
	var measureUnit = $('#str_measure_Unit').val();
	if(materialCode == "") {
		swal("操作失败","请输入物料编码", "info");
		return;
	}
	if(mateNameId == "") {
		swal("操作失败","请输入物料名称", "info");
		return;
	}
	if(mateModelId == "") {
		swal("操作失败","请输入规格型号", "info");
		return;
	}
	var param = formToJson($('#addForm'));
	param.orgId = orgIds;
	if(pramsId) {
		param.id = pramsId;
		param.materialCode = material_Code;
		$.ajax({
			 type : "post",
			 url : baseUrl + "/material/updateMaterial.action",
			 async : false,
			 data :  param,
			 success : function(data) {
			 	if(data.code == "操作成功") {
			 		swal("修改成功",data.message, "success");
			 		$('#loginModal').modal('hide');
			 	}else {
			 		swal("修改失败",data.message, "error");
			 	}
			 	getMaterialName();
			 	getMetarialList(materialMold, materialName, materialModel, materialType);
			 }
		});
	}else {
		$.ajax({
			 type : "post",
			 url : baseUrl + "/material/insertMaterialInfo.action",
			 async : false,
			 data :  param,
			 success : function(data) {
			 	if(data.code == "操作成功") {
			 		swal("操作成功",data.message, "success");
			 		if(!isChecked) {	
			 			$('#loginModal').modal('hide');
			 		}
			 	}else {
			 		swal("操作失败",data.message, "error");
			 	}
			 	empty();
			 	$('#continuouSure').removeAttr('checked');
			 	getMaterialName();
			 	getMetarialList(materialMold, materialName, materialModel, materialType);
			 }
		});
	}
	
}
function getMetarialList(a, b, c, d) {
	var table = $('#materialList').dataTable();
	table.fnDestroy();
	$("#materialList")
			.DataTable(
					{
						"paging" : true,
						"lengthChange" : false,
						"pageLength" : 14,
						"searching" : false,
						"ordering" : false,
						"info" : true,
						"sInfo" : true,
						"autoWidth" : false,
						"iDisplayStart" : 0,
						scrollY : "100%",
						scrollX : true,
						scrollCollapse : true,
						"language" : {
							"lengthMenu" : "每页 _MENU_ 条记录",
							"zeroRecords" : "没有找到记录",
							"info" : "第 _PAGE_ 页,共 _PAGES_ 页",
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
							type : "post",
							url : baseUrl + "/material/getMaterialList.action",
							dataSrc : "data",
							data : {
								materialMold : a,
								materialName : b,
								materialModel : c,
								materialType : d,
								orgId : orgIds,
								id:""
							}
						},
						"deferRender" : true,
						"columns" : [
								{
									"data" : "id"
								},
								{
									"data" : "id",
									render : function(data, type, row) {
										var html = '';
										html += '<span class="globalLoginBtn"><a href="javascript:void(0)" onclick="show('
												+ data
												+ ');"><img src="../../image/images/xiu.png"width="17" height="16" alt="修改" title="修改"></a></span><span><a href="javascript:void(0)" onclick="del('
												+ data
												+ ');"><img src="../../image/images/del.png" width="18" height="16" alt="删除" title="删除"></a></span>';
										return html;
									}
								},
								{
									"data" : "materialCode"
								},
								{
									"data" : "mate",
									render : function(data, type, row, meta) {
										return row.mateName + "/"
												+ row.mateModel;
									}
								}, {
									"data" : "density",
									render : function(data, type, row, meta) {
										if (data == null) {
											return "";
										} else {
											return data
										}
									}
								}, {
									"data" : "measureUnit",
									render : function(data, type, row, meta) {
										if (data == null) {
											return "";
										} else {
											return data
										}
									}
								}, {
									"data" : "materialMold",
									render : function(data, type, row, meta) {
										if (data == 0) {
											data = "原材料";
										} else if (data == 1) {
											data = "产成品";
										} else if (data == 2) {
											data = "半成品";
										}
										return data;
									}
								}, {
									"data" : "operator",
									render : function(data, type, row, meta) {
										if (data == null) {
											return "";
										} else {
											return data
										}
									}
								}, {
									"data" : "createDate"
								} ],
						"order" : [ [ 1, 'asc' ] ],
						"fnRowCallback" : function(nRow, aData, iDisplayIndex) {
							$("td:first", nRow).html(iDisplayIndex + 1);// 设置序号位于第一列，并顺次加一
							return nRow;
						}

					});
}
function search() {
	materialName = $('#material_Name').val();
	materialMold = $("#material_Mold").val();
	materialModel = $('#material_Model').val();
	getMetarialList(materialMold, materialName, materialModel, materialType);
}