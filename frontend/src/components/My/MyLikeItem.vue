<script setup>
import { defineProps, ref, watch } from "vue";
import { getMyLike } from "@/api/User/My";
import { getAttractionDetail } from "@/api/Attraction/Attraction";
import { Card, CardContent, CardTitle } from "@/components/ui/card";
import NoImage from "@/assets/image/no-image.png";
import OptionDialog from "../Plan/Option/OptionDialog.vue";

defineProps({
  item: Object,
});

const showDetail = ref(false);
</script>

<template>
  <div>
    <Card
      class="overflow-hidden hover:shadow-lg transition-shadow"
      @click="showDetail = true"
    >
      <img
        :src="item.firstImage1 || NoImage"
        :alt="item.title"
        class="h-48 w-full object-cover"
      />
      <CardContent class="p-4">
        <h3 class="font-semibold text-lg mb-2">{{ item.title }}</h3>
        <p class="text-gray-500 text-sm">{{ item.addr1 }}</p>
      </CardContent>
      <OptionDialog
        v-if="showDetail"
        :attractionNo="item.no"
        @close="showDetail = false"
      />
    </Card>
  </div>
</template>

<style scoped></style>
