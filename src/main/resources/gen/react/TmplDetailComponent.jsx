import React from 'react';
import Typography from '@material-ui/core/Typography';
import { TextField, Select, MenuItem, Button, FormControl, InputLabel } from '@material-ui/core';

import XYCLASSYXService from '../../api/XYclassYX/XYCLASSYXService';

export default class XYCLASSYXDetailComponent extends React.Component {

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
    console.log(`[XYCLASSYXDetailComponent.retrieve] id==>${this.props.match.params.id}`)
    XYCLASSYXService.get(this.props.match.params.id)
      .then(response => {
        console.log(`[XYCLASSYXDetailComponent.retrieve] response==>`, response)
        let thestate = this.getBlankDetails();
		if (this.props.match.params.id > -1) {
			thestate = response.data.XYclassYX;
		}
        thestate.listService = response.data.listService
        this.setState(thestate)
      })
  }

  save = () => {
    console.log(`[XYCLASSYXDetailComponent.save] id==>${this.props.match.params.id}`)
    XYCLASSYXService.save({
XYdetail-setfieldsYX
		id: this.state.id
    }).then(response => {
      console.log(`[XYCLASSYXDetailComponent.save] response==>`, response)

      this.props.history.push(`/XYclass-kebabYX-list`);
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
        <Typography variant="h4">XYCLASSYX Detail</Typography>
        <form>
XYdetail-fieldsYX

          <Button variant="contained" color="primary" onClick={() => this.save()}>Save</Button>&nbsp;
          <Button variant="contained" color="primary" onClick={() => this.props.history.push(`/XYclass-kebabYX-list`)}>Cancel</Button>
        </form>

      </div >
    );
  }
}
