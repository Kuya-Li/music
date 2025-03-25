import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('../views/Manage'),
    redirect: "/index",
    children: [
      {
        path: "/index" ,
        name:"Index",
        component: () => import('../views/index')
      },
      {
        path: "/singer",
        name:"Singer",
        component:() => import('../views/singer')
      },
      {
        path: "/musiclib",
        name: "Musiclib",
        component:() => import('../views/Musiclib')
      },
      {
        path: "/rank",
        name: "Rank",
        component:() => import('../views/rank')
      },
      {
        path: "/person",
        name: "Person",
        component:() => import('../views/Person')
      },
      {
        path: "/music/:musicId",
        name: "music",
        component:() => import('../views/Music')
      },
      {
        path: "/songList",
        name: "songList",
        component: () => import('../views/songList')
      },
      {
        path: "/myMusic",
        name: "myMusic",
        component: () => import('../views/myMusic')
      },
      {
        path: "/singerDetail/:artistId",
        component:() => import('../views/SingerDetail')
      }
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})




export default router
