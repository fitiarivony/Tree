import React, { Component } from 'react'
import Connexion from './Connexion';
class Choice extends Component {
    state = {  } 
    render() { 
        return (
            <>
            <div class="mt-4 p-5   rounded">
  <h1>Welcome  to E-fety!</h1>
  <p>Realize your craziest dream</p>
</div> 
            <div className='row'>
                <div className='col'>
                    <Connexion title='Connexion admin' default={{identifiant:'admin',mdp:'admin'}} session='idadmin' uri='admin/login'/>
                    </div>
                <div className='col'>

                <Connexion title='Connexion employe'   default={{identifiant:'jean',mdp:'jean'}} session='idemploye' uri='employe/login'/>
                </div>
            </div>
            </>
        );
    }
}
 
export default Choice;