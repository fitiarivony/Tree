import URLHelper from "./URLHelper";

export const methods={
    GET:'GET',
    POST:'POST',
    PUT:'PUT',
}
class FetchHelper{
    constructor(url,data,method,headers={}){
        this.url = url;
        this.data = data;
        this.headers = headers;
        this.method = method;
      
    }

    buildurl(){
        this.url=URLHelper.urlgen(this.url);
        let lien="";
        if(this.data!==null && this.data!==undefined && this.method==="GET"){
            lien="?"
            let keys=Object.keys(this.data);
            for (let i = 0; i < keys.length; i++) {
                if(i===keys.length-1){
                    lien+=keys[i]+"="+this.data[keys[i]];
                }else{
                    lien+=keys[i]+"="+this.data[keys[i]]+"&&";
                }
            }
            this.url+=lien;
        }

    }

    addbody(corps){
        
        if (this.data!==undefined && this.data!==null && (this.method==="POST" || this.method==="PUT")){
            corps.body=JSON.stringify(this.data);
          
        }
        console.log("Corps:");
        console.log(corps);
       
        console.log(this.url);
        return corps;
    }

    
    async sendrequest(){
        let corps= {
            crossDomain:true,
            method:this.method,
            headers:this.headers
        }
        this.buildurl()
        const response=await fetch(this.url,this.addbody(corps))
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data = await response.json();
          
            console.log(data);
            return  data;
    }

    async post(){
        const response=await fetch(this.buildurl(), {
        
            // Adding method type
            method: "POST",
            
            // Adding body or contents to send
            
            // Adding headers to the request
            headers:this.headers
        })

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        return data;

    }
     static getData= async (url,headers={})=>{
       console.log(url);
       const response=await fetch(url,
        {
            crossDomain:true,
            method:'GET',
            headers:headers
        })
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
      
        console.log(data);
        return  data;
    }

    
   static getDataPost=async (url,info,header={'Content-Type':'application/json'})=>{
        // main.js

    // POST request using fetch()
       const response=await fetch(url, {
        
            // Adding method type
            method: "POST",
            
            // Adding body or contents to send
            body: JSON.stringify(info),
            
            // Adding headers to the request
            headers:header
        })
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        return data;
    }

    static post=async (url)=>{
        // main.js

    // POST request using fetch()
       const response=await fetch(url, {
        
            // Adding method type
            method: "POST",
            // Adding headers to the request
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        })
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        return data;
    }

    topdf=async(filename)=>{
        fetch(URLHelper.urlgen(this.url+"?titre="+filename),{crossDomain:true,method:'POST', body: JSON.stringify(this.data), headers: {'Content-Type': 'application/json'}})
        .then(response=>{
            response.blob().then(blob=>{
                const fileURL = window.URL.createObjectURL(blob);
                let alink = document.createElement('a');
                alink.href = fileURL;
                alink.download = filename+'.pdf';
                alink.click();
            })
        })
    
    }
    
    tocsv=async()=>{
        console.log(this.data);
        fetch(URLHelper.urlgen(this.url), { method: 'POST',body: JSON.stringify(this.data), headers: {'Content-Type': 'application/json'}
      })
  .then(response => {
    const filename = "data.csv";
    response.blob().then(blob => {
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', filename);
      link.click();
    });
  });

    }

    toxls=async()=>{
        fetch(URLHelper.urlgen(this.url), { method: 'POST',body: JSON.stringify(this.data), headers: {'Content-Type': 'application/json'}
      })
  .then(response => {
    const filename = "data.xls";
    response.blob().then(blob => {
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', filename);
      link.click();
    });
  });

    }
   
   

    static put=async (url,info,headers={})=>{
        // main.js

    // POST request using fetch()
       const response=await fetch(url, {
        
            // Adding method type
            method: "PUT",
            
            // Adding body or contents to send
            body: JSON.stringify(info),
            
            // Adding headers to the request
            headers: headers,
        })
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        return data;
    }
}




export default FetchHelper;