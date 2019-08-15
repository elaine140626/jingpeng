var infoList;
$(document).ready(function() {
	// 树形信息显示
	showZtreeS();

	//按物料名称查询
	materialNameInquiryList();
	
	$("#startTime").val(getNowFormatDate(1));
	$("#endTime").val(getNowFormatDate(0));
	
	// 画面数据显示
	var param = {};
	getInfoList(param);
	
	$("#addBtn").fancybox({
		'href': 'not_weighing_edit.html',
		'width': 800,
		'height': 600,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'not_weighing.html';
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
	
	/*//鼠标放在合同编号，显示飘窗
	$('.hetong').mouseenter(function() {
		$(this).find('.fuchuang4').show();
	});
	//鼠标离开合同编号，飘窗消失
	$('.hetong').mouseleave(function() {
		$(this).find('.fuchuang4').hide();
	});
	
	
	//鼠标放在销售订单编号，显示飘窗
	$('.xiaoshou').mouseenter(function() {
		$(this).find('.fuchuang4').show();
	});
	//鼠标离开销售订单编号，飘窗消失
	$('.xiaoshou').mouseleave(function() {
		$(this).find('.fuchuang4').hide();
	});*/
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
		url: "../../repertory/getSalesOrderList.action",
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
//树形节点点击事件
function findClick(event, treeId, treeNode, isCancel) {
	var id = treeNode.id;
	var pid = treeNode.pId;
	var param = {};
	// 清空页面上检索信息
	$("#customerAlias1").val("");
    $("#materialNameInquiry").val("");
    $("#materialModelInquiry").val("");
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
//关键字检索
function keywordSelect(){
	showZtreeS();
}
//查询按钮
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
	var customerAlias = $("#customerAlias1").val();
	var materialName = $("#materialNameInquiry").val();
    var materialModel = $("#materialModelInquiry").val();
	param.client = customerAlias;
	param.startTime = startTime;
	param.endTime = endTime;
	param.materialName = materialName;
	param.materialModel = materialModel;
	getInfoList(param);
}
//按物料名称查询
function materialNameInquiryList(){
	$.ajax({
		type: "post",
		url: "../../repertory/getMaterialNameSearcList.action",
		data: {},
		dataType: "json",
		success: function (data) {
			if(data != null && data.length > 0){
				// 先清空
				$("#materialNameInquirys").empty();
				var materielName = '';
				for(var i=0; i< data.length; i++){
					if(materielName != data[i].materielName){
						$("#materialNameInquirys").append("<option value='"+data[i].materielName+"'>"+data[i].materielName+"</option>");
					}
					materielName = data[i].materielName;
				}
			}
		}
	});
}
//物料型号搜索框
function changeMaterialModel(){
	$.ajax({
		type: "post",
		url: "../../repertory/getMaterielinfoList.action",
		data: {"materielName":$("#materialNameInquiry").val()},
		dataType: "json",
		success: function (data) {
			if(data != null && data.length > 0){
				// 先清空
				$("#materialModelInquirys").empty();
				for(var i=0; i< data.length; i++){
					$("#materialModelInquirys").append("<option value='"+data[i].materielModel+"'>"+data[i].materielModel+"</option>");
				}
			}
		}
	});
}
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
			url : "../../repertory/getInfoList.action",
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
            	html +="<a class='fb' onclick=update("+row.id+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>";
            	html += "<a onclick=copy("+row.id+")><img src=\"../../img/common/copy.png\" width=\"20\" height=\"20\" alt=\"复制\" title=\"复制\"></a>";
	  		    html +=	"<a onclick=updateValidFlag("+row.id+")><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>";
	  		    return html;
            }
        }, {
        	"data":"serialID"
        }, {
            "data": "carName"
        }, {
        	"data": "materielInfoId",
            render: function(data, type, row) {
				data = row.materielName + "--" + row.materielModel;
				return data;
            }
        }, {
            "data": "client"
        }, {
        	"data": "consigneeAddress"
        }, {
            "data": "customerAlias"
        }, {
        	"data": "saleNumber"
        }, {
        	"data": "salePrice"
        }, {
        	"data": "isSelfLifting"	,
        	render: function(data, type, row) {
				if(data == 0){
					data = "是";
				}else if(data == 1){
					data = "否";
				}
				return data;
            }
        }, {
            "data": "isCarryOff",
            render: function(data, type, row) {
				if(data == 0){
					data = "是";
				}else if(data == 1){
					data = "否";
				}
				return data;
            }
        }, {
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
        }, {
            "data": "contractId",
            render: function(data, type, row) {
             	var html = "";
             	if(row.contractNumber != null && row.contractNumber != ''){
             		//html = "<span class='hetongbianhao hetong' onmouseover='mouseover("+row.id+",0)'  onmouseout ='mouseout("+row.id+",0)'>"+row.contractNumber+"</span>";
             		html = "<span class='hetongbianhao hetong'>"+row.contractNumber+"</span>";
             		var Contract = getContractList(row.contractId);
             		if(Contract != null && Contract != ''){
             		// 获取销售合同信息
//                     	html += "<div id='contractNumber"+row.id+"' class='fuchuang4'>"
//         							+"<table style='border-top: 1px solid #ECECEC;'>"
//         							+"	<tr>"
//         							+"		<td class='hetongzhuangtai'>合同名称：</td>"
//         							+"		<td class='hetongzhuangtai'>"+Contract[0].contractName+"</td>"
//         							+"		<td class='hetongzhuangtai'>合同日期：</td>"
//         							+"		<td class='hetongzhuangtai'>"+Contract[0].contractDate+"</td>"
//         							+"	</tr>"
//         							+"	<tr>"
//         							+"		<td class='hetongzhuangtai'>客户名称：</td>"
//         							+"		<td class='hetongzhuangtai'>"+Contract[0].customerName+"</td>"
//         							+"		<td class='hetongzhuangtai'>销售公司名称：</td>"
//         							+"		<td class='hetongzhuangtai'>"+Contract[0].salesCompanyName+"</td>"
//         							+"	</tr>"
//         							+"	<tr>"
//         							+"		<td class='hetongzhuangtai'>销售数量(t)：</td>"
//         							+"		<td class='hetongzhuangtai'>"+Contract[0].saleNumber+"</td>"
//         							+"		<td class='hetongzhuangtai'>是否来料加工：</td>"
//         							+"		<td class='hetongzhuangtai'>"+Contract[0].isIncoming+"</td>"
//         							+"	</tr>"
//         							+"</table>"
//         							+"</div>";
             		}
             	}
             	
             	return html;
            }
        }, {
            "data": "orderNumber"
        }, {
        	"data": "userName"
        }, {
        	"data": "deliverer",
        	render: function(data, type, row) {
				data = row.deliverer + "-" +row.deliveryPhone;
				return data;
            }
        }, {
            "data": "consignee",
            render: function(data, type, row) {
				data = row.consignee + "-" +row.consigneePhone;
				return data;
            }
        }, {
            "data": "creater"
        }, {
        	"data": "createrDate"
        },{
            "data": "remarks"
        }/*{
            "data": "createrDate",
            render: function(data, type, row) {
				if(data == 1){
					data = "是";
				}else{
					data = "否";
				}
				return data;
            }
        }{
            "data": "deliverer"
        }, {
            "data": "deliveryPhone"
        }, {
            "data": "taxRate",
            render: function(data, type, row) {
				if(data == 0){
					data = "5";
				}else if(data == 1){
					data = "10";
				}else if(data == 2){
					data = "15";
				}else if(data == 3){
					data = "20";
				}else if(data == 4){
					data = "30";
				}
				return data;
            }
        },{
            "data": "type",
            render: function(data, type, row) {
				if(data == 0){
					data = "正常";
				}else if(data == 1){
					data = "调拨";
				}
				return data;
            }
        },{
            "data": "isTesting",
            render: function(data, type, row) {
				if(data == 0){
					data = "是";
				}else if(data == 1){
					data = "否";
				}
				return data;
            }
        },{
            "data": "isQualified",
            render: function(data,type,row){
            	if(data == 0){
            		data = "合格";
            	}else if(data == 1){
            		data = "不合格";
            	}
            	return data;
            }
        }*/],
		"createdRow" : function(row, data, dataIndex) {
			if(data.remarks.length > 10){//只有超长，才有td点击事件
                $(row).children('td').eq(20).attr('onmouseover','javascript:changeShowRemarks(this);');
            }
            $(row).children('td').eq(20).attr('content',data.remarks);
		},
        "columnDefs": [{
            "targets": 20,
            "visible": true,
            "searchable": false,
            "type": "date",
            "render": function (data, type, full, meta) {
                if (full.remarks.length  > 10) {
                    return getPartialRemarksHtml(full.remarks);//显示部分信息
                } else {
                    return full.remarks;//显示原始全部信息            }
                }
            }
        }],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	//html +="<input name='radio' type='radio' value='"+aData.id+"'/>";
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一             	
            /*if(aData.validFlag == 0){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#FF7F00");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");                	
            }*/
           
           // 获取所有数据
           infoList.push(aData);
           return nRow;
        }
    });
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}
function copy(id){
	//点击复制
	/*id = $('input[type=radio]:checked').val();
	if(!id) {
		alert('请选择要复制的未称重出库单！');
		return false;
	}*/
	$.fancybox({
		'href': 'not_weighing_edit.html?id='+id+"&flag=0",
		'width': 800,
		'height': 600,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'not_weighing.html';
		}
	});
}
//修改
function update(id){
	$.fancybox({
		'href': 'not_weighing_edit.html?id='+id+"&flag=1",
		'width': 800,
		'height': 600,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'not_weighing.html';
		}
	});
}
//删除
function updateValidFlag(id){
	swal({
		title: "确定操作吗？",
		text:"删除后将无法恢复，请谨慎操作！",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
	},
	function(){
		$.ajax({
			type: "post",
			url: "../../repertory/getInfoList.action",
			data: {"id":id},
			dataType: "json",
			success: function (data) {
				var infoList = data.data;
				if(infoList.length>0){
					for(var i=0;i<infoList.length;i++){
						if(infoList[i].type == 1){
							setTimeout(function(){swal({
		    					title: "该条数据调拨生成,不可删除!",
		    					type: "error",
		    					cancelButtonText: '确定',
		    					cancelButtonFont: '微软雅黑',
		    				},
		    				function(){
		    					window.location.href="not_weighing.html";
		    				}); },200);
						}else{
							$.ajax({
								type: "post",
								url: "../../repertory/updateValidFlag.action",
								data: {"id":id},
								dataType: "json",
								success: function (data) {
									if(data.code == 'success'){
										var param = {};
										getInfoList(param);	
										window.location.href = 'not_weighing.html';
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
				}
			}
		});
	});
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

//试验日期初始化赋值
function getNowFormatDate(flg) {
	if(flg == 0) {
		//获取当前日期
		var date = new Date();
	} else {
		//获取30天前的日期
		var myDate = new Date();
		var date = new Date(myDate - 1000 * 60 * 60 * 24 * 7);
	}

	var seperator1 = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if(month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if(strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}