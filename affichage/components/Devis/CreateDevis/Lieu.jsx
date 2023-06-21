import React, { Component } from 'react'
class Lieu extends Component {
   
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
 
 delete=(el)=>{
    this.props.delete(el);
    this.props.deleteplace(el);
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
                    <label htmlFor="nb" className="form-label">Prix</label>
                    <input type="number" name="tarif" onKeyPress={this.onlyNumber}  className="form-control"/>
                </div>
                {this.props.categories.map(categorie=>
                    <div className='mb-3'>
                          <div className="mb-3">
                    <label htmlFor={categorie.numero} className="form-label">Prix {categorie.nom}</label>
                    <input type="number" name={categorie.numero} onKeyPress={this.onlyNumber}  className="form-control"/>
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
 
export default Lieu;