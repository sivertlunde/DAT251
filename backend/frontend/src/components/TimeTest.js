import React from 'react';
import { withRouter } from "react-router";
import logo from '../logo.svg';


class TimeTest extends React.Component {

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
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
                <h1 className="App-title">{this.state.message}</h1>
                <p>
                    Edit <code>src/App.js</code> and save to reload. You should be able to see the changes straight away.
            </p>
                <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Learn React
            </a>
            </header>
        );
    }
}

export default withRouter(TimeTest)