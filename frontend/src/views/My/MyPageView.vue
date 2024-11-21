<script setup>
import MyInfo from "@/components/My/MyInfo.vue";
import MyPlan from "@/components/My/MyPlan.vue";
import MyLike from "@/components/My/MyLike.vue";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
</script>

<template>
  <div class="min-h-screen bg-gray-50">
    <MyInfo />
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <Tabs defaultValue="plans" class="w-full">
        <TabsList class="mt-[80px] grid w-full grid-cols-2 mb-8">
          <TabsTrigger value="plans">나의 여행 일정</TabsTrigger>
          <TabsTrigger value="likes">관심 여행지</TabsTrigger>
        </TabsList>
        <TabsContent value="plans">
          <MyPlan />
        </TabsContent>
        <TabsContent value="likes">
          <MyLike />
        </TabsContent>
      </Tabs>
    </div>
  </div>
</template>
