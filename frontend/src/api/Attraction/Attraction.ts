import axios from "axios";
import apiClient from "../ApiClient";
import { Attraction, AttractionReview } from "@/type/type";

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

export const postAttractionReview = async (info: AttractionReview) => {
  try {
    const response = await apiClient.post(
      `/api/v1/attractions/${info.attractionNo}/comments`,
      info
    );
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const getAttractionNear = async (attractionNo: number) => {
  try {
    const response = await apiClient.get(
      `/api/v1/attractions/${attractionNo}/nearby`
    );
    return response.data;
  } catch (error) {
    console.error(error);
  }
};
