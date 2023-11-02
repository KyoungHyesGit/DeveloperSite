(function(){
    var shrinkHeader = 100;
    $(document).ready(function() {
        $(window).scroll(function(){
            var scroll = getCurrentScroll();
            if(scroll >= shrinkHeader){
                $('.main_hd').addClass('on');
            }else{
                $('.main_hd').removeClass('on');
            }
        });
    });

    function getCurrentScroll(){
        return window.pageYOffset || document.documentElement.scrollTop;
    }
})();

