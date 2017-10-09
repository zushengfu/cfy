/*
* @Author: Marte
* @Date:   2017-08-09 15:17:24
* @Last Modified by:   Marte
* @Last Modified time: 2017-09-30 14:19:03
*/
var socket=cfySocket('ws://192.168.1.110:8080/cfy/webSocketServer.do', function(message){
        cfyLayer.dialog({
            type: '1',
            title: '您有新的消息！',
            content: message||'这是弹出框的消息噢',
            offset: "rb",
            anim: 2,//弹框动画
            shade: 0, //去掉背景层
            move: false, //禁止拖曳
            closeBtn: 1
        });
    });

$(document).ready(function() {
//获取用户session的接口
    $.ajax({
        url: '/cfy/user/getSessionUser.do',
        type: 'POST'
    })
    .done(function(backdata) {
        console.log(backdata.user);
        sessionStorage.setItem('uname',backdata.user.userName);
    })
    .fail(function() {
        console.log("error");
    })
    .always(function() {
        console.log("complete");
    });
    //打开socket端口
     

//弹窗的请求，根据结果判断是否有新的档案提交
/*
if(sessionStorage.getItem("uname")==="hello")
{
    setInterval(function(){
    $.ajax({
        url: '/cfy/user/adCxYjJnNewCount.do',
        type: 'POST'
    })
    .done(function(backdata) {
        console.log(backdata);
        if(backdata>0){
            $('#adDbtc').html("您有未审核的文件！").css('background', 'lightblue').show();
            $('#adDbtc').on("click",function(){
                $(this).hide();
            });
        }

    })
    .fail(function() {
        console.log("error");
    })
    .always(function() {
        console.log("complete");
    });
}, 5000);
}
*/
//顶部的切换小图标
$("#topToggle").on('click',function(){
    if($('#topTogglei').hasClass('glyphicon-chevron-left')){
        console.log('jinru');
        $('#topTogglei').removeClass('glyphicon-chevron-left').addClass('glyphicon-chevron-right');
    }
    else if($('#topTogglei').hasClass('glyphicon-chevron-right')){
        $('#topTogglei').removeClass('glyphicon-chevron-right').addClass('glyphicon-chevron-left');
    }

})

//admin模板默认的首页内容
    $('#ad_maincontent').load("/cfy/forward/adIndex.do",function(){
        $('#adIndex').addClass('active');
        var indexScript = document.createElement('script');
        indexScript.type = 'text/javascript';
        indexScript.src="../dist/js/adIndex.js";
        document.querySelector('body').appendChild(indexScript);
    });
//左侧导航栏的li标签添加点击事件，点击对应的a标签跳转到相应的控制器，控制器名字和标签id名字相同
    $('.sidebar-menu li').on('click',function(event) {
//点击li删除所有的active
        $('.sidebar-menu').find('.active').removeClass('active');
//定位到点击的元素，th代表被点击的元素，thId代表元素的id
        var th=$(this);
        var thId=$(this).attr("id");
//点击li为其添加active
        setTimeout(function(){th.addClass('active');},'100');
        if(thId){
            console.log(thId);
//根据id请求不同的html片段
            $('#ad_maincontent').load("/cfy/forward/"+thId+".do",function(response,status,xhr){
                if(status==="success"&&thId.substr(0, 4)==="adXz"){
                    $('.activejs').remove();
                    var activeScript = document.createElement('script');
                    activeScript.type = 'text/javascript';
                    activeScript.setAttribute('class','activejs');
                    activeScript.src="../dist/js/adXz.js";
                    document.querySelector('body').appendChild(activeScript);
                }
                if(status==="success"&&thId.substr(0, 4)==="adGl"){
                    $('.activejs').remove();
                    var activeScript = document.createElement('script');
                    activeScript.type = 'text/javascript';
                    activeScript.setAttribute('class','activejs');
                    activeScript.src="../dist/js/adGl.js";
                    document.querySelector('body').appendChild(activeScript);
                }
                if(status==="success"&&thId.substr(0, 7)==="admDaTj"){
                    $('.activejs').remove();
                    var activeScript = document.createElement('script');
                    activeScript.type = 'text/javascript';
                    activeScript.setAttribute('class','activejs');
                    activeScript.src="../dist/js/admDaTj.js";
                    document.querySelector('body').appendChild(activeScript);
                }
                if(status==="success"&&thId.substr(0, 7)==="admDaJs"){
                    $('.activejs').remove();
                    var activeScript = document.createElement('script');
                    activeScript.type = 'text/javascript';
                    activeScript.setAttribute('class','activejs');
                    activeScript.src="../dist/js/admDaJs.js";
                    document.querySelector('body').appendChild(activeScript);
                }
                if(status==="success"&&thId==="adXtszLink"){
                    $('.activejs').remove();
                    var activeScript = document.createElement('script');
                    activeScript.type = 'text/javascript';
                    activeScript.setAttribute('class','activejs');
                    activeScript.src="../dist/js/admXtsz.js";
                    document.querySelector('body').appendChild(activeScript);
                }
                if(status==="success"&&thId==="adBfHyLink"){
                    $('.activejs').remove();
                    var activeScript = document.createElement('script');
                    activeScript.type = 'text/javascript';
                    activeScript.setAttribute('class','activejs');
                    activeScript.src="../dist/js/admBfhy.js";
                    document.querySelector('body').appendChild(activeScript);
                }
                if(status==="success"&&thId==="adSjdrLink"){
                    $('.activejs').remove();
                    var activeScript = document.createElement('script');
                    activeScript.type = 'text/javascript';
                    activeScript.setAttribute('class','activejs');
                    activeScript.src="../dist/js/admXtrz.js";
                    document.querySelector('body').appendChild(activeScript);
                }
                if(status==="success"&&thId==="adIndex"){
                    $('.activejs').remove();
                    var activeScript = document.createElement('script');
                    activeScript.type = 'text/javascript';
                    activeScript.setAttribute('class','activejs');
                    activeScript.src="../dist/js/adIndex.js";
                    document.querySelector('body').appendChild(activeScript);
                }
                if(status==="success"&&thId==="adSjfxLink"){
                    $('.activejs').remove();
                    var activeScript = document.createElement('script');
                    activeScript.type = 'text/javascript';
                    activeScript.setAttribute('class','activejs');
                    activeScript.src="../dist/js/cfyChart.js";
                    document.querySelector('body').appendChild(activeScript);
                }
            });
//根据id判断，如果是新增类的页面，就写入adXz.js

        }
    });

});

