import { createContext, useState, useEffect, Fragment } from "react";
import Cookies from "js-cookie";

export const AuthContext = createContext({});

export default function AuthProvider({ children }) {
  const [isAuth, setIsAuth] = useState(false);
  const [user, setUser] = useState({});

  useEffect(() => {
    const token = Cookies.get("cis.validator");
    token ? setIsAuth(true) : setIsAuth(false);
  }, []);

  return (
    <Fragment>
      <AuthContext.Provider value={{ isAuth, setIsAuth, user, setUser }}>
        {children}
      </AuthContext.Provider>
    </Fragment>
  );
}
