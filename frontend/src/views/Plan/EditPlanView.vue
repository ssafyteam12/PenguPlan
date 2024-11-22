<script setup>
import { useRoute } from "vue-router";
import PlanMain from "../../components/Plan/Setting/PlanMain.vue";

import getUserTripById from "@/api/Plan/plan";

const route = useRoute();
// 값 세팅해서 이동
</script>

<template>
  <div>
    <PlanMain />
  </div>
</template>

<style scoped></style>
