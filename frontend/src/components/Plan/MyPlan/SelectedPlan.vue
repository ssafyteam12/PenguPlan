<script setup>
import { computed, ref, watchEffect } from "vue";
import { dayStore, planStore } from "@/store/store";
import { storeToRefs } from "pinia";
import SelectedPlanItem from "./SelectedPlanItem.vue";
import SelectDate from "./SelectDate.vue";
import { ScrollArea } from "@/components/ui/scroll-area";
import Draggable from "vuedraggable/src/vuedraggable";

const store = dayStore();
const { selectedDay } = storeToRefs(store);
const pStore = planStore();

const localAttractionList = ref([]);

// plans의 변경을 감지하여 localAttractionList 업데이트
watchEffect(() => {
  localAttractionList.value = pStore.getAttraction(selectedDay.value) || [];
});

// 드래그 앤 드롭으로 순서가 변경될 때의 핸들러
const onSort = ({ moved }) => {
  const newList = [...localAttractionList.value];

  // 기존 아이템들을 모두 제거
  newList.forEach((attraction) => {
    pStore.removeAttraction(String(selectedDay.value), attraction.no);
  });

  // 새로운 순서로 아이템들을 다시 추가
  newList.forEach((attraction) => {
    pStore.addAttraction(String(selectedDay.value), {
      ...attraction,
      day: selectedDay.value,
    });
  });
};

const enabled = ref(true);

const dragOptions = {
  animation: 200,
  group: "description",
  disabled: false,
  ghostClass: "ghost",
};
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
      <Draggable
        v-model="localAttractionList"
        :disabled="!enabled"
        class="space-y-3 py-4"
        item-key="no"
        handle=".handle"
        @change="onSort"
      >
        <template #item="{ element, index }">
          <div class="relative handle cursor-move">
            <div
              v-if="index !== localAttractionList.length - 1"
              class="absolute left-[1.15rem] top-[2.5rem] w-0.5 h-[calc(100%-1rem)] bg-gray-200"
            />
            <SelectedPlanItem :attraction="element" :id="index + 1" />
          </div>
        </template>
      </Draggable>

      <div
        v-if="!localAttractionList.length"
        class="flex flex-col items-center justify-center py-10 text-gray-500"
      >
        <span class="text-sm">아직 선택된 여행지가 없습니다</span>
        <span class="text-xs mt-1">오른쪽 지도에서 여행지를 선택해주세요</span>
      </div>
    </ScrollArea>
  </div>
</template>

<style scoped>
.ghost {
  opacity: 0.5;
  background: #c8ebfb;
}

.cursor-move {
  cursor: move;
}

.sortable-drag {
  opacity: 0.5;
  background: #c8ebfb !important;
}

.sortable-ghost {
  opacity: 0.5;
  background: #f0f9ff !important;
}
</style>
