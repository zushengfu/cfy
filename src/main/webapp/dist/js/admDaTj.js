/*
* @Author: Marte
* @Date:   2017-08-31 14:40:37
* @Last Modified by:   Marte
* @Last Modified time: 2017-09-30 11:11:00
*/

$(document).ready(function() {
    $adDaTjTab=$('#adDaTjTab').bootstrapTable({
                    pagination: true,
                    sidePagination: 'client',
                    pageList:[10,20,30,40],
                    pageNumber: 1,                       //初始化加载第一页，默认第一页
                    pageSize: 10,
                    method:'POST',
                    toolbar:'#adDaTjTabTool',
                    toolbarAlign:'right',
                    url: '/cfy/fond/adCxJnAllInfo.do',
                    columns: [
                    {
                    checkbox: true
                    },
                    {
                        field: 'fileRecords.fondsNum',
                        align: 'center',
                        title: "全宗号"
                    },{
                        field: 'fileRecords.catalogNum',
                        align: 'center',
                        title: "目录号"
                    },{
                        field: 'fileRecords.caseYear',
                        align: 'center',
                        title: "年度"
                    },{
                        field: 'fileRecords.caseNum',
                        align: 'center',
                        title: "案卷号"
                    },{
                        field: 'fileRecords.fileNum',
                        align: 'center',
                        title: "件号"
                    },{
                        field: 'fileRecords.caseName',
                        align: 'center',
                        title: "题名"

                    },{
                        field: 'fileRecords.safekeepingTerm',
                        align: 'center',
                        title: "保存期限"
                    },{
                        field: 'fileRecords.securityRank',
                        align: 'center',
                        title: "密级"
                    },{
                        field: 'fileRecords.responsibler',
                        align: 'center',
                        title: "责任者"
                    },{
                        field: 'fileRecords.safekeepingTerm',
                        align: 'center',
                        title: "保管期限"
                    },{
                        field: 'fileRecords.roomNum',
                        align: 'center',
                        title: "库房号"
                    },{
                        field: 'fileRecords.cabNum',
                        align: 'center',
                        title: "排柜号"
                    }
                    ]
                });
    $('#admDaTjCxBtn').on('click',function(){

        if($('[name="admDaTjType"]').val()==="卷内文件"){
            $adDaTjTab.bootstrapTable('refreshOptions',{
                pagination: true,
                sidePagination: 'client',
                pageList:[10,20,30,40],
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 10,
                toolbar:'#adDaTjTabTool',
                toolbarAlign:'right',
                method:'POST',
                url: '/cfy/fond/adCxJnAllInfo.do',
                columns: [
                {
                checkbox: true
                },
                {
                    field: 'fileRecords.fondsNum',
                    align: 'center',
                    title: "全宗号"
                },{
                    field: 'fileRecords.catalogNum',
                    align: 'center',
                    title: "目录号"
                },{
                    field: 'fileRecords.caseYear',
                    align: 'center',
                    title: "年度"
                },{
                    field: 'fileRecords.caseNum',
                    align: 'center',
                    title: "案卷号"
                },{
                    field: 'fileRecords.fileNum',
                    align: 'center',
                    title: "件号"
                },{
                    field: 'fileRecords.caseName',
                    align: 'center',
                    title: "题名"

                },{
                    field: 'fileRecords.safekeepingTerm',
                    align: 'center',
                    title: "保存期限"
                },{
                    field: 'fileRecords.securityRank',
                    align: 'center',
                    title: "密级"
                },{
                    field: 'fileRecords.responsibler',
                    align: 'center',
                    title: "责任者"
                },{
                    field: 'fileRecords.safekeepingTerm',
                    align: 'center',
                    title: "保管期限"
                },{
                    field: 'fileRecords.roomNum',
                    align: 'center',
                    title: "库房号"
                },{
                    field: 'fileRecords.cabNum',
                    align: 'center',
                    title: "排柜号"
                }
                ]
            });
        }
    });
        $('#admDaTjTjshBtn').on('click',function(){
            if($('[name="admDaTjType"]').val()==="卷内文件"){
                var xzdata=$adDaTjTab.bootstrapTable('getSelections');
                var tjdata=[],tjUname,jsUname;
                socket.sendMsg();
                $.ajax({
                            url: '/cfy/user/getSessionUser.do',
                            type: 'POST'
                        })
                .done(function(backdata) {
                    console.log(backdata.user);
                    $.each(xzdata, function(i,valobj) {
                       //  tjdata.push({fondsNum:valobj.fondsNum,srcUname:backdata.user.userName,desUname:"hello"});
                       valobj.sender=backdata.user.userName;
                       valobj.accepter="hello";
                       valobj.beiyong="未审核";
                    });
                    $.ajax({
                        url: '/cfy/fond/adYjJn.do',
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify(xzdata)
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

                })
                .fail(function() {
                    console.log("error");
                })
                .always(function() {
                    console.log("complete");
                });
            };
             if($('[name="admDaTjType"]').val()==="归档文件"){
                var xzdata=$adDaTjTab.bootstrapTable('getSelections');
                var tjdata=[],tjUname,jsUname;
                socket.sendMsg();
                $.ajax({
                            url: '/cfy/user/getSessionUser.do',
                            type: 'POST'
                        })
                .done(function(backdata) {
                    console.log(backdata.user);
                    $.each(xzdata, function(i,valobj) {
                       //  tjdata.push({fondsNum:valobj.fondsNum,srcUname:backdata.user.userName,desUname:"hello"});
                       valobj.sender=backdata.user.userName;
                       valobj.accepter="hello";
                       valobj.beiyong="未审核";
                    });
                    $.ajax({
                        url: '/cfy/fond/adYjJn.do',
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify(xzdata)
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

                })
                .fail(function() {
                    console.log("error");
                })
                .always(function() {
                    console.log("complete");
                });
            }


        });


                $('#admDaTjCkTabBtn').on('click',function(){
                    if($('[name="admDaTjType"]').val()==="卷内文件"){
                        $adDaTjTab.bootstrapTable('refreshOptions',{
                            pagination: true,
                            sidePagination: 'client',
                            pageList:[10,20,30,40],
                            pageNumber: 1,                       //初始化加载第一页，默认第一页
                            pageSize: 10,
                            method:'POST',
                            url: '/cfy/fond/adCxJnAllInfo.do',
                            toolbar:'#adDaTjTabTool',
                            toolbarAlign:'right',
                            columns: [
                            {
                            checkbox: true
                            },
                            {
                                field: 'fileRecords.fondsNum',
                                align: 'center',
                                title: "全宗号"
                            },{
                                field: 'fileRecords.catalogNum',
                                align: 'center',
                                title: "目录号"
                            },{
                                field: 'fileRecords.caseYear',
                                align: 'center',
                                title: "年度"
                            },{
                                field: 'fileRecords.caseNum',
                                align: 'center',
                                title: "案卷号"
                            },{
                                field: 'fileRecords.fileNum',
                                align: 'center',
                                title: "件号"
                            },{
                                field: 'fileRecords.caseName',
                                align: 'center',
                                title: "题名"

                            },{
                                field: 'fileRecords.safekeepingTerm',
                                align: 'center',
                                title: "保存期限"
                            },{
                                field: 'fileRecords.securityRank',
                                align: 'center',
                                title: "密级"
                            },{
                                field: 'fileRecords.responsibler',
                                align: 'center',
                                title: "责任者"
                            },{
                                field: 'fileRecords.safekeepingTerm',
                                align: 'center',
                                title: "保管期限"
                            },{
                                field: 'fileRecords.roomNum',
                                align: 'center',
                                title: "库房号"
                            },{
                                field: 'fileRecords.cabNum',
                                align: 'center',
                                title: "排柜号"
                            }
                            ]
                        });
                    };
                    if($('[name="admDaTjType"]').val()==="归档文件"){
                    };
                });

                 $('#admDaTjCkztBtn').on('click',function(){
                    if($('[name="admDaTjType"]').val()==="卷内文件"){
                        $adDaTjTab.bootstrapTable('refreshOptions',{
                            pagination: true,
                            sidePagination: 'client',
                            pageList:[10,20,30,40],
                            pageNumber: 1,                       //初始化加载第一页，默认第一页
                            pageSize: 10,
                            method:'POST',
                            url: '/cfy/fond/adCxSelfYjJn.do',
                            columns: [
                            {
                            checkbox: true
                            },
                            {
                                field: 'fondsNum',
                                align: 'center',
                                title: "全宗号"
                            },{
                                field: 'catalogNum',
                                align: 'center',
                                title: "目录号"
                            },{
                                field: 'caseYear',
                                align: 'center',
                                title: "年度"
                            },{
                                field: 'caseNum',
                                align: 'center',
                                title: "案卷号"
                            },{
                                field: 'fileNum',
                                align: 'center',
                                title: "件号"
                            },{
                                field: 'caseName',
                                align: 'center',
                                title: "题名"

                            },{
                                field: 'safekeepingTerm',
                                align: 'center',
                                title: "保存期限"
                            },{
                                field: 'securityRank',
                                align: 'center',
                                title: "密级"
                            },{
                                field: 'responsibler',
                                align: 'center',
                                title: "责任者"
                            },{
                                field: 'safekeepingTerm',
                                align: 'center',
                                title: "保管期限"
                            },{
                                field: 'roomNum',
                                align: 'center',
                                title: "库房号"
                            },{
                                field: 'cabNum',
                                align: 'center',
                                title: "排柜号"
                            },{
                                field: 'chakan',
                                align: 'center',
                                title: "查看"
                            },{
                                field: 'beiyong',
                                align: 'center',
                                title: "审核状态"
                            }
                            ]
                        });
                    };
                    if($('[name="admDaTjType"]').val()==="归档文件"){
                    };
                });



















/*
    $('#admDaTjCxBtn').on('click',function(){
            var $adDaTjTab;
            if($('[name="admDaTjType"]').val()==="卷内文件"){
                $adDaTjTab.bootstrapTable('refreshOptions',{
                    pagination: true,
                    sidePagination: 'client',
                    pageList:[10,20,30,40],
                    pageNumber: 1,                       //初始化加载第一页，默认第一页
                    pageSize: 10,
                    toolbar:'#adDaTjTabTool',
                    toolbarAlign:'right',
                    method:'POST',
                    url: '/cfy/fond/adCxJnAllInfo.do',
                    columns: [
                    {
                    checkbox: true
                    },
                    {
                        field: 'fondsNum',
                        align: 'center',
                        title: "全宗号"
                    },{
                        field: 'catalogNum',
                        align: 'center',
                        title: "目录号"
                    },{
                        field: 'caseYear',
                        align: 'center',
                        title: "年度"
                    },{
                        field: 'caseNum',
                        align: 'center',
                        title: "案卷号"
                    },{
                        field: 'fileNum',
                        align: 'center',
                        title: "件号"
                    },{
                        field: 'caseName',
                        align: 'center',
                        title: "题名"

                    },{
                        field: 'safekeepingTerm',
                        align: 'center',
                        title: "保存期限"
                    },{
                        field: 'securityRank',
                        align: 'center',
                        title: "密级"
                    },{
                        field: 'responsibler',
                        align: 'center',
                        title: "责任者"
                    },{
                        field: 'safekeepingTerm',
                        align: 'center',
                        title: "保管期限"
                    },{
                        field: 'roomNum',
                        align: 'center',
                        title: "库房号"
                    },{
                        field: 'cabNum',
                        align: 'center',
                        title: "排柜号"
                    }
                    ]
                });


                $('#admDaTjCkTabBtn').on('click',function(){
                        $adDaTjTab.bootstrapTable('refreshOptions',{
                            pagination: true,
                            sidePagination: 'client',
                            pageList:[10,20,30,40],
                            pageNumber: 1,                       //初始化加载第一页，默认第一页
                            pageSize: 10,
                            method:'POST',
                            url: '/cfy/fond/adCxJnAllInfo.do',
                            toolbar:'#adDaTjTabTool',
                            toolbarAlign:'right',
                            columns: [
                            {
                            checkbox: true
                            },
                            {
                                field: 'fondsNum',
                                align: 'center',
                                title: "全宗号"
                            },{
                                field: 'catalogNum',
                                align: 'center',
                                title: "目录号"
                            },{
                                field: 'caseYear',
                                align: 'center',
                                title: "年度"
                            },{
                                field: 'caseNum',
                                align: 'center',
                                title: "案卷号"
                            },{
                                field: 'fileNum',
                                align: 'center',
                                title: "件号"
                            },{
                                field: 'caseName',
                                align: 'center',
                                title: "题名"

                            },{
                                field: 'safekeepingTerm',
                                align: 'center',
                                title: "保存期限"
                            },{
                                field: 'securityRank',
                                align: 'center',
                                title: "密级"
                            },{
                                field: 'responsibler',
                                align: 'center',
                                title: "责任者"
                            },{
                                field: 'safekeepingTerm',
                                align: 'center',
                                title: "保管期限"
                            },{
                                field: 'roomNum',
                                align: 'center',
                                title: "库房号"
                            },{
                                field: 'cabNum',
                                align: 'center',
                                title: "排柜号"
                            }
                            ]
                        });
                });
                $('#admDaTjCkztBtn').on('click',function(){
                        $adDaTjTab.bootstrapTable('refreshOptions',{
                            pagination: true,
                            sidePagination: 'client',
                            pageList:[10,20,30,40],
                            pageNumber: 1,                       //初始化加载第一页，默认第一页
                            pageSize: 10,
                            method:'POST',
                            url: '/cfy/fond/adCxSelfYjJn.do',
                            columns: [
                            {
                            checkbox: true
                            },
                            {
                                field: 'fondsNum',
                                align: 'center',
                                title: "全宗号"
                            },{
                                field: 'catalogNum',
                                align: 'center',
                                title: "目录号"
                            },{
                                field: 'caseYear',
                                align: 'center',
                                title: "年度"
                            },{
                                field: 'caseNum',
                                align: 'center',
                                title: "案卷号"
                            },{
                                field: 'fileNum',
                                align: 'center',
                                title: "件号"
                            },{
                                field: 'caseName',
                                align: 'center',
                                title: "题名"

                            },{
                                field: 'safekeepingTerm',
                                align: 'center',
                                title: "保存期限"
                            },{
                                field: 'securityRank',
                                align: 'center',
                                title: "密级"
                            },{
                                field: 'responsibler',
                                align: 'center',
                                title: "责任者"
                            },{
                                field: 'safekeepingTerm',
                                align: 'center',
                                title: "保管期限"
                            },{
                                field: 'roomNum',
                                align: 'center',
                                title: "库房号"
                            },{
                                field: 'cabNum',
                                align: 'center',
                                title: "排柜号"
                            },{
                                field: 'chakan',
                                align: 'center',
                                title: "查看"
                            },{
                                field: 'beiyong',
                                align: 'center',
                                title: "审核状态"
                            }
                            ]
                        });
                });
                    //if($(this).val()==="卷内文件"){的另一半
            };
            if($('[name="admDaTjType"]').val()==="归档文件"){
                 $adDaJsTab=$('#adDaJsTab').bootstrapTable({
                    pagination: true,
                    sidePagination: 'client',
                    pageList:[10,20,30,40],
                    pageNumber: 1,                       //初始化加载第一页，默认第一页
                    pageSize: 10,
                    method:'POST',
                    url: '/cfy/fond/adCxGdAllInfo.do',
                    columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'fondsNum',
                        align: 'center',
                        title: "全宗号"
                    },{
                        field: 'fileYear',
                        align: 'center',
                        title: "年度"
                    },{
                        field: 'caseNum',
                        align: 'center',
                        title: "盒号"
                    },{
                        field: 'fileNum',
                        align: 'center',
                        title: "件号"
                    },{
                        field: 'caseName',
                        align: 'center',
                        title: "题名"

                    },{
                        field: 'safekeepingTerm',
                        align: 'center',
                        title: "保存期限"
                    },{
                        field: 'securityRank',
                        align: 'center',
                        title: "密级"
                    },{
                        field: 'responsibler',
                        align: 'center',
                        title: "责任者"
                    },{
                        field: 'safekeepingTerm',
                        align: 'center',
                        title: "保管期限"
                    },{
                        field: 'roomNum',
                        align: 'center',
                        title: "库房号"
                    },{
                        field: 'cabNum',
                        align: 'center',
                        title: "排柜号"
                    },{
                        field: 'chakan',
                        align: 'center',
                        title: "查看",
                        formatter:adDaJsTabCk
                    },{
                        field: 'chakan',
                        align: 'center',
                        title: "通过",
                        formatter:adDaJsTabCk
                    },{
                        field: 'bianji',
                        align: 'center',
                        title: "打回",
                        formatter:adDaJsTabDh
                    }
                    ]
                });
                function adDaJsTabCk(value, row, index) {

                    return '<a href="#" class="tabOperA tabOperACk" data-toggle="modal" data-target="#adGlJnCkMod">查看</a>';

                };
                function adDaJsTabCk(value, row, index) {

                    return '<a href="#" class="tabOperA tabOperABj" data-toggle="modal" data-target="#adGlJnXgMod">通过</a>';

                };
                function adDaJsTabDh(value, row, index) {

                    return '<a href="#" class="tabOperA tabOperASc">打回</a>';

                };
            //if($(this).val()==="归档文件"){的另一半
            }
    });
*/
});