import { PageResponse, PaginationParams, get, post, put } from "./base";

export interface SearchParams {
  level: string;
  keyword?: string;
  pagination: PaginationParams;
}

export interface SearchResultItem {
  briefIntroduction?: string;
  genusId: number;
  cName: string;
  createTime?: string;
  latinName: string;
  detailIntroduction?: string;
  id: number;
  pictureUrl?: string;
  betterUrl?: string;
  recommend: boolean;
  updateTime: string;
  categorizedInheritance: string;
  inheritance: Record<"1" | "2" | "3" | "4" | "5", Array<string>>;
}

export const searchSpecies = (data: SearchParams) => {
  return get<PageResponse<SearchResultItem>>(`/api/species/blur`, {
    inheritance: data.level,
    keyword: data.keyword,
    ...data.pagination,
  });
};

export const getSpecies = (id: number) => {
  return get<SearchResultItem>(`/api/species/${id}`);
};

export const updateSpecies = (id: number, data: any) => {
  return put(`/api/species/${id}`, data);
};

export const createSpecies = (data: any) => {
  data.id = -1;
  return post(`/api/species`, data);
};

export const recommendSpecies = () => {
  return get<Array<SearchResultItem>>("/api/species/recommends");
};
