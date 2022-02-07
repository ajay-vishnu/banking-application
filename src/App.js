import React, { Component, useState } from 'react'

import { Login, Signup, Dashboard } from './containers';
import './App.css';

class App extends Component {
  state = {
    user: {
      name: "",
      email: ""
    },
    error: "",
    curPage: "login"
  }
  
  adminUser = {
    email: "admin@admin.com",
    password: "admin123"
  }

  LoginStatus = details => {
    console.log(details);
    if (this.state.curPage === "login") {
      if (details.email == this.adminUser.email && details.password == this.adminUser.password) {
        console.log("logged in");
        const updatedUser = this.state.user;
        updatedUser.name = details.email;
        updatedUser.email = details.password;
        this.setState({user: updatedUser, curPage: "dash"});
      } else  {
        console.log("Details do not match!");
        this.setState({error: "Details do not match!"});
      }
    } else {
      if (details.password === details.confirmpassword) {
        console.log("signed up");
        const updatedUser = this.state.user;
        updatedUser.name = details.name;
        updatedUser.email = details.email;
        this.setState({user: updatedUser, curPage: "dash"});
        console.log(details);
      } else {
        console.log("Passwords don't match. Try again!");
        this.setState({error: "Passwords don't match. Try again!"});
      }
    }
  }

  SetCurrentPage = currentPage => {
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
        Hello
        {(this.state.curPage === "dash") ? (
          <Dashboard Logout={this.Logout} />
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
