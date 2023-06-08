import { DateColumn, Table } from "~/components/table";

export default function FeedbackPage() {
  return (
    <Table
      columns={[
        {
          title: "姓名",
          value(record) {
            return record.reporterName;
          },
        },

        {
          title: "电子邮箱",
          value(record) {
            return record.email;
          },
        },

        {
          title: "内容",
          value(record) {
            return record.reportReason;
          },
        },
        {
          title: "创建时间",
          value(record) {
            return record.reportTime;
          },
          component: DateColumn,
        },
      ]}
      api="/api/report"
      operations={{
        edit: false,
        delete: true,
      }}
    />
  );
}
