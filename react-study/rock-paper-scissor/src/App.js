import { useState } from 'react';
import './App.css';
import Box from './component/Box';
// TODO
// 1. 박스 2개 (타이틀, 사진, 결과)
// 2. 가위 바위 보 버튼
// 3. 버튼을 클릭하면 클릭한 값이 박스에 보임
// 4. 컴퓨터는 랜덤하게 아이템 선택이 된다.
// 5. 3 & 4의 결과를 가지고 누가 이겼는지 승패를 따진다.
// 6. 승패 결과에 따라 테두리 색이 바뀐다 (이기면 초록 / 지면 빨강 / 비기면 검정)

const choice = {
  rock:{
    name:"Rock",
    img:"https://wimg.heraldcorp.com/content/default/2017/10/25/20171025000602_0.jpg"
  },
  scissors:{
    name:"Scissors",
    img:"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuNvlz2gQ86WhKTAbNBx1SSJkIwjgKmxMyaA&s"    
  },
  paper:{
    name:"Paper",
    img:"https://cdn.sisajournal.com/news/photo/202205/238900_150212_4536.jpg" 
  }
}

let itemArray = Object.keys(choice); // 객체의 키 값만 뽑아서 Array로 만듬 ['rock', 'scissors', 'paper'];

function App() {
  const [userSelect, setUserSelect] = useState(null);
  const [computerSelect, setComputerSelect] = useState(null);
  const [userResult, setUserResult] = useState("");
  const [computerResult, setComputerResult] = useState("");

  const play = (userChoice) => {
    setUserSelect(choice[userChoice]);

    let computerChoice = randomChoice();
    setComputerSelect(computerChoice);

    setUserResult(judgement(choice[userChoice], computerChoice));
    setComputerResult(judgement(computerChoice, choice[userChoice]));
  };

  const judgement = (user, computer) => {
    if(user.name === computer.name) return "tie";   
    else if(user.name === "Rock") return computer.name === "Scissors" ? "win" : "lose";  
    else if(user.name === "Scissors") return computer.name === "Paper" ? "win" : "lose";  
    else return computer.name === "Rock" ? "win" : "lose";
  }

  const randomChoice = () => {
    let randomItem = Math.floor(Math.random() * itemArray.length);
    let final = itemArray[randomItem];
    return choice[final];
  }

  return (
    <div>
      <div className="main">
        <Box title="You" item={userSelect} result={userResult}/>
        <Box title="Computer" item={computerSelect} result={computerResult}/>
      </div>
      <div className="main">
        <button onClick={() => play("scissors")}>가위</button>
        <button onClick={() => play("rock")}>바위</button>
        <button onClick={() => play("paper")}>보</button>
      </div>
    </div>
  );
}

export default App;
