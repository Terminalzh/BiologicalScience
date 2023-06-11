import { Image } from "@hope-ui/solid";
import { JSX, createMemo, Show } from "solid-js";
import { PictureTarget, Pictures } from "~/utils/pictures";

export function SolarGalleryRemoveBoldDuotone(
  props: JSX.IntrinsicElements["svg"]
) {
  return (
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" {...props}>
      <g fill="currentColor">
        <path
          d="M22 12.698c-.002 1.47-.013 2.718-.096 3.743c-.097 1.19-.296 2.184-.74 3.009a4.18 4.18 0 0 1-.73.983c-.833.833-1.893 1.21-3.237 1.39C15.884 22 14.2 22 12.053 22h-.106c-2.148 0-3.83 0-5.144-.177c-1.343-.18-2.404-.557-3.236-1.39c-.738-.738-1.12-1.656-1.322-2.795c-.2-1.12-.236-2.512-.243-4.241C2 12.957 2 12.492 2 12v-.054c0-2.148 0-3.83.177-5.144c.18-1.343.557-2.404 1.39-3.236c.832-.833 1.893-1.21 3.236-1.39c1.168-.157 2.67-.175 4.499-.177a.697.697 0 1 1 0 1.396c-1.855.002-3.234.018-4.313.163c-1.189.16-1.906.464-2.436.994S3.72 5.8 3.56 6.99C3.397 8.2 3.395 9.788 3.395 12v.784l.932-.814a2.14 2.14 0 0 1 2.922.097l3.99 3.99a1.86 1.86 0 0 0 2.385.207l.278-.195a2.79 2.79 0 0 1 3.471.209l2.633 2.37c.265-.557.423-1.288.507-2.32c.079-.972.09-2.152.091-3.63a.698.698 0 0 1 1.396 0Z"
          opacity=".5"
        ></path>
        <path
          fill-rule="evenodd"
          d="M17.5 11c-2.121 0-3.182 0-3.841-.659C13 9.682 13 8.621 13 6.5c0-2.121 0-3.182.659-3.841C14.318 2 15.379 2 17.5 2c2.121 0 3.182 0 3.841.659C22 3.318 22 4.379 22 6.5c0 2.121 0 3.182-.659 3.841c-.659.659-1.72.659-3.841.659Zm-1.47-7.03a.75.75 0 1 0-1.06 1.06l1.47 1.47l-1.47 1.47a.75.75 0 0 0 1.06 1.06l1.47-1.47l1.47 1.47a.75.75 0 1 0 1.06-1.06L18.56 6.5l1.47-1.47a.75.75 0 0 0-1.06-1.06L17.5 5.44l-1.47-1.47Z"
          clip-rule="evenodd"
        ></path>
      </g>
    </svg>
  );
}

export default function Picture(props: {
  class?: string;
  value?: string;
  target?: PictureTarget;
}) {
  const src = createMemo(() => {
    try {
      if (props.value) {
        const url = (JSON.parse(props.value) as Pictures)[props.target || "m"];
        if (!url.startsWith("https")) {
          return url.replace("http", "https");
        }
        return url;
      } else {
        return "";
      }
    } catch (e) {
      return props.value || "";
    }
  });

  return (
    <Image
      class={props.class}
      src={src()}
      fallback={
        <div class="light:bg-dark/5 dark:bg-white/5 w-full h-full flex items-center">
          <Show
            when={props.value}
            fallback={
              <p class="text-body text-secondary italic text-center">
                图片缺失
              </p>
            }
          >
            <SolarGalleryRemoveBoldDuotone class="opacity-20 p-[7rem]" />
          </Show>
        </div>
      }
    ></Image>
  );
}
