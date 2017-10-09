/*
* @Author: Marte
* @Date:   2017-08-08 09:14:49
* @Last Modified by:   Marte
* @Last Modified time: 2017-08-08 18:27:01
*/
$('#ad_xzform_bgqx').append('<option value="audi">永久</option><option value="audi">长期</option>');
$('.ctrl_list').append('<li class="list-group-item">永久</li><li class="list-group-item">长期</li>');
var opval_bgqx;
var oparr_bgqx;
var fnoparr_bgqx;
fnoparr_bgqx=function(a,b,c){

    for(var i=0;i<a.length;i++){
            b.append('<'+c+'>'+a[i]+'</'+c+'>')
    }
};
opul_bgqx=$('.ctrl_list').children();
$('.ctrl_add').on('click',function(event) {


        opval_bgqx=$(this).prev().val();
        if(opval_bgqx!=""){
            $(this).parent().next().append('<li class="list-group-item">'+opval_bgqx+'</li>');
            $('#ad_xzform_bgqx').append('<option value="audi">'+opval_bgqx+'</option>');
        }
        console.log($('.ctrl_list').children());
        opul_bgqx=$('.ctrl_list').children();

});
$('.ctrl_list').on('click',function(event) {
    console.log($(event)[0].target);
    //$(event.target).remove();
    $(event.target).toggleClass('active');
});
$('.ctrl_del').on('click',function(event) {
        console.log(   $('.ctrl_list').find('.active')  );
        $('.ctrl_list').find('.active').remove();

});



