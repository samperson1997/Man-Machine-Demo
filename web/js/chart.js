var option = {
    title: {
        text: '分数'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    legend: {
        data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎']
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: [
        {
            type: 'category',
            boundaryGap: false,
            data: ['0', '10', '20', '30', '40', '50', '60']
        }
    ],
    yAxis: [
        {
            type: 'value'
//			data: ['0', '10', '20', '30', '40', '50', '60','70','80','90','100']
        }
    ],
    series: [
        {
            name: 'Randoop',
            type: 'line',
            //stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            // areaStyle: {normal: {}},
            data: [64, 67, 69, 72, 75, 77, 80]
        },
        {
            name: 'Evosuite',
            type: 'line',
            //stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            // areaStyle: {normal: {}},
            data: [59, 62, 65, 71, 73, 74, 75]
        },
        {
            name: 'human1',
            type: 'line',
            //stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            // areaStyle: {normal: {}},
            data: [10, 12, 16, 18, 21, 24, 25]
        },
        {
            name: 'human2',
            type: 'line',
            //stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            // areaStyle: {normal: {}},
            data: [35,40,42,45,48,49,50]
        },
        {
            name: 'human3',
            type: 'line',
            //stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            // areaStyle: {normal: {}},
            data: [29,32,35,37,39,41,45]
        }
    ]
};

var chart = echarts.init(document.getElementById('chart'));
chart.setOption(option);