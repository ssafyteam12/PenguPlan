<script setup>
import { reactive } from "vue";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

const props = defineProps({
  userEmail: String,
  password: String,
});

const localProfile = reactive({
  userEmail: props.userEmail,
  password: props.password,
});
</script>

<template>
  <Dialog open>
    <DialogContent class="max-w-md">
      <DialogHeader>
        <DialogTitle class="text-lg font-bold text-center text-gray-800">
          내 정보
        </DialogTitle>
      </DialogHeader>
      <div class="flex flex-col gap-6">
        <div>
          <Label
            for="email"
            class="block mb-2 text-sm font-medium text-gray-700"
            >이메일</Label
          >
          <Input
            id="email"
            type="email"
            v-model="localProfile.userEmail"
            class="transition-colors duration-300 bg-white"
          />
        </div>

        <div>
          <Label for="name" class="block mb-2 text-sm font-medium text-gray-700"
            >비밀번호</Label
          >
          <Input
            id="name"
            type="text"
            v-model="localProfile.password"
            class="transition-colors duration-300 bg-white"
          />
        </div>

        <!-- 버튼 -->
        <div class="flex justify-between">
          <Button @click="$emit('close')" variant="outline">닫기</Button>
          <Button
            @click="
              $emit(
                'saveChanges',
                localProfile.userEmail,
                localProfile.password
              )
            "
            variant="outline"
          >
            저장
          </Button>
        </div>
      </div>
    </DialogContent>
  </Dialog>
</template>
