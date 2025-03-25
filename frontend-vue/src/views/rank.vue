<template>
  <div class="m-wrap">
    <div>
      <header class="h-title">
        <h2><a href="#">排行榜</a></h2>
        <span class="more"><a href="#">更多</a><i>&nbsp;</i></span>
      </header>
      <div class="n-list clearFix" style="margin-left: 140px;">
        <div class="sec" v-for="(rank, index) in ranks" :key="index">
          <div class="top">
            <div class="l-img">
              <img :src="rank.imageSrc" />
              <a href="#" class="msk"></a>
            </div>
            <div class="titl" style="margin-bottom: 43px">
              <h3 class="l-title"><a :href="rank.link">{{ rank.title }}</a></h3>
              <div class="btn">
                <a href="#" class="l-play">播放</a>
                <a href="#" class="l-collection">收藏</a>
              </div>
            </div>
          </div>
          <ul class="song-list">
            <li v-for="(song, songIndex) in rank.songs" :key="songIndex">
              <a :href="'http://localhost:8081/music/' + song.musicId" class="song" style="margin-left: 10px;">{{ song.musicName }}</a>
              <span class="num numb" style="margin-left: 10px;">{{ song.num }}</span>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: "rank",
  data() {
    return {
      ranks: [
        {
          title: "飙升榜",
          imageSrc: require("@/assets/img/list/2.jpg"),
          link: "#",
          songs: [
            {
              num: "1",
              musicName: "歌曲1",
              playLink: "#",
              addLink: "#",
              collectionLink: "#"
            },
            {
              num: "2",
              musicName: "歌曲2",
              playLink: "#",
              addLink: "#",
              collectionLink: "#"
            },
            {
              num: "3",
              musicName: "歌曲3",
              playLink: "#",
              addLink: "#",
              collectionLink: "#"
            },
          ]
        },
        {
          title: "新歌榜",
          imageSrc: require("@/assets/img/list/1.jpg"),
          link: "#",
          songs: [
            {
              num: "1",
              musicName: "歌曲1",
              playLink: "#",
              addLink: "#",
              collectionLink: "#"
            },
            {
              num: "2",
              musicName: "歌曲2",
              playLink: "#",
              addLink: "#",
              collectionLink: "#"
            }
          ]
        },
        {
          title: "原创歌曲榜",
          imageSrc: require("@/assets/img/list/3.jpg"),
          link: "#",
          songs: [
            {
              num: "1",
              musicName: "歌曲1",
              playLink: "#",
              addLink: "#",
              collectionLink: "#"
            },
            {
              num: "2",
              musicName: "歌曲2",
              playLink: "#",
              addLink: "#",
              collectionLink: "#"
            }
          ]
        }
        // 添加更多排行榜数据
      ]
    };
  },
  mounted() {
    document.title="排行榜"
    this.getNewMusicTop10()
    // 在页面返回时执行重新加载
    this.getPopularMusicTop10()
    this.getOriginTop10()
  },
  methods: {
    getPopularMusicTop10(){
      this.request.get("/popular/getPopularMusicTop10").then(res =>{
        if(res.code === '200'){
          const popularMusic = res.data;
          popularMusic.forEach((song, index) => {
            song.num = (index + 1).toString(); // 自动排序，从1开始
          });
          this.ranks[0].songs = popularMusic
        }
      })
    },
    getNewMusicTop10(){
      this.request.get("/musicNew/getNewMusicTop10").then(res => {
        if(res.code === '200'){
          // 假设请求返回的歌曲数据在res.data中
          const newSongs = res.data;

          // 为新歌曲数据添加排名
          newSongs.forEach((song, index) => {
            song.num = (index + 1).toString(); // 自动排序，从1开始
          });

          // 将新歌曲合并到云音乐新歌榜中
          this.ranks[1].songs = newSongs;
        }
      })
    },
    getOriginTop10(){
      this.request.get("/musicProfile/original").then(res => {
        if(res.code === '200'){
          const  originSongs = res.data;
          // 为新歌曲数据添加排名
          originSongs.forEach((song, index) => {
            song.num = (index + 1).toString(); // 自动排序，从1开始
          });

          this.ranks[2].songs = originSongs;

        }
      })
    }
  }
};
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
</style>
