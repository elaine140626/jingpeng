var outBoundInfoList;
var infoList;
$(document).ready(function() {
	// 树形信息显示
	showZtreeS();
	
	// 画面数据显示
	var param = {};
	getOutBoundInfoList(param);
	
	$('.shouqi').click(function() {
		$('#sider').css({
			width: '20px',
			border: 'none'
		})
		$('#box_top').hide();
		$('#box_center').hide();
		$('#tree').hide();
		$('#main').css({
			left: '20px',
		})
		$('.zhankai').show();
		$('.shouqi').hide();
	})
	$('.zhankai').click(function() {
		$('#sider').css({
			width: '260px',
			border: '1px solid #DEDFDF'
		})
		$('#box_top').show();
		$('#box_center').show();
		$('#tree').show();
		$('#main').css({
			left: '265px',
		})
		$('.zhankai').hide();
		$('.shouqi').show();
	})
});

//树形结构显示
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
		url: "../../instore/getSalesContractList.action",
		data: {"keyword":$("#keyword").val()},
		dataType: "json",
		success: function (data) {
			if (data != null && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					
					// 客户名称变更时
					if (data[i].customerName != pid) {
						// 父节点-标题名称
						pid = "客户订单信息";
						
						// 子节点-客户名称
						if (data[i].customerName != null
								&& data[i].customerName != '') {
							node = {
								id : data[i].customerName,
								pId : pid,
								name : data[i].customerName
							};
							nodes.push(node);
							
							// 父节点-客户名称
							ppid = data[i].customerName;
						}

						// 子节点-销售合同编号
						if (data[i].contractId != null
								&& data[i].contractId != '') {
							node = {
								id : data[i].contractNumber,
								pId : ppid,
								name : data[i].contractNumber
							};
							nodes.push(node);
							contractNumber = data[i].contractId;
						}

						// 下一个父节点-当前客户名称
						pid = data[i].customerName;

					} else {
						// 客户名称相同时
						if(contractNumber != data[i].contractId){
							// 子节点-销售合同编号
							if (data[i].contractId != null
									&& data[i].contractId != '') {
								node = {
									id : data[i].contractNumber,
									pId : ppid,
									name : data[i].contractNumber
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
//树形节点点击事件
function findClick(event, treeId, treeNode, isCancel) {
	var id = treeNode.id;
	var pid = treeNode.pId;
	var param = {};
	// 清空页面上检索信息
	$("#customerAlias1").val("");
	if(id == '客户订单信息'){
		// 总结点全部查询
		getOutBoundInfoList(param);
	}else if(pid == "客户订单信息"){
		param.client = id;
		// 根据客户名称查询
		getOutBoundInfoList(param);
	}else{
		// 根据客户名称和销售订单id查询
		param.orderNumber = id;
		param.client = pid;
		// 根据客户名称查询
		getOutBoundInfoList(param);
	}
}
//关键字检索
function keywordSelect(){
	showZtreeS();
}
//查询按钮
function selectInfoList(){
	// 画面数据显示
	var param = {};
	var plateNumber = $("#plateNumber").val();
	param.plateNumber = plateNumber;
	getOutBoundInfoList(param);
}

// 出库单列表
function getOutBoundInfoList(param){
	outBoundInfoList = [];
	//获取列表 刷新
	var table = $('#outBoundTable').dataTable();
	table.fnDestroy();
	
	$("#outBoundTable").DataTable({
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
        fixedColumns:   {
            leftColumns:7
        },
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
			url : "../../repertory/getInstoreInfoList.action",
			dataSrc : "data",
			data : function(d) {
				return param;
			}
		},
        "deferRender": true,
        "columns": [ 
	    	{
	            "data": "id"
	        },{
	            "data": "serialID"
	        },
	        {
	            "data": "plateNumber",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },
	        {
	            "data": "productID",
	            render: function(data, type, row) {
					data = row.materielName + "--" + row.materielModel;
					return data;
	            }
	        },
	        {
	            "data": "enterTypeMark",
	            render: function(data, type, row) {
					if (data == 0 || data == 1) {
						return "入库单";
					} else if (data == 2) {
						return "来料加工";
					} else if (data == 3) {
						return "退货";
					} else {
						return "";
					}
	            }
	        },{
	            "data": "supplierId",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },
	        {
	            "data": "startAddress",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },
	        {
	            "data": "enterDate",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },
	        {
	            "data": "lbsNetWeight",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data / 1000;
	            	}
	            }
	        },{
	            "data": "suttle",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data / 1000;
	            	}
	            }
	        },{
	            "data": "riseLoss",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "lbsGrossWeight",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "tareWeight",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "supplierDeviation",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "realityDeviation",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "abnormalFlag",
	            render: function(data, type, row) {
	            	if (data) {
	            		return "是";
	            	} else {
	            		return "否";
	            	}
	            }
	        },{
	            "data": "abnormalRemarks",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "abnormalOperator",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "abnormalDatetime",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },
	        {
	            "data": "pSerialId",
	            render: function(data, type, row) {
	            	if(data != null) {
			            if (row.enterTypeMark == 0) {
		            		return data;
		            	} else {
		            		return "";
		            	}
	            	}else{
	            		return "";
	            	}
	            }
	        },
	        {
	            "data": "pSerialId",
	            render: function(data, type, row) {
	            	if(data != null) {
			            if (row.enterTypeMark != 0) {
		            		return data;
		            	} else {
		            		return ""
		            	}
	            	}else{
	            		return "";
	            	}
	            }
	        },
	        {
	            "data": "deliveryMan",
	            render: function(data, type, row) {
	            	if(row.deliveryMan == undefined && row.deliveryManPhone == undefined){
	            		return "";
	            	}else{
	            		return row.deliveryMan + "--" + row.deliveryManPhone;
	            	}
	            }
	        },
	        {
	            "data": "ruchang",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },
	        {
	            "data": "creater",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },
	        {
	            "data": "createrDate",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        }
        ],
		"order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            $("td:first", nRow).html(iDisplayIndex +1);//设置序号位于第一列，并顺次加一               	
           return nRow;
        }
    });
}

function mouseover(id){
		//鼠标放在合同编号，显示飘窗
		$('#contractNumber'+id).show();

}

function mouseout(id){
		//鼠标离开合同编号，飘窗消失
		$('#contractNumber'+id).hide();
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
