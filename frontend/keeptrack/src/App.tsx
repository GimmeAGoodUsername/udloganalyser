import React from 'react';
import logo from './logo.svg';
import './App.css';
import { GetUsers } from "./components/GetUsers";
import axios from 'axios';
function App() {

  return (    
    <div id='root'>
      <h1>SR APP</h1>
      <GetUsers></GetUsers>
      </div>
  )
}

export default App;