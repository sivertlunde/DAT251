import React from 'react';
import { withRouter } from "react-router";
import ProductService from '../services/ProductService';


class RawText extends React.Component {
  constructor(props) {
  super(props);
  this.state = {
    shoppingList: [],
    wall: '',
    items: []
  };
  this.handleChange = this.handleChange.bind(this)
  this.handleSubmit = this.handleSubmit.bind(this)
}

componentDidMount() {

    const mockList = [
        { "name": "Chicken - Bones", "prices": { "meny": 8.38, "coop": 83.55, "spar": 85.94, "kolonialno": 10.86 } },
        { "name": "Hot Choc Vending", "prices": { "meny": 72.73, "coop": 97.33, "spar": 4.04, "kolonialno": 39.27 } },
        { "name": "Sauce - Chili", "prices": { "meny": null, "coop": 64.33, "spar": 75.58, "kolonialno": 48.5 } },
        { "name": "Chicken - Ground", "prices": { "meny": 35.04, "coop": 18.42, "spar": 57.21, "kolonialno": 80.92 } },
        { "name": "Grenadillo", "prices": { "meny": 97.76, "coop": 77.7, "spar": 42.26, "kolonialno": 47.05 } },
        { "name": "Wine - White, Ej", "prices": { "meny": 28.91, "coop": 8.62, "spar": 16.63, "kolonialno": 65.84 } }
    ]

    this.setState({
        shoppingList: mockList
    })
}

handleSubmit(event) {
  event.preventDefault();
  
  if (this.state.wall !== '') {
    ProductService.productsFromRawtext(this.state.wall).then(products => {
        this.setState({
          shoppingList: products
        })

    })
  }
}

handleChange(event) {
  this.setState({
    wall: event.target.value
  });
}

render() {
  return (
    <form onSubmit={this.handleSubmit}>
      <label>
        Add one item per line
        <textarea value={this.state.wall} onChange={this.handleChange} />
      </label>
      <input type="submit" value="Submit" />
    </form>
  );
}
}

export default withRouter(RawText)