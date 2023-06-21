<!DOCTYPE html>
<html lang="en">
  <head>
    <title>${devis.nomdevis}</title>
<link rel="stylesheet" href="./mycss/css/bootstrap.min.css">
  </head>

  <body>

    
    <div class="card" style="width: 25rem;margin-left:150px;">

        <div class="mt-4 p-5 bg-dark text-white rounded">
  <h1 class="lead">${devis.nomdevis}</h1>
  <p class="display-4">Ne ratez pas cet evenement!!!</p>
<p class="lead">Ce ${devis.dateheure?date}</p>
<p class="lead"> ${devis.dateheure?time}</p>
  <hr class="my-4">
</div>

 <#list prestation.lieu as lieu>
  <div class="card-body">
    <#if lieu.photo?? >
            <img  class='img-thumbnail card-image-top relative' src=${lieu.photo}>
            <#else>
        </#if>
    <h5 class="card-title text-center">${lieu.nomprestation}</h5>

  </div>
 </#list>




     
        <#list prestation.artiste as artiste>
            <div class="card-body ">
            <#if artiste.photo?? >
            <img class='img-thumbnail card-image-top relative' src=${artiste.photo}>
            <#else>
        </#if>
 <h5 class="card-title text-center"> ${artiste.nomprestation}</h5>
            </div>
        </#list>


 <div class="card-body">
 <h5 class="card-title text-center">Prix</h5>
        <ul class="list-group">
        <#list prestation.prixplace as prixplace>
            
            <li class="list-group-item"> ${prixplace.nomcategorieplace}: ${prixplace.prix}Ar</li>
           
 </#list>
       </ul>
</div>
       </div>
  </body>
</html>
