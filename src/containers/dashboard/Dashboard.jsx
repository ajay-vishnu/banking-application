import React, { useState } from 'react';
import './dashboard.css';

const Dashboard = ({ Credits, Debits, Withdraw, Transfer, Deposit, UserDetails, Logout }) => {

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
    <div className="banking__dashboard">
      <h2>Name: {UserDetails.name}</h2>
      <h2>Email: {UserDetails.email}</h2>
      <h2>Account Number: {UserDetails.accountNumber}</h2>
      <h2>Account Balance: {UserDetails.accountBalance}</h2>
      <h2>User phone: {UserDetails.phone}</h2>
      Dashboard
      <button onClick={Logout}>Logout</button>
      <div className="banking__dashboard-transactions">
        <div className="banking__dashboard-transactions_deposit">
          <h2>Deposit</h2>
          <label htmlFor="deposit_amount">Amount</label>
          <input type="number" name="deposit_amount" id="deposit_input" onChange={e => setTransferDetails({...transferDetails, creditAmount: e.target.value})} value={transferDetails.creditAmount}/>
          <label htmlFor="deposit_message">Message</label>
          <input type="text" name="deposit_message" id="deposit_message" onChange={e => setTransferDetails({...transferDetails, depositMessage: e.target.value})} value={transferDetails.depositMessage}/>
          <label htmlFor="deposit_misc">Misc</label>
          <input type="text" name="deposit_misc" id="deposit_misc" />
          <button onClick={() => depositHandler()}>Deposit</button>
          <span className="spacer"></span>
        </div>
        <div className="banking__dashboard-transactions_withdraw">
          <h2>Withdraw</h2>
          <label htmlFor="withdraw_amount">Amount</label>
          <input type="number" name="withdraw_amount" id="withdraw_input" onChange={e => setTransferDetails({...transferDetails, debitAmount: e.target.value})} value={transferDetails.debitAmount}/>
          <label htmlFor="withdraw_message">Message</label>
          <input type="text" name="withdraw_message" id="withdraw_message" onChange={e => setTransferDetails({...transferDetails, withdrawMessage: e.target.value})} value={transferDetails.withdrawMessage}/>
          <label htmlFor="withdraw_misc">Misc</label>
          <input type="text" name="withdraw_misc" id="withdraw_misc" />
          <button onClick={() => withdrawHandler()}>Withdraw</button>
          <span className="spacer"></span>
        </div>
        <div className="banking__dashboard-transactions_transfer">
          <h2>Transfer</h2>
          <label htmlFor="transfer_account">Account</label>
          <input type="number" name="transfer_account" id="transfer_account_input" onChange={e => setTransferDetails({...transferDetails, userAccount: e.target.value})} value={transferDetails.userAccount} />
          <label htmlFor="transfer_amount">Amount</label>
          <input type="number" name="transfer_amount" id="transfer_amount_input" onChange={e => setTransferDetails({...transferDetails, transferAmount: e.target.value})} value={transferDetails.transferAmount} />
          <label htmlFor="transfer_message">Message</label>
          <input type="text" name="transfer_message" id="transfer_message"  onChange={e => setTransferDetails({...transferDetails, transferMessage: e.target.value})} value={transferDetails.transferMessage}/>
          <button onClick={() => transferHandler()}>Transfer</button>
        </div>
      </div>
      <h2>Credits:</h2>
      <table className="table bg-white rounded shadow-sm table-hover">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Transaction Date</th>
            <th scope="col">Transaction Amount</th>
            <th scope="col">Transaction Message</th>
            <th scope="col">Sender's Account Number</th>
            <th scope="col">Account Balance</th>
          </tr>
        </thead>
        <tbody>
          {Credits.map((transaction, index) => {
            return <tr>
              <th scope="row">{index}</th>
              <td>{transaction.createdAt}</td>
              <td>{transaction.creditAmount}</td>
              <td>{transaction.message}</td>
              <td>{transaction.senderUserAccount}</td>
              <td>{transaction.accountBalance}</td>
            </tr>
          })}
        </tbody>
      </table>
      <h2>Debits:</h2>
      <table className="table bg-white rounded shadow-sm table-hover">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Transaction Date</th>
            <th scope="col">Transaction Amount</th>
            <th scope="col">Transaction Message</th>
            <th scope="col">Receiver's Account Number</th>
            <th scope="col">Account Balance</th>
          </tr>
        </thead>
        <tbody>
          {Debits.map((transaction, index) => {
            return <tr>
              <th scope="row">{index}</th>
              <td>{transaction.createdAt}</td>
              <td>{transaction.debitAmount}</td>
              <td>{transaction.message}</td>
              <td>{transaction.sentUserAccount}</td>
              <td>{transaction.accountBalance}</td>
            </tr>
          })}
        </tbody>
      </table>
    </div>
  )
}

export default Dashboard
