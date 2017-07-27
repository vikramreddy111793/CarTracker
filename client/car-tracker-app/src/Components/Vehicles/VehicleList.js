import React, { Component } from 'react';
import Vehicle from './Vehicle';
import './VehicleList.css';

class VehicleList extends Component {

  render() {
    let vehicleItems;
    if(this.props.vehicles){
      vehicleItems = this.props.vehicles.map(vehicle => {
        return (
            <Vehicle key={vehicle.id} vehicle= {vehicle} />
        );
      });
    }
    return (
      <div className="VehicleList">
        {vehicleItems}
      </div>
    );
  }
}

export default VehicleList;
