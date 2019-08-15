var baseUrl = "";
var addOrupdateTag = 1;//添加和修改的标识
$(function(){
	$("#locationText").html(cementOrgName);
	// 画面数据显示
	baseUrl = getContextPath();
	var param = {};
	param.orgId = cementOrgId;
	param.equipmentType = 1;//水泥混凝土拌和机
	if(cementOrgId != ''){
		getListInfo(param);
	}
});
function getListInfo(param){
	var table = $('#equipmentList').dataTable();
	table.fnDestroy();
	$("#equipmentList").DataTable({
        "paging": true,
        "lengthChange": false,
        "pageLength": 14,
        "searching": false,
        "ordering": false,
        "info": true,
        "sInfo": true,
        "autoWidth": false,
        "iDisplayStart" : 0,
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
            url: baseUrl + "/equipment/getAllEquipmentInfo.action",
            dataSrc: "data",
            data: function (d) {
                return param;
            }
        },
        "deferRender": true,
        "columns": [ 
			{"data" : "id"},
			{"data" : "equipmentNo"},
			{"data" : "equipmentName"},
			{"data" : "equipmentModel"},
			{
				"data" : "equiState",
				render : function(data,type,row){
					if(row.equiState == 0){
						data = " 不在线";
					}else if(row.equiState == 1){
						data = "在线但没有开启监控程序";
					}else if(row.equiState == 2){
						data = "在线并开启监控程序";
					}else if(row.equiState ==3){
						data = "开启监控程序下 突然计算机关闭或者拔出数据线";
					}
					return data;
				}
			},
			{"data" : "stateTime"},
			{"data" : "equipmentCode"},
			{
				"data" : "equipmentType",
				render : function(data,type,row){
					if(row.equipmentType == 0){
						data = "沥青拌和机";
					}else if(row.equipmentType == 1){
						data = "水泥混凝土拌和机";
					}else if(row.equipmentType == 2){
						data = "水泥稳拌和站";
					}
					return data;
				}
			},
			{"data" : "remarks"},
			{
				"data" : "id",
				render:function(data,type,row){
					var html = "";
					html += "<span><a onclick=updateEquipment("+row.id+")><img src=\"../../image/images/xiu.png\" width=\"17\" height=\"16\" alt=\"修改\" title=\"修改\"></a></span>";
    	  		    html +=	"<span><a onclick=deleteEquipment("+row.id+")><img src=\"../../image/images/del.png\" width=\"17\" height=\"16\" alt=\"删除\" title=\"删除\"></a></span>";
    	  		    return html;
				}
			}
			],
        "columnDefs": [{
        	"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
        }],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一         
            // 获取所有数据
            return nRow;
        }
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

//模糊查询
function search(){
	//拌合站名称
	var equipmentName = $("#equipment_Name").val();
	// 画面数据显示
	var param = {};
	param.orgId = cementOrgId;
	param.equipmentType = 1;//水泥混凝土拌和机
	param.equipmentName = equipmentName;
	if(cementOrgId != ''){
		getListInfo(param);
	}
}

//添加
function addEquipment(){
	var url = "";
	//获取页面数据
	var data = formToJson($("#addForm"));
	if(data.equipmentNo == null || data.equipmentNo == "") {
		swal("操作失败","设备编号不能为空!", "info");
		return;
	}
	if(data.equipmentName == null || data.equipmentName == "") {
		swal("操作失败","拌合设备名称不能为空!", "info");
		return;
	}
	if(data.equipmentModel == null || data.equipmentModel == "") {
		swal("操作失败","拌和机型号不能为空!", "info");
		return;
	}
	if(data.equiState == null || data.equiState == "") {
		swal("操作失败","设备状态不能为空!", "info");
		return;
	}
	if(data.stateTime == null || data.stateTime == "") {
		swal("操作失败","设备状态时间不能为空!", "info");
		return;
	}
	if(data.equipmentCode == null || data.equipmentCode == "") {
		swal("操作失败","拌和机标识不能为空!", "info");
		return;
	}
	if(data.equipmentType == null || data.equipmentType == ""){
		swal("操作失败","拌和机类型不能为空!", "info");
		return;
	}
	if(addOrupdateTag == 1) {
		data.orgId = cementOrgId;
		url =  baseUrl + "/equipment/addEquipmentInfo.action";
	} else {
		url =  baseUrl + "/equipment/updateEquipmentInfo.action";
	}
	$.ajax({
		type : "POST",
		url : url,
		data : data,
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
					search();
					$('#loginModal').modal('hide');
				});
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
}

//新增页面
function getAddView(){
	$("#tab input:text").val("");
	$("#equiState").val("");
	$("#equipmentType").val("");
    $("#loginModal").modal("show");
}

//修改
function updateEquipment(id){
	$("#loginModal").modal("show");
	addOrupdateTag = 2;
	$.ajax({
		type : "POST",
		url : baseUrl + "/equipment/getEquipmentInfoById.action",
		data : {"id" : id},
		dataType : "json",
		success : function(data) {
			$("#equipmentNo").val(data[0].equipmentNo);
			$("#equipmentName").val(data[0].equipmentName);
			$("#equipmentModel").val(data[0].equipmentModel);
			$("#equiState").val(data[0].equiState);
			$("#stateTime").val(data[0].stateTime);
			$("#equipmentCode").val(data[0].equipmentCode);
			$("#equipmentType").val(data[0].equipmentType);
			$("#remarks").val(data[0].remarks);
			$("#id").val(data[0].id);
		}
	});
}

//删除生产计划
function deleteEquipment(id){
	swal({
		title: "确定操作吗？",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
		
	},function(){
		$.ajax({
			type : "POST",
			url : baseUrl + "/equipment/deleteEquipmentInfo.action",
			data : {"id":id},
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
						search();
					}); },200);
				}else{
					setTimeout(function(){swal(data.message,"","error"); },200);
				}
			}
		});
	});
}