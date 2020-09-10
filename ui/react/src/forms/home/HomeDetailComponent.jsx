import React from 'react';
import Typography from '@material-ui/core/Typography';
import { TextField, Button } from '@material-ui/core';

import HomeService from '../../api/home/HomeService';

export default class HomeDetailComponent extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      id: this.props.match.params.id,
      name: '',
      urlStr: ''
    }
  }
  componentDidMount = () => {
    if (this.props.match.params.id !== -1) {
      this.retrieve();
    } else {
      this.setState({
        id: '',
        name: '',
        urlStr: ''
      })
    }
  }
  retrieve = () => {
    console.log(`[HomeDetailComponent.retrieve] id==>${this.state.id}`)
    HomeService.get(this.state.id)
      .then(response => {
        console.log(`[HomeDetailComponent.retrieve] response==>`, response)
        this.setState({
          id: response.data.home.id,
          name: response.data.home.name,
          urlStr: response.data.home.urlStr
        })
      })
  }

  save = () => {
    console.log(`[HomeDetailComponent.save] id==>${this.state.id}`)
    HomeService.save({
      id: this.state.id,
      name: this.state.name,
      urlStr: this.state.urlStr
    }).then(response => {
      console.log(`[HomeDetailComponent.save] response==>`, response)

      this.props.history.push(`/home`);
    })
  }

  render = () => {

    return (
      <div className="container">
        <Typography variant="h4">Home Detail</Typography>
        <form>
          <input type="hidden" name="id" value={this.state.id} />
          <div>
            <TextField margin="normal" fullWidth placeholder="Enter Name" label="Name" name="name" value={this.state.name} onChange={(e) => this.setState({
              name: e.target.value
            })} />
          </div>
          <div>
            <TextField margin="normal" fullWidth placeholder="Enter URL" label="URL" name="urlStr" value={this.state.urlStr} onChange={(e) => this.setState({
              urlStr: e.target.value
            })} />
          </div>



          <Button variant="contained" color="primary" onClick={() => this.save()}>Save</Button>&nbsp;
          <Button variant="contained" color="primary" onClick={() => this.props.history.push(`/home`)}>Cancel</Button>
        </form>

      </div >
    );
  }
}
