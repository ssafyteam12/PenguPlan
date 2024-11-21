<script setup lang="ts">
import { useKakao } from "vue3-kakao-maps/@utils";
import { Toaster } from "vue-sonner";

useKakao("dc8d614f13e8bcb345590c907dd601f5");
</script>

<template>
  <div class="font-main">
    <component :is="$route.meta.layout || 'div'">
      <Toaster />
      <router-view></router-view>
    </component>
  </div>
</template>

<style scoped></style>
