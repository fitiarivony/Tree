import React, { Component } from 'react'
class Pagination extends Component {

   navigate(index){
        this.props.naviguer(index);
   }
   generePrevious(){
    if (this.props.genere) {
        let data="";
        if (this.props.current-1<0) {
            data="disabled";
        }
        return (
            <li className="page-item" key={this.props.current-1}><button className={"page-link "+data} onClick={()=>{this.navigate(this.props.current-1)}} >Previous</button></li>
        );
    }
   }
   genereNext(){
    if (this.props.genere) {
        let data="";
        if ( this.props.current+1>=this.props.nbpage) {
            data="disabled";
        }
                return (
            <li className="page-item" key={this.props.current+1}><button className={"page-link "+data} onClick={()=>{this.navigate(this.props.current+1)}} >Next</button></li>
        );
    }
   }

    render() { 
        return (
            <ul className="pagination justify-content-center">
                 {this.generePrevious()}
      { Array.from({ length: this.props.nbpage }).map((value,index) => 
      <li className="page-item" key={index}><button className="page-link" onClick={()=>{this.navigate(index)}} >{index+1}</button></li>
      )}
  {this.genereNext()} 
  </ul>
        );
    }
}
 
export default Pagination;