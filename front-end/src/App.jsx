import React, { Fragment } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import PacientLogin from "./Views/Login/PacientLogin";
import AdminLogin from "./Views/Login/AdminLogin";
import Header from "./Components/Header/Header";
import Sidebar from "./Components/Sidebar/Sidebar";
import Home from "./Views/Home";
import Erro404 from "./Views/Erro404";
import AuthProvider from "./Contexts/authContext";
import Professional from "./Views/Professional";
import Rooms from "./Views/Rooms";
import Patients from "./Views/Patient";

function App() {
  return (
    <Fragment>
      <AuthProvider>
        <BrowserRouter>
          <Header />
          <Sidebar />
          <Routes>
            <Route path="/" exact element={<PacientLogin />} />
            <Route path="/home" element={<Home />} />
            <Route path="/admin/login" element={<AdminLogin />} />
            <Route path="/admin/profissionais" element={<Professional />} />
            <Route path="/admin/pacientes" element={<Patients />} />
            <Route path="/admin/salas" element={<Rooms />} />
            <Route path="*" element={<Erro404 />} />
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </Fragment>
  );
}

export default App;
