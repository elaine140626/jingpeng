var outBoundInfoList;
var infoList;
$(document).ready(function() {
	$('#ulList li').click(function() {
		$('#ulList li').removeClass('click');
		$('.sale').hide();
		$('#saleDiv' + $(this).index()).show();
		$(this).addClass('click');
		$('#saleDiv1').css('margin-top',"0px");
	})
	// 树形信息显示
	showZtreeS();
	
	// 画面数据显示
	var param = {};
	getInfoList(param);
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
		url: "../../outbound/getSalesOrderList.action",
		data: {
			"keyword":$("#keyword").val(),
			flag:"Y"
		},
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
						pid = data[i].customerName;

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
//树形节点点击事件
function findClick(event, treeId, treeNode, isCancel) {
	var id = treeNode.id;
	var pid = treeNode.pId;
	var param = {};
	// 清空页面上检索信息
	$("#customerAlias1").val("");
	if(id == '客户订单信息'){
		// 总结点全部查询
		getInfoList(param);
		getOutBoundInfoList(param);
	}else if(pid == "客户订单信息"){
		param.client = id;
		// 根据客户名称查询
		getInfoList(param);
		getOutBoundInfoList(param);
	}else{
		// 根据客户名称和销售订单id查询
		param.orderNumber = id;
		param.client = pid;
		// 根据客户名称查询
		getInfoList(param);
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
	getInfoList(param);
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
            leftColumns: 4
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
			url : "../../repertory/getOutBoundInfoList.action",
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
	            "data": "serialId"
	        },
	        {
	            "data": "plateNumber",
	            render: function(data, type, row) {
	            	if (data) {
	            		return data;
	            	}
	            }
	        },{
	            "data": "isExchange",
	            render: function(data, type, row) {
	            	var result = ""
	            	if (data == 0) {
	            		result += "由 " + row.materielName2 + "--" + row.materielModel2 + " 兑换为 " + row.materielName + "--" + row.materielModel;
	            	} else {
	            		result += row.materielName + "--" + row.materielModel;
	            	}
	            	return result;
	            }
	        },
	        {
	            "data": "client",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },
	        {
	            "data": "address",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },
	        {
	            "data": "customerAlias",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },
	        {
	            "data": "outTypeName",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
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
	            "data": "isEmpty",
	            render: function(data, type, row) {
					if(data == "Y"){
						data = "是";
					}else{
						data = "否";
					}
					return data;
	            }
	        },
	        {
	            "data": "factoryTime",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },
	        {
	            "data": "suttle",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data / 1000;
	            	}
	            }
	        },{
	            "data": "amount",
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
	            "data": "goodsPrice",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },
	        {
            	"data": "sumMoney",
	            render: function(data, type, row) {
	            	if(row.freightMoney == undefined && row.escortMoney == undefined){
	            		return "";
	            	}else{
	            		return row.freightMoney + row.escortMoney;
	            	}
	            }
	        },
	        {
            	"data": "contractNumber",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
//	             	var html = "";
//	             	if(row.contractNumber != null && row.contractNumber != ''){
//	             		html = "<span class='hetongbianhao hetong' onmouseover='mouseover("+row.id+",0)'  onmouseout ='mouseout("+row.id+",0)'>"+row.contractNumber+"</span>";
//	             		var Contract = getContractList(row.contractId);
//	             		// 获取销售合同信息
//	                 	html += "<div id='contractNumber"+row.id+"' class='fuchuang4'>"
//	     							+"<table style='border-top: 1px solid #ECECEC;'>"
//	     							+"	<tr>"
//	     							+"		<td class='hetongzhuangtai'>合同名称：</td>"
//	     							+"		<td class='hetongzhuangtai'>"+(Contract[0].contractName == null?"":Contract[0].contractName)+"</td>"
//	     							+"		<td class='hetongzhuangtai'>合同日期：</td>"
//	     							+"		<td class='hetongzhuangtai'>"+(Contract[0].contractDate == null?"":Contract[0].contractDate)+"</td>"
//	     							+"	</tr>"
//	     							+"	<tr>"
//	     							+"		<td class='hetongzhuangtai'>客户名称：</td>"
//	     							+"		<td class='hetongzhuangtai'>"+(Contract[0].customerName == null?"":Contract[0].customerName)+"</td>"
//	     							+"		<td class='hetongzhuangtai'>销售公司名称：</td>"
//	     							+"		<td class='hetongzhuangtai'>"+(Contract[0].salesCompanyName == null?"":Contract[0].salesCompanyName)+"</td>"
//	     							+"	</tr>"
//	     							+"	<tr>"
//	     							+"		<td class='hetongzhuangtai'>销售数量(t)：</td>"
//	     							+"		<td class='hetongzhuangtai'>"+(Contract[0].saleNumber == null?"":Contract[0].saleNumber)+"</td>"
//	     							+"		<td class='hetongzhuangtai'>是否来料加工：</td>"
//	     							+"		<td class='hetongzhuangtai'>"+(Contract[0].isIncoming == null?"":Contract[0].isIncoming)+"</td>"
//	     							+"	</tr>"
//	     							+"</table>"
//	     							+"</div>";
//	             	}
//	             	
//	             	return html;
	            }
	        },
	        {
	            "data": "purchaseContractId",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
//	             	var html = "";
//	             	if(row.purchaseContractId != null && row.purchaseContractId != ''){
//	             		html = "<span class='hetongbianhao hetong' onmouseover='mouseover("+row.id+",1)'  onmouseout ='mouseout("+row.id+",1)'>"+row.purchaseContractId+"</span>";
//	             		var OrderNumber = getOrderNumberList(row.salesOrderId);
//	             		
//	             		// 获取销售合同信息
//	                 	html += "<div id='orderNumber"+row.id+"' class='fuchuang3'>"
//	     							+"<table style='border-top: 1px solid #ECECEC;'>"
//	     							+"	<tr>"
//	     							+"		<td class='hetongzhuangtai'>客户名称：</td>"
//	     							+"		<td class='hetongzhuangtai'>"+(OrderNumber[0].customerName == null?"":OrderNumber[0].customerName)+"</td>"
//	     							+"		<td class='hetongzhuangtai'>客户别称：</td>"
//	     							+"		<td class='hetongzhuangtai'>"+(OrderNumber[0].customer == null?"":OrderNumber[0].customer)+"</td>"
//	     							+"	</tr>"
//	     							+"	<tr>"
//	     							+"		<td class='hetongzhuangtai'>销售公司名称：</td>"
//	     							+"		<td class='hetongzhuangtai'>"+(OrderNumber[0].salesCompanyName == null?"":OrderNumber[0].salesCompanyName)+"</td>"
//	     							+"		<td class='hetongzhuangtai'>是否实际发货：</td>"
//	     							+"		<td class='hetongzhuangtai'>"+(OrderNumber[0].isActualDelivery == null?"":OrderNumber[0].isActualDelivery)+"</td>"
//	     							+"	</tr>"
//	     							+"	<tr>"
//	     							+"		<td class='hetongzhuangtai'>联系人：</td>"
//	     							+"		<td class='hetongzhuangtai'>"+(OrderNumber[0].contacts == null?"":OrderNumber[0].contacts)+"</td>"
//	     							+"		<td class='hetongzhuangtai'>联系方式：</td>"
//	     							+"		<td class='hetongzhuangtai'>"+(OrderNumber[0].telephone == null?"":OrderNumber[0].telephone)+"</td>"
//	     							+"	</tr>"
//	     							+"</table>"
//	     							+"</div>";
//	             	}
//	             	
//	             	return html;
	            }
	        },
	        {
            	"data": "userName",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
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
            	"data": "consignee",
	            render: function(data, type, row) {
	            	if(row.consignee == undefined && row.consigneePhone == undefined){
	            		return "";
	            	}else{
	            		return row.consignee + "--" + row.consigneePhone;
	            	}
	            }
	        },
	        {
            	"data": "testReportNumber",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },
	        {
            	"data": "facingSlipNum",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        }
	        /*{
	            "data": "endAddress",
	            render: function(data, type, row) {
	            	var	result = "";
	            	if (data) {
	            		if (data.indexOf('辽宁') > 0) {
	            			result = "省内";
	            		} else {
	            			result = "省外";
	            		}
	            	}
	            	return result;
	            }
	        }*/
        ],
		"order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            $("td:first", nRow).html(iDisplayIndex +1);//设置序号位于第一列，并顺次加一               	
           return nRow;
        }
    });
}

function mouseover(id,flag){
	if(flag == 0){
		//鼠标放在合同编号，显示飘窗
		$('#contractNumber'+id).show();
	}else{
		//鼠标放在合同编号，显示飘窗
		$('#orderNumber'+id).show();
	}
}

function mouseout(id,flag){
	if(flag == 0){
		//鼠标离开合同编号，飘窗消失
		$('#contractNumber'+id).hide();
	}else{
		//鼠标离开合同编号，飘窗消失
		$('#orderNumber'+id).hide();
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

// 未称重出库单列表
function getInfoList(param){
	infoList = [];
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
        fixedColumns:   {
            leftColumns: 2
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
			url : "../../repertory/getUnBoundInfoList.action",
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
	        },{
	            "data": "factoryTime",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "companyName",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "carName",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "materielInfoId",
	            render: function(data, type, row) {
	            	var result = ""
	            	if (data) {
	            		result += row.materielName + "--" + row.materielModel;
	            	}
	            	return result;
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
	            "data": "amount",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data / 1000;
	            	}
	            }
	        },{
	            "data": "amount",
	            render: function(data, type, row) {
	            	var	result = (row.suttle * 1) - (row.amount * 1);
	            	return result / 1000;;
	            }
	        },{
	            "data": "remarks",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "orderNumber",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "salePrice",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
            	"data": "userName",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	        	"data": "consignee",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "consigneePhone",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "isCarryOff",
	            render: function(data, type, row) {
	            	if (data == true || data == false) {
	            		return data == false ? "是" : "否";
	            	} else {
	            		return "";
	            	}
	            }
	        },{
	            "data": "otherDelivery",
	            render: function(data, type, row) {
	            	if (data != null) {
	            		if (data == "") {
	            			return data;
	            		} else {
	            			if (data == 0) {
	            				return "邮寄";
	            			} else if (data == 1) {
	            				return "其他";
	            			}
	            		}
	            	} else {
	            		return "";
	            	}
	            }
	        },{
	            "data": "postNumber",
	            render: function(data, type, row) {
	            	if (data == null) {
	            		return "";
	            	} else {
	            		return data
	            	}
	            }
	        },{
	            "data": "type",
	            render: function(data, type, row) {
	            	if (data == 0) {
	            		return "正常";
	            	} else {
	            		return "调拨";
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


function exportInfo(){
	//var param = formToJson($("#form1"));
	$.ajaxFileUpload({
		url : '../../outbound/export.action',
		//data:param,
		secureuri : false,
		fileElementId : 'file',
		success : function(res, status) {
		}
	});
}