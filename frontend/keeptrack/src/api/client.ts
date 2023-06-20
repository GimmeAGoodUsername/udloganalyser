
import axios from 'axios';

export const client = axios.create({
    baseURL: "http://localhost:8080/userApi/getAllUsers"
    
})

export interface ResponseAPI {
    id: number;
    name: string;
    race: string;
    role: string;

}