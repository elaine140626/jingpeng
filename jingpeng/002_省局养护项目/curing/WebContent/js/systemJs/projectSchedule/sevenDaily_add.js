// 当前登录用户id
var userId = ''; 
// 主键id
var id = '';
// 项目id
var ProjectId = '';
// 子目号List
var SubheadNumberMoneyList;
var flag1 = false;
//查看/编辑标识
var flag = '';
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}
	
	id = getUrlParam("id");
	ProjectId = getUrlParam("ProjectId");
	flag = getUrlParam("flag");
	// 年
	$('#dailyYear').val(getUrlParam("DailyYear"));
	// 月
	$('#dailyMonth').val(getUrlParam("DailyMonth"));
	// 周
	$('#dailyDay').val(getUrlParam("DailyDay"));
	// 修改的场合
	if (id != null && id != ""){
		// 获取七日报信息
		$.ajax({
			type : "post",
			url : "../../SevenDaily/getSevenDailyById.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				if(data != null && data.length > 0){
					SetFromValues($("#form1"), data[0]);
				}
				
				if(flag == 0){
					// 查看
					$('input[type="text"]').attr('disabled','disabled');
					// 隐藏保存按钮
					$("#saveButton").hide();
				}else if(flag == 1){
					// 修改
					$('input[type="text"]').removeAttr('readonly','readonly');
					// 显示保存按钮
					$("#saveButton").show();
				}
			}
		});
		flag1 = true;
	}
	// 子目号list取得
	getSubheadNumberMoney();
})

// 子目号list取得
function getSubheadNumberMoney(){
	$.ajax({
		type : "post",
		url : "../../InitialInfo/getSubheadNumberMoney.action",
		data : {"ProjectId" : ProjectId},
		dataType : "json",
		success : function(data) {
			if(data != null && data.length > 0){
				SubheadNumberMoneyList = data;
			}
		}
	});
}

// 通过输入的子目号取得子目名称
function getName(obj){	
	// 子目id赋空
	$('#subheadNumberId').val('');
	// 子目名称赋空
	$('#subheadName').val('');
	// 总量
	$('#total').val('');
	// 单价
	$('#unitPrice').val('');
	// 总价
	$('#totalPrice').val('');
	var SubheadNumber = obj.value;
	if (SubheadNumberMoneyList != null && SubheadNumberMoneyList.length>0){
		for(var i=0;i<SubheadNumberMoneyList.length;i++){
			if (SubheadNumber == SubheadNumberMoneyList[i].subheadNumber){
				// 子目id赋值
				$('#subheadNumberId').val(SubheadNumberMoneyList[i].id);
				// 子目名称赋值
				$('#subheadName').val(SubheadNumberMoneyList[i].subheadName);
				// 总量
				$('#total').val(SubheadNumberMoneyList[i].total);
				// 单价
				$('#unitPrice').val(SubheadNumberMoneyList[i].unitPrice);
				// 总价
				$('#totalPrice').val(SubheadNumberMoneyList[i].totalPrice);
				flag1 = true;
				return;
			} else {
				flag1 = false;
			}
		}
	}
	if (!flag1){
		if($("#subheadNumber").val() == ''){
			// 操作提示
			layer.alert("子目号不能为空", {
				icon: 7,
				title: "提示"
			});
		}else{
			// 操作提示
			layer.alert("输入的子目号不存在", {
				icon: 7,
				title: "提示"
			});
		}
	}
}

//// 根据总量，单价计算总价
//function getTotalPrice(){
//	// 总量
//	var total = $('#total').val();
//	// 单价
//	var unitPrice = $('#unitPrice').val();
//	if (total!="" && unitPrice!= ""){
//		$('#totalPrice').val(total*unitPrice);
//		// 累计完成进度（%）
//		getCumulativeSchedule();
//	}
//}

// 累计计算
function getCumulative(){
	// 年
	var dailyYear = $('#dailyYear').val();
	// 月
	var dailyMonth = $('#dailyMonth').val();
	dailyMonth = ("0"+ dailyMonth ).substr(-2);
	// 周
	var dailyDay = $('#dailyDay').val();
	
	var date = '';
	date += dailyYear + dailyMonth + dailyDay;
	$.ajax({
		type : "post",
		url : "../../SevenDaily/getCumulative.action",
		data : {"projectId" : ProjectId,"subheadNumberId":$('#subheadNumberId').val(),"date":date},
		dataType : "json",
		success : function(data) {
			if(data != null && data.length > 0){
				var thisCompleteSum = 0;
				var thisCapitalSum = 0;
				if (data[0] != null){
					thisCompleteSum = data[0].thisCompleteSum;
					thisCapitalSum = data[0].thisCapitalSum;					
				}	
				// 累计完成（平方米）
				if ($('#thisComplete').val() != ""){
					cumulativeComplete = Number(thisCompleteSum) + Number($('#thisComplete').val())
					$('#cumulativeComplete').val(cumulativeComplete);
					// 累计完成进度（%）
					getCumulativeSchedule();
				}				
				// 累计完成资金
				if ($('#thisCapital').val() != ""){
					cumulativeCapital = Number(thisCapitalSum) + Number($('#thisCapital').val())
					$('#cumulativeCapital').val(cumulativeCapital);
				}
			}
		}
	});
}

// 累计完成进度（%）
function getCumulativeSchedule(){
	// 累计完成（平方米）
	var cumulativeComplete = $('#cumulativeComplete').val();
	// 总量
	var total = $('#total').val();
	if (cumulativeComplete != "" && total!= ""){
		cumulativeSchedule = cumulativeComplete/total *100;
		$('#cumulativeSchedule').val(cumulativeSchedule.toFixed(4));
	}	
}

// 保存
function save(){	
	if (flag1){
		var params = formToJson($("#form1"));	
		params.projectId = ProjectId;
		console.log(params);
		var url = '';
		if (id == null){
			// 新增
			url = '../../SevenDaily/insertSevenDaily.action';			
			params.creater=userId;
		} else {
			// 修改
			url = '../../SevenDaily/updateSevenDaily.action';
			params.id = id;
			params.reviser=userId;
		}
		$.ajax({
			type: 'POST',
			url: url,
			data:JSON.stringify(params),
			dataType: 'json',
			contentType: 'application/json',
			success: function(data){
				if (data.code == 'success'){
					// 操作成功
					layer.alert(data.message, {
						icon: 1,
						title: "提示"
					},function(){
						window.parent.location.reload();
					});
				} else {
					// 操作失败
					layer.alert(data.message, {
						icon: 2,
						title: "提示"
					});
				}			
			}
		});	
	} else {
		// 操作提示
		layer.alert("请输入正确的子目号", {
			icon: 7,
			title: "提示"
		});
	}	
}