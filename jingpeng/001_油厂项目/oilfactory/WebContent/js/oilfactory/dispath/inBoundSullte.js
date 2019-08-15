var id = "";

$(function() {
	// 获取上页面传过来的值
	id = getUrlParam("id");
	if(id != null && id != ""){
		$("#outBoundId").val(id);
		var _html = "";
		$.ajax({
			type: "post",
			url: "../../instore/getOutBoundWeight.action",
			data:{id: id},
			dataType: "json",
			async:false,
			success: function (data) {
				if (data[0] && data.length > 0) {
					if (data[0].GrossWeight) {
						$("#grossWeight").val(data[0].GrossWeight / 1000);
					} 
					if (data[0].TareWeight) {
						$("#tareWeight").val(data[0].TareWeight / 1000);
					}
					if (data[0].Suttle) {
						$("#sullte").val(data[0].Suttle / 1000);
					}
					if (data[0].FactoryTime) {
						$("#factoryTime").val(data[0].FactoryTime);
					}
				}
			}
		});
	}
});

// 提交
function addEmpty () {
	var _url = "../../instore/updateOutBoundWeight.action"
	$.ajax({
		type: "post",
		url: _url,
		data:{
			id: id,
			grossWeight: $("#grossWeight").val() * 1000,
			tareWeight: $("#tareWeight").val() * 1000,
			sullte: $("#sullte").val() * 1000,
			factoryTime: $("#factoryTime").val()
		},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data.code == 'success'){
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

//只能入数字和小数点  
function keepNumOrDecimal(obj){ 
  obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
  obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数 
  if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
      obj.value= parseFloat(obj.value); 
  } 
} 

//最后一位不是小数点
function checkPoint(obj){
	keepNumOrDecimal(obj);
	if (obj.value.charAt(obj.value.length - 1)=='.'){
		obj.value = obj.value.substring(0,obj.value.length - 1);
	}		
}

//净重计算
function getSuttle(){
	if ($("#grossWeight").val() && $("#tareWeight").val()) {
		$("#sullte").val(($("#grossWeight").val() * 1 - $("#tareWeight").val() * 1) < 0 ? 0 : ($("#grossWeight").val() * 1 - $("#tareWeight").val() * 1))
	}		
}