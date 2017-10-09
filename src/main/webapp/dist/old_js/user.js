
var table1;
$(document).ready(function() {
       table1=$('#user-shform').DataTable( {
        ajax: {
        url:'./../fond/searchFondsInfoAll.do',
        dataSrc: ''
        },
        language:{
            "decimal":        "",
            "emptyTable":     "没有搜索结果",
            "info":           "显示 _START_ 到 _END_ 条，总共 _TOTAL_条 ",
            "infoEmpty":      "没有信息",
            "infoFiltered":   "(filtered from _MAX_ total entries)",
            "infoPostFix":    "",
            "thousands":      ",",
            "lengthMenu":     "每页显示 _MENU_ 条",
            "loadingRecords": "加载中...",
            "processing":     "进行中...",
            "search":         "表内搜索:",
            "zeroRecords":    "没有您要搜索的结果",
            "paginate": {
                "first":      "First",
                "last":       "Last",
                "next":       "下一页",
                "previous":   "上一页"
            },
            "aria": {
                "sortAscending":  ": activate to sort column ascending",
                "sortDescending": ": activate to sort column descending"
        }},
        columns: [
            {title:"档案编号", data: 'fondsNum' },
            {title:"文件编号", data: 'fileNum' },
            {title:"档案名", data: 'fondsName' },
            {title:"档案年份", data: 'fondsYear' },
            {title:"备注", data: 'remarks' }
        ]
    });
} );
var user_s= new Vue({
  el: '#user-search',
  data: {
    show: false,
  },
  methods:{
    tog:function(){


    console.log(0);
    console.log(   $('#user-schform').serialize() );

                table1.ajax.url('./../fond/searchFondsDetail.do?'+ $('#user-schform').serialize()).load();


    }
  }
});
$('#user-schbtn').on("click",function(){

                table1.ajax.url('./../fond/searchFondsInfo.do?param1='+$('#user-schtext').val()).load();

});

