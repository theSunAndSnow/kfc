/*
 * @Descripttion: 
 * @version: 
 * @Author: wangziyang
 * @Date: 2020-05-20 09:17:19
 * @LastEditors: wangziyang
 * @LastEditTime: 2020-05-25 17:37:56
 */ 
$(function() {

    var input = document.getElementsByTagName("input");

    /**
     * 获取用户购买的食品的数量
     */
    var total = 0;
    var chickenWingNum = 0, chickenWingSetMealNum = 0,
        beerNum = 0, hamburgerNum = 0, congeeNum = 0,
        colaNum = 0;

    var chickenWingPrice = 12.0, chickenWingSetMealPrice = 72.0,
        beerPrice = 15.0, hamburgerPrice = 20.0,
        congeePrice = 10.0, colaPrice = 11.0;
    
    /**
     * 设置活动提示页面
     * 不同的时间点提示不同的优惠活动文字
     * 获取当天的年月日后，定义6个活动时间临界点，再与当前时间进行比较
     * 将活动时间内商品的价格改变
     */
    var date = new Date();
    console.log(date);
    var yearMonthDays = date.toLocaleDateString();
    var breakfastBegin = yearMonthDays + ' 05:45:00',
        breakfastEnd = yearMonthDays + ' 09:14:00',
        dinnerBegin = yearMonthDays + ' 09:15:00',
        dinnerEnd = yearMonthDays + ' 22:44:00',
        nightSnackBegin = yearMonthDays + ' 11:00:00',
        nightSnackEnd = yearMonthDays + ' 11:59:59';
    
    var bfb = new Date(breakfastBegin),
        bfe = new Date(breakfastEnd),
        db = new Date(dinnerBegin),
        de = new Date(dinnerEnd),
        nsb = new Date(nightSnackBegin),
        nse = new Date(nightSnackEnd);

    
    // console.log(bfb, bfe, db, de, nsb, nse);

    if (bfb < date && date <= bfe) {
        congeePrice = congeePrice * 0.8;
        $('.activityPrompet').text('冬菇滑鸡');
    } else if (db <= date && date <= de) {
        chickenWingPrice = chickenWingPrice * 0.8;
        $('.activityPrompet').text('香辣鸡翅');
    } else if (nsb <= date && date <= nse) {
        chickenWingSetMealPrice = chickenWingSetMealPrice * 0.8;
        $('.activityPrompet').text('炸鸡啤酒套餐');
    }
    
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

            total = chickenWingNum * chickenWingPrice + 
                chickenWingSetMealNum * chickenWingSetMealPrice + 
                beerNum * beerPrice + 
                hamburgerNum * hamburgerPrice + 
                congeeNum * congeePrice + 
                colaNum * colaPrice;

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
                console.log("登陆成功", data);
                if (data[0].status == true) {
                    console.log(data[1].telephone);
                    $(".login").html("欢迎回来" + '<span>' + data[1].telephone + "</span>");
                }
            },
            "json"
        )
    };

    login(); // 每次页面刷新都会检查账号是否已经登陆，关闭浏览器会退出登录
    
});