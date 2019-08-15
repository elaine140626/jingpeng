// 当前登录人姓名
var reviser = '';
// 用户角色
var roleCode = "";
// 用户觉得权限
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
			roleCode = 4;
			roleFlag = true;
		}
		if(list.indexOf(",5,") != -1){
			// 质检主任(操作)
			roleCode = 5;
			roleFlag = true;
		}
		if(list.indexOf(",0,") != -1){
			// 管理员(操作)
			roleCode = 0;
			roleFlag = true;
		}
		if(list.indexOf(",7,") != -1){
			// 总经理(操作)
			roleCode = 7;
			roleFlag = true;
		}
		
	}
	
	getList();
})

// datatable
function getList(){
	// 产品名称
	var materielName = $("#materielName").val();
	// 产品规格
	var materielModel = $("#materielModel").val();
	// 状态
	var isExamine = $("#isExamine").val();
	var table1 = $('#productionProcessNotice').dataTable();
	table1.fnDestroy();
	
	$("#productionProcessNotice").DataTable({		
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
           url: "../../ProductionProcessNotice/getPlanMeasure.action",
           dataSrc: "data",
           data: function (d) {       	   
         	   var param = {};
         	   param.materielName = materielName;
         	   param.materielModel = materielModel;
         	   param.isExamine = isExamine;
         		// 用户的角色
         		if(roleCode == 4){
         			// 化验员或主任
         			param.analystOrDirector = 0;
         		}else if(roleCode == 5){
         			param.analystOrDirector = 1;
         		}
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
			  		    if (row.isExamine != 0){
			  		    	html += "<a onclick=update("+row.id+","+row.isProduction+","+row.isExamine+","+row.isInspector+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>";
			  		        // 用户的角色
			  				if(roleCode == 4 && row.isInspector != 0){
			  					html += "<a onclick=examine("+row.id+")><img src=\"../../img/common/tijiao.png\" width=\"20\" height=\"20\" alt=\"审核\" title=\"审核\"></a>";
			  				}else if(roleCode == 5 || roleCode == 0 || roleCode == 7){
			  					html += "<a onclick=examine("+row.id+")><img src=\"../../img/common/tijiao.png\" width=\"20\" height=\"20\" alt=\"审核\" title=\"审核\"></a>";	
			  				}
			  		    } else {
			  		    	html += "<a onclick=update("+row.id+",0,0,0)><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"</a>";
			  		    }	
	            	}else{
	            		html += "<a onclick=update("+row.id+",0,0,0)><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"</a>";
	            	}
		  		    return html;
	            }
	        }, {
	            "data": "batch"
	        }, {
	            "data": "batchingList"
	        }, {
	            "data": "technicsNumber"
	        }, {
	            "data": "customerCode"
	        }, {
	            "data": "customerName"
	        }, {
	            "data": "finishTime"
	        }, {
	            "data": "materielName"// 产品名称
	        }, {
	            "data": "materielModel"// 产品规格
	        }, {
	        	"data": "planWeight"// 计划生产量
	        }, {
	        	"data": "unit"// 单位
	        }, {
	            "data": "isExamine",// 是否已下通知单
            	render:function(data,type,row,meta) {
	                   if (data == '0'){
	                	   return '是';
	                   } else {
	                	   return '否';
	                   }
	               }
	        }, {
	            "data": "examinePerson"// 下发人
	        }, {
	            "data": "examineDate"// 下发时间
	        }, {
	            "data": "isInspector",// 质检员是否审核
            	render:function(data,type,row,meta) {
	                   if (data == '0'){
	                	   return '是';
	                   } else {
	                	   return '否';
	                   }
	               }
	        }, {
	            "data": "inspectorPerson"// 质检员审核人
	        }, {
	            "data": "inspectorDate"// 质检员审核时间
	        }],
       "createdRow": function(row, data, dataIndex) {	
			// console.log(data);
       },
	   "order": [[ 1, 'asc' ]],
       "fnRowCallback" : function(nRow, aData, iDisplayIndex){
       		var html = '';
       		html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一            
            // 调整行变色
            if(aData.isExamine == 1 && aData.adjustNumber > 0){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#FFCC99");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");                	
            }else if(aData.isExamine == 0){
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

//修改
function update(id,isProduction,isExamine,isInspector){
	location.href = 'productionProcessNotice_edit.html?id='+id+'&flag=1&isProduction='+isProduction+"&isExamine="+isExamine+"&isInspector="+isInspector;
}

//审核
function examine(id){
	var flag = false;
	$.ajax({
		type : "POST",
		url : "../../ProductionProcessNotice/getProductionProcessNoticeById.action",
		async:false,
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			var list = data.list;
			var listDetail = data.listDetail;
			if (list[0].batchingList==null || list[0].technicsNumber==null){
				flag = true;
			}
			// 生产管理明细
			if (listDetail.length<1){
				flag = true;			
			} 		
		}
	});	
	if(flag){
		swal("操作失败", "生产工艺通知单未填写，不能审核", "info");
		return;
	}	
	swal({
		title: "确定要审核吗？",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
	},function(){
		var param = {};
		// 用户的角色
		if(roleCode == 4){
			// 化验员或主任
			param = {"id":id,"reviser":reviser,"isInspector":0,"inspectorPerson":reviser};
		}else if(roleCode == 5 || roleCode == 0 || roleCode == 7){
			param = {"id":id,"reviser":reviser,"isExamine":0,"examinePerson":reviser}
		}
		
		$.ajax({
	        type: "post",
	        url: '../../ProductionProcessNotice/examineProductionProcessNotice.action',
	        async:false,
	        data:param,
	        dataType: "json",
	        success: function (data) {
	        	setTimeout(function(){swal({
					title: data.message,
					type: "success",
					cancelButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					window.location.href="productionProcessNotice.html";
				}); },200);
	        }
		})
	})	  
}