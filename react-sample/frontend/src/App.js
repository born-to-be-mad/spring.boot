import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
    isWaiting: true,
    action: "",
	destination: ""
  };
  callServer = async (event) => {
    event.preventDefault();
    let response = await fetch('/apply?action=' + this.state.action + '&destination=' + this.state.destination);
    let body = await response.json();
    this.setState({ action: body.action, destination: body.destination, isWaiting: false });
  }
  updateAction = (event) => {
    event.preventDefault();
	this.setState({ isWaiting: true, action:event.target.value, destination:this.state.destination });
  }
  updateDestination = (event) => {
    event.preventDefault();
	this.setState({ isWaiting: true, action:this.state.action, destination:event.target.value });
  }
  render() {
    const {isWaiting, action, destination} = this.state;
    return (
<div className="App">
<header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
<div className="App-intro">
            <input onChange={(event)=>this.updateAction(event)} placeholder="Action"></input>
			<input onChange={(event)=>this.updateDestination(event)} placeholder="Destination"></input>
            <button onClick={(event)=>this.callServer(event)}>Call rest server!</button>
            <h2 style={{visibility: this.isActionVisible}}>{(isWaiting)?"Waiting for data from server"
			:"Server response:"+this.state.action+", "+this.state.destination}</h2>

        </div>
        </header>
    </div>
    );
  }
}


export default App;
