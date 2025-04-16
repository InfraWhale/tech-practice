import logo from './logo.svg';
import './App.css';
import Navbar from './components/Navbar';
import { Routes, Route } from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import Searchbar from './components/Searchbar';
import Main from './pages/Main';
import MyBook from './pages/MyBook';
import { useEffect, useState } from 'react';
import Login from './pages/Login';

function App() {
  const[authenticate, setAuthenticate] = useState(false)
  const navigate = useNavigate();

  return (
    <div>
      <Navbar />
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/mybook" element={ <MyBook/> } />
        <Route path="/login" element={<Login setAuthenticate={setAuthenticate} />} />
        
      </Routes>
    </div>
  );
}

export default App;
