import { deletes, get, post, put } from "./base";

export interface User {
  isAdmin: boolean;
  id: number;
  name: string;
  avatar: string;
  password: string;
  gender: string;
  phone: string;
  email: string;
  createTime: string;
}

export function login(data: any) {
  return post<User>("/api/user/login", {
    username: data.username,
    password: data.password,
  });
}

export function logout() {
  return deletes<string>("/api/user/logout");
}

export function register(data: any) {
  return post<string>("/api/user", data);
}

export function getMe() {
  return get<User>("/api/user/me");
}

export function updateUser(id: string, data: any) {
  return put<string>(`/api/user/${id}`, data);
}
