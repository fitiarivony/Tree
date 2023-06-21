import React, { Component } from 'react'
class Logistique extends Component {
 addLogistique=()=>{
   
    let form=new FormData(document.getElementById("form-log"))
    let data=Object.fromEntries(form.entries());
    let obj={
        idprestation:Number.parseInt(data.id),
        nb:Number.parseInt(data.nb),
        duree:Number.parseFloat(data.duree)
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
            <div className='log'>
                <form action="" id="form-log">
            <div className="mb-3">
                
                <label htmlFor="log" className='form-label'>Logistique</label>
                <select name="id" className='form-control'>
                    {this.props.logs.map(log=>
                         <option value={log.id}>{log.nomPrestation}  {log.tarif} {log.nomniveau}</option>
                        )}
                </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="nb" className="form-label">Nombre</label>
                    <input type="number" onKeyPress={this.onlyNumber} name='nb' className="form-control"  />
                </div>
                <div className="mb-3">
                    <label htmlFor="duree" className="form-label">Duree</label>
                    <input type="number" onKeyPress={this.onlyNumber} name='duree' className="form-control"  />
                </div>
            <button type="button" onClick={this.addLogistique} className="btn btn-success">Ajouter</button>
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
                    {this.props.liste.map(log=>
                        <tr>
                          <td>{this.props.logs.filter(element=> element.id===log.idprestation)[0].nomPrestation}
                          -{this.props.logs.filter(element=> element.id===log.idprestation)[0].nomniveau}
                          </td>
                        <td>{log.nb}</td>
                        <td>{log.duree}</td>
                        <td> <button className='btn btn-danger' onClick={()=>{this.delete(log.idprestation)}} >Effacer</button></td>
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
 
export default Logistique;