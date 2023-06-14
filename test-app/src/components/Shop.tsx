import { useEffect, useState } from "react";
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

//i18n
import { useTranslation } from "react-i18next";

interface productIn {
  id: number;
  name: string;
  price: number;
}

interface productData {
  getAllProducts: productIn[];
}

function Shop() {
  //i18n
  const { t } = useTranslation();

  const [onLoginuser, setonLoginuser] = useOutletContext<any>();

  useEffect(() => {
    if (!onLoginuser) {
      navigate("/Login");
    }
  }, [onLoginuser]);

  const [cookies, setCookie] = useCookies(["email", "id"]);

  const [idProduct, setIdProduct] = useState(0);
  const [unit, setUnit] = useState(0);

  console.log("unit", unit);

  const navigate = useNavigate();

  const [showOrder, setShowOrder] = useState(false);
  const handleCloseOrder = () => setShowOrder(false);

  const product = gql`
    query getAllProducts {
      getAllProducts {
        id
        name
        price
      }
    }
  `;

  const createInvoice = gql`
    mutation createInvoice($userId: ID!) {
      createInvoice(userId: $userId) {
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

  const createCategory = gql`
  mutation CreateCategory($invoiceId: ID!, $productId: ID!, $unit: Int!) {
    createCategory(invoiceId: $invoiceId, productId: $productId, categoryDto: { unit: $unit }) {
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

  const { data: products } = useQuery<productData>(product);

  const handleClick = (id: number) => {
    setIdProduct(id);
    setShowOrder(true);
    console.log("id", id);
  };

  const [createInvoiceData] = useMutation(createInvoice);
  const [createCategoryData] = useMutation(createCategory);

  const handleSaveOrder = async () => {
    setShowOrder(false);
    const userId = cookies.id;
    try {
      const {data: createOrderInvoice} = await createInvoiceData({ variables: { userId: userId } })
      console.log("invoice", createOrderInvoice);

      const invoiceId = createOrderInvoice.createInvoice.id;

      const {data: createOrderCategory} = await createCategoryData({
        variables: {
          invoiceId: invoiceId,
          productId: idProduct,
          unit: unit,
        },
      });
      console.log("category", createOrderCategory);
      alert("Purchase order has been created, You can see the invoice in your account")
    }catch(error){
      console.error(error);
    }

  };

  console.log(products);
  return (
    <Stack gap={1} className="col-md-7 mx-auto">
      <Card>
        <Card.Header as="h5">{t("Shop")}</Card.Header>
        {products && (
          <Card.Body>
            <Form.Label className="mt-2">
              <b>{t("ProductList")}</b>
            </Form.Label>
            {products.getAllProducts.map((data) => (
              <ListGroup as="ol" key={data.id}>
                <ListGroup.Item
                  as="li"
                  className="d-flex justify-content-between align-items-start mt-2"
                  key={data.id}
                  action
                  onClick={() => handleClick(data.id)}
                >
                  <div className="ms-2 me-auto">
                    <div className="fw-bold">{t("ProductName")}: {data.name}</div>
                    <div className="fw-bold">{t("ProductPrice")}: {data.price}</div>
                    {t("ProductID")}: {data.id}
                  </div>
                </ListGroup.Item>
              </ListGroup>
            ))}
          </Card.Body>
        )}
      </Card>

      <>
        <Modal
          show={showOrder}
          onHide={handleCloseOrder}
          size="lg"
          aria-labelledby="contained-modal-title-vcenter"
          centered
        >
          <Modal.Header closeButton>
            <Modal.Title>{t("OrderInformation")}</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <b>{t("UserId")}:</b> {cookies.id}
          </Modal.Body>
          <Modal.Body>
            <b>{t("email")}:</b> {cookies.email}
          </Modal.Body>
          <Modal.Body>
            <b>{t("ProductID")}:</b> {idProduct}
          </Modal.Body>
          <Modal.Body>
            <Form>
              <Form.Group
                className="mb-3"
                controlId="exampleForm.ControlInput1"
              >
                <Form.Label>{t("Unit")}</Form.Label>
                <Form.Control
                  type="text"
                  autoFocus
                  onChange={(e) => setUnit(Number(e.target.value))}
                />
              </Form.Group>
            </Form>
          </Modal.Body>

          <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseOrder}>
            {t("Close")}
            </Button>
            <Button variant="primary" onClick={handleSaveOrder}>
            {t("ConfirmInformation")}
            </Button>
          </Modal.Footer>
        </Modal>
      </>
    </Stack>
  );
}

export default Shop;
