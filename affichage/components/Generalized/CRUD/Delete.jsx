import React, { Component } from 'react'
import Modal from 'react-modal';
import FetchHelper from '../../../Helper/FetchHelper';

class Delete extends Component {
    state={
        isOpen:false
    }
    handleDelete=()=>{
        this.delete(this.props.element[this.props.id])
        this.closeModal();
    }
    openModal=()=>{
        this.setState({isOpen: true})
    
      }
      async delete(id) {
        let helper = new FetchHelper(
          this.props.deleting + "/" + id,
          null,
          "DELETE"
        );
        let data = await helper.sendrequest();
        this.props.error(data);
      }
      closeModal=()=>{
        this.setState({isOpen: false})
      }
    render() { 
        return (
            <>
            <button
            className="btn btn-danger rounded-pill"
            onClick={this.openModal}
          >
            Delete
            </button>
            <Modal
            isOpen={this.state.isOpen}
            onRequestClose={this.closeModal}
            style={{
              content: {
                position: 'absolute',
                top: '50%',
                left: '50%',
                transform: 'translate(-50%, -50%)',
                width: '400px',
                height: '200px'
                
              }
            }}
          >
            <h5>Confirmation de suppression</h5>
            <p>Êtes-vous sûr de vouloir supprimer cet élémentt ?</p>
            <table className='table table-bordered table-striped text-center'>
              <thead>
            {Object.keys(this.props.element).map(key=>
              <th>{key}</th> 
              )}
              </thead>
              <tbody>
              <tr>
              {Object.keys(this.props.element).map(key=>
              <td>{this.props.element[key]}</td> 
              )}
              </tr>
              </tbody>
            </table>
            
    
            <button className='btn btn-success' onClick={this.handleDelete}>Supprimer</button>
            <button className='btn btn-danger' onClick={this.closeModal}>Annuler</button>
          </Modal>
          </>
        );
    }
}
 
export default Delete;