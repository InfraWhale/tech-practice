// src/App.js

import React, { useState } from "react";

// const App = () => {
//   const [value, setValue] = useState("");
//   const onChangeHandler = (event) => {
//     const inputValue = event.target.value;
//     setValue(inputValue);
//   };
//   console.log(value);

//   return (
//     <div>
//       <input type="text" onChange={onChangeHandler} value={value} />
//     </div>
//   );
// };

function App() {
  const [number, setNumber] = useState(0);

  const numUp = () => {
    setNumber(number + 1)
  }
  const numDown = () => {
    setNumber(number - 1)
  }

  return (
    <div>
      <div>{number}</div>
      <button onClick={numUp}>+ 1</button>
      <button onClick={numDown}>- 1</button>
    </div>
  );
}

export default App;