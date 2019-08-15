//获取路径
var baseUrl ="";
$(function(){
	//获取路径
	baseUrl = getContextPath();
	//查询所有起运地
	TransportsList();
})

//跳转到修改页面
function update(id){
		$.fancybox({
			'href': 'start_transports_edit.html?id='+id,
			'width': 733,
			'height': 380,
			'type': 'iframe',
			'hideOnOverlayClick': false,
			'showCloseButton': false,
			'onClosed': function() {
				window.location.href = 'start_transports.html';
			}
		});
}

//查询所有起运地
function TransportsList(){
	 $("#TransportsList").DataTable({
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
         "ajax":baseUrl + "/Transports/getStartTransports.action",
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
             "data": "startAddress"
         }, {
             "data": "deviation"
         }, {
             "data": "remarks"
         }],
         "createdRow" : function(row, data, dataIndex) {
	          $(row).children('td').eq(1).attr('style', 'text-align: center;')
	          if(data.cisDel == 1){
	        	  $(row).children('td').eq(2).attr('style', 'color: red;')
	          }
				if(data.remarks.length > 10){//只有超长，才有td点击事件
	                $(row).children('td').eq(4).attr('onmouseover','javascript:changeShowRemarks(this);');
	            }
	            $(row).children('td').eq(4).attr('content',data.remarks);

	      },
	      "columnDefs": [{
	            "targets": 4,
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

//删除止运地
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
	        url: baseUrl+'/Transports/getStartTransportsById.action',
	        async:false,
	        data:{"id":id},
	        dataType: "json",
	        success: function (data) {
	        	var transports = data.data;
	        	if(transports != null){
		        		setTimeout(function(){swal({
		    				title: '该起运地已与来料加工关联,不可删除!',
		    				type: "error",
		    				cancelButtonText: '确定',
		    				cancelButtonFont: '微软雅黑',
		    			},
		    			function(){
		    				window.location.href="start_transports.html";
		    			}); },200);
	        	}else{
    	        		$.ajax({
    	        	        type: "post",
    	        	        url: baseUrl+'/Transports/delStartTransports.action',
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
    	        					window.location.href="start_transports.html";
    	        				}); },200);
    	        	        }
    	        	   });
	        	}
	        }
		});
	});
}

//模糊查询
function search(){
	//静态界面获客户名称
	var customerNames = $("#customerNames").val();
	//获取主列表 刷新
	var table = $('#TransportsList').dataTable();
	table.fnDestroy();
	$('#TransportsList').DataTable({
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
          url: baseUrl + "/Transports/findStartCustomerinfoByName.action",
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
          "data": "startAddress"
      }, {
          "data": "deviation"
      }, {
          "data": "remarks"
      }],
    "createdRow" : function(row, data, dataIndex) {
        $(row).children('td').eq(1).attr('style', 'text-align: center;')
        if(data.cisDel == 1){
      	  $(row).children('td').eq(2).attr('style', 'color: red;')
        }
			if(data.remarks.length > 10){//只有超长，才有td点击事件
              $(row).children('td').eq(4).attr('onmouseover','javascript:changeShowRemarks(this);');
          }
          $(row).children('td').eq(4).attr('content',data.remarks);

    },
    "columnDefs": [{
          "targets": 4,
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


