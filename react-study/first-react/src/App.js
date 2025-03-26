import { useState } from 'react';
import './App.css';

function App() {
  let counter = 0; // 리렌더링 시, 컴포넌트 안에 있는 state가 아닌 변수는 초기화가 된다.
  const [counter2, setCounter2] = useState(0);
  const increase = () => {
    counter = counter + 1; // 그래서 console.log 찍으면 counter는 0+1 => 1로 보일 것이다.
    setCounter2(counter2 + 1); // 이거만 바뀔 때 비동기적으로 동작 : increase가 끝나면 이게 동작할 것임
    console.log("counter : ", counter, "counter2 state : ", counter2); // 그래서 counter2는 위의 결과가 바로 반영되지 않는다.
  }

  return (
    <div>
      <div>{counter}</div>
      <div>state:{counter2}</div>
      <button onClick={increase}>클릭!</button>
    </div>
  );
}

export default App;
