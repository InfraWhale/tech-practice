import React from 'react'
import Button from 'react-bootstrap/Button';

const WeatherButton = ({ cities, city, setCity }) => {
    //console.log(cities);
    return (
    <div>
        <Button 
            variant={city === "" ? "primary" : "warning"}
            onClick={ () => setCity("")} 
        >Current Location</Button>
        {cities.map((item) => (
            <Button 
                variant={city === item ? "primary" : "warning"}
                onClick={ () => setCity(item)}
            >{item}</Button>
        ))}
    </div>
  )
}

export default WeatherButton
