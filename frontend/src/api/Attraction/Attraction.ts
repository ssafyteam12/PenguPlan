import axios from "axios";
import apiClient from "../ApiClient";
import { Attraction } from "@/type/type";

// 완료
export const getAttractionBySidoGugun = async (
  sido: string,
  gugun: string,
  content: string,
  keyword: string
) => {
  try {
    const response = await apiClient.get<Attraction[]>(
      "api/v1/attractions/search",
      {
        params: { sido, gugun, content, keyword },
      }
    );

    // console.log("response data = ", response.data);

    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// 완료
export const getAttractionByPosition = async (
  maxLat: number,
  maxLong: number,
  minLat: number,
  minLong: number
) => {
  try {
    const response = await apiClient.get<Attraction[]>("/api/v1/attractions", {
      params: { maxLat, maxLong, minLat, minLong },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

//
export const getAttractionDetail = async (attractionNo: number) => {
  try {
    const response = await apiClient.get(`/api/v1/attractions/${attractionNo}`);

    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const postAttractionLike = async (attractionNo: number) => {
  try {
    const response = await apiClient.post(
      `/api/v1/attractions/${attractionNo}/likes`
    );
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const postAttractionReview = async (
  info: object,
  attractionNo: number
) => {
  console.log("info = ", info);
  try {
    const response = await apiClient.post(
      `/api/v1/attractions/${attractionNo}/ratings`,
      info
    );
    console.log(response);
  } catch (error) {
    console.error(error);
  }
};

export const getAttractionNear = async (attractionNo: number) => {
  try {
    const response = await apiClient.get(
      `/api/v1/attractions/${attractionNo}/nearby`
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};
