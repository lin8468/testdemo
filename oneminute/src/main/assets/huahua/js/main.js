
//当前是否在画
var draw = true;
//橡皮擦的宽度
var eraserWidth = 30;
var canvas;
var ctx;
//画笔颜色
var color = "black";
//画笔粗细
var weight = 5;
//声明绘图开始的坐标
var startX;
var startY;
var endX;
var endY;
//页面加载结束之后会调用的方法
$(document).ready(function() {
	//同步像素
	setPixel();
	//监听绘图的方法
	readyToDraw();
	//设置颜色按钮
	setColors();
	//调用weight监听
	setWeight();
})

function setPixel() {

	canvas = document.getElementById("canvas");

	ctx = canvas.getContext("2d");

	canvas.width = window.innerWidth;

	canvas.height = window.innerHeight;
}

function readyToDraw() {
	//为canvas添加监听
	$(canvas).bind("touchstart touchmove touchend", function(event) {
		//判断事件类型
		switch (event.type) {
			case "touchstart":
				startX = event.originalEvent.targetTouches[0].clientX;
				startY = event.originalEvent.targetTouches[0].clientY;
				if (!draw) {
					$(".era").removeClass("hidden").addClass("show");
					$(".era").css({"left": startX - eraserWidth*0.5 + "px","top": startY - eraserWidth*0.5 + "px"});
					ctx.clearRect(startX - eraserWidth*0.5, startY - eraserWidth*0.5, eraserWidth, eraserWidth);
				}
				break;
			case "touchmove":
				endX = event.originalEvent.targetTouches[0].clientX;
				endY = event.originalEvent.targetTouches[0].clientY;
				if (draw) {
					ctx.beginPath();
					ctx.moveTo(startX, startY);
					ctx.lineTo(endX, endY);
					ctx.lineWidth = weight;
					ctx.strokeStyle = color;
					ctx.closePath();
					ctx.stroke();
					startX = endX;
					startY = endY;
				}else{
					$(".era").css({"left": endX - eraserWidth*0.5 + "px","top": endY - eraserWidth*0.5 + "px"});
					ctx.clearRect(endX - eraserWidth*0.5, endY - eraserWidth*0.5, eraserWidth, eraserWidth);
				}
				break;
			case "touchend":
				$(".era").removeClass("show").addClass("hidden");
				break;
		}
	})
}
//让颜色按钮好使的方法
function setColors() {
	$(".red").click(function() {
		color = "red";
	})
	$(".yellow").click(function() {
		color = "yellow";
	})
	$(".blue").click(function() {
		color = "blue";
	})
	$(".green").click(function() {
		color = "green";
	})
	$(".black").click(function() {
		color = "black";
	})
	$(".purple").click(function() {
		color = "purple";
	})
	$(".eraser").click(function() {
		draw = !draw;
		if(draw){
			$(".eraser").html("橡皮擦");
		}else{
			$(".eraser").html("画笔");
		}
	})
}

function setWeight() {
	//监听滑动条的值
	$(".weight").bind("input proptyChange", function() {
		
		if(draw){
			weight = this.value;
		}else{
			//改变橡皮擦大小
			eraserWidth = this.value*10;
			$(".era").css({"width":eraserWidth+"px","height":eraserWidth+"px"});
		}
	})
}