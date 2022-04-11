import './Start.css';
import './AdminProfile.css';
import React, {Component} from "react";
class ViewMenu extends Component {
    constructor(props) {
        super(props);
        this.state = {name: '',loc: '',zones:[], items:[]};
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

    render() {
        return (

            <div className="Start">
                <header className="Start-header">
                    <h1>{this.state.name}'s Menu</h1>
                    <ul>
                        <li>BREAKFAST</li>
                        <ul className="ul">
                            {this.state.items?.filter(item=>item.category==="BREAKFAST").map(r => {
                                return <li className="li">{r.name}( {r.description} ), Price: {r.price}</li>
                            })}
                        </ul>
                        <li>LUNCH</li>
                        <ul className="ul">
                            {this.state.items?.filter(item=>item.category==="LUNCH").map(r => {
                                return <li className="li">{r.name}( {r.description} ), Price: {r.price}</li>
                            })}
                        </ul>
                        <li>DESSERT</li>
                        <ul className="ul">
                            {this.state.items?.filter(item=>item.category==="DESSERT").map(r => {
                                return <li className="li">{r.name}( {r.description} ), Price: {r.price}</li>
                            })}
                        </ul>
                        <li>BEVERAGES</li>
                            <ul className="ul">
                                {this.state.items?.filter(item=>item.category==="BEVERAGES").map(r => {
                                    return <li className="li">{r.name}( {r.description} ), Price: {r.price}</li>
                                })}
                            </ul>
                    </ul>
                </header>
            </div>
        )
    }

}
export default ViewMenu;
