import { createRouter, createWebHistory } from 'vue-router'
import ListTestView from "@/views/ListTestView.vue";
import HomeView from "@/views/HomeView.vue";
import UserRegistration from "@/views/UserRegistration.vue";
import UserLogin from "@/views/UserLogin.vue";
//import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/public',
      name: 'public',
      component: ListTestView
    },
    {
      path: '/registration',
      name: 'registration',
      component: UserRegistration
    },
    {
      path: '/login',
      name: 'login',
      component: UserLogin
    },

  ]
})

export default router
