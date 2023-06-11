import { PageResponse, PaginationParams, get, post, put } from "./base";
import { SearchResultItem } from "./species";

export interface WorkCreationParam {
  speciesId: number;
  imageUrl: string;
  isPublic: boolean;
}

export interface RecommendItem {
  imageUrl?: string;
  species?: SearchResultItem;
}

export interface WorkItem {
  imageUrl: string;
}

export const createWork = (params: any) => {
  return post("/api/works", params);
};

export const updateWork = (id: number, params: any) => {
  return put(`/api/works/${id}`, params);
};

export const recommendWorks = () => {
  return get<Array<RecommendItem>>("/api/works/recommends");
};

export const listWorks = (pagination: PaginationParams) => {
  return get<PageResponse<WorkItem>>("/api/works", pagination);
};
