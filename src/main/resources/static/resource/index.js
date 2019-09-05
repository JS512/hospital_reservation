$(function () {
    $('.best-item.best-item-hover > .best-item-menu > ul > li > a').click(function() {
        return false;
    })
    
    //베스트 메뉴
    $('.best-item.best-item-hover > .best-item-menu > ul > li').mouseenter(function () {
        var $clickedBtn = $(this);

        var no = $clickedBtn.index() + 1;

        $clickedBtn.siblings('active').removeClass('active');
        $clickedBtn.addClass('active');

        var $bestItem = $clickedBtn.parent().parent().parent();

        $bestItem.find(' > .best-item-list > .best-item-list-1.active').removeClass('active');
        $bestItem.find(' > .best-item-list > .best-item-list-1:nth-child(' + no + ')').addClass('active');
    });
    
    //퀵메뉴
    var $퀵메뉴 = $('.qick');

    $(window).scroll(function () {
        var scrollTop = $(window).scrollTop();

        $퀵메뉴.css('top', scrollTop + 150 + 'px');
    });

    
    $(window).scroll();
    
    //가운데 메인 슬라이더
    var opts = {
        effect: 'random',
        animSpeed: 200,
        pauseTime: 3000,
    };
    opts['controlNav'] = true;
    opts['captionOpacity'] = 0.7;
    $('#nivoSlider1').nivoSlider(opts);

    //오른쪽 슬라이더    
    $('.carousel-1 > .owl-carousel').owlCarousel({
        autoplay: true, // 오토 플레이
        autoplayTimeout: 2000, // 오토 플레이 시에 다음 슬라이드로 넘어가는 주기, 2초
        autoplayHoverPause: true, // 마우스 올리면 오토 플레이 멈춤
        loop: true, // 끝에서 다시 처음으로 시작
        margin: 0,
        nav: true,
        navText: ['<i class="fas fa-angle-left"></i>', '<i class="fas fa-angle-right"></i>'],
        dots: false,
        responsive: {
            0: {
                items: 1
            }
        }
    });
});
