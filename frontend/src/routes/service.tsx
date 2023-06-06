import { Outlet } from "solid-start";
import BaseLayout from "~/components/BaseLayout";

export default function ServiceLayout() {
  return <BaseLayout mode="normal">{(_) => <Outlet />}</BaseLayout>;
}
