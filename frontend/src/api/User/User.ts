import apiClient from "../ApiClient";

export const register = async (registerForm: object) => {
  try {
    console.log("register = ", registerForm);
    const response = await apiClient.post("/api/v1/users", registerForm);
  } catch (error) {
    console.error(error);
  }
};

export const login = async (loginForm: object) => {
  try {
    const response = await apiClient.post("/api/v1/auth/login", loginForm);
    // accessToken 저장
    const accessToken = "Bearer " + response.data.accessToken;
    localStorage.setItem("accessToken", accessToken);

    return response.data.user;
  } catch (error) {
    console.error(error);
  }
};
