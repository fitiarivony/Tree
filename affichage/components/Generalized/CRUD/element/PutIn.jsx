import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import { CKEditor } from '@ckeditor/ckeditor5-react'
import React, { Component } from 'react'
class PutIn extends Component {
    state = {  } 
    outputinput(field){
        let step=0;
        if(field.type==='number'){
            step=0.0001;
        }
        return( 
            <div>
                <label htmlFor={field.name}>{field.name}:</label>
                 <input value={this.props.value}
                 step={step}
                
                 className='form-control' type={field.type} name={field.name} onChange={this.change} />
            </div>
            )
    }

    onlyNumber = (event) => {

        // console.log(event);

        if (!/[\d.]/.test(event.key)) {

            event.preventDefault();

        }

    }

    change=(e)=>{
        this.props.changing(this.props.field,e.target.value);
    }
    outputarea(field){
        return( 
            <div>
                <label htmlFor={field.name}>{field.name}:</label>
                 <CKEditor editor={ClassicEditor} name={field.name} id={field.name} data={this.props.value}
                 onChange={this.onckchange}
                 ></CKEditor>
            </div>
            )
    }
    onckchange=(event,editor)=>{
        this.props.changing(this.props.field,editor.getData());
    }
    changeimage=(event)=>{
        let file = event.target.files[0];
        let reader = new FileReader();
        reader.onloadend = () => {
          this.props.changing(this.props.field, reader.result);
        };
        reader.readAsDataURL(file);
       
    }

    outputselect(field){
        return (
            <div>
                    <label htmlFor={field.name}>{field.name}:</label>
                    <select className='form-control' name={field.name} onChange={this.change}>
                       { field.value.map(option=>
                     
                        <option value={option.value}>{option.name}</option>
                        )}
                    </select>
                </div>
        );
    }
    outputimage(field){
        return(
            <div>
                <label htmlFor={field.name}>{field.name}:</label>
                <input className='form-control' onChange={this.changeimage} type='file' name={field.name}/>
            </div>
        );
    }
    getoutput(field){
        // console.log(field);
        let ret=<></>
        if(field.input==="input"){
          ret=this.outputinput(field);
        }
        else if (field.input==="textarea"){
            ret=this.outputarea(field);
        }
        else if(field.input==="image"){
            ret=this.outputimage(field);
        }
        else if (field.input==="select"){ 
           ret=this.outputselect(field);
        }
        return ret;
    }
    render() { 
        return (
            <>
             {this.getoutput(this.props.field)}
             </>
           
        );
    }
}
 
export default PutIn;