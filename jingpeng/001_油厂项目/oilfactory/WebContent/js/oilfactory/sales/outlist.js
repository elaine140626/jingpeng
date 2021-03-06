// 用户信息
var userInfo = '';
// 用户权限
var roleFlag = '';
$(document).ready(function() {
	// 树形信息显示
	showZtreeS();	
	// 按车辆查询
	plateNumberList();
	// 获取用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		var list = userInfo.rolecode;
		if(list.indexOf(",1,") != -1){
			// 销售员(查看)
			roleFlag = false;
		}
		
		if(list.indexOf(",2,") != -1){
			// 销售总监(查看)
			roleFlag = false;
		}
		
		if(list.indexOf(",3,") != -1 || list.indexOf(",13,") != -1){
			// 调度 && 调度管理员(操作)
			roleFlag = true;
		}	
		if(list.indexOf(",0,") != -1){
			// 管理员(操作)
			roleFlag = true;
		}
		if(list.indexOf(",7,") != -1){
			// 总经理(操作)
			roleFlag = true;
		}
	}
	// 画面数据显示
	var param = {};
	// 销售出库单list
	getSalesList(param);
	// 未称重出库单list
	getNoweighoutList(param);
//	// 来料加工list
//	getProcessList(param);
	
	$("#outTypeName").html(getDataDictionaryMultiple('ckdzt'));
	$("#outTypeName").fSelect();
		
	// 左侧菜单展开收起
	$('.shouqi').click(function() {
		$('#sider').removeClass('show');
		$('#sider').addClass('hiden');
		$('#box_top').hide();
		$('#box_center').hide();
		$('#tree').hide();
		$('#main').removeClass('mshow');
		$('#main').addClass('mhide');
		$('.zhankai').show();
		$('.shouqi').hide();
	})
	$('.zhankai').click(function() {
		$('#sider').removeClass('hiden');
		$('#sider').addClass('show');
		$('#box_top').show();
		$('#box_center').show();
		$('#tree').show();
		$('#main').removeClass('mhide');
		$('#main').addClass('mshow');
		$('.zhankai').hide();
		$('.shouqi').show();
	})
});

// 树形结构显示
function showZtreeS(){
	var zTree;
	var setting = {
			view: {
				dblClickExpand: false,
				showLine: true,
				selectedMulti: false,
				expandSpeed: ($.browser.msie && parseInt($.browser.version) <= 6) ? "" : "fast"
			},
			data : {
				simpleData : {
					enable : true,
				}
			},
			callback: {
				onClick: findClick

			}
		};

	// 节点集合
	var nodes = [];
	var pid = '';
	var ppid = '';
	var node = {};
	node =  {
			id : "客户合同信息",
			pId : 0,
			name : "客户合同信息",
		}
	nodes.push(node);
	
	$.ajax({
		type: "post",
		url: "../../outlist/getCustomerInfo.action",
		data: {"keyword":$("#keyword").val()},
		dataType: "json",
		success: function (data) {
			if (data != null && data.length > 0) {
				for (var i = 0; i < data.length; i++) {				
					// 客户名称变更时
					if (data[i].customerCode != pid) {					
						// 子节点-客户名称
						if (data[i].customerCode != null
								&& data[i].customerCode != '') {
							node = {
								id : data[i].customerCode,
								pId : "客户合同信息",
								name : data[i].customerCode,
							};
							nodes.push(node);
							
							// 下一个父节点-当前客户名称
							pid = data[i].customerCode;
						}
					}
					if (data[i].contractName != ppid){
						// 子节点-合同编号
						if (data[i].contractName != null
								&& data[i].contractName != '') {
							node = {
								id : data[i].contractName,
								pId : pid,
								name : data[i].contractName,
							};
							nodes.push(node);
							
							// 下一个父节点-当前合同编号
							ppid = data[i].contractName;
						}
					}						
						
					// 子子节点-销售订单编号
					if (data[i].orderNumber != null
							&& data[i].orderNumber != '') {
						node = {
								id : data[i].orderNumber,
								pId : ppid,
								name : data[i].orderNumber,
							};
						nodes.push(node);
					}											 
				}
			}
			$.fn.zTree.init($("#tree"), setting, nodes);
			zTree = $.fn.zTree.getZTreeObj("tree");
			// 默认展开所有节点
			zTree.expandAll(true);
		}
	});
}

// 关键字检索
function keywordSelect(){
	showZtreeS();
}

// 树形节点点击事件
function findClick(event, treeId, treeNode, isCancel) {
	var id = treeNode.id;
	var pid = treeNode.pId;
	var param = {};
	// 清空页面上检索信息
	$("#keyWords").val("");
	
	if(id == '客户合同信息' && treeNode.name == '客户合同信息'){
		// 总结点全部查询
		// 销售出库单list
		getSalesList(param);
		// 未称重出库单list
		getNoweighoutList(param);
//		// 来料加工list
//		getProcessList(param);
	}else if(pid == '客户合同信息'){
		param.client = id;
		// 销售出库单list
		getSalesList(param);
		// 未称重出库单list
		getNoweighoutList(param);
//		// 来料加工list
//		getProcessList(param);
	}else{
		// 合同编号
		if(id.indexOf("HT")!=-1){
			param.contractId = id;
			param.client = pid;
		} else if(id.indexOf("D")!=-1){
			// 销售订单编号
			param.orderNumber = id;
			param.contractId = pid;
		}
		
		// 销售出库单list
		getSalesList(param);
<<<<<<< .mine
		// 未称重出库单list
//		getNoweighoutList(param);
		// 来料加工list
//		getProcessList(param);
||||||| .r200
//		// 未称重出库单list
//		getNoweighoutList(param);
//		// 来料加工list
//		getProcessList(param);
=======
>>>>>>> .r322
	}
}

// 查询按钮
function selectInfoList(){	
	// 画面数据显示
	var param = {};
	var plateNumber = $("#plateNumber").val();
	param.plateNumber = plateNumber;
	var obj = $("#outTypeName").val();
	if(obj==null){
		var outTypeName = '';
	}else{
		var outTypeName = obj.join(',');
	}
	param.outTypeName = outTypeName;
	// 销售出库单list
	getSalesList(param);
	// 未称重出库单list
	getNoweighoutList(param);
//	// 来料加工list
//	getProcessList(param);
}

// 画面list数据显示
function getSalesList(param){
	//获取列表 刷新
	var table = $('#salesList').dataTable();	
	table.fnDestroy();
	param.type = "true";
	$("#salesList").DataTable({
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
			type : "post",
			url : "../../outlist/getExportList.action",
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
        {
            "data": "id",
            render: function(data, type, row) {
            	var html = '';
            	if(roleFlag){
            		// 二次称重后 才能调拨 退货
                	if (row.grossWeight != null && row.tareWeight != null){
                		// 出库单状态 0：正常 1：调拨 2：退货 3:空发 4:兑换 5.兑换时调拨
                		if (row.outType == 0 || row.outType == 4) {
                			// 运输结算情况 不是客户自提
                			if(row.transportBalances != 1){
                				html +="<img src='../../img/common/all.png' title='调拨' onclick='halfTransfer("+data+",1)' class='all'>";	
                			}
                			html +=	"<img src='../../img/common/all_return.png' title='退货' onclick='refund("+data+")' class='allReturn'>";
                		}
                		else if (row.outType == 1 || row.outType == 5){
                			html +=	"<img src='../../img/common/all_return.png' title='退货' onclick='refund("+data+")' class='allReturn'>";
                		} else {
                			html +="<img src='../../img/common/all.png' style='color:red'title='调拨' class='all'>";
                    		html +=	"<img src='../../img/common/all_return.png' title='退货'  class='allReturn'>";
                		}
                	}
            	}
            	
	  		    return html;
            }
        },
        {
            "data": "serialId",
            render: function(data, type, row) {
				var html = "<a href='outlist_details.html?id="+row.id+"&outType="+row.outType+"'>"+data+"</a>";
				return html;
            }
        },
        {
            "data": "plateNumber"
        },
        {
        	"data": "productID",
            render: function(data, type, row) {
            	if(row.materielName != null){
            		if (row.outType == 4 || row.outType == 5) {
            			data = "由 " + row.materielName2 + "--" + row.materielModel2 + " 兑换为 " + row.materielName + "--" + row.materielModel 
            				+ "</br>兑换比例为" + row.proportion + "%";
            			return data;
            		} else {
            			data = row.materielName + "--" + row.materielModel;
            			return data;	
            		}
            	}else{
            		return "";
            	}
            }
        },
        {
        	"data": "customerCode"
        },
        {
            "data": "client",
            render: function(data, type, row) {
            	var _html = "";
            	if (data && data.length > 10) {
            		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
            	} else {
            		_html += "<span title='"+data+"'>"+data+"</span>";
            	}
            	return _html;
            }
        },
        {
            "data": "address",
            render: function(data, type, row) {
            	var _html = "";
            	if (data && data.length > 9) {
            		_html += "<span title='"+data+"'>"+data.substring(0,9) + "..." +"</span>";
            	} else {
            		_html += "<span title='"+data+"'>"+data+"</span>";
            	}
            	return _html;
            }
        },
        {
            "data": "customerAlias",
            render: function(data, type, row) {
            	var _html = "";
            	if (data && data.length > 10) {
            		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
            	} else {
            		_html += "<span title='"+data+"'>"+data+"</span>";
            	}
            	return _html;
            }
        },
        {
            "data": "outTypeName"
        },
        {
            "data": "outTypeMark",
            render: function(data, type, row) {
				if(data == 1){
					data = "是";
				}else{
					data = "否";
				}
				return data;
            }
        },
        {
            "data": "factoryTime"
        },
        {
            "data": "weight",
            render: function(data, type, row) {
            	var _html = "";
            	if (row.tareWeight != null) {
            		_html = "<span>一次称重</span>";
            	} 
            	if (row.grossWeight != null){
            		_html = "<span>二次称重</span>";
            	}
            	return _html;
            }
        },
        {
            "data": "suttle"
        }, 
        {
            "data": "goodsPrice"
        },
        {
        	 "data": "contractNumber"
        }, {
            "data": "orderNumber"
        }, 
        {
            "data": "customerAlias",
            render: function(data, type, row) {
            	var _html = "";
            	if (data && data.length > 10) {
            		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
            	} else {
            		_html += "<span title='"+data+"'>"+data+"</span>";
            	}
            	return _html;
            }
        },  {
            "data": "carOwner",
            render: function(data, type, row) {
				data = row.deliveryMan + "--" + row.deliveryManPhone;
				return data;
            }
        }, {
            "data": "consignee",
            render: function(data, type, row) {
				data = row.consignee + "--" + row.consigneePhone;
				return data;
            }
        }, {
            "data": "testReportNumber",
        }, 
        {
            "data": "facingSlipNum",
            render: function(data, type, row) {
            	var _html = "";
            	if (data) {
            		if (data && data.length > 10) {
                		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
                	} else {
                		_html += "<span title='"+data+"'>"+data+"</span>";
                	}
				}
            	return _html;
            }
        },
        {
            "data": "goodsCost"
        }],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            $("td:first", nRow).html(iDisplayIndex +1);//设置序号位于第一列，并顺次加一     
            if(aData.outType != 0 && aData.outType != 4 && aData.outType != 1 && aData.outType != 5){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#CCCCCC");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");                	
            }
           return nRow;
        }
    });
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

// 未称重出库单list
function getNoweighoutList(param){
	//获取列表 刷新
	var table = $('#noweighoutList').dataTable();
	table.fnDestroy();	
	$("#noweighoutList").DataTable({
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
			type : "post",
			url : "../../outlist/getNoweighoutList.action",
			dataSrc : "data",
			data : function(d) {
				return param;
			}
		},
        "deferRender": true,
        "columns": [ 
    	{
            "data": "id",
            swidth:"80px"
        }, {
            "data": "serialId",
            render: function(data, type, row) {
            	if (row.type == "1") {
            		var html = "<a href='outlist_details.html?id="+row.dispatchOutWarehouseId+"&outType=1'>"+data+"</a>";
    				return html;	
				}else{
					var html = "<a href='not_weighing_edits.html?id="+row.id+"&outType=1'>"+data+"</a>";
					return html;	
				}
            },
            swidth:"100px"
        }, {
            "data": "carName"
        }, {
        	"data": "productID",
            render: function(data, type, row) {
            	if(row.materielName != null){
        			data = row.materielName + "--" + row.materielModel;
        			return data;	
            	}else{
            		return "";
            	}
            }
        }, {
            "data": "customerCode",
            render: function(data, type, row) {
            	var _html = "";
            	if (data && data.length > 10) {
            		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
            	} else {
            		_html += "<span title='"+data+"'>"+data+"</span>";
            	}
            	return _html;
            }
        }, {
            "data": "customerName",
            render: function(data, type, row) {
            	var _html = "";
            	if (data && data.length > 10) {
            		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
            	} else {
            		_html += "<span title='"+data+"'>"+data+"</span>";
            	}
            	return _html;
            }
        }, {
            "data": "address"
        }, {
            "data": "customerAlias",
            render: function(data, type, row) {
            	var _html = "";
            	if (data && data.length > 10) {
            		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
            	} else {
            		_html += "<span title='"+data+"'>"+data+"</span>";
            	}
            	return _html;
            }
        }, {
            "data": "type",
            render: function(data, type, row) {
            	if (data == "0") {
            		return "正常";
				}else{
					return "调拨";	
				}
            }
        }, {
            "data": "isCarryOff",
            render: function(data, type, row) {
            	if(data == 1){
            		data = "否";
            	}else{
            		data = "是";
            	}
				return data;
            }
        }, {
            "data": "saleNumber"
        }, {
            "data": "settlementWeight"
        }, {
        	 "data": "contractNumber"
        }, {
            "data": "orderNumber"
        }, {
            "data": "consignee"
        }, {
            "data": "consigneePhone"
        }, {
            "data": "transportBalanceName"
        }, {
            "data": "carOwner"
        }, {
            "data": "carOwnerTelephone"
        }, {
            "data": "taxRateData"
        }, {
            "data": "remarks"
        }, {
            "data": "testReportNumber"
        }
        ],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            $("td:first", nRow).html(iDisplayIndex +1);//设置序号位于第一列，并顺次加一               	
           return nRow;
        }
    });
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

////来料加工list
//function getProcessList(param){
//	//获取列表 刷新
//	var table = $('#processList').dataTable();
//	table.fnDestroy();	
//	param.type = "true";
//	$("#processList").DataTable({
//        "paging": true,
//        "lengthChange": false,
//        "pageLength": 14,
//        "searching": false,
//        "ordering": false,
//        "info": true,
//        "sInfo": true,
//        "autoWidth": false,
//        "iDisplayStart" : 0,
//        "language": {
//            "lengthMenu": "每页 _MENU_ 条记录",
//            "zeroRecords": "没有找到记录",
//            "info": "第 _PAGE_ 页,共 _PAGES_ 页 (显示_END_ 条，共 _TOTAL_ 项)",
//            "infoEmpty": "无记录",
//            "sSearch": "在结果中查找：",
//            "infoFiltered": "(从 _MAX_ 条记录过滤)",
//            "oPaginate": {
//                "sFirst": "第一页",
//                "sPrevious": "上一页",
//                "sNext": "下一页",
//                "sLast": "最后一页"
//            },
//        },
//        "ajax" : {
//			type : "post",
//			url : "../../outlist/getProcessList.action",
//			dataSrc : "data",
//			data : function(d) {
//				return param;
//			}
//		},
//        "deferRender": true,
//        "columns": [ 
//    	{
//            "data": "id"
//        }, {
//            "data": "serialID",
//            render: function(data, type, row) {
//            	if (row.enterTypeMark == "3") {
//            		var html = "<a href='outlist_details.html?id="+row.outWarehouseId+"&outType=2'>"+data+"</a>";
//    				return html;
//				}else{
//					var html = "<a onclick='pageJump("+row.id+","+row.enterTypeMark+")'>"+data+"</a>";
//					return html;	
//				}
//            }
//        }, {
//            "data": "plateNumber"
//        }, {
//        	"data": "productID",
//            render: function(data, type, row) {
//            	if(row.materielName != null){
//        			data = row.materielName + "--" + row.materielModel;
//        			return data;	
//            	}else{
//            		return "";
//            	}
//            }
//        }, {
//            "data": "client",
//            render: function(data, type, row) {
//            	var _html = "";
//            	if (data && data.length > 10) {
//            		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
//            	} else {
//            		_html += "<span title='"+data+"'>"+data+"</span>";
//            	}
//            	return _html;
//            }
//        }, {
//            "data": "enterTypeMark",
//            render: function(data, type, row) {
//            	if (data == "2") {
//            		return "来料加工";
//				}else{
//					return "退货";	
//				}
//            }
//        }, {
//            "data": "customerAlias",
//            render: function(data, type, row) {
//            	var _html = "";
//            	if (data && data.length > 10) {
//            		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
//            	} else {
//            		_html += "<span title='"+data+"'>"+data+"</span>";
//            	}
//            	return _html;
//            }
//        }, {
//        	 "data": "contractNumber"
//        }, {
//            "data": "suttle"
//        }, {
//            "data": "carOwner"
//        }, {
//            "data": "carOwnerTelephone"
//        }, {
//            "data": "remarks",
//            render: function(data, type, row) {
//            	var _html = "";
//            	if (data && data.length > 10) {
//            		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
//            	} else {
//            		_html += "<span title='"+data+"'>"+data+"</span>";
//            	}
//            	return _html;
//            }
//        }
//        ],
//        "order": [[ 1, 'asc' ]],
//        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
//            $("td:first", nRow).html(iDisplayIndex +1);//设置序号位于第一列，并顺次加一               	
//           return nRow;
//        }
//    });
//	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
//}

//车辆检索框赋值
function plateNumberList(){
	$.ajax({
		type: "post",
		url: "../../outlist/getPlateNumberList.action",
		data: {},
		dataType: "json",
		success: function (data) {
			if(data != null && data.length > 0){
				// 先清空
				$("#plateNumbers").html("");
				for(var i=0; i< data.length; i++){
					$("#plateNumbers").append("<option value="+data[i]+">"+data[i]+"</option>");
				}
			}
		}
	});
}

// 修改
function update(id, outTypeMark){
	// 出库单
	if(outTypeMark == 0){
		$.fancybox({
			'href': 'outbound_edit.html?id='+id+"&flag=1",
			'width': 800,
			'height': 425,
			'type': 'iframe',
			'hideOnOverlayClick': false,
			'showCloseButton': false,
			'onClosed': function() {
				window.location.href = 'outbound.html';
			}
		});
	}else{	
		// 未入厂出库单
		$.fancybox({
			'href': 'outunbound_edit.html?id='+id+"&flag=1",
			'width': 800,
			'height': 425,
			'type': 'iframe',
			'hideOnOverlayClick': false,
			'showCloseButton': false,
			'onClosed': function() {
				window.location.href = 'outbound.html';
			}
		});
	}	
}

//销售合同编号
function getContractList(id){
	var Contract = '';
	$.ajax({
		type: "post",
		url: "../../instore/getContractList.action",
		data:{"id":id},
		dataType: "json",
		async:false,
		success: function (data) {
			Contract = data;
		}
	});
	return Contract;
}

//销售订单编号
function getOrderNumberList(id){
	var OrderNumber = '';
	$.ajax({
		type: "post",
		url: "../../outbound/getOrderNumberList.action",
		data:{
			"id":id,
			"type":"show"
		},
		dataType: "json",
		async:false,
		success: function (data) {
			OrderNumber = data;
		}
	});
	return OrderNumber;
}

// 作废或者删除
function updateValidFlag(id, flag){
	$.ajax({
		type: "post",
		url: "../../outbound/updateValidFlag.action",
		data: {"id":id, "flag":flag},
		dataType: "json",
		success: function (data) {
			if(data.code == 'success'){
				var param = {};
				getInfoList(param);		
			}else{
				swal({
					title: "错误提示",
					text: data.message,
					type: data.code,
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				});
			}	
		}
	});
}

// 退货
function refund(id){
	$.fancybox({
		'href' : 'goods_returned_note.html?id='+id,
		'width' : 800,
		'height' : 335,
		'type' : 'iframe',
		'hideOnOverlayClick' : false,
		'showCloseButton' : false,
		'onClosed' : function() {
			window.location.href = 'outlist.html';
		}
	})
}

// 半车调拨
function halfTransfer(id,flags){
	$.fancybox({
		'href' : 'half_car_dial.html?id='+id+'&flags='+flags,
		'width' : 800,
		'height' : 450,
		'type' : 'iframe',
		'hideOnOverlayClick' : false,
		'showCloseButton' : false,
		'onClosed' : function() {
			window.location.href = 'outlist.html';
		}
	})
}

// 来料加工的票据单号
function pageJump(id, enterTypeMark){
	if(enterTypeMark == 0){
		$.fancybox({
			'href' : "../dispath/instore_edit.html?id="+id+"&flag=2",
			'width' : 800,
			'height' : 390,
			'type' : 'iframe',
			'hideOnOverlayClick' : false,
			'showCloseButton' : false,
			'onClosed' : function() {
				window.location.href = 'outlist.html';
			}
		})
	}else if(enterTypeMark == 2){
		// 来料加工
		$.fancybox({
			'href': '../dispath/inbound_edit.html?id='+id+"&flag=2",
			'width' : 800,
			'height' : 390,
			'type' : 'iframe',
			'hideOnOverlayClick' : false,
			'showCloseButton' : false,
			'onClosed' : function() {
				window.location.href = 'outlist.html';
			}
		});
	}
}

// 未称重出库单
function transfer(id,flags){
	$.fancybox({
		'href' : '../sales/not_weighing_edit.html?id='+id+'&flag='+flags,
		'width' : 800,
		'height' : 390,
		'type' : 'iframe',
		'hideOnOverlayClick' : false,
		'showCloseButton' : false,
		'onClosed' : function() {
			window.location.href = 'outlist.html';
		}
	})
}
