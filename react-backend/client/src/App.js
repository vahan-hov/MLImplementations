import React, { Component } from 'react';
import './App.css';

class App extends Component {
  state = { users: [] }
  componentDidMount() {
    fetch('/users')
      .then(res => res.json())
      .then(users => this.setState({ users }));
  }
  render() {
    console.log(this.state.users[1]);
    { this.state.users.map(user => { user.img }) }
    return (
      <div className="App">
        <h1>Images</h1>
        {this.state.users.map(user =>
          <img src={process.env.PUBLIC_URL + user.img} />
        )}
      </div>
    );
  }
}

export default App;
