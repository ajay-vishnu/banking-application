import React, { useState } from 'react'

import { Login, Signup, Dashboard } from './containers';
import './App.css';

const App = () => {
  const adminUser = {
    email: "admin@admin.com",
    password: "admin123"
  }

  const [user, setUser] = useState({name: "", email: ""});
  const [error, setError] = useState("");

  const LoginStatus = details => {
    console.log(details);
    if (details.email == adminUser.email && details.password == adminUser.password) {
      console.log("logged in");
      setUser({
        name: details.email,
        email: details.password
      });
    } else  {
      console.log("Details do not match!");
    }
  }

  const Logout = () => {
    console.log("Logout");
    setUser({name: "" , email: ""});
  }
  
  return (
    <div className="App">
      Hello
      {(user.email !== "") ? (
        <Dashboard Logout={Logout} />
      ) : (
        <Login Login={LoginStatus} error={error} />
      )}
    </div>
  )
}

export default App
