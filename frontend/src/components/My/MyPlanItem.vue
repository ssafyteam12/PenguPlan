<script setup>
import { defineProps } from "vue";
import { Button } from "@/components/ui/button";
import { useRouter } from "vue-router";
import NoImage from "@/assets/image/no-image.png";
import { Calendar, MapPin, Trash2 } from "lucide-vue-next";
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from "@/components/ui/alert-dialog";

const router = useRouter();
const props = defineProps({
  trip: {
    type: Object,
    required: true,
  },
});

const deletePlan = async (tripId) => {
  await deleteUserTripById(tripId);
  location.reload();
};
</script>

<template>
  <div
    class="bg-white rounded-lg shadow-sm overflow-hidden hover:shadow-md transition-shadow"
  >
    <div class="flex">
      <div class="w-48 h-48">
        <img
          :src="trip.attractions[0]?.firstImage1 || NoImage"
          :alt="trip.content"
          class="w-full h-full object-cover"
        />
      </div>
      <div class="flex-1 p-6">
        <div class="flex justify-between items-start">
          <div>
            <h3 class="text-xl font-semibold text-gray-900 mb-2">
              {{ trip.content }}
            </h3>
            <div class="flex items-center text-gray-500 mb-4">
              <Calendar class="h-4 w-4 mr-2" />
              <span>{{ trip.date }}</span>
            </div>
            <div class="flex items-center text-gray-500">
              <MapPin class="h-4 w-4 mr-2" />
              <span>{{ trip.attractions[0]?.addr1 || "주소 정보 없음" }}</span>
            </div>
          </div>
          <div class="flex space-x-2">
            <Button
              @click="router.push(`/myplan/${trip.tripId}`)"
              variant="default"
            >
              상세 보기
            </Button>
            <AlertDialog>
              <AlertDialogTrigger asChild>
                <Button
                  variant="outline"
                  class="text-rose-500 hover:text-rose-600"
                >
                  <Trash2 class="h-4 w-4" />
                </Button>
              </AlertDialogTrigger>
              <AlertDialogContent>
                <AlertDialogHeader>
                  <AlertDialogTitle>여행 계획 삭제</AlertDialogTitle>
                  <AlertDialogDescription>
                    정말로 이 여행 계획을 삭제하시겠습니까? 이 작업은 취소할 수
                    없습니다.
                  </AlertDialogDescription>
                </AlertDialogHeader>
                <AlertDialogFooter>
                  <AlertDialogCancel>취소</AlertDialogCancel>
                  <AlertDialogAction
                    @click="deletePlan(trip.tripId)"
                    class="bg-rose-500 hover:bg-rose-600"
                  >
                    삭제
                  </AlertDialogAction>
                </AlertDialogFooter>
              </AlertDialogContent>
            </AlertDialog>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
