import React, { Component } from 'react'
class Sono extends Component {

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
                         <option value={sono.id}>{sono.nomPrestation} {sono.nomniveau}  {sono.tarif}</option>
                        )}
                </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="nb" className="form-label">Nombre</label>
                    <input type="number" name="nb" onKeyPress={this.onlyNumber}  className="form-control"  />
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
 
export default Sono;