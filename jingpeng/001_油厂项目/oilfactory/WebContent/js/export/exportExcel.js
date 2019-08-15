$(function() {
	$(".exportBtn").click(function () {
		$(".table2excel").table2excel({
			exclude: ".noExl",
			name: "Excel Document Name",
			filename: "myFileName" + new Date().toISOString().replace(/[\-\:\.]/g, ""),
			fileext: ".xls",
			exclude_img: true,
			exclude_links: true,
			exclude_inputs: true
		});
	})
});