<script setup lang="ts">
import { ref, computed } from "vue";
import { Input } from "@/components/ui/input";
import Button from "@/components/ui/button/Button.vue";
import { planStore, attractionStore } from "@/store/store";

import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from "@/components/ui/form";
import { Sigungu } from "@/type/type";
import { sigunguList, sidoList } from "@/components/Plan/Setting/location";
import { categoryList } from "@/components/Plan/Setting/contenttype";
import { getAttractionBySidoGugun } from "@/api/Attraction/Attraction";

const pStore = planStore();
const aStore = attractionStore();
const selectedSido = ref("");
const selectedSigungu = ref("");
const selectedContent = ref("");
const keyword = ref("");

// 시군구 필터링 - 선택된 시도에 따라
const filteredSigungu = computed(() => {
  if (!selectedSido.value || selectedSido.value === "all") {
    return sigunguList;
  }
  return sigunguList.filter(
    (sigungu: Sigungu) => sigungu.sidoId === parseInt(selectedSido.value)
  );
});

// 검색 핸들러
const handleSearch = async (e?: Event) => {
  if (e?.preventDefault) {
    e.preventDefault();
  }

  // 전체 선택인 경우 빈 문자열로 처리
  const sidoCode = selectedSido.value === "all" ? "" : selectedSido.value;
  const sigunguCode =
    selectedSigungu.value === "all" ? "" : selectedSigungu.value;
  const contentType =
    selectedContent.value === "all" ? "" : selectedContent.value;

  pStore.setSidoCode(sidoCode);
  pStore.setContent(contentType);

  const returnAttractions = await getAttractionBySidoGugun(
    sidoCode,
    sigunguCode,
    contentType,
    keyword.value
  );

  aStore.setAttractionList(returnAttractions.attractions);
};

// computed 속성 추가 (script 부분)
const isGugunDisabled = computed(() => {
  return !selectedSido.value || selectedSido.value === "all";
});

// 시도 변경 시 시군구 초기화
const handleSidoChange = (value: string) => {
  selectedSido.value = value;
  selectedSigungu.value = "all";
};
</script>

<template>
  <Form @submit="handleSearch">
    <div class="flex gap-2 flex-wrap">
      <!-- 시도 선택 -->
      <div class="grow-[2]">
        <FormField v-slot="{ componentField }" name="sido">
          <FormItem>
            <Select
              v-model="selectedSido"
              @update:modelValue="handleSidoChange"
            >
              <FormControl>
                <SelectTrigger>
                  <SelectValue placeholder="시/도 선택" />
                </SelectTrigger>
              </FormControl>
              <SelectContent>
                <SelectGroup>
                  <SelectLabel>시/도</SelectLabel>
                  <SelectItem value="all">전체</SelectItem>
                  <SelectItem
                    :value="String(sido.id)"
                    v-for="sido in sidoList"
                    :key="sido.id"
                  >
                    {{ sido.name }}
                  </SelectItem>
                </SelectGroup>
              </SelectContent>
            </Select>
            <FormMessage />
          </FormItem>
        </FormField>
      </div>

      <!-- 시군구 선택 -->
      <div class="grow-[2]">
        <FormField v-slot="{ componentField }" name="gugun">
          <FormItem>
            <Select
              v-model="selectedSigungu"
              v-bind="componentField"
              :disabled="isGugunDisabled"
            >
              <FormControl>
                <SelectTrigger>
                  <SelectValue
                    :placeholder="
                      isGugunDisabled
                        ? '시/도를 먼저 선택하세요'
                        : '시/군/구 선택'
                    "
                  />
                </SelectTrigger>
              </FormControl>
              <SelectContent>
                <SelectGroup v-if="!isGugunDisabled">
                  <SelectLabel>시/군/구</SelectLabel>
                  <SelectItem value="all">전체</SelectItem>
                  <SelectItem
                    :value="String(sigungu.id)"
                    v-for="sigungu in filteredSigungu"
                    :key="sigungu.id"
                  >
                    {{ sigungu.name }}
                  </SelectItem>
                </SelectGroup>
              </SelectContent>
            </Select>
            <FormMessage />
          </FormItem>
        </FormField>
      </div>

      <!-- 카테고리 선택 -->
      <div class="grow-[2]">
        <FormField v-slot="{ componentField }" name="content">
          <FormItem>
            <Select v-model="selectedContent" v-bind="componentField">
              <FormControl>
                <SelectTrigger>
                  <SelectValue placeholder="카테고리 선택" />
                </SelectTrigger>
              </FormControl>
              <SelectContent>
                <SelectGroup>
                  <SelectLabel>카테고리</SelectLabel>
                  <SelectItem value="all">전체</SelectItem>
                  <SelectItem
                    :value="String(category.id)"
                    v-for="category in categoryList"
                    :key="category.id"
                  >
                    {{ category.name }}
                  </SelectItem>
                </SelectGroup>
              </SelectContent>
            </Select>
            <FormMessage />
          </FormItem>
        </FormField>
      </div>

      <!-- 키워드 검색 -->
      <div class="grow-[3]">
        <Input
          id="search"
          type="text"
          placeholder="키워드 검색..."
          class="pl-10"
          v-model="keyword"
        />
      </div>

      <!-- 검색 버튼 -->
      <div class="flex items-center grow-[1]">
        <Button variant="outline" class="w-full"> 검색 </Button>
      </div>
    </div>
  </Form>
</template>

<style scoped>
.flex {
  align-items: flex-start;
}

.grow-[2] {
  min-width: 150px;
}

.grow-[3] {
  min-width: 200px;
}

.grow-[1] {
  min-width: 80px;
}
</style>
