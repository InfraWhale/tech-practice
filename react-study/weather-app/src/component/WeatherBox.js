import React from 'react'

const WeatherBox = ({ weather }) => {
  if (!weather) {
    return (
      <div className="weather-box">
        <h3>날씨 정보를 조회하지 못했습니다.</h3>
      </div>
    );
  }
  console.log(weather);
  const weatherMain = weather?.weather[0].main;
  const emojiMap = {
    Clear: "☀️",
    Clouds: "☁️",
    Rain: "🌧️",
    Drizzle: "🌦️",
    Thunderstorm: "⛈️",
    Snow: "❄️",
    Mist: "🌫️",
    Smoke: "💨",
    Haze: "🌁",
    Dust: "🌬️",
    Fog: "🌫️",
    Sand: "🏜️",
    Ash: "🌋",
    Squall: "🌪️",
    Tornado: "🌪️"
  };

  const emoji = emojiMap[weatherMain] || "🌈"; // 못 찾으면 기본값

  return (
    <div className="weather-box">
      <h3>🏙 {weather?.name}</h3>
      <h2>
         {weather?.main.temp.toFixed(1)} °C / {(weather?.main.temp * 1.8 + 32).toFixed(1)} °F
      </h2>
      <h3>{emoji} {weather?.weather[0].description}</h3>
    </div>
  );
};


export default WeatherBox