<script setup>
import { ref, onMounted } from "vue";
import EditProfile from "./EditProfile.vue";
import { Button } from "@/components/ui/button";
import { userStore } from "@/store/userStore";
import { getMyInfo, putMyInfo } from "@/api/User/my";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { User, Mail, MapPin } from "lucide-vue-next";

const userName = ref("");
const userEmail = ref("");
const password = ref("");

onMounted(async () => {
  const data = await getMyInfo();
  userName.value = data.username;
  userEmail.value = data.email;
});

const showModal = ref(false);
const toggleModal = () => {
  showModal.value = !showModal.value;
};

const editProfile = async (email, password) => {
  const send = { email, password };
  await putMyInfo(send);
  toggleModal();
};

const getInitials = (name) => {
  return name
    .split(" ")
    .map((n) => n[0])
    .join("")
    .toUpperCase();
};
</script>

<template>
  <div>
    <div class="bg-base-color h-[250px] relative">
      <div class="absolute -bottom-20 w-full">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div class="bg-white rounded-lg shadow-lg p-6">
            <div class="flex items-center space-x-6">
              <Avatar class="h-24 w-24">
                <AvatarImage
                  :src="`https://api.dicebear.com/7.x/initials/svg?seed=${userName}`"
                />
                <AvatarFallback>{{ getInitials(userName) }}</AvatarFallback>
              </Avatar>
              <div class="flex-1">
                <div class="flex items-center justify-between">
                  <div>
                    <h2 class="text-2xl font-bold text-gray-900">
                      {{ userName }}
                    </h2>
                    <div class="flex items-center space-x-4 mt-2 text-gray-500">
                      <div class="flex items-center">
                        <Mail class="h-4 w-4 mr-2" />
                        {{ userEmail }}
                      </div>
                    </div>
                  </div>
                  <Button
                    @click="toggleModal"
                    variant="outline"
                    class="flex items-center"
                  >
                    프로필 수정
                  </Button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <EditProfile
      v-if="showModal"
      :password="password"
      :userEmail="userEmail"
      @close="toggleModal"
      @saveChanges="editProfile"
    />
  </div>
</template>
