import React, { Component } from 'react'
import FetchHelper from '../../../Helper/FetchHelper';
import URLHelper from '../../../Helper/URLHelper';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
class Fiche extends Component {
    state = { 
        special:[],
        obj:{}
     } 
     pdf=()=>{
        this.topdf();
      }
      topdf=async()=>{
          let fetchpdf=new FetchHelper(this.props.uri+"/topdf",this.state.obj,"POST")
          await fetchpdf.topdf(this.props.title);
      }
  
    
    constructor(props) {
        super(props);
        this.initialize();
    }
    initialize=async()=> {
        let id=new URLSearchParams(window.location.search).get("id");
        let data = await FetchHelper.getData(URLHelper.urlgen(this.props.uri+"/"+id))
        this.handlerror(data);
       this.setState(data.data);
    }
    isspecial=(key)=>{
      return this.state.special.filter(specfiche=> specfiche.field===key);
    }
    displaydata=(key)=>{
        let speciality=this.isspecial(key);
       if (speciality.length===0) {
            return (
                <div>
                    <b>{key}:</b>{this.state.obj[key]}
                </div>
            );
       }else if(speciality.length===1 && speciality[0].representation==="image"){
            return <img alt="" src={this.state.obj[key]} className='img-thumbnail card-image-top relative' />
       }
       else if(speciality.length===1 && speciality[0].representation==="ckeditor"){
        return <div dangerouslySetInnerHTML={{__html: this.state.obj[key]}} />
    } 
     
       
    }
    handlerror=(data)=>{
        if ('error' in data) {
            let e = data.error.e;
            if (e === undefined || e === null) e = data.error.ex;
            toast.error(e.message, {
              position: "top-center",
              autoClose: 5000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
              theme: "light",
              onClose:()=>{ window.location.href='/' },
            });
          
        }
    }

    render() { 
        return (
            <div className='row'>
                <ToastContainer
          position="top-center"
          autoClose={5000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          theme="light"
        />
                <div className='col'></div>
                <div className='col'>
           <div className="card" style={{width: "18rem"}}>
            
            <div className="card-body">
                <h5 className="card-title">Fiche</h5>
                <div className="container">
                    <button className='btn btn-outline-info rounded-pill' onClick={this.pdf}>PDF</button>
                {Object.keys(this.state.obj).map(key=>
                    this.displaydata(key)
                    )}
                </div>
               
            </div>
           </div>
           </div>
           <div className='col'></div>
           </div>
        );
    }
}
 
export default Fiche;