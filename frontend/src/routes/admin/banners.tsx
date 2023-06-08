import {
  Button,
  FormControl,
  Input,
  ModalBody,
  ModalFooter,
  ModalHeader,
} from "@hope-ui/solid";
import { DateTimeColumn, PictureColumn, Table } from "~/components/table";

const CreationModal = (props: { data?: any; name?: string }) => {
  return (
    <>
      <ModalHeader>{props.data ? "修改" : "新建"}</ModalHeader>
      <ModalBody>
        <form id={props.name}>
          <FormControl>
            <Input type="text" />
          </FormControl>
        </form>
      </ModalBody>
      <ModalFooter>
        <Button type="submit" form={props.name || "form"}>
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
        { title: "拉丁名称", value: (row: any) => row.species.latinName },
        { title: "中文名称", value: (row: any) => row.species.cName },
        {
          title: "照片",
          value: (row: any) => row.species.pictureUrl,
          component: PictureColumn,
        },
        {
          title: "创建时间",
          value: (row: any) => row.createTime,
          component: DateTimeColumn,
        },
      ]}
      api="/api/banner/page"
      operations
      itemEditor={(data?: any) => <CreationModal data={data} name="banner" />}
      topCaptions={{
        createButton() {
          return false;
        },
      }}
    />
  );
}
