import React from 'react';
import Typography from '@material-ui/core/Typography';
import { TextField, Select, MenuItem, Button, FormControl, InputLabel } from '@material-ui/core';

import MyCaseService from '../../api/myCase/MyCaseService';

export default class MyCaseDetailComponent extends React.Component {

  constructor(props) {
    super(props)
    this.state = this.getBlankDetails()
  }

  getBlankDetails = () => {
    return {
      "id": '',
      "title": "",
      "status": { "id": '' },
      "caseType1": { "id": '' },
      "caseType2": { "id": '' },
      "caseType3": { "id": '' },
      "statusCode": { "id": '' },
      "comments": "",

      "listService": {
        "statusList": [],
        "caseType1List": [],
        "caseType2List": [],
        "caseType3List": [],
        "statusCodeList": []
      }
    }
  }

  componentDidMount = () => {
      this.retrieve();
  }

  retrieve = () => {
    console.log(`[MyCaseDetailComponent.retrieve] id==>${this.props.match.params.id}`)
    MyCaseService.get(this.props.match.params.id)
      .then(response => {
        console.log(`[MyCaseDetailComponent.retrieve] response==>`, response)
        let thestate = this.getBlankDetails();
		if (this.props.match.params.id > -1) {
			thestate = response.data.myCase;
		}
        thestate.listService = response.data.listService
        this.setState(thestate)
      })
  }

  save = () => {
    console.log(`[MyCaseDetailComponent.save] id==>${this.props.match.params.id}`)
    MyCaseService.save({
		title: this.state.title,
		status: this.state.status,
		caseType1: this.state.caseType1,
		caseType2: this.state.caseType2,
		caseType3: this.state.caseType3,
		statusCode: this.state.statusCode,
		comments: this.state.comments,

		id: this.state.id
    }).then(response => {
      console.log(`[MyCaseDetailComponent.save] response==>`, response)

      this.props.history.push(`/my-case-list`);
    })
  }

  changeState = (e) => {
    this.setState({
      [e.target.name]: e.target.value
    })
  }
  
  changeSelectState = (e) => {
    this.setState({
      [e.target.name]: { "id": e.target.value }
    })
  }

  renderOptions = (optionsList) => {
    return (optionsList.map(row => (
      <MenuItem key={row.id} value={row.id}>{row.description}</MenuItem>
    )))
  }

  render = () => {
    return (
      <div className="container">
        <Typography variant="h4">MyCase Detail</Typography>
        <form>
          <FormControl fullWidth margin="normal">
            <TextField label="Title" placeholder="Enter Title"
              name="title" value={this.state.title}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="status-label">Status</InputLabel>
            <Select labelId="status-label" placeholder="Enter Status"
              name="status" value={this.state.status.id}
              onChange={(e) => this.changeSelectState(e)}>
              {this.renderOptions(this.state.listService.statusList)}
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="caseType1-label">Case Type 1</InputLabel>
            <Select labelId="caseType1-label" placeholder="Enter Case Type 1"
              name="caseType1" value={this.state.caseType1.id}
              onChange={(e) => this.changeSelectState(e)}>
              {this.renderOptions(this.state.listService.caseType1List)}
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="caseType2-label">Case Type 2</InputLabel>
            <Select labelId="caseType2-label" placeholder="Enter Case Type 2"
              name="caseType2" value={this.state.caseType2.id}
              onChange={(e) => this.changeSelectState(e)}>
              {this.renderOptions(this.state.listService.caseType2List)}
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="caseType3-label">Case Type 3</InputLabel>
            <Select labelId="caseType3-label" placeholder="Enter Case Type 3"
              name="caseType3" value={this.state.caseType3.id}
              onChange={(e) => this.changeSelectState(e)}>
              {this.renderOptions(this.state.listService.caseType3List)}
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="statusCode-label">Status Code</InputLabel>
            <Select labelId="statusCode-label" placeholder="Enter Status Code"
              name="statusCode" value={this.state.statusCode.id}
              onChange={(e) => this.changeSelectState(e)}>
              {this.renderOptions(this.state.listService.statusCodeList)}
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal">
            <TextField label="Comments" placeholder="Enter Comments"
              name="comments" value={this.state.comments}
              onChange={(e) => this.changeState(e)} />
          </FormControl>


          <Button variant="contained" color="primary" onClick={() => this.save()}>Save</Button>&nbsp;
          <Button variant="contained" color="primary" onClick={() => this.props.history.push(`/my-case-list`)}>Cancel</Button>
        </form>

      </div >
    );
  }
}


