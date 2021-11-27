import { Fragment } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./Views/Login";
import Header from "./Components/Header/Header";
import Sidebar from "./Components/Sidebar/Sidebar";
import Home from "./Views/Home";
import { NameForm } from './Views/Testes'

function App() {
  const logged = {value: false}

  const handleLogged = (loggedValue) => {
    this.setState({logged: loggedValue})
  }

  return (
    <Fragment>
      <BrowserRouter>
        <Header />
        <Sidebar />
        <Routes>
          <Route path="/" element={<Login />}/>
          <Route path="/home" element={<Home />} /> 
          <Route path="/testes" element={<NameForm />} /> 
        </Routes>
      </BrowserRouter>
    </Fragment>
  );
}

export default App;
