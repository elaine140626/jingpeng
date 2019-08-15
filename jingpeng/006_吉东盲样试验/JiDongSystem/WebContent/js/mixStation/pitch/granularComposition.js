// 筛孔数据列表的列数组
var columns = [{title : "<img src='image/images2/jipeitou.jpg'>", field : "9999", align:"center", width : "10%"}];
// 新建/修改标识
var flag = "add";
$(function(){
	$("#locationText").html(orgName);
	// 获取当前权限下的物料名称
	getMaterialName(1);
	// 加载级配信息列
	initAsphaltGradingList();
});

//输入数字
function num(obj){
	obj.value = obj.value.replace(/[^\d.]/g,""); 
	obj.value = obj.value.replace(/^\./g,"");
	obj.value = obj.value.replace(/\.{2,}/g,".");
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
}

//获取当前权限下的物料名称
function getMaterialName(flag){
	$.ajax({
		type : "post",
		url : baseUrl + "/material/getMaterialName.action",
		async : false,
		data : {
			materialType : 0,
			materialMold: 1,
			orgId : orgId
		},
		success : function(data) {
			if (data.length > 0) {
				var html = '';
				var _html = '';
				if (flag == 1) {
					$.each(data, function(i, b) {
						html += "<option value='" + b.mateName + "'>" + b.mateName + "</option>"
					});
					$('#materialNames').html(html);
				} else if (flag == 2) {
					_html += "<option value=''>-- 请选择 --</option>";
					$.each(data, function(i, b) {
						_html += "<option value='" + b.mateNameId + "'>" + b.mateName + "</option>";
					});
					$('#materialName').html(_html);
				}
			}
		},
		error : function() {

		}
	});
}

// 物料变更
function changeMaterialName(flag) {
	if (flag == 1) {
		// 清空物料型号
		$("#material_Model").val("");
		$("#materialModels").empty();
		// 查询对应的物料型号
		getMaterialModel($("#material_Name").val(), flag);
	} else if (flag == 2) {
		// 清空物料型号
		$("#materialModel").val("");
		$("#materialModelList").empty();
		// 查询对应的物料型号
		getMaterialModel($("#materialName option:selected").val(), flag);
	}
}

//获取当前权限下物料名称下的物料型号
function getMaterialModel(materialName, flag) {
	var _url;
	if(flag == 1){
		_url = baseUrl + "/material/getMaterialModel.action";
	} else {
		_url = baseUrl + "/material/getMaterialModelByName.action";
	}
	var html = '';
	$.ajax({
		type : "post",
		url : _url,
		async : false,
		data : {
			materialName : materialName,
			orgId : orgId
		},
		success : function(data) {
			if (data.length > 0) {
				$.each(data, function(i, b) {
					if (flag == 1) {
						html += "<option value='" + b.mateModel + "'>" + b.mateModel + "</option>";
					} else {
						html += "<option value='" + b.Id + "'>" + b.Material_Model + "</option>";
					}
				});
			}
		},
		error : function() {

		}
	});
	if (flag == 1) {
		$('#materialModels').html(html);
	} else if (flag == 2) {
		$('#materialModel').html("<option value=''>-- 请选择 --</option>" + html);
	}
}

// 初始化筛孔checkbox列表
function initMeshCheckBoxList() {
	$.ajax({
		type : "POST",
		url : baseUrl + "/meshSize/queryMeshSizeList.action",
		data : {
			orgId: orgId
		},
		dataType : "json",
		async: false,
		success : function(data) {
			var _html = "";
			if (data) {
				for (var i = 0; i < data.length; i++) {
					_html += '<div style="float:left;width:10%;"><input type="checkbox" onclick="changeChecked(this)" style="width: 30%;" name="' + data[i].meshSize + '" value="' + data[i].id + '"><span>' + data[i].meshSize + '</span></div>';
				}
			}
			$("#meshList").html(_html);
		}
	});
}

//显示新建/修改弹窗
function show(id) {
	// 显示弹窗
	$('#loginModal').modal('show');
	// 初始化筛孔checkbox列表
	initMeshCheckBoxList();
	var params = {};
	if (id) {
		// 修改
		flag = "update";
		// 赋值到对应的表单项
		initForm(id);
		// 修改时，加载该条级配数据的筛孔模板列表的列
		getColumnModel(id);
		params.analysisId = id;
	} else {
		// 新建
		flag = "add";
		// 新建时，清空表单项
		initForm();
		// 新建时，清空筛孔模板列表的列
		columns = [{title: "<img src='../../image/images2/jipeitou.jpg'>", field: "9999", align:"center", width: "10%"}];
	}
	// 初始化筛孔模板列表
	initMeshModelList(columns, params);
	// 刷新列表，重新加载列
	$('#table').bootstrapTable('refreshOptions', {columns : columns});
}

// 初始化表单项
function initForm(id){
	// 新建时，清空表单项
	$("#grade_Code").val("");
	$("#materialName").empty("");
	$("#materialModel").empty("");
	$("#remarks").val("");
	if (id) {
		// 修改
		$("#grade_Code").attr("disabled","disabled");
		$.ajax({
			type : "POST",
			url : baseUrl + "/gradation/queryDataById.action",
			data : {
				id: id
			},
			dataType : "json",
			async: false,
			success : function(data) {
				$("#id").val(data.id);
				$("#grade_Code").val(data.gradeCode);
				getMaterialName(2);
				$("#materialName").val(data.materialName);
				getMaterialModel(data.materialName, 2);
				$("#materialModel").val(data.materialModel);
				$("#remarks").val(data.remarks);
			}
		});
	} else {
		$("#grade_Code").removeAttr("disabled");
		getMaterialName(2);
		$('#materialModel').html("<option value=''>-- 请选择 --</option>");
	}
}

// 加载该条级配数据的筛孔模板列表的列
function getColumnModel(id){
	columns = [{title: "<img src='../../image/images2/jipeitou.jpg'>", field: "9999", align:"center", width: "10%"}];
	$.ajax({
		type : "POST",
		url : baseUrl + "/gradation/getColumnModel.action",
		data : {
			id: id,
			orgId: orgId
		},
		dataType : "json",
		async: false,
		success : function(data) {
			$.each(data, function(k,v){
				// 将该条数据的筛孔checkbox变为选中状态
				$("input[name='" + v.meshSize + "']").attr('checked',true);
				// 将该条级配数据的筛孔添加到列表中
				columns.push({title : v.meshSize, field : v.id, align:"center", width : "10%"});
			})
		}
	});
}

//初始化筛孔模板列表
function initMeshModelList(columns, params) {
	$("#table").bootstrapTable('destroy'); 
	$('#table').bootstrapTable({
        url: baseUrl + '/meshSize/queryMeshSizeDataList.action',
        clickEdit: true,
        queryParams: params,
        columns: columns,
        /**
         * @param {点击列的 field 名称} field
         * @param {点击列的 value 值} value
         * @param {点击列的整行数据} row
         * @param {td 元素} $element
         */
        onClickCell: function(field, value, row, $element) {
            $element.attr('contenteditable', true);
            $element.blur(function() {
                // 当前点击的行
            	let index = $element.parent().data('index');
            	// 输入的值
                let tdValue = $element.html();
                // 当前点击的td在行中的位置
                var tdIndex = $element[0].cellIndex;
                // 所有th
                var thCells = $('#table').bootstrapTable()[0].children[0].children[0].cells;
                // 所在行的所有td
                var tdCells = $('#table').bootstrapTable()[0].children[1].children[index].cells;
                if(!(/^\d+(\.\d{0,2})?$/).test(tdValue * 1)){
                	swal({
        				title: "错误提示",
        				text: "请输入保留两位小数的有效数字!",
        				type: "error",
        				confirmButtonText: '确定',
        				cancelButtonFont: '微软雅黑',
        			},
        			function(){
                		saveData(index, field, "-");
                	});
                }else{
                	if ((tdValue * 1) > 100 ) {
                    	swal({
        					title: "错误",
        					text: "通过率不能大于100",
        					type: "error",
        					confirmButtonText: '确定',
        					cancelButtonFont: '微软雅黑',
        				},
        				function(){
        					saveData(index, field, "-");
        				});
                    } else if ((tdValue * 1) < 0) {
                    	swal({
        					title: "错误",
        					text: "通过率不能小于0",
        					type: "error",
        					confirmButtonText: '确定',
        					cancelButtonFont: '微软雅黑',
        				},
        				function(){
        					saveData(index, field, "-");
        				});
                    } else if ((tdValue * 1) == 100) {
                		// 通过率为100时，此筛孔之前的筛孔通过率均为100
                		for (var i = 1; i < tdIndex + 1; i++) {
                			saveData(index, thCells[i].attributes[1].value, tdValue);
                		}
                	} else if ((tdValue * 1) == 0) {
                		// 通过率为0时，此筛孔之前的筛孔通过率均为0
                		for (var i = tdIndex; i < thCells.length; i++) {
                			saveData(index, thCells[i].attributes[1].value, tdValue);
                		}
                	} else if ((tdValue * 1) > (tdCells[tdIndex - 1].textContent * 1)) {
                    	swal({
        					title: "错误",
        					text: "通过率不能大于上一筛孔通过率",
        					type: "error",
        					confirmButtonText: '确定',
        					cancelButtonFont: '微软雅黑',
        				},
        				function(){
        					saveData(index, field, "-");
        				});
                    }else if ((tdValue * 1) < (tdCells[tdIndex + 1].textContent * 1)){
                    	swal({
        					title: "错误",
        					text: "通过率不能小于下一筛孔通过率",
        					type: "error",
        					confirmButtonText: '确定',
        					cancelButtonFont: '微软雅黑',
        				},
        				function(){
        					saveData(index, field, "-");
        				});
                    }else {
                		saveData(index, field, tdValue);
                    }
                }
            })
        }
    });
}

// 行编辑临时保存(没提交到后台)
function saveData(index, field, value) {
	$('#table').bootstrapTable('updateCell', {
		index: index,       //行索引
		field: field,       //列名
		value: value        //cell值
	})
}

// 筛孔规格checkbox选中事件
function changeChecked(_obj) {
	var myColumns;
	if (_obj.checked == true){			// 选中
		// 将选中的筛孔添加到列表的列数组中
		columns.push({title : _obj.name, field : _obj.value, align:"center", width : "10%"});
		// 选中的筛孔从大到小进行排序
		myColumns = meshSort(columns);
	}else{								// 取消选中
		myColumns = columns;
		// 将取消选中的筛孔从列表的列数组中删除
		for (var i = 0; i < myColumns.length; i++) {
			if (_obj.name == myColumns[i].title) {
				myColumns.splice(i, 1);
				break;
			}
		}
	}
	// 刷新列表，重新加载列
	$('#table').bootstrapTable('refreshOptions', {columns : myColumns});
}

// 保存/修改
function saveOrUpdate() {
	// 级配信息
	var asphaltGrading = new Object();
	// 组织机构id
	asphaltGrading.orgId = orgId;
	// 级配编号
	var gradeCode = $("#grade_Code").val();
	if(gradeCode){
		asphaltGrading.gradeCode = gradeCode;
	}else{
		swal("操作失败","级配编号不能为空!","info");
		return;
	}
	// 产品型号
	var materialInfo = getProductId($("#materialName option:selected").val(), $("#materialModel option:selected").val());
	if(materialInfo){
		asphaltGrading.productId =materialInfo.id;
	}else{
		swal("操作失败","请选择产品名称/型号!","info");
		return;
	}
	// 备注
	asphaltGrading.remarks = $("#remarks").val();
	
	// 提交到后台的数据
	var tableArray = [];
	
	// 获取列表总行数
	var totalRows =  $('#table tr');
	for (var i = 0; i < totalRows.length; i++) {
		// 提交的当前行的数据
		var tr = [];
		// 获取当前行所有的td
		if (i == 0) {
			var ths = $('#table th');
			var th = new Object();
			for (var j = 1; j < ths.length; j++) {
				th["mesh" + j] = $(ths[j]).attr("data-field");
			}
			tableArray.push(th);
		} else {
			var tds = $('#table tr[data-index="' + (i - 1) + '"] td');
			var td = new Object();
			for (var j = 1; j < tds.length; j++) {
				td["mesh" + j] = tds[j].textContent;
			}
			tableArray.push(td);
		}
	}
	
	var _url;
	if (flag == "add") {
		_url = baseUrl + "/gradation/insertGradationInfo.action";
	} else {
		// 级配id
		asphaltGrading.id = $("#id").val();
		_url = baseUrl + "/gradation/updateGradationInfo.action";
	}
	var params = {};
	var tableArray2 = JSON.stringify(tableArray);
	var asphaltGrading2 = JSON.stringify(asphaltGrading);
	params.tableArray = tableArray2;
	params.asphaltGrading = asphaltGrading2;
	
	// 提交
	$.ajax({
		type : "POST",
		url : _url,
		data : params,
		dataType : "json",
		async: false,
		success : function(data) {
			if(data.code == "操作成功") {
				swal({
					title: data.message,
					text: "操作成功",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					$('#loginModal').modal('hide');
					// 重新加载列表
					initAsphaltGradingList();
				});
		 	}else {
		 		swal({
					title: data.message,
					text: "操作失败",
					type: "error",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					$('#loginModal').modal('hide');
					// 重新加载列表
					initAsphaltGradingList();
				});
		 	}
		}
	});
}

// 删除级配信息
function del(id) {
	// 提交
	$.ajax({
		type : "POST",
		url : baseUrl + "/gradation/deleteGradationInfo.action",
		data : {
			id: id
		},
		dataType : "json",
		async: false,
		success : function(data) {
			if(data.code == "操作成功") {
				swal({
					title: data.message,
					text: "操作成功",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					$('#loginModal').modal('hide');
					// 重新加载列表
					initAsphaltGradingList();
				});
		 	}else {
		 		swal({
					title: data.message,
					text: "操作失败",
					type: "error",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					$('#loginModal').modal('hide');
					// 重新加载列表
					initAsphaltGradingList();
				});
		 	}
		}
	});
}

// 对选中的筛孔从大到小进行排序(冒泡排序)
function meshSort(array) {
	var myArray = array;
	for (var i = 0; i < myArray.length; i++) {
		for (var j = 0; j < myArray.length - 1 - i; j++) {
			if ((myArray[j].title * 1) < (myArray[j + 1].title * 1)) {
				var temp = myArray[j + 1];
				myArray[j + 1] = myArray[j];
				myArray[j] = temp;
			}
		}
	}
	return myArray;
}

//根据产品名称和产品型号，获取产品ID
function getProductId(proNameId, proTypeId) {
	var materialInfo;
	$.ajax({
		type: "post",
		url: baseUrl + "/material/queryProductId.action",
		async: false,
		data: {
			proNameId: proNameId,
			proTypeId: proTypeId
		},
		dataType: 'json',
		success: function(data) {
			if (data.length > 0) {
				materialInfo = data[0];
			}
		}
	});
	return materialInfo;
}

// 加载级配信息列
function initAsphaltGradingList(queryParams){
	var table = $('#list').dataTable();
	table.fnDestroy();
	$("#list").DataTable({
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
			url : baseUrl + "/gradation/queryList.action",
			dataSrc : "data",
			data : {
				orgId: orgId,
				gradeCode: $("#proportion_Code").val(),
				materialName: $("#material_Name").val(),
				materialModel: $("#material_Model").val()
			}
		},
		"deferRender" : true,
		"columns" : [
				{
					"data" : "id"
				}, {
					"data" : "gradeCode"
				}, {
					"data" : "productId",
					render : function(data, type, row, meta) {
						if (data == null) {
							return "";
						} else {
							return row.materialName + " - " + row.materialModel;
						}
					}
				}, {
					"data" : "operator"
				}, {
					"data" : "createDate"
				}, {
					"data" : "remarks"
				},{
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
				} ],
		"order" : [ [ 1, 'asc' ] ],
		"createdRow" : function(row, data, dataIndex) {
	    	  console.log(data);
	      },
		"fnRowCallback" : function(nRow, aData, iDisplayIndex) {
			$("td:first", nRow).html(iDisplayIndex + 1);// 设置序号位于第一列，并顺次加一
			return nRow;
		}
	});
}