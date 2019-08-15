var baseUrl = getContextPath();
$(function() {
	 $.ajax({
	        type: "post",
	        url: "../Andr/getValue.html",
	        data:{},
	        async:false,
	        dataType: "json",
	        success: function (data) {
	            $(".userid").html(data.userName)
	        }
	 })
	getInfo();
})

function getInfo() {
	$.ajax({
		type : "POST",
		url : baseUrl+ "/Platform/getInfo.html",
		data : {},
		dataType : "json",
		success : function(data) {
			var list = data.list;
			var html = "";
			if(list != null && list != "" && list.length > 0) {
				for(var i = 0; i < list.length; i++) {
					var a = Number(list[i].HeGePanShu) * 100 /Number(list[i].TotalCount)
					var b = "";
					if(isNaN(a)){ 
						b = "0.00%";
					} else {
						b = a.toFixed(2) +"%";
					}
					
					html += "<tr>"
							+"<td>"+list[i].Org_Name+"</td>"
							+"<td>生产总盘数：</td>"
							+"<td align='left'>"+list[i].TotalCount+"</td>"
							+"<td align='right'>合格盘数：</td>"
							+"<td>"+list[i].HeGePanShu+"</td>"
							+"<td align='right'>合格率：</td>"
							+"<td>"+b+"</td>"
							+"</tr>";
				}
			}
			$("#tab").html(html);
			var lq = data.lq;
			var sn = data.sn;
			var sw = data.sw;
			var myDate = new Date();
			var year = myDate.getFullYear();
			var month = myDate.getMonth()+1;
			var day = myDate.getDate();
			$("#lqdate").html(month+"-"+day+"<i>"+year+".</i>");
			$("#sndate").html(month+"-"+day+"<i>"+year+".</i>");
			$("#swdate").html(month+"-"+day+"<i>"+year+".</i>");
			
			$("#lqtotal").html(lq.totalCount);
			$("#lqz").html(lq.mix);
			$("#lqy").html(lq.yongLiang);
			$("#sntotal").html(sn.totalCount);
			$("#snz").html(sn.mix);
			$("#sny").html(sn.yongLiang);
			$("#swtotal").html(sw.totalCount);
			$("#swz").html(sw.mix);
			$("#swy").html(sw.yongLiang);
		}
	});
}

/*function vedio() {
	 var a = new   ActiveXObject("wscript.shell");
	 //var strPath = "file:///D:\\Program Files (x86)\\Ezviz Studio\\EzvizProtect.exe"
	 var strPath = "file:///D:\\Program Files\\WeChat\\WeChat.exe";
     a.run(strPath);
	window.location.href=baseUrl+ "/Platform/vedio.html";
}*/