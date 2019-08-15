var _flag = "insert";

$(function(){
	$("#locationText").html(orgName);
	initDataTable();
});

// 初始化列表
function initDataTable () {
	// 初始化列表 
	$('#meshSizeTable').bootstrapTable({
		url: baseUrl + "/meshSize/queryMeshSizeList.action",
		pagination: true,
		sidePagination: 'client',
		pageNumber: 1,
		pageSize: 15,
		queryParams : {
			orgId: orgId
		},
		pageList: [15, 25, 50, 100], 
		columns: [
			{ title: "序号", field: "id", width: "10%", 
				formatter: function(value, row, index) {
				  	return index + 1;
			    }
			}, 
			{ title: "筛孔规格", field: "meshSize", width: "70%", sortable: true},
			{ title: "操作", field: "id", width: "20%",
			  formatter: function(value, row, index) {
			  	var _html = "";
			  	_html += "<a onclick=show("+index+")><img src=\"../../image/images/xiu.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>";
			  	_html += "<a onclick=deleteMesh("+row.id+")><img src=\"../../image/images/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>";
			  	return _html;
			  }
			}
		]
	});
}

// 显示新建/修改弹窗
function show(index) {
	// 修改的行数据
	var row;
	if (index != null) {
		// 获取当前修改的行对象
		row = $('#meshSizeTable').bootstrapTable('getData')[index];
		_flag = row.id;
	} else {
		_flag = "insert";
	}
	// 清空表单
	initModal(row);
	$('#loginModal').modal('show');
}

// 清空表单
function initModal(row) {
	if (row) {
		$("#meshSize").val(row.meshSize);
	} else {
		$("#meshSize").val("");
	}
}

// 修改/新建
function saveOrUpdate() {
	var params = {};
	// 筛孔规格
	var meshSize = $("#meshSize").val();
	if(meshSize){
		params.meshSize = meshSize;
	}else{
		swal("操作失败","筛孔规格不能为空!", "info");
		return;
	}
	// 级配id
	params.orgId = orgId;
	// 请求路径
	var _url = "";
	// 判断操作为新增还是修改
	if (_flag == "insert") {    // 新建
		_url += "/meshSize/insertMeshSizeList.action";
	} else {					// 修改 
		_url += "/meshSize/updateMeshSizeList.action";
		// 筛孔id
		params.id = _flag;
	}
	
	$.ajax({
		type : "POST",
		url : baseUrl + _url,
		data : params,
		dataType : "json",
		async: false,
		success : function(data) {
			if(data.code=="success"){
				swal({
					title: data.message,
					text: "操作成功",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					$('#loginModal').modal('hide');
					// 先销毁列表对象
					$("#meshSizeTable").bootstrapTable('destroy');
					// 重新加载列表
					initDataTable();
				});
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
}

// 删除筛孔
function deleteMesh(id) {
	var params = {};
	params.id = id;
	$.ajax({
		type : "POST",
		url : baseUrl + "/meshSize/deleteMeshSizeList.action",
		data : params,
		dataType : "json",
		async: false,
		success : function(data) {
			if(data.code=="success"){
				swal({
					title: data.message,
					text: "操作成功",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					$('#loginModal').modal('hide');
					// 先销毁列表对象
					$("#meshSizeTable").bootstrapTable('destroy');
					// 重新加载列表
					initDataTable();
				});
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
}