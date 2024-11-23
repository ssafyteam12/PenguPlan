<script setup lang="ts">
import { DefineProps } from "vue";
import NoImage from "@/assets/image/no-image.png";
import { Navigation, ArrowRight } from "lucide-vue-next";

defineProps({
  attraction: Object,
});
const emit = defineEmits(["click"]);

</script>

<template>
  <div class="group cursor-pointer" @click="$emit('click', attraction)">
    <div
      class="bg-white rounded-xl overflow-hidden shadow-lg hover:shadow-xl transition-all duration-300 transform hover:-translate-y-1"
    >
      <div class="relative h-56">
        <img
          :src="attraction.firstImage1 || NoImage"
          :alt="attraction?.title"
          class="w-full h-full object-cover transform transition-transform duration-700 group-hover:scale-110"
        />
        <div
          class="absolute inset-0 bg-gradient-to-t from-black/70 via-black/20 to-transparent"
        ></div>

        <!-- Distance Badge -->
        <div
          class="absolute top-4 right-4 bg-white/90 backdrop-blur-sm px-3 py-1.5 rounded-full"
        >
          <div class="flex items-center gap-1.5">
            <Navigation class="h-4 w-4 text-blue-600" />
            <span class="text-sm font-medium text-gray-700">5km</span>
          </div>
        </div>
      </div>

      <div class="p-5">
        <h3
          class="text-lg font-semibold text-gray-800 line-clamp-1 mb-3 group-hover:text-blue-600 transition-colors"
        >
          {{ attraction?.title }}
        </h3>

        <!-- Action Button -->
        <div class="flex items-center justify-between mt-4" >
          <button
            class="flex items-center gap-2 text-sm text-blue-600 font-medium group-hover:gap-3 transition-all"
          >
            자세히 보기
            <ArrowRight class="h-4 w-4" />
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.line-clamp-1 {
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>

