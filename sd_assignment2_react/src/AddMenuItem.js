import './Start.css';
import './Register.css';
import React, {Component} from "react";
import {Link} from "react-router-dom";
class AddMenuItem extends Component {
    constructor(props) {
        super(props);
        this.state = {item:'',description:'', price:'',category:''};
        this.handleAddItem = this.handleAddItem.bind(this);
        this.handleChangeItemName=this.handleChangeItemName.bind(this);
        this.handleChangeDescription=this.handleChangeDescription.bind(this);
        this.handleChangePrice=this.handleChangePrice.bind(this);
        this.handleChangeCategory=this.handleChangeCategory.bind(this);
    }

    async componentDidMount() {
    }
    handleChangeItemName(event) {
        this.setState({item: event.target.value});
    }
    handleChangeDescription(event) {
        this.setState({description: event.target.value});
    }
    handleChangePrice(event) {
        this.setState({price: event.target.value});
    }
    handleChangeCategory(event) {
        let e = document.getElementById("categorySelect");
        let value = e.options[e.selectedIndex].value;
        this.setState({category: value});
        console.log(value)
    }
    handleAddItem(event) {
        event.preventDefault();
        const data = {
            name: this.state.item,
            description: this.state.description,
            price: this.state.price,
            admin_username: localStorage.getItem("user") ,
            category: this.state.category
        }
        console.log(data.category)
        const requestOptions = {
            method: 'POST',
            headers:{ 'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        };
        fetch('http://localhost:8080/addmenuitem',requestOptions)
            .then(response =>  {
                if (response.ok) {
                    alert("Food item added!");
                }else {
                    alert("You have already added this food item!");
                }
                return response.json();
            });


    }

    render() {
        return (
            <div className="Start">
                <header className="Start-header">
                    <h1 className="Start-title">Add a new menu item!</h1>
                    <form onSubmit={this.handleAddItem}>
                        <div className="input">
                        <label>Name </label>
                        <input type="text" name="name" value={this.state.item} onChange={this.handleChangeItemName} required />
                        </div>
                        <div className="input">
                        <label>Description </label>
                        <input type="text" name="description" value={this.state.description} onChange={this.handleChangeDescription} required />
                        </div>
                        <div className="input">
                        <label>Price: </label>
                        <input type="text" name="price" value={this.state.price} onChange={this.handleChangePrice} required />
                        </div>
                        <div className="input">
                        <label>Category: </label>
                        <select id="categorySelect" onChange={this.handleChangeCategory}>
                            <option value="BREAKFAST" >BREAKFAST</option>
                            <option value="LUNCH" >LUNCH</option>
                            <option value="DESSERT" >DESSERT</option>
                            <option value="BEVERAGES" >BEVERAGES</option>
                        </select>
                        </div>
                        <input className="Reg-button" type="submit" value="Add" />
                        <button className="Reg-button">
                            <Link className="Reg-link" to="/adminprofile">Back</Link>
                        </button>
                    </form>
                </header>
            </div>
        )
    }

}
export default AddMenuItem;
