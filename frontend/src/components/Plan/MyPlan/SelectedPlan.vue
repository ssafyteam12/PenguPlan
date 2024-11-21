<script setup>
import { computed, watch, ref, watchEffect } from "vue";
import { dayStore, planStore } from "@/store/store";
import { storeToRefs } from "pinia";
import SelectedPlanItem from "./SelectedPlanItem.vue";
import SelectDate from "./SelectDate.vue";
import { ScrollArea } from "@/components/ui/scroll-area";

const store = dayStore();
const { selectedDay } = storeToRefs(store);

const pStore = planStore();
const attractionList = ref([]);

watchEffect(() => {
  attractionList.value = [...pStore.getAttraction(selectedDay.value)];
});
</script>

<template>
  <div class="h-full flex flex-col">
    <SelectDate />

    <div class="px-4 py-3 border-b border-gray-100 bg-gray-50">
      <span class="text-sm font-medium text-gray-600">
        {{ selectedDay }}일차 여행 일정
      </span>
    </div>

    <ScrollArea class="flex-1 px-4">
      <div class="space-y-3 py-4">
        <div
          v-for="(attraction, index) in attractionList"
          :key="attraction.no"
          class="relative"
        >
          <div
            v-if="index !== attractionList.length - 1"
            class="absolute left-[1.15rem] top-[2.5rem] w-0.5 h-[calc(100%-1rem)] bg-gray-200"
          />
          <SelectedPlanItem :attraction="attraction" :id="index + 1" />
        </div>
      </div>

      <div
        v-if="!attractionList.length"
        class="flex flex-col items-center justify-center py-10 text-gray-500"
      >
        <span class="text-sm">아직 선택된 여행지가 없습니다</span>
        <span class="text-xs mt-1">왼쪽 지도에서 여행지를 선택해주세요</span>
      </div>
    </ScrollArea>
  </div>
</template>
