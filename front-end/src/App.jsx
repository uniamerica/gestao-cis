import { Fragment } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './Views/Login';
import Header from './Components/Header/Header';
import Sidebar from './Components/Sidebar/Sidebar';
import Home from './Views/Home';


function App() {
  return (
    <Fragment>
      <Header />
      <Sidebar />
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Login />} />
          <Route path='/Home' element={<Home />}/>
        </Routes>
      </BrowserRouter>
    </Fragment>

  );
}

export default App;
