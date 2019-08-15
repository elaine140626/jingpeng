// 用户信息
var userInfo = '';
//用户权限
var roleFlag = "";
$(document).ready(function() {

	// 获取物料类别(有问题暂时隐藏)
	/*var materielType = getDataDictionary("1");
	$("#materielType").html(materielType);*/
	
	// 树形信息显示
	showZtreeS();
	
	// 按车辆查询
	plateNumberList();

	// 获取用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		// 权限判断
		var list = userInfo.rolecode;

		if(list.indexOf(",3,") != -1 || list.indexOf(",13,") != -1){
			// 调度&&调度管理员(操作)
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
	// 画面数据显示
	var param = {};
	getInfoList(param);
	
	// 新增出库单
	$("#addBtn").fancybox({
		'href': 'outEmptyBound_edit.html',
		'width': 800,
		'height': 600,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'outEmptyBound.html';
		}
	})
	
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
	var pid = 0;
	var ppid = '';
	var salesOrderId = "";
	var node = {};
	node = {
		id : "客户订单信息",
		pId : 0,
		name : "客户订单信息"
	};
	nodes.push(node);
	
	$.ajax({
		type: "post",
		url: "../../ExportMeasure/getCustomerOrderList.action",
		data: {"keyword":$("#keyword").val(),"flag":3},
		dataType: "json",
		success: function (data) {
			if (data != null && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					
					// 客户名称变更时
					if (data[i].customerCode != pid) {
						// 父节点-标题名称
						pid = "客户订单信息";
						
						// 子节点-客户名称
						if (data[i].customerCode != null
								&& data[i].customerCode != '') {
							node = {
								id : data[i].customerCode,
								pId : pid,
								name : data[i].customerCode
							};
							nodes.push(node);
							
							// 父节点-客户名称
							ppid = data[i].customerCode;
						}

						// 子节点-销售订单编号
						if (data[i].salesOrderId != null
								&& data[i].salesOrderId != '') {
							node = {
								id : data[i].orderNumber,
								pId : ppid,
								name : data[i].orderNumber
							};
							nodes.push(node);
							salesOrderId = data[i].salesOrderId;
						}

						// 下一个父节点-当前客户名称
						pid = data[i].customerCode;
					} else {
						// 客户名称相同时
						if(data[i].salesOrderId != salesOrderId){
							// 子节点-销售订单编号
							if (data[i].salesOrderId != null
									&& data[i].salesOrderId != '') {
								node = {
									id : data[i].orderNumber,
									pId : ppid,
									name : data[i].orderNumber
								};
								nodes.push(node);
							}
						}
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
	$("#plateNumber").val("");
	$("#startTime").val("");
	$("#endTime").val("");
	if(id == '客户订单信息'){
		// 总结点全部查询
		getInfoList(param)
	}else if(pid == "客户订单信息"){
		param.client = id;
		// 根据客户名称查询
		getInfoList(param)
	}else{
		// 根据客户名称和销售订单id查询
		param.orderNumber = id;
		param.client = pid;
		// 根据客户名称查询
		getInfoList(param)
	}
}

// 车辆检索框赋值
function plateNumberList(){
	$.ajax({
		type: "post",
		url: "../../ExportMeasure/getPlateNumberList.action",
		data: {"flag":3},
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
	var plateNumber = $("#plateNumber").val();
	// var materielType = $("#materielType").val();
	param.startTime = startTime;
	param.endTime = endTime;
	param.plateNumber = plateNumber;
	// param.materielType = materielType;
	getInfoList(param);
}

// 画面list数据显示
function getInfoList(param){
	// 出库单状态（3：空发）
	param.flag = 3;
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
			url : "../../ExportMeasure/getExportMeasureList.action",
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
                	html +="<a class='fb' onclick=copy("+row.id+")><img src=\"../../img/common/copy.png\" width=\"20\" height=\"20\" alt=\"复制\" title=\"复制\"></a>";
            	}
            	html +="<a class='fb' onclick=update("+row.id+","+row.outTypeMark+","+row.suttle+","+row.outType+")><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
            	return html;
            }
        }, {
        	 "data": "serialId"
        }, {
            "data": "plateNumber"
        },{
        	"data": "productId",
            render: function(data, type, row) {
    			data = row.materielName + "--" + row.materielModel;
    			return data;	
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
	       	 "data": "contractId",
	         render: function(data, type, row) {
	         	var html = "";
	         	if(row.contractNumber != null && row.contractNumber != ''){
	         		html = "<span class='hetongbianhao hetong' >"+row.contractNumber+"</span>";
	         	}
	         	return html;
	       }
	    }, {
            "data": "salesOrderId",
             render: function(data, type, row) {
             	var html = "";
             	if(row.orderNumber != null && row.orderNumber != ''){
             		html = "<span class='hetongbianhao hetong'>"+row.orderNumber+"</span>";
             	}
             	return html;
           }
        },{
            "data": "saleNumber"
        }, {
            "data": "outTypeName"
        }, 
        {
            "data": "exmIsExamine",
            render: function(data, type, row) {
            	var html = "";
            	if(data != null){
            		if(data == 0){
                		html = "审核通过"
                	}else if(data == 1){
                		html = "审核未通过"
                	}	
            	}else{
            		html = "未审核";
            	}
				return html;
            }
        },
        {
            "data": "salesName"
        }, {
            "data": "carOwner",
            render: function(data, type, row) {
				data = row.deliveryMan + "--" + row.deliveryManPhone;
				return data;
            }
        }, {
            "data": "address",
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
            "data": "consignee",
            render: function(data, type, row) {
				data = row.consignee + "--" + row.consigneePhone;
				return data;
            }
        }, {
            "data": "testReportNumber"
        }, {
            "data": "factoryTime"
        }, {
            "data": "expectedDeliveryDate"
        }, {
            "data": "suttle"
        }, {
            "data": "isTesting",
            render: function(data, type, row) {
				if(data == 0){
					data = "是";
				}else{
					data = "否";
				}
				return data;
            }
        }
        , {
            "data": "outWarehouseId",
            render: function(data, type, row) {
            	var _html = "";
            	if (data) {
            		if (data.length > 20) {
            			_html += "<span title='"+data+"'>"+data.substring(0,20) + "..." +"</span>";
            		} else {
            			_html += "<span title='"+data+"'>"+data+"</span>";
            		}
            	}
            	return _html;
            }
        }, {
            "data": "id",
            render: function(data, type, row) {
            	var _html = "";
            	if (row.isLowerHair == 0){
            		_html+="已下发生产计划"
            	}
            	if (row.isInspector == 0){
            		_html+="==>质检员审核工艺通知单"
            	}
            	if (row.isExamine == 0){
            		_html+="==>主任审核工艺通知单"
            	}
            	if (row.isCheck == 0){
            		_html+="==>生产任务核对"
            	}
            	if (row.isProduction == 0){
            		_html+="==>确认生产"
            	}
            	if (row.isApplication == 0){
            		_html+="==>提交质检申请"
            	}
            	if (row.isQualified == 0){
            		_html+="==>质检合格"
            	}
            	if (row.isQualified == 1 && row.isAdjust == 1){
            		_html+="==>质检不合格,不需要调整"           	
            	}
            	if (row.isComplete == 0){
            		_html+="==>生产完成"
            	}
            	if (row.isConfirmSubmission == 0){
            		_html+="==>确认实际消耗"
            	}
            	if (row.tareWeight != null){
            		_html+="==>一次称重"
            	}
            	if (row.grossWeight != null){
            		_html+="==>二次称重,已发车"
            	}
            	if (_html.substring(0, 3) == "==>"){
            		_html = _html.substring(3,_html.length)
            	}
            	var html = "";
            	if (_html.length > 0){
            		html = "<a onclick='swal("+'"进度查询"'+","+'"'+_html+'"'+");'>查看进度</a>"
            	}
            	return html;
            }
        }],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            $("td:first", nRow).html("<input name='radio' type='hidden' value=\""+ aData.id +"\" />"+(iDisplayIndex +1));//设置序号位于第一列，并顺次加一          
            // 作废变色
            if(aData.validFlag == 0){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#999999");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");                	
            }

           return nRow;
        }
    });
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

// 修改
function update(id, outTypeMark, tareWeight, outType) {
	// 出库单
	$.fancybox({
		'href' : 'outEmptyBound_edit.html?id=' + id + "&flag=0&rolecode=false",
		'width' : 800,
		'height' : 600,
		'type' : 'iframe',
		'hideOnOverlayClick' : false,
		'showCloseButton' : false,
		'onClosed' : function() {
			window.location.href = 'outEmptyBound.html';
		}
	});
}

// 复制
function copy(id){
	$.fancybox({
		'href': "outEmptyBound_edit.html?id="+id+"&flag=0",
		'width': 800,
		'height': 600,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'outEmptyBound.html';
		}
	});
}
