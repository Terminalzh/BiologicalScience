import { get } from "./base";

export function listBanners(current: number, pageSize: number = 10) {
  return get("/api/banner", {
    current,
    pageSize,
  });
}
