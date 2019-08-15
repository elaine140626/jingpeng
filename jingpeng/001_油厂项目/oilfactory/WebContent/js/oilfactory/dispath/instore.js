 var infoList;
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
	
	$("#addBtn").fancybox({
		'href': 'instore_edit.html',
		'width': 800,
		'height': 425,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'instore.html';
		}
	})
	$("#addBtn1").fancybox({
			'href': 'inbound_edit.html',
			'width': 733,
			'height': 340,
			'type': 'iframe',
			'hideOnOverlayClick': false,
			'showCloseButton': false,
			'onClosed': function() {
				window.location.href = 'instore.html';
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
	var contractNumber = '';
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
		getInfoList(param);
	}else{
		// 根据客户名称和销售合同id
		param.contractNumber = id;
		param.client = pid;
		// 根据客户名称查询
		getInfoList(param);
	}
}

// 车辆检索框赋值
function plateNumberList(){
	$.ajax({
		type: "post",
		url: "../../instore/getPlateNumberList.action",
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
	var table = $('#table').dataTable();
	table.fnDestroy();
	
	$("#table").DataTable({
        "paging": true,
        "lengthChange": false,
        "pageLength": 15,
        "searching": false,
        "ordering": false,
        "info": true,
        "sInfo": true,
        "autoWidth": false,
        "iDisplayStart" : 0,
        scrollY:"100%",
        scrollX:true,
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
			url : "../../instore/getInfoList.action",
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
            bSortable: true,
            render: function(data, type, row) {
            	var html = '';
            	html +="<a class='fb' onclick=update("+row.id+","+row.enterTypeMark+","+row.grossWeight+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>";
            	if (row.enterTypeMark != 1) {
            		html +="<a class='fb' onclick=copy("+row.id+")><img src=\"../../img/common/copy.png\" width=\"20\" height=\"20\" alt=\"复制\" title=\"复制\"></a>";
            	}
            	if(row.validFlag == 1 && row.enterTypeMark != 1){
    	  		    html +=	"<a onclick=updateValidFlag("+row.id+","+row.grossWeight+",0) class='zuofei'><img src='../../img/common/zuofei.png' width='20' height='20' alt='作废' title='作废'></a>";
            	}
            	if (row.enterTypeMark != 1) {
            		html +=	"<a onclick=deleteStore("+row.id+","+row.grossWeight+",'"+row.serialID+"',1)><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>";
            	}
            	html +=	"<a onclick=updateSullte("+row.id+")><img src=\"../../img/common/weight.png\" width=\"20\" height=\"20\" alt=\"修改重量\" title=\"修改重量\"></a>";
	  		    return html;
            }
        }, {
            "data": "serialID"
        }, {
            "data": "plateNumber"
        },{
        	"data": "productID",
            render: function(data, type, row) {
            	if (row.materielName != null) {
            		data = row.materielName + "--" + row.materielModel;
    				return data;	
				}else{
					return "";
				}
            }
        }, {
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
            "data": "purchaseContractId",
            render: function(data, type, row) {
            	var html = '';
            	if(row.billNumber != null && row.billNumber != ''){
            		//html = "<span class='hetongbianhao hetong' onmouseover='mouseover("+row.id+",1)'  onmouseout ='mouseout("+row.id+",1)'>"+row.billNumber+"</span>";
            		html = "<span class='hetongbianhao hetong'>"+row.billNumber+"</span>";
            		// 获取采购合同信息
//            		var purchaseContracget = getPurchasecontract(row.purchaseContractId);
//            		
//            		html += "<div id='billNumber"+row.id+"' class='fuchuang4' style='margin-left:175px;'>"
//								+"<table style='border-top: 1px solid #ECECEC;'>"
//								+"	<tr>"
//								+"		<td class='hetongzhuangtai'>合同编号：</td>"
//								+"		<td class='hetongzhuangtai'>"+(purchaseContracget[0].billNumber == null?"":purchaseContracget[0].billNumber)+"</td>"
//								+"		<td class='hetongzhuangtai'>合同日期：</td>"
//								+"		<td class='hetongzhuangtai'>"+(purchaseContracget[0].date == null?"":purchaseContracget[0].date)+"</td>"
//								+"	</tr>"
//								+"	<tr>"
//								+"		<td class='hetongzhuangtai'>运费结算情况：</td>"
//								+"		<td class='hetongzhuangtai'>"+(purchaseContracget[0].freightBalance == null?"":purchaseContracget[0].freightBalance)+"</td>"
//								+"		<td class='hetongzhuangtai'>预计运费金额：</td>"
//								+"		<td class='hetongzhuangtai'>"+(purchaseContracget[0].expectMoney == null?"":purchaseContracget[0].expectMoney)+"</td>"
//								+"	</tr>"
//								+"</table>"
//								+"</div>";
            	}
            	return html;
            }
        }, {
            "data": "contractId",
            render: function(data, type, row) {
            	var html = "";
            	if(row.contractNumber != null && row.contractNumber != ''){
            		//html = "<span class='hetongbianhao hetong' onmouseover='mouseover("+row.id+",0)'  onmouseout ='mouseout("+row.id+",0)'>"+row.contractNumber+"</span>";
            		html = "<span class='hetongbianhao hetong' >"+row.contractNumber+"</span>";
//            		var Contract = getContractList(row.contractId);
//            		// 获取销售合同信息
//                	html += "<div id='contractNumber"+row.id+"' class='fuchuang3'>"
//    							+"<table style='border-top: 1px solid #ECECEC;'>"
//    							+"	<tr>"
//    							+"		<td class='hetongzhuangtai'>合同名称：</td>"
//    							+"		<td class='hetongzhuangtai'>"+(Contract[0].contractName == null?"":Contract[0].contractName)+"</td>"
//    							+"		<td class='hetongzhuangtai'>合同日期：</td>"
//    							+"		<td class='hetongzhuangtai'>"+(Contract[0].contractDate == null?"":Contract[0].contractDate)+"</td>"
//    							+"	</tr>"
//    							+"	<tr>"
//    							+"		<td class='hetongzhuangtai'>客户名称：</td>"
//    							+"		<td class='hetongzhuangtai'>"+(Contract[0].customerName == null?"":Contract[0].customerName)+"</td>"
//    							+"		<td class='hetongzhuangtai'>销售公司名称：</td>"
//    							+"		<td class='hetongzhuangtai'>"+(Contract[0].salesCompanyName == null?"":Contract[0].salesCompanyName)+"</td>"
//    							+"	</tr>"
//    							+"	<tr>"
//    							+"		<td class='hetongzhuangtai'>销售数量(t)：</td>"
//    							+"		<td class='hetongzhuangtai'>"+(Contract[0].saleNumber == null?"":Contract[0].saleNumber)+"</td>"
//    							+"		<td class='hetongzhuangtai'>是否来料加工：</td>"
//    							+"		<td class='hetongzhuangtai'>"+(Contract[0].isIncoming == null?"":Contract[0].isIncoming)+"</td>"
//    							+"	</tr>"
//    							+"</table>"
//    							+"</div>";
            	}
            	
            	return html;
            }
        
        }, {
            "data": "deliveryMan",
            render: function(data, type, row) {
				data = row.deliveryMan + "--" + row.deliveryManPhone;
				return data;
            }
        }, {
            "data": "enterDate"
        },  
        {
            "data": "amount"
        }, {
            "data": "remarks",
            sWidth:"10%",
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
            "data": "cancellationCause",
            sWidth:"10%"
        }],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            $("td:first", nRow).html("<input name='radio' type='hidden' value=\""+ aData.id +"\" />"+(iDisplayIndex +1));//设置序号位于第一列，并顺次加一               	
            if(aData.validFlag == 0){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#CCCCCC");                		
            }
           
           // 获取所有数据
           infoList.push(aData);
           return nRow;
        },
		"createdRow":function(row,data,index){
            $('td',row).eq(0).html('').append('<a href="stock/detail?code='+data.code+'&company='+data.company+'" target="_blank">'+data.code+'</a>');
        },
    });
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

// 修改
function update(id, enterTypeMark, grossWeight){
	if (grossWeight != null) {
		swal({
			title: "该出库单已经历过一次称重，不可修改！",
			type: "error",
			cancelButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		},
		function(){
			window.location.href="instore.html";
		});
	} else {
		// 入库单
		if(enterTypeMark == 0){
			$.fancybox({
				'href': 'instore_edit.html?id='+id+"&flag=1",
				'width': 800,
				'height': 425,
				'type': 'iframe',
				'hideOnOverlayClick': false,
				'showCloseButton': false,
				'onClosed': function() {
					window.location.href = 'instore.html';
				}
			});
		}else if(enterTypeMark == 2 || enterTypeMark == 3){
			// 来料加工
			$.fancybox({
				'href': 'inbound_edit.html?id='+id+"&flag=1",
				'width': 733,
				'height': 340,
				'type': 'iframe',
				'hideOnOverlayClick': false,
				'showCloseButton': false,
				'onClosed': function() {
					window.location.href = 'instore.html';
				}
			});
		}else if(enterTypeMark == 1){
			// 来料加工
			$.fancybox({
				'href': 'instore_edit.html?id='+id+"&flag=2",
				'width': 733,
				'height': 340,
				'type': 'iframe',
				'hideOnOverlayClick': false,
				'showCloseButton': false,
				'onClosed': function() {
					window.location.href = 'instore.html';
				}
			});
		}
	}
}

function copy(id){
	//点击复制 (需通过标识来判断是出库单还是未入厂出库单)
//	id = $('input[type=radio]:checked').val();
	if(!id) {
		alert('请选择要复制的客户回访信息！');
		return false;
	}else{
		var enterTypeMark;
		for(var i=0;i<infoList.length;i++){
			if(id == infoList[i].id){
				enterTypeMark = infoList[i].enterTypeMark;
				break;
			}
		}
		
		// 入库单
		if(enterTypeMark == 0){
			$.fancybox({
				'href': 'instore_edit.html?id='+id+"&flag=0",
				'width': 800,
				'height': 425,
				'type': 'iframe',
				'hideOnOverlayClick': false,
				'showCloseButton': false,
				'onClosed': function() {
					window.location.href = 'instore.html';
				}
			});
		}else if(enterTypeMark == 3){
			// 来料加工
			$.fancybox({
				'href': 'inbound_edit.html?id='+id+"&flag=0",
				'width': 733,
				'height': 340,
				'type': 'iframe',
				'hideOnOverlayClick': false,
				'showCloseButton': false,
				'onClosed': function() {
					window.location.href = 'instore.html';
				}
			});
		}
	}
}

//作废或者删除
function updateValidFlag(id, grossWeight, flag){
	if (grossWeight != null) {
		swal({
			title: "该出库单已经历过一次称重，不可作废！",
			type: "error",
			cancelButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		},
		function(){
			window.location.href="instore.html";
		});
	} else {
		$.fancybox({
			'href': 'instoreValidType_edit.html?id='+id+"&flag=0",
			'width': 800,
			'height': 425,
			'type': 'iframe',
			'hideOnOverlayClick': false,
			'showCloseButton': false,
			'onClosed': function() {
				window.location.href = 'instore.html';
			}
		});
	}
}

// 作废或者删除
function deleteStore(id, grossWeight, serialId, flag){
	if (grossWeight != null) {
		swal({
			title: "该出库单已经历过一次称重，不可删除！",
			type: "error",
			cancelButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		},
		function(){
			window.location.href="instore.html";
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
		},function(){
			$.ajax({
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
								window.location.href="instore.html";
							}); 
						},200);
					} else {
						$.ajax({
							type: "post",
							url: "../../instore/updateValidFlag.action",
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
				}
			});
		});
	}
}

function mouseover(id,flag){
	if(flag == 0){
		//鼠标放在合同编号，显示飘窗
		$('#contractNumber'+id).show();
	}else{
		//鼠标放在合同编号，显示飘窗
		$('#billNumber'+id).show();
	}
}

function mouseout(id,flag){
	if(flag == 0){
		//鼠标离开合同编号，飘窗消失
		$('#contractNumber'+id).hide();
	}else{
		//鼠标离开合同编号，飘窗消失
		$('#billNumber'+id).hide();
	}
}

// 获取采购合同信息
function getPurchasecontract(purchaseContractId){
	var purchaseContract = '';
	$.ajax({
		type: "post",
		url: "../../outbound/getPurchasecontractList.action",
		data:{"id":purchaseContractId},
		dataType: "json",
		async:false,
		success: function (data) {
			purchaseContract = data;
		}
	});
	return purchaseContract;
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
//修改净重
function updateSullte(id) {
	$.fancybox({
		'href': 'inBoundSullte.html?id=' + id,
		'width': 600,
		'height': 425,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'instore.html';
		}
	});
}