//路径
var baseUrl ="";
$(function(){
	baseUrl = getContextPath();
	//查询
	search();
})
//模糊查询
function search(){
	DetectionindexList();
}
//跳转到修改页面
function update(id){
		$.fancybox({
			'href': 'weigher_maintenance_add.html?id='+id,
			'width': 733,
			'height': 380,
			'type': 'iframe',
			'hideOnOverlayClick': false,
			'showCloseButton': false,
			'onClosed': function() {
				window.location.href = 'weigher_maintenance.html';
			}
		});
}
//查询所有检斤员
function DetectionindexList(){
	 $("#DetectionindexList").DataTable({
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
         "ajax":baseUrl + "/WeigherMaintenance/getWeigherMaintenance.action",
         "deferRender": true,
         "columns": [{
         	 "data": "id"
         }, {
             "data": "id",
             render: function(data, type, row) {
 				var html = '';
 			    html =["<a style=\"margin: 0 15px;\" onclick=update("+row.id+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>"].join("");
 			    return html;
          }
         }, {
             "data": "username"
         }, {
             "data": "name"
         }, {
             "data": "anotherName"
         }, {
             "data": "telephone"
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



