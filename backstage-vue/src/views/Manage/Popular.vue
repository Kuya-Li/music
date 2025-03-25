<template>

  <div>
    <el-row>
      <el-col :span="16">
        <div id="main" style="width: 800px; height: 500px"></div>
      </el-col>
    </el-row>
  </div>


</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "popular",
  data() {
    return {
      musicName: [],
      scores: [],
    }
  },

  mounted() {
    let option = {
      title: {
        text: "热歌排行榜TOP10",
        left: "center"
      },
      tooltip: {
        trigger: 'axis', // 触发类型为坐标轴
        formatter: function(params) {
          const musicName = params[0].axisValue; // 获取横轴的歌名
          const score = params[0].value; // 获取得分
          return '歌名:'+ musicName + '<br>得分: ' + score; // 显示歌名和得分
        },
        axisPointer: {
          type: 'none' // 坐标轴指示器类型为线
        }
      },
      xAxis: {
        type: 'category',
        data: [],
        axisLabel: {
          rotate: 45 // 旋转角度，可以根据需要调整
        }
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
    }
    let chartDom = document.getElementById('main');
    let myChart = echarts.init(chartDom);
    this.request.get("/popular/top10").then(res => {
      console.log("Request response:", res);
      if (res.code === '200') {
        const musicNames = res.data.map(item => item.musicName);
        const scores = res.data.map(item => item.scores);
        option.xAxis.data = musicNames;
        option.series[0].data = scores;
        myChart.setOption(option);
      }
    })
  }
}
</script>

<style scoped>

</style>