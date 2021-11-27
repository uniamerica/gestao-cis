import { Fragment } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './Views/Login';
import Header from './Components/Header/Header';
import Sidebar from './Components/Sidebar/Sidebar';

function App() {
  return (
    <Fragment>
      <Header />
      <Sidebar />
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Login />} />
        </Routes>
      </BrowserRouter>
    </Fragment>

  );
}

export default App;
