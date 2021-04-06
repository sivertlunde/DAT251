import React, { Component } from 'react';
import './App.css';
import Routes from "./Routes";
import firebase from 'firebase';

var config = {
  apiKey: process.env.REACT_APP_API_KEY,
  authDomain: process.env.REACT_APP_AUTHDOMAIN,
  databaseURL: process.env.REACT_APP_BASEURL,
  projectId: process.env.REACT_APP_PROJECT_ID,
  storageBucket: process.env.REACT_APP_STORAGEBUCKET,
  messagingSenderId: process.env.REACT_APP_MESSAGING_SENDER_ID,
  appId: process.env.REACT_APP_APP_ID 
}

firebase.initializeApp(config);

class App extends Component {

  

  render() {
    return (
      <div className="App">
        <Routes />
      </div>
    );
  }
}

export default App;
