<script setup>
import KakaoMap from "@/components/Plan/Option/KakaoMap.vue";
import PlanDetailSideBar from "@/components/PlanDetail/PlanDetailSideBar.vue";
import {
  ResizableHandle,
  ResizablePanel,
  ResizablePanelGroup,
} from "@/components/ui/resizable";
</script>

<template>
  <div class="h-screen-minus-50">
    <ResizablePanelGroup direction="horizontal">
      <ResizablePanel>
        <KakaoMap />
      </ResizablePanel>
      <ResizableHandle withHandle />
      <ResizablePanel>
        <PlanDetailSideBar />
      </ResizablePanel>
    </ResizablePanelGroup>
  </div>
</template>

<style scoped></style>
