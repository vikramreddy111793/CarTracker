import React, { Component } from 'react';
import axios from 'axios';
import VehicleList from '../Vehicles/VehicleList';
import './App.css';

class App extends Component {
  constructor() {
    super();
    this.state = {vehicles:[]};
  }

  getInitialState() {
    return {
      vehicles: []
    }
  }

  componentDidMount() {
    const vehicleListUrl = "http://ec2-52-91-60-89.compute-1.amazonaws.com/car-tracker/vehicles";
    var self = this;
    axios.get(vehicleListUrl)
      .then(function (response){
          if(response.data){
            self.setState({
              vehicles: response.data
            });
          }
      })
      .catch(function (error){
        console.log(error);
      });
  }

  render() {
    return (
      <div className="App">
        <div className="AppMenu">

        </div>
        <div className="AppBody">
          <VehicleList vehicles={this.state.vehicles} />
        </div>
      </div>
    );
  }
}

export default App;
