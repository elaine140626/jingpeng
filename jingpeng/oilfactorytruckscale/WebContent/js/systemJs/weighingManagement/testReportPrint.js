//出库单id
var id = "";
//单位
var cachetCompanyList;
//人员
var cachetPersonnelList;

$(function(){
	//获取页面传过来的id
	id = getUrlParam("id");
	//获取所有单位电子签名
	getCachetCompany();
	//获取所有人员电子签名
	getCachetPersonnel();
	
	if(id != null && id != ""){
		//通过出库单id获取检测报告信息
		$.ajax({
			type : "post",
			url : "../../WeighingQueryOut/getTestreportInfoById.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				//检测报告信息
				var testreportListList = data.testreportListList;
				if(testreportListList.length > 0){
					//检测报告
					$("#reportRenown").text(testreportListList[0].reportRenown);
					//产品型号
					$("#productModel").text(testreportListList[0].productModel);
					//使用地点
					$("#usePlace").text(testreportListList[0].usePlace);
					//检测日期
					$("#testDate").text(testreportListList[0].testDate);
					//报告编号
					$("#testReportNumber").text(testreportListList[0].testReportNumber);
					//结论
					$("#conclusion").text((testreportListList[0].conclusion==0?"合格":"不合格"));
					//备注
					$("#reportRemarks").text(testreportListList[0].reportRemarks);
					//人员电子签名信息
					for (var i = 0; i < cachetPersonnelList.length; i++) {
						if(testreportListList[0].testPersonnelId == cachetPersonnelList[i].id ){
							$("#testPersonnelId").css("display","block");
							$("#testPersonnelId").attr("src","../../file/preview.action?fileName="+cachetPersonnelList[i].electronicsName);
						}
						if(testreportListList[0].reviewerId == cachetPersonnelList[i].id){
							$("#reviewerId").css("display","block");
							$("#reviewerId").attr("src","../../file/preview.action?fileName="+cachetPersonnelList[i].electronicsName);
						}
						if(testreportListList[0].inspectionSupervisorId == cachetPersonnelList[i].id){
							$("#inspectionSupervisorId").css("display","block");
							$("#inspectionSupervisorId").attr("src","../../file/preview.action?fileName="+cachetPersonnelList[i].electronicsName);
						}
					}
					//单位电子签名
					for (var i = 0; i < cachetCompanyList.length; i++) {
						if(testreportListList[0].detectionUnitId == cachetCompanyList[i].id){
							$("#detectionUnitId").css("display","block");
							$("#detectionUnitId").attr("src","../../file/preview.action?fileName="+cachetCompanyList[i].electronicsName);
						}
					}
					//出库时间
					$("#factoryTime").text(testreportListList[0].factoryTime);
					//检测报告单销售订单明细
					var testreportsaledetailedList = data.testreportsaledetailedList;
					if (testreportsaledetailedList.length > 0){
						for(var i=0;i<testreportsaledetailedList.length;i++){
							//序号
							$("#id"+i).text((i+1));
							//检测项目
							$("#testingItems"+i).text(testreportsaledetailedList[i].testingItems);				
							//单位
							$("#indexUnit"+i).text(testreportsaledetailedList[i].indexUnit);
							//技术要求
							$("#skillRequire"+i).text(testreportsaledetailedList[i].skillRequire);
							//检测结果
							$("#testResult"+i).text(testreportsaledetailedList[i].testResult);
							//试验方法
							$("#testMethod"+i).text(testreportsaledetailedList[i].testMethod);
						}
					} 	
				}
			}
		});
	}
});

//关闭按钮
function closes(){
	//关闭当前页面，刷新父页面的父页面
	window.parent.location.reload();
}

//获取电子公章检测单位表
function getCachetCompany(){
	$.ajax({
		type : "POST",
		url : "../../WeighingQueryOut/getCachetCompany.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			cachetCompanyList = data;
		}
	});
}

//检测指标信息取得(获取电子公章人员表)
function getCachetPersonnel(){
	$.ajax({
		type : "POST",
		url : "../../WeighingQueryOut/getCachetPersonnel.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			cachetPersonnelList = data;

		}
	});
}