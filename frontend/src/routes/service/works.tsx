import { AspectRatio, Skeleton } from "@hope-ui/solid";
import {
  createSignal,
  createResource,
  For,
  ErrorBoundary,
  Suspense,
} from "solid-js";
import { PaginationParams } from "~/api/base";
import { WorkItem, listWorks } from "~/api/works";
import Picture from "~/components/Picture";
import Pagination from "~/components/table/Pagination";

const Work = (props: { data: WorkItem }) => {
  return (
    <AspectRatio ratio={16 / 10} class="w-full">
      <Picture value={props.data.imageUrl} class="rounded-std"></Picture>
    </AspectRatio>
  );
};

const SkeletonItem = () => {
  return (
    <div class="light:bg-dark/5 dark:bg-light/5 rounded-std overflow-hidden">
      <div>
        <Skeleton height="8rem" />
      </div>
    </div>
  );
};

export default function WorksPage() {
  const [page, setPage] = createSignal<PaginationParams>({
    pageNum: 1,
    pageSize: 20,
  });

  const [worksResource] = createResource(page, listWorks);

  return (
    <section class="container-compact">
      <div class="flex justify-between py-1 items-end gap-2 pb-8">
        <div>
          <h1 class="font-bold font-sans text-primary text-5xl">摄影作品</h1>
        </div>
      </div>
      <ErrorBoundary
        fallback={
          <div class="flex items-center justify-center min-h-30vh">
            <p class="text-xl text-secondary italic text-center">加载失败</p>
          </div>
        }
      >
        <div>
          <ul class="list-none grid grid-cols-5 gap-8">
            <Suspense
              fallback={
                <For each={new Array(20)}>{(_) => <SkeletonItem />}</For>
              }
            >
              <For each={worksResource()?.list}>
                {(item) => <Work data={item} />}
              </For>
            </Suspense>
          </ul>
        </div>
        <div class="mt-8">
          <Pagination
            pagination={{
              total: worksResource()?.pages || 0,
              pageNum: worksResource()?.pageNum || 1,
              pageSize: worksResource()?.pageSize || 10,
            }}
            onChanged={(page) => {
              setPage(page);
            }}
          />
        </div>
      </ErrorBoundary>
    </section>
  );
}
