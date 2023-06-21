import React, { Component } from "react";
import moment from 'moment';
import Delete from "../../Generalized/CRUD/Delete";
import FetchHelper from "../../../Helper/FetchHelper";

class TabDevis extends Component {
  state={
    isOpen:false
  }
   isValidDurationString(durationString) {
    // Regular expression to match the "PT21H14M" format
    const durationRegex = /^PT\d+H\d+M$/;
  
    return durationRegex.test(durationString);
  }
  cloturer=(id)=>{
    this.cloturing(id);
  }
  cloturing=async (id)=>{
    let helper=new FetchHelper("devis/"+id+"/1",null,"PUT");
    let data= await helper.sendrequest();
    console.log(data);
  }
  
  format(date) {
    if (
      new Date(date).toString() !== "Invalid Date" &&
      typeof date === "string"
    ) {
      
      let daty = new Date(date);
      const utcHours = daty.getUTCHours();
const utcMinutes = daty.getUTCMinutes();
const utcSeconds = daty.getUTCSeconds();
const utcMilliseconds = daty.getUTCMilliseconds();
      if (utcHours===0 && utcMinutes===0 && utcSeconds===0 && utcMilliseconds===0) {
        return daty.toLocaleDateString();
      }

      console.log(daty);
      return daty.toLocaleString();
    }
   
    if (this.isValidDurationString(date)) {
          // Create a moment object with the duration
  const momentObj = moment().startOf('day').add(moment.duration(date));

  // Format the moment object as HH:mm
  return   momentObj.format('HH:mm');
    }
    return date;
  }
  constructor(props) {
    super(props);
    console.log("cool");
  }
  initurl = () => {
    if (
      new URLSearchParams(window.location.search).get("authorization") !==
        undefined &&
      new URLSearchParams(window.location.search).get("authorization") !== null
    ) {
      return (
        "&&authorization=" +
        new URLSearchParams(window.location.search).get("authorization")
      );
    }
    return "";
  };
  addInfoPerso(person) {
    if (
      this.props.infoperso !== undefined &&
      this.props.infoperso !== null &&
      this.props.infoperso
    ) {
      return (
        <td key={"link" + person[this.props.valuekey]}>
          {" "}
          <a
            className="btn btn-outline-info rounded-pill"
            href={
              this.props.link.value +
              "?" +
              this.props.valuekey +
              "=" +
              person[this.props.valuekey] +
              this.initurl()
            }
          >
            {this.props.link.label}
          </a>
        </td>
      );
    }
  }
  formattingData(valeur, formatting) {
    for (const format of formatting) {
      if (valeur === format.value) {
        return format.label;
      }
    }

    return valeur;
  }
 

  

  returnvalue=(header,data)=>{
    if (
      header.formatting !== undefined &&
      header.formatting !== null
    ) {
      return (
        <td key={data[header.value]}>
          {this.formattingData(
            data[header.value],
            header.formatting
          )}
        </td>
      );
    } else {
      return (
        <>
        <td key={data[header.value]}>
          {this.format(data[header.value])}
        </td>
        </>
      );
    }
  }
  returndata=(data)=>{
    return (
      <tr key={data[this.props.valuekey]}>
        {this.props.headers.map(header => 
        <> {this.returnvalue(header,data)}</>
         
        )}

      { this.props.cudable!==false && data.vita===0  ?
      <>
      
      <td>
          <button
            className="btn btn-warning rounded-pill"
            onClick={() => {
              window.location.href =
                "update?id=" + data[this.props.valuekey];
            }}
          >
            Update
          </button>
        </td>
      
        <td>
          <Delete deleting={this.props.deleting} element={data} id={this.props.valuekey} error={this.props.error} />
        </td>
        <td><button className="btn btn-success rounded-pill" onClick={()=>this.cloturer(data.id)}>Cloturer</button> </td>
       
      </>:<>
     <button className="btn  btn-outline-info  rounded-pill" onClick={()=>{window.location.href="/fromage?id="+data.id}} >Diagramme</button>
      </>
      }
 <td>
      {this.addInfoPerso(data)}
      </td>
    
       
       
      </tr>
    );
  }


  
  render() {
    return (
      <>
        <table className="table table-bordered table-stripped text-center">
          <thead>
            <tr>
              {this.props.headers.map((header) => {
                return <th key={header.value}>{header.label}</th>;
              })}
              { this.props.cudable!==false  ? 
              <>
              <th></th>
              <th></th>
              <th></th>
              </>
              :
              <></>
  }
        <th> {} </th>
            </tr>
          </thead>
          <tbody>
            {this.props.data.map(data => 
            <>
              {this.returndata(data)}
            </>
             
            )}
          </tbody>
        </table>
      </>
    );
  }
}
export default TabDevis;
