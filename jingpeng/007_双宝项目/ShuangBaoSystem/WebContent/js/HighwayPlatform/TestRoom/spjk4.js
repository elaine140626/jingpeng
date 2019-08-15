var Token;
var videoParams = {};
var player = '';
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
	getToken()
	showVideoAll();
});

function controllerPage(){
	window.location.replace("../../page/item_pages/Jqindex.html?type=1");
}

function showVideoAll(){
	player = new EZUIPlayer({
		id: "playWind",
		url: A4+","+S1+","+S2,
		autoplay: true,
		accessToken: Token,
		decoderPath: '../../js/1.5/',
		width: 925,
		height: 360
	});
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
