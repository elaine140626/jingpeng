// 获取的id
var id = "";
// 用户的id
var userId = "";
//材料种类列表
var MaterielTypeList;
//材料名称
var MaterielName;
//规格型号
var MaterielModel;

var generateNumber;

var feeddetailedcompanyEntityByIdFalg = true;

$(function(){
	
	uesrId = getUrlParam("userId");

	uesrInfo = getUserInfo(uesrId);
	
	// 材料种类列表
	$.ajax({
		type : "POST",
		url : "../../MaterialSetting/getMaterialSettingList.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) { 
			MaterielTypeList = data.data;
		}
	});
	//传所需的前缀英文简写
	$.ajax({
		type : "POST",
		url : "../../FeedCompany/getGenerateNumber.action",
		async:false,
		data : {"type":"GL"},
		dataType : "json",
		success : function(data) {
			generateNumber = data.generateNumber;
		}
	});
	MaterielName = getMaterielName();
//	MaterielModel = getMaterielModel(MaterielTypeList);
	// 获取传值id
	id = getUrlParam("id");
	
	if(id != null && id != ''){
		// 获取材料名称信息
		$.ajax({
			type : "post",
			url : "../../FeedCompany/getFeedCompanyById.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				// 画面赋值
				if(data != null){
//					SetFromValues($("#form1"), data);
					$("#feedCompanyNumber").val(data.feedCompanyNumber);
					$("#feedCompanyName").val(data.feedCompanyName);
					$("#contacts").val(data.contacts);
					$("#telephone").val(data.telephone);
					$("#remarks").val(data.remarks);
					var feeddetailedcompanyEntityList = data.feeddetailedcompanyEntityList;
					if(feeddetailedcompanyEntityList != undefined){
						if (feeddetailedcompanyEntityList.length > 0){
							for(var i=0;i<feeddetailedcompanyEntityList.length;i++){
								if (feeddetailedcompanyEntityByIdFalg) {
									//检测报告单销售订单明细新加一条
									addTable();
								}
								$("#feeddetailedcompanyId"+(i+1)).val(feeddetailedcompanyEntityList[i].id);
								for (var j = 0; j < MaterielTypeList.length; j++) {
									if(MaterielTypeList[j].id == feeddetailedcompanyEntityList[i].materielNameId){
										getMaterielModel(i+1,MaterielTypeList[j].materielName);
										$("#materielNameIds"+(i+1)).val(MaterielTypeList[j].materielName);
										break;
									}
								}
								$("#materielNameId"+(i+1)).val(feeddetailedcompanyEntityList[i].materielNameId);
								$("#unitPrice"+(i+1)).val(feeddetailedcompanyEntityList[i].unitPrice);
								$("#materielPlace"+(i+1)).val(feeddetailedcompanyEntityList[i].materielPlace);
								$("#manufactor"+(i+1)).val(feeddetailedcompanyEntityList[i].manufactor);
								$("#remark"+(i+1)).val(feeddetailedcompanyEntityList[i].remarks);
							}
						}	
					}
				}
			}
		});
	}else{
		$("#feedCompanyNumber").val(generateNumber);
		addTable();
	}
})

//获取材料名称
function getMaterielName(){
	$.ajax({
		type : "POST",
		url : "../../MaterialSetting/getMaterialSettingList.action",
		async:false,
		data : {type:1},
		dataType : "json",
		success : function(data) { 
			MaterielTypeLists = data.data;
		}
	});
	var html = "<option value='' selected='selected'>请选择</option>";
	for (var i = 0; i < MaterielTypeLists.length; i++) {
		html += "<option value='" + MaterielTypeLists[i].materielName + "'>"
		+ MaterielTypeLists[i].materielName + "</option>"							
	}
	return html;
}

//获取规格型号
function getMaterielModel(row,materielName){
	var html = "<option value='' selected='selected'>请选择</option>";
	for (var i = 0; i < MaterielTypeList.length; i++) {
		if(materielName == MaterielTypeList[i].materielName){
			html += "<option value='" + MaterielTypeList[i].id + "'>"
			+ MaterielTypeList[i].materielModel + "</option>"							
		}
	}
	$("#materielNameId"+row).html(html);
}


// 出库单检测报告单明细新增一条
function addTable(){
	var len = $('#tableList tr').length;
	var row;
	var result="";
	if(len==1){
		row = 1;
	}else{
		for(var i = 1;i<len;i++){
			row = i+1;
		}
	}
	result +="<tr>";
	result +="<td class='col-lg-1'>"+row+"</td>";
	result +="<td class='col-lg-1'><a class='del' onclick='removeTr1(this,"+ row +")' style='text-decoration:none' title='删除'> 删除</td>";
	result +="<td class='col-lg-1'><select class='select' style='height: 30px;' name='materielNameIds'  id='materielNameIds"+row+"' onchange='getMaterielModel("+row+",this.options[this.options.selectedIndex].text)'>"+MaterielName+"</select></td>";
	result +="<td class='col-lg-1'><select class='select' style='height: 30px;' name='materielNameId' id='materielNameId"+row+"'>"+MaterielModel+"</select></td>";
	result +="<td class='col-lg-2'><input class='input-text' maxlength='9' onchange='clearNoDecimal(this,8,2)' name='unitPrice' id='unitPrice"+row+"'/></td>";
	result +="<td class='col-lg-2'><input class='input-text' maxlength='50' name='materielPlace' id='materielPlace"+row+"' type='text' maxlength='30' /></td>";
	result +="<td class='col-lg-2'><input class='input-text' maxlength='50' name='manufactor' id='manufactor"+row+"' maxlength='30'/></td>";
	result +="<td class='col-lg-2'><input class='input-text' maxlength='150' name='remark' id='remark"+row+"' maxlength='30'/><input class='input-text' name='feeddetailedcompanyId'  id='feeddetailedcompanyId"+row+"' value = '0' type='hidden'/></td>";
	//result +="<td class='col-lg-2'></td>";
	result +="</tr>";
	$("#tableList").append(result);
}


function save(){
//	var params = formToJson($("#form1"));
	// 供料单位编号
	if($("#feedCompanyNumber").val() == ''){
		layer.alert("供料单位编号不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// "供料单位名称
	if($("#feedCompanyName").val() == ''){
		layer.alert("供料单位名称不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 联系人
//	if($("#contacts").val() == ''){
//		layer.alert("联系人不能为空!", {
//			icon: 2,
//			title: "提示"
//		});
//		return;
//	}
	
	// 联系人电话
//	if($("#telephone").val() == ''){
//		layer.alert("联系人电话不能为空!", {
//			icon: 2,
//			title: "提示"
//		});
//		return;
//	}
	var params = {
			"feedCompanyNumber":$("#feedCompanyNumber").val(),
			"feedCompanyName":$("#feedCompanyName").val(),
			"contacts" : $("#contacts").val(),
			"telephone" : $("#telephone").val(),
			"remarks" : $("#remarks").val()
	};
	if (id == null){
		// 新增
		params.id = 0;
		params.creater=userId;
	} else {
		//修改
		params.id = id;
		params.reviser=userId;
	}
	var feeddetailedcompanyList = [];
	var i = 0;
	var message = "";
	$('#tableList tbody').find('tr').each(function () { 
		var feeddetailedcompany = {};
		$(this).find('td').each(function () {				
			$(this).find('input').each(function () {                         //获取td中input的值
	           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
	              var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name");  //获取该name的属性名称 
	              /*if (idValue !='' && name == "unitPrice") {
	            	  feeddetailedcompany.unitPrice = idValue;
	              }else if(idValue !='' && name == "materielPlace"){
	            	  feeddetailedcompany.materielPlace = idValue;
	              }else if (idValue !='' && name == 'manufactor'){
	            	  feeddetailedcompany.manufactor = idValue;
            	  }else if(name == "feeddetailedcompanyId"){
            		  feeddetailedcompany.feeddetailedcompanyId = idValue;
            	  }else if(name == "remark"){
            		  feeddetailedcompany.remark = idValue;
            	  }else {
            		  if(name == 'unitPrice'){
            			  message += ("\n")+"第"+i+"单价（元）要求不能为空";
            		  }
            		  if(name == 'materielPlace'){
            			  message += ("\n")+"第"+i+"材料产地不能为空";
            		  }
            		  if(name == 'manufactor'){
            			  message += ("\n")+"第"+i+"生产厂家不能为空";
            		  }
            	  }  */  
	              if (name == "unitPrice") {
	            	  feeddetailedcompany.unitPrice = idValue;
	              }else if(name == "materielPlace"){
	            	  feeddetailedcompany.materielPlace = idValue;
	              }else if (name == 'manufactor'){
	            	  feeddetailedcompany.manufactor = idValue;
            	  }else if(name == "feeddetailedcompanyId"){
            		  feeddetailedcompany.feeddetailedcompanyId = idValue;
            	  }else if(name == "remark"){
            		  feeddetailedcompany.remark = idValue;
            	  }
	           }
	        })
	        $(this).find('select').each(function () {                        //获取td中input的值
	           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		          var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name");  //获取该name的属性名称 
	              if (idValue !='' && name == "materielNameId") {
	            	  feeddetailedcompany.materielNameId = idValue;
	              }else if (idValue !='' && name == "materielNameIds") {
	            	  feeddetailedcompany.materielNameIds = idValue;
	              }else {
	            	  if (name == 'materielNameId'){
	            		  message += ("\n")+"第"+i+"材料名称不能为空";
	            	  }
	            	  if (name == 'materielNameIds'){
	            		  message += ("\n")+"第"+i+"规格型号不能为空";
	            	  }
	              } 
	           }
	        }) 
	        
		})				  
		feeddetailedcompanyList.push(feeddetailedcompany);	
		i++;
	})
	if (message!=""){
		layer.alert("供料单位明细"+message, {
			icon: 2,
			title: "提示"
		});
		return;
	}
	params.feeddetailedcompanyList = JSON.stringify(feeddetailedcompanyList);
	// 保存操作
	$.ajax({
		type: 'post',
		url: '../../FeedCompany/insertFeedCompany.action',
		async:false,
		data:params,
		dataType: 'json',
		success: function(data){
			if (data.code == 'success'){
				// 操作成功
				layer.alert(data.message, {
					icon: 1,
					title: "提示"
				},function(){
					window.parent.location.reload();
				});
				feeddetailedcompanyEntityByIdFalg = false;
			} else {
				// 操作失败
				layer.alert(data.message, {
					icon: 2,
					title: "提示"
				});
			}			
		}
	});	
}

//删除出库单检测报告单明细
function removeTr1(obj,data){
	if($("#feeddetailedcompanyId"+data).val() == 0 ){
		var tr= $(obj).parent().parent();
	    tr.remove();
	    var len = $('#tableList tr').length;
	    for (var i = 0; i < len; i++) {
			$('#tableList tr:eq('+i+') td:first').text(i);		
		}	
	}else{
		var params = {
				"feeddetailedcompanyId":$("#feeddetailedcompanyId"+data).val(),
				"reviser" : userId
		};
		$.ajax({
	        type: "post",
	        url: '../../FeedCompany/deleteFeeddetailedcompany.action',
	        async:false,
	        data:params,
	        dataType: "json",
	        success: function (data) {
	        	var tr= $(obj).parent().parent();
	    	    tr.remove();
	    	    var len = $('#tableList tr').length;
	    	    for (var i = 0; i < len; i++) {
	    			$('#tableList tr:eq('+i+') td:first').text(i);		
	    		}
	      }
		});
	}
}