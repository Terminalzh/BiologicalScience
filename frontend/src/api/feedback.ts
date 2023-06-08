import { post } from "./base";

export default function feedback(data: any) {
  return post("/api/report", data);
}
