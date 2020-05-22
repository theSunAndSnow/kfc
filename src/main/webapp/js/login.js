/*
 * @Descripttion: 
 * @version: 
 * @Author: wangziyang
 * @Date: 2020-05-20 17:20:49
 * @LastEditors: wangziyang
 * @LastEditTime: 2020-05-22 16:41:15
 */ 

$(function() {

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


    $("#doLogin").on("click", function() {

        // 将内容切换成登陆页面
        $(".content-body-register").prop("hidden", true);
        $(".content-body-login").prop("hidden", false);

        $("#doLogin").css({
            background : "#e0e0e0"
        });

        $("#doRegister").css({
            background : "white"
        });
    });

    $("#doRegister").on("click", function() {
        $(".content-body-register").prop("hidden", false);
        $(".content-body-login").prop("hidden", true);

        $("#doLogin").css({
            background : "white"
        });

        $("#doRegister").css({
            background : "#e0e0e0"
        });
    });

});

document.getElementById("register").addEventListener("submit", register);
function register(e) {
    
    e.preventDefault(); //如果 type 属性是 "submit"，在事件传播的任意阶段可以调用任意的事件句柄，通过调用该方法，可以阻止提交表单。
    var telephone = $('#telephone').val();
    var password = $('#password').val();
    var name = $('#name').val();
    var sex = $('#sex').val();

    var params = "?telephone=" + telephone + 
        "&password=" + password + 
        "&name=" + name +
        "&sex=" + sex;

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/registerServlet" + params, true); // 我搞不到这里 true 是否还能发挥作用……

    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && (this.status == 200 || this.status == 302)) {
            console.log(this.responseText);
            var resp = JSON.parse(this.responseText);
            console.log(resp);
            console.log("注册状态" + resp.status);
            if (resp.status === true) {
                alert("注册成功！请登陆！");
            } else {
                alert("注册失败！手机号已被注册，请使用其他手机号！");
            }
        }
    }

    // xhr.onload = function() {
    //     console.log(this.responseText);
    //     var resp = JSON.parse(this.responseText);
    //     console.log(resp);
    //     if (resp.status === true) {
    //         alert("success");
    //     } else {
    //         alert("false");
    //     }
    // }
    xhr.send();
    
};
