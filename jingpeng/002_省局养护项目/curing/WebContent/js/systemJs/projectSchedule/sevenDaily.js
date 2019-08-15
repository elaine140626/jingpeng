// 当前登录用户id
var userId = '';
var cityId = '';
var countyId = '';
$(function(){
	// 年份下拉列表
	getYear();
	// 月份下拉列表
	getMonth();
	// 周下拉列表
	getWeek();
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
	// 初始化datatable
	var table1 = $('#table1').dataTable();
	table1.fnDestroy();
})

// datatable数据初始化
function getList(treeIds,levels){
	var myYear = document.getElementById("myYear").value;
	var myMonth = document.getElementById("myMonth").value;
	var myWeek = document.getElementById("myWeek").value;
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
            url: "../../SevenDaily/getSevenDailyList.action",
            dataSrc: "data",
            data: function (d) {
            	var param = {};
            	param.cityId = cityId
            	param.countyId = countyId
            	param.levels = levels;
            	param.treeIds = treeIds;
            	param.dailyYear = myYear;
            	param.dailyMonth = myMonth;
            	param.dailyDay = myWeek;
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
	            	html += "<a onclick='check(\""+row.id+"\")'style='text-decoration:none; font-size:14px;' href='javascript:;' title='查看'>查看&nbsp;</a>";
	            	html += "<a onclick='update(\""+row.id+"\")'style='text-decoration:none; font-size:14px;' href='javascript:;' title='编辑'>&nbsp;编辑&nbsp;</a>";
	            	html += "<a onclick='del(\""+row.id+"\")' style='text-decoration:none; font-size:14px;' href='javascript:;' title='删除'>&nbsp;删除</a>";
		  		    return html;
	            }
        	},
        	{"data": "projectNumber"},
        	{"data": "projectName"},
        	{"data": "contractSection"},
        	{"data": "contractingUnit"},
        	{"data": "mileage"},
        	{"data": "subheadNumber"},
        	{"data": "subheadName"},
        	{"data": "total"},
        	{"data": "unitPrice"},
        	{"data": "totalPrice"},
        	{"data": "thisComplete"},
        	{"data": "thisCapital"},
        	{"data": "cumulativeComplete"},
        	{"data": "cumulativeCapital"},
        	{"data": "cumulativeSchedule"},
        	{"data": "remarks"}    	       	
        ],
        "createdRow": function( row, data, dataIndex ) {
//        	console.log(data);
        },
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
		$('#cityCounty').html(treeList.cityName + treeList.county);
		// 项目名称
		$('#projectName').html(treeList.projectName);
		getList(treeIds,levels);
	} else {
		layer.alert('请选择一个项目来展示七日报！', {
			icon: 7,
			title: "提示"
		});
	}			
}	

//查询
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
		// 选中项目节点的场合
		if (levels == 3){
			var treeList = getVEntryTree(treeIds);
			// 所属市县
			$('#cityCounty').html(treeList.cityName + treeList.county);
			// 项目名称
			$('#projectName').html(treeList.projectName);
			getList(treeIds,levels);
		} else {
			layer.alert('请选择一个项目来展示七日报！', {
				icon: 7,
				title: "提示"
			});
		}	
	}	
}

// 添加
function add() {
	var myYear = document.getElementById("myYear").value;
	var myMonth = document.getElementById("myMonth").value;
	var myWeek = document.getElementById("myWeek").value;
	var zTreeObj = $.fn.zTree.getZTreeObj("EntryTree"); 
	//获取光标选中的(selected)
	var selectedNodes = zTreeObj.getSelectedNodes();
	if (selectedNodes != null && selectedNodes.length>0){
		if (selectedNodes[0].level != 3){
			layer.alert('请选择一个项目！', {
				icon: 7,
				title: "提示"
			});
		} else {
			var ProjectId = selectedNodes[0].id.substring(2,selectedNodes[0].id.length);
			var index = layer.open({
				type: 2,
				title: '添加七日报',
//				area: ['850px', '620px'],
				content: 'sevenDaily_add.html?ProjectId='+ProjectId+'&DailyYear='+myYear+'&DailyMonth='+myMonth+'&DailyDay='+myWeek,
			});
			layer.full(index);
		}
		
	} else {
		layer.alert('请选择一个项目！', {
			icon: 7,
			title: "提示"
		});
	}
}

// 编辑
function update(id){
	var zTreeObj = $.fn.zTree.getZTreeObj("EntryTree"); 
	var selectedNodes = zTreeObj.getSelectedNodes();
	var ProjectId = selectedNodes[0].id.substr(2,1);
	var index = layer.open({
		type: 2,
		title: '修改七日报',
//		area: ['850px', '620px'],
		content: 'sevenDaily_add.html?id='+id+'&ProjectId='+ProjectId+"&flag=1",
	});
	layer.full(index);
}

// 查看
function check(id){
	var zTreeObj = $.fn.zTree.getZTreeObj("EntryTree"); 
	var selectedNodes = zTreeObj.getSelectedNodes();
	var ProjectId = selectedNodes[0].id.substr(2,1);
	var index = layer.open({
		type: 2,
		title: '查看七日报',
//		area: ['850px', '620px'],
		content: 'sevenDaily_add.html?id='+id+'&ProjectId='+ProjectId+"&flag=0",
	});
	layer.full(index);
}

// 删除
function del(id){
	layer.confirm('确认要删除吗？', 
		function(index) {
			var params = {"id":id,"reviser":userId};	
			$.ajax({
				type: 'POST',
				url: '../../SevenDaily/deleteSevenDaily.action',
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
