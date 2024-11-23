<!-- <script setup lang="ts">
import { type Ref, ref, defineEmits } from "vue";
import { planStore } from "@/store/store";
import { storeToRefs } from "pinia";
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
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from "@/components/ui/form";
import Button from "@/components/ui/button/Button.vue";
import { RangeCalendar } from "@/components/ui/range-calendar";
import { toTypedSchema } from "@vee-validate/zod";
import { z } from "zod";
import { useForm } from "vee-validate";
import { DialogClose, type DateRange } from "radix-vue";
import { sidoList } from "./location";
import { categoryList } from "./contenttype";
import { getLocalTimeZone, today } from "@internationalized/date";
import { formatDate } from "@/util/util";

const pStore = planStore();

const currentStep = ref(0);

const nextStep = () => {
  if (currentStep.value < 2) {
    currentStep.value++;
  }
};

const selectedSido = ref("");

const formSchema = toTypedSchema(
  z.object({
    sido: z.string().optional(),
    gugun: z.string().optional(),
    content: z.string().optional(),
  })
);

const start = today(getLocalTimeZone());
const end = start.add({ days: 7 });

const dateValue = ref({
  start,
  end,
}) as Ref<DateRange>;

const initialValues = {
  sido: "",
  gugun: "",
  content: "",
};

const form = useForm({
  validationSchema: formSchema,
  initialValues,
});

const emit = defineEmits(["submitForm"]);

const onSubmit = form.handleSubmit(async (values) => {
  const returnForm = {
    ...values,
    startDate: formatDate(
      dateValue.value.start?.year,
      dateValue.value.start?.month,
      dateValue.value.start?.day
    ),
    endDate: formatDate(
      dateValue.value.end?.year,
      dateValue.value.end?.month,
      dateValue.value.end?.day
    ),
    dateRange: {
      from_year: dateValue.value.start?.year,
      from_month: dateValue.value.start?.month,
      from_day: dateValue.value.start?.day,
      to_year: dateValue.value.end?.year,
      to_month: dateValue.value.end?.month,
      to_day: dateValue.value.end?.day,
    },
  };

  pStore.setContent(returnForm.content);
  pStore.setSidoCode(returnForm.sido);
  pStore.setDate(returnForm.startDate, returnForm.endDate);

  emit("submitForm");
});
</script>

<template>
  <div
    class="relative w-[400px] mx-auto mt-10 bg-white rounded-lg overflow-hidden font-main"
  >
    <div
      class="flex transition-transform duration-500 ease-in-out"
      :style="{ transform: `translateX(-${currentStep * 100}%)` }"
    >
      <div class="w-[400px] flex-shrink-0 p-6">
        <label class="text-lg font-medium text-gray-700"
          >여행지를 선택하세요!</label
        >
        <FormField v-slot="{ componentField }" name="sido">
          <FormItem>
            <Select v-model="selectedSido" v-bind="componentField">
              <FormControl>
                <SelectTrigger>
                  <SelectValue placeholder="시/도 선택" />
                </SelectTrigger>
              </FormControl>
              <SelectContent>
                <SelectGroup>
                  <SelectLabel>시/도</SelectLabel>
                  <SelectItem
                    :value="String(sido.id)"
                    v-for="sido in sidoList"
                    :key="sido.id"
                  >
                    <div>{{ sido.name }}</div>
                  </SelectItem>
                </SelectGroup>
              </SelectContent>
            </Select>
            <FormMessage />
          </FormItem>
        </FormField>
        <Button
          @click="nextStep"
          class="mt-6 w-full py-3 rounded-lg bg-gradient-to-r text-sm font-semibold hover:opacity-90 transition-opacity duration-300"
        >
          다음
        </Button>
      </div>

      <div class="w-[400px] flex-shrink-0 p-6">
        <label class="text-lg font-medium text-gray-700"
          >무엇을 보러 가시나요?</label
        >
        <FormField v-slot="{ componentField }" name="content">
          <FormItem>
            <Select v-model="form.values.content" v-bind="componentField">
              <FormControl>
                <SelectTrigger>
                  <SelectValue placeholder="카테고리 선택" />
                </SelectTrigger>
              </FormControl>
              <SelectContent>
                <SelectGroup>
                  <SelectLabel>카테고리</SelectLabel>
                  <SelectItem
                    :value="String(category.id)"
                    v-for="category in categoryList"
                    :key="category.id"
                    >{{ category.name }}</SelectItem
                  >
                </SelectGroup>
              </SelectContent>
            </Select>
            <FormMessage />
          </FormItem>
        </FormField>

        <Button
          @click="nextStep"
          class="mt-6 w-full py-3 rounded-lg bg-gradient-to-r text-sm font-semibold hover:opacity-90 transition-opacity duration-300"
        >
          다음
        </Button>
      </div>

      <div class="w-[400px] flex-shrink-0 p-6">
        <label class="text-lg font-medium text-gray-700"
          >언제 여행하시나요?</label
        >
        <FormField v-slot="{ componentField }" name="dateRange">
          <FormItem>
            <RangeCalendar v-model="dateValue" class="rounded-md border" />
            <FormMessage />
          </FormItem>
        </FormField>

        <DialogClose as-child>
          <Button
            @click="onSubmit"
            class="mt-6 w-full py-3 rounded-lg bg-gradient-to-r text-sm font-semibold hover:opacity-90 transition-opacity duration-300"
          >
            제출
          </Button>
        </DialogClose>
      </div>
    </div>
  </div>
</template>

<style scoped></style> -->
<script setup>
import { ref } from "vue";
import { Button } from "@/components/ui/button";
import CitySelection from "./CitySelection.vue";
import CategorySelection from "./CategorySelection.vue";
import { RangeCalendar } from "@/components/ui/range-calendar";

const currentStep = ref(0);
const selectedCity = ref("");
const selectedCategory = ref("");
const dateValue = ref("");

const nextStep = () => {
  if (currentStep.value < 2) {
    currentStep.value++;
  }
};

const onSubmit = () => {
  // 폼 제출 로직
  console.log({
    city: selectedCity.value,
    category: selectedCategory.value,
    dates: dateValue.value,
  });
};
</script>

<template>
  <div
    class="relative w-full mx-auto bg-white rounded-lg overflow-hidden font-main"
  >
    <div
      class="flex transition-transform duration-500 ease-in-out"
      :style="{ transform: `translateX(-${currentStep * 100}%)` }"
    >
      <!-- Step 1: 도시 선택 -->
      <div class="w-[80%] mx-[10%] flex-shrink-0">
        <CitySelection v-model="selectedCity" />
        <Button
          @click="nextStep"
          class="mx-6 mb-6 w-[calc(100%-48px)] py-3 rounded-lg bg-gradient-to-r from-blue-500 to-blue-600 text-white text-sm font-semibold hover:opacity-90 transition-opacity duration-300"
        >
          다음
        </Button>
      </div>

      <!-- Step 2: 카테고리 선택 -->
      <div class="w-[80%] mx-[10%] flex-shrink-0">
        <CategorySelection v-model="selectedCategory" />
        <Button
          @click="nextStep"
          class="mx-6 mb-6 w-[calc(100%-48px)] py-3 rounded-lg bg-gradient-to-r from-blue-500 to-blue-600 text-white text-sm font-semibold hover:opacity-90 transition-opacity duration-300"
        >
          다음
        </Button>
      </div>

      <!-- Step 3: 날짜 선택 -->
      <div class="w-[80%] mx-[10%] flex-shrink-0 p-6">
        <label class="text-lg font-medium text-gray-700"
          >언제 여행하시나요?</label
        >
        <RangeCalendar v-model="dateValue" class="rounded-md border" />
        <Button
          @click="onSubmit"
          class="mt-6 w-full py-3 rounded-lg bg-gradient-to-r from-blue-500 to-blue-600 text-white text-sm font-semibold hover:opacity-90 transition-opacity duration-300"
        >
          제출
        </Button>
      </div>
    </div>
  </div>
</template>
