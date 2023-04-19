import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';

function Navber() {
  return (
    <Navbar collapseOnSelect expand="lg" bg="primary" variant="dark">
      <Container>
        <Navbar.Brand href="#home">Test-App</Navbar.Brand>
        
          <Nav>
            <Nav.Link href="Login">Login</Nav.Link>
            <Nav.Link href="Register">Register</Nav.Link>
          </Nav>
      </Container>
    </Navbar>
  );
}

export default Navber;