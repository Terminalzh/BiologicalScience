import {
  Button,
  ModalBody,
  ModalFooter,
  ModalHeader,
  notificationService,
} from "@hope-ui/solid";
import { createSignal } from "solid-js";
import RegistryForm from "~/components/RegistryForm";
import { AvatarColumn, DateColumn, Table } from "~/components/table";

const CreationModal = (props: {
  data?: any;
  name?: string;
  onClose?: () => void;
  refetch?: () => void;
}) => {
  const [loading, setLoading] = createSignal(false);

  return (
    <>
      <ModalHeader>{props.data ? "修改" : "新建"}</ModalHeader>
      <ModalBody>
        <RegistryForm
          name={props.name}
          data={props.data}
          onFailed={(e) => {
            notificationService.show({
              title: props.data ? "修改失败" : "新建失败",
              status: "danger",
              description: e.message,
            });
            setLoading(false);
          }}
          onSucceed={() => {
            notificationService.show({
              title: props.data ? "修改成功" : "新建成功",
              status: "success",
            });
            setLoading(false);
            props.onClose?.();
            props.refetch?.();
          }}
          onSubmitted={() => {
            setLoading(true);
          }}
        />
      </ModalBody>
      <ModalFooter>
        <Button onClick={props.onClose} class="btn-outlined">
          取消
        </Button>
        <Button
          type="submit"
          form={props.name || "form"}
          loading={loading()}
          class="btn ml-4"
        >
          {props.data ? "确认修改" : "确认保存"}
        </Button>
      </ModalFooter>
    </>
  );
};

export default function UsersPage() {
  return (
    <Table
      columns={[
        {
          title: "头像",
          value: (record: any) => record.avatar,
          component(data) {
            return AvatarColumn(data);
          },
        },
        {
          title: "名称",
          value: (record: any) => record.name,
        },
        {
          title: "性别",
          value: (record: any) => record.gender,
        },
        {
          title: "电话",
          value: (record: any) => record.phone,
        },
        {
          title: "电子邮箱",
          value: (record: any) => record.email,
        },
        {
          title: "创建时间",
          value: (record: any) => record.createTimeDateColumn,
          component: DateColumn,
        },
      ]}
      api="/api/user"
      operations
      itemEditor={(data?: any, onClose?: () => void, refetch?: () => void) => (
        <CreationModal
          data={data}
          name="user-form"
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
