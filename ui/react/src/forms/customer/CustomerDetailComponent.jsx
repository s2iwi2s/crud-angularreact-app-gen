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
            <TextField label="First Name" placeholder="Enter First Name"
              name="firstName" value={this.state.firstName}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
            <TextField label="Last Name" placeholder="Enter Last Name"
              name="lastName" value={this.state.lastName}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
            <TextField label="Address 1" placeholder="Enter Address 1"
              name="address1" value={this.state.address1}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
            <TextField label="Address 2" placeholder="Enter Address 2"
              name="address2" value={this.state.address2}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
            <TextField label="City" placeholder="Enter City"
              name="city" value={this.state.city}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
            <TextField label="State" placeholder="Enter State"
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


