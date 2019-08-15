//出库单id
var id = "";
//单位
var cachetCompanyList;
//人员
var cachetPersonnelList;
//出库单状态
var outType = "";

$(function(){
	//获取父页面的全局变量id
	id = parent.id;
	//获取父页面的全局变量outType
	outType = parent.outType;
	//获取所有单位电子签名
	getCachetCompany();
	//获取所有人员电子签名
	getCachetPersonnel();
	
	if(id != null && id != ""){
		//通过出库单id获取检测报告信息
		$.ajax({
			type : "post",
			url : "../../WeighingQueryOut/getTestreportInfoById.action",
			data : {"id" : id,"exchange" : 1},
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
					if(cachetPersonnelList != null && cachetPersonnelList.length > 0){
						for (var i = 0; i < cachetPersonnelList.length; i++) {
							//检测人
							if(testreportListList[0].testPersonnelId == cachetPersonnelList[i].id ){
								$("#testPersonnelId").css("display","block");
								$("#testPersonnelId").attr("src","http://119.3.233.75:8080/oilfactory/file/preview.action?fileName="+cachetPersonnelList[i].electronicsName);
							}
							//复核
							if(testreportListList[0].reviewerId == cachetPersonnelList[i].id){
								$("#reviewerId").css("display","block");
								$("#reviewerId").attr("src","http://119.3.233.75:8080/oilfactory/file/preview.action?fileName="+cachetPersonnelList[i].electronicsName);
							}
							//检测负责人
							if(testreportListList[0].inspectionSupervisorId == cachetPersonnelList[i].id){
								$("#inspectionSupervisorId").css("display","block");
								$("#inspectionSupervisorId").attr("src","http://119.3.233.75:8080/oilfactory/file/preview.action?fileName="+cachetPersonnelList[i].electronicsName);
							}
						}
					}else{
						$("#testPersonnelId").css("display","none");
						$("#reviewerId").css("display","none");
						$("#inspectionSupervisorId").css("display","none");
					}
					//单位电子签名
					if(cachetCompanyList != null && cachetCompanyList.length > 0){
						for (var i = 0; i < cachetCompanyList.length; i++) {
							if(testreportListList[0].detectionUnitId == cachetCompanyList[i].id){
								$("#testCompany").html(cachetCompanyList[i].testCompany);
								$("#detectionUnitId").css("display","block");
								$("#detectionUnitId").attr("src","http://119.3.233.75:8080/oilfactory/file/preview.action?fileName="+cachetCompanyList[i].electronicsName);
							}
						}
					}else{
						$("#detectionUnitId").css("display","none");
					}
					//出库时间
					if(outType == 3){//空发
						$("#factoryTime").text(testreportListList[0].createrDate);
					}else{
						$("#factoryTime").text(testreportListList[0].factoryTime);
					}
					//检测报告单销售订单明细
					var testreportsaledetailedList = data.testreportsaledetailedList;
					if (testreportsaledetailedList.length > 0){
						for(var i=0;i<testreportsaledetailedList.length;i++){
							//序号
							/*$("#id"+i).text((i+1));
							//检测项目
							$("#testingItems"+i).text(testreportsaledetailedList[i].testingItems);				
							//单位
							$("#indexUnit"+i).text(testreportsaledetailedList[i].indexUnit);
							//技术要求
							$("#skillRequire"+i).text(testreportsaledetailedList[i].skillRequire);
							//检测结果
							$("#testResult"+i).text(testreportsaledetailedList[i].testResult);
							//试验方法
							$("#testMethod"+i).text(testreportsaledetailedList[i].testMethod);*/
							var html = "";
							html += '<div class="commonTable2-line">';
							html += '<div><span>'+(i+1)+'</span></div>';
							html += '<div><span>'+testreportsaledetailedList[i].testingItems+'</span></div>';
							html += '<div><span>'+testreportsaledetailedList[i].indexUnit+'</span></div>';
							html += '<div><span>'+testreportsaledetailedList[i].skillRequire+'</span></div>';
							html += '<div><span>'+testreportsaledetailedList[i].testResult+'</span></div>';
							html += '<div><span>'+testreportsaledetailedList[i].testMethod+'</span></div>';
							html += '</div>';
							$("#table1").append(html);
						}
					} 	
				}else{
					$("#testPersonnelId").css("display","none");
					$("#reviewerId").css("display","none");
					$("#inspectionSupervisorId").css("display","none");
					$("#detectionUnitId").css("display","none");
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