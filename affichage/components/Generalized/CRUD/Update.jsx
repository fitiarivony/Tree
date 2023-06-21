import React, { Component } from 'react'
import FetchHelper from '../../../Helper/FetchHelper'
import URLHelper from '../../../Helper/URLHelper'
import PutIn from './element/PutIn'
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
class Update extends Component {
    state = { 
        fields:[],
        obj:{},
     } 
    constructor(props) {
        super(props)
        this.initialize()
    }
    update=(event)=>{
        event.preventDefault();
        this.updating();
    }
    addheader=(header)=>{   
        if (localStorage.getItem('admin')!=null) {
            header.authorization=localStorage.getItem('admin');
        } 
      
    } 
    updating =async()=>{
        let id=new URLSearchParams(window.location.search).get("id");
        let  header={ "Content-Type": "application/json"};
        this.addheader(header);
        let helper=new FetchHelper((this.props.updating+"/"+id),this.state.obj,"PUT",header);
        let data=helper.sendrequest();
        this.handlerror(data);
        if (!('error' in data)) {
            toast.success('Success!', {
              position: "top-center",
              autoClose: 5000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
              theme: "light",
              });
          }
        console.log(data);
    }
    initialize = async() =>{
        let header={};
        this.addheader(header);
        let id=new URLSearchParams(window.location.search).get("id");
        let data = await FetchHelper.getData(URLHelper.urlgen(this.props.uri+"/"+id),header)
        this.handlerror(data);
        this.setState({loading:false,fields:data.data.fields,obj:data.data.obj})
    }
    changing=(field,value)=>{
        let obj=this.state.obj;
        obj[field.name]=value;
        this.setState({obj:obj});
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
        onClose:()=>{ window.location.href='/' }
      });
    
        }
       }
 
    render() { 
        return (
            <div>
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
                <div className="container">
                    <div id="card" className="card  mb-3" style={{ maxWidth: "300rem" }}>
                        <div id="card-header" className="card-header text-center"><h2>{this.props.title}</h2> </div>
                        <div id="card-body" className="card-body">
                            <form onSubmit={this.update}>
                {this.state.fields.map(field =>
                 <PutIn field={field} key={field.name} value={this.state.obj[field.name]} changing={this.changing} />
                    )}
                   <button type='submit'className='btn btn-warning' >Update</button>
                   </form>
                    </div>
                    </div>
                    </div>
            </div>
        );
    }
}
 
export default Update;