var baseUrl ="";
$(function(){
	baseUrl = getContextPath();
	CachetPersonnelList();
})

function update(id){
		$.fancybox({
			'href': 'cachetPersonnel_edit.html?id='+id,
			'width': 733,
			'height': 380,
			'type': 'iframe',
			'hideOnOverlayClick': false,
			'showCloseButton': false,
			'onClosed': function() {
				window.location.href = 'cachetPersonnel.html';
			}
		});
}
function CachetPersonnelList(){
	 $("#CachetPersonnelList").DataTable({
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
         "ajax":baseUrl + "/CachetPersonnel/getCachetPersonnel.action",
         "deferRender": true,
         "columns": [{
         	 "data": "id"
         }, {
             "data": "operate",
             render: function(data, type, row) {
 				var html = '';
 			    html =["<a style=\"margin: 0 15px;\" onclick=update("+row.id+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>",
 			    	"<a style=\"margin: 0 15px;\" onclick=del("+row.id+")><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>"].join("");
 			    return html;
          }
         }, {
             "data": "cachetName"
         }, {
             "data": "electronicsName",
             render: function(data, type, row) {
  				var html = '';
  				if(data){
					html = "<img src='" + baseUrl + "/file/preview.action?fileName=" + data+"' width='150' height='40'>";
				}else{
					html = "";
				}
  			    return html;
           }
         }
         ],
         "order": [[ 1, 'asc' ]],
         "fnRowCallback" : function(nRow, aData, iDisplayIndex){
             $("td:first", nRow).html("<input name='radio' type='hidden' value=\""+ aData.id +"\" />"+(iDisplayIndex +1));//设置序号位于第一列，并顺次加一               	
            return nRow;
         }
     });
	 $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出 	 
	 $('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

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
	        url: baseUrl+'/CachetPersonnel/delCachetPersonnel.action',
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
					window.location.href="cachetPersonnel.html";
				}); },200);
	        }
	   });
	});
}


function search(){
	//静态界面获客户名称
	var cachetName = $("#cachetName").val();
	//获取主列表 刷新
	var table = $('#CachetPersonnelList').dataTable();
	table.fnDestroy();
	$('#CachetPersonnelList').DataTable({
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
          url: baseUrl + "/CachetPersonnel/findCachetPersonnelByName.action",
          dataSrc: "data",
          data: function (d) {
        	var param = {};
              param.cachetName = cachetName;//传递供应商名称
              return param;//自定义需要传递的参数。
          }
      },
      "deferRender" : true,
      "columns": [{
     	   "data": "id"
      }, {
          "data": "operate",
          render: function(data, type, row) {
				var html = '';
			    html =["<a style=\"margin: 0 15px;\" onclick=update("+row.id+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>",
			    	"<a style=\"margin: 0 15px;\" onclick=del("+row.id+")><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>"].join("");
			    return html;
       }
      }, {
          "data": "cachetName"
      }, {
          "data": "electronicsName",
          render: function(data, type, row) {
				var html = '';
				if(data){
					html = "<img src='" + baseUrl + "/file/preview.action?fileName=" + data+"' width='150' height='40'>";
				}else{
					html = "";
				}
			    return html;
         }
      }],
      "order": [[ 1, 'asc' ]],
      "fnRowCallback" : function(nRow, aData, iDisplayIndex){
          $("td:first", nRow).html("<input name='radio' type='hidden' value=\""+ aData.id +"\" />"+(iDisplayIndex +1));//设置序号位于第一列，并顺次加一               	
         return nRow;
      }
});
	
	 $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出 	
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}