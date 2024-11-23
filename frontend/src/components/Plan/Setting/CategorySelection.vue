<script setup>
import { ref, defineEmits } from "vue";
import { categoryList } from "./contenttype";
import { Button } from "@/components/ui/button";

defineEmits(["next"]);

const selectedCategory = ref(1);

const selectCategory = (id) => {
  selectedCategory.value = id;
};
</script>

<template>
  <div class="p-6 w-full max-w-3xl mx-auto">
    <h2 class="text-2xl font-semibold text-gray-800 mb-6">
      어떤 곳을 구경하고 싶으신가요?
    </h2>
    <div class="grid grid-cols-4 gap-2 mb-5">
      <button
        v-for="category in categoryList"
        :key="category.id"
        @click="selectCategory(category.id)"
        :class="[
          'group relative overflow-hidden rounded-lg transition-transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2',
          selectedCategory === category.id ? 'ring-2 ring-blue-500' : '',
          'h-24 w-24', // 버튼 크기 설정
        ]"
      >
        <div class="flex flex-col items-center gap-1">
          <div
            class="bg-base-color w-16 h-16 p-2 flex items-center justify-center rounded-full overflow-hidden"
          >
            <img
              :src="category.img"
              class="max-w-full max-h-full object-contain"
            />
          </div>
          <div class="text-sm text-center font-medium">
            {{ category.name }}
          </div>
        </div>
      </button>
    </div>
    <Button
      @click="$emit('next', selectedCategory)"
      class="mx-6 mb-6 w-[calc(100%-48px)] py-3 rounded-lg bg-gradient-to-r from-blue-500 to-blue-600 text-white text-sm font-semibold hover:opacity-90 transition-opacity duration-300"
    >
      다음
    </Button>
  </div>
</template>
