/*
* @Author: Marte
* @Date:   2017-07-24 09:38:20
* @Last Modified by:   Marte
* @Last Modified time: 2017-08-07 17:31:53
*/
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
    }];
var $table;
/*
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
*/
$table=$('#ad_xgqztab').bootstrapTable({

    pagination: true,
    sidePagination: 'client',
    pageList:[7,14,21,28],
    pageNumber: 1,                       //初始化加载第一页，默认第一页
    pageSize: 7,


   url: '../fond/searchFondsInfoAll.do',
   method:'POST',
/*    columns: [{
        field: 'fondsNum',
        title: "档案编号"
    }, {
        field: 'fileNum' ,
        title: "文件编号"
    }, {
        field: 'fondsName',
        title: "档案名"
    }, {
        field: 'fondsYear',
        title: "档案年份"
    }, {
        field: 'remarks',
        title: "备注"
    }, ]
    */
    columns: [
    {
       fileid: 'state',
       align:'center',
       checkbox: true,
    },{
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
        field: 'change',
        align: 'center',
        title: "修改",
        formatter:ad_change
    },{
        field: 'delete',
        align: 'center',
        title: "删除",
        formatter:ad_delete
    }],
data: testdata

});



function ad_change(value, row, index) {

        return '<a href="#" class="scandetail" data-toggle="modal" data-target="#ad_xgqzmod">修改信息</a>';


};
function ad_delete(value, row, index) {

        return '<a class="rowdelete">删除</a>';


};

/*

$('#btn_mudelete').click(function () {
 //获取选中结果行,返回数组
 //返回结果中包括多选框字段 state=true
 var result = $table.bootstrapTable('getSelections');
 console.info(result);
 var ids = [];
 for (var i = 0; i < result.length; i++) {
  var item = result[i];
  ids.push(item.fondsNum);
 }
 alert(ids);
});
function actionFormatter(value, row, index) {
        return '<a class="mod" >修改</a> ' + '<a class="delete">删除</a>';
    }
;

*/

$table.on('click-cell.bs.table', function (field, value, row, valobj) {

    if(value=='delete'){
        console.log(valobj.fileNum);
        $.ajax({
            url: '../fond/deleteByFileNum.do',
            type: 'POST',
            data: {fileNum: valobj.fileNum},
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
    }
    if(value=='change'){
        $("#ad_xgqzform").populateForm(valobj);

    }

});
$('#ad_xgqzbtn').on('click',function(){
    $.ajax({
            url: '../fond/updateFondsInfo.do',
            type: 'POST',
            contentType:"application/json; charset=UTF-8",
            data: JSON.stringify($("#ad_xgqzform").serializeJSON()),
            success:function(){
                $table.bootstrapTable('refresh');
                $('#ad_xgqzmod').modal('hide');
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

});
$('#ad_mudelbtn').click(function () {
            /*var ids=$table.bootstrapTable('getSelections');*/

            var ids = $.map($table.bootstrapTable('getSelections'), function (row) {
                return row.fileNum;
            });
            console.log(ids);
            /*
            $table.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            */
           var nums={fileNum:JSON.stringify(ids)};
            console.log(nums);
            $.ajax({
                url: '../fond/deleteByFileNum.do',
                type: 'POST',
                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                data:nums,
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
$('#ad_mutjbtn').click(function () {


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
