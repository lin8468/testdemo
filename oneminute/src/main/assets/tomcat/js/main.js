
window.onload= function () {
	var audio=document.getElementById("music");

	bodyScale();

    $("#drink").bind("tap",function(){
    	act(80,"drink");
    	var musicSrc="audio/drink.wav";
        $("#music").attr({"src":musicSrc});
        audio.play();
    });

    $("#eat").bind("tap",function(){
    	act(39,"eat");
        var musicSrc="audio/eat.wav";
       $("#music").attr({"src":musicSrc});
       audio.play();
    })

    $("#pie").bind("tap",function(){
    	act(23,"pie");
    	var musicSrc="audio/pie.wav";
    	$("#music").attr("src",musicSrc);
    	setTimeout(yanchi,500);
    })
    function yanchi(){
    	// alert("11")
    	audio.play();
    }
    $("#fart").bind("tap",function(){
    	act(27,"fart");
    	var musicSrc="audio/fart.wav";
     	$("#music").attr("src",musicSrc);
     	audio.play();
    })
    
    $("#cymbal").bind("tap",function(){
    	act(12,"cymbal");
    	var musicSrc="audio/cymbal.wav";
    	$("#music").attr("src",musicSrc);
    	audio.play();
    })
    
    $("#scratch").bind("tap",function(){
    	act2(55,"scratch");
    	var musicSrc="audio/scratch.wav" ;
    	$("#music").attr("src",musicSrc);
    	setTimeout(yanchi2,1000);
    })
    function yanchi2(){
    	audio.play();
    }

};

var one=0;//初始化一个变量，用来记录图片数字的变化
var timer;//定义一个定时器

function act(count,name){   //封装act 函数,形参 
    one=0;
    clearInterval(timer);//每次调用函数前，清楚老的定时器
    timer=setInterval(function(){//开启新的定时器
        if (one<count) {  ///
            one ++;
            var imgSrc="img/"+name+"/"+name+"_"+number(one)+".jpg" ;  ////number函数//改变图片的路径
            $("#cat").attr("src",imgSrc);
        }else{//不符合条件，清楚定时器
            clearInterval(timer);
            one=0;
        }    
    },50)
}
function act2(count,name){   //封装act 函数,形参 
    one=0;
    clearInterval(timer);//每次调用函数前，清楚老的定时器
    timer=setInterval(function(){//开启新的定时器
        if (one<count) {  ///
            one ++;
            var imgSrc="img/"+name+"/"+name+"_"+number(one)+".jpg" ;  ////number函数//改变图片的路径
            $("#cat").attr("src",imgSrc);
        }else{//不符合条件，清楚定时器
            clearInterval(timer);
            one=0;
        }    
    },80)
}
//图片路径中数字的变化
function number(one){    /////改变数字的格式
    if (one<10) {
        return "0"+one;
    }
    else{
        return one;
    }
}

function bodyScale(){
	var devicewidth=document.documentElement.clientWidth;
	var scale=devicewidth/640;
	document.body.style.zoom=scale;
}


