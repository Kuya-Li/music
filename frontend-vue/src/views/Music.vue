<template>
  <main>
    <div class="m-wrap">
      <header class="h-title">
        <h2><a href="#">音乐</a></h2>
      </header>
      <div class="m-info f-cb">
        <div class="cover">
          <img :src="musicList[0].pic">
        </div>
        <div class="info">
          <p class="m-title">歌曲标题: {{ musicList[0].musicName }}</p>
          <p class="m-artist">歌手名称: {{ musicList[0].name }}</p>
          <p class="m-album">专辑名称: {{ musicList[0].title }}</p>
          <p class="m-album">发行时间: {{ musicList[0].time }}</p>
          <div class="m-controls">
            <button class="m-play" @click="operation('p')"><i class="el-icon-headset"/>播放</button>
            <button class="m-collections" @click="operation('f')"><i class="el-icon-star-off"></i>收藏</button>
            <button class="m-like" @click="operation('l')"><i class="el-icon-lollipop"></i>喜欢</button>
          </div>
        </div>
      </div>
      <Comment></Comment>
    </div>
  </main>
</template>

<script>
import Comment from '@/components/Comment'
export default {
  components: {Comment},
  comments:{
    Comment
  },
  data(){
    return {
      musicId:"",
      musicList:[],
      musicArtist:[],
      musicAlbum:[],
    }
  },
  created() {
    this.musicId=this.$route.params.musicId
    this.getMusicList();
  },
  mounted() {
    document.title = "音乐";
  },
  name: "Music",
  methods:{
    // getMusicList(){  //老方法,请求速度太慢，已经换新接口处理
    //   const apiUrl = `http://localhost:9090/sys-music/getMusicMore/${this.musicId}`;
    //   this.request.get(apiUrl).then(res => {
    //     if( res.code === '200'){
    //       this.musicList = res.data;
    //       this.musicAlbum = res.data.musicAlbum;
    //       this.musicArtist = res.data.musicArtist;
    //     }
    //   })
    // },

    getMusicList(){
      const apiUrl = `http://192.168.43.105:9090/musicProfile/getByMusicId/${this.musicId}`
      // const apiUrl = `http://localhost:9090/musicProfile/getByMusicId/${this.musicId}`
      this.request.get(apiUrl).then(res => {
        if(res.code === '200'){
          this.musicList = res.data;
        }
      })
    },
    operation(op) {
      const userString = localStorage.getItem("user");
      const user = JSON.parse(userString);
      const userId = user.id;

      const formData = new FormData();
      formData.append('uid', userId);
      formData.append('musicId', this.musicId);
      formData.append('op', op);

      // 添加请求头，告诉后端请求体的格式是JSON
      const config = {
        headers: {
          'Content-Type': 'application/json', // 设置请求体为JSON格式
        },
      };

      this.request.post("/uop/music-play", formData, config).then(res => {
        if (res.code === '200') {
          if(op === "p"){
            this.$message.success("播放成功")
          }
          if(op === "l"){
            this.$message.success("喜欢成功")
          }
          if(op === "f"){
            this.$message.success("收藏成功")
          }
        } else {
          this.$message.error("操作失败")
        }
      });
    }

  }
}
</script>

<style scoped>
@import "../assets/css/css.css";
.m-wrap {
  overflow: hidden;
  margin: 0 auto;
  width: 980px;
  border: 1px solid #d3d3d3;
  border-bottom: none;
  background: #fff;
}

.m-info {
  display: flex;
  align-items: center;
  padding: 20px;
}

.cover {
  flex: 1;
  margin-right: 20px;
}

.cover img {
  max-width: 100%;
  height: auto;
}

.info {
  flex: 3;
}

.m-title {
  font-size: 24px;
  margin: 0;
}

.m-artist, .m-album {
  font-size: 18px;
  margin: 5px 0;
}

.m-controls {
  margin-top: 20px;
}

.m-play, .m-collections, .m-like {
  padding: 10px 20px;
  font-size: 18px;
  margin-right: 10px;
  border: none;
  background-color: #0070c9;
  color: #fff;
  cursor: pointer;
  border-radius: 5px;
}

.m-play:hover, .m-collection:hover, .m-like:hover {
  background-color: #0056a7;
}
</style>
