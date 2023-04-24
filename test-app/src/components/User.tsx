import { useEffect, useState } from "react";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import { useNavigate, useOutletContext } from "react-router-dom";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Stack from 'react-bootstrap/Stack';

//i18n
import { useTranslation} from "react-i18next";

interface State {
  fname: string;
  lname: string;
  phone: string;
  email: string;
  password: string;
}

function Account() {

  //i18n
  const{t} = useTranslation();
  ///

  const [show, setShow] = useState(false);
  const [onLoginuser, setonLoginuser] = useOutletContext<any>();
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
    <Stack gap={1} className="col-md-5 mx-auto">
      <Card>
        <Card.Header as="h5">{t('Account')}</Card.Header>
        <Card.Body>
          <div>
            <Card.Title style={{ display: "inline" }}>{t('fname')}</Card.Title>
            <span> : {values.fname}</span>
          </div>
          <div>
            <Card.Title style={{ display: "inline" }}>{t('lname')}</Card.Title>
            <span> : {values.lname}</span>
          </div>
          <div>
            <Card.Title style={{ display: "inline" }}>{t('phone')}</Card.Title>
            <span> : {values.phone}</span>
          </div>
          <div>
            <Card.Title style={{ display: "inline" }}>{t('email')}</Card.Title>
            <span> : {values.email}</span>
          </div>
          <br />
          <Button variant="warning" onClick={handleShow}>
            {t('Edit')}
          </Button>{" "}
          <Button variant="danger" onClick={handleDelete}>{t('Delete')}</Button>{" "}
          <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>{t('Edit')}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <Form>
                <Row className="mb-3">
                  <Form.Group as={Col} md="4">
                    <Form.Label>{t('fname')}</Form.Label>
                    <Form.Control
                      type="text"
                      defaultValue={values.fname}
                      onChange={handleChange("fname")}
                    />
                  </Form.Group>
                  <Form.Group as={Col} md="4">
                    <Form.Label>{t('lname')}</Form.Label>
                    <Form.Control
                      type="text"
                      defaultValue={values.lname}
                      onChange={handleChange("lname")}
                    />
                  </Form.Group>
                  <Form.Group as={Col} md="4">
                    <Form.Label>{t('phone')}</Form.Label>
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
                  <Form.Label>{t('email')}</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="name@example.com"
                    defaultValue={values.email}
                    onChange={handleChange("email")}
                  />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                  <Form.Label>{t('Password')}</Form.Label>
                  <Form.Control
                    type="password"
                    defaultValue={values.password}
                    onChange={handleChange("password")}
                  />
                </Form.Group>
              </Form>
            </Modal.Body>
            <Modal.Footer>
              <Button variant="secondary" onClick={handleClose}>
                {t('Close')}
              </Button>
              <Button variant="primary" onClick={handleSubmit}>
                {t('Save')}
              </Button>
            </Modal.Footer>
          </Modal>
        </Card.Body>
      </Card>
    </Stack>

  );
}

export default Account;
