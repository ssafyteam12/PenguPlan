import PlanJSON from "@/assets/lottie/Plan.json";
import SocialJSON from "@/assets/lottie/Social.json";
import PlaneJSON from "@/assets/lottie/Plane.json";
import MountainJSON from "@/assets/lottie/Mountain.json";
import { Info } from "@/type/type";

export const infoList: Info[] = [
  {
    id: 1,
    lottie: PlanJSON,
    title: "간편한 여행 계획",
    content:
      "AI가 추천하는 최적의 여행 코스로 손쉽게 계획을 세워보세요." +
      "<br>날짜와 선호도만 입력하면 맞춤형 일정이 완성됩니다." +
      "<br>드래그 앤 드롭으로 손쉽게 일정을 수정할 수 있어요." +
      "<br>교통, 소요시간까지 한 번에 확인하고 계획하세요.",
    buttonText: "여행 계획 세우기",
    category: "plan",
  },
  {
    id: 2,
    lottie: SocialJSON,
    title: "여행 커뮤니티",
    content:
      "다른 여행자들의 생생한 여행 이야기를 만나보세요." +
      "<br>실시간으로 현지 정보와 꿀팁을 공유할 수 있습니다." +
      "<br>나만의 특별한 여행 경험을 기록하고 공유해보세요." +
      "<br>함께 만들어가는 더 나은 여행을 경험하세요.",
    buttonText: "커뮤니티 가기",
    category: "trips",
  },
  {
    id: 3,
    lottie: PlaneJSON,
    title: "스마트한 여행 도구",
    content:
      "실시간 날씨 정보로 여행을 더 즐겁게 준비하세요." +
      "<br>주변 맛집, 카페 정보를 한 눈에 확인할 수 있어요." +
      "<br>대중교통 경로와 소요시간을 쉽게 파악하세요." +
      "<br>여행에 필요한 모든 정보를 한 곳에서 확인하세요.",
    buttonText: "도구 살펴보기",
    category: "plan",
  },
  {
    id: 4,
    lottie: MountainJSON,
    title: "지역별 추천 여행",
    content:
      "계절별로 엄선된 국내 여행지를 추천받아보세요." +
      "<br>지역별 숨은 명소와 특별한 장소를 발견하세요." +
      "<br>현지인이 추천하는 진짜 맛집 정보를 확인하세요." +
      "<br>새로운 여행의 즐거움을 발견하실 수 있습니다.",
    buttonText: "여행지 둘러보기",
    category: "plan",
  },
];
