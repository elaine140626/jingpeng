var chartTimer ;
$(function(){
	getUserInfo();
});


//加载子页面
function loadHTML(url, container, callback) {
	$.ajax({
		type: "get",
		url: url,
		dataType: 'html',
		beforeSend: function() {
			//					container.html('<h1><i class="fa fa-cog fa-spin"></i> 加载中...</h1>');
		},
		success: function(data) {
			container.html(data);
		},
		error: function(xhr, ajaxOptions, thrownError) {

		}
	});
};
//遍历得到菜单
function getMenu(box) {
	$.ajax({
		type: "get",
		url: "../../js/set.json",
		async: true,
		dataType: 'json',
		success: function(data) {
			$.each(data.projectItem[0].project, function(i, n) {
				if(i == 0) {
					firstUrl = n.itemUrl;
				}
				var itemStr = '<li class="left" onclick="changeMenu(this)" data-src="' + n.itemUrl + '"><a href="javascript:void(0)" data-clickClass="' + n.clickCLass + '" data-class="' + n.class + '" class="' + n.class + '">' + n.itemName + '</a></li>'
				$('.leftnav_g ul').append(itemStr);

			})
			if(viewUrl != '') {
				jumpUrl = viewUrl;
			} else {
				jumpUrl = firstUrl;
			}
			showMenu(jumpUrl);

		}
	});

}
//点击切换菜单
function changeMenu(e) {
	$('.left a').each(function(i, n) {
		$(n).removeClass($(n).attr('data-clickClass')).addClass($(n).attr('data-class'));
	})
	var jumpHref = $(e).attr('data-src');
	var name = $(e).find('a').html();
	var clickCLass = $(e).find('a').attr('data-clickClass');
	var CLass = $(e).find('a').attr('data-class');
	if(jumpHref == 'index.html') {
		location.href = 'index.html';
	} else {
		$('#bannerName').html(name);
		//加载子页面
		loadHTML(jumpHref, contain);
		$(e).find('a').removeClass(CLass).addClass(clickCLass);
		if(jumpHref == 'maintenance.html') {
			$('.shunav').hide();
			$('.arr').hide();
			$('.sele').hide();
		} else {
			$('.shunav').show();
			$('.arr').show();
			$('.sele').show();
		}
		if(chartTimer) {
			clearInterval(chartTimer)
		}
	}
}
//从主页跳转后，判断子页面，左侧图标高亮,显示当前页面
function showMenu(url) {
	$('.left').each(function(i, n) {
		if($(n).attr('data-src') == url) {
			var obj = $(n).find('a');
			$(obj).removeClass($(obj).attr('data-class')).addClass($(obj).attr('data-clickClass'));
			$('#bannerName').html($(n).find('a').html());
			//加载子页面
			loadHTML(url, contain);
		}
	})
}

//退出登陆
function outLogin(){
	$.ajax({
		type : "POST",
		url : "../../UserInfo/delUserSession.action",
		data : {},
		dataType : "json",
		success : function(data) {
			if(data.code == 'error'){
				// 失败信息
				swal("操作失败",data.message, data.code);
			}else{
				// 首页
				$(location).attr('href', '../centerLogin/centerLogin.html');	
			}
		}
	});
}