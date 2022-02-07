import React, { useState } from 'react'
import './signup.css';

const Signup = ({ Signup, CurPage, error }) => {
  const [details, setDetails] = useState({name: "", username: "", governmentId: "", address: "", phone: "", email: "", password: "", confirmpassword: ""});

  const submitHandler = e =>  {
    e.preventDefault();
    Signup(details);
  }
  
  return (
    <div className="signup__center">
      <h1>Signup</h1>
      <form onSubmit={submitHandler}>
        <div className="signup__form-inner">
          {(error !== "") ? (<div className="error">{error}</div>) : ""}
          <div className="signup__form-group">
            <span className="signup__from-group_field">
              <input required type="text" name="name" id="name" onChange={e => setDetails({...details, name: e.target.value})} value={details.name}/>
              <span />
              <label htmlFor="name">Name: </label>
            </span>
            <span className="signup__from-group_field">
              <input required type="text" name="username" id="username" onChange={e => setDetails({...details, username: e.target.value})} value={details.username}/>
              <span />
              <label htmlFor="username">Username: </label>
            </span>
          </div>
          <div className="signup__form-group">
            <span className="signup__from-group_field">
              <input required type="text" name="governmentId" id="governmentId" onChange={e => setDetails({...details, governmentId: e.target.value})} value={details.governmentId}/>
              <span />
              <label htmlFor="governmentId">Government ID: </label>
            </span>
            <span className="signup__from-group_field">
              <input required type="text" name="address" id="address" onChange={e => setDetails({...details, address: e.target.value})} value={details.address}/>
              <span />
              <label htmlFor="address">Address: </label>
            </span>
          </div>
          <div className="signup__form-group">
            <span className="signup__from-group_field">
              <input required type="number" name="phone" id="phone" onChange={e => setDetails({...details, phone: e.target.value})} value={details.phone}/>
              <span />
              <label htmlFor="phone">Phone: </label>
            </span>
            <span className="signup__from-group_field">
              <input required type="text" name="email" id="email" onChange={e => setDetails({...details, email: e.target.value})} value={details.email}/>
              <span />
              <label htmlFor="email">Email: </label>
            </span>
          </div>
          <div className="signup__form-group">
            <span className="signup__from-group_field">
              <input required type="password" name="password" id="password" onChange={e => setDetails({...details, password: e.target.value})} value={details.password} />
              <span />
              <label htmlFor="password">Password: </label>
            </span>
            <span className="signup__from-group_field">
              <input required type="password" name="confirm-password" id="confirm-password" onChange={e => setDetails({...details, confirmpassword: e.target.value})} value={details.confirmpassword} />
              <span />
              <label htmlFor="confirm-password">Confirm Password: </label>
            </span>
          </div>
          <input type="submit" value="Sign Up" />
        </div>
        <div className="signup__login_link">
          Already have an account? <span onClick={() => CurPage("login")}>Login</span>
        </div>
      </form>
    </div>
  )
}

export default Signup
