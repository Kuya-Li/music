<template>
  <div>
    <el-header>
      <el-row type="flex" align="middle">
        <!-- 左侧 Logo 和标题 -->
        <el-col :span="8">
          <div class="logo">
            <img src="../assets/headerLogo.png" alt="网站Logo" />
            <span class="site-title">音乐推荐系统</span>
          </div>
        </el-col>
        <!-- 中间导航菜单 -->
        <el-col :span="12">
          <el-menu class="nav-menu" mode="horizontal" router>
            <el-menu-item index="/index">发现音乐</el-menu-item>
            <el-menu-item index="/musiclib">音乐库</el-menu-item>
            <el-menu-item index="/singer">歌手</el-menu-item>
            <el-menu-item index="/rank">排行榜</el-menu-item>
            <el-menu-item index="/myMusic">为你推荐</el-menu-item>
          </el-menu>
        </el-col>
        <!-- 右侧用户区域 -->
        <el-col :span="4">
          <div class="user-section" >
            <template v-if="user.token" >
              <!-- 如果用户已登录，显示用户头像和用户名 -->
              <el-dropdown trigger="hover" :visible.sync="dropdownVisible">
                <div>
                  <img :src="user.avatar" alt="" class="user-avatar"/>
                  <span class="user-nickname" >{{ user.nickname }}</span>
                </div>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item >
                    <span @click="showProfile">个人信息</span>
                  </el-dropdown-item>
                  <el-dropdown-item>
                    <span  @click="openChangePassword">修改密码</span>
                  </el-dropdown-item>
                  <el-dropdown-item>
                  <span  @click="logout">退出登录</span>
                </el-dropdown-item>

                </el-dropdown-menu>
              </el-dropdown>
            </template>
            <template v-else>
              <!-- 如果用户未登录，显示登录按钮 -->
              <el-button type="text" class="login-item" @click="showLoginDialog">登录</el-button>
            </template>
          </div>
        </el-col>
      </el-row>
    </el-header>

    <el-dialog
        title="登录"
        :visible.sync="loginDialogVisible"
        width="400px"
        @close="resetLogin"
    >
      <!-- 登录表单 -->
      <el-form :model="user" :rules="loginRules" ref="userForm" @keyup.enter.native="login">
        <el-form-item prop="username">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small"  autocomplete="off" @click="login">登录</el-button>
          <el-button type="warning" size="small"  autocomplete="off" @click="toRegister">注册</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog
        title="注册"
        :visible.sync="registerDialogVisible"
        width="400px"
        @close="resetRegister"
    >
      <el-form :model="registerUser" :rules="registerRules" ref="registerForm">
        <el-form-item prop="username">
          <el-input placeholder="请输入账号" size="medium" prefix-icon="el-icon-user" v-model="registerUser.username"></el-input>
        </el-form-item>
        <el-form-item prop="nickname">
          <el-input placeholder="请输入昵称" size="medium" prefix-icon="el-icon-user" v-model="registerUser.nickname"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" size="medium" prefix-icon="el-icon-lock" show-password v-model="registerUser.password"></el-input>
        </el-form-item>
        <el-form-item prop="confirmPassword">
          <el-input placeholder="请确认密码" size="medium" prefix-icon="el-icon-lock" show-password v-model="registerUser.confirmPassword"></el-input>
        </el-form-item>
        <el-form-item style="margin: 5px 0; text-align: right">
          <el-button type="primary" size="small"  autocomplete="off" @click="register">注册</el-button>
          <el-button type="warning" size="small"  autocomplete="off" @click="toLogin">返回登录</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog
      title="修改密码"
      :visible.sync="changePasswordDialogVisible"
      width="600px"
      @close="closeChangePassword"
    >
      <el-form label-width="120px" size="small" :model="ChangePasswordForm" :rules="ChangePasswordRules" ref="pass">

        <el-form-item label="原密码" prop="password">
          <el-input v-model="ChangePasswordForm.password" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input v-model="ChangePasswordForm.newPassword" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认新密码" prop="confirmPassword">
          <el-input v-model="ChangePasswordForm.confirmPassword" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="changePassword">确 定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      registerUser:{},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      ChangePasswordForm: {},
      loginRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
      },
      loginDialogVisible: false,  //  登录框状态
      dropdownVisible: false, //  下拉框状态
      registerDialogVisible: false,
      registerRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
      },
      changePasswordDialogVisible: false,
      ChangePasswordRules: {
        password: [
          { required: true, message: '请输入原密码', trigger: 'blur' },
          { min: 3, message: '长度不少于3位', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { min: 3, message: '长度不少于3位', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, message: '长度不少于3位', trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    showLoginDialog() {
      //  打开登录框
      this.loginDialogVisible = true;
    },
    resetLogin() {
      // 关闭登录框
      this.loginDialogVisible = false
      this.user.username=''
      this.user.password=''
    },
    showRegisterDialog() {
      //  打开注册框
      this.registerDialogVisible = true;
    },
    resetRegister() {
      // 关闭注册框
      this.registerDialogVisible = false;
      this.registerUser.username=''
      this.registerUser.password=''
      this.registerUser.confirmPassword=''
      this.registerUser.nickname=''
    },
    toggleDropdown(){
      // 切换下拉菜单的显示状态
      this.dropdownVisible = !this.dropdownVisible;
    },
    login() {
      // 登陆逻辑验证
      this.$refs.userForm.validate((valid) => {
        if (valid) {
          // 表单校验合法
          // 发送登录请求
          this.request.post("/user/login", this.user).then(res => {
            if (res.code === '200') {
              localStorage.setItem("user", JSON.stringify(res.data));
              this.loginDialogVisible = false;
              this.$message.success("登陆成功");

              // 延迟刷新页面，等待消息提示显示完成
              setTimeout(() => {
                window.location.reload();
              }, 1000); // 1秒延迟，您可以根据需要调整延迟时间
            } else {
              this.$message.error(res.msg);
            }
          }).catch(error => {
            // 处理登录请求失败的情况
            console.error(error);
          });
        }
      });
    },

    logout() {
      // 在此处添加登出逻辑
      // 清除用户信息并执行登出操作
      localStorage.removeItem("user")
      this.$message.success("退出成功")
      this.user = {}
    },
    showProfile(){
      this.$router.push('/Person')
    },
    toRegister(){
      //关闭登录框
      this.resetLogin()
      //打开注册框
      this.showRegisterDialog()
    },



    register() {
      this.$refs['registerForm'].validate((valid) => {
        if (valid) {  // 表单校验合法
          if (this.registerUser.password !== this.registerUser.confirmPassword) {
            this.$message.error("两次输入的密码不一致")
            return false
          }
          this.request.post("/user/register", this.registerUser).then(res => {
            if(res.code === '200') {
              // 关闭注册
              this.resetRegister()
              this.$message.success("注册成功");
              setTimeout(() => {
                window.location.reload();
              },1000);
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      });
    },


    toLogin(){
      //关闭注册框
      this.resetRegister()
      //打开登录框
      this.showLoginDialog()
    },

    closeChangePassword(){
      this.ChangePasswordForm.password=''
      this.ChangePasswordForm.newPassword=''
      this.ChangePasswordForm.confirmPassword=''
      this.changePasswordDialogVisible=false
    },
    openChangePassword(){
      this.changePasswordDialogVisible=true
    },
    changePassword()  {
      this.$refs.pass.validate((valid)=>{
        if(valid){
          //表单合法 {
          if(this.ChangePasswordForm.newPassword !== this.ChangePasswordForm.confirmPassword){
            this.$message.error("两次输入的新密码不同")
            return false
          }
          this.request.post("/user/password",this.ChangePasswordForm).then(res => {
            if(res.code === '200'){
              this.$message.success("修改成功,请重新登陆")
              this.closeChangePassword()
              localStorage.removeItem("user")
              this.user={}
              this.showLoginDialog()
              }
            else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    }
  },
  created() {
    this.ChangePasswordForm.username=this.user.username
  }


};

</script>

<style scoped>
.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
}
.login-item {
  color: #fff;
  font-size: 16px;
  margin-right: 20px;
  cursor: pointer; /* 添加手型光标以指示可点击 */
}
/* 在激活状态下应用不同的背景颜色 */
.login-item.is-active {
  background-color: #07030a;
  color: #f6f2e3;
}
.logo {
  display: flex;
  align-items: center;
  font-size: 24px;
  color: #333;
}

.logo img {
  width: 40px;
  height: 40px;
  margin-right: 10px;
}

.site-title {
  color: #cccccc;
  font-weight: bold;
  font-style: italic;
}

/* 设置导航菜单的背景颜色为灰色 */
.nav-menu {
  background-color: #2B2E2FFF;
}

/* 设置导航菜单项的文本颜色为白色 */
.nav-menu .el-menu-item {
  color: #fff;
}

/* 设置导航菜单项的激活颜色为白色 */
.nav-menu .el-menu-item.is-active {
  background-color: #07030a; /* 设置激活项的背景颜色为深灰色 */
  color: #f6f2e3; /* 设置激活项的文本颜色为白色 */
}

.user-section {
  display: flex;
  align-items: center;
}


.login-button {
  font-size: 16px; /* 设置字体大小 */
  margin-right: 10px; /* 调整右侧间距 */
}

/* 调整更多选项按钮的间距 */
.el-dropdown-link {
  margin-right: 20px; /* 调整更多选项按钮的右侧间距 */
}
.user-nickname{
  margin-left: 5px;
  color: #cccccc;
  font-weight: bold;
  font-size: 15px;
}
.user-avatar{
  width: 30px;
  border-radius: 50%;
  position: relative;
  bottom: 2px;
  right: 5px
}
</style>