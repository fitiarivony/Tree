import React, { Component } from 'react';
import {ClipLoader} from 'react-spinners'

class ButtonSpinner extends Component {
    render(){
        return(
<button type="submit"  className="btn btn-success"  >
{this.props.loading ? (
<>{this.props.titre}  <ClipLoader size={15} color="white"  /></> 
) : (
this.props.titre
)}
  
</button>
);
    }
}
export default ButtonSpinner;

