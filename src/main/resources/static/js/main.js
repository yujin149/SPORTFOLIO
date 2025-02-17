$(function(){
    $(".projectListWrap .tabWrap li").click(function(){
       let tabId = $(this).children("a").attr("href");
       alert(tabId);
        $(".projectListWrap .tabWrap li").removeClass("active");
        $(this).addClass("active");

        $(".projectListWrap .list").removeClass("active");
        $(tabId).addClass("active");



    });
});