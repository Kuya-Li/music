<template>
  <div class="full-screen-background">
    <!-- Your component content here -->
  </div>
</template>

<script>
export default {
  name: "nici"
}
</script>

<style scoped>
.full-screen-background {
  /* 设置背景图片的路径，可以是相对路径或 URL */
  background-image: url('../../assets/bg1/bj.jpg');

  /* 设置背景图片的大小，以 cover 模式填充整个容器 */
  background-size: cover;

  /* 设置背景图片的重复方式，这里是不重复 */
  background-repeat: no-repeat;

  /* 设置背景图片的位置，这里是居中 */
  background-position: center;

  /* 设置容器的高度为 100vh，让容器占满整个视口高度 */
  height: 90vh;

  /* 设置其他样式属性，根据需要进行设置 */
}
</style>
