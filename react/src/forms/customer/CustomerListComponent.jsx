import React from 'react';

import Typography from '@material-ui/core/Typography';
import { Table, TableHead, TableRow, TableCell, Button, TableBody, TablePagination } from '@material-ui/core';
import CustomerService from '../../api/customer/CustomerService';

class CustomerListComponent extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      list: [],
      paging: {
        rowsPerPage: 25,
        totalElements: 0,
        currentPage: 0
      }
    }
  }
  componentDidMount = () => {
    this.retrieve();
  }
  retrieve = () => {
    CustomerService.getList(this.state.paging.currentPage, this.state.paging.rowsPerPage)
      .then(response => {
        console.log(response)
        this.setState({
			list: response.data.customerList.content ,
			paging: {
	            rowsPerPage: response.data.customerList.size,
	            totalElements: response.data.customerList.totalElements,
	            currentPage: response.data.customerList.pageable.pageNumber
			}
		})
      })
  }
  edit = (id) => {
    console.log(`[CustomerComponent.edit] id=${id}`)
    this.props.history.push(`/customer-detail/${id}`);
  }
  delete = (id) => {
    console.log(`[CustomerComponent.delete] id=${id}`)
	CustomerService.delete(id)
      .then(response => {
        console.log(`[CustomerComponent.delete] response==>`, response)
        this.retrieve();
      })
  }

  handleChangePage = (e, newPage) => {
    //this.state.paging.currentPage = newPage;
	let paging = this.state.paging;
	paging.currentPage = newPage
	this.setState({
		paging: paging
	});
    this.retrieve();
  }
  handleChangeRowsPerPage = (e) => {
	let paging = this.state.paging;
	paging.rowsPerPage = e.target.value
	paging.currentPage = 0;
	this.setState({
		paging: paging
	});
    this.retrieve();
  }

  render = () => {
    return (
      <div className="container">
        <Typography variant="h4">Customer List</Typography>
        <Button variant="contained" color="primary" onClick={() => this.edit(-1)}>NEW</Button>
        <TablePagination
          component="div"
          count={this.state.paging.totalElements}
          page={this.state.paging.currentPage}
          onChangePage={this.handleChangePage}
          rowsPerPage={this.state.paging.rowsPerPage}
          onChangeRowsPerPage={this.handleChangeRowsPerPage}
        />
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>First Name</TableCell>
              <TableCell>Last Name</TableCell>
              <TableCell>Address 1</TableCell>
              <TableCell>Address 2</TableCell>
              <TableCell>City</TableCell>
              <TableCell>State</TableCell>

              <TableCell>Action</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {this.state.list.map(row => (
              <TableRow key={row.id}>
                <TableCell>{row.firstName}</TableCell>
                <TableCell>{row.lastName}</TableCell>
                <TableCell>{row.address1}</TableCell>
                <TableCell>{row.address2}</TableCell>
                <TableCell>{row.city}</TableCell>
                <TableCell>{row.state}</TableCell>

                <TableCell align="right">
                  <Button variant="contained" color="primary" onClick={() => this.edit(row.id)}>Edit</Button>&nbsp;
                  <Button variant="contained" color="primary" onClick={() => this.delete(row.id)}>Delete</Button></TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        <TablePagination
          component="div"
          count={this.state.paging.totalElements}
          page={this.state.paging.currentPage}
          onChangePage={this.handleChangePage}
          rowsPerPage={this.state.paging.rowsPerPage}
          onChangeRowsPerPage={this.handleChangeRowsPerPage}
        />
      </div >
    );
  }
}

export default CustomerListComponent;


