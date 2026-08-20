import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AssetList from './pages/AssetList';
import AssetDetails from './pages/AssetDetails';
import './index.css';

export default function App() {
  return (
      <Router>
        <Routes>
          <Route path="/" element={<AssetList />} />
          <Route path="/assets/:id" element={<AssetDetails />} />
        </Routes>
      </Router>
  );
}