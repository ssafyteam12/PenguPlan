<!-- Modal.vue -->
<script setup>
import { onMounted, defineProps, ref, watch } from "vue";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/components/ui/carousel";
import Button from "@/components/ui/button/Button.vue";
import { Skeleton } from "@/components/ui/skeleton";
import { Map, Phone, Navigation, Star } from "lucide-vue-next";
import {
  postAttractionLike,
  getAttractionDetail,
  postAttractionReview,
  getAttractionNear,
} from "@/api/Attraction/Attraction";
import NoImage from "@/assets/image/no-image.png";
import OptionDialogNear from "./OptionDialogNear.vue";

const props = defineProps({
  attractionNo: Number,
});

const attraction = ref({});
const nearAttraction = ref([]);
const isOpen = ref(true);
const isLoading = ref(true);

const fetchAttractionData = async () => {
  isLoading.value = true;
  attraction.value = await getAttractionDetail(props.attractionNo);
  const near = await getAttractionNear(props.attractionNo);
  nearAttraction.value = near.nearbyAttractions;
  isLoading.value = false;
};

onMounted(fetchAttractionData);

const handleAttractionClick = async (selectedAttraction) => {
  isLoading.value = true;

  // 선택된 관광지 정보 불러오기
  attraction.value = await getAttractionDetail(selectedAttraction.no);
  const near = await getAttractionNear(selectedAttraction.no);
  nearAttraction.value = near.nearbyAttractions;

  isLoading.value = false;
};


const addReview = async () => {
  const send = {
    attractionNo: props.attractionNo,
    userId: "10",
    rating: 5,
    content:
      "정말 좋은 관광지였습니다. 특히 경치가 훌륭했고 시설도 잘 되어있었습니다.",
  };
  await postAttractionReview(send, props.attractionNo);
};
</script>

<template>
  <Dialog v-if="isOpen" open>
    <DialogTitle>상세정보</DialogTitle>
    <DialogContent v-if="isLoading" class="max-w-4xl">
      <div class="space-y-4">
        <Skeleton class="h-8 w-3/4" />
        <Skeleton class="h-[300px] w-full" />
        <Skeleton class="h-4 w-full" />
        <Skeleton class="h-4 w-2/3" />
      </div>
    </DialogContent>

    <DialogContent
      v-if="!isLoading"
      class="max-w-5xl max-h-[90vh] overflow-auto"
    >
      <DialogHeader>
        <DialogTitle class="text-3xl font-bold text-gray-800 mb-6">
          {{ attraction.attraction.title }}
        </DialogTitle>

        <DialogDescription class="space-y-8">
          <!-- Main Content Section -->
          <div class="grid md:grid-cols-2 gap-8">
            <!-- Image Section -->
            <div class="relative group">
              <img
                :src="attraction.attraction.firstImage1 || NoImage"
                class="w-full h-[400px] object-cover rounded-lg shadow-lg"
                alt="Attraction"
              />
              <div
                class="absolute inset-0 bg-black bg-opacity-40 opacity-0 group-hover:opacity-100 transition-opacity duration-300 rounded-lg"
              ></div>
            </div>

            <!-- Description Section -->
            <div class="space-y-6">
              <p class="text-lg leading-relaxed text-gray-600">
                {{ attraction.aiDescription }}
              </p>

              <!-- Contact Info -->
              <div class="space-y-4 bg-gray-50 p-6 rounded-lg">
                <div class="flex items-center gap-3">
                  <Phone class="h-5 w-5 text-gray-600" />
                  <p class="text-gray-700">
                    {{ attraction.attraction.tel || "정보없음" }}
                  </p>
                </div>
                <div class="flex items-center gap-3">
                  <Map class="h-5 w-5 text-gray-600" />
                  <p class="text-gray-700">{{ attraction.attraction.addr1 }}</p>
                </div>
              </div>
            </div>
          </div>

          <!-- Nearby Attractions Section -->
          <div class="mt-12 bg-gray-50 p-8 rounded-xl">
            <h2 class="text-2xl font-semibold text-center mb-8 text-gray-800">
              근처의 추천 관광지
            </h2>
            <div class="relative">
              <Carousel class="max-w-4xl mx-auto">
                <CarouselContent>
                  <CarouselItem
                    v-for="nAttraction in nearAttraction"
                    :key="nAttraction.no"
                    class="md:basis-1/2 lg:basis-1/3"
                  >
                  <OptionDialogNear
                      :attraction="nAttraction"
                      @click="handleAttractionClick(nAttraction)"
                      />
                  </CarouselItem>
                  
                </CarouselContent>
              </Carousel>
            </div>
          </div>
        </DialogDescription>
      </DialogHeader>

      <DialogFooter class="mt-8">
        <Button
          variant="outline"
          @click="isOpen = !isOpen"
          class="px-6 py-2 text-base"
        >
          닫기
        </Button>
      </DialogFooter>
    </DialogContent>
    
  </Dialog>
</template>
