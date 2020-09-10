import React from 'react';

import Typography from '@material-ui/core/Typography';
import { Table, TableHead, TableRow, TableCell, Button, TableBody, TablePagination } from '@material-ui/core';
import MyCaseService from '../../api/myCase/MyCaseService';

class MyCaseListComponent extends React.Component {
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
    MyCaseService.getList(this.state.paging.currentPage, this.state.paging.rowsPerPage)
      .then(response => {
        console.log(response)
        this.setState({
			list: response.data.myCaseList.content ,
			paging: {
	            rowsPerPage: response.data.myCaseList.size,
	            totalElements: response.data.myCaseList.totalElements,
	            currentPage: response.data.myCaseList.pageable.pageNumber
			}
		})
      })
  }
  edit = (id) => {
    console.log(`[MyCaseComponent.edit] id=${id}`)
    this.props.history.push(`/my-case-detail/${id}`);
  }
  delete = (id) => {
    console.log(`[MyCaseComponent.delete] id=${id}`)
	MyCaseService.delete(id)
      .then(response => {
        console.log(`[MyCaseComponent.delete] response==>`, response)
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
        <Typography variant="h4">MyCase List</Typography>
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
              <TableCell>Title</TableCell>
              <TableCell>Status</TableCell>
              <TableCell>Case Type 1</TableCell>
              <TableCell>Case Type 2</TableCell>
              <TableCell>Case Type 3</TableCell>
              <TableCell>Status Code</TableCell>
              <TableCell>Comments</TableCell>

              <TableCell>Action</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {this.state.list.map(row => (
              <TableRow key={row.id}>
                <TableCell>{row.title}</TableCell>
                <TableCell>{row.status.value}</TableCell>
                <TableCell>{row.caseType1.value}</TableCell>
                <TableCell>{row.caseType2.value}</TableCell>
                <TableCell>{row.caseType3.value}</TableCell>
                <TableCell>{row.statusCode.value}</TableCell>
                <TableCell>{row.comments}</TableCell>

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

export default MyCaseListComponent;


