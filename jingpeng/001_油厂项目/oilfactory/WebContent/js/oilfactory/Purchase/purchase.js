// 当前登录人姓名
var reviser = '';
// 结点id
var treeIds = ''
// tree等级0:省  1:市  2:客户
var levels = '';
$(function(){
	$('#purchaseState').fSelect();
	// 树形信息显示
	//showZtreeS();
	getList('','');
	// 合同管理datatable
	//getList(treeIds,levels);
	
	// 左侧菜单展开收起
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
							name : data[i].cusname
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

// 合同管理datatable
function getList(treeId,level){
		//treeIds = treeId.substr(1,treeId.length-1);
	
	// 按销售员查询
	var serialID = $("#freibillNumber").val();
	// 按供应商查询
	var supplier = $("#freiSupplier").val();
/*	// 开始时间和结束时间的判定
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();*/
	// 按合同状态查询
	var obj = $("#purchaseState").val();
	if(obj==null){
		var ContractState = '';
	}else{
		var ContractState = obj.join(',');;
	}
	var table1 = $('#purchaseList').dataTable();
	table1.fnDestroy();
	$("#purchaseList").DataTable({
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
            url: "../../Purchase/getPurchasecontractList.action",
            dataSrc: "data",
            data: function (d) {
          	var param = {};
                param.serialID = serialID;//按销售员查询
                param.contractState = ContractState;//按合同状态查询
                param.supplierName = supplier
                return param;//自定义需要传递的参数。
            }
        },
        "deferRender": true,
        "columns": 
        	[{
        	   "data": "rowCount"
	        }, {
	            "data": "opean",
	            render:function(data,type,row,meta) {
	            	var html = '';
		  		    html += "<a onclick=update("+row.id+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>";
		  		    html += "<a onclick=copy("+row.id+")><img src=\"../../img/common/copy.png\" width=\"20\" height=\"20\" alt=\"复制\" title=\"复制\"></a>";
		  		    if (row.contractState != 3){
		  		    	html +=	"<a onclick=zuofei("+row.id+") class='zuofei'><img src='../../img/common/zuofei.png' width='20' height='20' alt='作废' title='作废'></a>";
		  		    }		  		    
		  		    html +=	"<a onclick=del("+row.id+")><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>";
		  		    return html;
	            }
	        }, {
	            "data": "serialID"
	        }, {
	            "data": "supplierName"
	        }, {
	            "data": "telephone"
	        }, {
	            "data": "contractState",
	            render:function(data,type,row,meta) {
	            	var html = '';
		  		    if(row.contractState == 0){
		  		    	html = "执行中"
		  		    }else if(row.contractState == 1){
		  		    	html = "未执行"
		  		    }else if(row.contractState == 2){
		  		    	html = "完成"
		  		    }else if(row.contractState == 3){
		  		    	html = "作废"
		  		    }else if(row.contractState == 4){
		  		    	html = "未签订"
		  		    }else if(row.contractState == 5){
		  		    	html = "其他"
		  		    }
		  		    return html;
	            }
	        }, {
	            "data": "date"
	        }, {
	            "data": "remarks"
	        }, {
	            "data": "cancellationCause"
	        }, {
	            "data": "fileName",
	            render:function(data,type,row,meta) {
	                   if (row.fileName != 0 ){
	                	   return '已上传';
	                   } else {
	                	   return '未上传';
	                   }
	               }
	        }],
	        "createdRow": function( row, data, dataIndex ) {
	        	if(data.remarks != null){
	        		if(data.remarks.length > 10){//只有超长，才有td点击事件
	        			$(row).children('td').eq(7).attr('onmouseover','javascript:changeShowRemarks(this);');
	        		}
	        	}
                $(row).children('td').eq(7).attr('content',data.remarks);
	        	if(data.cancellationCause != null){
	        		if(data.cancellationCause.length > 10){//只有超长，才有td点击事件
	        			$(row).children('td').eq(8).attr('onmouseover','javascript:changeShowRemarks(this);');
	        		}
	        	}
                $(row).children('td').eq(8).attr('content',data.cancellationCause);
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
            "targets": 7,
            "visible": true,
            "searchable": false,
            "type": "date",
            "render": function (data, type, full, meta) {
            	if(full.remarks != null){
	                if (full.remarks.length  > 10) {
	                    return getPartialRemarksHtml(full.remarks);//显示部分信息
	                } else {
	                    return full.remarks;//显示原始全部信息            }
	                }
            	}else{
            		full.remarks = "";
            		return full.remarks;
            	}
            }
        },{
            "targets": 8,
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
	location.href = 'purchase_edit.html?flag=0';
}

// 复制合同
function copy(id){
		location.href = 'purchase_edit.html?flag=2&id='+id;
}

// 修改合同
function update(id){
	location.href = 'purchase_edit.html?flag=1&id='+id;
}

//作废合同
function zuofei(id){
	var isHaveExAndStor = ExAndstorIsHave(id);
	if(isHaveExAndStor){
   	 setTimeout(function(){swal({
			 title: "该合同已被调用 不可作废",
			 type: "error",
			 cancelButtonText: '确定',
			 cancelButtonFont: '微软雅黑',
		 },
		 function(){
			 window.location.href="purchase.html";
		 }); },200);
    }else{
	$.fancybox({
		'href': '../Purchase/purchase_zuofei.html?id='+id,
		'width': 400,
		'height': 300,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'purchase.html';
		}
	});
    }
}

// 删除合同
function del(id){
	var isHaveExAndStor = ExAndstorIsHave(id);
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
	     if(isHaveExAndStor){
	    	 setTimeout(function(){swal({
				 title: "该合同已被调用 不可删除",
				 type: "error",
				 cancelButtonText: '确定',
				 cancelButtonFont: '微软雅黑',
			 },
			 function(){
				 window.location.href="purchase.html";
			 }); },200);
	     }else{
	    	 $.ajax({
	    		 type: "post",
	    		 url: '../../Purchase/delPurchasecontractById.action',
	    		 async:false,
	    		 data:{"id":id},
	    		 dataType: "json",
	    		 success: function (data) {
	    			 setTimeout(function(){swal({
	    				 title: data.message,
	    				 type: "success",
	    				 cancelButtonText: '确定',
	    				 cancelButtonFont: '微软雅黑',
	    			 },
	    			 function(){
	    				 window.location.href="purchase.html";
	    			 }); },200);
	    		 }
	    	 });
	     }
	});
}

function ExAndstorIsHave(id){
	var isOK;
	$.ajax({
        type: "post",
        url: '../../Purchase/getExAndstor.action',
        async:false,
        data:{"id":id},
        dataType: "json",
        success: function (data) {
        	if(data.code == "success"){
        		isOK =  false;
        	}else{
        		isOK =  true;
        	}
        }
     })
     return isOK;
}

