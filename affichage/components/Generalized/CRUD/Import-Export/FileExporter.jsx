import React, { Component } from 'react'
import FetchHelper from '../../../../Helper/FetchHelper';
class FileExporter extends Component {
    pdf=()=>{
        this.topdf();
      }
      topdf=async()=>{
          let fetchpdf=new FetchHelper(this.props.uri+"/pdf",this.props.liste,"POST")
          await fetchpdf.topdf(this.props.title);
      }
  
      csv=()=>{
          this.tocsv();
      }
        tocsv=async()=>{
            let fetchcsv=new FetchHelper(this.props.uri+"/csv",this.props.liste,"POST")
            await fetchcsv.tocsv();
      }
      xls=()=>{
          this.toxls();
      }
        toxls=async()=>{
            let fetchcsv=new FetchHelper(this.props.uri+"/excel",this.props.liste,"POST")
            await fetchcsv.toxls();
      }
    render() { 
        return (
            <>
              <button className="btn btn-info btn-md dropdown-toggle mx-5" type="button" data-bs-toggle="dropdown" aria-expanded="false">
    Export
  </button>
  <ul className="dropdown-menu">
  <li><button className="dropdown-item"  onClick={this.pdf} >PDF</button></li>
    <li><button className="dropdown-item" onClick={this.csv} >CSV</button></li>
    <li><button className="dropdown-item" onClick={this.xls} >XLS</button></li>

  </ul>
            </>
        );
    }
}
 
export default FileExporter;