<script setup>
import { ref } from "vue";
import Welcome from "@/components/Main/Welcome.vue";
import Info from "@/components/Main/Info.vue";
import { Button } from "@/components/ui/button";
</script>

<template>
  <div class="relative">
    <Welcome @scrollToInfo="scrollToInfo" />
    <Info />
  </div>
</template>

<style scoped></style>
