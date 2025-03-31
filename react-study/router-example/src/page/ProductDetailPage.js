import React from 'react'
import { useParams } from 'react-router-dom'

const ProductDetailPage = () => {
    const params = useParams();
    console.log("id : ", params.id);
    console.log("number : ", params.number);
  return (
    <h1>Show Product Detail {params.id}</h1>
  )
}

export default ProductDetailPage