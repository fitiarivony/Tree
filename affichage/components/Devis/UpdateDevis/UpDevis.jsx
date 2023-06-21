import React, { Component } from 'react'
import FetchHelper from '../../../Helper/FetchHelper';

import UpArtiste from './ElementDevis/UpArtiste';
import UpSono from './ElementDevis/UpSono';
import UpLogistique from './ElementDevis/UpLogistique';
import UpLieu from './ElementDevis/UpLieu';
import UpAutre from './ElementDevis/UpAutre';
class UpDevis extends Component {
    state = {
        devis:{
            nomdevis:"",
            idclient:null,
            elementdevis:[
                
            ],
            dateheure:"",
            prixplace:[],
        },
        
        prestation:{
            artiste:[
                {
                    id:0,
                    nomPrestation:"Jean aime",
                    tarif:20000,
                },
                {
                    id:1,
                    nomPrestation:"Lola",
                    tarif:20000,
                }
            ],
            sono:[
                {
                    id:0,
                    nomPrestation:"Bose",
                    tarif:20000,
                }
            ],
            logistique:[],
            lieu:[],
            autre:[],
            categorieplaces:[],
        },

        liste:{
            artiste:[],
            sono:[],
            logistique:[],
            lieu:[],
            autre:[],
        },
        clients:[],
        prixTotal:0,
      
     } 
     constructor(props) {
        super(props);
        this.initialize();
     }
      initialize=async ()=> {
            let helperclient = new FetchHelper(
               "client",null,
                "GET",
            );

              let dataclient = await helperclient.sendrequest();
                this.setState({clients:dataclient.data.liste})  
               
                
                let id=new URLSearchParams(window.location.search).get("id");
                let helperdevis = new FetchHelper(
                    "devis/"+id,null,
                     "GET",
                     );
            let datadevis = await helperdevis.sendrequest();
             let devis=this.state.devis;
             devis.idclient=datadevis.data.idclient;
             devis.nomdevis=datadevis.data.nomdevis;
             devis.elementdevis=datadevis.data.elementdevis;
             devis.prixplace=datadevis.data.prixplace;
             this.setState({prestation:datadevis.data.prestation,liste:datadevis.data.liste,devis:devis})

                console.log(datadevis);


     }

     change = (e) => {
        let devis = this.state.devis;
        devis[e.target.name] = e.target.value;
        console.log(devis);
        this.setState({devis:devis});
      };

     
     add=(el,name)=>{
        let devis=this.state.devis;
        devis.elementdevis.push(el);
        this.setState({devis:devis});
        let liste=this.state.liste;
        liste[name].push(el);
        this.setState({liste:liste});
     }

     addArtiste=(el)=>{
      let tarif=this.state.prestation.artiste.filter(artiste=>artiste.id===el.idprestation)[0].tarif;
      el['tarif'] = tarif;
     this.add(el,"artiste");
  }

  addLog=(el)=>{
   let tarif=this.state.prestation.logistique.filter(logistique=>logistique.id===el.idprestation)[0].tarif;
   el['tarif'] = tarif;
     this.add(el,"logistique");
  }

  addSono=(el)=>{
   let tarif=this.state.prestation.sono.filter(sono=>sono.id===el.idprestation)[0].tarif;
   el['tarif'] = tarif;
     this.add(el,"sono"); 
  }

     addLieu=(el)=>{
        this.add(el,"lieu"); 
     }

     addPrixPlace=(el)=>{
      let devis=this.state.devis;
      let prixplace=devis.prixplace;
      devis.prixplace=prixplace.concat(el);
         this.setState({devis:devis});
     }


     addAutre=(el)=>{
        this.add(el,"autre"); 
     }

    

     
     delete=(el,name)=>{
      let devis=this.state.devis;
      let filtre1=devis.elementdevis.filter(element=>element.idprestation!==el);
      devis.elementdevis=filtre1;
      this.setState({devis:devis});

      let liste=this.state.liste;
      let filtre=liste[name].filter(element=>element.idprestation!==el); 
      liste[name]=filtre
      this.setState({liste:liste});
   }
     deleteArtiste=(el)=>{
        this.delete(el,"artiste");
     }
     
     deleteLog=(el)=>{

        this.delete(el,"logistique");
     }
     deleteSono=(el)=>{
        this.delete(el,"sono");
     }
     deleteLieu=(el)=>{

        this.delete(el,"lieu");
     }

     deletePlace=(el)=>{
      let devis=this.state.devis;
      devis.prixplace=devis.prixplace.filter(price=>price.idlieu!==el);
      this.setState({devis:devis});
  }

     deleteAutre=(el)=>{
        this.delete(el,"autre");
     }




     update=(el,name)=>{
      let devis=this.state.devis;
      let filtre1=devis.elementdevis.filter(element=>element.idprestation===el.idold)[0];
      let index=devis.elementdevis.indexOf(filtre1);
      let keys=Object.keys(el).filter(key=>key!=='idold')
      keys.map(key=>
         filtre1[key]=el[key]
      )
     
      devis.elementdevis.splice(index,1,filtre1);
     this.setState({devis:devis});
     
     let liste=this.state.liste;
     let filtre=liste[name].filter(element=>element.idprestation===el.idold)[0]; 
     let indexliste=liste[name].indexOf(filtre);
     liste[name].splice(indexliste,1,filtre1)
     this.setState({liste:liste});
   }
     updateArtiste=(el)=>{
        this.update(el,"artiste");
     }
     
     updateLog=(el)=>{

        this.update(el,"logistique");
     }
     updateSono=(el)=>{
        this.update(el,"sono");
     }
     updateLieu=(el)=>{

        this.update(el,"lieu");
     }

     updatePlace=(tab)=>{
      console.log(tab);
      let devis=this.state.devis;
         for (const prixplace of tab) {
            let price=devis.prixplace.filter(toerana=>toerana.idlieu===prixplace.idlieu && toerana.idcategorieplace===prixplace.idcategorieplace)[0];
            let index=devis.prixplace.indexOf(price);
            price.prix=prixplace.prix;
            devis.prixplace[index]=price;
            this.setState({devis:devis});
         }
     }
     updateAutre=(el)=>{
        this.update(el,"autre");
     }


     updating=()=>{
      this.devise();
     }
      devise=async()=>{
         let header={
            "Content-Type": "application/json"
         };
         let id=new URLSearchParams(window.location.search).get("id");
      let helper = new FetchHelper(
         "devis/"+id,this.state.devis,
          "PUT",
          header
          );
        let data = await helper.sendrequest();
        console.log(data);
     }
    
     
     calcul=async ()=>{
      let header={
         "Content-Type": "application/json"
      };
      
   let helper = new FetchHelper(
      "devis/calculdevis",this.state.devis,
       "POST",
       header
       );
     let data = await helper.sendrequest();
     
     this.setState({prixTotal:data.data.prixTotal});
     }
     calculing=()=>{
      this.calcul();
     }

    render() { 
        return (
            <>
            
            
                <div className='container'>
                    <div className='row'>
                    <div className='col'></div>
                    <div className='col'>
                    <div className='form-floating  mb-3'>  
        <input className='form-control'  type="text" name='nomdevis' value={this.state.devis.nomdevis} onChange={this.change}/>
        <label htmlFor="floatingInput">Nom devis</label>
        </div>
        <div className='mb-3'>
        <label htmlFor="form-label">Client</label>
         <select name="idclient" className='form-control' onChange={this.change}>
            {this.state.clients.map(client =>
             <option value={client.id}>{client.nomclient} {client.prenomclient}</option>
            )}
         </select>

        </div>
        <div className=' form-floating mb-3'>
        <input className='form-control'  type="datetime-local" name='dateheure' value={this.state.devis.dateheure} onChange={this.change}/>
        <label htmlFor="floatingInput">Date heure</label>
        </div>
        
                    </div>
                    <div className='col'>
                     <button type="submit" onClick={this.updating} className='btn btn-success'>Update Devise</button>
                    </div>
                    <div className='col'>
                     <button type="submit" onClick={this.calculing} className='btn btn-info'>Calcul Devise</button>
                    </div>
                    <div className='col'>
                     Prix total: {this.state.prixTotal}
                    </div>
                    </div>

             
                <UpArtiste update={this.updateArtiste} add={this.addArtiste} delete={this.deleteArtiste} liste={this.state.liste.artiste} artistes={this.state.prestation.artiste} />

               
     
       <hr />
       <UpSono update={this.updateSono} add={this.addSono} delete={this.deleteSono} liste={this.state.liste.sono} sonos={this.state.prestation.sono} />
       <hr />
       <UpLogistique update={this.updateLog} add={this.addLog} delete={this.deleteLog}  liste={this.state.liste.logistique} logs={this.state.prestation.logistique} />
       <hr />
       <UpLieu updatePlace={this.updatePlace} prices={this.state.devis.prixplace} deleteplace={this.deletePlace}  addplace={this.addPrixPlace} categories={this.state.prestation.categorieplaces} update={this.updateLieu} add={this.addLieu} delete={this.deleteLieu} liste={this.state.liste.lieu} elements={this.state.prestation.lieu} />
       <hr />
       <UpAutre  update={this.updateAutre} add={this.addAutre} delete={this.deleteAutre} liste={this.state.liste.autre} elements={this.state.prestation.autre} />

                </div>
          
           
            </>
        );
    }
}
 
export default UpDevis;