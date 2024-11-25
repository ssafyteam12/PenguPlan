import {
  createRouter,
  createWebHistory,
  createMemoryHistory,
} from "vue-router";
import { userStore } from "@/store/userStore";
import { storeToRefs } from "pinia";
import Layout from "@/components/layout/Layout.vue";
import MainLayout from "../components/layout/MainLayout.vue";
import MainView from "@/views/MainView.vue";
import LoginView from "@/views/User/LoginView.vue";
import PlanView from "@/views/Plan/PlanView.vue";
import EditPlanView from "@/views/Plan/EditPlanView.vue";
import MyPageView from "@/views/My/MyPageView.vue";
import PlanDetailView from "@/views/PlanDetail/PlanDetailView.vue";
import TripsView from "@/views/Trips/Trips.vue";

const routes = [
  {
    path: "/",
    component: MainView,
    meta: { layout: MainLayout },
  },
  {
    path: "/home",
    component: MainView,
    meta: { layout: Layout },
  },
  {
    path: "/login",
    component: LoginView,
    meta: { layout: Layout },
  },
  {
    path: "/plan",
    component: PlanView,
    meta: { layout: Layout, requiresAuth: true },
  },
  {
    path: "/trips",
    component: TripsView,
    meta: { layout: Layout, requiresAuth: true },
  },
  {
    path: "/mypage",
    component: MyPageView,
    meta: { layout: Layout, requiresAuth: true },
  },
  {
    path: "/myplan/:tripId",
    component: PlanDetailView,
    meta: { layout: Layout, requiresAuth: true },
  },
  {
    path: "/editplan/:tripId",
    component: EditPlanView,
    meta: { layout: Layout, requiresAuth: true },
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  const uStore = userStore();
  const { isLoggedIn } = storeToRefs(uStore);

  if (to.meta.requiresAuth && !isLoggedIn.value) {
    next({ path: "/login" });
  } else {
    next();
  }
});

export default router;
