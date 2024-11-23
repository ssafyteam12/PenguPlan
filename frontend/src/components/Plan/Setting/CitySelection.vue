<script setup>
import { ref } from "vue";
import { sidoList } from "./location";
const selectedCity = ref("");

const selectCity = (cityId) => {
  selectedCity.value = cityId;
};

defineEmits(["update:modelValue"]);
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
          <div class="bg-base-color">
            <img :src="city.img" />
          </div>
          <div>
            {{ city.name }}
          </div>
        </div>
      </button>
    </div>
  </div>
</template>
