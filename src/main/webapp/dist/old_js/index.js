/*$(document).ready(function(){    $('#myModal').modal('show')})
* @Author: Marte
* @Date:   2017-07-11 10:47:57
* @Last Modified by:   Marte
* @Last Modified time: 2017-07-14 15:13:16
*/
/*
$(function(){
    $('#index-btn-sub').on('click',function(){
        var xml_data={
            userName:$('#index-inp-un').val(),
            userPassword:$('#index-inp-pd').val()
        };
        var userinfo;
        console.log(JSON.stringify(xml_data));
        $.ajax({
                 type: "POST",
                 url: "../maven02/user/login.do",
                 data: JSON.stringify(xml_data),
                 dataType: "json",
                 contentType:"Application/JSON; charset=utf-8",
                 success: function(data){
                            userinfo=data;
                            console.log(userinfo);
                            if(userinfo.success==false){
                                console.log(userinfo.msg);
                            }
                            if (userinfo.success==true) {
                                console.log(userinfo.obj);
                                window.open('./WEB-INF/jsp/home.html');
                            };
                          }
             });
    })
});
*/