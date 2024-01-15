import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
// import Login from '../views/login.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/newpage',
    name:'newpage',
    component: () => import('../views/Newpage.vue')
  },
  {
    path: '/search',
    name:'search',
    component: () => import('../views/SearchView.vue')
  },
  {
    path: '/dataset',
    name:'dataset',
    component: () => import('../views/DatasetView.vue')
  },
  {
    path: '/signup',
    name:'signup',
    component: () => import('../views/Signup.vue')
  },
  {
    path: '/login',
    name:'login',
    component: () => import('../views/login.vue')
  },
  {
    path: '/test',
    name:'test',
    component: () => import('../views/zty_test.vue')
  },
  {
    path: '/signup',
    name:'singnup',
    component: () => import('../views/Signup.vue')
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
