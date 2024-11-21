import { createApp } from "vue";
import "./assets/index.css";
import App from "./App.vue";
import router from "./router/router";
import Vue3Lottie from "vue3-lottie";
import { createPinia } from "pinia";
import piniaPluginPersistedState from "pinia-plugin-persistedstate";

const app = createApp(App);
const pinia = createPinia();

pinia.use(piniaPluginPersistedState);
app.use(pinia);
app.use(Vue3Lottie, { name: "LottieAnimation" });
app.use(router);
app.mount("#app");
