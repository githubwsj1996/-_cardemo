import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import i18n from './lang/index'
import ECharts from 'vue-echarts'
import VueCookies from "vue-cookies"



axios.defaults.withCredentials=true
Vue.config.productionTip = false
Vue.use(ElementUI,{
  i18n:(key,value)=> i18n.t(key,value)
})
Vue.component('v-chart', ECharts)
Vue.use(VueCookies)
Vue.directive('title', {
    inserted: function (el, binding) {
        document.title = el.dataset.title
    }
})

new Vue({
  router,
  store,
  i18n,

  render: h => h(App)
}).$mount('#app')




router.beforeEach((to, from, next) => {
      if (to.meta.requireAuth) {
        if (store.state.user) {
          axios.get('/account/authentication').then(resp => {
            if (resp) next()
          })
        } else {
          next({
            path: 'login',
            query: {redirect: to.fullPath}
          })
        }
      } else {
        next()
      }
    }
)


