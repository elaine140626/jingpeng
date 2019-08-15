// 选择的展示规格
var displayType;
// 选中的图表
var chartArr= [];
// 加载layui的form组件，以显示页面筛选条件绑定的组件
var form;
layui.use('form', function(){
	form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
	// 单选框点击事件
	form.on('radio(displayType)', function(data){
		displayType = data.value;
		var checkbox = $("input[name='chart']");
		checkbox.prop("checked", false);
		chartArr = [];
		form.render('checkbox');
	}); 
	// 复选框点击事件
	form.on('checkbox(showChart)', function(data){
		var value = data.value;
		// 选中的图表数小于选择的显示规格时，可以继续选择图表，并将本次选择的图加入已选数组中
		if (data.elem.checked) {
			if (chartArr.length < displayType) {
				chartArr.push(value);
			} else {
				$("#" + value).prop("checked", false);
				layer.open({
				  title: '温馨提示'
				  ,content: '选择的图表已超过当前选择的显示规格！'
				});  
			}
		} else {
			// 从数组中删除取消勾选的项
			var index = chartArr.indexOf(value);
			chartArr.splice(index, 1);
		}
		form.render('checkbox');
	});
	form.render();
});  

$(function(){
	displayType = $("input[name='displayType']:checked").val();
	
	$.ajax({
		type : "POST",
		url :  "../../testInfo/queryAllChart.action",
		async : false,
		data : {
			systemCode: "display_type"
		},
		dataType : "json",
		success : function(data) {
			if (data.length > 0) {
				$("input[name=displayType][value="+data[0].Value+"]").attr("checked", true);
				displayType = data[0].Value;
				form.render('radio');
			}
		}
	});
	
	$.ajax({
		type : "POST",
		url :  "../../testInfo/queryAllChart.action",
		async : false,
		data : {
			systemCode: "chart_type"
		},
		dataType : "json",
		success : function(data) {
			if (data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					$("#" + data[i].Name).attr("checked", "checked");
					chartArr.push(data[i].Name);
					form.render('checkbox');
				}
			}
		}
	});
});

// 提交
function submit() {
	$.ajax({
		type : "POST",
		url :  "../../testInfo/updateScreenDisplay.action",
		async : false,
		data : {
			"displayType": displayType,
			"chartArr": chartArr
		},
		dataType : "json",
		success : function(data) {
			layer.msg('保存成功');
		}
	});
}