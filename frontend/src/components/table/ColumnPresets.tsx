import { JSX } from "solid-js";
import { PictureTarget, Pictures } from "~/utils/pictures";

import localizedFormat from "dayjs/plugin/localizedFormat";
import dayjs from "dayjs";
import { AspectRatio, Avatar, Image, Spinner } from "@hope-ui/solid";

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
  if (!pictures) {
    return (
      <AspectRatio
        ratio={16 / 10}
        class="dark:bg-white/5 rounded-2xl"
        width="6rem"
      >
        <p class="text-secondary text-center italic">图片缺失</p>
      </AspectRatio>
    );
  }
  try {
    const images = JSON.parse(pictures) as Pictures;
    if (images) {
      return (
        <AspectRatio ratio={16 / 10} width="6rem">
          return <Image src={images[target]} class="h-12 rounded-2xl" />;
        </AspectRatio>
      );
    }
  } catch (e) {}
  return (
    <AspectRatio ratio={16 / 10} width="6rem">
      <Image src={pictures} class="rounded-2xl" />
    </AspectRatio>
  );
};

export const AvatarColumn = (pictures: string) => {
  try {
    const images = JSON.parse(pictures) as Pictures;
    return <Avatar src={images["m"]} />;
  } catch (e) {
    return <Avatar src={pictures} />;
  }
};
