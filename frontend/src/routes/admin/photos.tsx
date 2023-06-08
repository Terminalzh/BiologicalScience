import {
  Button,
  FormControl,
  FormLabel,
  ModalBody,
  ModalFooter,
  ModalHeader,
  Spinner,
  Switch,
  notificationService,
} from "@hope-ui/solid";
import {
  ErrorBoundary,
  Suspense,
  createEffect,
  createResource,
  createSignal,
  untrack,
} from "solid-js";
import { createStore } from "solid-js/store";
import { WorkCreationParam, createWork, updateWork } from "~/api/works";
import { SpeciesSelector } from "~/components/SpeciesSearcher";
import PictureUploader from "~/components/form/PictureUploader";
import { DateColumn, PictureColumn, Table } from "~/components/table";
import { catchResource } from "~/utils";

const api = (data?: { id?: number; param?: any }) => {
  if (data?.id) {
    return updateWork(data.id, data.param);
  } else {
    return createWork(data?.param);
  }
};

const CreationModal = (props: {
  data?: any;
  name?: string;
  onClose?: () => void;
  refetch?: () => void;
}) => {
  const [param, setParam] = createSignal<{
    id?: number;
    param: WorkCreationParam;
  }>();

  const [apiResource] = createResource(param, api);
  const creationResult = catchResource(apiResource, (e) => {
    untrack(() => {
      notificationService.show({
        title: `${props.data ? "修改" : "创建"}失败`,
        status: "danger",
        description: e.message,
      });
    });
  });

  let imageUrl = "";
  let speciesId = 0;
  let isPublic = false;

  createEffect(() => {
    isPublic = props.data?.isPublic || false;
  });

  createEffect(() => {
    speciesId = props.data?.species?.id || 0;
  });

  createEffect(() => {
    if (creationResult() !== undefined) {
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
      <ModalBody>
        <ErrorBoundary fallback={(e) => <p class="py-4">{e.message}</p>}>
          <Suspense
            fallback={
              <div class="flex justify-center items-center py-4">
                <Spinner />
              </div>
            }
          >
            <div class="flex justify-around items-start gap-8">
              <FormControl required class="flex-1">
                <FormLabel>图片</FormLabel>
                <PictureUploader
                  name="avatar"
                  required
                  value={
                    props.data?.pictureUrl
                      ? JSON.parse(props.data!.pictureUrl)
                      : props.data?.pictureUrl
                  }
                  onChanged={(value) => {
                    imageUrl = JSON.stringify(value);
                  }}
                />
              </FormControl>
              <FormControl required>
                <FormLabel>绑定物种</FormLabel>
                <SpeciesSelector
                  speciesId={props.data?.species?.id}
                  onChanged={(item) => [(speciesId = item.id)]}
                />
              </FormControl>
            </div>
            <Switch
              class="mt-8"
              defaultChecked={props.data?.isPublic || false}
              onChange={() => {
                isPublic = !isPublic;
              }}
            >
              是否公开
            </Switch>
          </Suspense>
        </ErrorBoundary>
      </ModalBody>
      <ModalFooter>
        <Button class="btn-outlined" onClick={props.onClose}>
          取消
        </Button>
        <Button
          type="submit"
          loading={apiResource.loading}
          form={props.name || "form"}
          class="btn ml-4"
          onClick={() => {
            if (imageUrl === "" || speciesId === -1) {
              notificationService.show({
                title: "信息不完整",
                status: "danger",
              });
              return;
            }
            setParam({
              id: props.data?.id,
              param: {
                imageUrl,
                speciesId,
                isPublic,
              },
            });
          }}
        >
          {props.data ? "确认修改" : "确认保存"}
        </Button>
      </ModalFooter>
    </>
  );
};

export default function PhotoPage() {
  return (
    <Table
      columns={[
        {
          title: "图片",
          value(record) {
            return record.imageUrl;
          },
          component: PictureColumn,
        },
        {
          title: "点赞数",
          value(record) {
            return record.likeCount;
          },
        },
        {
          title: "查看数",
          value(record) {
            return record.viewCount;
          },
        },
        {
          title: "是否公开",
          value(record) {
            return <span>{record.isPublic ? "是" : "否"}</span>;
          },
        },
        {
          title: "创建时间",
          value(record) {
            return record.createTime;
          },
          component: DateColumn,
        },
      ]}
      api="/api/works"
      operations
      itemEditor={(data, onClose, refetch) => (
        <CreationModal data={data} onClose={onClose} refetch={refetch} />
      )}
      topCaptions={{
        createButton() {
          return false;
        },
      }}
    />
  );
}
