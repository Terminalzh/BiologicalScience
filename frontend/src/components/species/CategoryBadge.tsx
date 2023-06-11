import { Spinner, Text } from "@hope-ui/solid";
import {
  ErrorBoundary,
  For,
  Show,
  Suspense,
  createEffect,
  createMemo,
} from "solid-js";

export type CategoryType = "order" | "family" | "genus" | "sub_class";

export interface CategoryBadgeProps {
  latinName?: string;
  chineseName?: string;
  type?: CategoryType;
}

export const CategoryItem = (props: {
  index: number;
  values?: Array<string>;
}) => {
  const computeType = (index: number) => {
    switch (index) {
      case 0:
        return "sub_class";
      case 1:
        return "order";
      case 2:
        return "family";
      case 3:
        return "genus";
    }
    return "genus";
  };
  return (
    <ErrorBoundary fallback={(e) => <Text>{e.message}</Text>}>
      <Suspense fallback={<Spinner />}>
        <CategoryBadge
          type={computeType(props.index)}
          latinName={props.values?.[0]}
          chineseName={props.values?.[1]}
        />
      </Suspense>
    </ErrorBoundary>
  );
};

export const Category = (props: { values?: Array<Array<string>> }) => {
  return (
    <div class="text-start flex gap2 overflow-auto scrollbar dark:scrollbar-track-color-white/5 scrollbar-thumb-color-brand-primary/10 scrollbar-rounded scrollbar-w-0.5rem scrollbar-radius-2 scrollbar-track-radius-4 scrollbar-thumb-radius-4 pb-2">
      <For each={props.values}>
        {(item, index) => {
          return <CategoryItem index={index()} values={item} />;
        }}
      </For>
    </div>
  );
};

export function CategoryBadge(props: CategoryBadgeProps) {
  return (
    <span
      class={`px-2 py-1 inline-flex flex-col items-center rounded-3 px-2 py-2`}
      classList={{
        "bg-green-7/20": props.type === "order",
        "bg-cyan-7/20": props.type === "family",
        "bg-red-7/20": props.type === "genus",
        "bg-brand-primary/20": props.type === "sub_class",
      }}
    >
      <span
        class={`font-playfair italic font-bold text-xs  leading-none truncate`}
        classList={{
          "text-green-7/87": props.type === "order",
          "text-cyan-7/87": props.type === "family",
          "text-red-7/87": props.type === "genus",
          "text-brand-primary/87": props.type === "sub_class",
        }}
      >
        {props.latinName || props.chineseName || "NULL"}
      </span>
      <Show when={props.chineseName}>
        <span
          class={`font-bold text-xs leading-none truncate`}
          classList={{
            "text-green-7/87": props.type === "order",
            "text-cyan-7/87": props.type === "family",
            "text-red-7/87": props.type === "genus",
            "text-brand-primary/87": props.type === "sub_class",
          }}
        >
          {props.chineseName}
        </span>
      </Show>
    </span>
  );
}
