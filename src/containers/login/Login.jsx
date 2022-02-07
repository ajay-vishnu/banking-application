import React, { useState } from 'react'
import './login.css';

const Login = ({ Login, CurPage, error }) => {
  const [details, setDetails] = useState({email: "", password: ""});

  const submitHandler = e =>  {
    e.preventDefault();
    Login(details);
  }
  
  return (
    <div className="login__center">
      <h1>Login</h1>
      <form onSubmit={submitHandler}>
        <div className="login__form-inner">
          {(error !== "") ? (<div className="error">{error}</div>) : ""}
          <div className="login__form-group">
            <input required type="text" name="username" id="username" onChange={e => setDetails({...details, email: e.target.value})} value={details.email}/>
            <span></span>
            <label htmlFor="username">Username</label>
          </div>
          <div className="login__form-group">
            <input required type="password" name="password" id="password" onChange={e => setDetails({...details, password: e.target.value})} value={details.password} />
            <span></span>
            <label htmlFor="password">Password</label>
          </div>
          <input type="submit" value="Login" />
        </div>
        <div className="login__signup_link">
          Not a member? <span onClick={() => CurPage("signup")}>Sign Up</span>
        </div>
      </form>
    </div>
  )
}

export default Login
