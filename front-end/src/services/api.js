import axios from 'axios'
import cookies from 'js-cookie'

const api = axios.create({
    baseURL: 'http://localhost:8080/api'
})

api.interceptors.request.use(async config => {
    const token = cookies.get('CIS.TOKEN');
  
    if (token) {
      api.defaults.headers.authorization = `Bearer ${token}`;
      console.log(token);
    }
  
    return config;
  });

export default api;