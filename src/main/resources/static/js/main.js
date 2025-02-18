$(function(){
    //swiper 추가하기
    const swiper = new Swiper(".mainProjectSwiper", {
        slidesPerView: 3,
        spaceBetween: 24,
        centeredSlides: true,
        loop: true,
        autoplay: {
            delay: 5000,
            disableOnInteraction: false,
        },
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        },
    });

    $(".projectListWrap .tabWrap li").click(function(){
       let tabId = $(this).children("a").attr("href");
       //alert(tabId);
        $(".projectListWrap .tabWrap li").removeClass("active");
        $(this).addClass("active");

        $(".projectListWrap .list").removeClass("active");
        $(tabId).addClass("active");
    });



});