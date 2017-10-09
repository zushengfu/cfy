/*
* @Author: Marte
* @Date:   2017-08-17 11:26:21
* @Last Modified by:   Marte
* @Last Modified time: 2017-09-30 13:29:58
*/
 (function ($) {
    'use strict';
    var sprintf = $.fn.bootstrapTable.utils.sprintf;

    var TYPE_NAME = {
        doc: 'DOC',
        excel: 'Excel',
        csv: 'CSV',
        txt: 'TXT'
    };

    $.extend($.fn.bootstrapTable.defaults, {
        showExport: false,
        exportDataType: 'all', // basic, all, selected
        // 'json', 'xml', 'png', 'csv', 'txt', 'sql', 'doc', 'excel', 'powerpoint', 'pdf'
        exportTypes: ['csv', 'txt',  'excel', 'doc'],
        exportOptions: {}
    });

    $.extend($.fn.bootstrapTable.defaults.icons, {
        export: 'glyphicon-export icon-share'
    });

    $.extend($.fn.bootstrapTable.locales, {
        formatExport: function () {
            return 'Export data';
        }
    });
    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales);

    var BootstrapTable = $.fn.bootstrapTable.Constructor,
        _initToolbar = BootstrapTable.prototype.initToolbar;

    BootstrapTable.prototype.initToolbar = function () {
        this.showToolbar = this.options.showExport;

        _initToolbar.apply(this, Array.prototype.slice.apply(arguments));

        if (this.options.showExport) {
            var that = this,
                $btnGroup = this.$toolbar.find('>.btn-group'),
                $export = $btnGroup.find('div.export');

            if (!$export.length) {
                $export = $([
                    '<div class="export btn-group">',
                        '<button class="btn' +
                            sprintf(' btn-%s', this.options.buttonsClass) +
                            sprintf(' btn-%s', this.options.iconSize) +
                            ' dropdown-toggle" aria-label="export type" ' +
                            'title="' + this.options.formatExport() + '" ' +
                            'data-toggle="dropdown" type="button">',
                            sprintf('<i class="%s %s"></i> ', this.options.iconsPrefix, this.options.icons.export),
                            '<span class="caret"></span>',
                        '</button>',
                        '<ul class="dropdown-menu" role="menu">',
                        '</ul>',
                    '</div>'].join('')).appendTo($btnGroup);

                var $menu = $export.find('.dropdown-menu'),
                    exportTypes = this.options.exportTypes;

                if (typeof this.options.exportTypes === 'string') {
                    var types = this.options.exportTypes.slice(1, -1).replace(/ /g, '').split(',');

                    exportTypes = [];
                    $.each(types, function (i, value) {
                        exportTypes.push(value.slice(1, -1));
                    });
                }
                $.each(exportTypes, function (i, type) {
                    if (TYPE_NAME.hasOwnProperty(type)) {
                        $menu.append(['<li role="menuitem" data-type="' + type + '">',
                                '<a href="javascript:void(0)">',
                                    TYPE_NAME[type],
                                '</a>',
                            '</li>'].join(''));
                    }
                });

                $menu.find('li').click(function () {
                    var type = $(this).data('type'),
                        doExport = function () {
                            that.$el.tableExport($.extend({}, that.options.exportOptions, {
                                type: type,
                                escape: false
                            }));
                        };

                    if (that.options.exportDataType === 'all' && that.options.pagination) {
                        that.$el.one(that.options.sidePagination === 'server' ? 'post-body.bs.table' : 'page-change.bs.table', function () {
                            doExport();
                            that.togglePagination();
                        });
                        that.togglePagination();
                    } else if (that.options.exportDataType === 'selected') {
                        var data = that.getData(),
                            selectedData = that.getAllSelections();

                        // Quick fix #2220
                        if (that.options.sidePagination === 'server') {
                            data = {total: that.options.totalRows};
                            data[that.options.dataField] = that.getData();

                            selectedData = {total: that.options.totalRows};
                            selectedData[that.options.dataField] = that.getAllSelections();
                        }

                        that.load(selectedData);
                        doExport();
                        that.load(data);
                    } else {
                        doExport();
                    }
                });
            }
        }
    };
})(jQuery);



(function(){
        var $adGlQzTab,$adGlAjTab,$adGlJnTab,$adGlGdTab,$adGlJnCkTpTab,$adGlRyTab;
        var testdata=[
            {
                fondsNum:1,
                fondsName: "aaa",
                fondsYear: 11,
                remarks: "aaa",
                roomNum: 111,
                cabNum: 1111,
                fileNum:  11111
            },{
                fondsNum:2,
                fondsName: "b",
                fondsYear: 22,
                remarks: "bb",
                roomNum: 222,
                cabNum: 2222,
                fileNum: 22222
            },{
                fondsNum:1,
                fondsName: "aaa",
                fondsYear: 11,
                remarks: "aaa",
                roomNum: 111,
                cabNum: 1111,
                fileNum:  11111
            }

            ,{
                fondsNum:1,
                fondsName: "aaa",
                fondsYear: 11,
                remarks: "aaa",
                roomNum: 111,
                cabNum: 1111,
                fileNum:  11111
            },{
                fondsNum:1,
                fondsName: "aaa",
                fondsYear: 11,
                remarks: "aaa",
                roomNum: 111,
                cabNum: 1111,
                fileNum:  11111
            },{
                fondsNum:1,
                fondsName: "aaa",
                fondsYear: 11,
                remarks: "aaa",
                roomNum: 111,
                cabNum: 1111,
                fileNum:  11111
            },{
                fondsNum:1,
                fondsName: "aaa",
                fondsYear: 11,
                remarks: "aaa",
                roomNum: 111,
                cabNum: 1111,
                fileNum:  11111
            },{
                fondsNum:1,
                fondsName: "aaa",
                fondsYear: 11,
                remarks: "aaa",
                roomNum: 111,
                cabNum: 1111,
                fileNum:  11111
            },{
                fondsNum:1,
                fondsName: "aaa",
                fondsYear: 11,
                remarks: "aaa",
                roomNum: 111,
                cabNum: 1111,
                fileNum:  11111
            },{
                fondsNum:1,
                fondsName: "aaa",
                fondsYear: 11,
                remarks: "aaa",
                roomNum: 111,
                cabNum: 1111,
                fileNum:  11111
            },{
                fondsNum:1,
                fondsName: "aaa",
                fondsYear: 11,
                remarks: "aaa",
                roomNum: 111,
                cabNum: 1111,
                fileNum:  11111
            },{
                fondsNum:1,
                fondsName: "aaa",
                fondsYear: 11,
                remarks: "aaa",
                roomNum: 111,
                cabNum: 1111,
                fileNum:  11111
            },{
                fondsNum:1,
                fondsName: "aaa",
                fondsYear: 11,
                remarks: "aaa",
                roomNum: 111,
                cabNum: 1111,
                fileNum:  11111
            }
        ];
        //请求编号并改变下拉框，第一个是查询的地址，第二个是要改变的元素(css选择器)，第三个是查询参数
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
                //为时间选择器绑定事件
                $('[name="startTime"]').datetimepicker({
                    format: 'yyyy-mm-dd',
                    minView:2
                });
                $('[name="endTime"]').datetimepicker({
                    format: 'yyyy-mm-dd',
                    minView:2
                });
        //全宗的管理页面
        if($('#adGlQzTab').attr('id')!==undefined){
            $adGlQzTab=$('#adGlQzTab').bootstrapTable({
                pagination: true,
                sidePagination: 'client',
                pageList:[10,20,30,40],
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 10,
                showColumns:true,
                showExport: true,             //是否显示导出
                exportOptions:{
                    ignoreColumn: [4,5]
                },
                toolbar: '#adGlQzTabTool',
                method:'POST',
                url: '../fond/adCxQzAllInfo.do',
                columns: [
                {
                checkbox: true
                },
                {
                    field: 'fondsNum',
                    align: 'center',
                    title: "全宗号"
                },{
                    field: 'fondsName',
                    align: 'center',
                    title: "全宗名称"
                },{
                    field: 'roomNum',
                    align: 'center',
                    title: "库房号"
                },{
                    field: 'cabNum',
                    align: 'center',
                    title: "排柜号"
                },{
                    field: 'startTime',
                    align: 'center',
                    title: "起始时间"

                },{
                    field: 'endTime',
                    align: 'center',
                    title: "结束时间"
                },{
                    field: 'remarks',
                    align: 'center',
                    title: "备注"
                },{
                    field: 'chakan',
                    align: 'center',
                    title: "查看",
                    formatter:adGlQzFmCk
                },{
                    field: 'bianji',
                    align: 'center',
                    title: "编辑",
                    formatter:adGlQzFmBj
                },{
                    field: 'shanchu',
                    align: 'center',
                    title: "删除",
                    formatter:adGlQzFmSc
                }
                ],
                data: testdata

                });
            function adGlQzFmCk(value, row, index) {

                return '<a href="#" class="tabOperA tabOperACk" data-toggle="modal" data-target="#adGlQzCkMod">查看</a>';

            }
            function adGlQzFmBj(value, row, index) {

                return '<a href="#" class="tabOperA tabOperABj" data-toggle="modal" data-target="#adGlQzXgMod">编辑</a>';

            }
            function adGlQzFmSc(value, row, index) {

                return '<a href="#" class="tabOperA tabOperASc">删除</a>';

            }
            //为操作单元格绑定事件，根据查看、删除、修改分别绑定不同的事件
            $adGlQzTab.on('click-cell.bs.table', function (event, value, row, valobj)  {

                if(value==='chakan'){

                    $("#adGlQzCkForm").populateForm(valobj);
                };
                if(value==='bianji'){

                    $("#adGlQzXgForm").populateForm(valobj);
                };

                if(value==='shanchu'){

                            var scconfirm=confirm("确定要删除吗？");
                    if(scconfirm===true){

                        $.ajax({
                            url: '../fond/adScQz.do',
                            type: 'POST',
                            contentType:'application/json',
                            data: JSON.stringify({fondsNum:valobj.fondsNum})
                        })
                        .done(function(backdata) {
                            $adGlQzTab.bootstrapTable('refresh');
                            alert(backdata.msg);
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
                    }
                }
            });
            //全宗搜索按钮的事件绑定
            $('#adGlQzFoSs').on('click',function(){
                console.log($('#adGlQzForm').serializeJSON());
                $.ajax({
                            url: '../fond/adMhCxQz.do',
                            type: 'POST',
                            contentType:'application/json',
                            data: JSON.stringify($('#adGlQzForm').serializeJSON())
                        })
                        .done(function(backdata) {
                            console.log(backdata);
                            $adGlQzTab.bootstrapTable('load', backdata);
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
            });
            //全宗重置按钮的事件绑定
            $('#adGlQzFoCz').on('click',function(){
                console.log($('#adGlQzForm').serializeJSON());
                $("#adGlQzForm")[0].reset();
                $adGlQzTab.bootstrapTable('refresh');
            });
            //全宗表格工具栏的批量删除的事件绑定
            $('#adGlQzTabScBtn').on('click',function(){
                $adGlQzTab.bootstrapTable('getSelections');
                console.log(JSON.stringify($adGlQzTab.bootstrapTable('getSelections')));
                var ids = $.map($adGlQzTab.bootstrapTable('getSelections'), function (row) {
                return row.fondsNum;
                });
                console.log({fondsNum:JSON.stringify(ids)});
                $.ajax({
                            url: '../fond/adPlScQz.do',
                            type: 'POST',
                            contentType:'application/json',
                            data: JSON.stringify({fondsNum:ids})
                        })
                        .done(function(backdata) {
                            $adGlQzTab.bootstrapTable('refresh');
                            alert(backdata.msg);
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
            });

            //全宗修改模态框的事件绑定
            $('#adGlQzXgModSub').on('click',function(){
                    $.ajax({
                            url: '../fond/adXgQz.do',
                            type: 'POST',
                            contentType:'application/json',
                            data:JSON.stringify($('#adGlQzXgForm').serializeJSON())
                        })
                        .done(function(backdata) {
                            $adGlQzTab.bootstrapTable('refresh');
                            alert(backdata.msg);
                            $("#adGlQzXgMod").modal('hide');
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
            });
            //全宗查看模态框的打印事件绑定
            $("#adGlQzCkModPrf").on("click",function(event) {
                $("#adGlQzCkMod .modal-body").find('input').each(function(){
                    $(this).attr('value',$(this).val());
                console.log($(this).val());
              })
                $("#adGlQzCkMod .modal-body").jqprint({
                 debug: false, //如果是true则可以显示iframe查看效果（iframe默认高和宽都很小，可以再源码中调大），默认是false
                 importCSS: true, //true表示引进原来的页面的css，默认是true。（如果是true，先会找$("link[media=print]")，若没有会去找$("link")中的css文件）
                 printContainer: true, //表示如果原来选择的对象必须被纳入打印（注意：设置为false可能会打破你的CSS规则）。
                 operaSupport: true//表示如果插件也必须支持歌opera浏览器，在这种情况下，它提供了建立一个临时的打印选项卡。默认是true
                });
            });
        }
        //案卷的管理页面
        if($('#adGlAjTab').attr('id')!==undefined){
            $adGlAjTab=$('#adGlAjTab').bootstrapTable({
                pagination: true,
                sidePagination: 'client',
                pageList:[10,20,30,40],
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 10,
                showColumns:true,
                showExport: true,             //是否显示导出
                exportOptions:{
                    ignoreColumn: [4,5]
                },
                toolbar: '#adGlAjTabTool',
                method:'POST',
                url: '../fond/adCxAjAllInfo.do',
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
                    field: 'caseName',
                    align: 'center',
                    title: "案卷题名"
                },{
                    field: 'startTime',
                    align: 'center',
                    title: "起始时间"

                },{
                    field: 'endTime',
                    align: 'center',
                    title: "结束时间"
                },{
                    field: 'chakan',
                    align: 'center',
                    title: "查看",
                    formatter:adGlAjFmCk
                },{
                    field: 'bianji',
                    align: 'center',
                    title: "编辑",
                    formatter:adGlAjFmBj
                },{
                    field: 'shanchu',
                    align: 'center',
                    title: "删除",
                    formatter:adGlAjFmSc
                }
                ]

                });
            function adGlAjFmCk(value, row, index) {

                return '<a href="#" class="tabOperA tabOperACk" data-toggle="modal" data-target="#adGlAjCkMod">查看</a>';

            };
            function adGlAjFmBj(value, row, index) {

                return '<a href="#" class="tabOperA tabOperABj" data-toggle="modal" data-target="#adGlAjXgMod">编辑</a>';

            };
            function adGlAjFmSc(value, row, index) {

                return '<a href="#" class="tabOperA tabOperASc">删除</a>';

            };
            adCxXs("/cfy/fond/adCxQz.do",'[name="fondsNum"]');
            $('[name="fondsNum"]').on("blur",function(){
                                var subqueryurl="/cfy/fond/adCxAjForAjSearch.do";
                                var adCxParm=$(this).val();
                                var querydata=JSON.stringify({fondsNum:adCxParm});
                                $.ajax({
                                        url: subqueryurl,
                                        type: 'POST',
                                        contentType:"Application/JSON; charset=utf-8",
                                        data:querydata
                                    })
                                    .done(function(backdata) {
                                        $('[name="catalogNum"]').empty();
                                        $('[name="catalogNum"]').append("<option></option");
                                        $.each(backdata, function(i, obj){
                                        if(obj.catalogNum==""){
                                            obj.catalogNum=obj.caseYear;
                                        }
                                            var opt="<option>"+obj.catalogNum+"</option>";
                                            $('[name="catalogNum"]').append(opt);
                                        });
                                    })
                                    .fail(function() {
                                        console.log("error");
                                    })
                                    .always(function() {
                                        console.log("complete");
                                    });

                            });
            //案卷重置按钮的事件绑定
            $('#adGlAjFoCz').on('click',function(){

                $("#adGlAjForm")[0].reset();
                $adGlAjTab.bootstrapTable('refresh');
            });
            //为操作单元格绑定事件，根据查看、删除、修改分别绑定不同的事件
            $adGlAjTab.on('click-cell.bs.table', function (event, value, row, valobj)  {

                if(value==='chakan'){

                    $("#adGlAjCkForm").populateForm(valobj);
                };
                if(value==='bianji'){

                    $("#adGlAjXgForm").populateForm(valobj);
                };

                if(value==='shanchu'){

                            var scconfirm=confirm("确定要删除吗？");
                    if(scconfirm===true){

                        $.ajax({
                            url: '../fond/adScAj.do',
                            type: 'POST',
                            contentType:'application/json',
                            data: JSON.stringify({fondsNum:valobj.fondsNum,caseNum:valobj.caseNum})
                        })
                        .done(function(backdata) {
                            $adGlAjTab.bootstrapTable('refresh');
                            alert(backdata.msg);
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
                    }
                }
            });
            //案卷搜索按钮的事件绑定
            $('#adGlAjFoSs').on('click',function(){
                console.log($('#adGlAjForm').serializeJSON());
                $.ajax({
                            url: '../fond/adMhCxAj.do',
                            type: 'POST',
                            contentType:'application/json',
                            data: JSON.stringify($('#adGlAjForm').serializeJSON())
                        })
                        .done(function(backdata) {
                            console.log(backdata);
                            $adGlAjTab.bootstrapTable('load', backdata);
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
            });
            //案卷重置按钮的事件绑定
            $('#adGlAjFoCz').on('click',function(){
                console.log($('#adGlAjForm').serializeJSON());
                $("#adGlAjForm")[0].reset();
                $adGlAjTab.bootstrapTable('refresh');
            });
            //案卷表格工具栏的批量删除的事件绑定
            $('#adGlAjTabScBtn').on('click',function(){
                $adGlAjTab.bootstrapTable('getSelections');
                console.log(JSON.stringify($adGlAjTab.bootstrapTable('getSelections')));
                var ids = $.map($adGlAjTab.bootstrapTable('getSelections'), function (row) {
                return row.fondsNum;
                });
                console.log({fondsNum:JSON.stringify(ids)});
                $.ajax({
                            url: '../fond/adPlScQz.do',
                            type: 'POST',
                            contentType:'application/json',
                            data: {fondsNum:JSON.stringify(ids)}
                        })
                        .done(function(backdata) {
                            $adGlAjTab.bootstrapTable('refresh');
                            alert(backdata.msg);
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
            });
            //案卷修改模态框的事件绑定
            $('#adGlAjXgModSub').on('click',function(){
                    $.ajax({
                            url: '../fond/adXgAj.do',
                            type: 'POST',
                            contentType:'application/json',
                            data:JSON.stringify($('#adGlAjXgForm').serializeJSON())
                        })
                        .done(function(backdata) {
                            $adGlAjTab.bootstrapTable('refresh');
                            alert(backdata.msg);
                            $("#adGlAjXgMod").modal('hide');
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
            });
            //案卷查看模态框的打印事件绑定
            $("#adGlAjCkModPrf").on("click",function(event) {
                $("#adGlAjCkMod .modal-body").find('input').each(function(){
                    $(this).attr('value',$(this).val());
                console.log($(this).val());
              })
                $("#adGlAjCkMod .modal-body").jqprint({
                 debug: false, //如果是true则可以显示iframe查看效果（iframe默认高和宽都很小，可以再源码中调大），默认是false
                 importCSS: true, //true表示引进原来的页面的css，默认是true。（如果是true，先会找$("link[media=print]")，若没有会去找$("link")中的css文件）
                 printContainer: true, //表示如果原来选择的对象必须被纳入打印（注意：设置为false可能会打破你的CSS规则）。
                 operaSupport: true//表示如果插件也必须支持歌opera浏览器，在这种情况下，它提供了建立一个临时的打印选项卡。默认是true
                });
            });

        }
        //卷内的管理页面
        if($('#adGlJnTab').attr('id')!==undefined){
                $adGlJnTab=$('#adGlJnTab').bootstrapTable({
                pagination: true,
                sidePagination: 'client',
                pageList:[10,20,30,40],
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 10,
                showColumns:true,
                showExport: true,             //是否显示导出
                exportOptions:{
                    ignoreColumn: [0]
                },
                toolbar: '#adGlJnTabTool',
                method:'POST',
                url: '../fond/adCxJnAllInfo.do',
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
                    field: 'fileRecords.roomNum',
                    align: 'center',
                    title: "库房号"
                },{
                    field: 'fileRecords.cabNum',
                    align: 'center',
                    title: "排柜号"
                },{
                    field: 'chakan',
                    align: 'center',
                    title: "查看",
                    formatter:adGlJnFmCk
                },{
                    field: 'bianji',
                    align: 'center',
                    title: "编辑",
                    formatter:adGlJnFmBj
                },{
                    field: 'shanchu',
                    align: 'center',
                    title: "删除",
                    formatter:adGlJnFmSc
                }
                ]

                });
            function adGlJnFmCk(value, row, index) {

                return '<a href="#" class="tabOperA tabOperACk" data-toggle="modal" data-target="#adGlJnCkMod">查看</a>';

            };
            function adGlJnFmBj(value, row, index) {

                return '<a href="#" class="tabOperA tabOperABj" data-toggle="modal" data-target="#adGlJnXgMod">编辑</a>';

            };
            function adGlJnFmSc(value, row, index) {

                return '<a href="#" class="tabOperA tabOperASc">删除</a>';

            };
            //卷内的提交审核按钮
            $('#adGlJnTabTjBtn').on('click',function(){
                var xzdata=$adGlJnTab.bootstrapTable('getSelections');
                var tjdata=[],tjUname,jsUname;

                $.ajax({
                            url: '/cfy/user/getSessionUser.do',
                            type: 'POST'
                        })
                        .done(function(backdata) {
                            console.log(backdata.user);
                            $.each(xzdata, function(i,valobj) {
                               //  tjdata.push({fondsNum:valobj.fondsNum,srcUname:backdata.user.userName,desUname:"hello"});
                            	//delete valobj["0"];
                               valobj.sender=backdata.user.userName;
                               valobj.accepter="hello";
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
            });
            adCxXs("/cfy/fond/adCxQz.do",'[name="fondsNum"]');
            $('[name="fondsNum"]').on("blur",function(){
                                var adCxParm=$(this).val();
                                var querydata=JSON.stringify({fondsNum:adCxParm});
                                var subqueryurl="/cfy/fond/adCxAjForAjSearch.do";
                                $.ajax({
                                        url: subqueryurl,
                                        type: 'POST',
                                        contentType:"Application/JSON; charset=utf-8",
                                        data:querydata
                                    })
                                    .done(function(backdata) {
                                        $('[name="catalogNum"]').empty();
                                        var opt="<option></option>";
                                        $('[name="catalogNum"]').append(opt);
                                        $.each(backdata, function(i, obj){
                                            if(obj.catalogNum!="")
                                            {
                                                opt="<option>"+obj.catalogNum+"</option>";
                                                $('[name="catalogNum"]').append(opt);
                                            }

                                        });
                                    })
                                    .fail(function() {
                                        console.log("error");
                                    })
                                    .always(function() {
                                        console.log("complete");
                                    });
                            });
            $('[name="catalogNum"]').on("blur",function(){
                                var adCxParm=$(this).val();
                                var querydata=JSON.stringify({fondsNum:$('[name="fondsNum"]').val(),catalogNum:adCxParm});
                                adCxXs('/cfy/fond/adCxAjCaseNum.do','[name="caseNum"]',querydata);

                            });
            //卷内搜索按钮的事件绑定
            $('#adGlJnFoSs').on('click',function(){
                $.ajax({
                            url: '/cfy/fond/adMhCxJn.do',
                            type: 'POST',
                            contentType:'application/json',
                            data: JSON.stringify($('#adGlJnForm').serializeJSON())
                        })
                        .done(function(backdata) {
                            console.log(backdata);
                            /*
                            var newdata={fileRecords:{}};
                            var newdataArr=[];
                            for(var i=0;i<backdata.length;i++){
                            	
                            	newdata[i].fileRecords.fondsNum=backdata[i].fondsNum;
                            	newdata[i].fileRecords.catalogNum=backdata[i].fcatalogNum;
                            	newdata[i].fileRecords.caseYear=backdata[i].caseYear;
                            	newdata[i].fileRecords.caseNum=backdata[i].caseNum;
                            	newdata[i].fileRecords.fileNum=backdata[i].fileNum;
                            	newdata[i].fileRecords.safekeepingTerm=backdata[i].safekeepingTerm;
                            	newdata[i].fileRecords.securityRank=backdata[i].securityRank;
                            	newdata[i].fileRecords.responsibler=backdata[i].responsibler;
                            	newdata[i].fileRecords.roomNum=backdata[i].roomNum;
                            	newdata[i].fileRecords.cabNum=backdata[i].cabNum;
                            	newdataArr.push(newdata);
                            }
                            console.log(newdataArr);
                            */
                            $adGlJnTab.bootstrapTable('load', backdata);
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
            });
            //卷内搜索重置按钮的事件绑定
            $('#adGlJnFoCz').on('click',function(){
                $("#adGlJnForm")[0].reset();
                $adGlJnTab.bootstrapTable('refresh');
            });
            //切换到卷内文件关键字搜索的按钮
            $("#JnhrfToGjssFom").on('click',function(){
                $('#adGlJnForm').slideUp(700,function(){
                    $('#adGlJnGjzForm').fadeIn(300);
                });
            });
            //切换到卷内文件条件搜索的按钮
            $("#JnhrfToTjssFom").on('click',function(){
                $('#adGlJnGjzForm').slideUp(700,function(){
                    $('#adGlJnForm').fadeIn(300);
                });
            });
          //卷内文件关键字搜索
            $('#adGlJnFoGjzss').on('click',function(){
                $.ajax({
                        url: '/cfy/file/adSyQw.do',
                        type: 'GET',
                        contentType:'application/json',
                        data: 'condition='+$('[name="keywords"]').val()
                    })
                    .done(function(backdata) {
                        console.log(backdata);
                        $adGlJnTab.bootstrapTable('load', backdata);
                    })
                    .fail(function() {
                        console.log("error");
                    })
                    .always(function() {
                        console.log("complete");
                    });

        });
            /*
             $adGlJnTab.on('load-success.bs.table',function(event, value, row, valobj){

                    alert("表格数据加载完成！")
                var xzdata=[{xiazai:"111"},{xiazai:"111"},{xiazai:"111"},{xiazai:"111"},{xiazai:"111"}]
            });
            */
            //为操作单元格绑定事件，根据查看、删除、修改分别绑定不同的事件
            $adGlJnTab.on('click-cell.bs.table', function (event, value, row, valobj)  {

                var newvalobj=valobj.fileRecords;
                console.log(newvalobj);
                if(value==='chakan'){
                    $("#adGlJnCkForm").populateForm(newvalobj);
                    $("#adGlJnCkTpBtn").one("click",function(){
                    	var queryPar=JSON.stringify({"fondsNum":newvalobj.fondsNum, "catalogNum":newvalobj.catalogNum, "fileNum":newvalobj.fileNum});
                    	console.log(queryPar);
                    	$("#adGlJnCkTpName").empty();
                    	$("#adGlJnCkTpCal").empty();
                    	$.ajax({
                    	    url: '/cfy/file/adCxJnTpName.do',
                    	    type: 'POST',
                    	    contentType:"application/json",
                    	    data: queryPar
                    	})
                    	.done(function(backdata) {
                    	    console.log(backdata.length);
                            if(backdata.length===0){
                                $("#adGlJnCkTpName").append('<h3 style="text-align: center;">没有图片信息</h3>');
                            }
                            else{
                                for(var i=0;i<backdata.length;i++){
                                    var tpnameh6='<h6 data-target="#myCarousel" data-slide-to="'+i+'" class="active">'+backdata[i].img+"</h6>";
                                    var currentimg='adGlJnCkTpImg'+i;

                                    var tpimg='<div class="item" ><img id="'+currentimg+'" src="" style="display: inline-block;"></div>';
                                    if(i==0){
                                        tpimg='<div class="item active" ><img id="'+currentimg+'" src="" style="display: inline-block;"></div>'
                                    }
                                    $("#adGlJnCkTpName").append(tpnameh6);
                                    $("#adGlJnCkTpCal").append(tpimg);
                                    var currentimgurl='/cfy/file/adCxJnTpAdd.do?picName='+backdata[i].img;
                                    $('#'+currentimg).attr('src',currentimgurl);
                                }
                            }


                    	    $("#adGlJnCkTpName").on('click',function(event){
                    	    	/*
                    	    	var imgurl='/cfy/file/adCxJnTpAdd.do?picName='+$(event.target).html();
                                $('#adGlJnCkTpImg0').attr('src',imgurl);
                                */
                    	    	$(this).css('background','white');
                    	    	$(event.target).css('background','lightblue');
                    	    });

                    	})
                    	.fail(function() {
                    	    console.log("error");
                    	})
                    	.always(function() {
                    	    console.log("complete");
                    	});
                    	/*
                    	if($adGlJnCkTpTab!=undefined){
                    		$adGlJnCkTpTab.bootstrapTable('refreshOptions',{queryParams:queryPar});
                    		$adGlJnCkTpTab.bootstrapTable('refresh');
                    	}
                    	else
                    		{
                    		$adGlJnCkTpTab=$('#adGlJnCkTpTab').bootstrapTable({
                                url:"/cfy/file/adCxJnTpName.do",
                                method:"POST",
                                columns: [
                                {
                                    field: 'img',
                                    align: 'center',
                                    title: "附件列表"
                                }],
                                queryParams:queryPar
                                });

                                $adGlJnCkTpTab.on('click-cell.bs.table', function (field, value, row, valobj) {

                                    console.log(valobj.img);
                                    var url='/cfy/file/adCxJnTpAdd.do?picName='+valobj.img;
                                    $('#adGlJnCkTpImg').attr('src',url);
                                });
                    		}
                    		*/
                            var deg=0;
                            var  scale;
                            //图片查看器
                    	$('#adCkJnTpFd').on('click',function(){
                    		console.log("你点击了放大！");
                           scale='scale('+1.3+','+1.3+')';
                            $('#adGlJnCkTpCal .active').css('transform',scale);
                        });

                        $('#adCkJnTpSx').on('click',function(){
                            scale='scale('+1+','+1+')';
                               console.log(scale);
                                $('#adGlJnCkTpCal .active').css('transform',scale);

                        });
                        $('#adCkJnTpZz').on('click',function(){
                             (deg==-360)&&(deg=0);
                                deg-=90;
                               var  transformdeg='rotate('+deg+'deg)';
                                $('#adGlJnCkTpCal .active').css('transform',transformdeg);
                        });
                        $('#adCkJnTpYz').on('click',function(){
                            (deg==360)&&(deg=0);
                            deg+=90;
                           var  transformdeg='rotate('+deg+'deg)';
                            $('#adGlJnCkTpCal .active').css('transform',transformdeg);
                        });
                    });
                    $("#adGlJnCkModXz").on('click',function(){
                            location.href='/cfy/file/fileRecordsDownloadZip.do?'+'fondsNum='+valobj.fondsNum+'&catalogNum='+valobj.catalogNum+'&caseNum='+valobj.caseNum+'&fileNum='+valobj.fileNum
                    });
                };
                if(value==='bianji'){

                    $("#adGlJnXgForm").populateForm(newvalobj);
                    //卷内修改模态框的事件绑定
                    $('#adGlJnXgModSub').on('click',function(){
                    $.ajax({
                            url: '../fond/adXgJn.do',
                            type: 'POST',
                            contentType:'application/json',
                            data:JSON.stringify($('#adGlAjXgForm').serializeJSON())
                        })
                        .done(function(backdata) {
                            $adGlAjTab.bootstrapTable('refresh');
                            alert(backdata.msg);
                            $("#adGlJnXgMod").modal('hide');
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
                    });
                };

                if(value==='shanchu'){
                    var scconfirm=confirm("确定要删除吗？");
                    if(scconfirm===true){
                            $.ajax({
                                url: '/cfy/fond/adScJn.do',
                                type: 'POST',
                                contentType:'application/json',
                                data: JSON.stringify(valobj)
                            })
                            .done(function(backdata) {
                                $adGlJnTab.bootstrapTable('refresh');
                                alert(backdata.msg);
                            })
                            .fail(function() {
                                console.log("error");
                            })
                            .always(function() {
                                console.log("complete");
                            });
                    }
                }

            });
            //卷内查看模态框的打印事件绑定
            $("#adGlJnCkModPrf").on("click",function(event) {
                $("#adGlJnCkMod.modal-body").find('input').each(function(){
                    $(this).attr('value',$(this).val());
                console.log($(this).val());
              })
                $("#adGlJnCkMod .modal-body").jqprint({
                 debug: false, //如果是true则可以显示iframe查看效果（iframe默认高和宽都很小，可以再源码中调大），默认是false
                 importCSS: true, //true表示引进原来的页面的css，默认是true。（如果是true，先会找$("link[media=print]")，若没有会去找$("link")中的css文件）
                 printContainer: true, //表示如果原来选择的对象必须被纳入打印（注意：设置为false可能会打破你的CSS规则）。
                 operaSupport: true//表示如果插件也必须支持歌opera浏览器，在这种情况下，它提供了建立一个临时的打印选项卡。默认是true
                });
            });
            //卷内下载按钮的事件绑定

        //这是卷内if($('#adGlJnTab').attr('id')!==undefined)的另一半

        }
        //归档的管理页面adGlRyTab
        if($('#adGlGdTab').attr('id')!==undefined){
                $adGlGdTab=$('#adGlGdTab').bootstrapTable({
                    pagination: true,
                    sidePagination: 'client',
                    pageList:[10,20,30,40],
                    pageNumber: 1,                       //初始化加载第一页，默认第一页
                    pageSize: 10,
                    showColumns:true,
                    showExport: true,             //是否显示导出
                    exportOptions:{
                        ignoreColumn: [4,5]
                    },
                    toolbar: '#adGlGdTabTool',
                    method:'POST',
                    url: '/cfy/fond/adCxGdAllInfo.do',
                    columns: [
                    {
                    checkbox: true
                    },
                    {
                        field: 'fileInfo.fondsNum',
                        align: 'center',
                        title: "全宗号"
                    },{
                        field: 'fileInfo.fileYear',
                        align: 'center',
                        title: "年度"
                    },{
                        field: 'fileInfo.caseNum',
                        align: 'center',
                        title: "盒号"
                    },{
                        field: 'fileInfo.fileNum',
                        align: 'center',
                        title: "件号"
                    },{
                        field: 'fileInfo.caseName',
                        align: 'center',
                        title: "题名"

                    },{
                        field: 'fileInfo.safekeepingTerm',
                        align: 'center',
                        title: "保存期限"
                    },{
                        field: 'fileInfo.securityRank',
                        align: 'center',
                        title: "密级"
                    },{
                        field: 'fileInfo.responsibler',
                        align: 'center',
                        title: "责任者"
                    },{
                        field: 'fileInfo.safekeepingTerm',
                        align: 'center',
                        title: "保管期限"
                    },{
                        field: 'fileInfo.roomNum',
                        align: 'center',
                        title: "库房号"
                    },{
                        field: 'fileInfo.cabNum',
                        align: 'center',
                        title: "排柜号"
                    },{
                        field: 'chakan',
                        align: 'center',
                        title: "查看",
                        formatter:adGlGdFmCk
                    },{
                        field: 'bianji',
                        align: 'center',
                        title: "编辑",
                        formatter:adGlGdFmBj
                    },{
                        field: 'shanchu',
                        align: 'center',
                        title: "删除",
                        formatter:adGlGdFmSc
                    }
                    ]

                });
            function adGlGdFmCk(value, row, index) {

                return '<a href="#" class="tabOperA tabOperACk" data-toggle="modal" data-target="#adGlGdCkMod">查看</a>';

            };
            function adGlGdFmBj(value, row, index) {

                return '<a href="#" class="tabOperA tabOperABj" data-toggle="modal" data-target="#adGlGdXgMod">编辑</a>';

            };
            function adGlGdFmSc(value, row, index) {

                return '<a href="#" class="tabOperA tabOperASc">删除</a>';

            };
            adCxXs("/cfy/fond/adCxQz.do",'[name="fondsNum"]');
            $('[name="fondsNum"]').on("blur",function(){
                                var adCxParm=$(this).val();
                                var querydata=JSON.stringify({fondsNum:adCxParm});
                                var subqueryurl="/cfy/fond/adCxAjForAjSearch.do";
                                $.ajax({
                                        url: subqueryurl,
                                        type: 'POST',
                                        contentType:"Application/JSON; charset=utf-8",
                                        data:querydata
                                    })
                                    .done(function(backdata) {
                                        $('[name="caseYear"]').empty();
                                        var opt="<option></option>";
                                        $('[name="caseYear"]').append(opt);
                                        $.each(backdata, function(i, obj){
                                            if(obj.catalogNum=="")
                                            {
                                                opt="<option>"+obj.caseYear+"</option>";
                                                $('[name="caseYear"]').append(opt);
                                            }

                                        });
                                    })
                                    .fail(function() {
                                        console.log("error");
                                    })
                                    .always(function() {
                                        console.log("complete");
                                    });
                            });
            $('[name="caseYear"]').on("blur",function(){
                                var adCxParm=$(this).val();
                                var querydata=JSON.stringify({fondsNum:$('[name="fondsNum"]').val(),caseYear:adCxParm});
                                adCxXs('/cfy/fond/adCxAjGdCaseNum.do','[name="caseNum"]',querydata);
                            });
            //归档搜索按钮的事件绑定
            $('#adGlGdFoSs').on('click',function(){
                $.ajax({
                            url: '/cfy/fond/adMhCxGd.do',
                            type: 'POST',
                            contentType:'application/json',
                            data: JSON.stringify($('#adGlGdForm').serializeJSON())
                        })
                        .done(function(backdata) {
                            console.log(backdata);
                            $adGlGdTab.bootstrapTable('load', backdata);
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
            });
            //归档搜索重置按钮的事件绑定
            $('#adGlGdFoCz').on('click',function(){
                $("#adGlGdForm")[0].reset();
                $adGlGdTab.bootstrapTable('refresh');
            });
            //为操作单元格绑定事件，根据查看、删除、修改分别绑定不同的事件
            $adGlGdTab.on('click-cell.bs.table', function (event, value, row, valobj)  {
                    var newvalobj=valobj.fileInfo;
                if(value==='chakan'){

                    $("#adGlGdCkForm").populateForm(newvalobj);
                };
                $("#adGlGdCkModXz").on('click',function(){
                            location.href='/cfy/file/fileInfoDownloadZip.do?'+'fondsNum='+valobj.fondsNum+'&caseYear='+valobj.caseYear+'&caseNum='+valobj.caseNum+'&fileNum='+valobj.fileNum
                    });
                if(value==='bianji'){

                    $("#adGlGdXgForm").populateForm(newvalobj);
                    //归档修改模态框的事件绑定
                    $('#adGlGdXgModSub').on('click',function(){
                    $.ajax({
                            url: '../fond/adXgGd.do',
                            type: 'POST',
                            contentType:'application/json',
                            data:JSON.stringify($('#adGlGdXgForm').serializeJSON())
                        })
                        .done(function(backdata) {
                            $adGlAjTab.bootstrapTable('refresh');
                            alert(backdata.msg);
                            $("#adGlGdXgMod").modal('hide');
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
                    });
                };

                if(value==='shanchu'){

                        var scconfirm=confirm("确定要删除吗？");
                    if(scconfirm===true){

                        $.ajax({
                            url: '/cfy/fond/adScGd.do',
                            type: 'POST',
                            contentType:'application/json',
                            data: JSON.stringify(valobj)
                        })
                        .done(function(backdata) {
                            $adGlGdTab.bootstrapTable('refresh');
                            alert(backdata.msg);
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
                    }
                }
            });
        }
        //人员的管理页面
        if($('#adGlRyTab').attr('id')!==undefined){
            $adGlRyTab=$('#adGlRyTab').bootstrapTable({
                    pagination: true,
                    sidePagination: 'client',
                    pageList:[10,20,30,40],
                    pageNumber: 1,                       //初始化加载第一页，默认第一页
                    pageSize: 10,

                    exportOptions:{
                        ignoreColumn: [4,5]
                    },
                    toolbar:'#adGlRyTabTool',
                    toolbarAlign:'right',
                    method:'POST',
                    url: '/cfy/user/getAllUserInfo.do',
                    columns: [
                    {
                    checkbox: true
                    },
                    {
                        field: 'fondsNum',
                        align: 'center',
                        title: "头像"
                    },{
                        field: 'userName',
                        align: 'center',
                        title: "用户名"
                    },{
                        field: 'userType',
                        align: 'center',
                        title: "角色"
                    },{
                        field: 'createTime',
                        align: 'center',
                        title: "注册日期"
                    },{
                        field: 'cardType',
                        align: 'center',
                        title: "证件类型"
                    },{
                        field: 'chakan',
                        align: 'center',
                        title: "查看",
                        formatter:adGlRyFmCk
                    },{
                        field: 'bianji',
                        align: 'center',
                        title: "编辑",
                        formatter:adGlRyFmBj
                    },{
                        field: 'shanchu',
                        align: 'center',
                        title: "删除",
                        formatter:adGlRyFmSc
                    }
                    ]

                });
            function adGlRyFmCk(value, row, index) {

                return '<a href="#" class="tabOperA tabOperACk" data-toggle="modal" data-target="#adGlRyCkMod">查看</a>';

            };
            function adGlRyFmBj(value, row, index) {

                return '<a href="#" class="tabOperA tabOperABj" data-toggle="modal" data-target="#adGlRyXgMod">编辑</a>';

            };
            function adGlRyFmSc(value, row, index) {

                return '<a href="#" class="tabOperA tabOperASc">删除</a>';

            };
            $adGlRyTab.on('click-cell.bs.table', function (event, value, row, valobj)  {

                if(value==='chakan'){

                    $("#adGlRyCkForm").populateForm(valobj);
                };
                if(value==='bianji'){

                    $("#adGlRyXgForm").populateForm(valobj);
                    //归档修改模态框的事件绑定
                    $('#adGlRyXgModSub').on('click',function(){
                    $.ajax({
                            url: '../user/setUserPower.do',
                            type: 'POST',
                            contentType:'application/json',
                            data:JSON.stringify($('#adGlRyXgForm').serializeJSON())
                        })
                        .done(function(backdata) {
                            $adGlRyTab.bootstrapTable('refresh');
                            alert(backdata.msg);
                            $("#adGlRyXgMod").modal('hide');
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
                    });
                };

                if(value==='shanchu'){

                        var scconfirm=confirm("确定要删除吗？");
                    if(scconfirm===true){

                        $.ajax({
                            url: '/cfy/user/deleteUser.do',
                            type: 'POST',
                            contentType:'application/json',
                            data: JSON.stringify(valobj)
                        })
                        .done(function(backdata) {
                            $adGlRyTab.bootstrapTable('refresh');
                            alert(backdata.msg);
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });
                    }
                }
            });

        //这是if($('#adGlRyTab').attr('id')!==undefined)的另一半
        }


})();
