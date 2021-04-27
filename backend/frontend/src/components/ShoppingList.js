import React from 'react';
import { withRouter } from "react-router";
import DropdownInput from './DropdownInput';
import firebase from '../initFirebase';

const shopNames = ["coop", "kolonialno", "meny", "spar"]

function sum(shopName, shoppingList) {
    let sum = 0;

    shoppingList.forEach(element => {
        sum += element.storePrice[shopName] * element.qty;

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
            { "name": "Chicken - Bones", "storePrice": { "meny": 8.38, "coop": 83.55, "spar": 85.94, "kolonialno": 10.86 }, "qty":1 },
            { "name": "Hot Choc Vending", "storePrice": { "meny": 72.73, "coop": 97.33, "spar": 4.04, "kolonialno": 39.27 }, "qty":1 },
            { "name": "Sauce - Chili", "storePrice": { "meny": null, "coop": 64.33, "spar": 75.58, "kolonialno": 48.5 }, "qty":1 },
            { "name": "Chicken - Ground", "storePrice": { "meny": 35.04, "coop": 18.42, "spar": 57.21, "kolonialno": 80.92 }, "qty":1 },
            { "name": "Grenadillo", "storePrice": { "meny": 97.76, "coop": 77.7, "spar": 42.26, "kolonialno": 47.05 }, "qty":1 },
            { "name": "Wine - White, Ej", "storePrice": { "meny": 28.91, "coop": 8.62, "spar": 16.63, "kolonialno": 65.84 }, "qty":1 }
        ]

        this.setState({
            shoppingList: mockList
        })

        this.unregisterAuthObserver = firebase.auth().onAuthStateChanged(
            (_user) => {
                this.setState({user:_user});
                console.log("user: ", _user);
            }
        )


    }

    componentWillUnmount(){
        this.unregisterAuthObserver();
    }

    addProduct = (product) => {
        this.state.shoppingList.push(product); 
        console.log(this.state.shoppingList)
        this.setState({shoppingList: this.state.shoppingList});
    }

    updateQty(product, val) {
        this.state.shoppingList.forEach( (prod) => {
            if(prod['name'] === product['name']) {
                if(prod.qty + val >= 0)
                prod.qty += val;
            }
        })
        this.setState({shoppingList: this.state.shoppingList});
    }


    render() {
        return (
            <div className="container">
                <div className="row justify-content-around">
                    <div className="col-xs-6 ">
                        <h1>Lag handleliste </h1>

                        <DropdownInput onAddProduct={this.addProduct} />
                        
                        <table className="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <td>Antall</td>
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
                                                    <td>
                                                        <button className="btn btn-sm" onClick = {() => this.updateQty(product, 1)}>+</button>
                                                        {product.qty}
                                                        <button className="btn btn-sm" onClick = {() => this.updateQty(product, -1)}>-</button>
                                                    </td>
                                                    <td> {product.name}</td>
                                                    {
                                                        shopNames.map(shopName => {
                                                            if(product.storePrice[shopName] !== null)
                                                            return (<td>{(product.storePrice[shopName] * product.qty).toFixed(2)}</td>);
                                                            else 
                                                            return (<td></td>)
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
                                    <td></td>
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
                        {this.state.user ?
                            <button className="btn btn-primary" 
                            onClick={() => {
                                    //const curruser = userref.doc(this.state.user);
                                    firebase.firestore().collection("users").doc(this.state.user.uid).set({}, {merge:true})
                                    .then(()=> {
                                        const shoppinglistref = firebase.firestore().collection("users").doc(this.state.user.uid).collection("shoppingLists");
                                        console.log(shoppinglistref);
                                        shoppinglistref.add({
                                        'shoppinglist': this.state.shoppingList,
                                        'userid': this.state.user.uid,
                                        'date': new Date()
                                        });
                                    })
                                    
                                }
                            }
                            >Lagre Handleliste</button>

                            :
                            <div></div>

                            }
                            
                            
                        </div>
                    </div>
                </div >
            ) 
    }
}

export default withRouter(ShoppingList)