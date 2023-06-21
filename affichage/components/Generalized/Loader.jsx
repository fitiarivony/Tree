import React, { Component } from 'react';
import {FadeLoader} from 'react-spinners'

class Loader extends Component {
    render(){
        return (
            <div className='container bg-light' >
          <div className="row">
            <div className="col col-lg-6"></div>
          <div className='col col-lg-4'>
          <FadeLoader size={300} color={"black"} loading={this.props.loading}/>
          </div>
          <div className="col-lg-2 col"></div>
          </div>
          </div>
        );
    }
}
export default Loader;