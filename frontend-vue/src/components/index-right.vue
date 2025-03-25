<template>
  <div class="m-right">
    <div class="tab">
      <div class="user">
        <p>欢迎来到音乐推荐系统,我们将为您带来热门的个性化音乐推荐！</p>
        <a href="#">Let's Go</a>
      </div>
      <div class="m-singer">
        <h3>
          <p>入驻歌手</p>
          <a href="#">查看更多&gt;</a>
        </h3>
        <ul class="clearFix">
          <li>
            <a href="#">
              <div class="s-photo">
                <img src="../assets/img/singer/1.jpg"/>
              </div>
              <div class="s-info">
                <h4>张惠妹aMEI</h4>
                <p>台湾歌手张惠妹</p>
              </div>
            </a>
          </li>
          <li>
            <a href="#">
              <div class="s-photo">
                <img src="../assets/img/singer/2.jpg"/>
              </div>
              <div class="s-info">
                <h4>Fine乐团</h4>
                <p>独立音乐人</p>
              </div>
            </a>
          </li>
          <li>
            <a href="#">
              <div class="s-photo">
                <img src="../assets/img/singer/3.jpg"/>
              </div>
              <div class="s-info">
                <h4>Jony_J</h4>
                <p>说唱歌手</p>
              </div>
            </a>
          </li>
          <li>
            <a href="#">
              <div class="s-photo">
                <img src="../assets/img/singer/4.jpg"/>
              </div>
              <div class="s-info">
                <h4>Panta点打不出来Q</h4>
                <p>电子制作人</p>
              </div>
            </a>
          </li>
          <li>
            <a href="#">
              <div class="s-photo">
                <img src="../assets/img/singer/5.jpg"/>
              </div>
              <div class="s-info">
                <h4>音乐人赵雷</h4>
                <p>民谣歌手</p>
              </div>
            </a>
          </li>
        </ul>
        <div class="apply">
          <a href="#">
            <span>申请网易音乐人</span>
          </a>
        </div>
      </div>
      <section class="hotdj">
        <h3>热门DJ</h3>
        <ul class="hot-dj-er clearFix">
          <li>
            <a href="#" class="d-img">
              <img src="../assets/img/singer/dj.jpg"/>
            </a>
            <div class="d-info">
              <p>
                <a href="#" class="dj">陈立</a>
                <i class="h-icon"></i>
              </p>
              <p>心理学家、美食家陈立教授</p>
            </div>
          </li>
          <li>
            <a href="#" class="d-img">
              <img src="../assets/img/singer/dj2.jpg"/>
            </a>
            <div class="d-info">
              <p>
                <a href="#" class="dj">DJ艳秋</a>
                <i class="h-icon"></i>
              </p>
              <p>著名音乐节目主持人</p>
            </div>
          </li>
          <li>
            <a href="#" class="d-img">
              <img src="../assets/img/singer/dj3.jpg"/>
            </a>
            <div class="d-info">
              <p>
                <a href="#" class="dj">国家大剧院古典音乐频道</a>
                <i class="h-icon"></i>
              </p>
              <p>国家大剧院古典音乐官方</p>
            </div>
          </li>
          <li>
            <a href="#" class="d-img">
              <img src="../assets/img/singer/dj4.jpg"/>
            </a>
            <div class="d-info">
              <p>
                <a href="#" class="dj">谢谢收听</a>
                <i class="h-icon"></i>
              </p>
              <p>南京电台主持人王馨</p>
            </div>
          </li>
          <li>
            <a href="#" class="d-img">
              <img src="../assets/img/singer/dj5.jpg"/>
            </a>
            <div class="d-info">
              <p>
                <a href="#" class="dj">DJ晓苏</a>
                <i class="h-icon"></i>
              </p>
              <p>独立DJ，CRI环球旅游广播特邀DJ</p>
            </div>
          </li>
        </ul>
      </section>
    </div>
  </div>
</template>

<script>
export default {
  name: "index-right"
}
</script>

<style scoped>
@import "../assets/css/css.css";


.m-right {
  float: right;
  margin-top: -850px;
  margin-right: 20px;
}
.tab {
  width: 250px;
}
.user {
  height: 126px;
  background: url(../assets/img/singer/index.png) no-repeat 0 0;
}
.user p {
  width: 205px;
  margin: 0 auto;
  padding: 16px 0;
  line-height: 22px;
  color: #666;
}
.user a {
  margin: 0 auto;
  display: block;
  width: 100px;
  height: 31px;
  line-height: 31px;
  text-align: center;
  color: #fff;
  background: url(../assets/img/singer/index.png) no-repeat 0 -195px;
}
.m-singer {
  margin-top: 15px;
}
.m-singer h3 {
  height: 23px;
  margin: 0 20px;
  border-bottom: 1px solid #ccc;
  color: #333;
}
.m-singer h3 p {
  float: left;
  zoom: 1;
}
.m-singer h3 a {
  float: right;
  zoom: 1;
  color: #666;
}
.m-singer ul {
  margin: 6px 0 14px 20px;
}
.m-singer li {
  float: left;
  zoom: 1;
  margin-top: 14px;
}
.m-singer li a {
  float: left;
  zoom: 1;
  width: 210px;
  height: 62px;
  background: #fafafa;
}
.m-singer li a:hover {
  background: #f4f4f4;
}
.s-photo {
  float: left;
  zoom: 1;
  width: 62px;
  height: 62px;
}
.s-photo img {
  width: 62px;
  height: 62px;
}
.s-info {
  float: left;
  zoom: 1;
  width: 133px;
  height: 60px;
  padding-left: 14px;
  border: 1px solid #e9e9e9;
  border-left: none;
}
.s-info h4 {
  margin-top: 8px;
  font-size: 14px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  color: #333;
}
.s-info p {
  margin-top: 8px;
  color: #666;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.s-photo {
  float: left;
  zoom: 1;
  width: 62px;
  height: 62px;
}
.s-photo img {
  width: 62px;
  height: 62px;
}
.s-info {
  float: left;
  zoom: 1;
  width: 133px;
  height: 60px;
  padding-left: 14px;
  border: 1px solid #e9e9e9;
  border-left: none;
}
.s-info h4 {
  margin-top: 8px;
  font-size: 14px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  color: #333;
}
.s-info p {
  margin-top: 8px;
  color: #666;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.apply {
  margin-left: 20px;
}
.apply a {
  display: inline-block;
  padding: 0 5px 0 0;
  border-radius: 4px;
  background: url(../assets/img/singer/button2.png) no-repeat right -100px;
  color: #333;
}
.apply a span {
  display: inline-block;
  width: 170px;
  padding: 0 15px 0 20px;
  line-height: 31px;
  vertical-align: top;
  text-align: center;
  font-weight: bold;
  background: url(../assets/img/singer/button2.png) no-repeat 0 -59px;
}
.hotdj {
  margin-top: 30px;
}
.hotdj h3 {
  height: 23px;
  margin: 0 20px;
  border-bottom: 1px solid #ccc;
  color: #333;
}
.hot-dj-er {
  margin: 20px 0 0 20px;
}
.hot-dj-er li {
  float: left;
  zoom: 1;
  width: 210px;
  height: 50px;
}
.d-img {
  float: left;
  zoom: 1;
  margin-right: 10px;
}
.d-img img {
  width: 40px;
  height: 40px;
}
.d-info {
  float: left;
  zoom: 1;
  width: 160px;
  line-height: 21px;
  color: #666;
}
.d-info .dj {
  float: left;
  zoom: 1;
  color: #000;
}
.d-info p {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.h-icon {
  display: inline-block;
  vertical-align: middle;
  margin-top: -1px;
  width: 11px;
  height: 13px;
  background: url(../assets/img/icon.png) no-repeat 0 1px;
}
</style>