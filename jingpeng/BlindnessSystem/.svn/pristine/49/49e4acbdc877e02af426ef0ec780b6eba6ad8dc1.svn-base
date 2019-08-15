var baseUrl = "";
var d;
var orgId = "";
$(function() {
	 
	baseUrl = getContextPath();
	
	
	//获取权限
	getJurisdiction()
	
	//角色变化
	$("#roleType").change(function(){
		
		getJurisdiction();		
	});
	
/*	//提交
	$("#tijiao").click(function(){
		
		//判断orgid
		if(orgId==""){
			
			alert("请选择单位");
		}
		
	});*/
});


//获取权限
function getJurisdiction(){

	// ajax({roleType:$('#roleType').val()}, baseUrl + "/blindness/get/OrganizationInfo", "OrganizationInfoRe");
	 
	 $.ajax({
	        type: "post",
	        url: baseUrl + "/blindness/get/OrganizationInfo",
	        data:{roleType:$('#roleType').val()},
	        async:false,
	        dataType: "json",
	        success: function (data) {
//        	debugger;
	        	OrganizationInfoRe(data)
	        }
	    });
	 
	 
}



function OrganizationInfoRe(obj){
	
	//平台查询
	if($('#roleType').val()=="0"){
		
		JurisdictionData(obj,false)
	}
	
	//抽样
	if($('#roleType').val()=="1"){
		
		JurisdictionData(obj,true);
	}
	
	//录入权限
	if($('#roleType').val()=="2"){
		
		JurisdictionData(obj,true);
	}
	
	
}


//展示单位数据
function  JurisdictionData(data,obj){
	
	d = new dTree('d', obj);
	d.add(data.organizationInfoMap.level1[0].id, -1, data.organizationInfoMap.level1[0].orgName, true, false);
	
	var leve2List = data.organizationInfoMap.level2;
	
	for(var i = 0; i < leve2List.length; i++) {
		
		d.add(leve2List[i].id, leve2List[i].parentId, 'authority', leve2List[i].id, leve2List[i].orgName ,false, false);
	}
	
	  
	var leve3List = data.organizationInfoMap.level3;
	
	for(var i = 0; i < leve3List.length; i++) {
		
		d.add(leve3List[i].id, leve3List[i].parentId, 'authority', leve3List[i].id, leve3List[i].orgName ,false, false);
	}
	console.log(d);
	document.write(d);
    d.openAll();	
	
        	
}

	


