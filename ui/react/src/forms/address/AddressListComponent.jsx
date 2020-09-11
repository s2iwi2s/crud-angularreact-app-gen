import React from 'react';

import Typography from '@material-ui/core/Typography';
import { Table, TableHead, TableRow, TableCell, Button, TableBody, TablePagination } from '@material-ui/core';
import AddressService from '../../api/address/AddressService';

class AddressListComponent extends React.Component {
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
    //this.props.endUserId
    if (this.hasEndUser()) {
      AddressService.getByUser(this.props.endUserId)
        .then(response => {
          console.log(response)
          this.setState({
            list: response.data.list
          })
        })
    } else {
      AddressService.getList(this.state.paging.currentPage, this.state.paging.rowsPerPage)
        .then(response => {
          console.log(response)
          this.setState({
            list: response.data.addressList.content,
            paging: {
              rowsPerPage: response.data.addressList.size,
              totalElements: response.data.addressList.totalElements,
              currentPage: response.data.addressList.pageable.pageNumber
            }
          })
        })
    }
  }
  edit = (id) => {
    if (this.hasEndUser()) {
      this.props.history.push(`/address-detail/${id}/${this.props.endUserId}`);
    } else {
      this.props.history.push(`/address-detail/${id}`);
    }

  }
  delete = (id) => {
    console.log(`[AddressComponent.delete] id=${id}`)
    AddressService.delete(id)
      .then(response => {
        console.log(`[AddressComponent.delete] response==>`, response)
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

  hasEndUser = () => {
    return this.props.endUserId > 0;
  }
  renderPagination = () => {
    if (this.hasEndUser()) {
      return (<></>);
    }
    return (
      <TablePagination
        component="div"
        count={this.state.paging.totalElements}
        page={this.state.paging.currentPage}
        onChangePage={this.handleChangePage}
        rowsPerPage={this.state.paging.rowsPerPage}
        onChangeRowsPerPage={this.handleChangeRowsPerPage}
      />
    )
  }
  render = () => {
    let hsize = 'h4';
    if (this.hasEndUser()) {
      hsize = 'h5';
    }
    console.log(`[AddressListComponent.render] endUserId=${this.props.endUserId}, hsize = ${hsize}`)
    return (
      <div className="container">
        <Typography variant={hsize}>Address</Typography>
        {this.renderPagination()}

        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Address</TableCell>
              <TableCell>Is Billing?</TableCell>
              <TableCell>Is Shipping?</TableCell>
              <TableCell>Action</TableCell>
            </TableRow>
          </TableHead>

          <TableBody>
            {
              this.state.list.map(row => (
                <TableRow key={row.id}>
                  <TableCell>{row.name}</TableCell>
                  <TableCell>
                    <div>{row.address1}</div>
                    <div>{row.address2}</div>
                    <div>{row.city}, {row.state} {row.country} {row.zipCode}</div>
                  </TableCell>
                  <TableCell>{row.billTo}</TableCell>
                  <TableCell>{row.shipTo}</TableCell>
                  <TableCell align="right">
                    <Button variant="contained" color="primary" onClick={() => this.edit(row.id)}>Edit</Button>&nbsp;
                    <Button variant="contained" color="primary" onClick={() => this.delete(row.id)}>Delete</Button>
                  </TableCell>
                </TableRow>
              ))
            }
          </TableBody>
        </Table>
        {this.renderPagination()}
      </div >
    );
  }
}

export default AddressListComponent;


