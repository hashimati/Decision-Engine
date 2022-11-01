import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/AboutView.vue"),
    },
    {
      path:"/contexts", 
      name:"contexts", 
      component:()=> import("../views/lists/ContextListView.vue"),
    }, 
    {
      path:"/context/:id", 
      name:"context", 
      component:()=> import("../views/Context.vue"),
    },
    {
      path:"/variables", 
      name:"variables", 
      component:()=> import("../views/lists/VariableListView.vue"),
    },
    {
      path:"/variable/:id", 
      name:"variable", 
      component:()=> import("../views/Variable.vue"),
    },
    {
      path:"/decisions", 
      name:"decisions", 
      component:()=> import("../views/lists/DecisionListView.vue"),
    },
    {
      path:"/decision/:id", 
      name:"decision", 
      component:()=> import("../views/Decision.vue"),
    },
  ],
});

export default router;
