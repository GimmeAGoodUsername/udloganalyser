import axios from "axios";
import authHeader from "./auth-header";
import ISrUser from "../types/sruser.type";
import IPlanet from "../types/planet.type";

const API_URL = "https://ud-be.elimar.xyz/adminApi/";

export const getUserByName = (username: string) => {
  return axios.get<ISrUser>(API_URL + "getUser/" + username, { headers: authHeader() });
};

export const createUser = (username: string, password: string, role: string) => {
  return axios.put(API_URL + "createUser", { username,password,role } ,{ headers: authHeader() });
};

export const deleteUser = (id: number) => {
  return axios.delete<void>(API_URL + "deleteUser/" + id,  { headers: authHeader() });
};