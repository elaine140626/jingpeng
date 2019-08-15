// 当前登录人姓名
var reviser = '';
// 用户觉得权限
var roleFlag = "";
// 用户信息
var userInfo = '';
$(function(){
	// 状态
	$("#conclusion").html(getDataDictionary('conclusion'));
	
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		
		// 当前登录人姓名
		reviser = userInfo.id;
		// 用户权限
		var list = userInfo.rolecode;
		if(list.indexOf(",3,") != -1 || list.indexOf(",13,") != -1){
			// 调度 && 调度管理员(查看)
			roleFlag = false;
			// 隐藏添加空白报告单的按钮
			$("#addBtn").hide();
		}
		if(list.indexOf(",4,") != -1){
			// 质检员(操作)
			roleFlag = true
			// 显示添加空白报告单的按钮
			$("#addBtn").show();
		}
		if(list.indexOf(",5,") != -1){
			// 质检主任(操作)
			roleFlag = true;
			// 显示添加空白报告单的按钮
			$("#addBtn").show();
		}
		if(list.indexOf(",0,") != -1){
			// 管理员(操作)
			roleFlag = true;
			// 显示添加空白报告单的按钮
			$("#addBtn").show();
		}
		if(list.indexOf(",7,") != -1){
			// 总经理(操作)
			roleFlag = true;
			// 显示添加空白报告单的按钮
			$("#addBtn").show();
		}
	}
	// 获取出库单检测list
	getList();
	// 获取空白报告单list
	getTestreportList();
})

// 获取出库单检测list
function getList(){
	// 查询
	// 兑换前物料信息
	var materielName = $("#materielName").val();  
	var materielModel = $("#materielModel").val();
	// 兑换后物料信息
	var exchangeMaterielName = $("#exchangeMaterielName").val();
	var exchangeMaterielModel = $("#exchangeMaterielModel").val();
	// 质检状态
	var conclusion = $("#conclusion").val();
	var table1 = $('#exportmeasure').dataTable();
	table1.fnDestroy();
	$("#exportmeasure").DataTable({
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
            url: "../../testreportsaledetailed/getExportmeasure.action",
            dataSrc: "data",
            data: function (d) {
          	var param = {};
                param.materielName = materielName;
                param.materielModel = materielModel;
                param.exchangeMaterielName = exchangeMaterielName;
                param.exchangeMaterielModel = exchangeMaterielModel;
                param.conclusion = conclusion;
                return param;//自定义需要传递的参数。
            }
        },
        "deferRender": true,
        "columns": 
        	[{
        	   "data": "id"
	        }, 
	        {
	            "data": "id",
	            render:function(data,type,row,meta) {
	            	var html = '';
	            	// 是否兑换
	            	if(row.isExchange == 0){
	            		// 用户权限
	            		if(roleFlag){
		            		html += "<a style='margin: 0 5px;' onclick=update("+row.id+",'0')><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"检测\" title=\"检测\"></a>";
				  		    // 兑换前
		            		if(row.aftExportStorageId != null){
				  		    	html +="<a style='margin: 0 5px;' onclick=print("+row.id+",'0')><img src=\"../../img/common/printing.png\" width=\"20\" height=\"20\" alt=\"打印\" title=\"打印\"></a>";	
				  		    }
		            	}else{
		            		html += "<a style='margin: 0 5px;' onclick=check("+row.id+",'0')><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
		            	}
	            	}
		  		    return html;
	            }
	        }, {
	            "data": "id",
	            render:function(data,type,row,meta) {
	            	var html = '';
	            	// 用户权限
	            	if(roleFlag){
	            		html += "<a style='margin: 0 5px;' onclick=update("+row.id+",'1')><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"检测\" title=\"检测\"></a>";
	            		// 兑换后
	            		if(row.befExportStorageId != null){
			  		    	html +="<a style='margin: 0 5px;' onclick=print("+row.id+",'1')><img src=\"../../img/common/printing.png\" width=\"20\" height=\"20\" alt=\"打印\" title=\"打印\"></a>";	
			  		    }
	            	}else{
	            		html += "<a style='margin: 0 5px;' onclick=check("+row.id+",'1')><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
	            	}
		  		    return html;
	            }
	        }, {
	            "data": "serialID"
	        }, 
	        {
	            "data": "plateNumber"
	        },
	        {
	            "data": "client"
	        }, 
	        {
	            "data": "customerName"
	        },
	        {
	            "data": "isExchange",
	            render:function(data,type,row,meta){
	            	var html="";
	            	if (data == "0") {
	            		html = "是"
	            	} else {
	            		html = "否"
	            	}
	            	return html;
	            }
	        }, 
	        {
	            "data": "materielName"
	        },
	        {
	            "data": "materielModel"
	        },
	        {
	            "data": "exchangeMaterielName"
	        },
	        {
	            "data": "exchangeMaterielModel"
	        },
	        {
	            "data": "address"
	        }, {
	            "data": "deliveryMan"
	        }, {
	            "data": "deliveryManPhone"
	        }, 
	        {
	        	"data": "factoryTime"
	        }
	        ],
		"createdRow" : function(row, data, dataIndex) {
		},
		"order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            $("td:first", nRow).html("<input name='radio' type='hidden' value=\""+ aData.id +"\" />"+(iDisplayIndex +1));//设置序号位于第一列，并顺次加一               	
           return nRow;
        }
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('#exportmeasure_info').css('text-align','center');
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

// 空发检测报告list
function getTestreportList(){
	
	// 查询
	// 报告名头
	var reportRenown = $("#reportRenown").val();
	// 报告编号
	var testReportNumber = $("#testReportNumber").val();
	var table1 = $('#testreportList').dataTable();
	table1.fnDestroy();
	$("#testreportList").DataTable({
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
            url: "../../testreportsaledetailed/getTestreportList.action",
            dataSrc: "data",
            data: function (d) {
            	var param = {};
            	param.reportRenown =reportRenown;
            	param.testReportNumber =testReportNumber;
                return param;//自定义需要传递的参数。
            }
        },
        "deferRender": true,
        "columns": 
        	[{
        	   "data": "id"
	        }, {
	            "data": "id",
	            render:function(data,type,row,meta) {
	            	var html = '';
	            	// 用户权限
	            	if(roleFlag){
	            		html += "<a style='margin: 0 5px;' onclick=update1("+row.id+")><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"检测\" title=\"检测\"></a>";
			  		    html +="<a style='margin: 0 5px;' onclick=print1("+row.id+")><img src=\"../../img/common/printing.png\" width=\"20\" height=\"20\" alt=\"打印\" title=\"打印\"></a>";	
	            	}else{
	            		html += "<a style='margin: 0 5px;' onclick=check1("+row.id+")><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
	            	}
		  		    return html;
	            }
	        }, {
	            "data": "reportRenown"
	        }, 
	        {
	            "data": "productModel"
	        },
	        {
	            "data": "testReportNumber"
	        }, 
	        {
	            "data": "indexTypeName"
	        },
	        {
	            "data": "testPersonnelName"
	        },
	        {
	            "data": "insideRemarks"
	        }
	        ],
		"createdRow" : function(row, data, dataIndex) {
		},
		"order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            $("td:first", nRow).html(iDisplayIndex +1);//设置序号位于第一列，并顺次加一               	
           return nRow;
        }
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('#testreport_info').css('text-align','center');
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

// 点击查询
function search(){
	getList();
}

// 点击查询（空白报告单）
function search1(){
	getTestreportList();
}

// 检测出库单项目
function update(id, isExchange){
	location.href = 'out_detection_edit.html?flag=1&id='+id+"&isExchange="+isExchange;
}

// 检测出库单项目（空白报告单）
function update1(id){
	location.href = 'out_detection_edit.html?flag=3&id='+id;
}

// 查看出库单项目
function check(id, isExchange){
	location.href = 'out_detection_edit.html?flag=2&id='+id+"checkFlag=1&isExchange="+isExchange;
}

// 查看出库单项目（空白报告单）
function check1(id){
	location.href = 'out_detection_edit.html?flag=2&id='+id+"checkFlag=2";
}

// 打印
function print(id, isExchange){
	location.href = 'out_detection_copy.html?flag=1&id='+id+"&isExchange="+isExchange;
}

// 打印（空白报告单）
function print1(id){
	location.href = 'out_detection_copy.html?flag=3&id='+id;
}

// 新增空白检测报告单
function addBtn(){
	location.href = 'out_detection_edit.html?flag=1';
}






