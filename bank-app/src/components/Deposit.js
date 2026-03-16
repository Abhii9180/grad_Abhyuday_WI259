import { useState } from "react";

function Deposit() {
  const [amount, setAmount] = useState("");

  const handleDeposit = () => {
    alert("Deposited ₹" + amount);
    setAmount("");
  };

  return (
    <div className="container">
      <h2>Deposit Money</h2>

      <input
        type="number"
        placeholder="Enter Amount"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
      />

      <button onClick={handleDeposit}>Deposit</button>
    </div>
  );
}

export default Deposit;