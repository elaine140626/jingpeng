var baseUrl = "";
var dList;

$(function() {

	baseUrl = getContextPath();
	getJurisdiction();
	
	//角色变化					
	$("#roleType").change(function(){
		
		if($("#roleType").val()=="0"){
			
	       	 setOptions($("#rootType"), dList.organizationInfoMap.level0,{key:"id", value:"orgName",parentId:"parentId"}, false,'请选择一级组织公司');
	       	 $("#rootTypeDiv").show();
	       	var parentId = $("#rootType").val();
	       	
	       	 setOptions($("#twoType"), dList.organizationInfoMap["level"+parentId],{key:"id", value:"orgName",parentId:"parentId"}, true,'请选择二级组织公司');
	       	 $("#twoTypeDiv").show();
	       	 
	       	 var parentId = $("#twoType").val();
	       	 setOptions($("#threeType"), dList.organizationInfoMap["level"+parentId],{key:"id", value:"orgName",parentId:"parentId"}, true,'请选择三级组织公司');
	       	 $("#threeTypeDiv").show();
	       	 
			}
		
		
		if($("#roleType").val()=="1"){
			
			//默认加载录入权限 全部
	       	 setOptions($("#rootType"), dList.organizationInfoMap.level0,{key:"id", value:"orgName",parentId:"parentId"}, false,'请选择一级组织公司');
	       	 $("#rootTypeDiv").show();
	       	 var parentId = $("#rootType").val();
	       	
	       	 setOptions($("#twoType"), dList.organizationInfoMap["level"+parentId],{key:"id", value:"orgName",parentId:"parentId"}, true,'请选择二级组织公司');
	       	 $("#twoTypeDiv").show();
	       	 $("#threeTypeDiv").hide();
	       	 
			}
		
		
		if($("#roleType").val()=="2"){
			
		//默认加载录入权限 全部
       	 setOptions($("#rootType"), dList.organizationInfoMap.level0,{key:"id", value:"orgName",parentId:"parentId"}, false,'请选择一级组织公司');
       	 $("#rootTypeDiv").show();
       	 $("#twoTypeDiv").hide();
       	 $("#threeTypeDiv").hide();
		}
				
	}) 
	
	//二级公司变动
	$("#twoType").change(function(){
		 var parentId = $("#twoType").val();
       	 setOptions($("#threeType"), dList.organizationInfoMap["level"+parentId],{key:"id", value:"orgName",parentId:"parentId"}, true,'请选择三级组织公司');
	});

});

//获取权限
function getJurisdiction(){
	 
	 $.ajax({
	        type: "post",
	        url: baseUrl + "/blindness/get/OrganizationInfo",
	        data:{roleType:$('#roleType').val()},
	        async:false,
	        dataType: "json",
	        success: function (data) {
	        	
	        	dList = data;
	        	
	        	//默认加载录入权限 全部
	        	 setOptions($("#rootType"), dList.organizationInfoMap.level0,{key:"id", value:"orgName",parentId:"parentId"}, false,'请选择一级组织公司');
	        	 $("#rootTypeDiv").show();
	        }
	        
	    });
	 
	 
}



function addUser(){
	
	var param = [];
	var usercode = $("#userCode").val();
	var password = $("#userPwd").val();
	var name = $("#userName").val();
	var roletype = $("#roleType").val();
	var passwordAgin = $("#passwordAgin").val();
	var orgid = "";
	
	if($("#roleType").val()=="0"){
		var flag = false;
		
		if(!($("#threeType").val()=="")){
			
			orgid = $("#threeType").val();
			flag = true;
		}
	    
		if(!flag){
			if(!($("#twoType").val()=="")){
				
				orgid = $("#twoType").val();
				flag = true;
			}
		}
		
		if(!flag){
			if(!($("#roletype").val()=="")){
				
				orgid = $("#rootType").val();
				flag = true;
			}
		}
		
	}
	
	
	if($("#roleType").val()=="1"){
		
		if(!($("#twoType").val()=="")){
			
			orgid = $("#twoType").val();
		}
		
	}
	
	if($("#roleType").val()=="2"){
	   
		orgid = $("#rootType").val();
	}
    if(usercode==""||usercode==null){
    	alert("用户名不能为空")
    	return;
    }
    if(password==""||password==null){
    	alert("密码不能为空")
    	return;
    }
    if(password!=passwordAgin){
    	alert("二次密码输入不一样")
    	return;
    }
    if(name==""||name==null){
    	alert("名称不能为空")
    	return;
    }
    if(roletype==""||roletype==null){
    	alert("角色不能为空")
    	return;
    }
    
    if(orgid==""||orgid==null){ 
    	
    	
    	alert("请选择组织单位")
    	return;
    }

    var params = $("#tab").serialize();
    para = {"userCode":usercode, "password":password,"name":name,"roleType":roletype,"orgId":orgid};
	$.ajax({
			type : "POST",
			url : baseUrl + "/TestUser/addTestUser.html",
			data : para,
			async:false,
			dataType : "json",
			success : function(data) {
				if(data.code == "error") {
					alert(data.message);
				} else {
					alert(data.message);
					window.location.href=baseUrl+"/TestUser/login.html";
                    
				}
			}
	});
}



/*
 * 设置取回的值
 * tongnan
 * 2018.5.10
 */
function setOptions(item, list, params, flg,message) {
    item.empty();
    var optionName;
    if (flg) {
        item.append("<option value=''>"+message+"</option>");
    }
    if (list && list.length > 0) {

        var k = params.key;
        var v = params.value;
		 var j = params.parentId;
        $.each(list, function(idx, value){

            optionName =  value[v];
            item.append("<option value='"+ value[k] +"'>"+ optionName +"</option>");

        });
    }
}



