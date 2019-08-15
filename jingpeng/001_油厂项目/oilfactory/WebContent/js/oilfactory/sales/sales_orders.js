// 当前登录人姓名
var reviser = '';
// 结点id
var treeId = ''
// tree等级0:省  1:市  2:客户
var level = '';
var rolecode;
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		reviser = userInfo.id;
		var code = userInfo.rolecode;
		rolecode = code.split(",");
	}
	
	//销售权限的判定
	if($.inArray("0",rolecode) != -1 || $.inArray("2",rolecode) != -1 || $.inArray("7",rolecode) != -1){
		reviser = "";
	}else{
		reviser = reviser;
	}
	
	// 树形信息显示
	showZtreeS();
	
	// 销售订单管理datatable
	getList(treeId,level);
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
	
	//鼠标放在合同编号，显示飘窗
	$('.hetong').mouseenter(function(){
		$(this).find('.fuchuang4').show();
	});
	//鼠标离开合同编号，飘窗消失
	$('.hetong').mouseleave(function(){
		$(this).find('.fuchuang4').hide();
	});
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
	// 下个一级树 省 的父节点
	var pid = 0;
	// 二级树 市 的父节点
	var ppid = '';
	// 三级树 客户 的父节点
	var pppid = '';
	var node = {};
	
	$.ajax({
		type: "post",
		url: "../../salesOrders/getContractTree.action",
		data: {"keyword":$("#keyword").val(),"reviser":reviser},
		dataType: "json",
		success: function (data) {
			if (data != null && data.length > 0) {
				for (var i = 0; i < data.length; i++) {					
					// 一级树省变更时
					if (data[i].piid != ppid) {					
						// 一级树 省
						if (data[i].piid != null
								&& data[i].piid != '') {
							node = {
								id : data[i].piid,
								pId : pid,
								name : data[i].piname
							};
							nodes.push(node);
							
							// 市 的父节点-省
							ppid = data[i].piid;
							// 下个一级树 省 的父节点
							pid++;	
						}						
					} 
					//  二级树 市变更时
					if (data[i].ciid != pppid) {
						// 二级树 市
						if (data[i].ciid != null
								&& data[i].ciid != '') {
							node = {
								id : data[i].ciid,
								pId : ppid,
								name : data[i].ciname
							};
							nodes.push(node);
							
							// 客户 的父节点-市
							pppid = data[i].ciid;
						}
						
					} 
					// 三级树 客户
					if (data[i].cusid != null
							&& data[i].cusid != '') {
						node = {
							id : data[i].cusid,
							pId : pppid,
							name : data[i].cuscode
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

//关键字检索
function keywordSelect(){
	showZtreeS();
}

// 树形节点点击事件
function findClick(event, treeId, treeNode, isCancel) {
	treeId = treeNode.id;
	// 0:省  1:市  2:客户
	level = treeNode.level + 1;
	// 合同管理datatable
	getList(treeId,level);
}

//销售订单管理datatable
function getList(treeId,level,params){
	treeId = treeId.substr(1,treeId.length-1);
	
	var table1 = $('#salesOrders').dataTable();
	table1.fnDestroy();
	$("#salesOrders").DataTable({
		 "paging": true,
         "lengthChange": false,
         "pageLength": 14,
         "searching": true,
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
            type: "post",
            url: "../../salesOrders/getSalesOrdersInfo.action",
            dataSrc: "data",
            data: function (d) {
          	var param = {};
                param.treeId = treeId;//根据tree查询
                param.level = level;//当前第几级树
                if (params != null) {
                	// 时间
                	param.startTime = params.startTime;
                	param.endTime = params.endTime;
                }
        		param.reviser = reviser;
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
		  		    html += "<a onclick=update("+row.id+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>";
		  		    html += "<a onclick=copy("+row.id+")><img src=\"../../img/common/copy.png\" width=\"20\" height=\"20\" alt=\"复制\" title=\"复制\"></a>";
		  		    if (row.orderState != 3){
		  		    	html +=	"<a onclick=zuofei("+row.id+") class='zuofei'><img src='../../img/common/zuofei.png' width='20' height='20' alt='作废' title='作废'></a>";
		  		    }		  		    
		  		    html +=	"<a onclick=del("+row.id+")><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>";
		  		    return html;
	            }
	        }, {
	            "data": "orderNumber"
	        },{
	        	"data": "contractNumber"
	        }, {
	            "data": "customerCode"
	        }, {
	            "data": "customerAlias"
	        }, {
	            "data": "orderStateName"
	        }, {
	            "data": "name"
	        }, {
	            "data": "address"
	        }, {
	            "data": "contacts"
	        }, {
	            "data": "telephone"
	        }, {
	            "data": "createrDate",
	            render:function(data,type,row,meta) {
		  		    return data;
	            }
	        }, {
	            "data": "remarks"
	        }, {
	            "data": "cancellationCause"
	        }],
		"createdRow" : function(row, data, dataIndex) {
			
			if(data.remarks.length > 10){//只有超长，才有td点击事件
                $(row).children('td').eq(12).attr('onmouseover','javascript:changeShowRemarks(this);');
            }
            $(row).children('td').eq(12).attr('content',data.remarks);
            ////
            if(data.address.length > 10){//只有超长，才有td点击事件
            	$(row).children('td').eq(8).attr('onmouseover','javascript:changeShowRemarks(this);');
            }
            $(row).children('td').eq(8).attr('content',data.address);
            ///
            if(data.cancellationCause!=null){
            	if(data.cancellationCause.length > 13){//只有超长，才有td点击事件
            		$(row).children('td').eq(13).attr('onmouseover','javascript:changeShowRemarks(this);');
            	}
            }
            $(row).children('td').eq(13).attr('content',data.cancellationCause);
            
		},
		"order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	/*html +="<input name='radio' type='radio' value='"+aData.id+"'/>";*/
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一
            
            // 作废行变色
            if(aData.orderState == 3){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#999999");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");                	
            }
           return nRow;
        },
        "columnDefs": [{
            "targets": [0],
            "visible": true,
            "searchable": false
        },
        {
        	"targets": [3],
        	"class": "hetongbianhao hetong"
        },{
            "targets": 12,
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
        },{
            "targets": 8,
            "visible": true,
            "searchable": false,
            "type": "date",
            "render": function (data, type, full, meta) {
                if (full.address.length  > 10) {
                    return getPartialRemarksHtml(full.address);//显示部分信息
                } else {
                    return full.address;//显示原始全部信息            }
                }
            }
        },{
            "targets": 13,
            "visible": true,
            "searchable": false,
            "type": "date",
            "render": function (data, type, full, meta) {
            	if(full.cancellationCause != null){
                    if (full.cancellationCause.length  > 10) {
                        return getPartialRemarksHtml(full.cancellationCause);//显示部分信息
                    } else {
                        return full.cancellationCause;//显示原始全部信息            }
                    }
            	}else{
            		full.cancellationCause = "";
            		return full.cancellationCause;
            	}
            }
        }
        ]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('#salesOrders_info').css('text-align','center');
	
	// salesOrders_filter隐藏
	$("#salesOrders_filter").hide();//datatable自带查询框
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

//销售合同编号
function getContractList(id){
	var Contract = '';
	$.ajax({
		type: "post",
		url: "../../salesContractManagement/getContractInfo.action",
		data:{"id":id,"Cid":1},
		dataType: "json",
		async:false,
		success: function (data) {
			Contract = data.data;
		}
	});
	return Contract;
}

function mouseover(id){
	//鼠标放在合同编号，显示飘窗
	$('#contractNumber'+id).show();
}

function mouseout(id){
	//鼠标离开合同编号，飘窗消失
	$('#contractNumber'+id).hide();
}

// 点击查询
//function search(){
//	var search = $("#search").val();
//	$("#salesOrders").DataTable().search(search).draw();
//}

//查询按钮
function search(){
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
	param.startTime = startTime;
	param.endTime = endTime;
	getList("","",param);
}

//新增销售订单
function add(){
	location.href = 'sales_orders_edit.html?flag=0';
}

// 复制销售订单
function copy(id){
/*	var radio = $("input[name='radio']:checked").val();
	if (radio !='undefined' && radio != null && radio != ''){*/
		location.href = 'sales_orders_edit.html?flag=2&id='+id;
/*	} else {
		swal("操作失败", "请选择一条数据", "info");
	}*/	
}

// 修改销售订单
function update(id){
	location.href = 'sales_orders_edit.html?flag=1&id='+id;
}

//销售订单作废
function zuofei(id){
	var outboundList;
	$.ajax({
        type: "post",
        url: '../../outbound/getInfoList.action',
        async:false,
        data:{"salesOrderId":id},
        dataType: "json",
        success: function (data) {
        	outboundList = data.data
        	if(outboundList.length>0){
        		setTimeout(function(){swal({
					title: "该订单已在别处调用,不可作废",
					type: "error",
					cancelButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					window.location.href="sales_orders.html";
				}); },200)
        		}else{
        			$.fancybox({
        				'href': '../sales/sales_zuofei.html?id='+id,
        				'width': 400,
        				'height': 300,
        				'type': 'iframe',
        				'hideOnOverlayClick': false,
        				'showCloseButton': false,
        				'onClosed': function() {
        					window.location.href = 'sales_orders.html';
        				}
        			});
        		}
        }
	})
}

// 销售订单删除
function del(id){
	var outboundList;
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
		$.ajax({
	        type: "post",
	        url: '../../outbound/getInfoList.action',
	        async:false,
	        data:{"salesOrderId":id},
	        dataType: "json",
	        success: function (data) {
	        	outboundList = data.data
	        	if(outboundList.length>0){
	        		setTimeout(function(){swal({
    					title: "该订单已在别处调用,不可删除",
    					type: "error",
    					cancelButtonText: '确定',
    					cancelButtonFont: '微软雅黑',
    				},
    				function(){
    					window.location.href="sales_orders.html";
    				}); },200);
	        	}else{
	        		$.ajax({
	        	        type: "post",
	        	        url: '../../salesOrders/deleteSalesOrdersInfo.action',
	        	        async:false,
	        	        data:{"id":id,"reviser":reviser},
	        	        dataType: "json",
	        	        success: function (data) {
	        	        	setTimeout(function(){swal({
	        					title: data.message,
	        					type: "success",
	        					cancelButtonText: '确定',
	        					cancelButtonFont: '微软雅黑',
	        				},
	        				function(){
	        					window.location.href="sales_orders.html";
	        				}); },200);
	        	        }
	        		});
	        	}
	        }
		});
	});
}

function exportInfo(){
	//var param = formToJson($("#form1"));
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
    param.treeId = treeId;//根据tree查询
    param.level = level;//当前第几级树
    param.startTime = startTime;
    param.endTime = endTime;
	param.reviser = reviser;
	var salesOrdersParams = JSON.stringify(param);
	$.ajaxFileUpload({
		url : '../../salesOrders/export.action',
		data:{"param":salesOrdersParams},
		secureuri : false,
		fileElementId : 'file',
		success : function(res, status) {
		}
	});
}
