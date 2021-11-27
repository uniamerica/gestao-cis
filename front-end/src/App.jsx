import { Fragment } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './Views/Login/Index';
import Header from './Components/Header/Header';
import Home from './Views/Home';


function App() {
  return (
    <Fragment>
      <Header />
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
