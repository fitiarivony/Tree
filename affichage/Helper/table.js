class Colonne{
    constructor(label,value){
        this.label = label;
        this.value = value;
    }
    
    getlabel(){
        return this.label;
    }
    getvalue(){
        return this.value;
    }
  

}

class Tableau{
    constructor(data,content,id,colonnes=[]) {
        
        this.element=data;
        this.content=content;
        this.id=id;
        this.colonnes=colonnes;
    }

    getdata(){
        return this.element;
    }
    getcontent(){
        return this.content;
    }
    getid(){
        return this.id;
    }
    getcolonnes(){
        return this.colonnes;
    }
    addcolumn(tr,element){
        let td=document.createElement("td");
        td.append(element);
        tr.appendChild(td);
    }
    
    addcomponent(idtr,element="a",id="id",name="name",classe="",nom="lien"){
        let tr=document.getElementById(idtr);
        let td=document.createElement("td");
        let component=document.createElement(element);
        component.setAttribute("id",id);
        component.setAttribute("name",name);
        component.setAttribute("class",classe);
        component.append(nom);
        td.appendChild(component);
        tr.appendChild(td);
    }
    addHeader(table){
        let keys=null;
        if(Array.isArray(this.getdata())) keys=Object.keys(this.getdata()[0]);
        else  keys = Object.keys(this.getdata());
        if (this.getcolonnes().length!=0) {
            keys=this.getcolonnes();
        }
        keys.forEach(key => {
            let th=document.createElement("th");
            th.append(key.getvalue());
            table.appendChild(th);
        });
    }
    addbootstrap(table){
        table.setAttribute("class","table table-bordered table-hover table-striped");
        
    }
    
    array(table){
        let keys=Object.keys(this.getdata()[0]);
        if (this.getcolonnes().length!=0) {
            keys=this.getcolonnes();
        }
        for (let i=0;i<this.getdata().length;i++) {
            
            let tr=document.createElement("tr");
            tr.setAttribute("id",i);
            keys.forEach(key => {   
               
               this.addcolumn(tr, this.format(this.getdata()[i][key.getlabel()]));
            });
            table.appendChild(tr);
        }
    }
    format(date){
        
        if( new Date(date).toString() !== "Invalid Date" &&  typeof(date)==="string"){
           let daty =new Date(date);
           return daty.toLocaleString();
           
        }
        return date;
    }
    object(table){
        let keys=Object.keys(this.getdata());
            let tr=document.createElement("tr");
            tr.setAttribute("id","object");
            keys.forEach(key => {
                
               this.addcolumn(tr,this.format(this.getdata()[key]));
            });
            table.appendChild(tr);
    }

    initialize(){
   
        let table = document.createElement("table");
        table.setAttribute("id",this.getid());
       
        this.addbootstrap(table);
        this.addHeader(table);
        if(Array.isArray(this.getdata()))this.array(table);
        else this.object(table);
       this.getcontent().appendChild(table);
    }
}
