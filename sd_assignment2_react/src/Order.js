import './Start.css';
import './AdminProfile.css';
import './Order.css'
import React, {Component} from "react";
import {Link} from "react-router-dom";
class Order extends Component {
    constructor(props) {
        super(props);
        this.state = {name: '',loc: '',zones:[], items:[], cart:[], total:0};
        this.addItem = this.addItem.bind(this);
        this.order=this.order.bind(this);
    }

    async componentDidMount() {
        const d=localStorage.getItem("restaurant")
        console.log(d);
        this.setState({name: d});
        fetch('http://localhost:8080/viewmenu/'+localStorage.getItem("restaurant"),)
            .then(async response=>{
                const d=await response.json();
                this.setState({items: d});
                //localStorage.setItem('restaurant',d.name);
                console.log(d);

            })


    }
    addItem(item) {
        console.log(item.name);
        //localStorage.setItem('restaurant',name);
        let arr=this.state.cart
        arr.push(item)
        this.setState({cart: arr});
        let sum=parseFloat(this.state.total)
        sum=sum+parseFloat(item.price)
        this.setState({total: sum});

        console.log(this.state.cart)
    }
    order(){
        const data = {
            restaurant: localStorage.getItem("restaurant"),
            customer: localStorage.getItem("user"),
            items: this.state.cart
        }
        const requestOptions = {
            method: 'POST',
            headers:{ 'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        };
        fetch('http://localhost:8080/order',requestOptions)
        alert("Order successfully placed!")

    }

    render() {
        return (
            <body>
            <div className="split left">
                <header className="Start-header">
                    <h1>Order from {this.state.name}</h1>
                    <ul>
                        <li>BREAKFAST</li>
                        <ul className="ul">
                            {this.state.items?.filter(item=>item.category==="BREAKFAST").map(r => {
                                return <li className="li">{r.name}( {r.description} ), Price: {r.price}
                                    <button onClick={() => this.addItem(r)} className="order-button">Add</button>
                                </li>
                            })}
                        </ul>
                        <li>LUNCH</li>
                        <ul className="ul">
                            {this.state.items?.filter(item=>item.category==="LUNCH").map(r => {
                                return <li className="li">{r.name}( {r.description} ), Price: {r.price}
                                    <button onClick={() => this.addItem(r)} className="order-button">Add</button>
                                </li>
                            })}
                        </ul>
                        <li>DESSERT</li>
                        <ul className="ul">
                            {this.state.items?.filter(item=>item.category==="DESSERT").map(r => {
                                return <li className="li">{r.name}( {r.description} ), Price: {r.price}
                                <button onClick={() => this.addItem(r)} className="order-button">Add</button>
                                </li>
                            })}
                        </ul>
                        <li>BEVERAGES</li>
                        <ul className="ul">
                            {this.state.items?.filter(item=>item.category==="BEVERAGES").map(r => {
                                return <li className="li">{r.name}( {r.description} ), Price: {r.price}
                                <button onClick={() => this.addItem(r)} className="order-button">Add</button>
                                </li>
                            })}
                        </ul>
                    </ul>
                </header>
            </div>
            <div className="split right">
                <header className="Start-header">
                    <h1>My cart</h1>
                    <h2 className="subtitle">Total: {this.state.total}</h2>
                    <ul>
                        {this.state.cart?.map(r => {
                            return <li className="li">{r.name}, Price: {r.price}
                            </li>
                        })}
                    </ul>
                    <button onClick={this.order} className="button">Order</button>
                </header>
            </div>
            </body>

        )
    }

}
export default Order;
