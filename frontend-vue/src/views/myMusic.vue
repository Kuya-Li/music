<template>
  <div class="m-wrap">
    <header class="h-title">
      <h2><a href="#">为你推荐</a></h2>
    </header>
    <ul class="h-music clearFix">
      <!-- 使用 v-for 渲染数据 -->
      <li v-for="(item, groupIndex) in musicList" :key="groupIndex" style="margin-left: 20px">
        <div class="h-img">
          <!--                  <el-link :href="'http://localhost:8081/music/'+ item.musicId">-->
          <el-link :href="'http://192.168.43.105:8081/music/'+ item.musicId">
            <img :src="item.pic" />
            <span class="bg"></span>
            <span class="icon"></span>
            <span class="txt">55万</span>
          </el-link>
          <!--<el-link :href="'http://localhost:8081/music/'+ item.musicId" class="play" :title="item.musicName"></el-link>-->
          <el-link :href="'http://192.168.43.105:8081/music/'+ item.musicId" class="play" :title="item.musicName"></el-link>
        </div>
        <p><el-link :href="item.url">{{ item.musicName }}</el-link></p>
      </li>
    </ul>

  </div>
</template>

<script>
export default {
  name: "myMusic",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      uid: "",
      recommendedMusicArray:[],
      musicList: [],
      musicId: []
    };
  },
  created() {
    this.uid = this.user.id;
    this.getMyMusic().then(() => {
      this.getDetail();
    });
  },
  mounted() {
    document.title = "为你推荐";
  },
  methods: {
    getMyMusic() {
      return new Promise((resolve, reject) => {
        this.request.get("/musicRecommend/getMusic", {
          params: {
            uid: this.uid
          }
        }).then(res => {
          if (res.code === '200') {
            this.recommendedMusicArray = res.data;
            this.musicId = this.recommendedMusicArray.map(item => item.recommendedMusic);
            resolve(); // 解决 Promise，表示异步操作完成
          } else {
            console.error("请求失败");
            reject(); // 拒绝 Promise，表示异步操作失败
          }
        }).catch(error => {
          console.error("请求出错：", error);
          reject(); // 拒绝 Promise，表示异步操作失败
        });
      });
    },
    getDetail() {
      console.log(this.musicId);
      this.request.post("/musicProfile/getByMusicIds", this.musicId)
          .then(res => {
            if (res.code === '200') {
              this.musicList = res.data;
            } else {
              console.log("请求失败");
            }
          })
          .catch(error => {
            console.error("请求出错：", error);
          });
    }
  }
}
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
.m-right {
  float: right;
}
.h-music li{
  margin-bottom: 20px;
  margin-right: 30px;
}
</style>