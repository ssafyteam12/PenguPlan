<script setup lang="ts">
import { defineProps, ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { LottieAnimation } from "lottie-web-vue";
import PlanJSON from "@/assets/lottie/Plan.json";
import LottieItem from "@/components/Main/LottieItem.vue";
import { infoList } from "@/components/Main/info";
import { emitKeypressEvents } from "readline";

const router = useRouter();

const move = (category: string) => {
  router.push(`/${category}`);
};
</script>

<template>
  <div id="info-id" class="flex flex-col w-full rounded-tr-3xl">
    <div
      v-for="info in infoList"
      :key="info.id"
      class="w-full py-14 from-blue-600 to-teal-400"
    >
      <LottieItem :data="info" :even="info.id % 2 === 0" @click="move" />
    </div>
  </div>
</template>
