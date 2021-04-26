import React from 'react';
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
        <nav>
          <input type="checkbox" id="check"></input>
          <label for="check" class="checkbtn">
            <i class="fas fa-bars"></i>
          </label>
          <div class ="logo"><a href="/"><img src="logo.png" alt="Min Handleliste"></img></a></div>
          <ul classname="header">
            <li><a href="/">Hjem</a></li>
            <li><a href="/browse">Browse</a></li>
            <li><a href="/handleliste">Handleliste</a></li>
            <li><a href="/sammenlign">Sammenlign priser</a></li>
            {!initializing ? 
              user ?
                //<li className="App-link" href="/" onClick={()=> {firebase.auth().signOut();}}> Log Out</li>
                <li><a href= "/" onClick={()=> {firebase.auth().signOut();}}> Logg ut</a></li>
              :
                <li><a href="/login">Logg inn</a></li>
              :
              <div></div>
            }
          </ul>
        </nav>
        <Routes />
      </div>
  );
}

export default App;
