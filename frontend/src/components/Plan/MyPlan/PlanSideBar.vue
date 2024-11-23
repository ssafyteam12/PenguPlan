<script setup lang="ts">
import { ref, defineProps } from "vue";
import { useRoute, useRouter } from "vue-router";
import { planStore } from "@/store/store";
import { storeToRefs } from "pinia";
import SelectedPlan from "./SelectedPlan.vue";
import Button from "@/components/ui/button/Button.vue";
import { toPlainObject, extractNoAndTitle } from "@/util/util";
import { postUserTrip, putUserTrip } from "@/api/Plan/Plan";
import { toast } from "vue-sonner";
import { ChevronLeft, ChevronRight } from "lucide-vue-next";

defineProps({
  isEdit: Boolean,
});

const route = useRoute();
const router = useRouter();
const pStore = planStore();
const { plans, sidoCode, content, startDate, endDate } = storeToRefs(pStore);

const showSidebar = ref(false);
const tripTitle = ref("");

const toggleSidebar = () => {
  showSidebar.value = !showSidebar.value;
};

const updateContent = () => {
  content.value = tripTitle.value;
};

const handleSubmit = async () => {
  if (!tripTitle.value.trim()) {
    toast.error("여행 제목을 입력해주세요");
    return;
  }

  const sendAttraction = extractNoAndTitle(
    toPlainObject(Object.values(plans.value).flat())
  );

  const sendObject = {
    sidoCode: sidoCode.value,
    content: content.value,
    startDate: startDate.value,
    endDate: endDate.value,
    attractions: [...sendAttraction],
  };

  try {
    await postUserTrip(sendObject);
    toast("여행 일정 등록 성공!", {
      description: "마이페이지에서 일정을 확인하세요",
    });
    pStore.initiate();
    router.push("/mypage");
  } catch (error) {
    toast.error("여행 일정 등록 실패", {
      description: "다시 시도해주세요",
    });
  }
};

const handleEdit = async () => {
  if (!tripTitle.value.trim()) {
    toast.error("여행 제목을 입력해주세요");
    return;
  }

  const sendAttraction = extractNoAndTitle(
    toPlainObject(Object.values(plans.value).flat())
  );

  const sendObject = {
    sidoCode: sidoCode.value,
    content: content.value,
    startDate: startDate.value,
    endDate: endDate.value,
    attractions: [...sendAttraction],
  };

  try {
    await putUserTrip(route.params.tripId, sendObject);
    toast("여행 일정 수정 성공!", {
      description: "마이페이지에서 일정을 확인하세요",
    });
    pStore.initiate();
    router.push(`/myplan/${route.params.tripId}`);
  } catch (error) {
    toast.error("여행 일정 수정 실패", {
      description: "다시 시도해주세요",
    });
  }
};
</script>

<template>
  <div
    class="fixed top-[50px] left-0 h-screen-minus-50 transition-all duration-300 ease-in-out"
    :class="[
      showSidebar ? 'translate-x-0' : 'translate-x-[calc(-100%+2rem)]',
      'z-20',
    ]"
  >
    <!-- Toggle Button -->
    <button
      @click="toggleSidebar"
      class="absolute right-0 top-1/2 translate-x-full -translate-y-1/2 bg-white hover:bg-gray-50 rounded-r-lg p-2 shadow-lg border-t border-r border-b border-gray-200 transition-colors duration-200"
      :aria-label="showSidebar ? '사이드바 닫기' : '사이드바 열기'"
    >
      <component
        :is="showSidebar ? ChevronLeft : ChevronRight"
        class="w-4 h-4 text-gray-600"
      />
    </button>

    <!-- Main Sidebar Content -->
    <div
      class="w-[450px] h-full bg-white shadow-xl rounded-r-lg flex flex-col overflow-hidden"
    >
      <div class="p-4 border-b border-gray-100">
        <input
          v-model="tripTitle"
          type="text"
          placeholder="여행 일정의 제목을 입력하세요"
          class="w-full px-4 py-2 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-dark-color focus:border-transparent"
          @input="updateContent"
        />
      </div>

      <div class="flex-1 overflow-hidden">
        <SelectedPlan />
      </div>

      <!-- Save Button -->
      <div v-if="!isEdit" class="p-4 border-t border-gray-100 bg-white">
        <Button
          @click="handleSubmit"
          :disabled="!tripTitle.trim()"
          class="w-full py-3 bg-dark-color hover:bg-dark-color/90 text-white font-medium rounded-lg transition-colors duration-200 flex items-center justify-center gap-2 disabled:opacity-50 disabled:cursor-not-allowed"
        >
          일정 저장하기
        </Button>
      </div>

      <div v-if="isEdit" class="p-4 border-t border-gray-100 bg-white">
        <Button
          @click="handleEdit"
          :disabled="!tripTitle.trim()"
          class="w-full py-3 bg-dark-color hover:bg-dark-color/90 text-white font-medium rounded-lg transition-colors duration-200 flex items-center justify-center gap-2 disabled:opacity-50 disabled:cursor-not-allowed"
        >
          일정 수정하기
        </Button>
      </div>
    </div>
  </div>
</template>
