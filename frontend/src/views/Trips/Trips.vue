<template>
  <div class="max-w-6xl mx-auto p-4">
    <div class="flex justify-between items-center mb-8">
      <h1 class="text-3xl font-bold animate-fadeIn">공개된 여행 일정</h1>
      <div class="flex gap-3">
        <select
          v-model="sort"
          class="px-4 py-2 border rounded-lg bg-white shadow-sm hover:shadow transition-all"
        >
          <option value="copied_count">복사 횟수순</option>
          <option value="created_at">최신순</option>
        </select>
        <select
          v-model="size"
          class="px-4 py-2 border rounded-lg bg-white shadow-sm hover:shadow transition-all"
        >
          <option :value="9">small</option>
          <option :value="18">middle</option>
          <option :value="27">large</option>
        </select>
      </div>
    </div>
    <div class="grid grid-cols-2 lg:grid-cols-3 gap-6">
      <div
        v-for="trip in trips.sort((a, b) => a.tripId - b.tripId).slice(0, size)"
        :key="trip.tripId"
        class="bg-white rounded-xl shadow-sm hover:shadow-lg transition-all duration-300 transform hover:-translate-y-1"
      >
        <div class="p-5 space-y-4">
          <div class="flex justify-between items-start">
            <h3
              class="font-semibold text-xl hover:text-blue-600 transition-colors"
            >
              {{ trip.content }}
            </h3>
            <span
              class="bg-blue-100 text-blue-800 text-xs font-medium px-2.5 py-1.5 rounded-full"
            >
              복사 {{ trip.copiedCount || 0 }}회
            </span>
          </div>

          <p class="text-gray-600">{{ trip.startDate }} ~ {{ trip.endDate }}</p>

          <div class="space-y-4">
            <div
              v-for="attraction in trip.attractions
                .sort((a, b) => a.day - b.day || a.sequence - b.sequence)
                .slice(0, 1)"
              :key="attraction.no"
              class="group"
            >
              <div class="relative overflow-hidden rounded-lg">
                <img
                  :src="attraction.image"
                  @error="(e) => ((e.target as HTMLImageElement).src = NoImage)"
                  :alt="attraction.title"
                  class="w-full h-36 object-cover transform group-hover:scale-105 transition-transform duration-300"
                />
                <div class="absolute top-2 left-2">
                  <span
                    class="bg-black/70 text-white px-3 py-1 rounded-full text-sm"
                  >
                    Day {{ attraction.day }}
                  </span>
                </div>
                <div
                  class="absolute bottom-0 left-0 right-0 bg-gradient-to-t from-black/70 via-black/50 to-transparent p-3"
                >
                  <p class="text-white font-medium">
                    {{ attraction.sequence }}. {{ attraction.title }}
                  </p>
                  <p class="text-white/80 text-sm truncate">
                    {{ attraction.address }}
                  </p>
                </div>
              </div>
            </div>
          </div>

          <div class="pt-3 flex gap-2">
            <button
              @click="openTripDetail(trip.tripId)"
              class="flex-1 px-4 py-2.5 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors duration-200 text-sm font-medium"
            >
              상세보기
            </button>
            <button
              @click="handleCopyTrip(trip.tripId)"
              class="flex-1 px-4 py-2.5 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition-colors duration-200 text-sm font-medium"
            >
              일정 복사
            </button>
          </div>
        </div>
      </div>
    </div>

    <div class="mt-8 flex justify-center gap-2">
      <button
        v-for="pageNum in 5"
        :key="pageNum"
        @click="page = pageNum"
        :class="[
          'px-4 py-2 rounded-lg font-medium transition-all duration-200 hover:-translate-y-0.5',
          page === pageNum
            ? 'bg-blue-500 text-white shadow-md'
            : 'bg-white hover:bg-gray-50',
        ]"
      >
        {{ pageNum }}
      </button>
    </div>

    <!-- Modal -->
    <div
      v-if="selectedTrip"
      class="fixed inset-0 bg-black/50 backdrop-blur-sm flex items-center justify-center p-4 z-50"
      @click="selectedTrip = null"
    >
      <div
        class="bg-white rounded-xl w-full max-w-4xl max-h-[90vh] overflow-y-auto"
        @click.stop
      >
        <div
          class="sticky top-0 bg-white border-b p-4 flex justify-between items-center z-10"
        >
          <div>
            <h2 class="text-2xl font-bold">{{ selectedTrip.content }}</h2>
            <p class="text-gray-600">
              {{ selectedTrip.startDate }} ~ {{ selectedTrip.endDate }}
            </p>
          </div>
          <div class="flex gap-3 items-center">
            <button
              @click="handleCopyTrip(selectedTrip.tripId)"
              class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition-colors duration-200 text-sm font-medium"
            >
              일정 복사하기
            </button>
            <button
              @click="selectedTrip = null"
              class="p-2 hover:bg-gray-100 rounded-full transition-colors"
            >
              <span class="text-2xl">×</span>
            </button>
          </div>
        </div>

        <div class="p-6">
          <div
            v-for="day in getDays(selectedTrip)"
            :key="day"
            class="mb-8 last:mb-0"
          >
            <div class="flex items-center gap-3 mb-4">
              <div class="flex items-center gap-2">
                <h3 class="text-xl font-bold">Day {{ day }}</h3>
                <span class="text-sm text-gray-500"
                  >{{
                    selectedTrip.attractions.filter((a) => a.day === day)
                      .length
                  }}개의 장소</span
                >
              </div>
              <div class="h-px bg-gray-200 flex-1"></div>
            </div>

            <div class="grid gap-4">
              <div
                v-for="attraction in getDayAttractions(selectedTrip, day)"
                :key="attraction.no"
                class="bg-gray-50 rounded-lg p-4 flex gap-4 hover:bg-gray-100 transition-colors"
              >
                <img
                  :src="attraction.image"
                  @error="(e) => ((e.target as HTMLImageElement).src = NoImage)"
                  :alt="attraction.title"
                  class="w-48 h-32 object-cover rounded-lg flex-shrink-0"
                />

                <div class="flex-1">
                  <div class="flex justify-between items-start mb-2">
                    <h4 class="text-lg font-medium">
                      {{ attraction.sequence }}. {{ attraction.title }}
                    </h4>
                    <span
                      class="bg-blue-100 text-blue-800 px-2 py-1 rounded text-sm"
                    >
                      {{ attraction.sequence }}번째
                    </span>
                  </div>
                  <p class="text-gray-600">{{ attraction.address }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch } from "vue";
import { getPublicTrips, copyTrip, getUserTripById } from "@/api/Plan/Plan";
import NoImage from "@/assets/image/no-image.png";

interface Attraction {
  no: number;
  title: string;
  image: string;
  address: string;
  latitude: number;
  longitude: number;
  day: number;
  sequence: number;
}

interface Trip {
  tripId: number;
  userId: number;
  content: string;
  sidoCode: number;
  startDate: string;
  endDate: string;
  isPublic: boolean | null;
  copiedCount: number | null;
  attractions: Attraction[];
}

const trips = ref<Trip[]>([]);
const page = ref(1);
const size = ref(9);
const sort = ref("copied_count");
const selectedTrip = ref<Trip | null>(null);

const loadPublicTrips = async () => {
  const response = await getPublicTrips(page.value, size.value, sort.value);
  trips.value = response.trips;
};

const handleCopyTrip = async (tripId: number) => {
  try {
    await copyTrip(tripId);
    alert("여행 일정이 복사되었습니다!");
    selectedTrip.value = null; // 복사 후 모달 닫기
  } catch (error) {
    alert("복사 중 오류가 발생했습니다.");
  }
};

const openTripDetail = async (tripId: number) => {
  try {
    const tripDetail = await getUserTripById(tripId);
    selectedTrip.value = tripDetail;
  } catch (error) {
    alert("여행 정보를 불러오는데 실패했습니다.");
  }
};

const getDays = (trip: Trip): number[] => {
  return [...new Set(trip.attractions.map((a) => a.day))].sort((a, b) => a - b);
};

const getDayAttractions = (trip: Trip, day: number): Attraction[] => {
  return trip.attractions
    .filter((a) => a.day === day)
    .sort((a, b) => a.sequence - b.sequence);
};

watch([page, size, sort], () => {
  loadPublicTrips();
});

onMounted(() => {
  loadPublicTrips();
});
</script>

<style scoped>
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.animate-fadeIn {
  animation: fadeIn 0.6s ease-out;
}

.container {
  min-height: 100vh;
  background-color: #f8f9fa;
}
</style>
