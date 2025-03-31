import { useState, useEffect, useRef } from 'react';
import './App.css';
import Box from './component/Box';

const choice = {
  rock: {
    name: "Rock",
    img: "https://wimg.heraldcorp.com/content/default/2017/10/25/20171025000602_0.jpg"
  },
  scissors: {
    name: "Scissors",
    img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuNvlz2gQ86WhKTAbNBx1SSJkIwjgKmxMyaA&s"
  },
  paper: {
    name: "Paper",
    img: "https://cdn.sisajournal.com/news/photo/202205/238900_150212_4536.jpg"
  }
};

const itemArray = Object.keys(choice);

function App() {
  const [gameState, setGameState] = useState("ready"); // ready, playing, result
  const [userSelect, setUserSelect] = useState(null);
  const [computerSelect, setComputerSelect] = useState(null);
  const [userResult, setUserResult] = useState("");
  const [computerResult, setComputerResult] = useState("");
  const intervalRef = useRef(null);

  // 각 이미지를 브라우저 캐시에 저장
  useEffect(() => {
    Object.values(choice).forEach((item) => {
      const img = new Image();
      img.src = item.img;
    });
  }, []);

  useEffect(() => {
    if (gameState === "playing") {
      intervalRef.current = setInterval(() => {
        let random = randomChoice(); 
        setComputerSelect(random);
      }, 30);
    }

    return () => clearInterval(intervalRef.current);
  }, [gameState]);

  const startGame = () => {
    setUserSelect(null);
    setComputerSelect(null);
    setUserResult("");
    setComputerResult("");
    setGameState("playing");
  };

  const endGame = (userChoiceKey) => {
    clearInterval(intervalRef.current);

    const userChoice = choice[userChoiceKey];
    const finalComputerChoice = computerSelect;

    setUserSelect(userChoice);

    const userRes = judgement(userChoice, finalComputerChoice);
    const computerRes = judgement(finalComputerChoice, userChoice);

    setUserResult(userRes);
    setComputerResult(computerRes);

    setGameState("result");
  };

  const randomChoice = () => {
    const randomIndex = Math.floor(Math.random() * itemArray.length);
    return choice[itemArray[randomIndex]];
  };

  const judgement = (user, computer) => {
    if (user.name === computer.name) return "tie";
    else if (user.name === "Rock") return computer.name === "Scissors" ? "win" : "lose";
    else if (user.name === "Scissors") return computer.name === "Paper" ? "win" : "lose";
    else return computer.name === "Rock" ? "win" : "lose";
  };

  return (
    <div className="app">
      <h1 className="title">💖 마블리 가위바위보 게임 💖</h1>

      <div className="main">
        <Box title="You" item={userSelect} result={userResult} />
        <Box title="Computer" item={computerSelect} result={computerResult} />
      </div>

      {/* 상태별 버튼 영역 */}
      {gameState === "ready" && (
        <button className="game-button start-btn" onClick={startGame}>게임 시작하기</button>
      )}

      {gameState === "playing" && (
        <div className="button-area">
          <button className="game-button scissors-btn" onClick={() => endGame("scissors")}>✌️ 가위</button>
          <button className="game-button rock-btn"onClick={() => endGame("rock")}>✊ 바위</button>
          <button className="game-button paper-btn"onClick={() => endGame("paper")}>🖐️ 보</button>
        </div>
      )}

      {gameState === "result" && (
        <button className="game-button start-btn" onClick={startGame}>다시 시작하기</button>
      )}
    </div>
  );
}

export default App;
