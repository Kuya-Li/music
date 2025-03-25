<template>
  <main>
    <div class="m-wrap">
      <header class="h-title">
        <h2><a>歌手详情</a></h2>
      </header>
    </div>
  </main>
</template>

<script>


export default {
  name: "SingerDetail",
  data(){
    return{
      artistId: "",
      singerDetail: [],
    }
  },
  created() {
    this.artistId=this.$route.params.artistId
  },
  mounted() {
    document.title="歌手详情"
    this.getSingerDetail();
  },
  methods: {
    getSingerDetail(){
      const apiUrl = "http://192.168.43.102:3000/artist/detail?id=${artistId}";
      this.request.get(apiUrl).then(res => {
        if(res.code === 200){
            this.singerDetail=res.artist
        }
      })
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