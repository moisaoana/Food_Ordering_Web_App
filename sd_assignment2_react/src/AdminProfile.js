import './AdminProfile.css';
import React, {Component} from "react";
import {Link} from "react-router-dom";


class AdminProfile extends Component {

    constructor(props) {
        super(props);
        this.state = {name: '',loc: '',zones:[], selected_zones:[], restName:''};
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChangeName = this.handleChangeName.bind(this);
        this.handleChangeLocation = this.handleChangeLocation.bind(this);
        this.handleAddZone = this.handleAddZone.bind(this);

    }

    async componentDidMount() {

        fetch('http://localhost:8080/adminprofile',)
            .then(async response=>{
                const d=await response.json();
                this.setState({zones: d});
                console.log(this.state.list)

            })
        fetch('http://localhost:8080/adminprofile/'+localStorage.getItem("user"),)
            .then(async response=>{
                const d=await response.json();
                this.setState({restName: d.name});
                localStorage.setItem('restaurant',d.name);
                console.log(d);

            })

    }
    handleChangeName(event) {
        this.setState({name: event.target.value});
    }
    handleChangeLocation(event) {
        this.setState({loc: event.target.value});
    }
    handleSubmit(event) {
        event.preventDefault();
        const data = {
            name: this.state.name,
            location: this.state.loc,
            adminUsername: localStorage.getItem("user") ,
            zones: this.state.selected_zones
        }
            // alert('A name was submitted: ' + data.firstName + ' ' + data.lastName + ' ' + data.username + ' ' + data.password+' '+data.type);
            const requestOptions = {
                method: 'POST',
                headers:{ 'Content-Type': 'application/json'},
                body: JSON.stringify(data)
            };
            fetch('http://localhost:8080/adminprofile',requestOptions)
                .then(response =>  {
                    if (response.ok) {
                        alert("Restaurant registered!");
                    }else {
                        alert("You have already registered a restaurant!");
                    }
                    return response.json();
                });
    }
    handleAddZone(event) {
        let e = document.getElementById("zones");
        let value = e.options[e.selectedIndex].value;
        console.log(value)
        let arr=this.state.selected_zones
        if(!arr.includes(value)) {
            arr.push(value)
            this.setState({selected_zones: arr});
            console.log(this.state.selected_zones)

        }
    }

    render() {
        const d = this.state.list;
        return (
            <div className="AdminProfile">
                <header className="AdminProfile-header">
                    <h1 className="AdminProfile-title">Welcome,{localStorage.getItem("user")}!</h1>
                    <div >
                        <label className="subtitle">Your registered restaurant: </label>
                        <label className="title2">{this.state.restName}</label>
                    </div>

                    <form onSubmit={this.handleSubmit}>
                        <div className="subtitle">
                            <label>Add a restaurant: </label>
                        </div>
                        <label>Name </label>
                        <input type="text" name="name" value={this.state.name} onChange={this.handleChangeName} required />
                        <label>Location </label>
                        <input type="text" name="loc" value={this.state.loc} onChange={this.handleChangeLocation} required />

                        <label>Delivery zones: </label>
                        <select id="zones" onChange={this.handleAddZone}>
                            {this.state.zones?.map(z => {
                                return <option value={z.name} >{z.name}</option>;
                            })}
                        </select>
                        <input className="AdminProfile-button" type="submit" value="Add" />
                    </form>
                    <button className="button">
                        <Link className="link" to="/addmenuitem">Add menu item</Link>
                    </button>
                    <button className="button">
                        <Link className="link" to="/viewmenu">View menu</Link>
                    </button>
                    <button className="button">
                        <Link className="link" to="/vieworders">View orders</Link>
                    </button>
                    <button className="button">
                        <Link className="link" to="/">Back</Link>
                    </button>

                </header>
            </div>
        )
    };
}

export default AdminProfile;
