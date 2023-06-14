import React from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";

import MainLayout from "./components/Main-layout";
import Login from "./components/Login";
import Register from "./components/Register";
import User from "./components/User";
import FirstHome from "./components/FirstHome";
import Shop from "./components/Shop";

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainLayout/>}>
      <Route path="" element={<FirstHome/>}/>
        <Route path="Login" element={<Login/>}/>
        <Route path="Register" element={<Register/>}/>
        <Route path="User" element={<User/>}/>
        <Route path="Shop" element={<Shop/>}/>
      </Route>
    </Routes>
  );
}

export default App;
