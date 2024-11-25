<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import InputModel from "@/components/Plan/Setting/InputModel.vue";
import PlanMain from "../../components/Plan/Setting/PlanMain.vue";
import { TransitionRoot } from "@headlessui/vue";

const router = useRouter();
const completedSetting = ref(false);
const toggleCompletedSetting = () => {
  completedSetting.value = true;
};
</script>

<template>
  <div class="min-h-screen bg-white">
    <div class="h-screen-minus-50 relative">
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
            <div class="pt-[150px] text-center mb-12">
              <h1 class="text-3xl font-bold text-gray-800 mb-4">
                간편하게 여행을 계획해보세요!
              </h1>
              <p class="text-gray-600 text-lg">
                목적지와 일정을 선택하면 맞춤형 여행 계획을 만들어드립니다
              </p>
            </div>

            <div class="p-8 mb-8 flex justify-center items-center">
              <InputModel @complete="toggleCompletedSetting" />
            </div>

            <div class="text-center text-gray-600">
              <p>
                이미 계획된 여행이 있으신가요?
                <a
                  @click="router.push('/mypage')"
                  class="text-blue-600 hover:underline cursor-pointer"
                  >내 여행 보기</a
                >
              </p>
            </div>
          </div>
        </div>
      </TransitionRoot>

      <div v-if="completedSetting" class="h-full flex">
        <PlanMain :isEdit="false" />
      </div>
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
