import React from 'react';
import Typography from '@material-ui/core/Typography';
import { TextField, MenuItem, Button, FormControl, InputLabel } from '@material-ui/core';

import EndUserService from '../../api/endUser/EndUserService';
import AddressListComponent from '../address/AddressListComponent';

export default class EndUserDetailComponent extends React.Component {

  constructor(props) {
    super(props)
    this.state = this.getBlankDetails()
  }

  getBlankDetails = () => {
    return {
      "id": "",
      "firstName": '',
      "lastName": "",
      "address": []
    }
  }

  componentDidMount = () => {
    this.retrieve();
  }

  retrieve = () => {
    console.log(`[EndUserDetailComponent.retrieve] id==>${this.props.match.params.id}`)
    EndUserService.get(this.props.match.params.id)
      .then(response => {
        console.log(`[EndUserDetailComponent.retrieve] response==>`, response)
        let thestate = this.getBlankDetails();
        if (this.props.match.params.id > -1) {
          thestate = response.data.endUser;
        }
        thestate.listService = response.data.listService
        this.setState(thestate)
      })
  }

  save = () => {
    console.log(`[EndUserDetailComponent.save] id==>${this.props.match.params.id}`)
    EndUserService.save({
      firstName: this.state.firstName,
      lastName: this.state.lastName,
      address: this.state.address,

      id: this.state.id
    }).then(response => {
      console.log(`[EndUserDetailComponent.save] response==>`, response)

      this.props.history.push(`/end-user-list`);
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
  editAddress = (addressId, userId) => {
    this.props.history.push(`/address-detail/${addressId}/${userId}`);
  }

  render = () => {
    return (
      <div className="container">
        <Typography variant="h4">User Detail</Typography>
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


          <AddressListComponent endUserId={this.props.match.params.id} history={this.props.history} />
          <Button variant="contained" color="primary" onClick={() => this.save()}>Save</Button>&nbsp;
          <Button variant="contained" color="primary" onClick={() => this.editAddress(-1, this.state.id)}>Add Address</Button>&nbsp;
          <Button variant="contained" color="primary" onClick={() => this.props.history.push(`/end-user-list`)}>Cancel</Button>
        </form>

      </div >
    );
  }
}


