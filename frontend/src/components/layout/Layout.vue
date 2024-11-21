<script setup>
import Nav from "./Nav.vue";
import Footer from "./Footer.vue";
</script>

<template>
  <div class="flex flex-col min-h-screen">
    <Nav />

    <div class="flex-grow">
      <slot />
    </div>

    <Footer />
  </div>
</template>

<style scoped></style>
