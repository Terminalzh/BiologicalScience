import {
  Button,
  FormControl,
  FormLabel,
  ModalBody,
  ModalFooter,
  ModalHeader,
} from "@hope-ui/solid";
import { createSignal } from "solid-js";
import PictureUploader from "~/components/form/PictureUploader";
import { DateColumn, PictureColumn, Table } from "~/components/table";

const CreationModal = (props: {
  data?: any;
  name?: string;
  onClose?: () => void;
}) => {
  const [loading, setLoading] = createSignal(false);
  let avatar = "";
  return (
    <>
      <ModalHeader>{props.data ? "修改" : "新建"}</ModalHeader>
      <ModalBody>
        <form>
          <FormControl>
            <PictureUploader
              name="avatar"
              required
              value={
                props.data?.avatar ? JSON.parse(props.data!.avatar) : undefined
              }
              onChanged={(value) => {
                avatar = JSON.stringify(value);
              }}
            />
            <FormLabel></FormLabel>
          </FormControl>
        </form>
      </ModalBody>
      <ModalFooter>
        <Button
          type="submit"
          form={props.name || "form"}
          loading={loading()}
          class="btn"
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
      itemEditor={(data) => <CreationModal data={data} />}
      topCaptions={{
        createButton() {
          return false;
        },
      }}
    />
  );
}
