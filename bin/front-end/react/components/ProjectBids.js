import React from 'react';

class ProjectBids extends React.Component{
    constructor(){
        super();
        this.state = {
            bids: []
        }
    }

    componentDidMount(){
        var url = 'http://localhost:8080/getprojectbids/' + this.props.project_id;
        return fetch(url, {
            method: 'GET'
        }).then((res) => res.text())
          .then((data) => this.setState({bids: JSON.parse(data)}))
    }

    render(){
        return(
            <div>
                <h1 style={{textAlign: "center"}}>Bids</h1><br/>
                <table className='table'>
                    <thead>
                        <tr style={{backgroundColor: 'white'}}>
                            <th>Bid</th>
                            <th>Period</th>
                            <th>Bid By</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.bids.map(function(item, i){
                            return(
                                <tr>
                                    <td>{item.price}</td>
                                    <td>{item.period}</td>
                                    <td>{item.bidby}</td>
                                </tr>
                            )
                        })}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default ProjectBids;