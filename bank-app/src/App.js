import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Deposit from "./components/Deposit";
import Loan from "./components/Loan";
import Home from "./pages/Home";

function App() {
  return (
    <Router>
      <Navbar />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/deposit" element={<Deposit />} />
        <Route path="/loan" element={<Loan />} />
      </Routes>
    </Router>
  );
}

export default App;