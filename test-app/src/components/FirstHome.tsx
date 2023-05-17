import axios from "axios";
import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Stack from "react-bootstrap/Stack";
import Card from "react-bootstrap/Card";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

interface State {
  name: string;
}
function Home() {
  const [Seach, setSeach] = useState({ name: "" });

  const reset = () => {
    setSeach({ name: "" });
  };

  const [data, setData] = useState("");

  const dataJson = JSON.stringify(data);
  let data_: string = dataJson;
  let jsonObj = JSON.parse(data_);

  const [image, setImage] = useState("");
  const handleSubmit = () => {
    axios({
      method: "GET",
      url: `https://pokeapi.co/api/v2/pokemon/${Seach.name}`,
    })
      .then((response) => {
        return response.data;
      })
      .then((data) => {
        setData(data);
        console.log(data.forms[0].url);
        var url = data.forms[0].url;
        axios({
          method: "GET",
          url: url,
        })
          .then((response) => {
            return response.data;
          })
          .then((data2) => {
            setImage(data2.sprites.front_default);
          })
          .catch((error) => {
            console.error("found error image", error);
          });
      })
      .catch((error) => {
        alert("Name of Pokemon Incorrect");
        console.error("found error name", error);
      });
  };

  const handleChange =
    (prop: keyof State) => (event: React.ChangeEvent<HTMLInputElement>) => {
      setSeach({ ...Seach, [prop]: event.target.value });
    };
  return (
    <>
      <Stack direction="horizontal" gap={3}>
        <Form.Control
          className="me-auto"
          onChange={handleChange("name")}
          placeholder="Add your name of pokemon here..."
        />
        <Button variant="secondary" onClick={handleSubmit}>
          Submit
        </Button>
        <div className="vr" />
        <Button variant="outline-danger" onClick={reset}>
          Reset
        </Button>
      </Stack>
      <br />
      {data != "" && (
        <Stack gap={1} className="col-md-4 mx-auto">
          <Card>
            <Card.Img variant="top" src={`${image}`} />

            <Card.Body>
              <Row>
                <Col>
                  <Card.Text>
                    <b>Name: </b>
                    {Seach.name}
                  </Card.Text>
                  <Card.Text>
                    <b>Ability 1: </b>
                    {jsonObj.abilities[0].ability.name}
                  </Card.Text>
                  <Card.Text>
                    <b>Ability 2: </b>
                    {jsonObj.abilities[1].ability.name}
                  </Card.Text>
                </Col>
                <Col>
                  <Card.Text>
                    <b>Base Experience: </b>
                    {jsonObj.base_experience}
                  </Card.Text>
                  <Card.Text>
                    <b>Height: </b>
                    {jsonObj.height}
                  </Card.Text>
                  <Card.Text>
                    <b>Weight: </b>
                    {jsonObj.weight}
                  </Card.Text>
                </Col>
              </Row>
              {/* <Card.Text>
            Some quick example text to build on the card title and make up the
            bulk of the card's content.
          </Card.Text> */}
            </Card.Body>
          </Card>
        </Stack>
      )}
    </>
  );
}
export default Home;
