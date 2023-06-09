import { createForm } from "@felte/solid";
import {
  Button,
  FormControl,
  FormLabel,
  Input,
  ModalBody,
  ModalFooter,
  ModalHeader,
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
  SimpleSelect,
  Spinner,
  Switch,
  Text,
  Textarea,
  notificationService,
} from "@hope-ui/solid";
import {
  ErrorBoundary,
  For,
  Suspense,
  createSignal,
  createResource,
  untrack,
  createEffect,
  createMemo,
} from "solid-js";
import { createSpecies, getSpecies, updateSpecies } from "~/api/species";
import { CategoryFilter } from "~/components/SpeciesSearcher";
import PictureUploader from "~/components/form/PictureUploader";
import { DateColumn, PictureColumn, Table } from "~/components/table";
import { catchResource } from "~/utils";

const getGenusId = (node: any) => {
  let current = node;
  let result = 0;
  while ((current = current.next)) {
    result = current.id;
  }
  return result;
};

const api = (data: { id?: number; data: any }) => {
  if (data.id) {
    return updateSpecies(data.id, data.data);
  } else {
    return createSpecies(data.data);
  }
};

const CreationModal = (props: {
  data?: any;
  name?: string;
  onClose?: () => void;
  refetch?: () => void;
}) => {
  const [speciesParams, setSpeciesParams] = createSignal<{
    id?: number;
    data: any;
  }>();
  const [apiResource] = createResource(speciesParams, api);
  const apiResult = catchResource(apiResource, (e) => {
    untrack(() => {
      notificationService.show({
        title: `${props.data ? "修改" : "创建"}失败`,
        status: "danger",
        description: e.message,
      });
    });
  });

  // const [preDataResource] = createResource()

  let level = 0;
  let pictureUrl = "";
  let genusId = 0;
  let recommend = false;

  createEffect(() => {
    if (props.data?.level) {
      level = props.data?.level;
    }
  });

  createEffect(() => {
    if (props.data?.pictureUrl) {
      pictureUrl = props.data?.pictureUrl;
    }
  });

  createEffect(() => {
    if (props.data?.genusId) {
      genusId = props.data?.genusId;
    }
  });

  createEffect(() => {
    if (props.data?.recommend) {
      recommend = props.data?.recommend;
    }
  });

  const { form } = createForm({
    onSubmit(value) {
      if (genusId === 0) {
        untrack(() => {
          notificationService.show({
            title: "缺少物种类别",
            status: "danger",
          });
        });
        return;
      }
      value.level = level;
      value.pictureUrl = pictureUrl;
      value.genusId = genusId;
      value.recommend = recommend;
      setSpeciesParams({
        id: props.data?.id,
        data: value,
      });
    },
  });

  createEffect(() => {
    if (apiResult() !== undefined) {
      untrack(() => {
        notificationService.show({
          title: `${props.data ? "修改" : "创建"}成功`,
          status: "success",
        });
        props.refetch?.();
        props.onClose?.();
      });
    }
  });

  return (
    <>
      <ModalHeader>{props.data ? "修改" : "新建"}</ModalHeader>
      <ModalBody class="scrollbar-std">
        <ErrorBoundary fallback={(e) => <p class="py-4">{e.message}</p>}>
          <Suspense
            fallback={
              <div class="flex justify-center items-center py-4">
                <Spinner />
              </div>
            }
          >
            <form
              id={props.name || "form"}
              ref={form}
              class="flex flex-col gap-4"
            >
              <div class="flex gap-8 items-center">
                <div>
                  <PictureUploader
                    valueStr={props.data?.pictureUrl}
                    onChanged={(value) => {
                      pictureUrl = JSON.stringify(value);
                    }}
                  />
                </div>
                <div>
                  <FormControl required disabled={apiResource.loading}>
                    <FormLabel>拉丁名</FormLabel>
                    <Input
                      type="text"
                      name="latinName"
                      placeholder="输入拉丁名"
                      value={props.data?.latinName}
                    />
                  </FormControl>

                  <FormControl class="mt-4" disabled={apiResource.loading}>
                    <FormLabel>中文名</FormLabel>
                    <Input
                      type="text"
                      name="cName"
                      placeholder="输入中文名"
                      value={props.data?.cName}
                    />
                  </FormControl>
                </div>
              </div>

              <div class="flex gap-8 flex-1 items-center">
                <FormControl class="flex-1" disabled={apiResource.loading}>
                  <FormLabel>保护等级</FormLabel>
                  <Select
                    defaultValue={props.data?.level || 0}
                    onChange={(value) => {
                      level = value;
                    }}
                  >
                    <SelectTrigger>
                      <SelectPlaceholder>选择保护等级</SelectPlaceholder>
                      <SelectValue />
                      <SelectIcon />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectListbox>
                        <For
                          each={[
                            { k: "无危", v: 0 },
                            { k: "一级保护", v: 1 },
                            { k: "二级保护", v: 2 },
                          ]}
                        >
                          {(item) => (
                            <SelectOption value={item.v}>
                              <SelectOptionText>{item.k}</SelectOptionText>
                              <SelectOptionIndicator />
                            </SelectOption>
                          )}
                        </For>
                      </SelectListbox>
                    </SelectContent>
                  </Select>
                </FormControl>
                <Switch
                  defaultChecked={props.data?.recommend || false}
                  class="flex-1 mt-8"
                  onChange={() => {
                    recommend = !recommend;
                  }}
                >
                  是否推荐
                </Switch>
              </div>

              <FormControl required disabled={apiResource.loading}>
                <FormLabel>物种分类</FormLabel>
                <CategoryFilter
                  value={props.data?.categorizedInheritance}
                  class="flex flex-col gap-4"
                  onSelected={(_, root) => {
                    genusId = getGenusId(root);
                  }}
                />
              </FormControl>

              <FormControl disabled={apiResource.loading}>
                <FormLabel>简介</FormLabel>
                <Textarea
                  value={props.data?.briefIntroduction}
                  name="briefIntroduction"
                  placeholder="简要描述这个物种的信息"
                  rows={2}
                ></Textarea>
              </FormControl>

              <FormControl disabled={apiResource.loading}>
                <FormLabel>详细信息</FormLabel>
                <Textarea
                  value={props.data?.detailIntroduction}
                  name="detailIntroduction"
                  placeholder="详细描述这个物种"
                  rows={4}
                ></Textarea>
              </FormControl>
            </form>
          </Suspense>
        </ErrorBoundary>
      </ModalBody>
      <ModalFooter>
        <Button class="btn-outlined" onClick={props.onClose}>
          取消
        </Button>
        <Button
          type="submit"
          form={props.name || "form"}
          loading={apiResource.loading}
          class="btn ml-4"
        >
          {props.data ? "确认修改" : "确认保存"}
        </Button>
      </ModalFooter>
    </>
  );
};
export default function SpeciesPage() {
  const coverLevel = (level: number) => {
    switch (level) {
      case 0:
        return "无危";
      case 1:
        return "一级";
      case 2:
        return "二级";
    }
    return level + "";
  };

  return (
    <Table
      columns={[
        {
          title: "图片",
          value(record) {
            return record.pictureUrl;
          },
          component: PictureColumn,
        },

        {
          title: "拉丁名",
          value(record) {
            return record.latinName;
          },
        },

        {
          title: "中文名",
          value(record) {
            return record.cName;
          },
          component: (data) => <Text>{data || "-"}</Text>,
        },

        {
          title: "保护等级",
          value(record) {
            return record.level;
          },
          component: (data) => <span>{coverLevel(data)}</span>,
        },
        {
          title: "创建时间",
          value(record) {
            return record.createTime;
          },
          component: DateColumn,
        },
      ]}
      api="/api/species"
      operations
      itemEditor={(data, onClose, refetch) => (
        <CreationModal
          name="species-form"
          data={data}
          onClose={onClose}
          refetch={refetch}
        />
      )}
      topCaptions={{
        createButton() {
          return false;
        },
      }}
    />
  );
}
