import React, { useState } from 'react'

const Dashboard = ({ Withdraw, Transfer, Deposit, UserDetails, Logout }) => {

  const [transferDetails, setTransferDetails] = useState({userAccount: "", creditAmount: "", depositMessage: "", withdrawMessage: "", transferMessage: "", debitAmount: ""});

  const depositHandler = () => {
    Deposit(transferDetails);
    setTransferDetails({...transferDetails, depositMessage: "", creditAmount: ""});
  }

  const withdrawHandler = () => {
    Withdraw(transferDetails);
    setTransferDetails({...transferDetails, withdrawMessage: "", debitAmount: ""});
  }

  const transferHandler = () => {
    Transfer(transferDetails);
    setTransferDetails({...transferDetails, depositMessage: "", creditAmount: ""});
  }
  
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
          <button onClick={() => depositHandler()}>Deposit</button>
        </div>
        <div className="banking__dashboard-transactions_withdraw">
          Withdraw
          <input type="number" name="withdraw_amount" id="withdraw_input" onChange={e => setTransferDetails({...transferDetails, debitAmount: e.target.value})} value={transferDetails.debitAmount}/>
          <input type="text" name="withdraw_message" id="withdraw_message" onChange={e => setTransferDetails({...transferDetails, withdrawMessage: e.target.value})} value={transferDetails.withdrawMessage}/>
          <button onClick={() => withdrawHandler()}>Withdraw</button>
        </div>
        <div className="banking__dashboard-transactions_transfer">
          Transfer
          <input type="number" name="transfer_account" id="transfer_account_input" onChange={e => setTransferDetails({...transferDetails, userAccount: e.target.value})} value={transferDetails.userAccount} />
          <input type="number" name="transfer_amount" id="transfer_amount_input" onChange={e => setTransferDetails({...transferDetails, transferAmount: e.target.value})} value={transferDetails.transferAmount} />
          <input type="text" name="transfer_message" id="transfer_message"  onChange={e => setTransferDetails({...transferDetails, transferMessage: e.target.value})} value={transferDetails.transferMessage}/>
          <button onClick={() => transferHandler()}>Transfer</button>
        </div>
      </div>
    </div>
  )
}

export default Dashboard
