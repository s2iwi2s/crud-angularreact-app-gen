import React from 'react';
import Typography from '@material-ui/core/Typography';
import { TextField, Select, MenuItem, Button, FormControl, InputLabel } from '@material-ui/core';

import ProductService from '../../api/product/ProductService';

export default class ProductDetailComponent extends React.Component {

  constructor(props) {
    super(props)
    this.state = this.getBlankDetails()
  }

  getBlankDetails = () => {
    return {
      "id": "",
      "itemCode": "",
      "description": "",
      "category": { id: "" },
      "price": "",
      "quantity": "",

      "listService": {
        "categoryList": []
      }
    }
  }

  componentDidMount = () => {
    this.retrieve();
  }

  retrieve = () => {
    console.log(`[ProductDetailComponent.retrieve] id==>${this.props.match.params.id}`)
    ProductService.get(this.props.match.params.id)
      .then(response => {
        console.log(`[ProductDetailComponent.retrieve] response==>`, response)
        let thestate = this.getBlankDetails();
        if (this.props.match.params.id > -1) {
          thestate = response.data.product;
        }
        thestate.listService = response.data.listService
        this.setState(thestate)
      })
  }

  save = () => {
    console.log(`[ProductDetailComponent.save] id==>${this.props.match.params.id}`)
    ProductService.save({
      itemCode: this.state.itemCode,
      description: this.state.description,
      category: this.state.category,
      price: this.state.price,
      quantity: this.state.quantity,

      id: this.state.id
    }).then(response => {
      console.log(`[ProductDetailComponent.save] response==>`, response)

      this.props.history.push(`/product-list`);
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
        <Typography variant="h4">Product Detail</Typography>
        <form>
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="itemCode-label">Item Code</InputLabel>
            <TextField labelId="itemCode-label"
              name="itemCode" value={this.state.itemCode}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="description-label">Description</InputLabel>
            <TextField labelId="description-label"
              name="description" value={this.state.description}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="category-label">Category</InputLabel>
            <Select labelId="category-label" placeholder="Enter Category"
              name="category" value={this.state.category.id}
              onChange={(e) => this.changeSelectState(e)}>
              {this.renderOptions(this.state.listService.categoryList)}
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="price-label">Price</InputLabel>
            <TextField labelId="price-label"
              name="price" value={this.state.price}
              onChange={(e) => this.changeState(e)} />
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="quantity-label">Quantity</InputLabel>
            <TextField labelId="quantity-label"
              name="quantity" value={this.state.quantity}
              onChange={(e) => this.changeState(e)} />
          </FormControl>


          <Button variant="contained" color="primary" onClick={() => this.save()}>Save</Button>&nbsp;
          <Button variant="contained" color="primary" onClick={() => this.props.history.push(`/product-list`)}>Cancel</Button>
        </form>

      </div >
    );
  }
}


