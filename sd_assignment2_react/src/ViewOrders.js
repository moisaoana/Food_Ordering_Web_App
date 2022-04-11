import './Start.css';
import './AdminProfile.css';
import React, {Component} from "react";
class ViewOrders extends Component {
    constructor(props) {
        super(props);
        this.state = {orders:[], refresh:false, type:'',all:true};
        this.acceptOrder = this.acceptOrder.bind(this);
        this.declineOrder = this.declineOrder.bind(this);
        this.inDeliveryOrder=this.inDeliveryOrder.bind(this);
        this.deliveredOrder=this.deliveredOrder.bind(this);
        this.handleChangeStatus=this.handleChangeStatus.bind(this);
    }

    async componentDidMount() {
        fetch('http://localhost:8080/vieworders/'+localStorage.getItem("restaurant"),)
            .then(async response=>{
                const d=await response.json();
                this.setState({orders: d});
                //localStorage.setItem('restaurant',d.name);
                console.log(d);

            })


    }
    acceptOrder(order) {
        let str=''
        if(order.status==="PENDING"){
            str="ACCEPTED"
        }else{
            str=order.status
        }
        const data = {
            id: order.id,
            restaurant: order.restaurant,
            customer: order.customer,
            status: str,
            items: order.items,
        }
        const requestOptions = {
            method: 'PUT',
            headers:{ 'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        };
        fetch('http://localhost:8080/vieworders',requestOptions)
    }
    declineOrder(order) {
        let str=''
        if(order.status==="PENDING"){
            str="DECLINED"
        }else{
            str=order.status
        }
        const data = {
            id: order.id,
            restaurant: order.restaurant,
            customer: order.customer,
            status: str,
            items: order.items,
        }
        const requestOptions = {
            method: 'PUT',
            headers:{ 'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        };
        fetch('http://localhost:8080/vieworders',requestOptions)
    }
    inDeliveryOrder(order) {
        let str=''
        if(order.status==="ACCEPTED"){
            str="IN_DELIVERY"
        }else{
            str=order.status
        }
        const data = {
            id: order.id,
            restaurant: order.restaurant,
            customer: order.customer,
            status: str,
            items: order.items,
        }
        const requestOptions = {
            method: 'PUT',
            headers:{ 'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        };
        fetch('http://localhost:8080/vieworders',requestOptions)
    }
    deliveredOrder(order) {
        let str=''
        if(order.status==="IN_DELIVERY"){
            str= "DELIVERED"
        }else{
            str=order.status
        }
        const data = {
            id: order.id,
            restaurant: order.restaurant,
            customer: order.customer,
            status: str,
            items: order.items,
        }
        console.log(data.status)
        const requestOptions = {
            method: 'PUT',
            headers:{ 'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        };
        fetch('http://localhost:8080/vieworders',requestOptions)
    }
    handleChangeStatus(event) {
        let e = document.getElementById("statusSelect");
        let value = e.options[e.selectedIndex].value;
        if (value !== "ALL") {
            this.setState({type: value});
            this.setState({all: false});
        }else{
            this.setState({all: true});
        }


        console.log(value)
    }

    render() {
        return (

            <div className="Start">
                <header className="Start-header">
                    <h1>View Orders</h1>
                    <h2>Filter by status:</h2>
                    <select id="statusSelect" onChange={this.handleChangeStatus}>
                        <option value="PENDING" >PENDING</option>
                        <option value="ACCEPTED" >ACCEPTED</option>
                        <option value="DECLINED" >DECLINED</option>
                        <option value="IN_DELIVERY" >IN_DELIVERY</option>
                        <option value="DELIVERED" >DELIVERED</option>
                        <option value="ALL" >ALL</option>
                    </select>
                    <ul className="ul">
                        {this.state.orders?.filter(o=>this.state.all || o.status===this.state.type).map(o => {
                            return <li className="li">Customer: {o.customer}, Restaurant: {o.restaurant}, Status: {o.status}
                                <button onClick={() => this.acceptOrder(o)} className="order-button">Accept</button>
                                <button onClick={() => this.declineOrder(o)} className="order-button">Decline</button>
                                <button onClick={() => this.inDeliveryOrder(o)} className="order-button">Deliver</button>
                                <button onClick={() => this.deliveredOrder(o)} className="order-button">Delivered</button>
                                <ul className="ul">
                                    {o.items?.map(i => {
                                        return <li className="sub-li">{i.name}</li>
                                    })}
                                </ul>

                            </li>
                        })}
                    </ul>
                </header>
            </div>
        )
    }

}
export default ViewOrders;
