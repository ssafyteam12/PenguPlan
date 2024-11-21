<script setup>
import { ref } from "vue";
import { dayStore } from "@/store/store";
import { storeToRefs } from "pinia";

const store = dayStore();
const { selectedDay, totalDay } = storeToRefs(store);
</script>

<template>
  <div class="p-4 border-b border-gray-100">
    <h3 class="text-lg font-semibold text-gray-800 mb-3">여행 일정</h3>
    <div class="grid grid-cols-3 gap-2">
      <button
        v-for="day in totalDay"
        :key="day"
        @click="store.selectDay(day)"
        class="py-2 px-3 rounded-lg text-sm font-medium transition-all duration-200 hover:scale-105 transform"
        :class="[
          selectedDay === day
            ? 'bg-dark-color text-white shadow-md'
            : 'bg-gray-100 text-gray-600 hover:bg-gray-200',
        ]"
      >
        {{ day }}일차
      </button>
    </div>
  </div>
</template>
