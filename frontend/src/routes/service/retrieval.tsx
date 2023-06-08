import { Button, Input } from "@hope-ui/solid";
import { For, createSignal } from "solid-js";
import { PaginationParams } from "~/api/base";
import { SpeciesSearcher } from "~/components/SpeciesSearcher";
import SpeciesCard, {
  SpeciesCardProps,
} from "~/components/species/SpeciesCard";
import Pagination from "~/components/table/Pagination";
import pic from "~/assets/images/animal1.webp";

const test: Array<SpeciesCardProps> = [
  {
    src: pic,
    chineseName: "南方登鼠",
    latinName: "Rhipidomys",
  },
  {
    src: pic,
    chineseName: "南方登鼠",
    latinName: "Rhipidomys",
  },
  {
    src: pic,
    chineseName: "南方登鼠",
    latinName: "Rhipidomys",
  },
  {
    src: pic,
    chineseName: "南方登鼠",
    latinName: "Rhipidomys",
  },
];

export default function RetrievalPage() {
  const [pageParam, setPageParam] = createSignal<PaginationParams>({
    pageNum: 1,
    pageSize: 10,
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
        <div class="flex gap-4">
          <Input placeholder="关键词搜索"></Input>
          <Button class="btn">搜索</Button>
        </div>
      </div>
      <div class="py-2">
        <h3 class="text-brand-primary/87 font-bold leading-loose">筛选</h3>
        <SpeciesSearcher class="grid grid-cols-4 gap-2" />
      </div>
      <div class="mt-4">
        <div class="grid grid-cols-4 gap-4">
          <For each={test}>{(item) => <SpeciesCard size="sm" {...item} />}</For>
        </div>
        <Pagination
          pagination={{ pageNum: 1, pageSize: 10, total: 10 }}
          pageSetter={setPageParam}
        />
      </div>
    </section>
  );
}
