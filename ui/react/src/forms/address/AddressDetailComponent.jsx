import React from 'react';
import Typography from '@material-ui/core/Typography';
import { TextField, MenuItem, Button, FormControl, InputLabel } from '@material-ui/core';

import AddressService from '../../api/address/AddressService';
import EndUserService from '../../api/endUser/EndUserService';

export default class AddressDetailComponent extends React.Component {

  constructor(props) {
    super(props)
    this.state = this.getBlankDetails()
  }

  getBlankDetails = () => {
    return {
      "id": '',
      "endUserId": '',
      "endUser": { id: "", firstName: "", lastName: "" },
      "name": "",
      "address1": "",
      "address2": "",
      "city": "",
      "state": "",
      "country": "",
      "zipCode": "",
      "billTo": "",
      "shipTo": ""
    }
  }

  componentDidMount = () => {
    this.retrieve();
  }

  retrieve = () => {
    console.log(`[AddressDetailComponent.retrieve] id=${this.props.match.params.id}, endUserId=${this.props.match.params.endUserId}`);
    let thestate = this.getBlankDetails();
    if (this.props.match.params.id < 1 && this.props.match.params.endUserId > 0) {
      AddressService.getByEndUser(this.props.match.params.endUserId)
        .then(response => {
          console.log(`[AddressDetailComponent.retrieve] response=>`, response);
          thestate = response.data.address;
          thestate.endUserId = thestate.endUser.id
          thestate.listService = response.data.listService
          this.setState(thestate)
        })
    } else {
      AddressService.get(this.props.match.params.id)
        .then(response => {
          thestate = response.data.address;
          thestate.endUserId = thestate.endUser.id
          thestate.listService = response.data.listService
          this.setState(thestate)
        })
    }
  }


  save = () => {
    AddressService.save({
      endUser: {
        id: this.state.endUserId
      },
      name: this.state.name,
      address1: this.state.address1,
      address2: this.state.address2,
      city: this.state.city,
      state: this.state.state,
      country: this.state.country,
      zipCode: this.state.zipCode,
      billTo: this.state.billTo,
      shipTo: this.state.shipTo,

      id: this.state.id
    }).then(response => {
      let routeUrl = `/address-list`;
      let endUserId = this.props.match.params.endUserId
      if (endUserId > 0) {
        routeUrl = `/end-user-detail/${endUserId}`
      }
      this.props.history.push(routeUrl);
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

  cancel = () => {
    if (this.props.match.params.endUserId > 0) {
      this.props.history.push(`/end-user-detail/${this.props.match.params.endUserId}`)
    } else {
      this.props.history.push(`/address-list`)
    }
  }
  render = () => {
    return (
      <div className="container">
        <Typography variant="h4">Address Detail</Typography>
        <form>
          <FormControl fullWidth margin="normal">
            <span>End User: {this.state.endUser.lastName}, {this.state.endUser.firstName}</span>
            <input name="endUserId" type="hidden" value={this.state.endUserId} />

          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="itemCode-label">Name</InputLabel>
            <TextField labelId="itemCode-label"
              name="name" value={this.state.name}
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
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="country-label">Country</InputLabel>
            <TextField labelId="country-label"
              name="country" value={this.state.country}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="zipCode-label">Zip</InputLabel>
            <TextField labelId="zipCode-label"
              name="zipCode" value={this.state.zipCode}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="billTo-label">Is Billing?</InputLabel>
            <TextField labelId="billTo-label"
              name="billTo" value={this.state.billTo}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="shipTo-label">Is Shipping?</InputLabel>
            <TextField labelId="shipTo-label"
              name="shipTo" value={this.state.shipTo}
              onChange={(e) => this.changeState(e)} />
          </FormControl>


          <Button variant="contained" color="primary" onClick={() => this.save()}>Save</Button>&nbsp;
          <Button variant="contained" color="primary" onClick={() => this.cancel()}>Cancel</Button>
        </form>

      </div >
    );
  }
}


