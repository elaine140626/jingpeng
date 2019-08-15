/*底部鼠标经过*/
var mst;
jQuery(".Qr-codee li").hover(function(){
	var curItem = jQuery(this);
	mst = setTimeout(function(){//延时触发
		curItem.find("div").slideDown('fast');
		mst = null;
	});
}, function(){
	if(mst!=null)clearTimeout(mst);
	jQuery(this).find("div").slideUp('fast');
});