import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from '../css/ProductCard.module.css';

const ProductCard = ({ item }) => {
  const navigate = useNavigate();
  const showDetail = () => {
    navigate(`/products/${item.id}`);
  };

  return (
    <div className={styles.productCard} onClick={showDetail}>
      <img src={item?.img} alt={item?.title} className={styles.productImage} />

      <div className={styles.badgeArea}>
        {item?.new && <span className={`${styles.cardBadge} ${styles.new}`}>신상품</span>}
        {item?.choice && <span className={`${styles.cardBadge} ${styles.choice}`}>Conscious choice</span>}
      </div>

      <div className={styles.productTitle}>{item?.title}</div>
      <div className={styles.productPrice}>₩{item?.price.toLocaleString()}</div>
    </div>
  );
};

export default ProductCard;
