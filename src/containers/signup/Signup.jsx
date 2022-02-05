import React from 'react'

const Signup = ({ Signup, CurPage }) => {
  return (
    <div>
      <h2>Signup</h2>
      <button onClick={() => CurPage("login")}>Login</button>
    </div>
  )
}

export default Signup
