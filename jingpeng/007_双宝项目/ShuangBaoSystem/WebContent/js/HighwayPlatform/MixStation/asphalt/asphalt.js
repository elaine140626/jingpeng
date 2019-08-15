var chart_timer ;
var materials_echartimer ;
var warning_echartimer ;
$(function() {
	$.ajax({
        type: "post",
        url: "../../UserInfo/getPtUserInfo.action",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	//获取当前登录人做显示
        	$(".userid").html(data.name);
        }
    });
	$("#startTime").val(getNowFormatDate(1));
	$("#endTime").val(getNowFormatDate(0));
	$('.asphalt_search_btn').click(function() {
		if($("#startTime").val()>$("#endTime").val()){
			swal("操作失败","开始时间大于结束时间!", "info");
		}
		getList();
	})
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
//试验日期初始化赋值
function getNowFormatDate(flg) {
	if(flg == 0) {
		//获取当前日期
		var date = new Date();
	} else {
		//获取30天前的日期
		var myDate = new Date();
		var date = new Date(myDate - 1000 * 60 * 60 * 24 * 30);
	}

	var seperator1 = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if(month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if(strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}

//$('.asphalt_search_btn').click(function(){
//console.log(status);
//})
//遍历得到菜单
function getMenu(box) {
	$.ajax({
		type: "get",
		url: "../../js/set.json",
		async: true,
		dataType: 'json',
		success: function(data) {
			var datas = '';
			$.each(data.projectItem[1].project,function(i,v) {
				if(v.item_name == "沥青拌合站") {
					datas = v.item_project;
				}
			})
			console.log(datas);
			$.each(datas, function(i, n) {
				if(i == 0) {
					firstUrl = n.itemUrl;
				}
				var itemStr = '<li class="left" onclick="changeMenu(this)" data-src="' + n.itemUrl + '"data-status="'+n.status+'"><a href="javascript:void(0)" data-clickClass="' + n.clickCLass + '" data-class="' + n.class + '" class="' + n.class + '">' + n.itemName + '</a></li>'
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
	status = $(e).attr('data-status');
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
		if(jumpHref == 'warning_deviation.html') {
			$('#asphalt_search').hide();

		} else {
			$('#asphalt_search').show();
		}
		if(chart_timer) {
			clearInterval(chart_timer)
		}
		if(materials_echartimer) {
			clearInterval(materials_echartimer);
		}
		if(warning_echartimer) {
			clearInterval(warning_echartimer);
		}
		
	}
}
//从主页跳转后，判断子页面，左侧图标高亮,显示当前页面
function showMenu(url) {
	$('.left').each(function(i, n) {
		if($(n).attr('data-src') == url) {
			var obj = $(n).find('a');
			$(obj).removeClass($(obj).attr('data-class')).addClass($(obj).attr('data-clickClass'));
			//加载子页面
			loadHTML(url, contain);
		}
	})
}