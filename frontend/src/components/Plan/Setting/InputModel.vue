<script setup>
import { defineEmits } from "vue";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { PlusCircle } from "lucide-vue-next";
import Button from "@/components/ui/button/Button.vue";
import Input from "./Input.vue";

const emit = defineEmits(["complete"]);

const submitForm = () => {
  emit("complete");
};
</script>

<template>
  <Dialog>
    <DialogTrigger asChild>
      <Button
        class="flex items-center gap-2 px-6 py-3 bg-white border-2 border-blue-600 text-blue-600 hover:bg-blue-50 transition-colors rounded-full font-medium text-lg shadow-sm hover:shadow-md"
      >
        <PlusCircle class="w-5 h-5" />
        새로운 여행 만들기
      </Button>
    </DialogTrigger>

    <DialogContent class="sm:max-w-[600px]">
      <DialogHeader>
        <DialogTitle class="text-2xl font-bold text-gray-800 text-center pb-2">
          여행 설정하기
        </DialogTitle>
        <div class="w-16 h-1 bg-blue-600 mx-auto mb-6 rounded-full"></div>
        <DialogDescription class="text-center text-gray-600 mb-8">
          목적지와 일정을 선택하고 나만의 여행을 계획해보세요
        </DialogDescription>
      </DialogHeader>

      <div class="bg-gray-50 p-6 rounded-xl">
        <Input @submit-form="submitForm" />
      </div>

      <div class="text-center text-sm text-gray-500 mt-4">
        * 설정한 내용은 나중에도 수정할 수 있습니다
      </div>
    </DialogContent>
  </Dialog>
</template>

<style scoped>
:deep(.dialog-overlay) {
  background-color: rgba(0, 0, 0, 0.4);
  backdrop-filter: blur(4px);
}

:deep(.dialog-content) {
  border: none;
  box-shadow:
    0 20px 25px -5px rgba(0, 0, 0, 0.1),
    0 10px 10px -5px rgba(0, 0, 0, 0.04);
}

/* Animation classes */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

.slide-up-enter-active,
.slide-up-leave-active {
  transition: transform 0.3s ease-out;
}

.slide-up-enter-from,
.slide-up-leave-to {
  transform: translateY(10px);
}
</style>
