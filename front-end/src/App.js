import './App.scss';
import Header from './Components/Header/Header';
import Sidebar from './Components/Sidebar/Sidebar';
import Login from './Views/Login/Index';

function App() {
  return (
    <>
    <div className='appContent'>
      {/* <Sidebar /> */}
      <Login />
    </div>
    </>
  );
}

export default App;
