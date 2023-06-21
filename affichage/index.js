import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import reportWebVitals from "./reportWebVitals";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import './cssworkshop/custom.css'
import './cssworkshop/modalcustom.css'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import List from "./components/Generalized/CRUD/List";
import Update from "./components/Generalized/CRUD/Update";
import Fiche from "./components/Generalized/CRUD/Fiche";
import Insert from "./components/Generalized/CRUD/Insert";
import Accueil from "./components/Accueil";
import Navbar from "./components/Navbar";
import Deconnexion from "./components/Deconnexion";
import Connexion from "./components/Connexion";
import Choice from "./components/Choice";
import Devis from "./components/Devis/CreateDevis/Devis"
import UpDevis from "./components/Devis/UpdateDevis/UpDevis";
import InfoDevis from "./components/Devis/InfoDevis";
import TabDevis from "./components/Devis/ListeDevis/TabDevis";
import ListDevis from "./components/Devis/ListeDevis/ListeDevis";
import { Fromage } from "./components/Devis/Fromage";
const root = ReactDOM.createRoot(document.getElementById("root"));
const admin=[
  {
    name:"Help",
    data:[
      {
        label:"Niveau",
        value:"/niveau/list",
      },
      {
        label:"Employe",
        value:"/employe/list"
      },
      {
        label:"Type prestation",
        value:"/typeprestation/list"
      },
      {
        label:"Prestation",
        value:"/prestation/list"
      },
      {
        label:"Element devis",
        value:"/element_devis/list"
      },
      {
        label:"Client",
        value:"/client/list"
      },
    ]
  },
  {
    label:"Artiste",
    value:"/artiste/list",
  },
  
  {
    label:"Sono",
    value:"/sono/list",
  },
  {
    label:"Logistique",
    value:"/logistique/list",
  },
  {
    name:"Lieu",
    data:[
      {
        label:"Lieu",
        value:'/lieu/list'
      }, {
        label:"TypeLieu",
        value:'/typelieu/list'
      }
    
    ]
  },
  {
    label:"Autre",
    value:"/autre/list",
  },
  {
    name:"Place",
    data:[
      {
        label:"Categorie place",
        value:"/categorieplace/list",
      },
      {
        label:"Place lieu",
        value:"/placelieu/list",
      }
    ]
  },
  {
    name:"Benefice reeel",
    data:[
      {
        label:"Taxe",
        value:"/taxe/list",
      },
     
    ]
  },  
  {
    label:"Deconnexion",
    value:"/deconnexion",
  },
]
const employe=[
  {
    label:"Devis",
    value:"/devis/list"
  },
  {
    name:"Benefice reel",
    data:[
      {
        label:"Billet",
        value:"/billet/list",
      },
      {
        label:"Spectacle",
        value:"/devis_vita/list",
      },
    ],
  },
   {
    label:"Deconnexion",
    value:"/deconnect",
  },
]
const NavbarWrapper = () => {
  if (localStorage.getItem("idadmin") != null ) {
    return <Navbar urls={admin} />;
  } else if (localStorage.getItem("idemploye") != null ) {
    return <Navbar urls={employe} />;
    }
};
root.render(
  <>
    <NavbarWrapper />
    <BrowserRouter>
      <Routes>
        {/* Fromage */}
        <Route 
        path="/fromage/"
        element={ <Fromage></Fromage>}
        ></Route>
       
        {/* Devis_vita */}
        <Route
          path="/devis_vita/list"
          element={
            <List
              uri="devis_vita"
              creating="create"
              cudable={false}
              deleting="devis_vita"
              searching="devis_vita/search"
              fiche={{ label: "Plus", value: "/devis_vita/fiche" }}
              title="Liste des devis_vitas"
            />
          }
        ></Route>

      {/* Billet */}
      <Route
          path="/billet/list"
          element={
            <List
              uri="billet"
              creating="create"
              deleting="billet"
              searching="billet/search"
              fiche={{ label: "Plus", value: "/billet/fiche" }}
              title="Liste des billets"
            />
          }
        ></Route>
        <Route
          path="/billet/update"
          element={
            <Update
              uri="billet/update"
              updating="billet"
              title="Mettre à jour la billet"
            />
          }
        ></Route>
        <Route
          path="/billet/create"
          element={
            <Insert
              uri="billet/create"
              creating="billet"
              title="Enregistrer la billet"
            />
          }
        ></Route>

        <Route
          path="/billet/fiche"
          element={<Fiche uri="billet" title="Fiche billet"></Fiche>}
        ></Route>



        {/* Taxe */}
        <Route
          path="/taxe/list"
          element={
            <List
              uri="taxe"
              creating="create"
              deleting="taxe"
              searching="taxe/search"
              fiche={{ label: "Plus", value: "/taxe/fiche" }}
              title="Liste des taxes"
            />
          }
        ></Route>
        <Route
          path="/taxe/update"
          element={
            <Update
              uri="taxe/update"
              updating="taxe"
              title="Mettre à jour la taxe"
            />
          }
        ></Route>
        <Route
          path="/taxe/create"
          element={
            <Insert
              uri="taxe/create"
              creating="taxe"
              title="Enregistrer la taxe"
            />
          }
        ></Route>

        <Route
          path="/taxe/fiche"
          element={<Fiche uri="taxe" title="Fiche taxe"></Fiche>}
        ></Route>



        {/* Employe */}
         <Route
          path="/employe/list"
          element={
            <List
              uri="employe"
              creating="create"
              deleting="employe"
              searching="employe/search"
              fiche={{ label: "Plus", value: "/employe/fiche" }}
              title="Liste des employes"
            />
          }
        ></Route>
        <Route
          path="/employe/update"
          element={
            <Update
              uri="employe/update"
              updating="employe"
              title="Mettre à jour la employe"
            />
          }
        ></Route>
        <Route
          path="/employe/create"
          element={
            <Insert
              uri="employe/create"
              creating="employe"
              title="Enregistrer la employe"
            />
          }
        ></Route>

        <Route
          path="/employe/fiche"
          element={<Fiche uri="employe" title="Fiche employe"></Fiche>}
        ></Route>


        {/* Client */}
        <Route
          path="/client/list"
          element={
            <List
              uri="client"
              creating="create"
              deleting="client"
              searching="client/search"
              fiche={{ label: "Plus", value: "/client/fiche" }}
              title="Liste des clients"
            />
          }
        ></Route>
        <Route
          path="/client/update"
          element={
            <Update
              uri="client/update"
              updating="client"
              title="Mettre à jour la client"
            />
          }
        ></Route>
        <Route
          path="/client/create"
          element={
            <Insert
              uri="client/create"
              creating="client"
              title="Enregistrer la client"
            />
          }
        ></Route>

        <Route
          path="/client/fiche"
          element={<Fiche uri="client" title="Fiche client"></Fiche>}
        ></Route>



{/* TypePrestation */}
<Route
          path="/typeprestation/list"
          element={
            <List
              uri="typeprestation"
              creating="create"
              deleting="typeprestation"
              searching="typeprestation/search"
              import="typeprestation"
              fiche={{ label: "Plus", value: "/typeprestation/fiche" }}
              title="Liste des typeprestations"
            />
          }
        ></Route>
        <Route
          path="/typeprestation/update"
          element={
            <Update
              uri="typeprestation/update"
              updating="typeprestation"
              title="Mettre à jour l'typeprestation"
            />
          }
        ></Route>
        <Route
          path="/typeprestation/create"
          element={
            <Insert
              uri="typeprestation/create"
              creating="typeprestation"
              title="Enregistrer l'typeprestation"
            />
          }
        ></Route>

        <Route
          path="/typeprestation/fiche"
          element={<Fiche uri="typeprestation" title="Fiche typeprestation"></Fiche>}
        ></Route>



{/* Prestation */}
<Route
          path="/prestation/list"
          element={
            <List
              uri="prestation"
              creating="create"
              deleting="prestation"
              searching="prestation/search"
              import="prestation"
              fiche={{ label: "Plus", value: "/prestation/fiche" }}
              title="Liste des prestations"
            />
          }
        ></Route>
        <Route
          path="/prestation/update"
          element={
            <Update
              uri="prestation/update"
              updating="prestation"
              title="Mettre à jour l'prestation"
            />
          }
        ></Route>
        <Route
          path="/prestation/create"
          element={
            <Insert
              uri="prestation/create"
              creating="prestation"
              title="Enregistrer l'prestation"
            />
          }
        ></Route>

        <Route
          path="/prestation/fiche"
          element={<Fiche uri="prestation" title="Fiche prestation"></Fiche>}
        ></Route>
{/* Element devis */}
<Route
          path="/element_devis/list"
          element={
            <List
              uri="element_devis"
              creating="create"
              deleting="element_devis"
              searching="element_devis/search"
              import="element_devis"
              fiche={{ label: "Plus", value: "/element_devis/fiche" }}
              title="Liste des element_deviss"
            />
          }
        ></Route>
        <Route
          path="/element_devis/update"
          element={
            <Update
              uri="element_devis/update"
              updating="element_devis"
              title="Mettre à jour l'element_devis"
            />
          }
        ></Route>
        <Route
          path="/element_devis/create"
          element={
            <Insert
              uri="element_devis/create"
              creating="element_devis"
              title="Enregistrer l'element_devis"
            />
          }
        ></Route>

        <Route
          path="/element_devis/fiche"
          element={<Fiche uri="element_devis" title="Fiche element_devis"></Fiche>}
        ></Route>



{/* Artiste */}
<Route
          path="/artiste/list"
          element={
            <List
              uri="v_artiste"
              creating="create"
              deleting="artiste"
              searching="v_artiste/search"
              import="artiste"
              fiche={{ label: "Plus", value: "/artiste/fiche" }}
              title="Liste des artistes"
            />
          }
        ></Route>
        <Route
          path="/artiste/update"
          element={
            <Update
              uri="artiste/update"
              updating="artiste"
              title="Mettre à jour l'artiste"
            />
          }
        ></Route>
        <Route
          path="/artiste/create"
          element={
            <Insert
              uri="artiste/create"
              creating="artiste"
              title="Enregistrer l'artiste"
            />
          }
        ></Route>

        <Route
          path="/artiste/fiche"
          element={<Fiche uri="v_artiste" title="Fiche artiste"></Fiche>}
        ></Route>

        {/* Niveau */}
        <Route
          path="/niveau/list"
          element={
            <List
              uri="niveau"
              creating="create"
              deleting="niveau"
              searching="niveau/search"
              import="niveau"
              fiche={{ label: "Plus", value: "/niveau/fiche" }}
              title="Liste des niveaus"
            />
          }
        ></Route>
        <Route
          path="/niveau/update"
          element={
            <Update
              uri="niveau/update"
              updating="niveau"
              title="Mettre à jour la niveau"
            />
          }
        ></Route>
        <Route
          path="/niveau/create"
          element={
            <Insert
              uri="niveau/create"
              creating="niveau"
              title="Enregistrer la niveau"
            />
          }
        ></Route>

        <Route
          path="/niveau/fiche"
          element={<Fiche uri="niveau" title="Fiche niveau"></Fiche>}
        ></Route>



{/* Sono */}
<Route
          path="/sono/list"
          element={
            <List
              uri="v_sono"
              creating="create"
              deleting="sono"
              searching="v_sono/search"
              import="sono"
              fiche={{ label: "Plus", value: "/sono/fiche" }}
              title="Liste des sonos"
            />
          }
        ></Route>
        <Route
          path="/sono/update"
          element={
            <Update
              uri="sono/update"
              updating="sono"
              title="Mettre à jour la sono"
            />
          }
        ></Route>
        <Route
          path="/sono/create"
          element={
            <Insert
              uri="sono/create"
              creating="sono"
              title="Enregistrer la sono"
            />
          }
        ></Route>

        <Route
          path="/sono/fiche"
          element={<Fiche uri="sono" title="Fiche sono"></Fiche>}
        ></Route>

{/* logistique */}
<Route
          path="/logistique/list"
          element={
            <List
              uri="v_logistique"
              creating="create"
              deleting="logistique"
              searching="v_logistique/search"
              import="logistique"
              fiche={{ label: "Plus", value: "/logistique/fiche" }}
              title="Liste des logistiques"
            />
          }
        ></Route>
        <Route
          path="/logistique/update"
          element={
            <Update
              uri="logistique/update"
              updating="logistique"
              title="Mettre à jour la logistique"
            />
          }
        ></Route>
        <Route
          path="/logistique/create"
          element={
            <Insert
              uri="logistique/create"
              creating="logistique"
              title="Enregistrer la logistique"
            />
          }
        ></Route>

        <Route
          path="/logistique/fiche"
          element={<Fiche uri="logistique" title="Fiche logistique"></Fiche>}
        ></Route>

        {/* Autre */}
<Route
          path="/autre/list"
          element={
            <List
              uri="v_autre"
              creating="create"
              deleting="autre"
              searching="v_autre/search"
              import="autre"
              fiche={{ label: "Plus", value: "/autre/fiche" }}
              title="Liste des autres"
            />
          }
        ></Route>
        <Route
          path="/autre/update"
          element={
            <Update
              uri="autre/update"
              updating="autre"
              title="Mettre à jour la autre"
            />
          }
        ></Route>
        <Route
          path="/autre/create"
          element={
            <Insert
              uri="autre/create"
              creating="autre"
              title="Enregistrer la autre"
            />
          }
        ></Route>

        <Route
          path="/autre/fiche"
          element={<Fiche uri="autre" title="Fiche autre"></Fiche>}
        ></Route>


{/* TypeLieu */}
<Route
          path="/typelieu/list"
          element={
            <List
              uri="typelieu"
              creating="create"
              deleting="typelieu"
              searching="typelieu/search"
              import="typelieu"
              fiche={{ label: "Plus", value: "/typelieu/fiche" }}
              title="Liste des typelieus"
            />
          }
        ></Route>
        <Route
          path="/typelieu/update"
          element={
            <Update
              uri="typelieu/update"
              updating="typelieu"
              title="Mettre à jour la typelieu"
            />
          }
        ></Route>
        <Route
          path="/typelieu/create"
          element={
            <Insert
              uri="typelieu/create"
              creating="typelieu"
              title="Enregistrer la typelieu"
            />
          }
        ></Route>

        <Route
          path="/typelieu/fiche"
          element={<Fiche uri="typelieu" title="Fiche typelieu"></Fiche>}
        ></Route>
  

  {/* Lieu */}
  <Route
          path="/lieu/list"
          element={
            <List
              uri="v_lieu"
              creating="create"
              deleting="lieu"
              searching="v_lieu/search"
              import="lieu"
              fiche={{ label: "Plus", value: "/lieu/fiche" }}
              title="Liste des lieus"
            />
          }
        ></Route>
        <Route
          path="/lieu/update"
          element={
            <Update
              uri="lieu/update"
              updating="lieu"
              title="Mettre à jour la lieu"
            />
          }
        ></Route>
        <Route
          path="/lieu/create"
          element={
            <Insert
              uri="lieu/create"
              creating="lieu"
              title="Enregistrer la lieu"
            />
          }
        ></Route>

        <Route
          path="/lieu/fiche"
          element={<Fiche uri="lieu" title="Fiche lieu"></Fiche>}
        ></Route>
        {/* Devise */}
        <Route
          path="/devis/list"
          element={
            <ListDevis
              uri="devis"
              creating="create"
              deleting="devis"
              searching="devis/search"
              import="autre"
              fiche={{ label: "Plus", value: "/devis/fiche" }}
              title="Liste des devis"
            />
          }
        ></Route>

    <Route
    path="/devis/create"
    element={<Devis ></Devis>}
    ></Route>

<Route
    path="/devis/update"
    element={<UpDevis ></UpDevis>}
    ></Route>

    <Route
    path="/devis/fiche"
    element={<InfoDevis uri="devis" ></InfoDevis>}
    ></Route>

    {/* Categorie place */}
    <Route
          path="/categorieplace/list"
          element={
            <List
              uri="categorieplace"
              creating="create"
              deleting="categorieplace"
              searching="categorieplace/search"
              import="categorieplace"
              fiche={{ label: "Plus", value: "/categorieplace/fiche" }}
              title="Liste des categorieplaces"
            />
          }
        ></Route>
        <Route
          path="/categorieplace/update"
          element={
            <Update
              uri="categorieplace/update"
              updating="categorieplace"
              title="Mettre à jour la categorieplace"
            />
          }
        ></Route>
        <Route
          path="/categorieplace/create"
          element={
            <Insert
              uri="categorieplace/create"
              creating="categorieplace"
              title="Enregistrer la categorieplace"
            />
          }
        ></Route>

        <Route
          path="/categorieplace/fiche"
          element={<Fiche uri="categorieplace" title="Fiche categorieplace"></Fiche>}
        ></Route>

{/* Place lieu */}
<Route
          path="/placelieu/list"
          element={
            <List
              uri="placelieu"
              creating="create"
              deleting="placelieu"
              searching="placelieu/search"
              import="placelieu"
              fiche={{ label: "Plus", value: "/placelieu/fiche" }}
              title="Liste des placelieux"
            />
          }
        ></Route>
        <Route
          path="/placelieu/update"
          element={
            <Update
              uri="placelieu/update"
              updating="placelieu"
              title="Mettre à jour la placelieu"
            />
          }
        ></Route>
        <Route
          path="/placelieu/create"
          element={
            <Insert
              uri="placelieu/create"
              creating="placelieu"
              title="Enregistrer la placelieu"
            />
          }
        ></Route>

        <Route
          path="/placelieu/fiche"
          element={<Fiche uri="placelieu" title="Fiche placelieu"></Fiche>}
        ></Route>



             
        {/* Gestion session */}
        <Route
          path="/"
          element={ <Choice></Choice> }
        ></Route>
         <Route
          path="/"
          element={ <Connexion uri='admin/login'/> }
        ></Route>

        <Route path="/accueil" element={<Accueil />}></Route>

        <Route path="/deconnexion"  uri={'admin/logout'} element={<Deconnexion deconnexion={['idadmin']} token={false} />}></Route>
        <Route path="/deconnect"  uri={'employe/logout'} element={<Deconnexion deconnexion={['idemploye']} token={false} />}></Route>
      </Routes>
    </BrowserRouter>
  </>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
