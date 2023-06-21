import React, { Component } from 'react'
import Modal from 'react-modal'
class UpSono extends Component {
 state={
        isOpen: false,
        idprestation:0,
    }
    closeModal=()=>{
        this.setState({isOpen: false});
    }
    handleModalClick=()=>{
        let bool=!this.state.isOpen;
        this.setState({isOpen:bool});
    }

 addSono=()=>{
      
    let form=new FormData(document.getElementById("form-sono"))
    let data=Object.fromEntries(form.entries());
    console.log(data);
    let obj={
        idprestation:Number.parseInt(data.id),
        nb:Number.parseInt(data.nb),
        duree:Number.parseFloat(data.duree),
    }
   this.props.add(obj);
 }

 
mettreAJour=(id)=>{
    this.setState({idprestation:id});
    this.handleModalClick();
 }
 updateSono=(event)=>{
    event.preventDefault();
    let form=new FormData(event.target)
    let data=Object.fromEntries(form.entries());
    console.log(form);
    let obj={
        idprestation:Number.parseInt(data.idprestation),
        idold:Number.parseFloat(data.taloha),
        duree:Number.parseFloat(data.duration),
        nb:Number.parseInt(data.isa)
    }
   
   this.props.update(obj);
   this.handleModalClick();
 }

 delete=(el)=>{
    this.props.delete(el);
  }
  onlyNumber = (event) => {

    // console.log(event);

    if (!/[\d.]/.test(event.key)) {

        event.preventDefault();

    }

}

    render() { 
        return (
            <>
            <div className='row'>
                <div className='col'>
                <div className='sono'>
                <form action="" id='form-sono'>
            <div className="mb-3">
                <label htmlFor="sono" className='form-label'>Sono</label>
                <select name="id" className='form-control' >
                    {this.props.sonos.map(sono=>
                         <option value={sono.id}>{sono.nomPrestation}  {sono.tarif} {sono.nomniveau}</option>
                        )}
                </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="nb" className="form-label">Nombre</label>
                    <input type="number" onKeyPress={this.onlyNumber} name="nb"  className="form-control"  />
                </div>
                <div className="mb-3">
                    <label htmlFor="nb" className="form-label">Duree</label>
                    <input type="number" onKeyPress={this.onlyNumber} name="duree"  className="form-control"  />
                </div>
            <button type="button" onClick={this.addSono} className="btn btn-success">Ajouter</button>
            </form>
            </div>

                </div>
                <div className='col'>
                <table className="table table-light table-striped table-hover table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Nom prestation</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Duree</th>
                    </tr>
                </thead>
                <tbody>
                    {this.props.liste.map(sono=>
                        <tr>
                          <td>{this.props.sonos.filter(element=> element.id===sono.idprestation)[0].nomPrestation}
                          -{this.props.sonos.filter(element=> element.id===sono.idprestation)[0].nomniveau}
                          </td>
                        <td>{sono.nb} </td>
                        <td>{sono.duree} </td>
                        <td> <button className='btn btn-danger' onClick={()=>{this.delete(sono.idprestation)}} >Effacer</button></td>
                       
                          {('id' in sono )   ? 
                        <td><button className='btn btn-warning' onClick={()=>this.mettreAJour(sono.idprestation)}>Mis à jour</button>
                       
                        
                        <Modal
            isOpen={this.state.isOpen}
            onRequestClose={this.closeModal}
            style={{
              content: {
                position: 'absolute',
                top: '50%',
                left: '50%',
                transform: 'translate(-50%, -50%)',
                width: '400px',
                height: '200px' 
              }
            }}
          >
              <form action="" id='modal-artist' onSubmit={this.updateSono} >
                <p>Before: {this.state.idprestation}</p>
                <input type="hidden"  name="taloha" value={this.state.idprestation} />
        <div className="mb-3">
                <label htmlFor="Artiste" className='form-label'>Artiste</label>

                <select name="idprestation" className='form-control' >
                    {this.props.sonos.map(sono=>
                         <option value={sono.id}>{sono.nomPrestation}  {sono.tarif} {sono.nomniveau}</option>
                        )}
                </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="nb" className="form-label">Duree(h)</label>
                    <input type="number" onKeyPress={this.onlyNumber} name="duration"   className="form-control"  />
                </div>
                <div className="mb-3">
                    <label htmlFor="isa" className="form-label">Nombre</label>
                    <input type="number" onKeyPress={this.onlyNumber} name='isa' className="form-control"  />
                </div>

            <button type="submit" className="btn btn-warning">Mettre à jour</button>
            <button type="button" onClick={this.handleModalClick} className="btn btn-primary ml-10">Annuler</button>
            </form>
          </Modal>
 
                        </td>
                           : <></>}

                       
                       
                        </tr>
                        )}
                    
                </tbody>
            </table>
                </div>
            </div>
          
            </>
        );
    }
}
 
export default UpSono;