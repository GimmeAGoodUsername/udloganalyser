import React from 'react';
import './App.css';
export default function Greeter(props) {
  props.first = 'Dave';
    return <h1>Hello, {props.first}</h1>;
  }
ReactDOM.createRoot(document.getElementById('root')).render( 
  <Greeter first="Joe" />
  );