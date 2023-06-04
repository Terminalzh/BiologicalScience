import { get, post } from "./base";

export interface User {
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
    phone: data.username,
    password: data.password,
  });
}

export function register(data: any) {
  return post<string>("/api/user", data);
}

export function getMe() {
  return get<User>("/api/user/me");
}
