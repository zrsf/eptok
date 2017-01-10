<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<title>企业客户服务</title>
<style type="text/css">
.lbf-panel { min-width:400px; _width:400px; padding:0 20px;}
</style>
<script type="text/javascript" src="http://combo.b.qq.com/lbf/0.7.3/LBF.js"></script>
<script>
// LBF setting & preload
LBF.set({
  theme: 'bootstrap',
  useCombo: true,
  version: 20140303
});

LBF.use(['ui.widget.Panel.ConfirmPanel', 'ui.widget.Panel.AlertPanel'], function(){});

var domain = "qq.com";
var wpaType = 1;
var browserType = 2;
var g_kfuin = 800094945;
var g_accoutname = 800094945;
var bizParam = "";
var bizSize = 0;
var envID = 12;
var version = 1;
var appointed = 0;
var appointedType = 0;
var sessionVersion = 0;
var sessionParam = "";
var bizID = 0;
var customizeUrl = '';
var browerName = 'mozilla/5.0 (windows nt 6.3; wow64; rv:32.0) gecko/20100101 firefox/32.0';
var bt = '';
var debug = 0;
var wpaFrom = '0';
var clickId = '3265e4bbd07c6985b4f43a1ed6d83fbc17';

function win_onload() {
  LBF.use(['ui.widget.Panel.ConfirmPanel', 'ui.widget.Panel.AlertPanel'], function(require, ConfirmPanel, AlertPanel){
    var alert = function(msg, ok){
          new AlertPanel({
            title: '请确认',
            content: msg,
            events: {
              ok: function(){
                ok && ok();
                this.remove();
              }
            }
          });
        },
        confirm = function(msg, ok, cancel){
          new ConfirmPanel({
            title: '请确认',
            content: msg,
            events: {
              ok: function(){
                ok && ok();
                this.remove();
              },
              cancel: function(){
                cancel && cancel();
                this.remove();
              }
            }
          });
        };

    if (19 == envID) {
        var referrer = getDomain(document.referrer);
        if (referrer != "paipai.com" && referrer != "soso.com" && referrer != 'yixun.com') {
            alert("营销QQ在线咨询无权限在当前场景使用！", closeWindow);
            return;
        }
    }

    //debug info
    if(1 == debug) {
        alert("browerType=" + browserType + "; qqverison=" + v + "; browerName=" + browerName + "; bt=" + bt);
    }

    // 广电通客户端逻辑
    if(wpaFrom === '5' || wpaFrom === '7'){
        getSigtSigu(g_kfuin, bizParam, bizSize);

        return;
    }

    //QQ WPA
    if (1 == wpaType && 4 != browserType) {
        if(g_accoutname == '40012345'){ // kf.qq.com的号码，不做客户端版本的检查。
            var v = 2384;
        }
        else{
            var v = IsInstallQQ();
        }

        if (3 > browserType) //IE、FF Browser， Chrome待QQ2012beta3版本普及
        {
            if (10 == bizID || 1003 == bizID) {
                if(g_accoutname == '800010000'){
                    var msg = "确认打开QQ与中国电信QQ对话？";
                }
                else{
                    var msg = "确认打开QQ与营销QQ" + g_accoutname + "对话？";
                }

                confirm(msg, function(){
                  if (v >= 2383 || '4008000000' == g_accoutname) //for above hummer 1.26 version
                  {
                      getSigtSigu(g_kfuin, bizParam, bizSize);

                      return;
                  }

                  vipCase();
                }, closeWindow);                
            } else {
              if (v >= 2383 || '4008000000' == g_accoutname) //for above hummer 1.26 version
              {
                  getSigtSigu(g_kfuin, bizParam, bizSize);

                  return;
              }
              
              vipCase();
            }          
        }
        else //other Browser
        {

            if(g_accoutname == '800010000'){
                var msg = 11 == bizID ? "确认打开QQ与腾讯客服对话？" : "确认打开QQ与中国电信QQ对话？";
            }
            else{
                var msg = 11 == bizID ? "确认打开QQ与腾讯客服对话？" : "确认打开QQ与营销QQ" + g_accoutname + "对话？";
            }
            confirm(
              msg, 
              function(){
                getSigtSigu(g_kfuin, bizParam, bizSize);
              }, 
              vipCase
            );
        }
    } else {
      vipCase();
    }    


    function vipCase(){
      if (112 == envID) //中国电信模式特殊业务逻辑
      {
          if (10 == bizID || 1003 == bizID) {
              location.href = 'http://cturl.cn/t/kmljc';
              return;
          }
          else if (11 == bizID || 13 == bizID || 1016 == bizID || 1017 == bizID || 15 == bizID || 16 == bizID  || 17 == bizID || 18 == bizID || 19 == bizID || 1018 == bizID || 1020 == bizID  || 1021 == bizID) {
              alert('请先安装腾讯QQ，10秒后页面跳转到腾讯QQ下载页面。\n若您已经安装了腾讯QQ，请在浏览器中允许腾讯QQ组件的运行。');
              setTimeout('location.href = "http://im.qq.com/";', 10000);
              return;
          }
          else if (12 == bizID || 1011 == bizID || 1012 == bizID || 1013 == bizID || 1014 == bizID || 1015 == bizID || 1019 == bizID) {
              location.href = 'http://183.62.138.121:18080/qq/proxy.htm';
              return;
          }

          closeWindow();
          return;
      }
      else if (800013811 == g_kfuin)    //封禁匿名聊天业务逻辑
      {
          alert('请先安装QQ聊天软件');

          maxWindow();

          location.href = 'http://im.qq.com/';

          return;
      }

      anonymous();
    }

    function anonymous(){
      //Anonymous WPA
      var bFromAnonymous = wpaType == 2;
      loadAnonymousWPA(bFromAnonymous);
    }
  });
}

function getSigtSigu(kfuin, param, size) {
    //env非0，不返回xml格式，返回js回调
    if(wpaFrom === '5' || wpaFrom === '7'){
        if(wpaFrom === '5'){
            var fflg = 4;
        }else{
            var fflg = 5;
        }
        var req_data = 'kfguin=' + kfuin + '&p= ' + param + '&s=' + size + '&env=1&v=' + version
                + '&a=' + appointed + '&aty=' + appointedType + '&sv=5&sp=' + sessionParam
                + '&clickid=' + clickId + '&fflg=' + fflg;
    }
    else if(wpaFrom === '3'){
        var req_data = 'kfguin=' + kfuin + '&p=' + param + '&s=' + size + '&env=1&v=' + version
                + '&a=' + appointed + '&aty=' + appointedType + '&sv=' + sessionVersion + '&sp='
                + sessionParam + '&fflg=3';
    }
    else{
        var req_data = 'kfguin=' + kfuin + '&p=' + param + '&s=' + size + '&env=1&v=' + version
                + '&a=' + appointed + '&aty=' + appointedType + '&sv=' + sessionVersion + '&sp='
                + sessionParam + '&fflg=2';
    }

    var myDate = new Date();
    var oScript = document.createElement('script');
    oScript.src = "http://b." + domain + "/cgi/wpags?" + req_data + "&t=" + myDate.getTime();
    document.getElementsByTagName("head")[0].appendChild(oScript);
}

function onGetSigtSigu(sigUrl) {
    if (null == sigUrl || '' == sigUrl) {
        alert("您输入的参数有误！");

        closeWindow();

        return;
    }

    location.href = sigUrl;

    setTimeout(closeWindow, 1000);
}

function loadAnonymousWPA(bFromAnonymous) {
    var anonymousUrl = "http://webchat.b.qq.com/webchat.htm?sid=" + WPA.encrypt(g_kfuin.toString());

    if (bFromAnonymous) {
        anonymousUrl += "&q=1";
    }

    window.location.replace(anonymousUrl);
}

function IsInstallQQ() {
    try {
        var version = 0;
        if(1 == browserType){
            var xmlhttp = new ActiveXObject("TimwpDll.TimwpCheck");
            version = xmlhttp.GetHummerQQVersion();
        }
        else if(2 == browserType || 3 == browserType){
            var embed = document.getElementById("embed");
            embed.InitActiveX("TimwpDll.TimwpCheck");
            version = embed.GetHummerQQVersion();
        }

        return version;
    }
    catch (e) {
        return -1;
    }
    return -1;
}

function getDomain(referrerUrl) {
    var index = referrerUrl.indexOf('/', 7);
    var domain = referrerUrl.substring(7, index);

    index = domain.lastIndexOf('.');
    var tmp = domain.substr(0, index);
    index = tmp.lastIndexOf('.');

    domain = domain.substr(index + 1);

    return domain;
}

function closeWindow() {
    if ('' != customizeUrl) {
        setTimeout('location.href = customizeUrl;', 1);
        return;
    }

    if (2 != browserType && 3 != browserType) {
        window.opener = null;
        setTimeout('window.open("", "_self"); window.close();', 1);
    }
}

function maxWindow() {
    window.moveTo(0, 0);
    window.resizeTo(screen.availWidth, screen.availHeight);
    window.outerWidth = screen.availWidth;
    window.outerHeight = screen.availHeight;
}

var WPA = {};
WPA.ts = "8ABC7DLO5MN6Z9EFGdeJfghijkHIVrstuvwWSTUXYabclmnopqKPQRxyz01234";
WPA.encrypt = function (n) {
    var nl = n.length;
    var t = [];
    var a, b, c, x;
    var m = function (y) {
        t[t.length] = WPA.ts.charAt(y)
    }
    var N = WPA.ts.length;
    var N2 = N * N;
    var N5 = N * 5;

    for (x = 0; x < nl; x++) {
        a = n.charCodeAt(x);
        if (a < N5) {
            m(Math.floor(a / N));
            m(a % N);
        }
        else {
            m(Math.floor(a / N2) + 5);
            m(Math.floor(a / N) % N);
            m(a % N);
        }
    }
    var s = t.join("");

    return String(s.length).length + String(s.length) + s;
}
</script>
</head>
<body onload="win_onload();">
<embed id="embed" name="embed" type="application/qscall-plugin" width=0 height=0 hidden="true">
</body>
</html>
