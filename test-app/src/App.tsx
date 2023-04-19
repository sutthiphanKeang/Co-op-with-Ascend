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

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainLayout/>}>
      <Route path="" element={<Login/>}/>
        <Route path="Login" element={<Login/>}/>
        <Route path="Register" element={<Register/>}/>
      </Route>
    </Routes>
  );
}

export default App;
