var baseUrl ="";
var id;
$(function(){
	baseUrl = getContextPath();
	getFleetInfo()
})

function update(id){
	
	location.href = 'team_information_edit.html?id='+id;
	}

function getFleetInfo(){
	 $("#fleetList").DataTable({
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
         "ajax":baseUrl + "/FleetInfo/getFleetInfo.action",
         "deferRender": true,
         "columns": [{
         	   "data": "rownumber"
         }, {
             "data": "operation"
         }, {
             "data": "fleetNumber"
         }, {
             "data": "fleetName"
         }, {
             "data": "contacts"
         }, {
             "data": "telephone"
         }, {
             "data": "remarks"
         }],
         "createdRow" : function(row, data, dataIndex) {
	          if(data.remarks.length > 10){//只有超长，才有td点击事件
	             $(row).children('td').eq(6).attr('onmouseover','javascript:changeShowRemarks(this);');
	          }
	          $(row).children('td').eq(6).attr('content',data.remarks);
	      },
         "columnDefs": [{
             "targets": [0],
             "visible": true,
             "searchable": false
         },{
	    	  "targets": 6,
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
	 
	 $("#fleetList1").DataTable({
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
         "ajax":baseUrl + "/FleetInfo/getFleetInfo.action",
         "deferRender": true,
         "columns": [{
         	   "data": "rownumber"
         }, {
             "data": "fleetNumber"
         }, {
             "data": "fleetName"
         }, {
             "data": "contacts"
         }, {
             "data": "telephone"
         }, {
             "data": "remarks"
         }],
         "createdRow" : function(row, data, dataIndex) {
	          if(data.remarks.length > 10){//只有超长，才有td点击事件
	             $(row).children('td').eq(6).attr('onmouseover','javascript:changeShowRemarks(this);');
	          }
	          $(row).children('td').eq(6).attr('content',data.remarks);
	      },
         "columnDefs": [{
             "targets": [0],
             "visible": true,
             "searchable": false
         },{
	    	  "targets": 6,
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
	        url: baseUrl+'/FleetInfo/getAllCarInfo.action',
	        async:false,
	        data:{"fleetId":id},
	        dataType: "json",
	        success: function (data) {
	        	if(data.length>0){
	        		setTimeout(function(){swal({
	    				title: '该车队已有车辆,不可删除!',
	    				type: "error",
	    				cancelButtonText: '确定',
	    				cancelButtonFont: '微软雅黑',
	    			},
	    			function(){
	    				window.location.href="team_information.html";
	    			}); },200);
	        	}else{
	        		$.ajax({
	        	        type: "post",
	        	        url: baseUrl+'/FleetInfo/delFleetInfo.action',
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
	        					window.location.href="team_information.html";
	        				}); },200);
	        	        }
	        	   });
	        	}
	        }
		});
	});
}

function search(){
	//静态界面获取供应商名称
	var fleetName = $("#fyZldz").val();
	//获取主列表 刷新
	var table = $('#fleetList').dataTable();
	table.fnDestroy();
	//获取副列表 刷新
	var table1 = $('#fleetList1').dataTable();
	table1.fnDestroy();
	$('#fleetList').DataTable({
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
          url: baseUrl + "/FleetInfo/findFleetInfoByName.action",
          dataSrc: "data",
          data: function (d) {
        	var param = {};
              param.fleetName = fleetName;//传递供应商名称
              return param;//自定义需要传递的参数。
          }
      },
      "deferRender" : true,
      "columns": [{
    	   "data": "rownumber"
    }, {
        "data": "operation"
    }, {
        "data": "fleetNumber"
    }, {
        "data": "fleetName"
    }, {
        "data": "contacts"
    }, {
        "data": "telephone"
    }, {
        "data": "remarks"
    }],
    "createdRow" : function(row, data, dataIndex) {
        if(data.remarks.length > 10){//只有超长，才有td点击事件
           $(row).children('td').eq(6).attr('onmouseover','javascript:changeShowRemarks(this);');
        }
        $(row).children('td').eq(6).attr('content',data.remarks);
    },
      "columnDefs" : [ {
          "targets" : [ 0 ],
          "visible" : true,
          "searchable" : false
      },{
    	  "targets": 6,
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
	
	
	$('#fleetList1').DataTable({
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
	          url: baseUrl + "/FleetInfo/findFleetInfoByName.action",
	          dataSrc: "data",
	          data: function (d) {
	        	var param = {};
	              param.fleetName = fleetName;//传递供应商名称
	              return param;//自定义需要传递的参数。
	          }
	      },
	      "deferRender" : true,
	      "columns": [{
	    	   "data": "rownumber"
	    }, {
	        "data": "fleetNumber"
	    }, {
	        "data": "fleetName"
	    }, {
	        "data": "contacts"
	    }, {
	        "data": "telephone"
	    }, {
	        "data": "remarks"
	    }],
	    "createdRow" : function(row, data, dataIndex) {
	          if(data.remarks.length > 10){//只有超长，才有td点击事件
	             $(row).children('td').eq(6).attr('onmouseover','javascript:changeShowRemarks(this);');
	          }
	          $(row).children('td').eq(6).attr('content',data.remarks);
	      },
	      "columnDefs" : [ {
	          "targets" : [ 0 ],
	          "visible" : true,
	          "searchable" : false
	      },{
	    	  "targets": 6,
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