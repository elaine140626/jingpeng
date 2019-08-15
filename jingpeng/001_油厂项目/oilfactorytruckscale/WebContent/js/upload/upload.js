// 文件上传
$(function () {
    $('#fileupload').uploadFile({
		url:"../../file/fileUpload.action",
		fileName:"upload",
		multiple : false,
//		allowedTypes : allowedTypes,
//		acceptFiles: acceptFiles,
		showCancel: false,
	    showAbort: false,
	    showDone: false,
	    showDelete: false,
	    showError: false,
	    showStatusAfterSuccess: false,
	    showStatusAfterError: false,
	    showFileCounter: false,
		dragDrop:false,
		uploadBtnwidth:'60px',
		onSuccess:function (data,info) {
			layer.msg('上传成功');
			$("#file").html(info.fileName);
			$("#downloadBtn").show();
			$("#deleteBtn").show();
			$("#files").val(info.fileName);
//			$("#file").bind("mouseover", function(){
//				$("#deleteBtn").show();
//			});
//			$("#file").bind("mouseout", function(){
//				$("#deleteBtn").hide();
//			});
		}
	});
});

// 文件下载
function downloadFile () {
	$.ajax({
		url : "../../file/checkExist.action",
		async : false,
		dataType:'json',
		data : {
			"filename" : $("#files").val()
		},
		type : "post",
		success : function(_info) {
			if (_info) {
				window.location.href = "../../file/fileDownload.action?filename=" + $("#files").val();
			} else {
				layer.alert("文件已删除");
			}
		}
	});
	
}

function preview(){
	$("#sd").attr('href',"../../file/preview.action?fileName=" + $("#files").val());
}

function deleteFile (tips) {
	if (tips == "" || tips == null) {
		tips = '是否要删除附件？删除后将无法找回！';
	}
	layer.confirm(tips , {
		btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			url : "../../file/fileDelete.action",
			async : false,
			dataType:'json',
			data : {
				"filename" : $("#files").val()
			},
			type : "post",
			success : function(_info) {
				layer.msg('删除成功');
				$("#file").html("");
				$("#downloadBtn").hide();
				$("#deleteBtn").hide();
				$("#files").val("");
				return true;
			}
		});
	});
}