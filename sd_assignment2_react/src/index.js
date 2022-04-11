import React from 'react';
import ReactDOM from 'react-dom';
import {HashRouter, Route, Routes} from "react-router-dom";
import './index.css';
import reportWebVitals from './reportWebVitals'
import Start from "./Start";
import Register from "./Register";
import Login from "./Login";
import UserProfile from "./UserProfile";
import AdminProfile from "./AdminProfile";
import AddMenuItem from "./AddMenuItem";
import ViewMenu from "./ViewMenu";
import Order from "./Order";
import ViewOrders from "./ViewOrders";
import ViewOrderHistory from "./ViewOrderHistory";

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
                    <Route exact path="/userprofile" element={<UserProfile />}/>
                    <Route exact path="/adminprofile" element={<AdminProfile />}/>
                    <Route exact path="/addmenuitem" element={<AddMenuItem/>}/>
                    <Route exact path="/viewmenu" element={<ViewMenu/>}/>
                    <Route exact path="/order" element={<Order/>}/>
                    <Route exact path="/vieworders" element={<ViewOrders/>}/>
                    <Route exact path="/vieworderhistory" element={<ViewOrderHistory/>}/>
                </Routes>
            </HashRouter>
    ,
    document.getElementById('root')
);
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();