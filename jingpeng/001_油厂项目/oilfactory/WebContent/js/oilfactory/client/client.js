//初始化方法
var baseUrl = "";
$(function(){  
<<<<<<< .mine
	baseUrl = getContextPath();
	getList();
||||||| .r200
	// 树形结构
	showZtreeS();
	
	// 画面数据初始化
	var param = {};
	getList(param);
=======
	// 树形结构
	showZtreeS();
	// 画面数据初始化
	var param = {};
	getList(param);
>>>>>>> .r322
	$("#box_border1").hide();
})

//客户信息获取
<<<<<<< .mine
function getList(){
||||||| .r200
function getList(param){
	//获取主列表 刷新
	var table = $('#clientList').dataTable();
	table.fnDestroy();
	
=======
function getList(param){
	//获取主列表 刷新
	var table = $('#clientList').dataTable();
	table.fnDestroy();
>>>>>>> .r322
	$("#clientList").DataTable({
        "paging": true,
        "lengthChange": false,
        "pageLength": 14,
        "searching": false,
        "ordering": false,
        "info": true,
        "sInfo": true,
        "autoWidth": false,
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
        	type:"post",
        	url:baseUrl+"/client/getclient.action",
        	dataSrc:"data",
        	data:function(d){
        	}
        },
        "deferRender": true,
        "columns": [{
        	"data": "rownum"
        },{
	          "data": "uuid",
	          render:function(data,type,row){
	        	  var html = '';
	  		    html =["<a style=\"margin: 0 15px;\" onclick=update("+row.uuid+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>",
	  		    	"<a style=\"margin: 0 15px;\" onclick=del("+row.uuid+")><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>"].join("");
	  		    return html;
	          }
	    },{
        	"data":"customerCode"
        },{
        	"data":"customername"
        },{
            "data": "province"
        }, {
            "data": "city"
        }, {
            "data": "intentionallevel"
        }, {
            "data": "procurementlevel"
        }, {
            "data": "creditlevel"
        }, {
            "data": "address"
        }, {
            "data": "contacts"
        }, {
            "data": "telephone"
        }, {
            "data": "othernumbers"
        }, {
            "data": "remarks"
        }],
        "createdRow": function (row, data, dataIndex) {
			$(row).children('td').eq(1).attr('style','text-align: center;');
			if(data.remarks.length > 10){//只有超长，才有td点击事件
                $(row).children('td').eq(12).attr('onmouseover','javascript:changeShowRemarks(this);');
            }
            $(row).children('td').eq(12).attr('content',data.remarks);
      },
        "columnDefs": [{
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
        }]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
<<<<<<< .mine
	$("#clientList1").DataTable({
        "paging": true,
        "lengthChange": false,
        "pageLength": 14,
        "searching": false,
        "ordering": false,
        "info": true,
        "sInfo": true,
        "autoWidth": false,
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
        	type:"post",
        	url:baseUrl+"/client/getclient.action",
        	dataSrc:"data",
        	data:function(d){
        	}
        },
        "deferRender": true,
        "columns": [{
        	"data": "rownum"
        },{
        	"data":"customername"
        },{
            "data": "province"
        }, {
            "data": "city"
        }, {
            "data": "intentionallevel"
        }, {
            "data": "procurementlevel"
        }, {
            "data": "creditlevel"
        }, {
            "data": "address"
        }, {
            "data": "contacts"
        }, {
            "data": "telephone"
        }, {
            "data": "othernumbers"
        }, {
            "data": "remarks"
        }],
        "createdRow": function (row, data, dataIndex) {
			console.log(data);
			$(row).children('td').eq(1).attr('style','text-align: center;');
			if(data.remarks.length > 10){//只有超长，才有td点击事件
                $(row).children('td').eq(12).attr('onmouseover','javascript:changeShowRemarks(this);');
            }
            $(row).children('td').eq(12).attr('content',data.remarks);
      },
        "columnDefs": [{
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
        }]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
||||||| .r200
	
	//获取副列表 刷新
	var table1 = $('#clientList1').dataTable();
	table1.fnDestroy();
	$("#clientList1").DataTable({
        "paging": true,
        "lengthChange": false,
        "pageLength": 14,
        "searching": false,
        "ordering": false,
        "info": true,
        "sInfo": true,
        "autoWidth": false,
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
        	type:"post",
        	url:"../../client/getclient.action",
        	dataSrc:"data",
        	data:function(d){
        		return param;
        	}
        },
        "deferRender": true,
        "columns": [{
        	"data": "rownum"
        },{
        	"data":"customername"
        },{
            "data": "province"
        }, {
            "data": "city"
        }, {
            "data": "intentionallevel"
        }, {
            "data": "procurementlevel"
        }, {
            "data": "creditlevel"
        }, {
            "data": "address"
        }, {
            "data": "contacts"
        }, {
            "data": "telephone"
        }, {
            "data": "othernumbers"
        }, {
            "data": "remarks"
        }],
        "createdRow": function (row, data, dataIndex) {
			$(row).children('td').eq(1).attr('style','text-align: center;');
			if(data.remarks.length > 10){//只有超长，才有td点击事件
                $(row).children('td').eq(12).attr('onmouseover','javascript:changeShowRemarks(this);');
            }
            $(row).children('td').eq(12).attr('content',data.remarks);
      },
        "columnDefs": [{
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
        }]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
=======
>>>>>>> .r322
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}
//根据客户名称模糊查询
function search(){
	//静态界面获取客户名称
	var customername = $("#fyZldz2").val();
	//获取主列表 刷新
	var table = $('#clientList').dataTable();
	table.fnDestroy();
	//获取副列表 刷新
	var table1 = $('#clientList1').dataTable();
	table1.fnDestroy();
	$('#clientList').DataTable({
	  "paging": true,
      "lengthChange": false,
      "pageLength": 12,
      "searching": false,
      "ordering": false,
      "info": true,
      "sInfo": true,
      "autoWidth": false,
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
          url: baseUrl + "/client/findCustomerinfo.action",
          dataSrc: "data",
          data: function (d) {
        	var param = {};
              param.customername = customername;//传递供应商名称
              return param;//自定义需要传递的参数。
          }
      },
      "deferRender": true,
      "columns": [{
      	"data": "rownum"
      }, {
          "data": "uuid",
          render:function(data,type,row){
        	  var html = '';
  		    html =["<a style=\"margin: 0 15px;\" onclick=update("+row.uuid+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>",
  		    	"<a style=\"margin: 0 15px;\" onclick=del("+row.uuid+")><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>"].join("");
  		    return html;
          }
      },{
      	"data":"customerCode"
      },{
          "data": "province"
      }, {
          "data": "city"
      }, {
          "data": "intentionallevel"
      }, {
          "data": "procurementlevel"
      }, {
          "data": "creditlevel"
      }, {
          "data": "address"
      }, {
          "data": "contacts"
      }, {
          "data": "telephone"
      }, {
          "data": "othernumbers"
      }, {
          "data": "remarks"
      }],
      "createdRow": function (row, data, dataIndex) {
			console.log(data);
			$(row).children('td').eq(1).attr('style','text-align: center;');
			if(data.remarks.length > 10){//只有超长，才有td点击事件
                $(row).children('td').eq(12).attr('onmouseover','javascript:changeShowRemarks(this);');
            }
            $(row).children('td').eq(12).attr('content',data.remarks);
      },
      "columnDefs": [{
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
      }]
});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	
	$('#clientList1').DataTable({
		  "paging": true,
	      "lengthChange": false,
	      "pageLength": 12,
	      "searching": false,
	      "ordering": false,
	      "info": true,
	      "sInfo": true,
	      "autoWidth": false,
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
	          url: baseUrl + "/client/findCustomerinfo.action",
	          dataSrc: "data",
	          data: function (d) {
	        	var param = {};
	              param.customername = customername;//传递供应商名称
	              return param;//自定义需要传递的参数。
	          }
	      },
	      "deferRender": true,
	      "columns": [{
	      	"data": "rownum"
	      },{
	      	"data":"customername"
	      },{
	          "data": "province"
	      }, {
	          "data": "city"
	      }, {
	          "data": "intentionallevel"
	      }, {
	          "data": "procurementlevel"
	      }, {
	          "data": "creditlevel"
	      }, {
	          "data": "address"
	      }, {
	          "data": "contacts"
	      }, {
	          "data": "telephone"
	      }, {
	          "data": "othernumbers"
	      }, {
	          "data": "remarks"
	      }],
	      "createdRow": function (row, data, dataIndex) {
				console.log(data);
				$(row).children('td').eq(1).attr('style','text-align: center;');
				if(data.remarks.length > 10){//只有超长，才有td点击事件
	                $(row).children('td').eq(12).attr('onmouseover','javascript:changeShowRemarks(this);');
	            }
	            $(row).children('td').eq(12).attr('content',data.remarks);
	      },
	      "columnDefs": [{
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
	        }]
	});
		$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
		$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}
//修改客户信息
function update(id){
	$.fancybox({
		'href': 'client_edit.html?uuid='+id,
		'width': 733,
		'height': 380,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'client.html';
		}
	});
	}
//删除客户信息
function del(uuid){
	baseUrl = getContextPath();
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
		type : "POST",
		url : baseUrl + "/client/delCustomerinfoById.action",
		data : {"uuid":uuid},
		async:false,
		dataType : "json",
		success : function(data) {
				setTimeout(function(){swal({
					title: data.message,
					type: data.code,
					cancelButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					window.location.href="client.html";
				}); },200);
		}
	});
});
}
function findClickByName(id,pid){
	//获取主列表 刷新
	var table = $('#clientList').dataTable();
	table.fnDestroy();
	//获取副列表 刷新
	var table1 = $('#clientList1').dataTable();
	table1.fnDestroy();
	$('#clientList').DataTable({
		  "paging": true,
	      "lengthChange": false,
	      "pageLength": 12,
	      "searching": false,
	      "ordering": false,
	      "info": true,
	      "sInfo": true,
	      "autoWidth": false,
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
	          url: baseUrl + "/client/findByNameAndId.action",
	          dataSrc: "data",
	          data: function (d) {
	        	  var param = {};
	        	  param.id = id;
	              param.pid=pid;
	             return param;//自定义需要传递的参数。
	          }
	      },
	      "deferRender": true,
	      "columns": [{
	      	"data": "rownum"
	      }, {
	          "data": "uuid",
	          render:function(data,type,row){
	        	  var html = '';
	  		    html =["<a style=\"margin: 0 15px;\" onclick=update("+row.uuid+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>",
	  		    	"<a style=\"margin: 0 15px;\" onclick=del("+row.uuid+")><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>"].join("");
	  		    return html;
	          }
	      },{
	      	"data":"customerCode"
	      },{
	          "data": "province"
	      }, {
	          "data": "city"
	      }, {
	          "data": "intentionallevel"
	      }, {
	          "data": "procurementlevel"
	      }, {
	          "data": "creditlevel"
	      }, {
	          "data": "address"
	      }, {
	          "data": "contacts"
	      }, {
	          "data": "telephone"
	      }, {
	          "data": "othernumbers"
	      }, {
	          "data": "remarks"
	      }],
	      "createdRow": function (row, data, dataIndex) {
				console.log(data);
				$(row).children('td').eq(1).attr('style','text-align: center;');
				if(data.remarks.length > 10){//只有超长，才有td点击事件
	                $(row).children('td').eq(12).attr('onmouseover','javascript:changeShowRemarks(this);');
	            }
	            $(row).children('td').eq(12).attr('content',data.remarks);
	      },
	      "columnDefs": [{
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
	        }]
	});
		$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
		$('.dataTables_paginate').parent('div').addClass('col-sm-12');
		$('#clientList1').DataTable({
			  "paging": true,
		      "lengthChange": false,
		      "pageLength": 12,
		      "searching": false,
		      "ordering": false,
		      "info": true,
		      "sInfo": true,
		      "autoWidth": false,
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
		          url: baseUrl + "/client/findByNameAndId.action",
		          dataSrc: "data",
		          data: function (d) {
		        	  var param = {};
		        	  param.id = id;
		              param.pid=pid;
		              return param;//自定义需要传递的参数。
		          }
		      },
		      "deferRender": true,
		      "columns": [{
		      	"data": "rownum"
		      },{
		      	"data":"customername"
		      },{
		          "data": "province"
		      }, {
		          "data": "city"
		      }, {
		          "data": "intentionallevel"
		      }, {
		          "data": "procurementlevel"
		      }, {
		          "data": "creditlevel"
		      }, {
		          "data": "address"
		      }, {
		          "data": "contacts"
		      }, {
		          "data": "telephone"
		      }, {
		          "data": "othernumbers"
		      }, {
		          "data": "remarks"
		      }],
		      "createdRow": function (row, data, dataIndex) {
					console.log(data);
					$(row).children('td').eq(1).attr('style','text-align: center;');
					if(data.remarks.length > 10){//只有超长，才有td点击事件
		                $(row).children('td').eq(12).attr('onmouseover','javascript:changeShowRemarks(this);');
		            }
		            $(row).children('td').eq(12).attr('content',data.remarks);
		      },
		      "columnDefs": [{
		            "targets": 12,
		            "visible": true,
		            "searchable": false,
		            "type": "date",
		            "render": function (data, type, full, meta) {
		            	if(full.remarks!=null){
			                if (full.remarks.length  > 10) {
			                    return getPartialRemarksHtml(full.remarks);//显示部分信息
			                } else {
			                    return full.remarks;//显示原始全部信息            }
			                }
		            	}
		            }
		        }]
		});
			$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
			$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}
<<<<<<< .mine
||||||| .r200

function findClick(event, treeId, treeNode, isCancel) {
	var id = treeNode.id;
	var pid = treeNode.pId;
	var param = {};
	if(id != "客户信息"){
		param.id = id;
		param.pid = pid;
	}

	// 根据客户名称查询
	getList(param);
}
=======
//ztree节点查询
function findClick(event, treeId, treeNode, isCancel) {
	var id = treeNode.id;
	var pid = treeNode.pId;
	var param = {};
	if(id != "客户信息"){
		param.id = id;
		param.pid = pid;
	}

	// 根据客户名称查询
	getList(param);
}
>>>>>>> .r322
