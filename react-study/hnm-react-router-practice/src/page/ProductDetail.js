import React, { useEffect, useState } from 'react';
import { Container, Row, Col, Button, Form } from 'react-bootstrap';
import { useParams } from 'react-router-dom';
import styles from '../css/ProductDetail.module.css';

const ProductDetail = () => {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [selectedSize, setSelectedSize] = useState('');

  const getProductDetail = async () => {
    let url = `https://my-json-server.typicode.com/InfraWhale/tech-practice/products/${id}`;
    // let url = `http://localhost:5000/products/${id}`;
    let response = await fetch(url);
    let data = await response.json();
    setProduct(data);
  };

  useEffect(() => {
    getProductDetail();
  }, []);

  const handleSizeChange = (e) => {
    setSelectedSize(e.target.value);
  };

  return (
    <Container className={styles.detailContainer}>
      <Row>
        <Col md={6} className={styles.productImage}>
          <img src={product?.img} alt={product?.title} />
        </Col>
        <Col md={6} className={styles.productInfo}>
          {product?.new && <span className={`${styles.detailBadge} ${styles.new}`}>신상품</span>}
          {product?.choice && <span className={`${styles.detailBadge} ${styles.choice}`}>사장님 추천</span>}
          <h2 className={styles.productTitle}>{product?.title}</h2>
          <p className={styles.productPrice}>₩ {product?.price?.toLocaleString()}</p>

          <Form.Group className={styles.sizeSelect}>
            <Form.Label>옵션</Form.Label>
            <Form.Select onChange={handleSizeChange} value={selectedSize}>
              <option value="">옵션을 선택하세요</option>
              {product?.size?.map((s, idx) => (
                <option key={idx} value={s}>{s}</option>
              ))}
            </Form.Select>
          </Form.Group>

          <Button
            variant="dark"
            className={styles.buyBtn}
            disabled={!selectedSize}
          >
            {selectedSize ? '구매하기' : '먼저 사이즈를 선택하세요'}
          </Button>
        </Col>
      </Row>
    </Container>
  );
};

export default ProductDetail;
