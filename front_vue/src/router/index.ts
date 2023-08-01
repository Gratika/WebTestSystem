import { createRouter, createWebHistory } from 'vue-router';
// @ts-ignore
import ListTestView from "@/views/ListTestView.vue";
// @ts-ignore
import HomeView from "@/views/HomeView.vue";
// @ts-ignore
import UserRegistration from "@/views/UserRegistration.vue";
// @ts-ignore
import UserLogin from "@/views/UserLogin.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/testList',
      name: 'testList',
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
    }
  ]
});

export default router;
