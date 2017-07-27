import React, { Component } from 'react';
import VehicleList from './Components/Vehicles/VehicleList';
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
    this.setState({
      vehicles: [
        {
            id: 1,
            vin: "1HGCR2F3XFA027534",
            make: "HONDA",
            model: "ACCORD",
            year: 2015,
            redlineRpm: 5500,
            maxFuelVolume: 15,
            lastServiceDate: "2017-05-25T17:31:25.268Z"
         },
         {
            id: 2,
            vin: "WP1AB29P63LA60179",
            make: "PORSCHE",
            model: "CAYENNE",
            year: 2015,
            redlineRpm: 8000,
            maxFuelVolume: 18,
            lastServiceDate: "2017-03-25T17:31:25.268Z"
         }
      ]
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
