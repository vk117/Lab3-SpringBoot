import React from 'react';
import {withRouter} from 'react-router-dom';
import {Link} from 'react-router-dom';

class ShowBid extends React.Component{
    constructor(){
        super();
        this.state={
            arr: {}
        }
    }

    componentDidMount(){
        var url = "http://localhost:8080/projects/" + this.props.element.projectid;
        return fetch(url, {
            method: 'GET',
            //credentials: 'include'
        })/*.then((res) => res.json())
          .then((arr) => {
              this.setState({arr: arr.result}); 
            })*/
            .then((res) => res.text())
            .then((data) => this.setState({arr: JSON.parse(data)}))
    }

    render(){
        return(
            <tr>
                <td><Link to={{pathname: '/bid', state: {project: this.state.arr}}}>
                    {this.props.element.projecttitle}
                </Link></td>
                <td>{this.state.arr.user}</td>
                <td>{this.props.element.price}</td>
            </tr>
        )
    }
}

export default withRouter(ShowBid);