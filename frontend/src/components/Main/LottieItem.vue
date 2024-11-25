<script setup lang="ts">
import { defineEmits } from "vue";
import { LottieAnimation } from "lottie-web-vue";
import { defineProps } from "vue";
import { Button } from "@/components/ui/button";

const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
  even: {
    type: Boolean,
    default: false,
  },
});
defineEmits(["click"]);
</script>

<template>
  <div class="w-full flex justify-center h-screen-minus-50 py-16">
    <div
      class="flex flex-col md:flex-row items-center gap-12 md:gap-16"
      :class="{
        'md:flex-row-reverse': props.even,
      }"
    >
      <div class="w-full md:w-1/2 flex justify-center">
        <div class="relative w-64 h-64 lg:w-96 lg:h-96">
          <div
            class="absolute inset-0 bg-gradient-to-r from-blue-400 to-teal-300 rounded-full opacity-10 blur-2xl"
          ></div>
          <LottieAnimation
            :animation-data="props.data.lottie"
            :auto-play="true"
            :loop="true"
            :speed="1"
            class="relative z-10"
          />
        </div>
      </div>

      <div class="w-full md:w-1/2 space-y-6">
        <h2
          class="text-3xl lg:text-4xl font-bold bg-gradient-to-r from-blue-600 to-teal-400 text-transparent bg-clip-text"
          v-html="props.data.title"
        ></h2>

        <p
          class="text-lg text-gray-600 leading-relaxed"
          v-html="props.data.content"
        ></p>

        <Button
          @click="$emit('click', data.category)"
          class="mt-6 bg-main-color hover:bg-hover-color text-white shadow-lg hover:shadow-xl transition-all duration-300 transform hover:-translate-y-0.5"
          v-html="props.data.buttonText"
        ></Button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
