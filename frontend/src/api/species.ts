import { PageResponse, PaginationParams, get } from "./base";

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
  pictureUrl: string;
  recommend: boolean;
  updateTime: string;
}

export const searchSpecies = (data: SearchParams) => {
  return get<PageResponse<SearchResultItem>>(`/api/species/blur`, {
    inheritance: data.level,
    keyword: data.keyword,
    ...data.pagination,
  });
};


export const getSpecies = (id: number) => {
  return get<SearchResultItem>(`/api/species/${id}`)
}