var baseUrl = "";
var map = {};
var user ;
$(function() {
	baseUrl = getContextPath();
/*	$.ajax({
        type: "post",
        url: "../BlindSampleInfo/getBlindSampleInfoValue",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	orgId = data.orgId;
        	Id = data.id;
        	$(".userid").html(data.name)
        }
    });*/
	var afterUrl =  window.location.search.substring(1);
	if(afterUrl != "") {
		var a =  afterUrl.toString().split("&");
		var Id = a[0].substring(3)
	}
	getshowInfo("1111",Id);
	getJurisdiction()
	
	
	
	//角色变化					
	$("#roleType").change(function(){
		
		
		roletypeShow($("#roleType"),null,null);
				
	}) 
	
	//二级公司变动
	$("#twoType").change(function(){
		 var parentId = $("#twoType").val();
       	 setOptions($("#threeType"), dList.organizationInfoMap["level"+parentId],{key:"id", value:"orgName",parentId:"parentId"}, true,'请选择三级组织公司');
	});
	
	
});


function roletypeShow(obj,pid,twoid){
	
	if(obj.val()=="0"){
		
      	 setOptions($("#rootType"), dList.organizationInfoMap.level0,{key:"id", value:"orgName",parentId:"parentId"}, false,'请选择一级组织公司');
      	 $("#rootTypeDiv").show();
      	 
      	var parentId = $("#rootType").val();
      	
      	 setOptions($("#twoType"), dList.organizationInfoMap["level"+parentId],{key:"id", value:"orgName",parentId:"parentId"}, true,'请选择二级组织公司');
      	 $("#twoTypeDiv").show();
      	 
      	 if(pid==null){
      	 var parentId = $("#twoType").val();
      	 setOptions($("#threeType"), dList.organizationInfoMap["level"+parentId],{key:"id", value:"orgName",parentId:"parentId"}, true,'请选择三级组织公司');
      	 $("#threeTypeDiv").show();
      	 }else{
          	 var parentId =pid;
          	 setOptions($("#threeType"), dList.organizationInfoMap["level"+parentId],{key:"id", value:"orgName",parentId:"parentId"}, true,'请选择三级组织公司');
          	 $("#threeTypeDiv").show();
      	 }
      	 
		}
	
	
	if(obj.val()=="1"){
		
		//默认加载录入权限 全部
      	 setOptions($("#rootType"), dList.organizationInfoMap.level0,{key:"id", value:"orgName",parentId:"parentId"}, false,'请选择一级组织公司');
      	 $("#rootTypeDiv").show();
      	if(twoid==null){
      	 var parentId = $("#rootType").val();
      	 setOptions($("#twoType"), dList.organizationInfoMap["level"+parentId],{key:"id", value:"orgName",parentId:"parentId"}, true,'请选择二级组织公司');
      	 $("#twoTypeDiv").show();
      	 $("#threeTypeDiv").hide();
      	}else{
      		 var parentId = $("#rootType").val();
          	 setOptions($("#twoType"), dList.organizationInfoMap["level"+parentId],{key:"id", value:"orgName",parentId:"parentId"}, true,'请选择二级组织公司');
          	 $("#twoTypeDiv").show();
          	 $("#threeTypeDiv").hide();
          
      	}
      	 
		}
	
	
	if(obj.val()=="2"){
		
	//默认加载录入权限 全部
  	 setOptions($("#rootType"), dList.organizationInfoMap.level0,{key:"id", value:"orgName",parentId:"parentId"}, false,'请选择一级组织公司');
  	 $("#rootTypeDiv").show();
  	 $("#twoTypeDiv").hide();
  	 $("#threeTypeDiv").hide();
	}
			
	
	
}

var map1 = [];
//获取权限

function getshowInfo(orgId,Id) {
	$.ajax({
		type : "POST",
		url : baseUrl + "/TestUser/getshowInfo.html",
		data : {"orgId111":orgId,"id":Id},
		async:false,
		dataType : "json",
		success : function(data) {
			
			 user = data.data[0];
			
			//getJurisdiction(user);
			
			$("#str_user_Name").val(user.UserCode);
			$("#str_name").val(user.Name);
			$("#Org_Name").val(user.Org_Name);
			$("#i_id").val(user.i_id);
		}
	});
}
var dList;
function getJurisdiction(){
	 
	 $.ajax({
	        type: "post",
	        url: baseUrl + "/blindness/get/OrganizationInfo",
	        data:{roleType:$('#roleType').val()},
	        async:true,
	        dataType: "json",
	        success: function (data) {
	        	//默认加载录入权限 全部
	        	// setOptions($("#rootType"), dList.organizationInfoMap.level0,{key:"id", value:"orgName",parentId:"parentId"}, false,'请选择一级组织公司');
	        	// $("#rootTypeDiv").show();
	        	/*<input type="hidden" name="orgidmoren" id="orgidmoren">
	        	<input type="hidden" name="roleTypemoren" id="roleTypemoren">
	        	
	        	var user = obj.data[0];*/
	        /*	alert(user.roleType);*/
	        	$('#roleType').val(user.roleType);
	        	$("#roleTypemoren").val(user.roleType);
	        	$("#orgidmoren").val(user.Id);
	        	
	        	dList = data;
	        	var data1 =  data.organizationInfoList;
	        	for(var i = 0; i < data.organizationInfoList.length; i++) {
	    			
	        			
	        			map[data1[i].id]	= data1[i].id+"_"+data1[i].parentId;        			
	        	}
	        	
	        	map1 = [];
	        	
	        	map1.push(user.Id);
	        	var flag = true;
	        	
	        	while(true){
	        		var h;
	        		
	        		if(flag){
	        			
	        			 h = map[user.Id].split("_")[1];
	        			 flag = false;
	        		}
	        		if(h=="0"){
	        			
	        			break;
	        		}else{
	        			
	        			map1.push(h);
	        			h=map[h].split("_")[1];
	        		}
	        	}
	     
	    		
	        	if(user.roleType==0){
	        		
	        		//二级val 二级id
	        		roletypeShow($("#roleTypemoren"),map1[1],null);	
	        	}else if(user.roleType==1){
	        		
	        		roletypeShow($("#roleTypemoren"),null,map1[0]);	
	        	}else{
	        		roletypeShow($("#roleTypemoren"),null,null);	
	        	}
	        	
	        	if(map1.length==3){
	        		$("#twoType").val(map1[1]);
	        		$("#threeType").val(map1[0]);
	    		}else{
	    			$("#twoType").val(map1[0]);
	    		}
	        	
	        	$("#roletype").val("1");
	        	
	        }
	        
	    });
	 
	 
}



function add() {
	var usercode = $("#str_user_Name").val();
	var password = $("#str_password").val();
	var name = $("#str_name").val();
	var roletype = $("#roleType").val();
	var passwordAgin = $("#passwordAgin").val();
	var orgid = "";
	var i_id =$("#i_id").val();
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
    params = {"userCode":usercode, "password":password,"name":name,"roleType":roletype,"orgId":orgid,"id":i_id};
    
	$.ajax({
		type : "POST",
		url : baseUrl + "/TestUser/updateUser.html",
		data : params,
		async:false,
		dataType : "json",
		success : function(data) {
			if(data.amount!=1){
				alert("权限变更,请重新登录");
				window.location.href = baseUrl+"/TestUser/login.html";
			}else{
				window.location.href = baseUrl+"/TestUser/userinfo.html";
			}
/*			alert(data.message);
*/		}
	});
	//window.location.href = baseUrl+"/TestUser/userinfo.html";
}