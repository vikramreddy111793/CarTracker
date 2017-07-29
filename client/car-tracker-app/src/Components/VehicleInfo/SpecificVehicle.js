import React, { Component } from 'react';
import { Tabs } from 'antd';
import axios from 'axios';
import Readings from '../VehicleReadings/Readings';
import Alerts from '../VehicleAlerts/Alerts';
import Location from '../VehicleLocation/Location';
import './SpecificVehicle.css';


class SpecificVehicle extends Component {
  constructor(props) {
    super(props);
    this.state = {vehicle: {}};
  }

  getInitialState() {
    return {
      vehicle: {}
    }
  }

  componentDidMount() {
    var self =this;
    const vehicleID = this.props.match.params.id;
    const vehicleInfoUrl = "http://ec2-52-91-60-89.compute-1.amazonaws.com/car-tracker/vehicles/"+vehicleID;
    axios.get(vehicleInfoUrl)
      .then(function(response) {
        console.log(response);
        self.setState({
          vehicle: response.data
        });
      })
      .catch(function(error) {
        console.log(error);
      });

  }

  render() {
    const TabPane = Tabs.TabPane;
    return (
      <div className="SpecificVehicle">
          <div className="VehicleDetails">
              <h1>{this.state.vehicle.vin}</h1>
          </div>
          <Tabs defaultActiveKey="1">
            <TabPane tab="Readings" key="1">
              <Readings />
            </TabPane>
            <TabPane tab="Location" key="2">
              <Location vehicleID={this.state.vehicle.id} />
            </TabPane>
            <TabPane tab="Alerts" key="3">
              <Alerts vehicleID={this.state.vehicle.id} />
            </TabPane>
          </Tabs>
      </div>
    );
  }
}

export default SpecificVehicle
