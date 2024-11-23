<script setup lang="ts">
import { Ref, ref, watchEffect, computed } from "vue";
import {
  KakaoMap,
  KakaoMapMarker,
  KakaoMapInfoWindow,
  KakaoMapMarkerPolyline,
  type KakaoMapMarkerListItem,
} from "vue3-kakao-maps";
import { dayStore, planStore, attractionStore } from "@/store/store";
import { storeToRefs } from "pinia";
import { Attraction } from "@/type/type";
import { getAttractionByPosition } from "@/api/Attraction/Attraction";
import MarkImage from "@/assets/image/marker.png";

const dStore = dayStore();
const pStore = planStore();
const aStore = attractionStore();
const { selectedDay } = storeToRefs(dStore);
const { lat, lng, attractionList } = storeToRefs(aStore);

const map = ref<kakao.maps.Map | null>(null);
const currentZoomLevel = ref(3);
const hoveredMarker = ref<number | null>(null);

const selectedAttractionList = ref<Attraction[]>([]);
const markerRefs = ref<Map<number, kakao.maps.Marker>>(new Map());

const image = {
  imageSrc: MarkImage,
  imageWidth: 48,
  imageHeight: 48,
};

const markerList: Ref<KakaoMapMarkerListItem[]> = ref([]);

// 줌 레벨에 따른 표시할 인포윈도우 개수 설정
const getVisibleInfoWindowCount = computed(() => {
  if (currentZoomLevel.value >= 8) return 0;
  if (currentZoomLevel.value >= 6) return 2;
  if (currentZoomLevel.value >= 4) return 4;
  else return 8;
});

// 상위 N개의 관광지 선택 (예: 평점 기준)
const topAttractions = computed(() => {
  return [...attractionList.value]
    .sort((a, b) => (b.avgLank || 0) - (a.avgLank || 0))
    .slice(0, getVisibleInfoWindowCount.value);
}); 



// 마커를 보여줄 최대 줌 레벨 (이 레벨보다 크면 마커가 안 보임)
const MAX_MARKER_ZOOM_LEVEL = 8;

// 줌 레벨에 따라 마커를 보여줄지 결정
const showMarkers = computed(() => {
  return currentZoomLevel.value <= MAX_MARKER_ZOOM_LEVEL;
});

// 마커 등록 핸들러
const handleMarkerLoad = (marker: kakao.maps.Marker, attractionNo: number) => {
  markerRefs.value.set(attractionNo, marker);
};

watchEffect(() => {
  selectedAttractionList.value = [
    ...pStore.getAttraction(selectedDay.value.toString()),
  ];

  

  markerList.value = selectedAttractionList.value.map(
    (attraction: any, index: number) => ({
      lat: attraction.latitude,
      lng: attraction.longitude,
      image,
      orderBottomMargin: "37px",
      order: index === 0 ? "출발" : undefined,
    })
  );
});

// 마커 클릭 시 해당 위치로 지도 중심 이동
const handleMarkerClick = (latitude: number, longitude: number) => {
  if (map.value) {
    const moveLatLng = new kakao.maps.LatLng(latitude, longitude);
    map.value.panTo(moveLatLng);
  }
};

// 마커에 마우스 오버/아웃 이벤트 핸들러
const handleMouseOver = (attractionNo: number): void => {
  hoveredMarker.value = attractionNo;
};

const handleMouseOut = (): void => {
  hoveredMarker.value = null;
};

const fetchPlaces = async () => {
  if (map.value) {
    const bounds = map.value.getBounds();
    const swLatLng = bounds.getSouthWest();
    const neLatLng = bounds.getNorthEast();

    const maxLat = neLatLng.getLat();
    const maxLong = neLatLng.getLng();
    const minLat = swLatLng.getLat();
    const minLong = swLatLng.getLng();

    const returnAttractions = await getAttractionByPosition(
      maxLat,
      maxLong,
      minLat,
      minLong
    );

    aStore.setAttractionList(returnAttractions.attractions);
  }
};

const onLoadKakaoMap = (mapRef: kakao.maps.Map) => {
  map.value = mapRef;

  const mapTypeControl = new kakao.maps.MapTypeControl();
  map.value.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  kakao.maps.event.addListener(map.value, "dragend", () => {
    fetchPlaces();
  });

  kakao.maps.event.addListener(map.value, "zoom_changed", () => {
    if (map.value) {
      currentZoomLevel.value = map.value.getLevel();
    }
    fetchPlaces();
  });
};
</script>

<template>
  <KakaoMap
    @onLoadKakaoMap="onLoadKakaoMap"
    :width="'100%'"
    :height="'100%'"
    :level="3"
    :lat="lat"
    :lng="lng"
  >
  <template v-if="showMarkers">
    <div v-for="attr in attractionList" :key="attr.no">
      <!-- 마커 -->
      <KakaoMapMarker 
        :lat="attr.latitude" 
        :lng="attr.longitude"
        :clickable="true"
        @onLoadKakaoMapMarker="(marker) => handleMarkerLoad(marker, attr.no)"
        @onClickKakaoMapMarker="() => handleMarkerClick(attr.latitude, attr.longitude)"
        @mouseOverKakaoMapMarker="() => handleMouseOver(attr.no)"
        @mouseOutKakaoMapMarker="handleMouseOut"
      />
      
      <!-- 상위 N개 관광지에 대한 인포윈도우 -->
      <KakaoMapInfoWindow
        v-if="topAttractions.find(a => a.no === attr.no)"
        :marker="markerRefs.get(attr.no)"
        :lat="attr.latitude"
        :lng="attr.longitude"
        :visible="true"
      >
        <div class="px-2 py-1 bg-white rounded shadow">
          <p class="font-semibold text-sm">{{ attr.title }}</p>
          <p class="text-xs text-gray-600">평점: {{ attr.avgLank || '없음' }}</p>
        </div>
      </KakaoMapInfoWindow>

      <!-- 마우스 오버 시 표시되는 인포윈도우 -->
      <KakaoMapInfoWindow
        v-if="hoveredMarker === attr.no && !topAttractions.find(a => a.no === attr.no)"
        :marker="markerRefs.get(attr.no)"
        :lat="attr.latitude"
        :lng="attr.longitude"
        :visible="true"
      >
        <div class="px-2 py-1 bg-white rounded shadow">
          <p class="text-sm">{{ attr.title }}</p>
        </div>
      </KakaoMapInfoWindow>
    </div>

    <KakaoMapMarkerPolyline
      v-if="!markerList"
      :markerList="markerList"
      :showMarkerOrder="false"
      strokeColor="#C74C5E"
      :strokeOpacity="1"
      strokeStyle="shortdot"
    />
    </template>
  </KakaoMap>
</template>