import React from 'react';
import ReactDOM from 'react-dom';
import {HashRouter, Route, Routes, Switch} from "react-router-dom";
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals'
import {BrowserRouter} from 'react-router-dom'
import Start from "./Start";
import Register from "./Register";
import Login from "./Login";

/*
ReactDOM.render(
    <HashRouter>
        <App />
    </HashRouter>,
    document.getElementById('root')
);
*/
ReactDOM.render(
            <HashRouter>
                <Routes>
                    <Route exact path="/" element={<Start />}/>
                    <Route exact path="/register" element={<Register />}/>
                    <Route exact path="/login" element={<Login />}/>
                </Routes>
            </HashRouter>
    ,
    document.getElementById('root')
);
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();