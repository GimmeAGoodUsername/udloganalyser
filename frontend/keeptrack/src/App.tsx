import React from 'react';
import { useState, useEffect } from "react";
import IUser from './types/user.type';
import * as AuthService from "./services/auth.service";

import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import Login from "./components/Login";
import Home from "./components/Home";
import BoardUser from "./components/BoardUser";
import Order from './components/Orders';

import EventBus from "./common/EventBus";
import ISrUser from './types/sruser.type';
import Profile from './components/Profile';

const App: React.FC = () => {
  const [currentUser, setCurrentUser] = useState<ISrUser | undefined>(undefined);
  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (user) {
      setCurrentUser(user);
    }

    EventBus.on("logout", logOut);

    return () => {
      EventBus.remove("logout", logOut);
    };
  }, []);

  const logOut = () => {
    AuthService.logout();
    setCurrentUser(undefined);
  };


  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <Link to={"/"} className="navbar-brand">
          SR Tool
        </Link>

        <div className="navbar-nav mr-auto">

          
        </div>

        {currentUser ? (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/profile"} className="nav-link">
                {currentUser.name}
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/orders"} className="nav-link">
                Bestellungen
              </Link>
            </li>
            <li className="nav-item">
              <a href="/login" className="nav-link" onClick={logOut}>
                LogOut
              </a>
            </li>
          </div>
        ) : (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/login"} className="nav-link">
                Login
              </Link>
            </li>


          </div>
        )}
      </nav>

      <div className="container mt-2">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path='/profile' element={<Profile />} />
          <Route path="/orders" element={<Order />} />
          <Route path="/login" element={<Login />} />
          <Route path="/user" element={<BoardUser />} />
        </Routes>
      </div>
    </div>
  );
};

export default App;