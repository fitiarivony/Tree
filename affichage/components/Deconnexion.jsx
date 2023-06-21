import React, { Component } from 'react'
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import FetchHelper from '../Helper/FetchHelper';
import URLHelper from '../Helper/URLHelper';
class Deconnexion extends Component {
    
    constructor(props) {
        super(props);
        this.initialize = this.initialize.bind(this);
        this.initialize();
        this.removeconnexion();
        window.location.href='/';
    }
     removeconnexion=()=>{
        for (const deconnect of this.props.deconnexion) {
            localStorage.removeItem(deconnect);
        } 
    }
   async initialize(){
        if(!this.props.token){
            let header={};
            this.addheader(header);
            let data = await FetchHelper.getData(URLHelper.urlgen(this.props.uri),header);
            this.handlerror(data);
        } 
    }
    addheader=(header)=>{   
        if (localStorage.getItem('admin')!=null) {
            header.authorization=localStorage.getItem('admin');
        } 
        console.log(header);
    }
    handlerror=(data)=>{
        if ('error' in data) {
            
            let e = data.error.e;
            if (e === undefined || e === null) e = data.error.ex;
            
            toast.error(e.message+"!", {
              position: "top-center",
              autoClose: 5000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
              theme: "light",
              onClose:()=>{ window.location.href='/' }
            });
            
               
            
        }
       }
    render() { 
        return (
        
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
            
        );
    }
}
 
export default Deconnexion;