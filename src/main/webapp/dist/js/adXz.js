/*
* @Author: Marte
* @Date:   2017-08-17 11:26:21
* @Last Modified by:   Marte
* @Last Modified time: 2017-09-29 19:08:16
*/

(function(){
                var upfileobj,adXzJnGdFoFile;
                //请求编号并改变下拉框，第一个是查询的地址，第二个是要改变的下拉框，第三个是查询参数
                function adCxXs(subqueryurl,selectele,querydata){
                    $.ajax({
                        url: subqueryurl,
                        type: 'POST',
                        contentType:"Application/JSON; charset=utf-8",
                        data:querydata
                    })
                    .done(function(backdata) {
                        console.log("success");
                        console.log(backdata);
                        var opt="<option></option>";
                        $(selectele).empty().append(opt);
                        for(var i=0;i<backdata.length;i++)
                        {
                            opt="<option>"+backdata[i]+"</option>";
                            $(selectele).append(opt);
                        }
                    })
                    .fail(function() {
                        console.log("error");
                    })
                    .always(function() {
                        console.log("complete");
                    });
                };
                //提交表单函数，第一个参数是提交的地址，第二个是表单数据
                function submitform(subformurl,formdata){
                    $.ajax({
                         url: subformurl,
                        type: 'POST',
                        dataType: 'json',
                        data: formdata,
                        contentType:"Application/JSON; charset=utf-8"
                        })
                        .done(function(backdata) {
                                console.log(backdata);
                                alert(backdata.msg);
                        })
                        .fail(function(backdata) {
                            alert(backdata);
                        })
                };
                //初始化上传框函数，第一个参数是调用该方法的元素，第二个是上传文件的i地址
                function uploadfile(selectele,fileuploadUrl){
                    selectele.fileinput({
                                    language: 'zh',
                                    uploadUrl: fileuploadUrl,
                                    uploadAsync: false,
                                    maxFileCount: 10,
                                    showUpload:false,
                                    uploadExtraData:function(previewId, index) {
                                    //额外参数的关键点，必须写成回调函数，不然页面加载就读。读不出来表单数据
                                                return upfileobj;
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

                                })
                            .on('filebatchuploaderror', function(event, data, msg) {
                                alert('上传失败，检查您的网络');
                                console.log('File batch upload error');
                               alert(msg);
                            });
                };
                //为时间选择器绑定事件
                $('[name="startTime"]').datetimepicker({
                    format: 'yyyy-mm-dd',
                    minView:2
                });
                $('[name="endTime"]').datetimepicker({
                    format: 'yyyy-mm-dd',
                    minView:2
                });
                //查询秘级的下拉框
                $.ajax({
                    url: '/cfy/properties/getSecurityRank.do',
                    type: 'POST'

                })
                .done(function(backdata) {
                    $("[name='securityRank']").empty();
                    $("[name='securityRank']").append('<option></option>');
                    var SRKopt;
                    for(var i=0;i<backdata.length;i++){
                        SRKopt='<option>'+backdata[i].svalue+'</option>';
                        $("[name='securityRank']").append(SRKopt);
                    }

                })
                .fail(function() {
                    console.log("error");
                })
                .always(function() {
                    console.log("complete");
                });
                //查询文号的下拉框
                $.ajax({
                    url: '/cfy/properties/getFileDescribeNum.do',
                    type: 'POST'

                })
                .done(function(backdata) {
                    $("[name='fileDescribeNum']").empty();
                    $("[name='fileDescribeNum']").append('<option></option>');
                    var FDBNumopt;
                    for(var i=0;i<backdata.length;i++){
                        FDBNumopt='<option>'+backdata[i].fvalue+'</option>';
                        $("[name='fileDescribeNum']").append(FDBNumopt);
                    }

                })
                .fail(function() {
                    console.log("error");
                })
                .always(function() {
                    console.log("complete");
                });

                //新增全宗
                if ($('#adXzQzForm').attr("id")!==undefined) {
                    $('#adXzQzForm').submit(function(event) {
                        event.preventDefault();
                        var subformurl='/cfy/fond/adXzQz.do';
                        var formdata=JSON.stringify($("#adXzQzForm").serializeJSON());
                        submitform(subformurl,formdata);
                    });
                    $('#adXzQzFoRes').on('click',function(){
                        $("#adXzQzForm")[0].reset();
                    });
                }
                //新增案卷
                else if($('#adXzAjForm').attr("id")!==undefined){
                    adCxXs('/cfy/fond/adCxQz.do','[name="fondsNum"]');
                    //为select绑定事件
                    $('#adXzAjFoAjlx').on("click",function(event){

                                    if ($(event.target).val()==="卷内文件") {
                                        $('#adXzAjForm [name="catalogNum"]').attr("disabled", false);
                                        $('#adXzAjFoAjbh').html("案卷编号");
                                    };
                                    if ($(event.target).val()==="归档文件") {
                                        $('#adXzAjForm [name="catalogNum"]').attr("disabled", true);
                                        $('#adXzAjFoAjbh').html("盒号");
                                    };
                        });
                    //提交事件
                    $('#adXzAjForm').submit(function(event) {
                            //阻止刷新
                        event.preventDefault();
                        var subformurl='/cfy/fond/adXzAj.do';
                        var formdata=JSON.stringify($("#adXzAjForm").serializeJSON());
                        submitform(subformurl,formdata);
                        });
                     $('#adXzAjFoRes').on('click',function(){
                        $("#adXzAjForm")[0].reset();
                    });
                }
                else if($('#adXzJnForm').attr("id")!==undefined){
                            uploadfile($('#adXzJnFoFile'),"/cfy/file/adXzJnTp.do");
                            adCxXs('/cfy/fond/adCxQz.do','[name="fondsNum"]');
                            $('#adXzJnForm [name="fondsNum"]').on("blur",function(){
                                var adCxParm=$(this).val();
                                var querydata=JSON.stringify({fondsNum:adCxParm});
                                adCxXs('/cfy/fond/adCxAj.do','[name="catalogNum"]',querydata);
                            });
                            $('#adXzJnForm [name="catalogNum"]').on("blur",function(){
                                var adCxParm=$(this).val();
                                var querydata=JSON.stringify({
                                        fondsNum:$('#adXzJnForm [name="fondsNum"]').val(),
                                        catalogNum:adCxParm
                                    });
                                adCxXs('/cfy/fond/adCxAjCaseNum.do','[name="caseNum"]',querydata);
                            });
                            $('#adXzJnForm').submit(function(event) {
                            //阻止刷新
                            event.preventDefault();

                            var subformurl='/cfy/fond/adXzJn.do';
                            var formdata=JSON.stringify($("#adXzJnForm").serializeJSON());
                            adXzJnGdFoFile=$('#adXzJnForm').find('[type="file"]');
                            upfileobj={
                                fondsNum:$('#adXzJnForm').find('[name="fondsNum"]').val(),
                                catalogNum:$('#adXzJnForm').find('[name="catalogNum"]').val(),
                                caseNum:$('#adXzJnForm').find('[name="caseNum"]').val(),

                                fileNum:$('#adXzJnForm').find('[name="fileNum"]').val()
                                };
                            submitform(subformurl,formdata);
                            // 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false
                            if(adXzJnGdFoFile.val()!=""){
                                    adXzJnGdFoFile.fileinput('upload');
                                };
                            });
                             $('#adXzJnFoRes').on('click',function(){
                                $("#adXzJnForm")[0].reset();
                            });
                }
                else if($('#adXzGdForm').attr("id")!==undefined){

                            uploadfile($('#adXzGdFoFile'),"/cfy/gdfile/adXzGdTp.do");
                            adCxXs('/cfy/fond/adCxQz.do','[name="fondsNum"]');
                            $('[name="fondsNum"]').on("blur",function(){
                                var adCxParm=$(this).val();
                                var querydata=JSON.stringify({fondsNum:adCxParm});
                                adCxXs('/cfy/fond/adCxAjGdCaseYear.do','[name="caseYear"]',querydata);
                            });
                            $('[name="caseYear"]').on("blur",function(){
                                var adCxParm=$(this).val();
                                var querydata=JSON.stringify({
                                        fondsNum:$('#adXzGdForm [name="fondsNum"]').val(),
                                        caseYear:adCxParm
                                    });
                                adCxXs('/cfy/fond/adCxAjGdCaseNum.do','[name="caseNum"]',querydata);
                            });
                            $('#adXzGdForm').submit(function(event) {
                            //阻止刷新
                            event.preventDefault();

                            var subformurl='/cfy/fond/adXzGd.do';
                            var formdata=JSON.stringify($("#adXzGdForm").serializeJSON());
                            adXzJnGdFoFile=$('#adXzGdForm').find('[type="file"]');
                            upfileobj={
                                fondsNum:$('#adXzGdForm').find('[name="fondsNum"]').val(),
                                caseYear:$('#adXzGdForm').find('[name="caseYear"]').val(),
                                safekeepingTerm:$('#adXzJnForm').find('[name="safekeepingTerm"]').val(),
                                orgType:$('#adXzJnForm').find('[name="orgType"]').val(),
                                fileNum:$('#adXzGdForm').find('[name="fileNum"]').val()
                                };
                            submitform(subformurl,formdata);
                            // 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false
                            if(adXzJnGdFoFile.val()!=""){
                                    adXzJnGdFoFile.fileinput('upload');
                                };
                            });
                             $('#adXzGdFoRes').on('click',function(){
                                $("#adXzGdForm")[0].reset();
                            });
                }








})();
