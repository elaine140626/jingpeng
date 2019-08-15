var baseUrl ="";
var id;
var isHave = true;
var carArray = [];
var carArray2 = [];
$(function(){
	baseUrl = getContextPath();
	var param = parseUrl();// 解析所有参数 
	if(param != null && param != ""){
		id = param['id'];
		findFleetByid(id);
	}else{
		addTable()
	}
})
function parseUrl() {
	var url = location.href;
	var i = url.indexOf('?');
	if (i == -1)
		return;
	var querystr = url.substr(i + 1);
	var arr1 = querystr.split('&');
	var arr2 = new Object();
	for (i in arr1) {
		var ta = arr1[i].split('=');
		arr2[ta[0]] = ta[1];
	}
	return arr2;
}
//光标一直在最右侧
function moveEnd(obj) {
    obj.focus();
    var len = obj.value.length;
    if (document.selection) {
      var sel = obj.createTextRange();
      sel.moveStart('character', len);
      sel.collapse();
      sel.select();
    } else if (typeof obj.selectionStart == 'number'
      && typeof obj.selectionEnd == 'number') {
      obj.selectionStart = obj.selectionEnd = len;
    }
  }
function carNumber(){
	var fleetNumber = $("#fleetNumber").val();
	$.ajax({
		type: "post",
		url: baseUrl+'/FleetInfo/getCarInfoByCarNumber.action',
		async:false,
		data:{"carNumber":fleetNumber},
		dataType: "json",
		success: function (data) {
			if(data != null){
		      if(data.length>0){
		    		setTimeout(function(){swal({
					title: "该车辆编号重复，请重新输入!",
					type: "error",
					cancelButtonText: '确定',
					cancelButtonFont: '微软雅黑',
		    		}, function() {
		    			$("#fleetNumber").val("");
					}); 
		      });}
			}
	}
});
}
function addFleet(){
		var re =  /^[0-9a-zA-Z]*$/g;  //判断数字字母
		var phone=/[^\d-]*$/g; //手机验证
	 	var fleetNumber = $("#fleetNumber").val();
		var fleetName = $("#fleetName").val();
		var contacts = $("#contacts").val();
		var telephone = $("#telephone").val();
		var remarks = $("#remarks").val();
		if(fleetNumber==""){
			swal("操作失败", "车队编号不能为空", "info");
			return;
		}
		if(fleetNumber.length>20){
			swal("操作失败", "车队编号长度不能超过20", "info");
			$("#fleetNumber").val("");
			return;
		}
		if (!re.test(fleetNumber)){
			swal("操作失败", "车队编号格式错误只能输入字母和数字", "info");
			$("#fleetNumber").val("");
			return;
		}
		if(fleetName==""){
			swal("操作失败", "车队名称不能为空", "info");
			return;
		}
		if(fleetName.length>20){
			swal("操作失败", "车队名称长度不能超过20", "info");
			$("#fleetName").val("");
			return;
		}
		if(contacts==""){
			swal("操作失败", "联系人不能为空", "info");
			return;
		}
		if(contacts.length>20){
			swal("操作失败", "联系人长度不能超过20", "info");
			$("#contacts").val("");
			return;
		}
		if(telephone==""){
			swal("操作失败", "电话不能为空", "info");
			return;
		}
		if (!phone.test(telephone)){
			swal("操作失败", "电话格式错误", "info");
			$("#telephone").val("");
			return;
		}
		if(remarks.length>200){
			swal("操作失败", "配置长度不能超过200", "info");
			$("#remarks").val("");
			return;
		}
		var isNull = false;
		var plateNumberList=[];
		var carNumberList = [];
		$("#tableList1 tbody").find("tr").each(function () {
			var tdArr = $(this).children();
			var carNumber = tdArr.eq(2).find("input").val();
			var plateNumber = tdArr.eq(3).find("input").val();
			var LoadTon = tdArr.eq(4).find("input").val();
			var CarJname1 = tdArr.eq(6).find("input").val();
			var carPhone1 = tdArr.eq(7).find("input").val();
			var CarJname2 = tdArr.eq(8).find("input").val();
			var carPhone2 = tdArr.eq(9).find("input").val();
			plateNumberList.push(plateNumber);
			//plateNumberList.push(LoadTon);
			carNumberList.push(carNumber);
			var rs =  /^[0-9a-zA-Z]*$/g;  //判断数字字母
			var carnumRegex = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/; //车牌验证
			var phone1=/[^\d-]*$/g; //手机验证
			  if(carNumber==""){
			    	swal("操作失败", "车辆编号不能为空", "info");
			    	isNull = true;
			    	return false;
			    }
			  if(carNumber.length>20){
					swal("操作失败", "车辆编号长度不能超过20", "info");
					isNull = true;
			    	return false;
			    }
			    if (!rs.test(carNumber)){
					swal("操作失败", "车辆编号格式错误只能输入字母和数字", "info");
					isNull = true;
					return false;
				}
			    if(plateNumber==""){
			    	swal("操作失败", "车牌号码不能为空", "info");
			    	isNull = true;
			    	return false;
			    }
			    if(plateNumber!=""){
			    	isHavePlateNumber(plateNumber)
			    	if(isHave){
			    		swal("操作失败", plateNumber+"车牌已添加", "info");
			    		isNull = true;
			    		return false;
			    	}
//			    	if (!carnumRegex.test(plateNumber)){
//			    		swal("操作失败", "车牌格式错误", "info");
//			    		isNull = true;
//			    		return false;
//			    	}
			    }
			    if(CarJname1.length>10){
					swal("操作失败", "司机1长度不能超过10", "info");
					isNull = true;
			    	return false;
			    }
			    if(carPhone1!=null&&carPhone1!=""){
			    	if (!phone1.test(carPhone1)){
						swal("操作失败", "联系电话1格式错误", "info");
						isNull = true;
						return;
					}
			    }
			    if(CarJname2.length>10){
					swal("操作失败", "司机2长度不能超过10", "info");
					isNull = true;
			    	return false;
			    }
			    if(carPhone2!=null&&carPhone2!=""){
			    	if (!phone1.test(carPhone2)){
						swal("操作失败", "联系电话2格式错误", "info");
						isNull = true;
						return;
					}
			    }
		})
		
		var haveNullPlateNumberList = plateNumberList.filter(s => $.trim(s).length > 0);
		var haveNullCarNumberList = carNumberList.filter(s => $.trim(s).length > 0);
		if(isRepeat(haveNullPlateNumberList)){
			swal("操作失败", "车牌号不能重复", "info");
			return;
		}
		if(isRepeat(haveNullCarNumberList)){
			swal("操作失败", "车辆编号不能重复", "info");
			return;
		}
		if(isNull){
			return;
		}
		var fid = id
		if(fid!=""&&fid!=null){
			options = {
					"fleetNumber" : fleetNumber,
					"fleetName" : fleetName,
					"contacts" : contacts,
					"telephone" : telephone,
					"remarks" : remarks,
					"id":fid
			};
			 $.ajax({
		         type: "post",
		         url: baseUrl+'/FleetInfo/updateFleetInfo.action',
		         async:false,
		         data:JSON.stringify(options),
		         dataType: "json",
		         contentType : 'application/json;charset=UTF-8',
		         success: function (data) {
		        	 if(data.code == "200"){
		        		 var jsonStr = "{\"scientificProject\":[";
		        		  $('#tableList1 tbody').find('tr').each(function () {               //tableId 是table表格的id
		        		     jsonStr += "{";
		        		     $(this).find('td').each(function () {

		        		        $(this).find('input').each(function () {                        //获取td中input的值
		        		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		        		              jsonStr += "\""+$(this).attr("name")+"\":\""+$(this).val()+"\",";
		        		           }
		        		        })

		        		        $(this).find('select').each(function () {                        //获取td中input的值
		        		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		        		              jsonStr += "\""+$(this).attr("name")+"\":\""+$(this).val()+"\",";
		        		           }
		        		        })
		        		     })
		        		     jsonStr = jsonStr.substring(0,jsonStr.length - 1);
		        		     jsonStr += "},";
		        		  })
		        		  jsonStr = jsonStr.substring(0,jsonStr.length - 1);
		        		  jsonStr += "]}";
		        		  
		        		  var scientificProject = "{\"scientificProject\":]}"
		        		  if(jsonStr == scientificProject){
		        			  swal({
  								title : data.message,
  								text : "",
  								type : "success",
  								confirmButtonText : '确定',
  								cancelButtonFont : '微软雅黑',
  							}, function() {
  								window.location.href="team_information.html";
  							});
		        		  }
		        		  var bToObj=JSON.parse(jsonStr); 
		        		  var aToStr=JSON.stringify(bToObj.scientificProject);
		        			 $.ajax({
		        		         type: "post",
		        		         url: baseUrl+'/FleetInfo/updateCarInfo.action',
		        		         async:false,
		        		         data:aToStr,
		        		         dataType: "json",
		        		         contentType : 'application/json;charset=UTF-8',
		        		         success: function (data) {
		        		        	 if(data.code == "200"){
		        		        		 swal({
		        								title : data.message,
		        								text : "",
		        								type : "success",
		        								confirmButtonText : '确定',
		        								cancelButtonFont : '微软雅黑',
		        							}, function() {
		        								window.location.href="team_information.html";
		        							});
		        		        	 }else{
		        		        		 swal("操作失败", data.message, "error");
		        		        	 }
		        		         }
		        		    });
		        	 }else{
		        		 swal("操作失败", data.message, "error");
		        	 }
		         }
		    });
		}else{
			options = {
					"fleetNumber" : fleetNumber,
					"fleetName" : fleetName,
					"contacts" : contacts,
					"telephone" : telephone,
					"remarks" : remarks
			};
			 $.ajax({
		         type: "post",
		         url: baseUrl+'/FleetInfo/addFleetInfo.action',
		         async:false,
		         data:JSON.stringify(options),
		         dataType: "json",
		         contentType : 'application/json;charset=UTF-8',
		         success: function (data) {
		        	 if(data.code == "200"){
		        		 var jsonStr = "{\"scientificProject\":[";
		        		  $('#tableList1 tbody').find('tr').each(function () {               //tableId 是table表格的id
		        		     jsonStr += "{";
		        		     $(this).find('td').each(function () {

		        		        $(this).find('input').each(function () {                        //获取td中input的值
		        		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		        		              jsonStr += "\""+$(this).attr("name")+"\":\""+$(this).val()+"\",";
		        		           }
		        		        })

		        		        $(this).find('select').each(function () {                        //获取td中input的值
		        		           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		        		              jsonStr += "\""+$(this).attr("name")+"\":\""+$(this).val()+"\",";
		        		           }
		        		        })
		        		     })
		        		     jsonStr = jsonStr.substring(0,jsonStr.length - 1);
		        		     jsonStr += "},";
		        		  })
		        		  jsonStr = jsonStr.substring(0,jsonStr.length - 1);
		        		  jsonStr += "]}";

		        		  var scientificProject = "{\"scientificProject\":]}"
			        		  if(jsonStr == scientificProject){
			        			  swal({
      								title : data.message,
      								text : "",
      								type : "success",
      								confirmButtonText : '确定',
      								cancelButtonFont : '微软雅黑',
      							}, function() {
      								window.location.href="team_information.html";
      							});
			        		  }
		        		  var bToObj=JSON.parse(jsonStr); 
		        		  var aToStr=JSON.stringify(bToObj.scientificProject);
		        			 $.ajax({
		        		         type: "post",
		        		         url: baseUrl+'/FleetInfo/addCarInfo.action',
		        		         async:false,
		        		         data:aToStr,
		        		         dataType: "json",
		        		         contentType : 'application/json;charset=UTF-8',
		        		         success: function (data) {
		        		        	 if(data.code == "200"){
		        		        		 swal({
		        								title : data.message,
		        								text : "",
		        								type : "success",
		        								confirmButtonText : '确定',
		        								cancelButtonFont : '微软雅黑',
		        							}, function() {
		        								window.location.href="team_information.html";
		        							});
		        		        	 }else{
		        		        		 swal("操作失败", data.message, "error");
		        		        	 }
		        		         }
		        		    });
		        	 }else{
		        		 swal("操作失败", data.message, "error");
		        	 }
		         }
		    });
		}
		
	
}

//添加一行
function addTable(){
	var len = $('#tableList1 tbody tr').length;
	var row;
	var result="";
	if(len==0){
		row = 1;
	}else{
		for(var i = 0;i<len;i++){
			row = len+1;
		}
	}
	result +="<tr>";
	result +="<td>"+row+"</td>";
	result +="<td ><a href=\"javascript:void(0)\" onclick=\"removeTr(this)\"><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a></td>";
	result +="<td ><input name='carNumber' type='text'onkeyup='moveEnd(this);value=value.replace(/[\W]/g,\"\")'/><span style='color:red;'>*</span></td>";
	result +="<td ><input name='plateNumber' type='text' onkeyup='moveEnd(this)' maxlength='15'/><span style='color:red;'>*</span></td>";
	result +="<td ><input name='loadTon' type='text' maxlength='13' onkeyup='moveEnd(this);clearNoDecimal(this,8,4)' onblur='checkPoint(this)'/></td>";
	result +="<td ><select name='isLargeCar'><option value='1'>是</option><option value='0'>否</option> </select></td>";
	result +="<td ><input name='driver1' type='text' onkeyup='moveEnd(this)'/></td>";
	result +="<td ><input id='telephone1' name='telephone1' type='text' onkeyup='moveEnd(this);' onchange='changeTelephone(this.value,1);'/></td>";
	result +="<td ><input name='driver2'  type='text' onkeyup='moveEnd(this)'/></td>";
	result +="<td ><input id='telephone2' name='telephone2' type='text' onkeyup='moveEnd(this);' onchange='changeTelephone(this.value,2);'/></td>";
	result +="<td ><input name='remarks' type='text' onkeyup='moveEnd(this)'/></td>";
	result +="<td ><input style='display: none;' name='id' value='0' type='text'/></td>";
	result +="</tr>";
	$("#tableList1").append(result);
}

//移除一行
function removeTr(obj,id) {
	if(id!=undefined){
	swal({
		title: "确定操作吗？",
		text:"删除后将无法恢复，请谨慎操作！",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
	},
	function(){
	    	var cid = id
	    	$.ajax({
	    		type: "post",
	    		url: baseUrl+'/FleetInfo/getCarInfoById.action',
	    		async:false,
	    		data:{"id":cid},
	    		dataType: "json",
	    		success: function (data) {
	    			if(data != null){
	    				$.ajax({
	    		    		type: "post",
	    		    		url: baseUrl+'/FleetInfo/getAllCarName.action',
	    		    		async:false,
	    		    		data:{"plateNumber":data.plateNumber},
	    		    		dataType: "json",
	    		    		success: function (data) {
	    		    			if(data.length>0){
	    		    				setTimeout(function(){swal({
	    								title: "该车辆已用,不可删除!",
	    								type: "error",
	    								cancelButtonText: '确定',
	    								cancelButtonFont: '微软雅黑',
	    							}); },200);
	    		    			}else{
	    		    				$.ajax({
	    		    		    		type: "post",
	    		    		    		url: baseUrl+'/FleetInfo/delCarInfoById.action',
	    		    		    		async:false,
	    		    		    		data:{"id":cid},
	    		    		    		dataType: "json",
	    		    		    		success: function (data) {
	    		    		    			
	    		    		    		}
	    		    		    	});
	    		    				var tr= $(obj).parent().parent();
	    		    			    tr.remove();
	    		    			    var len = $('#tableList1 tr').length;
	    		    			    for (var i = 0; i < len; i++) {
	    		    					$('#tableList1 tr:eq('+i+') td:first').text(i);
	    		    					
	    		    				}
	    		    			}
	    		    		}
	    		    	});
	    			}
	    		}
	    	});
});
	}else{
		 var tr= $(obj).parent().parent();
		    tr.remove();
		    var len = $('#tableList1 tr').length;
		    for (var i = 0; i < len; i++) {
				$('#tableList1 tr:eq('+i+') td:first').text(i);
				
			}
	}
}

//回显车辆
function findFleetByid(id){
	$.ajax({
        type: "post",
        url: baseUrl+'/FleetInfo/getFleetInfoById.action',
        async:false,
        data:{"id":id},
        dataType: "json",
        success: function (data) {
        	var fleetInfo = data.data.fleetInfo;
        	var carList = data.data.carInfo;
        	$("#fleetNumber").val(fleetInfo.fleetNumber);
    		$("#fleetName").val(fleetInfo.fleetName);
    		$("#contacts").val(fleetInfo.contacts);
    		$("#telephone").val(fleetInfo.telephone);
    	    $("#remarks").val(fleetInfo.remarks);
    	    var result="";
    	 /*   $("#tableList1  tr:not(:first)").remove();*/
    	    for (var i = 0; i < carList.length; i++) {
    	    	result +="<tr>";
    	        result +="<td >"+(i+1)+"</td>";
    	        result +="<td ><a href=\"javascript:void(0)\" onclick=\"removeTr(this,"+carList[i].id+")\"><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a></td>";
    	        result +="<td ><input name='carNumber' onkeyup='moveEnd(this);' value='"+carList[i].carNumber+"' type='text'/><span style='color:red;'>*</span></td>";
    	        result +="<td ><input name='plateNumber' onkeyup='moveEnd(this);'  value='"+carList[i].plateNumber+"' type='text' maxlength='15'/><span style='color:red;'>*</span></td>";
    	        carArray.push(carList[i].plateNumber);
    	        if(carList[i].loadTon == null){
    	        	carList[i].loadTon = "";
    	        }
    	        result +="<td ><input name='loadTon'  onkeyup='moveEnd(this);' value='"+carList[i].loadTon+"' type='text' maxlength='15'/></td>";
    	        //carArray2.push(carList[i].plateNumber2);
    	        if(carList[i].isLargeCar == 1){
    	        	result +="<td ><select name='isLargeCar'><option selected='selected' value='1'>是</option><option value='0'>否</option> </select></td>";
    	        }else{
    	        	result +="<td ><select name='isLargeCar'><option value='1'>是</option><option selected='selected' value='0'>否</option> </select></td>";
    	        }
    	        result +="<td ><input name='driver1' onkeyup='moveEnd(this);' value='"+carList[i].driver1+"' type='text'/></td>";
    	        result +="<td ><input id='telephone1' onkeyup='moveEnd(this);' name='telephone1' value='"+carList[i].telephone1+"' type='text' onchange='changeTelephone(this.value,1);'/></td>";
    	        result +="<td ><input name='driver2' onkeyup='moveEnd(this);' value='"+carList[i].driver2+"'  type='text'/></td>";
    	        result +="<td ><input id='telephone2' onkeyup='moveEnd(this);' name='telephone2' value='"+carList[i].telephone2+"' type='text' onchange='changeTelephone(this.value,2);'/></td>";
    	        result +="<td ><input name='remarks' onkeyup='moveEnd(this);' value='"+carList[i].remarks+"' type='text'/></td>";
    	        result +="<td ><input style='display: none;' name='id' value='"+carList[i].id+"' type='text'/></td>";
    	        result +="</tr>";
			}
    	    $("#tableList1").append(result);
        }
   });
}

function isRepeat(arr) {
	   var hash = {};
	   for(var i in arr) {
	       if(hash[arr[i]])
	       {
	           return true;
	       }
	       // 不存在该元素，则赋值为true，可以赋任意值，相应的修改if判断条件即可
	       hash[arr[i]] = true;
	    }
	   return false;
	}
//电话号码
function changeTelephone(value, flag){
	var regBox = {
	        /*regMobile : /^0?1[3|4|5|8][0-9]\d{8}$/,//手机
	        regTel : /^0[\d]{2,3}-[\d]{7,8}$/ // 固定电话
	        
*/	    regMobile : /[^\d-]*$/g,//手机
		regTel : /[^\d-]*$/g // 固定电话
			}
	if(value != null && value != ''){
		//var mflag = regBox.regMobile.test(value);
	    //var tflag = regBox.regTel.test(value);
		//var mflag = regBox.regMobile.test(value);
	    //var tflag = regBox.regTel.test(value);
	    if (!(/^1[34578]\d{9}$/.test(value)) && !(/^([0-9]{3,4}-)?[0-9]{7,8}$/).test(value)) {
	    	if(flag == 0){
	    		swal({
		 			title: "错误提示",
		 			text: "联系电话格式错误,请重新填写!",
		 			type: "error",
		 			confirmButtonText: '确定',
		 			cancelButtonFont: '微软雅黑',
		 		},function(){
		 		// 清空处理
					$("#telephone").val("");
		 		});
	    	}else if(flag == 1){
	    		swal({
		 			title: "错误提示",
		 			text: "联系电话1格式错误,请重新填写!",
		 			type: "error",
		 			confirmButtonText: '确定',
		 			cancelButtonFont: '微软雅黑',
		 		},function(){
		 		// 清空处理
					$("#telephone1").val("");
		 		});
	    	}else if(flag == 2){
	    		swal({
		 			title: "错误提示",
		 			text: "联系电话2格式错误,请重新填写!",
		 			type: "error",
		 			confirmButtonText: '确定',
		 			cancelButtonFont: '微软雅黑',
		 		},function(){
		 		// 清空处理
					$("#telephone2").val("");
		 		});
	    	}
	    }
	}  
}

//车牌号判断
function isHavePlateNumber(plateNumber){
	var count = 0;
	if($.inArray(plateNumber, carArray)!=-1){
		count = 1
	}
	 $.ajax({
         type: "post",
         url: baseUrl+'/FleetInfo/getAllCarInfo.action',
         async:false,
         data:{"plateNumber":plateNumber},
         dataType: "json",
         success: function (data) {
        	 if(data.length > count){
        		 isHave =  true;
        	 }else{
        		 isHave = false;
        	 }
         }
    });
}