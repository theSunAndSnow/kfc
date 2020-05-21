/*
 * @Descripttion: 
 * @version: 
 * @Author: wangziyang
 * @Date: 2020-05-20 17:20:49
 * @LastEditors: wangziyang
 * @LastEditTime: 2020-05-21 14:09:28
 */ 

$(function() {

    var un = null;
    var pw = null;

    // TODO:竟然无法用这种方式使用 click？？？？
    // $(".btn").click = function() {
    //     console.log("sdfgrd");
    // };

    // $(".btn").on("click", function() {
    //     un = $("input[name='username']").val();
    //     pw = $("input[name='password']").val();
    //     console.log(un, pw);
    //     var returnStatus = null;
    //     $.post(
    //         "loginServlet",
    //         {username : un, password : pw},
    //         function(data) {
    //             console.log("连接成功！");
    //             var obj = data;
    //             console.log(obj);
    //             if (obj.status == false) {
    //                 console.log("用户名或密码错误！请重新登陆！");
    //                 alert("用户名或密码错误！请重新登陆！");
    //                 returnStatus = false;
    //             }
    //             // else if(obj.status == true){
    //             //     console.log("用户账户密码正确");
    //             //     returnStatus = true;
    //             // };
    //         },
    //         "json"
    //     );

    //     // if (returnStatus) {
    //     //     window.location.href("order.html");
    //     //     console.log("returnStatus");
    //     // } else {
    //     //     window.location.href("login.html");
    //     // }
    // });

})
