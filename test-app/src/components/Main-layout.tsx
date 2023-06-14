import React, { useState } from "react";
import { Outlet } from "react-router-dom";
import { useCookies } from "react-cookie";
import Navbar from "./Navbar";
import Card from 'react-bootstrap/Card';
function MainLayout() {

  const [cookies, setCookie] = useCookies(["email"]);

  const isCookieExpired = () => {
    try {
      const cookieExpiration = new Date(cookies.email.expires);
      const currentDate = new Date();
      if (currentDate > cookieExpiration) {
        return true;
      } else {
        return false;
      }
    } catch (ReferenceError) {
      return true;
    }
  };

  const [onLoginuser, setonLoginuser] = useState(
    !isCookieExpired()
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
