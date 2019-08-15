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
	// 计划批次
	var planBatchInfo = getDataDictionaryMultiple('jhpc');
	$("#planBatch").empty();
	$("#planBatch").html(planBatchInfo);
	// 建设性质
	var buildNatureInfo = getDataDictionaryMultiple('jsxz');
	$("#buildNature").empty();
	$("#buildNature").html(buildNatureInfo);
	// 项目来源
	var projectSourceInfo = getDataDictionaryMultiple('xmly');
	$("#projectSource").empty();
	$("#projectSource").html(projectSourceInfo);
	// 项目分类
	var projectTypeInfo = getDataDictionaryMultiple('xmfl');
	$("#projectType").empty();
	$("#projectType").html(projectTypeInfo);
	// 行政等级
	var administrationGradeInfo = getDataDictionaryMultiple('xzdj');
	$("#administrationGrade").empty();
	$("#administrationGrade").html(administrationGradeInfo);
	// 技术等级
	var technicalGradeInfo = getDataDictionaryMultiple('jsdj');
	$("#technicalGrade").empty();
	$("#technicalGrade").html(technicalGradeInfo);

	// datatable数据初始化
	getList(0,0);
})

// datatable数据初始化
function getList(treeIds,levels){
	var param = formToJson($("#form1"));
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
            	param.cityId = cityId
            	param.countyId = countyId
            	param.levels = levels;
            	param.treeIds = treeIds;
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
		  		    html += "<a onclick='update("+row.id+")'style='text-decoration:none' href='javascript:;' title='编辑'><i class='Hui-iconfont'>&#xe6df;</i></a>";
		  		    html += "<a onclick='del("+row.id+")' style='text-decoration:none' href='javascript:;' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
		  		    return html;
	            }
        	},
        	{"data": "projectNumber"},
        	{"data": "cityName"},
        	{"data": "lineName"},     	
        	{"data": "engineeringName"},
        	{"data": "agoName"},
        	{"data": "planBatchName"},
        	{"data": "location"},
        	{"data": "pileNumber"},
        	{"data": "buildNatureName"},
        	{"data": "technicalGradeName"},
        	{"data": "width"},
        	{"data": "administrationGradeName"},
        	{"data": "planTime"},
        	{"data": "projectSourceName"},
        	{
        		"data": "projectType",
        		render:function(data,type,row,meta) {
        			var html = '';
        			// 项目分类List
        			var list = getDataDictionaryList('xmfl');
        			if (data != null && data != ''){
        				var testItemList = data.split(",");
        				// 项目分类List循环
        				for(var i = 0;i<list.length;i++){
        					// 当前项目分类字段循环
        					for(var j = 0;j<testItemList.length;j++){
        						if (list[i].Code == testItemList[j]){
        							html += list[i].Content + ',';
        						}
        					}
        				}
        			}
        			// 截掉最后一位'，'
        			if (html != ''){
        				html = html.substring(0, html.length - 1);
        			}
		  		    return html;
	            }
        	},
        	{
        		"data": "qualityTarget",
        		render:function(data,type,row,meta) {
	            	var html = '';
		  		    if (data == 0){
		  		    	html = '合格 ';
		  		    } else {
		  		    	html = '优良 ';
		  		    }
	         
		  		    return html;
	            }
        	}, 
        	{
        		"data": "approvalsType",
        		render:function(data,type,row,meta) {
        			var html = '';
        			// 批文种类List
        			var list = getDataDictionaryList('pwlx');
        			if (data != null && data != ''){
        				var testItemList = data.split(",");
        				// 批文种类List循环
        				for(var i = 0;i<list.length;i++){
        					// 当前项目分类字段循环
        					for(var j = 0;j<testItemList.length;j++){
        						if (list[i].Code == testItemList[j]){
        							html += list[i].Content + ',';
        						}
        					}
        				}
        			}
        			// 截掉最后一位'，'
        			if (html != ''){
        				html = html.substring(0, html.length - 1);
        			}
		  		    return html;
	            }
        		
        	}, 
        	{"data": "uploadPicture",
        		render:function(data,type,row,meta) {
	            	var html = '';
		  		    if (data != '' && data!= null && data!= undefined){
		  		    	html = "<input class='btn btn-success search-btn' type=\"button\" value=\"预览文件\" onclick=\"openFileIIs("+row.id+")\">";
		  		    } else {
		  		    	html = '无文件';
		  		    }
	         
		  		    return html;
	            }}, 
        	{"data": "remarks"}
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

//添加
function add() {
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
				title: '添加工程信息',
				content: 'projectInfoSummary_add.html?ProjectId='+ProjectId,
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

//编辑
function update(id){
	var index = layer.open({
		type: 2,
		title: '修改工程信息',
		content: 'projectInfoSummary_add.html?id='+id,
	});
	layer.full(index);
}

//删除
function del(id){
	layer.confirm('确认要删除吗？', 
		function(index) {
			var params = {"id":id,"reviser":userId};	
			$.ajax({
				type: 'POST',
				url: '../../ProjectInfoSummary/deleteProjectInfoSummary.action',
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

function openFileIIs(fileId){     
	var index = layer.open({
		type: 2,
		title: '预览图片',
		content: 'projectInfoSummaryImage.html?id='+fileId,
	});
	layer.full(index);
/*	$.ajax({
		type : "post",
		url : "../../ProjectInfoSummary/openFile.action",
		data : {"id" : fileId},
		dataType : "json",
		success : function(data) {
			if(data.code == 'error'){
				layer.msg(data.message, {
					icon: 2,
					time: 1000
				});
			}
		}
	});*/
}