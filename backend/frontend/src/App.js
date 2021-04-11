import React, { Component } from 'react';
import './App.css';
import Routes from "./Routes";

class App extends Component {

  

  render() {
    return (
      <div className="App">
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
            <li><a href="/logginn">Logg inn</a></li>
          </ul>
        </nav>
        <Routes />
      </div>
    );
  }
}

export default App;
