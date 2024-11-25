import { defineStore } from "pinia";
import { ref, watch } from "vue";
import { Attraction } from "@/type/type";

export const dayStore = defineStore("day", () => {
  const totalDay = ref(4);
  const selectedDay = ref(1);

  const setTotalDay = (day: number) => {
    totalDay.value = day;
  };

  const selectDay = (day: number) => {
    selectedDay.value = day;
  };

  return { totalDay, setTotalDay, selectedDay, selectDay };
});

export const planStore = defineStore("plan", () => {
  const content = ref<number>(1);
  const sidoCode = ref<number>(1);
  const startDate = ref<string>("2024-11-11");
  const endDate = ref<string>("2024-12-21");
  const keyword = ref<string>("");

  const titleContent = ref<string>("");

  const setContent = (newContent: number) => {
    content.value = newContent;
  };

  const setSidoCode = (newSidoCode: string) => {
    sidoCode.value = Number(newSidoCode);
  };

  const setDate = (newStartDate: string, newEndDate: string) => {
    startDate.value = newStartDate;
    endDate.value = newEndDate;
  };

  const setKeyword = (newKeyword: string) => {
    keyword.value = newKeyword;
  };

  const setTitleContent = (newTitle: string) => {
    titleContent.value = newTitle;
  };

  const plans = ref<{ [key: string]: Attraction[] }>({});

  const initiate = () => {
    // content.value = 1;
    // sidoCode.value = 1;
    // keyword.value = "";
    plans.value = {};
  };

  const setPlan = (newPlan: any) => {
    plans.value = newPlan;
  };

  const addAttraction = (day: string, attraction: Attraction) => {
    if (!plans.value[day]) {
      plans.value[day] = [];
    }

    if (!plans.value[day].find((att) => att.no === attraction.no)) {
      const newId = plans.value[day].length + 1;
      plans.value[day].push({
        ...attraction,
        sequence: newId,
        day: Number(day),
      });
    }
  };

  const removeAttraction = (day: string, attractionNo: number) => {
    if (plans.value[day]) {
      plans.value[day] = plans.value[day].filter(
        (att) => att.no !== attractionNo
      );
    }
  };

  const getAttraction = (day: string) => {
    return plans.value[day] || [];
  };

  return {
    initiate,
    content,
    sidoCode,
    startDate,
    endDate,
    keyword,
    titleContent,
    setContent,
    setSidoCode,
    setDate,
    setKeyword,
    setPlan,
    setTitleContent,

    plans,
    addAttraction,
    removeAttraction,
    getAttraction,
  };
});

export const attractionStore = defineStore("attraction", () => {
  const attractionList = ref<Attraction[]>([]);

  const setAttractionList = (newList: Attraction[]) => {
    attractionList.value = newList;
  };

  const attractionNum = ref(0);

  const setattractionNum = (num: number) => {
    attractionNum.value = num;
  };

  const lat = ref(33.4509);
  const lng = ref(126.571);

  const setLat = (newLat: number) => {
    lat.value = newLat;
  };

  const setLng = (newLng: number) => {
    lng.value = newLng;
  };

  watch(attractionList, (newList) => {
    if (newList.length > 0) {
      lat.value = newList[0].latitude;
      lng.value = newList[0].longitude;
    }
  });

  return {
    attractionList,
    setAttractionList,

    attractionNum,
    setattractionNum,

    lat,
    lng,
    setLat,
    setLng,
  };
});
