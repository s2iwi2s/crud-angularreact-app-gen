import React from 'react';
import Typography from '@material-ui/core/Typography';
import { TextField, Select, MenuItem, Button, FormControl, InputLabel } from '@material-ui/core';

import CustomerService from '../../api/customer/CustomerService';

export default class CustomerDetailComponent extends React.Component {

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
    console.log(`[CustomerDetailComponent.retrieve] id==>${this.props.match.params.id}`)
    CustomerService.get(this.props.match.params.id)
      .then(response => {
        console.log(`[CustomerDetailComponent.retrieve] response==>`, response)
        let thestate = this.getBlankDetails();
		if (this.props.match.params.id > -1) {
			thestate = response.data.customer;
		}
        thestate.listService = response.data.listService
        this.setState(thestate)
      })
  }

  save = () => {
    console.log(`[CustomerDetailComponent.save] id==>${this.props.match.params.id}`)
    CustomerService.save({
		firstName: this.state.firstName,
		lastName: this.state.lastName,
		address1: this.state.address1,
		address2: this.state.address2,
		city: this.state.city,
		state: this.state.state,

		id: this.state.id
    }).then(response => {
      console.log(`[CustomerDetailComponent.save] response==>`, response)

      this.props.history.push(`/customer-list`);
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
        <Typography variant="h4">Customer Detail</Typography>
        <form>
          <FormControl fullWidth margin="normal">
			<InputLabel shrink id="firstName-label">First Name</InputLabel>
            <TextField labelId="firstName-label" 
              name="firstName" value={this.state.firstName}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
			<InputLabel shrink id="lastName-label">Last Name</InputLabel>
            <TextField labelId="lastName-label" 
              name="lastName" value={this.state.lastName}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
			<InputLabel shrink id="address1-label">Address 1</InputLabel>
            <TextField labelId="address1-label" 
              name="address1" value={this.state.address1}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
			<InputLabel shrink id="address2-label">Address 2</InputLabel>
            <TextField labelId="address2-label" 
              name="address2" value={this.state.address2}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
			<InputLabel shrink id="city-label">City</InputLabel>
            <TextField labelId="city-label" 
              name="city" value={this.state.city}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
			<InputLabel shrink id="state-label">State</InputLabel>
            <TextField labelId="state-label" 
              name="state" value={this.state.state}
              onChange={(e) => this.changeState(e)} />
          </FormControl>


          <Button variant="contained" color="primary" onClick={() => this.save()}>Save</Button>&nbsp;
          <Button variant="contained" color="primary" onClick={() => this.props.history.push(`/customer-list`)}>Cancel</Button>
        </form>

      </div >
    );
  }
}


