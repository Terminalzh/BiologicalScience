import axios, { AxiosRequestConfig } from "axios";

export interface BaseResponse<T> {
  code: number;
  message: string;
  data: T;
}

export interface PaginationParams {
  current: number;
  pageSize?: number;
}

export interface PaginationEntity extends PaginationParams {
  total: number;
}

export interface PageResponse<T> {
  list: Array<T>;
  pagination: PaginationEntity;
}

export interface KodoUploadInfoResponse {
  token: string;
}

export class HttpError extends Error {
  code: number;
  constructor(code: number, message: string) {
    super(message);
    this.code = code;
  }

  toString() {
    return `HttpError(code = ${this.code}, message: ${this.message})`;
  }
}

export function get<T>(path: string, query?: Record<string, any>): Promise<T> {
  return new Promise((resolve, reject) => {
    axios
      .get(path, {
        params: query,
      })
      .then((res) => {
        resolve(res.data.data as T);
      })
      .catch((e) => {
        const data = e.response.data;
        reject(
          new HttpError(data?.code || e.status, data?.message || "请求失败")
        );
      });
  });
}

export function deletes<T>(path: string, query: any = {}): Promise<T> {
  return new Promise((resolve, reject) => {
    axios
      .delete(path, {
        params: query,
      })
      .then((res) => {
        resolve(res.data.data as T);
      })
      .catch((e) => {
        const data = e.response.data;
        reject(
          new HttpError(data?.code || e.status, data?.message || "请求失败")
        );
      });
  });
}

export function fetchTableData<T>(
  path: string,
  pagination: Partial<PaginationEntity>
) {
  return get<PageResponse<T>>(path, pagination);
}

export function post<T>(
  path: string,
  params: any | undefined = undefined,
  config: AxiosRequestConfig<any> | undefined = undefined
): Promise<T> {
  return new Promise((resolve, reject) => {
    axios
      .post(path, params, config)
      .then((res) => {
        resolve(res.data.data as T);
      })
      .catch((e) => {
        const data = e.response.data;
        reject(
          new HttpError(data?.code || e.status, data?.message || "请求失败")
        );
      });
  });
}

export function put<T>(
  path: string,
  params: any,
  config: AxiosRequestConfig<any> | undefined = undefined
): Promise<T> {
  return new Promise((resolve, reject) => {
    axios
      .put(path, params, config)
      .then((res) => {
        resolve(res.data.data as T);
      })
      .catch((e) => {
        const data = e.response.data;
        reject(
          new HttpError(data?.code || e.status, data?.message || "请求失败")
        );
      });
  });
}
