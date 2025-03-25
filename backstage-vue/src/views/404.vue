<template>
  <div class="not-found">
    <div class="not-found-text">
      <h1>404</h1>
      <p>很抱歉，页面未找到。</p>
      <router-link to="/">返回首页</router-link>
      <router-link to="/login" style="margin-left: 40px">返回登陆</router-link>
    </div>
    <div class="not-found-image">
      <img src="../assets/headerLogo.png" alt="404 Image" />
    </div>
  </div>
</template>

<script>
export default {
  name: "NotFound",
  mounted() {
    document.title = "404";
  }
}
</script>

<style scoped>
.not-found {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f0f0;
}

.not-found-text {
  text-align: center;
  padding: 20px;
}

.not-found h1 {
  font-size: 4rem;
  margin-bottom: 10px;
  color: #ff6347;
}

.not-found p {
  font-size: 1.2rem;
  margin-bottom: 20px;
}

.not-found a {
  color: #3498db;
  text-decoration: none;
}

.not-found-image img {
  max-width: 100%;
  height: auto;
}
</style>
