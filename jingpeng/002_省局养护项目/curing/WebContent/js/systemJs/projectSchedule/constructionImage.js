// 当前登录用户id
var userId = ''; 
// 主键id
var id = '';
// 项目id
var ProjectId = '';
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		userId = userInfo.id;
	}
	id = getUrlParam("id");
	ProjectId = getUrlParam("ProjectId");
	// 修改的场合
	if (id != null && id != ""){
		// 获取合同信息
		$.ajax({
			type : "post",
			url : "../../Construction/selectFileList.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				var fileList = data.data;
				if(fileList != 'error'){
					for (var i = 0; i < fileList.length; i++) {
						var html = "";
						html = ["<li id="+fileList[i].name+" name="+fileList[i].prefix+" onclick='imageChick(this)'><img src=\"/image/"+fileList[i].prefix+"/"+fileList[i].name+"\"/></li>"].join("");
						$("#imageList").append(html)
					}
				}else{
					layer.msg(data.message, {
						icon: 2,
						time: 1000
					});
				}
			}
		});
	}
})

function imageChick(e){
	  $(e).toggleClass('selected');
	  if ($('li.selected').length == 0)
	    $('.select').removeClass('selected');
	  else
	    $('.select').addClass('selected');
	  counter();
}

// all item selection
$('.select').click(function () {
  if ($('li.selected').length == 0) {
    $('li').addClass('selected');
    $('.select').addClass('selected');
  }
  else {
    $('li').removeClass('selected');
    $('.select').removeClass('selected');
  }
  counter();
});

// number of selected items
function counter() {
  if ($('li.selected').length > 0)
    $('.send').addClass('selected');
  else
    $('.send').removeClass('selected');
  $('.send').attr('data-counter',$('li.selected').length);
}

function delOrDownImage(count){
	var imageNameArray=[];
	var imagePrefix;
	var imageNameStringList = "";
	var url;
	 if ($('li.selected').length <= 0){
		 if(count == 1){
			 //alert("请选择一个删除文件")
			 swal("删除失败", "请选择一个删除文件")
		 }else{
			 swal("下载失败", "请选择一个下载文件")
		 }
		 return;
	 }
	$('#imageList').find('li.selected').each(function () { 
		var imageNameList = {}
		var imageName =  $(this).attr("id");
		imagePrefix =  $(this).attr("name");
		imageNameList.imageName = imageName
		imageNameArray.push(imageNameList)
		imageNameStringList += imageName+","
	})
	var imageNamePrams = JSON.stringify(imageNameArray);
	if(count == 1){
		url =  "../../Construction/delUploadfile.action";
	}else{
		window.location.href = "../../Construction/downUploadfile.action?imageNamePrams=" + imageNameStringList+"&imagePrefix="+imagePrefix;
		//window.open("../../ProjectInfoSummary/downUploadfile.action?imageNamePrams=" + imageNameStringList+"&imagePrefix="+imagePrefix)
	}
	// 删除所选图片信息
	$.ajax({
		type : "post",
		url : url,
		data :{
			"imageNameList":imageNamePrams,
			"imagePrefix":imagePrefix
		},
		dataType : "json",
		success : function(data) {
			if(data.code == "error"){
				// 操作失败
				 swal("操作失败")
				return;
			}else{
				// 操作成功
				swal({
	 				title: "操作成功",
	 				text: "",
	 				type: data.code,
	 				confirmButtonText: '确定',
	 				cancelButtonFont: '微软雅黑',
	 			},
	 			function (){
	 				window.parent.location.reload();
	 			});
			}
		}
	});
}


