var infoList;
$(document).ready(function() {
	// 树形信息显示
	showZtreeS();
	// 画面数据显示
	var param = {};
	getInfoList(param);
	$("#addBtn").fancybox({
		'href': 'procurementApplication_Edit.html',
		'width': 800,
		'height': 600,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'procurementApplication.html';
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
	var goodsName = "";
	var node = {};
	node = {
		id : "采购物资",
		pId : 0,
		name : "采购物资"
	};
	nodes.push(node);
	
	$.ajax({
		type: "post",
		url: "../../purchaseRequisition/getTreeList.action",
		data: {"keyword":$("#keyword").val()},
		dataType: "json",
		success: function (data) {
			if (data != null && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					
					// 物资名称变更时
					if (data[i].materielName != pid) {
						// 父节点-标题名称
						pid = "采购物资";
						// 子节点-物资名称
						if (data[i].materielName != null
								&& data[i].materielName != '') {
							node = {
								id : data[i].materielName,
								pId : pid,
								name : data[i].materielName
							};
							nodes.push(node);
							
							// 父节点-物资名称
							ppid = data[i].materielName;
						}

						// 子节点-物资型号
						if (data[i].goodsName != null
								&& data[i].goodsName != '') {
							node = {
								id : data[i].materielModel,
								pId : ppid,
								name : data[i].materielModel
							};
							nodes.push(node);
							goodsName = data[i].goodsName;
						}

						// 下一个父节点-当前物资名称
						pid = data[i].materielName;

					} else {
						// 物资名称相同时
						if(data[i].goodsName != goodsName){
							// 子节点-物资型号
							if (data[i].goodsName != null
									&& data[i].goodsName != '') {
								node = {
									id : data[i].materielModel,
									pId : ppid,
									name : data[i].materielModel
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
	$("#freibillNumber").val("");
	$("#zhuangtai").val("");
	if(id == '采购物资'){
		// 总结点全部查询
		getInfoList(param)
	}else if(pid == "采购物资"){
		param.materielName = id;
		// 根据物资名称查询
		getInfoList(param)
	}else{
		// 根据物资名称和物料型号查询
		param.materielModel = id;
		param.materielName = pid;
		// 根据物资名称查询
		getInfoList(param)
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
	var freibillNumber = $("#freibillNumber").val();
	param.serialID = freibillNumber;
	getInfoList(param);
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
        fixedColumns:   {
            leftColumns: 3
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
			url : "../../purchaseRequisition/getInfoList.action",
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
            "data": "serialID"
        }, {
            "data": "goodsName",
            render:function(data,type,row){
            	data = row.materielName + "--" + row.materielModel;
				return data;
            }
        },{
        	"data":"applyNumber"
        },{
            "data": "applyDepartment"
        }, {
            "data": "applicant"
        }, {
            "data": "isUrgentState",
            render:function(data,type,row){
            	if(data == 0){
            		data = "急";
            		return data;
            	}else if(data == 1){
            		data = "不急";
            		return data;
            	}
            }
        }, {
            "data": "applyDate"
        }, {
        	"data": "purpose"
        }],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一             
        }
    });
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}
//复制
function copy(id){
	$.fancybox({
		'href': 'procurementApplication_Edit.html?id='+id+"&flag=0",
		'width': 800,
		'height': 480,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'procurementApplication.html';
		}
	});
}
//修改
function update(id){
	$.fancybox({
		'href': 'procurementApplication_Edit.html?id='+id+"&flag=1",
		'width': 800,
		'height': 480,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'procurementApplication.html';
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
	},function(){
		$.ajax({
			type: "post",
			url: "../../purchaseRequisition/getAllPurchaseorderinfo.action",
			data: {"purchaseRequisitionId":id},
			dataType: "json",
			success: function (data) {
				if(data.length>0){
					setTimeout(function(){swal({
						title: "该采购申请单已生成采购合同,不可删除!",
						type: "error",
						cancelButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					},
					function(){
						window.location.href="procurementApplication.html";
					}); },200);
				}else{
					$.ajax({
						type: "post",
						url: "../../purchaseRequisition/deletePurchaseRequisition.action",
						data: {"id":id},
						dataType: "json",
						success: function (data) {
							if(data.code == 'success'){
								var param = {};
								getInfoList(param);	
								window.location.href = 'procurementApplication.html';
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