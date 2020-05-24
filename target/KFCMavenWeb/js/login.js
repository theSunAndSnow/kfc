/*
 * @Descripttion: 
 * @version: 
 * @Author: wangziyang
 * @Date: 2020-05-20 17:20:49
 * @LastEditors: wangziyang
 * @LastEditTime: 2020-05-25 01:56:15
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

    /**
     * 动态监听用户输入的手机号是否合法
     * 若不合法则提示用户重新输入，并阻止提交表单
     */
    $('#telephone').on("input", function() {
        var telephone = $(this).val();
        // console.log(telephone.length);
        // console.log(telephone, parseInt(telephone, 10));
        // console.log( telephone.length != toString(parseInt(telephone, 10)).length )
        // console.log(parseInt(telephone, 10)+'');
        // console.log( telephone);

        var num = parseInt(telephone, 10)+''; // 若telephone为纯数字，那么 num.length == telephone.length

        if ( (10 < telephone.length && telephone.length < 12) && ( telephone.length == num.length ) ) {
            forbidden('#register>div', 0, 'h4', '账号', 'submit');
        } else if (telephone.length == 0) {
            forbidden('#register>div', 0, 'h4', '账号', 'button');
        } else {
            $('#register>div').eq(0).find('h4').html('' + 
                '<h4>账号</h4>' +
                '<span class="warning">请输入正确的手机号码</span>'
            );
            $('#sub').attr('type', 'button');
        }
    
        // if ( (telephone != 0) && ( (telephone.length < 10 || telephone.length > 12) || ( telephone.length != toString(parseInt(telephone, 10)).length ) ) ) {
        //     $('#register>div').eq(0).find('h4').html('' + 
        //         '<h4>账号</h4>' +
        //         '<span class="warning">请输入正确的手机号码</span>'
        //     );
        //     $('#sub').attr('type', 'button');
        // } else {
        //     // $('#register>div').eq(0).find('h4').html('' + 
        //     //     '<h4>账号</h4>'
        //     // );
        //     // $('#sub').attr('type', 'submit');
        //     forbidden('#register>div', 0, 'h4', '账号', 'submit');
        // }
    });


    /**
     * 监听用户是否输入密码，未输入密码无法注册
     */
    $('#password').on('input', function() {
        var password = $(this).val();
        if (password.length == 0) {
            forbidden('#register>div', 1, 'h4', '密码', 'button');
        } else {
            forbidden('#register>div', 1, 'h4', '密码', 'submit');
        }
    });

    /**
     * 监听用户是否输入了姓名，为输入姓名则无法注册
     */ 
    $('#name').on('input', function() {
        var name = $(this).val();
        if (name.length == 0) {
            forbidden('#register>div', 2, 'h4', '姓名', 'button');
        } else {
            forbidden('#register>div', 2, 'h4', '姓名', 'submit');
        }
    });

    /**
     * 监听用户输入的性别是否格式正确
     * 如果性别格式错误则无法提交表单
     */
    $('#sex').on('input', function() {
        var sex = $(this).val();
        console.log(sex);
        console.log(sex == '男');

        if ( (sex.length != 0) && (sex != '男' && sex != '女') ) {
            $('#register>div').eq(3).find('h4').html('' + 
                '<h4>性别</h4>' +
                '<span class="warning">请输入&nbsp;男&nbsp;或&nbsp;女</span>'
            );
            $('#sub').attr('type', 'button');
        }  else if (sex.length == 0) {
            forbidden('#register>div', 3, 'h4', '性别', 'button');
        } else {
            forbidden('#register>div', 3, 'h4', '性别', 'submit');
        }
    });

    function forbidden(tag, index, child, tagContent, type) {
        $(tag).eq(index).find(child).html('' + 
            '<h4>' + tagContent + '</h4>'
        );
        $('#sub').attr('type', type);
    }

});

document.getElementById("register").addEventListener("submit", register);
function register(e) {
    
    e.preventDefault(); //如果 type 属性是 "submit"，在事件传播的任意阶段可以调用任意的事件句柄，通过调用该方法，可以阻止提交表单。
    var telephone = $('#telephone').val();
    var password = $('#password').val();
    var name = $('#name').val();
    var sex = $('#sex').val();

    if (password.length == 0 || name.length == 0) {
        alert('注册信息未填写完整，无法注册！');
    } else {
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
    }

    // var params = "?telephone=" + telephone + 
    //     "&password=" + password + 
    //     "&name=" + name +
    //     "&sex=" + sex;

    // var xhr = new XMLHttpRequest();
    // xhr.open("GET", "/registerServlet" + params, true); // 我搞不到这里 true 是否还能发挥作用……

    // xhr.onreadystatechange = function() {
    //     if (this.readyState == 4 && (this.status == 200 || this.status == 302)) {
    //         console.log(this.responseText);
    //         var resp = JSON.parse(this.responseText);
    //         console.log(resp);
    //         console.log("注册状态" + resp.status);
    //         if (resp.status === true) {
    //             alert("注册成功！请登陆！");
    //         } else {
    //             alert("注册失败！手机号已被注册，请使用其他手机号！");
    //         }
    //     }
    // }

    // // xhr.onload = function() {
    // //     console.log(this.responseText);
    // //     var resp = JSON.parse(this.responseText);
    // //     console.log(resp);
    // //     if (resp.status === true) {
    // //         alert("success");
    // //     } else {
    // //         alert("false");
    // //     }
    // // }
    // xhr.send();
    
};
