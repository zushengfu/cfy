/*
* @Author: Marte
* @Date:   2017-08-31 14:40:37
* @Last Modified by:   Marte
* @Last Modified time: 2017-09-28 16:20:40
*/

$(document).ready(function() {
    $.ajax({
        url: '/cfy/user/getSessionUser.do',
        type: 'POST'
    })
    .done(function(backdata) {
        $adDaJsTab=$('#adDaJsTab').bootstrapTable({
                pagination: true,
                sidePagination: 'client',
                pageList:[10,20,30,40],
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 10,
                method:'POST',
                url: '/cfy/fond/adCxNoOperateYjJn.do',
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
                    field: 'sender',
                    align: 'center',
                    title: "发送人"
                },{
                    field: 'tongguo',
                    align: 'center',
                    title: "通过",
                    formatter:adDaJsTabTg
                },{
                    field: 'dahui',
                    align: 'center',
                    title: "打回",
                    formatter:adDaJsTabDh
                }
                ]
                });
                    function adDaJsTabCk(value, row, index) {

                    return '<a href="#" class="tabOperA tabOperACk" data-toggle="modal" data-target="#adGlJnCkMod">查看</a>';

                    };
                    function adDaJsTabTg(value, row, index) {

                        return '<a href="#" class="tabOperA tabOperABj" id="adDaJsTabTgA">通过</a>';

                    };
                    function adDaJsTabDh(value, row, index) {

                        return '<a href="#" class="tabOperA tabOperASc">打回</a>';

                    };
        if(backdata.user.userName==="hello"){
            $('[name="adDaJsType"]').on('blur',function(){
            var $adDaJsTab;
            if($(this).val()==="卷内文件"){
            $adDaJsTab=$('#adDaJsTab').bootstrapTable({
                pagination: true,
                sidePagination: 'client',
                pageList:[10,20,30,40],
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 10,
                method:'POST',
                url: '/cfy/fond/adCxNoOperateYjJn.do',
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
                    field: 'sender',
                    align: 'center',
                    title: "发送人"
                },{
                    field: 'tongguo',
                    align: 'center',
                    title: "通过",
                    formatter:adDaJsTabTg
                },{
                    field: 'dahui',
                    align: 'center',
                    title: "打回",
                    formatter:adDaJsTabDh
                }
                ]
                });
                    function adDaJsTabCk(value, row, index) {

                    return '<a href="#" class="tabOperA tabOperACk" data-toggle="modal" data-target="#adGlJnCkMod">查看</a>';

                    };
                    function adDaJsTabTg(value, row, index) {

                        return '<a href="#" class="tabOperA tabOperABj" id="adDaJsTabTgA">通过</a>';

                    };
                    function adDaJsTabDh(value, row, index) {

                        return '<a href="#" class="tabOperA tabOperASc">打回</a>';

                    };

                    $adDaJsTab.on('click-cell.bs.table', function (event, value, row, valobj) {
                        if(value==="tongguo"){
                            valobj.beiyong="已通过";
                             $.ajax({
                            url: '/cfy/fond/adYjXgJn.do',
                            type: 'POST',
                            contentType:'application/json',
                            data: JSON.stringify(valobj)
                            })
                            .done(function(backdata) {
                                $adDaJsTab.bootstrapTable('refresh');
                                alert(backdata.msg);
                            })
                            .fail(function() {
                                console.log("error");
                            })
                            .always(function() {
                                console.log("complete");
                            });
                        }
                        if(value==="dahui"){
                            valobj.beiyong="不合格";
                             $.ajax({
                            url: '/cfy/fond/adYjXgJn.do',
                            type: 'POST',
                            contentType:'application/json',
                            data: JSON.stringify(valobj)
                            })
                            .done(function(backdata) {
                                $adDaJsTab.bootstrapTable('refresh');
                                alert(backdata.msg);
                            })
                            .fail(function() {
                                console.log("error");
                            })
                            .always(function() {
                                console.log("complete");
                            });
                        }
                    });

                    //if($(this).val()==="卷内文件"){的另一半
                }
                if($(this).val()==="归档文件"){
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
                            field: 'sender',
                            align: 'center',
                            title: "发送人"
                        },{
                            field: 'beiyong',
                            align: 'center',
                            visible:false
                        },{
                            field: 'chakan',
                            align: 'center',
                            title: "查看",
                            formatter:adDaJsTabCk
                        },{
                            field: 'chakan',
                            align: 'center',
                            title: "通过",
                            formatter:adDaJsTabTg
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
                    function adDaJsTabTg(value, row, index) {

                        return '<a href="#" class="tabOperA tabOperABj" id="adDaJsTabTgA">通过</a>';

                    };
                    function adDaJsTabDh(value, row, index) {

                        return '<a href="#" class="tabOperA tabOperASc">打回</a>';

                    };
            //if($(this).val()==="归档文件"){的另一半
            }
            });
            //if(backdata.user.userName==="hello"){的另一半
        }
        else{
            $('#ad_maincontent').empty();
            $('#ad_maincontent').html("你没有此权限！");
        }
    })
    .fail(function() {
        console.log("error");
    })
    .always(function() {
        console.log("complete");
    });



});