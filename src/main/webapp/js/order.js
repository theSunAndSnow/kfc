/*
 * @Descripttion: 
 * @version: 
 * @Author: wangziyang
 * @Date: 2020-05-20 09:17:19
 * @LastEditors: wangziyang
 * @LastEditTime: 2020-05-21 14:56:48
 */ 
$(function() {

    var input = document.getElementsByTagName("input");

    var total = 0;
    var chickenWingNum = 0, chickenWingSetMealNum = 0,
        beerNum = 0, hamburgerNum = 0, congeeNum = 0,
        colaNum = 0;
    
    /**
     * 获取用户点击事件，每次点击都更新总付款数
     */ 
    for (var i = 0; i < input.length; ++i) {
        var j = i;

        input[j].onclick = function() {
            chickenWingNum = $("#chickenWing").val();
            chickenWingSetMealNum = $("#chickenWingSetMeal").val();
            beerNum = $("#beer").val();
            hamburgerNum = $("#hamburger").val();
            congeeNum = $("#congee").val();
            colaNum = $("#cola").val();

            total = chickenWingNum * 12.0 + chickenWingSetMealNum * 72.0 + 
                beerNum * 15.0 + hamburgerNum * 20.0 + 
                congeeNum * 10.0 + colaNum * 11.0;

                $(".totalPayment").text("合计：" + total.toFixed(1)); // 精确到小数点后 1 位
        };
    }

    /**
     * 用户点击确认购买后，将购买数据发送给后端
     */ 
    $(".buyIt").click(function() {
        $.get(
            "buyServlet",
            {
                chickenWing : chickenWingNum,
                chickenWingSetMeal : chickenWingSetMealNum,
                beer : beerNum,
                hamburger : hamburgerNum,
                congee : congeeNum,
                cola : colaNum,
            },
            function(data) {
                alert("响应成功");
                console.log(data);
            },
            "json"
        );
    }); 

    var login = function() {
        $.get(
            "loginServlet",
            {method : "status"},
            function(data) {
                if (data.status == true) {
                    $(".login").html("欢迎回来" + data.name)
                }
            }
        )
    }
});