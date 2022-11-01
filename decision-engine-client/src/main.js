import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";

// import "./assets/main.css";
import "./assets/bootstrap.min.css";
import "./assets/dashboard.css";
import "./assets/dashboard.js";
import "./assets/services/ContextService.js";
import "./assets/bootstrap.bundle.min.js";
import "axios/dist/axios.js";
import "./assets/services/ContextService.js";
import "feather-icons";
const app = createApp(App);

app.use(createPinia());
app.use(router);

app.mount("#app");
