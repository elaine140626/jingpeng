$(document).ready(function() {
	var tankArr = [{
		"name": "基质沥青",
		"data": 200,
		"percent": 50,
		"tank": "储罐1",
		"color": "#48f1b6"
	}, {
		"name": "基质沥青",
		"data": 365,
		"percent": 25,
		"tank": "储罐2",
		"color": "#48f1b6"
	}, {
		"name": "基质沥青",
		"data": 80,
		"percent": 60,
		"tank": "储罐3",
		"color": "#48f1b6"
	}, {
		"name": "基质沥青",
		"data": 300,
		"percent": 90,
		"tank": "储罐4",
		"color": "#48f1b6"
	}, {
		"name": "改性乳化沥青",
		"data": 69,
		"percent": 100,
		"tank": "储罐5",
		"color": "#fccd00"
	}, {
		"name": "改性乳化沥青",
		"data": 100,
		"percent": 35,
		"tank": "储罐6",
		"color": "#fccd00"
	}];
	var inventoryArr = [{
		"data": 750,
		"percent": 20,
		"inventory": "仓库一",
		"color": "#ff4848"
	}, {
		"data": 250,
		"percent": 19,
		"inventory": "仓库二",
		"color": "#ff4848"
	}, {
		"data": 965,
		"percent": 46,
		"inventory": "仓库三",
		"color": "#ff4848"
	}, {
		"data": 430,
		"percent": 50,
		"inventory": "仓库四",
		"color": "#ff4848"
	}, {
		"data": 550,
		"percent": 70,
		"inventory": "仓库五",
		"color": "#ff4848"
	}, ];

	for(var i = 0; i < tankArr.length; i++) {
		$('.tank_item' + (i + 1)).waterTank({
			width: 100,
			height: 130,
			color: tankArr[i].color,
			level: tankArr[i].percent
		})
	}
	for(var i = 0; i < inventoryArr.length; i++) {
		$('.inventory_item' + (i + 1)).waterTank({
			width: 140,
			height: 130,
			color: inventoryArr[i].color,
			level: inventoryArr[i].percent
		})
	}

})