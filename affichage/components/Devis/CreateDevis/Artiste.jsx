import React, { Component } from 'react'
class Artiste extends Component {

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
 deleteArtiste=(el)=>{
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
                    <input type="number" name="duree"  className="form-control"  onKeyPress={this.onlyNumber} />
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
                    </tr>
                </thead>
                <tbody>
                    {this.props.liste.map(element=>
                        <tr>
                        <td>{this.props.artistes.filter(artiste=> artiste.id===element.idprestation)[0].nomPrestation}</td>
                        <td>{this.props.artistes.filter(artiste=>artiste.id===element.idprestation)[0].tarif}</td>
                        <td>{element.duree}</td>
                        <td> <button className='btn btn-danger' onClick={()=>{this.deleteArtiste(element.idprestation)}} >Effacer</button></td>
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
 
export default Artiste;