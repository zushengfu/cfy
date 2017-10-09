/*
* @Author: Marte
* @Date:   2017-08-09 15:17:24
* @Last Modified by:   Marte
* @Last Modified time: 2017-09-30 12:14:33
*/


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
    $('[name="timeStart"]').datetimepicker({
        format: 'yyyy-mm-dd',
        minView:2
    });
    $('[name="timeOver"]').datetimepicker({
        format: 'yyyy-mm-dd',
        minView:2
    });
     $admBfhyTab=$('#admBfhyTab').bootstrapTable({
                    pagination: true,
                    sidePagination: 'client',
                    pageList:[10,20,30,40],
                    pageNumber: 1,                       //初始化加载第一页，默认第一页
                    pageSize: 10,
                    toolbar:'#admBfhyTabTool',
                    toolbarAlign:'right',
                    search:'true',
                    searchAlign:'left',
                    singleSelect:true,
                    method:'POST',
                    url: '/cfy/file/showRestoreDB.do',
                    columns: [
                    {
                    radio: true
                    },
                    {
                        field: 'fileName',
                        align: 'center',
                        title: "文件名"
                    },{
                        field: 'fileAddress',
                        align: 'center',
                        title: "文件地址"
                    }
                    ]

                });
     $("#admBfhyBfBtn").on('click',function(){
        console.log($("[name='beifentiaojian']").val());
        if($("[name='beifentiaojian']:checked").val()==="qbbf"){
            $.ajax({
                url: '/cfy/file/dataBackUp.do',
                type: 'POST'
            })
            .done(function(backdata) {
                alert(backdata.msg);
                  $admBfhyTab.bootstrapTable('refresh');
            })
            .fail(function() {
                console.log("error");
            })
            .always(function() {
                console.log("complete");
            });


        };
        if($("[name='beifentiaojian']:checked").val()==="xzbf"){
            if($("[name='fileType']").val()==="卷内文件"){
                    var beifenObj={};
                    beifenObj.biaoming="file_records";
                    beifenObj.timeStart=$("[name='timeStart']").val();
                    beifenObj.timeOver=$("[name='timeOver']").val();
                    $.ajax({
                        url: '/cfy/file/dataBackUpCondition.do',
                        type: 'POST',
                        data:beifenObj
                    })
                    .done(function(backdata) {
                        alert(backdata.msg);
                        $admBfhyTab.bootstrapTable('refresh');
                    })
                    .fail(function() {
                        console.log("error");
                    })
                    .always(function() {
                        console.log("complete");
                    });
            }
            else if($("[name='fileType']").val()==="归档文件"){
                     var beifenObj={};
                    beifenObj.biaoming="file_records";
                    beifenObj.timeStart=$("[name='timeStart']").val();
                    beifenObj.timeOver=$("[name='timeOver']").val();
                    $.ajax({
                        url: '/cfy/file/dataBackUpCondition.do',
                        type: 'POST',
                        contentType:"application/json",
                        data:JSON.stringify(beifenObj)
                    })
                    .done(function(backdata) {
                        alert(backdata);
                        $admBfhyTab.bootstrapTable('refresh');
                    })
                    .fail(function() {
                        console.log("error");
                    })
                    .always(function() {
                        console.log("complete");
                    });
            }

        };
     });

     $("#admBfhyHyBtn").on('click',function(){
        console.log($admBfhyTab.bootstrapTable('getSelections'));
        var dbName =$admBfhyTab.bootstrapTable('getSelections')[0].fileName;
        $.ajax({
            url: '/cfy/file/restoreDB.do',
            type: 'POST',
            data:'databaseName='+dbName
        })
        .done(function(backdata) {
            alert(backdata.msg)
        })
        .fail(function() {
            console.log("error");
        })
        .always(function() {
            console.log("complete");
        });

     });
});
