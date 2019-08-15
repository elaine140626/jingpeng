// 当前登录人姓名
var reviser = '';
// 结点id
var treeIds = ''
// tree等级0:省  1:市  2:客户
var levels = '';
$(function(){
	// 树形信息显示
	$('#ContractState').fSelect();
	showZtreeS();
	//销售员下拉列表
	getSalesName();
	// 合同管理datatable
	getList(treeIds,levels);
	
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
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		reviser = userInfo.id;
	}
})

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
	// 下个一级树 省 的父节点
	var pid = 0;
	// 二级树 市 的父节点
	var ppid = '';
	// 三级树 客户 的父节点
	var pppid = '';
	var node = {};
	
	$.ajax({
		type: "post",
		url: "../../salesContractManagement/getContractTree.action",
		data: {"keyword":$("#keyword").val()},
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
							// 下个一级树 省 的父节点
							pid++;
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
	treeIds = treeNode.id;
	// 0:省  1:市  2:客户
	levels = treeNode.level + 1;
	// 合同管理datatable
	getList(treeIds,levels);
}

//销售员下拉列表
function getSalesName(){
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getSalesName.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
//			var html = "<option value='' selected='selected'>请选择</option>";
			$("#plateNumbers").html("");
			for (var i = 0; i < data.length; i++) {
				$("#plateNumbers").append("<option value='" + data[i].name + "'>"+ data[i].name + "</option>");		
			}
//			$("#UserInfoId").empty();
//			$("#UserInfoId").html(html);
		}
	});
}

// 合同管理datatable
function getList(treeId,level){
	treeIds = treeId.substr(1,treeId.length-1);	
	// 按销售员查询
	var UserInfoId = $("#UserInfoId").val();
	var CustomerName = $("#CustomerName").val();
	// 开始时间和结束时间的判定
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	// 按合同状态查询
	var obj = $("#ContractState").val();
	if(obj==null){
		var ContractState = '';
	}else{
		var ContractState = obj.join(',');;
	}
	var table1 = $('#contractManagement').dataTable();
	table1.fnDestroy();
	$("#contractManagement").DataTable({
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
            type: "post",
            url: "../../salesContractManagement/getContractInfo.action",
            dataSrc: "data",
            data: function (d) {
          	var param = {};
                param.UserInfoId = UserInfoId;//按销售员查询
                param.CustomerName = CustomerName; //按客户名称查询
                param.ContractState = ContractState;//按合同状态查询
                param.startTime = startTime;//开始时间
                param.endTime = endTime;//结束时间
                param.treeId = treeIds;//根据tree查询
                param.level = level;//当前第几级树
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
	            	html += "<a onclick=selectDetailed("+row.id+")><img src=\"../../img/common/select.png\" width=\"20\" height=\"20\" alt=\"查询\" title=\"查询\"></a>";
		  		    html += "<a onclick=update("+row.id+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>";
		  		    html += "<a onclick=copy("+row.id+")><img src=\"../../img/common/copy.png\" width=\"20\" height=\"20\" alt=\"复制\" title=\"复制\"></a>";
		  		    if (row.contractState != 3){
		  		    	html +=	"<a onclick=zuofei("+row.id+") class='zuofei'><img src='../../img/common/zuofei.png' width='20' height='20' alt='作废' title='作废'></a>";
		  		    }		  		    
		  		    html +=	"<a onclick=del("+row.id+")><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>";
		  		    return html;
	            }
	        }, {
	            "data": "contractNumber"
	        }, {
	            "data": "customerCode"
	        }, {
	            "data": "saleNumber"
	        }, {
	            "data": "name"
	        }, {
	            "data": "contractStateName"
	        }, {
	        	"data": "contractDate"
	        }, {
	            "data": "remarks"
	        }, {
	            "data": "cancellationCause"
	        }, {
	            "data": "contractRoute",
	            render:function(data,type,row,meta) {
	                   if (data != null && data != ''){
	                	   return '已上传';
	                   } else {
	                	   return '未上传';
	                   }
	               }
	        }],
	        "createdRow": function( row, data, dataIndex ) {
                if(data.remarks.length > 10){//只有超长，才有td点击事件
                    $(row).children('td').eq(8).attr('onmouseover','javascript:changeShowRemarks(this);');
                }
                $(row).children('td').eq(8).attr('content',data.remarks);
	        	if(data.cancellationCause != null){
	        		if(data.cancellationCause.length > 10){//只有超长，才有td点击事件
	        			$(row).children('td').eq(9).attr('onmouseover','javascript:changeShowRemarks(this);');
	        		}
	        	}
                $(row).children('td').eq(9).attr('content',data.cancellationCause);
            },
		"order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一         
    		// 作废行变色
            if(aData.contractState == 3){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#999999");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");                	
            }
           return nRow;
        },
        "columnDefs": [
        	{
            "targets": 8,
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
            "targets": 9,
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
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

// 点击查询
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
	getList('','');
}

// 新增合同
function add(){
	location.href = 'contract_management_edit.html?flag=0';
}

// 复制合同
function copy(id){
	location.href = 'contract_management_edit.html?flag=2&id='+id;
}

// 修改合同
function update(id){
	var salesOrdersList;
	var isSales;
	$.ajax({
        type: "post",
        url: '../../salesContractManagement/getContractInfoById.action',
        async:false,
        data:{"id":id},
        dataType: "json",
        success: function (data) {
        	$.ajax({
    	        type: "post",
    	        url: '../../salesOrders/getSalesOrdersInfo.action',
    	        async:false,
    	        data:{"contractId":id},
    	        dataType: "json",
    	        success: function (data) {
    	        	salesOrdersList = data.data;   
    	        	isSales = salesOrdersList.length;
    	        }
    	    });	
        	var ContractInfoList = data.ContractInfoList;
        	// 合同可修改的场合
        	if(ContractInfoList[0].isModify == 0){
        		location.href = 'contract_management_edit.html?flag=1&id='+id+'&isSales='+isSales;
        	} else {
        		// 合同不可修改的场合
        		if(isSales>0){
	        		setTimeout(function(){swal({
						title: "该合同已生成销售订单,不可修改",
						type: "error",
						cancelButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					},
					function(){
						window.location.href="contract_management.html";
					}); },200);
	        	}else{
	        		location.href = 'contract_management_edit.html?flag=1&id='+id+'&isSales='+isSales;
	        	}
        	}
    	}
    });
	
}

//作废合同
function zuofei(id){
	var salesOrdersList;
	$.ajax({
        type: "post",
        url: '../../salesOrders/getSalesOrdersInfo.action',
        async:false,
        data:{"contractId":id},
        dataType: "json",
        success: function (data) {
        	salesOrdersList = data.data;
        	if(salesOrdersList.length>0){
        		setTimeout(function(){swal({
					title: "该合同已生成销售订单,不可作废",
					type: "error",
					cancelButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					window.location.href="contract_management.html";
				}); },200);
        	}else{
        		$.fancybox({
        			'href': '../contract/contract_zuofei.html?id='+id,
        			'width': 400,
        			'height': 300,
        			'type': 'iframe',
        			'hideOnOverlayClick': false,
        			'showCloseButton': false,
        			'onClosed': function() {
        				window.location.href = 'contract_management.html';
        			}
        		});
        	}
        }
       })
}

// 删除合同
function del(id){
	var salesOrdersList;
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
	        url: '../../salesOrders/getSalesOrdersInfo.action',
	        async:false,
	        data:{"contractId":id},
	        dataType: "json",
	        success: function (data) {
	        	salesOrdersList = data.data;
	        	if(salesOrdersList.length>0){
	        		setTimeout(function(){swal({
    					title: "该合同已生成销售订单,不可删除",
    					type: "error",
    					cancelButtonText: '确定',
    					cancelButtonFont: '微软雅黑',
    				},
    				function(){
    					window.location.href="contract_management.html";
    				}); },200);
	        	}else{
	        		$.ajax({
	        	        type: "post",
	        	        url: '../../salesContractManagement/deleteContractInfo.action',
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
	        					window.location.href="contract_management.html";
	        				}); },200);
	        	        }
	        		});
	        	}
	        }
		});
		
	});
}

// 通过合同查询下面关联的多个销售订单信息，销售订单查询下面关联的多个调度信息
function selectDetailed(id){
	window.location.href="contract_searchOrderAndDispatch.html?id="+id;
}
