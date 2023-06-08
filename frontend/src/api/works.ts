import { post, put } from "./base";

export interface WorkCreationParam {
  speciesId: number;
  imageUrl: string;
  isPublic: boolean;
}

export const createWork = (params: any) => {
  return post("/api/works", params);
};

export const updateWork = (id: number, params: any) => {
  return put(`/api/works/${id}`, params);
};
