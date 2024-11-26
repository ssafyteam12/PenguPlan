import axios from "axios";
import apiClient from "../ApiClient";
import { Attraction, AttractionReview } from "@/type/type";
import { planStore } from "@/store/store";
import { storeToRefs } from "pinia";

// 완료
export const getAttractionBySidoGugun = async (
  sido: string,
  gugun: string,
  pContent: string,
  pKeyword: string
) => {
  const pStore = planStore();
  const { sidoCode, content, keyword } = storeToRefs(pStore);

  if (sido === "") {
    sido = String(sidoCode.value);
  }
  if (pContent === "") {
    pContent = String(content.value);
  }
  if (pKeyword === "") {
    pKeyword = String(keyword.value);
  }

  try {
    const response = await apiClient.get<Attraction[]>(
      "api/v1/attractions/search",
      {
        params: { sido, gugun, content: pContent, keyword: pKeyword },
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
  const pStore = planStore();
  const { sidoCode, content, keyword } = storeToRefs(pStore);

  const send = {
    maxLat,
    maxLong,
    minLat,
    minLong,
    sido: sidoCode.value,
    content: content.value,
    keyword: keyword.value,
  };

  try {
    const response = await apiClient.get<Attraction[]>("/api/v1/attractions", {
      params: send,
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
