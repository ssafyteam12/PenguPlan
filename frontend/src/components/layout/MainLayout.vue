<script setup>
import MainNav from "./MainNav.vue";
import Footer from "./Footer.vue";
</script>

<template>
  <div class="relative flex flex-col min-h-screen">
    <MainNav class="absolute top-0 left-0 w-full z-10" />
    <div class="flex-grow w-full absolute top-0 left-0 z-0">
      <slot />
    </div>
    <Footer />
  </div>
</template>

<style scoped></style>
