import axios from "axios";
import authHeader from "./auth-header";
import SrOrder from "../types/order.type";

const API_URL = "https://ud-be.elimar.xyz/public/";

export const getOxiKillDay = () => {
    return axios.get<number>(API_URL + "oxi");
};

export const refreshOxi = () => {
    return axios.get(API_URL + "setOxi",
        { headers: authHeader() })
};



