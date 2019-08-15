$(document).ready(function() {
	var materialTypes = 0;
	//初期默认加载沥青菜单
	loadLeftBanner('pitch', $('.focusbtn'));
})
//加载子页面
function loadHTML(url, container, callback) {
	$.ajax({
		type: "get",
		url: url,
		dataType: 'html',
		beforeSend: function() {
			// container.html('<h1><i class="fa fa-cog fa-spin"></i> 加载中...</h1>');
		},
		success: function(data) {
			container.empty();
			container.html(data);
		},
		error: function(xhr, ajaxOptions, thrownError) {

		}
	});
};

//打开隐藏菜单
function isHidden(odiv) {
	var vdiv = document.getElementById(odiv);
	vdiv.style.display = (vdiv.style.display == 'block') ? 'none' : 'block';
}
//切换水泥 沥青左侧菜单栏
function loadLeftBanner(prams, obj) {
	$('#banners ul').empty();
	$('.hvr-bounce-to-top').removeClass('focusbtn');
	$(obj).addClass('focusbtn');
	$.ajax({
		type: "get",
		url: "../../js/mixStation/left_banner.json",
		dataType: '',
		success: function(data) {
			var banner_data = {};
			var bannerHtml = '';
			var dataHref = '';
			if (prams == "pitch") {
				banner_data = data.pitch;
				materialTypes = 0;
			} else if (prams == "cement") {
				banner_data = data.cement;
				materialTypes = 1;
			}
			$(banner_data).each(function(e, k) {
				if (k.data.length > 0) {
					bannerHtml += '<li><a href="javasript:;" data-clickClass="' + k.clickClass + '" data-class="' + k.class +
						'" class="' + k.class + '">' + k.name + '<span>></span></a>';
					bannerHtml += '<div class="down">'
					$(k.data).each(function(c, l) {
						bannerHtml += '<a href="javasript:;" data-href="' + l.dataUrl + '" onclick="changeChildrenMenu(this)">' +
							l.dataName +
							'</a>'
					})
					bannerHtml += '</div></li>'
				} else {
					bannerHtml += '<li><a href="javasript:;" onclick="changeMenu(this)" data-href="' + k.url +
						'" data-clickClass="' + k.clickClass + '" data-class="' + k.class + '" class="' + k.class + '">' + k.name +
						'</a></li>'
				}
			})
			$('#banners ul').append(bannerHtml);
			//初期默认加载第一个子页面
			loadHTML($('.leftnav_g ul li:first-child a').attr('data-href'), contain);
			$('.leftnav_g ul li:first-child a').removeClass($('.leftnav_g ul li:first-child a').attr('data-class')).addClass(
				$('.leftnav_g ul li:first-child a').attr('data-clickClass'));
		},
		error: function(xhr, ajaxOptions, thrownError) {
			console.log(xhr);
			console.log(ajaxOptions);
			console.log(thrownError);
		}
	});
}
//点击左侧菜单 切换右侧页面
function changeMenu(obj) {
	$('.leftnav_g ul li>a').each(function(i, n) {
		$(n).removeClass($(n).attr('data-clickClass')).addClass($(n).attr('data-class'));
	})
	var jumpHref = $(obj).attr('data-href');
	var clickCLass = $(obj).attr('data-clickClass');
	var CLass = $(obj).attr('data-class');
	$('.down a').each(function(i, n) {
		$(n).removeClass('click');
	})
	//加载子页面
	loadHTML(jumpHref, contain);
	$(obj).removeClass(CLass).addClass(clickCLass);
}
//点击左侧子菜单 切换右侧页面
function changeChildrenMenu(obj) {
	$('.leftnav_g ul li>a').each(function(i, n) {
		$(n).removeClass($(n).attr('data-clickClass')).addClass($(n).attr('data-class'));
	})
	$('.down a').each(function(i, n) {
		$(n).removeClass('click');
	})
	var jumpHref = $(obj).attr('data-href');
	var clickCLass = $(obj).parent('.down').siblings('a').attr('data-clickClass');
	var CLass = $(obj).parent('.down').siblings('a').attr('data-class');
	//加载子页面
	loadHTML(jumpHref, contain);
	$(obj).parent('.down').siblings('a').removeClass(CLass).addClass(clickCLass);
	$(obj).addClass('click');
}

/**
 * 获得url
 * @returns
 */
function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = window.location.protocol+"//"+window.location.host+pathName.substr(0,index+1);
    return result;
}

/**
 * 表单转json
 * @param form
 * @returns
 */
function formToJson(form){
    var o={};
    var a=form.serializeArray();
    $.each(a,function(){
    if(o[this.name]!=undefined){
        if(!o[this.name].push){
            o[this.name]=[o[this.name]];
        }
            o[this.name].push(this.value||"");
      }else{
            o[this.name]=this.value||"";
      }
         
    });
    return o;
}
//相差一个月
function getPreMonth(date) {
    var arr = date.split('-');
    var year = arr[0]; //获取当前日期的年份
    var month = arr[1]; //获取当前日期的月份
    var day = arr[2]; //获取当前日期的日
    var days = new Date(year, month, 0);
    days = days.getDate(); //获取当前日期中月的天数
    var year2 = year;
    var month2 = parseInt(month) - 1;
    if (month2 == 0) {
        year2 = parseInt(year2) - 1;
        month2 = 12;
    }
    var day2 = day;
    var days2 = new Date(year2, month2, 0);
    days2 = days2.getDate();
    if (day2 > days2) {
        day2 = days2;
    }
    if (month2 < 10) {
        month2 = '0' + month2;
    }
    var t2 = year2 + '-' + month2 + '-' + day2;
    return t2;
}
//相差三天
function getPreMonths(date) {
    var arr = date.split('-');
    var year = arr[0]; //获取当前日期的年份
    var month = arr[1]; //获取当前日期的月份
    var day = arr[2]; //获取当前日期的日
    var days = new Date(year, month, 0);
    days = days.getDate(); //获取当前日期中月的天数
    
    var days1 = new Date(year, month-1, 0);
    days1 = days1.getDate(); //获取上个月的天数
    var year2 = year;
    var month2 = parseInt(month);
    var day2 = parseInt(day)-3;
    
    if (day2 <= 0) {
    	month2 = parseInt(month) - 1;
    	if (month2 == 0) {
            year2 = parseInt(year2) - 1;
            month2 = 12;
        }
    	day2 =days1-(day2*-1);
    }
    

    if (month2 < 9) {
        month2 = '0' + month2;
    }
    if (day2 < 9) {
    	day2 = '0' + day2;
    }
    var t2 = year2 + '-' + month2 + '-' + day2;
    return t2;
}
//结束时间补0
function getDatePosition(date) {
    var arr = date.split('-');
    var year = arr[0]; //获取当前日期的年份
    var month = arr[1]; //获取当前日期的月份
    var day = arr[2]; //获取当前日期的日
   

    if (month < 10) {
    	month = '0' + month;
    }
    if (day < 10) {
    	day = '0' + day;
    }
    var t2 = year + '-' + month + '-' + day;
    return t2;
}
