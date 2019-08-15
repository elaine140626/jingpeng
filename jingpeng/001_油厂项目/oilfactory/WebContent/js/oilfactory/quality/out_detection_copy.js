// 单位电子签名
var cachetCompanyList;
// 人员电子签名
var cachetPersonnelList;
// 是否兑换
var isExchange;
$(function(){
	// 获取前页面传过来的id
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	isExchange = getUrlParam("isExchange");
	//获取所有单位电子签名
	getCachetCompany();
	//获取所有人员电子签名
	getCachetPersonnel();
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	// 根据出库单id获取检测项目明细
	getTestreportInfoById();
	if(userInfo != ""){	
		Creater = userInfo.id;
	}
	
})

// 根据出库单id获取检测项目明细
function getTestreportInfoById(){
	var param = {};
	// 出库单检测打印
	if(flag == 1){
		param.exportStorageId = id;
		param.exchange = isExchange;
	}else if(flag == 3){
		// 空白报告单打印
		param.id = id;
	}
	$.ajax({
		type : "POST",
		url : "../../testreportsaledetailed/getTestreportInfoById.action",
		async:false,
		data : param,
		dataType : "json",
		success : function(data) {
			var testreportListList = data.testreportListList;
			if(testreportListList.length > 0){
				var flag0 = true;
				var flag1 = true;
				var flag2 = true;
				var flag3 = true;
				$("#testreportId").val(testreportListList[0].id);
				$("#reportRenown").text(testreportListList[0].reportRenown);
				$("#productModel").text(testreportListList[0].productModel);
				$("#usePlace").text(testreportListList[0].usePlace);
				$("#testDate").text(testreportListList[0].testDate);
				$("#testReportNumber").text(testreportListList[0].testReportNumber);
				$("#conclusion").text((testreportListList[0].conclusion==0?"合格":"不合格"));
				$("#reportRemarks").text(testreportListList[0].reportRemarks);
				$("#detectionUnitName").text(testreportListList[0].detectionUnitName);
				for (var i = 0; i < cachetPersonnelList.length; i++) {
					if(testreportListList[0].testPersonnelId == cachetPersonnelList[i].id ){
						flag0 = false;
						$("#testPersonnelId").css("display","block");
						$("#testPersonnelId").attr("src","../../file/preview.action?fileName="+cachetPersonnelList[i].electronicsName);
					}else if(flag0){
						$("#testPersonnelId").css("display","none");
					}
					if(testreportListList[0].reviewerId == cachetPersonnelList[i].id){
						flag1 = false;
						$("#reviewerId").css("display","block");
						$("#reviewerId").attr("src","../../file/preview.action?fileName="+cachetPersonnelList[i].electronicsName);
					}else if(flag1){
						$("#reviewerId").css("display","none");
					}
					if(testreportListList[0].inspectionSupervisorId == cachetPersonnelList[i].id){
						flag2 = false;
						$("#inspectionSupervisorId").css("display","block");
						$("#inspectionSupervisorId").attr("src","../../file/preview.action?fileName="+cachetPersonnelList[i].electronicsName);
					}else if(flag2){
						$("#inspectionSupervisorId").css("display","none");
					}
				}
				for (var i = 0; i < cachetCompanyList.length; i++) {
					if(testreportListList[0].detectionUnitId == cachetCompanyList[i].id){
						flag3 = false;
						$("#detectionUnitId").css("display","block");
						$("#detectionUnitId").attr("src","../../file/preview.action?fileName="+cachetCompanyList[i].electronicsName);
					}else if(flag3){
						$("#detectionUnitId").css("display","none");
					}
				}
				$("#factoryTime").text(testreportListList[0].tareMeasureTime);
				var testreportsaledetailedList = data.testreportsaledetailedList;
				// 检测报告单销售订单明细
				if (testreportsaledetailedList.length > 0){
					for(var i=0;i<testreportsaledetailedList.length;i++){
						$("#id"+i).text((i+1));
						// 物料名称
						$("#testingItems"+i).text(testreportsaledetailedList[i].testingItems);
						if(testreportsaledetailedList[i].testingItems.length > 13){
							$("#testingItems"+i).parent('div').addClass('commonTable3 fold-line');	
						}
						//检测项目
						$("#indexUnit"+i).text(testreportsaledetailedList[i].indexUnit);
						if(testreportsaledetailedList[i].indexUnit.length > 7){
							$("#indexUnit"+i).parent('div').addClass('commonTable3 fold-line');	
						}
						//技术指标
						$("#testMethod"+i).text(testreportsaledetailedList[i].testMethod);
						if(testreportsaledetailedList[i].testMethod.length > 7){
							$("#testMethod"+i).parent('div').addClass('commonTable3 fold-line');	
						}
						$("#skillRequire"+i).text(testreportsaledetailedList[i].skillRequire);
						if(testreportsaledetailedList[i].skillRequire.length > 7){
							$("#skillRequire"+i).parent('div').addClass('commonTable3 fold-line');	
						}
						//检测结果
						$("#testResult"+i).text(testreportsaledetailedList[i].testResult);
						if(testreportsaledetailedList[i].testResult.length > 7){
							$("#testResult"+i).parent('div').addClass('commonTable3 fold-line');	
						}
					}
				} 	
			}
		}
	})
}

// 关闭页面
function closes(){
	location.href = 'out_detection.html';
}

//获取所有单位电子签名
function getCachetCompany(){
	$.ajax({
		type : "POST",
		url : "../../CachetCompany/getCachetCompany.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			cachetCompanyList = data.data;
		}
	});
}

//获取所有人员电子签名
function getCachetPersonnel(){
	$.ajax({
		type : "POST",
		url : "../../CachetPersonnel/getCachetPersonnel.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			cachetPersonnelList = data.data;

		}
	});
}