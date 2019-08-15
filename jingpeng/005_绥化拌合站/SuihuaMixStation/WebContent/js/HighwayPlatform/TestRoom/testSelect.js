// 试验室名称
var uniqueidentifiers = '';
//用户的权限
var roletype = '';
$(function() {
	// 获取当前用户的信息
	var userInfo = getUserInfo();
	if(userInfo != ""){
		// 获取当前登录人的试验室权限
    	uniqueidentifiers = userInfo.userTestDetailed;
    	roletype = userInfo.roletype;
	}

	// 获取试验名称
	getTestNameList();
	var param = {};
	param.testNameId = '';
	param.startTime = '';
	param.endTime = '';
	param.uniqueIdentifier = uniqueidentifiers;
	load(param);

});

function load(param){
	// 试验基本信息
	$('#list').DataTable({
		"paging" : true,
		"lengthChange" : false,
		"pageLength" : 12,
		"searching" : false,
		"ordering" : false,
		"info" : true,
		"sInfo" : true,
		"autoWidth" : false,
		"language" : {
			"lengthMenu" : "每页 _MENU_ 条记录",
			"zeroRecords" : "没有找到记录",
			"info" : "第 _PAGE_ 页,共 _PAGES_ 页 (显示_END_ 条，共 _TOTAL_ 项)",
			"infoEmpty" : "无记录",
			"sSearch" : "在结果中查找：",
			"infoFiltered" : "(从 _MAX_ 条记录过滤)",
			"oPaginate" : {
				"sFirst" : "第一页",
				"sPrevious" : "上一页",
				"sNext" : "下一页",
				"sLast" : "最后一页"
			},
		},
		"ajax" : {
			type : "post",
			url : "../../TestSelect/getInfoList.action",
			dataSrc : "data",
			data : function(d) {
				return param;//自定义需要传递的参数。
			}
		},
		"deferRender" : true,
		"columns" : [ {
			"data" : "rownum"
		}, {
			"data" : "testroomname"
		}, {
			"data" : "testclassification_name"
		}, {
			"data" : "testname"
		}, {
			"data" : "samplecode"
		},{
			"data" : "samplecount"
		},{
			"data" : "testdate"
		},{
			"data" : "testoperator"
		},{
			"data" : "isqualifiedtest",
			 render: function(data, type, row, meta) {
		            if (data == '1') {
		                data = "合格";
		            }else if (data == '0'){
		            	data = '<span style="color: red;">不合格</span>';
		            }else{
		            	data = '<span style="color: red;">未判定</span>';
		            }
		            return data;
		        }
		},{
			"data" : "testtype"
		}, {
			"data" : "collectionstate",
			 render: function(data, type, row, meta) {
		            if (data == '1') {
		                data = "仪器采集";
		            }else{
		            	data = "人工采集";
		            }
		            return data;
		        }
		}, {
			"data" : "teststate",
			 render: function(data, type, row, meta) {
		            if (data == '2') {
		                data = "已完成";
		            }else{
		            	data = "未完成";
		            }
		            return data;
		        }
		}, 
		  {
			"data" : "id",
			render: function(data, type, row) {
				var html = '';
				if(row.testtable == 'Test04006T0'){
					html ="<span><a target='_blank' href='../item_pages/shiyan01.html?id="+row.id +"&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0500101T0'){
					// 水泥混凝土抗压强度试验（立方体）
					html ="<span><a target='_blank' href='../item_pages/shiyan02.html?id="+row.id +"&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0500102T0'){
					// 砂浆抗压强度试验
					html ="<span><a target='_blank' href='../item_pages/shiyan03.html?id="+row.id +"&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test07003T0'){
					// 无机结合料稳定材料无侧限抗压强度试验
					html ="<span><a target='_blank' href='../item_pages/shiyan04.html?id="+row.id +"&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test08001T0'){
					// 沥青三大指标试验
					html ="<span><a target='_blank' href='../item_pages/shiyan05.html?id="+row.id +"&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0900101T0'){
					// 沥青混合料马歇尔稳定度试验
					html ="<span><a target='_blank' href='../item_pages/shiyan06.html?id="+row.id +"&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test1000101T0'){
					// 钢筋抗拉强度、屈服强度、伸长率、冷弯试验
					html ="<span><a target='_blank' href='../item_pages/shiyan07.html?id="+row.id +"&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test1000201T0'){
					// 钢筋接头抗拉强度、冷弯试验
					html ="<span><a target='_blank' href='../item_pages/shiyan08.html?id="+row.id +"&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0200101T0'){
					if (row.teststate != "2"){
						html ="<span><a onclick='testCompletion("+row.id +")'>试验完成</a></span>";
					}
					// 粗集料筛分试验
					html = html + "  <span><a target='_blank' href='../item_pages/shiyan21.html?id="+row.id +"&issave=true&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0200102T0'){
					if (row.teststate != "2"){
						html ="<span><a onclick='testCompletion("+row.id +")'>试验完成</a></span>";
					}
					// 细集料筛分试验
					html = html +"  <span><a target='_blank' href='../item_pages/shiyan25.html?id="+row.id +"&issave=true&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test02015T0'){
					if (row.teststate != "2"){
						html ="<span><a onclick='testCompletion("+row.id +")'>试验完成</a></span>";
					}
					// 粗集料含泥量试验
					html = html +"  <span><a target='_blank' href='../item_pages/shiyan22.html?id="+row.id +"&issave=true&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test02006T0'){
					if (row.teststate != "2"){
						html ="<span><a onclick='testCompletion("+row.id +")'>试验完成</a></span>";
					}
					// 细集料含泥量试验
					html = html +"  <span><a target='_blank' href='../item_pages/shiyan26.html?id="+row.id +"&issave=true&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test02002T0'){
					if (row.teststate != "2"){
						html ="<span><a onclick='testCompletion("+row.id +")'>试验完成</a></span>";
					}
					// 粗集料针、片状颗粒含量试验
					html =html + "  <span><a target='_blank' href='../item_pages/shiyan23.html?id="+row.id +"&issave=true&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0200301T0'){
					if (row.teststate != "2"){
						html ="<span><a onclick='testCompletion("+row.id +")'>试验完成</a></span>";
					}
					// 粗集料压碎值试验
					html = html + "  <span><a target='_blank' href='../item_pages/shiyan24.html?id="+row.id +"&issave=true&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test04003T0'){
					if (row.teststate != "2"){
						html ="<span><a onclick='testCompletion("+row.id +")'>试验完成</a></span>";
					}
					// 水泥凝结时间
					html = html + "  <span><a target='_blank' href='../item_pages/shiyan27.html?id="+row.id +"&issave=true&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0201T0'){
					if (row.teststate != "2"){
						html ="<span><a onclick='testCompletion("+row.id +")'>试验完成</a></span>";
					}
					// 粗集料试验
					html = html + "  <span><a target='_blank' href='../item_pages/shiyan28.html?id="+row.id +"&issave=true&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0202T0'){
					if (row.teststate != "2"){
						html ="<span><a onclick='testCompletion("+row.id +")'>试验完成</a></span>";
					}
					// 细集料试验
					html = html + "  <span><a target='_blank' href='../item_pages/shiyan29.html?id="+row.id +"&issave=true&istestcollection="+row.istestcollection+"&istestblind="+row.istestblind+"&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else{
					html ="<span><a href='#'>查看</a></span>";
				}
                return html;
            }
		}, 
		],
		"createdRow" : function(row, data, dataIndex) {
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		} ]
	});
	$('.lq_biao .row:first-child').css('display','none');
	$('#list_info').css('text-align','center');
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

//试验完成
function testCompletion(id){
	swal({
		title: "确定操作吗？",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
		
	},function(){
    $.ajax({
        type: "post",
        url: "../../TestSelect/changeTestState.action",
        data : {"id":id},
        dataType: "json",
        success: function (data) {
            if(data.code=="success"){
            	setTimeout(function(){swal({
					title: data.message,
					type: "success",
					cancelButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					//location.reload();
					loadHTML("test_query.html", contain);
				}); },200);
				
			}else{
				swal("操作失败",data.message, "error");
			}
        }
    });
  });	
}

//查询
$('#select').on('click', function(event) {
	//获取列表 刷新
	//  $('#list').DataTable().clear();
	//获取列表 刷新
	var table = $('#list').dataTable();
	table.fnDestroy();

	// 试验名称id
	var testNameId = $("#testPageInfosList option:selected").val();
	// 开始时间
	var startTime = $("#startTime").val();
	// 结束时间
	var endTime = $("#endTime").val();
	// 试验室名称
	// 试验室名称
	var authority = document.getElementsByName("authority");
	var children = document.getElementsByName("children");
	var uniqueIdentifier = "";

	if(startTime > endTime){
		swal({
			title: "开始时间大于结束时间!",
			text: "",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		// 下框框清空
		$("#testPageInfosList option:first").prop("selected", 'selected');
		// 开始时间
		$("#startTime").val("");
		// 结束时间
		$("#endTime").val("");
		var param = {};
		param.testNameId = '';
		param.startTime = '';
		param.endTime = '';
		param.uniqueIdentifier = uniqueidentifiers;
		load(param);
	}else if(endTime != '' && startTime == ''){
		swal({
			title: "请选择开始时间!",
			text: "",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		// 下框框清空
		$("#testPageInfosList option:first").prop("selected", 'selected');
		// 开始时间
		$("#startTime").val("");
		// 结束时间
		$("#endTime").val("");
		var param = {};
		param.testNameId = '';
		param.startTime = '';
		param.endTime = '';
		param.uniqueIdentifier = uniqueidentifiers;
		load(param);
	}else{		
		// 判断试验室是否选中
		var flag = false;
		for (var j = 0; j < authority.length; j++) {
			// 获取选中项
			if (authority[j].checked) {
				flag = true;
				if(j < authority.length - 1){			
					uniqueIdentifier += authority[j].value + ",";
				}else{
					uniqueIdentifier += authority[j].value;
				}
			}
		}
		
		// 如果父类有勾选
		if(uniqueIdentifier != ""){
			uniqueIdentifier += ",";
		}
		
		for (var j = 0; j < children.length; j++) {
			// 获取选中项
			if (children[j].checked) {
				flag = true;
				if(j < children.length - 1){			
					uniqueIdentifier += children[j].value + ",";
				}else{
					uniqueIdentifier += children[j].value;
				}
			}
		}
		// 提示信息
		if(!flag){
			swal({
				title: "请选择一个试验室",
				text: "",
				type: "error",
				confirmButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			});
			// 下框框清空
			$("#testPageInfosList option:first").prop("selected", 'selected');
			// 开始时间
			$("#startTime").val("");
			// 结束时间
			$("#endTime").val("");
			var param = {};
			param.testNameId = '';
			param.startTime = '';
			param.endTime = '';
			param.uniqueIdentifier = uniqueidentifiers;
			load(param);
		}else{
			var param = {};
			param.testNameId = testNameId;
			param.startTime = startTime;
			param.endTime = endTime;
			param.uniqueIdentifier = uniqueIdentifier;
			load(param);
		}
	}		
});
