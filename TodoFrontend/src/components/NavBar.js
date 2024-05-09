import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/NavBar.css';

function Navbar({ username }) {
  const navigate = useNavigate();
  const handleLogout = () => {
    
    localStorage.clear();
   
    
    navigate('/') 
  };

  return (
    <div className="navbar">
      <div className="navbar-left">
        <span>Welcome, {username}</span>
      </div>
      <div className="navbar-right">
        
        <button className="logout-btn" onClick={handleLogout}>Logout</button>
      </div>
    </div>
  );
}

export default Navbar;
