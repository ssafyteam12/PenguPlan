<script setup>
import { defineProps, onMounted } from "vue";
import KakaoMap from "@/components/Plan/Option/KakaoMap.vue";
import Option from "@/components/Plan/Option/Option.vue";
import Search from "@/components/Plan/Option/Search.vue";
import PlanSideBar from "@/components/Plan/MyPlan/PlanSideBar.vue";
import { planStore } from "@/store/store";

const pStore = planStore();

const props = defineProps({
  isEdit: Boolean,
});

onMounted(() => {
  if (!props.isEdit) {
    pStore.initiate();
    pStore.setKeyword("");
  }
});
</script>

<template>
  <div class="flex w-full h-full">
    <div class="relative flex-grow w-2/3 h-full border-r border-gray-200">
      <KakaoMap class="w-full h-full" />
      <PlanSideBar :isEdit="isEdit" />
    </div>

    <div class="w-1/3 h-full flex flex-col bg-white">
      <div class="p-4 border-b border-gray-100">
        <Search />
      </div>

      <div class="flex-1 h-full overflow-hidden">
        <div class="overflow-y-auto h-full custom-scrollbar">
          <Option />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.custom-scrollbar {
  scrollbar-width: thin;
  scrollbar-color: #e2e8f0 #f8fafc;
}

.custom-scrollbar::-webkit-scrollbar {
  width: 6px;
}

.custom-scrollbar::-webkit-scrollbar-track {
  background: #f8fafc;
}

.custom-scrollbar::-webkit-scrollbar-thumb {
  background-color: #e2e8f0;
  border-radius: 3px;
}
</style>
