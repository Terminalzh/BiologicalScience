import { Button, IconButton } from "@hope-ui/solid";
import { batch, createEffect, createSignal, For, JSX, Setter } from "solid-js";
import { PaginationEntity, PaginationParams } from "~/api/base";

export function SolarAltArrowLeftLineDuotone(
  props: JSX.IntrinsicElements["svg"]
) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="1.3em"
      height="1.3em"
      viewBox="0 0 24 24"
      {...props}
    >
      <path
        fill="none"
        stroke-linecap="round"
        stroke-linejoin="round"
        stroke-width="2.5"
        d="m15 5l-6 7l6 7"
      ></path>
    </svg>
  );
}

export function SolarAltArrowRightLineDuotone(
  props: JSX.IntrinsicElements["svg"]
) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="1.3em"
      height="1.3em"
      viewBox="0 0 24 24"
      {...props}
    >
      <path
        fill="none"
        stroke-linecap="round"
        stroke-linejoin="round"
        stroke-width="2.5"
        d="m9 5l6 7l-6 7"
      ></path>
    </svg>
  );
}

export default function Pagination(props: {
  pagination: PaginationEntity;
  pageSetter: Setter<PaginationParams>;
}) {
  let beforeOffset = 0;
  let left = 0;
  let right = 0;
  let afterOffset = 0;
  const [pageArray, setPageArray] = createSignal<Array<number>>();
  let total = 0;
  let page_size = 0;
  const [current, setCurrent] = createSignal<number>(props.pagination.pageNum);
  const [pageCount, setPageCount] = createSignal<number>(0);
  createEffect(() => {
    batch(() => {
      total = props.pagination?.total || 0;
      page_size = props.pagination?.pageSize || 0;
      setPageCount(total);
      beforeOffset = current() - 5;

      left = Math.max(1, beforeOffset);
      right = Math.min(current() + 4, pageCount());

      afterOffset = pageCount() - current() - 5;

      if (beforeOffset < 0) {
        right = Math.min(pageCount(), right - beforeOffset);
      }

      if (afterOffset < 0) {
        left = Math.max(1, afterOffset + left);
      }
      const pageArray = new Array<number>();
      for (let i = left; i <= right; i++) {
        pageArray.push(i);
      }
      setPageArray(pageArray);
    });
  });

  return (
    <div class="flex justify-center py-4 select-none">
      <section class="flex items-center">
        <IconButton
          icon={
            <SolarAltArrowLeftLineDuotone
              class={`${
                current() <= 1
                  ? "light:stroke-black/50 dark:stroke-white/30"
                  : "light:stroke-black/87 dark:stroke-white/87 "
              }`}
            />
          }
          disabled={current() <= 1}
          variant="ghost"
          class={`rounded-2xl ${
            current() <= 1
              ? "hover:bg-transparent"
              : "hover:bg-brand-primary/15"
          }`}
          aria-label="Previous"
          onClick={() => {
            if (current() <= 1) {
              return;
            }
            batch(() => {
              props.pageSetter((prev) => ({
                pageNum: prev.pageNum - 1,
                pageSize: prev.pageSize,
              }));
              setCurrent((p) => p - 1);
            });
          }}
        />
      </section>
      <section class="flex">
        <For each={pageArray()}>
          {(item) => (
            <Button
              onClick={() => {
                batch(() => {
                  props.pageSetter((prev) => ({
                    pageNum: item,
                    pageSize: prev.pageSize,
                  }));
                  setCurrent(item);
                });
              }}
              variant={item === current() ? "subtle" : "ghost"}
              class={`px-3 rounded-2xl font-bold tracking-tight ${
                item === current()
                  ? "bg-brand-primary/15 text-brand-primary/87  hover:bg-brand-primary/20"
                  : "text-primary light:hover:bg-dark/5 dark:hover:bg-white/5"
              }`}
            >
              {item}
            </Button>
          )}
        </For>
      </section>
      <section class="flex items-center">
        <IconButton
          icon={
            <SolarAltArrowRightLineDuotone
              class={`${
                current() >= pageCount()
                  ? "light:stroke-black/50 dark:stroke-white/30"
                  : "light:stroke-black/87 dark:stroke-white/87 "
              }`}
            />
          }
          variant="ghost"
          class={`rounded-2xl ${
            current() >= pageCount()
              ? "hover:bg-transparent"
              : "hover:bg-brand-primary/15"
          }`}
          aria-label="Next"
          disabled={current() >= pageCount()}
          onClick={() => {
            if (current() >= pageCount()) {
              return;
            }
            batch(() => {
              props.pageSetter((prev) => ({
                pageNum: prev.pageNum + 1,
                pageSize: prev.pageSize,
              }));
              setCurrent((p) => p + 1);
            });
          }}
        />
      </section>
      <section class="ml-4 flex items-center">
        <span class="text-secondary">
          共 <span class="text-brand-primary/87">{pageCount()}</span> 页
        </span>
      </section>
    </div>
  );
}
