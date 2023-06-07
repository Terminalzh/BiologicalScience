import { DateColumn, PictureColumn, Table } from "~/components/table";

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
      topCaptions={{
        createButton() {
          return false;
        },
      }}
    />
  );
}
