var id = "";
var flag = "";
var emptyList = "";
var str = '';
var materielinfoList = "";//物料信息
var list;
$(function() {
	// 获取上页面传过来的值
	id = getUrlParam("id");
	str = id.split(',');
	if(id != null && id != ""){
		var _html = "";
		$.ajax({
			type: "post",
			url: "../../price/getEmptyPriceInfo.action",
			data:{"id":id},
			dataType: "json",
			async:false,
			success: function (data) {
				var len = str.length;
				var row;
				var result="";
				for(var i = 0;i<len;i++){
					list = data[i];
					console.log(list);
					var contractN = ""
						if(list.contractNumber==undefined){
							contractN = ""
						}else{
							contractN = list.contractNumber;
						}
					var orderNumberN = ""
						if(list.orderNumber==undefined){
							orderNumberN = ""
						}else{
							orderNumberN = list.orderNumber;
						}
					var SalePriceN = ""
						if(list.SalePrice==undefined){
							SalePriceN = ""
						}else{
							SalePriceN = list.SalePrice;
						}
					var UpdatePriceN = ""
						if(list.UpdatePrice==undefined){
							UpdatePriceN = ""
						}else{
							UpdatePriceN = list.UpdatePrice;
						}
					//物料信息
					$.ajax({
						type: "post",
						url: "../../price/getMaterielinfoList.action",
						data:{"id":list.orderDetailedId},
						dataType: "json",
						async:false,
						success: function (data) {
							for(var i=0;i<data.length;i++){
								materielinfoList = data[i].materielName+"-"+data[i].materielModel;
							}
						}
					});	
					row = i+1
					_html +='	 <tr>'
					_html +='	 <td><span style="text-align:center;display: block;">'+row+'</span></td>'
					_html +='    <td><span style="text-align:center;display: block;">'+contractN+'</span></td>'
					_html +='    <td><span style="text-align:center;display: block;">'+orderNumberN+'</span></td>'
					_html +='    <td><span style="text-align:center;display: block;">'+list.client+'</span></td>'
					_html +='    <td><span style="text-align:center;display: block;">'+list.customerAlias+'</span></td>'
					_html +='    <td><span style="text-align:center;display: block;">'+materielinfoList+'</span></td>'
					_html +='    <td><span style="text-align:center;display: block;">'+SalePriceN+'</span></td>'
					_html +='    <td><span style="text-align:center;display: block;">'+UpdatePriceN+'</span></td>'
					_html +='    </tr>';
					}
				$("#transportationList").append(_html);
			}
		});
	}
});

// 提交
function addEmpty () {
	var newPrice = $("#newPrice").val();
	$.ajax({
		type: "post",
		url: "../../price/getEmptyPriceInfo.action",
		data:{"id":id},
		dataType: "json",
		async:false,
		success: function (data) {
			var len = str.length;
			var row;
			var result="";
			for(var i = 0;i<len;i++){
				list = data[i];
				list.UpdatePrice = newPrice;
				console.log(list);
				$.ajax({
					type: "post",
					url: "../../price/addOfferrecord.action",
					data:list,
					dataType: "json",
					async:false,
					success: function (result) {
						console.log(result)
					}
				});
			}
		}
	});
	$.ajax({
		type: "post",
		url: "../../price/updateEmptyPriceInfo.action",
		data:{
			"id": id,
			"updatePrice":newPrice,
		},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data.code == "success"){
       		 swal({
						title : data.message,
						text : "",
						type : "success",
						confirmButtonText : '确定',
						cancelButtonFont : '微软雅黑',
					}, function() {
						window.parent.$.fancybox.close();
						window.location.reload();
					});
       	 }else{
       		 swal("操作失败", data.message, "error");
       	 }
		}
	});
}