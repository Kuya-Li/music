import Vue from 'vue'
import Vuex from 'vuex'
import router from "@/router";

Vue.use(Vuex)

const store = new Vuex.Store({

  mutations: {
    logout(){
      // 退出后清空缓存
      localStorage.removeItem("user")
    }
  }
})

export default store
