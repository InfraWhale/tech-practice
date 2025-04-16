import './App.css';
import { Routes, Route, Link } from 'react-router-dom';
import HomePage from './Pages/HomePage';
import ReactQueryPage from './Pages/ReactQueryPage';
import ReactQueryPage2 from './Pages/ReactQueryPage2';
import NormalPage from './Pages/NormalPage';

function App() {
  return (
    <div className="App">
      <nav style={{ backgroundColor: 'beige', padding: '20px' }}>
        <Link to="/" style={{ marginRight: '10px' }}>
          Homepage
        </Link>
        <Link to="/normal-page" style={{ marginRight: '10px' }}>Normal Page</Link>
        <Link to="/react-query" style={{ marginRight: '10px' }}>React Query</Link>
        <Link to="/react-query-2">React Query2</Link>
      </nav>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/normal-page" element={<NormalPage />} />
        <Route path="/react-query" element={<ReactQueryPage />} />
        <Route path="/react-query-2" element={<ReactQueryPage2 />} />
      </Routes>
    </div>
  );
}

export default App;