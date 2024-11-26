<script setup lang="ts">
import { ref } from "vue";
import { useRouter } from "vue-router";
import { Button } from "@/components/ui/button";
import { RouterLink } from "vue-router";
import { userStore } from "@/store/userStore";
import { storeToRefs } from "pinia";
import { toast } from "vue-sonner";

const router = useRouter();
const store = userStore();
const { isLoggedIn } = storeToRefs(store);

const isMenuOpen = ref(false);
function toggleMenu() {
  isMenuOpen.value = !isMenuOpen.value;
}

const handleLogout = () => {
  if (!confirm("로그아웃 하시겠습니까?")) {
    return;
  }
  localStorage.removeItem("accessToken");
  store.logoutSetting();

  toast("로그아웃 되었습니다.");
  router.push("/");
};
</script>

<template>
  <header
    class="flex items-center justify-between px-4 py-3 shadow-md z-50 sticky top-0 bg-white w-full"
  >
    <img
      src="/src/assets/image/penguin.png"
      alt="SSAFY PLAN"
      class="w-8 h-8 mr-2"
    />

    <router-link to="/">
      <div class="font-bold text-xl text-point-color">PENGU PLAN</div>
    </router-link>
    <nav class="hidden md:flex space-x-6 ml-6 items-center">
      <router-link to="/trips">
        <Button variant="link" class="text-base"> 둘러보기 </Button>
      </router-link>
      <div class="text-dark-color text-lg">|</div>
      <router-link to="/plan">
        <Button variant="link" class="text-base"> 여행하기 </Button>
      </router-link>
    </nav>
    <nav class="flex items-center md:justify-end md:grow">
      <router-link to="/login" v-if="!isLoggedIn">
        <Button variant="link" class="text-sm px-3 font-bold">
          로그인 / 회원가입</Button
        >
      </router-link>
      <router-link to="/mypage" v-if="isLoggedIn">
        <Button variant="link" class="text-sm px-3 font-bold">
          마이페이지</Button
        >
      </router-link>
      <Button
        v-if="isLoggedIn"
        @click="handleLogout"
        variant="link"
        class="text-sm px-3 font-bold"
      >
        로그아웃</Button
      >

      <button
        class="inline-flex items-center md:hidden px-2 text-point-color"
        @click="toggleMenu"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
          class="w-6 h-6"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M4 6h16M4 12h16m-7 6h7"
          />
        </svg>
      </button>
    </nav>
  </header>
  <div v-if="isMenuOpen" class="md:hidden bg-white shadow-md">
    <nav class="flex flex-col space-y-3 p-4">
      <router-link to="/">
        <Button variant="link" class="w-full text-base"> 둘러보기 </Button>
      </router-link>
      <router-link to="/plan">
        <Button variant="link" class="w-full text-base"> 여행하기 </Button>
      </router-link>
      <router-link to="/">
        <Button variant="link" class="w-full text-base"> 공지사항 </Button>
      </router-link>
    </nav>
  </div>
</template>

<style scoped></style>
