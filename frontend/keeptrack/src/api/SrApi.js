import axios from 'axios'
import { config} from '../Constant'

export const srApi = {
  authenticate,
  getAllUsers
}

function authenticate(username, password) {
    return instance.post('/auth/authenticate', { username, password }, {
      headers: { 'Content-type': 'application/json' }
})}

function getAllUsers(){
    return instance.get('/userApi/'),{
        headers: { 'Content-type': 'application/json' }
    }
}

const instance = axios.create({
    baseURL: config.url.API_BASE_URL
  })

  function basicAuth(user) {
    return `Basic ${user.authdata}`
  }