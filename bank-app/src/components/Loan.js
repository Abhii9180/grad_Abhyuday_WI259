import { useState } from "react";

function Loan() {
  const [loan, setLoan] = useState("");

  const handleLoan = () => {
    alert("Loan Requested: ₹" + loan);
    setLoan("");
  };

  return (
    <div className="container">
      <h2>Apply for Loan</h2>

      <input
        type="number"
        placeholder="Enter Loan Amount"
        value={loan}
        onChange={(e) => setLoan(e.target.value)}
      />

      <button onClick={handleLoan}>Apply</button>
    </div>
  );
}

export default Loan;