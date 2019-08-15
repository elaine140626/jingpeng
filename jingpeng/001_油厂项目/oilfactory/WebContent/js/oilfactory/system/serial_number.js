var baseUrl = "";

var creater = "";

$(function(){
	baseUrl = getContextPath();
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		creater = userInfo.id;
	}
	//查询所有
	getAllPrefix();
});

//操作数据
function add(){
	//主键
	var id = $("#zj").val();
	var url;
	var params = {};
	var prefixList = [];
	var i = 0;
	if(id == null || id == ""){
		//新增
		$('#tableList1').find('tr').each(function () { 
			var prefix = {};
			$(this).find('td').each(function () {				
				$(this).find(':checkbox').each(function () {                         //获取td中input的值
		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		              var name=$(this).attr("name");  //获取该name的属性名称 
		              var idValue =$("input:checkbox[id='"+ name + i +"']:checked").val();//获取属性id对应的属性值
		              if (idValue == undefined) {
		            	  idValue = 1;
		            	  }
		              if (idValue !=''){
		            	  eval("prefix."+name+"=" + idValue);
		              }             
		           }
		        })
	        $(this).find(':text').each(function () {                         //获取td中input的值
		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		              var idValue = $(this).val(); //获取属性id对应的属性值
		              var name=$(this).attr("name");  //获取该name的属性名称 
		              if (idValue !=''&& name!="allPrefix" && name!="distinguish"){
		            	  eval("prefix."+name+"=" + idValue);
		              } else if(name == "allPrefix") {
		            	  prefix.allPrefix = idValue;
		              }else if(name == "distinguish"){
		            	  prefix.distinguish = idValue;
		              }
		            }              
		        }) 
			})				  
			prefixList.push(prefix);	
			i++;
		})
		params.prefixList = JSON.stringify(prefixList);
		params.creater = creater;
		url = baseUrl+"/prefix/addContractInfoPrefix.action";
	}else{
		//修改
		$('#tableList1').find('tr').each(function () { 
			var prefix = {};
			$(this).find('td').each(function () {				
				$(this).find(':checkbox').each(function () {                         //获取td中input的值
		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		              var name=$(this).attr("name");  //获取该name的属性名称 
		              var idValue =$("input:checkbox[id='"+ name + i +"']:checked").val();//获取属性id对应的属性值
		              if (idValue == undefined) {
		            	  idValue = 1;
		            	  }
		              if (idValue !=''){
		            	  eval("prefix."+name+"=" + idValue);
		              }             
		           }
		        })
	        $(this).find(':text').each(function () {                         //获取td中input的值
		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		              var idValue = $(this).val(); //获取属性id对应的属性值
		              var name=$(this).attr("name");  //获取该name的属性名称 
		              if (idValue !=''&& name!="allPrefix" && name!="distinguish" && name != "flowNumber"){
		            	  eval("prefix."+name+"=" + idValue);
		              } else if(name == "allPrefix") {
		            	  prefix.allPrefix = idValue;
		              } else if(name == "flowNumber"){
		            	  prefix.flowNumber = idValue;
		              } else if(name == "distinguish"){
		            	  prefix.distinguish = idValue;
		              }
		            }              
			     }) 
			})				  
			prefixList.push(prefix);	
			i++;
		})
		params.prefixList = JSON.stringify(prefixList);
		params.creater = creater;
		url = baseUrl+"/prefix/updateSerialNumber.action";
	}

		
	$.ajax({
		type: "post",
		url: url,
		data:params,
		async:false,
		dataType: "json",
		success: function (data) {
			if(data != null){
				if(data.code == "success"){
					swal({
			 			title: "提示",
			 			text: data.message,
			 			type: data.code,
			 			confirmButtonText: '确定',
			 			cancelButtonFont: '微软雅黑',
			 		},function(){
			 			window.location.reload();
			 		});
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
		}
	});
}

//只能入力数字和小数点  
function keepNumOrDecimal(obj){ 
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数 
//    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
//        obj.value= parseFloat(obj.value); 
//    } 
} 

//最后一位不是小数点
function checkPoint(obj){
	keepNumOrDecimal(obj);
	if (obj.value.charAt(obj.value.length - 1)=='.'){
		obj.value = obj.value.substring(0,obj.value.length - 1);
	}
}

//查询所有
function getAllPrefix(){
	$.ajax({
		type: "post",
		url: baseUrl+'/prefix/getAllPrefix.action',
		async:false,
		dataType: "json",
		success: function (data) {
			var prefixList = data.prefixList;
			if (prefixList.length>0){
				for(var i=0;i<prefixList.length;i++){
					//主键
					$("#zj").val(prefixList[i].id);
					$("#id"+i).val(prefixList[i].id);
					
					$("#allPrefix"+i).val(prefixList[i].allPrefix);	
					if (prefixList[i].year == "0") {
						$("#year"+i).attr("checked","checked");
					}
					if (prefixList[i].month == "0"){
						$("#month"+i).attr("checked","checked");
					}
					if (prefixList[i].day == "0"){
						$("#day"+i).attr("checked","checked");
					}
					if (prefixList[i].hour == "0") {
						$("#hour"+i).attr("checked","checked");
					}
					if (prefixList[i].branch == "0") {
						$("#branch"+i).attr("checked","checked");
					}
					if (prefixList[i].second == "0"){
						$("#second"+i).attr("checked","checked");
					}
					if (prefixList[i].flowNumber != "" && prefixList[i].flowNumber != null) {
						$("#flowNumber"+i).attr("disabled","disabled");
						$("#flowNumber"+i).val(prefixList[i].flowNumber);	
					}
				}
			}
		}
	});
}