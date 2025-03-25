<template>
  <div class="outer-container" :style="{ background: `linear-gradient(to bottom right, ${bgColor1}, ${bgColor2})` }">
    <div class="carousel-container">
      <el-carousel :interval="interval" arrow="always" class="custom-carousel" @change="handleCarouselChange">
        <el-carousel-item v-for="(item, index) in items" :key="index">
          <a :href="item.url" target="_blank">
            <img :src="item.image" alt="轮播图片" class="carousel-image" :style="{ backgroundColor: item.bgColor }" />
          </a>
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    items: {
      type: Array,
      required: true,
      default: () => []
    },
    interval: {
      type: Number,
      default: 4000
    }
  },
  data() {
    return {
      bgColor1: '#F39EBBFF', // 初始背景颜色为空字符串
      bgColor2: 'rgba(236,225,227,0.2)'
    };
  },
  methods: {
    handleCarouselChange(index) {
      // 在轮播项切换时更新背景颜色为当前轮播项的背景颜色
      this.bgColor1 = this.items[index].bgColor1
      this.bgColor2 = this.items[index].bgColor2
    }
  }
};
</script>

<style scoped>
/* 外部容器样式，设置背景颜色 */
.outer-container {
  height: 300px; /* 设置外部容器的高度 */
}

/* 容器样式，设置轮播的宽度和位置 */
.carousel-container {
  position: relative;
  width: 1000px; /* 调整轮播的宽度 */
  margin: 0 auto;
}

/* 轮播样式 */
.custom-carousel {
  position: relative;
}

/* 轮播图片样式 */
.carousel-image {
  width: 100%;
  height: 300px;
}
</style>
