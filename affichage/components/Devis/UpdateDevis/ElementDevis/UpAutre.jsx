import Modal from 'react-modal';
import React, { Component } from 'react'
class UpAutre extends Component {
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
 addelement=(event)=>{
    event.preventDefault();
    let form=new FormData(document.getElementById("form-autre"))
    let data=Object.fromEntries(form.entries());
    let obj={
        idprestation:Number.parseInt(data.id),
        tarif:Number.parseFloat(data.tarif)
    }

   this.props.add(obj);

 }
  delete=(el)=>{
    this.props.delete(el);
  }

  mettreAJour=(id)=>{
    this.setState({idprestation:id});
    this.handleModalClick();
 }

  updateAutre=(event)=>{
    event.preventDefault();
    let form=new FormData(event.target)
    let data=Object.fromEntries(form.entries());
    let obj={
        idold:Number.parseFloat(data.taloha),
        idprestation:Number.parseInt(data.idprestation),
        tarif:Number.parseFloat(data.prix)
    }
    console.log(obj);
   this.props.update(obj);
   this.handleModalClick();
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
                <div className='element'>
                <form action="" id='form-autre'>
            <div className="mb-3">
                
                <label htmlFor="element" className='form-label'>Autre</label>
                <select name="id" className='form-control' >
                    {this.props.elements.map(element=>
                         <option value={element.id}>{element.nomPrestation} </option>
                        )}
                </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="nb" className="form-label">Prix</label>
                    <input type="number"  onKeyPress={this.onlyNumber} name="tarif" className="form-control"  />
                </div>
                
            <button type="submit" onClick={this.addelement} className="btn btn-success">Ajouter</button>
            </form>
            </div>

                </div>
                <div className='col'>
                <table className="table table-light table-striped table-hover table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Nom prestation</th>
                        <th scope="col">Prix</th>
                    </tr>
                </thead>
                <tbody>
                    {this.props.liste.map(autre=>
                        <tr>
                         <td>{this.props.elements.filter(element=> element.id===autre.idprestation)[0].nomPrestation}</td>
                         <td>{autre.tarif}</td>
                        <td> <button className='btn btn-danger' onClick={()=>{this.delete(autre.idprestation)}} >Effacer</button></td>
                        {('id' in autre )   ? 
                        <td><button className='btn btn-warning' onClick={()=>this.mettreAJour(autre.idprestation)}>Mis à jour</button>
                        
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
              <form action="" id='modal-autre' onSubmit={this.updateAutre} >
                <p>Before: {this.state.idprestation}</p>
                <input type="hidden"  name="taloha" value={this.state.idprestation} />
                <div className="mb-3">
                
                <label htmlFor="element" className='form-label'>Autre</label>
                <select name="idprestation" className='form-control' >
                    {this.props.elements.map(element=>
                         <option value={element.id}>{element.nomPrestation} </option>
                        )}
                </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="nb" className="form-label">Prix</label>
                    <input type="number" onKeyPress={this.onlyNumber} name="prix" className="form-control"  />
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
 
export default UpAutre;