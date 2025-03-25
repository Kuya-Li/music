<template>
  <div class="m-wrap">
    <header class="h-title">
      <h2 href="#">热门歌手推荐</h2>
    </header>

    <el-table :data="singerList" border stripe :header-cell-class-name="'headerBg'">
      <el-table-column label="排行" >
      <template slot-scope="scope">
        {{ scope.$index + 1 }}
      </template>
    </el-table-column>
      <el-table-column prop="name" label="歌手" ID="name"></el-table-column>
      <el-table-column prop="totalScores" label="得分" ID="score"></el-table-column>
      <el-table-column prop="artist" label="歌手ID" ID="artistId"></el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "test",
  data(){
    return{
      singerList: [],
    }
  },
  mounted() {
    document.title="歌手"
    this.getSinger();
    console.log(this.singerList)
  },
  methods:{
    getSinger(){
      this.request.get("/singerPopular/getSingerPopular").then(res => {
        if(res.code=== '200'){
          this.singerList = res.data
        }

      })
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

.headerBg {
  background: #eee!important;
}
</style>