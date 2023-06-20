import React from 'react';
import logo from './logo.svg';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom'
import 'semantic-ui-css/semantic.min.css'
import './index.css';
import { AuthProvider } from './context/AuthContext';
import Login from './home/Login';
import Navbar from './misc/Navbar';
function App() {

  return (
    <AuthProvider>
    <Router>
        <Navbar />
        <Routes>
        <Route path='/login' element={<Login />} />


          </Routes>
       </Router>
      </AuthProvider>

  );
}

export default App;