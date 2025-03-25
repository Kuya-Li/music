<template>
  <el-container>
    <!-- 头部导航栏 -->
    <el-header class="custom-header">
      <!-- 使用封装好的 Header 组件 -->
      <Header />
    </el-header>

    <el-main>
      <!--        表示当前页面的子路由会在 <router-view /> 里面展示-->
      <router-view />

    </el-main>

  </el-container>
</template>

<script>
import Header from "@/components/Header";


export default {
  components: {
    Header,

  },

  data() {
    return {
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
      carouselInterval: 5000, // 自定义轮播切换间隔
    };
  },
};
</script>

<style scoped>
.custom-header {
  padding: 0;
  margin: 0;
  background-color: #2B2E2FFF;
}

</style>
