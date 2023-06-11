import { PageResponse, get, post } from "./base";
import { SearchResultItem } from "./species";

export interface BannerItem {
  id: number;
  species: SearchResultItem;
}

export function listBanners(current: number, pageSize: number = 10) {
  return get<PageResponse<BannerItem>>("/api/banner/page", {
    pageNum: current,
    pageSize,
  });
}

export function createBanner(data: any) {
  return post<string>("/api/banner", data);
}

export function updateBanner(id: number, data: any) {
  return post<string>(`/api/banner/${id}`, data);
}
