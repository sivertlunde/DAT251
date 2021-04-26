import React, { Component } from 'react';
import './App.css';
import Routes from "./Routes";
import firebase from 'firebase';
import { useState, useEffect } from 'react';

var config = {
  apiKey: "AIzaSyACKQCdycUQOvW0iuRe0nmi0wg0fXakCLk",
  authDomain: "dat251.firebaseapp.com",
  projectId: "dat251",
  storageBucket: "dat251.appspot.com",
  messagingSenderId: "375043058577",
  appId: "1:375043058577:web:0b59749814ec68c2bdc830"

}

firebase.initializeApp(config);

function App() {

  const [user, setUser] = useState();
  const [initializing, setInitializing] = useState(true);

  useEffect(() => {
    const subscriber = firebase.auth().onAuthStateChanged(
      (_user) =>{
        setUser(_user);
        setInitializing(false);
      }
    );
    return subscriber;
  }, []);
  
  return (
      <div className="App">
        <a className="App-link" href = "/">handleliste</a>
        {!initializing ? 
          user ?
          <a className="App-link" href="/" onClick={()=> {firebase.auth().signOut();}}> Log Out</a>
          :
          <a className="App-link" href="/login"> Log in</a>
          :
          <div></div>
        }
        <Routes></Routes>
      </div>
  );
}

export default App;
