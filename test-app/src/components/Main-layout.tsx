import React, { useState } from "react";
import { Outlet } from "react-router-dom";

import Container from "react-bootstrap/Container";

import Navbar from "./Navbar";
import Card from 'react-bootstrap/Card';
function MainLayout() {
  return (
    <div>
      <Navbar/>
      <Card style={{margin:20, padding:20}}>
        <Outlet/>
      </Card>
    </div>
  );
};
export default MainLayout;
