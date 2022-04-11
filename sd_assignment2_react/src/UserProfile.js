import './UserProfile.css';
import React, {Component} from "react";
import {Link} from "react-router-dom";

class UserProfile extends Component {

    constructor(props) {
        super(props);
        this.state = {list: [],search:''};
        this.setRestaurant = this.setRestaurant.bind(this);
        this.handleChangeSearch=this.handleChangeSearch.bind(this);

    }
    async componentDidMount() {
        fetch('http://localhost:8080/userprofile',)
            .then(async response=>{
                const d=await response.json();
                this.setState({list: d});
                console.log(this.state.list.map(l=>l.name));

            })

    }
    setRestaurant(name) {
        console.log(name);
        localStorage.setItem('restaurant',name);
    }
    handleChangeSearch(event) {
        this.setState({search: event.target.value});
        console.log(this.state.search)
    }


    render() {
        return (
            <div>
                <div className="split left">
                <header className="UserProfile-header">
                    <h1 className="UserProfile-title">Welcome,{localStorage.getItem("user")}!</h1>
                    <label className="UserProfile-subtitle">Available restaurants: </label>
                    <ul>
                        {this.state.list?.filter(r=>r.name.startsWith(this.state.search)).map(r => {
                            return <li >{r.name}: <Link  onClick={() => this.setRestaurant(r.name)} className="Start-link" to="/viewmenu">View menu</Link>, <Link  onClick={() => this.setRestaurant(r.name)} className="Start-link" to="/order">Order</Link></li>
                        })}
                    </ul>
                </header>
                </div>
                <div className="split right">
                    <header className="UserProfile-header">
                        <label className="subtitle">Search restaurants:</label>
                        <input type="text" value={this.state.search} onChange={this.handleChangeSearch} />
                        <label className="subtitle">View order history:</label>
                        <Link className="Reg-link" to="/vieworderhistory">View Orders</Link>
                        <button className="button">
                            <Link className="link" to="/">Back</Link>
                        </button>
                    </header>
                </div>
            </div>
        )
    };
}

export default UserProfile;