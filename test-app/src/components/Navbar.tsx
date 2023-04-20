import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";

type props = {
  onLoginuser: boolean;
  setonLoginuser: (a: boolean) => void;
};

function NavbarUser({ onLoginuser, setonLoginuser }: props) {
  console.log(onLoginuser);
  return (
    <Navbar collapseOnSelect expand="lg" bg="primary" variant="dark">
      <Container>
        <Navbar.Brand href="#home">Test-App</Navbar.Brand>

        {!onLoginuser && (
          <Nav>
            <Nav.Link href="Login">Login</Nav.Link>
            <Nav.Link href="Register">Register</Nav.Link>
          </Nav>
        )}
        {onLoginuser && (
          <Nav>
            <Nav.Link href="User">My Account</Nav.Link>
            <Nav.Link
              href="Login"
              onClick={() => {
                setonLoginuser(false);
                alert("Logout Succeed");
              }}
            >
              Logout
            </Nav.Link>
          </Nav>
        )}
      </Container>
    </Navbar>
  );
}

export default NavbarUser;
