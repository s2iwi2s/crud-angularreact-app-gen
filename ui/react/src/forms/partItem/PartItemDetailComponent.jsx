import React from 'react';
import Typography from '@material-ui/core/Typography';
import { TextField, Select, MenuItem, Button, FormControl, InputLabel } from '@material-ui/core';

import PartItemService from '../../api/partItem/PartItemService';

export default class PartItemDetailComponent extends React.Component {

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
    console.log(`[PartItemDetailComponent.retrieve] id==>${this.props.match.params.id}`)
    PartItemService.get(this.props.match.params.id)
      .then(response => {
        console.log(`[PartItemDetailComponent.retrieve] response==>`, response)
        let thestate = this.getBlankDetails();
		if (this.props.match.params.id > -1) {
			thestate = response.data.partItem;
		}
        thestate.listService = response.data.listService
        this.setState(thestate)
      })
  }

  save = () => {
    console.log(`[PartItemDetailComponent.save] id==>${this.props.match.params.id}`)
    PartItemService.save({
		name: this.state.name,
		description: this.state.description,
		serialized: this.state.serialized,

		id: this.state.id
    }).then(response => {
      console.log(`[PartItemDetailComponent.save] response==>`, response)

      this.props.history.push(`/part-item-list`);
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
        <Typography variant="h4">PartItem Detail</Typography>
        <form>
          <FormControl fullWidth margin="normal">
            <TextField label="Name" placeholder="Enter Name"
              name="name" value={this.state.name}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
            <TextField label="Description" placeholder="Enter Description"
              name="description" value={this.state.description}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
            <TextField label="Serialized" placeholder="Enter Serialized"
              name="serialized" value={this.state.serialized}
              onChange={(e) => this.changeState(e)} />
          </FormControl>


          <Button variant="contained" color="primary" onClick={() => this.save()}>Save</Button>&nbsp;
          <Button variant="contained" color="primary" onClick={() => this.props.history.push(`/part-item-list`)}>Cancel</Button>
        </form>

      </div >
    );
  }
}


