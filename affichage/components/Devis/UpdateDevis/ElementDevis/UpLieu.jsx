import React, { Component } from 'react'
import Modal from 'react-modal';
class UpLieu extends Component {
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

    mettreAJour=(id)=>{
    this.setState({idprestation:id});
    this.handleModalClick();
    }

  updateLieu=(event)=>{
    event.preventDefault();
    let form=new FormData(event.target)
    let data=Object.fromEntries(form.entries());
    let tab=this.initcatupdate(data);
    let obj={
        idold:Number.parseFloat(data.taloha),
        idprestation:Number.parseInt(data.idprestation),
        tarif:Number.parseFloat(data.prix)
    }
   this.props.update(obj);
   this.props.updatePlace(tab);
   this.handleModalClick();
 }


 addelement=(event)=>{
    event.preventDefault();
    let form=new FormData(document.getElementById("form-lieu"))
    let data=Object.fromEntries(form.entries());
    let tab=this.initCategorie(data);
    let obj={
        idprestation:Number.parseInt(data.id),
        tarif:Number.parseFloat(data.tarif)
    }
    this.props.addplace(tab);
   this.props.add(obj);
 }
 delete=(el)=>{
    this.props.delete(el);
    this.props.deleteplace(el);
  }
  initcatupdate=(data)=>{
    let tab=[];
    for (const cat of this.props.categories) {
        let prixplace={};
        prixplace["idlieu"]=Number.parseInt(data.idprestation);
        prixplace["idcategorieplace"]=cat.id
        prixplace["prix"]=Number.parseFloat(data[cat.numero])
        tab.push(prixplace);
    }
  return tab;
 }
  
  initCategorie=(data)=>{
    let tab=[];
    for (const cat of this.props.categories) {
        let prixplace={};
        prixplace["idlieu"]=Number.parseInt(data.id);
        prixplace["idcategorieplace"]=cat.id
        prixplace["prix"]=Number.parseFloat(data[cat.numero])
        tab.push(prixplace);
    }
  return tab;
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
                <form action="" id='form-lieu'>
            <div className="mb-3">
                <label htmlFor="element" className='form-label'>Lieu</label>
                <select name="id" className='form-control' >
                    {this.props.elements.map(element=>
                         <option value={element.id}>{element.nomPrestation} </option>
                        )}
                </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="nb" className="form-label">Nombre</label>
                    <input type="number" onKeyPress={this.onlyNumber} name="tarif"  className="form-control"/>
                </div>

                {this.props.categories.map(categorie=>
                    <div className='mb-3'>
                          <div className="mb-3">
                    <label htmlFor={categorie.numero} className="form-label">Prix {categorie.nom}</label>
                    <input type="number" onKeyPress={this.onlyNumber} name={categorie.numero}   className="form-control"/>
                </div> 
                    </div>
                    
                    )}

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
                        {this.props.categories.map(categorie=>
                        <th>Prix {categorie.nom}</th>
                        )}
                    </tr>
                </thead>
                <tbody>
                    {this.props.liste.map(lieu=>
                        <tr>
                          <td>{this.props.elements.filter(element=> element.id===lieu.idprestation)[0].nomPrestation}</td>
                        <td>{lieu.tarif}</td>
                        {this.props.categories.map(categorie=>
                        <td>{this.props.prices.filter(price=> price.idlieu===lieu.idprestation && price.idcategorieplace===categorie.id)[0].prix}</td>
                        )}

                        <td> <button className='btn btn-danger' onClick={()=>{this.delete(lieu.idprestation)}} >Effacer</button></td>
                         {('id' in lieu )   ? 
                        <td><button className='btn btn-warning' onClick={()=>this.mettreAJour(lieu.idprestation)}>Mis à jour</button>
                        
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
                height: '400px' 
              }
            }}
          >
              <form action="" id='modal-autre' onSubmit={this.updateLieu} >
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
                {this.props.categories.map(categorie=>
                    <div className='mb-3'>
                          <div className="mb-3">
                    <label htmlFor={categorie.numero} className="form-label">Prix {categorie.nom}</label>
                    <input type="number" onKeyPress={this.onlyNumber} name={categorie.numero}   className="form-control"/>
                </div> 
                    </div>
                    
                    )}

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
 
export default UpLieu;