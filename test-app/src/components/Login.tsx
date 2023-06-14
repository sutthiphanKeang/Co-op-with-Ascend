import React, { useEffect, useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { useNavigate, useOutletContext } from "react-router-dom";
import { useQuery, gql } from "@apollo/client";
import { useCookies } from "react-cookie";
//i18n
import { useTranslation } from "react-i18next";

////
interface State {
  email: string;
  password: string;
}

const userLogin = gql`
  query getUserByEmail($email: String!) {
    getUserByEmail(email: $email) {
      id
      firstName
      lastName
      phoneNo
      email
      password
    }
  }
`;

function Login() {
  //i18n
  const { t } = useTranslation();
  ///

  const navigate = useNavigate();

  const [cookies, setCookie] = useCookies(["email","id"]);


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

  console.log(isCookieExpired());
  const [onLoginuser, setonLoginuser] = useOutletContext<any>();

  useEffect(() => {
    if (!isCookieExpired()) {
      navigate("/User");
      setonLoginuser(true);
    }
  }, []);

  

  const [values, setValues] = React.useState<State>({
    email: "",
    password: "",
  });

  const handleChange =
    (prop: keyof State) => (event: React.ChangeEvent<HTMLInputElement>) => {
      setValues({ ...values, [prop]: event.target.value });
    };

  const email = values.email;
  const { loading, error, data } = useQuery(userLogin, {
    variables: { email },
  });

  const handleSubmit = (event: { preventDefault: () => void }) => {
    event.preventDefault();

    const expirationDate = new Date();
    expirationDate.setTime(expirationDate.getTime() + 60 * 60 * 1000);

    console.log(data);
    if (data) {
      if (
        values.email === data.getUserByEmail.email &&
        values.password === data.getUserByEmail.password
      ) {
        alert("Login Succeed");
        setonLoginuser(true);
        setCookie("email", email, { path: "/", expires: expirationDate });
        setCookie("id", data.getUserByEmail.id, { path: "/", expires: expirationDate });
        navigate("/User");
      } else {
        alert("Please check your information again.");
      }
    } else {
      alert(`Please login again! it's have Error! ${error}`);
    }
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
