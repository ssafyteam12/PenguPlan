<script setup>
import { ref, onMounted } from "vue";
import MyPlanItem from "./MyPlanItem.vue";
import { Button } from "@/components/ui/button";
import { useRouter } from "vue-router";
import { getUserTripAll } from "@/api/Plan/Plan";
import { Plus } from "lucide-vue-next";

const router = useRouter();
const returnTripsAll = ref({});

onMounted(async () => {
  returnTripsAll.value = await getUserTripAll();
});
</script>

<template>
  <div class="space-y-6">
    <div class="flex items-center justify-between mb-6">
      <h2 class="text-2xl font-semibold text-gray-900">나의 여행 일정</h2>
      <Button @click="router.push('/plan')" class="flex items-center">
        <Plus class="h-4 w-4 mr-2" />
        새 여행 계획
      </Button>
    </div>

    <div v-if="returnTripsAll.trips?.length" class="grid gap-6">
      <MyPlanItem
        v-for="trip in returnTripsAll.trips"
        :key="trip.tripId"
        :trip="trip"
      />
    </div>

    <div v-else class="text-center py-12 bg-gray-50 rounded-lg">
      <h3 class="text-lg font-medium text-gray-900 mb-2">
        아직 계획된 여행이 없습니다
      </h3>
      <p class="text-gray-500 mb-6">새로운 여행을 계획해보세요!</p>
      <Button @click="router.push('/plan')">
        <Plus class="h-4 w-4 mr-2" />
        여행 계획하기
      </Button>
    </div>
  </div>
</template>
