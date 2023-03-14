//chart1
{
    var dom = document.getElementById('container1');
    var myChart = echarts.init(dom, null, {
        renderer: 'canvas',
        useDirtyRect: false
    });
    var app = {};
    var option;
    option = {
        tooltip: {
            trigger: 'item'
        },
        dataset: {
            source: [
                ['借阅量', 'name'],
                [165, '三体'],
                [127, '高等数学'],
                [44, 'JS权威指南'],
                [53, 'Java 编程思想'],
                [73, 'C Primer Plus'],
                [48, '在北平'],
                [98, '飞鸟集'],
            ]
        },
        grid: { containLabel: true },
        xAxis: {
            axisLabel: {
                textStyle: {
                    show: true,
                    fontSize: '10',
                    fontWeight: '300',
                    color: 'black',
                },
            },
        },
        yAxis: {
            inverse: true,
            type: 'category',
            axisLabel: {
                textStyle: {
                    show: true,
                    fontSize: '16',
                    fontWeight: '300',
                    color: 'black',
                },
            },
        },
        visualMap: {
            show: false,
            orient: 'horizontal',
            min: 0,
            max: 200,
            dimension: 0,
            inRange: {
                color: ['#17234b', '#234199'],
            }
        },
        series: [
            {
                realtimeSort: true,
                type: 'bar',
                encode: {
                    x: 'number',
                    y: 'name'
                },
                label: {
                    show: true,
                    position: 'right',
                    valueAnimation: true,
                    textStyle: {
                        fontSize: 14,
                        fontWeight: '300',
                        color: 'black'
                    }
                },
            }
        ]
    };
    if (option && typeof option === 'object') {
        myChart.setOption(option);
    }
    window.addEventListener('resize', myChart.resize);
}
//chart2
{
    var dom = document.getElementById('container2');
    var myChart = echarts.init(dom, null, {
        renderer: 'canvas',
        useDirtyRect: false
    });
    var app = {};
    var option;
    option = {
        tooltip: {
            trigger: 'item'
        },
        visualMap: {
            show: false,
            min: 80,
            max: 600,
            inRange: {
                colorLightness: [0, 1]
            }
        },
        series: [
            {
                name: '书籍类型',
                type: 'pie',
                radius: '55%',
                center: ['50%', '50%'],
                data: [
                    { value: 404, name: '编程教程' },
                    { value: 288, name: '科幻小说' },
                    { value: 188, name: '传记' },
                    { value: 155, name: '散文' },
                    { value: 333, name: '教材' },
                    { value: 226, name: '诗歌' }
                ].sort(function (a, b) {
                    return a.value - b.value;
                }),
                roseType: 'radius',
                label: {
                    color: 'rgba(0, 0, 0, 0.8)'
                },
                labelLine: {
                    lineStyle: {
                        color: 'rgba(0, 0, 0, 0.8)'
                    },
                    smooth: 0.2,
                    length: 10,
                    length2: 20
                },
                itemStyle: {
                    color: '#17234b',
                },
                animationType: 'scale',
                animationEasing: 'elasticOut',
                animationDelay: function (idx) {
                    return Math.random() * 200;
                }
            }
        ]
    };
    if (option && typeof option === 'object') {
        myChart.setOption(option);
    }
    window.addEventListener('resize', myChart.resize);
}
//chart3
{
    var dom = document.getElementById('container3');
    var myChart = echarts.init(dom, null, {
        renderer: 'canvas',
        useDirtyRect: false
    });
    var app = {};
    var option;
    option = {
        tooltip: {
            trigger: 'item'
        },
        xAxis: {
            type: 'category',
            data: ['小紫', '小黄', '小橙', '小兰', '小绿', '小蓝'],
        },
        yAxis: {
            type: 'value',
            name: '(超出时长/小时)',
        },
        series: [
            {
                data: [
                    {
                        value: 40,
                        itemStyle: {
                            color: '#17234b',
                        }
                    },
                    {
                        value: 88,
                        itemStyle: {
                            color: '#234199'
                        }
                    },
                    {
                        value: 60,
                        itemStyle: {
                            color: '#17234b'
                        }
                    },
                    {
                        value: 16,
                        itemStyle: {
                            color: '#234199'
                        }
                    },
                    {
                        value: 48,
                        itemStyle: {
                            color: '#17234b'
                        }
                    },
                    {
                        value: 56,
                        itemStyle: {
                            color: '#234199'
                        }
                    },
                ],
                type: 'bar',
                barWidth: '30px',
                label: {
                    show: true,
                    position: 'top',
                    valueAnimation: true,
                    textStyle: {
                        fontSize: 14,
                        fontWeight: '300',
                        color: 'black'
                    }
                },
            }
        ]
    };
    if (option && typeof option === 'object') {
        myChart.setOption(option);
    }
    window.addEventListener('resize', myChart.resize);
}
// chart4
{
    const container4 = document.getElementById("container4")
    container4.insertAdjacentHTML("beforeend",
        `
            <p class="grade4"></p>
            <p class="grade3"></p> 
            <p class="grade2"></p>
            <p class="grade1"></p>
            <p class="grade2"></p>
            <p class="grade3"></p>
            <p class="grade4"></p>
        `
    )
    const display = container4.querySelectorAll("p")
    const data = ["蒙奇·D·路飞", "要成为火影", "要成为海贼", "二柱子", "进击的艾伦", "七代目火影", "九转大肠"]
    const number = [18, 15, 14, 12, 10, 9, 9]
    //按呈现效果排序
    const displayName = [data[5], data[3], data[1], data[0], data[2], data[4], data[6]]
    const displayNumber = [number[5], number[3], number[1], number[0], number[2], number[4], number[6]]
    //呈现效果动态生成
    for (let i = 0; i < display.length; i++) {
        display[i].textContent = displayName [i]
    }
    //聚焦
    for (let i = 0; i < display.length; i++){
        display[i].addEventListener("mouseout", function (event) {
            display[i].textContent = displayName[i]
        })
        display[i].addEventListener("mouseover", function (event) {
            display[i].textContent += ` 借阅 ${displayNumber[i]} 本`
        })
    }
}