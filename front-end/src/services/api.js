import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api'
})

// api.interceptors.request.use(async config => {
//     const token = ;
  
//     if (token) {
//       api.defaults.headers.authorization = `Bearer ${token}`;
//     }
  
//     return config;
//   });

export default api;