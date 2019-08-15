//接收项目路径
var baseUrl = "";
var TestRoomName = "";
var source = "";
var startTime;
var endtime;
//用户的权限
var roletype = '';
$(function() {
	$.ajax({
        type: "post",
        url: "../testUserInfo/getTestUserName.html",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	//获取当前登录人做显示
        	$(".userid").html(data.userName);
        	// 用户的权限
        	roletype = data.roletype;
        }
    });
	$.ajax({
        type: "post",
        url: "../testUserInfo/getTestUserName.html",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	//获取当前登录人做显示
        	$(".userid").html(data.userName);
        }
    });
	//转码解析跳页参数
	TestRoomName = getUrlParam("TestRoomName");
	source = getUrlParam("source");
	startTime = getUrlParam("startTime");
	endTime = getUrlParam("endTime");
	getList();
	//获取路径
	baseUrl = getContextPath();
});

//获取前页面参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if(r != null){
        return decodeURIComponent(r[2]);
//这里为什么是从第三个字符解析呢？不知道这样理解对不对，因为路径后面的参数形式为参数名=参数值，而第一个字符为参数名，第二个为=，第三个就为参数值了。。。因为下面调用的时候得出的就是参数值
    }
    return null;//返回参数值
}

//初始化
function getList() {
	$('#list').DataTable({
		"paging" : true,
		"lengthChange" : false,
		"pageLength" : 14,
		"searching" : false,
		"ordering" : false,
		"info" : true,
		"sInfo" : true,
		"autoWidth" : false,
		"language" : {
			"lengthMenu" : "每页 _MENU_ 条记录",
			"zeroRecords" : "没有找到记录",
			"info" : "第 _PAGE_ 页,共 _PAGES_ 页 (显示_END_ 条，共 _TOTAL_ 项)",
			"infoEmpty" : "无记录",
			"sSearch" : "在结果中查找：",
			"infoFiltered" : "(从 _MAX_ 条记录过滤)",
			"oPaginate" : {
				"sFirst" : "第一页",
				"sPrevious" : "上一页",
				"sNext" : "下一页",
				"sLast" : "最后一页"
			},
		},
		"ajax" :"../testInfo/getTestSummaryDetailedNumber.html?TestRoomName="+TestRoomName+"&source="+source+"&startTime="+startTime+"&endTime="+endTime,//拼接日期
		"deferRender" : true,
		"columns" : [ 
			{"data" : "no"}, 
			{"data" : "testRoomName"},
			{"data" : "testClassification_Name"},
			{"data" : "testName"}, 
			{"data" : "sampleCode"}, 
			{"data" : "sampleCount"}, 
			{"data" : "testDate"} ,
			{"data" : "testOperator"},
			{
				"data" : "isQualifiedTest1",
				render: function(data, type, row, meta) {
					 if (data == '合格') {
			                data = "合格";
			            }else if (data == '不合格'){
			            	data = '<span style="color: red;">不合格</span>';
			            }else{
			            	data = '<span style="color: red;">未判定</span>';
			            }
			            return data;
			        }
			
			},
			{"data" : "isTestCollection1"},
			{"data" : "isTestBlind1"},
			{"data" : "select"}
			],
		"createdRow" : function(row, data, dataIndex) {
			var date = new Date(data.testDate);
			var dateTime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
			$(row).children("td").eq(6).html(dateTime);
			
			var html = '';
			if(data.testTable == 'Test04006T0'){
				html ="<span><a href='../ShiYan/shiyan01.html?id="+data.id +"&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(data.testTable == 'Test0500101T0'){
				// 水泥混凝土抗压强度试验（立方体）
				html ="<span><a href='../ShiYan/shiyan02.html?id="+data.id +"&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(data.testTable == 'Test0500102T0'){
				// 砂浆抗压强度试验
				html ="<span><a href='../testInfo/shiyan03.html?serialNumber="+data.serialNumber+"&TestRoomName="+data.testRoomName+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(data.testTable == 'Test07003T0'){
				// 无机结合料稳定材料无侧限抗压强度试验
				html ="<span><a href='../ShiYan/shiyan04.html?id="+data.id +"&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(data.testTable == 'Test08001T0'){
				// 沥青三大指标试验
				html ="<span><a href='../ShiYan/shiyan05.html?id="+data.id +"&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(data.testTable == 'Test0900101T0'){
				// 沥青混合料马歇尔稳定度试验
				html ="<span><a href='../ShiYan/shiyan06.html?id="+data.id +"&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(data.testTable == 'Test1000101T0'){
				// 钢筋抗拉强度、屈服强度、伸长率、冷弯试验
				html ="<span><a href='../ShiYan/shiyan07.html?id="+data.id +"&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(data.testTable == 'Test1000201T0'){
				// 钢筋接头抗拉强度、冷弯试验
				html ="<span><a href='../ShiYan/shiyan08.html?id="+data.id +"&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(data.testTable == 'Test0200101T0'){
				// 粗集料筛分试验
				html ="<span><a href='../ShiYan02/shiyan21.html?id="+data.id +"&issave=false&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(data.testTable == 'Test0200102T0'){
				// 细集料筛分试验
				html ="<span><a href='../ShiYan02/shiyan25.html?id="+data.id +"&issave=false&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(data.testTable == 'Test02015T0'){
				// 粗集料含泥量试验
				html ="<span><a href='../ShiYan02/shiyan22.html?id="+data.id +"&issave=false&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(data.testTable == 'Test02006T0'){
				// 细集料含泥量试验
				html ="<span><a href='../ShiYan02/shiyan26.html?id="+data.id +"&issave=false&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(data.testTable == 'Test02002T0'){
				// 粗集料针、片状颗粒含量试验
				html ="<span><a href='../ShiYan02/shiyan23.html?id="+data.id +"&issave=false&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(data.testTable == 'Test0200301T0'){
				// 粗集料压碎值试验
				html ="<span><a href='../ShiYan02/shiyan24.html?id="+data.id +"&issave=false&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(data.testTable == 'Test04003T0'){
				// 水泥凝结时间
				html ="<span><a href='../ShiYan02/shiyan27.html?id="+data.id +"&issave=false&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(row.testtable == 'Test0201T0'){	
				// 粗集料试验
				html ="<span><a href='../ShiYan02/shiyan28.html?id="+data.id +"&issave=false&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else if(row.testtable == 'Test0202T0'){
				// 细集料试验
				html ="<span><a href='../ShiYan02/shiyan29.html?id="+data.id +"&issave=false&istestcollection="+data.isTestCollection+"&istestblind="+data.isTestBlind+"&roletype="+roletype+"&teststate="+data.teststate+"'>查看</a></span>";
			}else{
				html ="<span><a href='#'>查看</a></span>";
			}
			$(row).children("td").eq(11).html(html);
			
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		} ]
		
	});
	$('.lq_biao .row:first-child').css('display','none');
	$('.dataTables_info').css('text-align','center');
    $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

