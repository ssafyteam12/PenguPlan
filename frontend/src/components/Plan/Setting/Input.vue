<script setup lang="ts">
import { ref, Ref, defineEmits } from "vue";
import { Button } from "@/components/ui/button";
import CitySelection from "./CitySelection.vue";
import CategorySelection from "./CategorySelection.vue";
import { RangeCalendar } from "@/components/ui/range-calendar";
import type { DateRange } from "radix-vue";
import { getLocalTimeZone, today } from "@internationalized/date";
import { planStore } from "@/store/store";
import { dayStore } from "@/store/store";
import { DateTime } from "luxon";

const emit = defineEmits(["submitForm"]);

const pStore = planStore();
const dStore = dayStore();
import { storeToRefs } from "pinia";
const { content, sidoCode } = storeToRefs(pStore);

const { totalDay } = storeToRefs(dStore);
const currentStep = ref(0);
const selectedCity = ref(1);
const selectedCategory = ref(1);
const start = today(getLocalTimeZone());
const end = start.add({ days: 7 });

const selectedDate = ref({
  start,
  end,
}) as Ref<DateRange>;

const getDateDifference = () => {
  if (!selectedDate.value.start || !selectedDate.value.end) {
    return 0;
  }

  const start = DateTime.local(
    selectedDate.value.start.year,
    selectedDate.value.start.month,
    selectedDate.value.start.day
  );

  const end = DateTime.local(
    selectedDate.value.end.year,
    selectedDate.value.end.month,
    selectedDate.value.end.day
  );

  return end.diff(start, "days").days;
};

const formatDate = (date) => {
  if (!date) return "";
  const year = date.year;
  const month = String(date.month).padStart(2, "0");
  const day = String(date.day).padStart(2, "0");
  return `${year}-${month}-${day}`;
};

const nextStep = () => {
  if (currentStep.value < 2) {
    currentStep.value++;
  }
};

const submitCity = (id: number) => {
  selectedCity.value = id;
  nextStep();
};

const submitCategory = (id: number) => {
  selectedCategory.value = id;
  nextStep();
};

const onSubmit = () => {
  pStore.setContent(selectedCategory.value);
  pStore.setSidoCode(selectedCity.value);
  pStore.setDate(
    formatDate(selectedDate.value.start),
    formatDate(selectedDate.value.end)
  );
  dStore.setTotalDay(getDateDifference() + 1);
  emit("submitForm");
};
</script>

<template>
  <div
    class="relative w-full mx-auto bg-white rounded-lg overflow-hidden font-main"
  >
    <div
      class="flex transition-transform duration-500 ease-in-out"
      :style="{ transform: `translateX(-${currentStep * 100}%)` }"
    >
      <!-- Step 1: 도시 선택 -->
      <div class="w-[80%] mx-[10%] flex-shrink-0">
        <CitySelection @next="submitCity" />
      </div>

      <!-- Step 2: 카테고리 선택 -->
      <div class="w-[80%] mx-[10%] flex-shrink-0">
        <CategorySelection @next="submitCategory" />
      </div>

      <!-- Step 3: 날짜 선택 -->
      <div class="w-[80%] mx-[10%] flex-shrink-0 p-6">
        <h2 class="text-2xl font-semibold text-gray-800 mb-6">
          언제 떠나시나요?
        </h2>

        <div class="flex justify-center">
          <RangeCalendar v-model="selectedDate" class="rounded-md border" />
        </div>
        <button
          @click="onSubmit"
          class="mt-6 w-full py-3 rounded-lg bg-main-color hover:bg-hover-color text-white text-sm font-semibold hover:opacity-90 transition-opacity duration-300"
        >
          제출
        </button>
      </div>
    </div>
  </div>
</template>
