import { createContext, useState, useEffect, Fragment } from "react";
import Cookies from "js-cookie";
import { decode } from "jsonwebtoken";

export const AuthContext = createContext({});

export default function AuthProvider({ children }) {
  const [user, setUser] = useState({});

  useEffect(() => {
    const token = Cookies.get("cis.validator");
    !!token && updateUserState(token);
  }, []);

  const addTokenInCookies = (jwt) => Cookies.set("cis.validator", jwt);

  const updateUserState = (token) => {
    const { email, role } = decode(token);
    setUser({ email, role });
  };

  const tokenExists = () => {
    const token = Cookies.get("cis.validator");
    return !!token;
  };

  return (
    <Fragment>
      <AuthContext.Provider
        value={{ user, addTokenInCookies, updateUserState, tokenExists }}
      >
        {children}
      </AuthContext.Provider>
    </Fragment>
  );
}
