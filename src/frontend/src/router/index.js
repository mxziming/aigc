import Vue from 'vue'
import VueRouter from 'vue-router'
// import HomeView from '../views/HomeView.vue'
import Layout from '@/layout'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: () => import( '../views/登录注册/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import( '../views/登录注册/Register.vue')
  },
  /////////////////////////////////////////////////////////////
  {
    path:'/profile',
    component:Layout,
    children:[
      {
        path: 'editProfile',
        name: 'editProfile',
        component: () => import( '../views/个人主页/editProfile.vue')
      },
      {
        path: 'info',
        name: 'profile',
        component: () => import('../views/个人主页/Profile.vue')
      },
    ]
  },
  ///////////////////////////////////////////////////////
  {
    path:'/relative',
    component:Layout,
    children:[
      {
        path: 'info',
        name: 'relative',
        component: () => import( '../views/亲人信息/relative.vue')
      },
      {
        path: 'editRelative',
        name: 'editRelative',
        component: () => import( '../views/亲人信息/editRelative.vue')
      },
      {
        path: 'addRelative',
        name: 'addRelative',
        component: () => import( '../views/亲人信息/addRelative.vue')
      }
    ]
  },
/////////////////////////////////////////////////////////////////
  {
    path:'/source',
    component:Layout,
    children:[
      {
        path: 'picture',
        name: 'picture',
        component: () => import('../views/资源展示/picture.vue')
      },
      {
        path: 'video',
        name: 'video',
        component: () => import('../views/资源展示/video.vue')
      },
      {
        path: 'addPicture',
        name: 'addPicture',
        component: () => import( '../views/资源展示/addPicture.vue')
      },
      {
        path: 'addVideo',
        name: 'addVideo',
        component: () => import( '../views/资源展示/addVideo.vue')
      },
      {
        path: 'editPicture',
        name: 'editPicture',
        component: () => import( '../views/资源展示/editPicture.vue')
      },
      {
        path: 'editVideo',
        name: 'editVideo',
        component: () => import( '../views/资源展示/editVideo.vue')
      },
    ]
  },
///////////////////////////////////////////////////////////////////
{
  path:'/ai',
  component:Layout,
  children:[
    {
      path: 'chat',
      name: 'chat',
      component: () => import( '../views/与AI聊天/chat.vue')
    }
  ]
},
///////////////////////////////////////////////////////////////////
{
  path:'/health',
  component:Layout,
  children:[
    {
      path: '',   
      name: 'chat',
      component: () => import( '../views/养生知识/health.vue')
    }
  ]
},
///////////////////////////////////////////////////////////////////
{
  path:'/todo',
  component:Layout,
  children:[
    {
      path: 'list',   
      name: 'list',
      component: () => import( '../views/语音记事本/todolist.vue')
    },
    {
      path: 'add',   
      name: 'add',
      component: () => import( '../views/语音记事本/AddTask.vue')
    },
    {
      path: 'edit',   
      name: 'edit',
      component: () => import( '../views/语音记事本/EditTask.vue')
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
