/*
* @Author: Marte
* @Date:   2017-09-11 14:11:22
* @Last Modified by:   Marte
* @Last Modified time: 2017-09-18 15:07:42
*/

// View some images
$('#datetimepicker').datetimepicker();
var chart = new Highcharts.Chart('container', {
    title: {
        text: '档案录著审核量',
        x: -20
    },
    subtitle: {
        text: '统计数据',
        x: -20
    },
    xAxis: {
        categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
    },
    yAxis: {
        title: {
            text: '数量 (个)'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    },
    tooltip: {
        valueSuffix: '个'
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        borderWidth: 0
    },
    series: [{
        name: '录著',
        data: [700, 690, 950, 1450, 1820, 215, 852, 265, 233, 1830, 1390, 960]
    }, {
        name: '未通过',
        data: [200, 80, 70, 200, 170, 220, 204, 240, 200, 1400, 80, 200]
    }, {
        name: '已通过',
        data: [90, 60, 500, 400, 130, 170, 108, 170, 104, 90, 300, 100]
    }, {
        name: '待审核',
        data: [39, 200, 70, 80, 110, 150, 107, 106, 104, 100, 600, 400]
    }]
});
