var baseUrl = "";
var id = "";
var orgId;
var orgIds;
//调度出库单条数
var outgoingListNum = 0;
//调度出库单明细
var outgoingList = [];
//调度出库单空发条数
var outEmptyListNum = 0;
//调度出库单空发明细
var outEmptyList = [];
//调度出库单兑换条数
var outExchangeListNum = 0;
//调度出库单兑换明细
var outExchangeList = [];
//下发生产计划条数
var nextProductionCountNum = 0;
//出库单检测条数
var exportmeasureCountNum = 0;
//生产工艺通知单条数
var isInspectorNum = 0;
//生产工艺通知单条数
//var isExamineNum = 0;
//生产任务核对条数
var isCheckNum = 0;
//生产任务确认条数
var isProductionNum = 0;
//提交质检申请条数
var isApplicationNum = 0;
//生产过程检测
var isQualifiedNum = 0;
//生产完成确认条数
var isCompleteNum = 0;
//实际原料消耗条数
var isConfirmSubmissionNum = 0;

var isGoCenter = false;

$(function(){  
	baseUrl = getContextPath();
	$.ajax({
		type: "post",
		url: baseUrl+"/index/getMenu.action",
		data:{},
		async:false,
		dataType: "json",
		success: function (data) {
			if(data!=null){
				$("#username").html(data.name);
				orgId = data.rolecode;
				orgIds = orgId.split(",");
				id = data.id;
				$("#heTong").hide();
				$("#xiaoShou").hide();
				$("#diaoDu").hide();
				$("#yunShu").hide();
				$("#zhiJian").hide();
				$("#kuChun").hide();
				$("#xiTong").hide();
				$("#jiaGe").hide();
				$("#shengchan").hide();
				$("#cheliaojianguan").hide();
				$('.item').hide();
				//销售合同管理
				$("#contractManagement").hide();
				//客户信息
				$("#customerInformation").hide();
				//拜访记录
				$("#visitingRecord").hide();
				//回访记录
				$("#returnVisitRecord").hide();
				//箭头
				$("#icons").hide();
				//销售订单管理
				$("#isXiaoShou").hide();
				//箭头
				$("#icons1").hide();
				//出库单查询
				$("#isDDu").hide();
				//销售管理类隐藏
				$("#containerleft1").hide();
				//调度出库单
				$("#outboundOrder").hide();
				//调度空发单
				$("#outboundEmptyOrder").hide();
				//调度兑换单
				$("#outboundExchangeOrder").hide();
				//调度汇总单
				$('#outboundALL').hide();
				//其他出库单
				$("#outboundOther").hide();
				//下发生产计划
				$("#nextProductionPlan").hide();
				//生产任务确认
				$("#confirmProductionTask").hide();
				//提交质检申请
				$("#qualityChecking").hide();
				//生产完成确认
				$("#completeConfirm").hide();
				//实际原料消耗
				$("#actualMaterialConsumption").hide();
				//出库单检测
				$("#outDetection").hide();
				//生产工艺通知单
				$("#productionProcessNotice").hide();
				//生产过程检测
				$("#productionProcessDetection").hide();
				//未称重出库单
				$("#notWeighing").hide();
				//生产任务核对
				$("#taskChecking").hide();
				//车辆监管
				$("#vehicleSupervision").hide();
				//出库运输单
				$("#transportation").hide();
				if($.inArray("0", orgIds)!=-1){
					isGoCenter = true;
				}
				//0:管理员     1:销售员      2:销售总监    3:调度员   4:质检员    5:质检主任    6:财务    7:总经理      8:库管    9:生产部长     10:生产副部长    11:生产班长     12:检斤员
				if($.inArray("0", orgIds)!=-1 || $.inArray("7", orgIds)!=-1){
					$("#heTong").show();
					$("#xiaoShou").show();
					$("#diaoDu").show();
					$("#yunShu").show();
					$("#zhiJian").show();
					$("#kuChun").show();
					$("#xiTong").show();
					$("#shengchan").show();
					//$("#jiaGe").show();
					$("#cheliaojianguan").show();
					$('.item1').show();
					$("#containerleft1").show();
					$("#contractManagement").show();
					$("#customerInformation").show();
					$("#visitingRecord").show();
					$("#returnVisitRecord").show();
					$("#icons").show();
					$("#isXiaoShou").show();
					$("#icons1").show();
					$("#isDDu").show();
					$("#outboundOrder").show();
					$("#outboundEmptyOrder").show();
					$("#outboundExchangeOrder").show();
					$("#nextProductionPlan").show();
					$('#outboundALL').show();
					//其他出库单
					$("#outboundOther").show();
					$("#confirmProductionTask").show();
					$("#qualityChecking").show();
					$("#completeConfirm").show();
					$("#actualMaterialConsumption").show();
					$("#outDetection").show();
					$("#productionProcessNotice").show();
					$("#productionProcessDetection").show();
					$("#notWeighing").show();
					$("#taskChecking").show();
					$("#vehicleSupervision").show();
					$("#transportation").show();
				}				
				// 销售员
				if($.inArray("1", orgIds)!=-1){
					$("#heTong").show();
					$("#xiaoShou").show();
					$("#diaoDu").show();
					$("#yunShu").show();
					$("#cheliaojianguan").show();
					$('.item1').show();
					$("#containerleft1").show();
					$("#contractManagement").show();
					$("#customerInformation").show();
					$("#visitingRecord").show();
					$("#returnVisitRecord").show();
					$("#icons").show();
					$("#isXiaoShou").show();
					$("#icons1").show();
					$("#isDDu").show();
					$("#vehicleSupervision").show();
					$("#transportation").show();
					$('#outboundALL').show();
					//其他出库单
					$("#outboundOther").show();
				}
				// 销售总监
				if($.inArray("2", orgIds)!=-1){
					$("#heTong").show();
					$("#xiaoShou").show();
					$("#diaoDu").show();
					$("#yunShu").show();
					$("#cheliaojianguan").show();
					$('.item1').show();
					$("#containerleft1").show();
					$("#contractManagement").show();
					$("#customerInformation").show();
					$("#visitingRecord").show();
					$("#returnVisitRecord").show();
					$("#icons").show();
					$("#isXiaoShou").show();
					$("#icons1").show();
					$("#isDDu").show();
					$("#vehicleSupervision").show();
					$("#transportation").show();
					$("#xiTong").show();
					$('#outboundALL').show();
					//其他出库单
					$("#outboundOther").show();
				}
				// 调度员 && 调度管理员
				if($.inArray("3", orgIds)!=-1 || $.inArray("13", orgIds)!=-1){
					$("#diaoDu").show();
					$("#yunShu").show();
					$("#shengchan").show();
					$("#zhiJian").show();
					$("#kuChun").show();
					$("#cheliaojianguan").show();
					$('.item2').show();
					$("#outboundOrder").show();
					$("#outboundEmptyOrder").show();
					$("#outboundExchangeOrder").show();
					$("#nextProductionPlan").show();
					$('#outboundALL').show();
					//其他出库单
					$("#outboundOther").show();
					$("#confirmProductionTask").show();
					$("#qualityChecking").show();
					$("#completeConfirm").show();
					$("#actualMaterialConsumption").show();
//					$("#isDDu").removeClass("client_block block6");
//					$("#isDDu").css("margin-top","0px");
					$("#isDDu").show();
					$("#outDetection").show();
					$("#productionProcessNotice").show();
					$("#productionProcessDetection").show();
					$("#notWeighing").show();
					$("#taskChecking").show();
					$("#vehicleSupervision").show();
					$("#transportation").show();
				}
				// 质检员 && 质检主任
				if($.inArray("4", orgIds)!=-1 || $.inArray("5", orgIds)!=-1){
					$("#zhiJian").show();
					$('.item5').show();
					$("#outDetection").show();
					$("#productionProcessNotice").show();
					$("#productionProcessDetection").show();
				}
				// 质检主任
				if($.inArray("5", orgIds)!=-1){
					$("#xiTong").show();
				}
				// 财务
				if($.inArray("6", orgIds)!=-1){
				}
				// 库管
				if($.inArray("8", orgIds)!=-1){
					$("#diaoDu").show();
					$("#yunShu").show();
					$("#kuChun").show();
					$('.item3').show();
					$('#outboundALL').show();
					//其他出库单
					$("#outboundOther").show();
					$("#taskChecking").show();
					$("#xiTong").show();
					$("#transportation").show();
				}
				// 生产部长 && 生产副部长 && 生产班长 && 检斤员
				if($.inArray("9", orgIds)!=-1 || $.inArray("10", orgIds)!=-1 || $.inArray("11", orgIds)!=-1 || $.inArray("12", orgIds)!=-1){
					$("#diaoDu").show();
					$("#zhiJian").show();
					$("#kuChun").show();
					$("#shengchan").show();
					$('.item3').show();
					$("#nextProductionPlan").show();
					$("#confirmProductionTask").show();
					$("#qualityChecking").show();
					$("#completeConfirm").show();
					$("#actualMaterialConsumption").show();
					$("#productionProcessNotice").show();
					$("#productionProcessDetection").show();
					$("#taskChecking").show();
				}
				// 生产部长
				if($.inArray("9", orgIds)!=-1){
					$("#xiTong").show();
				}
				// 调度管理员
				if($.inArray("13", orgIds)!=-1){
					$("#xiTong").show();
				}
			}else{
				window.location.href="page/user/login.html";
			}
		}
	});
	getPush();
	//定时接受消息
	var pushTimer = setInterval(function(){ getPush() },60000)
 }); 
function outLogin() {
	$.ajax({
		type : "POST",
		url : baseUrl+"/login/delUserSession.action",
		data : {},
		dataType : "json",
		success : function(data) {
				// 首页
			window.location.href = "page/user/login.html";
		}
	});
}


function isXiaoShou(e){
	$.inArray("0", orgIds)!=-1
	if($.inArray("0", orgIds)!=-1||$.inArray("1", orgIds)!=-1||$.inArray("2", orgIds)!=-1||$.inArray("7", orgIds)!=-1){
				if(e.id == "kehu"){
					var jumpHref = "page/client/client.html";
					var itemId = $(e).parent('.tabs').parents('.item').attr('data-itemId');
					$('#rightMain').attr('src', jumpHref);
				}else if(e.id == "baifang"){
					var jumpHref = "page/client/client_visit.html";
					var itemId = $(e).parent('.tabs').parents('.item').attr('data-itemId');
					$('#rightMain').attr('src', jumpHref);
				}else if(e.id == "huifang"){
					var jumpHref = "page/client/client_back_visit.html";
					var itemId = $(e).parent('.tabs').parents('.item').attr('data-itemId');
					$('#rightMain').attr('src', jumpHref);
				}else if(e.id == "xsdingdan"){
					var jumpHref = "page/sales/sales_orders.html";
					var itemId = $(e).parent('.tabs').parents('.item').attr('data-itemId');
					$('#rightMain').attr('src', jumpHref);
				}
			}else{
				swal({
					title: "操作失败",
					text:"你没有该权限",
					type: "error",
					confirmButtonText: '确定',
		 			cancelButtonFont: '微软雅黑'
				},function(){
					window.location.href = "index.html";
					return;
				})
			}
}

function isDDu(e){
	if($.inArray("0", orgIds)!=-1||$.inArray("3", orgIds)!=-1||$.inArray("13", orgIds)!=-1||$.inArray("2", orgIds)!=-1||$.inArray("1", orgIds)!=-1||$.inArray("7", orgIds)!=-1){
		var jumpHref = "page/sales/outlist.html";
		var itemId = $(e).parent('.tabs').parents('.item').attr('data-itemId');
		$('#rightMain').attr('src', jumpHref);
	}else{
		swal({
			title: "操作失败",
			text:"你没有该权限",
			type: "error",
			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑'
		},function(){
			window.location.href = "index.html";
			return;
		})
	}
}
//点击列表跳转到当前模块对应的页面
function jumpDetalis(i){
	$('.item').hide();
	$('#rightMain').show();
	$('#here_area').html('当前位置：系统&nbsp;>&nbsp;<span style="color:#1A5CC6"></span>')
	$('#rightMain').attr('src', "");
	var liId = parseInt($(i).attr('data-id'));
	var liHref = $(i).attr('data-href');
	var texs = '';
	$('#rightMain').attr('src',liHref);
	$('#messageList,#messageOutList,#messageEmptyList,#messageExchangeList').css('display','none');
	$('#TabPage2 li').each(function(i, es) {
		if(i != liId) {
			$(es).find('img').attr('src', 'img/common/' + (i + 1) + '.png');
			$(es).find('span').css({
				color: '#fff'
			});
			$(es).css({
				background: '#044599'
			});
		} else {
			$(es).find('img').attr('src', 'img/common/' + (liId + 1) + '_hover.png');
			$(es).css({
				background: '#fff'
			});
			$(es).find('span').css({
				color: '#044599'
			});
			texs = $(es).find('span').html()
		}

	});
}
//获取推送消息
function getPush(){
	var ms = 0;
	$.ajax({
		type : "POST",
		url : baseUrl+"/PushMessage/getPushMessage.action",
		data : {
			id: id,
			orgId:orgId
		},
		dataType : "json",
		success : function(data) {
			//console.log(data);
			//调度出库单条数
			outgoingListNum = data.getPushMessageNormal.length = ''?0:parseInt(data.getPushMessageNormal.length);
			//调度出库单明细
			outgoingList = data.getPushMessageNormal;
			//调度出库单空发条数
			outEmptyListNum = data.getPushMessageEmpty.length = ''?0:parseInt(data.getPushMessageEmpty.length);
			//调度出库单空发明细
			outEmptyList = data.getPushMessageEmpty;
			//调度出库单兑换条数
			outExchangeListNum = data.getPushMessagExchange.length = ''?0:parseInt(data.getPushMessagExchange.length);
			//调度出库单兑换明细
			outExchangeList = data.getPushMessagExchange;
			if(outgoingListNum>0) {
				var outgoingListli='';
				$.each(outgoingList,function(j,k) {
					outgoingListli += '<li>销售订单编号：'+ k.orderNumber + '<br>明细编号：' + k.orderDetailedNumber +'</li>'
				})
				$('#messageOutList').html(outgoingListli);
			}
			if(outEmptyListNum>0) {
				var outEmptyListli='';
				$.each(outEmptyList,function(j,k) {
					outEmptyListli += '<li>销售订单编号：'+ k.orderNumber + '<br>明细编号：' + k.orderDetailedNumber +'</li>'
				})
				$('#messageEmptyList').html(outEmptyListli);
			}
			if(outExchangeListNum>0) {
				var outExchangeListli='';
				$.each(outExchangeList,function(j,k) {
					outExchangeListli += '<li>销售订单编号：'+ k.orderNumber + '<br>明细编号：' + k.orderDetailedNumber +'</li>'
				})
				$('#messageExchangeList').html(outExchangeListli);
			}
			//下发生产计划条数
			nextProductionCountNum = data.pushMessageList[0].nextProductionCount=''?0:parseInt(data.pushMessageList[0].nextProductionCount);
			//出库单检测条数
			exportmeasureCountNum = data.pushMessageList[1].exportmeasureCount=''?0:parseInt(data.pushMessageList[1].exportmeasureCount);
			//生产工艺通知单条数
			isInspectorNum = data.pushMessageList[2].isInspector=''?0:parseInt(data.pushMessageList[2].isInspector);
			//生产任务核对条数
			isCheckNum = data.pushMessageList[4].isCheck=''?0:parseInt(data.pushMessageList[4].isCheck);
			//生产任务确认条数
			isProductionNum = data.pushMessageList[5].isProduction=''?0:parseInt(data.pushMessageList[5].isProduction);
			//提交质检申请条数
			isApplicationNum = data.pushMessageList[6].isApplication=''?0:parseInt(data.pushMessageList[6].isApplication);
			//生产过程检测
			isQualifiedNum = data.pushMessageList[7].isQualified=''?0:parseInt(data.pushMessageList[7].isQualified);
			//生产完成确认条数
			isCompleteNum = data.pushMessageList[8].isComplete=''?0:parseInt(data.pushMessageList[8].isComplete);
			//实际原料消耗条数
			isConfirmSubmissionNum = data.pushMessageList[9].isConfirmSubmission=''?0:parseInt(data.pushMessageList[9].isConfirmSubmission);
			if(outgoingListNum != 0) {
				$('#outgoingListNum').show();
				$('#outgoingList').show();
				$('#outgoingListNum span').html(outgoingListNum);
				$('#outgoingList').html(outgoingListNum);
			}else {
				$('#outgoingListNum').hide();
				$('#outgoingList').hide();
			}
			if(outEmptyListNum != 0) {
				$('#outEmptyListNum').show();
				$('#outEmptyList').show();
				$('#outEmptyListNum span').html(outEmptyListNum);
				$('#outEmptyList').html(outEmptyListNum);
			}else {
				$('#outEmptyListNum').hide();
				$('#outEmptyList').hide();
			}if(outExchangeListNum != 0) {
				$('#outExchangeListNum').show();
				$('#outExchangeList').show();
				$('#outExchangeListNum span').html(outExchangeListNum);
				$('#outExchangeList').html(outExchangeListNum);
			}else {
				$('#outExchangeListNum').hide();
				$('#outExchangeList').hide();
			}
			if(nextProductionCountNum != 0) {
				$('#nextProductionCountNum').show();
				$('#nextProductionCount').show();
				$('#nextProductionCountNum span').html(nextProductionCountNum);
				$('#nextProductionCount').html(nextProductionCountNum)
			}else {
				$('#nextProductionCountNum').hide();
				$('#nextProductionCount').hide();
			}
			if(exportmeasureCountNum != 0) {
				$('#exportmeasureCountNum').show();
				$('#exportmeasureCount').show();
				$('#exportmeasureCountNum span').html(exportmeasureCountNum);
				$('#exportmeasureCount').html(exportmeasureCountNum);
			}else {
				$('#exportmeasureCountNum').hide();
				$('#exportmeasureCount').hide();
			}
			if(isInspectorNum != 0) {
				$('#isInspectorNum').show();
				$('#isInspector').show();
				$('#isInspectorNum span').html(isInspectorNum);
				$('#isInspector').html(isInspectorNum);
			}else {
				$('#isInspectorNum').hide();
				$('#isInspector').hide();
			}
			if(isCheckNum != 0) {
				$('#isCheckNum').show();
				$('#isCheck').show();
				$('#isCheckNum span').html(isCheckNum);
				$('#isCheck').html(isCheckNum);
			}else {
				$('#isCheckNum').hide();
				$('#isCheck').hide();
			}
			if(isProductionNum != 0) {
				$('#isProductionNum').show();
				$('#isProduction').show();
				$('#isProductionNum span').html(isProductionNum);
				$('#isProduction').html(isProductionNum);
			}else {
				$('#isProductionNum').hide();
				$('#isProduction').hide();
			}
			if(isApplicationNum != 0) {
				$('#isApplicationNum').show();
				$('#isApplication').show();
				$('#isApplicationNum span').html(isApplicationNum);
				$('#isApplication').html(isApplicationNum);
			}else {
				$('#isApplicationNum').hide();
				$('#isApplication').hide();
			}
			if(isQualifiedNum != 0) {
				$('#isQualifiedNum').show();
				$('#isQualified').show();
				$('#isQualifiedNum span').html(isQualifiedNum);
				$('#isQualified').html(isQualifiedNum);
			}else {
				$('#isQualifiedNum').hide();
				$('#isQualified').hide();
			}
			if(isCompleteNum != 0) {
				$('#isCompleteNum').show();
				$('#isComplete').show();
				$('#isCompleteNum span').html(isCompleteNum);
				$('#isComplete').html(isCompleteNum);
			}else {
				$('#isCompleteNum').hide();
				$('#isComplete').hide();
			}
				if(isConfirmSubmissionNum != 0) {
					$('#isConfirmSubmissionNum').show();
					$('#isConfirmSubmission').show();
					$('#isConfirmSubmissionNum span').html(isConfirmSubmissionNum);
					$('#isConfirmSubmission').html(isConfirmSubmissionNum);
				}else {
					$('#isConfirmSubmissionNum').hide();
					$('#isConfirmSubmission').hide();
				}
				if(outgoingListNum+outEmptyListNum+outExchangeListNum+nextProductionCountNum>0) {
					$('#diaoDu .tips').show();
					if($.inArray("1", orgIds)!=-1 || $.inArray("2", orgIds)!=-1 || $.inArray("8", orgIds)!=-1 || $.inArray("9", orgIds)!=-1 || $.inArray("10", orgIds)!=-1|| $.inArray("11", orgIds)!=-1) {
						$('#diaoDu .tips').hide();
					}
//					$('#diaoDu .tips').html(outgoingListNum+nextProductionCountNum);
				}else {
					$('#diaoDu .tips').hide();
				}
				if(exportmeasureCountNum+isInspectorNum+isQualifiedNum>0) {
					$('#zhiJian .tips').show();
//					$('#zhiJian .tips').html(exportmeasureCountNum+isInspectorNum+isQualifiedNum);
				}else {
					$('#zhiJian .tips').hide();
				}
				if(isCheckNum>0) {
					$('#kuChun .tips').show();
//					$('#kuChun .tips').html(isCheckNum);
				}else {
					$('#kuChun .tips').hide();
				}
				if(isProductionNum+isApplicationNum+isCompleteNum+isConfirmSubmissionNum>0) {
					$('#shengchan .tips').show();
//					$('#shengchan .tips').html(isProductionNum+isApplicationNum+isCompleteNum+isConfirmSubmissionNum);
				}else {
					$('#shengchan .tips').hide();
				}
				$('#messageList li').hide();
				$('#TabPage2 li').each(function(o,p) {
					if($(p).attr('style').indexOf('list-item') != -1) {
						$('#messageList li').each(function(w,r){
							if($(r).attr('data-id')==o){
								if($(r).find('span').html() != '') {
									if($('#'+$(r).attr('data-div')).attr('style').indexOf('block') != -1){
										$(r).show();
										ms += parseInt($(r).find('span').html())
									}else {
										$(r).hide();
									}
								}else {
									$(r).hide();
								}		
							}
							
						})
					}
				})
			if(ms<= 0) {
				$('#message').hide();
			} else {
				$('#message').show();
			}
		}
	});
	
}

function jmupInfo() {
	/* $('.item').hide();
	$('#rightMain').show(); */
	if(isGoCenter){
		location.href = "page/user/user_center.html";
	}
	/* $('#rightMain').attr('src', 'page/user/user_center.html');
	$('#here_area').html('当前位置：系统&nbsp;>&nbsp;<span style="color:#1A5CC6">个人中心</span>'); */
}
