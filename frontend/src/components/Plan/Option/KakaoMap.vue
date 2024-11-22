<script setup lang="ts">
import { Ref, ref, watchEffect } from "vue";
import {
  KakaoMap,
  KakaoMapMarker,
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

const selectedAttractionList = ref<Attraction[]>([]);

const image = {
  imageSrc: MarkImage,
  imageWidth: 48,
  imageHeight: 48,
};

const markerList: Ref<KakaoMapMarkerListItem[]> = ref([]);

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

const fetchPlaces = async () => {
  if (map.value) {
    const bounds = map.value.getBounds();
    const swLatLng = bounds.getSouthWest();
    const neLatLng = bounds.getNorthEast();

    const maxLat = neLatLng.getLat();
    const maxLong = neLatLng.getLng();
    const minLat = swLatLng.getLat();
    const minLong = swLatLng.getLng();

    // 위치 기반
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
    <div v-for="attr in attractionList">
      <KakaoMapMarker :lat="attr.latitude" :lng="attr.longitude" />
    </div>

    <KakaoMapMarkerPolyline
      v-if="!markerList"
      :markerList="markerList"
      :showMarkerOrder="false"
      strokeColor="#C74C5E"
      :strokeOpacity="1"
      strokeStyle="shortdot"
    />
  </KakaoMap>
</template>
