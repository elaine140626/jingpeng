// 树形结构显示
function showZtreeS(cityId,countyId){
	var zTree;
	var setting = {
			view: {
				dblClickExpand: false,
				showLine: false,
				selectedMulti: false
			},
			data : {
				simpleData : {
					enable : true,
				}
			},
			callback: {
				onClick: findClick
			}
		};

	// 节点集合
	var nodes = [];
	// 一级树 市 的父节点
	var pid = 0;
	// 二级树 县 的父节点
	var ppid = '';
	// 三级树 项目 的父节点
	var pppid = '';
	var node = {};
	
	$.ajax({
		type: "post",
		url: "../../InitialInfo/getEntryTreeList.action",
		data: {
			"cityId":cityId,
			"countyId":countyId
			},
		dataType: "json",
		success: function (data) {
			if (data != null && data.length > 0) {
				node = {
					id : 0,
					pId : 0,
					name : "辽宁省交通运输事业发展中心工程处",
					open : true
				};
				nodes.push(node);
				for (var i = 0; i < data.length; i++) {					
					// 一级树市变更时
					if (data[i].ciid != ppid) {					
						// 一级树 市
						if (data[i].ciid != null && data[i].ciid != '') {
							node = {
								id : data[i].ciid,
								pId : 0,
								name : data[i].cityName
							};
							nodes.push(node);
							
							// 县 的父节点-市
							ppid = data[i].ciid;
						}						
					} 
					//  二级树 县 变更时
					if (data[i].couid != pppid) {
						// 二级树 市
						if (data[i].couid != null && data[i].couid != '') {
							node = {
								id : data[i].couid,
								pId : ppid,
								name : data[i].county
							};
							nodes.push(node);
							
							// 项目 的父节点-县
							pppid = data[i].couid;
						}
						
					} 
					// 三级树 客户
					if (data[i].pid != null && data[i].pid != '') {
						node = {
							id : data[i].pid,
							pId : pppid,
							name : data[i].projectName
						};
						nodes.push(node);
					}				
				} 
			}
			var t = $("#EntryTree");
			t = $.fn.zTree.init(t, setting, nodes);
		}
	});
}


// 根据项目id 查询项目信息
function getVEntryTree(treeIds){
	var treeList;
	$.ajax({
		type: "post",
		url: "../../InitialInfo/getVEntryTree.action",
		data: {"ProjectId":treeIds},
		dataType: "json",
		async:false, // 同步
		success: function (data) {
			if (data != null){
				treeList = data[0];
			}
		}
	});
	return treeList;
}