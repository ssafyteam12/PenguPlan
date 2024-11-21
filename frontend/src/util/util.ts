import { Attraction } from "@/type/type";
import NoImage from "@/assets/image/no-image.png";

export const toPlainObject = <T>(proxyObject: T): T => {
  return JSON.parse(JSON.stringify(proxyObject));
};

export const extractNoAndTitle = (
  attractions: Attraction[]
): { no: number; title: string; day?: number; sequence?: number }[] => {
  return attractions.map(({ no, title, day, sequence }) => ({
    no,
    title,
    day,
    sequence,
  }));
};

export const formatDate = (year?: number, month?: number, day?: number) => {
  const pad = (num: number) => String(num).padStart(2, "0");

  const date =
    year && month && day ? `${year}-${pad(month)}-${pad(day)}` : undefined;

  return date;
};

export const getImageOrDefault = (image?: string): string => {
  return image || NoImage; // 기본 이미지 URL
};
