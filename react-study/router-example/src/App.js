import logo from './logo.svg';
import {useState} from "react";
import './App.css';
import Homepage from './page/Homepage';
import Aboutpage from './page/Aboutpage';
import Productpage from './page/Productpage';
import { Routes, Route, Navigate } from "react-router-dom";
import ProductDetailPage from './page/ProductDetailPage';
import Loginpage from './page/Loginpage';
import UserPage from './page/UserPage';

function App() {
  const [authenticate, setAuthenticate] = useState(false);
  const PrivateRoute = () => {
    return authenticate === true ? <UserPage/ > : <Navigate to="/login" /> // 리다이렉트
  }

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Homepage />}/>
        <Route path="/about" element={<Aboutpage />}/>
        <Route path="/products" element={<Productpage />}/>
        <Route path="/products/:id" element={<ProductDetailPage />} />
        <Route path="/products/:id" element={<ProductDetailPage/>} />
        <Route path="/login" element={<Loginpage />}/>
        <Route path="/user" element={<PrivateRoute />}/>
      </Routes>
    </div>
  );
}

export default App;
