import { defineStore } from "pinia";
import { ref } from "vue";
import { User } from "@/type/type";

export const userStore = defineStore(
  "user",
  () => {
    const isLoggedIn = ref<boolean>(false);
    const userId = ref<number>(1);
    const userName = ref<string>("");
    const userEmail = ref<string>("");

    const loginSetting = (info: User) => {
      isLoggedIn.value = true;
      userId.value = info.userId;
      userName.value = info.username;
      userEmail.value = info.email;
    };

    const logoutSetting = () => {
      isLoggedIn.value = false;
      userId.value = 0;
      userName.value = "";
      userEmail.value = "";
    };

    return { isLoggedIn, loginSetting, logoutSetting, userName, userEmail };
  },
  {
    persist: true,
  }
);
