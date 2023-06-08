import {
  Button,
  Image,
  Input,
  Modal,
  ModalBody,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ModalOverlay,
  Select,
  SelectContent,
  SelectIcon,
  SelectListbox,
  SelectOption,
  SelectOptionIndicator,
  SelectOptionText,
  SelectPlaceholder,
  SelectTrigger,
  SelectValue,
  Spinner,
  Text,
  createDisclosure,
  useColorMode,
} from "@hope-ui/solid";
import {
  ErrorBoundary,
  For,
  JSX,
  Show,
  Suspense,
  createEffect,
  createMemo,
  createResource,
  createSignal,
} from "solid-js";
import { getCategory } from "~/api/category";
import {
  SearchParams,
  SearchResultItem,
  getSpecies,
  searchSpecies,
} from "~/api/species";
import { PictureColumn } from "./table";
import { Pictures } from "~/utils/pictures";
import { catchResource } from "~/utils";

interface IdNode {
  id: number;
  next?: IdNode;
}

const serializeIdNode2String = (node: IdNode) => {
  let current: IdNode | undefined = node;
  let result = `${current.id}`;
  while ((current = current.next)) {
    result += `.${current.id}`;
  }
  return result;
};

const CategoryItem = (props: {
  title: string;
  node: IdNode;
  setNext: (next: IdNode) => void;
  children?: (node: IdNode) => JSX.Element;
}) => {
  const [id, setId] = createSignal<number>();
  createEffect(() => {
    setId(props.node.id);
  });
  const [selected, setSelected] = createSignal<IdNode>();
  const [categoryResource] = createResource(id, getCategory);

  return (
    <>
      <ErrorBoundary fallback={(e) => <div>Err: {e.message}</div>}>
        <Suspense
          fallback={
            <div class="flex items-center">
              <Spinner />
            </div>
          }
        >
          <Select
            onChange={(v) => {
              const node = { id: v };
              props.setNext(node);
              setSelected(node);
            }}
          >
            <SelectTrigger>
              <SelectPlaceholder>全部{props.title}</SelectPlaceholder>
              <SelectValue />
              <SelectIcon />
            </SelectTrigger>
            <SelectContent>
              <SelectListbox>
                <For each={categoryResource()}>
                  {(item) => (
                    <SelectOption
                      value={item.id}
                      textValue={`${item.latinName} ${
                        item.cName ? `（${item.cName}）` : ""
                      }`}
                    >
                      <SelectOptionText>{item.latinName}</SelectOptionText>
                      <Text>{item.cName}</Text>
                      <SelectOptionIndicator />
                    </SelectOption>
                  )}
                </For>
              </SelectListbox>
            </SelectContent>
          </Select>
        </Suspense>
      </ErrorBoundary>
      <Show when={selected()}>{props.children?.(selected()!)}</Show>
    </>
  );
};

export function CategoryFilter(props: {
  class?: string;
  onSelected?: (levels: string) => void;
}) {
  const [rootNode, setRootNode] = createSignal<IdNode>(
    { id: 1 },
    { equals: false }
  );
  const setNext = (current: IdNode, next: IdNode) => {
    setRootNode((prev) => {
      current.next = next;
      return prev;
    });
  };

  createEffect(() => {
    props.onSelected?.(serializeIdNode2String(rootNode()));
  });

  return (
    <div class={props.class}>
      <CategoryItem
        node={rootNode()}
        setNext={(next: IdNode) => {
          setNext(rootNode(), next);
        }}
        title="亚纲"
      >
        {(node) => (
          <CategoryItem
            node={node}
            setNext={(next: IdNode) => {
              setNext(node, next);
            }}
            title="目"
          >
            {(order) => (
              <CategoryItem
                node={order}
                setNext={(next: IdNode) => {
                  setNext(order, next);
                }}
                title="科"
              >
                {(family) => (
                  <CategoryItem
                    setNext={(next: IdNode) => {
                      setNext(family, next);
                    }}
                    node={family}
                    title="属"
                  />
                )}
              </CategoryItem>
            )}
          </CategoryItem>
        )}
      </CategoryItem>
    </div>
  );
}

const SearchResultBrief = (props: {
  item: SearchResultItem;
  onClick?: () => void;
}) => {
  const url = createMemo(() => {
    try {
      const pic = JSON.parse(props.item.pictureUrl) as Pictures;
      return pic.m;
    } catch (_) {
      return props.item.pictureUrl;
    }
  });

  return (
    <li
      class="flex gap-4 items-center rounded-2xl hover:dark:bg-white/5 hover:light:bg-dark/5 p-2 cursor-pointer transition-all"
      onClick={() => {
        props.onClick?.();
      }}
    >
      <div>
        <Image src={url()} class="h-10 rounded-xl" />
      </div>
      <div>
        <p class="font-bold font-playfair italic text-xl text-primary leading-none">
          {props.item.latinName}
        </p>
        <p class="font-sans text-secondary font-bold leading-none">
          {props.item.cName}
        </p>
      </div>
    </li>
  );
};

export function SpeciesSelector(props: {
  onChanged?: (item: SearchResultItem) => void;
  speciesId?: number;
}) {
  const { isOpen, onOpen, onClose } = createDisclosure();
  const [searchParam, setSearchParam] = createSignal<SearchParams>({
    level: "1",
    keyword: "",
    pagination: {
      pageNum: 1,
      pageSize: 10,
    },
  });
  const [searchResource] = createResource(searchParam, searchSpecies);
  const { colorMode } = useColorMode();
  const [selected, setSelected] = createSignal<SearchResultItem>();

  const [preDataResource] = createResource(props.speciesId, getSpecies);
  const preDataResult = catchResource(preDataResource, (e) => {});

  createEffect(() => {
    if (selected()) {
      props.onChanged?.(selected()!);
    }
  });

  return (
    <div
      classList={{
        dark: colorMode() === "dark",
        light: colorMode() === "light",
      }}
    >
      <div class="flex flex-col justify-center">
        <Show when={selected() || preDataResult()}>
          <span class="font-bold text-primary font-playfair text-lg italic leading-none">
            {selected()?.latinName || preDataResult()?.latinName}
          </span>
          <span class="font-bold text-secondary font-sans text-lg leading-none">
            {selected()?.cName || preDataResult()?.cName}
          </span>
        </Show>
        <Button class="btn-outlined mt-2" onClick={onOpen}>
          选择物种
        </Button>
      </div>

      <Modal scrollBehavior="inside" opened={isOpen()} onClose={onClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>选择物种</ModalHeader>
          <ModalBody class="flex flex-col gap-4">
            <CategoryFilter
              class="flex flex-col gap-2"
              onSelected={(levels) => {
                setSearchParam((pre) => {
                  return {
                    level: levels,
                    keyword: pre?.keyword,
                    pagination: pre.pagination,
                  };
                });
              }}
            />
            <Input
              type="text"
              placeholder="关键词搜索"
              onInput={(e) => {
                setSearchParam((pre) => {
                  if (!searchResource.loading) {
                    return {
                      level: pre.level,
                      keyword: e.target.value,
                      pagination: pre.pagination,
                    };
                  }
                  return pre;
                });
              }}
            />

            <section
              classList={{
                dark: colorMode() === "dark",
                light: colorMode() === "light",
              }}
            >
              <ErrorBoundary fallback={(e) => <p>{e.message}</p>}>
                <Suspense
                  fallback={
                    <div class="flex items-center justify-center">
                      <Spinner />
                    </div>
                  }
                >
                  <ul class="dark:border-white/10 light:border-dark/10 border-2 border-dashed rounded-xl">
                    <Show when={(searchResource()?.list?.length || 0) === 0}>
                      <p class="font-italic text-secondary py-4 text-center">
                        没有匹配的结果
                      </p>
                    </Show>
                    <For each={searchResource()?.list}>
                      {(item) => (
                        <SearchResultBrief
                          item={item}
                          onClick={() => {
                            setSelected(item);
                            onClose();
                          }}
                        />
                      )}
                    </For>
                  </ul>
                </Suspense>
              </ErrorBoundary>
            </section>
          </ModalBody>
          <ModalFooter>
            <Button class="btn-outlined" onClick={onClose}>
              取消
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </div>
  );
}
