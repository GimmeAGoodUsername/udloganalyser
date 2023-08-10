import axios from "axios";
import authHeader from "./auth-header";
import SrOrder from "../types/order.type";

const API_URL = "https://ud-be.elimar.xyz/orderApi/";

export const getOpenOrders = () => {
  return axios.get<SrOrder[]>(API_URL + "allNotDelivered",{ headers: authHeader() });
};

export const assignOrder = (srOrder: SrOrder, name: string) => {
    return axios.post<SrOrder>(API_URL+"assign",
    {srOrder,name},
    { headers: authHeader() })
};

export const createOrder = (srOrder: SrOrder) => {
  return axios.put<SrOrder>(API_URL+"create",
  {srOrder},
  { headers: authHeader() })
};

export const completeOrder = (order: SrOrder) => {
  return axios.put<SrOrder>(API_URL+"deliver",
  {order},
  { headers: authHeader() })
};

