
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

var $table;

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

$table=$('#ad_tjsjtab').bootstrapTable({
    pagination: true,
    sidePagination: 'client',
    pageList:[7,14,21,28],
    pageNumber: 1,                       //初始化加载第一页，默认第一页
    pageSize: 7,


    //ajaxOptions:{
          //  type: 'GET'
          //  },
    //url: '../fond/searchFondsInfoAll.do',
    columns: [
    {
       fileid: 'state',
       align:'center',
       checkbox: true,
    },
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
        field: 'roomNum',
        align: 'center',
        title: "房间号"
    },{
        field: 'fileNum',
        align: 'center',
        title: "柜号"
    },{
        field: 'fileNum',
        align: 'center',
        title: "备注"
    }],
data: testdata

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

$('#ad_mutjbtn').click(function () {


            $.ajax({
                url: '../fond/deleteByFileNum.do',
                type: 'POST',
                contentType:"application/json; charset=UTF-8",
                data:JSON.stringify($table.bootstrapTable('getSelections')),
                success:function(){
                    $table.bootstrapTable('refresh');

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
         $table.bootstrapTable('refresh');
           //load也可以替换数据
           //$table.bootstrapTable('load',testdata);
        });