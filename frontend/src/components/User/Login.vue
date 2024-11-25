<script setup lang="ts">
import { ref, reactive } from "vue";
import { useRouter } from "vue-router";
import { Button } from "@/components/ui/button";
import { register, login } from "@/api/User/user";
import { toPlainObject } from "@/util/util";
import { userStore } from "@/store/userStore";
import { toast } from "vue-sonner";

const router = useRouter();
const store = userStore();
const isLogin = ref(true);
const showPassword = ref(false);
const error = ref("");

const loginForm = reactive({
  username: "",
  password: "",
});

const registerForm = reactive({
  username: "",
  email: "",
  password: "",
});

const handleLogin = async () => {
  try {
    const userData = await login(toPlainObject(loginForm));

    store.loginSetting(userData);
    router.push("/");
  } catch (error) {
    toast.error("아이디와 비밀번호를 확인해주세요.");
  }
};

const handleRegister = async () => {
  await register(toPlainObject(registerForm));
  router.push("/");
};
</script>

<template>
  <div
    class="flex-col gap-7 my-10 bg-white rounded-lg shadow-lg overflow-hidden w-[400px] mx-auto"
  >
    <div class="text-3xl">PENGU TRIP</div>
    <div class="p-10">
      <!-- Login Form -->
      <form v-if="isLogin" @submit.prevent="handleLogin" class="space-y-5">
        <div>
          <label class="text-sm font-medium text-gray-700">아이디</label>
          <input
            v-model="loginForm.username"
            type="text"
            class="mt-1 w-full px-4 py-3 text-sm rounded-lg border border-gray-300 focus:ring-2 focus:ring-main-color focus:border-transparent transition-all duration-300"
            placeholder="아이디를 입력하세요"
            required
          />
        </div>

        <div>
          <label class="text-sm font-medium text-gray-700">비밀번호</label>
          <div class="relative">
            <input
              v-model="loginForm.password"
              :type="showPassword ? 'text' : 'password'"
              class="mt-1 w-full px-4 py-3 text-sm rounded-lg border border-gray-300 focus:ring-2 focus:ring-main-color focus:border-transparent transition-all duration-300"
              placeholder="비밀번호를 입력하세요."
              required
            />
            <button
              @click="showPassword = !showPassword"
              type="button"
              class="absolute right-3 top-1/2 -translate-y-1/2 text-gray-500 hover:text-gray-700"
            >
              {{ showPassword ? "🙈" : "👀" }}
            </button>
          </div>
        </div>

        <Button type="submit" class="w-full bg-main-color hover:bg-hover-color">
          로그인
        </Button>
        <div
          class="text-xs mt-5 flex justify-center gap-5 decoration-gray-400 text-gray-400"
        >
          <span>아이디가 없으신가요? </span>
          <span
            @click="isLogin = false"
            class="underline underline-offset-[3px] cursor-pointer"
            >회원가입</span
          >
        </div>
      </form>

      <!-- Register Form -->
      <form v-else @submit.prevent="handleRegister" class="space-y-5">
        <div>
          <label class="text-sm font-medium text-gray-700">아이디</label>
          <input
            v-model="registerForm.username"
            type="text"
            class="mt-1 w-full px-4 py-3 text-sm rounded-lg border border-gray-300 focus:ring-2 focus:ring-main-color focus:border-transparent transition-all duration-300"
            placeholder="아이디를 입력하세요."
            required
          />
        </div>

        <div>
          <label class="text-sm font-medium text-gray-700">이메일</label>
          <input
            v-model="registerForm.email"
            type="email"
            class="mt-1 w-full px-4 py-3 text-sm rounded-lg border border-gray-300 focus:ring-2 focus:ring-main-color focus:border-transparent transition-all duration-300"
            placeholder="이메일을 입력하세요."
            required
          />
        </div>

        <div>
          <label class="text-sm font-medium text-gray-700">비밀번호</label>
          <div class="relative">
            <input
              v-model="registerForm.password"
              :type="showPassword ? 'text' : 'password'"
              class="mt-1 w-full px-4 py-3 text-sm rounded-lg border border-gray-300 focus:ring-2 focus:ring-main-color focus:border-transparent transition-all duration-300"
              placeholder="비밀번호를 입력하세요."
              required
            />
            <button
              @click="showPassword = !showPassword"
              type="button"
              class="absolute right-3 top-1/2 -translate-y-1/2 text-gray-500 hover:text-gray-700"
            >
              {{ showPassword ? "🙈" : "👀" }}
            </button>
          </div>
        </div>

        <Button type="submit" class="w-full bg-main-color hover:bg-hover-color">
          회원가입
        </Button>
        <div
          class="text-xs mt-5 flex justify-center gap-5 decoration-gray-400 text-gray-400"
        >
          <span>이미 회원이신가요? </span>
          <span
            @click="isLogin = true"
            class="underline underline-offset-[3px] cursor-pointer"
            >로그인</span
          >
        </div>
      </form>

      <!-- Error Message -->
      <div
        v-if="error"
        class="mt-5 p-3 rounded-lg bg-red-100 text-red-700 text-sm"
      >
        {{ error }}
      </div>
    </div>
  </div>
</template>
