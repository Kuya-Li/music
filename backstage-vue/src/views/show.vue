<template>
  <div>
    <header>
      <h1>音乐推荐系统数据可视化</h1>
      <div class="show-time">{{ currentTime }}</div>
      <a href="/home" class="home-link">返回</a>
    </header>
    <section class="mainbox">
      <div class="column">
        <div class="panel bar">
          <h2>柱形图-最活跃用户</h2>
          <div class="chart" id="left-1"></div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel line">
          <h2>折线图-注册用户数变化
          </h2>
          <div class="chart" id="left-2"></div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel pie">
          <h2>饼形图-用户年龄分布</h2>
          <div class="chart"  id="left-3"></div>
          <div class="panel-footer"></div>
        </div>
      </div>
      <div class="column">
        <div class="no">
          <div class="no-hd">
            <ul>
              <li>{{ userCount }}</li>
              <li>{{ activeCount }}</li>
            </ul>
          </div>
          <div class="no-bd">
            <ul>
              <li>用户总数</li>
              <li>总活跃度</li>
            </ul>
          </div>
        </div>
        <div class="map ">
          <h2 class="myh2">用户地区分布图</h2>
          <div class="map1"></div>
          <div class="map2"></div>
          <div class="map3"></div>
          <div class="chart" id="center" ></div>
        </div>
      </div>
      <div class="column">
        <div class="panel bar2" >
          <h2>柱形图-性别比例</h2>
          <div class="chart" id="right-1"></div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel line2" >
          <h2>折线图-上月用户操作统计</h2>
          <div class="chart" id="right-2"></div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel pie2" >
          <h2>饼形图-地区用户分布Top</h2>
          <div class="chart" ref="pie2Chart" id="right-3"></div>
          <div class="panel-footer"></div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import * as echarts from "echarts";
import '../assets/js/china';

export default {
  data() {
    return {
      pageTitle: '数据可视化',
      currentTime: '',
      userCount: 0,
      activeCount: 0,
      bar: {},
      bar2: {},
      line1:{},
      line2:{},
      pie1:{},
      pie2:{},
      option:{},
      myChartLeft1: null,
      myChartRight1: null,
      myChartLeft2: null,
      myChartRight2: null,
      myChartLeft3: null,
      myChartRight3: null,
    };
  },
  created() {
    this.getCount();
  },
  mounted() {
    document.title = "可视化"
    this.setupClock();
    // 使用ECharts初始化图表
    // 柱状图
    this.bar = {
      color: ['#2f89cf'],
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      grid: {
        left: '0%',
        top: '10px',
        right: '0%',
        bottom: '4%',
        containLabel: true
      },
      xAxis: [{
        type: 'category',
        data: [],
        axisTick: {
          alignWithLabel: true
        },
        axisLabel: {
          color: "rgba(255,255,255,0.8)",
          fontSize: 10
        },
        axisLine: {
          show: false
        }
      }],
      yAxis: [{
        type: 'value',
        axisLabel: {
          color: "rgba(255,255,255,0.6)",
          fontSize: 12
        },
        axisLine: {
          lineStyle: {
            color: "rgba(255,255,255,0.6)",
            width: 2
          }
        },
        splitLine: {
          lineStyle: {
            color: "rgba(255,255,255,0.1)"
          }
        }
      }],
      series: [{
        name: '用户活跃度',
        type: 'bar',
        barWidth: '35%',
        data: [200, 300, 300, 900, 1500, 1200, 600],
        itemStyle: {
          barBorderRadius: 5
        }
      }]
    };
    let chartDomLeft1 = document.getElementById("left-1");
    this.myChartLeft1 = echarts.init(chartDomLeft1)
    // 将配置设置到图表中
    this.request.get("/user/userActionCountMax7").then(res => {
      if(res.code ==='200'){
        this.bar.series[0].data = res.data.map(item => item.actionCount);
        this.bar.xAxis[0].data = res.data.map(item => item.nickname);
        this.myChartLeft1.setOption(this.bar);
      }
    })


    // 柱状图2
    let myColor = ["#1089E7", "#F57474", "#F8B448", "#8B78F6"];
    this.bar2 = {
      grid: {
        top: "10%",
        left: '22%',
        bottom: '10%',
        // containLabel: true
      },
      xAxis: {
        // 不显示x轴相关信息
        show: false
      },
      yAxis: [{
        type: 'category',
        // y轴数据反转，与数组的顺序一致
        inverse: true,
        // 不显示y轴线和刻度
        axisLine: {
          show: false
        },
        axisTick: {
          show: false
        },
        // 将刻度标签文字设置为白色
        axisLabel: {
          color: "#fff"
        },
        data: ["男性", "女性", "其他"]
      }, {
        // y轴数据反转，与数组的顺序一致
        inverse: true,
        show: true,
        // 不显示y轴线和刻度
        axisLine: {
          show: false
        },
        axisTick: {
          show: false
        },
        // 将刻度标签文字设置为白色
        axisLabel: {
          color: "#fff"
        },
        data: [702, 350, 610]
      }],
      series: [{
        // 第一组柱子（条状）
        name: '条',
        type: 'bar',
        // 柱子之间的距离
        barCategoryGap: 50,
        // 柱子的宽度
        barWidth: 10,
        // 层级 相当于z-index
        yAxisIndex: 0,
        // 柱子更改样式
        itemStyle: {
          barBorderRadius: 20,
          // 此时的color可以修改柱子的颜色
          color: function (params) {
            // params 传进来的是柱子的对象
            // dataIndex 是当前柱子的索引号
            // console.log(params);
            return myColor[params.dataIndex];
          }
        },
        data: [70, 34, 60],
        // 显示柱子内的百分比文字
        label: {
          show: true,
          position: "inside",
          // {c} 会自动解析为数据（data内的数据）
          formatter: "{c}%"
        }
      },
        {
          // 第二组柱子（框状 border）
          name: '框',
          type: 'bar',
          // 柱子之间的距离
          barCategoryGap: 50,
          // 柱子的宽度
          barWidth: 14,
          // 层级 相当于z-index
          yAxisIndex: 1,
          // 柱子修改样式
          itemStyle: {
            color: "none",
            borderColor: "#00c1de",
            borderWidth: 2,
            barBorderRadius: 15,
          },
          data: [100, 100, 100]
        }
      ]
    };
    let chartDomRight1 = document.getElementById("right-1")
    this.myChartRight1 = echarts.init(chartDomRight1)
    this.request.get("/user/getSexCount").then(res => {
      if(res.code === '200'){
        let genders = res.data.map(item => item.sex);
        let counts = res.data.map(item => parseInt(item.count));
        let percentages = res.data.map(item => item.percentage.toFixed(2));
        this.bar2.yAxis[0].data = genders
        this.bar2.yAxis[1].data = counts
        this.bar2.series[0].data = percentages
        this.myChartRight1.setOption(this.bar2)
      }else {
        this.myChartRight1.setOption(this.bar2)
      }
    })

    // 折线图 左中间
    let userCount2022=[]
    let userCount2023=[]

    this.line1 ={
      // 修改两条线的颜色
      color: ['#00f2f1', '#ed3f35'],
      tooltip: {
        trigger: 'axis'
      },
      // 图例组件
      legend: {
        // 当serise 有name值时， legend 不需要写data
        // 修改图例组件文字颜色
        textStyle: {
          color: '#4c9bfd'
        },
        right: '10%',
      },
      grid: {
        top: "20%",
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true,
        show: true, // 显示边框
        borderColor: '#012f4a' // 边框颜色
      },
      xAxis: {
        type: 'category',
        boundaryGap: false, // 去除轴间距
        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
        // 去除刻度线
        axisTick: {
          show: false
        },
        axisLabel: {
          color: "#4c9bfb" // x轴文本颜色
        },
        axisLine: {
          show: false // 去除轴线
        }
      },
      yAxis: {
        type: 'value',
        // 去除刻度线
        axisTick: {
          show: false
        },
        axisLabel: {
          color: "#4c9bfb" // x轴文本颜色
        },
        axisLine: {
          show: false // 去除轴线
        },
        splitLine: {
          lineStyle: {
            color: "#012f4a"
          }
        }
      },
      series: [{
        type: 'line',
        smooth: true, // 圆滑的线
        name: '2022年',
        data: []
      },
        {
          type: 'line',
          smooth: true, // 圆滑的线
          name: '2023年',
          data: []
        }
      ]
    };

    let chartDomeLeft2 = document.getElementById("left-2")
    this.myChartLeft2 = echarts.init(chartDomeLeft2)

    this.request.get("/user/getMonthCount",{
      params: {
        year: "2022"
      }
    }).then(res => {
      if(res.code === '200'){
        userCount2022 = res.data.map(item => item.count)
        this.line1.series[0].data = userCount2022
        this.myChartLeft2.setOption(this.line1)
      }
    })
    this.request.get("/user/getMonthCount",{
      params: {
        year: "2023"
      }
    }).then(res => {
      if(res.code === '200'){
        userCount2023 = res.data.map(item => item.count)
        this.line1.series[1].data = userCount2023
        this.myChartLeft2.setOption(this.line1)
      }
    })


   // 折线图 播放量 右二
    this.line2 = {
      tooltip: {
        trigger: 'axis',
      },
      legend: {
        top: "0%",
        textStyle: {
          color: "rgba(255,255,255,.5)",
          fontSize: "12"
        }
      },
      grid: {
        top: '30',
        left: '10',
        right: '30',
        bottom: '10',
        containLabel: true
      },
      xAxis: [{
        type: 'category',
        boundaryGap: false,
        // 文本颜色为rgba(255,255,255,.6)  文字大小为 12
        axisLabel: {
          textStyle: {
            color: "rgba(255,255,255,.6)",
            fontSize: 12
          }
        },
        // x轴线的颜色为   rgba(255,255,255,.2)
        axisLine: {
          lineStyle: {
            color: "rgba(255,255,255,.2)"
          }
        },
        data: ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "26", "28", "29", "30"]
      }],
      yAxis: [{
        type: 'value',
        axisTick: {
          // 不显示刻度线
          show: false
        },
        axisLine: {
          lineStyle: {
            color: "rgba(255,255,255,.1)"
          }
        },
        axisLabel: {
          textStyle: {
            color: "rgba(255,255,255,.6)",
            fontSize: 12
          }
        },
        // 修改分割线的颜色
        splitLine: {
          lineStyle: {
            color: "rgba(255,255,255,.1)"
          }
        }
      }],
      series: [{
        name: '播放量',
        type: 'line',
        smooth: true, // 圆滑的线
        // 单独修改当前线条的样式
        lineStyle: {
          color: "#0184d5",
          width: 2
        },
        // 填充区域渐变透明颜色
        areaStyle: {
          color: new echarts.graphic.LinearGradient(
              0,
              0,
              0,
              1,
              [{
                offset: 0,
                color: "rgba(1, 132, 213, 0.4)" // 渐变色的起始颜色
              },
                {
                  offset: 0.8,
                  color: "rgba(1, 132, 213, 0.1)" // 渐变线的结束颜色
                }
              ],
              false
          ),
          shadowColor: "rgba(0, 0, 0, 0.1)"
        },
        // 拐点设置为小圆点
        symbol: 'circle',
        // 设置拐点大小
        symbolSize: 5,
        // 开始不显示拐点， 鼠标经过显示
        showSymbol: false,
        // 设置拐点颜色以及边框
        itemStyle: {
          color: "#0184d5",
          borderColor: "rgba(221, 220, 107, .1)",
          borderWidth: 12
        },
        data: [30, 40, 30, 40, 30, 40, 30, 60, 20, 40, 30, 40, 30, 40, 30, 40, 30, 60, 20, 40, 30, 40, 30, 40, 30, 40, 20, 60, 50, 40]
      },
        {
          name: "点赞量",
          type: "line",
          smooth: true,
          lineStyle: {
            normal: {
              color: "#00d887",
              width: 2
            }
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(
                  0,
                  0,
                  0,
                  1,
                  [{
                    offset: 0,
                    color: "rgba(0, 216, 135, 0.4)"
                  },
                    {
                      offset: 0.8,
                      color: "rgba(0, 216, 135, 0.1)"
                    }
                  ],
                  false
              ),
              shadowColor: "rgba(0, 0, 0, 0.1)"
            }
          },
          // 设置拐点 小圆点
          symbol: "circle",
          // 拐点大小
          symbolSize: 5,
          // 设置拐点颜色以及边框
          itemStyle: {
            color: "#00d887",
            borderColor: "rgba(221, 220, 107, .1)",
            borderWidth: 12
          },
          // 开始不显示拐点， 鼠标经过显示
          showSymbol: false,
          data: [130, 10, 20, 40, 30, 40, 80, 60, 20, 40, 90, 40, 20, 140, 30, 40, 130, 20, 20, 40, 80, 70, 30, 40, 30, 120, 20, 99, 50, 20]
        },
        {
          name: "收藏量",
          type: "line",
          smooth: true,
          lineStyle: {
            normal: {
              color: "#d100d8",
              width: 2
            }
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(
                  0,
                  0,
                  0,
                  1,
                  [{
                    offset: 0,
                    color: "rgba(86,0,216,0.4)"
                  },
                    {
                      offset: 0.8,
                      color: "rgba(216,0,198,0.1)"
                    }
                  ],
                  false
              ),
              shadowColor: "rgba(0, 0, 0, 0.1)"
            }
          },
          // 设置拐点 小圆点
          symbol: "circle",
          // 拐点大小
          symbolSize: 5,
          // 设置拐点颜色以及边框
          itemStyle: {
            color: "#6100d8",
            borderColor: "rgba(221, 220, 107, .1)",
            borderWidth: 12
          },
          // 开始不显示拐点， 鼠标经过显示
          showSymbol: false,
          data: [130, 10, 20, 40, 30, 40, 80, 60, 20, 40, 90, 40, 20, 140, 30, 40, 130, 20, 20, 40, 80, 70, 30, 40, 30, 120, 20, 99, 50, 20]
        }
      ]
    };
    let chartDomRight2 = document.getElementById("right-2")
    this.myChartRight2 = echarts.init(chartDomRight2)
    this.request.get("/uop/LastMonthDate").then(res => {
      if(res.code === '200'){
        // 提取数据
        let pCountData = res.data.map(item => parseInt(item.pcount)); // 转为整数
        let lCountData = res.data.map(item => parseInt(item.lcount)); // 转为整数
        let fCountData = res.data.map(item => parseInt(item.fcount)); // 转为整数
        this.line2.series[0].data = pCountData
        this.line2.series[1].data = lCountData
        this.line2.series[2].data = fCountData
        this.myChartRight2.setOption(this.line2)
      }
    })



    // 饼图 左三
    this.pie1 = {
      color: ["#1089E7", "#F57474", "#56D0E3", "#F8B448", "#8B78F6"],
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)'
      },
      legend: {
        // 垂直居中,默认水平居中
        // orient: 'vertical',

        bottom: 0,
        left: 10,
        // 小图标的宽度和高度
        itemWidth: 10,
        itemHeight: 10,
        // 修改图例组件的文字为 12px
        textStyle: {
          color: "rgba(255,255,255,.5)",
          fontSize: "10"
        }
      },
      series: [{
        name: '年龄分布',
        type: 'pie',
        // 设置饼形图在容器中的位置
        center: ["50%", "42%"],
        // 修改饼形图大小，第一个为内圆半径，第二个为外圆半径
        radius: ['40%', '60%'],
        avoidLabelOverlap: false,
        // 图形上的文字
        label: {
          show: false,
          position: 'center'
        },
        // 链接文字和图形的线
        labelLine: {
          show: false
        },
        data: [{
          value: 1,
          name: "0-20岁"
        },
          {
            value: 4,
            name: "20-29岁"
          },
          {
            value: 2,
            name: "30-39岁"
          },
          {
            value: 2,
            name: "40-49岁"
          },
          {
            value: 1,
            name: "50岁以上"
          }
        ]
      }]
    };
    let chartDomLeft3 = document.getElementById("left-3")
    this.myChartLeft3 = echarts.init(chartDomLeft3)


    this.request.get("/user/getAgeGroup").then(res=>{
      if(res.code === '200'){
        this.pie1.series[0].data = res.data.map(item => ({ value: item.count, name: item.ageGroup }));
        this.myChartLeft3.setOption(this.pie1)
      }
    })

    // 饼图 右三
    this.pie2 = {
      color: ['#60cda0', '#ed8884', '#ff9f7f', '#0096ff', '#9fe6b8', '#32c5e9', '#1d9dff'],
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        bottom: 0,
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: "rgba(255,255,255,.5)",
          fontSize: 10
        }
      },
      series: [{
        name: '地区分布',
        type: 'pie',
        radius: ["10%", "60%"],
        center: ['50%', '40%'],
        // 半径模式  area面积模式
        roseType: 'radius',
        // 图形的文字标签
        label: {
          fontsize: 10
        },
        // 引导线调整
        labelLine: {
          // 连接扇形图线长(斜线)
          length: 6,
          // 连接文字线长(横线)
          length2: 8
        },
        data: [{
          value: 26,
          name: '北京'
        },
          {
            value: 24,
            name: '山东'
          },
          {
            value: 25,
            name: '河北'
          },
          {
            value: 20,
            name: '江苏'
          },
          {
            value: 25,
            name: '浙江'
          },
          {
            value: 30,
            name: '四川'
          },
          {
            value: 42,
            name: '湖北'
          }
        ]
      }]
    };
    let chartDomRight3 = document.getElementById("right-3")
    this.myChartRight3 = echarts.init(chartDomRight3)
    this.request.get("/user/getAddressCountMax7").then(res=>{
      if(res.code === '200'){
        this.pie2.series[0].data = res.data.map(item => ({value: item.count,name:item.address}))
        this.myChartRight3.setOption(this.pie2)
      }
    })


    // 中间地图
    let geoCoordMap = {
      '上海': [121.4648, 31.2891],
      '东莞': [113.8953, 22.901],
      '东营': [118.7073, 37.5513],
      '中山': [113.4229, 22.478],
      '临汾': [111.4783, 36.1615],
      '临沂': [118.3118, 35.2936],
      '丹东': [124.541, 40.4242],
      '丽水': [119.5642, 28.1854],
      '乌鲁木齐': [87.9236, 43.5883],
      '佛山': [112.8955, 23.1097],
      '保定': [115.0488, 39.0948],
      '兰州': [103.5901, 36.3043],
      '包头': [110.3467, 41.4899],
      '北京': [116.4551, 40.2539],
      '北海': [109.314, 21.6211],
      '南京': [118.8062, 31.9208],
      '南宁': [108.479, 23.1152],
      '南昌': [116.0046, 28.6633],
      '南通': [121.1023, 32.1625],
      '厦门': [118.1689, 24.6478],
      '台州': [121.1353, 28.6688],
      '合肥': [117.29, 32.0581],
      '呼和浩特': [111.4124, 40.4901],
      '咸阳': [108.4131, 34.8706],
      '哈尔滨': [127.9688, 45.368],
      '唐山': [118.4766, 39.6826],
      '嘉兴': [120.9155, 30.6354],
      '大同': [113.7854, 39.8035],
      '大连': [122.2229, 39.4409],
      '天津': [117.4219, 39.4189],
      '太原': [112.3352, 37.9413],
      '威海': [121.9482, 37.1393],
      '宁波': [121.5967, 29.6466],
      '宝鸡': [107.1826, 34.3433],
      '宿迁': [118.5535, 33.7775],
      '常州': [119.4543, 31.5582],
      '广州': [113.5107, 23.2196],
      '廊坊': [116.521, 39.0509],
      '延安': [109.1052, 36.4252],
      '张家口': [115.1477, 40.8527],
      '徐州': [117.5208, 34.3268],
      '德州': [116.6858, 37.2107],
      '惠州': [114.6204, 23.1647],
      '成都': [103.9526, 30.7617],
      '扬州': [119.4653, 32.8162],
      '承德': [117.5757, 41.4075],
      '拉萨': [91.1865, 30.1465],
      '无锡': [120.3442, 31.5527],
      '日照': [119.2786, 35.5023],
      '昆明': [102.9199, 25.4663],
      '杭州': [119.5313, 29.8773],
      '枣庄': [117.323, 34.8926],
      '柳州': [109.3799, 24.9774],
      '株洲': [113.5327, 27.0319],
      '武汉': [114.3896, 30.6628],
      '汕头': [117.1692, 23.3405],
      '江门': [112.6318, 22.1484],
      '沈阳': [123.1238, 42.1216],
      '沧州': [116.8286, 38.2104],
      '河源': [114.917, 23.9722],
      '泉州': [118.3228, 25.1147],
      '泰安': [117.0264, 36.0516],
      '泰州': [120.0586, 32.5525],
      '济南': [117.1582, 36.8701],
      '济宁': [116.8286, 35.3375],
      '海口': [110.3893, 19.8516],
      '淄博': [118.0371, 36.6064],
      '淮安': [118.927, 33.4039],
      '深圳': [114.5435, 22.5439],
      '清远': [112.9175, 24.3292],
      '温州': [120.498, 27.8119],
      '渭南': [109.7864, 35.0299],
      '湖州': [119.8608, 30.7782],
      '湘潭': [112.5439, 27.7075],
      '滨州': [117.8174, 37.4963],
      '潍坊': [119.0918, 36.524],
      '烟台': [120.7397, 37.5128],
      '玉溪': [101.9312, 23.8898],
      '珠海': [113.7305, 22.1155],
      '盐城': [120.2234, 33.5577],
      '盘锦': [121.9482, 41.0449],
      '石家庄': [114.4995, 38.1006],
      '福州': [119.4543, 25.9222],
      '秦皇岛': [119.2126, 40.0232],
      '绍兴': [120.564, 29.7565],
      '聊城': [115.9167, 36.4032],
      '肇庆': [112.1265, 23.5822],
      '舟山': [122.2559, 30.2234],
      '苏州': [120.6519, 31.3989],
      '莱芜': [117.6526, 36.2714],
      '菏泽': [115.6201, 35.2057],
      '营口': [122.4316, 40.4297],
      '葫芦岛': [120.1575, 40.578],
      '衡水': [115.8838, 37.7161],
      '衢州': [118.6853, 28.8666],
      '西宁': [101.4038, 36.8207],
      '西安': [109.1162, 34.2004],
      '贵阳': [106.6992, 26.7682],
      '连云港': [119.1248, 34.552],
      '邢台': [114.8071, 37.2821],
      '邯郸': [114.4775, 36.535],
      '郑州': [113.4668, 34.6234],
      '鄂尔多斯': [108.9734, 39.2487],
      '重庆': [107.7539, 30.1904],
      '金华': [120.0037, 29.1028],
      '铜川': [109.0393, 35.1947],
      '银川': [106.3586, 38.1775],
      '镇江': [119.4763, 31.9702],
      '长春': [125.8154, 44.2584],
      '长沙': [113.0823, 28.2568],
      '长治': [112.8625, 36.4746],
      '阳泉': [113.4778, 38.0951],
      '青岛': [120.4651, 36.3373],
      '韶关': [113.7964, 24.7028]
    };

    let userData = [];
    this.request.get("/user/getAddressCount").then(res => {
      if (res.code === '200') {
        userData = res.data;
        // 数据请求成功后，初始化地图
        initMap();
      }
    });

    let color = ['#9bc84c', '#227aff', '#e9469d'];
    let option = {
      tooltip: {
        trigger: 'item',
        formatter: function (params) {
          let tooltipContent = `${params.name}<br>`;
          for (const data of userData) {
            if (params.name === data.address) {
              tooltipContent += `用户数: ${data.count}`;
              break;
            }
          }
          return tooltipContent;
        },
      },
      visualMap: {
        min: 0,
        max: 300, // 根据您的数据范围设置最大值
        left: 'left',
        bottom: 'bottom',
        text: ['高', '低'], // 文本，默认为数值文本
        textStyle: {
          color: '#fff',
        },
        calculable: true,
        inRange: {
          color: color, // 根据颜色数组设置颜色范围
        },
      },
      geo: {
        map: 'china',
        // 把地图放大1.2倍
        zoom: 0.5,
        label: {
          // 鼠标移动显示区域名称
          emphasis: {
            show: true,
            color: '#fff'
          }
        },
        roam: true,
        // 地图样式修改
        itemStyle: {
          normal: {
            areaColor: 'rgba(34, 70, 168, 0.7)',
            borderColor: '#0692a4'
          },
          emphasis: {
            areaColor: 'rgba(119, 139, 224, 0.548)'
          }
        }
      },
      series: []
    };

    function initMap() {
      // 根据用户数数据配置地图数据
      let mapData = userData.map(data => {
        return {
          name: data.address,
          value: data.count,
        };
      });

      option.series.push(
          {
            name: '用户数',
            type: 'map',
            mapType: 'china',
            roam: false,
            label: {
              normal: {
                show: true,
                textStyle: {
                  color: '#fff'
                }
              },
              emphasis: {
                show: true,
                textStyle: {
                  color: '#fff'
                }
              }
            },
            data: mapData
          }
      );

      let chartDomCenter = document.getElementById("center");
      let myChartCenter = echarts.init(chartDomCenter);
      myChartCenter.setOption(option);
    }


  },

  methods: {
    setupClock() {
      this.updateTime(); // 初始化时更新一次时间
      setInterval(this.updateTime, 1000); // 每隔一秒更新时间
    },
    updateTime() {
      const dt = new Date();
      const y = dt.getFullYear();
      const mt = dt.getMonth() + 1;
      const day = dt.getDate();
      const h = dt.getHours();
      const m = dt.getMinutes();
      const s = dt.getSeconds();
      this.currentTime =
          "当前时间：" +
          y +
          "年" +
          mt +
          "月" +
          day +
          "日-" +
          h +
          "时" +
          m +
          "分" +
          s +
          "秒";
    },

    changeYear(year){

    },

    getCount(){
      this.request.get("/echarts/getUserCountAndActive").then(res => {
        if(res.code === '200'){
          this.userCount = res.data.userCount
          this.activeCount = res.data.activeCount
        }
      })
    },

  },

};
</script>

<style scoped>
@import "../assets/css/show.css";

.myh2 {
  height: 0.6rem;
  color: #fff;
  line-height: 0.6rem;
  text-align: center;
  font-size: 0.25rem;
  font-weight: 400;
}
</style>
