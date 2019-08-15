var baseUrl ="";
$(function(){
	//获取超链接拼接url
	baseUrl = getContextPath();
	//列表查询初始化
	clientVisitList();
})
//修改跳转
function update(id){
		$.fancybox({
			'href': 'client_visit_edit.html?id='+id,
			'width': 733,
			'height': 380,
			'type': 'iframe',
			'hideOnOverlayClick': false,
			'showCloseButton': false,
			'onClosed': function() {
				window.location.href = 'client_visit.html';
			}
		});
}
//拜访记录列表查询
function clientVisitList(){
	 $("#clientVisitList").DataTable({
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
         "ajax":baseUrl + "/ClientVisit/getClientVisit.action",
         "deferRender": true,
         "columns": [{
         	   "data": "serialnumber"
         }, {
             "data": "operate",
             render: function(data, type, row) {
 				var html = '';
 			    html =["<a style=\"margin: 0 15px;\" onclick=update("+row.id+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>",
 			    	"<a style=\"margin: 0 15px;\" onclick=del("+row.id+")><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>"].join("");
 			    return html;
          }
         }, {
             "data": "customerCode"
         }, {
             "data": "userInfoName"
         }, {
             "data": "visitDate"
         }, {
             "data": "visitForm"
         }, {
             "data": "visitContent"
         },{
             "data": "remarks"
         }],
         "createdRow" : function(row, data, dataIndex) {
	          $(row).children('td').eq(1).attr('style', 'text-align: center;')
	          if(data.cisDel == 1){
	        	  $(row).children('td').eq(2).attr('style', 'color: red;')
	          }
				if(data.remarks.length > 10){//只有超长，才有td点击事件
	                $(row).children('td').eq(7).attr('onmouseover','javascript:changeShowRemarks(this);');
	            }
	            $(row).children('td').eq(7).attr('content',data.remarks);
	            //拜访内容
	            if(data.visitContent.length > 10){//只有超长时，才有td点击事件
	            	$(row).children('td').eq(6).attr('onmouseover','javascript:changeShowRemarks(this);');
	            }
	            $(row).children('td').eq(6).attr('content',data.visitContent);
	      },
	      "columnDefs": [{
	            "targets": 7,
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
	        	"targets":6,
	        	"visible":true,
	        	"searchable":false,
	        	"type":"data",
	        	"render":function(data,type,full,meta){
	        		if(full.visitContent.length > 10){
	        			return getPartialRemarksHtml(full.visitContent);//显示部分信息
	        		}else{
	        			return full.visitContent;//显示原始全部
	        		}
	        	}
	        }]
     });
	 $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出 	 	
	 $('.dataTables_paginate').parent('div').addClass('col-sm-12');
}
//删除拜访记录
function del(id){
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
        url: baseUrl+'/ClientVisit/delClientVisit.action',
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
				window.location.href="client_visit.html";
			}); },200);
        }
   });
	   });
}

//根据客户名称查询拜访记录
function search(){
	//静态界面获取供应商名称
	var customerNames = $("#customerNames").val();
	//获取主列表 刷新
	var table = $('#clientVisitList').dataTable();
	table.fnDestroy();
	$('#clientVisitList').DataTable({
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
          url: baseUrl + "/ClientVisit/findCustomerinfoByName.action",
          dataSrc: "data",
          data: function (d) {
        	var param = {};
              param.customerNames = customerNames;//传递供应商名称
              return param;//自定义需要传递的参数。
          }
      },
      "deferRender" : true,
      "columns": [{
    	   "data": "serialnumber"
    }, {
        "data": "operate",
        render: function(data, type, row) {
			var html = '';
		    html =["<a style=\"margin: 0 15px;\" onclick=update("+row.id+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>",
		    	"<a style=\"margin: 0 15px;\" onclick=del("+row.id+")><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>"].join("");
		    return html;
     }
    }, {
        "data": "customerCode"
    }, {
        "data": "userInfoName"
    }, {
        "data": "visitDate"
    }, {
        "data": "visitForm"
    }, {
        "data": "visitContent"
    },{
        "data": "remarks"
    }],
    "createdRow" : function(row, data, dataIndex) {
        $(row).children('td').eq(1).attr('style', 'text-align: center;')
        if(data.cisDel == 1){
      	  $(row).children('td').eq(2).attr('style', 'color: red;')
        }
			if(data.remarks.length > 10){//只有超长，才有td点击事件
              $(row).children('td').eq(7).attr('onmouseover','javascript:changeShowRemarks(this);');
          }
          $(row).children('td').eq(7).attr('content',data.remarks);
          //拜访内容
          if(data.visitContent.length > 10){//只有超长时，才有td点击事件
          	$(row).children('td').eq(6).attr('onmouseover','javascript:changeShowRemarks(this);');
          }
          $(row).children('td').eq(6).attr('content',data.visitContent);
    },
    "columnDefs": [{
          "targets": 7,
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
      	"targets":6,
    	"visible":true,
    	"searchable":false,
    	"type":"data",
    	"render":function(data,type,full,meta){
    		if(full.visitContent.length > 10){
    			return getPartialRemarksHtml(full.visitContent);//显示部分信息
    		}else{
    			return full.visitContent;//显示原始全部
    		}
    	}
    }]
});
	 $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出 	
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

//ztree树节点查询
function findClickByName(id,pid){
	//获取主列表 刷新
	var table = $('#clientVisitList').dataTable();
	table.fnDestroy();
	$('#clientVisitList').DataTable({
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
          url: baseUrl + "/ClientVisit/findByNameAndId.action",
          dataSrc: "data",
          data: function (d) {
        	  var param = {};
        	  param.id = id;
              param.pid=pid;
             return param;//自定义需要传递的参数。
          }
      },
      "deferRender" : true,
      "columns": [{
    	   "data": "serialnumber"
    }, {
        "data": "operate",
        render: function(data, type, row) {
			var html = '';
		    html =["<a style=\"margin: 0 15px;\" onclick=update("+row.id+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>",
		    	"<a style=\"margin: 0 15px;\" onclick=del("+row.id+")><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>"].join("");
		    return html;
     }
    }, {
        "data": "customerCode"
    }, {
        "data": "userInfoName"
    }, {
        "data": "visitDate"
    }, {
        "data": "visitForm"
    }, {
        "data": "visitContent"
    },{
        "data": "remarks"
    }], 
    "createdRow" : function(row, data, dataIndex) {
        $(row).children('td').eq(1).attr('style', 'text-align: center;')
        if(data.cisDel == 1){
      	  $(row).children('td').eq(2).attr('style', 'color: red;')
        }
			if(data.remarks.length > 10){//只有超长，才有td点击事件
              $(row).children('td').eq(7).attr('onmouseover','javascript:changeShowRemarks(this);');
          }
          $(row).children('td').eq(7).attr('content',data.remarks);
          //拜访内容
          if(data.visitContent.length > 10){//只有超长时，才有td点击事件
          	$(row).children('td').eq(6).attr('onmouseover','javascript:changeShowRemarks(this);');
          }
          $(row).children('td').eq(6).attr('content',data.visitContent);
    },
    "columnDefs": [{
          "targets": 7,
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
      	"targets":6,
    	"visible":true,
    	"searchable":false,
    	"type":"data",
    	"render":function(data,type,full,meta){
    		if(full.visitContent.length > 10){
    			return getPartialRemarksHtml(full.visitContent);//显示部分信息
    		}else{
    			return full.visitContent;//显示原始全部
    		}
    	}
    }]
});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出	
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}
