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
  createDisclosure,
  notificationService,
} from "@hope-ui/solid";
import { createEffect, createResource, createSignal, untrack } from "solid-js";
import { createBanner, updateBanner } from "~/api/banner";
import { SpeciesSelector } from "~/components/SpeciesSearcher";
import { DateTimeColumn, PictureColumn, Table } from "~/components/table";
import { catchResource } from "~/utils";

const api = (data: { id?: number; data: any }) => {
  if (data.id) {
    return updateBanner(data.id, data.data);
  } else {
    return createBanner(data.data);
  }
};

const CreationModal = (props: {
  data?: any;
  onClose?: () => void;
  refetch?: () => void;
}) => {
  const [param, setParam] = createSignal<{ id?: number; data: any }>();
  let speciesId = 0;
  const [apiResource] = createResource(param, api);
  const apiResult = catchResource(apiResource, (e) => {
    untrack(() => {
      notificationService.show({
        title: "提交失败",
        status: "danger",
        description: e.message,
      });
    });
  });

  createEffect(() => {
    if (apiResult() !== undefined) {
      untrack(() => {
        notificationService.show({
          title: "提交成功",
          status: "success",
        });
        props.onClose?.();
        props.refetch?.();
      });
    }
  });

  return (
    <>
      <ModalHeader>{props.data ? "修改" : "新建"}</ModalHeader>
      <ModalBody>
        <FormControl required>
          <FormLabel>绑定物种</FormLabel>
          <SpeciesSelector
            speciesId={props.data?.speciesId}
            onChanged={(item) => {
              speciesId = item.id;
            }}
          />
        </FormControl>
      </ModalBody>
      <ModalFooter>
        <Button class="btn-outlined" onClick={props.onClose}>
          取消
        </Button>
        <Button
          class="btn ml-4"
          onClick={() => {
            if (speciesId == -1) {
              untrack(() => {
                notificationService.show({
                  title: "信息不完整",
                  status: "danger",
                });
              });
            } else {
              setParam({
                id: props.data?.speciesId,
                data: { speciesId } || props.data,
              });
            }
          }}
        >
          {props.data ? "确认修改" : "确认保存"}
        </Button>
      </ModalFooter>
    </>
  );
};

export default function BannersPage() {
  return (
    <Table
      columns={[
        {
          title: "照片",
          value: (row: any) => row.species.pictureUrl,
          component: PictureColumn,
        },
        { title: "拉丁名称", value: (row: any) => row.species.latinName },
        { title: "中文名称", value: (row: any) => row.species.cName },
        {
          title: "创建时间",
          value: (row: any) => row.createTime,
          component: DateTimeColumn,
        },
      ]}
      api="/api/banner/page"
      operations
      itemEditor={(data?: any, onClose?: () => void, refetch?: () => void) => (
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
