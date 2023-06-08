import { JSX } from "solid-js";
import { PictureTarget, Pictures } from "~/utils/pictures";

import localizedFormat from "dayjs/plugin/localizedFormat";
import dayjs from "dayjs";
import { Avatar, Image } from "@hope-ui/solid";

dayjs.extend(localizedFormat);

export function formatDate(date: string): string {
  return dayjs(date).format("L");
}

export function formatDateTime(date: string): string {
  return dayjs(date).format("L LT");
}
/**
 * 展示时间的一个表格列
 * @param date ISO8016的字符串
 * @returns
 */
export const DateTimeColumn = (date: string): JSX.Element => {
  const result = formatDateTime(date);
  return <span title={result}>{result}</span>;
};

export const DateColumn = (date: string): JSX.Element => {
  const result = formatDate(date);
  return <span title={result}>{result}</span>;
};

/**
 * 展示图片的一个表格列
 * @param url 图片的URL
 * @returns
 */
export const PictureColumn = (
  pictures: string,
  target: PictureTarget = "m"
): JSX.Element => {
  try {
    const images = JSON.parse(pictures) as Pictures;
    return <Image src={images[target]} class="h-12 rounded-2xl" />;
  } catch (e) {
    return <Image src={pictures} class="h-12 rounded-2xl" />;
  }
};

export const AvatarColumn = (pictures: string) => {
  try {
    const images = JSON.parse(pictures) as Pictures;
    return <Avatar src={images["m"]} />;
  } catch (e) {
    return <Avatar src={pictures} />;
  }
};
