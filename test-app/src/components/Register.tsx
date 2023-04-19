import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { useEffect, useState } from "react";
import { useNavigate, useOutletContext } from "react-router-dom";

interface State {
  fname: string;
  lname: string;
  phone: string;
  email: string;
  password: string;
}

function Register() {
  const [values, setValues] = useState({
    fname: "",
    lname: "",
    phone: "",
    email: "",
    password: "",
  });
  const handleChange =
    (prop: keyof State) => (event: React.ChangeEvent<HTMLInputElement>) => {
      setValues({ ...values, [prop]: event.target.value });
    };
  const navigate = useNavigate();
  const handleSubmit = () => {
    localStorage.setItem("user", JSON.stringify(values));
    alert("Register Succeed");
    navigate("/Login");
    
  };
  return (
    <Form>
      <Row className="mb-3">
        <Form.Group as={Col} md="4">
          <Form.Label>First name</Form.Label>
          <Form.Control
            type="text"
            value={values.fname}
            onChange={handleChange("fname")}
          />
        </Form.Group>
        <Form.Group as={Col} md="4">
          <Form.Label>Last name</Form.Label>
          <Form.Control
            type="text"
            value={values.lname}
            onChange={handleChange("lname")}
          />
        </Form.Group>
        <Form.Group as={Col} md="4">
          <Form.Label>Phone No.</Form.Label>
          <Form.Control
            type="text"
            value={values.phone}
            onChange={handleChange("phone")}
          />
        </Form.Group>
      </Row>
      <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
        <Form.Label>Email address</Form.Label>
        <Form.Control
          type="email"
          placeholder="name@example.com"
          value={values.email}
          onChange={handleChange("email")}
        />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control
          type="password"
          placeholder="Password"
          value={values.password}
          onChange={handleChange("password")}
        />
      </Form.Group>
      <Button variant="primary" type="submit" onClick={handleSubmit}>
        Submit
      </Button>
    </Form>
  );
}

export default Register;
