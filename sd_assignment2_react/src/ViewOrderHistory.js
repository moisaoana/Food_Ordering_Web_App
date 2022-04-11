import './Start.css';
import './AdminProfile.css';
import React, {Component} from "react";
class ViewOrderHistory extends Component {
    constructor(props) {
        super(props);
        this.state = {orders:[]};

    }

    async componentDidMount() {
        fetch('http://localhost:8080/vieworderhistory/'+localStorage.getItem("user"),)
            .then(async response=>{
                const d=await response.json();
                this.setState({orders: d});
                //localStorage.setItem('restaurant',d.name);
                console.log(d);

            })
    }
    render() {
        return (

            <div className="Start">
                <header className="Start-header">
                    <h1>View Order History</h1>
                    <ul className="ul">
                        {this.state.orders?.map(o => {
                            return <li className="li">Customer: {o.customer}, Restaurant: {o.restaurant}, Status: {o.status}
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
export default ViewOrderHistory;
