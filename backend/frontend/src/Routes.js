import React from "react";
import { Route, Switch } from "react-router-dom";
import TimeTest from "./components/TimeTest";
import ShoppingList from "./components/ShoppingList";
import RawText from "./components/RawText";
import Login from "./components/Login";


export default function Routes() {
    return (
      <Switch>
        <Route exact path="/">
            <TimeTest />
        </Route>
        <Route path="/handleliste">
            <ShoppingList />
        </Route>
        <Route path="/rawtext">
            <RawText />
        </Route>
        <Route path ="/login">
            <Login />
        </Route>
      </Switch>
    );
  }
