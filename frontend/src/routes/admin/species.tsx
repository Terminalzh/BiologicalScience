import { DateColumn, PictureColumn, Table } from "~/components/table";

export default function SpeciesPage() {
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
        },

        {
          title: "保护等级",
          value(record) {
            return record.level;
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
      api="/api/species"
    />
  );
}
