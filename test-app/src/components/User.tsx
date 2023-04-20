import { validateYupSchema } from 'formik';
import { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import { useNavigate } from 'react-router-dom';


function WithHeaderStyledExample() {
  const navigate = useNavigate();
  // console.log(localStorage.getItem('user'));
  const[values, setValues] = useState("");
  useEffect(() => {
    if(localStorage.getItem('user') == null){
      alert('Please Login Before')
      navigate('/Login');
    }
    else{
      setValues(localStorage.getItem('user') || "null");
    }
  }, [])
  
  
  console.log(values);
  
  return (
    <Card>
      <Card.Header as="h5">My account</Card.Header>
      <Card.Body>
        <Card.Title></Card.Title>
        <Card.Text>
          {/* {values.fname} */}
        </Card.Text>
        <Button variant="primary">Go somewhere</Button>
      </Card.Body>
    </Card>
  );
}

export default WithHeaderStyledExample;