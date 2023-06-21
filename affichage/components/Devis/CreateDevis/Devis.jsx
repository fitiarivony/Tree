import React, { Component } from 'react'
import Artiste from './Artiste';
import Sono from './Sono';
import Logistique from './Logistique';
import Lieu from './Lieu';
import Autre from './Autre';
import FetchHelper from '../../../Helper/FetchHelper'
class Devis extends Component {
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
        let helper = new FetchHelper(
           "devis/getrequired",null,
            "GET",
            );
          let data = await helper.sendrequest();
            this.setState({prestation:data.data})

            let helperclient = new FetchHelper(
               "client",null,
                "GET",
                );
              let dataclient = await helperclient.sendrequest();
                this.setState({clients:dataclient.data.liste})  
                let devis=this.state.devis;
                devis.idclient=dataclient.data.liste[0].idclient;
                this.setState({devis:devis})

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

     create=()=>{
      this.devise();
     }
      devise=async()=>{
         let header={
            "Content-Type": "application/json"
         };
         
      let helper = new FetchHelper(
         "devis",this.state.devis,
          "POST",
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
                     <button type="submit" onClick={this.create} className='btn btn-success'>Creer Devise</button>
                    </div>
                    <div className='col'>
                     <button type="submit" onClick={this.calculing} className='btn btn-primary'>Calculer Devise</button>
                    </div>
                    <div className='col'>
                     Prix total: {this.state.prixTotal}
                    </div>
                    </div>

               
                <Artiste add={this.addArtiste} delete={this.deleteArtiste} liste={this.state.liste.artiste} artistes={this.state.prestation.artiste} />

               
     
       <hr />
       <Sono add={this.addSono} delete={this.deleteSono} liste={this.state.liste.sono} sonos={this.state.prestation.sono} />
       <hr />
       <Logistique add={this.addLog} delete={this.deleteLog}  liste={this.state.liste.logistique} logs={this.state.prestation.logistique} />
       <hr />
       <Lieu prices={this.state.devis.prixplace} deleteplace={this.deletePlace}  addplace={this.addPrixPlace} categories={this.state.prestation.categorieplaces} add={this.addLieu} delete={this.deleteLieu} liste={this.state.liste.lieu} elements={this.state.prestation.lieu} />
       <hr />
       <Autre add={this.addAutre} delete={this.deleteAutre} liste={this.state.liste.autre} elements={this.state.prestation.autre} />

                </div>
          
           
            </>
        );
    }
}
 
export default Devis;