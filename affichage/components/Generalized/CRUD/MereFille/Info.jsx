import React, { Component } from 'react'

import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import FetchHelper from '../../../../Helper/FetchHelper';
import URLHelper from '../../../../Helper/URLHelper';
class Info extends Component {
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
    displayfille=(mafille)=>{
        if (mafille.length===0) {
            return <></>
        }
        let val=<>

        <table className='table table-bordered table-stripped text-center table '>
            <thead>
        {Object.keys(mafille[0]).map(key=>
                     <th>{key}</th> 
        )}
        </thead>
        <tbody>
        {mafille.map(fille=>
            <tr>
                {Object.keys(fille).map(key=>       
                    <td >{fille[key]}</td>
                    )}
            </tr>
            )}
            </tbody>
         </table>
        </>;
        return val;
    }
    displayall=(key)=>{
        if (Array.isArray(this.state.obj[key])) { 
            return this.displayfille(this.state.obj[key])  
        }
        return this.displaydata(key,this.state.obj);
    }
    
    displaydata=(key,obj)=>{
       
        let speciality=this.isspecial(key);
       if (speciality.length===0) {
            return (
                <div>
                    <b>{key}:</b>{obj[key]}
                </div>
            );
       }else if(speciality.length>0 && speciality[0].representation==="image"){
            return <img alt="" src={obj[key]} className='img-thumbnail card-image-top relative' />
    }
       else if(speciality.length>0 && speciality[0].representation==="ckeditor"){
        return <div dangerouslySetInnerHTML={{__html: obj[key]}} />
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
           <div className="card" style={{width: "40rem"}}>
            
            <div className="card-body">
                <h5 className="card-title">Fiche</h5>
                <div className="container">
                    <button className='btn btn-outline-info rounded-pill' onClick={this.pdf}>PDF</button>
                {Object.keys(this.state.obj).map(key=>
                    
                    this.displayall(key)
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
 
export default Info;