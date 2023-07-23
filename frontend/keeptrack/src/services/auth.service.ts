import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/auth/";

export const login = (username: string, password: string) => {
  return axios
    .post(API_URL + "authenticate", {
      username,
      password,
    })
    .then((response) => {

      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }

      return response.data;
    });
};

export const logout = () => {
  axios.post("http://localhost:8080/" + "logout", { headers: authHeader() })
  localStorage.removeItem("user");
};

export const getCurrentUser = () => {
  const userStr = localStorage.getItem("user");
  if (userStr) return JSON.parse(userStr);

  return null;
};