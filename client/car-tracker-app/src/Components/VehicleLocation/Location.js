import React, { Component } from 'react';
import axios from 'axios';
import Map from './Map';
import './Location.css';

class Location extends Component {
  constructor(props) {
    super(props);
    this.state = {vehicleLocations: []};
  }

  getInitialState() {
    return {
      vehicleLocations: []
    }
  }

  componentDidMount() {
    var self = this;
    const vehicleID = this.props.vehicleID;
    const vehicleAlertsUrl = "http://ec2-52-91-60-89.compute-1.amazonaws.com/car-tracker/readings/location/"+vehicleID;
    axios.get(vehicleAlertsUrl)
      .then(function(response) {
        console.log(response);
        self.setState({
          vehiclelocations: response.data
        });
      })
      .catch(function(error) {
        console.log(error);
      });
    // this.setState({
    //   vehicleLocations: [
    //     {
    //       lat: 40.592802,
    //       lng: -74.66971799999999
    //     },
    //     {
    //       lat: 40.623139,
    //       lng: -74.599067
    //     }
    //   ]
    // });
  }

  render() {
    let markers = [];
    markers = this.state.vehicleLocations.map(location => {
      return (
        {
          position: {
           lat: location.lat,
           lng: location.lng,
          }
        }
      );
    });
    return(
      <div className="Location">
        <Map
          containerElement = {<div style={{height: `100%`}} />}
          mapElement = {<div style={{height: `100%`}} />}
          markers = {markers}
        />
      </div>
    );
  }
}

export default Location;
