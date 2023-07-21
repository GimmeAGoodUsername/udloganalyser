import axios from "axios";
import authHeader from "./auth-header";
import SrOrder from "../types/order.type";

const API_URL = "http://localhost:8080/orderApi/";

export const getOpenOrders = () => {
  return axios.get<SrOrder[]>(API_URL + "openOrders",{ headers: authHeader() });
};

export const assignOrder = (srOrder: SrOrder, name: string) => {
    return axios.post<SrOrder>(API_URL+"assign",
    {srOrder,name},
    { headers: authHeader() })
};

