import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Routes from "./Routes";

class App extends Component {

  state = {};

  componentDidMount() {
    setInterval(this.hello, 250);
  }

  hello = () => {
    fetch('/api/hello')
      .then(response => response.text())
      .then(message => {
        this.setState({ message: message });
      });
  };

  render() {
    return (
      <div className="App">
        <Routes />
      </div>
    );
  }
}

export default App;
