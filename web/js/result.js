window.setInterval(getResult, 10000);
var time = -1;
var chart = echarts.init(document.getElementById('chart'));
var timeList = [0];

function getResult() {
    time++;
    if (time === 0) {
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
                    data: [0]
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
                    data: [0]
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
                    data: []
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
                    data: []
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
                    data: []
                }
            ]
        };
        chart.setOption(option);
    } else if (time === 1 || time === 2 || time === 3 || time === 4 || time === 5 || time === 6 ||
        time === 12 || time === 18 || time === 24 || time === 30 || time === 60 || time === 120 ||
        time === 210 || time === 330 || time === 480 || time === 660 || time === 690 || time === 720) {
        if (time * 10 <= localStorage.getItem("longestTime")) {
            timeList.push(time * 10);
            $.ajax({
                type: "GET",
                url: "/api/score",
                contentType: "application/x-www-form-urlencoded",
                data: {},
                dataType: "json",
                success: function (data) {
                    $("#top-table").empty();

                    var randoopList = [];
                    var evosuiteList = [];
                    var randoopScoreList = [0];
                    var evosuiteScoreList = [0];

                    for (var i = 0; i < data.length; i++) {
                        switch (data[i].tool) {
                            case "Randoop":
                                randoopList.push(data[i]);
                                randoopScoreList.push(Math.round(data[i].total));
                                break;
                            case "Evosuite":
                                evosuiteList.push(data[i]);
                                evosuiteScoreList.push(Math.round(data[i].total));
                                break;
                        }
                    }

                    $("#top-table").append("<tr>\n" +
                        "                    <th></th>\n" +
                        "                    <th>总分</th>\n" +
                        "                    <th>BC</th>\n" +
                        "                    <th>MC</th>\n" +
                        "                </tr>");
                    $("#top-table").append("<tr>\n" +
                        "                <th>Randoop</th>\n" +
                        "                <th>" + Math.round(randoopList[randoopList.length - 1].total) + "</th>\n" +
                        "                <th>" + Math.round(randoopList[randoopList.length - 1].bc) + "</th>\n" +
                        "                <th>" + Math.round(randoopList[randoopList.length - 1].mc) + "</th>\n" +
                        "            </tr>");
                    $("#top-table").append("<tr>\n" +
                        "                <th>Evosuite</th>\n" +
                        "                <th>" + Math.round(evosuiteList[evosuiteList.length - 1].total) + "</th>\n" +
                        "                <th>" + Math.round(evosuiteList[evosuiteList.length - 1].bc) + "</th>\n" +
                        "                <th>" + Math.round(evosuiteList[evosuiteList.length - 1].mc) + "</th>\n" +
                        "            </tr>");
                    $("#top-table").append("<tr>\n" +
                        "                <th>human1</th>\n" +
                        "                <th>0</th>\n" +
                        "                <th>0</th>\n" +
                        "                <th>0</th>\n" +
                        "            </tr>");
                    $("#top-table").append("<tr>\n" +
                        "                <th>human2</th>\n" +
                        "                <th>0</th>\n" +
                        "                <th>0</th>\n" +
                        "                <th>0</th>\n" +
                        "            </tr>");
                    $("#top-table").append("<tr>\n" +
                        "                <th>human3</th>\n" +
                        "                <th>0</th>\n" +
                        "                <th>0</th>\n" +
                        "                <th>0</th>\n" +
                        "            </tr>");

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
                                data: randoopScoreList
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
                                data: evosuiteScoreList
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
                                data: []
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
                                data: []
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
                                data: []
                            }
                        ]
                    };
                    chart.setOption(option);
                }
            })
        }
    }
}

