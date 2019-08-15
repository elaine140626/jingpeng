//路径
var baseUrl = "";

$(function() {
	//获取路径
	baseUrl = getContextPath();
	//查询供应商列表信息
	getList();
	//跳转到新增供应商添加页面
	$("#addBtn").fancybox({
		'href': 'provider_edit.html',
		'width': 800,
		'height': 450,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'provider.html';
		}
	});
});

//查询供应商列表信息
function getList(){
		$('#provider').DataTable({
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
              url: baseUrl + "/Provider/getProvider.action",
              dataSrc: "data",
              data: function (d) {
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
	    	  "data": "supplierNumber"
	      }, {
	          "data": "supplierName"
	      }, {
	          "data": "address"
	      }, {
	          "data": "contacts"
	      }, {
	          "data": "telephone"
	      }, {
	          "data": "otherNumbers"
	      },{
	          "data": "arrearsMoney"
	      }, {
	          "data": "remarks"
	      }],
	      "createdRow" : function(row, data, dataIndex) {
	    	  console.log(data);
	          $(row).children('td').eq(1).attr('style', 'text-align: center;')
	          if(data.remarks.length > 10){//只有超长，才有td点击事件
	             $(row).children('td').eq(9).attr('onmouseover','javascript:changeShowRemarks(this);');
	          }
	          $(row).children('td').eq(9).attr('content',data.remarks);
	      },
	      "columnDefs" : [ {
	          "targets" : [ 0 ],
	          "visible" : true,
	          "searchable" : false
	      },{
	    	  "targets": 9,
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

//根据供应商名称模糊查询
function search(){
	//静态界面获取供应商名称
	var SupplierName = $("#fyZldz").val();
	//获取主列表 刷新
	var table = $('#provider').dataTable();
	table.fnDestroy();
	$('#provider').DataTable({
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
          url: baseUrl + "/Provider/findProvider.action",
          dataSrc: "data",
          data: function (d) {
        	var param = {};
              param.SupplierName = SupplierName;//传递供应商名称
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
    	  "data": "supplierNumber"
      }, {
          "data": "supplierName"
      }, {
          "data": "address"
      }, {
          "data": "contacts"
      }, {
          "data": "telephone"
      }, {
          "data": "otherNumbers"
      }, {
          "data": "arrearsMoney"
      }, {
          "data": "remarks"
      }],
      "createdRow" : function(row, data, dataIndex) {
          $(row).children('td').eq(1).attr('style', 'text-align: center;')
          if(data.remarks.length > 10){//只有超长，才有td点击事件
             $(row).children('td').eq(9).attr('onmouseover','javascript:changeShowRemarks(this);');
          }
          $(row).children('td').eq(9).attr('content',data.remarks);
      },
      "columnDefs" : [ {
          "targets" : [ 0 ],
          "visible" : true,
          "searchable" : false
      },{
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

//修改供应商信息
function update(id){
	$.fancybox({
		'href': 'provider_edit.html?id='+id,
		'width': 800,
		'height': 450,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'provider.html';
		}
	});
	}

//删除供应商信息
function del(id){
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
			url : baseUrl + "/Provider/getAllStoragemeasure.action",
			data : {"supplierId":id},
			async:false,
			dataType : "json",
			success:function(data){
				if(data.length>0){
					setTimeout(function(){swal({
						title: "该供应商已生成入库单,不可删除!",
						type: "error",
						cancelButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					},
					function(){
						window.location.href="provider.html";
					}); },200);
				}else{
					$.ajax({
						type : "POST",
						url : baseUrl + "/Provider/delProviderById.action",
						data : {"id":id},
						async:false,
						dataType : "json",
						success : function(data) {
								setTimeout(function(){swal({
									title: data.message,
									type: "success",
									cancelButtonText: '确定',
									cancelButtonFont: '微软雅黑',
								},
								function(){
									window.location.href="provider.html";
								}); },200);
						}
					});
				}
			}
		});
});
}

