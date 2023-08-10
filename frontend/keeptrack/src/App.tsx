import React from 'react';
import { useState, useEffect } from "react";
import * as AuthService from "./services/auth.service";

import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import Login from "./components/Login";
import Home from "./components/Home";
import BoardUser from "./components/BoardUser";
import Order from './components/Orders';
import { getUserByName } from "./services/user.service";

import EventBus from "./common/EventBus";
import ISrUser, { Authority } from './types/sruser.type';
import Profile from './components/Profile';
import dayjs from "dayjs";

import de from 'dayjs/locale/de';
import Admin from './components/Admin';
import Scanner from './components/Scanner';
dayjs.locale(de)

const App: React.FC = () => {
  const [currentUser, setCurrentUser] = useState<ISrUser | undefined>(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (user) {
      getUserByName(user.name).then(
        (response) => {
          setCurrentUser(response.data)
        },
        (error) => {
          const _content =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          setCurrentUser(_content)
        }
      );
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

  function isAdmin(sruser: ISrUser) {
    let role: boolean = sruser.authorities === Authority.ADMIN;
    debugger
    return role;
  }


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
            {isAdmin(currentUser) ? (
              <Link to={"/admin"} className='nav-link'>
                Admin Panel
              </Link>
            ) : (
              <div></div>
            )}
            <li className="nav-item">
              <Link to={"/scan"} className="nav-link">
                Scan Tool
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/orders"} className="nav-link">
                Bestellungen
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/profile"} className="nav-link">
                {currentUser.name}
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
        )
        }
      </nav >

      <div className="container mt-2">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path='/admin' element={<Admin />} />
          <Route path='/profile' element={<Profile />} />
          <Route path="/orders" element={<Order />} />
          <Route path="/login" element={<Login />} />
          <Route path="/scan" element={<Scanner />} />

          <Route path="/user" element={<BoardUser />} />
        </Routes>
      </div>
    </div >
  );
};
export default App;