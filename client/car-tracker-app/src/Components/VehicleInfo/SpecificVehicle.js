import React, { Component } from 'react';

class SpecificVehicle extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    const vehicleID = this.props.match.params.id;
    return (
      <div className="SpecificVehicle">
        <h1>Selected Vehicle Id: {vehicleID}</h1>
      </div>
    );
  }
}

export default SpecificVehicle
