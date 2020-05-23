/*
 * @Descripttion: 
 * @version: 
 * @Author: wangziyang
 * @Date: 2020-05-23 14:07:20
 * @LastEditors: wangziyang
 * @LastEditTime: 2020-05-23 21:25:17
 */ 

$(function() {
    $('.activities>li').mouseenter(function () { 
        $(this).find('p').prop('hidden', false);
    });
    $('.activities>li').mouseleave(function () { 
        $(this).find('p').prop('hidden', true);
    });
})