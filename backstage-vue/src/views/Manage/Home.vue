<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 40px">
      <el-col :span="6">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-user-solid"/>用户总数 </div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">{{ userCount}}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #F56C6C">
          <div><i class="el-icon-user-solid"/>歌手总数 </div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">{{ singerCount}}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #67C23A">
          <div><i class="el-icon-mic"/>歌曲总数 </div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">{{ songCount }}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #E6A23C">
          <div><i class="el-icon-folder-opened"/>专辑总数 </div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">{{ albumCount }}</div>
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "Home",
  data(){
    return {
        currentTime: new Date(),
        userCount: 0,
        songCount: 0,
        singerCount: 0,
        albumCount: 0,
        pieData: []
    }
  },
  created() {
    this.load()
  },
  mounted() {
    document.title="首页"
    setInterval(() => {
      this.currentTime = new Date();
    }, 1000); // 更新间隔为1秒

    let option = {
      title: {
        text: '本年注册用户数量统计',
        left: 'center'
      },
      tooltip: {
        trigger: 'axis', // 触发类型为坐标轴
        formatter: function(params) {
          // 自定义提示框内容的格式
          return '用户数: ' + params[0].value; // 显示第一个数据点的值
        },
        axisPointer: {
          type: 'none' // 坐标轴指示器类型为线
        }
      },
      xAxis: {
        type: 'category',
        data: ["第一季度", "第二季度", "第三季度", "第四季度"]
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'bar',
        }
      ]
    };

    let chartDom = document.getElementById('main');
    let myChart = echarts.init(chartDom);

    this.request.get("/echarts/members").then(res => {
      if(res.code === '200'){
        // option.xAxis.data = res.data.x
        option.series[0].data = res.data
        // 数据准备完毕之后再set
        myChart.setOption(option);

      }

    })


    let pieOption = {
      title: {
        text: '歌曲语种占比',
        subtext: '比例图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          type: 'pie',
          radius: '60%',
          label:{            //饼图图形上的文本标签
            normal:{
              show:true,
              position:'inner', //标签的位置
              textStyle : {
                fontWeight : 300 ,
                fontSize : 14,    //文字的字体大小
                color: "#fff"
              },
              formatter:'{d}%'
            }
          },
          data: [],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    let pieDom = document.getElementById('pie');
    let pieChart = echarts.init(pieDom);

    this.request.get("/echarts/language").then(res => {
      if(res.code === '200'){
        this.pieData = res.data.map(item => {
          return{
            name: item.language,
            value: item.count
          }
        })
        pieOption.series[0].data = this.pieData;
        pieChart.setOption(pieOption)
      }
    })

    let option2 = {


      title: {
        text: '用户活跃信息'
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
        data: ['用户登陆数', '用户收藏数', '用户播放数', '用户喜欢数'],
        right: 10, // 将图例放在图表的右侧
        top: 50,
        orient: 'vertical' // 竖向排列图例
      },

      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      grid: {
        left: '5%',
        right: '20%', // 调整右边的间距以给图例腾出空间
        bottom: '3%',
        containLabel: true
      },
      xAxis: [
        {
          type: 'category',
          boundaryGap: false,
          data: [] //先设置为空数据，更新为数据库的日期内容
        }
      ],
      yAxis: [
        {
          type: 'value'
        }
      ],
      series: [
        {
          name: '用户登陆数',
          type: 'line',
          stack: 'Total',
          smooth: true,
          lineStyle: {
            width: 0
          },
          showSymbol: false,
          areaStyle: {
            opacity: 0.8,
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: 'rgb(128, 255, 165)'
              },
              {
                offset: 1,
                color: 'rgb(1, 191, 236)'
              }
            ])
          },
          emphasis: {
            focus: 'series'
          },
          data: []  //先设置为空数字，统计为 login 数
        },
        {
          name: '用户收藏数',
          type: 'line',
          stack: 'Total',
          smooth: true,
          lineStyle: {
            width: 0
          },
          showSymbol: false,
          areaStyle: {
            opacity: 0.8,
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: 'rgb(0, 221, 255)'
              },
              {
                offset: 1,
                color: 'rgb(77, 119, 255)'
              }
            ])
          },
          emphasis: {
            focus: 'series'
          },
          data: [] // 用户收藏数数据 f
        },
        {
          name: '用户播放数',
          type: 'line',
          stack: 'Total',
          smooth: true,
          lineStyle: {
            width: 0
          },
          showSymbol: false,
          areaStyle: {
            opacity: 0.8,
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: 'rgb(55, 162, 255)'
              },
              {
                offset: 1,
                color: 'rgb(116, 21, 219)'
              }
            ])
          },
          emphasis: {
            focus: 'series'
          },
          data: [] //用户播放数数据 p
        },
        {
          name: '用户喜欢数',
          type: 'line',
          stack: 'Total',
          smooth: true,
          lineStyle: {
            width: 0
          },
          showSymbol: false,
          areaStyle: {
            opacity: 0.8,
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: 'rgb(255, 0, 135)'
              },
              {
                offset: 1,
                color: 'rgb(135, 0, 157)'
              }
            ])
          },
          emphasis: {
            focus: 'series'
          },
          data: [] //用户喜欢数数据 l
        },

      ]
    };
    let chartDom1 = document.getElementById('main2');
    let myChart2 = echarts.init(chartDom1);
    // 获取后端返回的数据
    this.request.get("/uop/user-record").then(res => {
      if (res.code === '200') {
        let responseData = res.data;

        // 提取日期和各个活跃指标的数据
        let datesSet = new Set(responseData.map(item => item.time));
        let dates = Array.from(datesSet);
        let loginCounts = responseData.filter(item => item.op === 'login').map(item => item.count).reverse();
        let favoriteCounts = responseData.filter(item => item.op === 'f').map(item => item.count).reverse();
        let playCounts = responseData.filter(item => item.op === 'p').map(item => item.count).reverse();
        let likeCounts = responseData.filter(item => item.op === 'l').map(item => item.count).reverse();

        // 对日期进行升序排序
        dates.sort((a, b) => new Date(a) - new Date(b));

        // 设置X轴的日期数据
        option2.xAxis[0].data = dates;

        // 分别设置各个系列（活跃指标）的数据
        option2.series[0].data = loginCounts;
        option2.series[1].data = favoriteCounts;
        option2.series[2].data = playCounts;
        option2.series[3].data = likeCounts;

        myChart2.setOption(option2);
      }
    })




  },

  methods: {
      load(){
        this.request.get("/echarts/statistics").then(res => {
          if(res.code === '200'){
            this.userCount = res.data.userCount
            this.singerCount = res.data.singerCount
            this.songCount = res.data.songCount
            this.albumCount = res.data.albumCount
          }
        })
      }
  }



}
</script>

<style scoped>
/* 样式为返回首页的链接 */
.home-link {
  font-size: 0.25rem;
  color: #409EFF; /* 链接的颜色 */
  text-decoration: none; /* 去掉下划线 */
  margin-left: 10px; /* 调整链接与其他文本的间距 */
  transition: color 0.3s ease; /* 添加颜色渐变效果 */
}

/* 鼠标悬停时的样式 */
.home-link:hover {
  color: #0063B1; /* 鼠标悬停时的颜色 */
}
</style>
