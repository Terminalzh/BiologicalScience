import {
  Accessor,
  For,
  Match,
  Setter,
  Show,
  Switch,
  batch,
  createEffect,
  createMemo,
  createResource,
  createSignal,
  onMount,
  untrack,
} from "solid-js";
import Highcharts from "highcharts";
import Sunburst from "highcharts/modules/sunburst";
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
} from "@hope-ui/solid";
import { createForm } from "@felte/solid";
import { allCategories } from "~/api/category";
import { catchResource } from "~/utils";
import { Table } from "~/components/table";

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
    canUseExists: index === categoryItems.length - 1 ? false : input !== 1,
  };
};

const CategoryItem = (props: {
  title: string;
  name: string;
  index: number;
  bitmapGetter: Accessor<number>;
  bitmapSetter: Setter<number>;
}) => {
  const [useExists, setUseExists] = createSignal(false);

  const status = createMemo(() =>
    parseBitmap(props.bitmapGetter(), props.index)
  );

  createEffect(() => {
    const bitmap = props.bitmapGetter();
    console.log(bitmap);
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
          <Select defaultValue="Solid" disabled={!status().available}>
            <SelectTrigger>
              <SelectPlaceholder>Choose a framework</SelectPlaceholder>
              <SelectValue />
              <SelectIcon />
            </SelectTrigger>
            <SelectContent>
              <SelectListbox>
                <For each={["React", "Angular", "Vue", "Svelte", "Solid"]}>
                  {(item) => (
                    <SelectOption value={item}>
                      <SelectOptionText>{item}</SelectOptionText>
                      <SelectOptionIndicator />
                    </SelectOption>
                  )}
                </For>
              </SelectListbox>
            </SelectContent>
          </Select>
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

export default function SpeciesCategory() {
  const { isOpen, onOpen, onClose } = createDisclosure();
  const { colorMode } = useColorMode();
  const [level, setLevel] = createSignal<number>();
  const [bitMap, setBitMap] = createSignal(0);
  const [allCategoriesResource] = createResource(level, allCategories);
  const allCategoriesResult = catchResource(allCategoriesResource, (e) => {
    untrack(() => {
      notificationService.show({
        title: "请求失败",
        status: "danger",
        description: e.message,
      });
    });
  });

  const [mode, setMode] = createSignal<"table" | "sunburst">("table");

  const { form } = createForm({
    onSubmit: (values) => {
      console.log(values);
    },
  });

  createEffect(() => {
    if (allCategoriesResult()) {
      chart.series[0].setData(allCategoriesResult()! as any);
    }
  });

  let chart: Highcharts.Chart;
  onMount(() => {
    Sunburst(Highcharts);

    chart = Highcharts.chart({
      chart: {
        renderTo: "container",
        spacingBottom: 30,
        marginRight: 120,
        backgroundColor: "transparent",
      },
      plotOptions: {
        series: {
          turboThreshold: 2000,
          colors: [
            "#F1C376",
            "#F7E6C4",
            "#FFF4F4",
            "#606C5D",
            "#F97B22",
            "#3A8891",
          ],
        },
        sunburst: {
          dataLabels: {
            style: {
              fontSize: "1rem",
              fontFamily: "Playfair",
              fontStyle: "italic",
            },
          },
          levels: [
            {
              level: 1,
              color: "#F1C376",
              dataLabels: {
                style: {
                  color: "#F1C376",
                },
              },
            },
            {
              level: 2,
              color: "#00FF00",
              dataLabels: {
                style: {
                  color: "#FFFFFF",
                },
              },
            },
            {
              level: 3,
              color: "#0000FF",
              dataLabels: {
                style: {
                  color: "#FFFFFF",
                },
              },
            },
          ],
        },
      },
      title: {
        text: "",
      },
      series: [
        {
          type: "sunburst",
          name: "Root",
          allowDrillToNode: true,
          borderRadius: 3,
          cursor: "pointer",
          dataLabels: {
            format: "{point.name}",
            filter: {
              property: "innerArcLength",
              operator: ">",
              value: 16,
            },
          },
          levels: [
            {
              level: 1,
              levelIsConstant: false,
              dataLabels: {
                filter: {
                  property: "outerArcLength",
                  operator: ">",
                  value: 64,
                },
              },
            },
            {
              level: 2,
              colorByPoint: true,
              colorVariation: {
                key: "brightness",
                to: -0.5,
              },
            },
            {
              level: 3,
              colorVariation: {
                key: "brightness",
                to: -0.5,
              },
            },
            {
              level: 4,
              colorVariation: {
                key: "brightness",
                to: 0.5,
              },
            },
          ],
        },
      ],
    } as any);
  });

  return (
    <>
      <div class="h-full">
        <Show when={mode() === "table"}>
          <Table
            columns={[
              {
                title: "名称",
                value(record) {
                  return record.name;
                },
              },
            ]}
            api="/api/category/page"
            params={{
              level: 5,
            }}
            topCaptions={{
              createButton() {
                onOpen();
                return true;
              },
              customElements: [<Button class="btn">旭日图</Button>],
            }}
          />
        </Show>

        <figure
          class="h-full opacity-80"
          id="container"
          classList={{
            hidden: mode() === "table",
          }}
        ></figure>
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
    </>
  );
}
