import React, { Component } from 'react';
import { Card } from 'antd';
import { Link } from 'react-router-dom';

import 'antd/dist/antd.css';
import './Vehicle.css';

class Vehicle extends Component {
  constructor(props) {
    super(props);
    this.selectVehicleClick = this.selectVehicleClick.bind(this);
  }

  selectVehicleClick() {
    console.log("Clicked "+this.props.vehicle.id);
  }

  render() {

    var vehicle = this.props.vehicle;
    const cardTitle = vehicle.make+" "+vehicle.model;

    return(
      <div className="Vehicle">
        <div className="card-group">
          <a><Link to={`/vehicle/${vehicle.id}`}><Card title={cardTitle} bodyStyle={{ padding:0 }}>
            <div className="custom-card">
              <h3>VIN:  <p>{vehicle.vin}</p></h3>
              <h3>MAKE: <p>{vehicle.make}</p></h3>
              <h3>MODEL: <p>{vehicle.model}</p></h3>
              <h3>YEAR: <p>{vehicle.year}</p></h3>
            </div>
          </Card></Link></a>
        </div>
      </div>
    );

  }
}

export default Vehicle;
