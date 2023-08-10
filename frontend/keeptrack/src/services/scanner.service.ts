import axios from "axios";
import authHeader from "./auth-header";
import SrOrder from "../types/order.type";

const API_URL = "https://ud-be.elimar.xyz/scanApi/";

export const scan = (amount: number, scanrange: number, creep:boolean) => {
    return axios.post<string[]>(API_URL+"scan",
    {amount,scanrange, creep},
    { headers: authHeader() })
};



