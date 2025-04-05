// ./component/SearchBox.js
import React, { useState } from "react";

const SearchBox = ({ setCity }) => {
  const [inputValue, setInputValue] = useState("");

  const handleKeyPress = (e) => {
    if (e.key === "Enter" && inputValue.trim()) {
      setCity(inputValue.trim());
      setInputValue("");
    }
  };

  const handleSearchClick = () => {
    if (inputValue.trim()) {
      setCity(inputValue.trim());
      setInputValue("");
    }
  };

  return (
    <div className="search-box">
      <input
        type="text"
        className="search-input"
        placeholder="도시 이름을 입력하세요"
        value={inputValue}
        onChange={(e) => setInputValue(e.target.value)}
        onKeyDown={handleKeyPress}
      />
      <button className="search-button" onClick={handleSearchClick}>
        검색
      </button>
    </div>
  );
};

export default SearchBox;