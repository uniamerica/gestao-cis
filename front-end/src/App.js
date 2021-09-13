import './App.scss';
import Header from './Components/Header/Header';
import Sidebar from './Components/Sidebar/Sidebar';
import Login from './Views/Login/Index';

function App() {
  return (
    <>
    <Header name='Meu Anjo' />
    <div className='appContent'>
      <Sidebar />
      <Login />
    </div>
    </>
  );
}

export default App;
