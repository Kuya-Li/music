import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store"

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/404',
    name: 'NotFound',
    component: () => import('../views/404.vue')
  },
  {
    path: '/show',
    name: 'show',
    component: () => import('../views/show')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 提供一个重置路由的方法
export const resetRouter = () => {
  router.matcher = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
  })
}

// 注意：刷新页面会导致页面路由重置
export const setRoutes = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  if (user && user.token && user.menus) {
    const storeMenus = localStorage.getItem("menus");
    if (storeMenus) {
      // 获取当前的路由对象名称数组
      const currentRouteNames = router.getRoutes().map(v => v.name)
      if (!currentRouteNames.includes('Manage')) {
        // 拼装动态路由
        const manageRoute = { path: '/', name: 'Manage', component: () => import('../views/Manage/Manage.vue'), redirect: "/home", children: [
            { path: 'person', name: '个人信息', component: () => import('../views/Manage/Person.vue')},
            { path: 'password', name: '修改密码', component: () => import('../views/Manage/Password.vue')}
          ] }
        const menus = JSON.parse(storeMenus)
        menus.forEach(item => {
          if (item.path) {  // 当且仅当path不为空的时候才去设置路由
            let itemMenu = { path: item.path.replace("/", ""), name: item.name, component: () => import('../views/Manage/' + item.pagePath + '.vue')}
            manageRoute.children.push(itemMenu)
          } else if(item.children.length) {
            item.children.forEach(item => {
              if (item.path) {
                let itemMenu = { path: item.path.replace("/", ""), name: item.name, component: () => import('../views/Manage/' + item.pagePath + '.vue')}
                manageRoute.children.push(itemMenu)
              }
            })
          }
        })
        // 动态添加到现在的路由对象中去
        router.addRoute(manageRoute)
      }
    }
  }
}


// 重置我就再set一次路由
setRoutes()


//导航守卫

router.beforeEach((to, from, next) => {
  const user = localStorage.getItem("user");

  // 判断用户是否未登录
  if (!user && to.path !== "/login" && to.path !=="/register") {
    next("/login");
  } else {
    // 判断是否未找到路由
    if (!to.matched.length) {
      const storeMenus = localStorage.getItem("menus");
      if (storeMenus) {
        next("/404");
      } else {
        // 跳回登录页面
        next("/login");
      }
    } else {
      localStorage.setItem("currentPathName",to.name)
      store.commit("setPath")
      // 其他情况都放行
      next();
    }
  }
});

export default router
