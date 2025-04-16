import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from '../css/ProductCard.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart } from '@fortawesome/free-solid-svg-icons';

const BookCard = ({ item }) => {
  return (
    <div className="book-card" >
      <img src={item?.img} alt={item?.title} className="book-image" />

      <div className="book-detail" id={item?.id}>
        {item?.title}
        {item?.author}
        <div className="book-like">
          <FontAwesomeIcon icon={faHeart} />
        </div>
      </div>
    </div>
  );
};

export default BookCard;
