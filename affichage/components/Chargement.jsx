import React, { Component } from 'react';
import { CircleLoader } from 'react-spinners';

class Chargement extends Component {
  state = {
    loading: true
  };

  componentDidMount() {
    setTimeout(() => this.setState({ loading: false }), 3000);
  }

  render() {
    return (
      <div className="loading">
        {this.state.loading ? (
          <CircleLoader
            size={100}
            color="#123abc"
            loading={this.state.loading}
          />
        ) : (
          <p>Data Loaded</p>
        )}
      </div>
    );
  }
}

export default Chargement;
