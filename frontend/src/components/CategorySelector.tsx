import {
  Accessor,
  For,
  Match,
  Setter,
  Show,
  Suspense,
  Switch,
  batch,
  createEffect,
  createMemo,
  createResource,
  createSignal,
  untrack,
} from "solid-js";
import {
  Button,
  FormControl,
  FormLabel,
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
  Switch as HopeSwitch,
  createDisclosure,
  useColorMode,
  notificationService,
  Spinner,
} from "@hope-ui/solid";
import { createForm } from "@felte/solid";
import { getCategory } from "~/api/category";
import { catchResource } from "~/utils";
import { Category } from "./species/CategoryBadge";

const categoryItems = [
  {
    title: "亚纲",
    name: "sub_class",
  },
  {
    title: "目",
    name: "order",
  },
  {
    title: "科",
    name: "family",
  },
  {
    title: "属",
    name: "genus",
  },
];

const parseBitmap = (bitmap: number, index: number) => {
  if (index === 0) {
    return { available: true, canUseExists: true };
  }
  const mask = 1;
  const max = 2 * categoryItems.length;
  const unused = max - index * 2;
  let _bitmap = bitmap;
  _bitmap = _bitmap >> unused;
  let input = 0;
  let available = 1;
  let inputBit = true;
  let lastInputBit = 0;
  for (let i = 0; i < index * 2; i++) {
    if (inputBit) {
      input = (_bitmap & mask) | input;
      lastInputBit = _bitmap & mask;
    } else {
      available = (lastInputBit | (_bitmap & mask)) & available;
    }
    inputBit = !inputBit;
    _bitmap = _bitmap >> 1;
  }
  return {
    available: available === 1,
    canUseExists: input !== 1,
  };
};

export interface CategoryItemProps {
  id: number;
  latinName?: string;
  cName?: string;
}

const getCategoryItem = (item: CategoryItemProps) => {
  return getCategory(item.id);
};

const CategoryItem = (props: {
  title: string;
  name: string;
  index: number;

  item: Accessor<CategoryItemProps | undefined>;
  setSelected: Setter<CategoryItemProps>;
  bitmapGetter: Accessor<number>;
  bitmapSetter: Setter<number>;
}) => {
  const [useExists, setUseExists] = createSignal(false);

  const [options] = createResource(props.item, getCategoryItem);
  
  const optionsResult = catchResource(options, (e) => {
    untrack(() => {
      notificationService.show({
        title: "查询类别失败",
        status: "danger",
        description: e.message,
      });
    });
  });

  const status = createMemo(() =>
    parseBitmap(props.bitmapGetter(), props.index)
  );

  createEffect(() => {
    if (optionsResult()?.[0].id) {
      props.setSelected({
        id: optionsResult()?.[0].id!,
        cName: optionsResult()?.[0].cName,
        latinName: optionsResult()?.[0].latinName,
      });
    }
  });

  return (
    <div
      class={` p-4 rounded-2xl`}
      classList={{
        "border-dashed light:border-dark/10 dark:border-light/10 border-3":
          status().available,
        "light:bg-dark/5 dark:bg-green-9/5": !status().available,
      }}
    >
      <div class="flex items-center gap-4 mb-2">
        <h2
          class="font-bold text-xl"
          classList={{
            "text-brand-primary/87": status().available,
            "text-secondary": !status().available,
          }}
        >
          {props.title}
        </h2>
        <HopeSwitch
          checked={status().canUseExists ? useExists() : false}
          disabled={!status().available || !status().canUseExists}
          onChange={() => {
            batch(() => {
              setUseExists((prev) => {
                props.bitmapSetter((bitmap) => {
                  let result = 0;
                  if (!prev) {
                    // enable
                    result =
                      bitmap |
                      (1 <<
                        ((categoryItems.length - (props.index + 1)) * 2 + 1));
                  } else {
                    // disable
                    result =
                      bitmap &
                      ~(
                        1 <<
                        ((categoryItems.length - (props.index + 1)) * 2 + 1)
                      );
                  }
                  if (prev === false) {
                    result =
                      result &
                      ~(1 << ((categoryItems.length - (props.index + 1)) * 2));
                  }
                  return result;
                });

                return !prev;
              });
            });
          }}
        >
          使用现有的{props.title}
        </HopeSwitch>
      </div>
      <Switch>
        <Match when={useExists() && status().canUseExists}>
          <Show
            when={!options.loading}
            fallback={
              <div class="flex justify-center items-center py-2">
                <Spinner />
              </div>
            }
          >
            <Select
              disabled={!status().available}
              defaultValue={optionsResult()?.[0].id}
              onChange={(v) => {
                optionsResult()?.forEach((item) => {
                  if (item.id === v) {
                    props.setSelected({
                      id: item.id,
                      cName: item.cName,
                      latinName: item.latinName,
                    });
                  }
                });
              }}
            >
              <SelectTrigger>
                <SelectPlaceholder>全部{props.title}</SelectPlaceholder>
                <SelectValue />
                <SelectIcon />
              </SelectTrigger>
              <SelectContent>
                <SelectListbox>
                  <For each={optionsResult()}>
                    {(item) => (
                      <SelectOption value={item.id}>
                        <SelectOptionText>{item.cName}</SelectOptionText>
                        <SelectOptionIndicator />
                      </SelectOption>
                    )}
                  </For>
                </SelectListbox>
              </SelectContent>
            </Select>
          </Show>
        </Match>
        <Match when={!useExists() || !status().canUseExists}>
          <div class="flex gap-4">
            <FormControl required>
              <FormLabel>拉丁名</FormLabel>
              <Input
                type="text"
                name={`${props.name}.latinName`}
                disabled={!status().available}
                onInput={(e) => {
                  props.bitmapSetter((bitmap) => {
                    let result = 0;
                    if (e.target.value !== "") {
                      // enable
                      result =
                        bitmap |
                        (1 << ((categoryItems.length - (props.index + 1)) * 2));
                    } else {
                      // disable
                      result =
                        bitmap &
                        ~(
                          1 <<
                          ((categoryItems.length - (props.index + 1)) * 2)
                        );
                    }
                    return result;
                  });
                }}
                placeholder="Latin Name"
              />
            </FormControl>
            <FormControl>
              <FormLabel>中文名</FormLabel>
              <Input
                type="text"
                placeholder="没有就留空"
                name={`${props.name}.cName`}
                disabled={!status().available}
              />
            </FormControl>
          </div>
        </Match>
      </Switch>
    </div>
  );
};

export function CategorySelector(props: {
  onChanged: (data: any) => void;
  data?: any;
}) {
  const { isOpen, onOpen, onClose } = createDisclosure();
  const { colorMode } = useColorMode();
  const [bitMap, setBitMap] = createSignal(0);

  const [selectResult, setSelectResult] = createSignal<string[][]>();

  const { form } = createForm({
    onSubmit: (values) => {
      untrack(() => {
        if (values.sub_class.latinName === undefined) {
          values.sub_class = categoryIds[1][0]();
        } else {
          values.sub_class.id = 0;
        }

        if (values.order.latinName === undefined) {
          values.order = categoryIds[2][0]();
        } else {
          values.order.id = 0;
        }
        if (values.family.latinName === undefined) {
          values.family = categoryIds[3][0]();
        } else {
          values.family.id = 0;
        }
        if (values.genus.latinName === undefined) {
          values.genus = categoryIds[4][0]();
        } else {
          values.genus.id = 0;
        }
        props.onChanged(values);
        onClose();

        setSelectResult(
          Object.values(values)
            .map((value: any) => {
              return [value.latinName, value.cName];
            })
            .reverse()
        );
      });
    },
  });

  const categoryIds = [
    createSignal<CategoryItemProps>({
      id: 1,
      cName: "",
      latinName: "",
    }),
    createSignal<CategoryItemProps>(),
    createSignal<CategoryItemProps>(),
    createSignal<CategoryItemProps>(),
    createSignal<CategoryItemProps>(),
  ];

  const categories = createMemo(() => {
    if (!props.data) {
      return undefined;
    }
    return Object.values(props.data).reverse() as string[][];
  });

  return (
    <div>
      <Category values={selectResult() || categories()} />
      <Button class="btn-outlined mt-4" type="button" onClick={onOpen}>
        选择类别
      </Button>
      <Modal
        opened={isOpen()}
        onClose={onClose}
        closeOnOverlayClick={false}
        scrollBehavior="inside"
      >
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>新增分类</ModalHeader>
          <ModalBody class={colorMode()}>
              <form id="category" ref={form}>
                <div class="flex flex-col gap-4">
                  <For each={categoryItems}>
                    {(item, index) => (
                      <CategoryItem
                        {...item}
                        index={index()}
                        item={categoryIds[index()][0]}
                        setSelected={categoryIds[index() + 1][1]}
                        bitmapGetter={bitMap}
                        bitmapSetter={setBitMap}
                      />
                    )}
                  </For>
                </div>
              </form>
          </ModalBody>
          <ModalFooter>
            <Button class="btn-outlined" onClick={onClose}>
              取消
            </Button>
            <Button type="submit" form="category" class="btn ml-4">
              确定新增
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </div>
  );
}
