import React, { useState } from "react";
import { Outlet } from "react-router-dom";

import Container from "react-bootstrap/Container";

import Navbar from "./Navbar";
import Card from 'react-bootstrap/Card';
function MainLayout() {
  const [onLoginuser, setonLoginuser] = useState(
    false
  );
  return (
    <div>
      <Navbar onLoginuser={onLoginuser} setonLoginuser={setonLoginuser}/>
      <Card style={{margin:20, padding:20, border:0}}>
        <Outlet context={[onLoginuser, setonLoginuser]}/>
      </Card>
    </div>
  );
};
export default MainLayout;
