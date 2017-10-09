/*
* @Author: Marte
* @Date:   2017-07-19 10:06:49
* @Last Modified by:   Marte
* @Last Modified time: 2017-07-20 18:32:03
*/

$('form').on('submit',function(event) {
    alert("修改成功!");
});

$('#user-szpd').on('click', function(event) {
        var postdata={
            cardNumber:$('#user-xg-sfz').val(),
            userPassword:$('#user-xg-opd').val(),
            newPassword:$('#user-xg-npd').val(),
        }
        $.ajax({
            url: '../user/setUserPassWord.do ',
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(postdata),
            success:function(data){
                    console.log(data);
            }
        })
        .done(function() {
            console.log("success");
        })
        .fail(function() {
            console.log("error");
        })
        .always(function() {
            console.log("complete");
        });


});