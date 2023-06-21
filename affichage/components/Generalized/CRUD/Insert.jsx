import { Component } from "react";
import FetchHelper from "../../../Helper/FetchHelper";
import URLHelper from "../../../Helper/URLHelper";
import PutIn from "./element/PutIn";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

class Insert extends Component {
  state = {
    fields: [],
    obj: {},
  };

  constructor(props) {
    super(props);
    this.initialize();
    this.creating = this.creating.bind(this);
    this.initialize = this.initialize.bind(this);
  }
  async initialize(){
    let header = {};
    this.addheader(header);
    console.log("init------");
    let data = await FetchHelper.getData(
      URLHelper.urlgen(this.props.uri),
      header
    );
    this.handlerror(data);
    this.setState({
      loading: false,
      fields: data.data.fields,
      obj: data.data.obj,
    });
  };

  changing = (field, value) => {
    let obj = this.state.obj;
    obj[field.name] = value;
    this.setState({ obj: obj });
  };
  create = (event) => {
    event.preventDefault();
    console.log("tonga");
    this.creating();
   
  }

  addheader = (header) => {
    if (localStorage.getItem("admin") != null) {
      header.authorization = localStorage.getItem("admin");
    }
  }


  async creating() {
    let header = { "Content-Type": "application/json" };
    this.addheader(header);
    let helper = new FetchHelper(
      this.props.creating,
      this.state.obj,
      "POST",
      header
    );
    let data = await helper.sendrequest();
    this.handlerror(data)
    return data;
  }
  handlerror = (data) => {
    if ("error" in data) {
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
            <div id="card-header" className="card-header text-center">
              <h2>{this.props.title}</h2>{" "}
            </div>
            <div id="card-body" className="card-body">
              <form onSubmit={this.create}>
                {this.state.fields.map((field) => (
                  <PutIn
                    key={field.name}
                    field={field}
                    value={this.state.obj[field.name]}
                    changing={this.changing}
                  />
                ))}

                <button type="submit" className="btn btn-success">
                  Create
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default Insert;
