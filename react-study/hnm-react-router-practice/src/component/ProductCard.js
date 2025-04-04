import React from 'react'
import '../css/ProductCard.css'
import { useNavigate } from 'react-router-dom';

const ProductCard = ({ item }) => {
  const navigate = useNavigate();
  const showDetail= () => {
    navigate(`/products/${item.id}`);
  }

  return (
    <div className="product-card" onClick={showDetail}>
        <img src={item?.img} />
        {/* \u00A0 : No Break Space(NBSP)*/}
        <div>{item?.choice === true ? "Conscious choice" : "\u00A0"}</div> 
        <div>{item?.title}</div>
        <div>₩{item?.price}</div>
        <div>{item?.new === true ? "신제품" : "\u00A0"} </div>
    </div>
  )
}

export default ProductCard