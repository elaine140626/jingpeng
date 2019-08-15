
//表格可编辑
function edit(data) {
	//找到当鼠标点击单元格时，对应的dom 对象（单元格）
	var tdObj = $(data);
	if(tdObj.children("input").length > 0) {
		return false;

	}
	//获取当前单元格的内容
	var text = tdObj.html();

	//创建一个文本框
	var inputObj = $("<input type='text'>");

	//去掉文本框的边框;设置文本框的文字大小和整体一样
	inputObj.css("border-with", "0").css("font-size", "16px")

	//使文本框的宽度和td的宽度相同
	inputObj.width(tdObj.width());

	//设置文本框的背景色
	inputObj.css("background", tdObj.css("background-clor"));

	//需要将当前td中的内容放入到文本框中
	inputObj.val(text);

	//清空td中的内容
	tdObj.html("");

	//将文本框插入到对应的td(单元格)中
	inputObj.appendTo(tdObj);

	//文本框插入后被选中
	inputObj.trigger("focus").trigger("select");

	//单元格触发单击事件没有任何返回值
	inputObj.click(function() {
		return false;
	});

	//处理文本框上回车和Esc事件
	inputObj.blur(function(event) {
		//获取当前文本框中的内容
		var inputtext = inputObj.val();
		//将td的内容修改成文本框中的内容
		tdObj.html(inputtext);
		return false;
	});
}

function showselectText(data) {
	var selectionParent = $(data).parent('td');
	var selectionNum = $(data).find("option:selected").val();
	var textObj = $(data).siblings('.ui_select.ui_selectText')
	if(selectionNum == 0) {
		textObj.hide()
	}else if(selectionNum == 1) {
		textObj.show();
	}
}
function mixText(Obj,data,flag) {
	$(Obj).blur(function(){
		if(flag == 1) {
			
		}
	})
	
}
