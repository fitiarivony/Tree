import React, { Component } from 'react';

class Multisearch extends Component {
    state={
        visible: false,
    }
    handleButtonClick=()=>{
       let etat=!this.state.visible;
        this.setState({visible:etat });
    }
    outputinput(field){
        return( 
            <div>
                <label htmlFor={field.name}><b>{field.name}:</b></label>
                 <input className='form-control' type={field.type} value={this.props.search.map[field.name]} name={field.name} onChange={this.change} />
            </div>
            )
    }
    changecheckbox=(event)=>{
        let multi=this.props.search.map;
        if (event.target.checked) {
            multi[event.target.name].push(event.target.value);
        }else{
           multi[event.target.name]=multi[event.target.name].filter(val=> val!==event.target.value)
        }
       this.props.update(multi);
    }
    makesearch=(event)=>{
        event.preventDefault();
        this.props.onsearch();
        this.handleButtonClick();
    }
    change=(event)=>{
        let multi=this.props.search.map;
        if (multi[event.target.name]===null || multi[event.target.name]===undefined) {
            multi[event.target.name]=0;
        }
        multi[event.target.name]=event.target.value
        this.props.update(multi);
    }
    getoutput(field){
        // console.log(field);
        let ret=<></>
        if(field.input==="input"){
          ret=this.outputinput(field);
        }else if (field.input==="select"){ 
           ret=(
                <div>
                    <label htmlFor={field.name}> <b>{field.name}:</b></label>
                    
                       { field.value.map(option=>
                   
                  <> <input key={option.name} onChange={this.changecheckbox} type='checkbox' name={field.name} value={option.value}/>{option.name} </>
                   
                        )}
                </div>
           );
        }
        return ret;
    }

    render() { 
        return (
            <div className='container'>
                <button className="btn btn-primary purple form-control" onClick={this.handleButtonClick}>
        Recherche
      </button>
      {this.state.visible && (
        <div className='container-fluid'>
 <form onSubmit={this.makesearch}>
                 {this.props.search.fields.map(field =>
        <div className='mb-3'>
                {this.getoutput(field)}
                </div>
            )}
            <button type='submit'className='btn btn-outline-info rounded pill'>Search</button>
            </form>
        </div>
      )}
               
            </div>
        );
    }
}
 
export default Multisearch;