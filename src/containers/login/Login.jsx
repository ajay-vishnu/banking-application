import React, { useState } from 'react'
import './login.css';

const Login = ({ Login, error }) => {
  const [details, setDetails] = useState({email: "", password: ""});

  const submitHandler = e =>  {
    e.preventDefault();
    Login(details);
  }
  
  return (
    <form onSubmit={submitHandler}>
      <div className="form-inner">
        <h2>Login</h2>
        {/* ERROR */}
        <div className="form-group">
          <label htmlFor="username">Username: </label>
          <input type="text" name="username" id="username" onChange={e => setDetails({...details, email: e.target.value})} value={details.email}/>
        </div>
        <div className="form-group">
          <label htmlFor="password">Password: </label>
          <input type="password" name="password" id="password" onChange={e => setDetails({...details, password: e.target.value})} value={details.password} />
        </div>
        <input type="submit" value="Login" />
      </div>
    </form>
  )
}

export default Login
