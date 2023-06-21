import React, { Component } from 'react'
class Autre extends Component {

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
                    <input type="number" name="tarif"  onKeyPress={this.onlyNumber} className="form-control"  />
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
 
export default Autre;