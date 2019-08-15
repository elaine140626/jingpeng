// 当前登录用户id
var userId = '';
var cityId = '';
var countyId = '';
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		cityId = userInfo.cityId;
		countyId = userInfo.countyId
		if(cityId == 0){
			cityId = '';
		}
		if(countyId == 0){
			countyId = '';
		}
	}
	showZtreeS(cityId,countyId);
	
	// 初始化数据
	var param = {};
	// datatable数据初始化
	getList(0,0);
})

// dataTable初始化数据
function getList(treeIds,levels){
    var params = formToJson($("#form1"));
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
            url: "../../ProjectInfoSummary/getProjectInfoList.action",
            dataSrc: "data",
            data: function (d) {
            	params.cityId = cityId
            	params.countyId = countyId
            	params.levels = levels;
            	params.treeIds = treeIds;
                return params;//自定义需要传递的参数。
            }
        },
        "deferRender": true,
        "columns": [
        	{"data": "id"},
        	{
        		"data": "id",
        		render:function(data,type,row,meta) {
	            	var html = '';
	            	html += "<a onclick='check(\""+row.id+"\")'style='text-decoration:none; font-size:14px;' href='javascript:;' title='查看'>查看&nbsp;</a>";
		  		    html += "<a onclick='update(\""+row.id+"\")'style='text-decoration:none; font-size:14px;' href='javascript:;' title='编辑'>&nbsp;编辑</a>";
		  		    // html += "<a onclick='delProjectPlanSummary("+row.id+")' style='text-decoration:none' href='javascript:;' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
		  		    return html;
	            }
        	},
        	{"data": "projectNumber"},
        	{"data": "projectName"},
        	{"data": "cityName"},
        	{"data": "lineName"},     	
        	{"data": "engineeringName"},
        	{"data": "agoName"},
        	{"data": "tenderingNatureName"},     	
        	{"data": "tenderingCompany"},
        	{"data": "tenderingAgent"},
        	{
        		"data": "tenderingRelease",
        		render:function(data,type,row,meta) {
        			var html = '';
        			if(row.tenderingRelease != null && row.tenderingRelease != ''){
        				if(row.tenderingReleaseAddress != null && row.tenderingReleaseAddress != ''){
        					html = '<a href='+ row.tenderingReleaseAddress +' target="_blank">'+row.tenderingRelease+'</a>';
        				}else{
        					html = row.tenderingRelease ;
        				}
        			}
        			
		  		    return html;
	            }},
        	{
	            "data": "winningBidCompany",
        		render:function(data,type,row,meta) {
        			var html = '';
        			if(row.winningBidCompany != null && row.winningBidCompany != ''){
        				if(row.winningBidCompanyAddress != null && row.winningBidCompanyAddress != ''){
        					html = '<a href='+ row.winningBidCompanyAddress +' target="_blank">'+row.winningBidCompany+'</a>';
        				}else{
        					html = row.winningBidCompany ;
        				}
        			}
		  		    return html;
	            }},
        	{
        		"data": "contractSign",
        		render:function(data,type,row,meta) {
	            	var html = '';
		  		    if (data == 0){
		  		    	html = '已签 ';
		  		    } else{
		  		    	html = '未签 ';
		  		    }
	         
		  		    return html;
	            }
        	}, 
        	{
        		"data": "uploadPicture2",
        		render:function(data,type,row,meta) {
	            	var html = '';
		  		    if (data != '' && data!= null && data!= undefined){
		  		    	html = "<input  class='btn btn-success search-btn' type=\"button\" value=\"预览文件\" onclick=\"openFileIIs("+row.id+")\">";
		  		    } else {
		  		    	html = '无文件';
		  		    }
	         
		  		    return html;
	            }
        	},
        	{"data": "remarks2"}
        	],
        "createdRow": function( row, data, dataIndex ) {
        	// console.log(data);
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
}

//树形节点点击事件
function findClick(event, treeId, treeNode, isCancel) {
	treeIds = treeNode.id;
	// 0:所有 1:市  2:县  3:项目
	levels = treeNode.level;
	if (levels != 0){
		treeIds = treeIds.substring(2,treeIds.length);
	}	
	// datatable
	getList(treeIds,levels);
}

// 查询
function search(){	
	var treeIds = 0;
	var levels = 0;
	var zTreeObj = $.fn.zTree.getZTreeObj("EntryTree"); 
	//获取光标选中的(selected)
	var selectedNodes = zTreeObj.getSelectedNodes();
	if (selectedNodes[0] != undefined){
		levels = selectedNodes[0].level;
		if (levels !=0){
			treeIds = selectedNodes[0].id.substring(2,selectedNodes[0].id.length);
		}		
	}	
	// datatable
	getList(treeIds,levels);
}

// 修改
function update(id){
	var index = layer.open({
		type: 2,
		title: '修改工程招标信息',
		content: 'ProjectTenderingSummary_add.html?id='+id+"&flag=1",
	});
	layer.full(index);
}

// 查看
function check(id){
	var index = layer.open({
		type: 2,
		title: '查看工程招标信息',
		content: 'ProjectTenderingSummary_add.html?id='+id+"&flag=0",
	});
	layer.full(index);
}

function openFileIIs(fileId){        
	var index = layer.open({
		type: 2,
		title: '预览图片',
		content: 'ProjectTenderingSummaryImage.html?id='+fileId,
	});
	layer.full(index);
}