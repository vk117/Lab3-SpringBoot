import React from 'react';
import {Link} from 'react-router-dom';
import {withRouter} from 'react-router-dom';

class ShowElement extends React.Component{
    constructor(){
        super();
        this.sendId = this.sendId.bind(this);
    }

    sendId(){
        var url = "http://localhost:8080/projects/" + this.props.element.id;
        return fetch(url, {
            method: 'GET',
            //credentials: 'include'
        })
          .then((res) => res.text())
          .then((data) => JSON.parse(data))
          .then((data) => this.props.history.push('/details_2', {arr: data, bidVisible: this.props.bidVisible, username: this.props.username}))
    }

    render(){
        return(
            <tr>
                <td>
                    <a href="#" onClick={this.sendId.bind(this)}> 
                        {this.props.element.title}
                    </a>
                </td>
            </tr>
        )
    }
} 

export default withRouter(ShowElement);