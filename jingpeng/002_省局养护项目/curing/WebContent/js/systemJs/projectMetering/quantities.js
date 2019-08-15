// 当前登录用户id
var userId = ''; 
//项目id
var ProjectId = '';
var levels = '';
var treeIds = '';
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
	
	// 年份下拉列表
	getYear();
	// 月份下拉列表
	getMonth();
	
	// datatable数据初始化
	getList(0,0);
	
	// 合计值赋值
	getSum();
})

// datatable数据初始化
function getList(treeIds,levels){
	var param = {};
    param = formToJson($("#form1"));
    param.levels = levels;
	param.treeIds = treeIds;
	param.ProjectId = ProjectId;
	
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
            url: "../../CompletionAmount/getCompletionAmountList.action",
            dataSrc: "data",
            data: function (d) {
                return param;//自定义需要传递的参数。
            }
        },
        "deferRender": true,
        "columns": [
        	{"data": "subheadNumber"},
        	{"data": "subheadNumber"},
        	{"data": "subheadName"},
        	{"data": "projectName"},
        	{"data": "total"},
        	{"data": "monthThisComplete"},
        	{"data": "yearThisComplete"},
        	{"data": "startThisComplete"}, 
        	{"data": "contractAmount"},     	
        	{"data": "monthCompletion"},
        	{"data": "yearCompletion"},
        	{"data": "startCompletion"}
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

//树形节点点击事件
function findClick(event, treeId, treeNode, isCancel) {
	treeIds = treeNode.id;
	// 1:市  2:县  3:项目
	levels = treeNode.level;
	if (levels != 0){
		treeIds = treeIds.substring(2,treeIds.length);
	}
	// 选中项目节点的场合
	if (levels == 3){
		var treeList = getVEntryTree(treeIds);
		// 所属市县
		$('#cityCcounty').html(treeList.cityName + treeList.county);
		// 项目名称
		$('#projectName').html(treeList.projectName);
	} else {
		layer.alert('请选择一个项目来展示投资计划完成情况表！', {
			icon: 7,
			title: "提示"
			});
	}
}	

//合计值赋值
function getSum(treeIds,levels){	
	var param = {};
    param = formToJson($("#form1"));
    param.levels = levels;
	param.treeIds = treeIds;
	param.ProjectId = ProjectId;
	
	$.ajax({
		type: 'POST',
		url: '../../CompletionAmount/getCompletionAmountSum.action',
		data: param,
		dataType: 'json',
		success: function(data){
			if (data[0] != null){
				$("#TotalSum").html(data[0].totalSum);
				$("#MonthThisCompleteSum").html(data[0].monthThisCompleteSum);
				$("#YearThisCompleteSum").html(data[0].yearThisCompleteSum);
				$("#StartThisCompleteSum").html(data[0].startThisCompleteSum);
				$("#ContractAmountSum").html(data[0].contractAmountSum);
				$("#MonthCompletionSum").html(data[0].monthCompletionSum);
				$("#YearCompletionSum").html(data[0].yearCompletionSum);
				$("#StartCompletionSum").html(data[0].startCompletionSum);
			} else {
				$("#TotalSum").html("");
				$("#MonthThisCompleteSum").html("");
				$("#YearThisCompleteSum").html("");
				$("#StartThisCompleteSum").html("");
				$("#ContractAmountSum").html("");
				$("#MonthCompletionSum").html("");
				$("#YearCompletionSum").html("");
				$("#StartCompletionSum").html("");
			}	
		}
	});			
}

//查询方法
function search(){
	var zTreeObj = $.fn.zTree.getZTreeObj("EntryTree"); 
	var selectedNodes = zTreeObj.getSelectedNodes();
	if(selectedNodes != null && selectedNodes.length > 0){
		ProjectId = selectedNodes[0].id.substring(2,selectedNodes[0].id.length);
	}
	
	// 选中项目节点的场合
	if (levels == 3){
		// datatable数据初始化
		getList(treeIds,levels);
		getSum(treeIds,levels);
	} else {
		layer.alert('请选择一个项目来展示投资计划完成情况表！', {
			icon: 7,
			title: "提示"
			});
	}
}
