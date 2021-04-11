import React from "react";
import { Route, Switch } from "react-router-dom";
import TimeTest from "./components/TimeTest";
import ShoppingList from "./components/ShoppingList";


export default function Routes() {
    return (
      <Switch>
        <Route exact path="/">
            <TimeTest /> 
        </Route>
        <Route path="/handleliste">
            <ShoppingList /> 
        </Route>
      </Switch>
    );
  }
  