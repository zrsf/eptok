<script type="text/javascript" src="<%=_contextPath%>/js/jquery/jquery-1.12.2.min.js"></script>
<!--弹窗-->
<div class="e-mask-box" style="display: none;">
    <div class="e-mask"></div>
    <div class="e-wrap">
        <div class="e-cell">
            <div class="e-container">
                <i class="e-x"></i>
                <div class="e-box">
                    <div class="e-mask-tit">
                        <span></span>
                    </div>
                    <div class="e-mask-content">
                        <!--自定义内容-->
                        <div class="e-ror-content">
                            <ul class="e-ror-ul">
                                <li class="e-w120">
                                    <!--<img src="<%=_contextPath%>/images/global/404.png">-->
                                    <i class="icon iconfont warn">&#xe663;</i>
                                </li>
                                <li class="e-w430">
                                    <p class="e-ror-h">您访问的页面不存在</p>
                                    <p class="e-ror-p">可能该服务已经过期，或者您输入的地址有误。</br>返回<a href="#">首页</a></p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
$(function(){
    $(".modalBox").click(function(){
        $(".e-mask-box").css("display","block");
    });
    $(".e-x").click(function(){
        $(".e-mask-box").css("display","none");
    });
});
</script>
