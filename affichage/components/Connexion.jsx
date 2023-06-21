import React, { Component } from "react";
import FetchHelper from "../Helper/FetchHelper";
import URLHelper from "../Helper/URLHelper";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

class Connexion extends Component {
  state = {
    client: {
      identifiant: this.props.default.identifiant,
      mdp:  this.props.default.mdp,
    },
  };
  

  change = (e) => {
    let client = this.state.client;
    client[e.target.name] = e.target.value;
    this.setState(client);
  };
  handleSumbit = (event) => {
    event.preventDefault();
    this.login();
  };
  login = async () => {
    console.log("tonga ato");
    let data = await FetchHelper.getDataPost(
      URLHelper.urlgen(this.props.uri),
      this.state.client
    )
   let response=this.handlerror(data);
   console.log(data);
   if(response){
    localStorage.setItem(this.props.session,data.data.id);
   }
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
      return false;
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
        onClose:()=>{ window.location.href='/accueil' },
        });
    }
    return true;
  }

  render() {
    return (
      <>
        <br />
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
        <div className="row">
          <div className="col"></div>
          <div className="col">
            <div id="details">
              <div className="card" style={{ width: "18rem" }}>
                <form id="myForm" onSubmit={this.handleSumbit}>
                  <div id="card-header" className="card-header text-center">
                    <h4>{this.props.title}</h4>
                  </div>
                  <div id="main" className="card-img-top"></div>
                  <div className="card-body">
                    <div className="mb-3">
                      <label for="exampleInputEmail1" className="form-label">
                        Identifiant
                      </label>
                      <input
                        type="text"
                        name="identifiant"
                        value={this.state.client.identifiant}
                        onInput={this.change}
                        className="form-control"
                        id="exampleInputEmail1"
                        aria-describedby="emailHelp"
                      />
                    </div>

                    <div className="mb-3">
                      <label for="exampleInputEmail1" className="form-label">
                        Mot de passe
                      </label>
                      <input
                        type="password"
                        value={this.state.client.mdp}
                        name="mdp"
                        onInput={this.change}
                        className="form-control"
                        id="exampleInputEmail1"
                        aria-describedby="emailHelp"
                      />
                    </div>
                    <div className="mb-3">
                      <input
                        type="submit"
                        className="btn btn-success"
                        value="Connexion"
                        id="exampleInputEmail1"
                        aria-describedby="emailHelp"
                      />
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
          <div className="col"></div>
        </div>
      </>
    );
  }
}
export default Connexion;
