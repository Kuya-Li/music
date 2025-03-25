<template>
  <div>
    <!-- 轮播组件 -->
    <Carousel :items="carouselItems" :interval="carouselInterval" />

    <main class="main">
      <div class="m-wrap">
        <div class="m-left">
          <!--热门推荐-->
          <header class="h-title">
            <h2><a href="#">热门推荐</a> </h2>
            <nav class="h-list">
              <a href="#">华语</a>
              <span class="line">|</span>
              <a href="#">流行</a>
              <span class="line">|</span>
              <a href="#">摇滚</a>
              <span class="line">|</span>
              <a href="#">民谣</a>
              <span class="line">|</span>
              <a href="#">电子</a>
            </nav>
            <el-link class="more" href="/musiclib">更多<i>&nbsp;</i></el-link>
          </header>

          <ul class="h-music clearFix">
            <!-- 使用 v-for 渲染数据 -->
            <li v-for="(item, groupIndex) in musicList" :key="groupIndex">
                <div class="h-img">
<!--                  <el-link :href="'http://localhost:8081/music/'+ item.musicId">-->
                  <el-link :href="'http://192.168.43.105:8081/music/'+ item.musicId">
                    <img :src="item.pic" />
                    <span class="bg"></span>
                    <span class="icon"></span>
                    <span class="txt">55万</span>
                  </el-link>
<!--                  <el-link :href="'http://localhost:8081/music/'+ item.musicId" class="play" :title="item.musicName"></el-link>-->
                  <el-link :href="'http://192.168.43.105:8081/music/'+ item.musicId" class="play" :title="item.musicName"></el-link>
                </div>
                <p><el-link :href="item.url">{{ item.musicName }}</el-link></p>
            </li>
          </ul>
        </div>

        <!--广告-->
        <div id="m-banner" style="margin-bottom: 5px">
          <a href="#">
            <img src="../assets/img/m-banner.jpg">
          </a>
        </div>
        <IndexRight></IndexRight>
        <div class="m-left">
          <header class="h-title">
            <h2><a href="#">新碟上架</a></h2>
            <span class="more"><a href="#">更多</a> </span>
          </header>

          <div class="disk clearFix">
            <a href="#" class="tit titL">&nbsp;</a>
            <div class="dd-list">
              <ul class="clearFix">
                <li class="d-disk" v-for="(item,index) in newMusicList" :key="index">
                  <div class="dd-img">
                    <img :src="item.pic"/>
                    <a :href="'http://192.168.43.105:8081/music/'+ item.musicId" class="p-disk"></a>
<!--                    <a :href="'http://localhost:8081/music/'+ item.musicId" class="p-disk"></a>-->
                    <a :href="'http://192.168.43.105:8081/music/'+ item.musicId" class="diskplay"></a>
<!--                    <a :href="'http://localhost:8081/music/'+ item.musicId" class="diskplay"></a>-->
                  </div>
                  <p><a :href="item.url">{{item.title}}</a></p>
                  <p class="singer"><a :href="item.url">{{ item.artist }}</a></p>
                </li>
              </ul>
            </div>
          </div>
        </div>


      </div>

    </main>

  </div>
</template>

<script>
import Carousel from "@/components/Carousel";
import IndexRight from "@/components/index-right";
export default {
  components: {
    Carousel,
    IndexRight
  },

  name: "index",
  data(){

    return{
      // 初始化一个空数组，用于存储数据
      musicList: [],
      newMusicList:[],
      carouselItems: [
        {
          image: require("@/assets/img/back01.png"),
          url: "https://music.163.com/#/song?id=2079350542",
          bgColor1: "#F39EBBFF", // 第一个轮播项的背景颜色
          bgColor2: "rgba(236,225,227,0.2)"
        },
        {
          image: require("@/assets/img/back02.png"),
          url: "https://y.music.163.com/m/at/64f1b3b404ede7895b448523",
          bgColor1: "#bedfee", // 第二个轮播项的背景颜色
          bgColor2: "rgba(234,226,226,0.2)",
        },
        {
          image: require("@/assets/img/back03.png"),
          url: "https://music.163.com/#/song?id=2078584332",
          bgColor1: "#574b25", // 第三个轮播项的背景颜色
          bgColor2: "#f8f2f1",
        },
        {
          image: require("@/assets/img/back04.png"),
          url: "https://music.163.com/#/album?id=173681761",
          bgColor1: "#ef9bed", // 第四个轮播项的背景颜色
          bgColor2: "rgb(230,228,236)"
        },
      ],
      carouselInterval: 3000, // 自定义轮播切换间隔
    };

  },
  mounted(){
    document.title="首页"
    // 组件挂载后，可以在这里获取后端数据更新musicList
    this.getHotMusicList()
    this.getNewMusicList()
  },
  methods:{
    getHotMusicList() {
      this.request.get("/popular/detail-new").then(res => {
        if (res.code === '200') {
          this.musicList = res.data; // 更新 musicList
          console.log("请求成功");
        } else {
          console.log("请求失败");
        }
      });
    },

    getNewMusicList(){
      this.request.get("/musicNew/getNewMusic").then(res => {
        if(res.code === '200'){
          this.newMusicList = res.data.slice(0, 5);
        }
      })

    }
  }
};
</script>

<style scoped>
@import "../assets/css/css.css";

.main {
  overflow: hidden;
  background: #f2f2f2;
}
.m-wrap {
  overflow: hidden;
  margin: 0 auto;
  width: 980px;
  border: 1px solid #d3d3d3;
  border-bottom: none;
  background: #fff;
}
.m-left {
  float: left;
  zoom: 1;
  width: 689px;
  padding: 20px 20px 40px;
  border-right: 1px solid #d3d3d3;
}
.h-music li{
  margin-bottom: 20px;
  margin-right: 30px;
}

</style>
