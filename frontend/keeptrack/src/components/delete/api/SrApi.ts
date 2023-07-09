import axios from 'axios'
import { config } from '../../Constant'
import { func } from 'prop-types'
import { useCallback } from 'react'
import { SrUser } from '../types/srUser'

export const srApi = {
  authenticate,
  getAllUsers,
  getUser
}

function authenticate(username:string, password:string) {
  return instance.post('/auth/authenticate', { username, password }, {
    headers: { 'Content-type': 'application/json' }
  })
}

function getAllUsers() {
  return instance.get('/userApi/'), {
    headers: { 'Content-type': 'application/json' }
  }
}

function getUser(username:string,user:any) {
  const url =`/userApi/getUser/${username}`; 
  console.log(basicAuth)
  return instance.get(url, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

const instance = axios.create({
  baseURL: config.url.API_BASE_URL
})

function basicAuth(user:any) {
  return `Basic ${user.authdata}`
}

export const useSrApi = () => {
  const getSrUser = useCallback( (username: string, user:any) =>{
    const url =`/userApi/getUser/${username}`
    return instance.get<SrUser>(url, {
      headers: { 'Authorization': basicAuth(user) }
    });
  },[]);
}