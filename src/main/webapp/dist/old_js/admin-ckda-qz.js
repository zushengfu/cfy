/*
* @Author: Marte
* @Date:   2017-07-24 09:38:20
* @Last Modified by:   Marte
* @Last Modified time: 2017-08-07 17:50:21
*/
/*
 bootstrap-table导出
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
var testdata=[{

        fondsNum:1,
        fondsName: "aaa",
        fondsYear: 11,
        remarks: "aaa",
        roomNum: 111,
        cabNum: 1111,
        fileNum:  11111
    }, {
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
    }];

var $table,$table1,realdata;

$(document).ready(function(){
    $.ajax({
        url: './../fond/searchFondsInfoAll.do',
        type: 'POST',
        success:function(data){
            alert("连接成功！")
            console.log(data);
            $table.bootstrapTable('load', data);
            realdata=data;
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

$table=$('#ad_ckqztab').bootstrapTable({
    pagination: true,
    sidePagination: 'client',
    pageList:[7,14,21,28],
    pageNumber: 1,                       //初始化加载第一页，默认第一页
    pageSize: 7,
    showColumns:true,
    showExport: true,             //是否显示导出
    exportOptions:{
        ignoreColumn: [4,5]
    },
    //ajaxOptions:{
          //  type: 'GET'
          //  },
    //url: '../fond/searchFondsInfoAll.do',
    columns: [
    {
        field: 'fondsNum',
        align: 'center',
        title: "宗卷编号"
    },{
        field: 'fondsName',
        align: 'center',
        title: "宗卷名称"
    },{
        field: 'fondsYear',
        align: 'center',
        title: "宗卷年份"
    },{
        field: 'fileNum',
        align: 'center',
        title: "文件编号"
    },{
        field: 'details',
        align: 'center',
        title: "详细信息",
        formatter:ad_scandetails

    },{
        field: 'download',
        align: 'center',
        title: "下载",
        formatter:ad_download
    }],
data: testdata

});

function ad_scandetails(value, row, index) {

        return '<a href="#" class="scandetail" data-toggle="modal" data-target="#ad_ckqzmod">查看详情</a>';

}
;
function ad_download(value, row, index) {

        return '<a href="#" type="submit">下载</a><input id="ad_ckqztjxzsj" name="fileNum" style="display:none"></input>';


};

$table1=$('#ad_ckqztptab').bootstrapTable({
    columns: [
    {
        field: 'img',
        align: 'center',
        title: "附件列表"
    }],
data: testdata
});


$table.on('click-cell.bs.table', function (field, value, row, valobj) {


    if(value==='details'){
        console.log(field);
        $("#ad_ckqzform").populateForm(valobj);
        $('#ad_ckqztpbtn').on("click",function() {
            console.log(JSON.stringify({fileNum:valobj.fileNum,fondsNum:valobj.fondsNum}));

        /* Act on the event */
        $.ajax({
            url: '../fond/searchFondsInfoForPicture.do',
            type: 'POST',
            dataType: 'json',
            contentType:"application/json",
            data: JSON.stringify({fileNum:valobj.fileNum,foundsNum:valobj.fondsNum})
        })
        .done(function(data) {
            console.log("success");
            console.log(data);
            $table1.bootstrapTable('load', data);
        })
        .fail(function(data) {
            console.log(data);
        })
        .always(function() {
            console.log("complete");
        });

});
    };
    if(value==='download'){

        console.log("valobj.fileNum");
        $("#ad_ckqzxzbd").attr('action','downloadZip.do?'+'fondsName='+valobj.fondsName).submit();
    }

});
$('.scandetail').on("click",function(row, $element, field){
            console.log($table.bootstrapTable('getSelections').length);



                if($table.bootstrapTable('getSelections').length>1){
                    alert("请一个个点击")
                };

            });



$('#ad_mhczqzbtn').on('click', function() {
    console.log($('#ad_jqczqzform').serialize());
    $.ajax({
        url: './../fond/searchFondsDetail.do',
        type: 'POST',
        data: $('#ad_jqczqzform').serialize(),
        success:function(data){
            $table.bootstrapTable('load', data);
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
$('#ad_jqczqzbtn').on('click', function() {
    console.log($('#ad_jqczqzform').serialize());
    $.ajax({
        url: './../fond/searchFondsDetailPicture.do',
        type: 'POST',
        data: $('#ad_jqczqzform').serialize(),
        success:function(data){
            $table.bootstrapTable('load', data);
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

$("#ad_ckqzprfbtn").on("click",function(event) {
    $("#ad_ckqzmod .modal-body").find('input').each(function(){
        $(this).attr('value',$(this).val());
    console.log($(this).val());
  })

    $("#ad_ckqzmod .modal-body").jqprint({
     debug: true, //如果是true则可以显示iframe查看效果（iframe默认高和宽都很小，可以再源码中调大），默认是false
     importCSS: true, //true表示引进原来的页面的css，默认是true。（如果是true，先会找$("link[media=print]")，若没有会去找$("link")中的css文件）
     printContainer: true, //表示如果原来选择的对象必须被纳入打印（注意：设置为false可能会打破你的CSS规则）。
     operaSupport: true//表示如果插件也必须支持歌opera浏览器，在这种情况下，它提供了建立一个临时的打印选项卡。默认是true
});
});


var activerow;
$table1.on('click-cell.bs.table', function (field, value, row, valobj) {

    console.log(activerow);
    console.log(valobj.img);
    var url='../fond/viewAndPicture.do?picName='+valobj.img;
    $('#ad_ckqztpimg').attr('src',url);
});

$('#ad_ckqztpimg').cropper({
  aspectRatio: 4 / 3,
  crop: function(e) {
    // Output the result data for cropping image.
    console.log(e.x);
    console.log(e.y);
    console.log(e.width);
    console.log(e.height);
    console.log(e.rotate);
    console.log(e.scaleX);
    console.log(e.scaleY);
  }
});
$("#ad_ckqztpfd").on('click',function(event) {
$("#ad_ckqztpimg").addClass('fangda');
});
$("#ad_ckqztpsx").on('click',function(event) {
$("#ad_ckqztpimg").removeClass('fangda');
});
$("#ad_ckqztplt").on('click',function(event) {
    $("#ad_ckqztpimg").removeClass('zuoxuanzhuan').addClass('zuoxuanzhuan');
});
$("#ad_ckqztprt").on('click',function(event) {

});
$("#ad_ckqztppre").on('click',function(event) {

});
$("#ad_ckqztpnext").on('click',function(event) {

});