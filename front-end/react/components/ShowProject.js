import React from 'react';
import Bid from './Bid';
import {Link} from 'react-router-dom';
import ProjectBids from './ProjectBids';

export default class ShowProject extends React.Component{
    constructor(){
        super();
        this.state={
            title: '',
            description: '',
            skills: '',
            budget: '',
            showBid: false
        }
    }

    componentDidMount(){
        /*return fetch('http://localhost:8080/checksession',{
            method: 'GET',
            credentials: 'include'
        }).then(res => {
            if(res.status != 201){
                this.props.history.push('/login');
            }
            else if(this.props.location.state.bidInvisible == 'true'){
                document.getElementById('bid').disabled = true;
            }
        })*/
        if(!this.props.location.state.bidVisible){
            document.getElementById('bid').disabled = true;
        }
    }

    handleClick(){
        this.setInvisible();
        this.setState({showBid: true});
    }

    setInvisible(){
        document.getElementById('bid').style.visibility = "hidden";
    }

    render(){
        var arr = this.props.location.state.arr;
        var user = this.props.location.state.username;
        return(
            <div className='container-fluid'>
                <div className='card bg-light text-dark profile'>
                    <div className='card-body'>
                        <h1 style={{textAlign: 'center'}}>{arr.title}</h1><br/><br/>
                        <table className='table'>
                            <tbody style={{textAlign: 'center'}}>
                                <tr>
                                    <td>Project Description:</td>
                                    <td>{arr.description}</td>
                                </tr>
                                <tr>
                                    <td>Skills Required:</td>
                                    <td>{arr.skills}</td>
                                </tr>
                                <tr>
                                    <td>Project Budget:</td>
                                    <td>{arr.budget}</td>
                                </tr>
                                <tr>
                                    <td>Posted By:</td>
                                    <td>{arr.user}</td>
                                </tr>
                            </tbody>
                        </table><br/><br/>
                        <div>
                            <button type="button" className="btn btn-info" id="bid" onClick={this.handleClick.bind(this)}>Bid</button>
                        </div><br/><br/><br/><br/>
                            {this.state.showBid ? <Bid project_id={arr.id} title={arr.title} bid_by={user} /> : null}
                            <ProjectBids project_id={arr.id}/> 
                    </div>
                </div>
            </div>
        )
    }
}