import React from 'react';

import Typography from '@material-ui/core/Typography';
import { Table, TableHead, TableRow, TableCell, Button, TableBody, TablePagination } from '@material-ui/core';
import PartItemService from '../../api/partItem/PartItemService';

class PartItemListComponent extends React.Component {
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
    PartItemService.getList(this.state.paging.currentPage, this.state.paging.rowsPerPage)
      .then(response => {
        console.log(response)
        this.setState({
			list: response.data.partItemList.content ,
			paging: {
	            rowsPerPage: response.data.partItemList.size,
	            totalElements: response.data.partItemList.totalElements,
	            currentPage: response.data.partItemList.pageable.pageNumber
			}
		})
      })
  }
  edit = (id) => {
    console.log(`[PartItemComponent.edit] id=${id}`)
    this.props.history.push(`/part-item-detail/${id}`);
  }
  delete = (id) => {
    console.log(`[PartItemComponent.delete] id=${id}`)
	PartItemService.delete(id)
      .then(response => {
        console.log(`[PartItemComponent.delete] response==>`, response)
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
        <Typography variant="h4">PartItem List</Typography>
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
              <TableCell>Name</TableCell>
              <TableCell>Description</TableCell>
              <TableCell>Serialized</TableCell>

              <TableCell>Action</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {this.state.list.map(row => (
              <TableRow key={row.id}>
                <TableCell>{row.name}</TableCell>
                <TableCell>{row.description}</TableCell>
                <TableCell>{row.serialized}</TableCell>

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

export default PartItemListComponent;


