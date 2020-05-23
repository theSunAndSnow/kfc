/*
 * @Descripttion: 
 * @version: 
 * @Author: wangziyang
 * @Date: 2020-05-23 14:07:20
 * @LastEditors: wangziyang
 * @LastEditTime: 2020-05-24 04:41:42
 */ 

$(function() {
    $('.activities>li').mouseenter(function () { 
        $(this).find('p').prop('hidden', false);
    });
    $('.activities>li').mouseleave(function () { 
        $(this).find('p').prop('hidden', true);
    });

    var imgs = document.querySelectorAll(".ImagesOfActivity img");
    var loop = 0;
    setInterval(function() {
        loop += 1;
        loop %= 2;
        switchImg();

    }, 2000);

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
})