<%@ page language="java"  pageEncoding="UTF-8"%>
<script type="text/javascript" src="${CONTEXT_PATH}/js/plugin/darsaSly/js/sly.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
    /**
     * 主导航栏
     * @type {[type]}
     */
    // Sly导航栏
    var $mainNavs = $('#mainNavs');
    var $frame = $mainNavs.find('.frame'); 
    window.frr = $frame;
    var sly = new Sly($frame, {
        horizontal: 1,
        itemNav: 'basic',
        activateMiddle: 1,
        // smart: 1,
        activateOn: 'click',
        // mouseDragging: 1,
        // touchDragging: 1,
        // releaseSwing: 1,
        // startAt: 0,
        // scrollBar: $mainNavs.find('.scrollbar'),
        // scrollBy: 1,
        // pagesBar: $mainNavs.find('.pages'),
        // activatePageOn: 'click',
        speed: 200,
        moveBy: 600,
        elasticBounds: 1,
        dragHandle: 1,
        dynamicHandle: 1,
        clickBar: 1,

        // Buttons
        forward: $mainNavs.find('.forward'),
        backward: $mainNavs.find('.backward'),
        prev: $mainNavs.find('.prev'),
        next: $mainNavs.find('.next'),
        prevPage: $mainNavs.find('.prevPage'),
        nextPage: $mainNavs.find('.nextPage'),
        activeClass: '',
        disabledClass: 'hide',
    }).init();
    // // 点击主导航, 显示子导航
    // $mainNav.on("click", ".frame ul li", function() {
    //     $("#subNav")
    //         .css("display", "block")
    //         .addClass("fadeInLeft");
    // });
    
    /**
     * 子导航栏 显示与处理
     * @type {String}
     */
    // 当前主导航栏编号
    var requestFModuleId = "${sessionScope.requestFId}";
    // 当前子导航栏编号
    var requestModuleId = "${sessionScope.requestId}";
    var $subNavs = $('#subNavs');
    if (requestModuleId == 2016030143) { //写死先
        // 无子导航的编号, 不会显示子导航
        $subNavs.hide();
    } else {
        $subNavs.show().addClass("fadeInLeft");
    }
    // 设置主导航高亮
    var $curMainNav = $("#mainNav_" + requestFModuleId);
    //console.log(requestFModuleId);
    if (requestFModuleId != "") {
        // $mainNavs.find('.frame ul li[id=mainNav_'+ requestFModuleId +']')
        $curMainNav.addClass("active");
    }
    var curMainNavLeft = $curMainNav.position().left;
    var curMainNavHalfWidth = $curMainNav.width() / 2;
    var curMainNavHalfLeft = curMainNavLeft + curMainNavHalfWidth;

    var $mainNavLi = $mainNavs.find('.frame ul li');
    if( $mainNavLi.length > 9 ) {
        if( $mainNavs.find('.frame ul li:first-child').hasClass('active') ) {
            // 第一个不显示左边滚动按钮
            // $mainNavs.find('.prev').addClass('hide');
            $mainNavs.find('.prev').css('display', 'none');
        }
        if( $mainNavs.find('.frame ul li:last-child').hasClass('active') ) {
            // 最后一个不显示右边滚动按钮
            // $mainNavs.find('.next').addClass('hide');
            $mainNavs.find('.next').css('display', 'none');
        }
    }else{
        // $mainNavs.find('.prev').addClass('hide');
        // $mainNavs.find('.next').addClass('hide');
        $mainNavs.find('.prev').css('display', 'none');
        $mainNavs.find('.next').css('display', 'none');
    }
    
    // 设置子导航
    if (requestModuleId != "") {
        $("#sModule" + requestModuleId).addClass("e-cur");
    }
    var $subNavsContainer = $subNavs.find('.container');
    var subNavsContainerWidth = $subNavsContainer.width();
    var $subNavsUl = $subNavs.find('.list_box ul');
    var subNavWidth = $subNavsUl.width();
    var subNavHalfWidth = subNavWidth / 2;
    
    var subNavUlAttr = {
        display: "inline",
        position: "absolute",
        top: "0px",
    };
    // var curSubNavLeft = curMainNavLeft + curMainNavHalfWidth - subNavHalfWidth + 36;
    // if (curSubNavLeft < 0) {
    //     curSubNavLeft = 36;
    // }
    if( (curMainNavHalfLeft - subNavHalfWidth) < 0 ) {
        subNavUlAttr.left = 0;
    }else if( (curMainNavHalfLeft + subNavHalfWidth) > subNavsContainerWidth ) {
        subNavUlAttr.right = 0;
    }else{
        subNavUlAttr.left = (curMainNavHalfLeft - subNavHalfWidth) +"px";
    }
    $subNavsUl.css(subNavUlAttr);
});

// 退出
function logout() {
    if (confirm("确认退出登录?")) {
        top.location = "${ctx}/login.do?method=logout";
    } else {
        return false;
    }
} 
</script>