import { display, margin } from "@mui/system";
import { validateYupSchema } from "formik";
import { useEffect, useState } from "react";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import { useNavigate, useOutletContext } from "react-router-dom";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Alert from 'react-bootstrap/Alert';


interface State {
  fname: string;
  lname: string;
  phone: string;
  email: string;
  password: string;
}

function Account() {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const navigate = useNavigate();
  // console.log(localStorage.getItem('user'));
  const [values, setValues] = useState({
    fname: "",
    lname: "",
    phone: "",
    email: "",
    password: "",
  });
  useEffect(() => {
    if (localStorage.getItem("user") == null) {
      alert("Please Login Before");
      navigate("/Login");
    } else {
      setValues({
        fname: JSON.parse(localStorage.getItem("user") ?? ' { "fname": "" }')
          .fname,
        lname: JSON.parse(localStorage.getItem("user") ?? ' { "lname": "" }')
          .lname,
        phone: JSON.parse(localStorage.getItem("user") ?? ' { "phone": "" }')
          .phone,
        email: JSON.parse(localStorage.getItem("user") ?? ' { "email": "" }')
          .email,
        password: JSON.parse(
          localStorage.getItem("user") ?? ' { "password": "" }'
        ).password,
      });
    }
  }, []);

  console.log(values);

  const [edit, setEdit] = useState({
    fname: "",
    lname: "",
    phone: "",
    email: "",
    password: "",
  });

  const handleChange =
    (prop: keyof State) => (event: React.ChangeEvent<HTMLInputElement>) => {
      setEdit({ ...values, [prop]: event.target.value });
    };
  
  const [onLoginuser, setonLoginuser] = useOutletContext<any>();
  const handleDelete = () => {
    alert("Delete account succeed");
    navigate("/Login");
    setonLoginuser(false);
    localStorage.clear();
  };

  const handleSubmit = () => {
    setValues({
      fname: edit.fname,
      lname: edit.lname,
      phone: edit.phone,
      email: edit.email,
      password: edit.password,
    });
    localStorage.setItem("user", JSON.stringify(edit));
    setShow(false)
  };

  return (
    <Card>
      <Card.Header as="h5">My account</Card.Header>
      <Card.Body>
        <div>
          <Card.Title style={{ display: "inline" }}>First Name</Card.Title>
          <span> : {values.fname}</span>
        </div>
        <div>
          <Card.Title style={{ display: "inline" }}>Last Name</Card.Title>
          <span> : {values.lname}</span>
        </div>
        <div>
          <Card.Title style={{ display: "inline" }}>Phone No.</Card.Title>
          <span> : {values.phone}</span>
        </div>
        <div>
          <Card.Title style={{ display: "inline" }}>E-mail</Card.Title>
          <span> : {values.email}</span>
        </div>
        <br />
        <Button variant="warning" onClick={handleShow}>
          Edit Account
        </Button>{" "}
        <Button variant="danger" onClick={handleDelete}>Delete Account</Button>{" "}
        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Edit Account</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form>
              <Row className="mb-3">
                <Form.Group as={Col} md="4">
                  <Form.Label>First name</Form.Label>
                  <Form.Control
                    type="text"
                    defaultValue={values.fname}
                    onChange={handleChange("fname")}
                  />
                </Form.Group>
                <Form.Group as={Col} md="4">
                  <Form.Label>Last name</Form.Label>
                  <Form.Control
                    type="text"
                    defaultValue={values.lname}
                    onChange={handleChange("lname")}
                  />
                </Form.Group>
                <Form.Group as={Col} md="4">
                  <Form.Label>Phone No.</Form.Label>
                  <Form.Control
                    type="text"
                    defaultValue={values.phone}
                    onChange={handleChange("phone")}
                  />
                </Form.Group>
              </Row>
              <Form.Group
                className="mb-3"
                controlId="exampleForm.ControlInput1"
              >
                <Form.Label>Email address</Form.Label>
                <Form.Control
                  type="email"
                  placeholder="name@example.com"
                  defaultValue={values.email}
                  onChange={handleChange("email")}
                />
              </Form.Group>

              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type="password"
                  placeholder="Password"
                  defaultValue={values.password}
                  onChange={handleChange("password")}
                />
              </Form.Group>
            </Form>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
            <Button variant="primary" onClick={handleSubmit}>
              Save Changes
            </Button>
          </Modal.Footer>
        </Modal>
      </Card.Body>
    </Card>
  );
}

export default Account;
