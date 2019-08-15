var Token;
var videoParams = {};
var player = '';
var flag;
//A1力学室(C64395300),A1水泥室(C64396413),A1水泥混凝土室
var A1 = 'ezopen://open.ys7.com/C64395300/1.live,ezopen://open.ys7.com/C64396413/1.live,ezopen://open.ys7.com/D30504968/1.live';
//
var A2 = '';
//A3力学室(万能型）,A3水泥混凝土室,A3沥青室(D28721299),A3沥青混凝土室,A3力学室（2000型）
var A3 = 'ezopen://open.ys7.com/D28721915/1.live,ezopen://open.ys7.com/D28721521/1.live,ezopen://open.ys7.com/D28721299/1.live,ezopen://open.ys7.com/D28721518/1.live,ezopen://open.ys7.com/D28721621/1.live';
var A4 = '';
//S1力学室(D28722144),S1水泥室(D28721843),S1水泥混凝土室,S1沥青室(D28721849),S1沥青混凝土室
var S1 = 'ezopen://open.ys7.com/D28722144/1.live,ezopen://open.ys7.com/D28721843/1.live,ezopen://open.ys7.com/D28722066/1.live,ezopen://open.ys7.com/D28721849/1.live,ezopen://open.ys7.com/D28721840/1.live';
//S2力学室(C64396348),S2水泥室(D30506025),S2水泥混凝土室,S2沥青室(C64395814),S2沥青混凝土室(D30504838)
var S2 = 'ezopen://open.ys7.com/C64396348/1.live,ezopen://open.ys7.com/D30506025/1.live,ezopen://open.ys7.com/D30505027/1.live,ezopen://open.ys7.com/C64395814/1.live,ezopen://open.ys7.com/D30504838/1.live';
$(function() {
	$("#showVideo").hide();
	getToken()
	flag = getUrlParam('type')
	if(flag == 1){
		$("#showSelect").hide();
		showVideoAll(A1+","+A2+","+A3);
	}
});

function controllerPage(){
	window.location.replace("../../page/item_pages/Jqindex3.html");
}

function getVideo(){
	$("#playWind").show();
	$("#controllerPage").show();
	var videoType = $("#typeList").val();
	$("#showSelect").hide();
	switch (videoType) {
	case '0':
		showVideoAll(A1+","+A2+","+A3);
		break;
	case '1':
		showVideoAll(A1,3);
		break;
	case '2':
		showVideoAll(A2,3);
		break;
	case '3':
		showVideoAll(A3,3);
		break;
	case '4':
		showVideoAll(A4,3);
		break;
	case '5':
		showVideoAll(S1,3);
		break;
	case '6':
		showVideoAll(S2,3);
		break;

	default:
		break;
	}
}

function showVideoAll(strss,count){
	$("#spjkTitle").html(["视频监控<a href=\"../../page/item_pages/Jqindex.html\" style=\"float: right;font-size: 20px\">重新选择</a>"].join(""))
	if(count == 3){
		player = new EZUIPlayer({
			id: "playWind",
			url: strss,
			autoplay: true,
			accessToken: Token,
			decoderPath: '../../js/1.5/',
			splitBasis:count,
			width: 925,
			height: 360
		});
		setTimeout('updateCss(1)',5000);
	}else{
		player = new EZUIPlayer({
			id: "playWind",
			url: strss,
			autoplay: true,
			accessToken: Token,
			decoderPath: '../../js/1.5/',
			width: 925,
			height: 360
		});
		setTimeout('updateCss(0)',5000);
	}
	
}

function getToken() {
	var params = {
		"appKey" : "db22c9f57ec84651b3d73f6fb6cd059d",
		"appSecret" : "768602a8cea4c08f37ea21c5110149c3"
	};
	videoParams.AppKey = 'db22c9f57ec84651b3d73f6fb6cd059d';
	$.ajax({
		url : "../../FluorescentCloud/getAccessToken.action?random="
				+ new Date().getTime(),
		async : false,
		data : params,
		dataType : "json",
		success : function(data) {
			//var obj = JSON.parse(data);
			var obj = data;
			Token = obj.data.accessToken;
			console.log(Token);
			videoParams.AccessToken = Token;
		},
		error : function(e) {
			alert(e)
		}
	});
}
function updateCss(count){
	if(count == 0){
		/*var css = {  
			    'width': '308px',  
			    'height': '120px !important'
			};*/
		$(".parent-wnd >div").each(function(){
			$(this).attr('style','float:left; background-color: #4C4B4B; position: relative;width:20% !important;height: calc(100%/3) !important');
		});
	}else{
		/*var css = {  
			    'width': '185px !important',  
			    'height': '180px !important'
			};*/
		$(".parent-wnd >div").each(function(){
			$(this).attr('style','float:left; background-color: #4C4B4B; position: relative;;width:33% !important;height: calc(100%/2) !important');
		});
	}
/*	$(".parent-wnd >div").each(function(){
		$(this).css(css)
	});*/
	//$(".parent-wnd >div").css(css)
}