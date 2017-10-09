/*
* @Author: Marte
* @Date:   2017-07-24 09:38:20
* @Last Modified by:   Marte
* @Last Modified time: 2017-08-08 13:51:47
*/
$(document).ready(function($) {


});
$('#ad_fileupload').fileinput({
        language: 'zh',
        uploadUrl: "../file/upload.do", // server upload action
        uploadAsync: false,
        maxFileCount: 10,
        showUpload:false,
        uploadExtraData:function(previewId, index) {   //额外参数的关键点，必须写成回调函数，不然页面加载就读。读不出来表单数据
                    var obj = {filemark:$("#ad_filenum").val(),
                               fondsmark:$("#ad_fondsnum").val(),
                               fondnamemark:$("#ad_fondsname").val()
                           };
                    console.log(obj);
                    return obj;
                },
        allowedFileExtensions: ["jpg", "png", "gif"],
        layoutTemplates:{
            actionUpload:''//去掉上传的小图标
        }
   })
.on("filebatchuploadsuccess", function (e, data) {
         var res = data.response;
        console.log(data);
        alert(res.msg);
        location.reload();
    })
.on('filebatchuploaderror', function(event, data, msg) {
    alert('上传失败，检查您的网络');
    console.log('File batch upload error');
   // get message
   alert(msg);
});;
$('#ad_test').on("click",function(){
    alert('宗卷编号不能为空！');

});
var xzdafmdata; //新增档案的表单数据


$("#ad_xzqzform").submit(function() {
                            //$("#ad_xzqzbtn").on("click",function(){

                            //$("#ad_xzqzbtn").on("click",function(){

                            //$('#file-zh').fileinput('refresh',{uploadExtraData: {filemark:999}}).fileinput('upload');

                            //$('#ad_fileupload').fileinput('refresh',{uploadExtraData:
                            //
                            xzdafmdata=JSON.stringify($("#ad_xzqzform").serializeJSON());
                                $.ajax({
                                    url: '../fond/createNewFond.do',
                                    type: 'POST',
                                    dataType: 'json',
                                    data: xzdafmdata,
                                    contentType:"Application/JSON; charset=utf-8",
                                    success:function(backdata){
                                        console.log(backdata);
                                        alert(backdata.msg);

                                    }
                                 })
                                .done(function() {
                                    console.log("success");
                                })
                                .fail(function() {
                                    console.log("error");
                                })
                        //});
                    // 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false
                    if($("#ad_fileupload").val()!=""){
                                $('#ad_fileupload').fileinput('upload');
                            };

                });





/***导入文件***/
$("#import").click(function(){//点击导入按钮，使files触发点击事件，然后完成读取文件的操作。
        $("#files").click();
    });

function impor(){
    var selectedFile = document.getElementById("files").files[0];//获取读取的File对象
    var name = selectedFile.name;//读取选中文件的文件名
    var size = selectedFile.size;//读取选中文件的大小
    console.log("文件名:"+name+"大小："+size);
    var reader = new FileReader();//这里是核心！！！读取操作就是由它完成的。
    reader.readAsText(selectedFile);//读取文件的内容
    reader.onload = function(){
        var res=this.result;
        console.log(this.result);//当读取完成之后会回调这个函数，然后此时文件的内容存储到了result中。直接操作即可。
        document.write(res);
    };
}