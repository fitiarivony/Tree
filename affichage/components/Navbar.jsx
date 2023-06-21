import React, { Component } from 'react'
class Navbar extends Component {
    state = {  } 
  generedropdown=(url)=>{
      return (
<li class="nav-item dropdown">
         <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="try" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            {url.name}
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            {url.data.map(myurl=>
               <li><a class="dropdown-item" href={myurl.value}>{myurl.label}</a></li>
              )}
          </ul>
        </li>
       </li>


      );

  }

    render() { 
        return (
            <>
         <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <p class="navbar-brand">E-fety</p>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      {this.props.urls.map(url=>
      <>
      {url.name ===undefined || url.name===null ?
        <li class="nav-item">
        <a className="nav-link" href={url.value}>{url.label}</a> 
         </li>
         :
         <>
         {this.generedropdown(url)}
         </>
         
      }
     </>
     )}
      
      
      </ul>
    </div>
  </div>
</nav>
                        
                            
                 
            </>
        );
    }
}
 
export default Navbar;