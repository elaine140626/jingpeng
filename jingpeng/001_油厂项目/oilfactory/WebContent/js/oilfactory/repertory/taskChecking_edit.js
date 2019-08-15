// 用户
var userInfo = "";
var id = "";
var flag = '';
// 是否满足生产
var isSatisfy = false;
$(document).ready(function(){
	// 获取用户信息
	userInfo = getUserInfo();
	// 获取上页面传过来的值
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	
	init();
})


// 画面数据初始化
function init(){
	// 画面数据初始化
	if(id != null && id != ''){
		$.ajax({
			type: "post",
			url: "../../taskChecking/getInfoList.action",
			data:{"Id":id},
			dataType: "json",
			async:false,
			success: function (data) {
				// 原材料内容
				if(data != null && data.length > 0){
					SetFromValues($("#submitForm"), data[0]);
					// 计划生产量
					$("#planWeight").val(data[0].planWeight + data[0].unit);
					
					var html = '';
					for(var i = 0; i<data.length; i++){
						if(data[i].isSatisfy == '否'){
							isSatisfy = true;
						}
						html += "<tr>"
							    +"<td>"+data[i].rawMaterialName+"</td>"
							    +"<td>"+data[i].rawMaterialModel+"</td>"
							    +"<td>"+data[i].materielWeight+"</td>"
							    +"<td>"+data[i].unit+"</td>"
							    +"<td>"+data[i].primeNumber+"</td>"
							    +"<td>"+data[i].unit+"</td>"
							    +"<td>"+data[i].isSatisfy+"</td>"
						        +"</tr>";
					}
					$("#tableList").html(html);
				}
			}
		});
		
		// 查看
		if(flag == 0){
			// 画面不可编辑
			$("#submitbutton").hide();
		}else{
			//审核
			$("#submitbutton").show();
		}
	}
}

//form表单赋值方法
function SetFromValues(el, data)
{
    for (var p in data)
    {
        el.find(":input[name='" + p + "']").val(data[p]);
    } 
}

// 保存方法
function save(){
	if(isSatisfy){
		swal({
			title: "错误提示",
			text: "不满足生产计划,不能核对!",
			type: 'error',
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}else{
		var param = {};
		// 核对人
		param.checkPerson = userInfo.name;
		// 是否核对
		param.isCheck = 0;
		// 更新人
		param.reviser = userInfo.id;
		// id
		param.id= id;

		$.ajax({
			type: "post",
			url: '../../nextProductionPlan/updateProductionPlan.action',
			data: param,
			dataType: "json",
			success: function (data) {
				if(data.code == "success"){
					swal({
						title: "提示",
						text: data.message,
						type: data.code,
						confirmButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					},function(){
						window.parent.$.fancybox.close();
					});
				}else{
					swal({
						title: "错误提示",
						text: data.message,
						type: data.code,
						confirmButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					});
				}
			}
		});
	}
}

function colse(){
	window.parent.$.fancybox.close();
}