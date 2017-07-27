import React, { Component } from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';

import App from './Components/App/App';
import SpecificVehicle from './Components/VehicleInfo/SpecificVehicle';

class Routes extends Component {

  render() {
    return (
      <div className="Routes">
        <BrowserRouter>
          <Switch>
            <Route exact path='/' component={App} />
            <Route path='/vehicle/:id' component={SpecificVehicle} />
          </Switch>
        </BrowserRouter>
      </div>
    );
  }
}

export default Routes;
