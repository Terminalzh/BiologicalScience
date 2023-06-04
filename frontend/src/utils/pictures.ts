import { post } from "~/api/base";

export type PictureTarget = "origin" | "s" | "m" | "l";

type UploadPictureResponse = Record<PictureTarget, string>;
export type Pictures = UploadPictureResponse;

export const uploadPicture = (file: Blob) => {
  const param = new FormData();
  param.append("file", file, file.name || "new-image");
  return post<UploadPictureResponse>("/api/pictures/cover/upload", param, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
};
