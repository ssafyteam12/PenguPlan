<script setup>
import { ref, onMounted } from "vue";
import { storeToRefs } from "pinia";
import { useRoute } from "vue-router";
import PlanMain from "../../components/Plan/Setting/PlanMain.vue";
import { planStore } from "@/store/store";

import { getUserTripById } from "@/api/Plan/plan";

const route = useRoute();
const pStore = planStore();
const { plans } = storeToRefs(pStore);
const planList = ref([]);

// 값 세팅해서 이동
onMounted(async () => {
  pStore.initiate();
  planList.value = await getUserTripById(route.params.tripId);

  const simplifiedArray = planList.value.attractions.map((attraction) => ({
    no: attraction.no,
    title: attraction.title,
    day: attraction.day,
    sequence: attraction.sequence,
  }));

  simplifiedArray.forEach((item) => {
    pStore.addAttraction(item.day, item);
  });
});
</script>

<template>
  <div class="min-h-screen bg-white">
    <div class="h-screen-minus-50 relative">
      <TransitionRoot
        appear
        enter="transition-all duration-300"
        enter-from="opacity-0 translate-y-4"
        enter-to="opacity-100 translate-y-0"
        leave="transition-all duration-300"
        leave-from="opacity-100 translate-y-0"
        leave-to="opacity-0 translate-y-4"
      >
        <div class="w-full h-full flex">
          <PlanMain :isEdit="true" />
        </div>
      </TransitionRoot>
    </div>
  </div>
</template>

<style scoped></style>
