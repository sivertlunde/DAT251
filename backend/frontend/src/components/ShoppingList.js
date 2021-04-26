import React from 'react';
import { withRouter } from "react-router";
import AsyncSelect from "react-select/async"
import ProductService from '../services/ProductService';
import DropdownInput from './DropdownInput';

const shopNames = ["coop", "kolonialno", "meny", "spar"]

function sum(shopName, shoppingList) {
    let sum = 0;

    shoppingList.forEach(element => {
        sum += element.prices[shopName];

    });

    return Math.round(sum * 100) / 100;
}

//const PromiseOptions = input => ProductService.getProducts(input);


class ShoppingList extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            shoppingList: []
        };
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

    onInputchange() {
        
    }



    render() {
        return (
            <div className="container">
                <div className="row justify-content-around">
                    <div className="col-xs-6 ">
                        <h1>Lag handleliste </h1>

                        <DropdownInput/>
                        
                        <table className="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <td>Produktnavn</td>
                                    {
                                        shopNames.map(shopName => {
                                            return <td><img width="60" alt={shopName} src={"/shopLogos/" + shopName + ".png"}></img></td>;
                                        })
                                    }
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.shoppingList ?
                                    this.state.shoppingList.map(
                                        (product) => {
                                            return (
                                                <tr key={product.name}>
                                                    <td> {product.name}</td>
                                                    {
                                                        shopNames.map(shopName => {
                                                            return (<td>{product.prices[shopName]}</td>);
                                                        })
                                                    }
                                                    <td><button className="btn btn-danger btn-sm" onClick={() => {
                                                        this.setState({ shoppingList: this.state.shoppingList.filter(event => event.name !== product.name) })
                                                    }

                                                    }>-</button></td>
                                                </tr>
                                            )
                                        })
                                    :
                                    <div></div>
                                }
                            </tbody>
                            <tfoot>
                                <tr >
                                    <td> Sum: </td>
                                    {
                                        // finds the sum of products for each shop
                                        shopNames.map(shopName => {
                                            return (<td> {sum(shopName, this.state.shoppingList)}</td>);
                                        })
                                    }
                                    <td></td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div >
        )
    }
}

export default withRouter(ShoppingList)