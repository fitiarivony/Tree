import React, { Component } from 'react'
import Modal from 'react-modal';
class UpArtiste extends Component {
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
    onlyNumber = (event) => {

        // console.log(event);
    
        if (!/[\d.]/.test(event.key)) {
    
            event.preventDefault();
    
        }
    
    }

 addArtist=(event)=>{
    event.preventDefault();
    let form=new FormData(document.getElementById("form-artist"))
    let data=Object.fromEntries(form.entries());
    let obj={
        idprestation:Number.parseInt(data.id),
        duree:Number.parseFloat(data.duree)
    }

   this.props.add(obj);
 }

 mettreAJour=(id)=>{
    this.setState({idprestation:id});
    this.handleModalClick();
 }
 updateArtist=(event)=>{
    event.preventDefault();
    let form=new FormData(event.target)
    let data=Object.fromEntries(form.entries());
    console.log(form);
    let obj={
        idprestation:Number.parseInt(data.idprestation),
        idold:Number.parseFloat(data.taloha),
        duree:Number.parseFloat(data.duration)
    }
   
   this.props.update(obj);
   this.handleModalClick();
 }

 deleteArtiste=(el)=>{
    this.props.delete(el);
  }

  
    render() { 
        return (
            <>
            <div className='row'>
                <div className='col'>
                <div className='artiste'>
                    <form action="" id='form-artist'>
            <div className="mb-3">
                <label htmlFor="Artiste" className='form-label'>Artiste</label>
                <select name="id" className='form-control' >
                    {this.props.artistes.map(artiste=>
                         <option value={artiste.id}>{artiste.nomPrestation}  {artiste.tarif}</option>
                        )}
                </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="duree" className="form-label">Duree(h)</label>
                    <input type="number" name="duree"  onKeyPress={this.onlyNumber} className="form-control"  />
                </div>
            <button type="submit" onClick={this.addArtist} className="btn btn-success">Ajouter</button>
            </form>
            </div>
            
                </div>
                <div className='col'>
                <table className="table table-light table-striped table-hover table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Nom prestation</th>
                        <th scope="col">Tarif</th>
                        <th scope="col">Duree</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {this.props.liste.map(element=>
                        <tr>
                        <td>{this.props.artistes.filter(artiste=> artiste.id===element.idprestation)[0].nomPrestation}</td>
                        <td>{this.props.artistes.filter(artiste=>artiste.id===element.idprestation)[0].tarif}</td>
                        <td>{element.duree}</td>
                        <td> <button className='btn btn-danger' onClick={()=>{this.deleteArtiste(element.idprestation)}} >Effacer</button></td>
                        {('id' in element )   ? 
                        <td><button className='btn btn-warning' onClick={()=>this.mettreAJour(element.idprestation)}>Mis à jour</button>
                       
                        
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
              <form action="" id='modal-artist' onSubmit={this.updateArtist} >
                <p>Before: {this.state.idprestation}</p>
                <input type="hidden"  name="taloha" value={this.state.idprestation} />
        <div className="mb-3">
                <label htmlFor="Artiste" className='form-label'>Artiste</label>

                <select name="idprestation" className='form-control' >
                    {this.props.artistes.map(artiste=>
                         <option value={artiste.id}>{artiste.nomPrestation}  {artiste.tarif}</option>
                        )}
                </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="duree" className="form-label">Duree(h)</label>
                    <input type="number" name="duration" onKeyPress={this.onlyNumber}   className="form-control"  />
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
 
export default UpArtiste;