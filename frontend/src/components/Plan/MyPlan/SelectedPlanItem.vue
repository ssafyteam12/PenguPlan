<script setup>
import { defineProps } from "vue";
import { dayStore, planStore } from "@/store/store";
import { storeToRefs } from "pinia";
import { Trash2 } from "lucide-vue-next";

const dStore = dayStore();
const pStore = planStore();
const { selectedDay } = storeToRefs(dStore);

const props = defineProps({
  attraction: Object,
  id: Number,
});

const deleteAttraction = (attractionNo) => {
  pStore.removeAttraction(selectedDay.value, attractionNo);
};
</script>

<template>
  <div
    class="group relative flex items-start gap-3 p-4 bg-white rounded-lg border border-gray-100 hover:border-gray-200 shadow-sm hover:shadow-md transition-all duration-200"
  >
    <!-- Number Badge -->
    <div
      class="w-7 h-7 flex items-center justify-center bg-dark-color text-white font-semibold text-sm rounded-full shadow-sm"
    >
      {{ id }}
    </div>

    <!-- Content -->
    <div class="flex-1 min-w-0">
      <h4 class="font-medium text-gray-900 truncate">
        {{ attraction.title }}
      </h4>
      <p class="mt-1 text-sm text-gray-500 truncate">
        {{ attraction.addr1 }}
      </p>
    </div>

    <!-- Delete Button -->
    <button
      @click="deleteAttraction(attraction.no)"
      class="opacity-0 group-hover:opacity-100 p-1.5 rounded-md text-gray-400 hover:text-red-500 hover:bg-red-50 transition-all duration-200"
      aria-label="일정 삭제"
    >
      <Trash2 class="w-4 h-4" />
    </button>
  </div>
</template>
