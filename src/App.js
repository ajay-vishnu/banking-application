import React, { Component, useState } from 'react'
import axios from 'axios';

import { Login, Signup, Dashboard } from './containers';
import './App.css';

class App extends Component {
  state = {
    user: {
      name: "",
      email: "",
      sessionId: ""
    },
    transaction: {},
    error: "",
    curPage: "login"
  }
  
  adminUser = {
    email: "admin@admin.com",
    password: "admin123"
  }

  Deposit = details => {
    console.log(this.state);
    console.log(details);
    const transactionDetails = {
      userAccount: this.state.user.accountNumber,
      creditAmount: details.creditAmount,
      transactionType: "deposit",
      message: details.message,
      createdBy: this.state.user.username
    }
    axios
        .post("http://localhost:8080/api/v1/creditamount", transactionDetails)
        .then(response => {
          console.log(response)
        })
        .catch(error => {
          console.log(error);
          this.setState({error: "Invalid details"});
        })
  }

  Withdraw = details => {
    
  }

  Transfer = details => {

  }

  LoginStatus = details => {
    console.log(this.state);
    console.log(details);
    if (this.state.curPage === "login") {
      const userDetails = {
        username: details.email,
        password: details.password
      }
      axios
        .post("http://localhost:8080/api/v1/session", userDetails)
        .then(response => {
          this.ValidStatusLogin(response)
        })
        .catch(error => {
          this.setState({error: "Invalid details"});
        })
    }
    if (this.state.curPage === "signup" && details.password === details.confirmpassword)  {
      const userDetails = {
        username: details.username,
        password: details.password,
        holderAddress: details.address,
        email: details.email,
        holderName: details.name,
        governmentId: details.governmentId,
        accountBalance: 0,
        phone: details.phone
      }
      axios
        .post("http://localhost:8080/api/v1/userAccount", userDetails)
        .then(response => {
          this.getSession(details)
        })
        .catch(error => {
          this.setState({error: "Invalid details"});
        })
    } else {
      console.log("Passwords don't match. Try again!");
      this.setState({error: "Passwords don't match. Try again!"});
    }
  }

  getSession = response => {
    const userDetails = {
      username: response.username,
      password: response.password
    }
    console.log(userDetails);
    axios
      .post("http://localhost:8080/api/v1/session", userDetails)
      .then(response => {
        this.ValidStatusLogin(response)
      })
      .catch(error => {
        this.setState({error: "Invalid details"});
      })
  }

  ValidStatusLogin = response => {
    console.log(response.data);
    if (response.data.sessionId !== "") {
      console.log("logged in");
      const updatedUser = this.state.user;
      updatedUser.name = response.data.clientUser.holderName;
      updatedUser.email = response.data.clientUser.clientUser.email;
      updatedUser.username = response.data.clientUser.clientUser.username;
      updatedUser.sessionId = response.data.sessionId;
      updatedUser.accountNumber = response.data.clientUser.accountNumber;
      updatedUser.accountBalance = response.data.clientUser.accountBalance;
      updatedUser.phone = response.data.clientUser.phone;
      this.setState({user: updatedUser, curPage: "dash"});
    } else  {
      console.log("Details do not match!");
      this.setState({error: "Details do not match!"});
    }
  }

  SetCurrentPage = currentPage => {
    console.log("set current page in app.js");
    this.setState({curPage: currentPage});
  }

  Logout = () => {
    console.log("Logout");
    const updatedUser = this.state.user;
    updatedUser.name = "";
    updatedUser.email = "";
    this.setState({user: updatedUser, curPage: "login"});
  }

  render()  {
    return (
      <div className="App">
        {(this.state.curPage === "dash") ? (
          <Dashboard Withdraw={this.Withdraw} Transfer={this.Transfer} Deposit={this.Deposit} UserDetails={this.state.user} Logout={this.Logout} />
        ) : (
          (this.state.curPage === "login") ? (
            <Login Login={this.LoginStatus} CurPage={this.SetCurrentPage} error={this.error} />
          ) : (
            <Signup Signup={this.LoginStatus} CurPage={this.SetCurrentPage} error={this.error} />
          )
        )}
      </div>
    )
  }
  
}

export default App
