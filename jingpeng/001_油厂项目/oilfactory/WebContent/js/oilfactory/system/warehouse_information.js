//路径
var baseUrl ="";

$(function(){
	//新增仓库信息
	$("#addBtn").fancybox({
		'href': 'warehouse_information_edit.html',
		'width': 733,
		'height': 500,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'warehouse_information.html';
		}
	});
	//路径的获取
	baseUrl = getContextPath();
	//查询所有仓库信息
	getWareHouseList();
})

//修改仓库信息
function update(id){
		$.fancybox({
			'href': 'warehouse_information_edit.html?id='+id,
			'width': 733,
			'height': 380,
			'type': 'iframe',
			'hideOnOverlayClick': false,
			'showCloseButton': false,
			'onClosed': function() {
				window.location.href = 'warehouse_information.html';
			}
		});
}

//查询所有仓库信息
function getWareHouseList(){
	 $("#wareHouseList").DataTable({
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
         "ajax":baseUrl + "/WareHouse/getWareHouseInfo.action",
         "deferRender": true,
         "columns": [{
         	   "data": "rownumber"
         }, {
             "data": "operation"
         }, {
             "data": "warehouseNumber"
         }, {
             "data": "warehouseName"
         }, {
             "data": "type"
         }, {
             "data": "remarks"
         }],
         "createdRow" : function(row, data, dataIndex) {
	          if(data.remarks.length > 10){//只有超长，才有td点击事件
	             $(row).children('td').eq(5).attr('onmouseover','javascript:changeShowRemarks(this);');
	          }
	          $(row).children('td').eq(5).attr('content',data.remarks);
	      },
         "columnDefs": [{
             "targets": [0],
             "visible": true,
             "searchable": false
         },{
	    	  "targets": 5,
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
	 $('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

//删除仓库信息
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
			type:"post",
			url:baseUrl+'/Materiel/getMateriel.action',
			data:{'warehouseId':id},
			dataType:'json',
			success:function(data){
				if(data.data.length > 0){
					setTimeout(function(){swal({
    					title: "该仓库已生成物料信息,不可删除!",
    					type: "error",
    					cancelButtonText: '确定',
    					cancelButtonFont: '微软雅黑',
    				},
    				function(){
    					window.location.href="warehouse_information.html";
    				}); },200);
				}else{
					$.ajax({
				        type: "post",
				        url: baseUrl+'/WareHouse/delWareHouseInfo.action',
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
								window.location.href="warehouse_information.html";
							}); },200);
				        }
				   });
				}
			}
		});
	});
}

//模糊查询仓库信息
function search(){
	//静态界面获取供应商名称
	var warehouseName = $("#fyZldz").val();
	//获取主列表 刷新
	var table = $('#wareHouseList').dataTable();
	table.fnDestroy();
	$('#wareHouseList').DataTable({
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
          url: baseUrl + "/WareHouse/findWareHouseInfoByName.action",
          dataSrc: "data",
          data: function (d) {
        	var param = {};
              param.warehouseName = warehouseName;//传递供应商名称
              return param;//自定义需要传递的参数。
          }
      },
      "deferRender" : true,
      "columns": [{
    	   "data": "rownumber"
    }, {
        "data": "operation"
    }, {
        "data": "warehouseNumber"
    }, {
        "data": "warehouseName"
    }, {
        "data": "type",
        render:function(data,type,row){
        	if(data == "6"){
        		return "仓库";
        	}else if(data == "7"){
        		return "储罐";
        	}
        }
    }, {
        "data": "remarks"
    }],
    "createdRow" : function(row, data, dataIndex) {
        if(data.remarks.length > 10){//只有超长，才有td点击事件
           $(row).children('td').eq(5).attr('onmouseover','javascript:changeShowRemarks(this);');
        }
        $(row).children('td').eq(5).attr('content',data.remarks);
    },
      "columnDefs" : [ {
          "targets" : [ 0 ],
          "visible" : true,
          "searchable" : false
      },{
    	  "targets": 5,
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
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}