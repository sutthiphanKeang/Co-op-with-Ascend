import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { FormEvent, useEffect, useRef, useState } from "react";
import { useNavigate, useOutletContext } from "react-router-dom";

//yup and formik
import * as yup from "yup";
import { Formik, FormikHelpers } from "formik";

//i18n
import { useTranslation } from "react-i18next";

interface State {
  fname: string;
  lname: string;
  phone: string;
  email: string;
  password: string;
  terms: boolean;
}

const schema = yup.object().shape({
  fname: yup.string().required("error_fname"),
  lname: yup.string().required("error_lname"),
  phone: yup
    .string()
    .min(10, "error_phone_min")
    .max(10, "error_phone_max")
    .required("error_phone"),
  email: yup.string().email("error_email_valid").required("error_email"),
  password: yup
    .string()
    .min(4, "error_password_min")
    .required("error_password"),
  terms: yup.bool().required().oneOf([true], "error_agree"),
});

function Register() {
  //i18n
  const { t } = useTranslation();
  ///

  const [values, setValues] = useState({
    fname: "",
    lname: "",
    phone: "0",
    email: "",
    password: "",
    terms: false,
  });
  // const handleChange = (prop: keyof State) => (event: React.ChangeEvent<any>): void => {
  //     setValues({ ...values, [prop]: event.target.value });
  //   };
  const navigate = useNavigate();

  // const handleChange = (e: React.ChangeEvent<any>): void;
  //   <T = string | React.ChangeEvent<any>>(field: T): T extends React.ChangeEvent<any> ? void : (e: string | React.ChangeEvent<any>) => void;
  //   };

  // const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
  //   const form = e.currentTarget;
  //   if (form.checkValidity() === false) {
  //     e.preventDefault();
  //     console.log('จะร้องไห้',form);
  //     alert("5555");
  //     e.stopPropagation();
  //   }else{
  //     e.preventDefault();
  //     setValidated(true);
  //     alert("Register Succeed");
  //     var nameValue = form.getElementById("fname").value;
  //     console.log('จะร้องไห้',nameValue);
  //     // localStorage.setItem("user", JSON.stringify(values));
  //     // navigate("/Login");
  //   }
  // };

  const myhandleSubmit = (values: State) => {
    console.log(values);
    // setValues(values);
    alert("Register Succeed");
    localStorage.setItem("user", JSON.stringify(values));
    navigate("/Login");
  };

  return (
    <Formik
      validationSchema={schema}
      onSubmit={myhandleSubmit}
      initialValues={{
        fname: "",
        lname: "",
        phone: "",
        email: "",
        password: "",
        terms: false,
      }}
    >
      {({
        handleSubmit,
        handleChange,
        values,
        touched,
        isValid,
        errors,
      }) => (
        <Form noValidate onSubmit={handleSubmit}>
          <Row className="mb-5">
            <Form.Group as={Col} md="4" className="position-relative">
              <Form.Label>{t("fname")}</Form.Label>
              <Form.Control
                type="text"
                id="fname"
                value={values.fname}
                onChange={handleChange("fname")}
                isValid={touched.fname && !errors.fname}
                isInvalid={!!errors.fname}
              />
              <Form.Control.Feedback type="valid"></Form.Control.Feedback>
              <Form.Control.Feedback type="invalid" tooltip>
                {t(`${errors.fname}`)}
              </Form.Control.Feedback>
            </Form.Group>

            <Form.Group as={Col} md="4" className="position-relative">
              <Form.Label>{t("lname")}</Form.Label>
              <Form.Control
                type="text"
                id="lname"
                value={values.lname}
                onChange={handleChange("lname")}
                isValid={touched.lname && !errors.lname}
                isInvalid={!!errors.lname}
              />
              <Form.Control.Feedback type="valid"></Form.Control.Feedback>
              <Form.Control.Feedback type="invalid" tooltip>
                {t(`${errors.lname}`)}
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group as={Col} md="4" className="position-relative">
              <Form.Label>{t("phone")}</Form.Label>
              <Form.Control
                type="text"
                value={values.phone}
                onChange={handleChange("phone")}
                isValid={touched.phone && !errors.phone}
                isInvalid={!!errors.phone}
              />
              <Form.Control.Feedback type="valid"></Form.Control.Feedback>
              <Form.Control.Feedback type="invalid" tooltip>
                {t(`${errors.phone}`)}
              </Form.Control.Feedback>
            </Form.Group>
          </Row>
          <Form.Group
            style={{ marginBottom: "3rem" }}
            className="position-relative"
          >
            <Form.Label>{t("email")}</Form.Label>
            <Form.Control
              type="email"
              placeholder="name@example.com"
              value={values.email}
              onChange={handleChange("email")}
              isValid={touched.email && !errors.email}
              isInvalid={!!errors.email}
            />
            <Form.Control.Feedback type="valid"></Form.Control.Feedback>
            <Form.Control.Feedback type="invalid" tooltip>
              {t(`${errors.email}`)}
            </Form.Control.Feedback>
          </Form.Group>

          <Form.Group
            style={{ marginBottom: "3rem" }}
            className="position-relative"
          >
            <Form.Label>{t("Password")}</Form.Label>
            <Form.Control
              type="password"
              value={values.password}
              onChange={handleChange("password")}
              isValid={touched.password && !errors.password}
              isInvalid={!!errors.password}
              
            />
            <Form.Control.Feedback type="valid"></Form.Control.Feedback>
            <Form.Control.Feedback type="invalid" tooltip>
              {t(`${errors.password}`)}
            </Form.Control.Feedback>
          </Form.Group>
          <Form.Group className="position-relative mb-5">
            <Form.Check
              
              name="terms"
              label={t("agree")}
              onChange={handleChange("terms")}
              isInvalid={!!errors.terms}
              feedback={t(`${errors.terms}`)}
              feedbackType="invalid"
              feedbackTooltip
            />
          </Form.Group>
          <Button variant="primary" type="submit">
            {t("Submit")}
          </Button>
        </Form>
      )}
    </Formik>
  );
}

export default Register;
