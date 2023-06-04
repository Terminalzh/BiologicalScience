import { Show, createMemo } from "solid-js";

export type CategoryType = "order" | "family" | "genus";

export interface CategoryBadgeProps {
  latinName: string;
  chineseName?: string;
  type?: CategoryType;
}

export default function CategoryBadge(props: CategoryBadgeProps) {
  return (
    <span
      class={`px-2 py-1 inline-flex flex-col items-center rounded-3 px-2 py-2`}
      classList={{
        "bg-green-7/20": props.type === "order",
        "bg-cyan-7/20": props.type === "family",
        "bg-red-7/20": props.type === "genus",
      }}
    >
      <span
        class={`font-playfair italic font-bold text-xs  leading-none `}
        classList={{
          "text-green-7/87": props.type === "order",
          "text-cyan-7/87": props.type === "family",
          "text-red-7/87": props.type === "genus",
        }}
      >
        {props.latinName || props.chineseName}
      </span>
      <Show when={props.chineseName}>
        <span
          class={`font-bold text-xs leading-none`}
          classList={{
            "text-green-7/87": props.type === "order",
            "text-cyan-7/87": props.type === "family",
            "text-red-7/87": props.type === "genus",
          }}
        >
          {props.chineseName}
        </span>
      </Show>
    </span>
  );
}
