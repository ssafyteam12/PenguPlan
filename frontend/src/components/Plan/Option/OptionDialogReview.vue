<script setup>
import { ref, defineProps, computed } from "vue";
import { userStore } from "@/store/userStore";
import { storeToRefs } from "pinia";
import { postAttractionReview } from "@/api/Attraction/Attraction";

const props = defineProps({
  review: Array,
  attractionNo: Number,
});

const reviews = ref([...props.review]);

const uStore = userStore();
const { userName } = storeToRefs(uStore);

const rating = ref(0);
const hoveredRating = ref(0);
const content = ref("");
const isFocused = ref(false);

const isValidForm = computed(() => {
  return content.value.trim() && rating.value > 0;
});

const setRating = (value) => {
  rating.value = value;
};

const submitReview = async () => {
  if (!isValidForm.value) return;

  const response = await postAttractionReview({
    userName: userName.value,
    attractionNo: props.attractionNo,
    rating: rating.value,
    content: content.value,
  });

  reviews.value.unshift(response);

  rating.value = 0;
  content.value = "";
};
</script>

<template>
  <div>
    <div class="space-y-4 max-h-[500px] overflow-y-auto">
      <div v-if="reviews.length < 1" class="flex justify-center">
        <div class="text-lg">리뷰가 없습니다. 첫 리뷰를 작성해 보세요!</div>
      </div>
      <div
        v-for="rev in reviews"
        :key="rev.id"
        class="p-4 bg-white shadow-md rounded-lg"
      >
        <div class="flex justify-between items-center mb-3">
          <div class="flex items-center gap-2">
            <div
              class="w-10 h-10 bg-blue-500 rounded-full flex items-center justify-center text-white font-semibold"
            >
              {{ rev.userName.charAt(0) }}
            </div>
            <span class="text-lg font-medium text-gray-700">{{
              rev.userName
            }}</span>
          </div>
          <div class="flex items-center gap-1 text-yellow-500">
            <span class="font-semibold">{{ rev.rating }}</span>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              class="h-5 w-5"
              viewBox="0 0 20 20"
              fill="currentColor"
            >
              <path
                d="M9.049 2.927a1 1 0 011.902 0l1.4 4.304a1 1 0 00.95.69h4.516a1 1 0 01.6 1.8l-3.657 2.657a1 1 0 00-.364 1.118l1.4 4.304a1 1 0 01-1.451 1.118l-3.657-2.657a1 1 0 00-1.175 0l-3.657 2.657a1 1 0 01-1.451-1.118l1.4-4.304a1 1 0 00-.364-1.118L2.083 9.72a1 1 0 01.6-1.8h4.516a1 1 0 00.95-.69l1.4-4.304z"
              />
            </svg>
          </div>
        </div>

        <div class="text-gray-600">
          <p class="mb-2">{{ rev.content }}</p>
        </div>

        <div class="text-right text-sm text-gray-500">
          {{ rev.createdAt }}
        </div>
      </div>
    </div>

    <div class="mt-5 w-full mx-auto p-6 bg-white rounded-xl shadow-md">
      <div class="space-y-6">
        <h2 class="text-2xl font-semibold text-gray-800">리뷰 작성하기</h2>

        <!-- Rating Stars -->
        <div class="space-y-2">
          <label class="block text-sm font-medium text-gray-700">별점</label>
          <div class="flex items-center gap-1">
            <div v-for="star in 5" :key="star" class="relative">
              <button
                type="button"
                @click="setRating(star)"
                @mouseenter="hoveredRating = star"
                @mouseleave="hoveredRating = 0"
                class="focus:outline-none transition-transform hover:scale-110"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  :class="[
                    'w-8 h-8 transition-colors duration-200',
                    (hoveredRating ? star <= hoveredRating : star <= rating)
                      ? 'fill-yellow-400 text-yellow-400'
                      : 'fill-gray-200 text-gray-200',
                  ]"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    d="M11.48 3.499a.562.562 0 011.04 0l2.125 5.111a.563.563 0 00.475.345l5.518.442c.499.04.701.663.321.988l-4.204 3.602a.563.563 0 00-.182.557l1.285 5.385a.562.562 0 01-.84.61l-4.725-2.885a.563.563 0 00-.586 0L6.982 20.54a.562.562 0 01-.84-.61l1.285-5.386a.562.562 0 00-.182-.557l-4.204-3.602a.563.563 0 01.321-.988l5.518-.442a.563.563 0 00.475-.345L11.48 3.5z"
                  />
                </svg>
              </button>
            </div>
          </div>
        </div>

        <!-- Review Content -->
        <div class="space-y-2">
          <label class="block text-sm font-medium text-gray-700"
            >리뷰 내용</label
          >
          <div
            :class="[
              'relative rounded-lg transition-all duration-200',
              isFocused ? 'ring-2 ring-blue-500' : 'ring-1 ring-gray-200',
            ]"
          >
            <textarea
              v-model="content"
              @focus="isFocused = true"
              @blur="isFocused = false"
              placeholder="이 장소에 대한 솔직한 리뷰를 남겨주세요!"
              class="w-full h-24 p-4 rounded-lg bg-transparent placeholder-gray-400 text-gray-800 resize-none focus:outline-none"
            />
            <div class="absolute bottom-2 right-2 text-sm text-gray-400">
              {{ content.length }}/500
            </div>
          </div>
        </div>

        <!-- Submit Button -->
        <button
          @click="submitReview"
          :disabled="!isValidForm"
          :class="[
            'w-full py-3 px-4 rounded-lg font-medium transition-all duration-200',
            isValidForm
              ? 'bg-blue-500 hover:bg-blue-600 text-white'
              : 'bg-gray-100 text-gray-400 cursor-not-allowed',
          ]"
        >
          리뷰 등록하기
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
