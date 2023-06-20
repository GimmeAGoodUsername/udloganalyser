import { client, ResponseAPI } from '../api/client';

export const getAllUsers = async (): Promise<ResponseAPI[]> => {

    const { data } = await client.get<ResponseAPI[]>('',{
        withCredentials: true,
        headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"},
        auth:{
        username: "ad",
        password: "password"}
    })

    return data
}