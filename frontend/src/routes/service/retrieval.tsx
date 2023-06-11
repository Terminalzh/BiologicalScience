import {
  Button,
  Input,
  Skeleton,
  SkeletonText,
  Spinner,
  notificationService,
} from "@hope-ui/solid";
import {
  For,
  Show,
  Suspense,
  batch,
  createEffect,
  createResource,
  createSignal,
  untrack,
} from "solid-js";
import { PaginationParams } from "~/api/base";
import { CategoryFilter } from "~/components/SpeciesSearcher";
import SpeciesCard from "~/components/species/SpeciesCard";
import Pagination from "~/components/table/Pagination";
import { createStore } from "solid-js/store";
import { SearchParams, searchSpecies } from "~/api/species";
import { ErrorBoundary, useSearchParams } from "solid-start";
import { createForm } from "@felte/solid";
import { catchResource } from "~/utils";

const SkeletonItem = () => {
  return (
    <div class="light:bg-dark/5 dark:bg-light/5 rounded-std overflow-hidden">
      <div>
        <Skeleton height="8rem" />
      </div>
      <div class="mt-6 px-3xl pb-3xl">
        <SkeletonText mt="$4" noOfLines={4} spacing="$4" />
      </div>
    </div>
  );
};

export default function RetrievalPage() {
  const [params, setParams] = createSignal<SearchParams>(
    {
      level: "1",
      keyword: "",
      pagination: {
        pageNum: 1,
        pageSize: 12,
      },
    },
    { equals: false }
  );

  const [searchParams, setSearchParams] = useSearchParams();

  const [searchResource] = createResource(params, searchSpecies);
  const searchResult = catchResource(searchResource, () => {});

  const setKeyword = (keyword: string) => {
    setParams((prev) => ({
      keyword,
      level: prev.level,
      pagination: prev.pagination,
    }));
  };

  const setPagination = (pagination: PaginationParams) => {
    setParams((prev) => ({
      keyword: prev.keyword,
      level: prev.level,
      pagination,
    }));
  };

  const setLevel = (level: string) => {
    setParams((prev) => ({
      keyword: prev.keyword,
      level,
      pagination: prev.pagination,
    }));
  };

  createEffect(() => {
    batch(() => {
      setPagination({
        pageNum: 1,
        pageSize: 12,
      });
      setKeyword(searchParams.keyword || "");
    });
  });

  const { form } = createForm({
    onSubmit(values) {
      setSearchParams({ keyword: values.keyword || "" });
    },
  });

  return (
    <section class="container-compact">
      <div class="flex justify-between py-1 items-end gap-2">
        <div>
          {/* <h2 class="font-bold font-sans text-brand-primary/87 text-lg">
            哺乳纲
          </h2> */}
          <h1 class="font-bold font-sans text-primary text-5xl">物种检索</h1>
        </div>
        <form ref={form} class="flex gap-4">
          <Input
            placeholder="关键词搜索"
            name="keyword"
            value={searchParams.keyword || ""}
          ></Input>
          <Button type="submit" class="btn">
            搜索
          </Button>
        </form>
      </div>
      <div class="py-2">
        <h3 class="text-brand-primary/87 font-bold leading-loose">筛选</h3>
        <CategoryFilter
          class="grid grid-cols-4 gap-2"
          onSelected={(level) => {
            batch(() => {
              setPagination({
                pageNum: 1,
                pageSize: 12,
              });
              setLevel(level);
            });
          }}
        />
      </div>
      <div class="mt-4">
        <Show
          when={!searchResource.loading}
          fallback={
            <div class="grid grid-cols-4 gap-4 mb-4">
              <For each={["", "", "", "", "", "", "", "", "", "", "", ""]}>
                {(_) => <SkeletonItem />}
              </For>
            </div>
          }
        >
          <div class="grid grid-cols-4 gap-4">
            <For each={searchResult()?.list}>
              {(item) => <SpeciesCard size="sm" data={item} />}
            </For>
          </div>
          <Show
            when={!searchResult() && !searchResource.loading}
            fallback={
              <Pagination
                pagination={{
                  total: searchResult()?.pages || 0,
                  pageNum: searchResult()?.pageNum || 1,
                  pageSize: searchResult()?.pageSize || 10,
                }}
                onChanged={(page) => {
                  setPagination(page);
                }}
              />
            }
          >
            <div class="flex items-center min-h-40vh justify-center">
              <p class="text-xl text-secondary italic text-center">
                没有找到关于 “
                <span class="text-brand-primary/67">{params().keyword}</span> ”
                的相关信息
              </p>
            </div>
          </Show>
        </Show>
      </div>
    </section>
  );
}
