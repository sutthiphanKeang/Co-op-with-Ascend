import React from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { useNavigate, useOutletContext } from "react-router-dom";

interface State {
  email: string;
  password: string;
}

function Login() {
  const navigate = useNavigate();

  const [onLoginuser, setonLoginuser] = useOutletContext<any>();

  const [values, setValues] = React.useState<State>({
    email: "",
    password: "",
  });

  const handleChange =
    (prop: keyof State) => (event: React.ChangeEvent<HTMLInputElement>) => {
      setValues({ ...values, [prop]: event.target.value });
    };

  const handleSubmit = () => {
    if (localStorage.getItem("user") !== null) {
      const email = JSON.parse(
        localStorage.getItem("user") ?? ' { "email": "" }'
      ).email;
      const password = JSON.parse(
        localStorage.getItem("user") ?? ' { "password": "" }'
      ).password;
      if (values.email == email && values.password == password) {
        alert("Login Succeed");
        setonLoginuser(true);
        navigate("/User");
      }
      else{
        alert("Please check your information again.");
      }
    } else {
      alert("Please login again or Register before login");
    }
  };

  return (
    <Form>
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

export default Login;
