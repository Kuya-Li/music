<template>
  <div>
    <div id="tool">
      <span @click="goBackChina">{{firstTitle}}</span>
      <span @click="goBackProvince">{{currentProvince.name}}</span>
      <span @click="goBackCity">{{currentCity.name}}</span>
    </div>
    <div id="chinaMap"></div>
  </div>
</template>
<script>
import * as echarts from 'echarts';
//  安装 province-city-china 依赖。 
//  npm i province-city-china -S
const { province, city } = require('province-city-china/data');

export default {
  name: 'ChinaMain',
  data() {
    return {
      firstTitle: '中国',
      myChart: '',
      currentClick: '',
      currentProvince: {},
      currentCity: {},
      cityState: false,
      provinceState: false
    }
  },
  mounted() {
    this.initEcharts("china", "中国");
  },
  methods: {
    goBackChina() {
      this.initEcharts("china", "中国");
      this.currentProvince.name = '';
      this.currentCity.name = '';
    },
    goBackProvince() {
      this.initEcharts(this.currentProvince.province, this.currentProvince.name, "1");
      this.currentCity.name = '';
    },
    goBackCity() {
      this.initEcharts(this.currentCity.code, this.currentCity.name);
    },
    initEcharts(pName, Chinese_, state = "0") {
      this.myChart = echarts.init(document.getElementById('chinaMap'));
      let tmpSeriesData = [];
      if (pName === "china") {
        let geoJson = require('../assets/map/json/china.json');
        echarts.registerMap(pName, geoJson);
      } else {
        if (this.currentClick === 'province' || state === "1") {
          let geoJson = require(`../assets/map/json/${pName}.json`);
          echarts.registerMap(pName, geoJson);
        } else {
          let geoJson = require(`../assets/map/json/${pName}.json`);
          echarts.registerMap(pName, geoJson);
        }
      }
      let option = {
        series: [
          {
            name: Chinese_ || pName,
            type: 'map',
            mapType: pName,
            roam: false,//是否开启鼠标缩放和平移漫游
            itemStyle: {//地图区域的多边形 图形样式
              normal: {//是图形在默认状态下的样式
                label: {
                  show: true,//是否显示标签
                  textStyle: {
                    color: "#303133",
                    fontSize: '10px'
                  }
                }
              },
              emphasis: {//是图形在高亮状态下的样式,比如在鼠标悬浮或者图例联动高亮时
                label: {
                  show: true,
                  textStyle: {
                    fontSize: '14px'
                  }
                }
              }
            },
            data: tmpSeriesData,//后端数据
            top: "10%"//组件距离容器的距离
          }
        ]
      };
      this.myChart.setOption(option, true);
      this.myChart.off("click");
      if (pName === "china") { // 全国时，添加click 进入省级
        this.currentClick = 'province'
        this.myChart.on('click', this.mapClick);
      } else {
        this.currentClick = 'city'
        this.myChart.on('click', this.mapClick);
      }
    },
    //地图点击事件
    mapClick(param) {
      this.provinceState = false;
      this.cityState = false;
      if (this.currentClick === 'province') {
        //遍历取到provincesText 中的下标  去拿到对应的省js
        for (var i = 0; i < province.length; i++) {
          if (param.name === province[i].name) {
            this.provinceState = true;
            this.currentProvince = { ...province[i] };
            this.currentProvince.name = ` -->${this.currentProvince.name} `;
            //显示对应省份的方法
            this.showProvince(province[i].province, province[i].name);
            break;
          }
        }
        !this.provinceState ? alert('暂不支持该区域地图展示!') : '';
      } else {
        for (var l = 0; l < city.length; l++) {
          if (param.name === city[l].name) {
            this.cityState = true;
            this.currentCity = { ...city[l] };
            this.currentCity.name = ` -->${this.currentCity.name} `;
            //显示对应城市的方法
            this.showProvince(city[l].code, city[l].name);
            break;
          }
        }
        !this.cityState ? alert('暂不支持该区域地图展示!') : '';
      }
    },
    showProvince(pName, Chinese_) {
      this.initEcharts(pName, Chinese_);
    }
  }
}
</script>
<style lang="less">
#tool {
  height: 30px;
  line-height: 30px;
  color: deepskyblue;
  cursor: pointer;
  // position: absolute;
  top: 10px;
  left: 10px;
  z-index: 10000;
  font-size: 18px;
  text-align: left;
}
#chinaMap {
  width: 1000px;
  height: 600px;
}
</style>

 

