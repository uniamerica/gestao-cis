import React, { Fragment, useEffect } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./Views/Login";
import Header from "./Components/Header/Header";
import Sidebar from "./Components/Sidebar/Sidebar";
import Home from "./Views/Home";
import Erro404 from "./Views/Erro404"
import { NameForm } from "./Views/Testes";
import AuthProvider from "./Contexts/authContext";
import Professional from "./Views/Professional";

function App() {
  return (
    <Fragment>
      <AuthProvider>
        <BrowserRouter>
          <Header />
          <Sidebar />
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/home" element={<Home />} />
            <Route path="/testes" element={<NameForm />} />
            <Route path="/profissionais" element={<Professional />} />
            <Route path="*" element={<Erro404 />} />
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </Fragment>
  );
}

export default App;
