// 用户信息
var userInfo = '';
//用户权限
var roleFlag = "";

var username;
$(document).ready(function() {	
	// 获取用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		// 权限判断
		var list = userInfo.rolecode;
		username = userInfo.username;
		if(list.indexOf(",3,") != -1 || list.indexOf(",13,") != -1){
			// 调度&&调度管理员(操作)
			roleFlag = true;
			$("#addBtn").show();
		}
		
		if(list.indexOf(",8,") != -1){
			// 库管(操作)
			roleFlag = true;
			$("#addBtn").show();
		}
		
		if(list.indexOf(",0,") != -1){
			// 管理员(操作)
			roleFlag = true;
			$("#addBtn").show();
		}
		if(list.indexOf(",7,") != -1){
			// 总经理(操作)
			roleFlag = true;
			$("#addBtn").show();
		}
	}
	
	// 下拉列表显示
	getDatalistInfo();
	// 画面数据显示
	var param = {};
	getInfoList(param);
	
	// 新增出库单
	$("#addBtn").fancybox({
		'href': 'exportmeasureother_edit.html',
		'width': 800,
		'height': 500,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'exportmeasureother.html';
		}
	})
});


// 查询按钮
function selectInfoList(){
	// 开始时间和结束时间的判定
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	// 开始时间大于结束时间
	if(startTime > endTime){	
		swal({
			title: "错误提示",
			text: "开始时间大于结束时间!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
		
	}else if(endTime != "" && startTime == ""){
		// 开始时间为空
		swal({
			title: "错误提示",
			text: "开始时间不能为空!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	// 画面数据显示
	var param = {};
	param = formToJson($("#form"));
	getInfoList(param);
}

// 画面list数据显示
function getInfoList(param){
	//获取列表 刷新
	var table = $('#listTable').dataTable();
	table.fnDestroy();
	
	$("#listTable").DataTable({
        "paging": true,
        "lengthChange": false,
        "pageLength": 14,
        "searching": false,
        "ordering": false,
        "info": true,
        "sInfo": true,
        "autoWidth": false,
        "iDisplayStart" : 0,
        scrollY:        "100%",
        scrollX:        true,
        scrollCollapse: true,
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
			type : "post",
			url : "../../exportmeasureother/getExportmeasureotherList.action",
			dataSrc : "data",
			data : function(d) {
				return param;
			}
		},
        "deferRender": true,
        "columns": [ 
	    	{
	            "data": "id"
	        },
	        {"data": "id",
		    	render:function(data,type,row,meta) {
					var html = '';
					html += '<a onclick="print('+ row.id + ',\'' + row.unit +'\')" style="text-decoration:none" href="javascript:;" title="打印">打印</a>&nbsp;';
					html += "<a onclick='update("+row.id+")' style='text-decoration:none' href='javascript:;' title='编辑'>编辑</a>&nbsp;";
					html += "<a onclick='del("+row.id+")' style='text-decoration:none' href='javascript:;' title='删除'>删除</a>";
					return html;
				}},
			{"data": "client"},
			{"data": "plateNumber"},
			{"data": "serial_ID"},
			{"data": "materielName"},
			{"data": "materielModel"},
			{"data": "grossWeight",
				render:function(data,type,row,meta) {
					if(data != 0){
						return data
					}else{
						return ''
					}
				}},
			{"data": "tareWeight",
				render:function(data,type,row,meta) {
					if(data != 0){
						return data
					}else{
						return ''
					}
				}},
			{"data": "suttle",
				render:function(data,type,row,meta) {
					if(data != 0){
						return data
					}else{
						return ''
					}
				}},
			{"data": "measureTime"},
			{"data": "unit"},
			{"data": "deliveryMan"},
			{"data": "deliveryManPhone"},
			{"data": "consigneePhone"},
			{"data": "fleetName"},
			{"data": "isSelfMention",
				render:function(data,type,row,meta) {
					var html = '';
					if(data == 0){
						return '是'
					}else{
						return '否'
					}
				}}
    	],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            $("td:first", nRow).html("<input name='radio' type='hidden' value=\""+ aData.id +"\" />"+(iDisplayIndex +1));//设置序号位于第一列，并顺次加一          
        }
    });
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

//删除称重信息
function del(id){
	swal({
		title: "确定要删除吗？",
		text:"删除后将无法恢复，请谨慎操作！",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
	},
	function(index) {
		$.ajax({
			type: 'POST',
			url: '../../exportmeasureother/deleteExportmeasureother.action',
			data: {"id":id, "reviser":username},
			dataType: 'json',
			success: function(data){
				setTimeout(function(){swal({
					title: data.message,
					type: data.code,
					cancelButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					window.location.href="exportmeasureother.html";
				}); },200);
			}
		});
	});
}
//编辑称重信息
function update(id){
	$.fancybox({
		'href': "exportmeasureother_edit.html?id="+id,
		'width': 800,
		'height': 500,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'exportmeasureother.html';
		}
	});
}

//打印出库单信息
function print(id,unit){
	if(unit.indexOf("瑞德") != -1){
		//瑞德小票
		location.href = '../dispath/exportmeasureother_print.html?id='+id;
	}else{
		//其它公司小票		
		location.href = '../dispath/exportmeasureother_print2.html?id='+id;
	}
}

//查询条件下拉列表
function getDatalistInfo(){
	$.ajax({
		type : "post",
		url : "../../exportmeasureother/getDataList.action",
		data : {},
		async : false,
		dataType : "json",
		success : function(data) {
			if (data != null) {
				var ClientList = data.clientList;
				var PlateNumberList = data.plateNumberList;
				var DeliveryManList = data.deliveryManList;
				var FleetNameList = data.fleetNameList;
				var MaterielNameList = data.materielNameList;
				var MaterielModelList = data.materielModelList;
				// 客户名称
				if (ClientList != null && ClientList.length > 0) {
					$("#client").empty();
					$("#client").append("<option value=''>请选择</option>");
					for (var i = 0; i < ClientList.length; i++) {
						if(  ClientList[i] != null &&  ClientList[i] != ''){
							$("#client").append("<option>" + ClientList[i] + "</option>");
						}
					}
				}
				// 车牌号码
				if (PlateNumberList != null && PlateNumberList.length > 0) {
					$("#plateNumber").empty();
					$("#plateNumber").append("<option value=''>请选择</option>");
					for (var i = 0; i < PlateNumberList.length; i++) {
						if(  PlateNumberList[i] != null &&  PlateNumberList[i] != '')
						{$("#plateNumber").append("<option>" + PlateNumberList[i] + "</option>");}
					}
				}
				// 承运人
				if (DeliveryManList != null && DeliveryManList.length > 0) {
					$("#deliveryMan").empty();
					$("#deliveryMan").append("<option value=''>请选择</option>");
					for (var i = 0; i < DeliveryManList.length; i++) {
						if(  DeliveryManList[i] != null &&  DeliveryManList[i] != '')
						{$("#deliveryMan").append("<option>" + DeliveryManList[i] + "</option>");}
					}
				}
				// 车队名称
				if (FleetNameList != null && FleetNameList.length > 0) {
					$("#fleetName").empty();
					$("#fleetName").append("<option value=''>请选择</option>");
					for (var i = 0; i < FleetNameList.length; i++) {
						if(  FleetNameList[i] != null &&  FleetNameList[i] != '')
						{$("#fleetName").append("<option>" + FleetNameList[i] + "</option>");}
					}
				}
				// 产品名称
				if (MaterielNameList != null && MaterielNameList.length > 0) {
					$("#materielName").empty();
					$("#materielName").append("<option value=''>请选择</option>");
					for (var i = 0; i < MaterielNameList.length; i++) {
						if(  MaterielNameList[i]  != null &&  MaterielNameList[i]  != '')
						{$("#materielName").append("<option>" + MaterielNameList[i] + "</option>");}
					}
				}
				// 规格型号
				if (MaterielModelList != null && MaterielModelList.length > 0) {
					$("#materielModel").empty();
					$("#materielModel").append("<option value=''>请选择</option>");
					for (var i = 0; i < MaterielModelList.length; i++) {
						if(  MaterielModelList[i] != null &&  MaterielModelList[i] != '')
						{$("#materielModel").append("<option>" + MaterielModelList[i] + "</option>");}
					}
				}
			}
		}
	});
}
