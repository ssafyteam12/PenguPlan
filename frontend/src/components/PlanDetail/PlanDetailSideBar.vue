<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { planStore } from "@/store/store";
import { getUserTripById } from "@/api/Plan/Plan";
import { Attraction } from "@/type/type";
import Button from "@/components/ui/button/Button.vue";

const route = useRoute();
const plans = ref<Attraction>([]);
const data = ref<Attraction[]>([]);

const addAttraction = (day: string, attraction: Attraction) => {
  if (!plans.value[day]) {
    plans.value[day] = [];
  }
  if (!plans.value[day].find((att) => att.no === attraction.no)) {
    plans.value[day].push({
      ...attraction,
    });
  }
};

onMounted(async () => {
  const id = route.params.tripId;
  const tmp = await getUserTripById(id);
  data.value = tmp.attractions;

  const filtered = data.value.filter((item: { day: any }) => {
    const day = item.day;
    addAttraction(day, item);
  });
});
</script>

<template>
  <div class="h-full overflow-y-auto p-4 bg-gray-50 relative">
    <Button variant="outline">수정하기</Button>
    <div v-for="(attractions, day) in plans" :key="day" class="mb-6">
      <h2 class="text-lg font-bold mb-3">{{ day }} 일차</h2>

      <div class="relative">
        <div
          v-for="(attraction, index) in attractions"
          :key="index"
          class="flex items-center gap-4 mb-3 p-3 bg-white shadow rounded-lg relative"
        >
          <div
            class="bg-dark-color w-6 h-6 mr-3 flex items-center justify-center rounded-full text-white font-bold text-xs"
          >
            {{ attraction.sequence }}
          </div>

          <div class="flex flex-col justify-between flex-1">
            <div class="font-semibold text-gray-800 truncate">
              {{ attraction.title }}
            </div>
            <div class="text-sm text-gray-500 truncate">
              명소 {{ attraction.addr1 }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
