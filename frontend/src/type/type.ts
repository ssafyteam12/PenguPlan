// 메인페이지 아래쪽에서 나오는 설명 / 이미지

import { boolean } from "zod";

export interface Info {
  id: number;
  lottie: any;
  title: string;
  content: string;
  buttonText: string;
  category: string;
}

// 여행지 카테고리
export interface Category {
  id: number;
  name: string;
  img: string;
}

// 여행지 시/도
export interface Sido {
  id: number;
  name: string;
  img: string;
}

// 여행지 시/군/구
export interface Sigungu {
  sidoId: number;
  id: number;
  name: string;
}
// 관광지
export interface Attraction {
  sequence?: number;
  day?: number;
  no: number;
  contentId: number;
  title: string;
  contentTypeId: number;
  areaCode: number;
  siGunGuCode: number;
  firstImage1: string;
  firstImage2: string;
  mapLevel: number;
  latitude: number;
  longitude: number;
  tel: string;
  addr1: string;
  addr2: string;
  homepage?: string;
  overview?: string;
  islike: boolean;
  avgLank: number;
}

export interface SumAttraction {
  no: number;
  title: string;
  day: number;
  sequence: number;
}

// 여행 계획 생성할 때
export interface SendTrip {
  content: string;
  sidoCode: number;
  startDate: string;
  endDate: string;
  attractions: SumAttraction[];
}

// 여행 계획 조회
export interface Trip {
  tripId: number;
  userId: number;
  content: string;
  sidoCode: number;
  date: string;
  attractions: Attraction[];
}

// 특정 사용자의 여행 계획 전체 조회시
export interface UserTrips {
  userId: number;
  trips: Trip[];
  totalTrips: number;
  mostVisitedCity: string | null;
  latestTripDate: string | null;
}

export interface User {
  username: string;
  userId: number;
  email: string;
}

export interface AttractionReview {
  attractionNo: number;
  userName: string;
  rating: number;
  content: string;
}
