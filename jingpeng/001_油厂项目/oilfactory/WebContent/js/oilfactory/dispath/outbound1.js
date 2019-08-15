var infoList;

// 用户信息
var userInfo = '';
//用户权限
var roleFlag = "";
var seorderNumber;
var seclient;
$(document).ready(function() {

	// 获取物料类别
	var materielType = getDataDictionary("1");
	$("#materielType").html(materielType);
	
	// 树形信息显示
	showZtreeS();
	
	// 按车辆查询
	plateNumberList();
	
	// 画面数据显示
	var param = {};
	getInfoList(param);
	
	// 获取用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		var list = userInfo.rolecode;
		
		if(list.indexOf(",8,") != -1){
			// 库管(查看)
			roleFlag = false;
			$("#addBtn").hide();
			$("#addBtn1").hide();
			$("#addEmptyBtn").hide();
			$("#addExchangeBtn").hide();
		}
		
		if(list.indexOf(",3,") != -1 || list.indexOf(",13,") != -1){
			// 调度&&调度管理员(操作)
			roleFlag = true;
			$("#addBtn").show();
			$("#addBtn1").show();
			$("#addEmptyBtn").show();
			$("#addExchangeBtn").show();
		}
		if(list.indexOf(",0,") != -1){
			// 管理员(操作)
			roleFlag = true;
			$("#addBtn").show();
			$("#addBtn1").show();
			$("#addEmptyBtn").show();
			$("#addExchangeBtn").show();
		}
		if(list.indexOf(",7,") != -1){
			// 总经理(操作)
			roleFlag = true;
			$("#addBtn").show();
			$("#addBtn1").show();
			$("#addEmptyBtn").show();
			$("#addExchangeBtn").show();
		}
	}
	
	// 新增出库单
	$("#addBtn").fancybox({
		'href': 'outbound_edit.html',
		'width': 800,
		'height': 500,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'outbound.html';
		}
	})
	
	// 新增未入厂出库单
	$("#addBtn1").fancybox({
		'href': 'outunbound_edit.html',
		'width': 800,
		'height': 500,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'outbound.html';
		}
	});
	
	// 新增空发出库单按钮
	$("#addEmptyBtn").fancybox({
		'href': 'outEmptyBound_edit.html',
		'width': 800,
		'height': 580,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'outbound.html';
		}
	});
	
	// 新增兑换出库单按钮
	$("#addExchangeBtn").fancybox({
		'href': 'outExchangeBound_edit.html',
		'width': 800,
		'height': 510,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'outbound.html';
		}
	});
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
		url: "../../outbound/getSalesOrderList.action",
		data: {"keyword":$("#keyword").val()},
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
		seclient  = id
		// 根据客户名称查询
		getInfoList(param)
	}else{
		// 根据客户名称和销售订单id查询
		param.orderNumber = id;
		param.client = pid;
		seorderNumber = id
		seclient  = pid
		// 根据客户名称查询
		getInfoList(param)
	}
}

// 车辆检索框赋值
function plateNumberList(){
	$.ajax({
		type: "post",
		url: "../../outbound/getPlateNumberList.action",
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
	seorderNumber='';
	seclient='';
	// 画面数据显示
	var param = {};
	var plateNumber = $("#plateNumber").val();
	var materielType = $("#materielType").val();
	param.startTime = startTime;
	param.endTime = endTime;
	param.plateNumber = plateNumber;
	param.materielType = materielType;
	getInfoList(param);
}

// 画面list数据显示
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
//        fixedColumns:   {
//            leftColumns: 4
//        },
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
			url : "../../outbound/getInfoList.action",
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
            		if (row.outType != 2 && row.validFlag == 1) {
                		html +="<a class='fb' onclick=update("+row.id+","+row.outTypeMark+","+row.tareWeight+","+row.outType+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>";
                		html +="<a class='fb' onclick=copy("+row.id+")><img src=\"../../img/common/copy.png\" width=\"20\" height=\"20\" alt=\"复制\" title=\"复制\"></a>";
                	}
                	if(row.validFlag == 1){
        	  		    html +=	"<a onclick=updateValidFlag("+row.id+",'"+row.serialId+"',"+row.tareWeight+",0) class='zuofei'><img src='../../img/common/zuofei.png' width='20' height='20' alt='作废' title='作废'></a>";
                	}
    	  		    html +=	"<a onclick=deleteStore("+row.id+",'"+row.serialId+"',"+row.tareWeight+",1)><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>";
//    	  		    html +=	"<a onclick=updateSullte("+row.id+ ','+row.transportBalances+")><img src=\"../../img/common/weight.png\" width=\"20\" height=\"20\" alt=\"修改重量\" title=\"修改重量\"></a>";
            	}else{
            		html +="<a class='fb' onclick=update("+row.id+","+row.outTypeMark+","+row.tareWeight+","+row.outType+")><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
            	}
            	return html;
//            	html +="<a class='fb' onclick=selectEmpty("+row.id+",\'"+row.isEmpty+"\','"+row.emptyList+"')><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"选择空发出库单\" title=\"选择空发出库单\"></a>";
            	
            }
        }, {
        	 "data": "serialId"
        }, {
            "data": "plateNumber"
        },{
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
	         		//html = "<span class='hetongbianhao hetong' onmouseover='mouseover("+row.id+",0)'  onmouseout ='mouseout("+row.id+",0)'>"+row.contractNumber+"</span>";
	         		html = "<span class='hetongbianhao hetong' >"+row.contractNumber+"</span>";
//	         		var Contract = getContractList(row.contractId);
//	         		// 获取销售合同信息
//	             	html += "<div id='contractNumber"+row.id+"' class='fuchuang4'>"
//	 							+"<table style='border-top: 1px solid #ECECEC;'>"
//	 							+"	<tr>"
//	 							+"		<td class='hetongzhuangtai'>合同名称：</td>"
//	 							+"		<td class='hetongzhuangtai'>"+(Contract[0].contractName == null?"":Contract[0].contractName)+"</td>"
//	 							+"		<td class='hetongzhuangtai'>合同日期：</td>"
//	 							+"		<td class='hetongzhuangtai'>"+(Contract[0].contractDate == null?"":Contract[0].contractDate)+"</td>"
//	 							+"	</tr>"
//	 							+"	<tr>"
//	 							+"		<td class='hetongzhuangtai'>客户名称：</td>"
//	 							+"		<td class='hetongzhuangtai'>"+(Contract[0].customerName == null?"":Contract[0].customerName)+"</td>"
//	 							+"		<td class='hetongzhuangtai'>销售公司名称：</td>"
//	 							+"		<td class='hetongzhuangtai'>"+(Contract[0].salesCompanyName == null?"":Contract[0].salesCompanyName)+"</td>"
//	 							+"	</tr>"
//	 							+"	<tr>"
//	 							+"		<td class='hetongzhuangtai'>销售数量(t)：</td>"
//	 							+"		<td class='hetongzhuangtai'>"+(Contract[0].saleNumber == null?"":Contract[0].saleNumber)+"</td>"
//	 							+"		<td class='hetongzhuangtai'>是否来料加工：</td>"
//	 							+"		<td class='hetongzhuangtai'>"+(Contract[0].isIncoming == null?"":Contract[0].isIncoming)+"</td>"
//	 							+"	</tr>"
//	 							+"</table>"
//	 							+"</div>";
	         	}
	         	
	         	return html;
	       }
	    }, {
            "data": "salesOrderId",
             render: function(data, type, row) {
             	var html = "";
             	if(row.orderNumber != null && row.orderNumber != ''){
             		//html = "<span class='hetongbianhao hetong' onmouseover='mouseover("+row.id+",1)'  onmouseout ='mouseout("+row.id+",1)'>"+row.orderNumber+"</span>";
             		html = "<span class='hetongbianhao hetong'>"+row.orderNumber+"</span>";
//             		var OrderNumber = getOrderNumberList(row.salesOrderId);
//             		
//             		// 获取销售合同信息
//                 	html += "<div id='orderNumber"+row.id+"' class='fuchuang3'>"
//     							+"<table style='border-top: 1px solid #ECECEC;'>"
//     							+"	<tr>"
//     							+"		<td class='hetongzhuangtai'>客户名称：</td>"
//     							+"		<td class='hetongzhuangtai'>"+(OrderNumber[0].customerName == null?"":OrderNumber[0].customerName)+"</td>"
//     							+"		<td class='hetongzhuangtai'>客户别称：</td>"
//     							+"		<td class='hetongzhuangtai'>"+(OrderNumber[0].customer == null?"":OrderNumber[0].customer)+"</td>"
//     							+"	</tr>"
//     							+"	<tr>"
//     							+"		<td class='hetongzhuangtai'>销售公司名称：</td>"
//     							+"		<td class='hetongzhuangtai'>"+(OrderNumber[0].salesCompanyName == null?"":OrderNumber[0].salesCompanyName)+"</td>"
//     							+"		<td class='hetongzhuangtai'>是否实际发货：</td>"
//     							+"		<td class='hetongzhuangtai'>"+(OrderNumber[0].isActualDelivery == null?"":OrderNumber[0].isActualDelivery)+"</td>"
//     							+"	</tr>"
//     							+"	<tr>"
//     							+"		<td class='hetongzhuangtai'>联系人：</td>"
//     							+"		<td class='hetongzhuangtai'>"+(OrderNumber[0].contacts == null?"":OrderNumber[0].contacts)+"</td>"
//     							+"		<td class='hetongzhuangtai'>联系方式：</td>"
//     							+"		<td class='hetongzhuangtai'>"+(OrderNumber[0].telephone == null?"":OrderNumber[0].telephone)+"</td>"
//     							+"	</tr>"
//     							+"</table>"
//     							+"</div>";
             	}
             	
             	return html;
           }
        }, {
            "data": "outTypeName"
        }, 
        {
            "data": "isExamine",
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
            "data": "name"
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
            "data": "suttle",
            render: function(data, type, row) {
				if (data) {
					return data / 1000;
				} else {
					return data;
				}
            }
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
//        , {
//            "data": "outTypeMark",
//            render: function(data, type, row) {
//				if(data == 1){
//					data = "是";
//				}else{
//					data = "否";
//				}
//				return data;
//            }
//        }
        , {
            "data": "isEmpty",
            render: function(data, type, row) {
				if(data == "Y"){
					data = "是";
				}else{
					data = "否";
				}
				return data;
            }
        }, {
            "data": "emptyList",
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
            "data": "cancellationCause",
            render: function(data, type, row) {
            	var _html = "";
            	if (data) {
            		if (data.length > 10) {
            			_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
            		} else {
            			_html += "<span title='"+data+"'>"+data+"</span>";
            		}
            	}
            	return _html;
            }
        }],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            $("td:first", nRow).html("<input name='radio' type='hidden' value=\""+ aData.id +"\" />"+(iDisplayIndex +1));//设置序号位于第一列，并顺次加一               	
            if(aData.validFlag == 0){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#CCCCCC");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");                	
            }
            
            // 除了正常情况下全变灰
            if(aData.outType != 0){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#999999");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");                	
            }
           
           // 获取所有数据
           infoList.push(aData);
           return nRow;
        }
    });
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

// 修改
function update(id, outTypeMark, tareWeight, outType){
	if(roleFlag){
		if (tareWeight != null) {
			swal({
				title: "该出库单已经历过一次称重，不可修改！",
				type: "error",
				cancelButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			},
			function(){
				window.location.href="outbound.html";
			});
		} else {
			// 出库单
			if(outTypeMark == 0){
				var _url = "";
				if (outType == 0 || outType == 1) { // 正常/调拨
					_url += "outbound_edit.html";
				} else if (outType == 3) { // 空发
					_url += "outEmptyBound_edit.html";
				} else if (outType == 4 || outType == 5) { // 兑换
					_url += "outExchangeBound_edit.html";
				}
				$.fancybox({
					'href': _url + '?id='+id+"&flag=1",
					'width': 800,
					'height': 500,
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
					'height': 500,
					'type': 'iframe',
					'hideOnOverlayClick': false,
					'showCloseButton': false,
					'onClosed': function() {
						window.location.href = 'outbound.html';
					}
				});
			}	
		}
	}else{
		// 出库单
		if(outTypeMark == 0){
			var _url = "";
			if (outType == 0 || outType == 1) { // 正常/调拨
				_url += "outbound_edit.html";
			} else if (outType == 3) { // 空发
				_url += "outEmptyBound_edit.html";
			} else if (outType == 4 || outType == 5) { // 兑换
				_url += "outExchangeBound_edit.html";
			}
			$.fancybox({
				'href': _url + '?id='+id+"&flag=1&rolecode=false",
				'width': 800,
				'height': 500,
				'type': 'iframe',
				'hideOnOverlayClick': false,
				'showCloseButton': false,
				'onClosed': function() {
					window.location.href = 'outbound.html';
				}
			});
		}
	}
	
}

// 选择空发调拨单
function selectEmpty(id, flag, emptyList){
	$.fancybox({
		'href': 'outbound_empty.html?id=' + id + '&flag=' + flag + '&emptyList=' + emptyList,
		'width': 800,
		'height': 500,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'outbound.html';
		}
	});
}

function copy(id){
	//点击复制 (需通过标识来判断是出库单还是未入厂出库单)
//	id = $('input[type=radio]:checked').val();
	if(!id) {
		alert('请选择要复制的客户回访信息！');
		return false;
	}else{
		var outTypeMark;
		var outType;
		for(var i=0;i<infoList.length;i++){
			if(id == infoList[i].id){
				outTypeMark = infoList[i].outTypeMark;
				outType = infoList[i].outType;
				break;
			}
		}

		// 出库单
		if(outTypeMark == 0){
			var _url = "";
			if (outType == 0 || outType == 1) { // 正常/调拨
				_url += "outbound_edit.html";
			} else if (outType == 3) { // 空发
				_url += "outEmptyBound_edit.html";
			} else if (outType == 4 || outType == 5) { // 兑换
				_url += "outExchangeBound_edit.html";
			}
			$.fancybox({
				'href': _url+"?id="+id+"&flag=0",
				'width': 800,
				'height': 500,
				'type': 'iframe',
				'hideOnOverlayClick': false,
				'showCloseButton': false,
				'onClosed': function() {
					window.location.href = 'outbound.html';
				}
			});
		}else if(outTypeMark == 1){
			// 未入厂出库单
			$.fancybox({
				'href': 'outunbound_edit.html?id='+id+"&flag=0",
				'width': 800,
				'height': 500,
				'type': 'iframe',
				'hideOnOverlayClick': false,
				'showCloseButton': false,
				'onClosed': function() {
					window.location.href = 'outbound.html';
				}
			});
		}
		
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
			"show":"Y",
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

//作废
function updateValidFlag(id, serialId, tareWeight, flag){
	if (tareWeight != null) {
		swal({
			title: "该出库单已经历过一次称重，不可作废！",
			type: "error",
			cancelButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		},
		function(){
			window.location.href="outbound.html";
		});
	} else {
		$.ajax({
			type: "post",
			url: "../../outbound/checkTransList.action",
			data: {"serialId":serialId},
			dataType: "json",
			async:false,
			success: function (data) {
				if (data && data.length > 0) {
					setTimeout(function(){
						swal({
							title: "该出库单已生成运输单,不可删除",
							type: "error",
							cancelButtonText: '确定',
							cancelButtonFont: '微软雅黑',
						},
						function(){
							window.location.href="outbound.html";
						}); 
					},200);
				} else {
					$.fancybox({
						'href': 'outstoreValidType_edit.html?id='+id+"&flag=0",
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
		});
	} 
}

// 删除
function deleteStore(id, serialId, tareWeight, flag){
	if (tareWeight != null) {
		swal({
			title: "该出库单已经历过一次称重，不可删除！",
			type: "error",
			cancelButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		},
		function(){
			window.location.href="outbound.html";
		});
	} else {
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
		function(){
/*			$.ajax({
				type: "post",
				url: "../../outbound/checkTransList.action",
				data: {"serialId":serialId},
				dataType: "json",
				success: function (data) {
					if (data && data.length > 0) {
						setTimeout(function(){
							swal({
		    					title: "该出库单已生成运输单,不可删除",
		    					type: "error",
		    					cancelButtonText: '确定',
		    					cancelButtonFont: '微软雅黑',
		    				},
		    				function(){
		    					window.location.href="outbound.html";
		    				}); 
						},200);
					} else {*/
						$.ajax({
							type: "post",
							url: "../../outbound/updateValidFlag.action",
							data: {"id":id, "flag":flag},
							dataType: "json",
							success: function (data) {
		        	        	setTimeout(function(){swal({
		        					title: data.message,
		        					type: "success",
		        					cancelButtonText: '确定',
		        					cancelButtonFont: '微软雅黑',
		        				},
		        				function(){
		        					window.location.href="outbound.html";
		        				}); },200);
		        	        }
						});
/*					}
				}
			});*/
		});
	}
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
function play() {
	var music = document.getElementById('music'); 
	if (music.paused) {
		music.play();
	} else {
		music.pause();
	}
}
// 修改净重
function updateSullte(id, transportBalances) {
	$.fancybox({
		'href': 'outBoundSullte.html?id=' + id + '&transportBalances=' + transportBalances,
		'width': 600,
		'height': 425,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'outbound.html';
		}
	});
}

function exportInfo(){
	
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
	var materielType = $("#materielType").val();
	param.startTime = startTime;
	param.endTime = endTime;
	param.plateNumber = plateNumber;
	param.materielType = materielType;
	param.orderNumber = seorderNumber;
	param.client = seclient;
	var outboundParams = JSON.stringify(param);
	$.ajaxFileUpload({
		url : '../../outbound/export.action',
		data:{"param":outboundParams},
		secureuri : false,
		fileElementId : 'file',
		success : function(res, status) {
		}
	});
}