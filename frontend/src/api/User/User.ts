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
    console.log("login form = ", loginForm);

    const response = await apiClient.post("/api/v1/auth/login", loginForm);

    console.log("response = ", response.data);
    const accessToken = "Bearer " + response.data.accessToken;
    localStorage.setItem("accessToken", accessToken);

    return response.data.user;
  } catch (error) {
    const errorMessage =
      (error as { message?: string })?.message ||
      "알 수 없는 오류가 발생했습니다.";
    throw errorMessage;
  }
};
