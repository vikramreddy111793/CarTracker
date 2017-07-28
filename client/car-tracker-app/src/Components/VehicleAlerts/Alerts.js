import React, { Component } from 'react';
import { Alert } from 'antd';
import axios from 'axios';

import './Alerts.css';

class Alerts extends Component {
  constructor(props) {
    super(props);
    this.state = {vehicleAlerts: []};
  }

  getInitialState() {
    return {
      vehicleAlerts: []
    }
  }

  componentDidMount() {
    var self = this;
    const vehicleID = this.props.vehicleID;
    const vehicleAlertsUrl = "http://ec2-52-91-60-89.compute-1.amazonaws.com/car-tracker/alerts/"+vehicleID;
    axios.get(vehicleAlertsUrl)
      .then(function(response) {
        console.log(response);
        self.setState({
          vehicleAlerts: response.data
        });
      })
      .catch(function(error) {
        console.log(error);
      });
    // this.setState({
    //   vehicleAlerts : [
    //     {
    //       id:	"2a6200d8-ac8b-4e7f-8841-e033c7206beb",
    //       vin:	"1HGCR2F3XFA027534",
    //       priority:	"LOW",
    //       description:	"Check Tire Pressure",
    //       timeStamp:	1501265745000
    //     },
    //     {
    //       id:	"09084b0c-14ca-4fb3-bfad-4a8a75dc0908",
    //       vin:	"1HGCR2F3XFA027534",
    //       priority:	"HIGH",
    //       description:	"Low Fuel",
    //       timeStamp:	1501265754000
    //     }
    //   ]
    // });
  }

  render() {
    let alertItems;
    var alerttype;
    if(this.state.vehicleAlerts){
    alertItems = this.state.vehicleAlerts.map(vehicleAlert => {
        if(vehicleAlert.priority === "LOW"){
          alerttype = "info";
        }
        else if (vehicleAlert.priority === "MEDIUM") {
          alerttype = "warning";
        }
        else{
          alerttype = "error";
        }
        var description = vehicleAlert.description + " at " + vehicleAlert.timeStamp;
        return (
            <Alert className="Alerts"
              message= {vehicleAlert.priority}
              description= {description}
              type= {alerttype}
              showIcon
            />
        );
      });
    }
    return(
      <div className="Alerts">
        {alertItems}
      </div>
    );
  }
}

export default Alerts;
