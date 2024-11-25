// src/data/locations.ts
import { Sido, Sigungu } from "@/type/type";

import CityImage1 from "@/assets/image/cities/seoul.png";
import CityImage2 from "@/assets/image/cities/incheon.png";
import CityImage3 from "@/assets/image/cities/daejeon.png";
import CityImage4 from "@/assets/image/cities/daegu.png";
import CityImage5 from "@/assets/image/cities/gwangju.png";
import CityImage6 from "@/assets/image/cities/busan.png";
import CityImage7 from "@/assets/image/cities/ulsan.png";
import CityImage8 from "@/assets/image/cities/sejong.png";
import CityImage9 from "@/assets/image/cities/gyungi.png";
import CityImage10 from "@/assets/image/cities/gangwon.png";
import CityImage11 from "@/assets/image/cities/chungbuk.png";
import CityImage12 from "@/assets/image/cities/chungnam.png";
import CityImage13 from "@/assets/image/cities/gyungbuk.png";
import CityImage14 from "@/assets/image/cities/gyungnam.png";
import CityImage15 from "@/assets/image/cities/jeonbuk.png";
import CityImage16 from "@/assets/image/cities/jeonnam.png";
import CityImage17 from "@/assets/image/cities/jeju.png";

// 시/도 데이터
export const sidoList: Sido[] = [
  { id: 1, name: "서울", img: CityImage1 },
  { id: 2, name: "인천", img: CityImage2 },
  { id: 3, name: "대전", img: CityImage3 },
  { id: 4, name: "대구", img: CityImage4 },
  { id: 5, name: "광주", img: CityImage5 },
  { id: 6, name: "부산", img: CityImage6 },
  { id: 7, name: "울산", img: CityImage7 },
  { id: 8, name: "세종", img: CityImage8 },
  { id: 31, name: "경기도", img: CityImage9 },
  { id: 32, name: "강원도", img: CityImage10 },
  { id: 33, name: "충청북도", img: CityImage11 },
  { id: 34, name: "충청남도", img: CityImage12 },
  { id: 35, name: "경상북도", img: CityImage13 },
  { id: 36, name: "경상남도", img: CityImage14 },
  { id: 37, name: "전라북도", img: CityImage15 },
  { id: 38, name: "전라남도", img: CityImage16 },
  { id: 39, name: "제주도", img: CityImage17 },
];

// 시/군/구 데이터
export const sigunguList: Sigungu[] = [
  { sidoId: 1, id: 1, name: "강남구" },
  { sidoId: 1, id: 2, name: "강동구" },
  { sidoId: 1, id: 3, name: "강북구" },
  { sidoId: 1, id: 4, name: "강서구" },
  { sidoId: 1, id: 5, name: "관악구" },
  { sidoId: 1, id: 6, name: "광진구" },
  { sidoId: 1, id: 7, name: "구로구" },
  { sidoId: 1, id: 8, name: "금천구" },
  { sidoId: 1, id: 9, name: "노원구" },
  { sidoId: 1, id: 10, name: "도봉구" },
  { sidoId: 1, id: 11, name: "동대문구" },
  { sidoId: 1, id: 12, name: "동작구" },
  { sidoId: 1, id: 13, name: "마포구" },
  { sidoId: 1, id: 14, name: "서대문구" },
  { sidoId: 1, id: 15, name: "서초구" },
  { sidoId: 1, id: 16, name: "성동구" },
  { sidoId: 1, id: 17, name: "성북구" },
  { sidoId: 1, id: 18, name: "송파구" },
  { sidoId: 1, id: 19, name: "양천구" },
  { sidoId: 1, id: 20, name: "영등포구" },
  { sidoId: 1, id: 21, name: "용산구" },
  { sidoId: 1, id: 22, name: "은평구" },
  { sidoId: 1, id: 23, name: "종로구" },
  { sidoId: 1, id: 24, name: "중구" },
  { sidoId: 1, id: 25, name: "중랑구" },
  { sidoId: 2, id: 1, name: "강화군" },
  { sidoId: 2, id: 2, name: "계양구" },
  { sidoId: 2, id: 3, name: "미추홀구" },
  { sidoId: 2, id: 4, name: "남동구" },
  { sidoId: 2, id: 5, name: "동구" },
  { sidoId: 2, id: 6, name: "부평구" },
  { sidoId: 2, id: 7, name: "서구" },
  { sidoId: 2, id: 8, name: "연수구" },
  { sidoId: 2, id: 9, name: "옹진군" },
  { sidoId: 2, id: 10, name: "중구" },
  { sidoId: 3, id: 1, name: "대덕구" },
  { sidoId: 3, id: 2, name: "동구" },
  { sidoId: 3, id: 3, name: "서구" },
  { sidoId: 3, id: 4, name: "유성구" },
  { sidoId: 3, id: 5, name: "중구" },
  { sidoId: 4, id: 1, name: "남구" },
  { sidoId: 4, id: 2, name: "달서구" },
  { sidoId: 4, id: 3, name: "달성군" },
  { sidoId: 4, id: 4, name: "동구" },
  { sidoId: 4, id: 5, name: "북구" },
  { sidoId: 4, id: 6, name: "서구" },
  { sidoId: 4, id: 7, name: "수성구" },
  { sidoId: 4, id: 8, name: "중구" },
  { sidoId: 4, id: 9, name: "군위군" },
  { sidoId: 5, id: 1, name: "광산구" },
  { sidoId: 5, id: 2, name: "남구" },
  { sidoId: 5, id: 3, name: "동구" },
  { sidoId: 5, id: 4, name: "북구" },
  { sidoId: 5, id: 5, name: "서구" },
  { sidoId: 6, id: 1, name: "강서구" },
  { sidoId: 6, id: 2, name: "금정구" },
  { sidoId: 6, id: 3, name: "기장군" },
  { sidoId: 6, id: 4, name: "남구" },
  { sidoId: 6, id: 5, name: "동구" },
  { sidoId: 6, id: 6, name: "동래구" },
  { sidoId: 6, id: 7, name: "부산진구" },
  { sidoId: 6, id: 8, name: "북구" },
  { sidoId: 6, id: 9, name: "사상구" },
  { sidoId: 6, id: 10, name: "사하구" },
  { sidoId: 6, id: 11, name: "서구" },
  { sidoId: 6, id: 12, name: "수영구" },
  { sidoId: 6, id: 13, name: "연제구" },
  { sidoId: 6, id: 14, name: "영도구" },
  { sidoId: 6, id: 15, name: "중구" },
  { sidoId: 6, id: 16, name: "해운대구" },
  { sidoId: 7, id: 1, name: "중구" },
  { sidoId: 7, id: 2, name: "남구" },
  { sidoId: 7, id: 3, name: "동구" },
  { sidoId: 7, id: 4, name: "북구" },
  { sidoId: 7, id: 5, name: "울주군" },
  { sidoId: 8, id: 1, name: "세종특별자치시" },
  { sidoId: 31, id: 1, name: "가평군" },
  { sidoId: 31, id: 2, name: "고양시" },
  { sidoId: 31, id: 3, name: "과천시" },
  { sidoId: 31, id: 4, name: "광명시" },
  { sidoId: 31, id: 5, name: "광주시" },
  { sidoId: 31, id: 6, name: "구리시" },
  { sidoId: 31, id: 7, name: "군포시" },
  { sidoId: 31, id: 8, name: "김포시" },
  { sidoId: 31, id: 9, name: "남양주시" },
  { sidoId: 31, id: 10, name: "동두천시" },
  { sidoId: 31, id: 11, name: "부천시" },
  { sidoId: 31, id: 12, name: "성남시" },
  { sidoId: 31, id: 13, name: "수원시" },
  { sidoId: 31, id: 14, name: "시흥시" },
  { sidoId: 31, id: 15, name: "안산시" },
  { sidoId: 31, id: 16, name: "안성시" },
  { sidoId: 31, id: 17, name: "안양시" },
  { sidoId: 31, id: 18, name: "양주시" },
  { sidoId: 31, id: 19, name: "양평군" },
  { sidoId: 31, id: 20, name: "여주시" },
  { sidoId: 31, id: 21, name: "연천군" },
  { sidoId: 31, id: 22, name: "오산시" },
  { sidoId: 31, id: 23, name: "용인시" },
  { sidoId: 31, id: 24, name: "의왕시" },
  { sidoId: 31, id: 25, name: "의정부시" },
  { sidoId: 31, id: 26, name: "이천시" },
  { sidoId: 31, id: 27, name: "파주시" },
  { sidoId: 31, id: 28, name: "평택시" },
  { sidoId: 31, id: 29, name: "포천시" },
  { sidoId: 31, id: 30, name: "하남시" },
  { sidoId: 31, id: 31, name: "화성시" },
  { sidoId: 32, id: 1, name: "강릉시" },
  { sidoId: 32, id: 2, name: "고성군" },
  { sidoId: 32, id: 3, name: "동해시" },
  { sidoId: 32, id: 4, name: "삼척시" },
  { sidoId: 32, id: 5, name: "속초시" },
  { sidoId: 32, id: 6, name: "양구군" },
  { sidoId: 32, id: 7, name: "양양군" },
  { sidoId: 32, id: 8, name: "영월군" },
  { sidoId: 32, id: 9, name: "원주시" },
  { sidoId: 32, id: 10, name: "인제군" },
  { sidoId: 32, id: 11, name: "정선군" },
  { sidoId: 32, id: 12, name: "철원군" },
  { sidoId: 32, id: 13, name: "춘천시" },
  { sidoId: 32, id: 14, name: "태백시" },
  { sidoId: 32, id: 15, name: "평창군" },
  { sidoId: 32, id: 16, name: "홍천군" },
  { sidoId: 32, id: 17, name: "화천군" },
  { sidoId: 32, id: 18, name: "횡성군" },
  { sidoId: 33, id: 1, name: "괴산군" },
  { sidoId: 33, id: 2, name: "단양군" },
  { sidoId: 33, id: 3, name: "보은군" },
  { sidoId: 33, id: 4, name: "영동군" },
  { sidoId: 33, id: 5, name: "옥천군" },
  { sidoId: 33, id: 6, name: "음성군" },
  { sidoId: 33, id: 7, name: "제천시" },
  { sidoId: 33, id: 8, name: "진천군" },
  { sidoId: 33, id: 9, name: "청원군" },
  { sidoId: 33, id: 10, name: "청주시" },
  { sidoId: 33, id: 11, name: "충주시" },
  { sidoId: 33, id: 12, name: "증평군" },
  { sidoId: 34, id: 1, name: "공주시" },
  { sidoId: 34, id: 2, name: "금산군" },
  { sidoId: 34, id: 3, name: "논산시" },
  { sidoId: 34, id: 4, name: "당진시" },
  { sidoId: 34, id: 5, name: "보령시" },
  { sidoId: 34, id: 6, name: "부여군" },
  { sidoId: 34, id: 7, name: "서산시" },
  { sidoId: 34, id: 8, name: "서천군" },
  { sidoId: 34, id: 9, name: "아산시" },
  { sidoId: 34, id: 11, name: "예산군" },
  { sidoId: 34, id: 12, name: "천안시" },
  { sidoId: 34, id: 13, name: "청양군" },
  { sidoId: 34, id: 14, name: "태안군" },
  { sidoId: 34, id: 15, name: "홍성군" },
  { sidoId: 34, id: 16, name: "계룡시" },
  { sidoId: 35, id: 1, name: "경산시" },
  { sidoId: 35, id: 2, name: "경주시" },
  { sidoId: 35, id: 3, name: "고령군" },
  { sidoId: 35, id: 4, name: "구미시" },
  { sidoId: 35, id: 6, name: "김천시" },
  { sidoId: 35, id: 7, name: "문경시" },
  { sidoId: 35, id: 8, name: "봉화군" },
  { sidoId: 35, id: 9, name: "상주시" },
  { sidoId: 35, id: 10, name: "성주군" },
  { sidoId: 35, id: 11, name: "안동시" },
  { sidoId: 35, id: 12, name: "영덕군" },
  { sidoId: 35, id: 13, name: "영양군" },
  { sidoId: 35, id: 14, name: "영주시" },
  { sidoId: 35, id: 15, name: "영천시" },
  { sidoId: 35, id: 16, name: "예천군" },
  { sidoId: 35, id: 17, name: "울릉군" },
  { sidoId: 35, id: 18, name: "울진군" },
  { sidoId: 35, id: 19, name: "의성군" },
  { sidoId: 35, id: 20, name: "청도군" },
  { sidoId: 35, id: 21, name: "청송군" },
  { sidoId: 35, id: 22, name: "칠곡군" },
  { sidoId: 35, id: 23, name: "포항시" },
  { sidoId: 36, id: 1, name: "거제시" },
  { sidoId: 36, id: 2, name: "거창군" },
  { sidoId: 36, id: 3, name: "고성군" },
  { sidoId: 36, id: 4, name: "김해시" },
  { sidoId: 36, id: 5, name: "남해군" },
  { sidoId: 36, id: 6, name: "마산시" },
  { sidoId: 36, id: 7, name: "밀양시" },
  { sidoId: 36, id: 8, name: "사천시" },
  { sidoId: 36, id: 9, name: "산청군" },
  { sidoId: 36, id: 10, name: "양산시" },
  { sidoId: 36, id: 12, name: "의령군" },
  { sidoId: 36, id: 13, name: "진주시" },
  { sidoId: 36, id: 14, name: "진해시" },
  { sidoId: 36, id: 15, name: "창녕군" },
  { sidoId: 36, id: 16, name: "창원시" },
  { sidoId: 36, id: 17, name: "통영시" },
  { sidoId: 36, id: 18, name: "하동군" },
  { sidoId: 36, id: 19, name: "함안군" },
  { sidoId: 36, id: 20, name: "함양군" },
  { sidoId: 36, id: 21, name: "합천군" },
  { sidoId: 37, id: 1, name: "고창군" },
  { sidoId: 37, id: 2, name: "군산시" },
  { sidoId: 37, id: 3, name: "김제시" },
  { sidoId: 37, id: 4, name: "남원시" },
  { sidoId: 37, id: 5, name: "무주군" },
  { sidoId: 37, id: 6, name: "부안군" },
  { sidoId: 37, id: 7, name: "순창군" },
  { sidoId: 37, id: 8, name: "완주군" },
  { sidoId: 37, id: 9, name: "익산시" },
  { sidoId: 37, id: 10, name: "임실군" },
  { sidoId: 37, id: 11, name: "장수군" },
  { sidoId: 37, id: 12, name: "전주시" },
  { sidoId: 37, id: 13, name: "정읍시" },
  { sidoId: 37, id: 14, name: "진안군" },
  { sidoId: 38, id: 1, name: "강진군" },
  { sidoId: 38, id: 2, name: "고흥군" },
  { sidoId: 38, id: 3, name: "곡성군" },
  { sidoId: 38, id: 4, name: "광양시" },
  { sidoId: 38, id: 5, name: "구례군" },
  { sidoId: 38, id: 6, name: "나주시" },
  { sidoId: 38, id: 7, name: "담양군" },
  { sidoId: 38, id: 8, name: "목포시" },
  { sidoId: 38, id: 9, name: "무안군" },
  { sidoId: 38, id: 10, name: "보성군" },
  { sidoId: 38, id: 11, name: "순천시" },
  { sidoId: 38, id: 12, name: "신안군" },
  { sidoId: 38, id: 13, name: "여수시" },
  { sidoId: 38, id: 16, name: "영광군" },
  { sidoId: 38, id: 17, name: "영암군" },
  { sidoId: 38, id: 18, name: "완도군" },
  { sidoId: 38, id: 19, name: "장성군" },
  { sidoId: 38, id: 20, name: "장흥군" },
  { sidoId: 38, id: 21, name: "진도군" },
  { sidoId: 38, id: 22, name: "함평군" },
  { sidoId: 38, id: 23, name: "해남군" },
  { sidoId: 38, id: 24, name: "화순군" },
  { sidoId: 39, id: 1, name: "남제주군" },
  { sidoId: 39, id: 2, name: "북제주군" },
  { sidoId: 39, id: 3, name: "서귀포시" },
  { sidoId: 39, id: 4, name: "제주시" },
];
