<script setup>
import { ref } from "vue";
import InputModel from "@/components/Plan/Setting/InputModel.vue";
import KakaoMap from "@/components/Plan/Option/KakaoMap.vue";
import Option from "@/components/Plan/Option/Option.vue";
import Search from "@/components/Plan/Option/Search.vue";
import PlanSideBar from "@/components/Plan/MyPlan/PlanSideBar.vue";
// import { TransitionRoot } from "@headlessui/vue";
import { MapPin, Calendar, Compass } from "lucide-vue-next";

const completedSetting = ref(false);
const toggleCompletedSetting = () => {
  completedSetting.value = true;
};
</script>

<template>
  <div class="min-h-screen bg-white">
    <div class="h-screen-minus-50 relative">
      <!-- Initial Planning Section -->
      <TransitionRoot
        appear
        :show="!completedSetting"
        enter="transition-opacity duration-300"
        enter-from="opacity-0"
        enter-to="opacity-100"
        leave="transition-opacity duration-300"
        leave-from="opacity-100"
        leave-to="opacity-0"
      >
        <div v-if="!completedSetting" class="container mx-auto px-4 py-8">
          <div class="max-w-5xl mx-auto">
            <!-- Header Section -->
            <div class="pt-[150px] text-center mb-12">
              <h1 class="text-3xl font-bold text-gray-800 mb-4">
                간편하게 여행을 계획해보세요!
              </h1>
              <p class="text-gray-600 text-lg">
                목적지와 일정을 선택하면 맞춤형 여행 계획을 만들어드립니다
              </p>
            </div>

            <!-- Feature Cards
            <div class="grid grid-cols-1 md:grid-cols-3 gap-8 mb-12">
              <div class="bg-gray-50 rounded-xl p-6 text-center shadow-sm">
                <div class="inline-block p-3 bg-blue-50 rounded-full mb-4">
                  <MapPin class="w-6 h-6 text-blue-600" />
                </div>
                <h3 class="font-semibold text-gray-800 mb-2">
                  맞춤 여행지 추천
                </h3>
                <p class="text-gray-600">
                  당신의 취향에 맞는 최적의 여행지를 추천해드립니다
                </p>
              </div>

              <div class="bg-gray-50 rounded-xl p-6 text-center shadow-sm">
                <div class="inline-block p-3 bg-blue-50 rounded-full mb-4">
                  <Calendar class="w-6 h-6 text-blue-600" />
                </div>
                <h3 class="font-semibold text-gray-800 mb-2">
                  스마트 일정 관리
                </h3>
                <p class="text-gray-600">
                  효율적인 동선과 시간 관리로 완벽한 일정을 계획하세요
                </p>
              </div>

              <div class="bg-gray-50 rounded-xl p-6 text-center shadow-sm">
                <div class="inline-block p-3 bg-blue-50 rounded-full mb-4">
                  <Compass class="w-6 h-6 text-blue-600" />
                </div>
                <h3 class="font-semibold text-gray-800 mb-2">현지 정보 제공</h3>
                <p class="text-gray-600">
                  관광지, 맛집, 숙소 등 필요한 모든 정보를 한 눈에
                </p>
              </div>
            </div> -->

            <!-- Input Form Section -->
            <div class="p-8 mb-8 flex justify-center items-center">
              <InputModel @complete="toggleCompletedSetting" />
            </div>

            <!-- Bottom Info Section -->
            <div class="text-center text-gray-600">
              <p>
                이미 계획된 여행이 있으신가요?
                <a href="#" class="text-blue-600 hover:underline"
                  >내 여행 보기</a
                >
              </p>
            </div>
          </div>
        </div>
      </TransitionRoot>

      <!-- Map and Planning Interface -->
      <TransitionRoot
        appear
        :show="completedSetting"
        enter="transition-all duration-300"
        enter-from="opacity-0 translate-y-4"
        enter-to="opacity-100 translate-y-0"
        leave="transition-all duration-300"
        leave-from="opacity-100 translate-y-0"
        leave-to="opacity-0 translate-y-4"
      >
        <div v-if="completedSetting" class="h-full flex">
          <!-- Left Section: Map and Sidebar -->
          <div class="relative flex-grow w-2/3 h-full border-r border-gray-200">
            <KakaoMap class="w-full h-full" />
            <PlanSideBar />
          </div>

          <!-- Right Section: Search and Options -->
          <div class="w-1/3 h-full flex flex-col bg-white">
            <!-- Search Bar -->
            <div class="p-4 border-b border-gray-100">
              <div class="relative w-full">
                <Search />
              </div>
            </div>

            <!-- Options List -->
            <div class="flex-1 overflow-hidden">
              <div class="h-full overflow-y-auto custom-scrollbar">
                <Option />
              </div>
            </div>
          </div>
        </div>
      </TransitionRoot>
    </div>
  </div>
</template>

<style scoped>
.h-screen-minus-50 {
  height: calc(100vh - 50px);
}

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

.transition-all {
  transition-property: all;
  transition-timing-function: cubic-bezier(0.4, 0, 0.2, 1);
}
</style>
