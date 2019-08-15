// 用户收样权限
var iscollector = 0;
var uniqueidentifiers = '';
$(function() {
	// 获取当前用户的信息
	var userInfo = getUserInfo();
	if(userInfo != ""){
		// 获取当前登录人的试验室权限
    	uniqueidentifiers = userInfo.userTestDetailed;
    	iscollector = userInfo.isCollector;
    	// 有收样权限
    	if(iscollector == 1){
    		$("#luru").attr("style", "display:block;");
    		// 获取权限树形
    		var testRooms = getTestRooms();
    		// 判断用户的收样权限
			$("#testRoomName").val(testRooms[0].testroomname);
			$("#testRoomNameId").val(testRooms[0].uniqueidentifier);
    	}
	}

	// 获取试验名称
	getTestNameList();

	// 试验员列表(默认显示权限下所有试验室) 
	$.ajax({
			type : "POST",
			url : "../../TestRecevied/getTestOperatorList.action",
			dataType : "json",
			data : {
				"uniqueIdentifiers" : uniqueidentifiers
			}, 
			success : function(data) {
				for (var i = 0; i < data.length; i++) {
					if (data[i].testoperator != null
							&& data[i].testoperator != '') {
						$('#cusName').append(
							"<option value='" + data[i].testoperator + "'>");
						// 判断用户的收样权限
						if(iscollector == 1){
							$('#Operator').append(
									"<option value='" + data[i].testoperator + "'>");
						}
					}
				}
			}
	});
	
	// 列表数据初始化
	load(uniqueidentifiers);

});

// 试验员
function getTestOperatorList(uniqueidentifier, flag){
	// 试验员列表 
	$.ajax({
			type : "POST",
			url : "../../TestRecevied/getTestOperatorList.action",
			dataType : "json",
			data : {
				"uniqueIdentifiers" : uniqueidentifier
			},  
			success : function(data) { 
				if(flag){
					$('#cusName').html("");
					for (var i = 0; i < data.length; i++) {
						if (data[i].testoperator != null
								&& data[i].testoperator != '') {
							$('#cusName').append(
								"<option value='" + data[i].testoperator + "'>");
							
						}
					}
				}else{
					$('#Operator').html("");
					for (var i = 0; i < data.length; i++) {
						if (data[i].testoperator != null
								&& data[i].testoperator != '') {
							$('#Operator').append(
								"<option value='" + data[i].testoperator + "'>");
							
						}
					}
				}
				
			}
	});
}

function load(uniqueidentifiers){
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
			url : "../../TestRecevied/getTestInfoList.action",
			dataSrc : "data",
			data : function(d) {
				var param = {};
				param.testNameId = '';
				param.testOperator = '';
				param.uniqueIdentifier = uniqueidentifiers;
				return param;
			}
		},
		"deferRender" : true,
		"columns" : [ {
			"data" : "row"
		}, {
			"data" : "testRoomName"
		}, {
			"data" : "testName"
		}, {
			"data" : "sampleName"
		}, {
			"data" : "testOperator"
		}, 
		 {
			"data" : "createDate"
		},
		 {
			"data" : "testDate"
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

//查询
$('#select').on('click', function(event) {
	//获取列表 刷新
	var table = $('#list').dataTable();
	table.fnDestroy();

	// 试验名称id
	var testNameId = $("#testPageInfosList option:selected").val();
	// 试验员
	var testOperator = $("#testOperator").val();
	// 试验室名称
	var authority = document.getElementsByName("authority");
	var children = document.getElementsByName("children");
	var uniqueIdentifier = "";
	
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
		load(uniqueidentifiers);
	}else{
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
				url : "../../TestRecevied/getTestInfoList.action",
				dataSrc : "data",
				data : function(d) {
					var param = {};
					param.testNameId = testNameId;
					param.testOperator = testOperator;
					param.uniqueIdentifier = uniqueIdentifier;
					return param;//自定义需要传递的参数。
				}
			},
			"deferRender" : true,
			"columns" : [ {
				"data" : "row"
			}, {
				"data" : "testRoomName"
			}, {
				"data" : "testName"
			}, {
				"data" : "sampleName"
			}, {
				"data" : "testOperator"
			},
			{
				"data" : "createDate"
			},
			 {
				"data" : "testDate"
			},],
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
});



var qRCode;

// 获取二维码
function QRCodeChange(value){
	qRCode= value;
	// 判断是否已经收样
	$.ajax({
		type : "POST",
		url : "../../TestRecevied/getExistFlag.action",
		dataType : "json",
		data : {
			"qRCode" : qRCode
		}, //参数（如果没有参数：null）  
		success : function(data) { //请求成功后处理函数。  
            if(data == 0){
            	// 通过二维码获取页面信息
            	$.ajax({
            		type : "POST",
            		url : "../../TestRecevied/getSampleIntelligence.action",
            		dataType : "json",
            		data : {
            			"qRCode" : qRCode
            		}, //参数（如果没有参数：null）  
            		success : function(data) { //请求成功后处理函数。  
                        if(data != null && data.length > 0){
                        	// 样品名称和试验名称
                			$("#testName").val(data[0].testName);
                			$("#sampleName").val(data[0].sampleName);	
                        }

            		}
               });

            	// 转换二维码
            	makeCode(qRCode);

            }else{
            	swal({
					title: "试验已经被收样！",
					text: "",
					type: "error",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				});
            	// 清空
            	$("#saomaxinxi").val("");
            }
		}
   });
	// input获取焦点事件
	$("#saomaxinxi").blur();
}

// 生成二维码大小
var qrcode = new QRCode(document.getElementById("qrcode"), {
	width : 100,
	height : 100
});

var index = 1;
// 转换二维码
function makeCode (value) {
	qrcode.makeCode(value);
	// 控制页面二维码图片显示
	$("#after").attr("style", "display:'';left: 50%;");
	$("#before").attr("style", "display:none;");
	// 清空
	$("#saomaxinxi").val("");
}

//关闭弹窗
$('#login_close').on('click', function(event) {
   	// 清空数值
	$("#testName").val("");
	$("#sampleName").val("");
	$("#testRoomName").html("");
	$("#saomaxinxi").val("");
	$("#qrcode").val("");
	$('#OperatorName').val("");
	// 控制页面二维码图片显示
	$("#after").attr("style", "display:none;");
	$("#before").attr("style", "display:'';");
});

//扫码按钮
$('#saoma').on('click', function(event) {
	// input获取焦点事件
	$("#saomaxinxi").focus();
});

// 保存
$('#save').on('click', function(event) {
	// 二维码
	// 试验室名称
	var uniqueIdentifier = $("#testRoomNameId").val();
	// 试验员
	var testOperator = $("#OperatorName").val();
	
	if(testOperator==''){
		swal("操作失败","试验员不能为空", "info");
		return;
	}
	
	if($("#testName").val() != null && $("#testName").val() != ''){
		$.ajax({
			type : "POST",
			url : "../../TestRecevied/addTestInfo.action",
			dataType : "json",
			data : {
				"qRCode" : qRCode,
				"uniqueIdentifier":uniqueIdentifier,
				"testoperator":testOperator
			}, //参数（如果没有参数：null）  
			success : function(data) { //请求成功后处理函数。
				if(data.code == "success"){
					swal({
						title: data.message,
						text: "",
						type: "success",
						confirmButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					},
					function(){
						//location.reload();
						loadHTML("test_received.html", contain);
					});
				}else{
					swal({
						title: data.message,
						text: "",
						type: "error",
					});
				}
			}
	   });		
	}else{
		swal({
			title: "没有相应的试验名称,请确认！",
			text: "",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		// 控制页面二维码图片显示
		$("#after").attr("style", "display:none;");
		$("#before").attr("style", "display:'';");
		$("#qrcode").val("");
		$("#testName").val("");
		$("#sampleName").val("");
		$("#testRoomName").html("");
		$('#OperatorName').val("");
	}
});
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
