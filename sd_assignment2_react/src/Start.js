import './Start.css';
import React, {Component} from "react";
import {Link, Switch, BrowserRouter, Route, Redirect} from 'react-router-dom'
class Start extends Component {
    constructor(props) {
        super(props);
        this.clickRegister= this.clickRegister.bind(this);
        this.clickLogin= this.clickLogin.bind(this);
    }

    async componentDidMount() {
        const response = await fetch('/start');
        const body = await response.json();
    }
    clickRegister(){
    }
    clickLogin(){

    }
    render() {
        return (
            /*
            <div>
                <h1>welcome Home!</h1>
                <Link to="/register">your component</Link>
            </div>*/
            <div className="Start">
                <header className="Start-header">
                    <h1 className="Start-title">Welcome to Food Panda!</h1>
                    <button className="Start-button">
                        <Link className="Start-link" to="/register">Register</Link>
                    </button>
                    <button className="Start-button">
                        <Link className="Start-link" to="/login">Login</Link>
                    </button>
                </header>
            </div>
        )
    }

}
export default Start;
