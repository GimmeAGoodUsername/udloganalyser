import axios from "axios";
import authHeader from "./auth-header";
import ISrUser from "../types/sruser.type";
import IPlanet from "../types/planet.type";

const API_URL = "https://ud-be.elimar.xyz/userApi/";

export const getPublicContent = () => {
  return axios.get(API_URL + "all");
};

export const getUserByName = (username: string) => {
  return axios.get<ISrUser>(API_URL + "getUser/" + username, { headers: authHeader() });
};

export const updateUser = (srUser: ISrUser) => {
  srUser.planets = (srUser.planets || []).map(p => {
    delete p.srUser
    return p
  })
  return axios.post<ISrUser>(API_URL + "updateUser", { srUser } ,{ headers: authHeader() });
};

export const deletePlanet = (planetId: IPlanet['id']) => {
  return axios.delete<void>(API_URL + "deletePlanet/" + planetId,  { headers: authHeader() });
};

export const getAllUsers = () => {
  return axios.get<ISrUser[]>(API_URL + "getAllUsers",  { headers: authHeader() });
};