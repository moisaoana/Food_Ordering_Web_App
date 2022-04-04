
import './App.css';
import React, {Component} from "react";
import {BrowserRouter,Link, Routes, Route, Redirect} from 'react-router-dom'
import Start from "./Start";
import Register from "./Register";


class App extends Component {
    /*
    state = {};
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

     */
    render() {
        return (
            /*
            <div className="App">
                <header className="App-header">

                    <h1 className="App-title">Welcome to Food Panda!</h1>
                    <button onClick={this.clickRegister} className="App-button">
                        Create Account
                    </button>
                    <button onClick={this.clickLogin} className="App-button">
                        Login
                    </button>
            </header>



            </div> */
            <BrowserRouter>
                <Routes>
                    <Route path="/" component={Start} exact/>
                    <Route path="/register" component={Register}/>
                </Routes>
            </BrowserRouter>
    )
    };
}

export default App;
