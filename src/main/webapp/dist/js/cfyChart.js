/**
 * Created by Administrator on 2017/9/29.
 */
$(document).ready(function($) {
	function resizeCharts(){
	var $targets = $(".chart-resize");
	var wrapWidth = $targets.width($targets.parent().width());

}
resizeCharts();
var circle = echarts.init(document.getElementById('circle'));
var colors = ['#5793f3', '#d14a61', '#675bba'];
var circleOption = {
	color: colors,

	tooltip: {
		trigger: 'none',
		axisPointer: {
			type: 'cross'
		}
	},
	legend: {
		data:['著录', '审核']
	},
	grid: {
		top: 70,
		bottom: 50
	},
	xAxis: [
		{
			type: 'category',
			axisTick: {
				alignWithLabel: true
			},
			axisLine: {
				onZero: false,
				lineStyle: {
					color: colors[1]
				}
			},
			axisPointer: {
				label: {
					formatter: function (params) {
						return '著录  ' + params.value
							+ (params.seriesData.length ? '：' + params.seriesData[0].data : '');
					}
				}
			},
			data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
		},
		{
			type: 'category',
			axisTick: {
				alignWithLabel: true
			},
			axisLine: {
				onZero: false,
				lineStyle: {
					color: colors[0]
				}
			},
			axisPointer: {
				label: {
					formatter: function (params) {
						return '审核  ' + params.value
							+ (params.seriesData.length ? '：' + params.seriesData[0].data : '');
					}
				}
			},
			data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
		}
	],
	yAxis: [
		{
			type: 'value',
			min: 0,
			max: 1500
		}
	],
	series: [
		{
			name:'著录',
			type:'line',
			xAxisIndex: 1,
			smooth: true,
			data: [300, 600, 780, 1000, 800, 600, 450]
		},
		{
			name:'审核',
			type:'line',
			smooth: true,
			data: [450, 750, 850, 950, 800, 650, 250]
		}
	]
};
circle.setOption(circleOption);

var pie = echarts.init(document.getElementById('pie'));
var pieOption = {
	tooltip: {
		trigger: 'item',
		formatter: "{a} <br/>{b}: {c} ({d}%)"
	},
	legend: {
		data:['卷内文件','归档文件']
	},
	calculable : true,
	series: [
		{
			name:'访问来源',
			type:'pie',
			radius: ['50%', '70%'],
			avoidLabelOverlap: false,
			roseType : 'radius',
			label: {
				normal: {
					show: false,
					position: 'center'
				},
				emphasis: {
					show: true,
					textStyle: {
						fontSize: '30',
						fontWeight: 'bold'
					}
				}
			},
			labelLine: {
				normal: {
					show: false
				}
			},
			data:[
				{value:60, name:'卷内文件'},
				{value:40, name:'归档文件'}
			]
		}
	]
};
pie.setOption(pieOption);

var bar = echarts.init(document.getElementById('bar'));
var barOption = {
	tooltip : {
		trigger: 'axis',
		axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		}
	},
	legend: {
		data:['申请借阅','审核借阅']
	},
	grid: {
		left: '1%',
		right: '1%',
		bottom: '1%',
		containLabel: true
	},
	xAxis : [
		{
			//type : 'category',
			data : ['周一','周二','周三','周四','周五','周六','周日']
		}
	],
	yAxis : [
		{
			type : 'value',
			min: 0,
			max: 50
		}
	],
	series : [
		{
			name:'申请借阅',
			type:'bar',
			data:[10, 20, 30, 8, 45, 23, 12]
		},
		{
			name:'审核借阅',
			type:'bar',
			//stack: '广告',
			data:[21, 23, 12, 45, 47, 38, 12]
		}
	]
};
bar.setOption(barOption);

$(window).resize(chartResize);
function chartResize() {
	resizeCharts();
	circle.resize();
	pie.resize();
	bar.resize();
}
});






