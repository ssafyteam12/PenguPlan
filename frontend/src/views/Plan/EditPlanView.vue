<script setup>
import { ref, onMounted } from "vue";
import { storeToRefs } from "pinia";
import { useRoute } from "vue-router";
import PlanMain from "../../components/Plan/Setting/PlanMain.vue";
import { planStore } from "@/store/store";

import { getUserTripById } from "@/api/Plan/plan";

const route = useRoute();
const pStore = planStore();
const planList = ref([]);

// 값 세팅해서 이동
onMounted(async () => {
  pStore.initiate();
  planList.value = await getUserTripById(route.params.tripId);

  pStore.setTitleContent(planList.value.content);

  // const simplifiedArray = planList.value.attractions.map((attraction) => ({
  //   no: attraction.no,
  //   title: attraction.title,
  //   day: attraction.day,
  //   sequence: attraction.sequence,
  // }));

  planList.value.attractions.forEach((item) => {
    pStore.addAttraction(item.day, item);
  });
});
</script>

<template>
  <div class="min-h-screen bg-white">
    <div class="h-screen-minus-50 relative">
      <div class="w-full h-full flex">
        <PlanMain :isEdit="true" />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
