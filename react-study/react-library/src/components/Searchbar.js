import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Searchbar = () => {

    return (
        <div className="search-bar">
            <h1> 코딩알려주는 누나 도서관 </h1>
            <div className="search-area">
                <input type="text" placeholder="책 제목이나 작가를 검색하세요"/>
                <div>검색</div>
            </div>
        </div>
    );
};

export default Searchbar;
