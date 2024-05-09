import React, { useState } from 'react';
import '../styles/LoginPage.css';
import { useNavigate } from "react-router-dom";
import client from './api/client';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';



function LoginPage() {
  const navigate = useNavigate();
  const [isSignUp, setIsSignUp] = useState(false);
  const [registerFormData, setRegisterFormData] = useState({
    firstName: '',
    lastName: '',
    username: '',
    password: ''
  });
  const [loginFormData, setLoginFormData] = useState({
    username: '',
    password: ''
  });

  const handleRegisterChange = (e) => {
    setRegisterFormData({ ...registerFormData, [e.target.name]: e.target.value });
    console.log(registerFormData)
  };

  const handleLoginChange = (e) => {
    setLoginFormData({ ...loginFormData, [e.target.name]: e.target.value });
    console.log(loginFormData)
  };

  const handleRegisterSubmit = async (e) => {
    e.preventDefault();

    try {

      // Make API request to create user
      const response = await client.post("/register", {

        firstName: registerFormData.firstName,
        lastName: registerFormData.lastName,
        username: registerFormData.username,
        password: registerFormData.password,
        role: 'USER'
      });

      // Handle successful signup
      console.log('User signed up successfully:', response.data.token);
      if (response?.data.token !== '') {
        localStorage.setItem('uid', JSON.stringify(response.data.userId));
        localStorage.setItem('userName', JSON.stringify(response.data.userName));
        localStorage.setItem('token', JSON.stringify(response.data.token));
        navigate("/projects")
      } else {
        alert('Error in signing up');
      }

    } catch (error) {
      console.error('Error signing up:', error);
      toast.error('Error is signing up');
    }




  };

  const handleLoginSubmit = async (e) => {
    e.preventDefault();

    try {
      // Make API request to login
      const response = await client.post("/login", {

        username: loginFormData.username,
        password: loginFormData.password
      });
      console.log(loginFormData);
      // Handle successful login
      if (response.data.token !== '' && loginFormData.username!='') {
        localStorage.setItem('uid', JSON.stringify(response.data.userId));
        localStorage.setItem('userName', JSON.stringify(response.data.userName));
        localStorage.setItem('token', JSON.stringify(response.data.token));
        navigate("/projects")
      } else {
        toast.error('Username or password incorrect');
      }

    } catch (error) {
      console.error('Error signing up:', error);
      toast.error('Username or password incorrect');

    }

  };

  const handleSignUpClick = () => {
    setIsSignUp(true);
  };

  const handleSignInClick = () => {
    setIsSignUp(false);
  };

  return (
    <div className='top-container'><div className={`login-container ${isSignUp ? 'right-panel-active' : ''}`}>
      <div className="form-container sign-up-container">
        <form className='general-form' onSubmit={handleRegisterSubmit}>
          <h1 style={{ color: '#446ce4' }}>Create Account</h1>
          
          <input className='form-input'
            type="text"
            placeholder="First Name"
            name="firstName"
            value={registerFormData.firstName}
            onChange={handleRegisterChange}
          />
          <input className='form-input'
            type="text"
            placeholder="Last Name"
            name="lastName"
            value={registerFormData.lastName}
            onChange={handleRegisterChange}
          />
          <input className='form-input'
            type="text"
            placeholder="Username"
            name="username"
            value={registerFormData.username}
            onChange={handleRegisterChange}
          />
          <input className='form-input'
            type="password"
            placeholder="Password"
            pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
            title="Must Contain 8 characters with caps , small letters and number"
            name="password"
            value={registerFormData.password}
            onChange={handleRegisterChange}
          />
          <button className=" button-container" type="submit">Sign Up</button>
        </form>
      </div>
      <div className="form-container sign-in-container">
        <form className='general-form' onSubmit={handleLoginSubmit}>
          <h1 style={{ color: '#446ce4' }}>Sign in</h1>
          <span className='info-text'>using your account</span>
          <input className='form-input'
            type="text"
            placeholder="Username"
            name="username"
            value={loginFormData.username}
            onChange={handleLoginChange}
          />
          <input className='form-input'
            type="password"
            placeholder="Password"
            name="password"
            value={loginFormData.password}
            onChange={handleLoginChange}
          />
          <button className='button-container' type="submit" style={{ marginTop: "10px" }}>Sign In</button>
        </form>
      </div>
      <div className="overlay-container">
        <div className="overlay">
          <div className="overlay-panel overlay-left">
            <h1>Welcome Back!</h1>
            <p className='login-text'>To keep connected with us please login with your personal info</p>
            <button className="ghost button-container" id="signIn" onClick={() => { setIsSignUp(false) }}>Sign In</button>
            <a className='link' href="/">
              <p style={{ color: 'white' }}>Return to Home Page</p>
            </a>
          </div>
          <div className="overlay-panel overlay-right">
            <h1>Hello, Friend!</h1>
            <p className='login-text'>Enter your personal details and start journey with us</p>
            <button className="ghost button-container" id="signUp" onClick={handleSignUpClick}>Sign Up</button>
            <a className='link' href="/">
            </a>
          </div>
        </div>
      </div>
    </div>
    <ToastContainer
      position="top-center"
      autoClose={5000}
      hideProgressBar={false}
      newestOnTop={false}
      closeOnClick
      rtl={false}
      pauseOnFocusLoss
      draggable
      pauseOnHover
    />    </div>




  );
};

export default LoginPage;