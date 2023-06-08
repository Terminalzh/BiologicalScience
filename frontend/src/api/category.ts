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

export const allCategories = (level: number) => {
  return get<Array<CategoryFlat>>(`/api/category?level=${level}`);
};

export const getCategory = (id: number) => {
  return get<Array<CategoryShort>>(`/api/category/${id}`);
};

