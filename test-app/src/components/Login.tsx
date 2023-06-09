import React, { useEffect, useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { useNavigate, useOutletContext } from "react-router-dom";
import { useQuery} from "@apollo/client";

// import {userLogin} from "../graphql/queries.graphql"

//i18n
import { useTranslation } from "react-i18next";
import { on } from "events";
import { gql } from '@apollo/client';

////
interface State {
  email: string;
  password: string;
}


const userLogin = gql(/* GraphQL */`
query getAllUsers{
      id
      firstName
      lastName
      phoneNo
      email
      password
    }
`);

function Login() {
  //i18n
  const { t } = useTranslation();
  ///

  const { loading, error, data } = useQuery(userLogin);

  useEffect(() => {
    console.log(data)
  },[data])
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

  const[submit, setSubmit] = useState(false);
  const handleSubmit = (event: { preventDefault: () => void; }) => {
    event.preventDefault();
    // console.log(data);
    // console.log(error?.message);
    // if (error) {
    //   if (values.email === data.email && values.password === data.password) {
    //     alert("Login Succeed");
    //     setonLoginuser(true);
    //     navigate("/User");
    //   } else {
    //     alert("Please check your information again.");
    //   }
    // } else {
    //   alert(`Please login again! it's have Error! ${error}`);
    // }

  };

  return (
    <Form>
      <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
        <Form.Label>{t("email")}</Form.Label>
        <Form.Control
          type="email"
          placeholder="name@example.com"
          value={values.email}
          onChange={handleChange("email")}
        />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>{t("Password")}</Form.Label>
        <Form.Control
          type="password"
          value={values.password}
          onChange={handleChange("password")}
        />
      </Form.Group>
      <Button variant="primary" type="submit" onClick={handleSubmit}>
        {t("Submit")}
      </Button>
    </Form>
  );
}

export default Login;
