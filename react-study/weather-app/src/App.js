import {useEffect, useState} from "react"
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import WeatherBox from "./component/WeatherBox.js";
import WeatherButton from "./component/WeatherButton.js";
import SearchBox from "./component/SearchBox";
import ClipLoader from "react-spinners/ClipLoader";

// TODO
// 1. 앱이 실행되자마자 현재위치기반의 날씨가 보인다.
// 2. 날씨정보에는 도시, 섭씨, 화씨, 날씨상태가 있다.
// 3. 5개의 버튼이 있다. (1개 현재위치, 4개 다른 도시)
// 4. 도시 버튼을 클릭할때 마다 도시의 정보가 바뀐다.
// 5. 현재위치 버튼 누르면 현재위치로 온다.
// 6. 데이터 들고오는 동안 로딩 스피너가 돈다.

function App() {
  const appid = process.env.REACT_APP_WEATHER_API_KEY;
  const [weather,setWeather] = useState(null);
  const [city, setCity] = useState('');
  const [loading, setLoading] = useState(false);
  const cities = ['Paris', 'New York', 'Tokyo', 'Seoul'];

  const getCurrentLocation = () => {
    navigator.geolocation.getCurrentPosition((position) => {
      let lat = position.coords.latitude;
      let lon = position.coords.longitude;
      getWeatherByCurrentLocation(lat, lon);
    });
  };

  const getWeatherByCurrentLocation = async(lat, lon)=> {
    try {
      setLoading(true);
      let url =`https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${appid}&units=metric`;
      let response = await fetch(url);

      if(!response.ok) throw new Error("현재 위치 날씨 정보 요청 실패");

      let data = await response.json();
      setWeather(data);
    } catch (err){
      setWeather(null);
      alert("현재 위치 날씨 정보를 불러오지 못했습니다.");
    } finally {
      setLoading(false);
    }
  };

  const getWeatherByCity = async()=> {
    try {
      setLoading(true);
      let url =`https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${appid}&units=metric`;
      let response = await fetch(url);

      if(!response.ok) throw new Error("도시 날씨 정보 요청 실패");

      let data = await response.json();
      setWeather(data);
    } catch (err){
      setWeather(null);
      alert("도시 날씨 정보를 불러오지 못했습니다.");
    } finally {
      setLoading(false);
    }
  };
  
  useEffect(() => {
    if (city === "") getCurrentLocation();
    else getWeatherByCity();
  }, [city])

  return (
    <div>
      {loading ? (
        <div className="container">
          <ClipLoader color="#f88c6b" loading={loading} size={150} />
        </div>
      ) : (
        <div className="container">
          <WeatherBox weather={weather}/>
          <WeatherButton cities={cities} city = {city} setCity = {setCity} />
          <SearchBox setCity={setCity} />
        </div>
      )}
    </div>
  );
}

export default App;
