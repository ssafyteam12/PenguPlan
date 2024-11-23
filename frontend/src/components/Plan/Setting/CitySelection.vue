<script setup>
import { ref, defineEmits, watch } from "vue";
import { sidoList } from "./location";
import { Button } from "@/components/ui/button";

const selectedCity = ref(1);

const selectCity = (id) => {
  selectedCity.value = id;
};

defineEmits(["next"]);
</script>

<template>
  <div class="p-6 w-full max-w-3xl mx-auto">
    <h2 class="text-2xl font-semibold text-gray-800 mb-6">
      어디로 떠나시나요?
    </h2>
    <div class="grid grid-cols-4 gap-4">
      <button
        v-for="city in sidoList"
        :key="city.id"
        @click="selectCity(city.id)"
        :class="[
          'group relative overflow-hidden rounded-lg transition-transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2',
          selectedCity === city.id ? 'ring-2 ring-blue-500' : '',
        ]"
      >
        <div class="flex flex-col gap-3">
          <div class="bg-base-color p-2">
            <img :src="city.img" />
          </div>
          <div>
            {{ city.name }}
          </div>
        </div>
      </button>
    </div>
  </div>
  <Button
    @click="$emit('next', selectedCity)"
    class="mx-6 mb-6 w-[calc(100%-48px)] py-3 rounded-lg bg-gradient-to-r from-blue-500 to-blue-600 text-white text-sm font-semibold hover:opacity-90 transition-opacity duration-300"
  >
    다음
  </Button>
</template>
