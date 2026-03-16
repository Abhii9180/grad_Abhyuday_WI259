import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav className="navbar">
      <h2>🏦 MyBank</h2>

      <div>
        <Link to="/">Home</Link>
        <Link to="/deposit">Deposit</Link>
        <Link to="/loan">Loan</Link>
      </div>
    </nav>
  );
}

export default Navbar;