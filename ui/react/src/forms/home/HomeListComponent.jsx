import React from 'react';

import Typography from '@material-ui/core/Typography';
import { Table, TableHead, TableRow, TableCell, Button, TableBody } from '@material-ui/core';
import HomeService from '../../api/home/HomeService';

class HomeListComponent extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      list: []
    }
  }
  componentDidMount = () => {
    this.retrieve();
  }
  retrieve = () => {
    HomeService.getList()
      .then(response => {
        console.log(response)
        this.setState({ list: response.data.homeList.content })
      })
  }
  edit = (id) => {
    console.log(`[HomeComponent.edit] id=${id}`)
    this.props.history.push(`/home-detail/${id}`);
  }
  delete = (id) => {
    console.log(`[HomeComponent.delete] id=${id}`)
    //this.props.history.push(`/home-detail/${id}`);
  }
  fwrd = (urlStr) => {
    this.props.history.push(urlStr);
  }

  render = () => {
    return (
      <div className="container">
        <Typography variant="h4">Home List</Typography>
        <Button variant="contained" color="primary" onClick={() => this.edit(-1)}>NEW</Button>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell align="left">Name</TableCell>
              <TableCell>URL</TableCell>
              <TableCell align="right">Action</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {this.state.list.map(row => (
              <TableRow key={row.id}>
                <TableCell>{row.name}</TableCell>
                <TableCell onClick={() => this.fwrd(row.urlStr)}>{row.urlStr}</TableCell>
                <TableCell align="right">
                  <Button variant="contained" color="primary" onClick={() => this.edit(row.id)}>Edit</Button>&nbsp;
                  <Button variant="contained" color="primary" onClick={() => this.delete(row.id)}>Delete</Button></TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div >
    );
  }
}

export default HomeListComponent;