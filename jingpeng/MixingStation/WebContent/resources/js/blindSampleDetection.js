// 用户的权限
var roletype = '';
//验证码
var VerificationCode = ""; 
$(function() {
	VerificationCode = getVerificationCode();
	$.ajax({
        type: "post",
        url: "../testUserInfo/getTestUserName.html",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	//获取当前登录人做显示
        	$(".userid").html(data.userName);
        	// 用户的权限
        	roletype = data.roletype;
        	
        	// 试验室权限 显示
        	if(roletype == 1){
        		$("#constructionunit").hide();
        		$("#engineeringname").hide();
        		$("#purpose").hide();
        	}else{
        		$("#constructionunit").show();
        		$("#engineeringname").show();
        		$("#purpose").show();
        	}
        }
    });
	
	// 获取试验名称
	$.ajax({
		type : "POST",
		url : "../TestCollection/getTestNameList",
		async : false,
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'>请选择</option>";
			for (var i = 0; i < data.length; i++) {
				html += "<option value='" + data[i].id + "'>"
						+ data[i].testname + "</option>"
			}
			$("#testPageInfosList").empty();
			$("#testPageInfosList").html(html);
		}
	});

	// 试验室隐藏
	$('#toggle').on('click', function(event) {
		event.stopPropagation();
		$(this).siblings('#content').toggle();
		var tag = $(this).siblings('#content');
		var flag = true;
		$(document).bind("click", function(e) {
			var target = $(e.target);
			if (target.closest(tag).length == 0 && flag == true) {
				$(tag).hide();
				flag = false;
			}
		});
	});

	load();

});

function load(){
	// 试验基本信息
	var list = $('#list').DataTable({
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
			url : "../BlindSampleDetection/getInfoList",
			dataSrc : "data",
			data : function(d) {
				var param = {};
				param.testNameId = '';
				param.teststate = '';
				param.startTime = '';
				param.endTime = '';
				param.uniqueIdentifier = '';
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
		},{
			"data" : "teststate",
			render: function(data, type, row, meta) {
	            if (row.testroomname == null) {
	                data = "抽样完成";
	            }else if(row.testroomname != null && data != '2'){
	            	data = "收样完成";
	            }else if(data == '2'){
	            	data = "试验完成";
	            }
	            return data;
	        }
		}, {
			"data" : "samplecode"
		},{
			"data" : "constructionunit"
		},{
			"data" : "engineeringname"
		},
		{
			"data" : "purpose"
		},
		{
			"data" : "testdate",
		},
		{
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
		},
		 {
			"data" : "collectionstate",
			 render: function(data, type, row, meta) {
		            if (data == '1') {
		                data = "仪器采集";
		            }else{
		            	data = "人工采集";
		            }
		            return data;
		        }
		},
		{
			"data" : "samplingPerson"
		},
		{
			"data" : "samplingdate"
		},
		  {
			"data" : "id",
			render: function(data, type, row) {
				var html = '';
				if(row.id != null && row.id != ''){
					if(row.testtable == 'Test04006T0'){
						html ="<span><a href='../ShiYan/shiyan01.html?id="+row.id +"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test0500101T0'){
						// 水泥混凝土抗压强度试验（立方体）
						html ="<span><a href='../ShiYan/shiyan02.html?id="+row.id +"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test0500102T0'){
						// 砂浆抗压强度试验
						html ="<span><a href='../testInfo/shiyan03.html?serialNumber="+row.serialNumber+"&TestRoomName="+row.testroomname+"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test07003T0'){
						// 无机结合料稳定材料无侧限抗压强度试验
						html ="<span><a href='../ShiYan/shiyan04.html?id="+row.id +"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test08001T0'){
						// 沥青三大指标试验
						html ="<span><a href='../ShiYan/shiyan05.html?id="+row.id +"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test0900101T0'){
						// 沥青混合料马歇尔稳定度试验
						html ="<span><a href='../ShiYan/shiyan06.html?id="+row.id +"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test1000101T0'){
						// 钢筋抗拉强度、屈服强度、伸长率、冷弯试验
						html ="<span><a href='../ShiYan/shiyan07.html?id="+row.id +"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test1000201T0'){
						// 钢筋接头抗拉强度、冷弯试验
						html ="<span><a href='../ShiYan/shiyan08.html?id="+row.id +"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test0200101T0'){
						// 粗集料筛分试验
						html ="<span><a href='../ShiYan02/shiyan21.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test0200102T0'){
						// 细集料筛分试验
						html ="<span><a href='../ShiYan02/shiyan25.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test02015T0'){
						// 粗集料含泥量试验
						html ="<span><a href='../ShiYan02/shiyan22.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test02006T0'){
						// 细集料含泥量试验
						html ="<span><a href='../ShiYan02/shiyan26.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test02002T0'){
						// 粗集料针、片状颗粒含量试验
						html ="<span><a href='../ShiYan02/shiyan23.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test0200301T0'){
						// 粗集料压碎值试验
						html ="<span><a href='../ShiYan02/shiyan24.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test04003T0'){
						// 水泥凝结时间
						html ="<span><a href='../ShiYan02/shiyan27.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test0201T0'){	
						// 粗集料试验
						html ="<span><a href='../ShiYan02/shiyan28.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else if(row.testtable == 'Test0202T0'){
						// 细集料试验
						html ="<span><a href='../ShiYan02/shiyan29.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
					}else{
						html ="<span><a href='#'>查看</a></span>";
					}
									
				}else{
					html ="<span><a onclick='show();'>查看</a></span>";
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
	// 根据用户的权限显示相应列
	if(roletype == 1){
		list.column(6).visible(false);
		list.column(7).visible(false);
		list.column(8).visible(false);
	}else{
		list.column(6).visible(true);
		list.column(7).visible(true);
		list.column(8).visible(true);
	}
	$('.lq_biao .row:first-child').css('display','none');
	$('#list_info').css('text-align','center');
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出

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
	// 试验状态
	var teststate = $("#testStateList option:selected").val();
	// 开始时间
	var startTime = $("#startTime").val();
	// 结束时间
	var endTime = $("#endTime").val();
	// 试验室名称
	var authority = document.getElementsByName("authority");
	var children = document.getElementsByName("children");
	var uniqueIdentifier = "";
	for (var j = 0; j < authority.length; j++) {
		// 获取选中项
		if (authority[j].checked) {
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
			if(j < children.length - 1){			
				uniqueIdentifier += children[j].value + ",";
			}else{
				uniqueIdentifier += children[j].value;
			}
		}
	}

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
		load();
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
		load();
	}else{		
		// 试验基本信息
		var list = $('#list').DataTable({
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
				url : "../BlindSampleDetection/getInfoList",
				dataSrc : "data",
				data : function(d) {
					var param = {};
					param.testNameId = testNameId;
					param.teststate = teststate;
					param.startTime = startTime;
					param.endTime = endTime;
					param.uniqueIdentifier = uniqueIdentifier;
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
			},{
				"data" : "teststate",
				render: function(data, type, row, meta) {
		            if (row.testroomname == null) {
		                data = "抽样完成";
		            }else if(row.testroomname != null && data != '2'){
		            	data = "收样完成";
		            }else if(data == '2'){
		            	data = "试验完成";
		            }
		            return data;
		        }
			}, {
				"data" : "samplecode"
			},{
				"data" : "constructionunit"
			},{
				"data" : "engineeringname"
			},
			{
				"data" : "purpose"
			},
			{
				"data" : "testdate",
			},
			{
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
			},
			 {
				"data" : "collectionstate",
				 render: function(data, type, row, meta) {
			            if (data == '1') {
			                data = "仪器采集";
			            }else{
			            	data = "人工采集";
			            }
			            return data;
			        }
			},
			{
				"data" : "samplingPerson"
			},
			{
				"data" : "samplingdate"
			},
			  {
				"data" : "id",
				render: function(data, type, row) {
					var html = '';
					if(row.id != null && row.id != ''){
						if(row.testtable == 'Test04006T0'){
							html ="<span><a href='../ShiYan/shiyan01.html?id="+row.id +"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test0500101T0'){
							// 水泥混凝土抗压强度试验（立方体）
							html ="<span><a href='../ShiYan/shiyan02.html?id="+row.id +"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test0500102T0'){
							// 砂浆抗压强度试验
							html ="<span><a href='../testInfo/shiyan03.html?serialNumber="+row.serialNumber+"&TestRoomName="+row.testroomname+"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test07003T0'){
							// 无机结合料稳定材料无侧限抗压强度试验
							html ="<span><a href='../ShiYan/shiyan04.html?id="+row.id +"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test08001T0'){
							// 沥青三大指标试验
							html ="<span><a href='../ShiYan/shiyan05.html?id="+row.id +"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test0900101T0'){
							// 沥青混合料马歇尔稳定度试验
							html ="<span><a href='../ShiYan/shiyan06.html?id="+row.id +"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test1000101T0'){
							// 钢筋抗拉强度、屈服强度、伸长率、冷弯试验
							html ="<span><a href='../ShiYan/shiyan07.html?id="+row.id +"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test1000201T0'){
							// 钢筋接头抗拉强度、冷弯试验
							html ="<span><a href='../ShiYan/shiyan08.html?id="+row.id +"&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test0200101T0'){
							// 粗集料筛分试验
							html ="<span><a href='../ShiYan02/shiyan21.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test0200102T0'){
							// 细集料筛分试验
							html ="<span><a href='../ShiYan02/shiyan25.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test02015T0'){
							// 粗集料含泥量试验
							html ="<span><a href='../ShiYan02/shiyan22.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test02006T0'){
							// 细集料含泥量试验
							html ="<span><a href='../ShiYan02/shiyan26.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test02002T0'){
							// 粗集料针、片状颗粒含量试验
							html ="<span><a href='../ShiYan02/shiyan23.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test0200301T0'){
							// 粗集料压碎值试验
							html ="<span><a href='../ShiYan02/shiyan24.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test04003T0'){
							// 水泥凝结时间
							html ="<span><a href='../ShiYan02/shiyan27.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test0201T0'){	
							// 粗集料试验
							html ="<span><a href='../ShiYan02/shiyan28.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else if(row.testtable == 'Test0202T0'){
							// 细集料试验
							html ="<span><a href='../ShiYan02/shiyan29.html?id="+row.id +"&issave=false&istestcollection=false&istestblind=true&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
						}else{
							html ="<span><a href='#'>查看</a></span>";
						}
										
					}else{
						html ="<span><a onclick='show();'>查看</a></span>";
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
		// 根据用户的权限显示相应列
		if(roletype == 1){
			list.column(6).visible(false);
			list.column(7).visible(false);
			list.column(8).visible(false);
		}else{
			list.column(6).visible(true);
			list.column(7).visible(true);
			list.column(8).visible(true);
		}
		$('.lq_biao .row:first-child').css('display','none');
		$('#list_info').css('text-align','center');
		$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	}		
});

function show(){
	swal({
		title: "当前试验还没有被收样!",
		text: "",
		type: "error",
		confirmButtonText: '确定',
		cancelButtonFont: '微软雅黑',
	});
}

//dtree选中事件
d.checkNode = function(id, i_parent_Id, flag, name, checked) {
	var html = "";
	// 获取所有tree项
	var objList = d.aNodes;
	// 判断是选中的父类或子类
	if(i_parent_Id != null && i_parent_Id != 0){		
		// 子节点勾选
		if(checked){	
			// 循环所有节点
			for(var i = 0; i < objList.length; i++){			
				// 对应子节点添加
				if(objList[i].id == id){
					if($("#station").html() != ""){
						// 添加子节点
						html = "<span class='s'>"+"、"+objList[i].cshow+"</span>";
						$("#station").append(html);
					}else{
						// 添加子节点
						html = "<span class='s'>"+objList[i].cshow+"</span>";
						$("#station").append(html);
					}
				}		
			}
			// 括号显示
			$("#isOrName").show();
			$("#isOrName1").show();
		}else{
			// 子节点取消
			// 循环所有节点
			for(var i = 0; i < objList.length; i++){
				if(objList[i].id == id){
					// 移除子节点
					$(".s").each(function() {
						if ($(this).text() == "、"+objList[i].cshow || $(this).text() == objList[i].cshow) {
							$(this).remove();
						}
					});
				}
			}
		}
	}else{
		// 父节点勾选
		if(checked){
			// 循环所有节点
			for(var i = 0; i < objList.length; i++){
				if(objList[i].pid == id){
					// 子节点勾选
					$("#children_"+objList[i].id).prop("checked",true);
					// 移除子节点勾选
					$(".s").each(function() {
						if ($(this).text() ==  "、" + objList[i].cshow || $(this).text() == objList[i].cshow ) {
							$(this).remove();
						}
					});
				}
			}
			
			// 循环所有节点
			for(var i = 0; i < objList.length; i++){
				// 对应所有的子集勾选状态
				if(objList[i].pid == id){
					if($("#station").html() != ""){
						// 添加子节点
						html = "<span class='s'>"+"、"+objList[i].cshow+"</span>";
						$("#station").append(html);
					}else{
						// 添加子节点
						html = "<span class='s'>"+objList[i].cshow+"</span>";
						$("#station").append(html);
					}
				}else if(objList[i].id == id){
					// 对应父节点添加
					if($("#station").html() != ""){
						html = "<span class='s'>"+"、"+objList[i].cshow+"</span>";
						$("#station").append(html);
					}else{
						html = "<span class='s'>"+objList[i].cshow+"</span>";
						$("#station").append(html);
				    }
				}	
			}
			$("#isOrName").show();
			$("#isOrName1").show();
		}else{
			// 取消父节点
			// 循环所有节点
			for(var i = 0; i < objList.length; i++){
				// 对应所有的子集不勾选状态
				if(objList[i].pid == id){
					// 子节点不勾选
					$("#children_"+objList[i].id).prop("checked",false);
					// 移除子节点
					$(".s").each(function() {
						if ($(this).text() == "、" + objList[i].cshow || $(this).text() == objList[i].cshow ) {
							$(this).remove();
						}
					});
				}else if(objList[i].id == id){
					// 移除父节点
					$(".s").each(function() {
						if ($(this).text() == "、" + objList[i].cshow || $(this).text() == objList[i].cshow) {
							$(this).remove();
						}
					});
				}
			}
			// 判断第一个显示是否是"、"
			if($("#station").html().substr(16,1) == "、"){
				html = $("#station").html().substr(0,16)+$("#station").html().substr(17,$("#station").html().length);
				$("#station").html(html);
			}
		}
	}
	
	// 全部取消时
	if($("#station").html() == null || $("#station").html() == ""){
		$("#isOrName").hide();
		$("#isOrName1").hide();
	}
}

//验证
function change(){
	$("#Modal").attr("style","display:block");
}


//确定
function ok() {
	
	var pd = $("#yanzhengma").val();
	// 验证
	if(pd == VerificationCode){
		guanBi()
		// 注册界面
		$(location).attr('href', '../BlindSample/shiyan_6.html');
	}else{
		swal({
			title: "验证码错误，无法操作!",
			text: "",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		},
		function(){
			$("#yanzhengma").val("");
		}
		);
	}
};

//关闭
function guanBi() {
	$("#Modal").attr("style","display:none");
	$("#yanzhengma").val("");
};
