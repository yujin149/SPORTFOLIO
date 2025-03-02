$(function() {
    // tab
    $(function(){
        $(".tabWrap li a").click(function(event){
            event.preventDefault();
            let tabId = $(this).attr("href");
            //alert(tabId);

            $(".tabWrap li a").removeClass("active");
            $(this).addClass("active");

            $(".tabCont").removeClass("active");
            $(tabId).addClass("active");
        });
    });

    $(".skillTabWrap li a").click(function(event){
        event.preventDefault();
        let tabId = $(this).attr("href");
        //alert(tabId);

        $(".skillTabWrap li a").removeClass("active");
        $(this).addClass("active");

        $(".skillCont").removeClass("active");
        $(tabId).addClass("active");
    });
    //
    // $(".percent .num").each(function() {
    //     var value = parseInt($(this).text());
    //     $(this).prop('Counter', 0).animate({
    //         Counter: value
    //     }, {
    //         duration: 2000,
    //         easing: 'swing',
    //         step: function (now) {
    //             $(this).text(Math.ceil(now) + '%');
    //         }
    //     });
    // });
});

