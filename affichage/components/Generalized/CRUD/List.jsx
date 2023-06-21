import React, { Component } from 'react';
import Tab from '../Tab';
import Loader from '../Loader';
import FetchHelper from '../../../Helper/FetchHelper';
import URLHelper from '../../../Helper/URLHelper';
import Pagination from './Pagination';
import Multisearch from './Multisearch';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import FileImporter from './Import-Export/FileImporter';
import FileExporter from './Import-Export/FileExporter';
class List extends Component {
    state={
        searching:false,
        loading : true,
        listTree:{
            title:"Le titre",
            liste:[
                {
                    info:'essai',
                },
            ],
        
            entete:[{
                label:"Info ",
                value:"info"}
            ], 
            valuekey:'info',
            infoperso:true,
          
            nbpage:0,
            multisearch:{
                fields:[],
                map:[],
            }
        },
        paginate:{
            offset:0,
            limit:0,
        },
        genere:false,       
       
    }
    constructor(props) {
        super(props);
        this.initialize();
    }
    
    initialize = async() =>{
        let header={};
        this.addheader(header);
        let data = await FetchHelper.getData(URLHelper.urlgen(this.props.uri),header);
        this.handlerror(data);
        if (!('error' in data)) {
            this.setState({loading:false,listTree:data.data});
        }
       
    }
    addheader=(header)=>{   
        if (localStorage.getItem('admin')!=null) {
            header.authorization=localStorage.getItem('admin');
        } 
        console.log(header);
    }
    actpaginate=(event) =>{
        event.preventDefault();
        this.paginating();
    }
    paginating=async()=>{
        console.log(this.state.paginate);
        if (!this.state.searching) {
            let header={};
            this.addheader(header);
            let data = await FetchHelper.getData(URLHelper.urlgen(this.props.uri+"?offset="+this.state.paginate.offset+"&limit="+this.state.paginate.limit),header);
            this.handlerror(data);
            this.setState({loading:false,listTree:data.data})
        }else{
            this.searching();
        }
        this.setState({genere:true})
        window.scrollTo(0, 0);
    }
    change=(e)=>{
        let paginate=this.state.paginate;
        paginate[e.target.name]=e.target.value;
        this.setState({paginate: paginate});
        
    }
    naviguer=(offset)=>{
        let paginate=this.state.paginate;
        paginate.offset=offset;
        this.setState({paginate: paginate});
        this.paginating();
    }
    

 
   changemulti=(multi)=>{
    this.setState({multi:multi});
   }
   searching=async()=>{
    let uri=this.props.searching;
    if(this.state.paginate.limit!==0 && this.state.searching){
        uri+="?limit="+this.state.paginate.limit+"&offset="+this.state.paginate.offset;
    }
    let header={"Content-Type": "application/json"};
    this.addheader(header);
    
        let search=new FetchHelper(uri,this.state.listTree.multisearch.map,"POST",header)
        let data =await search.sendrequest();
        this.handlerror(data);
        let listTree=this.state.listTree;
        listTree.liste=data.data.resultat;
        listTree.nbpage=0;
        let paginate=this.state.paginate;
       
        if (this.state.paginate.limit!==0 && this.state.searching) {
             listTree.nbpage=data.data.nbpage;
        }else{
            paginate.limit=0;
            paginate.offset=0;
        }
       
        this.setState({listTree:listTree,searching:true,paginate:paginate});
        window.scrollTo(0,0);
   }
   handlerror=(data)=>{
    if ('error' in data) {
        console.log('tonga');
        let e = data.error.e;
        if (e === undefined || e === null) e = data.error.ex;
        console.log("tonga atooo");
        toast.error(e.message+'!', {
          position: "top-center",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "light",
        });
        
    }else{

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
   }
    render(){
        return(
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
            <br />
            <div className="container">

                    <div id="card" className="card  mb-3" style={{ maxWidth: "300rem" }}>
                        <div id="card-header" className="card-header text-center"><h2>{this.props.title}</h2>
                       {this.props.cudable!==false ? <button className='btn  btn-success' onClick={()=>{ window.location.href=this.props.creating }}>Create</button> :<></>}
                        <div>
                            <h4>{this.state.listTree.liste.length} éléments</h4>
                        </div>
                        <div className="btn-group">
<FileExporter liste={this.state.listTree.liste} uri={this.props.uri} title={this.props.title} />
{this.props.cudable!==false ?
 <FileImporter error={this.handlerror} creating={this.props.import} />
:
<></>
 }
  
</div>
                         </div>
                        <div id="card-body" className="card-body">
                            
                            <div id="content" className="container">
                            { this.state.loading ? (
<Loader loading={this.state.loading} />
) : (
    <>
    <Multisearch   search={this.state.listTree.multisearch} onsearch={this.searching} update={this.changemulti} />

<Tab error={this.handlerror} deleting={this.props.deleting} cudable={this.props.cudable} headers={this.state.listTree.entete} uri={this.props.uri} data={this.state.listTree.liste} valuekey={this.state.listTree.valuekey} infoperso={this.state.listTree.infoperso} link={this.props.fiche} />
</>
)}
</div>

<nav>
  <Pagination nbpage={this.state.listTree.nbpage} genere={this.state.genere} current={this.state.paginate.offset} naviguer={this.naviguer} url={this.state.uri} paginate={this.state.paginate} />
  <div className='row'>
    <div className='col'></div>
    <div className='col'>
  <form onSubmit={this.actpaginate} >
    <div className='row'>
    <div className='col form-floating  mb-3'>  
    <input className='form-control'  type="number" name='limit' placeholder='Entries...' value={this.state.paginate.limit} onChange={this.change}/>
    <label htmlFor="floatingInput">Entries</label>
    </div>
    <div className='col mb-3'>
    <button type='submit' className='btn btn-info'>Change</button>
    </div> 
    </div>
  </form>
  </div>
  <div className='col'></div>
  </div>
</nav>
                        </div>
                    </div>
                </div>
            </div>
               
        );
    }
}
export default List;