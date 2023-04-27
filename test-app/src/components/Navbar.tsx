import { useEffect } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";

import { useTranslation} from "react-i18next";

type props = {
  onLoginuser: boolean;
  setonLoginuser: (a: boolean) => void;
};



const lngs = {
  en: {nativeName: 'English'},
  th: {nativeName: 'Thai'}
}

function NavbarUser({ onLoginuser, setonLoginuser }: props) {
  //i18n
  const{t, i18n} = useTranslation();


  return (
    <Navbar collapseOnSelect expand="lg" bg="primary" variant="dark">
      <Container>
        <Navbar.Brand href="/">Test-App</Navbar.Brand>
        <Nav>
        {!onLoginuser && (
          <>
          <Nav.Link href="Login">{t('Login')}</Nav.Link>
          <Nav.Link href="Register">{t('Register')}</Nav.Link>
        
          </>
          
      )}
        
        
        {onLoginuser && (
          <>
            <Nav.Link href="User">{t('Account')}</Nav.Link>
            <Nav.Link
              href="Login"
              onClick={() => {
                setonLoginuser(false);
                alert("Logout Succeed");
              }}
            >
              {t('Logout')}
            </Nav.Link>
          </>
        )}
        <NavDropdown title={t('Language')} id="navbarScrollingDropdown">
              {Object.keys(lngs).map((lng) => (
                <NavDropdown.Item key = "{lng}" onClick={() => i18n.changeLanguage(lng)} disabled={i18n.resolvedLanguage === lng}>{lngs[lng as keyof typeof lngs].nativeName}</NavDropdown.Item>
              ))}
            </NavDropdown>
        </Nav>
        
      </Container>
    </Navbar>
  );
}

export default NavbarUser;
