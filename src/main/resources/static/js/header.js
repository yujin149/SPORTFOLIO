$(function(){
   $(".headerWrap .headerInner .menuBtn").click(function(){
        $(this).toggleClass("active");
        $(".navWrap").toggleClass("active");
   });
});