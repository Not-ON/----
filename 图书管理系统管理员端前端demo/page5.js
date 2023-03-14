const chartChoose = document.getElementById("chart-choose")
const btn = chartChoose.querySelectorAll("button")
const main = document.querySelector("main")
let chart = main.querySelector("form")

window.onload = function () {
    btn[0].click()
}

for (let i = 0; i < btn.length; i++) {
    btn[i].addEventListener("click", () => {
        let preBtn = chartChoose.querySelector(".selected")
        preBtn.className = ""
        btn[i].className = "selected"
        chart.remove()
    })
}

btn[0].onclick = function () {
    chart.remove()
    chart = document.createElement("form")
    main.appendChild(chart)
    chart.insertAdjacentHTML("beforeend",
        `
        <div class="scroll-box">
    <div class="chart1">
                <div class="name">书名</div>
                <div class="number">借阅次数（热度）</div>
                <div id="container1" style="height: 100%"></div>
            </div>
        </div>
        `
    )
    //chart1
    {
        var dom = document.getElementById('container1');
        var myChart = echarts.init(dom, null, {
            renderer: 'canvas',
        });
        var app = {};
        var option;
        const data = [58, 165, 42, 84, 69, 62, 75, 136, 58, 96, 47, 14, 47, 73, 28];
        option = {
            xAxis: {
                splitLine: {
                    show: false
                },
                axisLabel: {
                    show: false
                },
            },
            yAxis: {
                type: 'category',
                data: ['三体', '高等数学', '飞鸟集', '诗经', '在北平', '西游记', '水浒传', '呼兰河传', '楚辞', '三国演义', '红楼梦', '红与黑', '飘', '简爱', '雾都孤儿'],
                inverse: true,
                axisLabel: {
                    textStyle: {
                        show: true,
                        fontSize: '18',
                        fontWeight: '300',
                        color: 'white',
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
                    color: ['#141414', '#A09F9F', 'white'],
                }
            },
            series: [
                {
                    realtimeSort: true,
                    type: 'bar',
                    data: data,
                    label: {
                        show: true,
                        position: 'right',
                        valueAnimation: true,
                        textStyle: {
                            fontSize: 16,
                            color: 'white'
                        }
                    },
                    barWidth: '30px',
                }
            ],
            legend: {
                show: true
            },
        };
        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
        window.addEventListener('resize', myChart.resize);
    }
}

btn[1].onclick = function () {
    chart.remove()
    chart = document.createElement("form")
    main.appendChild(chart)
    chart.insertAdjacentHTML("beforeend",
        `
            <div class="chart2">
                <h2>逾期未还者（排名）</h2>
                <h2>损坏书籍者</h2>
                <div class="chart2-box1">
                    <div class="past-time">逾期时间（小时）</div>
                    <div id="container2" style="height: 100%"></div>
                </div>
                <div class="chart2-box2">
                    <div class="box">
                        <div class="name-list" id="name-list1"></div>
                        <div class="tiplist">
                            <h3>意外损坏书籍者</h3>
                            <div class="tip">
                                <p>对其进行挂名、警告处分</p>
                            </div>
                        </div>
                    </div>
                    <div class="box">
                        <div class="name-list" id="name-list2">
                        </div>
                        <div class="tiplist">
                            <h3>恶意损坏书籍者</h3>
                            <div class="tip">
                                <p>对其进行挂名、警告处分</p>
                                <p>并责令其对损坏的书籍进行赔偿</p>
                                <p>逃避赔偿者取消借书资格</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `
    )
    //chart2
    {
        var dom = document.getElementById('container2');
        var myChart = echarts.init(dom, null, {
            renderer: 'canvas',
        });
        var app = {};
        var option;
        const data = [27, 24, 23, 20, 16, 16, 14, 10];
        option = {
            xAxis: {
                splitLine: {
                    show: false
                },
                axisLabel: {
                    show: false
                },
            },
            yAxis: {
                type: 'category',
                data: ['小黄', '小红', '小蓝', '小紫', '小绿', '小青', '小白', '小黑'],
                inverse: true,
                axisLabel: {
                    textStyle: {
                        show: true,
                        fontSize: '18',
                        fontWeight: '300',
                        color: 'white',
                    },
                },
            },
            visualMap: {
                show: false,
                orient: 'horizontal',
                min: 0,
                max: 48,
                dimension: 0,
                inRange: {
                    color: ['yellow', 'red', 'black'],
                }
            },
            series: [
                {
                    realtimeSort: true,
                    type: 'bar',
                    data: data,
                    label: {
                        show: true,
                        position: 'right',
                        valueAnimation: true,
                        textStyle: {
                            fontSize: 16,
                            color: 'white'
                        }
                    },
                    barWidth: '20px',
                }
            ],
            legend: {
                show: true
            },
        };
        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
        window.addEventListener('resize', myChart.resize);
    }
    {
        const nameList1 = document.getElementById("name-list1")
        const nameList2 = document.getElementById("name-list2")
        for (let i = 0; i < 20; i++) {
            nameList1.insertAdjacentHTML("beforeend",
                `
                            <p>xxx</p>
                        `
            )
        }
        for (let i = 0; i < 20; i++) {
            nameList2.insertAdjacentHTML("beforeend",
                `
                            <p>xxx</p>
                        `
            )
        }
    }
}

btn[2].onclick = function () {
    chart.remove()
    chart = document.createElement("form")
    main.appendChild(chart)
    chart.insertAdjacentHTML("beforeend",
        `
            <div class="chart3">
                <div class="list">
                    <h3>教材</h3>
                    <p class="list-name">小A</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小B</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小C</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小D</p>
                    <p>借阅 64 次</p>
                </div>
                <div class="list">
                    <h3>小说</h3>
                    <p class="list-name">小A</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小B</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小C</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小D</p>
                    <p>借阅 64 次</p>
                </div>
                <div class="list">
                    <h3>散文</h3>
                    <p class="list-name">小A</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小B</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小C</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小D</p>
                    <p>借阅 64 次</p>
                </div>
                <div class="list">
                    <h3>诗歌</h3>
                    <p class="list-name">小A</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小B</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小C</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小D</p>
                    <p>借阅 64 次</p>
                </div>
                <div class="list">
                    <h3>传记</h3>
                    <p class="list-name">小A</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小B</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小C</p>
                    <p>借阅 64 次</p>
                    <p class="list-name">小D</p>
                    <p>借阅 64 次</p>
                </div>
                <img class="bg" src="./photo/文本(2023-02-08 165050).svg" alt="">
            </div>
                `
    )
}