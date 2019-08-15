// 试验室名称
var uniqueidentifiers = '';
//用户的权限
var roletype = '';
$('.rightcon ').show();
$(function() {
	// 获取当前用户的信息
	var userInfo = getUserInfo();
	if(userInfo != ""){
		// 用户的权限
    	roletype = userInfo.roletype;
    	// 获取当前登录人的试验室权限
    	uniqueidentifiers = userInfo.userTestDetailed;
	}

	// 获取试验名称
	getTestNameList1();
	var param = {};
	param.testNameId = '';
	param.testName = '';
	param.startTime = '';
	param.endTime = '';
	param.uniqueIdentifier = uniqueidentifiers;
	// 初始化列表数据
	load(param);
	//getInfoList(param);
});

function load(param){
	// 试验基本信息
	$('#list').DataTable({
		"paging" : true,
		"lengthChange" : false,
		"pageLength" : 12,
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
		"ajax" : {
			type : "post",
			url : "../../TestAutoCollection/getInfoList.action",
			dataSrc : "data",
			data : function(d) {
				return param;//自定义需要传递的参数。
			}
		},
		"deferRender" : true,
		"columns" : [ {
			"data" : "rownum"
		}, {
			"data" : "testroomname"
		}, {
			"data" : "testclassification_name"
		}, {
			"data" : "testname"
		}, {
			"data" : "samplecode"
		},{
			"data" : "samplecount"
		},{
			"data" : "testdate"
		},{
			"data" : "testoperator"
		},{
			"data" : "isqualifiedtest",
			 render: function(data, type, row, meta) {
				 if (data == '1') {
		                data = "合格";
		            }else if (data == '0'){
		            	data = '<span style="color: red;">不合格</span>';
		            }else{
		            	if (row.testtable == 'Test04006T0'){
		            		// 水泥胶砂强度试验
		            		data = '<span style="color: red;">无效</span>';
		            	}else if(row.testtable == 'Test0500101T0'){
		            		// 水泥混凝土抗压强度试验（立方体）
		            		data = '<span style="color: red;">无效</span>';
		            	}else if(row.testtable == 'Test1000201T0'){
		            		// 钢筋接头抗拉强度、冷弯试验
		            		data = '<span style="color: red;">复检</span>';
		            	}else{
		            		data = '<span style="color: red;">为判定</span>';
		            	}		            	
		            }
		            return data;
		        }
		},
		  {
			"data" : "id",
			render: function(data, type, row) {
				var html = '';
				if(row.testtable == 'Test04006T0'){
					html ="<span><a target='_blank' href='../item_pages/shiyan01.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0500101T0'){
					// 水泥混凝土抗压强度试验（立方体）
					html ="<span><a target='_blank' href='../item_pages/shiyan02.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0500102T0'){
					// 砂浆抗压强度试验
					html ="<span><a target='_blank' href='../item_pages/shiyan03.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test07003T0'){
					// 无机结合料稳定材料无侧限抗压强度试验
					html ="<span><a target='_blank' href='../item_pages/shiyan04.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test08001T0'){
					// 沥青三大指标试验
					html ="<span><a target='_blank' href='../item_pages/shiyan05.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0900101T0'){
					// 沥青混合料马歇尔稳定度试验
					html ="<span><a target='_blank' href='../item_pages/shiyan06.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test1000101T0'){
					// 钢筋抗拉强度、屈服强度、伸长率、冷弯
					html ="<span><a target='_blank' href='../item_pages/shiyan07.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test1000201T0'){
					// 钢筋接头抗拉强度、冷弯试验
					html ="<span><a target='_blank' href='../item_pages/shiyan08.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0200101T0'){
					// 粗集料筛分试验
					html ="<span><a target='_blank' href='../item_pages/shiyan21.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0200102T0'){
					// 细集料筛分试验
					html ="<span><a target='_blank' href='../item_pages/shiyan25.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test02015T0'){
					// 粗集料含泥量试验
					html ="<span><a target='_blank' href='../item_pages/shiyan22.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test02006T0'){
					// 细集料含泥量试验
					html ="<span><a target='_blank' href='../item_pages/shiyan26.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test02002T0'){
					// 粗集料针、片状颗粒含量试验
					html ="<span><a target='_blank' href='../item_pages/shiyan23.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0200301T0'){
					// 粗集料压碎值试验
					html ="<span><a target='_blank' href='../item_pages/shiyan24.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test04003T0'){
					// 水泥凝结时间
					html ="<span><a target='_blank' href='../item_pages/shiyan27.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0201T0'){	
					// 粗集料试验
					html ="<span><a target='_blank' href='../item_pages/shiyan28.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else if(row.testtable == 'Test0202T0'){
					// 细集料试验
					html ="<span><a target='_blank' href='../item_pages/shiyan29.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
				}else{
					html ="<span><a href='#'>查看</a></span>";
				}
                return html;
            }
		}, 
		],
		"createdRow" : function(row, data, dataIndex) {
			console.log(data);
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		} ]
	});
}

/*function getInfoList(param){
	var columns = [];
	var url = '';
	var targets = [];
	if(param.testName == ''){
		//查询水泥混凝土抗压强度试验（立方体）
		url = "../../TestAutoCollection/getSNHNList.action";
		columns = [
        	{
        		"data" : "rownum" , "title" : "序号"
        	},{
        		"data" : "testroomname" , "title" : "试验室名称"
        	},{
        		"data" : "testname" , "title" : "试验名称"
        	},{
        		"data" : "samplecode" , "title" : "样品编号"
        	},{
        		"data" : "samplesize" , "title" : "试件尺寸(mm)"
        	},{
        		"data" : "cementgrade" , "title" : "强度等级"
        	},{
        		"data" : "purpose" , "title" : "工程部位"
        	},{
        		"data" : "testdate" , "title" : "试验日期"
        	},{
        		"data" : "age" , "title" : "龄期(天)"
        	},{
        		"data" : "testoperator" , "title" : "试验员"
        	},{
        		"data" : "samplecount" , "title" : "试件个数"
        	},{
        		"data" : "comprstrength1" , "title" : "抗压强度1(MPa)"
        	},{
        		"data" : "comprstrength2" , "title" : "抗压强度2(MPa)"
        	},{
        		"data" : "comprstrength3" , "title" : "抗压强度3(MPa)"
        	},{
        		"data" : "compressivestrength" , "title" : "测定值(MPa)"
        	},{
        		"data" : "prop_DesignStrength" , "title" : "占设计强度(%)"
        	},{
        		"data" : "isqualifiedtest", "title" : "结果判定",
        		render: function(data, type, row, meta) {
   				 if (data == '1') {
   		                data = "合格";
   		            }else if (data == '0'){
   		            	data = '<span style="color: red;">不合格</span>';
   		            }else{
   		            	data = '<span style="color: red;">未判定</span>';
   		            }
   		            return data;
   		        }
        	},{
        		"data" : "id" , "title" : "详情",
    			render: function(data, type, row) {
    				var html = '';
    				if(row.testtable == 'Test04006T0'){
    					html ="<span><a target='_blank' href='../item_pages/shiyan01.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500101T0'){
    					// 水泥混凝土抗压强度试验（立方体）
    					html ="<span><a target='_blank' href='../item_pages/shiyan02.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500102T0'){
    					// 砂浆抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan03.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test07003T0'){
    					// 无机结合料稳定材料无侧限抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan04.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test08001T0'){
    					// 沥青三大指标试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan05.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0900101T0'){
    					// 沥青混合料马歇尔稳定度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan06.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000101T0'){
    					// 钢筋抗拉强度、屈服强度、伸长率、冷弯
    					html ="<span><a target='_blank' href='../item_pages/shiyan07.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000201T0'){
    					// 钢筋接头抗拉强度、冷弯试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan08.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200101T0'){
    					// 粗集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan21.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200102T0'){
    					// 细集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan25.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02015T0'){
    					// 粗集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan22.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02006T0'){
    					// 细集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan26.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02002T0'){
    					// 粗集料针、片状颗粒含量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan23.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200301T0'){
    					// 粗集料压碎值试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan24.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test04003T0'){
    					// 水泥凝结时间
    					html ="<span><a target='_blank' href='../item_pages/shiyan27.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0201T0'){	
    					// 粗集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan28.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0202T0'){
    					// 细集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan29.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else{
    					html ="<span><a href='#'>查看</a></span>";
    				}
                    return html;
                }
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	}
        ];
		targets = [0,1,2,3,4,5,6,7,8,9,10,,16,17];
	}else if(param.testName == "砂浆抗压强度试验"){
		url = "../../TestAutoCollection/getBJList.action";
		columns = [
        	{
        		"data" : "rownum" , "title" : "序号"
        	},{
        		"data" : "testroomname" , "title" : "试验室名称"
        	},{
        		"data" : "testname" , "title" : "试验名称"
        	},{
        		"data" : "samplecode" , "title" : "样品编号"
        	},{
        		"data" : "cementgrade" , "title" : "强度等级"
        	},{
        		"data" : "purpose" , "title" : "工程部位"
        	},{
        		"data" : "testdate" , "title" : "试验日期"
        	},{
        		"data" : "ageday" , "title" : "龄期(天)"
        	},{
        		"data" : "testoperator" , "title" : "试验员"
        	},{
        		"data" : "samplecount" , "title" : "试件个数"
        	},{
        		"data" : "comprstrength1" , "title" : "抗压强度1(MPa)"
        	},{
        		"data" : "comprstrength2" , "title" : "抗压强度2(MPa)"
        	},{
        		"data" : "comprstrength3" , "title" : "抗压强度3(MPa)"
        	},{
        		"data" : "comprstrength4" , "title" : "抗压强度4(MPa)"
        	},{
        		"data" : "comprstrength5" , "title" : "抗压强度5(MPa)"
        	},{
        		"data" : "comprstrength6" , "title" : "抗压强度6(MPa)"
        	},{
        		"data" : "compressionstrength" , "title" : "测定值(MPa)"
        	},{
        		"data" : "isqualifiedtest" , "title" : "结果判定",
        		render: function(data, type, row, meta) {
   				 if (data == '1') {
   		                data = "合格";
   		            }else if (data == '0'){
   		            	data = '<span style="color: red;">不合格</span>';
   		            }else{
   		            	data = '<span style="color: red;">未判定</span>';
   		            }
   		            return data;
   		        }
        	},{
        		"data" : "id" , "title" : "详情",
    			render: function(data, type, row) {
    				var html = '';
    				if(row.testtable == 'Test04006T0'){
    					html ="<span><a target='_blank' href='../item_pages/shiyan01.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500101T0'){
    					// 水泥混凝土抗压强度试验（立方体）
    					html ="<span><a target='_blank' href='../item_pages/shiyan02.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500102T0'){
    					// 砂浆抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan03.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test07003T0'){
    					// 无机结合料稳定材料无侧限抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan04.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test08001T0'){
    					// 沥青三大指标试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan05.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0900101T0'){
    					// 沥青混合料马歇尔稳定度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan06.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000101T0'){
    					// 钢筋抗拉强度、屈服强度、伸长率、冷弯
    					html ="<span><a target='_blank' href='../item_pages/shiyan07.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000201T0'){
    					// 钢筋接头抗拉强度、冷弯试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan08.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200101T0'){
    					// 粗集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan21.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200102T0'){
    					// 细集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan25.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02015T0'){
    					// 粗集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan22.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02006T0'){
    					// 细集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan26.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02002T0'){
    					// 粗集料针、片状颗粒含量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan23.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200301T0'){
    					// 粗集料压碎值试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan24.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test04003T0'){
    					// 水泥凝结时间
    					html ="<span><a target='_blank' href='../item_pages/shiyan27.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0201T0'){	
    					// 粗集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan28.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0202T0'){
    					// 细集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan29.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else{
    					html ="<span><a href='#'>查看</a></span>";
    				}
                    return html;
                }
        	}
        ];
		targets = [0,1,2,3,4,5,6,7,8,9,,17,18];
	}else if(param.testName == "钢筋拉伸强度、屈服强度、伸长率、冷弯试验"){
		url = "../../TestAutoCollection/getKQSLList.action";
		columns = [
        	{
        		"data" : "rownum" , "title" : "序号"
        	},{
        		"data" : "testroomname" , "title" : "试验室名称"
        	},{
        		"data" : "testname" , "title" : "试验名称"
        	},{
        		"data" : "samplecode" , "title" : "样品编号"
        	},{
        		"data" : "strengthgrade" , "title" : "钢筋等级或牌号"
        	},{
        		"data" : "purpose" , "title" : "工程部位"
        	},{
        		"data" : "testdate" , "title" : "试验日期"
        	},{
        		"data" : "testoperator" , "title" : "试验员"
        	},{
        		"data" : "samplecount" , "title" : "试件数量"
        	},{
        		"data" : "yieldstrth1" , "title" : "屈服强度1(MPa)"
        	},{
        		"data" : "yieldstrth2" , "title" : "屈服强度2(MPa)"
        	},{
        		"data" : "tensilestrength1" , "title" : "抗拉强度1(MPa)"
        	},{
        		"data" : "tensilestrength2" , "title" : "抗拉强度2(MPa)"
        	},{
        		"data" : "isqualifiedtest" , "title" : "结果判定",
        		render: function(data, type, row, meta) {
   				 if (data == '1') {
   		                data = "合格";
   		            }else if (data == '0'){
   		            	data = '<span style="color: red;">不合格</span>';
   		            }else{
   		            	data = '<span style="color: red;">未判定</span>';
   		            }
   		            return data;
   		        }
        	},{
        		"data" : "id" , "title" : "详情",
    			render: function(data, type, row) {
    				var html = '';
    				if(row.testtable == 'Test04006T0'){
    					html ="<span><a target='_blank' href='../item_pages/shiyan01.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500101T0'){
    					// 水泥混凝土抗压强度试验（立方体）
    					html ="<span><a target='_blank' href='../item_pages/shiyan02.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500102T0'){
    					// 砂浆抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan03.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test07003T0'){
    					// 无机结合料稳定材料无侧限抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan04.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test08001T0'){
    					// 沥青三大指标试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan05.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0900101T0'){
    					// 沥青混合料马歇尔稳定度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan06.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000101T0'){
    					// 钢筋抗拉强度、屈服强度、伸长率、冷弯
    					html ="<span><a target='_blank' href='../item_pages/shiyan07.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000201T0'){
    					// 钢筋接头抗拉强度、冷弯试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan08.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200101T0'){
    					// 粗集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan21.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200102T0'){
    					// 细集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan25.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02015T0'){
    					// 粗集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan22.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02006T0'){
    					// 细集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan26.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02002T0'){
    					// 粗集料针、片状颗粒含量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan23.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200301T0'){
    					// 粗集料压碎值试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan24.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test04003T0'){
    					// 水泥凝结时间
    					html ="<span><a target='_blank' href='../item_pages/shiyan27.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0201T0'){	
    					// 粗集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan28.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0202T0'){
    					// 细集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan29.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else{
    					html ="<span><a href='#'>查看</a></span>";
    				}
                    return html;
                }
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	}
        ];
		targets = [0,1,2,3,4,5,6,7,8,13,14];
	}else if(param.testName == "钢筋接头拉伸强度、冷弯试验"){
		url = "../../TestAutoCollection/getKLList.action";
		columns = [
        	{
        		"data" : "rownum" , "title" : "序号"
        	},{
        		"data" : "testroomname" , "title" : "试验室名称"
        	},{
        		"data" : "testname" , "title" : "试验名称"
        	},{
        		"data" : "samplecode" , "title" : "样品编号"
        	},{
        		"data" : "strengthgrade" , "title" : "钢筋等级或牌号"
        	},{
        		"data" : "purpose" , "title" : "工程部位"
        	},{
        		"data" : "testdate" , "title" : "试验日期"
        	},{
        		"data" : "testoperator" , "title" : "试验员"
        	},{
        		"data" : "samplecount" , "title" : "试件数量"
        	},{
        		"data" : "tensilestrength1" , "title" : "抗拉强度1(MPa)"
        	},{
        		"data" : "tensilestrength2" , "title" : "抗拉强度2(MPa)"
        	},{
        		"data" : "isqualifiedtest" , "title" : "结果判定",
        		render: function(data, type, row, meta) {
   				 if (data == '1') {
   		                data = "合格";
   		            }else if (data == '0'){
   		            	data = '<span style="color: red;">不合格</span>';
   		            }else{
   		            	data = '<span style="color: red;">未判定</span>';
   		            }
   		            return data;
   		        }
        	},{
        		"data" : "id" , "title" : "详情",
    			render: function(data, type, row) {
    				var html = '';
    				if(row.testtable == 'Test04006T0'){
    					html ="<span><a target='_blank' href='../item_pages/shiyan01.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500101T0'){
    					// 水泥混凝土抗压强度试验（立方体）
    					html ="<span><a target='_blank' href='../item_pages/shiyan02.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500102T0'){
    					// 砂浆抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan03.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test07003T0'){
    					// 无机结合料稳定材料无侧限抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan04.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test08001T0'){
    					// 沥青三大指标试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan05.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0900101T0'){
    					// 沥青混合料马歇尔稳定度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan06.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000101T0'){
    					// 钢筋抗拉强度、屈服强度、伸长率、冷弯
    					html ="<span><a target='_blank' href='../item_pages/shiyan07.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000201T0'){
    					// 钢筋接头抗拉强度、冷弯试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan08.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200101T0'){
    					// 粗集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan21.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200102T0'){
    					// 细集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan25.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02015T0'){
    					// 粗集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan22.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02006T0'){
    					// 细集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan26.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02002T0'){
    					// 粗集料针、片状颗粒含量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan23.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200301T0'){
    					// 粗集料压碎值试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan24.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test04003T0'){
    					// 水泥凝结时间
    					html ="<span><a target='_blank' href='../item_pages/shiyan27.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0201T0'){	
    					// 粗集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan28.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0202T0'){
    					// 细集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan29.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else{
    					html ="<span><a href='#'>查看</a></span>";
    				}
                    return html;
                }
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	}
        ];
		targets = [0,1,2,3,4,5,6,7,8,11,12];
	}else if(param.testName == "沥青针入度试验"){
		url = "../../TestAutoCollection/getLQZRList.action";
		columns = [
        	{
        		"data" : "rownum" , "title" : "序号"
        	},{
        		"data" : "testroomname" , "title" : "试验室名称"
        	},{
        		"data" : "testname" , "title" : "试验名称"
        	},{
        		"data" : "samplecode" , "title" : "样品编号"
        	},{
        		"data" : "asphaltype" , "title" : "沥青种类"
        	},{
        		"data" : "asphaltgrade" , "title" : "沥青等级"
        	},{
        		"data" : "purpose" , "title" : "工程部位"
        	},{
        		"data" : "testdate" , "title" : "试验日期"
        	},{
        		"data" : "testoperator" , "title" : "试验员"
        	},{
        		"data" : "temperature" , "title" : "试验温度(℃)"
        	},{
        		"data" : "samplecount" , "title" : "试件数量"
        	},{
        		"data" : "penetration1" , "title" : "针入度1(0.1mm)"
        	},{
        		"data" : "penetration2" , "title" : "针入度2(0.1mm)"
        	},{
        		"data" : "penetration3" , "title" : "针入度3(0.1mm)"
        	},{
        		"data" : "avgpenetration" , "title" : "针入度平均值(0.1mm)"
        	},{
        		"data" : "isqualifiedtest" , "title" : "结果判定",
        		render: function(data, type, row, meta) {
   				 if (data == '1') {
   		                data = "合格";
   		            }else if (data == '0'){
   		            	data = '<span style="color: red;">不合格</span>';
   		            }else{
   		            	data = '<span style="color: red;">未判定</span>';
   		            }
   		            return data;
   		        }
        	},{
        		"data" : "id" , "title" : "详情",
    			render: function(data, type, row) {
    				var html = '';
    				if(row.testtable == 'Test04006T0'){
    					html ="<span><a target='_blank' href='../item_pages/shiyan01.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500101T0'){
    					// 水泥混凝土抗压强度试验（立方体）
    					html ="<span><a target='_blank' href='../item_pages/shiyan02.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500102T0'){
    					// 砂浆抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan03.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test07003T0'){
    					// 无机结合料稳定材料无侧限抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan04.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test08001T0'){
    					// 沥青三大指标试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan05.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0900101T0'){
    					// 沥青混合料马歇尔稳定度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan06.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000101T0'){
    					// 钢筋抗拉强度、屈服强度、伸长率、冷弯
    					html ="<span><a target='_blank' href='../item_pages/shiyan07.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000201T0'){
    					// 钢筋接头抗拉强度、冷弯试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan08.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200101T0'){
    					// 粗集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan21.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200102T0'){
    					// 细集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan25.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02015T0'){
    					// 粗集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan22.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02006T0'){
    					// 细集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan26.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02002T0'){
    					// 粗集料针、片状颗粒含量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan23.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200301T0'){
    					// 粗集料压碎值试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan24.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test04003T0'){
    					// 水泥凝结时间
    					html ="<span><a target='_blank' href='../item_pages/shiyan27.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0201T0'){	
    					// 粗集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan28.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0202T0'){
    					// 细集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan29.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else{
    					html ="<span><a href='#'>查看</a></span>";
    				}
                    return html;
                }
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	}
        ];
		targets = [0,1,2,3,4,5,6,7,8,9,10,14,16];
	}else if(param.testName == "沥青软化点试验"){
		url = "../../TestAutoCollection/getLQRHDList.action";
		columns = [
        	{
        		"data" : "rownum" , "title" : "序号"
        	},{
        		"data" : "testroomname" , "title" : "试验室名称"
        	},{
        		"data" : "testname" , "title" : "试验名称"
        	},{
        		"data" : "samplecode" , "title" : "样品编号"
        	},{
        		"data" : "asphaltype" , "title" : "沥青种类"
        	},{
        		"data" : "asphaltgrade" , "title" : "沥青等级"
        	},{
        		"data" : "purpose" , "title" : "工程部位"
        	},{
        		"data" : "testdate" , "title" : "试验日期"
        	},{
        		"data" : "testoperator" , "title" : "试验员"
        	},{
        		"data" : "temperature" , "title" : "试验温度(℃)"
        	},{
        		"data" : "samplecount" , "title" : "试件数量"
        	},{
        		"data" : "softenpoint1" , "title" : "软化点1(℃)"
        	},{
        		"data" : "softenpoint2" , "title" : "软化点2(℃)"
        	},{
        		"data" : "avgsoftenpoint" , "title" : "软化点平均值(℃)"
        	},{
        		"data" : "isqualifiedtest" , "title" : "结果判定",
        		render: function(data, type, row, meta) {
   				 if (data == '1') {
   		                data = "合格";
   		            }else if (data == '0'){
   		            	data = '<span style="color: red;">不合格</span>';
   		            }else{
   		            	data = '<span style="color: red;">未判定</span>';
   		            }
   		            return data;
   		        }
        	},{
        		"data" : "id" , "title" : "详情",
    			render: function(data, type, row) {
    				var html = '';
    				if(row.testtable == 'Test04006T0'){
    					html ="<span><a target='_blank' href='../item_pages/shiyan01.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500101T0'){
    					// 水泥混凝土抗压强度试验（立方体）
    					html ="<span><a target='_blank' href='../item_pages/shiyan02.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500102T0'){
    					// 砂浆抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan03.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test07003T0'){
    					// 无机结合料稳定材料无侧限抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan04.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test08001T0'){
    					// 沥青三大指标试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan05.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0900101T0'){
    					// 沥青混合料马歇尔稳定度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan06.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000101T0'){
    					// 钢筋抗拉强度、屈服强度、伸长率、冷弯
    					html ="<span><a target='_blank' href='../item_pages/shiyan07.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000201T0'){
    					// 钢筋接头抗拉强度、冷弯试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan08.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200101T0'){
    					// 粗集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan21.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200102T0'){
    					// 细集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan25.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02015T0'){
    					// 粗集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan22.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02006T0'){
    					// 细集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan26.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02002T0'){
    					// 粗集料针、片状颗粒含量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan23.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200301T0'){
    					// 粗集料压碎值试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan24.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test04003T0'){
    					// 水泥凝结时间
    					html ="<span><a target='_blank' href='../item_pages/shiyan27.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0201T0'){	
    					// 粗集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan28.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0202T0'){
    					// 细集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan29.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else{
    					html ="<span><a href='#'>查看</a></span>";
    				}
                    return html;
                }
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	}
        ];
		targets = [0,1,2,3,4,5,6,7,8,9,10,13,15];
	}else if(param.testName == "沥青混合料马歇尔试验"){
		url = "../../TestAutoCollection/getLQMXList.action";
		columns = [
        	{
        		"data" : "rownum" , "title" : "序号"
        	},{
        		"data" : "testroomname" , "title" : "试验室名称"
        	},{
        		"data" : "testname" , "title" : "试验名称"
        	},{
        		"data" : "samplecode" , "title" : "样品编号"
        	},{
        		"data" : "mixturetype" , "title" : "混合料种类"
        	},{
        		"data" : "gradationtype" , "title" : "级配类型"
        	},{
        		"data" : "purpose" , "title" : "工程部位"
        	},{
        		"data" : "testdate" , "title" : "试验日期"
        	},{
        		"data" : "testoperator" , "title" : "试验员"
        	},{
        		"data" : "samplecount" , "title" : "试件数量"
        	},{
        		"data" : "avgstab" , "title" : "稳定度平均值(kN)"
        	},{
        		"data" : "avgflow" , "title" : "流值平均值(0.1mm)"
        	},{
        		"data" : "stab" , "title" : "稳定度(kN)"
        	},{
        		"data" : "flow" , "title" : "流值(0.1mm)"
        	},{
        		"data" : "isqualifiedtest" , "title" : "结果判定",
        		render: function(data, type, row, meta) {
   				 if (data == '1') {
   		                data = "合格";
   		            }else if (data == '0'){
   		            	data = '<span style="color: red;">不合格</span>';
   		            }else{
   		            	data = '<span style="color: red;">未判定</span>';
   		            }
   		            return data;
   		        }
        	},{
        		"data" : "id" , "title" : "详情",
    			render: function(data, type, row) {
    				var html = '';
    				if(row.testtable == 'Test04006T0'){
    					html ="<span><a target='_blank' href='../item_pages/shiyan01.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500101T0'){
    					// 水泥混凝土抗压强度试验（立方体）
    					html ="<span><a target='_blank' href='../item_pages/shiyan02.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500102T0'){
    					// 砂浆抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan03.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test07003T0'){
    					// 无机结合料稳定材料无侧限抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan04.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test08001T0'){
    					// 沥青三大指标试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan05.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0900101T0'){
    					// 沥青混合料马歇尔稳定度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan06.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000101T0'){
    					// 钢筋抗拉强度、屈服强度、伸长率、冷弯
    					html ="<span><a target='_blank' href='../item_pages/shiyan07.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000201T0'){
    					// 钢筋接头抗拉强度、冷弯试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan08.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200101T0'){
    					// 粗集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan21.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200102T0'){
    					// 细集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan25.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02015T0'){
    					// 粗集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan22.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02006T0'){
    					// 细集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan26.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02002T0'){
    					// 粗集料针、片状颗粒含量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan23.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200301T0'){
    					// 粗集料压碎值试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan24.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test04003T0'){
    					// 水泥凝结时间
    					html ="<span><a target='_blank' href='../item_pages/shiyan27.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0201T0'){	
    					// 粗集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan28.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0202T0'){
    					// 细集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan29.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else{
    					html ="<span><a href='#'>查看</a></span>";
    				}
                    return html;
                }
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	}
        ];
		targets = [0,1,2,3,4,5,6,7,8,9,10,11,14,15];
	}else if(param.testName == "水泥混凝土抗压强度试验（立方体）"){
		url = "../../TestAutoCollection/getSNHNList.action";
		columns = [
        	{
        		"data" : "rownum" , "title" : "序号"
        	},{
        		"data" : "testroomname" , "title" : "试验室名称"
        	},{
        		"data" : "testname" , "title" : "试验名称"
        	},{
        		"data" : "samplecode" , "title" : "样品编号"
        	},{
        		"data" : "samplesize" , "title" : "试件尺寸(mm)"
        	},{
        		"data" : "cementgrade" , "title" : "强度等级"
        	},{
        		"data" : "purpose" , "title" : "工程部位"
        	},{
        		"data" : "testdate" , "title" : "试验日期"
        	},{
        		"data" : "age" , "title" : "龄期(天)"
        	},{
        		"data" : "testoperator" , "title" : "试验员"
        	},{
        		"data" : "samplecount" , "title" : "试件个数"
        	},{
        		"data" : "comprstrength1" , "title" : "抗压强度1(MPa)"
        	},{
        		"data" : "comprstrength2" , "title" : "抗压强度2(MPa)"
        	},{
        		"data" : "comprstrength3" , "title" : "抗压强度3(MPa)"
        	},{
        		"data" : "compressivestrength" , "title" : "测定值(MPa)"
        	},{
        		"data" : "prop_DesignStrength" , "title" : "占设计强度(%)"
        	},{
        		"data" : "isqualifiedtest", "title" : "结果判定",
        		render: function(data, type, row, meta) {
   				 if (data == '1') {
   		                data = "合格";
   		            }else if (data == '0'){
   		            	data = '<span style="color: red;">不合格</span>';
   		            }else{
   		            	data = '<span style="color: red;">未判定</span>';
   		            }
   		            return data;
   		        }
        	},{
        		"data" : "id" , "title" : "详情",
    			render: function(data, type, row) {
    				var html = '';
    				if(row.testtable == 'Test04006T0'){
    					html ="<span><a target='_blank' href='../item_pages/shiyan01.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500101T0'){
    					// 水泥混凝土抗压强度试验（立方体）
    					html ="<span><a target='_blank' href='../item_pages/shiyan02.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0500102T0'){
    					// 砂浆抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan03.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test07003T0'){
    					// 无机结合料稳定材料无侧限抗压强度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan04.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test08001T0'){
    					// 沥青三大指标试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan05.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0900101T0'){
    					// 沥青混合料马歇尔稳定度试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan06.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000101T0'){
    					// 钢筋抗拉强度、屈服强度、伸长率、冷弯
    					html ="<span><a target='_blank' href='../item_pages/shiyan07.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test1000201T0'){
    					// 钢筋接头抗拉强度、冷弯试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan08.html?id="+row.id +"&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200101T0'){
    					// 粗集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan21.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200102T0'){
    					// 细集料筛分试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan25.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02015T0'){
    					// 粗集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan22.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02006T0'){
    					// 细集料含泥量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan26.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test02002T0'){
    					// 粗集料针、片状颗粒含量试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan23.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0200301T0'){
    					// 粗集料压碎值试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan24.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test04003T0'){
    					// 水泥凝结时间
    					html ="<span><a target='_blank' href='../item_pages/shiyan27.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0201T0'){	
    					// 粗集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan28.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else if(row.testtable == 'Test0202T0'){
    					// 细集料试验
    					html ="<span><a target='_blank' href='../item_pages/shiyan29.html?id="+row.id +"&issave=false&istestcollection=true&istestblind=false&roletype="+roletype+"&teststate="+row.teststate+"'>查看</a></span>";
    				}else{
    					html ="<span><a href='#'>查看</a></span>";
    				}
                    return html;
                }
        	},{
        		"data" : "id" , "bVisible" : false //此列不显示
        	}
        ];
		targets = [0,1,2,3,4,5,6,7,8,9,10,,16,17];
	}else{
		swal({
			title: "没有该试验的自动采集的数据!",
			text: "",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		},function(){
			location.reload();
		});
	}
	$("#listTable").DataTable({
		"paging": true,
        "lengthChange": false,
        "pageLength": 14,
        "searching": false,
        "ordering": false,
        "info": true,
        "sInfo": true,
        "autoWidth": false,
        "iDisplayStart" : 0,
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
			type : "post",
			url : url,
			dataSrc : "data",
			data : function(d) {
				return param;
			}
		},
        "deferRender": true,
        "columns": columns,
        "createdRow" : function(row, data, dataIndex) {
        	console.log(data);
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		},{
			targets: targets, //要合并的列数（第1，2，3 列）
            createdCell: function (td, cellData, rowData, row, col) { //重要的操作可以合并列的代码
                var rowspan = rowData.merge;//这里主要是利用了模拟数据中的merge来控制
                if (rowspan > 1) {
                    $(td).attr('rowspan', rowspan)
                }
                if (rowspan == 0) {
                    $(td).remove();
                }
            }
		}]
    });
	$('.lq_biao .row:first-child').css('display','none');
	$('#list_info').css('text-align','center');
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}
*/
//查询
$('#select').on('click', function(event) {
	//获取列表 刷新
	var table = $('#list').dataTable();
	table.fnDestroy();
	// 试验名称id
	var testNameId = $("#testPageInfosList option:selected").val();
	//试验名称
	var testName = $("#testPageInfosList option:selected").text();
	// 开始时间
	var startTime = $("#startTime").val();
	// 结束时间
	var endTime = $("#endTime").val();
	// 试验室名称
	var authority = document.getElementsByName("authority");
	var children = document.getElementsByName("children");
	var uniqueIdentifier = "";

	if(startTime > endTime){
		swal({
			title: "开始时间大于结束时间!",
			text: "",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		// 下框框清空
		$("#testPageInfosList option:first").prop("selected", 'selected');
		// 开始时间
		$("#startTime").val("");
		// 结束时间
		$("#endTime").val("");
		var param = {};
		param.testNameId = '';
		param.testName = '';
		param.startTime = '';
		param.endTime = '';
		param.uniqueIdentifier = uniqueidentifiers;
		// 初始化列表数据
		load(param);
		//getInfoList(param);
	}else if(endTime != '' && startTime == ''){
		swal({
			title: "请选择开始时间!",
			text: "",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		// 下框框清空
		$("#testPageInfosList option:first").prop("selected", 'selected');
		// 开始时间
		$("#startTime").val("");
		// 结束时间
		$("#endTime").val("");
		var param = {};
		param.testNameId = '';
		param.testName = '';
		param.startTime = '';
		param.endTime = '';
		param.uniqueIdentifier = uniqueidentifiers;
		// 初始化列表数据
		load(param);
		//getInfoList(param);
	}else{		
		// 判断试验室是否选中
		var flag = false;
		for (var j = 0; j < authority.length; j++) {
			// 获取选中项
			if (authority[j].checked) {
				flag = true;
				if(j < authority.length - 1){			
					uniqueIdentifier += authority[j].value + ",";
				}else{
					uniqueIdentifier += authority[j].value;
				}
			}
		}
		
		// 如果父类有勾选
		if(uniqueIdentifier != ""){
			uniqueIdentifier += ",";
		}
		for (var j = 0; j < children.length; j++) {
			// 获取选中项
			if (children[j].checked) {
				flag = true;
				if(j < children.length - 1){			
					uniqueIdentifier += children[j].value + ",";
				}else{
					uniqueIdentifier += children[j].value;
				}
			}
		}
		
		// 提示信息
		if(!flag){
			swal({
				title: "请选择一个试验室",
				text: "",
				type: "error",
				confirmButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			});
			// 下框框清空
			$("#testPageInfosList option:first").prop("selected", 'selected');
			// 开始时间
			$("#startTime").val("");
			// 结束时间
			$("#endTime").val("");
			var param = {};
			param.testNameId = '';
			param.testName = '';
			param.startTime = '';
			param.endTime = '';
			param.uniqueIdentifier = uniqueidentifiers;
			// 初始化列表数据
			load(param);
			//getInfoList(param);
		}else{
			var param = {};
			param.testNameId = testNameId;
			param.testName = testName;
			param.startTime = startTime;
			param.endTime = endTime;
			param.uniqueIdentifier = uniqueIdentifier;
			// 初始化列表数据
			load(param);
			//getInfoList(param);
		}
	}		
});

//获取试验名称
function getTestNameList1(){
	// 获取试验名称
	$.ajax({
		type : "POST",
		url : "../../TestRecevied/getTestNameList.action",
		data : {"isAutoTest":'1'},
		dataType : "json",
		success : function(data) {			
			var html = "<option value=''>请选择</option>";
			for (var i = 0; i < data.length; i++) {
//				var sel = "";
//				if(data[i].id == 2){
//					sel = "selected";
//				}
				html += "<option value='" + data[i].id + "'>"
						+ data[i].testname + "</option>"
			}
			// 清空操作
			$("#testPageInfosList").empty();
			$("#testPageInfosList").html(html);
		}
	});
}