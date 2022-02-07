import React, { useState } from 'react'

const Dashboard = ({ Withdraw, Transfer, Deposit, UserDetails, Logout }) => {

  const [transferDetails, setTransferDetails] = useState({userAccount: "", creditAmount: "", depositMessage: "", withdrawMessage: "", transferMessage: "", debitAmount: ""});
  
  return (
    <div>
      <h2>{UserDetails.name}</h2>
      <h2>{UserDetails.email}</h2>
      <h2>{UserDetails.sessionId}</h2>
      <h2>{UserDetails.accountNumber}</h2>
      <h2>{UserDetails.accountBalance}</h2>
      <h2>{UserDetails.phone}</h2>
      Dashboard
      <button onClick={Logout}>Logout</button>
      <div className="banking__dashboard-transactions">
        <div className="banking__dashboard-transactions_deposit">
          Deposit
          <input type="number" name="deposit_amount" id="deposit_input" onChange={e => setTransferDetails({...transferDetails, creditAmount: e.target.value})} value={transferDetails.creditAmount}/>
          <input type="text" name="deposit_message" id="deposit_message" onChange={e => setTransferDetails({...transferDetails, depositMessage: e.target.value})} value={transferDetails.depositMessage}/>
          <button onClick={() => Deposit(transferDetails)}>Deposit</button>
        </div>
        <div className="banking__dashboard-transactions_withdraw">
          Withdraw
          <input type="number" name="withdraw_amount" id="withdraw_input" onChange={e => setTransferDetails({...transferDetails, debitAmount: e.target.value})} value={transferDetails.debitAmount}/>
          <input type="text" name="withdraw_message" id="withdraw_message" onChange={e => setTransferDetails({...transferDetails, withdrawMessage: e.target.value})} value={transferDetails.withdrawMessage}/>
          <button onClick={() => Withdraw(transferDetails)}>Withdraw</button>
        </div>
        <div className="banking__dashboard-transactions_transfer">
          Transfer
          <input type="number" name="transfer_amount" id="transfer_input" />
          <input type="text" name="transfer_message" id="transfer_message" />
          <input type="submit" value="Transfer" />
        </div>
      </div>
    </div>
  )
}

export default Dashboard
