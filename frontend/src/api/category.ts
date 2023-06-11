import { get } from "./base";

interface CategoryFlat {
  id: number;
  parent: number;
  name: string;
  value: number;
}

interface CategoryShort {
  id: number;
  cName: string;
  latinName: string;
  parent: number;
}

interface CategoryInfo {
  cName: string;
  latinName: string;
  level: number;
}

export const allCategories = (level: number) => {
  return get<Array<CategoryFlat>>(`/api/category?level=${level}`);
};

export const getCategory = (id: number) => {
  return get<Array<CategoryShort>>(`/api/category/${id}`);
};

export const getCategoryInfo = (id: number) => {
  return get<CategoryInfo>(`/api/category/${id}/info`);
};
