var baseUrl = "";
var Token;
// 条形图显示数据
var series=[];
	
$(function(){
	baseUrl = getContextPath();
	$("#mp4").hide();

/*$.ajax({
		url:'../FluorescentCloud/getAccessToken.action',
	    async:false,
		data:{},
	    dataType: "json",
		success:function(data){
			var obj = JSON.parse(data);
			Token =obj.data.accessToken;
		},
		error:function(e){
			alert(e)
		}
	});*/
/*	
	  
	VsById()
	VsById2()
	VsById3()*/
   
   // 获取试验室显示的数量
   $.ajax({
        type: "post",
        url:  "../../VideoSurveillance/getTestInfoCount.action",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
            var html = "";
            Arr = data;
        	if(data != null && data.length > 0){
        		for(var i = 0; i < data.length; i++){
        			
        			html += "<div id='count"+(i+1)+"'>"
					        + "<ul>"
					        + "<li style='display:none;'>"+data[i].UniqueIdentifier+"</li>"
					        + "<li>"+data[i].TestRoomName+"</li>"
					        + "<li>试验总数：<span>"+data[i].zCount+"</span></li>"
					        + "<li>自动采集试验数：<span>"+data[i].dCount+"</span></li>"
					        + "<li>盲样试验数：<span>"+data[i].mCount+"</span></li>"
				            + "</ul>"
				            + "</div>";
        			
        			// 动态赋值
        		    var item={
        		    		product: data[i].TestRoomName,
        					'试验总数': data[i].zCount,
        					'盲样试验数量': data[i].mCount,
        					'自动采集试验数量': data[i].dCount
        		        };
        		    series.push(item);
        		    
        		}
        	}
        	$("#count").html(html);
        	
        	// 试验汇总展示图表
        	mainShow(series);
        }
   });
	// 自动采集查询
	getAutoCollectionCount();
	// 盲样数量
	getBlindCount();
	
	//文字居中,延迟0.5秒
	setTimeout(function(){
		textCenter();
	},50)
	
	
})
//文字居中
function textCenter(){
	var textHeight = $('.icon_text').height();
	$('.icon_text').css('line-height', textHeight + 'px');
	var textHeight0 = $('.icon_text0').height();
	$('.icon_text0').css('line-height', textHeight0 + 'px');
	var textHeight2 = $('.icon-pie0 .noIconz-pie ').height();
	$('.icon-pie0 .noIconz-pie ').css('line-height', textHeight2 + 'px');
	var textHeight3 = $('.cameraText').height();
	$('.cameraText').css('line-height', textHeight3 + 'px');
}
// 自动采集数量
function getAutoCollectionCount(){	
	$.ajax({
        type: "post",
        url: "../../VideoSurveillance/getAutoCollectionCount.action",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	if(data != null && data.length > 0){
        		for(var i = 0; i < data.length; i++){
        			var html = "";
        			html += "<div class='swiper-slide'>"
						 + "<a href='#myModal' class='btn1' data-toggle='modal' onclick='getAutoCollectionList(\""+data[i].UniqueIdentifier+"\")';>点击查看详细数据</a>"
						 + "<div class='pie' id='pie"+(i+1)+"'></div>"
						 + "<div class='pieCon col-10'>"
						 + "<div class='icon-pie'>"
						 + "<div class='icon_half'></div>"
						 + "<div class='icon_text'>自动采集合格率：<span>"+percentNum(data[i].hgCount,data[i].zdCount)+"</span>"
						 + "</div>"
						 + "</div>"
						 + "<div class='noIconz-pie'>"
						 + "<span class='i1'>自动采集总数量：</span><span class='i2'>"+data[i].zdCount+"</span>"
						 + "</div>"
						 + "<div class='noIconz-pie'>"
						 + "<span class='i1'>合格试验数量：</span><span class='i3'>"+data[i].hgCount+"</span>"
						 + "</div>"
						 + "</div>"
					     + "</div>";
        			$("#zdcj").append(html);
        			
        			// 显示自动采集试验饼形图
        			pieShow(data[i], (i+1));
        		}
        		
        	}
        }
	})		
	var swiper1 = new Swiper('#swiper1', {
		speed: 2500,
		autoplay: 3500,
		effect: 'flip',
		flipEffect: {
			slideShadows: true,
			limitRotation: true,
		}
	});
	var swiper3 = new Swiper('#banhezhan', {
		speed: 2500,
		autoplay: 3500,
		effect: 'flip',
		flipEffect: {
			slideShadows: true,
			limitRotation: true,
		}
	});
}

// 盲样数量
function getBlindCount(){
	$.ajax({
        type: "post",
        url:"../../VideoSurveillance/getBlindCount.action",
        data:{},
        dataType: "json",
        success: function (data) {
        	if(data != null && data.length > 0){
        		for(var i = 0; i < data.length; i++){
        			var html = "";
        			html += "<div class='swiper-slide'>"
        				   + "<a href='#myModal1' class='btn2' data-toggle='modal' onclick='getBlindList()'>点击查看详细数据</a>"
				           + "<div class='pieCon0'>"
					        + "<div class='icon-pie0' style='margin-top: 20%;'>"
					        + "<div class='icon_half'></div>"
					        + "<div class='icon_text0'>盲样合格率：<span>"+percentNum(data[0].qualifiedBlindCount,data[0].completedBlindCount)+"</span>"
					        + "</div>"
					        + "</div>"
					        + "<div class='icon-pie0' style='margin-top: 5%;'>"
					        + "<div class='noIconz-pie'>"
					        + "<span class='i1'>盲样总数：</span><span class='i2'>"+data[0].blindCount+"</span>"
					        + "</div>"
					        + "<div class='noIconz-pie'>"
					        + "<span class='i1'>盲样合格数：</span><span class='i3'>"+data[0].qualifiedBlindCount+"</span>"
					        + "</div></div><div class='icon-pie0' style='margin-top: 0;'>"
					        + "<div class='noIconz-pie'>"
					        + "<span class='i1'>盲样未完成数：</span><span class='i3'>"+(data[0].blindCount - data[0].completedBlindCount)+"</span>"
					        + "</div>"
					        + "<div class='noIconz-pie'>"
					        + "<span class='i1'>盲样已完成数：</span><span class='i3'>"+data[0].completedBlindCount+"</span>"
					        + "</div>"
					        + "</div>"
					        + "</div>"
					        + "<div class='pie0' id='pie4'></div>"
				            + "</div>";
        			$("#myjc").html(html);
        			$('#myjc .icon_text0').css('line-height','100px');
        			ZS1pieShow(data[i])
        		}
        	}   	
        }
	})

  var swiper2 = new Swiper('#swiper2', { direction: 'vertical', speed: 2500,
 autoplay: 3500, effect: 'coverflow', });

}


/*function VsById(){	
	var vid = 1;
	$.ajax({
        type: "get",
        url:  baseUrl + "/VideoSurveillance/getById.action",
        data:{"vid":vid},
        async:false,
        dataType: "json",
        success: function (data) {
        	var url = data[0].vurl;
        	$("#myPlayerDiv").empty();
        	var vhtml =["<video id=\"myPlayer\" poster=\"\" controls playsInline",
        		"							webkit-playsinline autoplay>",
        		"						<source src="+url+"",
        		"								type=\"rtmp/flv\" />",
        		"						</video>"].join("");
        	$("#myPlayerDiv").html(vhtml);
        	new EZUIPlayer('myPlayer');
        }
 })
}

function VsById2(){	
	var vid = 2;
	$.ajax({
        type: "get",
        url:  baseUrl + "/VideoSurveillance/getById.action",
        data:{"vid":vid},
        async:false,
        dataType: "json",
        success: function (data) {
        	var url = data[0].vurl;
        	$("#myPlayerDiv2").empty();
        	var vhtml =["<video id=\"myPlayer2\" poster=\"\" controls playsInline",
        		"							webkit-playsinline autoplay>",
        		"						<source src="+url+"",
        		"								type=\"rtmp/flv\" />",
        		"						</video>"].join("");
        	$("#myPlayerDiv2").html(vhtml);
        	new EZUIPlayer('myPlayer2');
        }
 })
}

function VsById3(){
	var vid = 3;
	$.ajax({
        type: "get",
        url:  baseUrl + "/VideoSurveillance/getById.action",
        data:{"vid":vid},
        async:false,
        dataType: "json",
        success: function (data) {
        	//var url = "rtmp://rtmp.open.ys7.com/openlive/7d9f523127b044c0828c3312edd7be80.hd";
        	var url = data[0].vurl;
        	$("#myPlayerDiv3").empty();
        	var vhtml =["<video id=\"myPlayer3\" poster=\"\" controls playsInline",
        		"							webkit-playsinline autoplay>",
        		"						<source src="+url+"",
        		"								type=\"rtmp/flv\" />",
        		"						</video>"].join("");
        	$("#myPlayerDiv3").html(vhtml);
        	new EZUIPlayer('myPlayer3');
        }
 })
}*/
// 自动采集明细
function getAutoCollectionList(value){
	
	//获取列表 刷新
	var table = $('#autoCollection').dataTable();
	table.fnDestroy();
	
	$('#autoCollection').DataTable({
        "paging" : true,
        "lengthChange" : false,
        "bLengthChange": false,
        "pageLength" : 14,
        "searching" : false,
        "ordering" : false,
        "info" : true,
        "sInfo" : true,
        "autoWidth" : false,
        "language": {
            "lengthMenu": "每页 _MENU_ 条记录",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页,共 _PAGES_ 页 (显示_END_ 条，共 _TOTAL_ 项)",
            "infoEmpty": "无记录",
            "sSearch": "在结果中查找：",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "oPaginate": {
                "sFirst": "第一页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "最后一页"
            },
        },
        "ajax" : {
            type: "post",
            url: "../../VideoSurveillance/getAutoCollectionList.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.UniqueIdentifier =value;
                return param;// 自定义需要传递的参数。
                
            }
        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "testRoomName"
        }, {
            "data" : "testClassification_Name"
        }, {
            "data" : "testName"
        }, {
            "data" : "sampleCode"
        }, {
            "data" : "sampleCount"
        }, {
            "data" : "testDate"
        }, {
            "data" : "testOperator"
        },{
	        "data" : "isQualifiedTest",
	        render: function(data, type, row, meta) {
	        	if (data == "1") {
	                data = "合格";
	            }else{
	            	data = "不合格";
	            }
	            return data;
	        }
        }],
        "createdRow" : function(row, data, dataIndex) {
        },
	   });
	
	$('.lq_biao .row:first-child').css('display','none');
	$('#autoCollection_info').css('text-align','center');
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	}

// 盲样检测明细
function getBlindList(){
	$('#blindInfo').DataTable({
        "paging" : true,
        "lengthChange" : false,
        "bLengthChange": false,
        "pageLength" : 14,
        "searching" : false,
        "ordering" : false,
        "info" : true,
        "sInfo" : true,
        "autoWidth" : false,
        "language": {
            "lengthMenu": "每页 _MENU_ 条记录",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页,共 _PAGES_ 页 (显示_END_ 条，共 _TOTAL_ 项)",
            "infoEmpty": "无记录",
            "sSearch": "在结果中查找：",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "oPaginate": {
                "sFirst": "第一页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "最后一页"
            },
        },
        "ajax" : {
            type: "post",
            url: "../../VideoSurveillance/getBlindList.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                return param;// 自定义需要传递的参数。
            }
        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "testRoomName"
        }, {
            "data" : "testClassification_Name"
        }, {
            "data" : "testName"
        }, {
            "data" : "testState",
            render: function(data, type, row, meta) {
            	if (data == null) {
	                data = "抽样完成";
	            }else if(data != null && data != '2'){
	            	data = "收样完成";
	            }else if(data == '2'){
	            	data = "试验完成";
	            }
            	return data;
            	}
        }, {
            "data" : "sampleCode"
        }, {
            "data" : "constructionUnit"
        },
        {
            "data" : "engineeringName"
        },
        {
            "data" : "sampleName"
        },
        {
            "data" : "purpose"
        },
        {
            "data" : "testDate"
        },
        {
            "data" : "testOperator"
        },
        {
            "data" : "isQualifiedTest",
            render: function(data, type, row, meta) {
/*	        	if (data == "1") {
	                data = "合格";
	            }else{
	            	data = "不合格";
	            }*/
	        	if (row.isQualifiedTest == "1") {
	                data = "合格";
	            }else if(row.isQualifiedTest == "0"){
	            	data = "不合格";
	            }else{
	            	data = "未判定";
	            }
	            return data;
	        }
        },
        ]
        });
	$('.lq_biao .row:first-child').css('display','none');
	$('#blindInfo_info').css('text-align','center');
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}


// 百分数计算
function percentNum(dividend, divisor) { 
	if(divisor != 0){
		return (Math.round(dividend / divisor * 10000) / 100.00 + "%"); // 小数点后两位百分比
	}else{
		return "0%";
	}
	
}

/*function Jpcontroller(id){
	
	 var params = {
		        "accessToken": Token,
		        "deviceSerial": "C16294786",
		        "channelNo": 1,
		        "direction": id,
		        "speed": 1,
		    };
	 	$.post("https://open.ys7.com/api/lapp/device/ptz/start",params,function(date){
	 	})

		$.ajax({
			url:'../FluorescentCloud/controllerJp.action',
		    async:false,
			data:params,
		    dataType: "json",
			success:function(data){
				var obj = JSON.parse(data);
				var code =obj.msg;
			},
		});
	 	function sleep(ms) {
	 		$.post("https://open.ys7.com/api/lapp/device/ptz/stop",params)
	 	}
	 	
	 	sleep(2000)
}

function Jpstop(){
	
	var params = {
	        "accessToken":Token,
	        "deviceSerial": "C16294786",
	        "channelNo": 1,
	    };
	$.ajax({
		url:'../FluorescentCloud/controllerJpStop.action',
	    async:false,
		data:params,
	    dataType: "json",
		success:function(data){
			var obj = JSON.parse(data);
			var code =obj.msg;
		},
	});
 //	$.post("https://open.ys7.com/api/lapp/device/ptz/stop",params)
}
*/

function login(){
	var password = $("#JpPassWord").val();
	if(password==123){
		$("#mp4").show();
		$("#isLogin").hide();
	}else{
		alert("密码错误")
	}
}

function banHeZhan(){
	// 拌合站显示的数量
	   $.ajax({
	        type: "post",
	        url:  "../../VideoSurveillance/getTestInfoCount.action",
	        data:{},
	        async:false,
	        dataType: "json",
	        success: function (data) {
	            var html = "";
	            Arr = data;
	        	if(data != null && data.length > 0){
	        		for(var i = 0; i < data.length; i++){
	        			
	        			html += "<div id='count"+(i+1)+"'>"
						        + "<ul>"
						        + "<li style='display:none;'>"+data[i].UniqueIdentifier+"</li>"
						        + "<li>"+data[i].TestRoomName+"</li>"
						        + "<li>试验总数：<span>"+data[i].zCount+"</span></li>"
						        + "<li>自动采集试验数：<span>"+data[i].dCount+"</span></li>"
						        + "<li>盲样试验数：<span>"+data[i].mCount+"</span></li>"
					            + "</ul>"
					            + "</div>";
	        			
	        			// 动态赋值
	        		    var item={
	        		    		product: data[i].TestRoomName,
	        					'试验总数': data[i].zCount,
	        					'盲样试验数量': data[i].mCount,
	        					'自动采集试验数量': data[i].dCount
	        		        };
	        		    series.push(item);
	        		    
	        		}
	        	}
	        	$("#count").html(html);
	        	
	        	// 试验汇总展示图表
	        	mainShow(series);
	        }
	   });
}
