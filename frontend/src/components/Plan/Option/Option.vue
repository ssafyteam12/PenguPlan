<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import { attractionStore, planStore } from "@/store/store";
import { storeToRefs } from "pinia";
import { ScrollArea } from "@/components/ui/scroll-area";
import OptionItem from "./OptionItem.vue";
import { Input } from "@/components/ui/input";
import { getAttractionBySidoGugun } from "@/api/Attraction/Attraction";
import { Skeleton } from "@/components/ui/skeleton";

const aStore = attractionStore();
const pStore = planStore();
const { attractionList } = storeToRefs(aStore);
const { sidoCode, content } = storeToRefs(pStore);
const isLoading = ref(true);
const searchQuery = ref("");

// 초기 리스트 로딩
onMounted(async () => {
  try {
    const returnAttractions = await getAttractionBySidoGugun(
      sidoCode.value,
      "",
      content.value,
      ""
    );
    aStore.setAttractionList(returnAttractions.attractions);
  } finally {
    isLoading.value = false;
  }
});

const filteredAttractions = computed(() => {
  if (!searchQuery.value) return attractionList.value;
  return attractionList.value.filter(
    (attraction) =>
      attraction.title
        .toLowerCase()
        .includes(searchQuery.value.toLowerCase()) ||
      attraction.addr1.toLowerCase().includes(searchQuery.value.toLowerCase())
  );
});
</script>

<template>
  <div class="flex flex-col gap-4 p-4 bg-gray-50 h-full">
    <!-- Search Bar -->
    <div class="sticky top-0 z-10 bg-white shadow-sm rounded-lg p-4">
      <Input
        v-model="searchQuery"
        placeholder="결과 내 검색 ... "
        class="w-full transition-all duration-300 focus:ring-2 focus:ring-blue-500"
      />
    </div>

    <!-- Loading State -->
    <div v-if="isLoading" class="space-y-4">
      <div
        v-for="n in 5"
        :key="n"
        class="bg-white rounded-lg p-4 animate-pulse"
      >
        <div class="flex items-center space-x-4">
          <Skeleton class="h-16 w-16 rounded-md" />
          <div class="space-y-2 flex-1">
            <Skeleton class="h-4 w-3/4" />
            <Skeleton class="h-3 w-1/2" />
          </div>
        </div>
      </div>
    </div>

    <!-- Content -->
    <ScrollArea v-else class="flex-grow rounded-lg h-full">
      <div class="space-y-3 p-2">
        <TransitionGroup name="list" tag="div" class="space-y-3">
          <div
            v-for="attraction in filteredAttractions"
            :key="attraction.no"
            class="transform transition-all duration-300 hover:scale-[1.02]"
          >
            <OptionItem :attraction="attraction" />
          </div>
        </TransitionGroup>
      </div>
    </ScrollArea>
  </div>
</template>

<style scoped>
.list-enter-active,
.list-leave-active {
  transition: all 0.5s ease;
}
.list-enter-from,
.list-leave-to {
  opacity: 0;
  transform: translateX(-30px);
}
</style>
