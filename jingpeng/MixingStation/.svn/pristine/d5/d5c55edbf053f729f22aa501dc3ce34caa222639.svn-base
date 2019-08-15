$(function() {
	//试验汇总
	$.ajax({
		type : "POST",
		url : "../testInfo/getIndexSummary.html",
		data : {},
		dataType : "json",
		success : function(data) {
			$("#syzs")[0].innerHTML=data.data[0].sumCount;
			$("#zdcj")[0].innerHTML=data.data[0].sumAutoCount;
			$("#mysy")[0].innerHTML=data.data[0].sumBlindCount;
			
			$("#zdsysmc")[0].innerHTML=data.data[0].testRoomName;
			$("#zdsyzs")[0].innerHTML=data.data[0].autoCount;
			$("#zdhgs")[0].innerHTML=data.data[0].autoQualifiedCount;
			
			$("#mysysmc")[0].innerHTML=data.data[0].testRoomName;
			$("#mysyzs")[0].innerHTML=data.data[0].blindCount;
			$("#myhgs")[0].innerHTML=data.data[0].blindQualifiedCount;
		}
	});
});
