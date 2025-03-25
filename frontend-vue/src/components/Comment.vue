<template>
  <section class="comment">
    <header class="com-title clearFix">
      <h3>评论</h3>
      <p>共123456条评论</p>
    </header>
    <div class="comarea clearFix">
      <div class="com-img">
        <img src="../assets/img/comimg.jpg"/>
      </div>
      <div class="commentarea">
        <div class="area">
          <textarea></textarea>
        </div>
        <div class="comicon">
          <i class="smile"></i>
          <i class="art"></i>
          <a href="#" class="comtxt">评论</a>
          <span>140</span>
        </div>
      </div>
    </div>
    <div class="comment-info">
      <h3>精彩评论</h3>
      <ul>
        <li class="com-info clearFix">
          <div class="head">
            <a href="#">
              <img src="../assets/img/19146895486401442.jpg"/>
            </a>
          </div>
          <div class="com-content">
            <p class="com-top">
              <a href="#">游客甲</a>:这是一条评论！
            </p>
            <p class="com-bottom">
              <time>10:36</time>
              <a href="#" class="finger"><i></i>(666)</a>
              <span>|</span>
              <a href="#">回复</a>
            </p>
          </div>
        </li>
        <li class="com-info clearFix">
          <div class="head">
            <a href="#">
              <img src="../assets/img/002.jpg"/>
            </a>
          </div>
          <div class="com-content">
            <p class="com-top">
              <a href="#">游客乙</a>:这就是一条评论！
            </p>
            <p class="com-bottom">
              <time>10:36</time>
              <a href="#" class="finger"><i></i>(666)</a>
              <span>|</span>
              <a href="#">回复</a>
            </p>
          </div>
        </li>
        <li class="com-info clearFix">
          <div class="head">
            <a href="#">
              <img src="../assets/img/003.jpg"/>
            </a>
          </div>
          <div class="com-content">
            <p class="com-top">
              <a href="#">游客丙</a>:这还是一条评论！
            </p>
            <p class="com-bottom">
              <time>10:36</time>
              <a href="#" class="finger"><i></i>(666)</a>
              <span>|</span>
              <a href="#">回复</a>
            </p>
          </div>
        </li>
      </ul>
      <h3>最新评论</h3>
      <ul>
        <li class="com-info clearFix">
          <div class="head">
            <a href="#">
              <img src="../assets/img/004.jpg"/>
            </a>
          </div>
          <div class="com-content">
            <p class="com-top">
              <a href="#">游客丁</a>:这不是一条评论！
            </p>
            <p class="com-bottom">
              <time>10:36</time>
              <a href="#" class="finger"><i></i>(666)</a>
              <span>|</span>
              <a href="#">回复</a>
            </p>
          </div>
        </li>
        <li class="com-info clearFix">
          <div class="head">
            <a href="#">
              <img src="../assets/img/005.jpg"/>
            </a>
          </div>
          <div class="com-content">
            <p class="com-top">
              <a href="#">游客戊</a>:这怎么不是一条评论！
            </p>
            <p class="com-bottom">
              <time>10:36</time>
              <a href="#" class="finger"><i></i>(666)</a>
              <span>|</span>
              <a href="#">回复</a>
            </p>
          </div>
        </li>
        <li class="com-info clearFix">
          <div class="head">
            <a href="#">
              <img src="../assets/img/006.jpg"/>
            </a>
          </div>
          <div class="com-content">
            <p class="com-top">
              <a href="#">游客己</a>:这真的不是一条评论！
            </p>
            <p class="com-bottom">
              <time>10:36</time>
              <a href="#" class="finger"><i></i>(666)</a>
              <span>|</span>
              <a href="#">回复</a>
            </p>
          </div>
        </li>
      </ul>
      <nav class="page">
        <a href="#" class="page-change pro-page" style="width: 73px">上一页</a>
        <a href="#" class="page-change page-active">1</a>
        <a href="#" class="page-change">2</a>
        <a href="#" class="page-change">3</a>
        <a href="#" class="page-change">4</a>
        <a href="#" class="page-change">5</a>
        <span class="page-change font">...</span>
        <a href="#" class="page-change">88</a>
        <a href="#" class="page-change next-page" style="width: 73px">下一页</a>
      </nav>
    </div>
  </section>
</template>

<script>
export default {
  name: "Comment"
}
</script>

<style scoped>
@import "../assets/css/base.css";
@import "../assets/css/ranking.css";

</style>