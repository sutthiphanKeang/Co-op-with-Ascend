import {
  JSXElementConstructor,
  ReactElement,
  ReactFragment,
  ReactPortal,
  useEffect,
  useState,
} from "react";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import { useNavigate, useOutletContext } from "react-router-dom";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Stack from "react-bootstrap/Stack";
import { useCookies } from "react-cookie";
import { useMutation, useQuery, gql } from "@apollo/client";
import ListGroup from "react-bootstrap/ListGroup";
import Badge from "react-bootstrap/Badge";

//i18n
import { useTranslation } from "react-i18next";
import { UUID } from "crypto";

interface InvoiceIn {
  id: number;
  user: {
    id: number;
    firstName: string;
    lastName: string;
    phoneNo: string;
    email: string;
    password: string;
  };
  status: boolean;
}

interface InvoiceData {
  getInvoiceByUserEmail: InvoiceIn[];
}

function Account() {
  //i18n
  const { t } = useTranslation();
  ///

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

  const [onLoginuser, setonLoginuser] = useOutletContext<any>();

  const [cookies, setCookie] = useCookies(["email", "id"]);

  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [showInvoice, setShowInvoice] = useState(false);
  const handleCloseInvoice = () => setShowInvoice(false);

  const navigate = useNavigate();

  const email = cookies.email;
  console.log("email_cookie", email);
  console.log(onLoginuser);

  useEffect(() => {
    if (!onLoginuser) {
      navigate("/Login");
    }
  }, [onLoginuser]);

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

  const updateUser = gql`
    mutation updateUser($id: ID!, $userDto: UserDto!) {
      updateUser(id: $id, userDto: $userDto) {
        id
        firstName
        lastName
        phoneNo
        email
      }
    }
  `;

  const deleteUser = gql`
    mutation DeleteUser($id: ID!) {
      deleteUser(id: $id)
    }
  `;

  const invoice = gql`
    query getInvoiceByUserEmail($email: String!) {
      getInvoiceByUserEmail(email: $email) {
        id
        user {
          id
          firstName
          lastName
          phoneNo
          email
          password
        }
        status
      }
    }
  `;

  const updateInvoice = gql`
    mutation UpdateInvoice($id: ID!, $invoiceDto: InvoiceDto!) {
      updateInvoice(id: $id, invoiceDto: $invoiceDto) {
        id
        user {
          id
          firstName
          lastName
          phoneNo
          email
          password
        }
        status
      }
    }
  `;

  const { data: userData } = useQuery(userLogin, {
    variables: { email },
  });

  console.log(userData);

  const { data: invoices } = useQuery<InvoiceData>(invoice, {
    variables: { email },
  });

  console.log(invoices);

  const [edit, setEdit] = useState({
    fname: "",
    lname: "",
    phone: "",
    email: "",
    password: "",
  });

  console.log(edit);

  const [updateUserData] = useMutation(updateUser);

  const handleUpdateUser = async () => {
    try {
      const id = userData.getUserByEmail.id ?? "";
      const password = userData.getUserByEmail.password ?? "";
      if (edit.password == password) {
        var fname = edit.fname;
        var lname = edit.lname;
        var phone = edit.phone;
        const { data: update } = await updateUserData({
          variables: {
            id: id,
            userDto: {
              firstName: fname,
              lastName: lname,
              phoneNo: phone,
              email: email,
              password: password,
            },
          },
        });
        alert("Edit Success");
        setShow(false);
        console.log(update.updateUser);
      } else {
        alert("Password Invalid");
      }
    } catch (error) {
      console.error(error);
    }
  };

  const [deleteUserMutation] = useMutation(deleteUser);

  const handleDeleteUser = () => {
    const id = userData.getUserByEmail.id ?? "";
    deleteUserMutation({
      variables: {
        id: id,
      },
    });
    alert("Delete account succeed");
    navigate("/Login");
  };

  const [invoiceId, setInvoiceId] = useState(0);
  const [updateInvoiceData] = useMutation(updateInvoice);

  const handleClick = (id: number) => {
    setInvoiceId(id);
    setShowInvoice(true);
    console.log("id", id);
  };

  const handleUpdateInvoice = async () => {
    try {
      const { data: invoiceUpdateData } = await updateInvoiceData({
        variables: {
          id: invoiceId,
          invoiceDto: {
            status: true,
          },
        },
      });
      alert("The invoice has been active.");
      console.log("invoiceUpdateData", invoiceUpdateData);
    } catch (error) {
      console.error(error);
    }
    setShowInvoice(false);
  };

  return (
    <Stack gap={1} className="col-md-7 mx-auto">
      <Card>
        <Card.Header as="h5">{t("Account")}</Card.Header>
        <Card.Body>
        <div>
            {cookies.id && (
              <>
                <Card.Title style={{ display: "inline" }}>
                  {t("UserId")}
                </Card.Title>
                <span> : {cookies.id}</span>
              </>
            )}
          </div>
          <div>
            {userData && userData.getUserByEmail && (
              <>
                <Card.Title style={{ display: "inline" }}>
                  {t("fname")}
                </Card.Title>
                <span> : {userData.getUserByEmail.firstName}</span>
              </>
            )}
          </div>
          <div>
            {userData && userData.getUserByEmail && (
              <>
                <Card.Title style={{ display: "inline" }}>
                  {t("lname")}
                </Card.Title>
                <span> : {userData.getUserByEmail.lastName}</span>
              </>
            )}
          </div>
          <div>
            {userData && userData.getUserByEmail && (
              <>
                <Card.Title style={{ display: "inline" }}>
                  {t("phone")}
                </Card.Title>
                <span> : {userData.getUserByEmail.phoneNo}</span>
              </>
            )}
          </div>
          <div>
            {userData && userData.getUserByEmail && (
              <>
                <Card.Title style={{ display: "inline" }}>
                  {t("email")}
                </Card.Title>
                <span> : {userData.getUserByEmail.email}</span>
              </>
            )}
          </div>
          <br />
          <Button variant="warning" onClick={handleShow}>
            {t("Edit")}
          </Button>{" "}
          <Button variant="danger" onClick={handleDeleteUser}>
            {t("Delete")}
          </Button>{" "}
          <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>{t("Edit")}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <Form>
                <Row className="mb-3">
                  <Form.Group as={Col} md="4">
                    <Form.Label>{t("fname")}</Form.Label>
                    <Form.Control
                      type="text"
                      defaultValue={
                        userData && userData.getUserByEmail
                          ? userData.getUserByEmail.firstName
                          : ""
                      }
                      value={edit.fname}
                      onChange={(e) =>
                        setEdit({ ...edit, fname: e.target.value })
                      }
                    />
                  </Form.Group>
                  <Form.Group as={Col} md="4">
                    <Form.Label>{t("lname")}</Form.Label>
                    <Form.Control
                      type="text"
                      defaultValue={
                        userData && userData.getUserByEmail
                          ? userData.getUserByEmail.lastName
                          : ""
                      }
                      value={edit.lname}
                      onChange={(e) =>
                        setEdit({ ...edit, lname: e.target.value })
                      }
                    />
                  </Form.Group>
                  <Form.Group as={Col} md="4">
                    <Form.Label>{t("phone")}</Form.Label>
                    <Form.Control
                      type="text"
                      defaultValue={
                        userData && userData.getUserByEmail
                          ? userData.getUserByEmail.phoneNo
                          : ""
                      }
                      value={edit.phone}
                      onChange={(e) =>
                        setEdit({ ...edit, phone: e.target.value })
                      }
                    />
                  </Form.Group>
                </Row>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                  <Form.Label>{t("Confirm Password")}</Form.Label>
                  <Form.Control
                    type="password"
                    onChange={(e) =>
                      setEdit({ ...edit, password: e.target.value })
                    }
                  />
                </Form.Group>
              </Form>
            </Modal.Body>
            <Modal.Footer>
              <Button variant="secondary" onClick={handleClose}>
                {t("Close")}
              </Button>
              <Button variant="primary" onClick={handleUpdateUser}>
                {t("Save")}
              </Button>
            </Modal.Footer>
          </Modal>
        </Card.Body>
      </Card>

      <br />

      {invoices && invoices.getInvoiceByUserEmail && (
        <Card>
          <Card.Header as="h5">{t("Invoice")}</Card.Header>

          {invoices.getInvoiceByUserEmail.map((data) => (
            <>
              <ListGroup as="ol" key={data.id}>
                <ListGroup.Item
                  as="li"
                  className="d-flex justify-content-between align-items-start"
                  key={data.id}
                  action
                  onClick={() => handleClick(data.id)}
                >
                  <div className="ms-2 me-auto">
                    <div className="fw-bold">{t("InvoiceID")}: {data.id}</div>
                  </div>
                  {!data.status && (
                    <Badge bg="danger" pill>
                      Not Active
                    </Badge>
                  )}
                  {data.status && (
                    <Badge bg="primary" pill>
                      Active
                    </Badge>
                  )}
                </ListGroup.Item>
              </ListGroup>
              <>
                <Modal
                  show={showInvoice}
                  onHide={handleCloseInvoice}
                  size="lg"
                  aria-labelledby="contained-modal-title-vcenter"
                  centered
                >
                  <Modal.Header closeButton>
                    <Modal.Title>{t("InvoiceInformation")}</Modal.Title>
                  </Modal.Header>
                  <Modal.Body>
                    <CategoriesComponent invoiceId={data.id.toString()} t={t} />
                  </Modal.Body>

                  <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseInvoice}>
                      {t("Close")}
                    </Button>
                    <Button variant="primary" onClick={handleUpdateInvoice}>
                      {t("ConfirmInformation")}
                    </Button>
                  </Modal.Footer>
                </Modal>
              </>
            </>
          ))}
        </Card>
      )}
    </Stack>
  );
}

interface Product {
  id: number;
  name: string;
  price: number;
}

interface User {
  id: number;
  firstName: string;
  lastName: string;
  phoneNo: string;
  email: string;
  password: string;
}

interface Invoice {
  id: number;
  user: User;
  status: boolean;
}

interface Category {
  id: UUID;
  unit: number;
  product: Product;
  invoice: Invoice;
}

interface GetCategoryByInvoiceIdData {
  getCategoryByInvoiceId: Category[];
}

interface CategoriesComponentProps {
  invoiceId: string;
  t: (key: string) => string;
}

const category = gql`
  query GetCategoryByInvoiceId($id: ID!) {
    getCategoryByInvoiceId(id: $id) {
      id
      unit
      product {
        id
        name
        price
      }
      invoice {
        id
        user {
          id
          firstName
          lastName
          phoneNo
          email
          password
        }
        status
      }
    }
  }
`;

const CategoriesComponent: React.FC<CategoriesComponentProps> = ({
  invoiceId,
  t,
}) => {
  const { loading, error, data } = useQuery<GetCategoryByInvoiceIdData>(
    category,
    {
      variables: {
        id: invoiceId,
      },
    }
  );

  if (loading) {
    return <p>Loading categories...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }

  return (
    <>
      {data?.getCategoryByInvoiceId.map((categoryData) => (
        <div key={categoryData.id}>
          <div><b>{t("CategoryID")}:</b> {categoryData.id}</div>
          <div><b>{t("ProductID")}:</b> {categoryData.product.id}</div>
          <div><b>{t("ProductName")}:</b> {categoryData.product.name}</div>
          <div><b>{t("ProductPrice")} / {t("Unit")}:</b> {categoryData.product.price}</div>
          <div><b>{t("Unit")}:</b> {categoryData.unit}</div>
        </div>
      ))}
    </>
  );
};

export default Account;
