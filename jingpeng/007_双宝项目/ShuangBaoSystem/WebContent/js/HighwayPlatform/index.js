var user;
$(function() {
	$.ajaxSetup({ 
	    statusCode: {
	        401: function() { 
	            window.location.href="../page/centerLogin/centerLogin.html"
	        }, 
	        403: function() { 
	        	window.location.href="../page/centerLogin/centerLogin.html"
	        }
	    }
	})
	// 首页初始化加载
	$.ajax({
		type : "POST",
		url : "../js/set.json",
		data : {},
		dataType : "json",
		ansyc:false,
		success : function(data) {
			var index_block = $('#index_block');
			var slide = data.slides;
			var test_block = data.projectItem[0];
			var mix_block = data.projectItem[1];
			var index_block_text = data.index_block_text;
			var index_block_img = data.index_block_img;
			$('html head title').html(data.projectName);

			// 遍历轮播
			var html = '<div class="swiper-wrapper">';
			$.each(slide,
					function(i, n) {
						html += '<div class="swiper-slide"><img src="' + n
								+ '"></div>';
					})
			html += '</div>';
			$('#swiper1').append(html);
			// 轮播图初始化
			var swiper1 = new Swiper('#swiper1', {
				autoplay : 3500,
				loop : true,
			});
			loadmodel(test_block.project)
			//test_block.project.length
			
			/*if (mix_block.project.length > 0) {
				$.each(mix_block.project, function(i, n) {
					var str = "";
					str += '<div class="col-md-3">';
					if(n.page_url=="asphalt/asphalt.html"){
						//沥青跳转
						str += '	<a href="' + n.page_url + '"onclick="return isJumpTestOrgLi()">';
					}else if(n.page_url=="cement/cement.html"){
						//水泥跳转
						str += '	<a href="' + n.page_url + '"onclick="return isJumpTestOrgNi()">';
					}else{
						//水稳跳转
						str += '	<a href="' + n.page_url + '"onclick="return isJumpTestOrgWen()">';
					}
					str += '		<div class="featured-box">';
					str += '			<i class="fa fa-dot-circle-o fa-2x"></i>';
					str += '			<div class="text">';
					str += '			<h3>' + n.item_name + '</h3>' + n.item_name
							+ '信息';
					str += '			</div>';
					str += '		</div>';
					str += '	</a>';
					str += '</div>';
					$('#testOrMixBlock').append(str);
				})
			}*/
			// 调取block模块
			loadHTML(test_block.index_block, index_block);
			// loadHTML(mix_block.index_block, index_block);
			$('.index_pic').html(
					'<img src="' + index_block_img
							+ '" class="img-center" alt="">');
			$('.about_p').html(index_block_text);

		}
	});
	// 试验汇总
	$.ajax({
		type : "POST",
		url : "../index/getIndexSummary.action",
		data : {},
		ansyc:false,
		dataType : "json",
		success : function(data) {
			$("#syzs")[0].innerText = data.data[0].sumCount;
			$("#zdcj")[0].innerText = data.data[0].sumAutoCount;
//			$("#mysy")[0].innerText = data.data[0].sumBlindCount;

			$("#zdsysmc")[0].innerText = data.data[0].testRoomName;
			$("#zdsyzs")[0].innerText = data.data[0].autoCount;
			$("#zdhgs")[0].innerText = data.data[0].autoQualifiedCount;

//			$("#mysysmc")[0].innerText = data.data[0].testRoomName;
//			$("#mysyzs")[0].innerText = data.data[0].blindCount;
//			$("#myhgs")[0].innerText = data.data[0].blindQualifiedCount;
		}
	});
	user = getUser()
	var str = user.userOrgDetailed;//获取拌合转权限字符串
	//判断沥青混合料权限
	var lq_1 = RegExp(/5/);//判断字符串是否包含orgId为5
	var lq_2 = RegExp(/7/);//判断字符串是否包含orgId为7
	var lq_3 = RegExp(/9/);//判断字符串是否包含orgId为9
	//判断水泥混凝土权限
	var sn_1 = RegExp(/11/);//判断字符串是否包含orgId为11
	//判断水泥稳定土权限
	var sw_1 = RegExp(/6/);//判断字符串是否包含orgId为6
	var sw_2 = RegExp(/8/);//判断字符串是否包含orgId为8
	var sw_3 = RegExp(/10/);//判断字符串是否包含orgId为10
	//有一个条件满足即调用沥青拌合站生产统计方法
	if(lq_1.test(str)== true || lq_2.test(str)== true || lq_3.test(str) == true){
		getIndexLq();
	}
	//有一个条件满足即调用水泥拌合站生产统计方法
	if(sn_1.test(str)== true){
		getIndexSn();
	}
	//有一个条件满足即调用水稳拌合站生产统计方法
	if(sw_1.test(str)== true || sw_2.test(str)== true || sw_3.test(str) == true){
		getIndexSw();
	}
});
//加载试验室拌合站     按钮    配置板块
function loadmodel(datas){
	var str = "";
	// 遍历banner 下的按钮
	if (datas.length > 0) {
		$.each(datas, function(i, n) {
			if (i <= datas.length - 1) {
				str += '<div class="swiper-slide">';
				str += '<a href="item_pages/testbed.html?viewUrl=' + n.itemUrl + '" onclick="return isJumpTest()" class="stars"><span class="star"></span><span>' + n.itemName + '</span></a>';
				str += '</div>'

			}
		});
		str += '<div class="swiper-slide">';
		str += '<a href="item_pages/Jqindex.html" target="_blank" class="stars"><span class="star"></span><span>屏幕展示</span></a>';
		str += '</div>'
		str += '<div class="swiper-slide">';
		str += '<a href="item_pages/spjk.html" target="_blank" class="stars"><span class="star"></span><span>视频监控</span></a>';
		str += '</div>'

		$('#testOrMixBlock .swiper-wrapper').append(str);
		var swiper = new Swiper('#testOrMixBlock.swiper-container', {
	        pagination: '.swiper-pagination',
	        slidesPerView: 4,
	        paginationClickable: true,
	        spaceBetween: 30,
	        loop : true,
	    });
	}
}
// 加载子页面
function loadHTML(url, container) {
	$.ajax({
		type : "get",
		url : url,
		ansyc:false,
		dataType : 'html',
		beforeSend : function() {
		},
		success : function(data) {
			container.append(data);
		},
		error : function(xhr, ajaxOptions, thrownError) {

		}
	});
};
function outLogin() {
	$.ajax({
		type : "POST",
		url : "../UserInfo/delUserSession.action",
		data : {},
		dataType : "json",
		success : function(data) {
			if (data.code == 'error') {
				// 失败信息
				swal("操作失败", data.message, data.code);
			} else {
				// 首页
				$(location).attr('href', '../page/centerLogin/centerLogin.html');
			}
		}
	});
}

function getUser() {
	var userInfo = '';
	$.ajax({
		type : "post",
		url : "../UserInfo/getPtUserInfo.action",
		data : {},
		async : false,
		dataType : "json",
		success : function(data) {
			userInfo = data;
			// 获取当前登录人做显示
		}
	});
	return userInfo;
}
function getIndexLq() {
	$.ajax({
		type : "POST",
		url : "../PlatformCementData/getIndexLq.action",
		data : {},
		dataType : "json",
		success : function(data) {
			console.log(data);
			data= data.lq;
			$("#lqtotal").append(data.TotalCount);
			$("#lqz").append(data.ZongChanLiang);
			$("#lqy").append(data.LQYongLiang);
		}
	});
}
function getIndexSn() {
	$.ajax({
		type : "POST",
		url : "../PlatformCementData/getIndexSn.action",
		data : {},
		dataType : "json",
		success : function(data) {
			console.log(data);
			data= data.sn;
			$("#sntotal").append(data.TotalCount);
			$("#snz").append(data.ZongChanLiang);
			$("#sny").append(data.SNYongLiang);
		}
	});
}
function getIndexSw() {
	$.ajax({
		type : "POST",
		url : "../PlatformCementData/getIndexSw.action",
		data : {},
		dataType : "json",
		success : function(data) {
			console.log(data);
			data= data.sw;
			$("#swtotal").append(data.TotalCount);
			$("#swz").append(data.ZongChanLiang);
			$("#swy").append(data.SWYongLiang);
		}
	});
}
function isJumpTest() {
	if (user.userTestDetailed == "") {
		swal("操作失败", "你没有该权限", "info");
		return false;
	} else {
		return true;
	}
}

function isJumpTestOrgLi() {
	arr=user.userOrgDetailed.split(',');
		if ($.inArray("5", arr)>-1||$.inArray("7", arr)>-1||$.inArray("9", arr)>-1) {
			return true;
		} else {
			swal("操作失败", "你没有该权限", "info");
			return false;
		}
}
function isJumpTestOrgNi() {
	arr=user.userOrgDetailed.split(',');
	if ($.inArray("11", arr)>-1) {
		return true;
	} else {
		swal("操作失败", "你没有该权限", "info");
		return false;
	}
}
function isJumpTestOrgWen() {
	arr=user.userOrgDetailed.split(',');
	if ($.inArray("6", arr)>-1||$.inArray("8", arr)>-1||$.inArray("10", arr)>-1) {
		return true;
	} else {
		swal("操作失败", "你没有该权限", "info");
		return false;
	}
}
