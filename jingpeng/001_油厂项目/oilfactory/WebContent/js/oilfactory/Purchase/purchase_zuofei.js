var id = "";
$(function(){
	id = getUrlParam("id");// 解析所有参数 
	userInfo = getUserInfo();
	if(userInfo != ""){	
		reviser = userInfo.id;
	}
});

function baocun(id){
	var CancellationCause = $("#zuofeiyuanyin").val();
	$.ajax({
        type: "post",
        url: '../../Purchase/ZuoFeiPurchasecontract.action',
        async:false,
        data:{"id":id,"reviser":reviser,"cancellationCause":CancellationCause,},
        dataType: "json",
        success: function (data) {
        	setTimeout(function(){swal({
				title: data.message,
				type: data.code,
				cancelButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			},
			function(){
				window.parent.$.fancybox.close();
				window.location.reload();
			}); },200);
        }
	});
};