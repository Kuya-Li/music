<template>


  <div class="m-wrap">
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入歌曲名" suffix-icon="el-icon-search" v-model="musicName"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

      <header class="h-title">
        <h2><a href="#">音乐库</a></h2>
      </header>
    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange">
      <el-table-column prop="id" label="ID" width="70" ></el-table-column>
      <el-table-column prop="musicName" label="歌曲名" width="240"></el-table-column>
      <el-table-column label="歌曲链接" width="300px">
        <template slot-scope="scope">
          <a :href="scope.row.url" target="_blank">{{ scope.row.url }}</a>
        </template>
      </el-table-column>
      <el-table-column label="音乐首页" width="400">
        <template slot-scope="scope">
<!--          <a :href="'http://localhost:8081/music/' + scope.row.musicId" target="_blank">{{ scope.row.musicId }}</a>-->
          <a :href="'http://192.168.43.105:8081/music/' + scope.row.musicId" target="_blank">{{ scope.row.musicId }}</a>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[ 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

  </div>

</template>

<script>
export default {
  name: "Album",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      musicId:"",
      musicName:"",
      url:"",
      form: {},
      records: [],
      dialogFormVisible: false,
      multipleSelection: [],
    }
  },
  created() {
    this.load()
  },
  mounted() {
    document.title = "音乐库";
  },
  methods: {
    load() {
      this.request.get("/sys-music/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          musicName: this.musicName,
        }
      }).then(res => {
        if(res.code === '200'){
          this.tableData = res.data.records
          this.total = res.data.total
        }
      })
    },



    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },

    reset() {
      this.musicName = ""
      this.singer = ""
      this.album = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },

  }
}
</script>


<style>
@import "../assets/css/custom-pagination.css";

.headerBg {
  background: #eee!important;
}
/* 消除链接的蓝色文本颜色和下划线 */
a {
  color: inherit; /* 使用父元素的文本颜色 */
  text-decoration: none; /* 消除下划线 */
}

/* 如果需要为链接添加悬停样式 */
a:hover {
  /* 添加悬停时的样式，比如修改文本颜色 */
  color: rebeccapurple;
}

</style>
