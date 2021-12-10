import Axios from "axios";
import Cookies from "js-cookie";

const api = Axios.create({
  baseURL: "http://localhost:8080/api/",
});

api.interceptors.request.use((config) => {
  const token = Cookies.get("cis.validator");

  if (token !== undefined) {
    api.defaults.headers.common["Authorization"] = `Bearer ${token}`;
  }
  return config;
});

export { api };
