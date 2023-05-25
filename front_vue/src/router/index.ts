import { createRouter, createWebHistory } from 'vue-router'
import ListTestView from "@/views/ListTestView.vue";
import HomeView from "@/views/HomeView.vue";
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

  ]
})

export default router
