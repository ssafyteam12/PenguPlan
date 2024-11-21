import apiClient from "../ApiClient";

// 완료
export const getMyInfo = async () => {
  try {
    const response = await apiClient.get("/api/v1/users/me");
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const putMyInfo = async (info: object) => {
  try {
    const response = await apiClient.put("/api/v1/users/me", info);

    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const getMyLike = async () => {
  try {
    const response = await apiClient.get("/api/v1/users/me/likes");
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};
