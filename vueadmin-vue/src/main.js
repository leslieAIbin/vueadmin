import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import axios from './axios.js'
import gobal from "./globalFun"

Vue.config.productionTip = false

Vue.prototype.$axios = axios //
require("./mock")  //引入mock数据，关闭则注释该行


Vue.use(Element)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
