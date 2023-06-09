import { createForm } from "@felte/solid";
import {
  Box,
  Button,
  ButtonGroup,
  IconButton,
  Input,
  Popover,
  PopoverArrow,
  PopoverBody,
  PopoverCloseButton,
  PopoverContent,
  PopoverFooter,
  PopoverHeader,
  PopoverTrigger,
} from "@hope-ui/solid";
import { batch, createEffect, createSignal, For, JSX, Setter } from "solid-js";
import { PaginationEntity, PaginationParams } from "~/api/base";

export function SolarForward2BoldDuotone(props: JSX.IntrinsicElements["svg"]) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="1.3em"
      height="1.3em"
      viewBox="0 0 24 24"
      {...props}
    >
      <g>
        <path
          fill-rule="evenodd"
          d="M13.97 17.53a.75.75 0 0 0 1.06 0l5-5a.75.75 0 0 0 0-1.06l-5-5a.75.75 0 1 0-1.06 1.06L18.44 12l-4.47 4.47a.75.75 0 0 0 0 1.06Z"
          clip-rule="evenodd"
        ></path>
        <path
          d="M17.69 12.75H9.5c-.953 0-2.367-.28-3.563-1.141C4.702 10.719 3.75 9.244 3.75 7a.75.75 0 1 1 1.5 0c0 1.756.715 2.78 1.563 3.391c.887.639 1.974.859 2.687.859h8.19l.75.75l-.75.75Zm2.503-.463Z"
          opacity=".5"
        ></path>
      </g>
    </svg>
  );
}

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

  const { form } = createForm({
    onSubmit(values, context) {
      batch(() => {
        props.pageSetter((prev) => ({
          pageNum: values.pageForward,
          pageSize: prev.pageSize,
        }));
        setCurrent(values.pageForward);
      });
    },
  });

  return (
    <div class="flex justify-center py-4 select-none flex-wrap">
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
      <section class="ml-4 flex items-center gap-4">
        <form ref={form} class="flex items-center gap-2">
          <Input
            type="number"
            name="pageForward"
            max={pageCount()}
            min={1}
            placeholder="页码"
            required
          />
          <Button type="submit" size="sm" class="btn">
            转跳
          </Button>
        </form>
        <span class="text-secondary">
          共 <span class="text-brand-primary/87">{pageCount()}</span> 页
        </span>
      </section>
    </div>
  );
}
