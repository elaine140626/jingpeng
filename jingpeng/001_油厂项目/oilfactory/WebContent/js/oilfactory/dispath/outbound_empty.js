var id = "";
var flag = "";
var emptyList = "";

$(function() {
	// 获取上页面传过来的值
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	emptyList = getUrlParam("emptyList");
	if(id != null && id != ""){
		$("#outBoundId").val(id);
		var _html = "";
		$.ajax({
			type: "post",
			url: "../../outbound/getEmptyOutboundInfo.action",
			data:{},
			dataType: "json",
			async:false,
			success: function (data) {
				_html += '<tr><td></td><td style="width: 100px;text-align:center;">订单编号</td><td style="width: 100px;text-align:center;">'+
					'客户名称</td><td style="width: 100px;text-align:center;">车主信息</td><td style="width: 100px;text-align:center;">车主电话'+
					'</td><td style="width: 100px;text-align:center;">车牌号码</td></tr>';
				for (var i = 0; i < data.length; i++) {
					_html += '<tr><td><input type="checkbox" name="emptyId" id="'+ data[i].Serial_ID +'" value="' + data[i].Serial_ID + '">' + (i + 1)  + '.</td><td>' + 
							 data[i].Serial_ID + '</td><td>' + data[i].Client + '</td><td>' + data[i].CarOwner + '</td><td>' + data[i].CarOwnerTelephone +
							 '</td><td>' + data[i].PlateNumber + '</td></tr>';
				}
				$("#emptyList").html(_html);
				if (flag == "Y") {
					var emptyIds = emptyList.split(',');
					for (var i = 0; i < emptyIds.length; i++) {
						$('#'+emptyIds[i]).attr('checked',true);
					}
				}
			}
		});
	}
});

// 提交
function addEmpty () {
	var id = $("#outBoundId").val();
	var checked = $("input[name='emptyId']:checked");
	var ids = "";
	checked.each(function(){
		ids += $(this).val()+",";
	})
	var _url = "";
	if (flag == "N") {
		_url += "../../outbound/insertEmptyOutBound.action"
	} else {
		_url += "../../outbound/updateEmptyOutBound.action"
	}
	$.ajax({
		type: "post",
		url: _url,
		data:{
			id: id,
			ids: ids.substring(0, ids.length - 1)
		},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data.code == 'success'){
				var param = {};
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