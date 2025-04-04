import React from 'react';
import Button from 'react-bootstrap/Button';

const WeatherButton = ({ cities, city, setCity }) => {
  return (
    <div className="weather-buttons">
      <Button
        variant={city === "" ? "primary" : "outline-light"}
        onClick={() => setCity("")}
      >
        Current Location
      </Button>
      {cities.map((item, index) => (
        <Button
          key={index}
          variant={city === item ? "primary" : "outline-light"}
          onClick={() => setCity(item)}
        >
          {item}
        </Button>
      ))}
    </div>
  );
};

export default WeatherButton;