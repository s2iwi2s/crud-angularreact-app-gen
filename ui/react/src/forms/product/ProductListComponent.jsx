import React from 'react';

import Typography from '@material-ui/core/Typography';
import { Table, TableHead, TableRow, TableCell, Button, TableBody, TablePagination } from '@material-ui/core';
import ProductService from '../../api/product/ProductService';

class ProductListComponent extends React.Component {
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
    ProductService.getList(this.state.paging.currentPage, this.state.paging.rowsPerPage)
      .then(response => {
        console.log(response)
        this.setState({
			list: response.data.productList.content ,
			paging: {
	            rowsPerPage: response.data.productList.size,
	            totalElements: response.data.productList.totalElements,
	            currentPage: response.data.productList.pageable.pageNumber
			}
		})
      })
  }
  edit = (id) => {
    console.log(`[ProductComponent.edit] id=${id}`)
    this.props.history.push(`/product-detail/${id}`);
  }
  delete = (id) => {
    console.log(`[ProductComponent.delete] id=${id}`)
	ProductService.delete(id)
      .then(response => {
        console.log(`[ProductComponent.delete] response==>`, response)
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
        <Typography variant="h4">Product List</Typography>
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
              <TableCell>Item Code</TableCell>
              <TableCell>Description</TableCell>
              <TableCell>Price</TableCell>
              <TableCell>Quantity</TableCell>

              <TableCell>Action</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {this.state.list.map(row => (
              <TableRow key={row.id}>
                <TableCell>{row.itemCode}</TableCell>
                <TableCell>{row.description}</TableCell>
                <TableCell>{row.price}</TableCell>
                <TableCell>{row.quantity}</TableCell>

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

export default ProductListComponent;


