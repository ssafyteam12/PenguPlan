<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { planStore, attractionStore } from "@/store/store";
import { getUserTripById } from "@/api/Plan/Plan";
import { Attraction } from "@/type/type";
import Button from "@/components/ui/button/Button.vue";
import NoImage from "@/assets/image/no-image.png";

const router = useRouter();
const route = useRoute();
const plans = ref<Attraction>([]);
const data = ref<Attraction[]>([]);
const name = ref("");
const aStore = attractionStore();

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
  name.value = tmp.content;

  const filtered = data.value.filter((item: { day: any }) => {
    const day = item.day;
    addAttraction(day, item);
  });

  aStore.setAttractionList(data.value);
});

const handleClick = (index: number) => {
  aStore.setLat(data.value[index].latitude);
  aStore.setLng(data.value[index].longitude);
};
</script>

<template>
  <div class="h-full overflow-y-auto p-6 bg-gray-50 relative">
    <div class="flex justify-between gap-5 text-2xl font-bold my-5">
      <Button
        variant="outline"
        @click="router.push(`/editplan/${route.params.tripId}`)"
      >
        수정하기
      </Button>
      {{ name }}

      <Button variant="link" @click="router.push('/mypage')">
        마이페이지
      </Button>
    </div>

    <div v-for="day in Object.keys(plans)" :key="day" class="mb-8 last:mb-0">
      <!-- Day Header -->
      <div class="flex items-center gap-3 mb-4">
        <div class="flex items-center gap-2">
          <h3 class="text-xl font-bold">Day {{ day }}</h3>
          <span class="text-sm text-gray-500">
            {{ plans[day].length }}개의 장소
          </span>
        </div>
        <div class="h-px bg-gray-200 flex-1"></div>
      </div>

      <!-- Attractions List -->
      <div class="grid gap-4">
        <div
          v-for="(attraction, index) in plans[day]"
          :key="attraction.no"
          class="bg-gray-50 rounded-lg p-4 flex gap-4 hover:bg-gray-100 transition-colors"
          @click="
            handleClick(
              (day - 1) * plans[Object.keys(plans)[day - 1]].length + index
            )
          "
        >
          <img
            :src="attraction.image"
            @error="(e) => ((e.target as HTMLImageElement).src = NoImage)"
            :alt="attraction.title"
            class="w-32 h-24 object-cover rounded-lg flex-shrink-0"
          />

          <div class="flex-1">
            <div class="flex justify-between items-start mb-2">
              <h4 class="text-lg font-medium">
                {{ attraction.sequence }}. {{ attraction.title }}
              </h4>
              <span class="bg-blue-100 text-blue-800 px-2 py-1 rounded text-sm">
                {{ attraction.sequence }}번째
              </span>
            </div>
            <p class="text-gray-600">{{ attraction.address }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
