import React from 'react'
import "./Header.css";
function Header() {
  return (
    <div className='bg-primary bg-gradient text-light fw-bold header d-flex flex-row py-1 px-2 justify-content-between'>
        <div className='logo'>
        </div>
        <div className='mx-4 fs-3 heading px-2'>Flight Status Dashboard</div>
    </div>
  )
}

export default Header