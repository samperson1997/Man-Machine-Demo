window.setInterval(getResult, 10000);
var chart = echarts.init(document.getElementById('chart'));
var timeList = [0];
var randoopList = [];
var evosuiteList = [];
var human1List = [];
var human2List = [];
var human3List = [];

function getResult() {
    $.ajax({
        type: "GET",
        url: "/api/score",
        contentType: "application/x-www-form-urlencoded",
        data: {},
        dataType: "json",
        success: function (data) {
            $("#top-table").empty();
            $("#top-table").append("<tr>\n" +
                "                    <th></th>\n" +
                "                    <th>总分</th>\n" +
                "                    <th>BC</th>\n" +
                "                    <th>MC</th>\n" +
                "                </tr>");

            for (var i = 0; i < data.length; i++) {
                console.log(data[i]);

                $("#top-table").append("<tr>\n" +
                    "                <th>" + data[i].name + "</th>\n" +
                    "                <th>" + Math.round(data[i].total) + "</th>\n" +
                    "                <th>" + Math.round(data[i].bc) + "</th>\n" +
                    "                <th>" + Math.round(data[i].mc) + "</th>\n" +
                    "            </tr>");

                switch (data[i].name) {
                    case "Randoop":
                        randoopList.push(Math.round(data[i].total));
                        break;
                    case "Evosuite":
                        evosuiteList.push(Math.round(data[i].total));
                        break;
                    case "human1":
                        human1List.push(Math.round(data[i].total));
                        break;
                    case "human2":
                        human2List.push(Math.round(data[i].total));
                        break;
                    case "human3":
                        human3List.push(Math.round(data[i].total));
                        break;
                }
            }


            var option = {
                title: {
                    text: '分数趋势'
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
                    data: ['Randoop', 'Evosuite', 'human1', 'human2', 'human3'],
                    x: 'right',
                    show: 'true'
                },
                toolbox: {
                    feature: {
                        // saveAsImage: {}
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
                        data: timeList,
                        name: '时间',
                        nameLocation: 'end',
                        nameGap: 0
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '分数',
                        nameLocation: 'end',
                        nameGap: 10
                    }
                ],
                series: [
                    {
                        name: 'Randoop',
                        type: 'line',
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        data: randoopList
                    },
                    {
                        name: 'Evosuite',
                        type: 'line',
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        data: evosuiteList
                    },
                    {
                        name: 'human1',
                        type: 'line',
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        data: human1List
                    },
                    {
                        name: 'human2',
                        type: 'line',
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        data: human2List
                    },
                    {
                        name: 'human3',
                        type: 'line',
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        data: human1List
                    }
                ]
            };
            chart.setOption(option);
            timeList.push(timeList[timeList.length - 1] + 10);
        }
    })
}

