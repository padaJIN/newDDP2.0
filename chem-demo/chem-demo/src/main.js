import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import {VueLatex} from "vatex";

createApp(App).use(store).use(router).use(ElementPlus).use(mavonEditor).use(VueLatex).mount('#app')

