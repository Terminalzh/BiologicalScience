import {
  AspectRatio,
  IconButton,
  Image,
  notificationService,
} from "@hope-ui/solid";
import {
  For,
  JSX,
  createMemo,
  createResource,
  createSignal,
  onMount,
  untrack,
} from "solid-js";
import pic from "~/assets/images/ivan.webp";
import pic1 from "~/assets/images/lion.webp";
import pic2 from "~/assets/images/animal.webp";
import { Queue, catchResource } from "~/utils";
import { createForm } from "@felte/solid";
import { useNavigate } from "solid-start";
import { PaginationParams } from "~/api/base";
import { listBanners } from "~/api/banner";
import Picture from "../Picture";

const getBanner = (pagination: PaginationParams) => {
  return listBanners(pagination.pageNum, pagination.pageSize);
};

export function PhMagnifyingGlassBold(props: JSX.IntrinsicElements["svg"]) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="1.5em"
      height="1.5em"
      viewBox="0 0 256 256"
      {...props}
    >
      <path
        fill="currentColor"
        d="M232.49 215.51L185 168a92.12 92.12 0 1 0-17 17l47.53 47.54a12 12 0 0 0 17-17ZM44 112a68 68 0 1 1 68 68a68.07 68.07 0 0 1-68-68Z"
      ></path>
    </svg>
  );
}

export default function LandingPage() {
  const bannerPics = [pic, pic1, pic2].map((value) => (
    <Image
      src={value}
      objectFit="cover"
      borderRadius="3rem"
      shadow="$md"
      class="hover:brightness-110 transition-all cursor-pointer animate-fade-in"
    />
  ));
  
  const [pos, setPos] = createSignal(0);
  const imageQueue = new Queue<JSX.Element>();
  imageQueue.enqueue(bannerPics[pos()]);
  setPos((prev) => prev + 1);
  imageQueue.enqueue(bannerPics[pos()]);
  setPos((prev) => prev + 1);
  const navigate = useNavigate();

  const [queue, setQueue] = createSignal(imageQueue, {
    equals: false,
  });

  const [page, setPage] = createSignal<PaginationParams>({
    pageNum: 1,
    pageSize: 4,
  });

  const [bannerResource] = createResource(page, getBanner);
  const bannerResult = catchResource(bannerResource, (e) => {
    untrack(() => {
      notificationService.show({
        title: "轮播图加载失败",
        status: "danger",
        description: e.message,
      });
    });
  });

  onMount(() => {
    setInterval(() => {
      setQueue((prev) => {
        prev.dequeue();
        untrack(() => {
          prev.enqueue(bannerPics[pos() % bannerPics.length]);
        });
        return prev;
      });
      setPos((prev) => prev + 1);
    }, 3000);
  });

  const { form } = createForm({
    onSubmit(values) {
      navigate(`retrieval?keyword=${values.keyword}`);
    },
  });

  return (
    <div class="container-compact flex gap-12 items-center pt-15">
      <div class="flex-1">
        <h1 class="text-primary text-16 font-slogan">
          与生俱来的温柔力量，孕育出美好世界
          <span class="text-brand-primary">。</span>
        </h1>
        <p class="text-secondary text-body">
          哺乳动物在地球上已经存在了约2.2亿年，现在已经分布在全球各地，包括陆地、海洋和空气中。哺乳动物的种类非常丰富，包括鲸、象、猫、狗、猴子、鼠类和人类等。它们在生态系统中扮演着重要的角色，包括控制猎物数量、传播花粉、分解有机物和维持生态平衡等。
        </p>
        <form
          ref={form}
          class="mt-6 border-brand-primary/87 border-solid border-4 flex items-center justify-between rounded-full pl-10 pr-4 py-4"
        >
          <input
            type="text"
            name="keyword"
            placeholder="探索更多哺乳动物..."
            class="focus:outline-none bg-transparent outline-0  text-primary text-2xl placeholder:text-brand-primary/50 placeholder:font-500 flex-1"
          />
          <IconButton
            size="lg"
            type="submit"
            class="rounded-full bg-brand-primary/87"
            icon={<PhMagnifyingGlassBold />}
            aria-label="搜索"
          />
        </form>
      </div>
      <div class="w-md">
        <AspectRatio ratio={16 / 10}>
          <For each={bannerResult()?.list}>
            {(item) => (
              <Picture
                value={item.species.pictureUrl || item.species.betterUrl}
                class="hover:brightness-110 transition-all cursor-pointer animate-fade-in rounded-3rem shadow-md"
              />
            )}
          </For>
        </AspectRatio>
        <div class="mt-4">
          <ul class="list-none flex justify-center gap-2 h-2">
            <For each={bannerResult()?.list}>
              {(_, index) => {
                const isCurrent = createMemo(
                  () => pos() % bannerPics.length === index()
                );
                return (
                  <li
                    class="rounded-full w-2 transition-all cursor-pointer"
                    onClick={() => {
                      if (!isCurrent()) {
                      }
                    }}
                    classList={{
                      "bg-brand-primary": isCurrent(),
                      "light:bg-neutral-2 dark:bg-neutral-5": !isCurrent(),
                    }}
                  ></li>
                );
              }}
            </For>
          </ul>
        </div>
      </div>
    </div>
  );
}
