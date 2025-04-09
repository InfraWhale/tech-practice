import logo from './logo.svg';
import './App.css';
import { useState } from "react";
import CounterBox from './components/CounterBox';
import counterStore from './stores/CounterStore';

function App() {
  //const [count, setCount] = useState(0);
  const { count, increase, decrease, increaseBy} = counterStore();
  return (
    <div className="App">
      <h1>count: {count} </h1>
      <button onClick={increase}>increase</button>
      <button onClick={decrease}>decrease</button>
      <button onClick={()=>increaseBy(10)}>10씩 증가</button>
      <button onClick={()=>increaseBy(20)}>20씩 증가</button>
      <CounterBox />
    </div>
  );
}

export default App;
