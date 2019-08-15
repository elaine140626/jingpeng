// 当前登录人姓名
var reviser = '';
//用户觉得权限
var roleFlag = "";
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
		if(list.indexOf(",9,") != -1 || list.indexOf(",10,") != -1 || list.indexOf(",11,") != -1 || list.indexOf(",12,") != -1 ){
			// 生产部长    生产副部长   生产班长   检斤员(查看)
			roleFlag = false;
		}
		if(list.indexOf(",4,") != -1){
			// 质检员(操作)
			roleFlag = true;
		}
		if(list.indexOf(",5,") != -1){
			// 质检主任(操作)
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
	var isQualified = $("#isQualified").val();
	var table1 = $('#productionProcessDetection').dataTable();
	table1.fnDestroy();
	$("#productionProcessDetection").DataTable({		
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
           url: "../../ProductionProcessDetection/getPlanMeasure.action",
           dataSrc: "data",
           data: function (d) {       	   
         	   var param = {};
         	   param.materielName = materielName;
         	   param.materielModel = materielModel;
         	   param.isQualified = isQualified;
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
	            		html += "<a onclick=confirmQualified("+row.id+","+row.isQualified+","+row.isAdjust+")><img src=\"../../img/common/tijiao.png\" width=\"20\" height=\"20\" alt=\"确认质检\" title=\"确认质检\"></a>";
	            	}else{
	            		html += "<a onclick=confirmQualified("+row.id+",-1,"+row.isAdjust+")><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
	            	}
		  		    return html;
	            }
	        }, {
	            "data": "planNumber"
	        }, {
	            "data": "productionDate"// 生产日期
	        }, {
	            "data": "materielName"// 产品名称
	        }, {
	            "data": "materielModel"// 产品规格
	        }, {
	        	"data": "planWeight"// 计划生产量
	        }, {
	        	"data": "actualWeight"// 实际生产量
	        }, {
	        	"data": "unit"// 单位
	        }, {
        		"data": "isQualified",// 质检是否合格
            	render:function(data,type,row,meta) {
	                   if (data == '0'){
	                	   return '是';
	                   } else {
	                	   return '否';
	                   }
	               }
	        }, {
	        	"data": "isAdjust",// 是否调整
        		render:function(data,type,row,meta) {
	                   if (data == '0'){
	                	   return '是';
	                   } else {
	                	   return '否';
	                   }
	               }	
	        }, {
	            "data": "qualifiedPerson"// 质检员
	        }, {
	            "data": "qualifiedDate"// 确认时间
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
            // 不合格 需要调整 调整次数大于0
            if(aData.isQualified == 1 && aData.isAdjust == 0 && aData.adjustNumber>0){                		
            	//设置满足条件行的背景颜色(浅黄色)                		
            	$(nRow).css("background", "#FFCC99");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");                	
            }else if(aData.isQualified == 0){
            	// 合格
            	//设置满足条件行的背景颜色(灰色)                		
            	$(nRow).css("background", "#999999");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important"); 
            }else if(aData.isQualified == 1 && aData.isAdjust == 1){
            	// 不合格 不需要调整
            	//设置满足条件行的背景颜色(灰色)                		
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

//确认合格
function confirmQualified(id, isQualified,isAdjust){
	if(isQualified == 0){
		swal({
			title: "提示",
			text: "已经确认质检合格!",
			type: "info",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}else if(isQualified == 1 && isAdjust == 1){
		swal({
			title: "提示",
			text: "已经完成质检且不需要调整!",
			type: "info",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}else if(isQualified == -1){
		swal({
			title: "提示",
			text: "只有查看的权限!",
			type: "info",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}else{
		$.fancybox({
			'href': 'productionProcessDetection_edit.html?id='+id,
			'width': 800,
			'height': 450,
			'type': 'iframe',
			'hideOnOverlayClick': false,
			'showCloseButton': false,
			'onClosed': function() {
				window.location.href = 'productionProcessDetection.html';
			}
		}); 
	}	
}