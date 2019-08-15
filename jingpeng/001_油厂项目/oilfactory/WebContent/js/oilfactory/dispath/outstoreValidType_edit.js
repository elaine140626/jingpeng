var id = "";
// 赋值和修改的标识
var flag;

$(function() {
	// 获取上页面传过来的值
	id = getUrlParam("id");
	flag = getUrlParam("flag");
});

// 提交
function submitValidFlag () {
	$.ajax({
		type: "post",
		url: "../../ExportMeasure/updateValidFlag.action",
		data: {"id":id, "flag":flag, "cancellationCause":$("#cancellationCause").val()},
		dataType: "json",
		success: function (data) {
			if(data.code == 'success'){
				var param = {};
				window.parent.$.fancybox.close();
				window.location.href = 'outbound.html';	
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

