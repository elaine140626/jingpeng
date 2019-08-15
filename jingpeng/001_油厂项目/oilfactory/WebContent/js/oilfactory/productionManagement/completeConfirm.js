// 当前登录人姓名
var reviser = '';
// 权限判断
var roleFlag = '';
$(function(){

	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		reviser = userInfo.id;
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
	var isComplete = $("#isComplete").val();
	var table1 = $('#table').dataTable();
	table1.fnDestroy();
	$("#table").DataTable({		
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
           url: "../../completeConfirm/getCompleteConfirm.action",
           dataSrc: "data",
           data: function (d) {       	   
         	   var param = {};
         	   param.materielName = materielName;
         	   param.materielModel = materielModel;
         	   param.isComplete = isComplete;
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
	            		html += "<a onclick=update("+row.id+","+row.isComplete+")><img src=\"../../img/common/tijiao.png\" width=\"20\" height=\"20\" alt=\"提交\" title=\"提交\"></a>";
	            	}else{
	            		html += "<a onclick=update("+row.id+",-1)><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
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
	            "data": "isExamine",// 生产工艺通知单查看
            	render:function(data,type,row,meta) {
	                   return "<a onclick=chakan("+row.id+")>查看</a>";
	               }
	        }, {
	        	"data": "planWeight"// 计划生产量(t)
	        },
	        {
	        	"data":"unit"
	        }, {
	            "data": "storageLocationName"// 储位
	        }, {
	            "data": "isComplete",// 是否生产完成
            	render:function(data,type,row,meta) {
	                   var html = '';
	                   if(data == 0){
	                	   html = '是';
	                   }else{
	                	   html = '否';
	                   }
	                   return html;
	               }
	        }, {
	            "data": "completePerson"// 确认人
	        }, {
	            "data": "completeDate"// 确认时间
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
            if(aData.isComplete == 0){                		
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

// 提交
function update(id, isApplication){
	if(isApplication == 0){
		swal({
			title: "提示",
			text: "已经确认生产完成!",
			type: "info",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}else if(isApplication == -1){
		swal({
			title: "提示",
			text: "只有查看的权限!",
			type: "info",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}else{
		
		swal({
			title: "确定要确认生产完成吗？",
			text:"",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: '#AEDEF4',
			confirmButtonText: '确定',
			cancelButtonText: '取消',
			cancelButtonFont: '微软雅黑',
		},
		function(){
			var param = {};
			// 确认人
			param.completePerson = userInfo.name;
			// 是否生产完成
			param.isComplete = 0;
			// 更新人
			param.reviser = userInfo.id;
			// id
			param.id= id;

			$.ajax({
				type: "post",
				url: '../../nextProductionPlan/updateProductionPlan.action',
				data: param,
				dataType: "json",
				success: function (data) {
					if(data.code == "success"){
						setTimeout(function(){swal({
							title: data.message,
							type: "success",
							cancelButtonText: '确定',
							cancelButtonFont: '微软雅黑',
						},
						function(){
							window.location.href="completeConfirm.html";
						}); },200);
					}else{
						swal({
							title: "错误提示",
							text: data.message,
							type: data.code,
							confirmButtonText: '确定',
							cancelButtonFont: '微软雅黑',
						});
					}
				}
			});
		});
	}
}

//查看生产工艺通知单
function chakan(id){
	location.href = '../quality/productionProcessNotice_edit.html?id='+id+'&flag=4';
}