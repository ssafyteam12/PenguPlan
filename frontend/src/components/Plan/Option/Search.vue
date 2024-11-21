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
import { sigunguList } from "@/components/Plan/Setting/location";
import { getAttractionBySidoGugun } from "@/api/Attraction/Attraction";

const pStore = planStore();
const aStore = attractionStore();
const selectedSigungu = ref("");
const keyword = ref("");

const filteredSigungu = computed(() => {
  return sigunguList.filter(
    (sigungu: Sigungu) => sigungu.sidoId === parseInt(pStore.sidoCode)
  );
});

const handleSearch = async (e?: Event) => {
  if (e?.preventDefault) {
    e.preventDefault();
  }
  const returnAttractions = await getAttractionBySidoGugun(
    pStore.sidCode,
    selectedSigungu.value,
    pStore.content,
    keyword.value
  );
  console.log(pStore.sidCode);
  aStore.setAttractionList(returnAttractions.attractions);
};
</script>

<template>
  <Form @submit="handleSearch">
    <div class="flex gap-2">
      <div class="grow-[2]">
        <FormField v-slot="{ componentField }" name="gugun" class="w-[150px]">
          <FormItem>
            <Select v-model="selectedSigungu" v-bind="componentField">
              <FormControl>
                <SelectTrigger>
                  <SelectValue placeholder="시/군/구 선택" />
                </SelectTrigger>
              </FormControl>
              <SelectContent>
                <SelectGroup>
                  <SelectLabel>시/군/구</SelectLabel>
                  <SelectItem
                    :value="String(sigungu.id)"
                    v-for="sigungu in filteredSigungu"
                    :key="sigungu.id"
                    >{{ sigungu.name }}</SelectItem
                  >
                </SelectGroup>
              </SelectContent>
            </Select>
            <FormMessage />
          </FormItem>
        </FormField>
      </div>
      <div class="grow-[3]">
        <Input
          id="search"
          type="text"
          placeholder="Search..."
          class="pl-10"
          v-model="keyword"
        />
      </div>
      <div class="flex items-center grow-[1]">
        <Button variant="outline"> 검색</Button>
      </div>
    </div>
  </Form>
</template>

<style scoped></style>
