<script setup lang="ts">
import { defineProps, ref, onMounted, reactive, watch } from "vue";
import { dayStore, planStore, attractionStore } from "@/store/store";
import { storeToRefs } from "pinia";
import Button from "@/components/ui/button/Button.vue";
import { Badge } from "@/components/ui/badge";
import {
  postAttractionLike,
  getAttractionDetail,
} from "@/api/Attraction/Attraction";
import { Heart, Plus, MapPin, Star } from "lucide-vue-next";
import FullHeart from "@/assets/image/full-heart.png";
import EmptyHeart from "@/assets/image/empty_heart.png";
import OptionDialog from "./OptionDialog.vue";

import NoImage from "@/assets/image/no-image.png";

const dStore = dayStore();
const pStore = planStore();
const aStore = attractionStore();
const { selectedDay } = storeToRefs(dStore);

const props = defineProps({
  attraction: {
    type: Object,
    required: true,
  },
});

const localAttraction = reactive({ ...props.attraction });
const showDetail = ref<boolean>(false);
const isHovered = ref(false);

watch(
  () => props.attraction,
  (newAttraction) => {
    Object.assign(localAttraction, newAttraction);
  }
);

// 관광지 추가
const addAttraction = (attraction) => {
  pStore.addAttraction(selectedDay.value, attraction);
};

const handleClick = (attraction) => {
  aStore.setLat(attraction.latitude);
  aStore.setLng(attraction.longitude);
};

const toggleHeart = async (no: number) => {
  const data = await postAttractionLike(no);
  localAttraction.islike = !localAttraction.islike;
};
</script>

<template>
  <div
    @click="handleClick(attraction)"
    @mouseenter="isHovered = true"
    @mouseleave="isHovered = false"
    class="bg-white rounded-lg shadow-sm hover:shadow-md transition-all duration-300 overflow-hidden group"
  >
    <div class="flex items-center p-4 gap-4">
      <!-- Image with overlay on hover -->
      <div class="relative flex-shrink-0">
        <img
          :src="attraction.firstImage1 || NoImage"
          alt="Thumbnail"
          class="w-20 h-20 object-cover rounded-lg transition-transform duration-300 group-hover:scale-105"
        />
        <div
          v-if="isHovered"
          class="absolute inset-0 bg-black bg-opacity-20 rounded-lg transition-opacity duration-300"
        ></div>
      </div>

      <!-- Content -->
      <div class="flex-grow">
        <div class="flex justify-between items-start">
          <h3
            @click="showDetail = true"
            class="text-lg font-semibold text-gray-800 hover:text-hover-color transition-colors duration-200 cursor-pointer"
          >
            {{ attraction.title }}
          </h3>

          <div class="flex items-center gap-2">
            <button
              @click.stop="toggleHeart(attraction.no)"
              class="p-1.5 rounded-full hover:bg-gray-100 transition-colors duration-200"
            >
              <Heart
                :class="
                  localAttraction.islike
                    ? 'fill-red-500 text-red-500'
                    : 'text-gray-400'
                "
                class="w-5 h-5"
              />
            </button>

            <Button
              @click.stop="addAttraction(attraction)"
              variant="ghost"
              size="icon"
              class="rounded-full hover:bg-blue-50 hover:text-blue-600 transition-all duration-200"
            >
              <Plus class="w-5 h-5" />
            </Button>
          </div>
        </div>

        <div class="flex items-center gap-2 mt-1 text-sm text-gray-600">
          <MapPin class="w-4 h-4" />
          <span>{{ attraction.addr1 }}</span>
        </div>

        <div class="flex items-center gap-2 mt-2">
          <Badge variant="secondary" class="flex items-center gap-1">
            <Star class="w-3 h-3 text-yellow-500" />
            {{ attraction.avgLank || "평가없음" }}
          </Badge>
        </div>
      </div>
    </div>
  </div>

  <OptionDialog
    v-if="showDetail"
    :attractionNo="attraction.no"
    @close="showDetail = false"
  />
</template>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
