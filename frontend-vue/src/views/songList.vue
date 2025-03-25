<template>
  <div class="m-wrap">
    <header class="h-title">
      <h2 href="#">歌单</h2>
    </header>
  </div>
</template>

<script>
export default {
  name: "songList"
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
</style>