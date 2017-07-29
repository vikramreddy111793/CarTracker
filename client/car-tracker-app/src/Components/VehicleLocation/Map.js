import React, { Component } from 'react';
import { withGoogleMap, GoogleMap, Marker } from 'react-google-maps';

class Map extends Component {

  render() {
    const markers = this.props.markers || []
    return(
        <GoogleMap
          defaultZoom={14}
          defaultCenter={{ lat:40.592802 , lng:-74.66971799999999 }}>
          {markers.map((marker,index) => (
              <Marker {...marker} />
            )
          )}
        </GoogleMap>
    );
  }
}

export default withGoogleMap(Map);
