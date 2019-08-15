var userId = "";

$(function(){
	getList();
})


// datatable数据初始化
function getList(){
	var param = formToJson($("#form1"));
	var table1 = $('#table1').dataTable();
	table1.fnDestroy();
	$("#table1").DataTable({
		 "paging": true,
         "lengthChange": false,
         "pageLength": 14,
         "searching": false,
         "ordering": false,
         "info": true,
         "sInfo": true,
         "autoWidth": false,
         "iDisplayStart" : 0,
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
            url: "../../FeedCompany/getFeedCompanyList.action",
            dataSrc: "data",
            data: function (d) {
                return param;//自定义需要传递的参数。
            }
        },
        "deferRender": true,
        "columns": [
        	{"data": "id"},
        	{
        		"data": "id",
        		render:function(data,type,row,meta) {
	            	var html = '';
		  		    html += "<a onclick='update("+row.id+")'style='text-decoration:none' href='javascript:;' title='编辑'>编辑&nbsp;&nbsp;</a>";
		  		    html += "<a onclick='del("+row.id+")' style='text-decoration:none' href='javascript:;' title='删除'>&nbsp;&nbsp;删除</a>";
		  		    return html;
	            }
        	},
        	{"data": "feedCompanyNumber"},
        	{"data": "feedCompanyName"},
        	{"data": "contacts"},     	
        	{"data": "telephone"},
        	{"data": "remarks"}
        	],
        "createdRow": function( row, data, dataIndex ) {
//        	console.log(data);
        },
        "order": [],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一      
            return nRow;
        },
        "columnDefs": []
    });
//	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
//	$('.table1_info').parent('div').addClass('col-sm-12');
}

// 新增供料单位
function add(){
	var index = layer.open({
		type: 2,
		title: '新增供料单位',
		content: 'supplyUnit_add.html',
	});
	layer.full(index);
}

//修改供料单位表
function update(id){
	var index = layer.open({
		type: 2,
		title: '新增材料信息',
		content: 'supplyUnit_add.html?id='+id,
	});
	layer.full(index);
}

// 删除供料单位表
function del(id){
	layer.confirm('确认要删除吗？', 
			function(index) {
				var params = {"id":id,"reviser":userId};	
				$.ajax({
					type: 'POST',
					url: '../../FeedCompany/deleteFeedCompany.action',
					data:JSON.stringify(params),
					dataType: 'json',
					contentType: 'application/json',
					success: function(data){
						if (data.code == 'success'){
							// 操作成功
							layer.msg(data.message, {
								icon: 1,
								time: 1000
							},function(){
								// 刷新当前页
								location.reload();
							});
						} else {
							// 操作失败
							layer.msg(data.message, {
								icon: 2,
								time: 1000
							},function(){
								// 刷新当前页
								location.reload();
							});
						}		
					}
				});		
		});
}
//查询方法
function search(){
	
	// 画面list初始化
	getList();
}