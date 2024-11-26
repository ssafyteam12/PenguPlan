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
    class="flex items-center justify-between px-4 py-3 z-50 sticky top-0 bg-transparent backdrop-blur-sm w-full"
  >
    <img
      src="/src/assets/image/penguin.png"
      alt="SSAFY TRIP"
      class="w-8 h-8 mr-2"
    />
    <router-link to="/">
      <div class="font-bold text-xl text-dark-color drop-shadow-md">
        PENGU PLAN
      </div>
    </router-link>

    <nav class="flex items-center md:justify-end md:grow">
      <router-link to="/login" v-if="!isLoggedIn">
        <Button
          variant="link"
          class="text-sm px-3 font-bold text-black drop-shadow-md"
        >
          로그인 / 회원가입</Button
        >
      </router-link>
      <router-link to="/mypage" v-if="isLoggedIn">
        <Button
          variant="link"
          class="text-sm px-3 font-bold text-black drop-shadow-md"
        >
          마이페이지</Button
        >
      </router-link>
      <Button
        v-if="isLoggedIn"
        @click="handleLogout"
        variant="link"
        class="text-sm px-3 font-bold text-black drop-shadow-md"
      >
        로그아웃</Button
      >

      <button
        class="inline-flex items-center md:hidden px-2 text-black"
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
  <div v-if="isMenuOpen" class="md:hidden bg-white/30 backdrop-blur-sm">
    <nav class="flex flex-col space-y-3 p-4">
      <router-link to="/">
        <Button
          variant="link"
          class="w-full text-base text-white drop-shadow-md"
        >
          둘러보기
        </Button>
      </router-link>
    </nav>
  </div>
</template>
