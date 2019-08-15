var id = "";
var orgId = "";
$(function(){
	var baseUrl = "";
	baseUrl = getContextPath();
	var searchURL =  window.location.search.substring(1);
	searchURL = searchURL.substring(1, searchURL.length);
    var id = searchURL.split("&")[0].split("=")[1];
    var orgId = searchURL.split("&")[1].split("=")[1];
    $.ajax({
        type: "post",
        url: baseUrl + "/BlindSamp2/BlindSampInfo2_1.html?id="+id+"&orgId="+orgId,
        data:{id,orgId},
        async:false,
        dataType: "json",
        success: function (data) {
        	$("#testName").html(data.blindSampleInfoList[0].testName)
        	$("#sampeCode").html(data.blindSampleInfoList[0].sampeCode);
        	$("#constructionUnit").html(data.blindSampleInfoList[0].constructionUnit);
        	$("#engineeringName").html(data.blindSampleInfoList[0].engineeringName);
        	$("#purpose").html(data.blindSampleInfoList[0].purpose);
        	$("#designStrength").html(data.blindSampleInfoList[0].designStrength);
        	$("#materialName").html(data.blindSampleInfoList[0].materialName);
        	$("#sPileNumber").html(data.blindSampleInfoList[0].sPileNumber);
        	$("#samplingPerson").html(data.blindSampleInfoList[0].samplingPerson);
        	$("#quantity").html(data.blindSampleInfoList[0].quantity);
        	$("#purpose").html(data.blindSampleInfoList[0].purpose);
        	$("#samplingDate").html(data.blindSampleInfoList[0].samplingDate);
        	$("#moldDate").html(data.blindSampleInfoList[0].moldDate);
        	$("#placeOrigin").html(data.blindSampleInfoList[0].placeOrigin);
        	$("#remarks").html(data.blindSampleInfoList[0].remarks);
        	$("#targetDate").html(data.blindSampleInfoList[0].targetDate);
        	qrcode(data.blindSampleInfoList[0].qRCode);        	
        }
        
    });
    
    
    $.ajax({
        type: "post",
        url: baseUrl + "/BlindSamp2/BlindSampInfo2_2.html?id="+id,
        data:{id},
        async:false,
        dataType: "json",
        success: function (data) {
        	$.each(data.blindSampDetailedList,function (i,v) {
        		if(i>=20){
        		var listBlindHtml = "<tr>" +
        		"<td><label id="+"sernum_"+(i+1)+"></label></td>" +
        		"<td><label id="+"lq_"+(i+1)+"></label></td>" +
        		"<td><label id="+"technical_"+(i+1)+"></label></td>" +
        		"<td><label id="+"detection_"+(i+1)+"></label></td>" +
        		"<td><label id="+"resultPd_"+(i+1)+"></label></td>" +
        		"</tr>"
        		$("#tab").append(listBlindHtml);//在table最后面添加一行
        	}
                $("<label></label>").attr("value",i+1).html(i+1).appendTo($("#sernum"+"_"+(i+1)))
                $("<label></label>").attr("value",v.detectionProject).html(v.detectionProject).appendTo($("#lq"+"_"+(i+1)))
                $("<label></label>").attr("value",v.technicalIndicators).html(v.technicalIndicators).appendTo($("#technical"+"_"+(i+1)))
                $("<label></label>").attr("value",v.detectionResult).html(v.detectionResult).appendTo($("#detection"+"_"+(i+1)))
                $("<label></label>").attr("value",v.resultDetermination).html(v.resultDetermination).appendTo($("#resultPd"+"_"+(i+1)))
        			})
        	}
    	})
})
