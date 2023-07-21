import axios from "axios";
import authHeader from "./auth-header";
import ISrUser from "../types/sruser.type";

const API_URL = "http://localhost:8080/userApi/";

export const getPublicContent = () => {
  return axios.get(API_URL + "all");
};

export const getUserByName = (username:string) => {
  return axios.get<ISrUser>(API_URL + "getUser/"+username, { headers: authHeader() });
};

export const getModeratorBoard = () => {
  return axios.get(API_URL + "mod", { headers: authHeader() });
};

export const getAdminBoard = () => {
  return axios.get(API_URL + "admin", { headers: authHeader() });
};