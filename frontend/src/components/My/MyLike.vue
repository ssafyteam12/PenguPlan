<script setup>
import { onMounted, ref } from "vue";
import { getMyLike } from "@/api/User/My";
import { Card, CardContent } from "@/components/ui/card";
import NoImage from "@/assets/image/no-image.png";
import { Heart } from "lucide-vue-next";

const data = ref({});
onMounted(async () => {
  // const returned = await getMyLike();
  data.value = await getMyLike();
  // data.value = returned.likedAttractions;
});
</script>

<template>
  <div class="space-y-6">
    <div class="flex items-center justify-between mb-6">
      <h2 class="text-2xl font-semibold text-gray-900">관심 여행지</h2>
      <div class="flex items-center space-x-2 text-rose-500">
        <Heart class="h-5 w-5 fill-current" />
        <span class="font-medium">{{ data.totalLikes || 0 }}</span>
      </div>
    </div>

    <div
      v-if="data.totalLikes"
      class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6"
    >
      <Card
        v-for="like in data.likedAttractions"
        :key="like.id"
        class="overflow-hidden hover:shadow-lg transition-shadow"
      >
        <img
          :src="like.firstImage1 || NoImage"
          :alt="like.title"
          class="h-48 w-full object-cover"
        />
        <CardContent class="p-4">
          <h3 class="font-semibold text-lg mb-2">{{ like.title }}</h3>
          <p class="text-gray-500 text-sm">{{ like.addr1 }}</p>
        </CardContent>
      </Card>
    </div>

    <div v-else class="text-center py-12 bg-gray-50 rounded-lg">
      <Heart class="h-12 w-12 mx-auto text-gray-400 mb-4" />
      <h3 class="text-lg font-medium text-gray-900 mb-2">
        관심 여행지가 없습니다
      </h3>
      <p class="text-gray-500">마음에 드는 여행지를 찾아보세요!</p>
    </div>
  </div>
</template>
