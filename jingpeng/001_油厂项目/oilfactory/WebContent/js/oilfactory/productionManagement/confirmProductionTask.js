// 当前登录人姓名
var reviser = '';
// 权限的判断
var roleFlag = '';
$(function(){

	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		reviser = userInfo.name;
		var list = userInfo.rolecode;
		if(list.indexOf(",3,") != -1 || list.indexOf(",13,") != -1){
			// 调度 && 调度管理员(查看)
			roleFlag = false;
		}
		if(list.indexOf(",9,") != -1 || list.indexOf(",10,") != -1 || list.indexOf(",11,") != -1 || list.indexOf(",12,") != -1){
			// 生产部长    生产副部长   生产班长   检斤员(操作)
			roleFlag = true;
		}
		if(list.indexOf(",0,") != -1){
			// 管理员(操作)
			roleFlag = true;
		}
		if(list.indexOf(",7,") != -1){
			// 总经理(操作)
			roleFlag = true;
		}
	}
	
	// datatable
	getList();
})

// datatable
function getList(){
	// 产品名称
	var materielName = $("#materielName").val();
	// 产品规格
	var materielModel = $("#materielModel").val();
	// 状态
	var isProduction = $("#isProduction").val();
	var table1 = $('#confirmProductionTask').dataTable();
	table1.fnDestroy();
	$("#confirmProductionTask").DataTable({		
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
           url: "../../ConfirmProductionTask/getPlanMeasure.action",
           dataSrc: "data",
           data: function (d) {       	   
         	   var param = {};
         	   param.materielName = materielName;
         	   param.materielModel = materielModel;
         	   param.isProduction = isProduction;
               return param;//自定义需要传递的参数。
           }
       },
       "deferRender": true,
       "columns": 
       	[{
       	   "data": "id"
	        }, {
	            "data": "id",
	            render:function(data,type,row,meta) {
	            	var html = '';
	            	if(roleFlag){            		
			  		    if (row.isProduction != 0){
			  		    	html += "<a onclick=update("+row.id+","+row.isProduction+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>";
			  		    	html += "<a onclick=confirm("+row.id+")><img src=\"../../img/common/tijiao.png\" width=\"20\" height=\"20\" alt=\"确认生产\" title=\"确认生产\"></a>";	
			  		    } else {
			  		    	html += "<a onclick=update("+row.id+",0)><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
			  		    }
	            	}else{
	            		html += "<a onclick=update("+row.id+",0)><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
	            	}
		  		    		  		      		    		  		    
		  		    return html;
	            }
	        }, {
	            "data": "planNumber"
	        }, {
	            "data": "batch"
	        }, {
	            "data": "finishTime"
	        }, {
	            "data": "materielName"// 产品名称
	        }, {
	            "data": "materielModel"// 产品规格
	        }, {
	            "data": "customerCode"
	        }, {
	            "data": "customerName"
	        }, {
	        	"data": "planWeight"// 计划生产量
	        }, {
	        	"data": "unit"// 单位
	        }, {
	            "data": "isExamine",// 生产工艺通知单查看
            	render:function(data,type,row,meta) {
	                   return "<a onclick=chakan("+row.id+")>查看</a>";
	               }
	        }, {
	            "data": "storageLocationName"// 储位
	        }, {
	            "data": "productionPerson"// 确认人
	        }, {
	            "data": "productionDate"// 确认时间
	        }],
       "createdRow": function(row, data, dataIndex) {	
			// console.log(row);
			// console.log(data);
			// console.log(dataIndex);
       },
	   "order": [[ 1, 'asc' ]],
       "fnRowCallback" : function(nRow, aData, iDisplayIndex){
       		var html = '';
       		html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一
            // 调整行变色
            if(aData.isProduction == 1 && aData.adjustNumber > 0){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#ffcc99");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");                	
            }else if(aData.isProduction == 0){
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#999999");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");   
            }
            return nRow;
       }     
    });
//	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');	
}

//点击查询
function search(){
	getList();
}

//查看生产工艺通知单
function chakan(id){
	location.href = '../quality/productionProcessNotice_edit.html?id='+id+'&flag=2';
}

//修改
function update(id, isProduction){
	$.fancybox({
		'href': 'confirmProductionTask_edit.html?id='+id+"&isProduction="+isProduction,
		'width': 800,
		'height': 450,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'confirmProductionTask.html';
		}
	});
}

//生产确认
function confirm(id){
	var flag = false;
	$.ajax({
		type : "POST",
		url : "../../ConfirmProductionTask/getPlanMeasure.action",
		async:false,
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			var list = data.data[0];
			if (list.storageLocation==null){
				flag = true;
				}
		}
	});
	if(flag){
		swal("操作失败", "储位未填写，不能确认生产", "info");
		return;
	}
	swal({
		title: "确定要生产确认吗？",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
	},function(){
		$.ajax({
	        type: "post",
	        url: '../../ConfirmProductionTask/updateProductionTask.action',
	        async:false,
	        data:{"id":id,"reviser":reviser,"isProduction":0,"productionPerson":reviser},
	        dataType: "json",
	        success: function (data) {
	        	setTimeout(function(){swal({
					title: data.message,
					type: "success",
					cancelButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					window.location.href="confirmProductionTask.html";
				}); },200);
	        }
		})
	})	
}