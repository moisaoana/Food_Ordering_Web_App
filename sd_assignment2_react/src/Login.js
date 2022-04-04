import './Login.css';
import React, {Component} from "react";
import {Link, Switch, BrowserRouter, Route, Redirect} from 'react-router-dom'


class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {username: '',password: ''};
        this.handleChangeUsername = this.handleChangeUsername.bind(this);
        this.handleChangePassword = this.handleChangePassword.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    async componentDidMount() {
        const response = await fetch('/login');
        const body = await response.json();
    }

    handleChangeUsername(event) {
        this.setState({username: event.target.value});
    }
    handleChangePassword(event) {
        this.setState({password: event.target.value});
    }

    handleSubmit(event) {
            event.preventDefault();
            alert('Submitted: ' + this.state.username + ' ' + this.state.password);
            const requestOptions = {
                method: 'POST',
                headers:{ 'Content-Type': 'application/json'},
                body: JSON.stringify(this.state)
            };
            fetch('http://localhost:8080/login',requestOptions)
                .then(response => response.json())
                .then(response =>console.log(response+ " aa"));

    }

    render() {
        return (
            <div className="Login">
                <header className="Login-header">
                    <h1 className="Login-title">Login</h1>
                    <form onSubmit={this.handleSubmit}>
                        <div className="input">
                            <label>Username</label>
                            <input type="text" name="username" value={this.state.username} onChange={this.handleChangeUsername} required />
                        </div>
                        <div className="input">
                            <label>Password</label>
                            <input type="password" name="password" value={this.state.password} onChange={this.handleChangePassword} required />
                        </div>
                        <div align="right">
                            <input className="Login-button" type="submit" value="Login" />
                            <button className="Login-button">
                                <Link className="Login-link" to="/">Back</Link>
                            </button>
                        </div>
                    </form>
                </header>
            </div>
        )
    };
}

export default Login;
