import React, { useEffect, useState } from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Pie } from 'react-chartjs-2';
import FetchHelper from '../../Helper/FetchHelper';
import URLHelper from '../../Helper/URLHelper';

ChartJS.register(ArcElement, Tooltip, Legend);


export function Fromage() {
    let [stats,setstats]=useState(null);
    let [label,setlabel]=useState(null);
    let [valeur,setvalue]=useState(null);
    useEffect(()=>{
     if (stats===null) {
      init();
     }
    });


     const data = {
        labels: label,
        datasets: [
          {
            label: '# de depense',
            data: valeur,
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(153, 102, 255, 0.2)',
              'rgba(255, 159, 64, 0.2)',
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(153, 102, 255, 1)',
              'rgba(255, 159, 64, 1)',
            ],
            borderWidth: 1,
          },
        ],
      };
      
    const initlabel=(data) =>{
    let keys=Object.keys(data).filter(stat=>stat!=="iddevis")

    setlabel(keys);
   
    
    }
    const initvalue=(data)=>{
            let tab=[];
            let keys=Object.keys(data).filter(stat=>stat!=="iddevis")
            for (const lab of keys) {
                tab.push(data[lab]);
            }
           
            setvalue(tab);
      
    }
    const init=async ()=>{
        if (localStorage.getItem("idemploye")!==null) {
            let id=new URLSearchParams(window.location.search).get("id");
          let data = await FetchHelper.getData(URLHelper.urlgen("devis/fromage/"+id));
           console.log(data);
           if (data.data !==undefined && data.data!==null) {
                setstats(data.data);
                initlabel(data.data);
                initvalue(data.data);
           }
          
         }else{
            alert("Not employee")
            // window.location.href="/homeadmin?authorization="+localStorage.getItem("idadministrateur");
         }
      }
      

  return (
    <>
    <div className="row">
    <div className='col'></div>
    <div className='col'>
        <div className='card'>
            <div className='card-header'>
            <h1 className='card-title'>Diagramme</h1>

            </div>
           
            <div className='card-body'>
            <Pie data={data} />

            </div>
        </div>
   
    </div>
    <div className='col'></div>
    </div>
  
    </>
  )
  
}
