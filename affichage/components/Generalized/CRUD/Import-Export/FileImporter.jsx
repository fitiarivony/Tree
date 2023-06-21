import React from 'react';
import * as Papa from 'papaparse';
import * as XLSX from 'xlsx';
import Modal from 'react-modal';
import FetchHelper from '../../../../Helper/FetchHelper';
import 'react-toastify/dist/ReactToastify.css';

class FileImporter extends React.Component {
  state={
    entete:[
      ["tryy"]
    ],
    donnee:[
      [
        "essau"
      ]
    ],
    isOpen:false
  }
  handleFileUpload = (event) => {
    const file = event.target.files[0];

    if (file) {
      const { name } = file;
      const extension = name.split('.').pop().toLowerCase();

      if (extension === 'csv') {
        this.parseCSV(file);
        this.openModal()
      } else if (extension === 'xls' || extension === 'xlsx') {
        this.parseXLS(file);
        this.openModal();
      } else {
        alert('Unsupported file format');
      }
    }
  };

  getData=(data)=>{
    let tab=[]
    for (let index = 1; index < data.length; index++) {
      tab.push(data[index]);
    }
    return tab;
  }
  initData=()=>{
   let tab=[]
      for (const element of this.state.donnee) {
        let obj={}
        for (let j = 0; j < this.state.entete.length; j++) {
          obj[this.state.entete[j]]=element[j]
        }
        tab.push(obj);
      }
     return tab;
  }

  parseCSV = (file) => {
    Papa.parse(file, {
      complete: (results) => {
        this.setState({entete: results.data[0],donnee: this.getData(results.data)})
        // console.log(this.state.donnee);
      },
    });
  };
  openModal=()=>{
    this.setState({isOpen: true})

  }
  closeModal=()=>{
    this.setState({isOpen: false})

  }
  handleInsert=()=>{
    console.log("Insert");
     this.creating();    
    this.closeModal()
  }

  async creating() {
    let header = { "Content-Type": "application/json" };
    this.addheader(header);
    let helper = new FetchHelper(
      this.props.creating+"/multiinsert",
      this.initData(),
      "POST",
      header
    );
    let data = await helper.sendrequest();
    this.props.error(data)
    return data;
  }

  addheader=(header)=>{   
    if (localStorage.getItem('admin')!=null) {
        header.authorization=localStorage.getItem('admin');
    } 
    console.log(header);
}


  parseXLS = (file) => {
    const reader = new FileReader();

    reader.onload = (event) => {
      const data = new Uint8Array(event.target.result);
      const workbook = XLSX.read(data, { type: 'array' });
      const sheetName = workbook.SheetNames[0];
      const worksheet = workbook.Sheets[sheetName];
      const jsonData = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

      // console.log(jsonData); // Access the parsed data here
      this.setState({entete: jsonData[0],donnee: this.getData(jsonData)})
    };

    reader.readAsArrayBuffer(file);
  };

  render() {
    return (
      <div>
        <input type="file" className='form-control'  onChange={this.handleFileUpload} />
        <div>
      <Modal
        isOpen={this.state.isOpen}
        onRequestClose={this.closeModal}
        style={{
          content: {
            overflowY: 'scroll',
            position: 'absolute',
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            maxWidth: '500px',
            maxHeight: '300px' // Adjust the maximum height as needed
          }
        }}
      >
        <h2>Confirmation d'Insertion</h2>
        <p>Êtes-vous sûr de vouloir inserer ces éléments ?</p>
        <table className='table table-bordered'>
          {
            this.state.entete.map(colonne=>
              <th> {colonne}</th>
            )
          }
          {
            this.state.donnee.map(ligne=>
              <tr>
                {ligne.map(colonne=>
              <td>{colonne}</td>
                )
              }
              </tr>
              )
          }

         

        </table>

        <button className='btn btn-success' onClick={this.handleInsert}>Inserer</button>
        <button className='btn btn-danger' onClick={this.closeModal}>Annuler</button>
      </Modal>
    </div>
      </div>
    );
  }
}

export default FileImporter;
