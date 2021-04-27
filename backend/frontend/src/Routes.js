import React from "react";
import { Route, Switch } from "react-router-dom";
import TimeTest from "./components/TimeTest";
import ShoppingList from "./components/ShoppingList";
import Login from "./components/Login";


const mockList = [
  { "name": "Chicken - Bones", "storePrice": { "meny": 8.38, "coop": 83.55, "spar": 85.94, "kolonialno": 10.86 }, "qty":1 },
  { "name": "Hot Choc Vending", "storePrice": { "meny": 72.73, "coop": 97.33, "spar": 4.04, "kolonialno": 39.27 }, "qty":1 },
  { "name": "Sauce - Chili", "storePrice": { "meny": null, "coop": 64.33, "spar": 75.58, "kolonialno": 48.5 }, "qty":1 },
  { "name": "Chicken - Ground", "storePrice": { "meny": 35.04, "coop": 18.42, "spar": 57.21, "kolonialno": 80.92 }, "qty":1 },
  { "name": "Grenadillo", "storePrice": { "meny": 97.76, "coop": 77.7, "spar": 42.26, "kolonialno": 47.05 }, "qty":1 },
  { "name": "Wine - White, Ej", "storePrice": { "meny": 28.91, "coop": 8.62, "spar": 16.63, "kolonialno": 65.84 }, "qty":1 }
]

export default function Routes() {
    return (
      <Switch>
        <Route exact path="/">
            <TimeTest /> 
        </Route>
        <Route path="/handleliste">
            <ShoppingList products={mockList} /> 
        </Route>
        <Route path ="/login">
            <Login />
        </Route>
      </Switch>
    );
  }
  