/*
 * @Descripttion: 
 * @version: 
 * @Author: wangziyang
 * @Date: 2020-05-23 14:07:20
 * @LastEditors: wangziyang
 * @LastEditTime: 2020-05-29 10:03:59
 */ 

$(function() {
    // $('.activities>li').mouseenter(function () { 
    //     $(this).find('p').prop('hidden', false);
    // });
    // $('.activities>li').mouseleave(function () { 
    //     $(this).find('p').prop('hidden', true);
    // });

    $('.activities>li').mouseenter(function() {
        $(this).find('p').stop();
        $(this).find('p').slideDown(500);
    });

    $('.activities>li').mouseleave(function () { 
        $(this).find('p').stop();
        $(this).find('p').slideUp(500);
    });

    // 轮播图-start
    var imgs = document.querySelectorAll(".ImagesOfActivity img");
    var loop = 0;
    setInterval(function() {
        loop += 1;
        loop %= 2;
        switchImg();

    }, 3000);

    $('#button0').on('click', function() {
        loop = 0;
        switchImg();
    });

    $('#button1').on('click', function() {
        loop = 1;
        switchImg();
    });

    function switchImg() {
        for (var i = 0; i < imgs.length; ++i) {
            imgs[i].style.display = 'none';
        }

        imgs[loop].style.display = 'block';

        $('#button' + (loop)).addClass('currentButton');
        $('#button' + (loop + 1) % 2).removeClass('currentButton');
    }

    // 轮播图-end

    $('.right > a').css({
        display : 'inline-block',
        color: 'white',
        font: '1.6rem bold',
        'background-color': 'rgb(198, 0, 10)',
        height: '2.9rem',
        'line-height': '2.9rem',
        width: '100%',
        'text-align': 'center'
    })
})